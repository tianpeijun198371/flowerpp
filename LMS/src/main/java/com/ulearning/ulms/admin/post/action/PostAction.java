/**
 * PostAction.java.
 * User: shid Date: 2005-7-21 19:59:26
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.admin.post.action;

import com.ulearning.ulms.admin.post.exceptions.PostAppException;
import com.ulearning.ulms.admin.post.form.PostDirForm;
import com.ulearning.ulms.admin.post.form.PostForm;
import com.ulearning.ulms.admin.post.helper.PostHelper;
import com.ulearning.ulms.admin.post.util.PostConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class PostAction extends DispatchAction
{
        public ActionForward execute(ActionMapping actionMapping,
                                     ActionForm actionForm, HttpServletRequest httpServletRequest,
                                     HttpServletResponse httpServletResponse) throws Exception
        {
                return super.execute(actionMapping, actionForm, httpServletRequest,
                        httpServletResponse);
        }

        public ActionForward addPost(ActionMapping actionMapping,
                                     ActionForm actionForm, HttpServletRequest httpServletRequest,
                                     HttpServletResponse httpServletResponse) throws Exception
        {
                PostForm pf = (PostForm) actionForm;

                try
                {
                        PostHelper.addPost(pf);
                }
                catch (PostAppException e)
                {
                        throw new PostAppException(e);
                }

                ActionForward forward = actionMapping.findForward("success");
                StringBuffer bf = new StringBuffer(forward.getPath());

                //bf.append("?parentID=" + parentID + "&type=" + type + "&relationID=" + relationID);
                return new ActionForward(bf.toString(), true);

                //return actionMapping.findForward("success");
        }

        public ActionForward updatePost(ActionMapping actionMapping,
                                        ActionForm actionForm, HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse) throws Exception
        {
                PostForm pf = (PostForm) actionForm;

                try
                {
                        PostHelper.updatePost(pf);
                }
                catch (PostAppException e)
                {
                        throw new PostAppException(e);
                }

                return actionMapping.findForward("success");
        }

        public ActionForward removePost(ActionMapping actionMapping,
                                        ActionForm actionForm, HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse) throws Exception
        {
                List post = new ArrayList();
                List dir = new ArrayList();
                String[] postids = httpServletRequest.getParameterValues("postIDs");

                for (int i = 0; i < postids.length; i++)
                {
                        String[] temp = postids[i].split("_");

                        if (temp[1].equals(PostConstants.DIR))
                        {
                                dir.add(temp[0]);
                        }
                        else if (temp[1].equals(PostConstants.POST))
                        {
                                post.add(temp[0]);
                        }
                }

                try
                {
                        PostHelper.removePost(post);
                        PostHelper.removePostDir(dir);
                }
                catch (PostAppException e)
                {
                        throw new PostAppException(e);
                }

                return actionMapping.findForward("success");
        }

        public ActionForward addPostDir(ActionMapping actionMapping,
                                        ActionForm actionForm, HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse) throws Exception
        {
                PostDirForm pf = (PostDirForm) actionForm;

                try
                {
                        PostHelper.addPostDir(pf);
                }
                catch (PostAppException e)
                {
                        throw new PostAppException(e);
                }

                ActionForward forward = actionMapping.findForward("success");
                StringBuffer bf = new StringBuffer(forward.getPath());

                //bf.append("?parentID=" + parentID + "&type=" + type + "&relationID=" + relationID);
                return new ActionForward(bf.toString(), true);

                //return actionMapping.findForward("success");
        }

        public ActionForward updatePostDir(ActionMapping actionMapping,
                                           ActionForm actionForm, HttpServletRequest httpServletRequest,
                                           HttpServletResponse httpServletResponse) throws Exception
        {
                PostDirForm pf = (PostDirForm) actionForm;

                try
                {
                        PostHelper.updatePostDir(pf);
                }
                catch (PostAppException e)
                {
                        throw new PostAppException(e);
                }

                ActionForward forward = actionMapping.findForward("success");
                StringBuffer bf = new StringBuffer(forward.getPath());

                //bf.append("?parentID=" + parentID + "&type=" + type + "&relationID=" + relationID);
                return new ActionForward(bf.toString(), true);

                //return actionMapping.findForward("success");
        }

        public ActionForward removePostDir(ActionMapping actionMapping,
                                           ActionForm actionForm, HttpServletRequest httpServletRequest,
                                           HttpServletResponse httpServletResponse) throws Exception
        {
                List lt = new ArrayList();
                String[] postids = httpServletRequest.getParameterValues("postIDs");

                for (int i = 0; i < postids.length; i++)
                {
                        lt.add(postids[i]);
                }

                try
                {
                        PostHelper.removePost(lt);
                }
                catch (PostAppException e)
                {
                        throw new PostAppException(e);
                }

                return actionMapping.findForward("success");
        }
}
