/**
 * PaperHelper.java.
 * User: huangsb  Date: 2004-6-15
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd. * All rights reserved.
 */
package com.ulearning.ulms.course.test.paper.bean;

import com.ulearning.ulms.core.util.DateTimeUtil;
import com.ulearning.ulms.core.util.StringUtil;
import com.ulearning.ulms.course.model.CourseListModel;
import com.ulearning.ulms.course.model.CourseModel;
import com.ulearning.ulms.course.test.Exambatch.dao.ExambatchDAOImpl;
import com.ulearning.ulms.course.test.Exambatch.form.ExambatchForm;
import com.ulearning.ulms.course.test.paper.dao.PaperDAO;
import com.ulearning.ulms.course.test.paper.dao.PaperDAOFactory;
import com.ulearning.ulms.course.test.paper.exceptions.PaperDAOSysException;
import com.ulearning.ulms.course.test.paper.form.PaperForm;
import com.ulearning.ulms.course.test.testbase.PaperBaseConstants;
import com.ulearning.ulms.course.webimpls.CourseWebImpl;
import com.ulearning.ulms.user.group.dao.UserGroupDAOImpl;
import com.ulearning.ulms.user.group.form.UserGroupForm;

import java.util.*;


public class PaperHelper
{
        /**
         * Wrapping the get paper method for JSP and  the other modules
         *
         * @param paperID
         * @return the admin modle according to the paperID
         */
        public PaperForm getPaper(int paperID)
        {
                PaperForm pf = null;

                try
                {
                        PaperDAO paperDao = PaperDAOFactory.getDAO();
                        pf = paperDao.getPaper(paperID);
                }
                catch (PaperDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return pf;
        }

        public static void removePaper(int paperID)
        {
                try
                {
                        PaperDAO paperDao = PaperDAOFactory.getDAO();
                        paperDao.removePaper(paperID);
                }
                catch (PaperDAOSysException udse)
                {
                        udse.printStackTrace();
                }
        }

        /**
         * Wrapping the get paperList method for JSP and  the other modules
         *
         * @param courseID
         * @return the plan list according to the paperID
         */
        public List getPaperList(int courseID, int type)
        {
                List paperList = null;

                try
                {
                        PaperDAO paperDao = PaperDAOFactory.getDAO();
                        paperList = paperDao.getPaperList(courseID, type);
                }
                catch (PaperDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return paperList;
        }

        /**
         * �Կ���LIST���ٴι��ˣ���ʾ���Կ��Ե�
         *
         * @return LIST ҳ����ʾ��LIST
         */
        public List getcheckList(List paperListin, boolean isAdmi, int userID,
                                 String path)
        {
                List paperList = new ArrayList();

                //ArrayList paperList = new ArrayList();
                for (int i = 0; i < paperListin.size(); i++)
                {
                        //System.out.print(paperListin.size());
                        PaperForm pf = (PaperForm) paperListin.get(i);
                        System.out.println("pf.getPaperID() ==================" +
                                pf.getPaperID());

                        if (pf.getDesc2().indexOf("A") != -1)
                        {
                                paperList.add(changeA(pf, userID, path));
                        }
                        else if (pf.getDesc2().indexOf("B") != -1)
                        {
                                paperList.add(changeB(pf, userID, path));
                        }
                        else if (pf.getDesc2().indexOf("C") != -1)
                        {
                                paperList.add(changeC(pf, userID, path));
                        }
                }

                return paperList;
        }

        /**
         * ���ڶೡ�ο��Ե��ж�
         *
         * @param pf     �Ծ�ʵ�����
         * @param userID �û�ID
         * @param path   �ͻ�ϵͳ�����·��
         * @return �Ծ��ʵ�����
         */
        public PaperForm changeC(PaperForm pf, int userID, String path)
        {
                boolean testClassID = true;
                boolean grade = true;
                String restr = "<div align=\"center\"><a href=\"javascript:exam(" +
                        pf.getPaperID() + ")\" ><img src=\"" + path +
                        "/images/index_dot12.gif\" " +
                        "alt=\"�μӿ���\" border=\"0\"><br>���뿼��</a></div>";

                //System.out.println(pf.getEndTime().getTime()>new Date().getTime());
                //�ж������Ծ����ʱ����ڵ�ǰʱ��
                if (pf.getEndTime().getTime() > new Date().getTime())
                {
                        //
                        ExambatchDAOImpl exambatchDAOImpl = new ExambatchDAOImpl();
                        List paperList = exambatchDAOImpl.getPaperList(pf.getPaperID());
                        boolean flag = false;

                        //�ж��Ƿ��ǿ�ʼʱ��
                        boolean flagnext = false;
                        Date bsgstr = null;
                        boolean flagstr = false;
                        UserGroupDAOImpl userGroupDAOImpl = new UserGroupDAOImpl();

                        for (int i = 0; i < paperList.size(); i++)
                        {
                                ExambatchForm exambatchModel = (ExambatchForm) paperList.get(i);

                                //�ж��Ƿ���뿼�Ե�ʱ��
                                if (DateTimeUtil.isTestTime(exambatchModel.getExambegintime(),
                                        new Date(), exambatchModel.getExamendtime()))
                                {
                                        //�жϿ����Ƿ��Ǳ�������
                                        List bl = userGroupDAOImpl.getUserGroupList(exambatchModel.getClassID());

                                        for (int ui = 0; ui < bl.size(); ui++)
                                        {
                                                UserGroupForm userGroupForm = (UserGroupForm) bl.get(ui);

                                                if (userGroupForm.getUserid() == userID)
                                                {
                                                        flagstr = true;
                                                }
                                        }

                                        /*if (flagnext)
                                       {       //���ж������
                                               pf.setStartTime(exambatchModel.getExambegintime());
                                               pf.setEndTime(exambatchModel.getExamendtime());
                                               flagnext = false;
                                       }
                                       if (new Date().getTime() > exambatchModel.getExamendtime().getTime())
                                       {
                                               flagnext = true;
                                       } */

                                        //�������óٵ�ʱ��
                                        bsgstr = exambatchModel.getExambegintime();
                                        flag = true;
                                }
                        }

                        //�жϿ�������
                        if (!flagstr)
                        {
                                pf.setDesc6(
                                        "<div align=\"center\"><font color=\"blue\">�����Ǳ�������</font></div>");

                                return pf;
                        }

                        //����������ʱ��
                        if (flag && (testClassID || grade))
                        {
                                String time = pf.getDesc4();
                                Calendar cl = Calendar.getInstance();
                                cl.add(12, -StringUtil.parseInt(time));

                                //�ж��Ƿ�ʱ��Ϊnull
                                if (cl.getTime().getTime() > bsgstr.getTime())
                                {
                                        if ((time == null) || (time.equals("")) ||
                                                (time.equals("0")))
                                        {
                                                pf.setDesc6(restr);

                                                return pf;
                                        }
                                        else
                                        {
                                                pf.setDesc6(
                                                        "<div align=\"center\"><font color=\"blue\">��������ٵ�ʱ��</font></div>");

                                                return pf;
                                        }
                                }
                                else
                                {
                                        pf.setDesc6(restr);

                                        return pf;
                                }
                        }
                        //������Ϣʱ��
                        else if (!flag)
                        {
                                pf.setDesc6(
                                        "<div align=\"center\"><font color=\"red\">������Ϣʱ��</font></div>");

                                return pf;
                        }
                        //�ɼ�������paper���д���
                        else if (!grade)
                        {
                                pf.setDesc6(
                                        "<div align=\"center\"><font color=\"red\">���ĳɼ��Ѿ�ͨ������</font></div>");

                                return pf;
                        }
                        else
                        {
                                return null;
                        }
                }
                else
                {
                        return null;
                }
        }

        /**
         * ���ڶ�ο��Ե��ж�
         *
         * @param pf     �Ծ�ʵ�����
         * @param userID �û�ID
         * @param path   �û��ı���ϵͳ��ַ
         * @return ���˵��Ծ�ʵ��
         */
        public PaperForm changeB(PaperForm pf, int userID, String path)
        {
                String restr = "<div align=\"center\"><a href=\"javascript:exam(" +
                        pf.getPaperID() + ")\" ><img src=\"" + path +
                        "/images/index_dot12.gif\" " +
                        "alt=\"�μӿ���\" border=\"0\"><br>���뿼��</a></div>";

                //�жϿ�ʼʱ�䳬������ʱ��
                if (pf.getEndTime().getTime() > new Date().getTime())
                {
                        PaperAnswerHelper paperhelper = new PaperAnswerHelper();

                        //�ж��Ƿ񳬹����Ŀ��Դ���
                        if (paperhelper.getPaperAnswerTimes(userID, pf.getPaperID()) >= Integer.parseInt(
                                pf.getDesc2().split("|")[3]))
                        {
                                pf.setDesc6(
                                        "<div align=\"center\"><font color=\"red\">�ѳ�������Դ���</font></div>");

                                return pf;
                        }
                        else
                        {
                                pf.setDesc6(restr);

                                return pf;
                        }
                }
                else
                {
                        //pf.setDesc6(restr);
                        return null;
                }
        }

        /**
         * ���ڵ��ο��Ե��ж�
         *
         * @param pf     �Ծ�ʵ�����
         * @param userID �û�ID
         * @param path   �û��ı���ϵͳ��ַ
         * @return ���˵��Ծ�ʵ��
         */
        public PaperForm changeA(PaperForm pf, int userID, String path)
        {
                String restr = "<div align=\"center\"><a href=\"javascript:exam(" +
                        pf.getPaperID() + ")\" ><img src=\"" + path +
                        "/images/index_dot12.gif\"  " +
                        "alt=\"�μӿ���\" border=\"0\"><br>���뿼��</a></div>";

                //�жϿ�ʼʱ�䳬������ʱ��
                if (pf.getEndTime().getTime() > new Date().getTime())
                {
                        PaperAnswerHelper paperhelper = new PaperAnswerHelper();

                        //�ж��Ƿ��п��Գɼ�
                        if (paperhelper.getPaperAnswerTimes(userID, pf.getPaperID()) > 0)
                        {
                                pf.setDesc6(
                                        "<div align=\"center\"><font color=\"red\">�Ѳμӿ���</font></div>");

                                return pf;
                        }
                        //�ж��Ƿ��ڳٵ�ʱ����
                        else if ((pf.getDesc4() != null) && !pf.getDesc4().equals("") &&
                                !pf.getDesc4().equals("0"))
                        {
                                String time = pf.getDesc4();
                                Calendar cl = Calendar.getInstance();
                                cl.add(12, -StringUtil.parseInt(time));

                                //�жϳٵ�ʱ��
                                if (cl.getTime().getTime() > pf.getStartTime().getTime())
                                {
                                        pf.setDesc6(
                                                "<div align=\"center\"><font color=\"blue\">��������ٵ�ʱ��</font></div>");

                                        return pf;
                                }
                                else
                                {
                                        pf.setDesc6(restr);

                                        return pf;
                                }
                        }
                        else
                        {
                                pf.setDesc6(restr);

                                return pf;
                        }
                }
                else
                {
                        return null;
                }
        }

        /**
         * Wrapping the get paperList method for JSP and  the other modules
         *
         * @param type
         * @return the plan list according to the paperID
         */
        public List getPaperShowList(int courseID, int type)
        {
                List paperList = null;
                ArrayList resultList = new ArrayList();

                try
                {
                        PaperDAO paperDao = PaperDAOFactory.getDAO();
                        paperList = paperDao.getPaperShowList(courseID, type);

                        Date date = new Date();

                        for (int i = 0; i < paperList.size(); i++)
                        {
                                PaperForm pf = (PaperForm) paperList.get(i);

                                if (pf.getStartTime().before(date) &&
                                        pf.getEndTime().after(date))
                                {
                                        resultList.add(pf);
                                }
                        }
                }
                catch (PaperDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return resultList;
        }

        /**
         * Wrapping the get paperList method for JSP and  the other modules
         *
         * @param courseID
         * @return the plan list according to the paperID
         */
        public List getExerciseList(int courseID, int type)
        {
                List paperList = null;

                try
                {
                        PaperDAO paperDao = PaperDAOFactory.getDAO();
                        paperList = paperDao.getExerciseList(courseID, type);
                }
                catch (PaperDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return paperList;
        }

        /**
         * Wrapping the get paperList method for JSP and  the other modules
         *
         * @param courseID
         * @return the plan list according to the paperID
         */
        public List getDistinctExerciseList(int courseID, int type)
        {
                List list = new ArrayList();

                try
                {
                        PaperDAO paperDao = PaperDAOFactory.getDAO();
                        list = paperDao.getDistinctExerciseList(courseID, type);
                }
                catch (PaperDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return list;
        }

        public List getMyExamList(int userID)
        {
                List list = new ArrayList();
                CourseWebImpl cwm = new CourseWebImpl();
                CourseListModel clm = cwm.getMyAllCourses(userID);
                List myCourseList = clm.getList();

                for (int i = 0; i < myCourseList.size(); i++)
                {
                        CourseModel cm = (CourseModel) myCourseList.get(i);
                        List tmpPaperList = getDistinctExerciseList(cm.getCourseID(),
                                Integer.parseInt(PaperBaseConstants.PAPER_EXAMINATION_TYPE));

                        //System.out.println("cm.getCourseID() = " + cm.getCourseID());
                        if ((tmpPaperList != null) && (tmpPaperList.size() > 0))
                        {
                                list.addAll(tmpPaperList);
                        }

                        /*
                        if(tmpPaperList!=null&&tmpPaperList.size()>0)
                        {
                             for(int j=0;j<tmpPaperList.size();j++)
                             {
                                  PaperForm pf = (PaperForm) tmpPaperList.get(i);
                                  list.add(pf);
                             }
                        }*/
                }

                return list;
        }

        public List getMyPaperList(int userID)
        {
                List list = new ArrayList();
                CourseWebImpl cwm = new CourseWebImpl();
                CourseListModel clm = cwm.getMyAllCourses(userID);
                List myCourseList = clm.getList();

                for (int i = 0; i < myCourseList.size(); i++)
                {
                        CourseModel cm = (CourseModel) myCourseList.get(i);
                        List tmpPaperList = getPaperList(cm.getCourseID(),
                                Integer.parseInt(PaperBaseConstants.PAPER_EXAMINATION_TYPE));

                        //System.out.println("cm.getCourseID() = " + cm.getCourseID());
                        if ((tmpPaperList != null) && (tmpPaperList.size() > 0))
                        {
                                list.addAll(tmpPaperList);
                        }

                        /*
                        if(tmpPaperList!=null&&tmpPaperList.size()>0)
                        {
                             for(int j=0;j<tmpPaperList.size();j++)
                             {
                                  PaperForm pf = (PaperForm) tmpPaperList.get(i);
                                  list.add(pf);
                             }
                        }*/
                }

                return list;
        }

        public static void main(String[] main)
        {
                PaperHelper ph = new PaperHelper();
                List aa = ph.getMyExamList(100);

                for (int i = 0; i < aa.size(); i++)
                {
                        PaperForm pf = new PaperForm();
                        //pf.getBeginTime();
                        System.out.println("pf.getTitle() +getBeginTime()= " +
                                pf.getTitle() + "==" + pf.getBeginTime());
                }
        }
}
