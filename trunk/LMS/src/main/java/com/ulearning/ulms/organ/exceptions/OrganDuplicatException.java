/**
 * OrganDuplicatException
 * User: huangsb
 * Date: Apr 13, 2006
 * Time: 11:15:32
 * Copyright (c) 2006-2007.Beijing WenhuaOnline Sci-tech DevelopmentCo.,Ltd.
 */
package com.ulearning.ulms.organ.exceptions;

public class OrganDuplicatException extends Exception
{
        /**
         * Constructor
         *
         * @param str a string that explains what the exception condition is
         */
        public OrganDuplicatException(String str)
        {
                super(str);
        }

        /**
         * Default constructor. Takes no arguments
         */
        public OrganDuplicatException()
        {
                super();
        }
}
