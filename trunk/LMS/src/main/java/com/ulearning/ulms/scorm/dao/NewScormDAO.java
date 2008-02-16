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
         * 查找
         *
         * @param id
         * @throws ScormSysException
         */
        public NewScorm get(Integer id) throws ScormSysException;

        /**
         * 根据相关ID，返回对应Scorm
         *
         * @throws ScormSysException
         */
        public NewScorm getByRelationID(Integer relationID, String type)
                throws ScormSysException;

        /**
         * 插入
         *
         * @param newScorm
         * @throws ScormSysException
         */
        public void insert(NewScorm newScorm) throws ScormSysException;

        /**
         * 更新
         *
         * @param newScorm
         * @throws ScormSysException
         */
        public void update(NewScorm newScorm) throws ScormSysException;

        /**
         * 删除
         *
         * @param newScorm
         * @throws ScormSysException
         */
        public void delete(NewScorm newScorm) throws ScormSysException;
}
