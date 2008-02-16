/**
 * NewDocumentDBbean.java.
 * User: Administrator  Date: 2005-3-8
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.newdocument.bean;

import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.core.util.Config;
import com.ulearning.ulms.core.util.IOUtil;
import com.ulearning.ulms.core.util.StringUtil;
import com.ulearning.ulms.core.util.DateTimeUtil;
import com.ulearning.ulms.tools.newdocument.dao.NewDcumentDAOFactory;
import com.ulearning.ulms.tools.newdocument.dao.NewDocumentDAO;
import com.ulearning.ulms.tools.newdocument.exceptions.NewDocumentSysException;
import com.ulearning.ulms.tools.newdocument.form.NewDocumentCatalogForm;
import com.ulearning.ulms.tools.newdocument.form.NewDocumentForm;
import com.ulearning.ulms.tools.newdocument.model.NewDocumentCatalogModel;
import com.ulearning.ulms.tools.newdocument.model.NewDocumentModel;
import com.ulearning.ulms.tools.newdocument.util.NewDocumentConstants;
import com.ulearning.ulms.util.log.LogUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;


public class NewDocumentHelper
{
        private static NewDocumentDAO newDocumentDAO;


        static
        {
                try
                {
                        newDocumentDAO = NewDcumentDAOFactory.getDAO();

                }
                catch (Exception ex)
                {
                        ex.printStackTrace();
                }
        }

        public static void insertDocument(NewDocumentForm newDocumentForm) throws NewDocumentSysException
        {
                LogUtil.debug("course", "[NewDocumentHelper]insertDocument------start!");
                NewDocumentModel ndm = newDocumentForm.getNewDocumentModel();
                /* Date dt = new Date();
            Date dt2 = new Date();*/
                /*  String format="yyyy.MM.dd G 'at' HH:mm:ss z";//格式化日期和时间
             SimpleDateFormat formatter=new SimpleDateFormat(format);
              dt2= new Date(formatter.format(dt));*/

                //System.out.println("[NewDocumentHelper]insertDocument  ndm.getIsOpenGuest() = " + ndm.getIsOpenGuest());
                ndm.setCreateDate(DateTimeUtil.parseDateTime(DateTimeUtil.FormatDateToWeb3(new Date())));
                ndm.setModifyDate(DateTimeUtil.parseDateTime(DateTimeUtil.FormatDateToWeb3(new Date())));
                newDocumentDAO.insertDocument(ndm);

                //System.out.println("[NewDocumentHelper]insertDocument  StringUtil.parseInt(newDocumentForm.getType()) = " + StringUtil.parseInt(newDocumentForm.getType()));
                //System.out.println("[NewDocumentHelper]insertDocument  (StringUtil.parseInt(newDocumentForm.getType()) == NewDocumentConstants.EXPERT) = " + (StringUtil.parseInt(newDocumentForm.getType()) == NewDocumentConstants.EXPERT));
                //针对专家库
                if (StringUtil.parseInt(newDocumentForm.getType()) == NewDocumentConstants.EXPERT)
                {
                        //System.out.println("[NewDocumentHelper]insertDocument (newDocumentForm.getIsOpenGuest()="+(newDocumentForm.getIsOpenGuest()));
                        if (StringUtil.nullToStr(newDocumentForm.getIsOpenGuest()).equals("1"))
                        {
                                //System.out.println("[NewDocumentHelper]insertDocument resetOtherExpertViewInLearningPortalStatus!");
                                newDocumentDAO.resetOtherExpertViewInLearningPortalStatus(ndm.getDocID());
                        }
                }
        }


        /**
         * @param form
         * @throws NewDocumentSysException
         */
        public static void insertCatalog(NewDocumentCatalogForm form)
                throws NewDocumentSysException
        {
                try
                {
                        LogUtil.debug("course", "[NewDocumentHelper]insertCatalog------start!");
                        NewDocumentCatalogModel ndm = form.getModel();
                        ndm.setCreateDate(new Date());
                        ndm.setModifyDate(new Date());
                        newDocumentDAO.insertCatalog(ndm);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new NewDocumentSysException(e);
                }
        }

        public static NewDocumentForm getDocument(int documenID) throws NewDocumentSysException
        {
                NewDocumentModel newDocumentModel = new NewDocumentModel();
                NewDocumentForm newDocForm = new NewDocumentForm();
                String typeS = Integer.toString(documenID);
                LogUtil.debug("course", "[NewDocumentHelper] -----------------type=" + typeS);
                NewDocumentDAO dao = NewDcumentDAOFactory.getDAO();
                newDocumentModel = dao.getDocument(documenID);
                return newDocForm.getNewDocumentForm(newDocumentModel);
        }

        public static void updCssOrder(List cssList) throws NewDocumentSysException
        {
                int strn = 0;
                for (int i = 0; i < cssList.size(); i++)
                {
                        String str = String.valueOf(cssList.get(i));
                        strn = str.indexOf("_");
                        String str_cssOrder = str.substring(0, strn);
                        LogUtil.debug("course", "[NewDocumentHerlper***********str_cssOrder=" + str_cssOrder);
                        String str_cssID = str.substring(strn + 1);
                        int cssID = Integer.parseInt(str_cssID);
                        int cssOrder = Integer.parseInt(str_cssOrder);
                        NewDocumentModel ndm = newDocumentDAO.getDocument(cssID);
                        ndm.setOrderIndex(cssOrder);

                        ndm.setTempClobString(ndm.getContentClobString());
                        newDocumentDAO.updateDocument(ndm);
                }
        }

        public static void changeRelease(List IDs) throws NewDocumentSysException
        {
                int strn = 0;
                for (int i = 0; i < IDs.size(); i++)
                {
                        NewDocumentModel ndm = newDocumentDAO.getDocument(StringUtil.parseInt(IDs.get(i).toString()));
                        int view = Math.abs(StringUtil.parseInt(ndm.getIsView()) - 1);

                        ndm.setIsView(String.valueOf(view));
                        ndm.setTempClobString(ndm.getContentClobString());

                        newDocumentDAO.updateDocument(ndm);
                }
        }


        /**
         * @param form
         * @throws NewDocumentSysException
         */
        public static void updateCatalog(NewDocumentCatalogForm form)
                throws NewDocumentSysException
        {
                try
                {
                        int id = form.getCatalogID();
                        NewDocumentCatalogModel model = newDocumentDAO.getCatalog(id);
                        model.setCatalogName(form.getCatalogName());
                        model.setDescription(form.getDescription());
                        model.setModifyDate(new Date());
                        newDocumentDAO.updateCatalog(model);

                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new NewDocumentSysException(e);
                }
        }

        public static void updDocument(NewDocumentForm newDocForm) throws NewDocumentSysException
        {
                NewDocumentModel ndm = newDocumentDAO.getDocument(newDocForm.getDocID());
                if (newDocForm.getType() != null)
                {

                        ndm.setType(newDocForm.getType());
                }

                ndm.setContent(newDocForm.getContent());

                ndm.setDepth(newDocForm.getDepth());
                ndm.setDisplayBeginDate(newDocForm.getDisplayBeginDate());
                ndm.setDisplayEndDate(newDocForm.getDisplayEndDate());
                ndm.setDocName(newDocForm.getDocName());
                ndm.setModifyDate(new Date());
                ndm.setDownloadTimes(newDocForm.getDownloadTimes());
                ndm.setIsContent(newDocForm.getIsContent());
                ndm.setIsOpenGuest(newDocForm.getIsOpenGuest());
                ndm.setIsUserful(newDocForm.getIsUserful());
                ndm.setIsView(newDocForm.getIsView());
                ndm.setLinkTitle(newDocForm.getLinkTitle());
                ndm.setOrderIndex(newDocForm.getOrderIndex());
                ndm.setParentID(newDocForm.getParentID());
                ndm.setRemark(newDocForm.getRemark());
                ndm.setUserName(newDocForm.getUserName());
                ndm.setRemark2(newDocForm.getRemark2());
                ndm.setRemark3(newDocForm.getRemark3());
                ndm.setRemark4(newDocForm.getRemark4());
                ndm.setRemark5(newDocForm.getRemark5());
                ndm.setRemark6(newDocForm.getRemark6());
                ndm.setStatus(newDocForm.getStatus());
                ndm.setTempClobString(newDocForm.getContentClobString());

                ndm.setContentClob(newDocForm.getContentClob());

                //假如为网络教育简介，Remark1保存的是Link的初始值，这时就不要修改他了
/*                if (StringUtil.parseInt(newDocForm.getType()) != NewDocumentConstants.INSTITUTE_INTRO_TYPE)
                {
                        ndm.setRemark1(newDocForm.getRemark1());
                }*/

                //假如Link＝＝－1，则说明附件没有输入。这时就不要修改他原来附件了
                if (!StringUtil.nullToStr(newDocForm.getLink()).equals("-1") && !StringUtil.nullToStr(newDocForm.getLink()).equals(""))
                {
                        //说明已经在action里上载了新的附件，这需要删掉原来附件。
                        IOUtil.delAllFile(new File(Config.getUploadPhysicalPath() + ndm.getLink()));
                        ndm.setLink(newDocForm.getLink());
                        ndm.setContentSize(newDocForm.getContentSize());
                }
                //System.out.println("[NewDocumentHelper]updDocument  ndm.getIsOpenGuest() = " + ndm.getIsOpenGuest());
                //针对专家库
                if (StringUtil.parseInt(ndm.getType()) == NewDocumentConstants.EXPERT)
                {
                        //System.out.println("[NewDocumentHelper]updDocument (newDocumentForm.getIsOpenGuest()="+(ndm.getIsOpenGuest()));
                        if (StringUtil.nullToStr(ndm.getIsOpenGuest()).equals("1"))
                        {
                                //System.out.println("[NewDocumentHelper]updDocument resetOtherExpertViewInLearningPortalStatus!");
                                newDocumentDAO.resetOtherExpertViewInLearningPortalStatus(ndm.getDocID());
                        }
                }
                newDocumentDAO.updateDocument(ndm);
        }

        public static void updCss(NewDocumentForm newDocForm) throws NewDocumentSysException
        {
                LogUtil.debug("course", "[NewDocumentHerlper***********start");
                NewDocumentModel ndm = new NewDocumentModel();
                ndm = newDocumentDAO.getDocument(newDocForm.getDocID());
                ndm.setIsUserful("1");
                newDocumentDAO.updateDocument(ndm);

                List lCssModel = newDocumentDAO.getAllCss(newDocForm.getAspID(), 32);
                for (int i = 0; i < lCssModel.size(); i++)
                {
                        ndm = (NewDocumentModel) lCssModel.get(i);
                        if (ndm.getDocID() != newDocForm.getDocID() && ndm.getIsUserful().equals("1"))
                        {
                                ndm.setIsUserful("0");
                        }
                        newDocumentDAO.updateDocument(ndm);
                }
        }


        /**
         * @param catalogID
         * @throws NewDocumentSysException
         */
        public static void deleteCatalog(String catalogID) throws NewDocumentSysException
        {
                ArrayList list = new ArrayList();
                int len = 0;
                String[] catalogIDs = null;
                if (catalogID != null && !catalogID.equals(""))
                {
                        catalogIDs = new String[]{catalogID};
                }
                if (catalogIDs != null)
                {
                        len = catalogIDs.length;
                }
                if (len > 0)
                {
                        for (int i = 0; i < len; i++)
                        {
                                list.add(catalogIDs[i]);
                        }
                }
                try
                {
                        LogUtil.debug("course", "start NewDocumentHerlper.delDocument");
                        newDocumentDAO.deleteCatalog(list);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new NewDocumentSysException(e);
                }

        }

        public static NewDocumentCatalogModel getCatalog(int catalogID)
                throws NewDocumentSysException
        {
                return newDocumentDAO.getCatalog(catalogID);
        }

        /**
         * catalog的综合查询
         *
         * @param type        模块分类            null：不限
         * @param parentID    newdocumentID的值   -1：不限
         * @param aspID       null：不限
         * @param name        catalogName         null：不限
         * @param description null：不限
         * @return
         * @throws NewDocumentSysException
         */
        public static List getCatalogList(String type, int parentID, int aspID, String name, String description)
                throws NewDocumentSysException
        {
                List clList = newDocumentDAO.getCatalogList(type, parentID, aspID, name, description);
                return clList;
        }

        public static void delDocument(List documenIDs) throws NewDocumentSysException
        {
                newDocumentDAO.deleteDocument(documenIDs);
        }

        public static int getMaxOrderIndex() throws NewDocumentSysException
        {
                int orderIndex = newDocumentDAO.getMaxOrderIndex();
                return orderIndex;
        }

        /**
         * 删除招生简章
         *
         * @param documenIDs
         * @throws NewDocumentSysException
         */
        public static void delDocumentForOrderIndex(List documenIDs) throws NewDocumentSysException
        {
                newDocumentDAO.delDocumentForOrderIndex(documenIDs);
        }

        public static boolean documentReName(String name, String type)
                throws NewDocumentSysException
        {
                NewDocumentModel model = newDocumentDAO.getNewDocument(name, type);
                if (model != null)
                {
                        return true;
                }
                return false;
        }


        public static void main(String[] args)
        {
                //System.out.println("=========================================");


        }

}
