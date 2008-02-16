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

        //系统内之匹配规则-'部门'
        int MATCH_DEPART = 1;

        //系统内之匹配规则-'角色'
        int MATCH_ROLE = 2;

        //系统内之匹配规则-''职务''
        int MATCH_POSITION = 3;

        //系统内之匹配规则-'教育程度'
        int MATCH_EDULEVEL = 4;

        //系统内之匹配规则-'性别'
        int MATCH_SEX = 5;

        //'男'
        String MATCH_SEX_MALE = "1";

        //'女'
        String MATCH_SEX_FEMALE = "0";

        // a session key
        String AVAILABLE_MATCH_LIST_SESSION_KEY = "availableMatchList";
}
