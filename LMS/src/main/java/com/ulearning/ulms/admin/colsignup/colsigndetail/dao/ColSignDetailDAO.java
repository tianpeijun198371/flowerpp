/**
 * Created by IntelliJ IDEA.
 * User: zengwj
 * Date: 2004-8-20
 * Time: 10:08:09
 * To change this template use File | Settings | File Templates.
 */
package com.ulearning.ulms.admin.colsignup.colsigndetail.dao;

import com.ulearning.ulms.admin.colsignup.colsigndetail.exceptions.ColSignDetailDAOSysException;
import com.ulearning.ulms.admin.colsignup.colsigndetail.form.ColSignDetailForm;

import java.util.List;


public interface ColSignDetailDAO
{
        public int addColSignDetail(ColSignDetailForm csdf)
                throws ColSignDetailDAOSysException;

        public void updateColSignDetail(ColSignDetailForm csdf)
                throws ColSignDetailDAOSysException;

        public void removeColSignDetail(int ColSignDetailID)
                throws ColSignDetailDAOSysException;

        public ColSignDetailForm getColSignDetail(int ColSignDetailID)
                throws ColSignDetailDAOSysException;

        public List getColSignDetailList(int ColSignID)
                throws ColSignDetailDAOSysException;

        public boolean isExist(int ColSignID, int RelationID, int Type)
                throws ColSignDetailDAOSysException;

        public int ColSignStudentNum(int ColSignDetailID, int approved)
                throws ColSignDetailDAOSysException;
}
