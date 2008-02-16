/**
 * FluxManageAppException.java.
 * User: fengch  Date: 2004-6-23
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.admin.fluxManage.exceptions;

import com.ulearning.ulms.core.exceptions.ULMSAppException;


public class FluxManageAppException extends ULMSAppException
{
        /**
         * Creates new <code>ULMSAppException</code> without detail message.
         */
        public FluxManageAppException()
        {
                super();
        }

        /**
         * Constructs an <code>FluxManageAppException</code> with the specified detail message.
         *
         * @param msg the detail message.
         */
        public FluxManageAppException(String msg)
        {
                super(msg);
        }

        /**
         * Constructs an <code>FluxManageAppException</code> with the specified detail message and nested Exception.
         *
         * @param msg the detail message.
         */
        public FluxManageAppException(String msg, Throwable nested)
        {
                super(msg, nested);
        }

        /**
         * Constructs an <code>FluxManageAppException</code> with the specified detail message and nested Exception.
         *
         * @param nested the detail message.
         */
        public FluxManageAppException(Throwable nested)
        {
                super(nested);
        }
}
