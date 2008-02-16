/**
 * PostDAO.java.
 * User: shid Date: 2005-7-21 10:40:25
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.admin.post.dao;

import com.ulearning.ulms.admin.post.exceptions.PostDAOSysException;
import com.ulearning.ulms.admin.post.model.PostDirModel;
import com.ulearning.ulms.admin.post.model.PostModel;
import com.ulearning.ulms.admin.post.model.PostTutorialModel;

import java.util.List;


public interface PostDAO
{
        //----------------------------PostModel-------------------------//
        public void addPost(PostModel pm) throws PostDAOSysException;

        public void updatePost(PostModel pm) throws PostDAOSysException;

        public void removePost(List lt) throws PostDAOSysException;

        /**
         * 删除相应目录下的Post
         *
         * @param lt
         * @throws PostDAOSysException
         */
        public void removePostByParentID(List lt) throws PostDAOSysException;

        public PostModel getPost(int postID) throws PostDAOSysException;

        /**
         * 得到某个目录下的Post
         *
         * @param parentID
         * @return
         * @throws PostDAOSysException
         */
        public List getPostByParentID(int parentID) throws PostDAOSysException;

        //-------------------------PostTutorialModel--------------------//
        public void addPostTutorial(PostTutorialModel ptm)
                throws PostDAOSysException;

        public void updatePostTutorial(PostTutorialModel ptm)
                throws PostDAOSysException;

        public void removePostTutorial(List lt) throws PostDAOSysException;

        public PostTutorialModel getPostTutorial(int PostTutorialID)
                throws PostDAOSysException;

        /**
         * 删除条件为type和relationID或tutorialID或sort的值，如果tutorialID或sort值为-1,则忽略此条件
         *
         * @param relationID
         * @param type
         * @param tutorialID
         * @param sort
         * @throws PostDAOSysException
         */
        public void removePostTutorial(int relationID, int type, int tutorialID,
                                       int sort) throws PostDAOSysException;

        /**
         * 根据type和relationID或tutorialID或sort得到相关的list,如果tutorialID或sort值为-1,则忽略此条件
         *
         * @param relationID
         * @param type
         * @param tutorialID
         * @param sort
         * @return
         * @throws PostDAOSysException
         */
        public List getPostTutorial(int relationID, int type, int tutorialID,
                                    int sort) throws PostDAOSysException;

        //----------------------PostDirModel---------------------------//
        public void addPostDir(PostDirModel pdm) throws PostDAOSysException;

        public void updatePostDir(PostDirModel pdm) throws PostDAOSysException;

        public void removePostDir(List lt) throws PostDAOSysException;

        public PostDirModel getRootPostDir(int type) throws PostDAOSysException;

        public PostDirModel getPostDir(int DirID) throws PostDAOSysException;

        public List getSubPostDir(int DirID, int type) throws PostDAOSysException;

        public List getPostTree(int parentID, int level) throws PostDAOSysException;
}
