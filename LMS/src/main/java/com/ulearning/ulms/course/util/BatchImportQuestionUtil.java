/** * BatchImportQuestionUtilUtil.java.
 * User: chenxj
 * Date: 2004-11-18
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd. * All rights reserved. */
package com.ulearning.ulms.course.util;

import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.core.util.StringUtil;
import com.ulearning.ulms.course.test.paper.form.PaperQuestionForm;
import com.ulearning.ulms.course.test.question.base.dao.BaseDAO;
import com.ulearning.ulms.course.test.question.base.dao.BaseDAOFactory;
import com.ulearning.ulms.course.test.question.base.form.BaseForm;
import com.ulearning.ulms.course.test.question.choice.dao.ChoiceDAO;
import com.ulearning.ulms.course.test.question.choice.dao.ChoiceDAOFactory;
import com.ulearning.ulms.course.test.question.choice.form.ChoiceForm;
import com.ulearning.ulms.course.test.question.choiceitem.form.ChoiceItemForm;
import com.ulearning.ulms.util.DbInputUtil;
import com.ulearning.ulms.util.log.DebugUtil;
import com.ulearning.ulms.util.log.LogUtil;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.FileInputStream;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class BatchImportQuestionUtil
{
        private HSSFWorkbook wb = null;
        private HSSFSheet sheet = null;
        private HSSFRow row = null;
        private HSSFCell cell = null;
        private String res = ""; //return check info
        private String insert_str = ""; //return insert info
        private String typeStr = "单选题,多选题,填空题,判断题,问答题";
        private int rowCount = 0; //excel total row
        private String tempStr = ""; //deal 空行的变量
        private String chapter = ""; //章节
        private String point = ""; //知识点
        private String type = ""; //题的类型
        private String hardLevel = ""; //难度
        private String score = ""; //分数
        private String title = ""; //题目内容
        private String options = ""; //可选项
        private String answer = ""; //答案
        private String keyword = ""; //说明
        private String scope = ""; //说明
        private String description = ""; //说明

        public String checkExcel(String filePath) throws ULMSSysException
        {
                int cellNum = 0;
                DebugUtil.print("[BatchImportQuestionUtil]=============filepath=" +
                        filePath);
                DebugUtil.print("[BatchImportQuestionUtil]=============after=" +
                        filePath);

                try
                {
                        FileInputStream in = new FileInputStream(filePath);
                        DebugUtil.print("[BatchImportQuestionUtil]=============fileStream=" +
                                in);
                        wb = new HSSFWorkbook(in);
                        sheet = wb.getSheetAt(0);
                        rowCount = sheet.getLastRowNum();
                        DebugUtil.print("[BatchImportQuestionUtil]==============rowCount=" +
                                rowCount);

                        for (int i = 3; i <= rowCount; i++)
                        {
                                row = sheet.getRow(i);
                                DebugUtil.print("[BatchImportQuestionUtil]==============row" +
                                        i + "=" + row);

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

                                short item = 1;

                                double intChapter = 0;

                                if (row.getCell(item).getCellType() == 0)
                                {
                                        intChapter = row.getCell(item++).getNumericCellValue();
                                        //                                    chapter=String.valueOf(intChapter).trim();
                                        chapter = (int) intChapter + "";
                                }
                                else
                                {
                                        chapter = DbInputUtil.getThisCellValue(row, item++);
                                }

                                point = DbInputUtil.getThisCellValue(row, item++).trim();
                                type = DbInputUtil.getThisCellValue(row, item++).trim();

                                double f1 = 0;

                                if (row.getCell(item).getCellType() == 0)
                                {
                                        f1 = row.getCell(item++).getNumericCellValue();
                                        hardLevel = (int) f1 + "";
                                }
                                else
                                {
                                        hardLevel = DbInputUtil.getThisCellValue(row, item++).trim();
                                }

                                double f2 = 0;

                                if (row.getCell(item).getCellType() == 0)
                                {
                                        f2 = row.getCell(item++).getNumericCellValue();
                                        score = (int) f2 + "";
                                }
                                else
                                {
                                        score = DbInputUtil.getThisCellValue(row, item++).trim();
                                }

                                title = DbInputUtil.getThisCellValue(row, item++).trim();
                                options = DbInputUtil.getThisCellValue(row, item++).trim();

                                double f3 = 0;

                                if (row.getCell(item).getCellType() == 0)
                                {
                                        f3 = row.getCell(item++).getNumericCellValue();
                                        answer = (int) f3 + "";
                                }
                                else
                                {
                                        answer = DbInputUtil.getThisCellValue(row, item++).trim();
                                }

                                description = DbInputUtil.getThisCellValue(row, item++).trim();
                                DebugUtil.print("[BatchImportQuestionUtil]==============type=" +
                                        type);
                                DebugUtil.print(
                                        "[BatchImportQuestionUtil]==============hardLevel=" +
                                                hardLevel);
                                DebugUtil.print("[BatchImportQuestionUtil]==============score=" +
                                        score);
                                DebugUtil.print("[BatchImportQuestionUtil]==============title=" +
                                        title);
                                DebugUtil.print(
                                        "[BatchImportQuestionUtil]==============answer=" +
                                                answer.toUpperCase());

                                //                                DebugUtil.print("[BatchImportQuestionUtil]==============total=" + row.getCell((short)1).getStringCellValue()+row.getCell((short)3).getNumericCellValue());
                                if (type.trim().equals("") || hardLevel.trim().equals("") ||
                                        score.trim().equals("") ||
                                        title.trim().trim().equals("") ||
                                        answer.trim().equals(""))
                                {
                                        res += ("第 " + (i + 1) + " 行的题型、难度、分数、题目内容和答案不能为空<br>");

                                        continue;
                                }

                                if (typeStr.indexOf(type) == -1)
                                {
                                        res += ("第 " + (i + 1) + " 行的试题类型不存在<br>");

                                        continue;
                                }

                                if ((Integer.parseInt(hardLevel) > 3) ||
                                        (Integer.parseInt(hardLevel) < 1))
                                {
                                        res += ("第 " + (i + 1) + " 行的难度超出范围<br>");

                                        continue;
                                }
                        }
                }
                catch (Exception e)
                {
                        e.printStackTrace(); //To change body of catch statement use File | Settings | File Templates.
                        throw new ULMSSysException(
                                "异常 happened in BatchImportQuestionUtil[checkExcel]===========\n info:\n" +
                                        e, e);
                }

                return res;
        }

        public void insertIntoDb(String filePath, String parentID, String courseID)
                throws ULMSSysException
        {
                int cellNum = 0;
                BaseForm bf = null;
                ChoiceForm cf = null;
                ChoiceItemForm cif = null;
                ArrayList list = null;
                DebugUtil.print("[BatchImportQuestionUtil]=============filepath=" +
                        filePath);

                try
                {
                        FileInputStream fileStream = new FileInputStream(filePath);
                        wb = new HSSFWorkbook(fileStream);
                        sheet = wb.getSheetAt(0);
                        rowCount = sheet.getLastRowNum();
                        DebugUtil.print("[BatchImportQuestionUtil]==============rowCount=" +
                                rowCount);

                        for (int i = 3; i <= rowCount; i++)
                        {
                                row = sheet.getRow(i);
                                DebugUtil.print("[BatchImportQuestionUtil]==============row=" +
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

                                short item = 1;

                                double intChapter = 0;

                                if (row.getCell(item).getCellType() == 0)
                                {
                                        intChapter = row.getCell(item++).getNumericCellValue();
                                        //chapter=String.valueOf(intChapter).trim();
                                        chapter = (int) intChapter + "";
                                }
                                else
                                {
                                        chapter = DbInputUtil.getThisCellValue(row, item++);
                                }

                                point = DbInputUtil.getThisCellValue(row, item++).trim();
                                type = DbInputUtil.getThisCellValue(row, item++).trim();

                                double f1 = 0;

                                if (row.getCell(item).getCellType() == 0)
                                {
                                        f1 = row.getCell(item++).getNumericCellValue();
                                        hardLevel = (int) f1 + "";
                                }
                                else
                                {
                                        hardLevel = DbInputUtil.getThisCellValue(row, item++).trim();
                                }

                                double f2 = 0;

                                if (row.getCell(item).getCellType() == 0)
                                {
                                        f2 = row.getCell(item++).getNumericCellValue();
                                        score = (int) f2 + "";
                                }
                                else
                                {
                                        score = DbInputUtil.getThisCellValue(row, item++).trim();
                                }

                                title = DbInputUtil.getThisCellValue(row, item++).trim();
                                options = DbInputUtil.getThisCellValue(row, item++).trim();

                                double f3 = 0;

                                if (row.getCell(item).getCellType() == 0)
                                {
                                        f3 = row.getCell(item++).getNumericCellValue();
                                        answer = (int) f3 + "";
                                }
                                else
                                {
                                        answer = DbInputUtil.getThisCellValue(row, item++).trim();
                                }

                                description = DbInputUtil.getThisCellValue(row, item++).trim();
                                DebugUtil.print("[BatchImportQuestionUtil]==============type=" +
                                        type);
                                DebugUtil.print(
                                        "[BatchImportQuestionUtil]==============hardLevel=" +
                                                hardLevel);
                                DebugUtil.print("[BatchImportQuestionUtil]==============score=" +
                                        score);
                                DebugUtil.print("[BatchImportQuestionUtil]==============title=" +
                                        title);
                                DebugUtil.print(
                                        "[BatchImportQuestionUtil]==============answer=" +
                                                answer.toUpperCase());
                                DebugUtil.print("[BatchImportQuestionUtil]==============point=" +
                                        point);
                                DebugUtil.print(
                                        "[BatchImportQuestionUtil]==============options=" +
                                                options);
                                DebugUtil.print(
                                        "[BatchImportQuestionUtil]==============description=" +
                                                description);

                                //转换试题类型
                                String[] t = typeStr.split(",");
                                String qtype = "";

                                for (int n = 1; n <= t.length; n++)
                                {
                                        if (t[n - 1].equals(type))
                                        {
                                                qtype += n;
                                        }
                                }

                                bf = new BaseForm();
                                bf.setIsContent("1");
                                bf.setTitle(title);
                                bf.setPoint(point);
                                bf.setType(qtype);
                                bf.setParentID(Integer.parseInt(parentID));
                                bf.setCourseID(Integer.parseInt(courseID));

                                if (!score.equals(""))
                                {
                                        bf.setScore((new Float(score)).floatValue());
                                }
                                else
                                {
                                        bf.setScore(0);
                                }

                                bf.setHardLevel(StringUtil.parseInt(hardLevel));
                                bf.setCorrectAnswer(answer);
                                bf.setDescription(description);
                                bf.setChapter(chapter);

                                Date d = new Date();
                                bf.setCreateTime(d);
                                bf.setUpdateTime(d);
                                bf.setDesc7("110"); //为了将调查反馈和题库分开

                                //当类型为单选、多选时，转换试题选项
                                if ((type != null) &&
                                        (type.equals("单选题") || type.equals("多选题")))
                                {
                                        DebugUtil.print(
                                                "[BatchImportQuestionUtil]==============type=" + type);
                                        cf = new ChoiceForm();
                                        list = new ArrayList();
                                        //转换选项
                                        options = options.replace('；', ';');

                                        String[] option = options.split(";");
                                        String optionItem = "";

                                        for (int m = 0; m < option.length; m++)
                                        {
                                                cif = new ChoiceItemForm();
                                                cif.setItemTitle(option[m].toUpperCase());
                                                DebugUtil.print(
                                                        "[BatchImportQuestionUtil]==============option[" +
                                                                m + "]=" + option[m]);
                                                list.add(cif);
                                        }

                                        cf.setList(list);
                                        cf.setBaseForm(bf);

                                        ChoiceDAO dao = ChoiceDAOFactory.getDAO();
                                        dao.addChoice(cf);
                                }
                                else
                                {
                                        BaseDAO dao = BaseDAOFactory.getDAO();
                                        dao.addBase(bf);
                                }
                        }
                }
                catch (Exception e)
                {
                        e.printStackTrace(); //To change body of catch statement use File | Settings | File Templates.
                        throw new ULMSSysException(
                                "异常 happened in BatchImportQuestionUtil[insertIntoDb]===========\n info:\n" +
                                        e, e);
                }
        }

        public List insertIntoDbforpage(String filePath, String parentID,
                                        String courseID) throws ULMSSysException
        {
                int cellNum = 0;
                BaseForm bf = null;
                ChoiceForm cf = null;
                ChoiceItemForm cif = null;
                ArrayList list = null;
                List listquestion = new ArrayList();

                int questionid = 0;
                DebugUtil.print("[BatchImportQuestionUtil]=============filepath=" +
                        filePath);

                try
                {
                        FileInputStream fileStream = new FileInputStream(filePath);
                        wb = new HSSFWorkbook(fileStream);
                        sheet = wb.getSheetAt(0);
                        rowCount = sheet.getLastRowNum();
                        DebugUtil.print("[BatchImportQuestionUtil]==============rowCount=" +
                                rowCount);

                        for (int i = 3; i <= rowCount; i++)
                        {
                                row = sheet.getRow(i);
                                DebugUtil.print("[BatchImportQuestionUtil]==============row=" +
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

                                short item = 1;

                                double intChapter = 0;

                                if (row.getCell(item).getCellType() == 0)
                                {
                                        intChapter = row.getCell(item++).getNumericCellValue();
                                        //chapter=String.valueOf(intChapter).trim();
                                        chapter = (int) intChapter + "";
                                }
                                else
                                {
                                        chapter = DbInputUtil.getThisCellValue(row, item++);
                                }

                                point = DbInputUtil.getThisCellValue(row, item++).trim();
                                type = DbInputUtil.getThisCellValue(row, item++).trim();

                                double f1 = 0;

                                if (row.getCell(item).getCellType() == 0)
                                {
                                        f1 = row.getCell(item++).getNumericCellValue();
                                        hardLevel = (int) f1 + "";
                                }
                                else
                                {
                                        hardLevel = DbInputUtil.getThisCellValue(row, item++).trim();
                                }

                                double f2 = 0;

                                if (row.getCell(item).getCellType() == 0)
                                {
                                        f2 = row.getCell(item++).getNumericCellValue();
                                        score = (int) f2 + "";
                                }
                                else
                                {
                                        score = DbInputUtil.getThisCellValue(row, item++).trim();
                                }

                                title = DbInputUtil.getThisCellValue(row, item++).trim();
                                options = DbInputUtil.getThisCellValue(row, item++).trim();

                                double f3 = 0;

                                if (row.getCell(item).getCellType() == 0)
                                {
                                        f3 = row.getCell(item++).getNumericCellValue();
                                        answer = (int) f3 + "";
                                }
                                else
                                {
                                        answer = DbInputUtil.getThisCellValue(row, item++).trim()
                                                .toUpperCase();
                                }

                                keyword = DbInputUtil.getThisCellValue(row, item++).trim();

                                double intScope = 0;

                                if (row.getCell(item).getCellType() == 0)
                                {
                                        intScope = row.getCell(item++).getNumericCellValue();
                                        scope = (int) intScope + "";
                                }
                                else
                                {
                                        scope = DbInputUtil.getThisCellValue(row, item++);
                                }

                                description = DbInputUtil.getThisCellValue(row, item++).trim();
                                LogUtil.debug("[BatchImportQuestionUtil]==============type=",
                                        type);
                                LogUtil.debug("[BatchImportQuestionUtil]==============hardLevel=",
                                        hardLevel);
                                LogUtil.debug("[BatchImportQuestionUtil]==============score=",
                                        score);
                                LogUtil.debug("[BatchImportQuestionUtil]==============title=",
                                        title);
                                LogUtil.debug("[BatchImportQuestionUtil]==============answer=",
                                        answer.toUpperCase());
                                LogUtil.debug("[BatchImportQuestionUtil]==============point=",
                                        point);
                                LogUtil.debug("[BatchImportQuestionUtil]==============options=",
                                        options);
                                LogUtil.debug("[BatchImportQuestionUtil]==============description=",
                                        description);
                                System.out.println("answer = " + answer);

                                //转换试题类型
                                String[] t = typeStr.split(",");
                                String qtype = "";

                                for (int n = 1; n <= t.length; n++)
                                {
                                        if (t[n - 1].equals(type))
                                        {
                                                qtype += n;
                                        }
                                }

                                bf = new BaseForm();
                                bf.setIsContent("1");
                                bf.setTitle(title);
                                bf.setPoint(point);
                                bf.setType(qtype);
                                bf.setParentID(Integer.parseInt(parentID));
                                bf.setCourseID(Integer.parseInt(courseID));

                                if (!score.equals(""))
                                {
                                        bf.setScore((new Float(score)).floatValue());
                                }
                                else
                                {
                                        bf.setScore(0);
                                }

                                bf.setHardLevel(StringUtil.parseInt(hardLevel));
                                bf.setCorrectAnswer(answer);
                                bf.setRemark(description);
                                bf.setChapter(chapter);
                                bf.setKey(keyword);
                                bf.setScope(scope);

                                Date d = new Date();
                                bf.setCreateTime(d);
                                bf.setUpdateTime(d);
                                bf.setDesc7("110"); //为了将调查反馈和题库分开
                                //当类型为单选、多选时，转换试题选项

                                PaperQuestionForm paf = new PaperQuestionForm();

                                if ((type != null) &&
                                        (type.equals("单选题") || type.equals("多选题")))
                                {
                                        DebugUtil.print(
                                                "[BatchImportQuestionUtil]==============type=" + type);
                                        cf = new ChoiceForm();
                                        list = new ArrayList();
                                        //转换选项
                                        options = options.replace('；', ';');

                                        String[] option = options.split(";");
                                        String optionItem = "";

                                        for (int m = 0; m < option.length; m++)
                                        {
                                                cif = new ChoiceItemForm();
                                                cif.setItemTitle(option[m].toUpperCase());
                                                DebugUtil.print(
                                                        "[BatchImportQuestionUtil]==============option[" +
                                                                m + "]=" + option[m]);
                                                list.add(cif);
                                        }

                                        cf.setList(list);
                                        cf.setBaseForm(bf);

                                        ChoiceDAO dao = ChoiceDAOFactory.getDAO();
                                        questionid = dao.addChoice(cf);
                                        paf.setQuestionID(questionid);
                                        paf.setScore(bf.getScore());
                                        paf.setType(Integer.parseInt(bf.getType()));
                                }
                                else
                                {
                                        BaseDAO dao = BaseDAOFactory.getDAO();
                                        questionid = dao.addBase(bf);
                                        paf.setQuestionID(questionid);
                                        paf.setScore(bf.getScore());
                                        paf.setType(Integer.parseInt(bf.getType()));
                                }

                                listquestion.add(paf);
                        }
                }
                catch (Exception e)
                {
                        e.printStackTrace(); //To change body of catch statement use File | Settings | File Templates.
                        throw new ULMSSysException(
                                "异常 happened in BatchImportQuestionUtil[insertIntoDb]===========\n info:\n" +
                                        e, e);
                }

                return listquestion;
        }

        //        public static void main(String [] arg)
        //        {
        //              BatchImportQuestionUtil mm = new BatchImportQuestionUtil();
        //                String file = "c:\\Downloads\\questionimport.xls";
        //                System.out.println("ss ="+file);
        //                String ss = mm.checkExcel(file);
        //
        //                System.out.println("ss ="+ss);
        //        }
}
