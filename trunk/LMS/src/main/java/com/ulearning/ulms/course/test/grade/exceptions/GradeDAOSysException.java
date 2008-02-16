/**
 * Created by IntelliJ IDEA.
 * Grade: dengj
 * Date: Apr 8, 2004
 * Time: 9:16:14 AM
 * To change this template use Options | File Templates.
 */
package com.ulearning.ulms.course.test.grade.exceptions;

import com.ulearning.ulms.core.exceptions.ULMSSysException;


public class GradeDAOSysException extends ULMSSysException
{
        /**
         * Constructor
         *
         * @param str a string that explains what the exception condition is
         */
        public GradeDAOSysException(String str)
        {
                super(str);
        }

        /**
         * Default constructor. Takes no arguments
         */
        public GradeDAOSysException()
        {
                super();
        }
}
