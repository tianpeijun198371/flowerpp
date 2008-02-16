/**
 * ConfigDAO.java.
 * User: fengch  Date: 2004-5-13
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.core.config;

import java.util.HashMap;


public interface ConfigDAO
{
        public HashMap loadulmsConfigMappings();

        public HashMap loadSYSConfigMappings();
}
