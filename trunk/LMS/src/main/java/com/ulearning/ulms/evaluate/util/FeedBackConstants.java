/**
 * FeedBackConstants.java.
 * User: fengch Date: 2005-6-27 20:00:01
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.evaluate.util;

public interface FeedBackConstants
{
        //类型：回复
        int REPLY_TYPE = -1;         
        //类型：教学评估>教学反馈>学习中心反馈
        int STUDY_CENTER_TYPE = 0;
        //类型：教学评估-教学反馈-课程反馈
        int COURSE_TYPE = 1;
        //类型：家教在线预约
        int FAMILYEDUCATION_ONLINERESERVE_TYPE = 2;

        //状态：已回复
        int STATUS_REPLYED=1;
        //状态：未回复
        int STATUS_NOTREPLYED=0;
}
