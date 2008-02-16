/**
 * Copyright (c)2006 Beijing WenhuaOnline Sci-tech DevelopmentCo.,Ltd. 
 * All rights reserved. 
 *
 * User: Fengch
 * Date: 2007-11-8 10:17:00
 */


package com.ulearning.ulms.tools.meeting.util;

public interface MeetingKeys
{
        //相关类型：==1:代表为和课程相关联的在线课堂，相关ID为课程ID
        int TYPE_COURSE = 1;

        //相关类型：==3:代表为和机构相关联的在线课堂，相关ID为机构ID
        int TYPE_ORGAN = 3;

        //会议类型:智捷寰宇
        int MEETINGTYPE_ZHIJIEHUANYU = 1;
        //会议类型:学窗
        int MEETINGTYPE_XUECHUANG = 2;
        //会议类型:Webex
        int MEETINGTYPE_WEBEX = 3;

        //审核状态:未审核
        int AUDIT_BLANK=0;
        //审核状态:审核通过
        int AUDIT_APPROVED=1;
        //审核状态:审核未通过
        int AUDIT_UNAPPROVED=2;

        //审核状态:未召开, 创建学窗会议时默认为“未召开”
        int STATUS_INACTIVE=1;
        //审核状态:正召开
        int STATUS_ACTIVE=2;
        //审核状态:已结束
        int STATUS_FINSIHED=4;
}
