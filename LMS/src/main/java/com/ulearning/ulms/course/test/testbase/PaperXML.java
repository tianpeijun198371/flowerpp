/**
 * PaperUtil.java.
 * User: yangds  Date: 2005-8-31
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.course.test.testbase;

import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.core.util.DateTimeUtil;
import com.ulearning.ulms.core.util.StringUtil;
import com.ulearning.ulms.course.test.grade.dao.PaperUserDAO;
import com.ulearning.ulms.course.test.grade.dao.PaperUserDAOFactory;
import com.ulearning.ulms.course.test.grade.form.PaperUserForm;
import com.ulearning.ulms.course.test.paper.dao.PaperAnswerDAO;
import com.ulearning.ulms.course.test.paper.dao.PaperAnswerDAOFactory;
import com.ulearning.ulms.course.test.paper.dao.PaperQuestionDAO;
import com.ulearning.ulms.course.test.paper.dao.PaperQuestionDAOFactory;
import com.ulearning.ulms.course.test.paper.form.PaperAnswerForm;
import com.ulearning.ulms.course.test.paper.form.PaperQuestionForm;
import com.ulearning.ulms.course.test.question.base.dao.BaseDAO;
import com.ulearning.ulms.course.test.question.base.dao.BaseDAOFactory;
import com.ulearning.ulms.course.test.question.base.form.BaseForm;
import com.ulearning.ulms.util.log.LogUtil;

import org.dom4j.io.XMLWriter;

import org.w3c.dom.*;

import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;


public class PaperXML
{
        private static String OS = System.getProperty("os.name");
        private static List arryList = new ArrayList();

        /**
         * 读取xml文件中的学生考试信息
         *
         * @param inFile
         */
        static public boolean readXMLFile(String inFile, boolean isHasAnswer,
                                          HashMap hm) throws ULMSSysException
        {
                PaperAnswerDAO padao = PaperAnswerDAOFactory.getDAO();
                PaperUserDAO pudao = PaperUserDAOFactory.getDAO();
                PaperQuestionDAO pqdao = PaperQuestionDAOFactory.getDAO();

                //为解析XML作准备，创建DocumentBuilderFactory实例,指定DocumentBuilder
                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                DocumentBuilder db = null;

                try
                {
                        db = dbf.newDocumentBuilder();
                }
                catch (ParserConfigurationException pce)
                {
                        pce.printStackTrace(); //出异常时输出异常信息，然后退出，下同
                }

                Document doc = null;

                try
                {
                        doc = db.parse(inFile);
                }
                catch (DOMException dom)
                {
                        dom.printStackTrace();
                }
                catch (IOException ioe)
                {
                        ioe.printStackTrace();
                }
                catch (SAXException e)
                {
                        e.printStackTrace();
                }

                //下面是解析XML的全过程，比较简单，先取根元素"Exam"
                Element root = doc.getDocumentElement();
                root.normalize();

                /**---------addAnswertable---------------**/
                NodeList answers = root.getElementsByTagName("T_Answer_Tab");
                Element paperanswer = (Element) answers.item(0);

                //取"UserID"元素列表
                NodeList UserIDs = paperanswer.getElementsByTagName("UserID");

                //取"PaperID"元素列表
                NodeList PaperIDs = paperanswer.getElementsByTagName("PaperID");

                //取"QuestionID"元素列表
                NodeList QuestionIDs = paperanswer.getElementsByTagName("QuestionID");

                //取"type"元素列表
                NodeList types = paperanswer.getElementsByTagName("type");

                //取"Answer"元素列表
                NodeList Answers = paperanswer.getElementsByTagName("Answer");

                //取"ExamTimes"元素列表
                NodeList ExamTimes = paperanswer.getElementsByTagName("ExamTimes");
                BaseForm bf = null;

                PaperQuestionForm pqf = null;

                float score = 0;
                PaperUserForm puf = new PaperUserForm();
                PaperAnswerForm paf = new PaperAnswerForm();

                for (int i = 0; (UserIDs != null) && (i < UserIDs.getLength()); i++)
                {
                        //依次取每个"学生"元素
                        paf.setUserID(StringUtil.parseInt(((Element) UserIDs.item(i)).getFirstChild()
                                .getNodeValue()));
                        paf.setPaperID(StringUtil.parseInt(((Element) PaperIDs.item(i)).getFirstChild()
                                .getNodeValue()));
                        paf.setQuestionID(StringUtil.parseInt(((Element) QuestionIDs
                                .item(i)).getFirstChild()
                                .getNodeValue()));
                        paf.setType(StringUtil.parseInt(types.item(i).getFirstChild()
                                .getNodeValue()));
                        paf.setExamtimes(StringUtil.parseInt(ExamTimes.item(i)
                                .getFirstChild()
                                .getNodeValue()));

                        if (Answers.item(i).getFirstChild() != null)
                        {
                                paf.setAnswer(((Element) Answers.item(i)).getFirstChild()
                                        .getNodeValue());
                        }
                        else
                        {
                                paf.setAnswer("");
                        }

                        if (isHasAnswer)
                        {
                                bf = (BaseForm) hm.get(paf.getQuestionID() + "");
                                pqf = pqdao.getPaperQuestion(paf.getPaperID(),
                                        paf.getQuestionID());

                                if ((paf != null) && (bf != null) && (paf.getAnswer() != null) &&
                                        (bf.getCorrectAnswer() != null) &&
                                        paf.getAnswer().toUpperCase()
                                                .equals(bf.getCorrectAnswer().toUpperCase()))
                                {
                                        //paf.setGrade(bf.getScore());
                                        //score += bf.getScore();
                                        paf.setGrade(pqf.getScore());
                                        score += pqf.getScore();
                                }
                                else
                                {
                                        paf.setGrade(0);
                                }
                        }

                        padao.addPaperAnswer(paf);

                        //                        System.out.println("insert questionID=" +paf.getQuestionID());
                }

                //al数组中0位置放置BaseForm对象数组
                /**---------addPaperUsertable---------------**/
                NodeList paperusers = root.getElementsByTagName("T_PaperUser_Tab");
                Element paperuser = (Element) paperusers.item(0);

                //取"UserID"元素列表
                NodeList UserID1s = paperuser.getElementsByTagName("UserID");

                //取"PaperID"元素列表
                NodeList PaperID1s = paperuser.getElementsByTagName("PaperID");

                //取"type"元素列表
                NodeList type1s = paperuser.getElementsByTagName("type");

                //取"Answer"元素列表
                NodeList startTimes = paperuser.getElementsByTagName("startTime");
                NodeList endTimes = paperuser.getElementsByTagName("endTime");

                //取"ExamTimes"元素列表
                NodeList ExamTime1s = paperanswer.getElementsByTagName("ExamTimes");
                //取"UserID"元素列表
                puf.setUserID(StringUtil.parseInt(((Element) UserID1s.item(0)).getFirstChild()
                        .getNodeValue()));
                puf.setPaperID(StringUtil.parseInt(((Element) PaperID1s.item(0)).getFirstChild()
                        .getNodeValue()));
                puf.setType(StringUtil.parseInt(((Element) type1s.item(0)).getFirstChild()
                        .getNodeValue()));
                puf.setStartTime(DateTimeUtil.parseDateTime(((Element) startTimes
                        .item(0)).getFirstChild()
                        .getNodeValue()));
                puf.setEndTime(DateTimeUtil.parseDateTime(endTimes.item(0)
                        .getFirstChild()
                        .getNodeValue()));
                paf.setExamtimes(StringUtil.parseInt(ExamTime1s.item(0).getFirstChild()
                        .getNodeValue()));
                puf.setGrade(score);
                puf.setObjective(score);

                if (isHasAnswer)
                {
                        puf.setStatus(2);
                }
                else
                {
                        puf.setStatus(0);
                }

                System.out.println("addpu:getUserID" + puf.getUserID());
                System.out.println("addpu:getPaperID" + puf.getPaperID());
                System.out.println("addpu:getType" + puf.getType());

                //System.out.println(puf.)
                pudao.addPaperUser(puf);
                //清理内存空间
                paf = null;
                puf = null;

                //                System.out.println("insert userid=" +puf.getUserID());
                //                System.out.println("insert grade=" +puf.getGrade());
                return true;
        }

        public static List getArryList()
        {
                return arryList;
        }

        public static void setArryList(List arryList)
        {
                PaperXML.arryList = arryList;
        }

        public static void clearArryList()
        {
                PaperXML.arryList.clear();
        }

        /**
         * 批量读取xml文件中的学生答题内容
         *
         * @param inFile      试卷目录
         * @param isHasAnswer 是否批改
         * @param hm          试题内容
         * @throws ULMSSysException
         */
        static public void batchReadXML(String inFile, boolean isHasAnswer,
                                        HashMap hm) throws ULMSSysException
        {
                File f = new File(inFile);

                if (f.getName().toLowerCase().indexOf("_old") != -1)
                {
                }
                else
                {
                        String[] s = f.list();

                        for (int i = 0; (s != null) && (i < s.length); i++)
                        {
                                if (s[i].indexOf('.') == -1)
                                {
                                        if (f.getName().toLowerCase().indexOf("_old") != -1)
                                        {
                                        }
                                        else
                                        {
                                                //嵌套当前函数
                                                batchReadXML(inFile + "/" + s[i], isHasAnswer, hm);
                                        }
                                }
                                else
                                {
                                        if (i == (s.length - 1))
                                        {
                                                //判断文件是否是XML
                                                if (s[i].toLowerCase().indexOf("xml") == -1)
                                                {
                                                }
                                                else
                                                {
                                                        //读一个xml文件
                                                        boolean is = readXMLFile(inFile + "/" + s[i],
                                                                isHasAnswer, hm);

                                                        if (is)
                                                        {
                                                                //读xml文件写入库中成功后更换文件夹名称
                                                                f.renameTo(new File(inFile + "_old"));
                                                        }

                                                        LogUtil.info("PaperXML",
                                                                "batchReadXML========s[" + i + "]=" + s[i]);
                                                }
                                        }
                                }
                        }

                        f = null;
                }
        }

        /**
         * 读取xml文件中的学生考试信息
         *
         * @param inFile
         * @return List
         */
        static public List readXMLFileTemp(String inFile) throws ULMSSysException
        {
                ArrayList list = new ArrayList();
                ArrayList al = new ArrayList();
                PaperAnswerDAO padao = PaperAnswerDAOFactory.getDAO();
                PaperUserDAO pudao = PaperUserDAOFactory.getDAO();

                //为解析XML作准备，创建DocumentBuilderFactory实例,指定DocumentBuilder
                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                DocumentBuilder db = null;

                try
                {
                        db = dbf.newDocumentBuilder();
                }
                catch (ParserConfigurationException pce)
                {
                        pce.printStackTrace(); //出异常时输出异常信息，然后退出，下同
                }

                Document doc = null;

                try
                {
                        doc = db.parse(inFile);
                }
                catch (DOMException dom)
                {
                        dom.printStackTrace();
                }
                catch (IOException ioe)
                {
                        ioe.printStackTrace();
                }
                catch (SAXException e)
                {
                        e.printStackTrace();
                }

                //下面是解析XML的全过程，比较简单，先取根元素"Exam"
                Element root = doc.getDocumentElement();
                root.normalize();

                //                       System.out.println("root="+root.getElementsByTagName("T_Answer_Tab"));
                //                      Attr paperanswer = root.getAttributeNode("Exam");
                /**---------addAnswertable---------------**/
                NodeList answers = root.getElementsByTagName("T_Answer_Tab");
                Element paperanswer = (Element) answers.item(0);

                //取"UserID"元素列表
                NodeList UserIDs = paperanswer.getElementsByTagName("UserID");

                //取"PaperID"元素列表
                NodeList PaperIDs = paperanswer.getElementsByTagName("PaperID");

                //取"QuestionID"元素列表
                NodeList QuestionIDs = paperanswer.getElementsByTagName("QuestionID");

                //取"type"元素列表
                NodeList types = paperanswer.getElementsByTagName("type");

                //取"Answer"元素列表
                NodeList Answers = paperanswer.getElementsByTagName("Answer");

                //取"ExamTimes"元素列表
                NodeList ExamTimes = root.getElementsByTagName("ExamTimes");
                BaseForm bf = null;
                BaseDAO bdao = BaseDAOFactory.getDAO();
                float score = 0;

                for (int i = 0; (UserIDs != null) && (i < UserIDs.getLength()); i++)
                {
                        //依次取每个"学生"元素
                        PaperAnswerForm paf = new PaperAnswerForm();
                        paf.setUserID(StringUtil.parseInt(((Element) UserIDs.item(i)).getFirstChild()
                                .getNodeValue()));
                        paf.setPaperID(StringUtil.parseInt(((Element) PaperIDs.item(i)).getFirstChild()
                                .getNodeValue()));
                        paf.setQuestionID(StringUtil.parseInt(((Element) QuestionIDs
                                .item(i)).getFirstChild()
                                .getNodeValue()));
                        //                               System.out.println("入库成功!" + types.item(i).getFirstChild());
                        paf.setType(StringUtil.parseInt(types.item(i).getFirstChild()
                                .getNodeValue()));
                        paf.setExamtimes(StringUtil.parseInt(ExamTimes.item(i)
                                .getFirstChild()
                                .getNodeValue()));

                        if (Answers.item(i).getFirstChild() != null)
                        {
                                paf.setAnswer(((Element) Answers.item(i)).getFirstChild()
                                        .getNodeValue());
                        }
                        else
                        {
                                paf.setAnswer("");
                        }

                        bf = bdao.getBase(paf.getQuestionID());

                        if ((paf != null) && (bf != null) && (paf.getAnswer() != null) &&
                                (bf.getCorrectAnswer() != null) &&
                                paf.getAnswer().toUpperCase()
                                        .equals(bf.getCorrectAnswer().toUpperCase()))
                        {
                                paf.setGrade(bf.getScore());
                                score += bf.getScore();
                        }
                        else
                        {
                                paf.setGrade(0);
                        }

                        //                               list.add(paf);
                        padao.addPaperAnswer(paf);
                }

                //                       al.add(0,list);
                /**---------addPaperUsertable---------------**/
                NodeList paperusers = root.getElementsByTagName("T_PaperUser_Tab");
                Element paperuser = (Element) paperusers.item(0);

                //取"UserID"元素列表
                NodeList UserID1s = paperuser.getElementsByTagName("UserID");

                //取"PaperID"元素列表
                NodeList PaperID1s = paperuser.getElementsByTagName("PaperID");

                //取"type"元素列表
                NodeList type1s = paperuser.getElementsByTagName("type");

                //取"Answer"元素列表
                NodeList startTimes = paperuser.getElementsByTagName("startTime");
                NodeList endTimes = paperuser.getElementsByTagName("endTime");
                PaperUserForm puf = new PaperUserForm();

                //取"UserID"元素列表
                PaperAnswerForm paf = new PaperAnswerForm();
                puf.setUserID(StringUtil.parseInt(((Element) UserID1s.item(0)).getFirstChild()
                        .getNodeValue()));
                puf.setPaperID(StringUtil.parseInt(((Element) PaperID1s.item(0)).getFirstChild()
                        .getNodeValue()));
                puf.setType(StringUtil.parseInt(((Element) type1s.item(0)).getFirstChild()
                        .getNodeValue()));
                puf.setStartTime(DateTimeUtil.parseDateTime(((Element) startTimes
                        .item(0)).getFirstChild()
                        .getNodeValue()));
                puf.setEndTime(DateTimeUtil.parseDateTime(endTimes.item(0)
                        .getFirstChild()
                        .getNodeValue()));
                puf.setGrade(score);
                puf.setObjective(score);
                pudao.addPaperUser(puf);

                return al;
        }

        /**
         * 把list中的学生考试信息写入xml文件
         *
         * @param outFile
         */
        static boolean writeXMLFile(String outFile, List list)
                throws Exception
        {
                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                DocumentBuilder db = null;

                try
                {
                        db = dbf.newDocumentBuilder();
                }
                catch (ParserConfigurationException pce)
                {
                        pce.printStackTrace(); //出异常时输出异常信息，然后退出，下同
                        System.exit(1);
                }

                Document doc = null;
                doc = db.newDocument();

                Element item = null;
                Element prop = null;
                Text tex = null;
                Element root = doc.createElement("Exam");
                //根元素添加上文档
                doc.appendChild(root);

                Element paperanswer = doc.createElement("T_Answer_Tab");
                root.appendChild(paperanswer);

                HashMap hm = (HashMap) list.get(0);
                Collection coll = hm.values();
                Iterator ite = coll.iterator();

                while (ite.hasNext())
                {
                        PaperAnswerForm paf = (PaperAnswerForm) ite.next();
                        item = doc.createElement("item_" + paf.getQuestionID());
                        prop = doc.createElement("UserID");
                        tex = doc.createTextNode(paf.getUserID() + "");
                        prop.appendChild(tex);
                        item.appendChild(prop);

                        prop = doc.createElement("PaperID");
                        tex = doc.createTextNode(paf.getPaperID() + "");
                        prop.appendChild(tex);
                        item.appendChild(prop);

                        prop = doc.createElement("QuestionID");
                        tex = doc.createTextNode(paf.getQuestionID() + "");
                        prop.appendChild(tex);
                        item.appendChild(prop);

                        prop = doc.createElement("type");
                        tex = doc.createTextNode(paf.getType() + "");
                        prop.appendChild(tex);
                        item.appendChild(prop);

                        prop = doc.createElement("Answer");
                        tex = doc.createTextNode(paf.getAnswer());
                        prop.appendChild(tex);
                        item.appendChild(prop);
                        paperanswer.appendChild(item);

                        prop = doc.createElement("ExamTimes");
                        tex = doc.createTextNode(paf.getExamtimes() + "");
                        prop.appendChild(tex);
                        item.appendChild(prop);
                        paperanswer.appendChild(item);
                }

                PaperUserForm puf = (PaperUserForm) list.get(1);
                Element paperuser = doc.createElement("T_PaperUser_Tab");
                root.appendChild(paperuser);
                prop = doc.createElement("UserID");
                tex = doc.createTextNode(puf.getUserID() + "");
                prop.appendChild(tex);
                paperuser.appendChild(prop);

                prop = doc.createElement("PaperID");
                tex = doc.createTextNode(puf.getPaperID() + "");
                prop.appendChild(tex);
                paperuser.appendChild(prop);

                prop = doc.createElement("type");
                tex = doc.createTextNode(puf.getType() + "");
                prop.appendChild(tex);
                paperuser.appendChild(prop);

                prop = doc.createElement("startTime");
                tex = doc.createTextNode(DateTimeUtil.format(puf.getStartTime(),
                        "yyyy-MM-dd HH:mm:ss"));
                prop.appendChild(tex);
                paperuser.appendChild(prop);

                prop = doc.createElement("endTime");
                tex = doc.createTextNode(DateTimeUtil.format(puf.getEndTime(),
                        "yyyy-MM-dd HH:mm:ss"));
                prop.appendChild(tex);
                paperuser.appendChild(prop);

                Element e = doc.getDocumentElement();
                e.normalize();

                TransformerFactory tFactory = TransformerFactory.newInstance();
                Transformer transformer = tFactory.newTransformer();
                DOMSource source = new DOMSource(e);

                //System.out.println("outFile =1111 ========" + outFile);
                if (createNewXMLFile(outFile))
                {
                        StreamResult result = new StreamResult(new java.io.File(outFile));
                        transformer.transform(source, result);

                        File filestr = new File(outFile);

                        if (filestr.exists())
                        {
                                return true;
                        }
                        else
                        {
                                return false;
                        }
                }
                else
                {
                        return false;
                }
        }

        private static boolean createNewXMLFile(String location)
                throws Exception
        {
                boolean ceateNewFile = false;
                String filePath = location.substring(0,
                        location.lastIndexOf(File.separator));

                //System.out.println("filePath = =========" + filePath);
                //System.out.println("location = =========" + location);
                File newXMLDir = new File(filePath);

                if (!newXMLDir.exists())
                {
                        newXMLDir.mkdirs();
                }

                File newXMLFile = new File(location);

                try
                {
                        if (newXMLFile.exists())
                        {
                                return ceateNewFile;
                        }

                        newXMLFile.createNewFile();

                        PrintWriter newXMLFileWriter = new PrintWriter(new FileWriter(
                                newXMLFile));
                        newXMLFileWriter.println(
                                "<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
                        newXMLFileWriter.println("<systemOperations>");
                        newXMLFileWriter.println("</systemOperations>");
                        newXMLFileWriter.close();
                        ceateNewFile = true;
                }
                catch (Exception e)
                {
                        e.printStackTrace();
                        throw new Exception("" + e);
                }

                return ceateNewFile;
        }

        //用递归调用的方法实现文件的删除
        public static void delFile(String path)
        {
                File file = null;

                if ((path != null) && !path.equals(""))
                {
                        file = new File(path);

                        if (file.exists())
                        {
                                if (file.isDirectory())
                                {
                                        String[] names = file.list();

                                        for (int i = 0; (names != null) && (i < names.length);
                                             i++)
                                        {
                                                if (path.charAt(path.length() - 1) == '/')
                                                {
                                                        delFile(path + names[i]);
                                                }
                                                else
                                                {
                                                        delFile(path + "/" + names[i]);
                                                }
                                        }

                                        file.delete();
                                }
                                else
                                {
                                        file.delete();
                                }
                        }
                }
        }
}
