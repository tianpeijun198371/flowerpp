/** * AssignProcessHelper.java.
 * AssignProcess: xiejh  Date: 2004-4-22 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.assignment.assignprocess.bean;

import com.ulearning.ulms.core.util.StringUtil;
import com.ulearning.ulms.tools.assignment.assignprocess.dao.AssignProcessDAO;
import com.ulearning.ulms.tools.assignment.assignprocess.dao.AssignProcessDAOFactory;
import com.ulearning.ulms.tools.assignment.assignprocess.exceptions.AssignProcessDAOSysException;
import com.ulearning.ulms.tools.assignment.assignprocess.form.AssignProcessForm;

import java.util.Date;
import java.util.List;


public class AssignProcessHelper
{
        public String strTree = "";
        public int level = 0;

        public void addAssignProcess(AssignProcessForm details)
        {
                AssignProcessForm bf = null;

                try
                {
                        AssignProcessDAO assignmentDao = AssignProcessDAOFactory.getDAO();
                        assignmentDao.addAssignProcess(details);
                }
                catch (AssignProcessDAOSysException udse)
                {
                        udse.printStackTrace();
                }
        }

        public void addAssignProcess(int RelationID, String RelationType,
                                     String State, int UserID)
        {
                AssignProcessForm details = new AssignProcessForm();
                details.setRelationID(RelationID);
                details.setRelationType(RelationType);
                details.setState(State);
                details.setUserID(UserID);
                details.setAccess_datetime(new Date());
                addAssignProcess(details);
        }

        /**
         * Wrapping the get assignment method for JSP and  the other modules
         *
         * @param assignProcessID
         * @return the assignment modle according to the assignProcessID
         */
        public AssignProcessForm getAssignProcess(int assignProcessID)
        {
                AssignProcessForm bf = null;

                try
                {
                        AssignProcessDAO assignmentDao = AssignProcessDAOFactory.getDAO();
                        bf = assignmentDao.getAssignProcess(assignProcessID);
                }
                catch (AssignProcessDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return bf;
        }

        public AssignProcessForm getAssignProcess(int relationID,
                                                  String relationType, int UserID)
        {
                AssignProcessForm bf = null;

                try
                {
                        AssignProcessDAO assignmentDao = AssignProcessDAOFactory.getDAO();
                        bf = assignmentDao.getAssignProcess(relationID, relationType, UserID);
                }
                catch (AssignProcessDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return bf;
        }

        /**
         * Wrapping the get assignmentList method for JSP and  the other modules
         *
         * @param relationID , relationType
         * @return the assignment list according to the rootID
         */
        public List getAssignProcessList(int relationID, String relationType)
        {
                List assignmentList = null;

                try
                {
                        AssignProcessDAO assignmentDao = AssignProcessDAOFactory.getDAO();
                        assignmentList = assignmentDao.getAssignProcessList(relationID,
                                relationType);
                }
                catch (AssignProcessDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return assignmentList;
        }

        public static void main(String[] args) throws Exception
        {
                AssignProcessHelper kk = new AssignProcessHelper();
                AssignProcessForm nn = kk.getAssignProcess(121, "1", 368);
                System.out.println(nn);
        }
}
