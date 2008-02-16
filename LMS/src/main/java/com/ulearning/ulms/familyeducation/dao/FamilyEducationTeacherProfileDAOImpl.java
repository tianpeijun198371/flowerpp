/**
 * Copyright (c)2006 Beijing WenhuaOnline Sci-tech DevelopmentCo.,Ltd.
 * All rights reserved.
 *
 * User: Fengch
 * Date: 2007-12-6 13:15:34
 */
package com.ulearning.ulms.familyeducation.dao;

import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.core.util.PagerList;
import com.ulearning.ulms.familyeducation.exception.FamilyEducationSysException;
import com.ulearning.ulms.familyeducation.model.FamilyEducationTeacherProfileModel;
import com.ulearning.ulms.util.HibernateDAO;
import com.ulearning.ulms.util.HibernateUtil;

import net.sf.hibernate.Query;
import net.sf.hibernate.Session;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Iterator;
import java.util.List;


public class FamilyEducationTeacherProfileDAOImpl
        implements FamilyEducationTeacherProfileDAO
{
        protected static Log logger = LogFactory.getLog(FamilyEducationTeacherProfileDAOImpl.class);

        //查看
        public FamilyEducationTeacherProfileModel get(int id)
                throws FamilyEducationSysException
        {
                return (FamilyEducationTeacherProfileModel) HibernateDAO.load(FamilyEducationTeacherProfileModel.class,
                        new Integer(id));
        }

        //发布
        public void add(FamilyEducationTeacherProfileModel model)
                throws FamilyEducationSysException
        {
                HibernateDAO.add(model);
        }

        //删除
        public void delete(int[] ids) throws FamilyEducationSysException
        {
                String hql = " from FamilyEducationTeacherProfileModel";

                if ((ids == null) || (ids.length == 0))
                {
                        return;
                }

                for (int i = 0; i < ids.length; i++)
                {
                        int id = ids[i];

                        if (i == 0)
                        {
                                hql += (" where teacherID=" + id);
                        }
                        else
                        {
                                hql += (" or teacherID=" + id);
                        }
                }

                logger.info("hql=" + hql);

                try
                {
                        HibernateDAO.delete(hql);
                }
                catch (ULMSSysException e)
                {
                        logger.info(e);
                        throw new FamilyEducationSysException(e);
                }
        }

        //修改
        public void update(FamilyEducationTeacherProfileModel model)
                throws FamilyEducationSysException
        {
                HibernateDAO.update(model);
        }

        public PagerList getFamilyEducationTeacherProfiles(int aspID,
                                                           int bodyIdentity, int status, int pageNo, int pageSize)
                throws FamilyEducationSysException
        {
                int cruentIndex = pageNo * pageSize;
                PagerList pl = new PagerList();
                List list = null;

                try
                {
                        //取数据
                        String hql = "from FamilyEducationTeacherProfileModel";

                        String tmpCondition = "";

                        if (aspID != -1)
                        {
                                tmpCondition += (" and  aspID=" + aspID);
                        }

                        if (status != -1)
                        {
                                tmpCondition += (" and  status=" + status);
                        }

                        if (bodyIdentity != -1)
                        {
                                tmpCondition += (" and  bodyIdentity=" + bodyIdentity);
                        }

                        if (!tmpCondition.equals(""))
                        {
                                tmpCondition = tmpCondition.substring(5);
                                tmpCondition = " where " + tmpCondition;
                        }

                        hql += tmpCondition;
                        logger.info("hql=" + (hql + " order by teacherID desc"));
                        list = HibernateDAO.find(hql + " order by teacherID desc",
                                cruentIndex, pageSize);

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

        public PagerList search(int aspID, String province, String area,
                                String district, String subject, int bodyIdentity, int tutorMode,
                                String teachername,String teacherCode, int gender,String keyword, int pageNo, int pageSize)
                throws FamilyEducationSysException
        {
                int cruentIndex = pageNo * pageSize;
                PagerList pl = new PagerList();
                List list = null;
                Session session = null;

                try
                {
                        session = HibernateUtil.getSession();

                        //取数据
                        String hql = "from FamilyEducationTeacherProfileModel";

                        String tmpCondition = "";

                        if (aspID != -1)
                        {
                                tmpCondition += (" and  aspID=" + aspID);
                        }

                        if (!StringUtils.isBlank(province))
                        {
                                tmpCondition += (" and  province='" + province + "'");
                        }

                        if (!StringUtils.isBlank(area))
                        {
                                tmpCondition += (" and  area='" + area + "'");
                        }

                        if (!StringUtils.isBlank(district))
                        {
                                tmpCondition += (" and  district='" + district + "'");
                        }

                        if (!StringUtils.isBlank(subject))
                        {
                                tmpCondition += " and  availableTeachSubject like :availableTeachSubject";
                                subject = "%" + subject + "%";
                        }

                        if (bodyIdentity != -1)
                        {
                                tmpCondition += (" and  bodyIdentity=" + bodyIdentity);
                        }
                        
                        if (gender != -1)
                        {
                                tmpCondition += (" and  gender=" + gender);
                        }

                        if (tutorMode != -1)
                        {
                                tmpCondition += (" and  tutorMode=" + tutorMode);
                        }

                        if (!StringUtils.isBlank(teachername))
                        {
                                tmpCondition += " and  teacherName like :teacherName";
                                teachername = "%" + teachername + "%";
                        }
                        if (!StringUtils.isBlank(teacherCode))
                        {
                                tmpCondition += " and  teacherCode like :teacherCode";
                                teacherCode = "%" + teacherCode + "%";
                        }
                        if (!StringUtils.isBlank(keyword))
                        {
                                tmpCondition += " and  keyword like :keyword";
                                keyword = "%" + keyword + "%";
                        }

                        if (!tmpCondition.equals(""))
                        {
                                tmpCondition = tmpCondition.substring(5);
                                tmpCondition = " where " + tmpCondition;
                        }

                        hql += tmpCondition;
                        logger.info("hql=" + (hql + " order by teacherID desc"));

                        Query q = session.createQuery(hql + " order by teacherID desc");

                        if (-1 != pageNo)
                        {
                                q.setFirstResult(cruentIndex);
                        }

                        if (-1 != pageSize)
                        {
                                q.setMaxResults(pageSize);
                        }
                        if (!StringUtils.isBlank(teachername))
                        {
                                q.setString("teacherName", teachername);
                        }
                        if (!StringUtils.isBlank(teacherCode))
                        {
                                q.setString("teacherCode", teacherCode);
                        }
                        if (!StringUtils.isBlank(keyword))
                        {
                                q.setString("keyword", keyword);
                        }

                        if (!StringUtils.isBlank(subject))
                        {
                                q.setString("availableTeachSubject", subject);
                        }

                        list = q.list();

                        int totalCount = 0;

                        //取总记录数
                        String hqlCount = "select count(*) " + hql;

                        q = session.createQuery(hqlCount);

                        if (!StringUtils.isBlank(teachername))
                        {
                                q.setString("teacherName", teachername);
                        }
                        if (!StringUtils.isBlank(teacherCode))
                        {
                                q.setString("teacherCode", teacherCode);
                        }
                        
                        if (!StringUtils.isBlank(keyword))
                        {
                                q.setString("keyword", keyword);
                        }

                        if (!StringUtils.isBlank(subject))
                        {
                                q.setString("availableTeachSubject", subject);
                        }

                        totalCount = ((Integer) q.uniqueResult()).intValue();

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
}
