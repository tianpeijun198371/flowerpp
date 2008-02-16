/**
 * NewDocumentAction.java.
 * User: Administrator  Date: 2005-3-8
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.newdocument.action;


import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.core.util.*;
import com.ulearning.ulms.tools.newdocument.bean.NewDocumentHelper;
import com.ulearning.ulms.tools.newdocument.exceptions.NewDocumentAppException;
import com.ulearning.ulms.tools.newdocument.form.NewDocumentCatalogForm;
import com.ulearning.ulms.tools.newdocument.form.NewDocumentForm;
import com.ulearning.ulms.tools.newdocument.dao.NewDocumentDAOImpl;
import com.ulearning.ulms.tools.newdocument.model.NewDocumentModel;
import com.ulearning.ulms.tools.newdocument.util.NewDocumentConstants;
import com.ulearning.ulms.tools.upload.action.UploadAction;
import com.ulearning.ulms.tools.upload.model.UploadForm;
import com.ulearning.ulms.user.dao.UserDAOFactory;
import com.ulearning.ulms.util.LMSConstants;
import com.ulearning.ulms.util.log.LogUtil;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;


public class NewDocumentAction extends DispatchAction
{
        public ActionForward execute(ActionMapping actionMapping,
                                     ActionForm actionForm,
                                     HttpServletRequest httpServletRequest,
                                     HttpServletResponse httpServletResponse)
                throws Exception
        {
                return super.execute(actionMapping, actionForm, httpServletRequest, httpServletResponse);

        }

        /**
         * @param actionMapping
         * @param actionForm
         * @param httpServletRequest
         * @param httpServletResponse
         * @return
         * @throws Exception
         */
        public ActionForward addDocument(ActionMapping actionMapping,
                                         ActionForm actionForm,
                                         HttpServletRequest httpServletRequest,
                                         HttpServletResponse httpServletResponse) throws Exception
        {
                LogUtil.debug("course", "[NewDocumentAction]-----adddDocument start!");
                String resultScreen = "success";
                NewDocumentForm newDocumentForm = (NewDocumentForm) actionForm;
                String fromPortal = httpServletRequest.getParameter("fromPortal"); //从门户的自我推荐和留言板添加内容
                int docID = Integer.parseInt(httpServletRequest.getParameter("docID"));
                //System.out.println("docID ======NewDocumentAction================ " + docID);
                String projectName = com.ulearning.ulms.core.util.Config.getProjectName();
                if (!projectName.equals("XLN"))
                {
                    if (NewDocumentHelper.documentReName(newDocumentForm.getDocName(), newDocumentForm.getType()))
                    {
                            throw (new NewDocumentAppException("名称已存在！请重新输入"));
                    }
                }
                if (httpServletRequest.getContentType().startsWith("multipart/form-data"))
                {
                        try
                        {
                                UploadUtil.executeUpload(newDocumentForm,
                                        httpServletRequest, httpServletResponse);

                        }
                        catch (Exception e)
                        {
                                e.printStackTrace();
                                LogUtil.debug("system", "[UpdateAssignmentAction] Exeception====================" + e);
                                throw e;
                        }
                        LogUtil.info("course", "[NewDocumentAction]===========1");
                        if (StringUtil.parseInt(newDocumentForm.getType()) == NewDocumentConstants.NEWS_PIC)
                        {
                                String tmp = StringUtils.trimToEmpty((String) httpServletRequest.getAttribute("newFilePath"));
                                newDocumentForm.setLink(tmp);
                                tmp = StringUtils.trimToEmpty((String) httpServletRequest.getAttribute("newFilePath1"));
                                newDocumentForm.setRemark(tmp);
                                tmp = StringUtils.trimToEmpty((String) httpServletRequest.getAttribute("newFilePath2"));
                                newDocumentForm.setRemark2(tmp);
                                tmp = StringUtils.trimToEmpty((String) httpServletRequest.getAttribute("newFilePath3"));
                                newDocumentForm.setRemark3(tmp);
                                tmp = StringUtils.trimToEmpty((String) httpServletRequest.getAttribute("newFilePath4"));
                                newDocumentForm.setRemark4(tmp);
                                tmp = StringUtils.trimToEmpty((String) httpServletRequest.getAttribute("newFilePath5"));
                                newDocumentForm.setRemark5(tmp);
                                int contentSize = StringUtil.parseInt((String) httpServletRequest.getParameter("size"));
                                newDocumentForm.setContentSize(10);
                        }
                        else
                        {
                                if (!String.valueOf(httpServletRequest.getParameter("size")).equals("0"))
                                {
                                        newDocumentForm.setLink((String) httpServletRequest.getAttribute("newFilePath"));
                                        int contentSize = StringUtil.parseInt((String) httpServletRequest.getParameter("size"));
                                        newDocumentForm.setContentSize(10);
                                }
                                else if (ValidateUtil.isEmpty((String) (httpServletRequest.getAttribute("fileName"))))
                                {
                                        newDocumentForm.setLink("-1");
                                        newDocumentForm.setContentSize(0);
                                }
                                else
                                {
                                        newDocumentForm.setLink("");
                                }
                        }
                }

                int userID = StringUtil.parseInt((String) httpServletRequest.getSession().getAttribute(LMSConstants.USERID_KEY));
                String userName = StringUtil.nullToStr((String) httpServletRequest.getSession().getAttribute(LMSConstants.LOGINNAME_KEY_NAME));
                if (userName.equals(""))
                {
                        userName = StringUtil.nullToStr((String) httpServletRequest.getSession().getAttribute(LMSConstants.LOGINNAME_KEY));
                }
                newDocumentForm.setUserID(userID);
                Date endDate = DateTimeUtil.parseDate(newDocumentForm.getEndDate());
                Date beginDate = DateTimeUtil.parseDate(newDocumentForm.getBeginDate());
                newDocumentForm.setDisplayBeginDate(beginDate);
                newDocumentForm.setDisplayEndDate(endDate);
                if (userID == 0) //从门户的自我推荐和留言板添加内容
                {
                        newDocumentForm.setUserName("访客");
                }
                else
                {
                        newDocumentForm.setUserName(userName);
                }

                //System.out.println("[NewDocumentAction]addDocument  newDocumentForm.getType() = " + newDocumentForm.getType());
                //System.out.println("[NewDocumentAction]addDocument  (StringUtil.parseInt(newDocumentForm.getType())== NewDocumentConstants.EXPERT) = " + (StringUtil.parseInt(newDocumentForm.getType())== NewDocumentConstants.EXPERT));
                //System.out.println("[NewDocumentAction]addDocument  httpServletRequest.getParameter(\"isViewInLearningPortal\") = " + httpServletRequest.getParameter("isViewInLearningPortal"));
                //针对专家库
                if (StringUtil.parseInt(newDocumentForm.getType()) == NewDocumentConstants.EXPERT)
                {
                        if (httpServletRequest.getParameter("isViewInLearningPortal") != null)
                        {
                                newDocumentForm.setIsOpenGuest("1");
                        }
                        else
                        {
                                newDocumentForm.setIsOpenGuest("0");
                        }
                }
                //System.out.println("[NewDocumentAction]addDocument  newDocumentForm.getIsOpenGuest() = " + newDocumentForm.getIsOpenGuest());
                NewDocumentHelper.insertDocument(newDocumentForm);

/*                if (newDocumentForm.getType() != null && Integer.parseInt(newDocumentForm.getType()) == NewDocumentConstants.ZHAOSHENG_JIANZHANG_TYPE)
                {
                        newDocumentForm.setOrderIndex(NewDocumentHelper.getMaxOrderIndex() + 1);
                }

                NewDocumentHelper.insertDocument(newDocumentForm);
                LogUtil.info("system", "[NewDocumentAction]===========resultScreen = "
                        + resultScreen);
                if (newDocumentForm.getType() != null && newDocumentForm.getType().equals(NewDocumentConstants.PAPER_COMMENT_TEMPLATE_TYPE + ""))
                {
                        String id = httpServletRequest.getParameter("id");
                        String name = httpServletRequest.getParameter("name");
                        ActionForward inforward = actionMapping.findForward(resultScreen);
                        StringBuffer bf = new StringBuffer(inforward.getPath());
                        bf.append("?id=" + id + "&name=" + name);
                        LogUtil.debug("tools", "[NewDocumentAction]-----addDocument start:url=" + bf.toString());
                        return new ActionForward(bf.toString(), true);
                }
                else
                {*/

                if (fromPortal != null && fromPortal.equals("true"))
                {
                        ActionForward forward = actionMapping.findForward("successPortal");
                        StringBuffer bf = new StringBuffer(forward.getPath());
                        bf.append("?type=" + newDocumentForm.getType() + "&parentType=" + newDocumentForm.getParentType());
                        return new ActionForward(bf.toString());
                }
                else
                {
                        ActionForward forward = actionMapping.findForward("success");
                        StringBuffer bf = new StringBuffer(forward.getPath());
                        bf.append("?type=" + newDocumentForm.getType() + "&parentType=" + newDocumentForm.getParentType());
                        if (Integer.parseInt(newDocumentForm.getType()) == 1801)
                        {
                                bf.append("&docID=" + docID);
                        }
                        return new ActionForward(bf.toString());
                }
                //}

        }

        /**
         * @param actionMapping
         * @param actionForm
         * @param httpServletRequest
         * @param httpServletResponse
         * @return
         * @throws Exception
         */
        public ActionForward addCatalog(ActionMapping actionMapping,
                                        ActionForm actionForm,
                                        HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse) throws Exception
        {
                LogUtil.debug("course", "[NewDocumentAction]-----adddDocument start!");
                String resultScreen = "success";
                NewDocumentCatalogForm newDocumentForm = (NewDocumentCatalogForm) actionForm;
                try
                {
                        NewDocumentHelper.insertCatalog(newDocumentForm);
                }
                catch (ULMSSysException ex)
                {
                        LogUtil.debug("tools", "[NewDocumentAction]=======while addCatalog() start!");
                        ex.printStackTrace();
                }
                LogUtil.info("system", "[NewDocumentAction]===========resultScreen = "
                        + resultScreen);
                return actionMapping.findForward("success");
        }

        /**
         * @param actionMapping
         * @param actionForm
         * @param httpServletRequest
         * @param httpServletResponse
         * @return
         * @throws Exception
         */
        public ActionForward delDocument(ActionMapping actionMapping,
                                         ActionForm actionForm,
                                         HttpServletRequest httpServletRequest,
                                         HttpServletResponse httpServletResponse) throws Exception
        {

                String resultScreen = "success";
                ArrayList al = new ArrayList();
                NewDocumentForm newDocumentForm = (NewDocumentForm) actionForm;
                int docID = Integer.parseInt(httpServletRequest.getParameter("docID"));
                //System.out.println("docID ======NewDocumentAction================ " + docID);
                String documentIDs[] = httpServletRequest.getParameterValues("documentIDs");
                String type = httpServletRequest.getParameter("type");
                String parentType = httpServletRequest.getParameter("parentType");
                if (documentIDs != null)
                {
                        for (int i = 0; i < documentIDs.length; i++)
                        {
                                al.add(new Integer(documentIDs[i]));
                        }
                }

/*                if (type != null && Integer.parseInt(type) == NewDocumentConstants.ZHAOSHENG_JIANZHANG_TYPE)
                {
                        NewDocumentHelper.delDocumentForOrderIndex(al);
                }
                else
                {*/
                NewDocumentHelper.delDocument(al);
/*                }
                if (type != null && type.equals(NewDocumentConstants.PAPER_COMMENT_TEMPLATE_TYPE + ""))
                {
                        String id = httpServletRequest.getParameter("id");
                        String name = httpServletRequest.getParameter("name");
                        ActionForward inforward = actionMapping.findForward(resultScreen);
                        StringBuffer bf = new StringBuffer(inforward.getPath());
                        bf.append("?id=" + id + "&name=" + name);
                        LogUtil.debug("tools", "[NewDocumentAction]-----delDocument start:url=" + bf.toString());
                        return new ActionForward(bf.toString(), true);
                }
                else
                {*/
                //return actionMapping.findForward("success");
                //}
                ActionForward forward = actionMapping.findForward("success");
                StringBuffer bf = new StringBuffer(forward.getPath());
                bf.append("?type=" + type + "&parentType=" + parentType);
                if (Integer.parseInt(newDocumentForm.getType()) == 1801)
                {
                        bf.append("&docID=" + docID);
                }
                return new ActionForward(bf.toString());
        }

        /**
         * @param actionMapping
         * @param actionForm
         * @param httpServletRequest
         * @param httpServletResponse
         * @return
         * @throws Exception
         */
        public ActionForward delCatalog(ActionMapping actionMapping,
                                        ActionForm actionForm,
                                        HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse) throws Exception
        {

                String resultScreen = "success";
                String catalogID = httpServletRequest.getParameter("catalogID");
                try
                {
                        NewDocumentHelper.deleteCatalog(catalogID);
                }
                catch (ULMSSysException ex)
                {
                        LogUtil.debug("tools", "[NewDocumentAction]=======while delCatalog() start!");
                        ex.printStackTrace();
                }
                return actionMapping.findForward(resultScreen);
        }

        /**
         * @param actionMapping
         * @param actionForm
         * @param httpServletRequest
         * @param httpServletResponse
         * @return
         * @throws Exception
         */
        public ActionForward updDocument(ActionMapping actionMapping,
                                         ActionForm actionForm,
                                         HttpServletRequest httpServletRequest,
                                         HttpServletResponse httpServletResponse) throws Exception
        {

                LogUtil.debug("course", "[NewDocumentAction]-----updDocument start!");
                String resultScreen = "success";
                NewDocumentForm newDocumentForm = (NewDocumentForm) actionForm;
                int docID = Integer.parseInt(httpServletRequest.getParameter("docID"));
                //System.out.println("docID ======NewDocumentAction================ " + docID);
                if (httpServletRequest.getContentType().startsWith("multipart/form-data"))
                {
                        try
                        {
                                UploadUtil.executeUpload(newDocumentForm,
                                        httpServletRequest, httpServletResponse);

                        }
                        catch (Exception e)
                        {
                                e.printStackTrace();
                                LogUtil.debug("system", "[UpdateAssignmentAction] Exeception====================" + e);
                                throw e;
                        }
                        LogUtil.info("course", "[NewDocumentAction]===========1");
                        String parameter = httpServletRequest.getParameter("isDelFile");
                        LogUtil.info("system", "[NewDocumentAction]updDocument===========parameter = "
                                + parameter);
                        parameter = StringUtil.nullToStr(parameter);
                        LogUtil.info("system", "[NewDocumentAction]updDocument===========parameter = "
                                + parameter);

                        if (StringUtil.parseInt(newDocumentForm.getType()) == NewDocumentConstants.NEWS_PIC)
                        {
                                String tmp = StringUtils.trimToEmpty((String) httpServletRequest.getAttribute("newFilePath"));
                                newDocumentForm.setLink(tmp);
                                tmp = StringUtils.trimToEmpty((String) httpServletRequest.getAttribute("newFilePath1"));
                                newDocumentForm.setRemark(tmp);
                                tmp = StringUtils.trimToEmpty((String) httpServletRequest.getAttribute("newFilePath2"));
                                newDocumentForm.setRemark2(tmp);
                                tmp = StringUtils.trimToEmpty((String) httpServletRequest.getAttribute("newFilePath3"));
                                newDocumentForm.setRemark3(tmp);
                                tmp = StringUtils.trimToEmpty((String) httpServletRequest.getAttribute("newFilePath4"));
                                newDocumentForm.setRemark4(tmp);
                                tmp = StringUtils.trimToEmpty((String) httpServletRequest.getAttribute("newFilePath5"));
                                newDocumentForm.setRemark5(tmp);
                                newDocumentForm.setContentSize(10);
                        }
                        else
                        {
                                if (parameter.equals("1"))
                                { //删除原来的附件
                                        LogUtil.info("system", "[NewDocumentAction]updDocument===========删除原来的附件");
                                        try
                                        {
                                                String pPath = Config.getUploadPhysicalPath() + newDocumentForm.getLink();
                                                pPath = StringUtil.replaceString(pPath, "\\", File.separator);
                                                pPath = StringUtil.replaceString(pPath, "/", File.separator);
                                                LogUtil.info("system", "[NewDocumentAction]updDocument===========pPath = "
                                                        + pPath);
                                                File deleteFile = new File(pPath);
                                                if (deleteFile.isFile())
                                                {
                                                        deleteFile.delete();
                                                }
                                                LogUtil.info("system", "[NewDocumentAction]updDocument===========delete file success!!!");
                                        }
                                        catch (Exception ex)
                                        {
                                                ex.printStackTrace();
                                        }
                                        newDocumentForm.setLink("");
                                        newDocumentForm.setContentSize(0);

                                }
                                else
                                {
                                        LogUtil.info("system", "[NewDocumentAction]updDocument===========size=" + httpServletRequest.getAttribute("size"));
                                        LogUtil.info("system", "[NewDocumentAction]updDocument===========isEmpty=" + (ValidateUtil.isEmpty((String) (httpServletRequest.getAttribute("fileName")))));
                                        LogUtil.info("system", "[NewDocumentAction]updDocument===========fileName=" + ((String) (httpServletRequest.getAttribute("fileName"))));
                                        if (httpServletRequest.getAttribute("size") != null && !String.valueOf(httpServletRequest.getAttribute("size")).equals("0"))
                                        {
                                                LogUtil.info("system", "[NewDocumentAction]updDocument===========1");
                                                newDocumentForm.setLink((String) httpServletRequest.getAttribute("newFilePath"));
                                                newDocumentForm.setContentSize(StringUtil.parseInt((String) httpServletRequest.getAttribute("size")));
                                        }
                                        else
                                        if (ValidateUtil.isEmpty((String) (httpServletRequest.getAttribute("fileName"))))
                                        {
                                                LogUtil.info("system", "[NewDocumentAction]updDocument===========2");
                                                newDocumentForm.setLink("-1");
                                                newDocumentForm.setContentSize(0);
                                        }
                                }
                        }
                }
                //NewDocumentDAOImpl newDocumentDAOImpl=new NewDocumentDAOImpl();
                // NewDocumentModel docModel=newDocumentDAOImpl.getDocument(docID);
                // Date endDate =docModel.getDisplayEndDate();
                //Date beginDate=docModel.getDisplayBeginDate();
                Date endDate = DateTimeUtil.parseDate(newDocumentForm.getEndDate());
                Date beginDate = DateTimeUtil.parseDate(newDocumentForm.getBeginDate());
                newDocumentForm.setDisplayBeginDate(beginDate);
                newDocumentForm.setDisplayEndDate(endDate);

                //修改发布人姓名
                int userID = StringUtil.parseInt((String) httpServletRequest.getSession().getAttribute(LMSConstants.USERID_KEY));
                String userName = StringUtil.nullToStr((String) httpServletRequest.getSession().getAttribute(LMSConstants.LOGINNAME_KEY_NAME));
                if (userName.equals(""))
                {
                        userName = StringUtil.nullToStr((String) httpServletRequest.getSession().getAttribute(LMSConstants.LOGINNAME_KEY));
                }
                newDocumentForm.setUserID(userID);
                newDocumentForm.setUserName(userName);

                //System.out.println("[NewDocumentAction]updDocument  newDocumentForm.getType() = " + newDocumentForm.getType());
                //System.out.println("[NewDocumentAction]updDocument  (StringUtil.parseInt(newDocumentForm.getType())== NewDocumentConstants.EXPERT) = " + (StringUtil.parseInt(newDocumentForm.getType())== NewDocumentConstants.EXPERT));
                //System.out.println("[NewDocumentAction]updDocument  httpServletRequest.getParameter(\"isViewInLearningPortal\") = " + httpServletRequest.getParameter("isViewInLearningPortal"));
                //针对专家库
                if (StringUtil.parseInt(newDocumentForm.getType()) == NewDocumentConstants.EXPERT)
                {
                        if (httpServletRequest.getParameter("isViewInLearningPortal") != null)
                        {
                                newDocumentForm.setIsOpenGuest("1");
                        }
                        else
                        {
                                newDocumentForm.setIsOpenGuest("0");
                        }
                }
                //System.out.println("[NewDocumentAction]updDocument  newDocumentForm.getIsOpenGuest() = " + newDocumentForm.getIsOpenGuest());

                NewDocumentHelper.updDocument(newDocumentForm);
                LogUtil.info("system", "[NewDocumentAction]===========resultScreen = "
                        + resultScreen);
                /*if (newDocumentForm.getType() != null && newDocumentForm.getType().equals(NewDocumentConstants.PAPER_COMMENT_TEMPLATE_TYPE + ""))
                {
                        String id = httpServletRequest.getParameter("id");
                        String name = httpServletRequest.getParameter("name");
                        ActionForward inforward = actionMapping.findForward(resultScreen);
                        StringBuffer bf = new StringBuffer(inforward.getPath());
                        bf.append("?id=" + id + "&name=" + name);
                        LogUtil.debug("tools", "[NewDocumentAction]-----updDocument start:url=" + bf.toString());
                        return new ActionForward(bf.toString(), true);
                }
                else
                {*/
                //return actionMapping.findForward("success");
                //}
                ActionForward forward = actionMapping.findForward("success");
                StringBuffer bf = new StringBuffer(forward.getPath());
                bf.append("?type=" + newDocumentForm.getType() + "&parentType=" + newDocumentForm.getParentType());
                if (Integer.parseInt(newDocumentForm.getType()) == 1801)
                {
                        bf.append("&docID=" + docID);
                }
                return new ActionForward(bf.toString());
        }


        /**
         * @param actionMapping
         * @param actionForm
         * @param httpServletRequest
         * @param httpServletResponse
         * @return
         * @throws Exception
         */
        public ActionForward updCatalog(ActionMapping actionMapping,
                                        ActionForm actionForm,
                                        HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse) throws Exception
        {

                LogUtil.debug("course", "[NewDocumentAction]-----updCatalog start!");
                String resultScreen = "success";
                NewDocumentCatalogForm NewDocumentCatalogForm = (NewDocumentCatalogForm) actionForm;
                try
                {
                        NewDocumentHelper.updateCatalog(NewDocumentCatalogForm);
                }
                catch (ULMSSysException ex)
                {
                        LogUtil.debug("tools", "[NewDocumentAction]=======while updCatalog() start!");
                        ex.printStackTrace();
                }
                LogUtil.info("system", "[NewDocumentAction]===========resultScreen = "
                        + resultScreen);
                return actionMapping.findForward(resultScreen);
        }

        /**
         * 对没有附件记录的修改
         *
         * @param actionMapping
         * @param actionForm
         * @param httpServletRequest
         * @param httpServletResponse
         * @return
         * @throws Exception
         */
        public ActionForward updDocument2(ActionMapping actionMapping,
                                          ActionForm actionForm,
                                          HttpServletRequest httpServletRequest,
                                          HttpServletResponse httpServletResponse) throws Exception
        {

                LogUtil.debug("course", "[NewDocumentAction]-----updDocument start2!");
                String resultScreen = "success";
                NewDocumentForm newDocumentForm = (NewDocumentForm) actionForm;

                //修改发布人姓名
                int userID = StringUtil.parseInt((String) httpServletRequest.getSession().getAttribute(LMSConstants.USERID_KEY));
                String userName = StringUtil.nullToStr((String) httpServletRequest.getSession().getAttribute(LMSConstants.LOGINNAME_KEY_NAME));
                if (userName.equals(""))
                {
                        userName = StringUtil.nullToStr((String) httpServletRequest.getSession().getAttribute(LMSConstants.LOGINNAME_KEY));
                }
                newDocumentForm.setUserID(userID);
                newDocumentForm.setUserName(userName);
                NewDocumentHelper.updDocument(newDocumentForm);
                LogUtil.info("system", "[NewDocumentAction]===========resultScreen = "
                        + resultScreen);
                return actionMapping.findForward(resultScreen);
        }

        /**
         * @param actionMapping
         * @param actionForm
         * @param httpServletRequest
         * @param httpServletResponse
         * @return
         * @throws Exception
         */
        public ActionForward updCssOrder(ActionMapping actionMapping,
                                         ActionForm actionForm,
                                         HttpServletRequest httpServletRequest,
                                         HttpServletResponse httpServletResponse) throws Exception
        {

                String resultScreen = "success";
                ArrayList al = new ArrayList();
                String cssOrder[] = httpServletRequest.getParameterValues("cssOrder");

                if (cssOrder != null)
                {
                        for (int i = 0; i < cssOrder.length; i++)
                        {
                                al.add(cssOrder[i]);
                        }
                }
                NewDocumentHelper.updCssOrder(al);
                return actionMapping.findForward(resultScreen);
        }

        public ActionForward updCss(ActionMapping actionMapping,
                                    ActionForm actionForm,
                                    HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse) throws Exception
        {

                LogUtil.debug("course", "[NewDocumentAction]-----updDocument start2!");
                String resultScreen = "success";
                NewDocumentForm newDocumentForm = (NewDocumentForm) actionForm;
                NewDocumentHelper.updCss(newDocumentForm);
                LogUtil.info("system", "[NewDocumentAction]===========resultScreen = "
                        + resultScreen);
                return actionMapping.findForward(resultScreen);
        }

        public ActionForward changeRelease(ActionMapping actionMapping,
                                           ActionForm actionForm,
                                           HttpServletRequest httpServletRequest,
                                           HttpServletResponse httpServletResponse) throws Exception
        {
                ArrayList al = new ArrayList();
                String documentIDs[] = httpServletRequest.getParameterValues("documentIDs");
                String type = httpServletRequest.getParameter("type");
                String parentType = httpServletRequest.getParameter("parentType");
                if (documentIDs != null)
                {
                        for (int i = 0; i < documentIDs.length; i++)
                        {
                                al.add(new Integer(documentIDs[i]));
                        }
                }

                NewDocumentHelper.changeRelease(al);

                ActionForward forward = actionMapping.findForward("success");
                StringBuffer bf = new StringBuffer(forward.getPath());
                bf.append("?type=" + type + "&parentType=" + parentType);
                return new ActionForward(bf.toString());
        }
}


