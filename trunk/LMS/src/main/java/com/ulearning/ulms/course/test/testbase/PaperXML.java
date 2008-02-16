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
         * ��ȡxml�ļ��е�ѧ��������Ϣ
         *
         * @param inFile
         */
        static public boolean readXMLFile(String inFile, boolean isHasAnswer,
                                          HashMap hm) throws ULMSSysException
        {
                PaperAnswerDAO padao = PaperAnswerDAOFactory.getDAO();
                PaperUserDAO pudao = PaperUserDAOFactory.getDAO();
                PaperQuestionDAO pqdao = PaperQuestionDAOFactory.getDAO();

                //Ϊ����XML��׼��������DocumentBuilderFactoryʵ��,ָ��DocumentBuilder
                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                DocumentBuilder db = null;

                try
                {
                        db = dbf.newDocumentBuilder();
                }
                catch (ParserConfigurationException pce)
                {
                        pce.printStackTrace(); //���쳣ʱ����쳣��Ϣ��Ȼ���˳�����ͬ
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

                //�����ǽ���XML��ȫ���̣��Ƚϼ򵥣���ȡ��Ԫ��"Exam"
                Element root = doc.getDocumentElement();
                root.normalize();

                /**---------addAnswertable---------------**/
                NodeList answers = root.getElementsByTagName("T_Answer_Tab");
                Element paperanswer = (Element) answers.item(0);

                //ȡ"UserID"Ԫ���б�
                NodeList UserIDs = paperanswer.getElementsByTagName("UserID");

                //ȡ"PaperID"Ԫ���б�
                NodeList PaperIDs = paperanswer.getElementsByTagName("PaperID");

                //ȡ"QuestionID"Ԫ���б�
                NodeList QuestionIDs = paperanswer.getElementsByTagName("QuestionID");

                //ȡ"type"Ԫ���б�
                NodeList types = paperanswer.getElementsByTagName("type");

                //ȡ"Answer"Ԫ���б�
                NodeList Answers = paperanswer.getElementsByTagName("Answer");

                //ȡ"ExamTimes"Ԫ���б�
                NodeList ExamTimes = paperanswer.getElementsByTagName("ExamTimes");
                BaseForm bf = null;

                PaperQuestionForm pqf = null;

                float score = 0;
                PaperUserForm puf = new PaperUserForm();
                PaperAnswerForm paf = new PaperAnswerForm();

                for (int i = 0; (UserIDs != null) && (i < UserIDs.getLength()); i++)
                {
                        //����ȡÿ��"ѧ��"Ԫ��
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

                //al������0λ�÷���BaseForm��������
                /**---------addPaperUsertable---------------**/
                NodeList paperusers = root.getElementsByTagName("T_PaperUser_Tab");
                Element paperuser = (Element) paperusers.item(0);

                //ȡ"UserID"Ԫ���б�
                NodeList UserID1s = paperuser.getElementsByTagName("UserID");

                //ȡ"PaperID"Ԫ���б�
                NodeList PaperID1s = paperuser.getElementsByTagName("PaperID");

                //ȡ"type"Ԫ���б�
                NodeList type1s = paperuser.getElementsByTagName("type");

                //ȡ"Answer"Ԫ���б�
                NodeList startTimes = paperuser.getElementsByTagName("startTime");
                NodeList endTimes = paperuser.getElementsByTagName("endTime");

                //ȡ"ExamTimes"Ԫ���б�
                NodeList ExamTime1s = paperanswer.getElementsByTagName("ExamTimes");
                //ȡ"UserID"Ԫ���б�
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
                //�����ڴ�ռ�
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
         * ������ȡxml�ļ��е�ѧ����������
         *
         * @param inFile      �Ծ�Ŀ¼
         * @param isHasAnswer �Ƿ�����
         * @param hm          ��������
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
                                                //Ƕ�׵�ǰ����
                                                batchReadXML(inFile + "/" + s[i], isHasAnswer, hm);
                                        }
                                }
                                else
                                {
                                        if (i == (s.length - 1))
                                        {
                                                //�ж��ļ��Ƿ���XML
                                                if (s[i].toLowerCase().indexOf("xml") == -1)
                                                {
                                                }
                                                else
                                                {
                                                        //��һ��xml�ļ�
                                                        boolean is = readXMLFile(inFile + "/" + s[i],
                                                                isHasAnswer, hm);

                                                        if (is)
                                                        {
                                                                //��xml�ļ�д����гɹ�������ļ�������
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
         * ��ȡxml�ļ��е�ѧ��������Ϣ
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

                //Ϊ����XML��׼��������DocumentBuilderFactoryʵ��,ָ��DocumentBuilder
                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                DocumentBuilder db = null;

                try
                {
                        db = dbf.newDocumentBuilder();
                }
                catch (ParserConfigurationException pce)
                {
                        pce.printStackTrace(); //���쳣ʱ����쳣��Ϣ��Ȼ���˳�����ͬ
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

                //�����ǽ���XML��ȫ���̣��Ƚϼ򵥣���ȡ��Ԫ��"Exam"
                Element root = doc.getDocumentElement();
                root.normalize();

                //                       System.out.println("root="+root.getElementsByTagName("T_Answer_Tab"));
                //                      Attr paperanswer = root.getAttributeNode("Exam");
                /**---------addAnswertable---------------**/
                NodeList answers = root.getElementsByTagName("T_Answer_Tab");
                Element paperanswer = (Element) answers.item(0);

                //ȡ"UserID"Ԫ���б�
                NodeList UserIDs = paperanswer.getElementsByTagName("UserID");

                //ȡ"PaperID"Ԫ���б�
                NodeList PaperIDs = paperanswer.getElementsByTagName("PaperID");

                //ȡ"QuestionID"Ԫ���б�
                NodeList QuestionIDs = paperanswer.getElementsByTagName("QuestionID");

                //ȡ"type"Ԫ���б�
                NodeList types = paperanswer.getElementsByTagName("type");

                //ȡ"Answer"Ԫ���б�
                NodeList Answers = paperanswer.getElementsByTagName("Answer");

                //ȡ"ExamTimes"Ԫ���б�
                NodeList ExamTimes = root.getElementsByTagName("ExamTimes");
                BaseForm bf = null;
                BaseDAO bdao = BaseDAOFactory.getDAO();
                float score = 0;

                for (int i = 0; (UserIDs != null) && (i < UserIDs.getLength()); i++)
                {
                        //����ȡÿ��"ѧ��"Ԫ��
                        PaperAnswerForm paf = new PaperAnswerForm();
                        paf.setUserID(StringUtil.parseInt(((Element) UserIDs.item(i)).getFirstChild()
                                .getNodeValue()));
                        paf.setPaperID(StringUtil.parseInt(((Element) PaperIDs.item(i)).getFirstChild()
                                .getNodeValue()));
                        paf.setQuestionID(StringUtil.parseInt(((Element) QuestionIDs
                                .item(i)).getFirstChild()
                                .getNodeValue()));
                        //                               System.out.println("���ɹ�!" + types.item(i).getFirstChild());
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

                //ȡ"UserID"Ԫ���б�
                NodeList UserID1s = paperuser.getElementsByTagName("UserID");

                //ȡ"PaperID"Ԫ���б�
                NodeList PaperID1s = paperuser.getElementsByTagName("PaperID");

                //ȡ"type"Ԫ���б�
                NodeList type1s = paperuser.getElementsByTagName("type");

                //ȡ"Answer"Ԫ���б�
                NodeList startTimes = paperuser.getElementsByTagName("startTime");
                NodeList endTimes = paperuser.getElementsByTagName("endTime");
                PaperUserForm puf = new PaperUserForm();

                //ȡ"UserID"Ԫ���б�
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
         * ��list�е�ѧ��������Ϣд��xml�ļ�
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
                        pce.printStackTrace(); //���쳣ʱ����쳣��Ϣ��Ȼ���˳�����ͬ
                        System.exit(1);
                }

                Document doc = null;
                doc = db.newDocument();

                Element item = null;
                Element prop = null;
                Text tex = null;
                Element root = doc.createElement("Exam");
                //��Ԫ��������ĵ�
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

        //�õݹ���õķ���ʵ���ļ���ɾ��
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
