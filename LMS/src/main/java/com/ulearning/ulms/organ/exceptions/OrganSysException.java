/**
 * Created by IntelliJ IDEA.
 * User: zengwj
 * Date: 2004-6-28
 * Time: 15:35:53
 * To change this template use File | Settings | File Templates.
 */
package com.ulearning.ulms.organ.exceptions;

import com.ulearning.ulms.core.exceptions.ULMSSysException;


public class OrganSysException extends ULMSSysException
{
        /**
         * Constructor
         *
         * @param str a string that explains what the exception condition is
         */
        public OrganSysException(String str)
        {
                super(str);
        }

        /**
         * Default constructor. Takes no arguments
         */
        public OrganSysException()
        {
                super();
        }
}
