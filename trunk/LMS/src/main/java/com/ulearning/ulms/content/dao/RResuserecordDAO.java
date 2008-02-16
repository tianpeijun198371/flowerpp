/**
 * RResuserecordDAO.java.
 * User: liz  Date: 2006-2-20
 * ��Դʹ�ü�¼��DAO�ӿ�
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.content.dao;

import com.ulearning.ulms.content.model.RResuserecordModel;
import com.ulearning.ulms.core.exceptions.ULMSException;

import java.util.Date;
import java.util.List;


public interface RResuserecordDAO
{
        /**
         * �����Դʹ�ü�¼
         *
         * @param mod RResuserecordModel
         * @return 1�ɹ�����0��ʧ��
         * @throws ULMSException
         */
        public int addResUsed(RResuserecordModel mod) throws ULMSException;

        /**
         * ������Դʹ�ü�¼
         *
         * @param mod RResuserecordModel
         * @throws ULMSException
         */
        public void updateResUsed(RResuserecordModel mod) throws ULMSException;

        /**
         * ɾ����Դ��¼��ֻ��ɾ�����
         *
         * @param resuseId ��Դʹ�ü�¼ID
         * @throws ULMSException
         */
        public void removeResUsed(int resuseId) throws ULMSException;

        /**
         * ȡ������������Դʹ�ü�¼
         *
         * @param hql           ��ѯSQL
         * @param startDateTime �������ڡ���Ĳ�ѯ�Ŀ�ʼ
         * @param endDateTime   �������ڡ���Ĳ�ѯ�Ľ���
         * @param firstResult   ��ҳʹ��
         * @param maxResults    ��ҳʹ��
         * @return
         * @throws ULMSException
         */
        public List getResUsedData(String hql, Date startDateTime,
                                   Date endDateTime, int firstResult, int maxResults)
                throws ULMSException;
}
