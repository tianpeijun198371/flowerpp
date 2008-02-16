/**
 * DBIput.java.
 * User: huangsb Date: 2006-3-23 11:38:21
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.user.util;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFCell;

import java.util.List;
import java.util.ArrayList;
import java.io.FileInputStream;

import com.ulearning.ulms.util.log.LogUtil;
import com.ulearning.ulms.util.DbInputUtil;
import com.ulearning.ulms.admin.gradeterm.bean.GradeTermImpl;
import com.ulearning.ulms.admin.gradeterm.form.GradeTermForm;
import com.ulearning.ulms.admin.gradetermcourse.bean.GradeTermCourseImpl;
import com.ulearning.ulms.admin.gradetermcourse.form.GradeTermCourseForm;
import com.ulearning.ulms.admin.gradeuser.form.GradeUserForm;
import com.ulearning.ulms.admin.gradeuser.dao.GradeUserDAOImpl;
import com.ulearning.ulms.admin.gradeusercourse.dao.GradeUserCourseDAOImpl;
import com.ulearning.ulms.admin.gradeusercourse.form.GradeUserCourseForm;
import com.ulearning.ulms.admin.gradeusercourse.bean.GradeUserCourseImpl;


public class DBIput
{
        private HSSFWorkbook wb = null;
        private HSSFSheet sheet = null;
        private HSSFRow row = null;
        private HSSFCell cell = null;
        private String res = "";   //return check info
        private String res1 = "";   //return check info
        private String insert_str = ""; //return insert info

        private int rowCount = 0; //excel total row

        private String tempStr = "";
        private String specifate = "";
        private String grade = "";
        private String term = "";
        private String name = "";
        private String pwd = "";
        private String entergrade = "";
        private String[] courseName = null;
        private String[] courseGrade = null;
        private String gradeIDCourse[] = null;
        private String gradeIDCourseStr = "";

        public String insertIntoDb(String file_path) throws Exception
        {
                ArrayList gradeTermCourseFormList = new ArrayList();
                int cellNum = 0;
                try
                {

                        FileInputStream in = new FileInputStream(file_path);
                        wb = new HSSFWorkbook(in);
                        sheet = wb.getSheetAt(0);
                        LogUtil.debug("User", "[UserDbInput]====================Start insert user info!");

                        rowCount = sheet.getLastRowNum();
                        row = sheet.getRow((short) 0);
                        specifate = DbInputUtil.getThisCellValue(row, 0);
                        row = sheet.getRow((short) 1);
                        grade = DbInputUtil.getThisCellValue(row, 0);
                        row = sheet.getRow((short) 2);
                        term = DbInputUtil.getThisCellValue(row, 0);

                        //System.out.println("specifate = " + specifate);
                        //System.out.println("grade = " + grade);
                        //System.out.println("term = " + term);
                        GradeTermForm gtf = GradeTermImpl.getGradeTermSpe(grade, term, specifate);
                        int gradeTID = gtf.getGradetID();
                        //System.out.println("gradeTID = " + gradeTID);
                        //课程
                        row = sheet.getRow((short) 3);
                        try
                        {
                                cellNum = row.getLastCellNum();
                        }
                        catch (Exception e)
                        {
                        }
                        for (int t = 3; t < cellNum; t++)
                        {
                                int m = 0;
                                courseName = new String[cellNum - 3];
                                courseName[m] = DbInputUtil.getThisCellValue(row, t);
                                gradeTermCourseFormList.add(GradeTermCourseImpl.getGradeTermCourseListByName(gradeTID, courseName[m]));
                                m++;
                        }
                        for (int m = 0; m < gradeTermCourseFormList.size(); m++)
                        {
                                gradeIDCourse = new String[gradeTermCourseFormList.size()];
                                int gcID = ((GradeTermCourseForm) gradeTermCourseFormList.get(m)).getGradeCID();
                                // gradeIDCourse[m] = gcID;
                                gradeIDCourseStr += gcID + ";";


                        }
                        System.out.println("gradeIDCourseStr = " + gradeIDCourseStr);

                        gradeIDCourse = gradeIDCourseStr.split(";");
                        for (int i = 0; i < gradeIDCourse.length; i++)
                        {
                                System.out.println(" gradeIDCourse[" + i + "] = " + gradeIDCourse[i]);
                        }

                        //System.out.println("gradeTermCourseFormList.size() = " + gradeTermCourseFormList.size());
/*                        for(int m =0;m<gradeTermCourseFormList.size();m++)
                        {
                                GradeTermCourseForm mm= ((GradeTermCourseForm)gradeTermCourseFormList.get(m));

                                //System.out.println("==========================");
                                try
                                {
                                        System.out.println("bb=="+ mm.getGradeCID());
                                        System.out.println("bb=="+ mm.getGradeTID());
                                        System.out.println("bb=="+ mm.getCourseName());
                                }
                                catch (Exception e)
                                {
                                        e.printStackTrace();
                                }
                                //System.out.println("---------------------------");
                        }*/
                        //List lis = GradeTermCourseImpl.getGradeTermCourseList(gradeTID);
                        for (int k = 4; k <= rowCount; k++)
                        {

                                row = sheet.getRow((short) k);
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
                                        tempStr += DbInputUtil.getThisCellValue(row, j);
                                }
                                if (tempStr.trim().equals(""))
                                {
                                        continue;
                                }
                                name = DbInputUtil.getThisCellValue(row, 0);
                                pwd = DbInputUtil.getThisCellValue(row, 1);
                                entergrade = DbInputUtil.getThisCellValue(row, 2);

                                GradeUserForm gf = new GradeUserForm();
                                gf.setGradeUserName(name);
                                gf.setGradeUserpwd(pwd);
                                gf.setEntergrade(entergrade);

                                GradeUserDAOImpl ne = new GradeUserDAOImpl();
                                //增加用户
                                int gradeUserID = ne.insertGradeUser(gf);
                                //
                                //System.out.println("name = " + name);
                                //System.out.println("pwd = " + pwd);
                                //System.out.println("entergrage = " + entergrade);
                                int n = 0;
                                for (int j = 3; j < cellNum; j++)
                                {
                                        GradeUserCourseForm gcf = new GradeUserCourseForm();

                                        courseGrade = new String[cellNum];
                                        courseGrade[n] = DbInputUtil.getThisCellValue(row, j);
                                        if (courseGrade[n].equals("") || courseGrade[n] == null)
                                        {
                                                courseGrade[n] = "0";
                                        }
                                        if (courseGrade[n].equals("免"))
                                        {
                                                courseGrade[n] = "-1";
                                        }
                                        GradeUserCourseDAOImpl nc = new GradeUserCourseDAOImpl();
                                        // GradeUserCourseForm guf = GradeUserCourseImpl.checkuser(gradeTID,gradeUserID,gcID);
                                        gcf.setGradetID(gradeTID);
                                        gcf.setGradeuserID(gradeUserID);
                                        gcf.setCoursegrade(Float.parseFloat(courseGrade[n]));
                                        gcf.setGradecID(Integer.parseInt(gradeIDCourse[n]));

                                        //System.out.println("gradeTID = " + gradeTID);
                                        //System.out.println("gradeUserID = " + gradeUserID);
                                        //System.out.println("courseGrade["+n+"] = " + courseGrade[n]);
                                        //System.out.println("gradeIDCourse["+n+"] = " + gradeIDCourse[n]);
                                        nc.insertGradeUserCourse(gcf);
                                        n++;
                                }
                        }
                }
                catch (Exception e)
                {
                        throw new Exception("userDbInput is error " + e);
                }
                return tempStr;

        }

        public static void main(String[] args)
        {
                DBIput dbint = new DBIput();
                try
                {

                        String dd = dbint.insertIntoDb("d:\\stu.xls");
                        System.out.println("dd ss= " + dd);
                }
                catch (Exception e)
                {

                }

        }


}
