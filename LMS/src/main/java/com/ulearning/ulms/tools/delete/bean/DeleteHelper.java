/** * DeleteHelper.java.
 * Delete: xiejh  Date: 2004-4-22 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.delete.bean;

import com.ulearning.ulms.tools.delete.dao.DeleteDAO;
import com.ulearning.ulms.tools.delete.dao.DeleteDAOFactory;
import com.ulearning.ulms.tools.delete.exceptions.DeleteDAOSysException;
import com.ulearning.ulms.tools.delete.form.DeleteForm;

import java.util.Date;
import java.util.List;


public class DeleteHelper
{
        /**
         * Wrapping the get delete method for JSP and  the other modules
         *
         * @param deleteID
         * @return the delete modle according to the deleteID
         */
        public DeleteForm getDelete(int deleteID)
        {
                DeleteForm bf = null;

                try
                {
                        DeleteDAO deleteDao = DeleteDAOFactory.getDAO();
                        bf = deleteDao.getDelete(deleteID);
                }
                catch (DeleteDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return bf;
        }

        /**
         * Wrapping the get deleteList method for JSP and  the other modules
         *
         * @param relationType relationID
         * @return the delete list according to the catalog
         */
        public List getDeleteList(int relationID, String relationType)
        {
                List deleteList = null;

                try
                {
                        DeleteDAO deleteDao = DeleteDAOFactory.getDAO();
                        deleteList = deleteDao.getDeleteList(relationID, relationType);
                }
                catch (DeleteDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return deleteList;
        }

        public void deleteNoSave(String modelName, String timeFieldName,
                                 int saveMaxRows, Date saveAfterDate, String otherConditionItem)
                throws DeleteDAOSysException
        {
                try
                {
                        DeleteDAO deleteDao = DeleteDAOFactory.getDAO();
                        deleteDao.deleteNoSave(modelName, timeFieldName, saveMaxRows,
                                saveAfterDate, otherConditionItem);
                }
                catch (DeleteDAOSysException udse)
                {
                        udse.printStackTrace();
                }
        }
}
