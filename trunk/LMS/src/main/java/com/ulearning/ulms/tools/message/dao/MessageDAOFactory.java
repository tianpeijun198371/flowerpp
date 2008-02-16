package com.ulearning.ulms.tools.message.dao;

import com.ulearning.ulms.tools.message.exceptions.MessageDAOSysException;
import com.ulearning.ulms.util.log.LogUtil;


/**
 * Created by IntelliJ IDEA.
 * User: keyh
 * Date: 2004-7-23
 * Time: 16:48:42
 * To change this template use File | Settings | File Templates.
 */
public class MessageDAOFactory
{
        public static MessageDAO getDAO() throws MessageDAOSysException
        {
                MessageDAO dao = null;

                try
                {
                        dao = new MessageDAOImpl();
                }
                catch (Exception se)
                {
                        LogUtil.debug("common",
                                "[MessageDAOFactory]======================SQLException=" +
                                        se.getMessage());
                }

                return dao;
        }
}
