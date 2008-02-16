/**
 * CourseContentDAOOracle.java.
 * User: dengj  Date: 2004-4-30
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.course.dao;

import com.ulearning.ulms.course.exceptions.CourseDAOSysException;
import com.ulearning.ulms.course.model.CourseContentForm;
import com.ulearning.ulms.util.log.LogUtil;

import java.io.UnsupportedEncodingException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class CourseContentDAOOracle extends CourseContentDAOImpl
{
        /**
         * Insert a new CourseContent record to database
         *
         * @param courseContentForm the value object to be added
         * @throws CourseDAOSysException
         */
        public int addCourseContent(CourseContentForm courseContentForm)
                throws CourseDAOSysException
        {
                Statement stmt = null;
                ResultSet rs = null;
                int nodeID = 0;
                java.sql.Date dayToInsert = new java.sql.Date(System.currentTimeMillis());
                String createDate = "to_date('" + dayToInsert + "','yyyy-mm-dd')";
                String LastModDate = createDate; //"to_date('" + courseContentForm.getLastModDate() + "','yyyy-mm-dd')";
                String sqlStr = "insert into C_CourseContentTreeNode_Tab" +
                        "(NodeID,NodeTitle,RelationID,Type,NodeType," +
                        "IsUserful,IsOpenGuest,IsContent,Link,IsHyperLink," +
                        "IsView,Depth,ParentID,CreateDate,LastModDate ," +
                        "OrderIndex,CATALOG ) " + "values" + "(nodeID.nextval,'" +
                        courseContentForm.getNodeTitle() + "'," +
                        courseContentForm.getRelationID() + "," +
                        courseContentForm.getType() + "," +
                        courseContentForm.getNodetype() + "," +
                        courseContentForm.getIsUserful() + "," +
                        courseContentForm.getIsOpenGuest() + "," +
                        courseContentForm.getIsContent() + ",'" +
                        courseContentForm.getLink() + "'," +
                        courseContentForm.getIsHyperLink() + "," +
                        courseContentForm.getIsView() + "," + courseContentForm.getDepth() +
                        "," + courseContentForm.getParentID() + "," + createDate + "," +
                        LastModDate + "," + courseContentForm.getOrderIndex() + ",'" +
                        courseContentForm.getCatalog() + "')";

                try
                {
                        dbConnection = getConnection();
                        stmt = dbConnection.createStatement();
                        LogUtil.debug("system",
                                "[CourseContentDAOOracle]====================the sql string is : " +
                                        sqlStr);
                        stmt.execute(sqlStr);

                        String isql = "SELECT nodeID.currval  as nodeID FROM DUAL";
                        stmt.execute(isql);
                        rs = stmt.getResultSet();

                        if (rs.next())
                        {
                                nodeID = rs.getInt("nodeID");
                        }
                }
                catch (SQLException se)
                {
                        throw new CourseDAOSysException("SQLException while updating " +
                                "course; Serial = " + courseContentForm.getNodeTitle() +
                                " :\n" + se, se);
                }
                finally
                {
                        try
                        {
                                closeResultSet(rs);

                                if (stmt != null)
                                {
                                        stmt.close();
                                }

                                closeConnection();
                        }
                        catch (Exception se)
                        {
                                se.printStackTrace();
                        }
                }

                return nodeID;
        }

        /**
         * Update CourseContent by the new Form
         *
         * @param courseContentForm value object for changed
         * @throws CourseDAOSysException
         */
        public void updateCourseContent(CourseContentForm courseContentForm)
                throws CourseDAOSysException
        {
                Statement stmt = null;
                java.sql.Date nowday = new java.sql.Date(System.currentTimeMillis());
                String LastModDate = "to_date('" + nowday + "','yyyy-mm-dd')";
                String sqlStr = "update C_CourseContentTreeNode_Tab set " +
                        "NodeTitle = '" + courseContentForm.getNodeTitle() + "', " +
                        "RelationID = " + courseContentForm.getRelationID() + ", " +
                        "Type = " + courseContentForm.getType() + ", " + "NodeType = " +
                        courseContentForm.getNodetype() + ", " + "IsUserful = " +
                        courseContentForm.getIsUserful() + ", " + "IsOpenGuest = " +
                        courseContentForm.getIsOpenGuest() + ", " + "IsContent = " +
                        courseContentForm.getIsContent() + ", " + "Link = '" +
                        courseContentForm.getLink() + "', " + "IsHyperLink = " +
                        courseContentForm.getIsHyperLink() + ", " + "IsView = " +
                        courseContentForm.getIsView() + ", " + "Depth = " +
                        courseContentForm.getDepth() + ", " + "ParentID = " +
                        courseContentForm.getParentID() + ", " + "LastModDate = " +
                        LastModDate + ", " + "OrderIndex = " +
                        courseContentForm.getOrderIndex() + ", " + "Catalog = '" +
                        courseContentForm.getCatalog() + "' " + " where NodeID = " +
                        courseContentForm.getNodeID();

                try
                {
                        dbConnection = getConnection();
                        stmt = dbConnection.createStatement();
                        LogUtil.debug("system",
                                "[CourseContentDAOOracle]====================the sql string is : " +
                                        sqlStr);
                        stmt.execute(sqlStr);
                }
                catch (SQLException se)
                {
                        throw new CourseDAOSysException("SQLException while updating " +
                                "Course; Serial = " + courseContentForm.getNodeTitle() +
                                " :\n" + se, se);
                }
                finally
                {
                        try
                        {
                                stmt.close();
                                closeConnection();
                        }
                        catch (SQLException se)
                        {
                        }
                }
        }
}
