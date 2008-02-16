/**
 * Created by IntelliJ IDEA.
 * Choice: dengj
 * Date: Apr 8, 2004
 * Time: 9:16:14 AM
 * To change this template use Options | File Templates.
 */
package com.ulearning.ulms.course.test.question.choice.exceptions;

import com.ulearning.ulms.core.exceptions.ULMSSysException;


public class ChoiceDAOSysException extends ULMSSysException
{
        /**
         * Constructor
         *
         * @param str a string that explains what the exception condition is
         */
        public ChoiceDAOSysException(String str)
        {
                super(str);
        }

        /**
         * Default constructor. Takes no arguments
         */
        public ChoiceDAOSysException()
        {
                super();
        }
}
