/**
 * CreditCourseWiseDAOImpl.java.
 * User: keyh  Date: 2004-9-2
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.course.courseconfig.dao;

import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.course.courseconfig.exceptions.CreditCourseWiseSysException;
import com.ulearning.ulms.course.courseconfig.form.CreditCourseWiseForm;
import com.ulearning.ulms.course.courseconfig.model.CreditCourseWiseModel;
import com.ulearning.ulms.util.HibernateDAO;

import java.util.ArrayList;
import java.util.List;


public class CreditCourseWiseDAOImpl implements CreditCourseWiseDAO
{
        public void add(CreditCourseWiseForm ccwf)
                throws CreditCourseWiseSysException
        {
                try
                {
                        HibernateDAO.saveOrUpdateCopy(ccwf.getCreditCourseWiseModel());
                }
                catch (ULMSSysException ese)
                {
                        ese.printStackTrace();
                }

                //To change body of implemented methods use File | Settings | File Templates.
        }

        public void delete(int typeID, int relationID)
                throws CreditCourseWiseSysException
        {
                String hql = "from CreditCourseWiseModel as ccwm" +
                        " where ccwm.comp_id.typeid=" + typeID +
                        " and ccwm.comp_id.relationid=" + relationID;

                try
                {
                        HibernateDAO.delete(hql);
                }
                catch (ULMSSysException ese)
                {
                        ese.printStackTrace();
                }

                //To change body of implemented methods use File | Settings | File Templates.
        }

        public void update(CreditCourseWiseForm ccwf)
                throws CreditCourseWiseSysException
        {
                try
                {
                        HibernateDAO.saveOrUpdateCopy(ccwf.getCreditCourseWiseModel());
                }
                catch (ULMSSysException ese)
                {
                        ese.printStackTrace();
                }

                //To change body of implemented methods use File | Settings | File Templates.
        }

        public CreditCourseWiseForm get(int typeID, int relationID)
                throws CreditCourseWiseSysException
        {
                String hql = "from CreditCourseWiseModel as ccwm" +
                        " where ccwm.comp_id.typeid=" + typeID +
                        "and  ccwm.comp_id.relationid=" + relationID;
                List modelList = null;
                List list = new ArrayList();
                CreditCourseWiseForm ccwf = new CreditCourseWiseForm();

                try
                {
                        modelList = HibernateDAO.find(hql);

                        if ((modelList != null) && (modelList.size() > 0))
                        {
                                list = getFormList(modelList);
                                ccwf = (CreditCourseWiseForm) list.get(0);
                        }
                        else
                        {
                                return null;
                        }
                }
                catch (ULMSSysException ee)
                {
                        ee.printStackTrace();
                }

                return ccwf; //To change body of implemented methods use File | Settings | File Templates. //To change body of implemented methods use File | Settings | File Templates.
        }

        public List getAll() throws CreditCourseWiseSysException
        {
                String hql = "from CreditCourseWiseModel";
                List modelList = null;
                List list = new ArrayList();

                try
                {
                        modelList = HibernateDAO.find(hql);

                        if ((modelList != null) && (modelList.size() > 0))
                        {
                                list = getFormList(modelList);
                        }
                        else
                        {
                                return null;
                        }
                }
                catch (ULMSSysException ee)
                {
                        ee.printStackTrace();
                }

                return list;
        }

        private List getFormList(List modelList)
        {
                List list = new ArrayList();

                for (int i = 0; i < modelList.size(); i++)
                {
                        CreditCourseWiseModel ccwm = new CreditCourseWiseModel();
                        CreditCourseWiseForm ccwf = new CreditCourseWiseForm();
                        ccwm = (CreditCourseWiseModel) modelList.get(i);
                        ccwf = ccwf.getCreditCourseWiseForm(ccwm);
                        list.add(ccwf);
                }

                return list;
        }
}
