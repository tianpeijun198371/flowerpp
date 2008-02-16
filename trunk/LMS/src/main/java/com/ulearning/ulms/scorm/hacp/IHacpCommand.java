/**
 * Copyright (c)2006 Beijing WenhuaOnline Sci-tech DevelopmentCo.,Ltd.
 * All rights reserved.
 *
 * User: fengch
 * Date: 2007-9-28 11:46:34
 */
package com.ulearning.ulms.scorm.hacp;

/**
 * interface for the hacp command.
 *
 */

import java.io.*;

import java.lang.*;

import javax.servlet.*;


public interface IHacpCommand
{
        public void execute() throws IOException, ServletException;
}
