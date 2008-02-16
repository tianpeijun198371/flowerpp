//Source file: d:\\ulms\\source\\com\\eduedu\\ulms\\tools\\document\\webimpls\\DocumentWebImpl.java
package com.ulearning.ulms.tools.doc.document.webimpls;

import com.ulearning.ulms.tools.doc.document.dao.*;
import com.ulearning.ulms.tools.doc.document.exceptions.*;
import com.ulearning.ulms.tools.doc.document.model.*;

import java.io.Serializable;

import java.util.ArrayList;


public class DocumentWebImpl implements Serializable
{
        protected DocumentDAO dao;

        public DocumentWebImpl()
        {
        }

        /**
         * @param arg0
         * @param arg1
         * @param arg2
         * @param arg3
         * @param parentID
         * @param docType
         * @param relationID
         * @return java.util.ArrayList
         * @throws com.ulearning.ulms.tools.doc.document.exception.DocumentSysException
         *
         * @throws DocumentSysException
         * @throws com.ulearning.ulms.tools.doc.document.exceptions.DocumentSysException
         *
         */
        public java.util.ArrayList getDocuments(int parentID, int docType,
                                                int relationID) throws DocumentSysException
        {
                return null;
        }

        /**
         * @param arg0
         * @param docID
         * @return com.ulearning.ulms.tools.doc.document.model.DocumentModel
         * @throws com.ulearning.ulms.tools.doc.document.exception.DocumentSysException
         *
         * @throws DocumentSysException
         * @throws com.ulearning.ulms.tools.doc.document.exceptions.DocumentSysException
         *
         */
        public DocumentModel getDocument(int docID) throws DocumentSysException
        {
                return null;
        }

        /**
         * 获取某一个目录的整个目录路径
         *
         * @return java.util.ArrayList
         * @throws com.ulearning.ulms.tools.doc.document.exception.DocumentSysException
         *
         * @throws DocumentSysException
         * @throws com.ulearning.ulms.tools.doc.document.exceptions.DocumentSysException
         *
         */
        public java.util.ArrayList getCatalogPath() throws DocumentSysException
        {
                return null;
        }
}
