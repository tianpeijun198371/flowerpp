/**
 * Created by IntelliJ IDEA.
 * User: zengwj
 * Date: 2004-6-29
 * Time: 12:16:36
 * To change this template use File | Settings | File Templates.
 */
package com.ulearning.ulms.course.util;

import com.ulearning.ulms.core.util.StringUtil;
import com.ulearning.ulms.course.dao.MasterDAO;
import com.ulearning.ulms.course.dao.MasterDAOFactory;
import com.ulearning.ulms.course.exceptions.CourseSysException;
import com.ulearning.ulms.course.model.MasterModel;
import com.ulearning.ulms.organ.bean.OrganHelper;
import com.ulearning.ulms.organ.form.OrganForm;
import com.ulearning.ulms.util.BatchImport;
import com.ulearning.ulms.util.DbInputUtil;
import com.ulearning.ulms.util.LMSConstants;
import com.ulearning.ulms.util.log.DebugUtil;
import com.ulearning.ulms.util.log.LogUtil;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import sun.security.util.Debug;

import java.io.*;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public class MasterDbImport extends BatchImport
{
        private String courseName = "";
        private String courseCode = "";
        private String key = "";
        private String description = "";
        private String period = "0";
        private String credit = "0";
        private String catalogName = "";
        private String orgName = "";
        private String plan = "";
        private String typeName = "";
        private int catalogID = 0;
        private int masterType = 0;
        private int orgID = 0;
        private int aspID = 0;
        private int type = 0;
        private MasterDAO masterDAO = null;

        public List insertIntoDb(String file_path, String typeString)
                throws CourseSysException
        {
                ArrayList masterModelList = new ArrayList();
                MasterModel mm = null;
                int cellNum = 0;
                this.type = Integer.parseInt(typeString);

                try
                {
                        FileInputStream in = new FileInputStream(file_path);
                        wb = new HSSFWorkbook(in);
                        sheet = wb.getSheetAt(0);
                        LogUtil.debug("Master",
                                "[MasterDbInput]====================Start insert master info!");
                        masterDAO = MasterDAOFactory.getDAO();

                        //get user info total number
                        rowCount = sheet.getLastRowNum();

                        //insert user info
                        for (int i = 1; i <= rowCount; i++)
                        {
                                row = sheet.getRow((short) i);
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

                                mm = new MasterModel();
                                courseName = DbInputUtil.getThisCellValue(row, 0);
                                courseCode = DbInputUtil.getThisCellValue(row, 1);
                                key = DbInputUtil.getThisCellValue(row, 2);
                                description = DbInputUtil.getThisCellValue(row, 3);
                                period = DbInputUtil.getThisCellValue(row, 4);
                                credit = DbInputUtil.getThisCellValue(row, 5);
                                plan = DbInputUtil.getThisCellValue(row, 6);
                                catalogName = DbInputUtil.getThisCellValue(row, 7);

                                if (catalogName.equals(""))
                                {
                                        catalogID = 0;
                                }
                                else
                                {
                                        List catalogNames = StringUtil.split(catalogName, "/");
                                        String tempString = "";
                                        int previousCatID = -1;
                                        int currentCatID = 0;

                                        for (int k = 0; k < catalogNames.size(); k++)
                                        {
                                                tempString = (String) catalogNames.get(k);
                                                currentCatID = masterDAO.getCatalogID(tempString,
                                                        previousCatID, masterType);
                                                previousCatID = currentCatID;
                                        }

                                        catalogID = currentCatID;
                                }

                                /*orgName = DbInputUtil.getThisCellValue(row,8);
                                if(orgName.equals(""))
                                {
                                        aspID = 1;
                                }
                                else
                                {
                                        int tempOrgID = OrganHelper.getOrgIDByCode(orgName);
                                        OrganForm of = null;
                                        of = OrganHelper.getOrgan(tempOrgID);
                                        if(of != null)
                                        aspID = of.getAspID();
                                }*/

                                /*typeName =  DbInputUtil.getThisCellValue(row,9);
                                  if(typeName.equals("�γ�"))
                                         type = 0;
                                  else
                                         type = 1;
                                */
                                mm.setType(type);
                                mm.setMasterCode(courseCode);
                                mm.setKey(key);
                                mm.setDescription(description);
                                mm.setName(courseName);
                                //mm.setOrgID(aspID);
                                mm.setCredit(Float.parseFloat(credit));
                                mm.setCatalogID(catalogID);
                                mm.setPeriod(Integer.parseInt(period));
                                mm.setPlan(plan);
                                masterModelList.add(mm);
                        }
                }
                catch (Exception e)
                {
                        throw new CourseSysException("Exception in insertExcel " + e);
                }

                return masterModelList;
        }

        /**
         * check organ in file
         *
         * @param file_path
         * @return chechinfo
         * @throws CourseSysException
         */
        public String checkExcel(String file_path, String typeString)
                throws CourseSysException
        {
                int cellNum = 0; // row number
                this.type = Integer.parseInt(typeString);

                try
                {
                        FileInputStream in = new FileInputStream(file_path);
                        wb = new HSSFWorkbook(in);
                        sheet = wb.getSheetAt(0);
                        masterDAO = MasterDAOFactory.getDAO();
                        LogUtil.debug("course",
                                "[CourseDbInput]====================Start parse course info!");

                        // get organ info total number
                        rowCount = sheet.getLastRowNum();
                        System.out.println("rowCount ============" + rowCount);
                        LogUtil.debug("course",
                                "[courseDbInput ========================rowCount]==" +
                                        rowCount);

                        //check organ info ever row
                        List courseCodeList = new ArrayList();

                        if (rowCount == 0)
                        {
                                res = "���ڵڶ���������Ŀγ���Ϣ ";
                        }

                        for (int i = 1; i <= rowCount; i++)
                        {
                                row = sheet.getRow(i);

                                //deal with empty row
                                courseCode = DbInputUtil.getThisCellValue(row, 1);
                                courseCodeList.add(courseCode);

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
                                        tempStr += DbInputUtil.getThisCellValue(row, j);
                                }

                                if (tempStr.trim().equals(""))
                                {
                                        continue;
                                }

                                //check course name
                                courseName = DbInputUtil.getThisCellValue(row, 0);

                                if (courseName.equals("") || (courseName.length() > 100))
                                {
                                        res += ("��" + (i + 1) + "�� �γ����ֲ���Ϊ�ջ����<BR>");
                                }

                                // check type
                                /* typeName =  DbInputUtil.getThisCellValue(row,9);
                                  if((!typeName.equals("�γ�") && !typeName.equals("֤��")))
                                          res += "typeName in "+(i+1)+" row is invalidation<BR>";
                                  else
                                  {
                                          if(typeName.equals("�γ�"))
                                                  type = 0;
                                          else
                                                  type = 1;
                                  }
                                */

                                //check courseCode
                                courseCode = DbInputUtil.getThisCellValue(row, 1);

                                if (courseCode.equals("") || (courseCode.length() > 50))
                                {
                                        res += ("��" + (i + 1) + "�� �γ̱�Ų���Ϊ�ջ�̫��<BR>");
                                }
                                else
                                {
                                        //todo:later we need get the aspID there
                                        System.out.println("[MasterDBImport] ================" +
                                                "���õķ��������⣬���������ķ���");

                                        if (masterDAO.isExistMasterCode(courseCode, -1, type, -1, -1))
                                        {
                                                res += ("��" + (i + 1) + "�� �γ̱���Ѿ�����<BR>");
                                        }
                                }

                                //check key
                                key = DbInputUtil.getThisCellValue(row, 2);

                                //System.out.println("key================"+key+""+key.length());
                                if (key.equals("") || (courseCode.length() > 50))
                                {
                                        res += ("��" + (i + 1) + "�� �γ̹ؼ��ֲ���Ϊ�ջ�̫��");
                                }

                                //check description

                                //check period
                                period = DbInputUtil.getThisCellValue(row, 4);

                                if (period.length() > 0)
                                {
                                        if (!DbInputUtil.isNumeric(period))
                                        {
                                                res += ("��" + (i + 1) + "�� ��ѵ��ʱӦ��������<BR>");
                                        }
                                }

                                //check credit
                                credit = DbInputUtil.getThisCellValue(row, 5);

                                if (credit.length() > 0)
                                {
                                        if (!DbInputUtil.isNumeric(credit))
                                        {
                                                res += ("��" + (i + 1) + "�� ѧ��Ӧ��������<BR>");
                                        }
                                }

                                //check plan

                                //check catalogName
                                catalogName = DbInputUtil.getThisCellValue(row, 7);

                                if (!catalogName.equals(""))
                                {
                                        List catalogNames = StringUtil.split(catalogName, "/");
                                        String tempString = "";
                                        int previousCatID = -1;
                                        int currentCatID = 0;

                                        for (int k = 0; k < catalogNames.size(); k++)
                                        {
                                                tempString = (String) catalogNames.get(k);
                                                currentCatID = masterDAO.getCatalogID(tempString,
                                                        previousCatID, masterType);

                                                if (currentCatID == -1)
                                                {
                                                        res += ("��" + (i + 1) + "�� ����Ŀ¼���������<BR>");

                                                        break;
                                                }

                                                previousCatID = currentCatID;
                                        }
                                }

                                //check orgName
                                /* orgName =  DbInputUtil.getThisCellValue(row,8);
                                  if(!orgName.equals(""))
                                  {
                                          if(OrganHelper.getOrgIDByCode(orgName) == -1)
                                                  res += "orgName in "+(i+1)+" row is inexistence<BR>";
                                  }
                                */
                        }

                        int count = 3;
                        int displayCount = 0;
                        int n = 0;
                        String temp1 = "";
                        String temp2 = "";

                        outWhile:
                        while (n < courseCodeList.size())
                        {
                                temp1 = (String) courseCodeList.get(n);

                                for (int m = n + 1; m < courseCodeList.size(); m++)
                                {
                                        temp2 = (String) courseCodeList.get(m);

                                        if (!temp1.equals("") && !temp2.equals("") &&
                                                temp1.equals(temp2))
                                        {
                                                res += ("��" + (n + 2) + "�к͵�" + (m + 2) +
                                                        "�еĿγ̱���ظ�<br>");
                                                displayCount++;

                                                if (displayCount >= count)
                                                {
                                                        res += "������������";

                                                        break outWhile;
                                                }
                                        }
                                }

                                n++;
                        }
                }
                catch (Exception e)
                {
                        throw new CourseSysException("Exception in checkExcel " + e);
                }

                return res;
        }

        public String checkExcel(String file_path, String typeString,
                                 HttpServletRequest request) throws CourseSysException
        {
                int cellNum = 0; // row number
                this.type = Integer.parseInt(typeString);

                try
                {
                        FileInputStream in = new FileInputStream(file_path);
                        wb = new HSSFWorkbook(in);
                        sheet = wb.getSheetAt(0);
                        masterDAO = MasterDAOFactory.getDAO();
                        LogUtil.debug("course",
                                "[CourseDbInput]====================Start parse course info!");

                        // get organ info total number
                        rowCount = sheet.getLastRowNum();
                        System.out.println("rowCount ============" + rowCount);
                        LogUtil.debug("course",
                                "[courseDbInput ========================rowCount]==" +
                                        rowCount);

                        //check organ info ever row
                        List courseCodeList = new ArrayList();

                        if (rowCount == 0)
                        {
                                res = "���ڵڶ���������Ŀγ���Ϣ ";
                        }

                        for (int i = 1; i <= rowCount; i++)
                        {
                                row = sheet.getRow(i);

                                //deal with empty row
                                courseCode = DbInputUtil.getThisCellValue(row, 1);
                                courseCodeList.add(courseCode);

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
                                        tempStr += DbInputUtil.getThisCellValue(row, j);
                                }

                                if (tempStr.trim().equals(""))
                                {
                                        continue;
                                }

                                //check course name
                                courseName = DbInputUtil.getThisCellValue(row, 0);

                                if (courseName.equals("") || (courseName.length() > 100))
                                {
                                        res += ("��" + (i + 1) + "�� �γ����ֲ���Ϊ�ջ����<BR>");
                                }

                                // check type
                                /* typeName =  DbInputUtil.getThisCellValue(row,9);
                                  if((!typeName.equals("�γ�") && !typeName.equals("֤��")))
                                          res += "typeName in "+(i+1)+" row is invalidation<BR>";
                                  else
                                  {
                                          if(typeName.equals("�γ�"))
                                                  type = 0;
                                          else
                                                  type = 1;
                                  }
                                */

                                //check courseCode
                                courseCode = DbInputUtil.getThisCellValue(row, 1);

                                if (courseCode.equals("") || (courseCode.length() > 50))
                                {
                                        res += ("��" + (i + 1) + "�� �γ̱�Ų���Ϊ�ջ�̫��<BR>");
                                }
                                else
                                {
                                        //todo:later we need get the aspID there
                                        HttpSession session = request.getSession();
                                        String aspIDstr = (String) session.getAttribute(LMSConstants.USER_ASPID_KEY);
                                        String orgIDstr = (String) session.getAttribute(LMSConstants.USER_ORGID_KEY);

                                        if (aspIDstr != null)
                                        {
                                                aspID = Integer.parseInt(aspIDstr);
                                                orgID = Integer.parseInt(orgIDstr);
                                        }

                                        if (masterDAO.isExistMasterCode(courseCode, -1, type,
                                                aspID, orgID))
                                        {
                                                res += ("��" + (i + 1) + "�� �γ̱���Ѿ�����<BR>");
                                        }
                                }

                                //check key
                                key = DbInputUtil.getThisCellValue(row, 2);

                                //System.out.println("key================"+key+""+key.length());
                                if (key.equals("") || (courseCode.length() > 50))
                                {
                                        res += ("��" + (i + 1) + "�� �γ̹ؼ��ֲ���Ϊ�ջ�̫��");
                                }

                                //check description

                                //check period
                                period = DbInputUtil.getThisCellValue(row, 4);

                                if (period.length() > 0)
                                {
                                        if (!DbInputUtil.isNumeric(period))
                                        {
                                                res += ("��" + (i + 1) + "�� ��ѵ��ʱӦ��������<BR>");
                                        }
                                }

                                //check credit
                                credit = DbInputUtil.getThisCellValue(row, 5);

                                if (credit.length() > 0)
                                {
                                        if (!DbInputUtil.isNumeric(credit))
                                        {
                                                res += ("��" + (i + 1) + "�� ѧ��Ӧ��������<BR>");
                                        }
                                }

                                //check plan

                                //check catalogName
                                catalogName = DbInputUtil.getThisCellValue(row, 7);

                                if (!catalogName.equals(""))
                                {
                                        List catalogNames = StringUtil.split(catalogName, "/");
                                        String tempString = "";
                                        int previousCatID = -1;
                                        int currentCatID = 0;

                                        for (int k = 0; k < catalogNames.size(); k++)
                                        {
                                                tempString = (String) catalogNames.get(k);
                                                currentCatID = masterDAO.getCatalogID(tempString,
                                                        previousCatID, masterType);

                                                if (currentCatID == -1)
                                                {
                                                        res += ("��" + (i + 1) + "�� ����Ŀ¼���������<BR>");

                                                        break;
                                                }

                                                previousCatID = currentCatID;
                                        }
                                }

                                //check orgName
                                /* orgName =  DbInputUtil.getThisCellValue(row,8);
                                  if(!orgName.equals(""))
                                  {
                                          if(OrganHelper.getOrgIDByCode(orgName) == -1)
                                                  res += "orgName in "+(i+1)+" row is inexistence<BR>";
                                  }
                                */
                        }

                        int count = 3;
                        int displayCount = 0;
                        int n = 0;
                        String temp1 = "";
                        String temp2 = "";

                        outWhile:
                        while (n < courseCodeList.size())
                        {
                                temp1 = (String) courseCodeList.get(n);

                                for (int m = n + 1; m < courseCodeList.size(); m++)
                                {
                                        temp2 = (String) courseCodeList.get(m);

                                        if (!temp1.equals("") && !temp2.equals("") &&
                                                temp1.equals(temp2))
                                        {
                                                res += ("��" + (n + 2) + "�к͵�" + (m + 2) +
                                                        "�еĿγ̱���ظ�<br>");
                                                displayCount++;

                                                if (displayCount >= count)
                                                {
                                                        res += "������������";

                                                        break outWhile;
                                                }
                                        }
                                }

                                n++;
                        }
                }
                catch (Exception e)
                {
                        throw new CourseSysException("Exception in checkExcel " + e);
                }

                return res;
        }
}
