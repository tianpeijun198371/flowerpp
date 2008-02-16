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
         * 查找
         *
         * @param id
         * @throws ScormSysException
         */
        public NewScormScoes get(Integer id) throws ScormSysException;

        /**
         * 查找
         *
         * @param identifer
         * @throws ScormSysException
         */
        public NewScormScoes getByIdentifier(Integer scormId, String identifer);

        /**
         * 返回Scorm的最后一个Sco节点
         *
         * @param @return
         * @throws com.ulearning.ulms.content.exceptions.ContentManageSysException
         *
         */
        public NewScormScoes getLastScoByScorm(Integer scormId)
                throws ScormSysException;

        /**
         * 返回Scorm的第一个Sco节点
         *
         * @param @return
         * @throws com.ulearning.ulms.content.exceptions.ContentManageSysException
         *
         */
        public NewScormScoes getFirstScoByScorm(Integer scormId)
                throws ScormSysException;

        /**
         * 返回Scorm的所有节点
         *
         * @param @return
         * @throws com.ulearning.ulms.content.exceptions.ContentManageSysException
         *
         */
        public List getByScorm(Integer scormId) throws ScormSysException;

        /**
         * 返回Scorm的所有Sco节点
         *
         * @param @return
         * @throws com.ulearning.ulms.content.exceptions.ContentManageSysException
         *
         */
        public List getScoByScorm(Integer scormId);

        /**
         * 返回Scorm的所有节点
         *
         * @param @return
         * @throws com.ulearning.ulms.content.exceptions.ContentManageSysException
         *
         */
        public List getByScorm(Integer scormId, String scoType)
                throws ScormSysException;

        /**
         * 返回某节点下面的内容。不包含子目录
         *
         * @param @return
         * @throws com.ulearning.ulms.content.exceptions.ContentManageSysException
         *
         */
        public List getSubContent(Integer scormId, String identifer);

        /**
         * 插入
         *
         * @param newScormScoes
         * @throws com.ulearning.ulms.scorm.exceptions.ScormSysException
         *
         */
        public void insert(NewScormScoes newScormScoes) throws ScormSysException;

        /**
         * 更新
         *
         * @param newScormScoes
         * @throws ScormSysException
         */
        public void update(NewScormScoes newScormScoes) throws ScormSysException;

        /**
         * 删除
         *
         * @param newScormScoes
         * @throws ScormSysException
         */
        public void delete(NewScormScoes newScormScoes) throws ScormSysException;
}
