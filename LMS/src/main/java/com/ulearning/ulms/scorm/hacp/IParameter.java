/**
 * Copyright (c)2006 Beijing WenhuaOnline Sci-tech DevelopmentCo.,Ltd.
 * All rights reserved.
 *
 * User: fengch
 * Date: 2007-9-28 11:46:34
 */
package com.ulearning.ulms.scorm.hacp;

/**
 * interface for the parameter.
 *
 */

import java.lang.*;


public interface IParameter
{
        public String getParameter(String pName);

        public String getPairs();

        public int getCount();

        public void print();

        public String getName(int i);
}
