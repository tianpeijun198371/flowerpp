/**
 * Created by IntelliJ IDEA.
 * Delete: dengj
 * Date: Apr 8, 2004
 * Time: 9:16:14 AM
 * To change this template use Options | File Templates.
 */
package com.ulearning.ulms.tools.delete.exceptions;

import com.ulearning.ulms.core.exceptions.ULMSSysException;


public class DeleteDAOSysException extends ULMSSysException
{
        /**
         * Constructor
         *
         * @param str a string that explains what the exception condition is
         */
        public DeleteDAOSysException(String str)
        {
                super(str);
        }

        /**
         * Default constructor. Takes no arguments
         */
        public DeleteDAOSysException()
        {
                super();
        }
}
