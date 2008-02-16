/**
 * ContentManageAction.java.
 * User: shid Date: 2005-6-9 13:25:59
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.content.action;

import com.ulearning.ulms.content.exceptions.ContentManageAppException;
import com.ulearning.ulms.content.form.*;
import com.ulearning.ulms.content.helper.ContentManageHelper;
import com.ulearning.ulms.content.helper.ContentTreeHelper;
import com.ulearning.ulms.content.model.ContentConfigModel;
import com.ulearning.ulms.content.model.ContentLanguageModel;
import com.ulearning.ulms.content.model.ContentTypeModel;
import com.ulearning.ulms.content.model.CourseContentTypeModel;
import com.ulearning.ulms.content.util.ContentManageConstants;
import com.ulearning.ulms.core.security.bean.SecurityConstants;
import com.ulearning.ulms.core.security.bean.SecurityHelper;
import com.ulearning.ulms.core.security.form.STicket;
import com.ulearning.ulms.core.util.*;
import com.ulearning.ulms.tools.upload.model.UploadForm;
import com.ulearning.ulms.util.LMSConstants;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import java.io.IOException;

import java.lang.reflect.InvocationTargetException;

import java.text.DecimalFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ContentManageAction extends DispatchAction
{
        public ActionForward execute(ActionMapping actionMapping,
                                     ActionForm actionForm, HttpServletRequest request,
                                     HttpServletResponse response) throws Exception
        {
                return super.execute(actionMapping, actionForm, request,
                        response);
        }

        public ActionForward importCourseContent_Courseware(
                ActionMapping actionMapping, ActionForm actionForm,
                HttpServletRequest request,
                HttpServletResponse response) throws Exception
        {
                String result = "success";

                String filepath = "";
                UploadForm ccf = (UploadForm) actionForm;

                if (request.getContentType().startsWith("multipart/form-data"))
                {
                        UploadUtil.executeUpload(ccf, request,
                                response);

                        if (!String.valueOf(request.getAttribute("size"))
                                .equals("0"))
                        {
                                filepath = (String) request.getAttribute(
                                        "newFilePath");

                                //int contentSize = StringUtil.parseInt((String) request.getAttribute("size"));
                        }
                        else if (ValidateUtil.isEmpty(
                                (String) (request.getAttribute("fileName"))))
                        {
                                //ccf.setContentSize(-1);
                        }
                        else
                        {
                                //ccf.setContentSize(-1);
                        }
                }

                List err = new ArrayList();

                String path = Config.getUploadPhysicalPath() + filepath.substring(1);
                //System.out.println("path = " + path);
                err = ContentManageHelper.importCourseContent_Courseware(path,
                        Integer.parseInt(String.valueOf(request.getSession()
                                .getAttribute(LMSConstants.USER_ASPID_KEY))));

                if (err.isEmpty())
                {
                        err.add("导入成功！");
                }

                request.setAttribute("messages", err);

                return actionMapping.findForward(result);
        }

        public ActionForward advSearch(ActionMapping actionMapping,
                                       ActionForm actionForm, HttpServletRequest request,
                                       HttpServletResponse response) throws Exception
        {
                String result = "success";
                ContentAdvSearchForm asf = (ContentAdvSearchForm) actionForm;

                //当用户输入查询条件并点击确定后，所传的参数中包括一个submit，值为ContentManageConstants.IS_NEW,当点击分页的下一页时则不传入没有这个参数,
                //有这个参数时说明提交的是form，表明用户输入新的查询条件，所以将session中的值去掉。
                //System.out.println("request.getParameter(\"submit\")        --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------                   "+request.getParameter("submit"));
                if ((request.getParameter("submit") != null) &&
                        request.getParameter("submit").toString()
                                .equals(ContentManageConstants.IS_NEW))
                {
                        request.getSession().removeAttribute("advSearch");
                }

                //判断权限。管理员能查看所有资源，普通用户只能查看已发布的资源
                STicket st = (STicket) request.getSession()
                        .getAttribute(LMSConstants.TICKET_KEY);
                int secOrgID = Integer.parseInt((String) request.getSession()
                        .getAttribute(LMSConstants.USER_ORGID_KEY));

                boolean isAdmin = false; //SecurityHelper.isHasPermission(userID, SecurityConstants.DEFAULT_RELATIONID_PLATFORM, SecurityConstants.USER_PLATFORM_RELATION, SecurityConstants.SOURCE_MAINTENANCE_MANAGE);

                if (asf.getScopeType() == ContentManageConstants.PUBLIC_CONTENT_TYPE)
                {
                        isAdmin = SecurityHelper.isHasPermission(st, secOrgID,
                                SecurityConstants.USER_PLATFORM_RELATION,
                                SecurityConstants.SOURCE_PUBLIC_MANAGE);
                }
                else if (asf.getScopeType() == ContentManageConstants.COURSE_CONTENT_TYPE)
                {
                        isAdmin = SecurityHelper.isHasPermission(st, secOrgID,
                                SecurityConstants.USER_PLATFORM_RELATION,
                                SecurityConstants.SOURCE_COURSE_MANAGE);
                }
                else if (asf.getScopeType() == ContentManageConstants.All_TYPE)
                {
                        if (SecurityHelper.isHasPermission(st, secOrgID,
                                SecurityConstants.USER_PLATFORM_RELATION,
                                SecurityConstants.SOURCE_PUBLIC_MANAGE) &&
                                SecurityHelper.isHasPermission(st, secOrgID,
                                        SecurityConstants.USER_PLATFORM_RELATION,
                                        SecurityConstants.SOURCE_COURSE_MANAGE))
                        {
                                isAdmin = true;
                        }
                }

                asf.setAdmin(isAdmin);

                //当session为空时，表明用户提交的是form,则把相关数据（hql语句，日期）存到session中，当用户点击下一页时，从session中取出hql语句进行查询
                if (request.getSession().getAttribute("advSearch") == null)
                {
                        ContentAdvSearchForm sf = ContentManageHelper.advSearch(asf,
                                request);
                        String hql = sf.getHql();
                        Date begin = sf.getStartDate();
                        Date end = sf.getOverDate();
                        List content = sf.getContentModels();
                        int totalCount = sf.getTotalCount();

                        try
                        {
                                BeanUtils.copyProperties(sf, asf);
                        }
                        catch (IllegalAccessException e)
                        {
                                throw new ContentManageAppException(e);
                        }
                        catch (InvocationTargetException e)
                        {
                                throw new ContentManageAppException(e);
                        }

                        sf.setContentModels(content);
                        sf.setHql(hql);
                        sf.setStartDate(begin);
                        sf.setOverDate(end);
                        sf.setTotalCount(totalCount);
                        //System.out.println("c.totalCount()     －－－－－－－－－－－－－－－－－－－－－－－－－－－－－》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》               "+totalCount);
                        request.getSession().setAttribute("advSearch", sf);
                }
                else
                {
                        ContentAdvSearchForm c = (ContentAdvSearchForm) request.getSession()
                                .getAttribute("advSearch");
                        int pageNo = StringUtil.parseInt((String) request.getParameter(
                                "pager.offset")) / c.getPageSize();
                        ContentAdvSearchForm sf = ContentManageHelper.advSearchBySession(c.getHql(),
                                c.getStartDate(), c.getOverDate(), c.getPageSize(), pageNo);
                        String hql = sf.getHql();
                        Date begin = sf.getStartDate();
                        Date end = sf.getOverDate();
                        List content = sf.getContentModels();
                        int totalCount = sf.getTotalCount();

                        //System.out.println("c.getHql()              》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》               "+c.getHql());
                        try
                        {
                                BeanUtils.copyProperties(sf, c);
                        }
                        catch (IllegalAccessException e)
                        {
                                throw new ContentManageAppException(e);
                        }
                        catch (InvocationTargetException e)
                        {
                                throw new ContentManageAppException(e);
                        }

                        sf.setContentModels(content);
                        sf.setHql(hql);
                        sf.setStartDate(begin);
                        sf.setOverDate(end);
                        sf.setTotalCount(totalCount);

                        request.getSession().removeAttribute("advSearch");
                        request.getSession().setAttribute("advSearch", sf);
                }

                String parentID = request.getParameter("parentID");
                String relationID = request.getParameter("relationID");
                String type = request.getParameter("type");
                String browse = request.getParameter("from");

                if ((browse != null) && browse.equals("browse"))
                {
                        ActionForward forward = actionMapping.findForward("browse");
                        StringBuffer bf = new StringBuffer(forward.getPath());
                        bf.append("?parentID=" + parentID + "&relationID=" + relationID +
                                "&type=" + type);

                        return new ActionForward(bf.toString());
                }
                else if ((browse != null) && browse.equals("teacherCourse"))
                {
                        ActionForward forward = actionMapping.findForward("teacherCourse");
                        StringBuffer bf = new StringBuffer(forward.getPath());
                        bf.append("?parentID=" + parentID + "&relationID=" + relationID +
                                "&type=" + type);

                        return new ActionForward(bf.toString());
                }
                else
                {
                        ActionForward forward = actionMapping.findForward(result);
                        StringBuffer bf = new StringBuffer(forward.getPath());
                        bf.append("?parentID=" + parentID + "&relationID=" + relationID +
                                "&type=" + type);

                        return new ActionForward(bf.toString());
                }
        }

        public ActionForward move(ActionMapping actionMapping,
                                  ActionForm actionForm, HttpServletRequest request,
                                  HttpServletResponse response) throws Exception
        {
                String[] contentIDs = (String[]) request.getSession()
                        .getAttribute("moveCatalogAndContent");

                String relationID = request.getParameter("relationID");
                String type = request.getParameter("type");

                //String aspID =request.getParameter("aspID");
                String aspID = request.getSession()
                        .getAttribute(LMSConstants.USER_ASPID_KEY)
                        .toString();

                List contentList = new ArrayList();
                List catalogList = new ArrayList();

                if (contentIDs != null)
                {
                        for (int i = 0; i < contentIDs.length; i++)
                        {
                                String[] temp = contentIDs[i].toString().split("_");

                                if (temp[1].equals((String.valueOf(
                                        ContentManageConstants.displayContent))))
                                {
                                        contentList.add(temp[0]);
                                }
                                else if (temp[1].equals(
                                        (String.valueOf(
                                                ContentManageConstants.displayCatalog))))
                                {
                                        catalogList.add(temp[0]);
                                }
                        }
                }

                String parentID = request.getParameter("moveTo");

                if (ContentManageHelper.move(catalogList, contentList,
                        Integer.parseInt(parentID), -1, -1, aspID))
                {
                        request.getSession()
                                .removeAttribute("moveCatalogAndContent");

                        ActionForward forward = actionMapping.findForward("success");
                        StringBuffer bf = new StringBuffer(forward.getPath());
                        bf.append("?parentID=" + parentID + "&type=" + type +
                                "&relationID=" + relationID);

                        return new ActionForward(bf.toString(), true);
                }
                else
                {
                        request.getSession()
                                .removeAttribute("moveCatalogAndContent");
                        parentID = request.getParameter("parentID");
                        request.getSession()
                                .setAttribute("contentIDs", contentIDs);

                        ActionForward forward = actionMapping.findForward("movefail");
                        StringBuffer bf = new StringBuffer(forward.getPath());
                        bf.append("?parentID=" + parentID + "&type=" + type +
                                "&relationID=" + relationID);

                        return new ActionForward(bf.toString(), true);
                }
        }

        /**
         * ***************************************    ContentType   ***************************************************
         */
        public ActionForward updateContentType(ActionMapping actionMapping,
                                               ActionForm actionForm, HttpServletRequest request,
                                               HttpServletResponse response) throws Exception
        {
                String result = "success";
                ContentTypeForm ctf = (ContentTypeForm) actionForm;

                boolean flag = ContentManageHelper.updateContentType(ctf);

                if (flag == false)
                {
                        result = "fail";

                        ActionForward forward = actionMapping.findForward("fail");
                        StringBuffer bf = new StringBuffer(forward.getPath());
                        bf.append("?contentTypeID=" + ctf.getContentTypeID());

                        return new ActionForward(bf.toString(), true);
                }

                return actionMapping.findForward(result);
        }

        public ActionForward addContentType(ActionMapping actionMapping,
                                            ActionForm actionForm, HttpServletRequest request,
                                            HttpServletResponse response) throws Exception
        {
                ContentTypeForm ctf = (ContentTypeForm) actionForm;

                boolean flag = ContentManageHelper.addContentType(ctf);

                if (flag == false)
                {
                        return actionMapping.findForward("fail");
                }

                return actionMapping.findForward("success");
        }

        public ActionForward deleteContentType(ActionMapping actionMapping,
                                               ActionForm actionForm, HttpServletRequest request,
                                               HttpServletResponse response) throws Exception
        {
                String resultScreen = "delete";
                ArrayList al = new ArrayList();
                String[] contentTypeIDs = request.getParameterValues(
                        "contentTypeIDs");

                if (contentTypeIDs != null)
                {
                        for (int i = 0; i < contentTypeIDs.length; i++)
                        {
                                al.add(new Integer(contentTypeIDs[i]));
                        }
                }

                String aspID = request.getSession()
                        .getAttribute(LMSConstants.USER_ASPID_KEY)
                        .toString();
                List lt = ContentManageHelper.deleteContentType(al, aspID);

                List errors = new ArrayList();

                for (int i = 0; i < lt.size(); i++)
                {
                        ContentTypeModel clm = ContentManageHelper.getContentTypeByContentTypeID(Integer.parseInt(
                                lt.get(i).toString()));
                        errors.add("禁止删除正在使用中的类型：" + clm.getContentType());
                }

                request.setAttribute("usingcontent", errors);

                return actionMapping.findForward(resultScreen);
        }

        public ActionForward updateContentTypeOrderIndex(
                ActionMapping actionMapping, ActionForm actionForm,
                HttpServletRequest request,
                HttpServletResponse response) throws Exception
        {
                String resultScreen = "success";
                ArrayList al = new ArrayList();
                String[] cssOrder = request.getParameterValues("cssOrder");

                if (cssOrder != null)
                {
                        for (int i = 0; i < cssOrder.length; i++)
                        {
                                al.add(cssOrder[i]);
                        }
                }

                ContentManageHelper.updateContentTypeOrderIndex(al);

                return actionMapping.findForward(resultScreen);
        }

        /**
         * **************************************      ContentConfig       ************************************************
         */
        public ActionForward updateContentConfig(ActionMapping actionMapping,
                                                 ActionForm actionForm, HttpServletRequest request,
                                                 HttpServletResponse response) throws Exception
        {
                String resultScreen = "success";
                ContentConfigForm ccf = (ContentConfigForm) actionForm;
                List errors = new ArrayList();
                long MAX_ALLOWED_FILE_SIZE = StringUtil.parseLong(Config.get(
                        "upload-max-allowed-file-size"));

                if ((StringUtil.parseLong(ccf.getLimitUploadFileSize()) * 1024) > MAX_ALLOWED_FILE_SIZE)
                {
                        errors.add("资源上传单个文件大小不能超过平台允许上传的大小！最大不能超过" +
                                formatSize(MAX_ALLOWED_FILE_SIZE));
                        request.setAttribute("configWrong", errors);

                        return actionMapping.findForward(resultScreen);
                }

                ContentManageHelper.updateContentConfig(ccf);
                errors.add("资源配置修改成功");
                request.setAttribute("configWrong", errors);

                return actionMapping.findForward(resultScreen);
        }

        /**
         * **************************************      ContentCatalog       ************************************************
         */
        public ActionForward addContentCatalog(ActionMapping actionMapping,
                                               ActionForm actionForm, HttpServletRequest request,
                                               HttpServletResponse response) throws Exception
        {
                String result = "success";
                ContentCatalogForm ccf = (ContentCatalogForm) actionForm;
                ccf.setRemark1((String) request.getSession()
                        .getAttribute(LMSConstants.USERID_KEY));

                if (ContentManageHelper.addContentCatalog(ccf))
                {
                        String parentID = request.getParameter("parentID");
                        String relationID = request.getParameter("relationID");
                        String type = request.getParameter("type");

                        ActionForward forward = actionMapping.findForward(result);
                        StringBuffer bf = new StringBuffer(forward.getPath());
                        bf.append("?parentID=" + parentID + "&relationID=" + relationID +
                                "&type=" + type + "&addCatalog=0");

                        return new ActionForward(bf.toString(), true);
                }
                else
                {
                        String parentID = request.getParameter("parentID");
                        String relationID = request.getParameter("relationID");
                        String type = request.getParameter("type");

                        ActionForward forward = actionMapping.findForward("fail");
                        StringBuffer bf = new StringBuffer(forward.getPath());
                        bf.append("?parentID=" + parentID + "&relationID=" + relationID +
                                "&type=" + type + "&addCatalog=0");

                        return new ActionForward(bf.toString(), true);
                }
        }

        public ActionForward updateContentCatalog(ActionMapping actionMapping,
                                                  ActionForm actionForm, HttpServletRequest request,
                                                  HttpServletResponse response) throws Exception
        {
                String result = "success";
                String relationID = request.getParameter("relationID");
                String type = request.getParameter("type");
                String parentID = request.getParameter("parentID");
                ContentCatalogForm ccf = (ContentCatalogForm) actionForm;

                if (ContentManageHelper.updateContentCatalog(ccf))
                {
                        ActionForward forward = actionMapping.findForward(result);
                        StringBuffer bf = new StringBuffer(forward.getPath());
                        bf.append("?parentID=" + parentID + "&relationID=" + relationID +
                                "&type=" + type);

                        return new ActionForward(bf.toString(), true);
                }
                else
                {
                        int catalogID = ccf.getContentCatalogID();

                        ActionForward forward = actionMapping.findForward("fail");
                        StringBuffer bf = new StringBuffer(forward.getPath());
                        bf.append("?parentID=" + parentID + "&catalogID=" + catalogID +
                                "&relationID=" + relationID + "&type=" + type);

                        return new ActionForward(bf.toString(), true);
                }
        }

        /*public ActionForward deleteContentCatalog(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws Exception
        {
                String result = "success";
                ArrayList al = new ArrayList();
                String contentCatlogIDs[] = request.getParameterValues("contentCatlogIDs");
                if (contentCatlogIDs != null)
                {
                        for (int i = 0; i < contentCatlogIDs.length; i++)
                        {
                                al.add(new Integer(contentCatlogIDs[i]));
                        }
                }
                ContentManageHelper.deleteContentCatalog(al);
                return actionMapping.findForward(result);
        }*/

        /**
         * **************************************      Content       ***************************************************
         */

        public ActionForward auditContentsByAdmin(
                ActionMapping actionMapping, ActionForm actionForm,
                HttpServletRequest request,
                HttpServletResponse response) throws Exception
        {
                String result = "success";
                String[] ids=request.getParameterValues("contentIDs");
                List ids_int=new ArrayList();
                for (int i = 0; i < ids.length; i++)
                {
                        String id = ids[i];
                        String[] idss = id.split("_");
                        if (idss!=null && idss.length>=2 && idss[1].equals(String.valueOf(
                                ContentManageConstants.displayContent)))
                        {
                                ids_int.add(new Integer(NumberUtils.toInt(idss[0])));
                        }
                }

                int auditStatus = NumberUtils.toInt(request.getParameter("auditStatus"));
                ContentManageHelper.auditContentsByAdmin(ids_int,auditStatus);

                String info="审核成功！";
                request.getSession().setAttribute("info", info);

                String parentID = request.getParameter("parentID");
                String relationID = request.getParameter("relationID");
                String type = request.getParameter("type");

                String view= StringUtils.trimToEmpty(request.getParameter("view"));
                String courseContentTypeID= StringUtils.trimToEmpty(request.getParameter("courseContentTypeID"));
                int pageOffset = NumberUtils.toInt(request.getParameter("pager.offset"));
                ActionForward forward = actionMapping.findForward(result);
                StringBuffer bf=new StringBuffer(forward.getPath());

                bf.append("?parentID=" + parentID + "&relationID=" + relationID +
                        "&type=" + type + "&addFile=0&view=" + view + "&pager.offset=" + pageOffset+"&courseContentTypeID="+courseContentTypeID);

                return new ActionForward(bf.toString(), true);
        }

        public ActionForward auditContentsBySubAdmin(
                ActionMapping actionMapping, ActionForm actionForm,
                HttpServletRequest request,
                HttpServletResponse response) throws Exception
        {
                String result = "success";
                String[] ids=request.getParameterValues("contentIDs");
                List ids_int=new ArrayList();
                for (int i = 0; i < ids.length; i++)
                {
                        String id = ids[i];
                        String[] idss = id.split("_");
                        if (idss!=null && idss.length>=2 && idss[1].equals(String.valueOf(
                                ContentManageConstants.displayContent)))
                        {
                                ids_int.add(new Integer(NumberUtils.toInt(idss[0])));
                        }
                }

                int auditStatus = NumberUtils.toInt(request.getParameter("auditStatus"));
                ContentManageHelper.auditContentsBySubAdmin(ids_int,auditStatus);

                String info="审核成功！";
                request.getSession().setAttribute("info", info);

                String parentID = request.getParameter("parentID");
                String relationID = request.getParameter("relationID");
                String type = request.getParameter("type");

                String view= StringUtils.trimToEmpty(request.getParameter("view"));
                String courseContentTypeID= StringUtils.trimToEmpty(request.getParameter("courseContentTypeID"));
                int pageOffset = NumberUtils.toInt(request.getParameter("pager.offset"));
                 ActionForward forward = actionMapping.findForward(result);
                StringBuffer bf=new StringBuffer(forward.getPath());
                bf.append("?parentID=" + parentID + "&relationID=" + relationID +
                        "&type=" + type + "&addFile=0&view=" + view + "&pager.offset=" + pageOffset+"&courseContentTypeID="+courseContentTypeID);

                return new ActionForward(bf.toString(), true);
        }
        
        public ActionForward addContent(ActionMapping actionMapping,
                                        ActionForm actionForm, HttpServletRequest request,
                                        HttpServletResponse response) throws Exception
        {
                String result = "success";
                ContentForm ccf = (ContentForm) actionForm;

                String parentID = request.getParameter("parentID");
                String relationID = request.getParameter("relationID");
                String type = request.getParameter("type");
                String aspID = request.getSession()
                        .getAttribute(LMSConstants.USER_ASPID_KEY)
                        .toString();

                List errors = new ArrayList();
                ContentConfigModel ccm = ContentManageHelper.getContentConfig(request.getSession()
                        .getAttribute(LMSConstants.USER_ASPID_KEY)
                        .toString());
                long currentFileSize = 0;

                if (ccf.getTheFile() != null)
                {
                        ccf.getTheFile().getFileSize();
                }

                long l = Long.parseLong(ccm.getLimitUploadFileSize()) * 1024;

                if (Integer.parseInt(type) == ContentManageConstants.PERSONAL_CONTENT_TYPE)
                {
                        long sum = 0;
                        sum = ContentTreeHelper.sumCatalogContent(0,
                                Integer.parseInt(relationID), Integer.parseInt(type), sum,
                                aspID);

                        //当前文件和以前上传的文件的总大小
                        sum = sum + currentFileSize;

                        long size = Long.parseLong(ccm.getLimitSpaceSize());

                        if (currentFileSize > l)
                        {
                                errors.add("上传文件过大！最大不能超过" + formatSize(l));
                                request.setAttribute("errors", errors);
                                result = "outOfLimitUploadFileSize";
                        }

                        long theSize = size * 1024 * 1024;

                        if (sum > theSize)
                        {
                                errors.add("文件总容量大小超限制！最大不能超过" + formatSize(theSize));
                                request.setAttribute("errors", errors);
                                result = "personalLimitSpaceSize";
                        }
                }
                else
                {
                        if (currentFileSize > (Long.parseLong(ccm.getLimitUploadFileSize()) * 1024))
                        {
                                errors.add("上传文件过大！最大不能超过" + formatSize(l));
                                request.setAttribute("errors", errors);
                                result = "outOfLimitUploadFileSize";
                        }
                }

                if (errors.isEmpty())
                {
                        if (request.getContentType()
                                .startsWith("multipart/form-data"))
                        {
                                UploadUtil.executeUpload(ccf, request,
                                        response);
                                if (!String.valueOf(request.getAttribute("size"))
                                        .equals("0"))
                                {
                                        ccf.setLocation((String) request.getAttribute(
                                                "newFilePath"));
                                        long contentSize = StringUtil.parseLong((String) request.getAttribute(
                                                "size"));
                                        ccf.setContentSize(contentSize);
                                }
                                else if (ValidateUtil.isEmpty(
                                        (String) (request.getAttribute(
                                                "fileName"))))
                                {
                                        ccf.setContentSize(-1);
                                }
                                else
                                {
                                        ccf.setContentSize(-1);
                                }
                        }
                        else
                        {
                                ccf.setContentSize(-1);
                        }
                }
                ContentManageHelper.addContent(ccf);

                String view= StringUtils.trimToEmpty(request.getParameter("view"));
                String courseContentTypeID= StringUtils.trimToEmpty(request.getParameter("courseContentTypeID"));
                int pageOffset = NumberUtils.toInt(request.getParameter("pager.offset"));
                ActionForward forward = actionMapping.findForward(result);
                StringBuffer bf=new StringBuffer(forward.getPath());
                bf.append("?parentID=" + parentID + "&relationID=" + relationID +
                        "&type=" + type + "&addFile=0&view=" + view + "&pager.offset=" + pageOffset+"&courseContentTypeID="+courseContentTypeID);

                if (errors.isEmpty())
                {
                        return new ActionForward(bf.toString(), true);
                }
                else
                {
                        return new ActionForward(bf.toString());
                }
        }

        public ActionForward updateContent(ActionMapping actionMapping,
                                           ActionForm actionForm, HttpServletRequest request,
                                           HttpServletResponse response) throws Exception
        {
                String result = "success";
                String relationID = request.getParameter("relationID");
                String type = request.getParameter("type");
                String fromSimpleSearch = request.getParameter(
                        "fromSimpleSearch");
                List errors = new ArrayList();

                ContentForm ccf = (ContentForm) actionForm;

                if (ccf.getRadionbt().equals("0")) //修改附件radio选中
                {
                        if (ccf.getIsChangeFile().equals("1")) //通过“不修改附件”checkbox来判断是否要修改附件，0表示不修改，1表示修改
                        {
                                ContentConfigModel ccm = ContentManageHelper.getContentConfig(request.getSession()
                                        .getAttribute(LMSConstants.USER_ASPID_KEY)
                                        .toString());
                                long currentFileSize = 0;

                                if (ccf.getTheFile() != null)
                                {
                                        ccf.getTheFile().getFileSize();
                                }

                                String aspID = request.getSession()
                                        .getAttribute(LMSConstants.USER_ASPID_KEY)
                                        .toString();

                                long l = Long.parseLong(ccm.getLimitUploadFileSize()) * 1024;

                                if (Integer.parseInt(type) == ContentManageConstants.PERSONAL_CONTENT_TYPE)
                                {
                                        long sum = 0;
                                        sum = ContentTreeHelper.sumCatalogContent(0,
                                                Integer.parseInt(relationID),
                                                Integer.parseInt(type), sum, aspID);

                                        //当前文件和以前上传的文件的总大小
                                        sum = sum + currentFileSize;

                                        long size = Long.parseLong(ccm.getLimitSpaceSize());

                                        if (currentFileSize > l)
                                        {
                                                errors.add("上传文件过大！最大不能超过" + formatSize(l));
                                                request.setAttribute("errors", errors);

                                                //result = "outOfLimitUploadFileSize";
                                        }

                                        long theSize = size * 1024 * 1024;

                                        if (sum > theSize)
                                        {
                                                errors.add("文件总容量大小超限制！最大不能超过" + formatSize(theSize));
                                                request.setAttribute("errors", errors);
                                                result = "personalLimitSpaceSizeUpload";
                                        }
                                }
                                else
                                {
                                        if (currentFileSize > (Long.parseLong(
                                                ccm.getLimitUploadFileSize()) * 1024))
                                        {
                                                errors.add("上传文件过大！最大不能超过" + formatSize(l));
                                                request.setAttribute("errors", errors);
                                                result = "outOfLimitUploadFileSizeUpload";
                                        }
                                }

                                if (request.getContentType()
                                        .startsWith("multipart/form-data"))
                                {
                                        UploadUtil.executeUpload(ccf, request,
                                                response);

                                        if (!String.valueOf(request.getAttribute("size"))
                                                .equals("0"))
                                        {
                                                ccf.setLocation((String) request.getAttribute(
                                                        "newFilePath"));

                                                long contentSize = StringUtil.parseLong((String) request.getAttribute(
                                                        "size"));
                                                ccf.setContentSize(contentSize);
                                        }
                                        else if (ValidateUtil.isEmpty(
                                                (String) (request.getAttribute(
                                                        "fileName"))))
                                        {
                                                result = "fail";

                                                ActionForward forward = actionMapping.findForward(
                                                        "fail");
                                                StringBuffer bf = new StringBuffer(forward.getPath());
                                                bf.append("?contentID=" + ccf.getContentID() +
                                                        "&parentID=" + ccf.getParentID() + "&relationID=" +
                                                        relationID + "&type=" + type);

                                                return new ActionForward(bf.toString(), true);

                                                //ccf.setLocation("-1");
                                                //ccf.setContentSize(0);
                                                //ccf.setLocation("error");
                                        }
                                        else
                                        {
                                                result = "fail";

                                                ActionForward forward = actionMapping.findForward(
                                                        "fail");
                                                StringBuffer bf = new StringBuffer(forward.getPath());
                                                bf.append("?contentID=" + ccf.getContentID() +
                                                        "&parentID=" + ccf.getParentID() + "&relationID=" +
                                                        relationID + "&type=" + type);

                                                return new ActionForward(bf.toString(), true);

                                                //ccf.setContentSize(0);
                                                //ccf.setLocation("error");
                                        }
                                }
                        }
                        else if (ccf.getIsChangeFile().equals("0"))
                        {
                                //ccf.setContentSize(null);
                                //ccf.setLocation(null);
                        }
                }
                else if (ccf.getRadionbt().equals("1")) //修改url radio选中
                {
                        if (ccf.getContentSize() != -1)
                        {
                                ContentManageHelper.deleteContentFile(ccf.getContentID());
                        }

                        ccf.setContentSize(-1);
                }

                ContentManageHelper.updateContent(ccf);

                String parentID = request.getParameter("parentID");
                String deleteFromAdvSearch = request.getParameter(
                        "deleteFromAdvSearch");

                //System.out.println("deleteFromAdvSearch ------------------------------------------------------------------------                     " +deleteFromAdvSearch );
                if ((deleteFromAdvSearch != null) &&
                        deleteFromAdvSearch.equals("deleteFromAdvSearch"))
                {
                        result = "deleteFromAdvSearch";

                        //String offset = request.getParameter("pager.offset");
                        ContentAdvSearchForm cf = (ContentAdvSearchForm) request.getSession()
                                .getAttribute("advSearch");
                        int pageNo1 = StringUtil.parseInt((String) request.getParameter(
                                "pager.offset")) / cf.getPageSize();
                        ContentAdvSearchForm sf = ContentManageHelper.advSearchBySession(cf.getHql(),
                                cf.getStartDate(), cf.getOverDate(), cf.getPageSize(),
                                pageNo1);
                        String hql = sf.getHql();
                        Date begin = sf.getStartDate();
                        Date end = sf.getOverDate();
                        List content = sf.getContentModels();
                        int totalCount = sf.getTotalCount();
                        pageNo1 = sf.getPageNo();

                        try
                        {
                                BeanUtils.copyProperties(sf, cf);
                        }
                        catch (IllegalAccessException e)
                        {
                                throw new ContentManageAppException(e);
                        }
                        catch (InvocationTargetException e)
                        {
                                throw new ContentManageAppException(e);
                        }

                        sf.setContentModels(content);
                        sf.setHql(hql);
                        sf.setStartDate(begin);
                        sf.setOverDate(end);
                        sf.setTotalCount(totalCount);
                        sf.setPageNo(pageNo1);

                        request.getSession().removeAttribute("advSearch");
                        request.getSession().setAttribute("advSearch", sf);


                        String view= StringUtils.trimToEmpty(request.getParameter("view"));
                        String courseContentTypeID= StringUtils.trimToEmpty(request.getParameter("courseContentTypeID"));
                        int pageOffset = NumberUtils.toInt(request.getParameter("pager.offset"));
                        ActionForward forward = actionMapping.findForward(result);
                        StringBuffer bf=new StringBuffer(forward.getPath());
                        bf.append("?parentID=" + parentID + "&relationID=" + relationID +
                                "&type=" + type + "&addFile=0&view=" + view + "&pager.offset=" + pageOffset+"&courseContentTypeID="+courseContentTypeID);

                        return new ActionForward(bf.toString(), false);

                        /*                        String offset = request.getParameter("pager.offset");
                     result = "deleteFromAdvSearch";
                     ActionForward forward = actionMapping.findForward(result);
                     StringBuffer bf = new StringBuffer(forward.getPath());
                     bf.append("?parentID=" + parentID + "&relationID=" + relationID + "&type=" + type + "&pager.offset=" + offset);
                     return new ActionForward(bf.toString(), false);*/
                }
                else if ((fromSimpleSearch != null) &&
                        fromSimpleSearch.equals("fromSimpleSearch"))
                {
                        String view= StringUtils.trimToEmpty(request.getParameter("view"));
                        String courseContentTypeID= StringUtils.trimToEmpty(request.getParameter("courseContentTypeID"));
                        int pageOffset = NumberUtils.toInt(request.getParameter("pager.offset"));
                        ActionForward forward = actionMapping.findForward("updateFromSearch");
                        StringBuffer bf=new StringBuffer(forward.getPath());
                        bf.append("?parentID=" + parentID + "&relationID=" + relationID +
                                "&type=" + type + "&addFile=0&view=" + view + "&pager.offset=" + pageOffset+"&courseContentTypeID="+courseContentTypeID);

                        return new ActionForward(bf.toString(), false);
                }
                else
                {
                        String view= StringUtils.trimToEmpty(request.getParameter("view"));
                        String courseContentTypeID= StringUtils.trimToEmpty(request.getParameter("courseContentTypeID"));
                        int pageOffset = NumberUtils.toInt(request.getParameter("pager.offset"));
                        ActionForward forward = actionMapping.findForward(result);
                        StringBuffer bf=new StringBuffer(forward.getPath());
                        bf.append("?parentID=" + parentID + "&relationID=" + relationID +
                                "&type=" + type + "&addFile=0&view=" + view + "&pager.offset=" + pageOffset+"&courseContentTypeID="+courseContentTypeID);

                        if (errors.isEmpty())
                        {
                                return new ActionForward(bf.toString(), true);
                        }
                        else
                        {
                                return new ActionForward(bf.toString());
                        }
                }
        }

        public ActionForward deleteContentOrCatalog(ActionMapping actionMapping,
                                                    ActionForm actionForm, HttpServletRequest request,
                                                    HttpServletResponse response) throws Exception
        {
                //ContentForm cf = (ContentForm) actionForm;
                String result = "success";
                ArrayList al = new ArrayList();
                String[] contentIDs = request.getParameterValues(
                        "contentIDs");
                String deleteFromAdvSearch = request.getParameter(
                        "deleteFromAdvSearch");

                if (contentIDs != null)
                {
                        for (int i = 0; i < contentIDs.length; i++)
                        {
                                al.add(contentIDs[i]);
                        }
                }

                String aspID = request.getSession()
                        .getAttribute(LMSConstants.USER_ASPID_KEY)
                        .toString();

                /*if (cf.getType() != null && cf.getType().equals(String.valueOf(ContentManageConstants.COURSE_CONTENT_TYPE)))
                {
                        for (int i = 0; i < al.size(); i++)
                        {
                                String id = al.get(i).toString();
                                String[] ids = id.split("_");
                                if (ids[1].equals(String.valueOf(ContentManageConstants.displayCatalog)))
                                {
                                        ContentTreeHelper.getTree() (ids[0]);
                                }
                        }
                        ContentTreeHelper.getTree()
                        List errors = new ArrayList();
                        errors.add("请先删除目录下所有文件，然后再删除此目录！");
                        request.setAttribute("errors", errors);
                        return actionMapping.findForward("deleteFromCourse");
                }*/
                ContentManageHelper.deleteContentOrCatalog(al, aspID);

                String parentID = request.getParameter("parentID");
                String relationID = request.getParameter("relationID");
                String type = request.getParameter("type");

                if (Integer.parseInt(type) == ContentManageConstants.PERSONAL_CONTENT_TYPE)
                {
                        result = "personDeleteSuccess";
                }

                //System.out.println("delete    ............................................................................................................               "+deleteFromAdvSearch);
                if ((deleteFromAdvSearch != null) &&
                        deleteFromAdvSearch.equals("deleteFromAdvSearch"))
                {
                        result = "deleteFromAdvSearch";

                        //String offset = request.getParameter("pager.offset");
                        ContentAdvSearchForm c = (ContentAdvSearchForm) request.getSession()
                                .getAttribute("advSearch");
                        int pageNo = StringUtil.parseInt((String) request.getParameter(
                                "pager.offset")) / c.getPageSize();
                        ContentAdvSearchForm sf = ContentManageHelper.advSearchBySession(c.getHql(),
                                c.getStartDate(), c.getOverDate(), c.getPageSize(), pageNo);
                        String hql = sf.getHql();
                        Date begin = sf.getStartDate();
                        Date end = sf.getOverDate();
                        List content = sf.getContentModels();
                        int totalCount = sf.getTotalCount();
                        pageNo = sf.getPageNo();

                        try
                        {
                                BeanUtils.copyProperties(sf, c);
                        }
                        catch (IllegalAccessException e)
                        {
                                throw new ContentManageAppException(e);
                        }
                        catch (InvocationTargetException e)
                        {
                                throw new ContentManageAppException(e);
                        }

                        sf.setContentModels(content);
                        sf.setHql(hql);
                        sf.setStartDate(begin);
                        sf.setOverDate(end);
                        sf.setTotalCount(totalCount);
                        sf.setPageNo(pageNo);

                        request.getSession().removeAttribute("advSearch");
                        request.getSession().setAttribute("advSearch", sf);

                        String view= StringUtils.trimToEmpty(request.getParameter("view"));
                        String courseContentTypeID= StringUtils.trimToEmpty(request.getParameter("courseContentTypeID"));
                        int pageOffset = NumberUtils.toInt(request.getParameter("pager.offset"));
                        ActionForward forward = actionMapping.findForward(result);
                        StringBuffer bf=new StringBuffer(forward.getPath());
                        bf.append("?parentID=" + parentID + "&relationID=" + relationID +
                                "&type=" + type + "&addFile=0&view=" + view + "&pager.offset=" + pageOffset+"&courseContentTypeID="+courseContentTypeID);

                        return new ActionForward(bf.toString(), false);
                }
                else
                {
                        String view= StringUtils.trimToEmpty(request.getParameter("view"));
                        String courseContentTypeID= StringUtils.trimToEmpty(request.getParameter("courseContentTypeID"));
                        int pageOffset = NumberUtils.toInt(request.getParameter("pager.offset"));
                        ActionForward forward = actionMapping.findForward(result);
                        StringBuffer bf=new StringBuffer(forward.getPath());
                        bf.append("?parentID=" + parentID + "&relationID=" + relationID +
                                "&type=" + type + "&addFile=0&view=" + view + "&pager.offset=" + pageOffset+"&courseContentTypeID="+courseContentTypeID);

                        return new ActionForward(bf.toString(), true);
                }
        }

        /**
         * ***********************************      ContentLanguage       *******************************************
         */
        public ActionForward addContentLanguage(ActionMapping actionMapping,
                                                ActionForm actionForm, HttpServletRequest request,
                                                HttpServletResponse response) throws Exception
        {
                ContentLanguageForm ccf = (ContentLanguageForm) actionForm;

                if (ContentManageHelper.addContentLanguage(ccf))
                {
                        return actionMapping.findForward("success");
                }
                else
                {
                        return actionMapping.findForward("fail");
                }
        }

        public ActionForward updateContentLanguage(ActionMapping actionMapping,
                                                   ActionForm actionForm, HttpServletRequest request,
                                                   HttpServletResponse response) throws Exception
        {
                String result = "success";
                ContentLanguageForm ctf = (ContentLanguageForm) actionForm;

                boolean flag = ContentManageHelper.updateContentLanguage(ctf);

                if (flag == false)
                {
                        result = "fail";

                        ActionForward forward = actionMapping.findForward("fail");
                        StringBuffer bf = new StringBuffer(forward.getPath());
                        bf.append("?languageID=" + ctf.getLanguageID());

                        return new ActionForward(bf.toString(), true);
                }

                return actionMapping.findForward(result);
        }

        public ActionForward deleteContentLanguage(ActionMapping actionMapping,
                                                   ActionForm actionForm, HttpServletRequest request,
                                                   HttpServletResponse response) throws Exception
        {
                String resultScreen = "delete";
                ArrayList al = new ArrayList();
                String[] languageIDs = request.getParameterValues(
                        "languageIDs");

                if (languageIDs != null)
                {
                        for (int i = 0; i < languageIDs.length; i++)
                        {
                                al.add(new Integer(languageIDs[i]));
                        }
                }

                List lt = ContentManageHelper.deleteContentLanguage(al);
                List errors = new ArrayList();

                for (int i = 0; i < lt.size(); i++)
                {
                        ContentLanguageModel clm = ContentManageHelper.getContentLanguage(Integer.parseInt(
                                lt.get(i).toString()));
                        errors.add("禁止删除正在使用中的语言：" + clm.getLanguage());
                }

                request.setAttribute("usinglanguage", errors);

                return actionMapping.findForward(resultScreen);
        }

        //***************************************      ContentServer       *********************************************
        public ActionForward addContentServer(ActionMapping actionMapping,
                                              ActionForm actionForm, HttpServletRequest request,
                                              HttpServletResponse response) throws Exception
        {
                ContentServerForm ccf = (ContentServerForm) actionForm;

                if (ContentManageHelper.addContentServer(ccf))
                {
                        return actionMapping.findForward("success");
                }
                else
                {
                        return actionMapping.findForward("fail");
                }
        }

        public ActionForward updateContentServer(ActionMapping actionMapping,
                                                 ActionForm actionForm, HttpServletRequest request,
                                                 HttpServletResponse response) throws Exception
        {
                String result = "success";
                ContentServerForm ctf = (ContentServerForm) actionForm;

                boolean flag = ContentManageHelper.updateContentServer(ctf);

                if (flag == false)
                {
                        result = "fail";

                        ActionForward forward = actionMapping.findForward("fail");
                        StringBuffer bf = new StringBuffer(forward.getPath());
                        bf.append("?contentServerID=" + ctf.getContentServerID());

                        return new ActionForward(bf.toString(), true);
                }

                return actionMapping.findForward(result);
        }

        public ActionForward deleteContentServer(ActionMapping actionMapping,
                                                 ActionForm actionForm, HttpServletRequest request,
                                                 HttpServletResponse response) throws Exception
        {
                String resultScreen = "success";
                ArrayList al = new ArrayList();
                String[] contentServerIDs = request.getParameterValues(
                        "contentServerIDs");

                if (contentServerIDs != null)
                {
                        for (int i = 0; i < contentServerIDs.length; i++)
                        {
                                al.add(new Integer(contentServerIDs[i]));
                        }
                }

                ContentManageHelper.deleteContentServer(al);

                return actionMapping.findForward(resultScreen);
        }

        public ActionForward setDefaultContentServer(ActionMapping actionMapping,
                                                     ActionForm actionForm, HttpServletRequest request,
                                                     HttpServletResponse response) throws Exception
        {
                String resultScreen = "success";
                int al = 0;
                String[] contentServerIDs = request.getParameterValues(
                        "isDefault");

                if (contentServerIDs != null)
                {
                        for (int i = 0; i < contentServerIDs.length; i++)
                        {
                                al = Integer.parseInt((contentServerIDs[0]).toString());
                        }

                        String aspID = request.getSession()
                                .getAttribute(LMSConstants.USER_ASPID_KEY)
                                .toString();

                        ContentManageHelper.setDefaultContentServer(al, aspID);
                }

                return actionMapping.findForward(resultScreen);
        }

        //*****************************************      Input       ***************************************************
        public ActionForward addInputContent(ActionMapping actionMapping,
                                             ActionForm actionForm, HttpServletRequest request,
                                             HttpServletResponse response) throws Exception
        {
                String parentID = request.getParameter("parentID");
                String relationID = request.getParameter("relationID");
                String type = request.getParameter("type");
                String result = "success";

                ContentForm ccf = (ContentForm) actionForm;

                List errors = new ArrayList();
                ContentConfigModel ccm = ContentManageHelper.getContentConfig(request.getSession()
                        .getAttribute(LMSConstants.USER_ASPID_KEY)
                        .toString());
                long currentFileSize = 0;

                if (ccf.getTheFile() != null)
                {
                        ccf.getTheFile().getFileSize();
                }

                if (currentFileSize > (Long.parseLong(ccm.getLimitUploadFileSize()) * 1000))
                {
                        errors.add("上传文件过大！");
                        request.setAttribute("errors", errors);
                        result = "outOfLimitUploadFileSizeByInput";
                }

                if (errors.isEmpty())
                {
                        if (request.getContentType()
                                .startsWith("multipart/form-data"))
                        {
                                UploadUtil.executeUpload(ccf, request,
                                        response);

                                if (!String.valueOf(request.getAttribute("size"))
                                        .equals("0"))
                                {
                                        ccf.setLocation((String) request.getAttribute(
                                                "newFilePath"));

                                        long contentSize = StringUtil.parseLong((String) request.getAttribute(
                                                "size"));
                                        ccf.setContentSize(contentSize);
                                }
                                else if (ValidateUtil.isEmpty(
                                        (String) (request.getAttribute(
                                                "fileName"))))
                                {
                                        result = "inputfail";

                                        ActionForward forward = actionMapping.findForward(result);
                                        StringBuffer bf = new StringBuffer(forward.getPath());
                                        bf.append("?parentID=" + parentID + "&relationID=" +
                                                relationID + "&type=" + type);

                                        return new ActionForward(bf.toString(), true);

                                        //ccf.setLocation("-1");
                                        //ccf.setContentSize(-1);
                                }
                                else
                                {
                                        result = "inputfail";

                                        ActionForward forward = actionMapping.findForward(result);
                                        StringBuffer bf = new StringBuffer(forward.getPath());
                                        bf.append("?parentID=" + parentID + "&relationID=" +
                                                relationID + "&type=" + type);

                                        return new ActionForward(bf.toString(), true);

                                        //ccf.setContentSize(-1);
                                }
                        }

                        try
                        {
                                ContentManageHelper.addInputContent(ccf);
                        }
                        catch (IOException e)
                        {
                                result = "wrongType";

                                ActionForward forward = actionMapping.findForward(result);
                                StringBuffer bf = new StringBuffer(forward.getPath());
                                bf.append("?parentID=" + parentID + "&relationID=" +
                                        relationID + "&type=" + type);

                                return new ActionForward(bf.toString(), true);
                        }
                }

                ActionForward forward = actionMapping.findForward(result);
                StringBuffer bf = new StringBuffer(forward.getPath());
                bf.append("?parentID=" + parentID + "&relationID=" + relationID +
                        "&type=" + type);

                if (errors.isEmpty())
                {
                        return new ActionForward(bf.toString(), true);
                }
                else
                {
                        return new ActionForward(bf.toString());
                }
        }

        public ActionForward addInputCourseware(ActionMapping actionMapping,
                                                ActionForm actionForm, HttpServletRequest request,
                                                HttpServletResponse response) throws Exception
        {
                ContentForm ccf = (ContentForm) actionForm;
                int parentID = ccf.getParentID();
                int relationID = ccf.getRelationID();
                int type = Integer.parseInt(ccf.getType());
                String result = "success";

                String vod = request.getParameter("vod");

                List errors = new ArrayList();
                ContentConfigModel ccm = ContentManageHelper.getContentConfig(request.getSession()
                        .getAttribute(LMSConstants.USER_ASPID_KEY)
                        .toString());
                long currentFileSize = 0;

                if (ccf.getTheFile() != null)
                {
                        ccf.getTheFile().getFileSize();
                }

                if (currentFileSize > (Long.parseLong(ccm.getLimitUploadFileSize()) * 1000))
                {
                        errors.add("上传文件过大！");
                        request.setAttribute("errors", errors);
                        result = "outOfLimitUploadFileSizeByInputCourseware";
                }

                if (errors.isEmpty())
                {
                        if (request.getContentType()
                                .startsWith("multipart/form-data"))
                        {
                                UploadUtil.executeUpload(ccf, request,
                                        response);

                                if (!String.valueOf(request.getAttribute("size"))
                                        .equals("0"))
                                {
                                        ccf.setLocation((String) request.getAttribute(
                                                "newFilePath"));

                                        long contentSize = StringUtil.parseLong((String) request.getAttribute(
                                                "size"));
                                        ccf.setContentSize(contentSize);
                                }
                                else if (ValidateUtil.isEmpty(
                                        (String) (request.getAttribute(
                                                "fileName"))))
                                {
                                        ActionForward forward = actionMapping.findForward(
                                                "inputfail");
                                        StringBuffer bf = new StringBuffer(forward.getPath());
                                        bf.append("?parentID=" + parentID + "&relationID=" +
                                                relationID + "&type=" + type + "&courseware=1");

                                        return new ActionForward(bf.toString(), true);

                                        //ccf.setLocation("-1");
                                        //ccf.setContentSize(-1);
                                }
                                else
                                {
                                        ActionForward forward = actionMapping.findForward(
                                                "inputfail");
                                        StringBuffer bf = new StringBuffer(forward.getPath());
                                        bf.append("?parentID=" + parentID + "&relationID=" +
                                                relationID + "&type=" + type + "&courseware=1");

                                        return new ActionForward(bf.toString(), true);

                                        //ccf.setContentSize(-1);
                                }
                        }

                        try
                        {
                                if ((ccf.getIndex() == null) || (ccf.getIndex().length() == 0))
                                {
                                        ccf.setIndex("content.htm");
                                }

                                ContentManageHelper.addInputCourseware(ccf);
                        }
                        catch (IOException e)
                        {
                                ActionForward forward = actionMapping.findForward("wrongType");
                                StringBuffer bf = new StringBuffer(forward.getPath());
                                bf.append("?parentID=" + parentID + "&relationID=" +
                                        relationID + "&type=" + type);

                                return new ActionForward(bf.toString(), true);
                        }
                }

                ActionForward forward = actionMapping.findForward(result);
                StringBuffer bf = new StringBuffer(forward.getPath());
                bf.append("?parentID=" + parentID + "&relationID=" + relationID +
                        "&type=" + type);

                if (errors.isEmpty())
                {
                        if ((vod != null) && vod.equals("vod"))
                        {
                                result = "successByInputVod";
                                forward = actionMapping.findForward(result);
                                bf = new StringBuffer(forward.getPath());
                                bf.append("?aspID=" + ccf.getRemark3());

                                return new ActionForward(bf.toString(), true);
                        }
                        else
                        {
                                return new ActionForward(bf.toString(), true);
                        }
                }
                else
                {
                        if ((vod != null) && vod.equals("vod"))
                        {
                                result = "outOfLimitUploadFileSizeByInputVod";
                                forward = actionMapping.findForward(result);
                                bf = new StringBuffer(forward.getPath());
                                bf.append("?aspID=" + ccf.getRemark3());

                                return new ActionForward(bf.toString());
                        }
                        else
                        {
                                return new ActionForward(bf.toString());
                        }
                }
        }

        /**
         * ***************************************    CourseContentType   ***************************************************
         */
        public ActionForward updateCourseContentType(ActionMapping actionMapping,
                                                     ActionForm actionForm, HttpServletRequest request,
                                                     HttpServletResponse response) throws Exception
        {
                String result = "success";
                CourseContentTypeForm ctf = (CourseContentTypeForm) actionForm;

                boolean flag = ContentManageHelper.updateCourseContentType(ctf);

                if (flag == false)
                {
                        result = "fail";

                        ActionForward forward = actionMapping.findForward("fail");
                        StringBuffer bf = new StringBuffer(forward.getPath());
                        bf.append("?contentTypeID=" + ctf.getCourseContentTypeID());

                        return new ActionForward(bf.toString(), true);
                }

                return actionMapping.findForward(result);
        }

        public ActionForward addCourseContentType(ActionMapping actionMapping,
                                                  ActionForm actionForm, HttpServletRequest request,
                                                  HttpServletResponse response) throws Exception
        {
                CourseContentTypeForm ctf = (CourseContentTypeForm) actionForm;

                boolean flag = ContentManageHelper.addCourseContentType(ctf);

                if (flag == false)
                {
                        return actionMapping.findForward("fail");
                }

                return actionMapping.findForward("success");
        }

        public ActionForward deleteCourseContentType(ActionMapping actionMapping,
                                                     ActionForm actionForm, HttpServletRequest request,
                                                     HttpServletResponse response) throws Exception
        {
                String resultScreen = "delete";
                ArrayList al = new ArrayList();
                String[] contentTypeIDs = request.getParameterValues(
                        "contentTypeIDs");

                if (contentTypeIDs != null)
                {
                        for (int i = 0; i < contentTypeIDs.length; i++)
                        {
                                al.add(new Integer(contentTypeIDs[i]));
                        }
                }

                String aspID = request.getSession()
                        .getAttribute(LMSConstants.USER_ASPID_KEY)
                        .toString();
                List lt = ContentManageHelper.deleteCourseContentType(al, aspID);

                List errors = new ArrayList();

                for (int i = 0; i < lt.size(); i++)
                {
                        CourseContentTypeModel clm = ContentManageHelper.getCourseContentTypeByCourseContentTypeID(Integer.parseInt(
                                lt.get(i).toString()));
                        errors.add("禁止删除正在使用中的类型：" + clm.getCourseContentType());
                }

                request.setAttribute("usingcontent", errors);

                return actionMapping.findForward(resultScreen);
        }

        public ActionForward updateCourseContentTypeOrderIndex(
                ActionMapping actionMapping, ActionForm actionForm,
                HttpServletRequest request,
                HttpServletResponse response) throws Exception
        {
                String resultScreen = "success";
                ArrayList al = new ArrayList();
                String[] cssOrder = request.getParameterValues("cssOrder");

                if (cssOrder != null)
                {
                        for (int i = 0; i < cssOrder.length; i++)
                        {
                                al.add(cssOrder[i]);
                        }
                }

                ContentManageHelper.updateCourseContentTypeOrderIndex(al);

                return actionMapping.findForward(resultScreen);
        }

        /**
         * 返回可以显示大小的值,<br>形如4234,返回"4,2k";
         *
         * @param b
         * @return
         */
        public static String formatSize(long b)
        {
                long SIZE_K = 1024;
                long SIZE_M = SIZE_K * SIZE_K;
                long SIZE_G = SIZE_K * SIZE_K * SIZE_K;
                DecimalFormat nf = new DecimalFormat("##########.#");
                String result = null;

                if (b >= SIZE_G)
                {
                        result = nf.format((double) b / SIZE_G) + " G";
                }
                else if (b >= SIZE_M)
                {
                        result = nf.format((double) b / SIZE_M) + " M";
                }
                else if (b >= SIZE_K)
                {
                        result = nf.format((double) b / SIZE_K) + " K";
                }
                else
                {
                        result = String.valueOf(b);
                }

                return result;
        }
}
