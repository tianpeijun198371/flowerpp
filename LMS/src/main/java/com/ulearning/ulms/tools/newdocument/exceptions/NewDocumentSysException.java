/**
 * NewDocumentSysException.java.
 * User: Administrator  Date: 2005-3-7
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.newdocument.exceptions;

import com.ulearning.ulms.core.exceptions.ULMSSysException;

public class NewDocumentSysException extends ULMSSysException
{
        public NewDocumentSysException()
        {
        }

        public NewDocumentSysException(String msg)
        {
                super(msg);
        }

        public NewDocumentSysException(String msg, Throwable nested)
        {
                super(msg, nested);
        }

        public NewDocumentSysException(Throwable nested)
        {
                super(nested);
        }
}
