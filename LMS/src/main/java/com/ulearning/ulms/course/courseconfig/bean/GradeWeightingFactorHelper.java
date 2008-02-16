/**
 * GradeWeightingFactorHelper.java.
 * User: keyh  Date: 2004-9-2
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.course.courseconfig.bean;

import com.ulearning.ulms.course.courseconfig.dao.GradeWeightingFactorDAO;
import com.ulearning.ulms.course.courseconfig.dao.GradeWeightingFactorDAOFactory;
import com.ulearning.ulms.course.courseconfig.exceptions.GradeWeightingFactorSysException;
import com.ulearning.ulms.course.courseconfig.form.GradeWeightingFactorForm;

import java.util.ArrayList;
import java.util.List;


public class GradeWeightingFactorHelper
{
        public static GradeWeightingFactorForm get(int typeID, int relationID)
                throws GradeWeightingFactorSysException
        {
                try
                {
                        GradeWeightingFactorDAO dao = GradeWeightingFactorDAOFactory.getDAO();

                        return dao.get(typeID, relationID);
                }
                catch (GradeWeightingFactorSysException ccwe)
                {
                        ccwe.printStackTrace();
                }

                return null;
        }

        public static List getAll() throws GradeWeightingFactorSysException
        {
                List list = new ArrayList();

                try
                {
                        GradeWeightingFactorDAO dao = GradeWeightingFactorDAOFactory.getDAO();
                        list = dao.getAll();
                }
                catch (GradeWeightingFactorSysException ccwe)
                {
                        ccwe.printStackTrace();
                }

                return list;
        }

        /*
        * 计算加权后的分数
        *注意的地方:由于使用了浮点数保存加权因子,有一定的精度损失
        *@param typeID :SecurityConstants.USER_COURSE_RELATION; SecurityConstants.USER_CERTIFICATE_RELATION
        *@relationID: courseID or certID
        *@exerciseWF:作业平均分
        *@testWF:模拟自测分
        *@examWF:考试分
        */
        public static float compute(int typeID, int relationID, float exerciseWF,
                                    float testWF, float examWF) throws GradeWeightingFactorSysException
        {
                try
                {
                        GradeWeightingFactorDAO dao = GradeWeightingFactorDAOFactory.getDAO();

                        return dao.compute(typeID, relationID, exerciseWF, testWF, examWF);
                }
                catch (GradeWeightingFactorSysException ccwe)
                {
                        ccwe.printStackTrace();
                }

                return 0;
        }

        public static void delete(int typeID, int relationID)
                throws GradeWeightingFactorSysException
        {
                GradeWeightingFactorDAO dao = GradeWeightingFactorDAOFactory.getDAO();
                dao.delete(typeID, relationID);
        }
}
