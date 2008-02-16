/**
 * CourseKeys.java.
 * User: fengch  Date: 2004-4-25
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.course.util;

public interface CourseKeys
{
        /**
         * 新理念项目所需 师训课程
         */
        int COURSEID_FIRST=1;

        /**
         * catalog type
         */
        int CATALOG_MASTER_COURSE = 0;
        int CATALOG_MASTER_CERT = 3;
        int CATALOG_MASTER_OFFLINE_CERT = 4;
        int CATALOG_COURSE_COURSE = 1;
        int CATALOG_COURSE_CERT = 2;

        /*
          /*培训班中的各种类型
        */
        int CLASS_TYPE = 65;
        int CLASS_FEATURE = 66;
        int CLASS_LEVEL = 67;
        int CLASS_PATTERN = 68;

        /**
         * 教师资历
         */
        int TEACHER_SOLVENCY = 18;

        /*
        * 目录类型：MASTER_CATALOG__TYPE
        */
        int CATALOG_MASTER_TYPE = 0;

        /*
        * 目录类型：COURSE_CATALOG__TYPE
        */
        int CATALOG_COURSE_TYPE = 1;

        /*
        * 课程状态：UNAvailable COURSE Status
        */
        int COURSE_UNAVAILABLE_STATE = 0;

        /*
        * 课程状态：Available COURSE Status
        */
        int COURSE_AVAILABLE_STATE = 1;

        /*
        *   用户缴费状态：缴费
        */
        int COURSE_USER_Fee_AVAILABLE_STATE = 0;

        /*
        *   用户缴费状态：未缴费
        */
        int COURSE_USER_Fee_UNAVAILABLE_STATE = 1;

        /*
        *   用户状态：N/A,
        *   表示以前是学生，现在不再是学生时的用户在学习档案里的纪录的状态，若为此状态则该纪录不再学习档案显示。
        */
        int COURSE_USER_NOTAVAILABLE_STATE = 9;

        /*
        *   用户状态：禁用
        */
        int COURSE_USER_UNAVAILABLE_STATE = 0;

        /*
        *  用户状态：申请中.
        */
        int COURSE_USER_APPLY_STATE = 1;

        /*
        *  用户状态：学习中
        */
        int COURSE_USER_AVAILABLE_STATE = 2;

        /**
         * 用户状态：完成
         */
        int COURSE_USER_FINISH_STATE = 3;

        /**
         * 用户状态：未完成审核（分两级） 或者正在审核中
         */
        int COURSE_USER_NOT_CHECK_STATE = 4;

        /**
         * course type：elective
         */
        int ELECTIVE_COURSE_TYPE = 0;

        /**
         * course type：compulsory
         */
        int COMPULSORY_COURSE_TYPE = 1;

        /**
         * All the user can't enroll the course/certificate freely
         */
        int REGISTER_TYPE_NOT_ALLOW = 0;

        /**
         * All the user can enroll the course/certificate quite freely
         */
        int REGISTER_TYPE_ALLOW = 1;

        /**
         * All the user can enroll the course/certificate quite freely but need verified by the manager
         */
        int REGISTER_TYPE_ALLOW_NEEDAPPROVE = 2;

        /**
         * Teach Mode
         */
        //培训课程
        int TEACH_MODE_COURSE = 1;
        //课程课件
        int TEACH_MODE_COURSEWARE = 2;
        //同步课堂
        int TEACH_MODE_VCLASS = 3;
        //考试题库
        int TEACH_MODE_EXAME = 4;
        //传统培训
        int TEACH_MODE_CLASSICAL = 5;
        //其他方式
        int TEACH_MODE_OTHER = 6;
        //家教班级
        int TEACH_MODE_FAMILYEDUCATION = 7;

        /*
        * 课程的生命周期类型定义:
        *1-- 永久
        *2--一段时间
        *3--几天
        */
        int LIFESORT_FOREVER = 1;
        int LIFESORT_DATEBETWEEN = 2;
        int LIFESORT_DAYS = 3;

        /*课程目录*/
        //儿童
        int XLN_ENGLISH_ERTONG=1;
        int XLN_ENGLISH_ERTONG_ONE=7;
        int XLN_ENGLISH_ERTONG_TWO=8;
        int XLN_ENGLISH_ERTONG_THREE=9;
        int XLN_ENGLISH_ERTONG_FORE=10;
        //少儿
        int XLN_ENGLISH_SHAOER=2;
        int XLN_ENGLISH_SHAOER_ONE=11;
        int XLN_ENGLISH_SHAOER_TWO=12;
        int XLN_ENGLISH_SHAOER_THREE=13;
        int XLN_ENGLISH_SHAOER_FORE=14;
        int XLN_ENGLISH_SHAOER_FIVE=15;
        //初中
        int XLN_ENGLISH_CHUZHONG=3;
        int XLN_ENGLISH_CHUZHONG_ONE=16;
        int XLN_ENGLISH_CHUZHONG_TWO=17;
        int XLN_ENGLISH_CHUZHONG_THREE=18;
        int XLN_ENGLISH_CHUZHONG_FORE=19;
        int XLN_ENGLISH_CHUZHONG_FIVE=20;
        int XLN_ENGLISH_CHUZHONG_SIX=59;

        int PRIMARY=4;
        int PRIMARY_YUWEN=21;
        int PRIMARY_MATH=22;
        int PRIMARY_ENGLISH=23;
        int PRIMARY_OTHER=58;

        int JUNIOR=5;
        int JUNIOR_ONE_YUWEN=24;
        int JUNIOR_ONE_MATH=25;
        int JUNIOR_ONE_ENGLISH=26;
        int JUNIOR_ONE_OTHER=27;

        int JUNIOR_TWO_YUWEN=28;
        int JUNIOR_TWO_MATH=29;
        int JUNIOR_TWO_ENGLISH=30;
        int JUNIOR_TWO_PHYSICAL=31;
        int JUNIOR_TWO_OTHER=32;

        int JUNIOR_THREE_YUWEN=33;
        int JUNIOR_THREE_MATH=34;
        int JUNIOR_THREE_ENGLISH=35;
        int JUNIOR_THREE_PHYSICAL=36;
        int JUNIOR_THREE_CHEMICAL=37;
        int JUNIOR_THREE_OTHER=38;
        
        int HIGHT=6;

        int HIGHT_ONE_YUWEN=39;
        int HIGHT_ONE_MATH=40;
        int HIGHT_ONE_ENGLISH=41;
        int HIGHT_ONE_PHYSICAL=42;
        int HIGHT_ONE_CHEMICAL=43;
        int HIGHT_ONE_OTHER=44;

        int HIGHT_TWO_YUWEN=45;
        int HIGHT_TWO_MATH=46;
        int HIGHT_TWO_ENGLISH=47;
        int HIGHT_TWO_PHYSICAL=48;
        int HIGHT_TWO_CHEMICAL=49;
        int HIGHT_TWO_OTHER=50;

        int HIGHT_THREE_YUWEN=51;
        int HIGHT_THREE_MATH=52;
        int HIGHT_THREE_ENGLISH=53;
        int HIGHT_THREE_PHYSICAL=54;
        int HIGHT_THREE_CHEMICAL=55;
        int HIGHT_THREE_OTHER=56;

        int XLN_OTHER_CLASS=57;
        
        

        
}
