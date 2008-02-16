/**
 * Created by IntelliJ IDEA.
 * Backup: xiejh
 * Date: Apr 8, 2004
 * Time: 9:17:53 AM
 * To change this template use Options | File Templates.
 */
package com.ulearning.ulms.tools.backup.exceptions;

import com.ulearning.ulms.core.exceptions.ULMSAppException;


public class BackupAppException extends ULMSAppException
{
        /**
         * Constructor
         *
         * @param str a string that explains what the exception condition is
         */
        public BackupAppException(String str)
        {
                super(str);
        }

        /**
         * Default constructor. Takes no arguments
         */
        public BackupAppException()
        {
                super();
        }
}
