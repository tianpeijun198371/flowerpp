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
 * TODO Description here...
 *
 * @author Huaxia
 */
public class UpdateArticleController implements Controller
{
        private Facade facade;
        String baseDir = "";

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
                int articleId = Integer.parseInt(multipartRequest.getParameter(
                        "articleId"));
                Article article = facade.getArticleInfo(articleId);
                String inclosure = multipartRequest.getParameter("inclosure");
                String inclosurePath = multipartRequest.getParameter("inclosurePath");
                String operation = "0"; //0,do nothing ; 1,update ;

                if (multipartRequest.getParameter("operation") != null)
                {
                        operation = multipartRequest.getParameter("operation");
                }

                if (file.isEmpty())
                {
                        operation = "1";
                }

                if (!file.isEmpty() && !operation.equals("1")) //用户重新上传附件
                {
                        System.out.println("用户重新上传附件");
                        deleteInclosure(article);

                        long uploadLimit = 1 * 1024 * 1024;

                        if (Config.get("blog-upload-limit") != null)
                        {
                                uploadLimit = Long.parseLong(Config.get("blog-upload-limit"));
                        }

                        baseDir = Config.getUploadPhysicalPath() + File.separator +
                                "blogIMG";

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

                        if (inclosure.length() < 1)
                        {
                                article.setInclosure(originalFilename);
                        }
                        else
                        {
                                article.setInclosure(inclosure);
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
                else if (!operation.equals("1")) //不修改附件
                {
                        System.out.println("不修改附件");

                        //article.setInclosure(multipartRequest.getParameter("inclosure"));
                        //article.setInclosurePath(multipartRequest.getParameter("inclosurePath"));
                }
                else if (operation.equals("1")) //删除附件
                {
                        System.out.println("删除附件");
                        deleteInclosure(article);
                }

                article.setTitle(multipartRequest.getParameter("title"));
                article.setCategoryId(Integer.parseInt(multipartRequest.getParameter(
                        "categoryId")));
                article.setType(Integer.parseInt(multipartRequest.getParameter("type")));
                article.setVisible("0".equals(multipartRequest.getParameter("visible"))
                        ? false : true);
                article.setUpdatedDate(new Date());

                String summary = multipartRequest.getParameter("summary");
                article.setSummary((summary == null) ? "" : summary);

                String content = multipartRequest.getParameter("content");
                article.setContent(((content == null) || (content.length() < 1))
                        ? "暂时没有内容" : content);

                facade.updateArticle(id, article);

                // create model:
                Map map = new HashMap();
                map.put("account", facade.getAccount(id.getAccountId()));
                map.put("url", "manageArticle.c");
                map.put("position", "管理 >> 结果");
                map.put("message", "文章 \"" + article.getTitle() + "\" 已经保存!");

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

        public boolean deleteInclosure(Article article)
        {
                boolean delelted = false;
                System.out.println("article.getInclosurePath()=" +
                        article.getInclosurePath());

                if ((article.getInclosurePath() != null) &&
                        (article.getInclosurePath().length() > 1))
                {
                        String filePath = Config.getUploadPhysicalPath() + File.separator +
                                "blogIMG" + File.separator + article.getInclosurePath();
                        System.out.println("Will delete file:" + filePath);

                        File delFile = new File(filePath);

                        if (delFile.exists())
                        {
                                delelted = delFile.delete();

                                if (delelted)
                                {
                                        System.out.println("File has been deleted " + filePath);
                                }
                                else
                                {
                                        System.out.println("Can't delete the file: " + filePath);
                                }
                        }
                }

                article.setInclosurePath("");
                article.setInclosure("");

                return delelted;
        }
}
