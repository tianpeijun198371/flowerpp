/**
 * Created by IntelliJ IDEA.
 * Delete: dengj
 * Date: Apr 7, 2004
 * Time: 5:06:46 PM
 * To change this template use Options | File Templates.
 */
package com.ulearning.ulms.tools.delete.dao;

import com.ulearning.ulms.tools.delete.exceptions.DeleteDAOSysException;
import com.ulearning.ulms.tools.delete.form.DeleteForm;

import java.io.Serializable;

import java.util.Date;
import java.util.List;


public interface DeleteDAO
{
        public Serializable addDelete(DeleteForm details)
                throws DeleteDAOSysException;

        public void updateDelete(DeleteForm details) throws DeleteDAOSysException;

        public void removeDelete(String deleteID) throws DeleteDAOSysException;

        public DeleteForm getDelete(int deleteID) throws DeleteDAOSysException;

        public List getDeleteList(int relationID, String relationType)
                throws DeleteDAOSysException;

        public void deleteNoSave(String modelName, String timeFieldName,
                                 int saveMaxRows, Date saveAfterDate, String otherConditionItem)
                throws DeleteDAOSysException;
}
