/**
 * ScormDAO.java.
 * User: fengch  Date: 2004-7-13
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.scorm.dao;

import com.ulearning.ulms.scorm.exceptions.ScormSysException;
import com.ulearning.ulms.scorm.model.ItemModel;
import com.ulearning.ulms.scorm.model.UserSCOModel;

import java.util.List;
import java.util.Vector;


public interface ScormDAO
{
        /**
         * insert iterm.
         *
         * @param relationID
         * @param type
         * @param courseContentNodeID
         * @param items
         * @throws ScormSysException
         */
        public void insertItem(int relationID, int type, int courseContentNodeID,
                               Vector items) throws ScormSysException;

        /**
         * insert  usersco.
         *
         * @param userID
         * @param courseContentNodeID
         * @throws ScormSysException
         */
        public void insertUserSCO(int userID, int courseContentNodeID)
                throws ScormSysException;

        /**
         * 判断用户是否已初始化scorm数据
         *
         * @param userID
         * @param courseContentNodeID
         * @return
         * @throws ScormSysException
         */
        public boolean isInitUserSCO(int userID, int courseContentNodeID)
                throws ScormSysException;

        /**
         * get all the scorm's model
         *
         * @param courseContentNodeID
         * @return
         * @throws ScormSysException
         */
        public List getItems(int courseContentNodeID) throws ScormSysException;

        /**
         * get a  ItemModel  according to the itemID.
         *
         * @param itemID
         * @return
         * @throws ScormSysException
         */
        public ItemModel getItem(int itemID) throws ScormSysException;

        /**
         * get all the scorm's model  according to the user.
         *
         * @param courseContentNodeID
         * @return
         * @throws ScormSysException
         */
        public List getUserSCOs(int userID, int courseContentNodeID)
                throws ScormSysException;

        /**
         * get a  scorm's model  according to the user.
         *
         * @param userID
         * @param scoID
         * @return
         * @throws ScormSysException
         */
        public UserSCOModel getUserSCO(int userID, int scoID)
                throws ScormSysException;

        /**
         * update the user's lessonStatus
         *
         * @param userID
         * @param scoID
         * @param lessonStatus
         * @throws ScormSysException
         */
        public void updateUserSCO(int userID, int scoID, String lessonStatus)
                throws ScormSysException;

        /**
         * update the user's lessonStatus and time
         *
         * @param userID
         * @param scoID
         * @param lessonStatus
         * @param sessionTime
         * @throws ScormSysException
         */
        public void updateUserSCO(int userID, int scoID, String lessonStatus,
                                  String sessionTime) throws ScormSysException;

        /**
         * 根据itemID，返回对应的SCO，如果itemID的类型就是SCO，否则返回itemID下一个SCO
         *
         * @param itemID
         * @return
         * @throws ScormSysException
         */
        public int getSCO(int itemID) throws ScormSysException;
}
