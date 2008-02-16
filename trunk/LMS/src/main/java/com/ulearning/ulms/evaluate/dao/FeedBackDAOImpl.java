/**
 * FeedBackDAO.java.
 * User: yud Date: 2005-6-29 10:11:58
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.evaluate.dao;

import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.core.util.PagerList;
import com.ulearning.ulms.evaluate.exceptions.EvaluateManageSysException;
import com.ulearning.ulms.evaluate.model.FeedBackModel;
import com.ulearning.ulms.evaluate.model.FeedBackOptionModel;
import com.ulearning.ulms.evaluate.util.FeedBackConstants;
import com.ulearning.ulms.util.HibernateDAO;
import com.ulearning.ulms.util.HibernateUtil;
import com.ulearning.ulms.familyeducation.exception.FamilyEducationSysException;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Iterator;
import java.util.List;

import net.sf.hibernate.Session;
import net.sf.hibernate.Query;


public class FeedBackDAOImpl implements FeedBackDAO
{
        protected static Log logger = LogFactory.getLog(FeedBackDAOImpl.class);
        /**
         * add a FeedBack.
         *
         * @param am
         * @throws com.ulearning.ulms.evaluate.exceptions.EvaluateManageSysException
         *
         */
        public void insert(FeedBackModel am) throws EvaluateManageSysException
        {
                try
                {
                        HibernateDAO.add(am);
                }
                catch (ULMSSysException e)
                {
                        throw new EvaluateManageSysException(e);
                }
        }

        /**
         * get a FeedBack.
         *
         * @param feedBackID
         * @throws EvaluateManageSysException
         */
        public FeedBackModel get(int feedBackID) throws EvaluateManageSysException
        {
                String hql = " from FeedBackModel model where model.feedbackID=" + feedBackID;
                List list = null;
                FeedBackModel feedBackModel = null;

                try
                {
                        list = HibernateDAO.find(hql);
                }
                catch (ULMSSysException e)
                {
                        throw new EvaluateManageSysException(e);
                }

                if (list != null)
                {
                        feedBackModel = (FeedBackModel) list.get(0);
                }

                return feedBackModel;
        }

        /**
         * 返回回复.
         *
         * @return
         * @throws EvaluateManageSysException
         */
        public PagerList getReplys(int feedBackID,int pageNo,int pageSize)
                throws EvaluateManageSysException
        {

                int cruentIndex = pageNo * pageSize;
                PagerList pl = new PagerList();
                List list = null;
                Session session = null;
                try
                {
                        session = HibernateUtil.getSession();
                        //取数据
                        String hql = " from FeedBackModel model where model.parentID="+feedBackID;
                                                              
                        logger.info("hql="+(hql+" order by model.feedbackID asc"));
                        Query q = session.createQuery(hql+" order by model.feedbackID desc");
                        if (-1 != cruentIndex)
                        {
                                q.setFirstResult(cruentIndex);
                        }
                        if (-1 != pageSize)
                        {
                                q.setMaxResults(pageSize);
                        }

                        list = q.list();

                        int totalCount = 0;
                        //取总记录数
                        String hqlCount = "select count(*) " + hql;

                        q = session.createQuery(hqlCount);
                        if (-1 != cruentIndex)
                        {
                                q.setFirstResult(cruentIndex);
                        }
                        if (-1 != pageSize)
                        {
                                q.setMaxResults(pageSize);
                        }
                        totalCount = ((Integer)q.uniqueResult()).intValue();

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
         * get list by relationID
         *
         * @return
         * @throws EvaluateManageSysException
         */
        public List getFeedBackByRelationID() throws EvaluateManageSysException
        {
                String hql = "select pm.relationID,count(pm.feedbackID) from FeedBackModel pm " +
                        " where type="+FeedBackConstants.STUDY_CENTER_TYPE+
                        " group by pm.relationID order by count(pm.feedbackID) desc";
                List list = null;

                try
                {
                        list = HibernateDAO.find(hql);
                }
                catch (ULMSSysException e)
                {
                        throw new EvaluateManageSysException(e);
                }

                return list;
        }

        /**
         * get list by orgID
         *
         * @param orgID
         * @return
         * @throws EvaluateManageSysException
         */
        public List getCheck(int orgID) throws EvaluateManageSysException
        {
                List tmList = null;
                String hql = null;

                hql = " from FeedBackModel where relationID=" + orgID
                        + " and type="+FeedBackConstants.STUDY_CENTER_TYPE;

                try
                {
                        tmList = HibernateDAO.find(hql);
                }
                catch (ULMSSysException e)
                {
                        throw new EvaluateManageSysException(e);
                }

                return tmList;
        }

        /**
         * get course list by score
         *
         * @return
         * @throws EvaluateManageSysException
         */
        public List getCourseCheck() throws EvaluateManageSysException
        {
                List tmList = null;
                String hql = null;

                hql = "select pm.relationID,count(pm.score) from FeedBackModel pm where " +
                        "type="+ FeedBackConstants.COURSE_TYPE+" group by pm.relationID order by count(pm.score) desc";

                try
                {
                        tmList = HibernateDAO.find(hql);
                }
                catch (ULMSSysException e)
                {
                        throw new EvaluateManageSysException(e);
                }

                return tmList;
        }

        /**
         * get course feedBack by User
         *
         * @param userID
         * @return
         * @throws EvaluateManageSysException
         */
        public List getCourseFeedBackByUser(int userID)
                throws EvaluateManageSysException
        {
                String hql = " from FeedBackModel where userID=" + userID +
                        " and type="+FeedBackConstants.COURSE_TYPE;
                List list = null;

                try
                {
                        list = HibernateDAO.find(hql);
                }
                catch (ULMSSysException e)
                {
                        throw new EvaluateManageSysException(e);
                }

                return list;
        }

        /**
         * get list by orgID and score
         *
         * @param orgID
         * @param score
         * @return
         * @throws EvaluateManageSysException
         */
        public List getByOrgAndScore(int orgID, int score)
                throws EvaluateManageSysException
        {
                List tmList = null;
                String hql = null;

                hql = " from FeedBackModel where relationID=" + orgID + " and score=" +
                        score+" and type="+FeedBackConstants.STUDY_CENTER_TYPE;

                try
                {
                        tmList = HibernateDAO.find(hql);
                }
                catch (ULMSSysException e)
                {
                        throw new EvaluateManageSysException(e);
                }

                return tmList;
        }

        /**
         * add a FeedBackOption
         *
         * @param am
         * @throws EvaluateManageSysException
         */
        public void addFeedBackOption(FeedBackOptionModel am)
                throws EvaluateManageSysException
        {
                try
                {
                        HibernateDAO.add(am);
                }
                catch (ULMSSysException e)
                {
                        throw new EvaluateManageSysException(e);
                }
        }

        /**
         * update a feedback
         *
         * @param am
         * @throws EvaluateManageSysException
         */
        public void updateFeedBackOption(FeedBackOptionModel am)
                throws EvaluateManageSysException
        {
                try
                {
                        HibernateDAO.update(am);
                }
                catch (ULMSSysException e)
                {
                        throw new EvaluateManageSysException(e);
                }
        }

        /**
         * get a model by orgID
         *
         * @param orgID
         * @return
         * @throws EvaluateManageSysException
         */
        public List getByOrgID(int orgID) throws EvaluateManageSysException
        {
                String hql = " from FeedBackOptionModel where relationID=" + orgID+
                        " and type="+FeedBackConstants.STUDY_CENTER_TYPE;
                List list = null;

                try
                {
                        list = HibernateDAO.find(hql);
                }
                catch (ULMSSysException e)
                {
                        throw new EvaluateManageSysException(e);
                }

                return list;
        }

        /**
         * get  FeedBacks.
         *
         * @param userID
         * @param relationID
         * @param type
         * @return
         * @throws EvaluateManageSysException
         */
        public List get(int userID, int relationID, int type)
                throws EvaluateManageSysException
        {
                String hql = " from FeedBackModel where userID=" + userID +
                        " and relationID=" + relationID + " and type='" + type + "'";
                List list = null;

                try
                {
                        list = HibernateDAO.find(hql);
                }
                catch (ULMSSysException e)
                {
                        throw new EvaluateManageSysException(e);
                }

                return list;
        }

        /**
         * get  FeedBacks.
         *
         * @param userID
         * @param relationID
         * @param type
         * @return
         * @throws EvaluateManageSysException
         */
        public PagerList get(int aspID,int userID, int relationID, int type,int pageNo,int pageSize)
                throws EvaluateManageSysException
        {

                int cruentIndex = pageNo * pageSize;
                PagerList pl = new PagerList();
                List list = null;
                try
                {
                        //取数据
                        String hql = " from FeedBackModel model";

                        String tmpCondition = "";
                        if(aspID!=-1)
                        {
                                tmpCondition+=" and  aspID=" +aspID;
                        }
                        if(userID!=-1)
                        {
                                tmpCondition+=" and  userID=" +userID;

                        }
                        if(relationID!=-1)
                        {
                                tmpCondition+=" and  relationID=" +relationID;

                        }
                        if(type!=-1)
                        {
                                tmpCondition+=" and  type=" +type;

                        }
                        if (!tmpCondition.equals(""))
                        {
                                tmpCondition = tmpCondition.substring(5);
                                tmpCondition=" where " + tmpCondition;
                        }
                        hql+=tmpCondition;
                        logger.info("hql="+(hql+" order by model.feedbackID desc"));
                        list = HibernateDAO.find(hql+" order by model.feedbackID desc", cruentIndex, pageSize);

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
         * search  FeedBacks.
         *
         * @param userID
         * @param relationID
         * @param type
         * @param feedbackID 订单号
         * @return
         * @throws EvaluateManageSysException
         */
        public PagerList search(int aspID,int userID, int relationID, int type,String userName,String relationName,
                                String keyword,int feedbackID,int pageNo,int pageSize)
                throws EvaluateManageSysException
        {

                int cruentIndex = pageNo * pageSize;
                PagerList pl = new PagerList();
                List list = null;
                Session session = null;
                try
                {
                        session = HibernateUtil.getSession();
                        //取数据
                        String hql = " from FeedBackModel model";

                        String tmpCondition = "";
                        if(aspID!=-1)
                        {
                                tmpCondition+=" and  aspID=" +aspID;

                        }
                        if(userID!=-1)
                        {
                                tmpCondition+=" and  userID=" +userID;

                        }
                        if(relationID!=-1)
                        {
                                tmpCondition+=" and  relationID=" +relationID;

                        }
                        if(type!=-1)
                        {
                                tmpCondition+=" and  type=" +type;

                        }
                        if(feedbackID!=-1 && feedbackID!=0)
                        {
                                tmpCondition+=" and  model.feedbackID=" +feedbackID;

                        }
                        if(!StringUtils.isBlank(userName))
                        {
                                tmpCondition+=" and  userName='" + StringEscapeUtils.escapeSql(userName)+"'";
                        }

                        if(!StringUtils.isBlank(relationName))
                        {
                                tmpCondition+=" and  relationName='" + StringEscapeUtils.escapeSql(relationName)+"'";
                        }
                        if(!StringUtils.isBlank(keyword))
                        {
                                tmpCondition+=" and  feedBack like :keyword";
                        }
                        if (!tmpCondition.equals(""))
                        {
                                tmpCondition = tmpCondition.substring(5);
                                tmpCondition=" where " + tmpCondition;
                        }
                        hql+=tmpCondition;
                        logger.info("hql="+(hql+" order by model.feedbackID desc"));
                         Query q = session.createQuery(hql+" order by model.feedbackID desc");
                        if (-1 != cruentIndex)
                        {
                                q.setFirstResult(cruentIndex);
                        }
                        if (-1 != pageSize)
                        {
                                q.setMaxResults(pageSize);
                        }
                        if(!StringUtils.isBlank(keyword))
                        {
                                q.setString("keyword", keyword);
                        }
                        list = q.list();

                        int totalCount = 0;
                        //取总记录数
                        String hqlCount = "select count(*) " + hql;

                        q = session.createQuery(hqlCount);
                        if (-1 != cruentIndex)
                        {
                                q.setFirstResult(cruentIndex);
                        }
                        if (-1 != pageSize)
                        {
                                q.setMaxResults(pageSize);
                        }
                        if(!StringUtils.isBlank(keyword))
                        {
                                q.setString("keyword", keyword);
                        }
                        totalCount = ((Integer)q.uniqueResult()).intValue();

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
         * 返回对某反馈的总参加人次.
         *
         * @param relationID
         * @param type
         * @return
         * @throws EvaluateManageSysException
         */
        public int getFeedBackPersonTimes(int relationID, int type)
                throws EvaluateManageSysException
        {
                String hql = "select count(*) from FeedBackModel where relationID=" + relationID +
                        " and type='" + type + "'";
                List list = null;
                int count=0;
                try
                {
                        list = HibernateDAO.find(hql);
                        Iterator it = list.iterator();

                        if (it.hasNext())
                        {
                                Object row = it.next();
                                count = ((Integer) row).intValue();
                        }
                }
                catch (ULMSSysException e)
                {
                        throw new EvaluateManageSysException(e);
                }

                return count;
        }

        /**
         * delete a FeedBack.
         *
         * @param feedBackID
         * @throws EvaluateManageSysException
         */
        public void delete(int feedBackID) throws EvaluateManageSysException
        {
                String hql = " from FeedBackModel model where model.feedbackID=" + feedBackID;

                try
                {
                        HibernateDAO.delete(hql);
                }
                catch (ULMSSysException e)
                {
                        throw new EvaluateManageSysException(e);
                }
        }

        //删除
        public  void delete(int[] ids)
                throws EvaluateManageSysException
        {
                String hql = " from FeedBackModel model";
                if(ids==null || ids.length==0)
                        return;

                for (int i = 0; i < ids.length; i++)
                {
                        int id = ids[i];
                        if(i==0)
                        {
                                 hql+=" where model.feedbackID=" +id;
                        }
                        else
                        {
                                hql+=" or model.feedbackID=" +id;
                        }
                }
                logger.info("hql="+hql);
                try
                {
                        HibernateDAO.delete(hql);
                }
                catch (ULMSSysException e)
                {
                        logger.info(e);
                        throw new EvaluateManageSysException(e);
                }
        }

        /**
         * update a FeedBack.
         *
         * @param am
         * @throws EvaluateManageSysException
         */
        public void update(FeedBackModel am) throws EvaluateManageSysException
        {
                try
                {
                        HibernateDAO.update(am);
                }
                catch (ULMSSysException e)
                {
                        throw new EvaluateManageSysException(e);
                }
        }
}
