package com.ulearning.ulms.scorm.dao;

import com.ulearning.ulms.scorm.exceptions.ScormSysException;
import com.ulearning.ulms.scorm.model.NewScorm;


/**
 * Copyright (c)2006 Beijing WenhuaOnline Sci-tech DevelopmentCo.,Ltd.
 * All rights reserved.
 * <p/>
 * User: fengch
 * Date: 2007-9-21 13:20:02
 */
public interface NewScormDAO
{
        /**
         * ����
         *
         * @param id
         * @throws ScormSysException
         */
        public NewScorm get(Integer id) throws ScormSysException;

        /**
         * �������ID�����ض�ӦScorm
         *
         * @throws ScormSysException
         */
        public NewScorm getByRelationID(Integer relationID, String type)
                throws ScormSysException;

        /**
         * ����
         *
         * @param newScorm
         * @throws ScormSysException
         */
        public void insert(NewScorm newScorm) throws ScormSysException;

        /**
         * ����
         *
         * @param newScorm
         * @throws ScormSysException
         */
        public void update(NewScorm newScorm) throws ScormSysException;

        /**
         * ɾ��
         *
         * @param newScorm
         * @throws ScormSysException
         */
        public void delete(NewScorm newScorm) throws ScormSysException;
}
