/**
 * LmslogConstants.java.
 * User: keyh  Date: 2004-8-19
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.lmslog.util;

public interface LmslogConstants
{
        public static int LOGTYPE_ALL = 10000; //������־
        public static int OPERATION_ALL = 20000; //���в���
        public static int LOGTYPE_USER = 11001; //�û���־
        public static int OPERATION_USER_LOGIN = 11002; //�û���¼
        public static int OPERATION_USER_LOGOUT = 11003; //�û��˳�
        public static int OPERATION_SECURETY_CHANGPWD = 11004; //�û���������
        public static int LOGTYPE_SYSTEM = 12001; //ϵͳ��־
        public static int OPERATION_SYSTEM_AUTO_DEL = 12002;
        public static int OPERATION_SYSTEM_AUTO_BACKUP = 12003;
        public static int USER_TYPE_MANAGER = 1; //����Ա
        public static int USER_TYPE_TEACHER = 2; //��ʦ
        public static int USER_TYPE_STUDENT = 3; //ѧ��
}
