package com.ulearning.ulms.tools.report.custom.tasktime.dao;

import org.apache.struts.action.ActionForm;
import com.ulearning.ulms.tools.report.custom.tasktime.exceptions.TaskTimeDAOSysException;
import com.ulearning.ulms.util.DBUtil;

/**
 * Created by IntelliJ IDEA.
 * User: keyh
 * Date: 2004-7-20
 * Time: 17:05:31
 * To change this template use File | Settings | File Templates.
 */
public class TaskTimeDAOFactory extends ActionForm
{
        public static TaskTimeDAO getDAO() throws TaskTimeDAOSysException
        {
                TaskTimeDAO dao = null;
                try
                {
                        dao = new TaskTimeDAOImpl();
                        /*
                        if(DBUtil.getWhichDatabase()==0)
                        {
                                dao=new TaskTimeDAOOracle();
                        }
                        else if (DBUtil.getWhichDatabase() == 1)
                        {

                        }
                        */
                }
                catch (Exception se)
                {
                        throw new TaskTimeDAOSysException("TaskTimeDAOFactory.getDAO:  Exception while getting DAO type : \n" + se.getMessage());
                }

                return dao;

        }
}
