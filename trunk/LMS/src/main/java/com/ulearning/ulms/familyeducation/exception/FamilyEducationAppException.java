package com.ulearning.ulms.familyeducation.exception;

import com.ulearning.ulms.core.exceptions.ULMSAppException;


public class FamilyEducationAppException extends ULMSAppException
{
        /**
         * Creates new <code>ULMSAppException</code> without detail message.
         */
        public FamilyEducationAppException()
        {
                super();
        }

        /**
         * Constructs an <code>ContentAppException</code> with the specified detail message.
         *
         * @param msg the detail message.
         */
        public FamilyEducationAppException(String msg)
        {
                super(msg);
        }

        /**
         * Constructs an <code>ContentAppException</code> with the specified detail message and nested Exception.
         *
         * @param msg the detail message.
         */
        public FamilyEducationAppException(String msg, Throwable nested)
        {
                super(msg, nested);
        }

        /**
         * Constructs an <code>ContentAppException</code> with the specified detail message and nested Exception.
         *
         * @param nested the detail message.
         */
        public FamilyEducationAppException(Throwable nested)
        {
                super(nested);
        }
}
