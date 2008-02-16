/**
 * GradeWeightingFactorDAOImpl.java.
 * User: keyh  Date: 2004-9-2
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.course.courseconfig.dao;

import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.course.courseconfig.exceptions.GradeWeightingFactorSysException;
import com.ulearning.ulms.course.courseconfig.form.GradeWeightingFactorForm;
import com.ulearning.ulms.course.courseconfig.model.GradeWeightingFactorModel;
import com.ulearning.ulms.util.HibernateDAO;

import java.util.ArrayList;
import java.util.List;


public class GradeWeightingFactorDAOImpl implements GradeWeightingFactorDAO
{
        public void add(GradeWeightingFactorForm gwff)
        {
                try
                {
                        HibernateDAO.saveOrUpdateCopy(gwff.getGradeWeightingFactorModel());
                }
                catch (Exception ese)
                {
                        ese.printStackTrace();
                }

                //To change body of implemented methods use File | Settings | File Templates.
        }

        public void delete(int typeID, int relationID)
                throws GradeWeightingFactorSysException
        {
                String hql = "from GradeWeightingFactorModel as gwfm" +
                        " where gwfm.comp_id.typeid=" + typeID +
                        " and gwfm.comp_id.relationid=" + relationID;

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

        public void update(GradeWeightingFactorForm gwff)
                throws GradeWeightingFactorSysException
        {
                try
                {
                        HibernateDAO.saveOrUpdateCopy(gwff.getGradeWeightingFactorModel());
                }
                catch (ULMSSysException ese)
                {
                        ese.printStackTrace();
                }

                //To change body of implemented methods use File | Settings | File Templates.
        }

        public float compute(int typeID, int relationID, float exerciseWF,
                             float testWF, float examWF) throws GradeWeightingFactorSysException
        {
                GradeWeightingFactorForm gwf = get(typeID, relationID);

                if (gwf != null)
                {
                        return (exerciseWF * gwf.getExerciseWF()) +
                                (testWF * gwf.getTestWF()) + (examWF * gwf.getExamWF());
                }

                return exerciseWF + testWF + examWF;
        }

        public GradeWeightingFactorForm get(int typeID, int relationID)
                throws GradeWeightingFactorSysException
        {
                String hql = "from GradeWeightingFactorModel  as gwfm" +
                        " where gwfm.comp_id.typeid=" + typeID +
                        " and gwfm.comp_id.relationid=" + relationID;
                List modelList = null;
                List list = new ArrayList();
                GradeWeightingFactorForm gwff = new GradeWeightingFactorForm();

                try
                {
                        modelList = HibernateDAO.find(hql);

                        if ((modelList != null) && (modelList.size() > 0))
                        {
                                list = getFormList(modelList);
                                gwff = (GradeWeightingFactorForm) list.get(0);
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

                return gwff; //To change body of implemented methods use File | Settings | File Templates.
        }

        public List getAll() throws GradeWeightingFactorSysException
        {
                String hql = "from GradeWeightingFactorModel";
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

        public List getFormList(List modelList)
        {
                List list = new ArrayList();

                for (int i = 0; i < modelList.size(); i++)
                {
                        GradeWeightingFactorForm gwff = new GradeWeightingFactorForm();
                        GradeWeightingFactorModel gwfm = new GradeWeightingFactorModel();
                        gwfm = (GradeWeightingFactorModel) modelList.get(i);
                        gwff = gwff.getGradeWeightingFactorForm(gwfm);
                        list.add(gwff);
                }

                return list;
        }
}
