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
        //������ͣ�==1:����Ϊ�Ϳγ�����������߿��ã����IDΪ�γ�ID
        int TYPE_COURSE = 1;

        //������ͣ�==3:����Ϊ�ͻ�������������߿��ã����IDΪ����ID
        int TYPE_ORGAN = 3;

        //��������:�ǽ����
        int MEETINGTYPE_ZHIJIEHUANYU = 1;
        //��������:ѧ��
        int MEETINGTYPE_XUECHUANG = 2;
        //��������:Webex
        int MEETINGTYPE_WEBEX = 3;

        //���״̬:δ���
        int AUDIT_BLANK=0;
        //���״̬:���ͨ��
        int AUDIT_APPROVED=1;
        //���״̬:���δͨ��
        int AUDIT_UNAPPROVED=2;

        //���״̬:δ�ٿ�, ����ѧ������ʱĬ��Ϊ��δ�ٿ���
        int STATUS_INACTIVE=1;
        //���״̬:���ٿ�
        int STATUS_ACTIVE=2;
        //���״̬:�ѽ���
        int STATUS_FINSIHED=4;
}
