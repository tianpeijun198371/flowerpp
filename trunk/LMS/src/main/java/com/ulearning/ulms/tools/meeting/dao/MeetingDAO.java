/**
 * Copyright (c)2006 Beijing WenhuaOnline Sci-tech DevelopmentCo.,Ltd.
 * All rights reserved.
 *
 * User: Fengch
 * Date: 2007-11-8 10:15:32
 */
package com.ulearning.ulms.tools.meeting.dao;

import com.ulearning.ulms.core.util.PagerList;
import com.ulearning.ulms.tools.meeting.exception.MeetingSysException;
import com.ulearning.ulms.tools.meeting.model.MeetingModel;

import java.util.Date;
import java.util.List;


public interface MeetingDAO {
    /**
     * 返回会议列表
     * @param aspID
     * @param relationID  -1.则表示不限
     * @param type      -1.则表示不限
     * @param meetingType    -1.则表示不限
     * @param auditStatus   -1.则表示不限
     * @param status         -1.则表示不限
     * @param pageNo
     * @param pageSize
     * @return
     * @throws com.ulearning.ulms.tools.meeting.exception.MeetingSysException
     */
    public PagerList getMeetings(int aspID, int relationID, int type,
        int meetingType, int auditStatus, int status, int pageNo, int pageSize)
        throws MeetingSysException;

    /**
     * 返回班级可用的一个会议
     * @return
     * @throws com.ulearning.ulms.tools.meeting.exception.MeetingSysException
     */
    public MeetingModel getAvailableOneMeetingByCourse(int courseID)
        throws MeetingSysException;

    public MeetingModel getMeeting(int id) throws MeetingSysException;

    public void addMeeting(MeetingModel meeting) throws MeetingSysException;

    public void updateMeeting(MeetingModel meeting) throws MeetingSysException;

    /**
     * 审核会以
     * @param ids  会议列表
     * @param services 主从服务器
     * @param primaryServer 主服务器
     * @param auditStatus
     * @throws MeetingSysException
     */
    public void auditMeetings(int[] ids, String services, int primaryServer,
        int auditStatus, String fixed) throws MeetingSysException;

    public void deleteMeeting(int id) throws MeetingSysException;

    public void deleteMeeting(int[] ids) throws MeetingSysException;

    /**
     * 统计在某个时间段内服务器被分配为主服务器的会议个数.
     * @param primaryServer
     * @param startTime
     * @param endTime
     * @return
     * @throws MeetingSysException
     */
    public int getMeetingPrimaryServerCount(int primaryServer, Date startTime,
        Date endTime) throws MeetingSysException;
}
