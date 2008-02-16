/**
 * ScormDAOOracle.java.
 * User: fengch  Date: 2004-7-13
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.scorm.dao;

import com.ulearning.ulms.core.util.StringUtil;
import com.ulearning.ulms.scorm.adl.parsers.dom.ADLItem;
import com.ulearning.ulms.scorm.datamodels.SCODataManager;
import com.ulearning.ulms.scorm.exceptions.ScormSysException;
import com.ulearning.ulms.scorm.util.ScormConstants;
import com.ulearning.ulms.util.DBUtil;
import com.ulearning.ulms.util.log.LogUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

import java.net.URLDecoder;

import java.sql.*;

import java.util.Date;
import java.util.Vector;


public class ScormDAOOracle extends ScormDAOImpl
{
        /**
         * to insert scorm's item to table C_Item_Tab.
         *
         * @param relationID
         * @param type
         * @param courseContentNodeID
         * @param items
         * @throws ScormSysException
         */
        public void insertItem(int relationID, int type, int courseContentNodeID,
                               Vector items) throws ScormSysException
        {
                Connection conn = null;
                PreparedStatement stmtInsertItem = null;

                LogUtil.debug("scorm",
                        "[ScormDAOOracle]insertItem--***********************relationID=" +
                                relationID);
                LogUtil.debug("scorm",
                        "[ScormDAOOracle]insertItem--***********************type=" + type);
                LogUtil.debug("scorm",
                        "[ScormDAOOracle]insertItem--***********************courseContentNodeID=" +
                                courseContentNodeID);

                String sqlInsertItem = "INSERT INTO C_Item_Tab (ItemID,RelationID,Type,CourseContentNodeID," +
                        " Identifier, ItemType, Title, Launch, " +
                        "ParameterString, DataFromLMS, Prerequisites, MasteryScore, " +
                        "MaxTimeAllowed, TimeLimitAction, Sequence, TheLevel) " +
                        "VALUES(itemID.nextval,?,?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

                LogUtil.debug("scorm",
                        "[ScormDAOOracle]insertItem--***********************sqlInsertItem=" +
                                sqlInsertItem);

                try
                {
                        conn = DBUtil.getConnection();

                        stmtInsertItem = conn.prepareStatement(sqlInsertItem);

                        // Create a temporary ADLItem
                        ADLItem tempItem = new ADLItem();

                        LogUtil.debug("scorm",
                                "[ScormDAOOracle]insertItem--***********************items.size=" +
                                        items.size());

                        // Loop through each item in the course adding it to the database
                        for (int i = 0; i < items.size(); i++)
                        {
                                LogUtil.debug("scorm",
                                        "[ScormDAOOracle]insertItem--***********************i=" +
                                                i);
                                tempItem = (com.ulearning.ulms.scorm.adl.parsers.dom.ADLItem) items.elementAt(i);

                                // Decode the URL before inserting into the database
                                String alteredLocation = new String();

                                //  If its external, don't concatenate to the local Web root.
                                if ((tempItem.getLaunchLocation().startsWith("http://")) ||
                                        (tempItem.getLaunchLocation().startsWith("https://")))
                                {
                                        alteredLocation = URLDecoder.decode((String) tempItem.getLaunchLocation());
                                }
                                else
                                {
                                        // Create the altered location (with decoded url)
                                        alteredLocation = "Scorm/ImportRoot/" +
                                                courseContentNodeID + "/" +
                                                URLDecoder.decode((String) tempItem.getLaunchLocation());
                                }

                                stmtInsertItem.setInt(1, relationID);
                                stmtInsertItem.setString(2, String.valueOf(type));
                                stmtInsertItem.setInt(3, courseContentNodeID);
                                stmtInsertItem.setString(4, tempItem.getIdentifier());
                                stmtInsertItem.setString(5, tempItem.getScormType());
                                stmtInsertItem.setString(6, tempItem.getTitle());
                                stmtInsertItem.setString(7, alteredLocation);
                                stmtInsertItem.setString(8, tempItem.getParameterString());
                                stmtInsertItem.setString(9, tempItem.getDataFromLMS());
                                stmtInsertItem.setString(10, tempItem.getPrerequisites());
                                stmtInsertItem.setFloat(11,
                                        StringUtil.parseFloat(tempItem.getMasteryScore()));
                                stmtInsertItem.setString(12, tempItem.getMaxTimeAllowed());
                                stmtInsertItem.setString(13, tempItem.getTimeLimitAction());
                                stmtInsertItem.setInt(14, i);
                                stmtInsertItem.setInt(15, tempItem.getLevel());
                                stmtInsertItem.executeUpdate();
                        }
                }
                catch (SQLException se)
                {
                        se.printStackTrace();
                        throw new ScormSysException(se);
                }
                finally
                {
                        DBUtil.closeStatement(stmtInsertItem);
                        DBUtil.closeConnection(conn);
                }
        }
}
