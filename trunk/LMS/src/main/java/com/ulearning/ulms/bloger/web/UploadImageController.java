/*
 * Created on 2004-10-9
 * Author: Huaxia, Copyright (C) 2004, Huaxia.
 */
package com.ulearning.ulms.bloger.web;

import com.ulearning.ulms.bloger.domain.*;
import com.ulearning.ulms.bloger.logic.Facade;
import com.ulearning.ulms.core.util.Config;

import org.springframework.web.multipart.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import java.io.*;

import java.util.*;

import javax.servlet.http.*;


/**
 * Upload an image.
 *
 * @author Huaxia
 */
public class UploadImageController implements Controller
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
                if (baseDir == null)
                {
                        synchronized (this)
                        {
                                File file = new File(Config.getUploadPhysicalPath());
                                file.mkdir();
                                file = new File(Config.getUploadPhysicalPath() +
                                        File.separator + "blogIMG");
                                file.mkdir();
                                baseDir = Config.getUploadPhysicalPath() + File.separator +
                                        "blogIMG";

                                if (!baseDir.endsWith("/") && !baseDir.endsWith("\\"))
                                {
                                        baseDir = baseDir + File.separator;
                                }

                                System.out.println("[INFO] Set upload dir = " + baseDir);
                        }
                }

                Identity id = Identity.getIdentity(request);
                MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

                int categoryId = Integer.parseInt(multipartRequest.getParameter(
                        "categoryId"));
                String title = multipartRequest.getParameter("title");

                // get file:
                MultipartFile file = multipartRequest.getFile("file");
                System.out.println("File: " + file.getOriginalFilename());

                String filename = file.getOriginalFilename();
                int n = filename.lastIndexOf(".");

                if (n == (-1))
                {
                        throw new IllegalArgumentException(
                                "The image file is invalid. Only \".gif\", \".jpg\" and \".png\" formats are allowed.");
                }

                String type = filename.substring(n);

                Image image = new Image();
                image.setCategoryId(categoryId);
                image.setTitle(title);
                image.setVisible("0".equals(request.getParameter("visible")) ? false
                        : true);
                image.setImageType(type);
                // create image:
                System.out.println("contextPath=" + request.getContextPath());
                facade.createImage(id, image, baseDir, file.getInputStream());

                // show result:
                Map map = new HashMap();
                map.put("account", facade.getAccount(id.getAccountId()));
                map.put("position", "管理 >> 结果");
                map.put("message", "图片已经更新!");
                map.put("url", "manageImage.c?categoryId=" + categoryId);

                return new ModelAndView("manage/result", map);
        }
}
