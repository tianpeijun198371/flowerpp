/** * AccessHelp.java.
 * User: xiejh  Date: 2004-5-21 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.access.helper;

import com.ulearning.ulms.tools.access.dao.AccessDAO;
import com.ulearning.ulms.tools.access.dao.AccessDAOFactory;
import com.ulearning.ulms.tools.access.exceptions.AccessSysException;


public class AccessHelp
{
        //将秒转换为小时分秒:  hh:mm:ss
        public static String changeTime(int sec)
        {
                String temp = "";

                if (sec < 60)
                {
                        temp = "00:00:" + getTwoNum(sec) + "";
                }
                else if (sec < 3600)
                {
                        temp = "00:" + getTwoNum(sec / 60) + ":" + getTwoNum(sec % 60) +
                                "";
                }
                else
                {
                        temp = "" + (sec / 3600) + ":" + getTwoNum((sec % 3600) / 60) +
                                ":" + getTwoNum(sec % 60) + "";
                }

                return temp;
        }

        private static String getTwoNum(int n)
        {
                if (n < 10)
                {
                        return "0" + n;
                }
                else
                {
                        return String.valueOf(n);
                }
        }

        public static void deleteByCourseID(int courseID) throws AccessSysException
        {
                AccessDAO assignmentDao = AccessDAOFactory.getDAO();
                assignmentDao.deleteByCourseID(courseID);
        }
}
