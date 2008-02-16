/**
 * Copyright (c)2006 Beijing WenhuaOnline Sci-tech DevelopmentCo.,Ltd.
 * All rights reserved.
 * <p/>
 * User: fengch
 * Date: 2007-9-21 13:22:26
 */
package com.ulearning.ulms.scorm.dao;

import com.ulearning.ulms.scorm.exceptions.ScormSysException;
import com.ulearning.ulms.scorm.model.NewScorm;
import com.ulearning.ulms.scorm.model.NewScormScoes;

import java.util.List;


public interface NewScormScoesDAO
{
        /**
         * ����
         *
         * @param id
         * @throws ScormSysException
         */
        public NewScormScoes get(Integer id) throws ScormSysException;

        /**
         * ����
         *
         * @param identifer
         * @throws ScormSysException
         */
        public NewScormScoes getByIdentifier(Integer scormId, String identifer);

        /**
         * ����Scorm�����һ��Sco�ڵ�
         *
         * @param @return
         * @throws com.ulearning.ulms.content.exceptions.ContentManageSysException
         *
         */
        public NewScormScoes getLastScoByScorm(Integer scormId)
                throws ScormSysException;

        /**
         * ����Scorm�ĵ�һ��Sco�ڵ�
         *
         * @param @return
         * @throws com.ulearning.ulms.content.exceptions.ContentManageSysException
         *
         */
        public NewScormScoes getFirstScoByScorm(Integer scormId)
                throws ScormSysException;

        /**
         * ����Scorm�����нڵ�
         *
         * @param @return
         * @throws com.ulearning.ulms.content.exceptions.ContentManageSysException
         *
         */
        public List getByScorm(Integer scormId) throws ScormSysException;

        /**
         * ����Scorm������Sco�ڵ�
         *
         * @param @return
         * @throws com.ulearning.ulms.content.exceptions.ContentManageSysException
         *
         */
        public List getScoByScorm(Integer scormId);

        /**
         * ����Scorm�����нڵ�
         *
         * @param @return
         * @throws com.ulearning.ulms.content.exceptions.ContentManageSysException
         *
         */
        public List getByScorm(Integer scormId, String scoType)
                throws ScormSysException;

        /**
         * ����ĳ�ڵ���������ݡ���������Ŀ¼
         *
         * @param @return
         * @throws com.ulearning.ulms.content.exceptions.ContentManageSysException
         *
         */
        public List getSubContent(Integer scormId, String identifer);

        /**
         * ����
         *
         * @param newScormScoes
         * @throws com.ulearning.ulms.scorm.exceptions.ScormSysException
         *
         */
        public void insert(NewScormScoes newScormScoes) throws ScormSysException;

        /**
         * ����
         *
         * @param newScormScoes
         * @throws ScormSysException
         */
        public void update(NewScormScoes newScormScoes) throws ScormSysException;

        /**
         * ɾ��
         *
         * @param newScormScoes
         * @throws ScormSysException
         */
        public void delete(NewScormScoes newScormScoes) throws ScormSysException;
}
