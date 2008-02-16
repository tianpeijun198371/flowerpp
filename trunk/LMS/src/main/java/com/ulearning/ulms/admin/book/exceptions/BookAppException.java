/**
 * Created by IntelliJ IDEA.
 * Book: dengj
 * Date: Apr 8, 2004
 * Time: 9:17:53 AM
 * To change this template use Options | File Templates.
 */
package com.ulearning.ulms.admin.book.exceptions;

import com.ulearning.ulms.core.exceptions.ULMSAppException;


public class BookAppException extends ULMSAppException
{
        /**
         * Constructor
         *
         * @param str a string that explains what the exception condition is
         */
        public BookAppException(String str)
        {
                super(str);
        }

        /**
         * Default constructor. Takes no arguments
         */
        public BookAppException()
        {
                super();
        }
}
