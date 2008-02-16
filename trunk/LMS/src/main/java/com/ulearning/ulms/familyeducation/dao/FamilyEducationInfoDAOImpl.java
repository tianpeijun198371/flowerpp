/**
 * Copyright (c)2006 Beijing WenhuaOnline Sci-tech DevelopmentCo.,Ltd.
 * All rights reserved.
 *
 * User: Fengch
 * Date: 2007-12-6 13:15:24
 */
package com.ulearning.ulms.familyeducation.dao;

import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.core.util.PagerList;
import com.ulearning.ulms.familyeducation.exception.FamilyEducationSysException;
import com.ulearning.ulms.familyeducation.model.FamilyEducationInfoModel;
import com.ulearning.ulms.util.HibernateDAO;
import com.ulearning.ulms.util.HibernateUtil;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Iterator;
import java.util.List;

import net.sf.hibernate.Session;
import net.sf.hibernate.Query;


public class FamilyEducationInfoDAOImpl implements FamilyEducationInfoDAO
{
        protected static Log logger = LogFactory.getLog(FamilyEducationInfoDAOImpl.class);

        //查看
        public FamilyEducationInfoModel get(int id)
                throws FamilyEducationSysException
        {
                return (FamilyEducationInfoModel) HibernateDAO.load(FamilyEducationInfoModel.class,
                        new Integer(id));
        }

        //发布
        public void add(FamilyEducationInfoModel model)
                throws FamilyEducationSysException
        {
                HibernateDAO.add(model);
        }

        //删除
        public void delete(int[] ids) throws FamilyEducationSysException
        {
                String hql = " from FamilyEducationInfoModel";

                if ((ids == null) || (ids.length == 0))
                {
                        return;
                }

                for (int i = 0; i < ids.length; i++)
                {
                        int id = ids[i];

                        if (i == 0)
                        {
                                hql += (" where id=" + id);
                        }
                        else
                        {
                                hql += (" or id=" + id);
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
        public void update(FamilyEducationInfoModel model)
                throws FamilyEducationSysException
        {
                HibernateDAO.update(model);
        }

        public PagerList getFamilyEducationInfos(int aspID, int createID,
                                                 int status, int pageNo, int pageSize)
                throws FamilyEducationSysException
        {
                int cruentIndex = pageNo * pageSize;
                PagerList pl = new PagerList();
                List list = null;

                try
                {
                        //取数据
                        String hql = "from FamilyEducationInfoModel";

                        String tmpCondition = "";
                        System.out.println("hql =========123======= " + hql);
                        if (aspID != -1)
                        {
                                tmpCondition += (" and  aspID=" + aspID);
                        }

                        if (status != -1)
                        {
                                tmpCondition += (" and  status=" + status);
                        }

                        if (createID != -1)
                        {
                                tmpCondition += (" and  createID=" + createID);
                        }

                        if (!tmpCondition.equals(""))
                        {
                                tmpCondition = tmpCondition.substring(5);
                                tmpCondition = " where " + tmpCondition;
                                System.out.println("tmpCondition ====678==== " + tmpCondition);
                        }

                        hql += tmpCondition;
                        logger.info("hql=" + (hql + " order by id desc"));
                        System.out.println("hql =========789======= " + hql);
                        list = HibernateDAO.find(hql + " order by id desc", cruentIndex,
                                pageSize);

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
                                String district, String gradeName, String subjectName,
                                String contactName, int id,int status, int pageNo, int pageSize)
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
                        String hql = "from FamilyEducationInfoModel ";

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

                        if (!StringUtils.isBlank(gradeName))
                        {
                                tmpCondition += (" and  gradeName='" + gradeName + "'");
                        }

                        if (!StringUtils.isBlank(subjectName))
                        {
                                tmpCondition += (" and  subjectName='" + subjectName + "'");
                        }
                        
                        if (id!=-1 && id!=0)
                        {
                                tmpCondition += (" and  id=" + id);
                        }

                        if (!StringUtils.isBlank(contactName))
                        {
                                tmpCondition += " and  contactName like :contactName";
                                contactName = "%" + contactName + "%";
                        }

                        if (status != -1)
                        {
                                tmpCondition += (" and  status=" + status);
                        }

                        if (!tmpCondition.equals(""))
                        {
                                tmpCondition = tmpCondition.substring(5);
                                tmpCondition = " where " + tmpCondition;
                        }

                        hql += tmpCondition;
                        logger.info("hql=" + (hql + " order by id desc"));
                        Query q = session.createQuery(hql + " order by id desc");

                        if (-1 != pageNo)
                        {
                                q.setFirstResult(cruentIndex);
                        }

                        if (-1 != pageSize)
                        {
                                q.setMaxResults(pageSize);
                        }
                        if (!StringUtils.isBlank(contactName))
                        {
                                q.setString("contactName", contactName);
                        }
                        list = q.list();

                        int totalCount = 0;

                        //取总记录数
                        String hqlCount = "select count(*) " + hql;
                        q = session.createQuery(hqlCount);

                        if (!StringUtils.isBlank(contactName))
                        {
                                q.setString("contactName", contactName);
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
