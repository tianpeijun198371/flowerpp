/**
 * CatalogIsExisitException.java.
 * User: fengch  Date: 2004-6-1
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.course.exceptions;

import com.ulearning.ulms.core.exceptions.ULMSAppException;


public class CatalogIsExisitException extends CourseAppException
{
        /**
         * Creates new <code>CatalogIsExisitException</code> without detail message.
         */
        public CatalogIsExisitException()
        {
                super();
        }

        /**
         * Constructs an <code>CatalogIsExisitException</code> with the specified detail message.
         *
         * @param msg the detail message.
         */
        public CatalogIsExisitException(String msg)
        {
                super(msg);
        }

        /**
         * Constructs an <code>CatalogIsExisitException</code> with the specified detail message and nested Exception.
         *
         * @param msg the detail message.
         */
        public CatalogIsExisitException(String msg, Throwable nested)
        {
                super(msg, nested);
        }

        /**
         * Constructs an <code>CatalogIsExisitException</code> with the specified detail message and nested Exception.
         *
         * @param nested the detail message.
         */
        public CatalogIsExisitException(Throwable nested)
        {
                super(nested);
        }
}
