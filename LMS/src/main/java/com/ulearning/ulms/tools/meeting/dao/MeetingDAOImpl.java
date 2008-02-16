/**
 * Copyright (c)2006 Beijing WenhuaOnline Sci-tech DevelopmentCo.,Ltd. 
 * All rights reserved. 
 *
 * User: Fengch
 * Date: 2007-11-8 10:15:45
 */


package com.ulearning.ulms.tools.meeting.dao;

import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.core.util.PagerList;
import com.ulearning.ulms.tools.meeting.exception.MeetingAppException;
import com.ulearning.ulms.tools.meeting.exception.MeetingSysException;
import com.ulearning.ulms.tools.meeting.helper.MeetingHelper;
import com.ulearning.ulms.tools.meeting.model.MeetingModel;
import com.ulearning.ulms.tools.meeting.util.MeetingKeys;
import com.ulearning.ulms.util.HibernateDAO;
import com.ulearning.ulms.util.HibernateUtil;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.MappingException;
import net.sf.hibernate.Session;
import net.sf.hibernate.Query;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.sql.SQLException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class MeetingDAOImpl implements MeetingDAO
{
        protected static Log logger = LogFactory.getLog(MeetingDAOImpl.class);

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
        public PagerList getMeetings(int aspID,int relationID,int type,int meetingType,int auditStatus,int status,int pageNo,
                                                  int pageSize) throws MeetingSysException
        {
                int cruentIndex = pageNo * pageSize;
                PagerList pl = new PagerList();
                List list = null;
                try
                {
                        //取数据
                        String hql = "from MeetingModel";

                        String tmpCondition = "";
                        if(aspID!=-1 && relationID==-1)
                        {
                                tmpCondition+=" and  aspID=" +aspID;

                        }
                        if(type!=-1)
                        {
                                tmpCondition+=" and  type='" +type+"'";

                        }
                        if(meetingType!=-1)
                        {
                                tmpCondition+=" and  meetingType='" +meetingType+"'";
                        }
                        if(relationID!=-1)
                        {
                                tmpCondition+=" and  relationID=" +relationID;
                        }
                        if(status!=-1)
                        {
                                tmpCondition+=" and   status=" +status;
                        }
                        if(auditStatus!=-1)
                        {
                                tmpCondition+=" and   auditStatus=" +auditStatus;
                        }


                        if (!tmpCondition.equals(""))
                        {
                                tmpCondition = tmpCondition.substring(5);
                                tmpCondition=" where " + tmpCondition;
                        }
                        hql+=tmpCondition;
                        logger.info("hql="+(hql+" order by id desc"));
                        list = HibernateDAO.find(hql+" order by id desc", cruentIndex, pageSize);

                        int totalCount = 0;
                        //取总记录数
                        String hqlCount = "select count(*) " + hql;
                        List hqlCountList = HibernateDAO.find(hqlCount);
                        Iterator it = hqlCountList.iterator();

                        if (it.hasNext())
                        {
                                Object row = it.next();
                                totalCount = ((Integer) row).intValue();
                        }
                        pl.setPageSize(pageSize);
                        pl.setPageNo(pageNo);
                        pl.setTotalCount(totalCount);
                        pl.setPagerList(list);
                }
                catch (Exception se)
                {
                        se.printStackTrace();
                }

                return pl;
        }

        /**
         * 返回班级可用的一个会议
         * @return
         * @throws com.ulearning.ulms.tools.meeting.exception.MeetingSysException
         */
        public MeetingModel getAvailableOneMeetingByCourse(int courseID) throws MeetingSysException
        {

                MeetingModel model = null;
                Session session = null;
                try
                {
                        session = HibernateUtil.getSession();
                        //取数据
                        String hql = "from MeetingModel where type=" + MeetingKeys.TYPE_COURSE +
                               " and  relationID=" +courseID +
                                " and auditStatus='" +MeetingKeys.AUDIT_APPROVED+"'";

                        String visitSQL = " and (" +
                                        "(startTime IS NULL and startTime IS NULL)" +
                                        "or (startTime IS NULL and startTime IS NOT NULL and " +
                                        "startTime >=  :now_day)" +
                                        " or (startTime IS NOT NULL and startTime<=   :now_day and startTime IS NULL)" +
                                        " or (startTime IS NOT NULL and startTime<=   :now_day and startTime IS NOT NULL and " +
                                        " startTime >=  :now_day)" + " )";

                        hql+=visitSQL + " order by id desc";

                        logger.info("hql="+(hql));

                        Query q = session.createQuery(hql);
                        Date now = new Date();
                        q.setDate("now_day", now);
                        List list=q.list();
                        if(!list.isEmpty())
                        {
                                model=(MeetingModel)list.get(0);
                        }
                }
                catch (Exception se)
                {
                        se.printStackTrace();
                }
                return model;
        }

        public MeetingModel getMeeting(int id)  throws MeetingSysException
        {
                return (MeetingModel)HibernateDAO.load(MeetingModel.class,
                                new Integer(id));
        }

        public void addMeeting(MeetingModel meeting)  throws MeetingSysException
        {
                 HibernateDAO.add(meeting);
        }

        public void updateMeeting(MeetingModel meeting)  throws MeetingSysException
        {
                 HibernateDAO.update(meeting);
        }

        public void deleteMeeting(int id)  throws MeetingSysException
        {
                String hql = " from MeetingModel where id=" +id;

                try
                {
                        HibernateDAO.delete(hql);
                }
                catch (ULMSSysException e)
                {
                        logger.info(e);
                        throw new MeetingSysException(e);
                }
        }

        /**
         * 审核会以
         * @param ids  会议列表
         * @param services 主从服务器
         * @param primaryServer 主服务器
         * @param auditStatus
         * @throws MeetingSysException
         */
        public void auditMeetings(int[] ids,String services,int primaryServer,int auditStatus,String fixed)  throws MeetingSysException
        {
                Session session = null;
                try
                {
                        session = HibernateUtil.getSession();
                        for (int i = 0; i < ids.length; i++)
                        {
                                MeetingModel model=(MeetingModel)session.load(MeetingModel.class,
                                        new Integer(ids[i]));
                                model.setAuditStatus(auditStatus);
                                model.setPrimaryServer(primaryServer);
                                model.setFixed(fixed);
                                if(auditStatus== MeetingKeys.AUDIT_APPROVED)
                                {
                                        model.setServices(services);
                                        int meetingID=MeetingHelper.generateXuechuangMeeting(model);
                                        if(meetingID==-1)
                                                throw new MeetingAppException("学窗服务器WebService有问题，请检查配置！");
                                        model.setMeetingID(meetingID);
                                }
                        }
                        session.flush();
                        session.clear();
                        session.connection().commit();
                }
                catch (Exception e)
                {
                        throw new MeetingSysException("Exception while Hibernate :  " + e, e);
                }
                finally
                {
                        try
                        {
                                HibernateUtil.releaseSession(session);
                        }
                        catch (HibernateException e)
                        {
                                throw new MeetingSysException("HibernateException while Hibernate releaseSession:  " + e, e);
                        }
                }
        }

        public void deleteMeeting(int[] ids)  throws MeetingSysException
        {
                String tmpCondition = "";

                for (int i = 0; i < ids.length; i++)
                {
                        tmpCondition = tmpCondition + " or id=" + ids[i];
                }

                if (!tmpCondition.equals(""))
                {
                        tmpCondition = tmpCondition.substring(4);
                }

                String hql = " from MeetingModel where " + tmpCondition;

                try
                {
                        HibernateDAO.delete(hql);
                }
                catch (ULMSSysException e)
                {
                        logger.info(e);
                        throw new MeetingSysException(e);
                }
        }

        /**
         * 统计在某个时间段内服务器被分配为主服务器的会议个数.
         * @param primaryServer
         * @param startTime
         * @param endTime
         * @return
         * @throws MeetingSysException
         */
        public int getMeetingPrimaryServerCount(int primaryServer, Date startTime,Date endTime) throws MeetingSysException
        {
                int count=0;
                Session session = null;

                try
                {
                        session = HibernateUtil.getSession();
                        String hql = "select count(*) from MeetingModel where primaryServer=:primaryServer and " +
                                "(startTime >= :startTime or endTime<=:endTime)";
                        Query query = session.createQuery(hql);
                        query.setParameter("primaryServer", new Integer(primaryServer));
                        query.setParameter("startTime", startTime);
                        query.setParameter("endTime", endTime);

                        logger.info("hql="+hql);
                        //取总记录数
                        String hqlCount = "select count(*) " + hql;
                        List hqlCountList = query.list();

                        if (!hqlCountList.isEmpty())
                        {
                                Object row = hqlCountList.get(0);
                                count = ((Integer) row).intValue();
                        }
                }
                catch (Exception se)
                {
                        logger.info("Error:",se);
                }

                return count;
        }

}
