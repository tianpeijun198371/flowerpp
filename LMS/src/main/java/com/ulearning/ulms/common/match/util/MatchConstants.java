/**
 * MatchConstants.java.
 * User: fengch  Date: 2004-7-23
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.common.match.util;

public interface MatchConstants
{
        //Operator==0: like
        int OPERATOR_LIKE = 0;

        //Operator==1: =
        int OPERATOR_EQUAL = 1;

        //Operator==2: >
        int OPERATOR_MORETHAN = 2;

        //Operator==3: >=
        int OPERATOR_NOT_LESSTHAN = 3;

        //Operator==4: <
        int OPERATOR_LESSTHAN = 4;

        //Operator==5: <=
        int OPERATOR_NOT_MORETHAN = 5;

        //ϵͳ��֮ƥ�����-'����'
        int MATCH_DEPART = 1;

        //ϵͳ��֮ƥ�����-'��ɫ'
        int MATCH_ROLE = 2;

        //ϵͳ��֮ƥ�����-''ְ��''
        int MATCH_POSITION = 3;

        //ϵͳ��֮ƥ�����-'�����̶�'
        int MATCH_EDULEVEL = 4;

        //ϵͳ��֮ƥ�����-'�Ա�'
        int MATCH_SEX = 5;

        //'��'
        String MATCH_SEX_MALE = "1";

        //'Ů'
        String MATCH_SEX_FEMALE = "0";

        // a session key
        String AVAILABLE_MATCH_LIST_SESSION_KEY = "availableMatchList";
}
