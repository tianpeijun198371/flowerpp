/*
 * Created on 2004-10-13
 * Author: Huaxia, Copyright (C) 2004, Huaxia.
 */
package com.ulearning.ulms.bloger.web;

import com.ulearning.ulms.bloger.logic.Facade;
import com.ulearning.ulms.core.util.Config;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import java.util.*;

import javax.servlet.http.*;


/**
 * Delete the image.
 *
 * @author Huaxia
 */
public class DeleteImageController implements Controller
{
        private String baseDir = null;
        private Facade facade;

        public void setBaseDir(String baseDir)
        {
                this.baseDir = baseDir;
        }

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
                                baseDir = Config.getUploadPhysicalPath() + "//blogIMG";

                                if (!baseDir.endsWith("/") && !baseDir.endsWith("\\"))
                                {
                                        baseDir = baseDir + "/";
                                }

                                System.out.println("[INFO] Set upload dir = " + baseDir);
                        }
                }

                Identity id = Identity.getIdentity(request);
                int imageId = Integer.parseInt(request.getParameter("imageId"));
                int categoryId = Integer.parseInt(request.getParameter("categoryId"));
                facade.deleteImage(id, imageId, baseDir);

                // show result:
                Map map = new HashMap();
                map.put("account", facade.getAccount(id.getAccountId()));
                map.put("message", "图片已经成功删除!");
                map.put("url", "manageImage.c?categoryId=" + categoryId);
                map.put("position", "管理 >> 结果");

                return new ModelAndView("manage/result", map);
        }
}
