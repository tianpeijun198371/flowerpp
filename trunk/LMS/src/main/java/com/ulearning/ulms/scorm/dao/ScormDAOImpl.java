/**
 * ScormDAOImpl.java.
 * User: fengch  Date: 2004-7-13
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.scorm.dao;

import com.ulearning.ulms.core.util.StringUtil;
import com.ulearning.ulms.scorm.datamodels.SCODataManager;
import com.ulearning.ulms.scorm.datamodels.cmi.*;
import com.ulearning.ulms.scorm.exceptions.ScormSysException;
import com.ulearning.ulms.scorm.model.ItemModel;
import com.ulearning.ulms.scorm.model.UserSCOModel;
import com.ulearning.ulms.scorm.util.ScormConstants;
import com.ulearning.ulms.scorm.util.CMIConstants;
import com.ulearning.ulms.user.bean.UserHelper;
import com.ulearning.ulms.util.DBUtil;
import com.ulearning.ulms.util.log.LogUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;


public class ScormDAOImpl implements ScormDAO
{
        protected static Log logger = LogFactory.getLog(ScormDAOImpl.class);
        protected String RTERoot = ScormConstants.RTERoot;

        /*
        * to insert scorm's item to table C_Item_Tab.
        */
        public void insertItem(int relationID, int type, int courseContentNodeID,
                               Vector items) throws ScormSysException
        {
        }

        /**
         * to insert scorm's all item to table C_UserSCO_Tab.
         *
         * @param userID
         * @param courseContentNodeID
         * @throws ScormSysException
         */
        public void insertUserSCO(int userID, int courseContentNodeID)
                throws ScormSysException
        {
                int scoID;
                String location;
                float masteryScore;
                String parameterString;
                String lessonStatus;
                String prerequisites;
                String exit;
                String entry;
                String dataFromLMS;
                String maxTimeAllowed;
                String timeLimitAction;
                int sequence;

                Connection conn = null;
                PreparedStatement stmtInsertUserSCO = null;

                String sqlInsertUserSCO = "INSERT INTO C_UserSCO_Tab (UserID,  SCOID, Launch, ParameterString, " +
                        "LessonStatus, Prerequisites, ExitString, Entry, MasteryScore, Sequence, ItemType) " +
                        "VALUES(?,?,?,?,?,?,?,?,?,?,?)";
                logger.info("[ScormDAOImpl]insertUserSCO--start!");

                try
                {
                        conn = DBUtil.getConnection();
                        stmtInsertUserSCO = conn.prepareStatement(sqlInsertUserSCO);

                        logger.info(
                                "[ScormDAOImpl]]insertUserSCO--about to call item in RTEFile");
                        logger.info("[ScormDAOImpl]insertUserSCO--userID: " + userID);
                        logger.info("[ScormDAOImpl]insertUserSCO--courseContentNodeID: " +
                                courseContentNodeID);

                        logger.info(
                                "[ScormDAOImpl]insertUserSCO--call to itemRS is complete");

                        List items = getItems(courseContentNodeID);
                        ItemModel iterm = null;
                        String itemType;

                        for (int i = 0; i < items.size(); i++)
                        {
                                iterm = (ItemModel) items.get(i);

                                try
                                {
                                        itemType = iterm.getItemType();
                                        logger.info("[ScormDAOImpl]insertUserSCO--itemTtype>" +
                                                itemType + "<");

                                        if ((itemType == null) || itemType.equals("") ||
                                                itemType.equals("sco") || itemType.equals("asset"))
                                        {
                                                scoID = iterm.getItemID();
                                                location = iterm.getLaunch();
                                                masteryScore = iterm.getMasteryScore();
                                                prerequisites = iterm.getPrerequisites();
                                                parameterString = iterm.getParameterString();
                                                dataFromLMS = iterm.getDataFromLMS();
                                                maxTimeAllowed = iterm.getMaxTimeAllowed();
                                                timeLimitAction = iterm.getTimeLimitAction();
                                                sequence = iterm.getSequence();

                                                logger.info("[ScormDAOImpl]insertUserSCO--scoID: " +
                                                        scoID);
                                                // Insert entry into UserSCOInfo DB table
                                                stmtInsertUserSCO.setInt(1, userID);
                                                stmtInsertUserSCO.setInt(2, scoID);
                                                stmtInsertUserSCO.setString(3, location);
                                                stmtInsertUserSCO.setString(4, parameterString);
                                                stmtInsertUserSCO.setString(5,
                                                        CMIConstants.STATUS_NOTATTEMPTED);

                                                stmtInsertUserSCO.setString(6, prerequisites);
                                                stmtInsertUserSCO.setString(7, "");
                                                stmtInsertUserSCO.setString(8, "ab-initio");

                                                logger.info(
                                                        "[ScormDAOImpl]insertUserSCO--masteryScore>" +
                                                                masteryScore + "<");

                                                stmtInsertUserSCO.setFloat(9, masteryScore);

                                                logger.info("[ScormDAOImpl]insertUserSCO--sequence>" +
                                                        sequence + "<");
                                                stmtInsertUserSCO.setInt(10, sequence);

                                                stmtInsertUserSCO.setString(11, itemType);

                                                stmtInsertUserSCO.executeUpdate();
                                                logger.info(
                                                        "[ScormDAOImpl]insertUserSCO--stmtInsertUserSCO executeUpdate END!");

                                                //initing the SCODataFile
                                                String RTESCODataFile = RTERoot + courseContentNodeID +
                                                        File.separator + userID + File.separator + scoID;
                                                File theRTESCODataFile = new File(RTESCODataFile);

                                                SCODataManager rteSCOData = initSCOData(String.valueOf(
                                                        userID), location, dataFromLMS,
                                                        String.valueOf(masteryScore), maxTimeAllowed,
                                                        timeLimitAction);

                                                logger.info(
                                                        "[ScormDAOImpl]insertUserSCO--RTESCODataFile>" +
                                                                RTESCODataFile + "<");

                                                // Write out the data to disk using serialization
                                                FileOutputStream fo = new FileOutputStream(RTESCODataFile);
                                                ObjectOutputStream out = new ObjectOutputStream(fo);
                                                out.writeObject(rteSCOData);
                                                out.close();
                                        }
                                        else
                                        {
                                                logger.info(
                                                        "[ScormDAOImpl]insertUserSCO--NOT A SCO OR ASSET");
                                        }
                                }
                                catch (Exception se)
                                {
                                        logger.info("[ScormDAOImpl]insertUserSCO--have some error");
                                }
                        }

                        logger.info("[ScormDAOImpl]insertUserSCO--end!");
                }
                catch (Exception se)
                {
                        se.printStackTrace();
                        throw new ScormSysException(se);
                }
                finally
                {
                        DBUtil.closeStatement(stmtInsertUserSCO);
                        DBUtil.closeConnection(conn);
                }
        }

        /**
         * this method is used to initialize the data model with temporary data.
         *
         * @param this_userID
         * @param this_location
         * @param this_dataFromLMS
         * @param this_masteryScore
         * @param this_maxTimeAllowed
         * @param this_timeLimitAction
         * @return
         */
        protected SCODataManager initSCOData(String this_userID,
                                             String this_location, String this_dataFromLMS,
                                             String this_masteryScore, String this_maxTimeAllowed,
                                             String this_timeLimitAction)
        {
                SCODataManager tmpSCOData = new SCODataManager();

                try
                {
                        logger.info("[ScormDAOImpl]initSCOData-----start!");
                        logger.info("[ScormDAOImpl]initSCOData-----getting user info");

                        String studentName = UserHelper.getUser(String.valueOf(this_userID))
                                .getName();

                        logger.info("[ScormDAOImpl]initSCOData-----student name: " +
                                studentName);

                        // Build Core
                        CMIScore tmpScore = new CMIScore();
                        tmpScore.setRaw("0");
                        tmpScore.setMax("0");
                        tmpScore.setMin("0");

                        CMICore tmpCore = new CMICore();
                        tmpCore.setStudentId(this_userID);
                        tmpCore.setStudentName(studentName);
                        tmpCore.setLessonLocation(this_location);
                        tmpCore.setCredit("credit");
                        tmpCore.setLessonStatus("not attempted");
                        tmpCore.setEntry("ab-initio");
                        tmpCore.setTotalTime("00:00:00.0");
                        tmpCore.setLessonMode("normal");
                        tmpCore.setExit("");
                        tmpCore.setScore(tmpScore);
                        tmpSCOData.setCore(tmpCore);

                        // Build Suspend Data
                        CMISuspendData tmpSuspendData = new CMISuspendData();
                        tmpSuspendData.setSuspendData("");
                        tmpSCOData.setSuspendData(tmpSuspendData);

                        // Build Launch Data
                        CMILaunchData tmpLaunchData = new CMILaunchData();
                        tmpLaunchData.setLaunchData(this_dataFromLMS);
                        tmpSCOData.setLaunchData(tmpLaunchData);

                        // Build Comments
                        CMICommentsFromLms tmpComments = new CMICommentsFromLms("");
                        tmpSCOData.setCommentsFromLMS(tmpComments);

                        // Build Objective Data
                        /*
                          CMIObjectives tmpObjectives = new CMIObjectives();
                          CMIObjectiveData tmpObjectiveData = new CMIObjectiveData();
                          tmpObjectiveData.setId("O001");
                          tmpObjectiveData.setScore(tmpScore);
                          tmpObjectiveData.setStatus("not attempted");
                          tmpObjectives.setObjectives(tmpObjectiveData,0);
                          tmpSCOData.setObjectives(tmpObjectives);
                        */

                        // Build Student Data
                        CMIStudentData tmpStudentData = new CMIStudentData();

                        tmpStudentData.setMasteryScore(this_masteryScore);
                        tmpStudentData.setMaxTimeAllowed(this_maxTimeAllowed);
                        tmpStudentData.setTimeLimitAction(this_timeLimitAction);
                        tmpSCOData.setStudentData(tmpStudentData);

                        // Build Student Preference
                        CMIStudentPreference tmpStudentPreference = new CMIStudentPreference();
                        tmpStudentPreference.setAudio("3");
                        tmpStudentPreference.setLanguage("Chinese");
                        tmpStudentPreference.setSpeed("4");
                        tmpStudentPreference.setText("10");
                        tmpSCOData.setStudentPreference(tmpStudentPreference);
                }
                catch (Exception e)
                {
                        e.printStackTrace();
                }

                logger.info("[ScormDAOImpl]initSCOData-----end!");

                return tmpSCOData;
        }

        /**
         * get all the scorm's model
         *
         * @param courseContentNodeID
         * @return
         * @throws ScormSysException
         */
        public List getItems(int courseContentNodeID) throws ScormSysException
        {
                Connection conn = null;
                PreparedStatement stmtISelectItems = null;
                ResultSet rs = null;
                List itemList = new ArrayList();
                ItemModel item = null;

                logger.info("[ScormDAOImpl]getItems--***********************start!");
                logger.info(
                        "[ScormDAOImpl]getItems--***********************courseContentNodeID=" +
                                courseContentNodeID);

                String selectItemsSql = "select * from  C_Item_Tab where CourseContentNodeID=? ORDER BY Sequence";

                logger.info(
                        "[ScormDAOImpl]getItems--***********************selectItemsSql=" +
                                selectItemsSql);

                try
                {
                        conn = DBUtil.getConnection();
                        stmtISelectItems = conn.prepareStatement(selectItemsSql);
                        stmtISelectItems.setInt(1, courseContentNodeID);
                        rs = stmtISelectItems.executeQuery();

                        while (rs.next())
                        {
                                item = new ItemModel(rs.getInt("ItemID"),
                                        rs.getInt("CourseContentNodeID"),
                                        rs.getInt("RelationID"),
                                        StringUtil.parseInt(rs.getString("Type")),
                                        rs.getString("Identifier"),
                                        StringUtil.nullToStr(rs.getString("ItemType")),
                                        rs.getString("Title"), rs.getString("Launch"),
                                        rs.getString("ParameterString"),
                                        rs.getString("DataFromLMS"),
                                        rs.getString("Prerequisites"),
                                        rs.getFloat("MasteryScore"),
                                        rs.getString("MaxTimeAllowed"),
                                        rs.getString("TimeLimitAction"), rs.getInt("Sequence"),
                                        rs.getInt("TheLevel"));
                                itemList.add(item);
                        }

                        logger.info("[ScormDAOImpl]getItems--***********************end!");
                }
                catch (SQLException se)
                {
                        se.printStackTrace();
                        throw new ScormSysException(se);
                }
                finally
                {
                        DBUtil.closeResultSet(rs);
                        DBUtil.closeStatement(stmtISelectItems);
                        DBUtil.closeConnection(conn);
                }

                return itemList;
        }

        /**
         * get all the scorm's model  according to the user.
         *
         * @param courseContentNodeID
         * @return
         * @throws ScormSysException
         */
        public List getUserSCOs(int userID, int courseContentNodeID)
                throws ScormSysException
        {
                Connection conn = null;
                PreparedStatement stmtISelectUserSCOs = null;
                ResultSet rs = null;
                List userSCOList = new ArrayList();
                UserSCOModel userSCO = null;

                logger.info("[ScormDAOImpl]getUserSCOs--***********************start!");
                logger.info(
                        "[ScormDAOImpl]getUserSCOs--***********************courseContentNodeID=" +
                                courseContentNodeID);

                String selectUserSCOSql = "select C_UserSCO_Tab.UserID,C_UserSCO_Tab.SCOID,C_UserSCO_Tab.Launch," +
                        "C_UserSCO_Tab.ParameterString,C_UserSCO_Tab.LessonStatus," +
                        "C_UserSCO_Tab.Prerequisites,C_UserSCO_Tab.ExitString," +
                        "C_UserSCO_Tab.Entry ,C_UserSCO_Tab.MasteryScore," +
                        "C_UserSCO_Tab.Sequence   Sequence,C_UserSCO_Tab.ItemType," +
                        "C_Item_Tab.CourseContentNodeID,C_Item_Tab.relationID,C_Item_Tab.type,C_Item_Tab.identifier," +
                        "C_Item_Tab.title,C_Item_Tab.dataFromLMS,C_Item_Tab.maxTimeAllowed," +
                        "C_Item_Tab.timeLimitAction,C_Item_Tab.theLevel " +
                        " from C_Item_Tab,C_UserSCO_Tab where C_Item_Tab.ItemID=C_UserSCO_Tab.SCOID and UserID=? and CourseContentNodeID=? ORDER BY Sequence";

                logger.info(
                        "[ScormDAOImpl]getUserSCOs--***********************selectUserSCOSql=" +
                                selectUserSCOSql);
                logger.info("[ScormDAOImpl]getUserSCOs--***********************userID=" +
                        userID);
                logger.info(
                        "[ScormDAOImpl]getUserSCOs--***********************courseContentNodeID=" +
                                courseContentNodeID);

                try
                {
                        conn = DBUtil.getConnection();
                        stmtISelectUserSCOs = conn.prepareStatement(selectUserSCOSql);
                        stmtISelectUserSCOs.setInt(1, userID);
                        stmtISelectUserSCOs.setInt(2, courseContentNodeID);
                        rs = stmtISelectUserSCOs.executeQuery();

                        while (rs.next())
                        {
                                userSCO = new UserSCOModel(rs.getInt("UserID"),
                                        rs.getInt("SCOID"), rs.getInt("CourseContentNodeID"),
                                        rs.getString("Launch"),
                                        rs.getString("ParameterString"),
                                        StringUtils.trimToEmpty(rs.getString("LessonStatus")),
                                        rs.getString("Prerequisites"),
                                        rs.getString("ExitString"), rs.getString("Entry"),
                                        rs.getFloat("MasteryScore"), rs.getInt("Sequence"),
                                        StringUtil.nullToStr(rs.getString("ItemType")),
                                        rs.getInt("RelationID"),
                                        StringUtil.parseInt(rs.getString("Type")),
                                        rs.getString("Identifier"), rs.getString("Title"),
                                        rs.getString("DataFromLMS"),
                                        rs.getString("MaxTimeAllowed"),
                                        rs.getString("TimeLimitAction"), rs.getInt("TheLevel"));
                                userSCOList.add(userSCO);
                        }

                        logger.info(
                                "[ScormDAOImpl]getUserSCOs--***********************end!");
                }
                catch (SQLException se)
                {
                        se.printStackTrace();
                        throw new ScormSysException(se);
                }
                finally
                {
                        DBUtil.closeResultSet(rs);
                        DBUtil.closeStatement(stmtISelectUserSCOs);
                        DBUtil.closeConnection(conn);
                }

                return userSCOList;
        }

        /**
         * get a  ItemModel  according to the itemID.
         *
         * @param itemID
         * @return
         * @throws ScormSysException
         */
        public ItemModel getItem(int itemID) throws ScormSysException
        {
                Connection conn = null;
                PreparedStatement stmtISelectIterm = null;
                ResultSet rs = null;
                ItemModel item = null;

                logger.info("[ScormDAOImpl]getItem--***********************start!");
                logger.info("[ScormDAOImpl]getItem--***********************itemID=" +
                        itemID);

                String selectItemSql = "select *  from C_Item_Tab  where ItemID=?";

                logger.info(
                        "[ScormDAOImpl]getItem--***********************selectItemSql=" +
                                selectItemSql);

                try
                {
                        conn = DBUtil.getConnection();
                        stmtISelectIterm = conn.prepareStatement(selectItemSql);
                        stmtISelectIterm.setInt(1, itemID);
                        rs = stmtISelectIterm.executeQuery();

                        if (rs.next())
                        {
                                item = new ItemModel(rs.getInt("ItemID"),
                                        rs.getInt("CourseContentNodeID"),
                                        rs.getInt("RelationID"),
                                        StringUtil.parseInt(rs.getString("Type")),
                                        rs.getString("Identifier"),
                                        StringUtil.nullToStr(rs.getString("ItemType")),
                                        rs.getString("Title"), rs.getString("Launch"),
                                        rs.getString("ParameterString"),
                                        rs.getString("DataFromLMS"),
                                        rs.getString("Prerequisites"),
                                        rs.getFloat("MasteryScore"),
                                        rs.getString("MaxTimeAllowed"),
                                        rs.getString("TimeLimitAction"), rs.getInt("Sequence"),
                                        rs.getInt("TheLevel"));
                        }

                        logger.info("[ScormDAOImpl]getItem--***********************end!");
                }
                catch (SQLException se)
                {
                        se.printStackTrace();
                        throw new ScormSysException(se);
                }
                finally
                {
                        DBUtil.closeResultSet(rs);
                        DBUtil.closeStatement(stmtISelectIterm);
                        DBUtil.closeConnection(conn);
                }

                return item;
        }

        /**
         * get a  scorm's model  according to the user.
         *
         * @param userID
         * @param scoID
         * @return
         * @throws ScormSysException
         */
        public UserSCOModel getUserSCO(int userID, int scoID)
                throws ScormSysException
        {
                Connection conn = null;
                PreparedStatement stmtISelectUserSCO = null;
                ResultSet rs = null;
                UserSCOModel userSCO = null;

                logger.info("[ScormDAOImpl]getUserSCO--***********************start!");
                logger.info("[ScormDAOImpl]getUserSCO--***********************userID=" +
                        userID);
                logger.info("[ScormDAOImpl]getUserSCO--***********************scoID=" +
                        scoID);

                String selectUserSCOSql = "select C_UserSCO_Tab.UserID,C_UserSCO_Tab.SCOID,C_UserSCO_Tab.Launch," +
                        "C_UserSCO_Tab.ParameterString,C_UserSCO_Tab.LessonStatus," +
                        "C_UserSCO_Tab.Prerequisites,C_UserSCO_Tab.ExitString," +
                        "C_UserSCO_Tab.Entry ,C_UserSCO_Tab.MasteryScore," +
                        "C_UserSCO_Tab.Sequence,C_UserSCO_Tab.ItemType," +
                        "C_Item_Tab.CourseContentNodeID,C_Item_Tab.relationID,C_Item_Tab.type,C_Item_Tab.identifier," +
                        "C_Item_Tab.title,C_Item_Tab.dataFromLMS,C_Item_Tab.maxTimeAllowed," +
                        "C_Item_Tab.timeLimitAction,C_Item_Tab.theLevel " +
                        " from C_Item_Tab,C_UserSCO_Tab where C_Item_Tab.ItemID=C_UserSCO_Tab.SCOID and UserID=? and SCOID=?";

                logger.info(
                        "[ScormDAOImpl]getUserSCO--***********************selectUserSCOSql=" +
                                selectUserSCOSql);

                try
                {
                        conn = DBUtil.getConnection();
                        stmtISelectUserSCO = conn.prepareStatement(selectUserSCOSql);
                        stmtISelectUserSCO.setInt(1, userID);
                        stmtISelectUserSCO.setInt(2, scoID);
                        rs = stmtISelectUserSCO.executeQuery();

                        if (rs.next())
                        {
                                userSCO = new UserSCOModel(rs.getInt("UserID"),
                                        rs.getInt("SCOID"), rs.getInt("CourseContentNodeID"),
                                        rs.getString("Launch"),
                                        rs.getString("ParameterString"),
                                        StringUtils.trimToEmpty(rs.getString("LessonStatus")),
                                        rs.getString("Prerequisites"),
                                        rs.getString("ExitString"), rs.getString("Entry"),
                                        rs.getFloat("MasteryScore"), rs.getInt("Sequence"),
                                        StringUtil.nullToStr(rs.getString("ItemType")),
                                        rs.getInt("RelationID"),
                                        StringUtil.parseInt(rs.getString("Type")),
                                        rs.getString("Identifier"), rs.getString("Title"),
                                        rs.getString("DataFromLMS"),
                                        rs.getString("MaxTimeAllowed"),
                                        rs.getString("TimeLimitAction"), rs.getInt("TheLevel"));
                        }

                        logger.info("[ScormDAOImpl]getUserSCO--***********************end!");
                }
                catch (SQLException se)
                {
                        se.printStackTrace();
                        throw new ScormSysException(se);
                }
                finally
                {
                        DBUtil.closeResultSet(rs);
                        DBUtil.closeStatement(stmtISelectUserSCO);
                        DBUtil.closeConnection(conn);
                }

                return userSCO;
        }

        /**
         * update the user's lessonStatus
         *
         * @param userID
         * @param scoID
         * @param lessonStatus
         * @throws ScormSysException
         */
        public void updateUserSCO(int userID, int scoID, String lessonStatus)
                throws ScormSysException
        {
                Connection conn = null;
                PreparedStatement stmtIUpdateUserSCOs = null;

                logger.info(
                        "[ScormDAOImpl]updateUserSCO--***********************start!");
                logger.info(
                        "[ScormDAOImpl]updateUserSCO--***********************scoID=" +
                                scoID);

                String updateUserSCOSql = "update C_UserSCO_Tab set LessonStatus=?  where UserID=? and SCOID=?";

                logger.info(
                        "[ScormDAOImpl]updateUserSCO--***********************updateUserSCOSql=" +
                                updateUserSCOSql);
                logger.info("[ScormDAOImpl]updateUserSCO--userID: " + userID);
                logger.info("[ScormDAOImpl]updateUserSCO--scoID: " + scoID);
                logger.info("[ScormDAOImpl]updateUserSCO--lessonStatus: " +
                        lessonStatus);

                try
                {
                        conn = DBUtil.getConnection();
                        stmtIUpdateUserSCOs = conn.prepareStatement(updateUserSCOSql);
                        stmtIUpdateUserSCOs.setString(1, lessonStatus);
                        stmtIUpdateUserSCOs.setInt(2, userID);
                        stmtIUpdateUserSCOs.setInt(3, scoID);

                        stmtIUpdateUserSCOs.executeUpdate();

                        logger.info(
                                "[ScormDAOImpl]updateUserSCO--***********************end!");
                }
                catch (SQLException se)
                {
                        se.printStackTrace();
                        throw new ScormSysException(se);
                }
                finally
                {
                        DBUtil.closeStatement(stmtIUpdateUserSCOs);
                        DBUtil.closeConnection(conn);
                }
        }

        /**
         * update the user's lessonStatus and sessionTime
         *
         * @param userID
         * @param scoID
         * @param lessonStatus
         * @param sessionTime
         * @throws ScormSysException
         */
        public void updateUserSCO(int userID, int scoID, String lessonStatus,
                                  String sessionTime) throws ScormSysException
        {
                Connection conn = null;
                PreparedStatement stmtIUpdateUserSCOs = null;

                logger.info(
                        "[ScormDAOImpl]updateUserSCO--***********************start!");
                logger.info(
                        "[ScormDAOImpl]updateUserSCO--***********************scoID=" +
                                scoID);

                String updateUserSCOSql = "update C_UserSCO_Tab set LessonStatus=? ,ExitString=? where UserID=? and SCOID=?";

                logger.info(
                        "[ScormDAOImpl]updateUserSCO--***********************updateUserSCOSql=" +
                                updateUserSCOSql);
                logger.info("[ScormDAOImpl]updateUserSCO--userID: " + userID);
                logger.info("[ScormDAOImpl]updateUserSCO--scoID: " + scoID);
                logger.info("[ScormDAOImpl]updateUserSCO--lessonStatus: " +
                        lessonStatus);
                logger.info("[ScormDAOImpl]updateUserSCO--sessionTime: " + sessionTime);

                try
                {
                        conn = DBUtil.getConnection();
                        stmtIUpdateUserSCOs = conn.prepareStatement(updateUserSCOSql);
                        stmtIUpdateUserSCOs.setString(1, lessonStatus);
                        stmtIUpdateUserSCOs.setString(2, sessionTime);
                        stmtIUpdateUserSCOs.setInt(3, userID);
                        stmtIUpdateUserSCOs.setInt(4, scoID);

                        stmtIUpdateUserSCOs.executeUpdate();

                        logger.info(
                                "[ScormDAOImpl]updateUserSCO--***********************end!");
                }
                catch (SQLException se)
                {
                        se.printStackTrace();
                        throw new ScormSysException(se);
                }
                finally
                {
                        DBUtil.closeStatement(stmtIUpdateUserSCOs);
                        DBUtil.closeConnection(conn);
                }
        }

        /**
         * 判断用户是否已初始化scorm数据
         *
         * @param userID
         * @param courseContentNodeID
         * @return
         * @throws ScormSysException
         */
        public boolean isInitUserSCO(int userID, int courseContentNodeID)
                throws ScormSysException
        {
                boolean isInit = false;

                Connection conn = null;
                PreparedStatement stmtSelectUserSCO = null;
                ResultSet rs = null;

                String sqlSelectUserSCO = "select C_UserSCO_Tab.SCOID " + "from" +
                        " C_Item_Tab,C_CourseContentTreeNode_Tab,C_UserSCO_Tab " +
                        "where " +
                        "c_item_tab.CourseContentNodeID=C_CourseContentTreeNode_Tab.NodeID " +
                        "and C_UserSCO_Tab.SCOID=C_Item_Tab.ItemID " +
                        "and C_UserSCO_Tab.userID=? and C_CourseContentTreeNode_Tab.NodeID=? ";
                logger.info("[ScormDAOImpl]isInitUserSCO--start!");
                logger.info("[ScormDAOImpl]isInitUserSCO--sqlSelectUserSCO=" +
                        sqlSelectUserSCO);

                try
                {
                        conn = DBUtil.getConnection();
                        stmtSelectUserSCO = conn.prepareStatement(sqlSelectUserSCO);

                        stmtSelectUserSCO.setInt(1, userID);
                        stmtSelectUserSCO.setInt(2, courseContentNodeID);
                        rs = stmtSelectUserSCO.executeQuery();

                        isInit = rs.next();

                        logger.info("[ScormDAOImpl]isInitUserSCO--isInit=" + isInit);
                        logger.info("[ScormDAOImpl]isInitUserSCO--end!");
                }
                catch (SQLException se)
                {
                        se.printStackTrace();
                        throw new ScormSysException(se);
                }
                finally
                {
                        DBUtil.closeResultSet(rs);
                        DBUtil.closeStatement(stmtSelectUserSCO);
                        DBUtil.closeConnection(conn);
                }

                return isInit;
        }

        /**
         * 根据itemID，返回对应的SCO，如果itemID的类型就是SCO，否则返回itemID下一个SCO
         *
         * @param itemID
         * @return
         * @throws ScormSysException
         */
        public int getSCO(int itemID) throws ScormSysException
        {
                int itemID_tmp = -1;
                String itemType = null;
                Connection conn = null;
                PreparedStatement stmtSelectUserSCO = null;
                ResultSet rs = null;

                logger.info("[ScormDAOImpl]getSCO--@@start!  itemID=" + itemID);

                ItemModel im = getItem(itemID);
                itemType = StringUtil.nullToStr(im.getItemType());

                int courseContentNodeID = im.getCourseContentNodeID();
                logger.info("[ScormDAOImpl]getSCO--@@start!  courseContentNodeID=" +
                        courseContentNodeID);
                logger.info("[ScormDAOImpl]getSCO--itemType = " + itemType);

                if (!"".equals(itemType))
                {
                        logger.info("[ScormDAOImpl]getSCO--正常直接返回该itemID！");

                        return itemID;
                }
                else
                {
                        logger.info("[ScormDAOImpl]getSCO--非SCO，需要往下遍历，直到发现一个sco！");
                }

                String sql = "select itemID,itemType" + " from" + " C_Item_Tab " +
                        "where " + "courseContentNodeID=? ";

                logger.info("[ScormDAOImpl]getSCO--sql=" + sql);

                try
                {
                        conn = DBUtil.getConnection();
                        stmtSelectUserSCO = conn.prepareStatement(sql);
                        stmtSelectUserSCO.setInt(1, courseContentNodeID);
                        rs = stmtSelectUserSCO.executeQuery();

                        //非SCO，需要往下遍历，直到发现一个sco
                        while (rs.next())
                        {
                                itemType = StringUtil.nullToStr(rs.getString("itemType"));
                                itemID_tmp = rs.getInt("itemID");
                                logger.info("[ScormDAOImpl]getSCO--while start! itemID_tmp=" +
                                        itemID_tmp);
                                logger.info("[ScormDAOImpl]getSCO--while itemType=" + itemType);
                                logger.info(
                                        "[ScormDAOImpl]getSCO--while rs.getString(\"itemType\")=" +
                                                rs.getString("itemType"));

                                if (itemID_tmp > itemID)
                                {
                                        logger.info("[ScormDAOImpl]getSCO--itemID_tmp > itemID");
                                        logger.info(
                                                "[ScormDAOImpl]getSCO--\"sco\".equals(itemType_tmp)=" +
                                                        "sco".equals(itemType));

                                        if (!"".equals(itemType))
                                        {
                                                logger.info("[ScormDAOImpl]getSCO--return itemID=" +
                                                        itemID_tmp);

                                                return itemID_tmp;
                                        }
                                }
                        }
                }
                catch (SQLException se)
                {
                        se.printStackTrace();
                        throw new ScormSysException(se);
                }
                finally
                {
                        DBUtil.closeResultSet(rs);
                        DBUtil.closeStatement(stmtSelectUserSCO);
                        DBUtil.closeConnection(conn);
                }

                return itemID;
        }
}
