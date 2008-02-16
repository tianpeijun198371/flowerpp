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
         * 查找
         *
         * @param id
         * @throws ScormSysException
         */
        public NewScormScoesTrack get(Integer id) throws ScormSysException;

        /**
         * 返回学生在某sco的状态数据。
         *
         * @param userId
         * @param scoId
         * @return
         * @throws ScormSysException
         */
        public List getNewScormScoesTracksByUserAndSco(Integer userId, Integer scoId);

        /**
         * 判断用户在SCO的跟踪记录(element对应的值)是否存在.
         *
         * @param userId  用户
         * @param scoId   SCO
         * @param element 元素
         * @return 存在与否
         * @throws ScormSysException 出错信息
         */
        public NewScormScoesTrack isExistScormScoesTrack(Integer userId,
                                                         Integer scoId, String element) throws ScormSysException;

        /**
         * 插入
         *
         * @param newScormScoesTrack
         * @throws com.ulearning.ulms.scorm.exceptions.ScormSysException
         *
         */
        public void insert(NewScormScoesTrack newScormScoesTrack)
                throws ScormSysException;

        /**
         * 更新
         *
         * @param newScormScoesTrack
         * @throws ScormSysException
         */
        public void update(NewScormScoesTrack newScormScoesTrack)
                throws ScormSysException;

        /**
         * 删除
         *
         * @param newScormScoesTrack
         * @throws ScormSysException
         */
        public void delete(NewScormScoesTrack newScormScoesTrack)
                throws ScormSysException;
}
