/**
 * Created by IntelliJ IDEA.
 * User: dengj
 * Date: Apr 8, 2004
 * Time: 9:16:14 AM
 * To change this template use Options | File Templates.
 */
package com.ulearning.ulms.tools.visit.exceptions;

import com.ulearning.ulms.core.exceptions.ULMSSysException;


public class VisitDAOSysException extends ULMSSysException
{

        /**
         * Constructor
         *
         * @param str a string that explains what the exception condition is
         */
        public VisitDAOSysException(String str)
        {
                super(str);
        }

        /**
         * Default constructor. Takes no arguments
         */
        public VisitDAOSysException()
        {
                super();
        }

}
