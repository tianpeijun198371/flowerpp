/**
 * Copyright (c) 2000-2005.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.course.test.Exambatch.dao;

import com.ulearning.ulms.course.test.Exambatch.exceptions.ExambatchDAOSysException;
import com.ulearning.ulms.course.test.Exambatch.form.ExambatchForm;

import java.io.Serializable;

import java.util.Date;
import java.util.List;


/**
 * Class description goes here.
 * <p/>
 * 考场方法接口定义
 * User: zhuyr
 * Date: 20051121
 * Time: 135243
 */
public interface ExambatchDAO
{
        public Serializable insertExambatch(ExambatchForm tf)
                throws ExambatchDAOSysException;

        public void updateExambatch(ExambatchForm tf)
                throws ExambatchDAOSysException;

        public void deleteExambatch(int exambatchID)
                throws ExambatchDAOSysException;

        public List getExambatchList() throws ExambatchDAOSysException;

        public List getPaperList(int paperID) throws ExambatchDAOSysException;

        public ExambatchForm getExambatch(int exambatchID)
                throws ExambatchDAOSysException;

        public Date getdatePaperTime(int PaperID, boolean type)
                throws ExambatchDAOSysException;
}
