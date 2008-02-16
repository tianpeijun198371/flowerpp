/**
 * Created by IntelliJ IDEA.
 * Book: dengj
 * Date: Apr 8, 2004
 * Time: 9:16:14 AM
 * To change this template use Options | File Templates.
 */
package com.ulearning.ulms.admin.book.exceptions;

import com.ulearning.ulms.core.exceptions.ULMSSysException;


public class BookDAOSysException extends ULMSSysException
{
        /**
         * Constructor
         *
         * @param str a string that explains what the exception condition is
         */
        public BookDAOSysException(String str)
        {
                super(str);
        }

        /**
         * Default constructor. Takes no arguments
         */
        public BookDAOSysException()
        {
                super();
        }
}
