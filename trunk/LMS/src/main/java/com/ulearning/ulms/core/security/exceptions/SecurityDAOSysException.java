/**
 * Created by IntelliJ IDEA.
 * Security: dengj
 * Date: Apr 8, 2004
 * Time: 9:16:14 AM
 * To change this template use Options | File Templates.
 */
package com.ulearning.ulms.core.security.exceptions;

import com.ulearning.ulms.core.exceptions.ULMSSysException;


public class SecurityDAOSysException extends ULMSSysException
{
        /**
         * Constructor
         *
         * @param str a string that explains what the exception condition is
         */
        public SecurityDAOSysException(String str)
        {
                super(str);
        }

        /**
         * Default constructor. Takes no arguments
         */
        public SecurityDAOSysException()
        {
                super();
        }

        /**
         * Constructs an <code>SecurityDAOSysException</code> with the specified detail message and nested Exception.
         *
         * @param msg the detail message.
         */
        public SecurityDAOSysException(String msg, Throwable nested)
        {
                super(msg, nested);
        }

        /**
         * Constructs an <code>SecurityDAOSysException</code> with the specified detail message and nested Exception.
         *
         * @param nested the detail message.
         */
        public SecurityDAOSysException(Throwable nested)
        {
                super(nested);
        }
}
