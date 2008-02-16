/**
 * PostHelper.java.
 * User: shid Date: 2005-7-21 16:45:35
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.admin.post.helper;

import com.ulearning.ulms.admin.post.dao.PostDAO;
import com.ulearning.ulms.admin.post.dao.PostDAOFactory;
import com.ulearning.ulms.admin.post.exceptions.PostAppException;
import com.ulearning.ulms.admin.post.exceptions.PostDAOSysException;
import com.ulearning.ulms.admin.post.form.PostDirForm;
import com.ulearning.ulms.admin.post.form.PostForm;
import com.ulearning.ulms.admin.post.model.PostDirModel;
import com.ulearning.ulms.admin.post.model.PostModel;
import com.ulearning.ulms.admin.post.util.PostConstants;

import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;

import java.util.ArrayList;
import java.util.List;


public class PostHelper
{
        private static PostDAO postDAO;

        static
        {
                try
                {
                        postDAO = PostDAOFactory.getDAO();
                }
                catch (Exception ex)
                {
                        ex.printStackTrace();
                }
        }

        /**
         * copy properties.
         *
         * @param dest
         * @param orig
         */
        private static void copyProperites(java.lang.Object dest,
                                           java.lang.Object orig) throws PostAppException
        {
                try
                {
                        BeanUtils.copyProperties(dest, orig);
                }
                catch (IllegalAccessException e)
                {
                        throw new PostAppException(e);
                }
                catch (InvocationTargetException e)
                {
                        throw new PostAppException(e);
                }
        }

        //-------------------------------------------PostDirModel------------------------------------------------------//
        public static PostDirModel getPostDir(int dirID)
        {
                PostDirModel pdm = new PostDirModel();

                try
                {
                        pdm = postDAO.getPostDir(dirID);
                }
                catch (PostDAOSysException e)
                {
                }

                return pdm;
        }

        public static void addPostDir(PostDirForm pdf) throws PostAppException
        {
                PostDirModel pdm = new PostDirModel();

                try
                {
                        copyProperites(pdm, pdf);

                        if (postDAO.getRootPostDir(Integer.parseInt(
                                PostConstants.TM_POSTDIR_TAB_TYPE)) == null)
                        {
                                pdm.setParentID(-1);
                        }

                        postDAO.addPostDir(pdm);
                }
                catch (PostAppException e)
                {
                        throw new PostAppException(e);
                }
                catch (PostDAOSysException e)
                {
                        throw new PostAppException(e);
                }
        }

        public static PostDirModel getRootPostDir(String type)
        {
                PostDirModel pdm = null;

                try
                {
                        pdm = postDAO.getRootPostDir(Integer.parseInt(type));
                }
                catch (PostDAOSysException e)
                {
                }

                return pdm;
        }

        public static void updatePostDir(PostDirForm pdf) throws PostAppException
        {
                try
                {
                        PostDirModel pdm = postDAO.getPostDir(pdf.getDirID());
                        pdm.setDescribe(pdf.getDescribe());
                        pdm.setTitle(pdf.getTitle());
                        postDAO.updatePostDir(pdm);
                }
                catch (PostDAOSysException e)
                {
                        throw new PostAppException(e);
                }
        }

        /**
         * list中内容为12_3, 12表示dirID,3表示type
         *
         * @param lt
         */
        public static void removePostDir(List lt) throws PostAppException
        {
                for (int i = 0; i < lt.size(); i++)
                {
                        String temp = lt.get(i).toString();

                        removePostDir(Integer.parseInt(temp),
                                Integer.parseInt(PostConstants.TM_POSTDIR_TAB_TYPE));
                }
        }

        public static void removePostDir(int dirID, int type)
                throws PostAppException
        {
                List lt = new ArrayList();

                try
                {
                        lt = postDAO.getSubPostDir(dirID, type);
                }
                catch (PostDAOSysException e)
                {
                        throw new PostAppException(e);
                }

                for (int i = 0; i < lt.size(); i++)
                {
                        PostDirModel pdm = (PostDirModel) lt.get(i);
                        removePostDir(pdm.getDirID(), type);
                }

                List a = new ArrayList();
                a.add(String.valueOf(dirID));

                try
                {
                        postDAO.removePostDir(a);
                        postDAO.removePostByParentID(a);
                }
                catch (PostDAOSysException e)
                {
                        throw new PostAppException(e);
                }
        }

        public static List getSubPostDir(int dirID, int type)
        {
                List lt = new ArrayList();

                try
                {
                        lt = postDAO.getSubPostDir(dirID, type);
                }
                catch (PostDAOSysException e)
                {
                }

                return lt;
        }

        //----------------------------------------PostModel-----------------------------------------------------------//
        public static List getPostByParentID(int parentID)
        {
                List l = new ArrayList();

                try
                {
                        l = postDAO.getPostByParentID(parentID);
                }
                catch (PostDAOSysException e)
                {
                }

                return l;
        }

        public static PostModel getPost(int postID)
        {
                PostModel pm = new PostModel();

                try
                {
                        pm = postDAO.getPost(postID);
                }
                catch (PostDAOSysException e)
                {
                }

                return pm;
        }

        public static void addPost(PostForm pdf) throws PostAppException
        {
                PostModel pdm = new PostModel();

                try
                {
                        copyProperites(pdm, pdf);
                        postDAO.addPost(pdm);
                }
                catch (PostAppException e)
                {
                        throw new PostAppException(e);
                }
                catch (PostDAOSysException e)
                {
                        throw new PostAppException(e);
                }
        }

        public static void updatePost(PostForm pdf) throws PostAppException
        {
                try
                {
                        PostModel pdm = postDAO.getPost(pdf.getPostID());
                        pdm.setDescribe(pdf.getDescribe());
                        pdm.setName(pdf.getName());
                        pdm.setPostLevel(pdf.getPostLevel());
                        pdm.setPostType(pdf.getPostType());
                        pdm.setRequire(pdf.getRequire());
                        pdm.setSkill(pdf.getSkill());
                        postDAO.updatePost(pdm);
                }
                catch (PostDAOSysException e)
                {
                        throw new PostAppException(e);
                }
        }

        public static void removePost(List lt) throws PostAppException
        {
                try
                {
                        postDAO.removePost(lt);
                }
                catch (PostDAOSysException e)
                {
                        throw new PostAppException(e);
                }
        }

        public static List getPostTree(int parentID, int level)
                throws PostAppException
        {
                List returnLit = null;

                try
                {
                        returnLit = postDAO.getPostTree(parentID, level);
                }
                catch (PostDAOSysException e)
                {
                        throw new PostAppException(e);
                }

                return returnLit;
        }
}
