/**
 * AnswerQuestionAction.java.
 * User: Administrator  Date: 2005-3-8
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.answerquestion.action;

import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.core.util.DateTimeUtil;
import com.ulearning.ulms.core.util.StringUtil;
import com.ulearning.ulms.core.util.UploadUtil;
import com.ulearning.ulms.core.util.ValidateUtil;
import com.ulearning.ulms.tools.answerquestion.bean.AnswerQuestionHelper;
import com.ulearning.ulms.tools.answerquestion.exceptions.AnswerQuestionAppException;
import com.ulearning.ulms.tools.answerquestion.form.AQCatalogForm;
import com.ulearning.ulms.tools.answerquestion.form.AnswerQuestionForm;
import com.ulearning.ulms.tools.answerquestion.model.AQCatalogModel;
import com.ulearning.ulms.tools.answerquestion.model.AnswerQuestionModel;
import com.ulearning.ulms.tools.answerquestion.webimpls.AnswerQuestionWeblmpl;
import com.ulearning.ulms.tools.upload.action.UploadAction;
import com.ulearning.ulms.tools.upload.model.UploadForm;
import com.ulearning.ulms.util.log.LogUtil;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class AnswerQuestionAction extends DispatchAction
{
        public ActionForward execute(ActionMapping actionMapping,
                                     ActionForm actionForm, HttpServletRequest httpServletRequest,
                                     HttpServletResponse httpServletResponse) throws Exception
        {
                return super.execute(actionMapping, actionForm, httpServletRequest,
                        httpServletResponse);
        }

        public ActionForward selectQKey(ActionMapping actionMapping,
                                        ActionForm actionForm, HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse) throws Exception
        {
                HttpSession session = null;
                session = httpServletRequest.getSession();
                LogUtil.debug("course", "[AnswerQuestionAction]-----selectQKey start!");

                String resultScreen = "success";
                AnswerQuestionForm keyForm = (AnswerQuestionForm) actionForm;
                session.setAttribute("qKey", keyForm);

                return actionMapping.findForward(resultScreen);
        }

        public ActionForward addCatalog(ActionMapping actionMapping,
                                        ActionForm actionForm, HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse) throws Exception
        {
                LogUtil.debug("course", "[AnswerQuestionAction]-----addCatalog start!");

                String resultScreen = "success";

                AQCatalogForm cForm = (AQCatalogForm) actionForm;

                //              判断类别标题是否已存在
                boolean b = AnswerQuestionHelper.isRename(Integer.parseInt(
                        cForm.getType()), cForm.getCatalogName(), -1);

                if (b)
                {
                        throw new AnswerQuestionAppException("类别标题已存在，请重新输入！");
                }

                AnswerQuestionHelper.addCatalog(cForm);
                httpServletRequest.setAttribute("cType", cForm.getType());

                String relationIDs = httpServletRequest.getParameter("relationID");
                String relationName = httpServletRequest.getParameter("relationName");
                httpServletRequest.setAttribute("relationID", relationIDs);
                httpServletRequest.setAttribute("relationName", relationName);
                LogUtil.debug("tools",
                        "[AnswerQuestionAction]-----addCatalog start:relationName=" +
                                relationName);

                ActionForward inforward = actionMapping.findForward(resultScreen);
                StringBuffer bf = new StringBuffer(inforward.getPath());
                bf.append("?cType=" + cForm.getType() + "&relationID=" + relationIDs +
                        "&relationName=" + relationName);
                LogUtil.debug("tools",
                        "[AnswerQuestionAction]-----addCatalog start:url=" + bf.toString());

                return new ActionForward(bf.toString(), true);
        }

        public ActionForward updCatalog(ActionMapping actionMapping,
                                        ActionForm actionForm, HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse) throws Exception
        {
                LogUtil.debug("course", "[AnswerQuestionAction]-----updCatalog start!");

                String resultScreen = "success";
                String relationIDs = httpServletRequest.getParameter("relationID");
                String relationName = httpServletRequest.getParameter("relationName");
                AQCatalogForm cForm = (AQCatalogForm) actionForm;

                //判断类别标题是否已存在
                boolean b = AnswerQuestionHelper.isRename(Integer.parseInt(
                        cForm.getType()), cForm.getCatalogName(),
                        cForm.getCatalogID());

                if (b)
                {
                        throw new AnswerQuestionAppException("类别标题已存在，请重新输入！");
                }

                LogUtil.debug("course",
                        "[AnswerQuestionAction*updCatalog]getDescription=" +
                                cForm.getDescription());
                AnswerQuestionHelper.updCatalog(cForm);
                httpServletRequest.setAttribute("cType", cForm.getType());

                ActionForward inforward = actionMapping.findForward(resultScreen);
                StringBuffer bf = new StringBuffer(inforward.getPath());
                bf.append("?cType=" + cForm.getType() + "&relationID=" + relationIDs +
                        "&relationName=" + relationName);

                return new ActionForward(bf.toString(), true);
        }

        public ActionForward delCatalog(ActionMapping actionMapping,
                                        ActionForm actionForm, HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse) throws Exception
        {
                LogUtil.debug("course", "[AnswerQuestionAction]-----delCatalog start!");

                String resultScreen = "success";
                ArrayList al = new ArrayList();
                String ctyp = httpServletRequest.getParameter("updType");
                String[] catalogIDs = httpServletRequest.getParameterValues(
                        "catalogIDs");
                String relationIDs = httpServletRequest.getParameter("relationID");
                String relationName = httpServletRequest.getParameter("relationName");
                AnswerQuestionWeblmpl weblmpl = new AnswerQuestionWeblmpl();
                String alert = "";
                int flag = 0;

                if (catalogIDs != null)
                {
                        for (int i = 0; i < catalogIDs.length; i++)
                        {
                                int result = weblmpl.getParent(Integer.parseInt(catalogIDs[i]));

                                if (result > 0)
                                {
                                        flag = 1;

                                        AQCatalogModel aqCatalogModel = AnswerQuestionHelper.getCatalog(Integer.parseInt(
                                                catalogIDs[i]));
                                        alert += ("“" + aqCatalogModel.getCatalogName() + "”、");
                                }
                                else
                                {
                                        al.add(new Integer(catalogIDs[i]));
                                }
                        }

                        if (!alert.equals(""))
                        {
                                alert = alert.substring(0, alert.length() - 1) + "等已被使用，不能删除！";
                        }
                }

                if (flag == 1)
                {
                        throw new AnswerQuestionAppException(alert);
                }

                AnswerQuestionHelper.deleteCatalog(al);

                httpServletRequest.setAttribute("cType", ctyp);

                ActionForward inforward = actionMapping.findForward(resultScreen);
                StringBuffer bf = new StringBuffer(inforward.getPath());
                bf.append("?cType=" + ctyp + "&relationID=" + relationIDs +
                        "&relationName=" + relationName);

                return new ActionForward(bf.toString(), true);
        }

        public ActionForward delQuestionM(ActionMapping actionMapping,
                                          ActionForm actionForm, HttpServletRequest httpServletRequest,
                                          HttpServletResponse httpServletResponse) throws Exception
        {
                String resultScreen = "success";

                //yangds:forwardType用以区别导向到所有问题列表页面还是我的问题列表页面
                String forwardType = httpServletRequest.getParameter("forwardType");

                if ((forwardType != null) && forwardType.equals("0"))
                {
                        resultScreen = "myquestion";
                }

                ArrayList al = new ArrayList();
                String qtype = httpServletRequest.getParameter("qtype");
                String[] aqIDs = httpServletRequest.getParameterValues("aqIDs");
                String relationIDs = httpServletRequest.getParameter("relationID");
                String relationName = httpServletRequest.getParameter("relationName");

                if (aqIDs != null)
                {
                        for (int i = 0; i < aqIDs.length; i++)
                        {
                                al.add(new Integer(aqIDs[i]));
                        }
                }

                AnswerQuestionHelper.deleteQuestiont(al);
                httpServletRequest.setAttribute("qtype", qtype);

                ActionForward inforward = actionMapping.findForward(resultScreen);
                StringBuffer bf = new StringBuffer(inforward.getPath());
                bf.append("?qtype=" + qtype + "&relationID=" + relationIDs +
                        "&relationName=" + relationName);

                return new ActionForward(bf.toString(), true);
        }

        public ActionForward updQuestionM(ActionMapping actionMapping,
                                          ActionForm actionForm, HttpServletRequest httpServletRequest,
                                          HttpServletResponse httpServletResponse) throws Exception
        {
                LogUtil.debug("course", "[AnswerQuestionAction]-----updDocument start!");

                String resultScreen = "success";
                String forwardType = httpServletRequest.getParameter("forwardType");

                if ((forwardType != null) && forwardType.equals("0"))
                {
                        resultScreen = "myquestion";
                }

                AnswerQuestionForm qForm = (AnswerQuestionForm) actionForm;

                if (httpServletRequest.getContentType().startsWith("multipart/form-data"))
                {
                        try
                        {
                                UploadAction uploadAction = new UploadAction();
                                UploadForm uploadForm = qForm.getUploadForm();
                                uploadAction.executeUpload(actionMapping, uploadForm,
                                        httpServletRequest, httpServletResponse);
                        }
                        catch (Exception e)
                        {
                                e.printStackTrace();
                                LogUtil.debug("system",
                                        "[UpdateAssignmentAction] Exeception====================" +
                                                e);
                        }

                        LogUtil.info("course", "[AnswerQuestionAction]===========1");

                        String flag = httpServletRequest.getParameter("flag");
                        LogUtil.info("yangds", "updquestionM----------------flag=" + flag);

                        String delAnnex = httpServletRequest.getParameter("delAnnex");
                        LogUtil.info("yangds",
                                "updquestionM----------------delAnnex=" + delAnnex);

                        if (!String.valueOf(httpServletRequest.getAttribute("size"))
                                .equals("0"))
                        {
                                LogUtil.info("course",
                                        "[AnswerQuestionAction]===========qForm.getFilePath()=" +
                                                qForm.getFilePath());
                                LogUtil.info("course",
                                        "[AnswerQuestionAction]===========httpServletRequest.getAttribute(\"newFileName\")=" +
                                                httpServletRequest.getAttribute("newFileName"));
                                LogUtil.info("course",
                                        "[AnswerQuestionAction]===========(String)httpServletRequest.getAttribute(\"newFilePath\")=" +
                                                (String) httpServletRequest.getAttribute("newFilePath"));

                                if ((flag != null) && !flag.equals("null") && flag.equals("0"))
                                {
                                        qForm.setQuestionLink((String) httpServletRequest.getAttribute(
                                                "newFilePath"));
                                }
                                else
                                {
                                        qForm.setAnswerLink((String) httpServletRequest.getAttribute(
                                                "newFilePath"));
                                }
                        }
                        else if ((delAnnex != null) && delAnnex.equals("1"))
                        {
                                if ((flag != null) && !flag.equals("null") && flag.equals("0"))
                                {
                                        qForm.setQuestionLink("2");
                                }
                                else
                                {
                                        qForm.setAnswerLink("2");
                                }
                        }
                        else
                        {
                                if ((flag != null) && !flag.equals("null") && flag.equals("0"))
                                {
                                        qForm.setQuestionLink("-1");
                                }
                                else
                                {
                                        qForm.setAnswerLink("-1");
                                }
                        }
                }

                try
                {
                        AnswerQuestionHelper.updateQuestiont(qForm);
                }
                catch (ULMSSysException ex)
                {
                        ex.printStackTrace();
                }

                LogUtil.info("system",
                        "[AnswerQuestionAction]===========isMessage = " +
                                qForm.getIsMessage());
                LogUtil.info("system",
                        "[AnswerQuestionAction]===========resultScreen = " + resultScreen);
                LogUtil.info("system",
                        "[AnswerQuestionAction]= public String getType() = " +
                                qForm.getType());
                httpServletRequest.setAttribute("qtype", qForm.getType());
                httpServletRequest.setAttribute("aqIDs", new Integer(qForm.getAqID()));

                ActionForward inforward = actionMapping.findForward(resultScreen);
                StringBuffer bf = new StringBuffer(inforward.getPath());
                bf.append("?qtype=" + qForm.getType() + "&aqIDs=" +
                        new Integer(qForm.getAqID()) + "&relationID=" +
                        qForm.getRelationID() + "&relationName=" + qForm.getRelationName());

                return new ActionForward(bf.toString(), true);
        }

        public ActionForward addQuestion(ActionMapping actionMapping,
                                         ActionForm actionForm, HttpServletRequest httpServletRequest,
                                         HttpServletResponse httpServletResponse) throws Exception
        {
                LogUtil.debug("course", "[AnswerQuestionAction]-----addQuestion start!");

                String resultScreen = "success";
                String submitType = httpServletRequest.getParameter("submitType");

                if ((submitType != null) && submitType.equals("1"))
                {
                        resultScreen = "portalsuccess";
                }

                AnswerQuestionForm qForm = (AnswerQuestionForm) actionForm;

                //String relationID = httpServletRequest.getParameter("relationID");
                //String relationName = httpServletRequest.getParameter("relationName");
                //LogUtil.debug("yangds:1","[AnswerQuestionAction]-----relationID="+relationID);
                HttpSession session = null;
                session = httpServletRequest.getSession();
                session.removeAttribute("qKey");
                LogUtil.info("yangds",
                        "addQuestion===========getContentType=" +
                                httpServletRequest.getContentType());

                if (httpServletRequest.getContentType().startsWith("multipart/form-data"))
                {
                        try
                        {
                                UploadAction uploadAction = new UploadAction();
                                UploadForm uploadForm = qForm.getUploadForm();
                                uploadAction.executeUpload(actionMapping, uploadForm,
                                        httpServletRequest, httpServletResponse);
                        }
                        catch (Exception e)
                        {
                                e.printStackTrace();
                                LogUtil.debug("system",
                                        "[UpdateAssignmentAction] Exeception====================" +
                                                e);
                        }

                        LogUtil.info("course", "[AnswerQuestionAction]===========1");

                        if (!String.valueOf(httpServletRequest.getAttribute("size"))
                                .equals("0"))
                        {
                                LogUtil.info("course",
                                        "[AnswerQuestionAction]===========qForm.getFilePath()=" +
                                                qForm.getFilePath());
                                LogUtil.info("course",
                                        "[AnswerQuestionAction]===========httpServletRequest.getAttribute(\"newFileName\")=" +
                                                httpServletRequest.getAttribute("newFileName"));
                                LogUtil.info("course",
                                        "[AnswerQuestionAction]===========(String)httpServletRequest.getAttribute(\"newFilePath\")=" +
                                                (String) httpServletRequest.getAttribute("newFilePath"));
                                qForm.setQuestionLink((String) httpServletRequest.getAttribute(
                                        "newFilePath"));
                        }
                        else
                        {
                                qForm.setQuestionLink("-1");
                        }
                }

                LogUtil.info("yangds:",
                        "[AnswerQuestionAction] relationName=" + qForm.getRelationName());
                AnswerQuestionHelper.insertQuestion(qForm);
                httpServletRequest.setAttribute("qtype", qForm.getType());
                LogUtil.info("system",
                        "[AnswerQuestionAction]===========resultScreen = " + resultScreen);

                ActionForward inforward = actionMapping.findForward(resultScreen);
                StringBuffer bf = new StringBuffer(inforward.getPath());
                bf.append("?qtype=" + qForm.getType() + "&relationID=" +
                        qForm.getRelationID() + "&relationName=" + qForm.getRelationName());

                return new ActionForward(bf.toString(), true);
        }

        public ActionForward addAnswerQuestion(ActionMapping actionMapping,
                                               ActionForm actionForm, HttpServletRequest httpServletRequest,
                                               HttpServletResponse httpServletResponse) throws Exception
        {
                LogUtil.debug("course", "[AnswerQuestionAction]-----addQuestion start!");

                String resultScreen = "success";
                String submitType = httpServletRequest.getParameter("submitType");

                if ((submitType != null) && submitType.equals("1"))
                {
                        resultScreen = "portalsuccess";
                }

                AnswerQuestionForm qForm = (AnswerQuestionForm) actionForm;

                //String relationID = httpServletRequest.getParameter("relationID");
                //String relationName = httpServletRequest.getParameter("relationName");
                //LogUtil.debug("yangds:1","[AnswerQuestionAction]-----relationID="+relationID);
                HttpSession session = null;
                session = httpServletRequest.getSession();
                session.removeAttribute("qKey");
                LogUtil.info("yangds",
                        "addAnswerQuestion===========getContentType=" +
                                httpServletRequest.getContentType());

                if (httpServletRequest.getContentType().startsWith("multipart/form-data"))
                {
                        try
                        {
                                UploadUtil.executeUpload(qForm, httpServletRequest,
                                        httpServletResponse);
                        }
                        catch (Exception e)
                        {
                                e.printStackTrace();
                                LogUtil.debug("system",
                                        "[AnswerQuestionAction] Exeception====================" +
                                                e);
                        }

                        LogUtil.info("course", "[AnswerQuestionAction]===========1");

                        if (!String.valueOf(httpServletRequest.getAttribute("size"))
                                .equals("0"))
                        {
                                qForm.setQuestionLink((String) httpServletRequest.getAttribute(
                                        "newFilePath"));
                        }
                        else
                        {
                                qForm.setQuestionLink("-1");
                        }

                        if (!String.valueOf(httpServletRequest.getAttribute("size1"))
                                .equals("0"))
                        {
                                qForm.setAnswerLink((String) httpServletRequest.getAttribute(
                                        "newFilePath1"));
                        }
                        else
                        {
                                qForm.setAnswerLink("-1");
                        }
                }

                LogUtil.info("yangds:",
                        "[AnswerQuestionAction] relationName=" + qForm.getRelationName());
                AnswerQuestionHelper.insertQuestion(qForm);
                httpServletRequest.setAttribute("qtype", qForm.getType());
                LogUtil.info("system",
                        "[AnswerQuestionAction]===========resultScreen = " + resultScreen);

                ActionForward inforward = actionMapping.findForward(resultScreen);
                StringBuffer bf = new StringBuffer(inforward.getPath());
                bf.append("?qtype=" + qForm.getType() + "&relationID=" +
                        qForm.getRelationID() + "&relationName=" + qForm.getRelationName());

                return new ActionForward(bf.toString(), true);
        }

        public ActionForward delQuestion(ActionMapping actionMapping,
                                         ActionForm actionForm, HttpServletRequest httpServletRequest,
                                         HttpServletResponse httpServletResponse) throws Exception
        {
                String resultScreen = "success";
                ArrayList al = new ArrayList();
                String qtype = httpServletRequest.getParameter("stype");
                String aqIDs = httpServletRequest.getParameter("answerquestionID");
                String relationIDs = httpServletRequest.getParameter("relationID");
                String relationName = httpServletRequest.getParameter("relationName");

                if (aqIDs != null)
                {
                        al.add(new Integer(aqIDs));
                }

                AnswerQuestionHelper.deleteQuestiont(al);
                httpServletRequest.setAttribute("qtype", qtype);

                ActionForward inforward = actionMapping.findForward(resultScreen);
                StringBuffer bf = new StringBuffer(inforward.getPath());
                bf.append("?qtype=" + qtype + "&relationID=" + relationIDs +
                        "&relationName=" + relationName);

                return new ActionForward(inforward.getName(), bf.toString(), true);
        }

        public ActionForward updQuestion(ActionMapping actionMapping,
                                         ActionForm actionForm, HttpServletRequest httpServletRequest,
                                         HttpServletResponse httpServletResponse) throws Exception
        {
                LogUtil.debug("course", "[AnswerQuestionAction]-----updDocument start!");

                String resultScreen = "success";
                AnswerQuestionForm qForm = (AnswerQuestionForm) actionForm;

                if (httpServletRequest.getContentType().startsWith("multipart/form-data"))
                {
                        try
                        {
                                UploadUtil.executeUpload(qForm, httpServletRequest,
                                        httpServletResponse);
                        }
                        catch (Exception e)
                        {
                                e.printStackTrace();
                                LogUtil.debug("system",
                                        "[AnswerQuestionAction] Exeception====================" +
                                                e);
                        }

                        LogUtil.info("course", "[AnswerQuestionAction]===========1");

                        String delQuestionAnnex = httpServletRequest.getParameter(
                                "delQuestionAnnex");
                        LogUtil.info("yangds",
                                "updquestionM----------------delQuestionAnnex=" +
                                        delQuestionAnnex);

                        String delAnswerAnnex = httpServletRequest.getParameter(
                                "delAnswerAnnex");
                        LogUtil.info("yangds",
                                "updquestionM----------------delAnswerAnnex=" + delAnswerAnnex);

                        if (!String.valueOf(httpServletRequest.getAttribute("size"))
                                .equals("0"))
                        {
                                qForm.setQuestionLink((String) httpServletRequest.getAttribute(
                                        "newFilePath"));
                        }
                        else if ((delQuestionAnnex != null) &&
                                delQuestionAnnex.trim().equals("1"))
                        {
                                qForm.setQuestionLink("2");
                        }
                        else
                        {
                                qForm.setQuestionLink("-1");
                        }

                        if (!String.valueOf(httpServletRequest.getAttribute("size1"))
                                .equals("0"))
                        {
                                qForm.setAnswerLink((String) httpServletRequest.getAttribute(
                                        "newFilePath1"));
                        }
                        else if ((delAnswerAnnex != null) &&
                                delAnswerAnnex.trim().equals("1"))
                        {
                                qForm.setAnswerLink("2");
                        }
                        else
                        {
                                qForm.setAnswerLink("-1");
                        }
                }

                AnswerQuestionHelper.updateQuestiont(qForm);
                LogUtil.info("system",
                        "[AnswerQuestionAction]===========resultScreen = " + resultScreen);
                httpServletRequest.setAttribute("cType", qForm.getType());

                ActionForward inforward = actionMapping.findForward(resultScreen);
                StringBuffer bf = new StringBuffer(inforward.getPath());
                bf.append("?qtype=" + qForm.getType() + "&relationID=" +
                        qForm.getRelationID() + "&relationName=" + qForm.getRelationName());

                return new ActionForward(bf.toString(), true);
        }

        public ActionForward updDepth(ActionMapping actionMapping,
                                      ActionForm actionForm, HttpServletRequest httpServletRequest,
                                      HttpServletResponse httpServletResponse) throws Exception
        {
                LogUtil.debug("AnswerQuestion",
                        "[AnswerQuestionAction]-----updDepth start!");

                String resultScreen = "success";
                String an = httpServletRequest.getParameter("an");
                String aqID = httpServletRequest.getParameter("aqID");
                String depths = httpServletRequest.getParameter("depth");
                int depth = 0;

                if (depths != null)
                {
                        depth = Integer.parseInt(depths);
                }

                AnswerQuestionForm qForm = (AnswerQuestionForm) actionForm;
                //qForm.setDepth(depth+1);
                AnswerQuestionHelper.updateDepth(qForm);

                if (StringUtil.checkEmpty(an).equals("1"))
                {
                        LogUtil.debug("AnswerQuestion",
                                "[AnswerQuestionAction]-getType=" + qForm.getType());
                        httpServletRequest.setAttribute("qtype", qForm.getType());

                        ActionForward inforward = actionMapping.findForward("answer");
                        StringBuffer bf = new StringBuffer(inforward.getPath());
                        bf.append("?qtype=" + qForm.getType() + "&aqID=" + aqID +
                                "&relationID=" + qForm.getRelationID() + "&relationName=" +
                                qForm.getRelationName());

                        return new ActionForward(bf.toString(), true);
                }

                LogUtil.debug("AnswerQuestion",
                        "[AnswerQuestionAction]-else--getType=" + qForm.getType());
                httpServletRequest.setAttribute("aqID", aqID);

                ActionForward inforward = actionMapping.findForward(resultScreen);
                StringBuffer bf = new StringBuffer(inforward.getPath());
                bf.append("?aqID=" + aqID + "&qtype=" + qForm.getType() +
                        "&relationID=" + qForm.getRelationID() + "&relationName=" +
                        qForm.getRelationName());

                return new ActionForward(bf.toString(), true);
        }

        public ActionForward update(ActionMapping actionMapping,
                                    ActionForm actionForm, HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse) throws Exception
        {
                LogUtil.debug("AnswerQuestion",
                        "[AnswerQuestionAction]-----updDepth start!");

                String resultScreen = "success";
                String depths = httpServletRequest.getParameter("depth");
                AnswerQuestionForm qForm = (AnswerQuestionForm) actionForm;
                AnswerQuestionHelper.update(qForm);
                LogUtil.debug("AnswerQuestion",
                        "[AnswerQuestionAction]-else--getType=" + qForm.getType());

                ActionForward inforward = actionMapping.findForward(resultScreen);
                StringBuffer bf = new StringBuffer(inforward.getPath());
                bf.append("?qtype=" + qForm.getType() + "&relationID=" +
                        qForm.getRelationID() + "&relationName=" + qForm.getRelationName());

                return new ActionForward(bf.toString(), true);
        }
}
