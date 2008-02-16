/**
 * FunctionHelper.java.
 * User: Fengch  Date: 2005-4-1 10:48:43
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.portal.fuctionconfig.helper;

import com.ulearning.ulms.portal.fuctionconfig.dao.FunctionConfigDAO;
import com.ulearning.ulms.portal.fuctionconfig.dao.FunctionConfigDAOFactory;
import com.ulearning.ulms.portal.fuctionconfig.exceptions.FunctionConfigSysException;
import com.ulearning.ulms.portal.fuctionconfig.model.FunctionConfigModel;
import com.ulearning.ulms.portal.fuctionconfig.util.FunctionConfigConstants;

import java.util.Date;
import java.util.List;


public class FunctionHelper
{
        private static FunctionConfigDAO functionConfigDAO;

        static
        {
                try
                {
                        functionConfigDAO = FunctionConfigDAOFactory.getDAO();
                }
                catch (Exception ex)
                {
                        ex.printStackTrace();
                }
        }

        /**
         * 更新一个list
         *
         * @param l
         * @throws FunctionConfigSysException
         */
        public void update(List l) throws FunctionConfigSysException
        {
                functionConfigDAO.update(l);
        }

        /**
         * 根据type取得一个list
         *
         * @param type
         * @return
         * @throws FunctionConfigSysException
         */
        public static List getAll(int type) throws FunctionConfigSysException
        {
                List tmList = functionConfigDAO.getAll(type);

                return tmList;
        }

        /**
         * 根据课程relationID和type,取得该课程的定制情况
         *
         * @param relationID
         * @param type
         * @return
         * @throws FunctionConfigSysException
         */
        public static List getCourseList(int relationID, int type)
                throws FunctionConfigSysException
        {
                List tmList = functionConfigDAO.getCourseList(relationID, type);

                return tmList;
        }

        /**
         * 插入一组新的课程定制
         */
        public static List insertCourse(int courseID, int type)
                throws FunctionConfigSysException
        {
                FunctionConfigModel functionConfigModel = new FunctionConfigModel();
                List l = functionConfigDAO.getCourseList(courseID, type);

                if (l.isEmpty())
                {
                        l = functionConfigDAO.getCourseList(0,
                                FunctionConfigConstants.COURSE_TYPE);

                        for (int i = 0; i < l.size(); i++)
                        {
                                functionConfigModel = (FunctionConfigModel) l.get(i);
                                functionConfigModel.setName(functionConfigModel.getName());
                                functionConfigModel.setBeginDate(new Date());
                                functionConfigModel.setIsAvailable("1");
                                functionConfigModel.setRelationID(String.valueOf(courseID));
                                functionConfigModel.setType(String.valueOf(type));

                                functionConfigDAO.insertCourse(functionConfigModel);
                        }
                }

                return l;
        }
}
