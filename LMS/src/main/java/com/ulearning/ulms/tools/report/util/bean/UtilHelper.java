/** * UtilHelper.java. 
 * User: xiejh  Date: 2004-7-19 *  
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd. 
 * All rights reserved. 
 */
package com.ulearning.ulms.tools.report.util.bean;

import com.ulearning.ulms.tools.report.util.exceptions.UtilDAOSysException;
import com.ulearning.ulms.tools.report.util.dao.UtilDAO;
import com.ulearning.ulms.tools.report.util.dao.UtilDAOFactory;

import java.util.List;

public class UtilHelper
{
        UtilDAO utilDao = null;

        public List getList(String sql, int fieldNum, int pageNo, int pageSize)
        {
                List formList = null;
                try
                {
                        if (utilDao == null)
                        {
                                utilDao = UtilDAOFactory.getDAO();
                        }
                        formList = utilDao.getList(sql, fieldNum, pageNo, pageSize);
                }
                catch (UtilDAOSysException se)
                {
                        se.printStackTrace();
                }
                return formList;
        }

        public String getSQLOneValue(String sql)
        {
                String tmp = null;
                try
                {
                        if (utilDao == null)
                        {
                                utilDao = UtilDAOFactory.getDAO();
                        }
                        tmp = utilDao.getSQLOneValue(sql);
                }
                catch (UtilDAOSysException se)
                {
                        se.printStackTrace();
                }
                return tmp;
        }
}