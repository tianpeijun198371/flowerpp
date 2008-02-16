/**
 * NewDocumentAppException.java.
 * User: Administrator  Date: 2005-3-7
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.newdocument.exceptions;

import com.ulearning.ulms.core.exceptions.ULMSAppException;

public class NewDocumentAppException extends ULMSAppException
{
        public NewDocumentAppException()
        {
        }

        public NewDocumentAppException(Throwable nested)
        {
                super(nested);
        }

        public NewDocumentAppException(String msg, Throwable nested)
        {
                super(msg, nested);
        }

        public NewDocumentAppException(String msg)
        {
                super(msg);
        }


}
