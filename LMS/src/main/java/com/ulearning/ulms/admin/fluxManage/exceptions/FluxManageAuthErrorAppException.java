/**
 * FluxManageAuthErrorAppException.java.
 * User: fengch  Date: 2004-11-25
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.admin.fluxManage.exceptions;

import com.ulearning.ulms.core.exceptions.ULMSAppException;


public class FluxManageAuthErrorAppException extends FluxManageAppException
{
        /**
         * Creates new <code>ULMSAppException</code> without detail message.
         */
        public FluxManageAuthErrorAppException()
        {
                super();
        }

        /**
         * Constructs an <code>FluxManageAuthErrorAppException</code> with the specified detail message.
         *
         * @param msg the detail message.
         */
        public FluxManageAuthErrorAppException(String msg)
        {
                super(msg);
        }

        /**
         * Constructs an <code>FluxManageAuthErrorAppException</code> with the specified detail message and nested Exception.
         *
         * @param msg the detail message.
         */
        public FluxManageAuthErrorAppException(String msg, Throwable nested)
        {
                super(msg, nested);
        }

        /**
         * Constructs an <code>FluxManageAuthErrorAppException</code> with the specified detail message and nested Exception.
         *
         * @param nested the detail message.
         */
        public FluxManageAuthErrorAppException(Throwable nested)
        {
                super(nested);
        }
}
