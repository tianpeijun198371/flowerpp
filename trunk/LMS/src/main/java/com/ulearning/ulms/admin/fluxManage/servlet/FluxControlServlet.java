/**
 * FluxControlServlet.java.
 * User: ff  Date: 2004-11-24
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.admin.fluxManage.servlet;

import com.ulearning.ulms.admin.fluxManage.exceptions.FluxManageAuthErrorAppException;
import com.ulearning.ulms.admin.fluxManage.exceptions.FluxManageExceedSizeAppException;
import com.ulearning.ulms.admin.fluxManage.helper.FluxHelper;
import com.ulearning.ulms.admin.fluxManage.model.FluxModel;
import com.ulearning.ulms.admin.fluxManage.util.FluxUtil;
import com.ulearning.ulms.admin.sysconfig.bean.SysConfigHelper;
import com.ulearning.ulms.admin.sysconfig.form.SysConfigForm;
import com.ulearning.ulms.core.security.bean.SecurityConstants;
import com.ulearning.ulms.core.security.bean.SecurityHelper;
import com.ulearning.ulms.core.util.StringUtil;
import com.ulearning.ulms.course.helper.CourseContentHelper;
import com.ulearning.ulms.util.LMSConstants;
import com.ulearning.ulms.util.log.LogUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import java.net.URL;

import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class FluxControlServlet extends HttpServlet
{
        public void init(ServletConfig config) throws ServletException
        {
                LogUtil.info("system", "[FluxControlServlet]======================init");
                super.init(config);
        }

        public void doPost(HttpServletRequest req, HttpServletResponse res)
                throws IOException, ServletException
        {
                doGet(req, res);
        }

        public void doGet(HttpServletRequest req, HttpServletResponse res)
                throws IOException, ServletException
        {
                LogUtil.info("system",
                        "[FluxControlServlet]======================start!");

                // res.setContentType("text/plain; charset=gb2312");
                InputStream in = null;

                try
                {
                        SysConfigHelper helper = new SysConfigHelper();
                        SysConfigForm scf = helper.getSysConfig("0");

                        int nodeID = StringUtil.parseInt(req.getParameter("nodeID"));
                        int userID = StringUtil.parseInt((String) req.getSession()
                                .getAttribute(LMSConstants.USERID_KEY));
                        int relationID = CourseContentHelper.getCourseContent(String.valueOf(
                                nodeID)).getRelationID();

                        //�ж�Ȩ��
                        if (!scf.getIsAllowDownloadCourseWare().equals("1") ||
                                !SecurityHelper.isHasPermission(userID, relationID,
                                        SecurityConstants.USER_COURSE_RELATION,
                                        SecurityConstants.COURSE_CONTENT_VIEW))
                        {
                                throw new FluxManageAuthErrorAppException();
                        }

                        String urlStr = CourseContentHelper.getCourseContentURL(nodeID);
                        String clientBrowserName = req.getParameter("navigator.appName");
                        String clientBrowserVersion = req.getParameter(
                                "navigator.appVersion");
                        String clientOsName = req.getParameter("navigator.platformName");
                        String clientOsVersion = req.getParameter(
                                "navigator.platformVersion");
                        clientBrowserVersion = getCilentBrowserVersion(clientBrowserName,
                                clientBrowserVersion);

                        LogUtil.info("system",
                                "[FluxControlServlet]======================urlStr:" + urlStr);
                        LogUtil.info("system",
                                "[FluxControlServlet]======================clientBrowserVersion:" +
                                        clientBrowserVersion);

                        if (urlStr == null)
                        {
                                throw new FluxManageAuthErrorAppException();
                        }

                        // �õ��ļ����ֺ�·��
                        String filename = FluxUtil.getURLResourceName(urlStr);

                        URL aURL = new URL(urlStr);
                        in = aURL.openStream();

                        int size_available = in.available();
                        LogUtil.info("system",
                                "[FluxControlServlet]======================available:" +
                                        size_available);

                        // д������Ϣ
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        byte[] b = new byte[1024];

                        while (true)
                        {
                                int bytes = in.read(b);

                                if (bytes == -1)
                                {
                                        break;
                                }

                                baos.write(b, 0, bytes);
                        }

                        LogUtil.info("system",
                                "[FluxControlServlet]======================baos.size():" +
                                        baos.size());

                        long limitSize = scf.getDownloadCourseWareSizeLimit();

                        if (baos.size() > limitSize)
                        {
                                throw new FluxManageExceedSizeAppException();
                        }

                        long flux = baos.size();
                        // ������Ӧͷ�����ر�����ļ���,��һ���������жϿμ�����Ȩ�޵�����
                        res.setContentType("APPLICATION/OCTET-STREAM");
                        res.setHeader("Content-Disposition",
                                "attachment; filename=\"" + filename + "\"");

                        res.setContentLength(baos.size());

                        ServletOutputStream out = res.getOutputStream();
                        out.write(baos.toByteArray(), 0, baos.size());
                        out.flush();

                        //flux control
                        int fluxID = 0;
                        int orgID = StringUtil.parseInt((String) req.getSession()
                                .getAttribute(LMSConstants.USER_ASPID_KEY));
                        int clientID = StringUtil.parseInt((String) req.getSession()
                                .getAttribute(LMSConstants.USERID_KEY));
                        String clientLoginName = (String) req.getSession()
                                .getAttribute(LMSConstants.LOGINNAME_KEY);
                        String clientUserName = null;
                        String clientIP = req.getRemoteAddr();
                        Date createDate = new Date();
                        String description = null;
                        FluxModel fm = new FluxModel(fluxID, orgID, clientID,
                                clientLoginName, clientUserName, clientIP,
                                clientBrowserName, clientBrowserVersion, clientOsName,
                                clientOsVersion, flux, createDate, description);

                        LogUtil.info("system",
                                "[FluxControlServlet]======================start fluxControl");
                        FluxHelper.fluxControl(fm);
                }
                catch (FluxManageExceedSizeAppException ex)
                {
                        req.setAttribute(LMSConstants.ERROR_PAGE_INFO, "�˿μ�̫����û��Ȩ�����ش˿μ���");
                        forward(req, res, "/error_page/error.jsp");
                }
                catch (FluxManageAuthErrorAppException ex)
                {
                        req.setAttribute(LMSConstants.ERROR_PAGE_INFO, "��û��Ȩ�����ش˿μ���");
                        forward(req, res, "/error_page/error.jsp");
                }
                catch (java.net.ConnectException ce)
                {
                        req.setAttribute(LMSConstants.ERROR_PAGE_INFO,
                                "�Ҳ����˿μ��������ǿμ��Ѿ���ɾ��������û�д�Web��������");
                        forward(req, res, "/error_page/error.jsp");
                }
                catch (Exception ex)
                {
                        LogUtil.info("system",
                                "[FluxControlServlet]======================Unknowed Exception in servlet=" +
                                        ex.getMessage());
                        ex.printStackTrace();
                }
                finally
                {
                        if (in != null)
                        {
                                in.close();
                        }
                }
        }

        /**
         * Forward to a URL
         *
         * @param request
         * @param response
         * @param url
         * @throws ServletException
         * @throws IOException
         */
        protected void forward(HttpServletRequest request,
                               HttpServletResponse response, String url)
                throws ServletException, IOException
        {
                response.setContentType("text/html; charset=gb2312");

                RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
                rd.forward(request, response);
        }

        /**
         * ����������汾��
         *
         * @param browser
         * @param ver
         * @return
         */
        private String getCilentBrowserVersion(String browser, String ver)
        {
                String version = null;

                if (browser.indexOf("Microsoft") == -1)
                {
                        version = getCilentBrowserVersionForNotIE(ver);
                }
                else
                {
                        version = getCilentBrowserVersionForIE(ver);
                }

                return version;
        }

        /**
         * ���ط� IE������汾��
         *
         * @param
         * @return
         */
        private String getCilentBrowserVersionForNotIE(String ver)
        {
                String str = null;

                try
                {
                        List l = StringUtil.split(ver, " ");

                        if (l != null)
                        {
                                str = (String) l.get(0);
                        }
                }
                catch (Exception ex)
                {
                }

                return str;
        }

        /**
         * ���ط� IE������汾��
         *
         * @param
         * @return
         */
        private String getCilentBrowserVersionForIE(String ver)
        {
                String str = null;

                try
                {
                        List l = StringUtil.split(ver, " ");

                        if (l != null)
                        {
                                str = (String) l.get(3);
                        }
                }
                catch (Exception ex)
                {
                }

                return truncate(str, 3);
        }

        /**
         * return  the string truncated,<br>
         * if size>str.length,then  truncat the str.
         *
         * @param str
         * @param size
         * @return
         */
        public String truncate(String str, int size)
        {
                if (str == null)
                {
                        return "";
                }

                if ((size < 0) || (size >= str.length()))
                {
                        return str;
                }
                else
                {
                        return str.substring(0, size);
                }
        }
}
