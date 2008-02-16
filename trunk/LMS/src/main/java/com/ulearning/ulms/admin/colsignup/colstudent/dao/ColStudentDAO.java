/**
 * Created by IntelliJ IDEA.
 * User: zengwj
 * Date: 2004-8-20
 * Time: 10:08:09
 * To change this template use File | Settings | File Templates.
 */
package com.ulearning.ulms.admin.colsignup.colstudent.dao;

import com.ulearning.ulms.admin.colsignup.colstudent.exceptions.ColStudentDAOSysException;
import com.ulearning.ulms.admin.colsignup.colstudent.form.ColStudentForm;

import java.util.List;


public interface ColStudentDAO
{
        public void addColStudent(ColStudentForm csf)
                throws ColStudentDAOSysException;

        public void updateColStudent(ColStudentForm csf)
                throws ColStudentDAOSysException;

        public void removeColStudent(int ColSignDetailID, int relationID, int type)
                throws ColStudentDAOSysException;

        public List getColStudentList(int ColSignDetailID, int Type)
                throws ColStudentDAOSysException;

        public int getStudentNumber(int orgID, int ColSignDetailID, int Type)
                throws ColStudentDAOSysException;

        /**
         * »Ù≤ª¥Ê‘⁄∑µªÿnull.,
         *
         * @param csf
         * @return
         * @throws ColStudentDAOSysException
         */
        public ColStudentForm getColStudent(int colSignDetailID, int relationID,
                                            int type) throws ColStudentDAOSysException;

        public List getColStudentList(int userID, int relationID, int type)
                throws ColStudentDAOSysException;

        public List getStuOfOrg(int orgID, int ColSignDetailID, int type)
                throws ColStudentDAOSysException;
}
