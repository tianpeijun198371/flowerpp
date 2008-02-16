/** * AssignProcessDAO.java.
 * User: xiejh  Date: 2004-4-22 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.assignment.assignprocess.dao;

import com.ulearning.ulms.tools.assignment.assignprocess.exceptions.AssignProcessDAOSysException;
import com.ulearning.ulms.tools.assignment.assignprocess.form.AssignProcessForm;

import java.util.List;


public interface AssignProcessDAO
{
        public int addAssignProcess(AssignProcessForm details)
                throws AssignProcessDAOSysException;

        public void updateAssignProcess(AssignProcessForm details)
                throws AssignProcessDAOSysException;

        public void removeAssignProcess(String assignProcessID)
                throws AssignProcessDAOSysException;

        public AssignProcessForm getAssignProcess(int assignProcessID)
                throws AssignProcessDAOSysException;

        public AssignProcessForm getAssignProcess(int relationID,
                                                  String relationType, int UserID) throws AssignProcessDAOSysException;

        public List getAssignProcessList(int relationID, String relationType)
                throws AssignProcessDAOSysException;

        //public List getAssignProcessUserList(int relationID ,String relationType) throws AssignProcessDAOSysException;
}
