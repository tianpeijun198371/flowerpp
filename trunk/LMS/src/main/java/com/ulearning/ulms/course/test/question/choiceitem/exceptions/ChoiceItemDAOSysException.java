/**
 * Created by IntelliJ IDEA.
 * ChoiceItem: dengj
 * Date: Apr 8, 2004
 * Time: 9:16:14 AM
 * To change this template use Options | File Templates.
 */
package com.ulearning.ulms.course.test.question.choiceitem.exceptions;

import com.ulearning.ulms.core.exceptions.ULMSSysException;


public class ChoiceItemDAOSysException extends ULMSSysException
{
        /**
         * Constructor
         *
         * @param str a string that explains what the exception condition is
         */
        public ChoiceItemDAOSysException(String str)
        {
                super(str);
        }

        /**
         * Default constructor. Takes no arguments
         */
        public ChoiceItemDAOSysException()
        {
                super();
        }
}
