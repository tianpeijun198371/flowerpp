/**
 * Created by IntelliJ IDEA.
 * author: houct
 * Date: 2005/04/08
 * Time: 10:58:23 AM
 * To change this template use Options | File Templates.
 */
package com.ulearning.ulms.admin.commendCourse.bean;

import com.ulearning.ulms.admin.commendCourse.dao.CCommendDAO;
import com.ulearning.ulms.admin.commendCourse.dao.CCommendDAOFactory;
import com.ulearning.ulms.admin.commendCourse.exceptions.CCommendDAOSysException;
import com.ulearning.ulms.admin.commendCourse.form.CCommendForm;

import java.util.List;


public class CCommendHelper
{
        public static List getCCommendList(String displayed, int month)
        {
                List tmList = null;

                try
                {
                        CCommendDAO Dao = CCommendDAOFactory.getDAO();
                        tmList = Dao.getCCommend_month(displayed, month);
                }
                catch (CCommendDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return tmList;
        }

        public static CCommendForm getCCommend(int ccourseID)
        {
                CCommendForm cCommendForm = null;

                try
                {
                        CCommendDAO Dao = CCommendDAOFactory.getDAO();
                        cCommendForm = Dao.getCCommend(ccourseID);
                }
                catch (CCommendDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return cCommendForm;
        }
}
