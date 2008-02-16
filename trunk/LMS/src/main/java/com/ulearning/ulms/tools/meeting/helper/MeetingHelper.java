/**
 * Copyright (c)2006 Beijing WenhuaOnline Sci-tech DevelopmentCo.,Ltd. 
 * All rights reserved. 
 *
 * User: Fengch
 * Date: 2007-11-8 10:16:26
 */


package com.ulearning.ulms.tools.meeting.helper;

import com.ulearning.ulms.core.util.Calendar;
import com.ulearning.ulms.core.util.PagerList;
import com.ulearning.ulms.core.util.ValidateUtil;
import com.ulearning.ulms.tools.meeting.dao.MeetingDAO;
import com.ulearning.ulms.tools.meeting.dao.MeetingDAOFactory;
import com.ulearning.ulms.tools.meeting.exception.MeetingSysException;
import com.ulearning.ulms.tools.meeting.model.MeetingModel;
import com.ulearning.ulms.tools.meeting.util.MeetingKeys;
import com.ulearning.ulms.tools.meeting.xuechuang.client.SetMeetingClient;
import com.ulearning.ulms.tools.meeting.xuechuang.client.SetMeetingStub;
import com.ulearning.ulms.util.LMSConstants;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

public class MeetingHelper
{
        protected static Log logger = LogFactory.getLog(MeetingHelper.class);
        private static MeetingDAO meetingDAO;

        static
        {
                try
                {
                        meetingDAO = MeetingDAOFactory.getDAO();
                }
                catch (Exception ex)
                {
                        ex.printStackTrace();
                }
        }


        /**
         * 返回课程（班级）的学窗会议列表
         *
         * @param courseID 课程（班级）ID
         * @param pageNo
         * @param pageSize
         * @return
         * @throws com.ulearning.ulms.tools.meeting.exception.MeetingSysException
         *
         */
        public static PagerList getMeetingsByCourse(int courseID, int auditStatus, int pageNo,
                                                    int pageSize) throws MeetingSysException
        {
                return meetingDAO.getMeetings(-1, courseID, MeetingKeys.TYPE_COURSE,
                        MeetingKeys.MEETINGTYPE_XUECHUANG, auditStatus, -1, pageNo, pageSize);
        }

        /**
         * 返回课程（班级）的学窗会议列表
         *
         * @param auditStatus 审核状态
         * @param pageNo
         * @param pageSize
         * @return
         * @throws com.ulearning.ulms.tools.meeting.exception.MeetingSysException
         *
         */
        public static PagerList getAllMeetings(int aspID, int auditStatus, int pageNo,
                                               int pageSize) throws MeetingSysException
        {
                return meetingDAO.getMeetings(aspID, -1, MeetingKeys.TYPE_COURSE,
                        MeetingKeys.MEETINGTYPE_XUECHUANG, auditStatus, -1, pageNo, pageSize);
        }

        /**
         * 返回班级可用的一个会议的地址
         *
         * @return
         * @throws com.ulearning.ulms.tools.meeting.exception.MeetingSysException
         *
         */
        public static String getAvailableOneMeetingHrefByCourse(HttpServletRequest request, int courseID) throws MeetingSysException
        {
                String href = "javascript:alert('会议不存在，可能还未发布或者已被删除！')";
                MeetingModel model = meetingDAO.getAvailableOneMeetingByCourse(courseID);
                int userID= NumberUtils.toInt((String)request.getSession().getAttribute(LMSConstants.USERID_KEY));


                if (model != null)
                {
                        HttpSession session = request.getSession();
                        String loginName = (String) session.getAttribute(LMSConstants.LOGINNAME_KEY);
                        String name = (String) session.getAttribute(LMSConstants.LOGINNAME_KEY_NAME);
                        if (StringUtils.isBlank(name))
                        {
                                name = loginName;
                        }
                        href = SetMeetingClient.getEnterCodeByUserId(userID,model.getMeetingID(), name, model.getCompereCode());
                }
                return href;
        }

        public static MeetingModel getMeeting(int id) throws MeetingSysException
        {
                return meetingDAO.getMeeting(id);
        }

        public static void addMeeting(MeetingModel meeting) throws MeetingSysException
        {
                meeting.setLastUpdateDate(new Date());
                meeting.setCreateDate(new Date());
                meetingDAO.addMeeting(meeting);
        }

        public static void updateMeeting(int id, String name, String summary, int cameras, int capacity,
                                         String compereCode, Date startTime, Date endTime, String fixed)
                throws MeetingSysException
        {
                MeetingModel meeting = meetingDAO.getMeeting(id);
                meeting.setName(name);
                meeting.setSummary(summary);
                meeting.setCameras(cameras);
                meeting.setCapacity(capacity);
                meeting.setCompereCode(compereCode);
                meeting.setStartTime(startTime);
                meeting.setEndTime(endTime);
                meeting.setFixed(fixed);
                meeting.setLastUpdateDate(new Date());
                meetingDAO.updateMeeting(meeting);
        }

        /**
         * 审核会以
         *
         * @param ids           会议列表
         * @param services      主从服务器
         * @param primaryServer 主服务器
         * @param auditStatus
         * @throws MeetingSysException
         */
        public static void auditMeetings(int[] ids, String services, int primaryServer, int auditStatus, String fixed) throws MeetingSysException
        {
                meetingDAO.auditMeetings(ids, services, primaryServer, auditStatus, fixed);

        }

        public static void deleteMeeting(int id) throws MeetingSysException
        {
                meetingDAO.deleteMeeting(id);
        }

        public static void deleteMeeting(int[] ids) throws MeetingSysException
        {
                meetingDAO.deleteMeeting(ids);
        }

        /**
         * 删除课程的所有在线课堂
         * @param courseID
         * @throws MeetingSysException
         */
        public static void deleteMeetingByCourseID(int courseID) throws MeetingSysException
        {
                try
                {
                        PagerList pl = getMeetingsByCourse(courseID, -1, 0,
                                10000);
                        List list = pl.getPagerList();
                        if (!list.isEmpty())
                        {
                                int[] ids = new int[list.size()];
                                for (int i = 0; i < list.size(); i++)
                                {
                                        MeetingModel meetingModel = (MeetingModel) list.get(i);
                                        ids[i] = meetingModel.getId();
                                }
                                meetingDAO.deleteMeeting(ids);
                        }
                }
                catch (Exception e)
                {
                        e.printStackTrace(); 
                }
        }

        /**
         * 若存在,则修改会议的主从服务器.
         *
         * @param meet
         * @return
         */
        public static int generateXuechuangMeeting(MeetingModel meet)
        {
                int id = -1;

                try
                {
                        if (meet.getMeetingID() <= 0)
                        {
                                String name = meet.getName();
                                String services = meet.getServices();
                                int createrId = meet.getCreaterId();
                                int cameras = meet.getCameras();
                                String info = meet.getSummary();
                                int capacity = meet.getCapacity();
                                boolean secrecy = ValidateUtil.isTrue(meet.getSecrecy());
                                boolean specify = ValidateUtil.isTrue(meet.getSpecify());
                                boolean fixed = ValidateUtil.isTrue(meet.getFixed());
                                boolean enabledREC = ValidateUtil.isTrue(meet.getEnabledREC());

                                java.util.Calendar createTime = java.util.Calendar.getInstance();
                                java.util.Calendar startTime = (new Calendar(meet.getStartTime())).getCalendar();
                                java.util.Calendar endTime = (new Calendar(meet.getEndTime())).getCalendar();

                                String compereCode = StringUtils.trimToEmpty(meet.getCompereCode());
                                String specifyCode = StringUtils.trimToEmpty(meet.getSpecifyCode());
                                int lectureSize = meet.getLectureSize();
                                String lectureURL = StringUtils.trimToEmpty(meet.getLectureURL());
                                String recordURL = StringUtils.trimToEmpty(meet.getRecordURL());
                                int status = meet.getStatus();

                                SetMeetingStub.MeetingInfo meetingInfo = new SetMeetingStub.MeetingInfo();
                                meetingInfo.setName(name);
                                meetingInfo.setCameras(cameras);
                                meetingInfo.setCapacity(capacity);
                                meetingInfo.setCompereCode(compereCode);
                                meetingInfo.setCreaterID(createrId);
                                meetingInfo.setCreateTime(createTime);
                                meetingInfo.setEnabledREC(enabledREC);
                                meetingInfo.setEndTime(endTime);
                                meetingInfo.setFixed(fixed);
                                meetingInfo.setInfo(info);
                                meetingInfo.setLectureSize(lectureSize);
                                meetingInfo.setLectureURL(lectureURL);
                                meetingInfo.setRecordURL(recordURL);
                                meetingInfo.setSecrecy(secrecy);
                                meetingInfo.setServices(services);
                                meetingInfo.setSpecify(specify);
                                meetingInfo.setSpecifyCode(specifyCode);
                                meetingInfo.setStartTime(startTime);
                                meetingInfo.setStatus(status);
                                id = SetMeetingClient.addMeeting(meetingInfo);
                        }
                        else
                        {
                                SetMeetingStub.MeetingInfo meetingInfo = SetMeetingClient.getMeeting(meet.getMeetingID());
                                meetingInfo.setServices(meet.getServices());
                                SetMeetingClient.editMeeting(meetingInfo);
                                id=meetingInfo.getMeetingID();
                        }
                }
                catch (Exception e)
                {
                        logger.info("Error:", e);
                }
                return id;
        }

        /**
         * 统计在某个时间段内服务器被分配为主服务器的会议个数.
         *
         * @param primaryServer
         * @param startTime
         * @param endTime
         * @return
         * @throws MeetingSysException
         */
        public static int getMeetingPrimaryServerCount(int primaryServer, Date startTime, Date endTime) throws MeetingSysException
        {
                return meetingDAO.getMeetingPrimaryServerCount(primaryServer, startTime, endTime);
        }
}
