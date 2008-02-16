/** * BatchImportMasterUserUtil.java.
 * User: chenxj
 * Date: 2004-11-18
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd. * All rights reserved. */
package com.ulearning.ulms.course.util;

import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.core.security.bean.SecurityConstants;
import com.ulearning.ulms.core.security.bean.SecurityHelper;
import com.ulearning.ulms.core.util.StringUtil;
import com.ulearning.ulms.course.helper.CourseUserHelper;
import com.ulearning.ulms.user.bean.UserHelper;
import com.ulearning.ulms.util.DbInputUtil;
import com.ulearning.ulms.util.LMSConstants;
import com.ulearning.ulms.util.log.DebugUtil;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;


public class BatchImportMasterUserUtil
{
        private HSSFWorkbook wb = null;
        private HSSFSheet sheet = null;
        private HSSFRow row = null;
        private HSSFCell cell = null;
        private String res = ""; //return check info
        private String insert_str = ""; //return insert info
        private int rowCount = 0; //excel total row
        private String tempStr = ""; //deal 空行的变量
        private String loginName = ""; //
        private String courseNumber = "";
        private String courseRole = "";

        public String checkExcel(String filePath, int type,
                                 HttpServletRequest request) throws ULMSSysException
        {
                int cellNum = 0;
                DebugUtil.print("[BatchImportMasterUser]=============filepath=" +
                        filePath);

                try
                {
                        String coursName = "";

                        if (type == SecurityConstants.USER_COURSE_RELATION)
                        {
                                coursName = "课程";
                        }
                        else if (type == SecurityConstants.USER_CERTIFICATE_RELATION)
                        {
                                coursName = "班级s";
                        }

                        FileInputStream fileStream = new FileInputStream(filePath);
                        wb = new HSSFWorkbook(fileStream);
                        sheet = wb.getSheetAt(0);
                        rowCount = sheet.getLastRowNum();
                        DebugUtil.print("[BatchImportMasterUser]==============rowCount=" +
                                rowCount);

                        for (int i = 1; i <= rowCount; i++)
                        {
                                row = sheet.getRow(i);
                                DebugUtil.print("[BatchImportMasterUser]==============row=" +
                                        row);

                                if (row == null)
                                {
                                        break;
                                }

                                tempStr = "";
                                cellNum = row.getLastCellNum();

                                for (int j = 0; j < cellNum; j++)
                                {
                                        tempStr += DbInputUtil.getThisCellValue(row, j);
                                }

                                if (tempStr.trim().equals(""))
                                {
                                        continue;
                                }

                                loginName = DbInputUtil.getThisCellValue(row, 0).trim();
                                courseNumber = DbInputUtil.getThisCellValue(row, 1).trim();
                                courseRole = DbInputUtil.getThisCellValue(row, 2).trim();

                                if (loginName.equals("") || courseNumber.equals("") ||
                                        courseRole.equals(""))
                                {
                                        res += ("第 " + (i + 1) + " 行没有写全,请把此行写全<br>");

                                        continue;
                                }

                                String aspIDStr = (String) request.getSession()
                                        .getAttribute(LMSConstants.USER_ASPID_KEY);
                                String orgIDStr = (String) request.getSession()
                                        .getAttribute(LMSConstants.USER_ORGID_KEY);

                                //String orgIDStr ="0";
                                int orgID = Integer.parseInt(orgIDStr);
                                int aspID = Integer.parseInt(aspIDStr);

                                if ((UserHelper.getUserID(loginName) == 0) ||
                                        !CourseUserHelper.isValidateUser(loginName, aspID))
                                {
                                        res += ("第" + (i + 1) + "行 不存在的用户名:" + loginName + " <br>");
                                }

                                System.out.println("check:" + courseNumber + "," + orgID);

                                if (!CourseUserHelper.isValidateRelationCode(courseNumber,
                                        type, aspID))
                                {
                                        res += ("第" + (i + 1) + "行 不存在的" + coursName + "编号'" +
                                                courseNumber + "'<br>");
                                }

                                List roleList = StringUtil.split(courseRole, "/");

                                for (int j = 0; j < roleList.size(); j++)
                                {
                                        String roleName = ((String) roleList.get(j)).trim();

                                        if (SecurityHelper.getRoleIDByName(roleName) == 0)
                                        {
                                                res += ("第" + (i + 1) + "行 不存在的课程角色:" +
                                                        (String) roleList.get(j) + "<br>");
                                        }
                                }
                        }
                }
                catch (Exception e)
                {
                        e.printStackTrace(); //To change body of catch statement use File | Settings | File Templates.
                }

                return res;
        }

        public void insertIntoDb(String filePath, int type,
                                 HttpServletRequest request) throws ULMSSysException
        {
                int cellNum = 0;
                DebugUtil.print("[BatchImportMasterUser]=============filepath=" +
                        filePath);

                try
                {
                        FileInputStream fileStream = new FileInputStream(filePath);
                        wb = new HSSFWorkbook(fileStream);
                        sheet = wb.getSheetAt(0);
                        rowCount = sheet.getLastRowNum();
                        DebugUtil.print("[BatchImportMasterUser]==============rowCount=" +
                                rowCount);

                        String orgIDStr = (String) request.getSession()
                                .getAttribute(LMSConstants.USER_ASPID_KEY);

                        //String orgIDStr ="0";
                        int orgID = Integer.parseInt(orgIDStr);

                        //orgID = 0;
                        for (int i = 1; i <= rowCount; i++)
                        {
                                row = sheet.getRow(i);
                                DebugUtil.print("[BatchImportMasterUser]==============row=" +
                                        row);

                                if (row == null)
                                {
                                        break;
                                }

                                tempStr = "";
                                cellNum = row.getLastCellNum();

                                for (int j = 0; j < cellNum; j++)
                                {
                                        tempStr += DbInputUtil.getThisCellValue(row, j);
                                }

                                if (tempStr.trim().equals(""))
                                {
                                        continue;
                                }

                                loginName = DbInputUtil.getThisCellValue(row, 0).trim();
                                courseNumber = DbInputUtil.getThisCellValue(row, 1).trim();
                                courseRole = DbInputUtil.getThisCellValue(row, 2).trim();

                                List roleList = StringUtil.split(courseRole, "/");

                                for (int j = 0; j < roleList.size(); j++)
                                {
                                        String roleName = ((String) roleList.get(j)).trim();

                                        if (!CourseUserHelper.isExisitCourseUserRole(loginName,
                                                roleName, courseNumber, type, orgID))
                                        {
                                                CourseUserHelper.addCourseUser(courseNumber, type,
                                                        loginName, roleName, orgID);
                                        }
                                }
                        }
                }
                catch (Exception e)
                {
                        e.printStackTrace(); //To change body of catch statement use File | Settings | File Templates.
                }
        }
}
