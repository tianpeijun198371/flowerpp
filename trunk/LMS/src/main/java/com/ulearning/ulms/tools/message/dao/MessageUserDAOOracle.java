package com.ulearning.ulms.tools.message.dao;

import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.tools.message.exceptions.MessageUserDAOSysException;
import com.ulearning.ulms.tools.message.form.MessageUserForm;
import com.ulearning.ulms.util.log.LogUtil;


/**
 * Created by IntelliJ IDEA.
 * User: keyh
 * Date: 2004-7-26
 * Time: 10:20:51
 * To change this template use File | Settings | File Templates.
 */
public class MessageUserDAOOracle extends MessageUserDAOImpl
{
        /*           public void insertMessageUser(MessageUserForm muf) throws MessageUserDAOSysException
        {
           String sqlStr = "insert into T_MESSAGEUSER_TAB values(" +
                        muf.getMessageID() + "," +
                        muf.getUserID() + ")";
                {
                }
                try
                {
                        LogUtil.debug("system", "[MessageUserDAOOracle]====================the sql string is : " + sqlStr);
                }
                catch (ULMSSysException se)
                {
                        se.printStackTrace();
                        throw new MessageUserDAOSysException("SQLException while updating " + "MessageUser; Serial = " + muf.getMessageID() + " :\n" + se);
                }
        }
        public void updateMessageUserByMessage(MessageUserForm muf) throws MessageUserDAOSysException
        {
                String sqlStr = "Update T_MESSAGEUSER_TAB set UserID = " + muf.getUserID() +
                        " where MessageID=" + muf.getMessageID();
                {
                }
                try
                {
                        LogUtil.debug("system", "[MessageUserDAOOracle]====================the sql string is : " + sqlStr);
                }
                catch (ULMSSysException se)
                {
                        se.printStackTrace();
                        throw new MessageUserDAOSysException("SQLException while updating " + "UserID; Serial = " + muf.getMessageID() + " :\n" + se);
                }
        }
        public void updateMessageUserByUser(MessageUserForm muf) throws MessageUserDAOSysException
        {
                String sqlStr = "insert into T_MESSAGEUSER_TAB set MessageID = " + muf.getUserID() +
                        " where UserID=" + muf.getUserID();
                {
                }
                try
                {
                        LogUtil.debug("system", "[MessageUserDAOOracle]====================the sql string is : " + sqlStr);
                }
                catch (ULMSSysException se)
                {
                        se.printStackTrace();
                        throw new MessageUserDAOSysException("SQLException while updating " + "MessageID; Serial = " + muf.getUserID() + " :\n" + se);
                }
        }*/
}
