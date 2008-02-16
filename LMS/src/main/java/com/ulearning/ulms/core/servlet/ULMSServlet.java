/**
 * ulmsServlet.java.
 * User: Fengch  Date: 2005-4-1 15:16:15
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.core.servlet;

import com.ulearning.ulms.content.dao.ContentManageDAOFactory;
import com.ulearning.ulms.content.helper.ContentManageHelper;
import com.ulearning.ulms.content.model.ContentModel;
import com.ulearning.ulms.core.crypto.MD5Encrypt;
import com.ulearning.ulms.core.security.bean.SecurityHelper;
import com.ulearning.ulms.core.util.Config;
import com.ulearning.ulms.core.util.IOUtil;
import com.ulearning.ulms.core.util.StringUtil;
import com.ulearning.ulms.tools.newdocument.dao.NewDocumentDAO;
import com.ulearning.ulms.tools.newdocument.dao.NewDocumentDAOImpl;
import com.ulearning.ulms.tools.newdocument.model.NewDocumentModel;
import com.ulearning.ulms.tools.newdocument.webimpls.NewDocumentWeblmpl;
import com.ulearning.ulms.util.log.LogUtil;

import org.apache.commons.lang.RandomStringUtils;

import java.awt.*;
import java.awt.image.BufferedImage;

import java.io.*;

import java.util.Random;

import javax.imageio.ImageIO;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ULMSServlet extends DefaultServlet
{
        public void init(ServletConfig config) throws ServletException
        {
                LogUtil.info("system", "[ulmsServlet]======================init");
                super.init(config);
        }

        public void doPost(HttpServletRequest req, HttpServletResponse res)
                throws IOException, ServletException
        {
                doGet(req, res);
        }

        public void doGet(HttpServletRequest request, HttpServletResponse response)
                throws IOException, ServletException
        {
                LogUtil.info("system", "[ulmsServlet]======================start!");

                String path = request.getServletPath();
                LogUtil.info("system", "[ulmsServlet]======================path=" +
                        path);

                try
                {
                        LogUtil.info("system",
                                "[ulmsServlet]======================path.indexOf(\"/authImage.jpeg\")=" +
                                        path.indexOf("/authImage.jpeg"));

                        if (path.indexOf("/authImage.jpeg") != -1)
                        {
                                System.out.println("[ulmsServlet]cccccccc");
                                executeGernateAuthImg(response, request);
                        }
                        else
                        {
                                String method = StringUtil.nullToStr(request.getParameter(
                                        "method"));
                                System.out.println("[ulmsServlet]dddddddddd");
                                System.out.println("method = " + method);

                                if (method.equals("ToolDownload"))
                                {
                                        System.out.println("[ulmsServlet]doget22222");

                                        NewDocumentDAO newDocumentDAO = new NewDocumentDAOImpl();
                                        int docID = Integer.parseInt(request.getParameter(
                                                "documentIDs"));
                                        NewDocumentWeblmpl webImpl = new NewDocumentWeblmpl();
                                        System.out.println("[ulmsServlet]doget3333");

                                        NewDocumentModel newDocument = webImpl.getDocument(docID);
                                        String link = Config.getUploadVirtualPath() +
                                                newDocument.getLink();
                                        System.out.println("[ulmsServlet]doget4444");

                                        String link2 = IOUtil.getFileName(link);
                                        String phlink = Config.getUploadPhysicalPath() +
                                                newDocument.getLink();
                                        phlink = StringUtil.replaceString(phlink, "/",
                                                File.separator);

                                        int downloadTimes = newDocument.getDownloadTimes();
                                        newDocument.setDownloadTimes(downloadTimes + 1);
                                        LogUtil.info("system",
                                                "[ulmsServlet]===========newDocument.getContentClobString()=" +
                                                        newDocument.getContentClobString());
                                        newDocument.setTempClobString(newDocument.getContentClobString());
                                        newDocumentDAO.updateDocument(newDocument);

                                        /*executeDownLoad(response,"C1vOPcW2QkNe.gif",
                                          "\\\\172.20.29.10\\bnu_upload\\portal\\downloadtool\\0\\C1vOPcW2QkNe.gif",
                                          "APPLICATION/OCTET-STREAM");
                                        */
                                        executeDownLoad(response, link2, phlink,
                                                "APPLICATION/OCTET-STREAM");
                                }
                                else if (method.equals("ContentDownload"))
                                {
                                        int contentID = Integer.parseInt(request.getParameter(
                                                "contentID"));
                                        ContentModel cm = ContentManageHelper.getContent(contentID);

                                        String httpUrl = cm.getLocation();

                                        String fileExtName = IOUtil.getFileName(httpUrl);
                                        String fileName = cm.getTitle() +
                                                IOUtil.getFileExtName(httpUrl);
                                        String phlink = Config.getUploadPhysicalPath() + httpUrl;
                                        phlink = StringUtil.replaceString(phlink, "/",
                                                File.separator);

                                        int downloadTimes = cm.getDownloadTimes();
                                        cm.setDownloadTimes(downloadTimes + 1);
                                        ContentManageDAOFactory.getDAO().updateContent(cm);

                                        executeDownLoad(response, fileName, phlink,
                                                "APPLICATION/OCTET-STREAM");
                                }
                                else if (method.equals("MD5"))
                                {
                                        String parameter = StringUtil.nullToStr(request.getParameter(
                                                "parameter"));
                                        executeMD5String(response, parameter);
                                }
                                else if (method.equals("AuthLogin"))
                                {
                                        String loginName = StringUtil.nullToStr(request.getParameter(
                                                "loginName"));
                                        String password = StringUtil.nullToStr(request.getParameter(
                                                "password"));
                                        executeAuthLogin(response, loginName, password);
                                }
                        }
                }
                catch (Exception ex)
                {
                        ex.printStackTrace();
                }
        }

        public void executeGernateAuthImg(HttpServletResponse response,
                                          HttpServletRequest request) throws IOException
        {
                response.setContentType("image/jpeg");

                String rand = RandomStringUtils.randomAlphabetic(4);
                rand = rand.toUpperCase();
                request.getSession().setAttribute("random", rand);

                //在内存中创建图象
                int iWidth = 66;

                //在内存中创建图象
                int iHeight = 18;
                BufferedImage image = new BufferedImage(iWidth, iHeight,
                        BufferedImage.TYPE_INT_RGB);

                //获取图形上下文
                Graphics g = image.getGraphics();
                //设定背景色
                g.setColor(Color.white);
                g.fillRect(0, 0, iWidth, iHeight);
                //画边框
                g.setColor(Color.black);
                g.drawRect(0, 0, iWidth - 1, iHeight - 1);
                //取随机产生的认证码(4位数字)

                //将认证码显示到图象中
                g.setColor(Color.blue);
                g.setFont(new Font("Times New Roman", Font.PLAIN, 18));
                g.drawString(rand, 10, 15);

                //随机产生15个干扰点,使图象中的认证码不易被其它程序探测到
                Random random = new Random();

                for (int iIndex = 0; iIndex < 15; iIndex++)
                {
                        int x = random.nextInt(iWidth);
                        int y = random.nextInt(iHeight);
                        g.drawLine(x, y, x, y);
                }

                //图象生效
                g.dispose();
                //输出图象到页面
                ImageIO.write(image, "JPEG", response.getOutputStream());
        }

        public void executeMD5String(HttpServletResponse response, String str)
                throws Exception
        {
                response.setContentType("text/plain; charset=gb2312");

                PrintWriter out = response.getWriter();

                String encryptStr = "";

                if (str != null)
                {
                        encryptStr = MD5Encrypt.encrypt(str);
                }

                LogUtil.info("system",
                        "[ulmsServlet]executeMD5String======================System file.encoding:" +
                                System.getProperty("file.encoding"));
                LogUtil.info("system",
                        "[ulmsServlet]executeMD5String======================encryptStr:" +
                                encryptStr);

                out.println(encryptStr);
        }

        public void executeAuthLogin(HttpServletResponse response,
                                     String loginName, String password) throws Exception
        {
                response.setContentType("text/plain; charset=gb2312");

                PrintWriter out = response.getWriter();

                String encryptStr = "";
                int userID = SecurityHelper.checkSecurity(loginName, password);

                LogUtil.info("system",
                        "[ulmsServlet]executeAuthUser======================System file.encoding:" +
                                System.getProperty("file.encoding"));
                LogUtil.info("system",
                        "[ulmsServlet]executeAuthUser======================userID:" +
                                userID);

                out.println(userID);
        }

        public void executeDownLoad(HttpServletResponse response, String fileName,
                                    String fileUrl, String contentType) throws Exception
        {
                LogUtil.info("system", "begin download " + fileName);

                String fileURL = fileUrl;
                LogUtil.info("system", fileURL);

                if (fileURL == null)
                {
                        return;
                }

                try
                {
                        File file = new File(fileUrl);
                        FileInputStream bis = new FileInputStream(file);

                        response.setContentType(contentType);
                        response.setHeader("Content-disposition",
                                "attachment; filename=" + fileName);
                        /*response.setHeader("Content-disposition",
                      "filename=" + fileName);*/
                        response.setContentLength((int) file.length());

                        OutputStream bos = response.getOutputStream();

                        byte[] buff = new byte[1024];
                        int readCount = 0;
                        int i = 0;
                        readCount = bis.read(buff);

                        while (readCount != -1)
                        {
                                bos.write(buff, 0, readCount);
                                readCount = bis.read(buff);
                        }

                        LogUtil.info("system", "read finished!");

                        if (bis != null)
                        {
                                bis.close();
                        }

                        if (bos != null)
                        {
                                bos.close();
                        }
                }
                catch (Exception e)
                {
                        e.printStackTrace();
                        throw e;
                }
        }
}
