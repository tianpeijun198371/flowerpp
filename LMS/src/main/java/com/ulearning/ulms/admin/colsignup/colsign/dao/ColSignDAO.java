/**
 * Created by IntelliJ IDEA.
 * User: zengwj
 * Date: 2004-8-20
 * Time: 10:08:09
 * To change this template use File | Settings | File Templates.
 */
package com.ulearning.ulms.admin.colsignup.colsign.dao;

import com.ulearning.ulms.admin.colsignup.colsign.exceptions.ColSignDAOSysException;
import com.ulearning.ulms.admin.colsignup.colsign.form.ColSignForm;

import java.util.List;


public interface ColSignDAO
{
        public int addColSign(ColSignForm details) throws ColSignDAOSysException;

        public void updateColSign(ColSignForm details)
                throws ColSignDAOSysException;

        public void removeColSign(int ColSignID) throws ColSignDAOSysException;

        public ColSignForm getColSign(int ColSign) throws ColSignDAOSysException;

        public List getColSignList(int orgID, int Published)
                throws ColSignDAOSysException;

        public void submitColSign(int ColSignID, String isSubmit)
                throws ColSignDAOSysException;

        public int totalNumInColSign(int ColSignID, int approved)
                throws ColSignDAOSysException;

        public int totalNumInColSignByFee(int colSignID, int feeState)
                throws ColSignDAOSysException;

        public void approvedColSign(int ColSignID, String approved)
                throws ColSignDAOSysException;

        public int totalNumInOrg(int orgID, int approved)
                throws ColSignDAOSysException;
}
