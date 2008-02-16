/** * DocContentHelper.java.
 * DocContent: xiejh  Date: 2004-4-22 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.doc.doccontent.bean;

import com.ulearning.ulms.tools.doc.doccontent.dao.DocContentDAO;
import com.ulearning.ulms.tools.doc.doccontent.dao.DocContentDAOFactory;
import com.ulearning.ulms.tools.doc.doccontent.exceptions.DocContentDAOSysException;
import com.ulearning.ulms.tools.doc.doccontent.form.DocContentForm;

import java.util.List;


public class DocContentHelper
{
        /**
         * Wrapping the get doccontent method for JSP and  the other modules
         *
         * @param docID
         * @return the doccontent modle according to the docID
         */
        public DocContentForm getDocContent(int docID)
        {
                DocContentForm dcf = null;

                try
                {
                        DocContentDAO doccontentDao = DocContentDAOFactory.getDAO();
                        dcf = doccontentDao.getDocContent(docID);
                }
                catch (DocContentDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return dcf;
        }

        /**
         * Wrapping the get doccontentList method for JSP and  the other modules
         *
         * @param orgID
         * @return the doccontent list according to the catalog
         */
        public List getDocContentList(int orgID)
        {
                List doccontentList = null;

                try
                {
                        DocContentDAO doccontentDao = DocContentDAOFactory.getDAO();
                        doccontentList = doccontentDao.getDocContentList(orgID);
                }
                catch (DocContentDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return doccontentList;
        }
}
