package com.ulearning.ulms.tools.visit.dao;

import com.ulearning.ulms.tools.visit.exceptions.VisitDAOSysException;

/**
 * Created by IntelliJ IDEA.
 * User: Jacky
 * Date: 2007-12-28
 * Time: 15:40:26
 * To change this template use File | Settings | File Templates.
 */
public class VisitDAOFactory
{
        public static VisitDAO getDAO() throws VisitDAOSysException
        {
                VisitDAO dao = null;
                try
                {
                        dao = new VisitDAOImpl();
                }
                catch (Exception se)
                {
                        throw new VisitDAOSysException("VisitDAOFactory.getDAO:  Exception while getting DAO type : \n" + se.getMessage());
                }

                return dao;
        }
}
