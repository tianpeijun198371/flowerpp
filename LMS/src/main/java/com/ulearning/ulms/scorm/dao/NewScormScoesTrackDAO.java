/**
 * Copyright (c)2006 Beijing WenhuaOnline Sci-tech DevelopmentCo.,Ltd.
 * All rights reserved.
 *
 * User: fengch
 * Date: 2007-9-21 13:50:41
 */
package com.ulearning.ulms.scorm.dao;

import com.ulearning.ulms.scorm.exceptions.ScormSysException;
import com.ulearning.ulms.scorm.model.NewScormScoesTrack;

import java.util.List;


public interface NewScormScoesTrackDAO
{
        /**
         * ����
         *
         * @param id
         * @throws ScormSysException
         */
        public NewScormScoesTrack get(Integer id) throws ScormSysException;

        /**
         * ����ѧ����ĳsco��״̬���ݡ�
         *
         * @param userId
         * @param scoId
         * @return
         * @throws ScormSysException
         */
        public List getNewScormScoesTracksByUserAndSco(Integer userId, Integer scoId);

        /**
         * �ж��û���SCO�ĸ��ټ�¼(element��Ӧ��ֵ)�Ƿ����.
         *
         * @param userId  �û�
         * @param scoId   SCO
         * @param element Ԫ��
         * @return �������
         * @throws ScormSysException ������Ϣ
         */
        public NewScormScoesTrack isExistScormScoesTrack(Integer userId,
                                                         Integer scoId, String element) throws ScormSysException;

        /**
         * ����
         *
         * @param newScormScoesTrack
         * @throws com.ulearning.ulms.scorm.exceptions.ScormSysException
         *
         */
        public void insert(NewScormScoesTrack newScormScoesTrack)
                throws ScormSysException;

        /**
         * ����
         *
         * @param newScormScoesTrack
         * @throws ScormSysException
         */
        public void update(NewScormScoesTrack newScormScoesTrack)
                throws ScormSysException;

        /**
         * ɾ��
         *
         * @param newScormScoesTrack
         * @throws ScormSysException
         */
        public void delete(NewScormScoesTrack newScormScoesTrack)
                throws ScormSysException;
}
