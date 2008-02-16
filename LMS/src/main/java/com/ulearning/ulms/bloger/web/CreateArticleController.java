/*
 * Created on 2004-10-8
 * Author: Huaxia, Copyright (C) 2004, Huaxia.
 */
package com.ulearning.ulms.bloger.web;

import com.ulearning.ulms.bloger.domain.Article;
import com.ulearning.ulms.bloger.logic.Facade;
import com.ulearning.ulms.core.util.Config;
import com.ulearning.ulms.tools.announcement.exceptions.AnnouncementSysException;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import java.io.*;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Create a new article.
 *
 * @author Huaxia
 */
public class CreateArticleController implements Controller
{
        private String baseDir = null;
        private Facade facade;

        public void setFacade(Facade facade)
        {
                this.facade = facade;
        }

        public ModelAndView handleRequest(HttpServletRequest request,
                                          HttpServletResponse response) throws Exception
        {
                MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
                MultipartFile file = multipartRequest.getFile("file");

                Identity id = Identity.getIdentity(multipartRequest);
                Article article = new Article();

                article.setInclosure(multipartRequest.getParameter("inclosure"));
                article.setInclosurePath(multipartRequest.getParameter("inclosurePath"));

                if (!file.isEmpty())
                {
                        long uploadLimit = 1 * 1024 * 1024;

                        if (Config.get("blog-upload-limit") != null)
                        {
                                uploadLimit = Long.parseLong(Config.get("blog-upload-limit"));
                        }

                        File uploadPath = new File(Config.getUploadPhysicalPath());
                        baseDir = Config.getUploadPhysicalPath() + File.separator +
                                "blogIMG";
                        uploadPath = new File(baseDir);
                        uploadPath.mkdir();
                        uploadPath = new File(baseDir + File.separator + id.getAccountId());
                        uploadPath.mkdir();
                        uploadPath = null;
                        System.out.println("[INFO] Set upload dir = " + baseDir);

                        long exsitFilesize = getFoldSize(baseDir + File.separator +
                                id.getAccountId());
                        System.out.println(" exsitFilesize=" + exsitFilesize);

                        if (uploadLimit < (exsitFilesize + file.getSize()))
                        {
                                throw new RuntimeException("对不起，您的上载空间限制为" + uploadLimit +
                                        "字节,您已经使用" + exsitFilesize + "字节，当前上传文件为" + file.getSize() +
                                        "字节，请删除不必要的文件或者联系管理员！");
                        }

                        String originalFilename = file.getOriginalFilename();
                        System.out.println("originalFilename=" + originalFilename);

                        int p = originalFilename.lastIndexOf(".");

                        if (p == (-1))
                        {
                                throw new IllegalArgumentException("The image file is invalid.");
                        }

                        String affix = originalFilename.substring(p,
                                originalFilename.length());

                        if (article.getInclosure().length() < 1)
                        {
                                article.setInclosure(originalFilename);
                        }

                        System.out.println("inclosure=" + article.getInclosure());

                        Calendar cal = Calendar.getInstance();
                        String fileNew = new String();
                        fileNew = "" + cal.getTimeInMillis() + affix;
                        System.out.println("fileNew=" + fileNew);
                        article.setInclosurePath(File.separator + id.getAccountId() +
                                File.separator + fileNew);
                        System.out.println("inclosurePath=" + article.getInclosurePath());

                        // create file:
                        OutputStream os = null;

                        try
                        {
                                System.out.println("FilePath=" + baseDir +
                                        article.getInclosurePath());
                                os = new BufferedOutputStream(new FileOutputStream(baseDir +
                                        article.getInclosurePath()));
                        }
                        catch (FileNotFoundException e)
                        {
                                e.printStackTrace();
                        }

                        byte[] buffer = new byte[1024];
                        int n;
                        int i = 0;
                        InputStream is = file.getInputStream();

                        try
                        {
                                while ((n = is.read(buffer)) != (-1))
                                {
                                        os.write(buffer, 0, n);
                                }

                                os.close();
                        }
                        catch (IOException e)
                        {
                                e.printStackTrace();
                        }
                }
                else
                {
                        article.setInclosure("");
                        article.setInclosurePath("");
                }

                // set categoryId:
                article.setCategoryId(Integer.parseInt(multipartRequest.getParameter(
                        "categoryId")));
                // set type:
                article.setType(Integer.parseInt(multipartRequest.getParameter("type")));
                // set title:
                article.setTitle(multipartRequest.getParameter("title"));

                // set summary:
                String summary = multipartRequest.getParameter("summary");
                article.setSummary((summary == null) ? "" : summary);
                // set visible:
                article.setVisible("0".equals(multipartRequest.getParameter("visible"))
                        ? false : true);

                // set content:
                String content = multipartRequest.getParameter("content");
                article.setContent(((content == null) || (content.length() < 1))
                        ? "暂时没有内容" : content);
                // set created date:
                article.setCreatedDate(new Date());
                // ok, create the article:
                facade.createArticle(id, article);

                // show result:
                Map map = new HashMap();
                map.put("account", facade.getAccount(id.getAccountId()));
                map.put("url", "manageArticle.c");
                map.put("position", "管理 >> 结果");
                map.put("message", "新文章 \"" + article.getTitle() + "\" 撰写成功!");

                return new ModelAndView("manage/result", map);
        }

        public long getFoldSize(String foldPath) throws AnnouncementSysException
        {
                long s = 0;
                File fp = new File(foldPath);

                if (fp.exists() && fp.isDirectory())
                {
                        File[] f = fp.listFiles();

                        for (int i = 0; i < f.length; i++)
                        {
                                if (f[i].isDirectory())
                                {
                                        File[] twoLevel = f[i].listFiles();

                                        for (int j = 0; j < twoLevel.length; j++)
                                        {
                                                s += twoLevel[j].length();
                                        }
                                }

                                s = s + f[i].length();
                        }
                }
                else
                {
                        if (fp.isFile())
                        {
                                fp.delete();
                        }

                        fp.mkdirs();
                }

                return s;
        }
}
