/**
 * FeedBackDAO.java.
 * User: fengch Date: 2005-6-27 19:31:58
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.evaluate.dao;

import com.ulearning.ulms.evaluate.exceptions.EvaluateManageSysException;
import com.ulearning.ulms.evaluate.model.FeedBackModel;
import com.ulearning.ulms.evaluate.model.FeedBackOptionModel;
import com.ulearning.ulms.core.util.PagerList;

import java.util.List;


public interface FeedBackDAO
{
        /**
         * add a FeedBack.
         *
         * @param am
         * @throws EvaluateManageSysException
         */
        public void insert(FeedBackModel am) throws EvaluateManageSysException;

        /**
         * get a FeedBack.
         *
         * @param feedBackID
         * @throws EvaluateManageSysException
         */
        public FeedBackModel get(int feedBackID) throws EvaluateManageSysException;

        /**
         * 返回回复.
         *
         * @return
         * @throws EvaluateManageSysException
         */
        public PagerList getReplys(int feedBackID,int pageNo,int pageSize)
                throws EvaluateManageSysException;
        
        /**
         * get list by orgID
         *
         * @param orgID
         * @return
         * @throws EvaluateManageSysException
         */
        public List getCheck(int orgID) throws EvaluateManageSysException;

        /**
         * get course feedBack by User
         *
         * @param userID
         * @return
         * @throws EvaluateManageSysException
         */
        public List getCourseFeedBackByUser(int userID)
                throws EvaluateManageSysException;

        /**
         * get course lise by score
         *
         * @return
         * @throws EvaluateManageSysException
         */
        public List getCourseCheck() throws EvaluateManageSysException;

        /**
         * get list by orgID and score
         *
         * @param orgID
         * @param score
         * @return
         * @throws EvaluateManageSysException
         */
        public List getByOrgAndScore(int orgID, int score)
                throws EvaluateManageSysException;

        /**
         * get list by relationID
         *
         * @return
         * @throws EvaluateManageSysException
         */
        public List getFeedBackByRelationID() throws EvaluateManageSysException;

        /**
         * get a FeedBack.
         *
         * @param userID
         * @param relationID
         * @param type
         * @return
         * @throws EvaluateManageSysException
         */
        public List get(int userID, int relationID, int type)
                throws EvaluateManageSysException;

        /**
         * get  FeedBacks.
         *
         * @param userID
         * @param relationID
         * @param type
         * @return
         * @throws EvaluateManageSysException
         */
        public PagerList get(int aspID,int userID, int relationID, int type,int pageNo,int pageSize)
                throws EvaluateManageSysException;

        /**
         * search  FeedBacks.
         *
         * @param userID
         * @param relationID
         * @param type
         * @param feedbackID 订单号
         * @return
         * @throws EvaluateManageSysException
         */
        public PagerList search(int aspID,int userID, int relationID, int type,String userName,String relationName,
                                String keyword,int feedbackID,int pageNo,int pageSize);
        
        /**
         * 返回对某反馈的总参加人次.
         *
         * @param relationID
         * @param type
         * @return
         * @throws EvaluateManageSysException
         */
        public int getFeedBackPersonTimes(int relationID, int type)
                throws EvaluateManageSysException;

        /**
         * delete a FeedBack.
         *
         * @param feedBackID
         * @throws EvaluateManageSysException
         */
        public void delete(int feedBackID) throws EvaluateManageSysException;

        //删除
        public  void delete(int[] ids)
                throws EvaluateManageSysException;
        
        /**
         * update a FeedBack.
         *
         * @param am
         * @throws EvaluateManageSysException
         */
        public void update(FeedBackModel am) throws EvaluateManageSysException;

        /**
         * update a feedback
         *
         * @param am
         * @throws EvaluateManageSysException
         */
        public void addFeedBackOption(FeedBackOptionModel am)
                throws EvaluateManageSysException;

        /**
         * update a feedback
         *
         * @param am
         * @throws EvaluateManageSysException
         */
        public void updateFeedBackOption(FeedBackOptionModel am)
                throws EvaluateManageSysException;

        /**
         * get a model by orgID
         *
         * @param orgID
         * @return
         * @throws EvaluateManageSysException
         */
        public List getByOrgID(int orgID) throws EvaluateManageSysException;
}
