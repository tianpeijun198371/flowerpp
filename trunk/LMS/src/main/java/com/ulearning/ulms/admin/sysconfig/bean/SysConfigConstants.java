/**
 * SysConfigConstants.java.
 * User: dengj  Date: 2004-8-19
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.admin.sysconfig.bean;

public interface SysConfigConstants
{
        // ******************  Actions of Auto Inform ******************
        /**
         * Calender event operation
         */
        public static final int SYS_CONFIG_CALENDER_EVENT = 1;

        /**
         * The success operation of apply a course
         */
        public static final int SYS_CONFIG_APPLY_COURSE_SUC_EVENT = 2;

        /**
         * The failure operation of apply a course
         */
        public static final int SYS_CONFIG_APPLY_COURSE_FAIL_EVENT = 3;

        /**
         * Data backup operation
         */
        public static final int SYS_CONFIG_DATA_BAK_FINISH_EVENT = 4;

        /**
         * Report finish operation
         */
        public static final int SYS_CONFIG_REPORT_FINISH_EVENT = 5;
}
