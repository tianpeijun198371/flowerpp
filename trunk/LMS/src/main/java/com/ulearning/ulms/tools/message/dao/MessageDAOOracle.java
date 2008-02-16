package com.ulearning.ulms.tools.message.dao;

import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.tools.message.exceptions.MessageDAOSysException;
import com.ulearning.ulms.tools.message.form.MessageForm;
import com.ulearning.ulms.util.log.LogUtil;

import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Created by IntelliJ IDEA.
 * User: keyh
 * Date: 2004-7-23
 * Time: 16:35:49
 * To change this template use File | Settings | File Templates.
 */
public class MessageDAOOracle extends MessageDAOImpl
{
        /*
     public int insertMessage(MessageForm mf) throws MessageDAOSysException
     {
             java.sql.Date dayToInsert = new java.sql.Date(System.currentTimeMillis());
             java.sql.Time timeToInsert = new java.sql.Time(System.currentTimeMillis());
             int messageID = 0;
             String sqlStr = "insert into T_Message_TAB values(MessageID.nextval," +
                     mf.getSenderID() + ",'" +
                     mf.getTitle() + "','" +
                     mf.getContent() + "'," +
                     mf.getIsPopUpMessage() + "," +
                     mf.getIsSave() + "," +
                     mf.getType() + "," +
                     "to_date('" + dayToInsert + " " + timeToInsert + "','yyyy-mm-dd hh24:mi:ss'))";
             {
             }
             try
             {
                     LogUtil.debug("system", "[MessageDAOOracle]====================the sql string is : " + sqlStr);
                     sqlStr = "SELECT messageID.currval  as messageID FROM T_MESSAGE_TAB";
                     if (rs.next())
                     {
                             messageID = rs.getInt("messageID");
                     }
             }
             catch (SQLException se)
             {
                     se.printStackTrace();
             }
             catch (ULMSSysException se)
             {
                     se.printStackTrace();
                     throw new MessageDAOSysException("SQLException while updating " + "group; Serial = " + mf.getMessageID() + " :\n" + se);
             }
             finally
             {
                     try
                     {
                     }
                     catch (SQLException se)
                     {
                             se.printStackTrace();
                     }
             }
             return messageID;
     }
     public void updateMessage(MessageForm mf) throws MessageDAOSysException
     {
             java.sql.Date dayToInsert = new java.sql.Date(System.currentTimeMillis());
             java.sql.Time timeToInsert = new java.sql.Time(System.currentTimeMillis());
             String sqlStr = "update T_Message_TAB set " +
                     "SendUserID=" + mf.getSenderID() + "," +
                     "Title ='" + mf.getTitle() + "'," +
                     "Content ='" + mf.getContent() + "'," +
                     "IsPopUpMessage=" + mf.getIsPopUpMessage() + "," +
                     "IsSave=" + mf.getIsSave() + "," +
                     "Type=" + mf.getType() + "," +
                     "SendTime=to_date('" + dayToInsert + " " + timeToInsert + "','yyyy-mm-dd hh24:mi:ss')" +
                     " where MessageID=" + mf.getMessageID();
             {
             }
             try
             {
                     LogUtil.debug("system", "[MessageDAOOracle]====================the sql string is : " + sqlStr);
             }
             catch (ULMSSysException se)
             {
                     se.printStackTrace();
                     throw new MessageDAOSysException("SQLException while updating " + "group; Serial = " + mf.getMessageID() + " :\n" + se);
             }
             //To change body of implemented methods use File | Settings | File Templates.
     }
     public void deleteMessageFromRecieveBox(int messageID) throws MessageDAOSysException
     {
             String sqlStr = "update T_Message_TAB set " +
                     " Type = " + 0 +
                     " where MessageID = " + messageID;
             {
             }
             try
             {
                     LogUtil.debug("system", "[MessageDAOOracle]====================the sql string is : " + sqlStr);
             }
             catch (ULMSSysException se)
             {
                     se.printStackTrace();
                     throw new MessageDAOSysException("SQLException while updating " + "group; Serial = " + messageID + " :\n" + se);
             }
                     //To change body of implemented methods use File | Settings | File Templates.
     }
     public void deleteMessageFromSendBox(int messageID) throws MessageDAOSysException
      {
              String sqlStr = "update T_Message_TAB set " +
                      " IsSave = " + 0 +
                      " where MessageID = " + messageID;
              {
              }
              try
              {
                      LogUtil.debug("system", "[MessageDAOOracle]====================the sql string is : " + sqlStr);
              }
              catch (ULMSSysException se)
              {
                      se.printStackTrace();
                      throw new MessageDAOSysException("SQLException while updating " + "group; Serial = " + messageID + " :\n" + se);
              }
                      //To change body of implemented methods use File | Settings | File Templates.
      }  */
}
