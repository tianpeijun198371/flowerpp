/** * AssignmentHelper.java.
 * Assignment: xiejh  Date: 2004-4-22 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.assignment.bean;

import com.ulearning.ulms.core.util.PagerList;
import com.ulearning.ulms.core.util.StringUtil;
import com.ulearning.ulms.course.exceptions.CourseDAOSysException;
import com.ulearning.ulms.tools.assignment.dao.AssignmentDAO;
import com.ulearning.ulms.tools.assignment.dao.AssignmentDAOFactory;
import com.ulearning.ulms.tools.assignment.exceptions.AssignmentDAOSysException;
import com.ulearning.ulms.tools.assignment.form.AssignmentForm;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class AssignmentHelper
{
        public String strTree = "";
        public int level = 0;

        /**
         * 返回用户所能看到的的所有作业列表
         *
         * @param userID
         * @param pageNo
         * @param pageSize
         * @return
         * @throws com.ulearning.ulms.course.exceptions.CourseDAOSysException
         *
         */
        public static PagerList getAllAssignmentsByUser(int userID, int pageNo,
                                                        int pageSize) throws CourseDAOSysException
        {
                AssignmentDAO assignmentDao = AssignmentDAOFactory.getDAO();

                return assignmentDao.getAllAssignmentsByUser(userID, pageNo, pageSize);
        }

        /**
         * Wrapping the get assignment method for JSP and  the other modules
         *
         * @param assignmentID
         * @return the assignment modle according to the assignmentID
         */
        public AssignmentForm getAssignment(int assignmentID)
        {
                AssignmentForm bf = null;

                try
                {
                        AssignmentDAO assignmentDao = AssignmentDAOFactory.getDAO();
                        bf = assignmentDao.getAssignment(assignmentID);
                }
                catch (AssignmentDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return bf;
        }

        /**
         * Wrapping the get assignmentList method for JSP and  the other modules
         *
         * @param rootID
         * @return the assignment list according to the rootID
         */
        public List getAssignmentList(int rootID)
        {
                List assignmentList = null;

                try
                {
                        AssignmentDAO assignmentDao = AssignmentDAOFactory.getDAO();
                        assignmentList = assignmentDao.getAssignmentList(rootID);
                }
                catch (AssignmentDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return assignmentList;
        }

        /**
         * Wrapping the get assignmentList method for JSP and  the other modules
         *
         * @param courseID
         * @return the assignment list according to the rootID
         */
        public List getAssignmentList(int courseID, boolean isAdmin)
        {
                List assignmentList = null;

                try
                {
                        AssignmentDAO assignmentDao = AssignmentDAOFactory.getDAO();
                        assignmentList = assignmentDao.getAssignmentList(courseID, isAdmin);
                }
                catch (AssignmentDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return assignmentList;
        }

        /**
         * Wrapping the get assignmentList method for JSP and  the other modules
         *
         * @param CourseID
         * @return the assignment list according to the CourseID
         */
        public List getRootAssignmentList(int CourseID)
        {
                List assignmentList = null;

                try
                {
                        AssignmentDAO assignmentDao = AssignmentDAOFactory.getDAO();
                        assignmentList = assignmentDao.getRootAssignmentList(CourseID);
                }
                catch (AssignmentDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return assignmentList;
        }

        /**
         * Wrapping the get assignmentList method for JSP and  the other modules
         *
         * @param parentID intArray[][]
         */
        public void setTree(int parentID, int[][] intArray)
        {
                level++;

                int[][] SubID = getSubID(parentID, intArray);

                if ((SubID != null) && (SubID.length != 0))
                {
                        for (int index = 0; index < SubID.length; index++)
                        {
                                if (SubID[index][1] != 0)
                                {
                                        strTree = strTree + "*" + SubID[index][0] + "," +
                                                SubID[index][1] + "," + level;
                                        setTree(SubID[index][1], intArray);
                                }
                        }
                }

                level--;
        }

        public int[][] getSubID(int parentID, int[][] intArray)
        {
                int len = 0;

                for (int index = 0; index < intArray.length; index++)
                {
                        if ((parentID == intArray[index][0]) &&
                                (parentID != intArray[index][1]))
                        {
                                len++;
                        }
                }

                if (len == 0)
                {
                        return null;
                }

                int[][] a = new int[len + 1][2];

                for (int index = 0; index < a.length; index++)
                {
                        a[index][0] = 0;
                        a[index][1] = 0;
                }

                int num = 0;

                for (int index = 0; index < intArray.length; index++)
                {
                        if ((parentID == intArray[index][0]) &&
                                (parentID != intArray[index][1]))
                        {
                                a[num][0] = intArray[index][0];
                                a[num][1] = intArray[index][1];
                                num++;
                        }
                }

                return a;
        }

        public void iniTree()
        {
                strTree = "";
                level = 0;
        }

        public List getTree()
        {
                List a = StringUtil.split(strTree, "*");

                return a;
        }

        //str="1,2/2,3/2,5/"   parentID=2
        public List getSubID(String str, String parentID)
        {
                List s = StringUtil.split(str, "/");

                if (s == null)
                {
                        return null;
                }

                int[][] aa = new int[s.size()][2];

                for (int i = 0; i < s.size(); i++)
                {
                        String tmp = (String) s.get(i);
                        int n = tmp.indexOf(",");
                        aa[i][0] = Integer.parseInt(tmp.substring(0, n));
                        aa[i][1] = Integer.parseInt(tmp.substring(n + 1));
                }

                iniTree();
                setTree(Integer.parseInt(parentID), aa);

                return getTree();
        }

        public static void main(String[] args)
        {
                AssignmentHelper ah = new AssignmentHelper();

                /*
                  int[][] aa = new int[8][2];
                  aa[1][0]=0;                 aa[1][1]=1;
                  aa[2][0]=1;                 aa[2][1]=2;
                  aa[3][0]=2;                 aa[3][1]=3;
                  aa[4][0]=2;                 aa[4][1]=4;
                  aa[5][0]=1;                 aa[5][1]=5;
                  aa[6][0]=2;                 aa[6][1]=6;
                  aa[7][0]=4;                 aa[7][1]=7;
                  ah.iniTree();
                  ah.setTree(0,aa);
                  List res=ah.getTree();
                */

                //List res=ah.getSubID("","2");
                PagerList pres = AssignmentHelper.getAllAssignmentsByUser(248, 0, 10);
                AssignmentHelper as = new AssignmentHelper();
                List res = pres.getPagerList();

                for (int index = 0; index < res.size(); index++)
                {
                        AssignmentForm fo = (AssignmentForm) res.get(index);
                        System.out.println(fo.getAssignmentID());
                        System.out.println(fo.getName());
                        System.out.println("========================");
                }
        }

        public List getNewTrainInfo(int courseID, int rootID, int type,
                                    boolean isAdmin) throws AssignmentDAOSysException
        {
                return AssignmentDAOFactory.getDAO()
                        .getNewTrainInfo(courseID, rootID, type,
                                isAdmin);
        }

        /**
         * 过滤得到没有过期的作业，利用getRootAssignmentList(int) method,　进行再过滤.
         *
         * @param courseID Current select courseID of course.
         * @return not overdue assignment list.
         * @ author Liz
         * @ date 2005.11.30
         */
        public List getUsableAssignment(int courseID)
        {
                List list = null;
                List returnList = new ArrayList();
                //取课程作业
                list = getRootAssignmentList(courseID);

                //取当前日期
                Calendar cal_today = Calendar.getInstance();
                String today = (new java.sql.Date(cal_today.get(Calendar.YEAR) - 1900,
                        cal_today.get(Calendar.MONTH), cal_today.get(Calendar.DATE))).toString();

                if ((null != list) && (0 != list.size()))
                {
                        for (int i = 0; i < list.size(); i++)
                        {
                                if ((((AssignmentForm) list.get(i)).getDisUntilTime()
                                        .compareTo(today) >= 0) &&
                                        ((AssignmentForm) list.get(i)).getViewable().equals("1") &&
                                        ((AssignmentForm) list.get(i)).getAvailable().equals("1"))
                                {
                                        returnList.add(list.get(i));
                                }
                        }
                }

                return returnList;
        }
}
