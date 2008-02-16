/**
 * Created by IntelliJ IDEA.
 * User: zengwj
 * Date: 2004-8-20
 * Time: 10:10:23
 * To change this template use File | Settings | File Templates.
 */
package com.ulearning.ulms.admin.colsignup.colstudent.bean;

import com.ulearning.ulms.admin.colsignup.colstudent.dao.ColStudentDAO;
import com.ulearning.ulms.admin.colsignup.colstudent.dao.ColStudentDAOFactory;
import com.ulearning.ulms.admin.colsignup.colstudent.exceptions.ColStudentDAOSysException;
import com.ulearning.ulms.admin.colsignup.colstudent.form.ColStudentForm;
import com.ulearning.ulms.core.security.bean.SecurityConstants;
import com.ulearning.ulms.user.dao.UserDAOImpl;
import com.ulearning.ulms.user.exceptions.UserDAOSysException;
import com.ulearning.ulms.user.form.UserForm;

import java.util.ArrayList;
import java.util.List;


public class ColStudentHelper
{
        private static ColStudentDAO colStudentDAO = null;

        static
        {
                try
                {
                        colStudentDAO = ColStudentDAOFactory.getDAO();
                }
                catch (Exception ex)
                {
                }
        }

        /**
         * Wrapping the get book method for JSP and  the other modules
         *
         * @return the book modle according to the bookID
         */
        public static int getStudentNumber(int organID, int ColSignDetailID,
                                           int Type)
        {
                int total = 0;

                try
                {
                        total = colStudentDAO.getStudentNumber(organID, ColSignDetailID,
                                Type);
                }
                catch (ColStudentDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return total;
        }

        public static void addUser(ColStudentForm csf)
        {
                try
                {
                        if (colStudentDAO.getColStudent(csf.getColSignDetailID(),
                                csf.getRelationID(), csf.getType()) == null)
                        {
                                colStudentDAO.addColStudent(csf);
                        }
                }
                catch (ColStudentDAOSysException udse)
                {
                        udse.printStackTrace();
                }
        }

        public static void signUpByOrg(int orgID, int ColSignDetailID)
        {
                UserDAOImpl userDAOImpl = new UserDAOImpl();
                List studentList = new ArrayList();

                try
                {
                        studentList = userDAOImpl.getUserList(orgID + "");

                        ColStudentForm csf = new ColStudentForm();
                        UserForm uf = null;

                        csf.setColSignDetailID(ColSignDetailID);
                        csf.setType(SecurityConstants.USER_DEFAULT_RELATION);
                        csf.setApproved("0");
                        csf.setFeeState("0");

                        for (int i = 0; i < studentList.size(); i++)
                        {
                                uf = (UserForm) studentList.get(i);
                                csf.setRelationID(uf.getUserID());
                                addUser(csf);
                        }
                }
                catch (UserDAOSysException ee)
                {
                        ee.printStackTrace();
                }
        }

        public static void removeByOrg(int orgID, int ColSignDetailID)
        {
                UserDAOImpl userDAOImpl = new UserDAOImpl();
                List studentList = new ArrayList();

                try
                {
                        studentList = userDAOImpl.getUserList(orgID + "");

                        System.out.println(
                                "studentList size ==============================" +
                                        studentList.size());

                        ColStudentForm csf = new ColStudentForm();
                        int type = SecurityConstants.USER_DEFAULT_RELATION;
                        int userID = 0;

                        for (int i = 0; i < studentList.size(); i++)
                        {
                                userID = ((UserForm) studentList.get(i)).getUserID();
                                System.out.println("userID ============" + userID);
                                colStudentDAO.removeColStudent(ColSignDetailID, userID, type);
                        }
                }
                catch (UserDAOSysException ee)
                {
                        ee.printStackTrace();
                }
                catch (ColStudentDAOSysException udse)
                {
                        udse.printStackTrace();
                }
        }

        public static List getStuOfOrg(int orgID, int ColSignDetailID)
        {
                List list = new ArrayList();

                try
                {
                        int type = SecurityConstants.USER_DEFAULT_RELATION;

                        list = colStudentDAO.getStuOfOrg(orgID, ColSignDetailID, type);
                }
                catch (ColStudentDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return list;
        }

        /**
         * 若不存在返回null.,
         *
         * @param csf
         * @return
         * @throws ColStudentDAOSysException
         */
        public static ColStudentForm getColStudent(int colSignDetailID,
                                                   int relationID, int type)
        {
                ColStudentForm csf = null;

                try
                {
                        csf = colStudentDAO.getColStudent(colSignDetailID, relationID, type);
                }
                catch (ColStudentDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return csf;
        }

        /**
         * 若不存在返回null.,
         *
         * @param csf
         * @return
         * @throws ColStudentDAOSysException
         */
        public static List getColStudentList(int userID, int relationID, int type)
        {
                List l = null;

                try
                {
                        l = colStudentDAO.getColStudentList(userID, relationID, type);
                }
                catch (ColStudentDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return l;
        }

        public static List getColStudentList(int ColSignDetailID, int Type)
        {
                List list = new ArrayList();

                try
                {
                        list = colStudentDAO.getColStudentList(ColSignDetailID, Type);
                }
                catch (ColStudentDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return list;
        }

        public static void updateColStudent(ColStudentForm csf)
        {
                try
                {
                        colStudentDAO.updateColStudent(csf);
                }
                catch (ColStudentDAOSysException udse)
                {
                        udse.printStackTrace();
                }
        }
}
