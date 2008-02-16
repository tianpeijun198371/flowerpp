/**
 * FunctionConfigDAO.java.
 * User: Yud  Date: 2005-4-1 10:45:11
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.portal.fuctionconfig.dao;

import com.ulearning.ulms.portal.fuctionconfig.exceptions.FunctionConfigSysException;
import com.ulearning.ulms.portal.fuctionconfig.model.FunctionConfigModel;

import java.util.List;


public interface FunctionConfigDAO
{
        /**
         * 返回一个FunctionModel.
         *
         * @param functionID
         * @return
         * @throws FunctionConfigSysException
         */
        public FunctionConfigModel get(int functionID)
                throws FunctionConfigSysException;

        /**
         * 返回该类型所有的list
         *
         * @param type
         * @return
         * @throws FunctionConfigSysException
         */
        public List getAll(int type) throws FunctionConfigSysException;

        /**
         * 根据课程relationID和type,取得该课程的定制情况
         *
         * @param relationID
         * @param type
         * @return
         * @throws FunctionConfigSysException
         */
        public List getCourseList(int relationID, int type)
                throws FunctionConfigSysException;

        /**
         * 更新所有的model的isAvailale和BeginDate,EndDate.<br>
         * List里存放的是FunctionConfigModel
         *
         * @param l
         * @throws FunctionConfigSysException
         */
        public void update(List l) throws FunctionConfigSysException;

        /**
         * 插入一条新的课程定制
         *
         * @param addFunctionConfigModel
         * @throws FunctionConfigSysException
         */
        public void insertCourse(FunctionConfigModel addFunctionConfigModel)
                throws FunctionConfigSysException;
}
