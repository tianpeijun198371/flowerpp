package com.ulearning.ulms.tools.report.custom.task.dao;

import com.ulearning.ulms.tools.report.custom.task.exceptions.TaskDAOSysException;
import com.ulearning.ulms.util.DBUtil;

/**
 * Created by IntelliJ IDEA.
 * User: keyh
 * Date: 2004-7-20
 * Time: 14:34:31
 * To change this template use File | Settings | File Templates.
 */
public class TaskDAOFactory
{
        public static TaskDAO getDAO() throws TaskDAOSysException
        {
                TaskDAO dao = null;
                try
                {
                        dao = new TaskDAOOracle();
                        /*
                        if(DBUtil.getWhichDatabase()==0)
                        {
                                dao=new TaskDAOOracle();
                        }
                        else if (DBUtil.getWhichDatabase() == 1)
                        {

                        }
                        */
                }
                catch (Exception se)
                {
                        throw new TaskDAOSysException("TaskDAOFactory.getDAO:  Exception while getting DAO type : \n" + se.getMessage());
                }

                return dao;

        }

}
