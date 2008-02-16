/**
 * add by yud
 */
package com.ulearning.ulms.portal.fuctionconfig.dao;

import com.ulearning.ulms.portal.fuctionconfig.dao.FunctionConfigDAO;
import com.ulearning.ulms.portal.fuctionconfig.exceptions.FunctionConfigSysException;


public class FunctionConfigDAOFactory
{
        public static FunctionConfigDAO getDAO() throws FunctionConfigSysException
        {
                FunctionConfigDAO dao = null;

                try
                {
                        dao = new FunctionConfigDaolmpl();
                }
                catch (Exception se)
                {
                        throw new FunctionConfigSysException(se);
                }

                return dao;
        }
}
