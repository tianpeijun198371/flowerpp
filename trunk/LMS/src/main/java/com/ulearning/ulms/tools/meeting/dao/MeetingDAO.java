/**
 * Copyright (c)2006 Beijing WenhuaOnline Sci-tech DevelopmentCo.,Ltd.
 * All rights reserved.
 *
 * User: Fengch
 * Date: 2007-11-8 10:15:32
 */
package com.ulearning.ulms.tools.meeting.dao;

import com.ulearning.ulms.core.util.PagerList;
import com.ulearning.ulms.tools.meeting.exception.MeetingSysException;
import com.ulearning.ulms.tools.meeting.model.MeetingModel;

import java.util.Date;
import java.util.List;


public interface MeetingDAO {
    /**
     * ���ػ����б�
     * @param aspID
     * @param relationID  -1.���ʾ����
     * @param type      -1.���ʾ����
     * @param meetingType    -1.���ʾ����
     * @param auditStatus   -1.���ʾ����
     * @param status         -1.���ʾ����
     * @param pageNo
     * @param pageSize
     * @return
     * @throws com.ulearning.ulms.tools.meeting.exception.MeetingSysException
     */
    public PagerList getMeetings(int aspID, int relationID, int type,
        int meetingType, int auditStatus, int status, int pageNo, int pageSize)
        throws MeetingSysException;

    /**
     * ���ذ༶���õ�һ������
     * @return
     * @throws com.ulearning.ulms.tools.meeting.exception.MeetingSysException
     */
    public MeetingModel getAvailableOneMeetingByCourse(int courseID)
        throws MeetingSysException;

    public MeetingModel getMeeting(int id) throws MeetingSysException;

    public void addMeeting(MeetingModel meeting) throws MeetingSysException;

    public void updateMeeting(MeetingModel meeting) throws MeetingSysException;

    /**
     * ��˻���
     * @param ids  �����б�
     * @param services ���ӷ�����
     * @param primaryServer ��������
     * @param auditStatus
     * @throws MeetingSysException
     */
    public void auditMeetings(int[] ids, String services, int primaryServer,
        int auditStatus, String fixed) throws MeetingSysException;

    public void deleteMeeting(int id) throws MeetingSysException;

    public void deleteMeeting(int[] ids) throws MeetingSysException;

    /**
     * ͳ����ĳ��ʱ����ڷ�����������Ϊ���������Ļ������.
     * @param primaryServer
     * @param startTime
     * @param endTime
     * @return
     * @throws MeetingSysException
     */
    public int getMeetingPrimaryServerCount(int primaryServer, Date startTime,
        Date endTime) throws MeetingSysException;
}
