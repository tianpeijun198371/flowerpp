/**
 * Created by IntelliJ IDEA.
 * User: dengj
 * Date: Apr 14, 2004
 * Time: 5:19:39 PM
 * To change this template use Options | File Templates.
 */
package com.ulearning.ulms.organ.exceptions;

import com.ulearning.ulms.core.exceptions.ULMSSysException;


public class OrganDAOSysException extends ULMSSysException
{
        /**
         * Constructor
         *
         * @param str a string that explains what the exception condition is
         */
        public OrganDAOSysException(String str)
        {
                super(str);
        }

        /**
         * Default constructor. Takes no arguments
         */
        public OrganDAOSysException()
        {
                super();
        }
}
