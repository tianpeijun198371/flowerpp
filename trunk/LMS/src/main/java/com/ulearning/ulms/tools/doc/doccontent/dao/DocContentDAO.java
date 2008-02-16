/** * DocumentAction.java.
 * User: xiejh  Date: 2004-4-22 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.doc.doccontent.dao;

import com.ulearning.ulms.tools.doc.doccontent.exceptions.DocContentDAOSysException;
import com.ulearning.ulms.tools.doc.doccontent.form.DocContentForm;

import java.util.List;


public interface DocContentDAO
{
        public void addDocContent(DocContentForm details)
                throws DocContentDAOSysException;

        public void updateDocContent(DocContentForm details)
                throws DocContentDAOSysException;

        public void removeDocContent(String docID) throws DocContentDAOSysException;

        public DocContentForm getDocContent(int docID)
                throws DocContentDAOSysException;

        public List getDocContentList(int docID) throws DocContentDAOSysException;
}
