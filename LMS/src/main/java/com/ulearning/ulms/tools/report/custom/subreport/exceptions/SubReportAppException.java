/**
 * Created by IntelliJ IDEA.
 * SubReport: dengj
 * Date: Apr 8, 2004
 * Time: 9:17:53 AM
 * To change this template use Options | File Templates.
 */

package com.ulearning.ulms.tools.report.custom.subreport.exceptions;

import com.ulearning.ulms.core.exceptions.ULMSAppException;


public class SubReportAppException extends ULMSAppException
{

        /**
         * Constructor
         *
         * @param str a string that explains what the exception condition is
         */
        public SubReportAppException(String str)
        {
                super(str);
        }

        /**
         * Default constructor. Takes no arguments
         */
        public SubReportAppException()
        {
                super();
        }

}
