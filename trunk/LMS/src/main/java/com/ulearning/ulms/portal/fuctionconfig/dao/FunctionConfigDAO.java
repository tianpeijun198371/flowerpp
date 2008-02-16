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
         * ����һ��FunctionModel.
         *
         * @param functionID
         * @return
         * @throws FunctionConfigSysException
         */
        public FunctionConfigModel get(int functionID)
                throws FunctionConfigSysException;

        /**
         * ���ظ��������е�list
         *
         * @param type
         * @return
         * @throws FunctionConfigSysException
         */
        public List getAll(int type) throws FunctionConfigSysException;

        /**
         * ���ݿγ�relationID��type,ȡ�øÿγ̵Ķ������
         *
         * @param relationID
         * @param type
         * @return
         * @throws FunctionConfigSysException
         */
        public List getCourseList(int relationID, int type)
                throws FunctionConfigSysException;

        /**
         * �������е�model��isAvailale��BeginDate,EndDate.<br>
         * List���ŵ���FunctionConfigModel
         *
         * @param l
         * @throws FunctionConfigSysException
         */
        public void update(List l) throws FunctionConfigSysException;

        /**
         * ����һ���µĿγ̶���
         *
         * @param addFunctionConfigModel
         * @throws FunctionConfigSysException
         */
        public void insertCourse(FunctionConfigModel addFunctionConfigModel)
                throws FunctionConfigSysException;
}
