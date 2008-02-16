/** * AssignmentDAO.java.
 * User: xiejh  Date: 2004-4-22 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.assignment.dao;

import com.ulearning.ulms.core.util.PagerList;
import com.ulearning.ulms.course.exceptions.CourseDAOSysException;
import com.ulearning.ulms.tools.assignment.exceptions.AssignmentDAOSysException;
import com.ulearning.ulms.tools.assignment.form.AssignmentForm;

import java.io.Serializable;

import java.util.List;


public interface AssignmentDAO
{
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
        public PagerList getAllAssignmentsByUser(int userID, int pageNo,
                                                 int pageSize) throws CourseDAOSysException;

        public int addAssignment(AssignmentForm details)
                throws AssignmentDAOSysException;

        public void updateAssignment(AssignmentForm details)
                throws AssignmentDAOSysException;

        public void removeAssignment(String assignmentID)
                throws AssignmentDAOSysException;

        public void removeAssignmentRoot(String rootID)
                throws AssignmentDAOSysException;

        public AssignmentForm getAssignment(int assignmentID)
                throws AssignmentDAOSysException;

        public List getAssignmentList(int rootID) throws AssignmentDAOSysException;

        public List getAssignmentList(int courseID, boolean isAdmin)
                throws AssignmentDAOSysException;

        public List getRootAssignmentList(int CourseID)
                throws AssignmentDAOSysException;

        public AssignmentForm getLastAssignment(AssignmentForm details)
                throws AssignmentDAOSysException;

        public void updateAssignmentViewable(String assignmentID, String viewable)
                throws AssignmentDAOSysException;

        public void updateAssignmentAvailable(String assignmentID, String available)
                throws AssignmentDAOSysException;

        public List getNewTrainInfo(int courseID, int rootID, int type,
                                    boolean isAdmin) throws AssignmentDAOSysException;
}
