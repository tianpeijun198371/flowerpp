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
         * 对考试LIST的再次过滤，显示可以考试的
         *
         * @return LIST 页面显示的LIST
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
         * 对于多场次考试的判断
         *
         * @param pf     试卷实体对象
         * @param userID 用户ID
         * @param path   客户系统定义的路径
         * @return 试卷的实体对象
         */
        public PaperForm changeC(PaperForm pf, int userID, String path)
        {
                boolean testClassID = true;
                boolean grade = true;
                String restr = "<div align=\"center\"><a href=\"javascript:exam(" +
                        pf.getPaperID() + ")\" ><img src=\"" + path +
                        "/images/index_dot12.gif\" " +
                        "alt=\"参加考试\" border=\"0\"><br>进入考试</a></div>";

                //System.out.println(pf.getEndTime().getTime()>new Date().getTime());
                //判断文章试卷结束时间大于当前时间
                if (pf.getEndTime().getTime() > new Date().getTime())
                {
                        //
                        ExambatchDAOImpl exambatchDAOImpl = new ExambatchDAOImpl();
                        List paperList = exambatchDAOImpl.getPaperList(pf.getPaperID());
                        boolean flag = false;

                        //判断是否是开始时间
                        boolean flagnext = false;
                        Date bsgstr = null;
                        boolean flagstr = false;
                        UserGroupDAOImpl userGroupDAOImpl = new UserGroupDAOImpl();

                        for (int i = 0; i < paperList.size(); i++)
                        {
                                ExambatchForm exambatchModel = (ExambatchForm) paperList.get(i);

                                //判断是否进入考试的时间
                                if (DateTimeUtil.isTestTime(exambatchModel.getExambegintime(),
                                        new Date(), exambatchModel.getExamendtime()))
                                {
                                        //判断考生是否是本场考生
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
                                       {       //当判断如果是
                                               pf.setStartTime(exambatchModel.getExambegintime());
                                               pf.setEndTime(exambatchModel.getExamendtime());
                                               flagnext = false;
                                       }
                                       if (new Date().getTime() > exambatchModel.getExamendtime().getTime())
                                       {
                                               flagnext = true;
                                       } */

                                        //重新设置迟到时间
                                        bsgstr = exambatchModel.getExambegintime();
                                        flag = true;
                                }
                        }

                        //判断考生考场
                        if (!flagstr)
                        {
                                pf.setDesc6(
                                        "<div align=\"center\"><font color=\"blue\">您不是本场考生</font></div>");

                                return pf;
                        }

                        //超过允许考试时间
                        if (flag && (testClassID || grade))
                        {
                                String time = pf.getDesc4();
                                Calendar cl = Calendar.getInstance();
                                cl.add(12, -StringUtil.parseInt(time));

                                //判断是否时间为null
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
                                                        "<div align=\"center\"><font color=\"blue\">超过允许迟到时间</font></div>");

                                                return pf;
                                        }
                                }
                                else
                                {
                                        pf.setDesc6(restr);

                                        return pf;
                                }
                        }
                        //考场休息时间
                        else if (!flag)
                        {
                                pf.setDesc6(
                                        "<div align=\"center\"><font color=\"red\">考场休息时间</font></div>");

                                return pf;
                        }
                        //成绩测试在paper进行处理
                        else if (!grade)
                        {
                                pf.setDesc6(
                                        "<div align=\"center\"><font color=\"red\">您的成绩已经通过考试</font></div>");

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
         * 对于多次考试的判断
         *
         * @param pf     试卷实体对象
         * @param userID 用户ID
         * @param path   用户的本地系统地址
         * @return 过滤的试卷实体
         */
        public PaperForm changeB(PaperForm pf, int userID, String path)
        {
                String restr = "<div align=\"center\"><a href=\"javascript:exam(" +
                        pf.getPaperID() + ")\" ><img src=\"" + path +
                        "/images/index_dot12.gif\" " +
                        "alt=\"参加考试\" border=\"0\"><br>进入考试</a></div>";

                //判断开始时间超过结束时间
                if (pf.getEndTime().getTime() > new Date().getTime())
                {
                        PaperAnswerHelper paperhelper = new PaperAnswerHelper();

                        //判断是否超过最大的考试次数
                        if (paperhelper.getPaperAnswerTimes(userID, pf.getPaperID()) >= Integer.parseInt(
                                pf.getDesc2().split("|")[3]))
                        {
                                pf.setDesc6(
                                        "<div align=\"center\"><font color=\"red\">已超过最大考试次数</font></div>");

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
         * 对于单次考试的判断
         *
         * @param pf     试卷实体对象
         * @param userID 用户ID
         * @param path   用户的本地系统地址
         * @return 过滤的试卷实体
         */
        public PaperForm changeA(PaperForm pf, int userID, String path)
        {
                String restr = "<div align=\"center\"><a href=\"javascript:exam(" +
                        pf.getPaperID() + ")\" ><img src=\"" + path +
                        "/images/index_dot12.gif\"  " +
                        "alt=\"参加考试\" border=\"0\"><br>进入考试</a></div>";

                //判断开始时间超过结束时间
                if (pf.getEndTime().getTime() > new Date().getTime())
                {
                        PaperAnswerHelper paperhelper = new PaperAnswerHelper();

                        //判断是否有考试成绩
                        if (paperhelper.getPaperAnswerTimes(userID, pf.getPaperID()) > 0)
                        {
                                pf.setDesc6(
                                        "<div align=\"center\"><font color=\"red\">已参加考试</font></div>");

                                return pf;
                        }
                        //判断是否在迟到时间内
                        else if ((pf.getDesc4() != null) && !pf.getDesc4().equals("") &&
                                !pf.getDesc4().equals("0"))
                        {
                                String time = pf.getDesc4();
                                Calendar cl = Calendar.getInstance();
                                cl.add(12, -StringUtil.parseInt(time));

                                //判断迟到时间
                                if (cl.getTime().getTime() > pf.getStartTime().getTime())
                                {
                                        pf.setDesc6(
                                                "<div align=\"center\"><font color=\"blue\">超过允许迟到时间</font></div>");

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
