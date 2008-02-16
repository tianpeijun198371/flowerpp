/**
 * Created by IntelliJ IDEA.
 * Backup: xiejh
 * Date: Apr 8, 2004
 * Time: 9:16:14 AM
 * To change this template use Options | File Templates.
 */
package com.ulearning.ulms.tools.backup.exceptions;

import com.ulearning.ulms.core.exceptions.ULMSSysException;


public class BackupDAOSysException extends ULMSSysException
{
        /**
         * Constructor
         *
         * @param str a string that explains what the exception condition is
         */
        public BackupDAOSysException(String str)
        {
                super(str);
        }

        /**
         * Default constructor. Takes no arguments
         */
        public BackupDAOSysException()
        {
                super();
        }
}
