/**
 * SendMailException.java.
 * User: fengch  Date: 2004-7-20
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.core.mail;

import com.ulearning.ulms.core.exceptions.ULMSSysException;


public class SendMailException extends ULMSSysException
{
        /**
         * Creates new <code>ULMSAppException</code> without detail message.
         */
        public SendMailException()
        {
                super();
        }

        /**
         * Constructs an <code>SendMailException</code> with the specified detail message.
         *
         * @param msg the detail message.
         */
        public SendMailException(String msg)
        {
                super(msg);
        }

        /**
         * Constructs an <code>SendMailException</code> with the specified detail message and nested Exception.
         *
         * @param msg the detail message.
         */
        public SendMailException(String msg, Throwable nested)
        {
                super(msg, nested);
        }

        /**
         * Constructs an <code>SendMailException</code> with the specified detail message and nested Exception.
         *
         * @param nested the detail message.
         */
        public SendMailException(Throwable nested)
        {
                super(nested);
        }
}
