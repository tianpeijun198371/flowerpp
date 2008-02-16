package com.ulearning.ulms.familyeducation.exception;

import com.ulearning.ulms.core.exceptions.ULMSSysException;


public class FamilyEducationSysException extends ULMSSysException
{
        /**
         * Creates new <code>ULMSAppException</code> without detail message.
         */
        public FamilyEducationSysException()
        {
                super();
        }

        /**
         * Constructs an <code>ContentAppException</code> with the specified detail message.
         *
         * @param msg the detail message.
         */
        public FamilyEducationSysException(String msg)
        {
                super(msg);
        }

        /**
         * Constructs an <code>ContentAppException</code> with the specified detail message and nested Exception.
         *
         * @param msg the detail message.
         */
        public FamilyEducationSysException(String msg, Throwable nested)
        {
                super(msg, nested);
        }

        /**
         * Constructs an <code>ContentAppException</code> with the specified detail message and nested Exception.
         *
         * @param nested the detail message.
         */
        public FamilyEducationSysException(Throwable nested)
        {
                super(nested);
        }
}
