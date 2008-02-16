/**
 * ScormAppException.java.
 * User: fengch  Date: 2004-7-13
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.scorm.exceptions;

import com.ulearning.ulms.core.exceptions.ULMSAppException;


public class ScormAppException extends ULMSAppException
{
        /**
         * Creates new <code>ULMSAppException</code> without detail message.
         */
        public ScormAppException()
        {
                super();
        }

        /**
         * Constructs an <code>ScormAppException</code> with the specified detail message.
         *
         * @param msg the detail message.
         */
        public ScormAppException(String msg)
        {
                super(msg);
        }

        /**
         * Constructs an <code>ScormAppException</code> with the specified detail message and nested Exception.
         *
         * @param msg the detail message.
         */
        public ScormAppException(String msg, Throwable nested)
        {
                super(msg, nested);
        }

        /**
         * Constructs an <code>ScormAppException</code> with the specified detail message and nested Exception.
         *
         * @param nested the detail message.
         */
        public ScormAppException(Throwable nested)
        {
                super(nested);
        }
}
