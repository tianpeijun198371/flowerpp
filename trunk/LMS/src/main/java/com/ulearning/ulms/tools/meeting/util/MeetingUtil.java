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
                        str="正在召开";
                else if(status== MeetingKeys.STATUS_FINSIHED)
                        str="已结束";
                else if(status== MeetingKeys.STATUS_INACTIVE)
                        str="还未开始";
                return str;
        }

        public static String getAuditStatusStr(int status)
        {
                String str="";
                if(status== MeetingKeys.AUDIT_APPROVED)
                        str="审核通过";
                else if(status== MeetingKeys.AUDIT_BLANK)
                        str="还未审核";
                else if(status== MeetingKeys.AUDIT_UNAPPROVED)
                        str="审核不通过";
                return str;
        }
}
