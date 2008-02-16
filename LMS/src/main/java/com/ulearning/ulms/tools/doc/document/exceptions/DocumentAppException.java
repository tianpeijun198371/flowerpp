/** * DocumentForm.java.
 * User: xiejh  Date: 2004-4-23 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.doc.document.exceptions;

public class DocumentAppException extends java.lang.Exception
{
        /**
         * Constructor
         *
         * @param str a string that explains what the exception condition is
         */
        public DocumentAppException(String str)
        {
                super(str);
        }

        /**
         * Default constructor. Takes no arguments
         */
        public DocumentAppException()
        {
                super();
        }
}
