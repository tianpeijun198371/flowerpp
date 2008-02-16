/**
 * SchoolWorkHelper.java.
 * User: yud  Date: 2005-4-16
 *
 * Copyright (c) 2000-2005.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.schoolwork.bean;

import com.ulearning.ulms.util.log.LogUtil;
import com.ulearning.ulms.util.HibernateDAO;
import com.ulearning.ulms.tools.schoolwork.dao.SchoolWorkDAO;
import com.ulearning.ulms.tools.schoolwork.dao.SchoolWorkDAOFactory;
import com.ulearning.ulms.tools.schoolwork.form.SchoolWorkForm;
import com.ulearning.ulms.tools.schoolwork.model.SchoolWorkModel;
import com.ulearning.ulms.tools.schoolwork.exceptions.SchoolWorkSysException;
import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.core.util.StringUtil;
import com.ulearning.ulms.core.util.IOUtil;
import com.ulearning.ulms.core.util.Config;
import com.ulearning.ulms.core.util.DateTimeUtil;

import java.util.Date;
import java.util.List;
import java.io.File;


public class SchoolWorkHelper
{
        private static SchoolWorkDAO newDocumentDAO;

        static
        {
                try
                {
                        newDocumentDAO = SchoolWorkDAOFactory.getDAO();

                }
                catch (Exception ex)
                {
                        ex.printStackTrace();
                }
        }

        public static void insertSchoolWork(SchoolWorkForm addSchoolWorkForm) throws SchoolWorkSysException
        {
                LogUtil.debug("course", "[NewDocumentHelper]insertDocument------start!");
                addSchoolWorkForm.setCreateDate((new Date()));
                SchoolWorkModel schoolWorkModel = addSchoolWorkForm.getSchoolWorkModel();
                newDocumentDAO.insertSchoolWork(schoolWorkModel);
        }

        public static List getSchoolList(int relationID, int type) throws SchoolWorkSysException
        {

                List list = newDocumentDAO.getSchoolList(relationID, type);
                return list;
        }

        public static boolean getSchoolAnswerFromDate(int swID) throws SchoolWorkSysException
        {

                boolean boo = newDocumentDAO.getSchoolAnswerFromDate(swID);
                return boo;
        }

        public static List getSchoolListFromDate(int relationID, int type) throws SchoolWorkSysException
        {

                List list = newDocumentDAO.getSchoolListFromDate(relationID, type);
                return list;
        }

        public static SchoolWorkModel getSchoolModel(int swID) throws SchoolWorkSysException
        {
                SchoolWorkModel schoolWorkModel = newDocumentDAO.getSchoolModel(swID);

                return schoolWorkModel;
        }

        public static void updateSchoolWork(SchoolWorkForm updSchoolWorkForm) throws SchoolWorkSysException
        {
                SchoolWorkModel ndm = newDocumentDAO.getSchoolModel(updSchoolWorkForm.getSwID());
                ndm.setModifyDate(new Date());
                ndm.setStatus(updSchoolWorkForm.getStatus());
                ndm.setSwKey(updSchoolWorkForm.getSwKey());
                ndm.setSwLinkTitle(updSchoolWorkForm.getSwLinkTitle());
                ndm.setTitle(updSchoolWorkForm.getTitle());
                //ndm.setType(updSchoolWorkForm.getType());
                ndm.setUserId(updSchoolWorkForm.getUserId());
                ndm.setAnswerLinkTitle(updSchoolWorkForm.getAnswerLinkTitle());
                ndm.setDepth(updSchoolWorkForm.getDepth());
                ndm.setDescription(updSchoolWorkForm.getDescription());
                ndm.setDisplayBeginDate(DateTimeUtil.parseDate(updSchoolWorkForm.getDisplayBeginDate()));
                ndm.setDisplayEndDate(DateTimeUtil.parseDate(updSchoolWorkForm.getDisplayEndDate()));
                ndm.setIsAvailable(updSchoolWorkForm.getIsAvailable());
                ndm.setIsMail(updSchoolWorkForm.getIsMail());
                ndm.setIsMessage(updSchoolWorkForm.getIsMessage());
                ndm.setIsOpenGuest(updSchoolWorkForm.getIsOpenGuest());
                ndm.setIsPublishAnswer(updSchoolWorkForm.getIsPublishAnswer());
                ndm.setIsUserful(updSchoolWorkForm.getIsUserful());
                ndm.setIsView(updSchoolWorkForm.getView());
                ndm.setOrderIndex(updSchoolWorkForm.getOrderIndex());
                //ndm.setParentID(updSchoolWorkForm.getParentID());
                //ndm.setRelationID(updSchoolWorkForm.getRelationID());
                ndm.setRelationName(updSchoolWorkForm.getRelationName());
                ndm.setRemark(updSchoolWorkForm.getRemark());
                ndm.setRemark1(updSchoolWorkForm.getRemark1());
                ndm.setRemark2(updSchoolWorkForm.getRemark2());
                ndm.setRemark3(updSchoolWorkForm.getRemark3());
                ndm.setRemark4(updSchoolWorkForm.getRemark4());
                ndm.setRemark5(updSchoolWorkForm.getRemark5());
                ndm.setRemark6(updSchoolWorkForm.getRemark6());

                if (!StringUtil.nullToStr(updSchoolWorkForm.getSwLink()).equals("-1"))
                {
                        IOUtil.delAllFile(new File(Config.getUploadPhysicalPath() + ndm.getSwLink()));
                        ndm.setSwLink(updSchoolWorkForm.getSwLink());
                }

                if (!StringUtil.nullToStr(updSchoolWorkForm.getAnswerLink()).equals("-1"))
                {
                        IOUtil.delAllFile(new File(Config.getUploadPhysicalPath() + ndm.getAnswerLink()));
                        ndm.setAnswerLink(updSchoolWorkForm.getAnswerLink());
                }

                newDocumentDAO.updateSchoolWork(ndm);
        }

        public static void deleteSchoolWork(List l) throws SchoolWorkSysException
        {
                newDocumentDAO.deleteSchoolWork(l);
        }

}
