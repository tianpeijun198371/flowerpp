/**
 * Copyright (c)2006 Beijing WenhuaOnline Sci-tech DevelopmentCo.,Ltd. 
 * All rights reserved. 
 *
 * User: Fengch
 * Date: 2007-11-8 10:16:43
 */


package com.ulearning.ulms.tools.meeting.util;

public class MeetingUtil
{
        public static String getStatusStr(int status)
        {
                String str="";
                if(status== MeetingKeys.STATUS_ACTIVE)
                        str="�����ٿ�";
                else if(status== MeetingKeys.STATUS_FINSIHED)
                        str="�ѽ���";
                else if(status== MeetingKeys.STATUS_INACTIVE)
                        str="��δ��ʼ";
                return str;
        }

        public static String getAuditStatusStr(int status)
        {
                String str="";
                if(status== MeetingKeys.AUDIT_APPROVED)
                        str="���ͨ��";
                else if(status== MeetingKeys.AUDIT_BLANK)
                        str="��δ���";
                else if(status== MeetingKeys.AUDIT_UNAPPROVED)
                        str="��˲�ͨ��";
                return str;
        }
}
