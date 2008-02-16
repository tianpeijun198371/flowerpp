/**
 * Created by IntelliJ IDEA.
 * Plan: dengj
 * Date: Apr 8, 2004
 * Time: 9:12:45 AM
 * To change this template use Options | File Templates.
 */
package com.ulearning.ulms.admin.plan.dao;

import com.ulearning.ulms.admin.plan.exceptions.PlanDAOSysException;
import com.ulearning.ulms.util.DBUtil;


public class PlanDAOFactory
{
        /**
         * This method instantiates a particular subclass implementing
         * the DAO methods based on the information obtained from the
         * deployment descriptor
         */
        public static PlanDAO getDAO() throws PlanDAOSysException
        {
                PlanDAO dao = null;

                try
                {
                        dao = new PlanDAOImpl();
                }
                catch (Exception se)
                {
                        throw new PlanDAOSysException(
                                "PlanDAOFactory.getDAO:  Exception while getting DAO type : \n" +
                                        se.getMessage());
                }

                return dao;
        }
}
