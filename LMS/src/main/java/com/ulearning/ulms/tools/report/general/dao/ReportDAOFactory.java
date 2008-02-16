/**
 * ReportDAOFactory.java.
 * User: liz  Date: 2006-4-29
 * 报表的通用DAO工厂类
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.report.general.dao;

import com.ulearning.ulms.core.exceptions.ULMSException;

public class ReportDAOFactory

{
        //private static ReportDAO dao = null;

        /**
         * 该类通过反射实现对所有继承ReportDAO的类实现。
         *
         * @param obj 要实现的类，要求完整的类路径。
         * @return
         * @throws ULMSException
         */
        public static ReportDAO getDAO(String obj) throws ULMSException
        {
                ReportDAO dao = null;
                try
                {
                        if (null == dao)
                        {

                                dao = (ReportDAO) Class.forName(obj).newInstance();
                        }
                        else
                        {
                                if (!dao.getClass().getName().equals(obj))
                                {
                                        dao = (ReportDAO) Class.forName(obj).newInstance();
                                }
                        }

                }
                catch (ClassNotFoundException ex)
                {
                        ex.printStackTrace();
                }
                catch (IllegalAccessException ex)
                {
                        ex.printStackTrace();
                }
                catch (InstantiationException ex)
                {
                        ex.printStackTrace();
                }

                catch (Exception se)
                {
                        throw new ULMSException("ResuserecordDAOFactory.getDAO:  Exception while getting DAO type : \n" + se.getMessage());
                }

                return dao;

        }
}
