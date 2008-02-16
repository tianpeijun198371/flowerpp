/**
 * CourseContentHelper.java.
 * User: huangsb  Date: 2004-5-6
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd. * All rights reserved.
 */
package com.ulearning.ulms.course.helper;

import com.ulearning.ulms.content.util.ContentManageUtil;
import com.ulearning.ulms.core.exceptions.ULMSAppException;
import com.ulearning.ulms.core.util.Config;
import com.ulearning.ulms.core.util.PagerList;
import com.ulearning.ulms.course.dao.CourseContentDAO;
import com.ulearning.ulms.course.dao.CourseContentDAOFactory;
import com.ulearning.ulms.course.exceptions.CourseDAOSysException;
import com.ulearning.ulms.course.model.CourseContentForm;
import com.ulearning.ulms.course.model.CourseContentModel;
import com.ulearning.ulms.course.util.CourseContentKeys;
import com.ulearning.ulms.course.util.CourseKeys;
import com.ulearning.ulms.util.log.LogUtil;
import org.apache.commons.io.FilenameUtils;
import org.apache.webapp.admin.TreeControlNode;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


public class CourseContentHelper
{
        public static int addCourseContent(CourseContentForm courseContentForm)
        {
                CourseContentDAO courseContentDao = CourseContentDAOFactory.getDAO();

                return courseContentDao.addCourseContent(courseContentForm);
        }

        /**
         * 返回用户所能看到的的所有课程文档列表.
         *
         * @param userID
         * @return
         */
        public static PagerList getAllCourseContentsByUser(int userID, int pageNo,
                                                           int pageSize)
        {
                CourseContentDAO courseContentDao = CourseContentDAOFactory.getDAO();

                return courseContentDao.getAllCourseContentsByUser(userID, pageNo,
                        pageSize);
        }

        /**
         * Wrapping the get document method for JSP and  the other modules
         *
         * @param nodeID
         * @return the document modle according to the nodeID
         */
        public static CourseContentForm getCourseContent(String nodeID)
        {
                CourseContentForm ccf = null;

                try
                {
                        CourseContentDAO courseContentDao = CourseContentDAOFactory.getDAO();
                        ccf = courseContentDao.getCourseContent(nodeID);
                }
                catch (CourseDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return ccf;
        }

        /**
         * Wrapping the get document method for JSP and  the other modules
         *
         * @param relationID
         * @return the document modle according to the nodeID
         */
        public static CourseContentForm getCourseContentBytype(int relationID,
                                                               int type)
        {
                CourseContentForm ccf = null;

                try
                {
                        CourseContentDAO courseContentDao = CourseContentDAOFactory.getDAO();
                        ccf = courseContentDao.getCourseContentByType(relationID, type);
                }
                catch (CourseDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return ccf;
        }

        public static String getCourseContentURL(int nodeID)
        {
                String url = null;

                try
                {
                        CourseContentDAO courseContentDao = CourseContentDAOFactory.getDAO();
                        url = courseContentDao.getCourseContentURL(nodeID);
                }
                catch (CourseDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return url;
        }

        /**
         * Wrapping the get documentList method for JSP and  the other modules
         *
         * @param relationID
         * @param
         * @return the document list according to the parentID
         */
        public static List getCourseContentList(int relationID)
        {
                List courseContentList = null;

                try
                {
                        CourseContentDAO courseContentDao = CourseContentDAOFactory.getDAO();
                        courseContentList = courseContentDao.getCourseContentList(relationID);
                }
                catch (CourseDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return courseContentList;
        }

        /**
         * Wrapping the get documentList method for JSP and  the other modules
         *
         * @param relationID
         * @param parentID
         * @return the document list according to the parentID
         */
        public static List getCourseContentList(int relationID, int parentID)
        {
                List courseContentList = null;

                try
                {
                        CourseContentDAO courseContentDao = CourseContentDAOFactory.getDAO();
                        courseContentList = courseContentDao.getCourseContentList(relationID,
                                parentID);
                }
                catch (CourseDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return courseContentList;
        }

        /**
         * Wrapping the get documentList method for JSP and  the other modules
         *
         * @param isAdmin
         * @param relationID
         * @param parentID
         * @return the document list according to the parentID
         */
        public static List getCourseContentList(boolean isAdmin, int relationID,
                                                int parentID)
        {
                List courseContentList = null;

                try
                {
                        CourseContentDAO courseContentDao = CourseContentDAOFactory.getDAO();
                        courseContentList = courseContentDao.getCourseContentList(isAdmin,
                                relationID, parentID);
                }
                catch (CourseDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return courseContentList;
        }

        /**
         * Wrapping the get discussList method for JSP and  the other modules
         *
         * @param parentID
         * @param courseID
         * @return the discuss list according to the discussID
         */
        public static List getCourseContentTree(int courseID, int parentID)
        {
                List discussList = null;

                try
                {
                        CourseContentDAO courseContentDao = CourseContentDAOFactory.getDAO();
                        discussList = courseContentDao.getCourseContentTree(courseID,
                                parentID);
                }
                catch (CourseDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return discussList;
        }

        public static TreeControlNode genarateSubtreeNode(CourseContentForm ccf,
                                                          int userID)
        {
                TreeControlNode subtree = null;

                try
                {
                        LogUtil.info("System", "I am here -courseContent");

                        String name = ccf.getNodeTitle();
                        String link = null;
                        String img = null;
                        String fileName = null;

                        //TreeControlNode temp = null;
                        int ext;
                        int nodeType;
                        link = ccf.getLink();

                        if (link == null)
                        {
                                link = "";
                        }

                        ext = link.lastIndexOf(".");

                        nodeType = ccf.getNodetype();

                        if (ccf.getIsContent() == 0)
                        {
                                img = "/mystudy/coursecontent/images/dot00.gif";
                        }
                        else
                        {
                                if (nodeType == CourseContentKeys.AICC_NODETYPE)
                                {
                                        fileName = "aicc";
                                }
                                else if (nodeType == CourseContentKeys.SCORM_NODETYPE)
                                {
                                        fileName = "scorm";
                                }
                                else if (nodeType == CourseContentKeys.STREAMMEDIA_NODETYPE)
                                {
                                        fileName = "stream";
                                }
                                else if (nodeType == CourseContentKeys.FILE_NODETYPE)
                                {
                                        if ((ext != -1) && ((ext + 1) <= link.length()))
                                        {
                                                fileName = link.substring(ext + 1);
                                        }
                                        else
                                        {
                                                fileName = "unknown";
                                        }
                                }
                                else if (nodeType == CourseContentKeys.HYPERLINK_NODETYPE)
                                {
                                        fileName = "hyperlink";
                                }
                                else
                                {
                                        fileName = "unknown";
                                }

                                img = "/mystudy/coursecontent/images/filetype/" + fileName +
                                        ".gif";
                        }

                        img =  Config.getContextRoot() + img;
                        LogUtil.info("System", "houhx===========img=" + img);

                        String path = "";

                        if (ccf.getIsHyperLink() == 1)
                        {
                                path = ccf.getLink();
                        }
                        else
                        {
                                path = Config.getUploadVirtualPath() + ccf.getLink();
                        }

                        String href = "";

                        if (nodeType == CourseContentKeys.SCORM_NODETYPE)
                        {
                                href = "javascript:runScorm(" + ccf.getNodeID() + "," + userID +
                                        ")";
                        }
                        else
                        {
                                href = "javascript:content('" + path + "')";
                        }

                        if (ccf.getIsContent() != 0)
                        {
                                subtree = new TreeControlNode("document/" + ccf.getNodeID(),
                                        img, ccf.getNodeTitle(), href, null, false,
                                        "CourseContent");

                                //temp = control.findNode("document/" + ccf.getNodeID());
                        }
                        else
                        {
                                subtree = new TreeControlNode("nodeID/" + ccf.getNodeID(), img,
                                        name, null, null, false, "CourseContent");

                                //temp = control.findNode("nodeID/" + ccf.getNodeID());
                        }

                        // return  subtree;
                }
                catch (Exception e)
                {
                        e.printStackTrace();
                }

                return subtree;
        }

        public static void throwsReAddException(CourseContentForm ccf)
                throws Exception
        {
                int courseID = ccf.getRelationID();
                int parentID = ccf.getParentID();
                List list = getCourseContentList(courseID, parentID);
                System.out.println("ccf.getNodeTiTle = " + ccf.getNodeTitle());

                if ((list != null) && (list.size() > 0))
                {
                        System.out.println("list.size() = " + list.size());

                        for (int i = 0; i < list.size(); i++)
                        {
                                CourseContentForm temp = (CourseContentForm) list.get(i);

                                if ((temp.getNodetype() == ccf.getNodetype()) &&
                                        temp.getNodeTitle().equals(ccf.getNodeTitle()))
                                {
                                        throw new ULMSAppException("您已经添加过了该名称的课程文档,请返回,输入一个新的名称!");
                                }
                        }
                }
        }

        //返回课程内容的前边要显示的图片.(包括CourseContentForm为课程目录)
        public static String getCourseContentImgURL(CourseContentForm ccf,
                                                    HttpServletRequest httpServletRequest) throws Exception
        {
                String fileName = null;
                String imgURL = null;

                if (ccf != null)
                {
                        int nodeType = ccf.getNodetype();
                        String link = ccf.getLink();

                        if (link == null)
                        {
                                link = "";
                        }

                        if (ccf.getIsContent() == 0)
                        {
                                imgURL = "/mystudy/coursecontent/images/dot00.gif";
                        }
                        else
                        {
                                if (nodeType == CourseContentKeys.AICC_NODETYPE)
                                {
                                        fileName = "aicc";
                                }
                                else if (nodeType == CourseContentKeys.SCORM_NODETYPE)
                                {
                                        fileName = "scorm";
                                }
                                else if (nodeType == CourseContentKeys.STREAMMEDIA_NODETYPE)
                                {
                                        fileName = "stream";
                                }
                                else if (nodeType == CourseContentKeys.FILE_NODETYPE)
                                {
                                        fileName = FilenameUtils.getExtension(link);

                                        if (!ContentManageUtil.isValidateIMG(fileName,
                                                httpServletRequest.getSession()
                                                        .getServletContext()))
                                        {
                                                fileName = "unknown";
                                        }
                                }
                                else if (nodeType == CourseContentKeys.HYPERLINK_NODETYPE)
                                {
                                        fileName = "hyperlink";
                                }
                                else
                                {
                                        fileName = "unknown";
                                }

                                imgURL = "/content/images/icon/filetype/" + fileName + ".gif";
                        }
                }
                else
                {
                        imgURL = "/content/images/icon/filetype/unknown.gif";
                }

                imgURL = httpServletRequest.getContextPath() + imgURL;

                return imgURL;
        }

        /**
         * 返回nodetype类型的课程内容列表
         * @param relationID
         * @param nodeType
         * @return
         * @throws CourseDAOSysException
         */
        public static List getCourseContentByNodeType(int relationID, int nodeType)
                throws CourseDAOSysException
        {
                List list = null;

                try
                {
                        CourseContentDAO courseContentDao = CourseContentDAOFactory.getDAO();
                        list = courseContentDao.getCourseContentByNodeType(relationID,
                                nodeType);
                }
                catch (CourseDAOSysException udse)
                {
                        udse.printStackTrace();
                }
                return list;
        }

        /**
         * 返回理念项目课程的试听视频或者讲义
         * @param relationID
         * @return
         * @throws CourseDAOSysException
         */
        public static CourseContentModel getXLNCourseContentByShiTingNodeType(int relationID)
                throws CourseDAOSysException
        {
                CourseContentModel model=null;
                List list = getCourseContentByNodeType(relationID, CourseContentKeys.SHITING_NODETYPE);

                if(list!=null && !list.isEmpty())
                {
                        model=(CourseContentModel)list.get(0);
                }
                return model;
        }
}
