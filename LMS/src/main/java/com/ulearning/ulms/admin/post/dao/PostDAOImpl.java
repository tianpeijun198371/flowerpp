/**
 * PostDAOImpl.java.
 * User: shid Date: 2005-7-21 11:00:26
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.admin.post.dao;

import com.ulearning.ulms.admin.post.exceptions.PostDAOSysException;
import com.ulearning.ulms.admin.post.form.PostForm;
import com.ulearning.ulms.admin.post.model.PostDirModel;
import com.ulearning.ulms.admin.post.model.PostModel;
import com.ulearning.ulms.admin.post.model.PostTutorialModel;
import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.util.HibernateDAO;
import com.ulearning.ulms.util.log.LogUtil;

import java.util.ArrayList;
import java.util.List;


public class PostDAOImpl implements PostDAO
{
        //----------------------------postModel-----------------------------------------------------------------------//
        public void addPost(PostModel pm) throws PostDAOSysException
        {
                try
                {
                        HibernateDAO.add(pm);
                }
                catch (ULMSSysException e)
                {
                        throw new PostDAOSysException(e);
                }
        }

        public void updatePost(PostModel pm) throws PostDAOSysException
        {
                try
                {
                        HibernateDAO.update(pm);
                }
                catch (ULMSSysException e)
                {
                        throw new PostDAOSysException(e);
                }
        }

        public void removePost(List l) throws PostDAOSysException
        {
                String sql_str = null;

                if (l.size() > 0)
                {
                        sql_str = "from PostModel where postID = " + l.get(0);

                        for (int i = 1; i < l.size(); i++)
                        {
                                sql_str += ("or postID = " + l.get(i));
                        }
                }

                if (sql_str == null)
                {
                        return;
                }

                try
                {
                        HibernateDAO.delete(sql_str);
                }
                catch (ULMSSysException e)
                {
                        throw new PostDAOSysException(e);
                }
        }

        public void removePostByParentID(List l) throws PostDAOSysException
        {
                String sql_str = null;

                if (l.size() > 0)
                {
                        sql_str = "from PostModel where parentID = " + l.get(0);

                        for (int i = 1; i < l.size(); i++)
                        {
                                sql_str += ("or parentID=" + l.get(i));
                        }
                }

                if (sql_str == null)
                {
                        return;
                }

                try
                {
                        HibernateDAO.delete(sql_str);
                }
                catch (ULMSSysException e)
                {
                        throw new PostDAOSysException(e);
                }
        }

        public PostModel getPost(int postID) throws PostDAOSysException
        {
                List lt = new ArrayList();
                PostModel pm = new PostModel();

                String hql = "from PostModel where postID='" + postID + "'";

                try
                {
                        lt = HibernateDAO.find(hql);
                }
                catch (ULMSSysException e)
                {
                        throw new PostDAOSysException(e);
                }

                for (int i = 0; i < lt.size(); i++)
                {
                        pm = (PostModel) lt.get(i);
                }

                return pm;
        }

        public List getPostByParentID(int parentID) throws PostDAOSysException
        {
                List lt = new ArrayList();

                String hql = "from PostModel where parentID=" + parentID;

                try
                {
                        lt = HibernateDAO.find(hql);
                }
                catch (ULMSSysException e)
                {
                        throw new PostDAOSysException(e);
                }

                return lt;
        }

        //------------------------------PostTutorialModel-------------------------------------------------------------//
        public void addPostTutorial(PostTutorialModel ptm)
                throws PostDAOSysException
        {
                try
                {
                        HibernateDAO.add(ptm);
                }
                catch (ULMSSysException e)
                {
                        throw new PostDAOSysException(e);
                }
        }

        public void updatePostTutorial(PostTutorialModel ptm)
                throws PostDAOSysException
        {
                try
                {
                        HibernateDAO.update(ptm);
                }
                catch (ULMSSysException e)
                {
                        throw new PostDAOSysException(e);
                }
        }

        public void removePostTutorial(List l) throws PostDAOSysException
        {
                String sql_str = null;

                if (l.size() > 0)
                {
                        sql_str = "from PostTutorialModel where PostTutorialID = " +
                                l.get(0);

                        for (int i = 1; i < l.size(); i++)
                        {
                                sql_str += ("or PostTutorialID = '" + l.get(i) + "'");
                        }
                }

                if (sql_str == null)
                {
                        return;
                }

                try
                {
                        HibernateDAO.delete(sql_str);
                }
                catch (ULMSSysException e)
                {
                        throw new PostDAOSysException(e);
                }
        }

        public PostTutorialModel getPostTutorial(int PostTutorialID)
                throws PostDAOSysException
        {
                List lt = new ArrayList();
                PostTutorialModel pm = new PostTutorialModel();

                String hql = "from PostTutorialModel where PostTutorialID='" +
                        PostTutorialID + "'";

                try
                {
                        lt = HibernateDAO.find(hql);
                }
                catch (ULMSSysException e)
                {
                        throw new PostDAOSysException(e);
                }

                for (int i = 0; i < lt.size(); i++)
                {
                        pm = (PostTutorialModel) lt.get(i);
                }

                return pm;
        }

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
                                       int sort) throws PostDAOSysException
        {
                String hql = "from PostTutorialModel where relationID=" + relationID +
                        " and type=" + type;

                if (tutorialID != -1)
                {
                        hql = hql + " and tutorialID=" + tutorialID;
                }

                if (sort != -1)
                {
                        hql = hql + " and sort=" + sort;
                }

                try
                {
                        HibernateDAO.delete(hql);
                }
                catch (ULMSSysException e)
                {
                        throw new PostDAOSysException(e);
                }
        }

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
                                    int sort) throws PostDAOSysException
        {
                List lt = new ArrayList();

                String hql = "from PostTutorialModel where relationID=" + relationID +
                        " and type=" + type;

                if (tutorialID != -1)
                {
                        hql = hql + " and tutorialID=" + tutorialID;
                }

                if (sort != -1)
                {
                        hql = hql + " and sort=" + sort;
                }

                try
                {
                        lt = HibernateDAO.find(hql);
                }
                catch (ULMSSysException e)
                {
                        throw new PostDAOSysException(e);
                }

                return lt;
        }

        //-----------------------------------------PostDirModel-------------------------------------------------------//
        public void addPostDir(PostDirModel pdm) throws PostDAOSysException
        {
                try
                {
                        HibernateDAO.add(pdm);
                }
                catch (ULMSSysException e)
                {
                        throw new PostDAOSysException(e);
                }
        }

        public void updatePostDir(PostDirModel pdm) throws PostDAOSysException
        {
                try
                {
                        HibernateDAO.update(pdm);
                }
                catch (ULMSSysException e)
                {
                        throw new PostDAOSysException(e);
                }
        }

        public void removePostDir(List l) throws PostDAOSysException
        {
                String sql_str = null;

                if (l.size() > 0)
                {
                        sql_str = "from PostDirModel where dirID = " + l.get(0);

                        for (int i = 1; i < l.size(); i++)
                        {
                                sql_str += ("or dirID = '" + l.get(i) + "'");
                        }
                }

                if (sql_str == null)
                {
                        return;
                }

                try
                {
                        HibernateDAO.delete(sql_str);
                }
                catch (ULMSSysException e)
                {
                        throw new PostDAOSysException(e);
                }
        }

        public PostDirModel getPostDir(int DirID) throws PostDAOSysException
        {
                List lt = new ArrayList();
                PostDirModel pm = new PostDirModel();

                String hql = "from PostDirModel where dirID='" + DirID + "'";

                try
                {
                        lt = HibernateDAO.find(hql);
                }
                catch (ULMSSysException e)
                {
                        throw new PostDAOSysException(e);
                }

                for (int i = 0; i < lt.size(); i++)
                {
                        pm = (PostDirModel) lt.get(i);
                }

                return pm;
        }

        public List getSubPostDir(int DirID, int type) throws PostDAOSysException
        {
                List sub = new ArrayList();

                String hql = "from PostDirModel where parentID=" + DirID +
                        " and type=" + type;

                try
                {
                        sub = HibernateDAO.find(hql);
                }
                catch (ULMSSysException e)
                {
                        throw new PostDAOSysException(e);
                }

                return sub;
        }

        public PostDirModel getRootPostDir(int type) throws PostDAOSysException
        {
                List sub = new ArrayList();

                String hql = "from PostDirModel where parentID=-1 and type=" + type;

                PostDirModel pdm = null;

                try
                {
                        sub = HibernateDAO.find(hql);
                }
                catch (ULMSSysException e)
                {
                        throw new PostDAOSysException(e);
                }

                for (int i = 0; i < sub.size(); i++)
                {
                        pdm = (PostDirModel) sub.get(i);
                }

                return pdm;
        }

        public List getPostTree(int parentID, int level) throws PostDAOSysException
        {
                List returnList = new ArrayList();
                PostForm form = null;
                PostModel model = null;
                String hql = " from PostModel where PostID >= 0 ";

                if (level >= 0)
                {
                        hql += (" and parentID = " + parentID);
                }

                try
                {
                        LogUtil.debug("PostDAOImpl ============================",
                                "hql =============" + hql);

                        List tempList = HibernateDAO.find(hql);

                        for (int i = 0; i < tempList.size(); i++)
                        {
                                model = (PostModel) tempList.get(i);
                                form = new PostForm(model);
                                returnList.add(form);
                        }
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                }

                return returnList;
        }
}
