/**
 * ScoreManageDAO.java.
 * User: fengch  Date: 2004-4-27
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.common.dao;

import com.ulearning.ulms.common.exceptions.ScoreSysException;
import com.ulearning.ulms.common.model.ScoreListModel;
import com.ulearning.ulms.common.model.ScoreModel;

import java.util.List;


public interface ScoreManageDAO
{
        /**
         * get  the relationed scores
         * type
         * ==0:课程成绩
         * ==1:证书成绩
         * ==2:项目成绩
         *
         * @throws ScoreSysException
         */
        public ScoreListModel get(int relationID, int type, int pageNo, int pageSize)
                throws ScoreSysException;

        /**
         * get  the user-relationed score
         * type
         * ==0:课程成绩
         * ==1:证书成绩
         * ==2:项目成绩
         *
         * @throws com.ulearning.ulms.common.exceptions.ScoreSysException
         *
         */
        public ScoreModel get(int userID, int relationID, int type)
                throws ScoreSysException;

        /**
         * 判断培训班下所有通过课程的学生
         *
         * @param userID   用户
         * @param courseID 培训班下的课程
         * @return
         * @throws ScoreSysException
         */
        public List getPassCourse(int userID, List courseID)
                throws ScoreSysException;

        /**
         * 培训班下的课程成绩
         *
         * @param relationID
         * @param isPass
         * @param type
         * @return
         * @throws ScoreSysException
         */
        public List getCourseScore(int relationID, int isPass, int type)
                throws ScoreSysException;

        /**
         * get  a score
         *
         * @throws ScoreSysException
         */
        public ScoreModel get(int scoreID) throws ScoreSysException;

        /**
         * update a a score.
         *
         * @throws ScoreSysException
         */
        public void update(ScoreModel hpm) throws ScoreSysException;

        /**
         * insert a score.
         *
         * @throws ScoreSysException
         */
        public void insert(ScoreModel hpm) throws ScoreSysException;

        /**
         * delete a score
         *
         * @throws ScoreSysException
         */
        public void delete(int scoreID) throws ScoreSysException;

        /**
         * delete  score by userID.
         *
         * @throws ScoreSysException
         */
        public void deleteByUserID(int userID) throws ScoreSysException;

        /**
         * delete a score
         *
         * @throws ScoreSysException
         */
        public void delete(int userID, int relationID, int type)
                throws ScoreSysException;

        /**
         * return the pass radio of the module.
         * <p>if all module user's number is 0,then reurn -1
         * <p>module type
         * ==0:课程
         * ==1:证书
         * ==2:项目
         *
         * @throws com.ulearning.ulms.common.exceptions.ScoreSysException
         *
         */
        public float getPassRadio(int relationID, int type)
                throws ScoreSysException;
}
