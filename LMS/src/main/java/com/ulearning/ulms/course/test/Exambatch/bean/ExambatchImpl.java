/**
 * Copyright (c) 2000-2005.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.course.test.Exambatch.bean;

import com.ulearning.ulms.course.test.Exambatch.dao.ExambatchDAO;
import com.ulearning.ulms.course.test.Exambatch.dao.ExambatchDAOFactory;
import com.ulearning.ulms.course.test.Exambatch.exceptions.ExambatchDAOSysException;
import com.ulearning.ulms.course.test.Exambatch.form.ExambatchForm;

import java.util.List;


/**
 * Class description goes here.
 * <p/>
 * 考场基本方法封状类
 * User: zhuyr
 * Date: 20051121
 * Time: 135243
 */
public class ExambatchImpl
{
        public List getExambatchList() throws ExambatchDAOSysException
        {
                List ExambatchList = null;

                try
                {
                        ExambatchDAO dao = ExambatchDAOFactory.getDAO();
                        ExambatchList = dao.getExambatchList();
                }
                catch (ExambatchDAOSysException tdse)
                {
                        tdse.printStackTrace();
                }

                return ExambatchList;
        }

        public ExambatchForm getExambatch(int exambatchID)
                throws ExambatchDAOSysException
        {
                ExambatchForm tf = null;

                try
                {
                        ExambatchDAO dao = ExambatchDAOFactory.getDAO();
                        tf = dao.getExambatch(exambatchID);
                }
                catch (ExambatchDAOSysException tdse)
                {
                        tdse.printStackTrace();
                }

                return tf;
        }

        public void deleteExambatch(int exambatchID)
                throws ExambatchDAOSysException
        {
                try
                {
                        ExambatchDAO dao = ExambatchDAOFactory.getDAO();
                        dao.deleteExambatch(exambatchID);
                }
                catch (ExambatchDAOSysException tdse)
                {
                        tdse.printStackTrace();
                }
        }
}
