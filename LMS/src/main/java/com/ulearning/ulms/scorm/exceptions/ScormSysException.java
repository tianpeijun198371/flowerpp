/**
 * ScormSysException.java.
 * User: fengch  Date: 2004-7-13
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.scorm.exceptions;

import com.ulearning.ulms.core.exceptions.ULMSSysException;


public class ScormSysException extends ULMSSysException
{
        /**
         * Creates new <code>ULMSAppException</code> without detail message.
         */
        public ScormSysException()
        {
                super();
        }

        /**
         * Constructs an <code>ScormSysException</code> with the specified detail message.
         *
         * @param msg the detail message.
         */
        public ScormSysException(String msg)
        {
                super(msg);
        }

        /**
         * Constructs an <code>ScormSysException</code> with the specified detail message and nested Exception.
         *
         * @param msg the detail message.
         */
        public ScormSysException(String msg, Throwable nested)
        {
                super(msg, nested);
        }

        /**
         * Constructs an <code>ScormSysException</code> with the specified detail message and nested Exception.
         *
         * @param nested the detail message.
         */
        public ScormSysException(Throwable nested)
        {
                super(nested);
        }
}
