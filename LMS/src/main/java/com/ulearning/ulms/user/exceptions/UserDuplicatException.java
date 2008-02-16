/**
 * UserDuplicatException.java.
 * User: dengj  Date: 2004-5-25
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.user.exceptions;

public class UserDuplicatException extends UserAppException
{
        /**
         * Constructor
         *
         * @param str a string that explains what the exception condition is
         */
        public UserDuplicatException(String str)
        {
                super(str);
        }

        /**
         * Default constructor. Takes no arguments
         */
        public UserDuplicatException()
        {
                super();
        }
}
