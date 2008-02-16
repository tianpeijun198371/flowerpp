/**
 * Copyright (c)2006 Beijing WenhuaOnline Sci-tech DevelopmentCo.,Ltd.
 * All rights reserved.
 *
 * User: fengch
 * Date: 2007-9-21 14:01:08
 */
package com.ulearning.ulms.scorm.dao;

import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.scorm.exceptions.ScormSysException;
import com.ulearning.ulms.scorm.model.NewScormScoes;
import com.ulearning.ulms.scorm.model.NewScormScoesTrack;
import com.ulearning.ulms.util.HibernateDAO;
import com.ulearning.ulms.util.HibernateUtil;

import net.sf.hibernate.Query;
import net.sf.hibernate.Session;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.List;


public class NewScormScoesTrackDAOImpl implements NewScormScoesTrackDAO
{
        protected static Log logger = LogFactory.getLog(NewScormDAOImpl.class);

        /**
         * 查找
         *
         * @param id
         * @throws ScormSysException
         */
        public NewScormScoesTrack get(Integer id) throws ScormSysException
        {
                try
                {
                        return (NewScormScoesTrack) HibernateDAO.load(NewScormScoesTrack.class,
                                id);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new ScormSysException(e);
                }
        }

        /**
         * 返回学生在某sco的状态数据。
         *
         * @param userId
         * @param scoId
         * @return
         * @throws ScormSysException
         */
        public List getNewScormScoesTracksByUserAndSco(Integer userId, Integer scoId)
                throws ScormSysException
        {
                List tracks = new ArrayList();

                try
                {
                        Session session = HibernateUtil.getSession();
                        session.clear();

                        String hqlStr = "from NewScormScoesTrack where  userId=" + userId +
                                " and scoId=" + scoId;
                        logger.info("hsql: " + hqlStr);

                        Query query = session.createQuery(hqlStr);
                        tracks = query.list();
                }
                catch (Exception e)
                {
                        e.printStackTrace();
                        throw new ScormSysException(e);
                }

                return tracks;
        }

        /**
         * 判断用户在SCO的跟踪记录(element对应的值)是否存在.
         *
         * @param userId  用户
         * @param scoId   SCO
         * @param element 元素
         * @return 存在与否
         * @throws ScormSysException 出错信息
         */
        public NewScormScoesTrack isExistScormScoesTrack(Integer userId,
                                                         Integer scoId, String element) throws ScormSysException
        {
                NewScormScoesTrack track = null;
                List list = null;
                String hsql = "from NewScormScoesTrack where userId=:userId and scoId=:scoId and element=:element";

                try
                {
                        Session session = HibernateUtil.getSession();
                        session.clear();
                        logger.info("hsql: " + hsql);

                        Query query = session.createQuery(hsql);
                        query.setParameter("userId", userId);
                        query.setParameter("scoId", scoId);
                        query.setParameter("element", element);
                        list = query.list();
                        logger.info("list=" + list.size());

                        if (!list.isEmpty())
                        {
                                track = (NewScormScoesTrack) list.get(0);
                        }
                }
                catch (Exception e)
                {
                        e.printStackTrace();
                        throw new ScormSysException(e);
                }

                return track;
        }

        /**
         * 插入
         *
         * @param newScormScoesTrack
         * @throws com.ulearning.ulms.scorm.exceptions.ScormSysException
         *
         */
        public void insert(NewScormScoesTrack newScormScoesTrack)
                throws ScormSysException
        {
                try
                {
                        HibernateDAO.add(newScormScoesTrack);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new ScormSysException(e);
                }
        }

        /**
         * 更新
         *
         * @param newScormScoesTrack
         * @throws ScormSysException
         */
        public void update(NewScormScoesTrack newScormScoesTrack)
                throws ScormSysException
        {
                try
                {
                        HibernateDAO.update(newScormScoesTrack);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new ScormSysException(e);
                }
        }

        /**
         * 删除
         *
         * @param newScormScoesTrack
         * @throws ScormSysException
         */
        public void delete(NewScormScoesTrack newScormScoesTrack)
                throws ScormSysException
        {
                try
                {
                        HibernateDAO.delete(newScormScoesTrack);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new ScormSysException(e);
                }
        }
}
