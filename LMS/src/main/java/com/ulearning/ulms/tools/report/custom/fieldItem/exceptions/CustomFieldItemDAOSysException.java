/**
 * Created by IntelliJ IDEA.
 * CustomFieldItem: dengj
 * Date: Apr 8, 2004
 * Time: 9:16:14 AM
 * To change this template use Options | File Templates.
 */
package com.ulearning.ulms.tools.report.custom.fieldItem.exceptions;

import com.ulearning.ulms.core.exceptions.ULMSSysException;


public class CustomFieldItemDAOSysException extends ULMSSysException
{

        /**
         * Constructor
         *
         * @param str a string that explains what the exception condition is
         */
        public CustomFieldItemDAOSysException(String str)
        {
                super(str);
        }

        /**
         * Default constructor. Takes no arguments
         */
        public CustomFieldItemDAOSysException()
        {
                super();
        }

}
