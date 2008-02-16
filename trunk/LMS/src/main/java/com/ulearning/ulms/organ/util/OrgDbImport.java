/**
 * Created by IntelliJ IDEA.
 * User: zengwj
 * Date: 2004-6-28
 * Time: 14:56:48
 * To change this template use File | Settings | File Templates.
 */
package com.ulearning.ulms.organ.util;

import com.ulearning.ulms.organ.bean.OrganHelper;
import com.ulearning.ulms.organ.exceptions.OrganSysException;
import com.ulearning.ulms.organ.form.OrganForm;
import com.ulearning.ulms.util.BatchImport;
import com.ulearning.ulms.util.DbInputUtil;
import com.ulearning.ulms.util.log.LogUtil;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.FileInputStream;

import java.util.ArrayList;
import java.util.List;


public class OrgDbImport extends BatchImport
{
        private String orgName = "";
        private String orgNO = "";
        private String description = "";
        private String parentOrgName = "";
        private int parentID = 0;
        private String isAspString = "";
        private int isAsp = 0;
        private int aspID = 0;

        /**
         * batch insert user info
         *
         * @param file_path
         * @return List userList
         * @throws OrganSysException
         */
        public List insertIntoDb(String file_path) throws OrganSysException
        {
                ArrayList organFormList = new ArrayList();
                OrganForm of = null;
                int cellNum = 0;

                try
                {
                        FileInputStream in = new FileInputStream(file_path);
                        wb = new HSSFWorkbook(in);
                        sheet = wb.getSheetAt(0);
                        LogUtil.debug("User",
                                "[UserDbInput]====================Start insert user info!");

                        //get user info total number
                        rowCount = sheet.getLastRowNum();

                        String currentOrgNoString = "/";

                        //insert user info
                        for (int i = 1; i <= rowCount; i++)
                        {
                                row = sheet.getRow((short) i);

                                String tempString = "";
                                //deal with empty row
                                tempStr = "";

                                try
                                {
                                        cellNum = row.getLastCellNum();
                                }
                                catch (Exception e)
                                {
                                }

                                for (int j = 0; j < cellNum; j++)
                                {
                                        tempStr += String.valueOf(DbInputUtil.getThisCellValue(
                                                row, j));
                                }

                                if (tempStr.trim().equals(""))
                                {
                                        continue;
                                }

                                of = new OrganForm();
                                orgName = DbInputUtil.getThisCellValue(row, 0);
                                orgNO = DbInputUtil.getThisCellValue(row, 1);
                                currentOrgNoString += (orgNO + "/");

                                description = DbInputUtil.getThisCellValue(row, 2);

                                parentOrgName = DbInputUtil.getThisCellValue(row, 3);

                                if (parentOrgName.equals(""))
                                {
                                        parentID = 0;
                                        aspID = 1;
                                }
                                else
                                {
                                        parentID = OrganHelper.getOrgIDByCode(parentOrgName);

                                        if (parentID != -1)
                                        {
                                                OrganForm of1 = null;
                                                of1 = OrganHelper.getOrgan(parentID);

                                                if (of1 != null)
                                                {
                                                        aspID = of1.getAspID();
                                                }
                                        }
                                        else
                                        {
                                                tempString = "/!@#$%/" + parentOrgName;
                                        }
                                }

                                isAspString = DbInputUtil.getThisCellValue(row, 4);

                                if (isAspString.equals("Y") || isAspString.equals("y") ||
                                        isAspString.equals("是"))
                                {
                                        isAsp = 1;
                                }
                                else
                                {
                                        isAsp = 0;
                                }

                                /*System.out.println("description = "+description+tempString);
                                  System.out.println("isAsp = "+isAsp);
                                  System.out.println("orgNO = "+orgNO);
                                  System.out.println("parentID = "+parentID);
                                  System.out.println("aspID = "+aspID);
                                  System.out.println("orgName = "+orgName);
                                */
                                of.setDescription(description + tempString);
                                of.setIsAsp(isAsp);
                                of.setOrgNO(orgNO);
                                of.setParentID(parentID);
                                of.setAspID(aspID);
                                of.setOrgName(orgName);
                                organFormList.add(of);
                        }
                }
                catch (Exception e)
                {
                        throw new OrganSysException("Exception in insertExcel " + e);
                }

                return organFormList;
        }

        /**
         * check organ in file
         *
         * @param file_path
         * @return che  chinfo
         * @throws OrganSysException
         */
        public String checkExcel(String file_path) throws OrganSysException
        {
                int cellNum = 0; // row number

                try
                {
                        FileInputStream in = new FileInputStream(file_path);
                        wb = new HSSFWorkbook(in);
                        sheet = wb.getSheetAt(0);
                        LogUtil.debug("organ",
                                "[OrgDbInput]====================Start parse organ info!");

                        // get organ info total number
                        rowCount = sheet.getLastRowNum();

                        String currentOrganNoString = "/";

                        //check organ info ever row
                        List orgNoList = new ArrayList();
                        List orgNameList = new ArrayList();
                        List parentCodeList = new ArrayList();

                        for (int i = 1; i <= rowCount; i++)
                        {
                                row = sheet.getRow(i);

                                if (row == null)
                                {
                                        break;
                                }

                                //deal with empty row
                                tempStr = "";

                                cellNum = row.getLastCellNum();

                                orgNO = DbInputUtil.getThisCellValue(row, 1);
                                orgNoList.add(orgNO);

                                for (int j = 0; j < cellNum; j++)
                                {
                                        tempStr += DbInputUtil.getThisCellValue(row, j);
                                }

                                if (tempStr.trim().equals(""))
                                {
                                        continue;
                                }

                                // check organName
                                orgName = DbInputUtil.getThisCellValue(row, 0);
                                orgNameList.add(orgName);

                                if (orgName.equals("") || (orgName.length() > 40))
                                {
                                        res += ("第" + (i + 1) + "行 单位名称不能为空或单位名称太长<BR>");
                                }

                                //check organNo
                                orgNO = DbInputUtil.getThisCellValue(row, 1);

                                if (orgNO.equals("") || (orgNO.length() > 40))
                                {
                                        res += ("第" + (i + 1) + "行 单位编号不能为空或编号不能太长<BR>");
                                }

                                if (OrganHelper.getOrgIDByCode(orgNO) != -1)
                                {
                                        res += ("第" + (i + 1) + "行 此单位编号已经存在<BR>");
                                }

                                //current all orgNo of want to import into database
                                //currentOrganNoString += orgNO + "/";
                                LogUtil.debug("currentOrganNoString = ", currentOrganNoString);

                                //check description
                                description = DbInputUtil.getThisCellValue(row, 2);

                                if (description.equals(""))
                                {
                                        res += ("第" + (i + 1) + "行 单位描述不能为空<BR>");
                                }

                                if (description.length() > 200)
                                {
                                        res += ("第" + (i + 1) + "行 单位描述信息太长<BR>");
                                }

                                //check parent organ
                                parentOrgName = DbInputUtil.getThisCellValue(row, 3);

                                if (parentOrgName == null)
                                {
                                        parentCodeList.add("");
                                }
                                else
                                {
                                        parentCodeList.add(parentOrgName);
                                }

                                if (!parentOrgName.equals(""))
                                {
                                        if (OrganHelper.getOrgIDByCode(parentOrgName) == -1)
                                        {
                                                res += ("第" + (i + 1) + "行 父单位不存在<BR>");
                                        }
                                }

                                //check is asp
                                isAspString = DbInputUtil.getThisCellValue(row, 4);

                                if (!isAspString.equals(""))
                                {
                                        if (!isAspString.equals("Y") && !isAspString.equals("y") &&
                                                !isAspString.equals("是") &&
                                                !isAspString.equals("n") &&
                                                !isAspString.equals("N") &&
                                                !isAspString.equals("否"))
                                        {
                                                res += ("第" + (i + 1) + "行 是否是asp 填写有错识<BR>");
                                        }
                                }

                                if (!parentOrgName.equals(""))
                                {
                                        if (isAspString.equals("y") || isAspString.equals("Y") ||
                                                isAspString.equals("是"))
                                        {
                                                res += ("第" + (i + 1) + "行 有父单位的单位不可能是ASP<BR>");
                                        }
                                }
                        }

                        int count = 3;
                        int displayCount = 0;
                        int n = 0;
                        String temp1 = "";
                        String temp2 = "";

                        outWhile:
                        while (n < orgNoList.size())
                        {
                                temp1 = (String) orgNoList.get(n);

                                for (int m = n + 1; m < orgNoList.size(); m++)
                                {
                                        temp2 = (String) orgNoList.get(m);

                                        if (!temp1.equals("") && !temp2.equals("") &&
                                                temp1.equals(temp2))
                                        {
                                                res += ("第" + (n + 2) + "行和第" + (m + 2) +
                                                        "行的单位编号重复<br>");
                                                displayCount++;

                                                if (displayCount >= count)
                                                {
                                                        res += "………………";

                                                        break outWhile;
                                                }
                                        }
                                }

                                n++;
                        }

                        n = 0;

                        while (n < orgNameList.size())
                        {
                                int m = n + 1;
                                String pName = (String) orgNameList.get(n);
                                String pParentName = (String) parentCodeList.get(n);

                                while (m < orgNameList.size())
                                {
                                        String nName = (String) orgNameList.get(m);
                                        String nParentName = (String) parentCodeList.get(m);

                                        if (pName.equals(nName) && pParentName.equals(nParentName))
                                        {
                                                res += ("第" + (n + 2) + "行和第" + (m + 2) +
                                                        "行的单位名称重复<br>");
                                        }

                                        m++;
                                }

                                n++;
                        }
                }
                catch (Exception e)
                {
                        throw new OrganSysException("Exception in checkExcel " + e);
                }

                return res;
        }
}
