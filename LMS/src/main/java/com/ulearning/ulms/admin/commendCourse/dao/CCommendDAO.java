/**
 * Created by IntelliJ IDEA.
 * author: houct
 * Date: 2005/04/08
 * Time: 10:58:23 AM
 * To change this template use Options | File Templates.
 */
package com.ulearning.ulms.admin.commendCourse.dao;

import com.ulearning.ulms.admin.commendCourse.exceptions.CCommendDAOSysException;
import com.ulearning.ulms.admin.commendCourse.form.CCommendForm;

import java.util.List;


public interface CCommendDAO
{
        public void addCCommend(CCommendForm details)
                throws CCommendDAOSysException;

        public void updateCCommend(CCommendForm details)
                throws CCommendDAOSysException;

        public void removeCCommend(int ccourseID) throws CCommendDAOSysException;

        public CCommendForm getCCommend(int ccourseID)
                throws CCommendDAOSysException;

        public List getCCommend_month(String displayed, int month)
                throws CCommendDAOSysException;
}
