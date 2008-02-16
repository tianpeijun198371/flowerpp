/**
 * FluxManageSysException.java.
 * User: fengch  Date: 2004-6-23
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.admin.fluxManage.exceptions;

import com.ulearning.ulms.core.exceptions.ULMSSysException;


public class FluxManageSysException extends ULMSSysException
{
        /**
         * Creates new <code>ULMSAppException</code> without detail message.
         */
        public FluxManageSysException()
        {
                super();
        }

        /**
         * Constructs an <code>FluxManageSysException</code> with the specified detail message.
         *
         * @param msg the detail message.
         */
        public FluxManageSysException(String msg)
        {
                super(msg);
        }

        /**
         * Constructs an <code>FluxManageSysException</code> with the specified detail message and nested Exception.
         *
         * @param msg the detail message.
         */
        public FluxManageSysException(String msg, Throwable nested)
        {
                super(msg, nested);
        }

        /**
         * Constructs an <code>FluxManageSysException</code> with the specified detail message and nested Exception.
         *
         * @param nested the detail message.
         */
        public FluxManageSysException(Throwable nested)
        {
                super(nested);
        }
}
