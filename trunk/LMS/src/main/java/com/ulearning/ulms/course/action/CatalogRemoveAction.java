/*
 * Copyright (c) 2005 Your Corporation. All Rights Reserved.
 */
package com.ulearning.ulms.course.action;

import com.ulearning.ulms.admin.certificate.dao.CertDAO;
import com.ulearning.ulms.admin.certificate.dao.CertDAOFactory;
import com.ulearning.ulms.admin.certificate.form.CertForm;
import com.ulearning.ulms.core.util.StringUtil;
import com.ulearning.ulms.core.util.Config;
import com.ulearning.ulms.course.dao.CourseDAO;
import com.ulearning.ulms.course.dao.CourseDAOFactory;
import com.ulearning.ulms.course.dao.MasterDAO;
import com.ulearning.ulms.course.dao.MasterDAOFactory;
import com.ulearning.ulms.course.exceptions.CourseAppException;
import com.ulearning.ulms.course.helper.CourseHelper;
import com.ulearning.ulms.course.model.CatalogModel;
import com.ulearning.ulms.course.model.CourseModel;
import com.ulearning.ulms.course.util.CourseKeys;
import com.ulearning.ulms.course.util.TreeFlush;
import com.ulearning.ulms.tools.upload.action.MultipartParamUtils;
import com.ulearning.ulms.tools.upload.action.UploadAction;
import com.ulearning.ulms.util.LMSConstants;
import com.ulearning.ulms.util.log.LogUtil;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import org.apache.webapp.admin.TreeControl;
import org.apache.webapp.admin.TreeControlNode;
import org.apache.commons.lang.StringUtils;

import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * @author <a href="mailto:youmail@yourdomain.com">yourname</a> Date: 2005-3-10
 */
public class CatalogRemoveAction extends UploadAction
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                HttpSession session = request.getSession();
                MultipartParamUtils mp = new MultipartParamUtils(request,
                        1024 * 1014 * 10);
                String selectCourse = request.getParameter("selectCourse");
                String selectCert = request.getParameter("selectCert");
                String selectCat = request.getParameter("selectCat");
                String catalogID = request.getParameter("catalogIDs");
                LogUtil.info("CatalogRemoveAction", "catalogID============ " +
                        catalogID);

                List courseIDs = StringUtil.split(selectCourse, "/");
                List certIDs = StringUtil.split(selectCert, "/");
                List catIDs = StringUtil.split(selectCat, "/");
                String tempID = "";
                ServletContext sevletContext = getServlet().getServletContext();

                //获得树
                String aspID = "0";

                if (session.getAttribute(LMSConstants.USER_ASPID_KEY) != null)
                {
                        aspID = (String) session.getAttribute(LMSConstants.USER_ASPID_KEY);
                }

                String orgID = "0";

                if (session.getAttribute(LMSConstants.USER_ORGID_KEY) != null)
                {
                        aspID = (String) session.getAttribute(LMSConstants.USER_ORGID_KEY);
                }

                CourseModel cm = null;
                CertForm cf = null;
                CatalogModel cmm = null;
                CertDAO certDao = null;
                CourseDAO courseDao = null;
                MasterDAO catDao = null;

                for (int i = 0; (courseIDs != null) && (i < courseIDs.size()); i++)
                {
                        tempID = (String) courseIDs.get(i);

                        if ((tempID != null) && !tempID.equals(""))
                        {
                                LogUtil.debug("CatalogRemoveAction",
                                        "courseID=====================" + tempID);
                                courseDao = CourseDAOFactory.getDAO();
                                cm = courseDao.getCourse(Integer.parseInt(tempID));
                                cm.setCatalogID(Integer.parseInt(catalogID));
                                cm.setModifyDate(new Date());
                                courseDao.updateCourse(cm);
                        }
                }

                for (int i = 0; (certIDs != null) && (i < certIDs.size()); i++)
                {
                        tempID = (String) certIDs.get(i);

                        if ((tempID != null) && !tempID.equals(""))
                        {
                                certDao = CertDAOFactory.getDAO();
                                LogUtil.debug("CatalogRemoveAction",
                                        "cert========================= " + tempID);
                                cf = certDao.getCert(Integer.parseInt(tempID));
                                cf.setCatalogID(Integer.parseInt(catalogID));
                                certDao.updateCert(cf);
                        }
                }

                for (int i = 0; (catIDs != null) && (i < catIDs.size()); i++)
                {
                        tempID = (String) catIDs.get(i);

                        if ((tempID != null) && !tempID.equals(""))
                        {
                                catDao = MasterDAOFactory.getDAO();

                                if (catDao.isHierachical(Integer.parseInt(tempID),
                                        Integer.parseInt(catalogID)))
                                {
                                        LogUtil.debug("course", "[CourseDAOImpl]  不能把目录放到它的子目录中!");
                                        throw new CourseAppException("不能把目录放到它的子目录中!");
                                }

                                cmm = catDao.getCatalog(Integer.parseInt(tempID));
                                cmm.setParentID(Integer.parseInt(catalogID));
                                catDao.updateCatalog(cmm);
                        }
                }

                LogUtil.info("CatalogRemoveAction",
                        "children size 33==========================" +
                                sevletContext.getAttribute(LMSConstants.TREE_PUBLISH + aspID));

                //更新树
                TreeFlush.flush(sevletContext, session, Integer.parseInt(aspID), null);

                String resultScreen = "success";
                request.setAttribute("catalogID", "0");

                return mapping.findForward(resultScreen);
        }
}
