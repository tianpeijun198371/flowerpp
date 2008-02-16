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
         * ��������Ŀ���� ʦѵ�γ�
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
          /*��ѵ���еĸ�������
        */
        int CLASS_TYPE = 65;
        int CLASS_FEATURE = 66;
        int CLASS_LEVEL = 67;
        int CLASS_PATTERN = 68;

        /**
         * ��ʦ����
         */
        int TEACHER_SOLVENCY = 18;

        /*
        * Ŀ¼���ͣ�MASTER_CATALOG__TYPE
        */
        int CATALOG_MASTER_TYPE = 0;

        /*
        * Ŀ¼���ͣ�COURSE_CATALOG__TYPE
        */
        int CATALOG_COURSE_TYPE = 1;

        /*
        * �γ�״̬��UNAvailable COURSE Status
        */
        int COURSE_UNAVAILABLE_STATE = 0;

        /*
        * �γ�״̬��Available COURSE Status
        */
        int COURSE_AVAILABLE_STATE = 1;

        /*
        *   �û��ɷ�״̬���ɷ�
        */
        int COURSE_USER_Fee_AVAILABLE_STATE = 0;

        /*
        *   �û��ɷ�״̬��δ�ɷ�
        */
        int COURSE_USER_Fee_UNAVAILABLE_STATE = 1;

        /*
        *   �û�״̬��N/A,
        *   ��ʾ��ǰ��ѧ�������ڲ�����ѧ��ʱ���û���ѧϰ������ļ�¼��״̬����Ϊ��״̬��ü�¼����ѧϰ������ʾ��
        */
        int COURSE_USER_NOTAVAILABLE_STATE = 9;

        /*
        *   �û�״̬������
        */
        int COURSE_USER_UNAVAILABLE_STATE = 0;

        /*
        *  �û�״̬��������.
        */
        int COURSE_USER_APPLY_STATE = 1;

        /*
        *  �û�״̬��ѧϰ��
        */
        int COURSE_USER_AVAILABLE_STATE = 2;

        /**
         * �û�״̬�����
         */
        int COURSE_USER_FINISH_STATE = 3;

        /**
         * �û�״̬��δ�����ˣ��������� �������������
         */
        int COURSE_USER_NOT_CHECK_STATE = 4;

        /**
         * course type��elective
         */
        int ELECTIVE_COURSE_TYPE = 0;

        /**
         * course type��compulsory
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
        //��ѵ�γ�
        int TEACH_MODE_COURSE = 1;
        //�γ̿μ�
        int TEACH_MODE_COURSEWARE = 2;
        //ͬ������
        int TEACH_MODE_VCLASS = 3;
        //�������
        int TEACH_MODE_EXAME = 4;
        //��ͳ��ѵ
        int TEACH_MODE_CLASSICAL = 5;
        //������ʽ
        int TEACH_MODE_OTHER = 6;
        //�ҽ̰༶
        int TEACH_MODE_FAMILYEDUCATION = 7;

        /*
        * �γ̵������������Ͷ���:
        *1-- ����
        *2--һ��ʱ��
        *3--����
        */
        int LIFESORT_FOREVER = 1;
        int LIFESORT_DATEBETWEEN = 2;
        int LIFESORT_DAYS = 3;

        /*�γ�Ŀ¼*/
        //��ͯ
        int XLN_ENGLISH_ERTONG=1;
        int XLN_ENGLISH_ERTONG_ONE=7;
        int XLN_ENGLISH_ERTONG_TWO=8;
        int XLN_ENGLISH_ERTONG_THREE=9;
        int XLN_ENGLISH_ERTONG_FORE=10;
        //�ٶ�
        int XLN_ENGLISH_SHAOER=2;
        int XLN_ENGLISH_SHAOER_ONE=11;
        int XLN_ENGLISH_SHAOER_TWO=12;
        int XLN_ENGLISH_SHAOER_THREE=13;
        int XLN_ENGLISH_SHAOER_FORE=14;
        int XLN_ENGLISH_SHAOER_FIVE=15;
        //����
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
