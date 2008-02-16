/**
 * ContentManageHelper.java.
 * User: fengch Date: 2005-5-30 17:15:51
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.content.helper;

import com.ulearning.ulms.content.dao.ContentManageDAO;
import com.ulearning.ulms.content.dao.ContentManageDAOFactory;
import com.ulearning.ulms.content.dao.CourseContentTypeDAO;
import com.ulearning.ulms.content.dao.CourseContentTypeDAOFactory;
import com.ulearning.ulms.content.exceptions.ContentManageAppException;
import com.ulearning.ulms.content.exceptions.ContentManageSysException;
import com.ulearning.ulms.content.form.*;
import com.ulearning.ulms.content.model.*;
import com.ulearning.ulms.content.util.ContentManageConstants;
import com.ulearning.ulms.content.util.ImportUtil;
import com.ulearning.ulms.core.security.bean.SecurityConstants;
import com.ulearning.ulms.core.security.bean.SecurityHelper;
import com.ulearning.ulms.core.security.form.STicket;
import com.ulearning.ulms.core.util.*;
import com.ulearning.ulms.course.helper.CourseHelper;
import com.ulearning.ulms.tools.schoolwork.bean.SchoolWorkHelper;
import com.ulearning.ulms.tools.schoolwork.form.SchoolWorkForm;
import com.ulearning.ulms.user.bean.UserHelper;
import com.ulearning.ulms.user.form.UserForm;
import com.ulearning.ulms.util.HibernateDAO;
import com.ulearning.ulms.util.LMSConstants;
import com.ulearning.ulms.util.log.LogUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;


public class ContentManageHelper
{
        private static ContentManageDAO contentManageDAO;
        private static CourseContentTypeDAO courseContentTypeDAO;

        static
        {
                try
                {
                        contentManageDAO = ContentManageDAOFactory.getDAO();
                        courseContentTypeDAO = CourseContentTypeDAOFactory.getDAO();
                }
                catch (Exception ex)
                {
                        ex.printStackTrace();
                }
        }

        /**
         * 返回特定类型的资源列表。
         *
         * @throws ContentManageSysException
         */
        public static PagerList getContentsByContentType(int contentType,
                                                         int pageNo, int pageSize) throws ContentManageSysException
        {
                return contentManageDAO.getContentsByContentType(contentType, pageNo,
                        pageSize);
        }

        /**
         * copy properties.
         *
         * @param dest
         * @param orig
         */
        private static void copyProperites(java.lang.Object dest,
                                           java.lang.Object orig)
        {
                try
                {
                        BeanUtils.copyProperties(dest, orig);
                }
                catch (IllegalAccessException e)
                {
                        throw new ContentManageAppException(e);
                }
                catch (InvocationTargetException e)
                {
                        throw new ContentManageAppException(e);
                }
        }

        /**
         * 导入师大老数据-学习材料部分.
         * 从服务器的上载目录上解析resource目录,
         * 把相应的课程资源-学习材料部分导入我们的数据库.
         * <p/>
         * 前提条件:resource要上传到服务器的上载目录
         * “resources”下，有以课程编号后三位命名（严格的说是：0＋课程文件夹名字＝课程编号）的课程文件夹（如：001、003等），里面才是各个课程中的所需资源。
         * 资源命名如下：
         * images
         * ×××01
         * ×××02
         * ×××04
         * ×××06
         * 其中，×××：前边加个“0”即“0×××”代表平台中的课程编号
         * “01”代表教师信息；“02”代表课程大纲、“04”代表课程作业、“06”代表相关资源。
         * 其中的images文件为×××01中的图片。
         * <p/>
         * <p/>
         * 文件名举例：
         * \resources\055代表的是课程编号为0055的课程资源所在路径。
         * 05504代表的是课程编号为0055的课程作业。
         *
         * @param aspID
         */
        public static void importConurseContent_StudyMaterial(String aspID)
        {
                List contents = new ArrayList();

                ContentForm content = null;

                String physicsFilePath = Config.getUploadPhysicalPath() +
                        File.separator + "resources"; //"D:\\Projects\\BNU\\doc\\requirement\\resource\\resorces";
                
                try
                {
                        File dir = new File(physicsFilePath);

                        if (dir.exists() & dir.isDirectory())
                        {
                                File[] files = dir.listFiles();

                                for (int i = 0; i < files.length; i++)
                                {
                                        File file = files[i];

                                        //System.out.println("1 file.getName() = " + file.getName());
                                        if (file.isDirectory())
                                        {
                                                content = new ContentForm();

                                                //判断课程编码是否合法
                                                String courseCode = "0" + file.getName();
                                                content.setRemark1(courseCode);
                                                content.setIsView(String.valueOf(
                                                        ContentManageConstants.doView));
                                                content.setType(String.valueOf(
                                                        ContentManageConstants.COURSE_CONTENT_TYPE));
                                                content.setParentID(0);
                                                //System.out.println("courseCode = " + courseCode);
                                                /* if(CourseHelper.getCourse(Integer.parseInt(aspID), courseCode)==null)
                                                continue;*/
                                                content.setRelationID(CourseHelper.getCourse(
                                                        Integer.parseInt(aspID), courseCode)
                                                        .getCourseID());
                                                content.setSubject(" ");
                                                content.setDescription(" ");
                                                content.setLanguageID(1);
                                                content.setKeyword("");
                                                content.setContentSize(1);
                                                content.setUserID(1);
                                                content.setCreator("lmsadmin");
                                                content.setContentTypeID(1);
                                                //content.setCreateDate(new Date());
                                                content.setPublisher("");
                                                content.setLocation(File.separator + "resources" +
                                                        File.separator + file.getName());
                                                content.setRemark3(aspID);

                                                //content.setLastModDate(new Date());
                                                File[] files1 = file.listFiles();

                                                for (int j = 0; j < files1.length; j++)
                                                {
                                                        File files2 = files1[j];
                                                        ContentForm content1 = (ContentForm) BeanUtils.cloneBean(content);

                                                        if (files2.isFile())
                                                        {
                                                                //System.out.println("    2 files2.getName() = " + files2.getName());
                                                                String temp = files2.getName().substring(3, 5);

                                                                content1.setLocation(content1.getLocation() +
                                                                        File.separator + files2.getName());
                                                                content1.setIdentifier(files2.getName());

                                                                if (temp.equals("01"))
                                                                {
                                                                        content1.setCourseContentTypeID(ContentManageHelper.getCourseContentTypeID(
                                                                                "教师信息", aspID));
                                                                        content1.setTitle("教师信息");
                                                                        contents.add(content1);
                                                                }
                                                                else if (temp.equals("02"))
                                                                {
                                                                        content1.setCourseContentTypeID(ContentManageHelper.getCourseContentTypeID(
                                                                                "教学大纲", aspID));
                                                                        content1.setTitle("教学大纲");
                                                                        contents.add(content1);
                                                                }
                                                                else if (temp.equals("04"))
                                                                {
                                                                        SchoolWorkForm swf = new SchoolWorkForm();
                                                                        swf.setUserId(String.valueOf(
                                                                                content1.getUserID()));
                                                                        swf.setParentID(0);
                                                                        swf.setOrderIndex(0);
                                                                        swf.setSwLink(content1.getLocation());
                                                                        swf.setAnswerLink("-1");

                                                                        String name = CourseHelper.getCourse(Integer.parseInt(
                                                                                aspID), courseCode).getName() +
                                                                                "作业";
                                                                        swf.setTitle(name);
                                                                        swf.setSwLinkTitle(name);
                                                                        swf.setIsAvailable("1");
                                                                        swf.setIsPublishAnswer("1");
                                                                        swf.setType("0");
                                                                        swf.setRelationID(content1.getRelationID());
                                                                        swf.setCreateDate(new Date());
                                                                        swf.setDepth(0);
                                                                        SchoolWorkHelper.insertSchoolWork(swf);
                                                                }
                                                                else if (temp.equals("06"))
                                                                {
                                                                        content1.setCourseContentTypeID(ContentManageHelper.getCourseContentTypeID(
                                                                                "相关资源", aspID));
                                                                        content1.setTitle("相关资源");
                                                                        contents.add(content1);
                                                                }
                                                        }
                                                }
                                        }
                                }
                        }

                        for (int i = 0; i < contents.size(); i++)
                        {
                                ContentForm contentForm = (ContentForm) contents.get(i);
                                addContent(contentForm);
                        }
                }
                catch (Exception ex)
                {
                        ex.printStackTrace();
                }
        }

        /**
         * 导入师大老数据-课件部分.
         *
         * @param filePath
         * @param aspID
         * @return
         * @throws Exception
         */
        public static List importCourseContent_Courseware(String filePath, int aspID)
                throws Exception
        {
                List err = new ArrayList();
                CSVParser csvparser = null;

                //System.out.println("aspID = " + aspID);
                csvparser = new CSVParser(filePath, ',', true, "GBK");

                ImportUtil importutil = new ImportUtil();

                List parserlt = csvparser.parse();
                err = importutil.checkFile(parserlt, aspID);

                if (err.isEmpty())
                {
                        Date date = new Date();

                        for (int i = 0; i < parserlt.size(); i++)
                        {
                                String[] args = (String[]) parserlt.get(i);
                                ContentForm cm = new ContentForm();
                                String title = args[1] + "_" + args[4];
                                cm.setParentID(0);
                                cm.setRelationID(CourseHelper.getCourse(aspID, args[0])
                                        .getCourseID());
                                cm.setType(String.valueOf(
                                        ContentManageConstants.COURSE_CONTENT_TYPE));
                                cm.setIdentifier(title);
                                cm.setTitle(title);
                                cm.setDescription(args[2]);
                                cm.setLanguageID(1);
                                cm.setKeyword(title);
                                cm.setContentSize(-1);
                                cm.setLocation(args[5]);
                                cm.setUserID(1);
                                cm.setCreator("lmsadmin");
                                cm.setPublisher("");
                                cm.setContentTypeID(1);
                                cm.setCourseContentTypeID(getCourseContentTypeID(args[3],
                                        String.valueOf(aspID)));
                                cm.setCreateDate(date);
                                cm.setLastModDate(date);
                                cm.setIsView(args[6]);
                                cm.setRemark1(args[0]);
                                cm.setRemark3(String.valueOf(aspID));
                                addContent(cm);
                        }
                }

                return err;
        }

        public static ContentAdvSearchForm advSearchBySession(String hql,
                                                              Date begin, Date end, int pageSize, int pageNo)
        {
                return contentManageDAO.advSearch(hql, begin, end, pageSize, pageNo);
        }

        public static ContentAdvSearchForm advSearch(ContentAdvSearchForm sf,
                                                     HttpServletRequest request)
        {
                String hql = "from ContentModel where isview='" +
                        ContentManageConstants.doView + "') ";

                //管理员能查看所有资源，普通用户只能查看已发布的资源
                if (sf.isAdmin())
                {
                        hql = "from ContentModel where 1=1";
                }
                else
                {
                        hql = "from ContentModel where isview='" +
                                ContentManageConstants.doView + "'  ";
                }

                if ((sf.getScopeType() != ContentManageConstants.All_TYPE) &&
                        (sf.getScopeType() != ContentManageConstants.COURSE_CONTENT_TYPE))
                {
                        hql += (" and (RELATIONID=" + sf.getRelationID() + " and  type='" +
                                sf.getScopeType() + "')");
                }
                else if (sf.getScopeType() == ContentManageConstants.COURSE_CONTENT_TYPE)
                {
                        hql += (" and (type='" + sf.getScopeType() + "'");

                        if (!sf.getRemark1().equals("-1"))
                        {
                                hql += (" and (remark1='" + sf.getRemark1() + "')");
                        }

                        if (!sf.getCourseContentTypeID().equals("-1"))
                        {
                                hql += (" and (courseContentTypeID=" +
                                        sf.getCourseContentTypeID() + ")");
                        }

                        hql += ")";
                }
                else if (sf.getScopeType() == ContentManageConstants.All_TYPE) // 如果查找全部，则依次判断时候有相应类型查看的权限
                {
                        STicket st = (STicket) request.getSession()
                                .getAttribute(LMSConstants.TICKET_KEY);
                        int secOrgID = Integer.parseInt((String) request.getSession()
                                .getAttribute(LMSConstants.USER_ORGID_KEY));
                        int i = 0;
                        hql += " and ( ";

                        if ((SecurityHelper.isHasPermission(st, secOrgID,
                                SecurityConstants.USER_PLATFORM_RELATION,
                                SecurityConstants.SOURCE_PUBLIC__VIEW) ||
                                SecurityHelper.isHasPermission(st, secOrgID,
                                        SecurityConstants.USER_PLATFORM_RELATION,
                                        SecurityConstants.SOURCE_PUBLIC_MANAGE)) &&
                                (SecurityHelper.isHasPermission(st, secOrgID,
                                        SecurityConstants.USER_PLATFORM_RELATION,
                                        SecurityConstants.SOURCE_COURSE__VIEW) ||
                                        SecurityHelper.isHasPermission(st, secOrgID,
                                                SecurityConstants.USER_PLATFORM_RELATION,
                                                SecurityConstants.SOURCE_COURSE_MANAGE)))
                        {
                                hql += (" (type<>'" +
                                        ContentManageConstants.PERSONAL_CONTENT_TYPE + "') "); //不搜索个人资源
                        }
                        else if (SecurityHelper.isHasPermission(st, secOrgID,
                                SecurityConstants.USER_PLATFORM_RELATION,
                                SecurityConstants.SOURCE_PUBLIC__VIEW) ||
                                SecurityHelper.isHasPermission(st, secOrgID,
                                        SecurityConstants.USER_PLATFORM_RELATION,
                                        SecurityConstants.SOURCE_PUBLIC_MANAGE))
                        {
                                i++;
                                hql += (" (RELATIONID=" + sf.getRelationID() + " and  type='" +
                                        ContentManageConstants.PUBLIC_CONTENT_TYPE + "')");
                        }
                        else if (SecurityHelper.isHasPermission(st, secOrgID,
                                SecurityConstants.USER_PLATFORM_RELATION,
                                SecurityConstants.SOURCE_COURSE__VIEW) ||
                                SecurityHelper.isHasPermission(st, secOrgID,
                                        SecurityConstants.USER_PLATFORM_RELATION,
                                        SecurityConstants.SOURCE_COURSE_MANAGE))
                        {
                                i++;
                                hql += (" (type='" +
                                        ContentManageConstants.COURSE_CONTENT_TYPE + "'");

                                if (!sf.getRemark1().equals("-1"))
                                {
                                        hql += (" and (remark1='" + sf.getRemark1() + "')");
                                }

                                if (!sf.getCourseContentTypeID().equals("-1"))
                                {
                                        hql += (" and (courseContentTypeID=" +
                                                sf.getCourseContentTypeID() + ")");
                                }

                                hql += ")";
                        }
                        else
                        {
                                hql += ("RELATIONID=" + sf.getRelationID() + " and  type='" +
                                        ContentManageConstants.PUBLIC_CONTENT_TYPE + "'");
                        }

                        hql += " )";
                }

                hql += (" and       (" +
                        opinionLocation(sf.getLocation1(), sf.getMatch1(),
                                sf.getMatchContent1(), sf.getAor1(), 0));
                hql += opinionLocation(sf.getLocation2(), sf.getMatch2(),
                        sf.getMatchContent2(), sf.getAor2(), 0);
                hql += opinionLocation(sf.getLocation3(), sf.getMatch3(),
                        sf.getMatchContent3(), sf.getAor3(), 0);
                hql += opinionLocation(sf.getLocation4(), sf.getMatch4(),
                        sf.getMatchContent4(), sf.getAor4(), 0);
                hql += opinionLocation(sf.getLocation5(), sf.getMatch5(),
                        sf.getMatchContent5(), 9999, -1);

                hql += ")";

                if (sf.getLanguageID() != ContentManageConstants.SEARCH_ALL_LANGUAGE)
                {
                        hql += ("      and (languageID=" + sf.getLanguageID() + ")");
                }

                if (sf.getContentTypeID() != ContentManageConstants.SEARCH_ALL_CONTENT_TYPE)
                {
                        hql += ("      and (contentTypeID=" + sf.getContentTypeID() + ")");
                }

                List temp = new ArrayList();

                if (!((null == sf.getBeginDate()) || "".equals(sf.getBeginDate()) ||
                        "null".equals(sf.getBeginDate()) || (null == sf.getEndDate()) ||
                        "".equals(sf.getEndDate()) || "null".equals(sf.getEndDate())))
                {
                        hql += opinionDate(sf, temp);
                }

                hql += (sf.getOrder() + sf.getSort());

                //System.out.println(temp.get(0));
                //System.out.println(temp.get(1));
                //System.out.println(hql);
                //System.out.println("sf.getOrder() = " + sf.getOrder());
                //System.out.println("sf.getSort() = " + sf.getSort());
                Date begindate = null;
                Date enddate = null;

                if (!((null == sf.getBeginDate()) || "".equals(sf.getBeginDate()) ||
                        "null".equals(sf.getBeginDate())))
                {
                        begindate = DateTimeUtil.parseDate(sf.getBeginDate());
                }

                if (!((null == sf.getEndDate()) || "".equals(sf.getEndDate()) ||
                        "null".equals(sf.getEndDate())))
                {
                        enddate = DateTimeUtil.parseDate(sf.getEndDate());
                }

                return contentManageDAO.advSearch(hql, begindate, enddate,
                        sf.getPageSize(), sf.getPageNo());
        }

        private static String opinionDate(ContentAdvSearchForm sf, List lt)
        {
                String hql = " and       (" +
                        "(displaybegindate IS NULL and displayenddate IS NULL)" +
                        "or (displaybegindate IS NULL and displayenddate IS NOT NULL and " +
                        "displayenddate <=:end)" +
                        "or (displaybegindate IS NOT NULL and displaybegindate>=:begin and displayenddate IS NULL)" +
                        "or (displaybegindate IS NOT NULL and displaybegindate>=:begin and displayenddate IS NOT NULL and " +
                        " displayenddate <=:end)" + ")";

                Date startDate = null;
                Date endDate = null;

                if (sf.getDatebutton() == ContentManageConstants.SEARCH_INTPUT_DATE)
                {
                        if ((sf.getEndDate() != null) && !sf.getEndDate().equals(""))
                        {
                                String end = sf.getEndDate().substring(0, 10);
                                String[] tmp = StringUtil.splitString(end, "-");
                                endDate = DateTimeUtil.toDate(tmp[1], tmp[2], tmp[0], "0", "0",
                                        "0");
                        }

                        if ((sf.getBeginDate() != null) && !sf.getBeginDate().equals(""))
                        {
                                String end = sf.getBeginDate().substring(0, 10);
                                String[] tmp = StringUtil.splitString(end, "-");
                                startDate = DateTimeUtil.toDate(tmp[1], tmp[2], tmp[0], "0",
                                        "0", "0");
                        }

                        //System.out.println("[ContentManageHelper]opinionDate--SEARCH_INTPUT_DATE I18Util.FormatDateTime(startDate, Locale.CHINA) = " + I18Util.FormatDateTime(startDate, Locale.CHINA));
                        //System.out.println("[ContentManageHelper]opinionDate--SEARCH_INTPUT_DATE I18Util.FormatDateTime(endDate, Locale.CHINA) = " + I18Util.FormatDateTime(endDate, Locale.CHINA));
                }
                else if (sf.getDatebutton() == ContentManageConstants.SEARCH_SELECT_DATE)
                {
                        java.util.Calendar c = java.util.Calendar.getInstance();
                        c.setTime(new Date());

                        endDate = new GregorianCalendar(9999, 1, 1).getTime();

                        if (sf.getSelectdate() == ContentManageConstants.SEARCH_ALL_DAY)
                        {
                                endDate = null;

                                //管理员能查看所有资源，普通用户只能查看已发布的资源(当前日期)
                                if (sf.isAdmin())
                                {
                                        hql = "";
                                }
                                else
                                {
                                        startDate = new Date();
                                        endDate = new Date();
                                }
                        }
                        else if (sf.getSelectdate() == ContentManageConstants.SEARCH_SEVEN_DAY)
                        {
                                c.add(java.util.Calendar.DATE, -7);
                                startDate = c.getTime();
                        }
                        else if (sf.getSelectdate() == ContentManageConstants.SEARCH_THIRTY_DAY)
                        {
                                c.add(java.util.Calendar.DATE, -30);
                                startDate = c.getTime();
                        }
                        else if (sf.getSelectdate() == ContentManageConstants.SEARCH_SIXTY_DAY)
                        {
                                c.add(java.util.Calendar.DATE, -60);
                                startDate = c.getTime();
                        }
                        else if (sf.getSelectdate() == ContentManageConstants.SEARCH_ONE_YEAR)
                        {
                                c.add(java.util.Calendar.YEAR, -1);
                                startDate = c.getTime();
                        }

                        //System.out.println("[ContentManageHelper]opinionDate--SEARCH_SELECT_DATE I18Util.FormatDateTime(startDate, Locale.CHINA) = " + I18Util.FormatDateTime(startDate, Locale.CHINA));
                        //System.out.println("[ContentManageHelper]opinionDate--SEARCH_SELECT_DATE I18Util.FormatDateTime(endDate, Locale.CHINA) = " + I18Util.FormatDateTime(endDate, Locale.CHINA));
                }

                lt.add(startDate);
                lt.add(endDate);

                return hql;
        }

        /**
         * 根据在页面位置中选择的条件进行组合，flag为-1表示最后一个查询条件
         *
         * @param location
         * @param match
         * @param content
         * @param aor
         * @return
         */
        private static String opinionLocation(int location, int match,
                                              String content, int aor, int flag)
        {
                String hql = "(";
                String matchName = "";

                if (location == ContentManageConstants.SEARCH_NAME)
                {
                        matchName = "TITLE";
                }
                else if (location == ContentManageConstants.SEARCH_KEYWORD)
                {
                        matchName = "KEYWORD";
                }
                else if (location == ContentManageConstants.SEARCH_EXPLAIN)
                {
                        matchName = "DESCRIPTION";
                }
                else if (location == ContentManageConstants.SEARCH_AUTHOR)
                {
                        matchName = "Publisher";
                }
                else if (location == ContentManageConstants.SEARCH_RELEASE)
                {
                        matchName = "Creator";
                }

                if (match == ContentManageConstants.SEARCH_LIKE)
                {
                        if ((content != null) && (content.length() != 0))
                        {
                                hql += (matchName + " like '%" + content + "%'");
                        }
                        else
                        {
                                hql += ("(" + matchName + " is null or " + matchName +
                                        " like '%%')");
                        }
                }
                else if (match == ContentManageConstants.SEARCH_EQUAL)
                {
                        if ((content != null) && (content.length() != 0))
                        {
                                hql += (matchName + "='" + content + "'");
                        }
                        else
                        {
                                hql += ("(" + matchName + " is null or " + matchName +
                                        " like '%%')");
                        }
                }
                else if (match == ContentManageConstants.SEARCH_NOT_CONTAIN)
                {
                        if ((content != null) && (content.length() != 0))
                        {
                                hql += (matchName + " not like '%" + content + "%'");
                        }
                        else
                        {
                                hql += ("(" + matchName + " is not null or not like '%%')");
                        }
                }

                hql += ")";

                if (flag != -1)
                {
                        if (aor == ContentManageConstants.SEARCH_AND)
                        {
                                hql += " and ";
                        }
                        else if (aor == ContentManageConstants.SEARCH_OR)
                        {
                                hql += " or ";
                        }
                        else if (aor == ContentManageConstants.SEARCH_NOT)
                        {
                                hql += " and not ";
                        }
                }

                return hql;
        }

        /******************************************     ContentType     ***********************************************/
        /**
         * 同过传入一个ContentTypeForm添加一个contentType
         *
         * @param CTF
         * @return
         */
        public static boolean addContentType(ContentTypeForm CTF)
        {
                String ContentType = CTF.getContentType();
                boolean isExist = contentManageDAO.isExistContentType(ContentType,
                        CTF.getRemark3());

                if (isExist == false)
                {
                        ContentTypeModel CTM = new ContentTypeModel();
                        int ordexIndex = contentManageDAO.getMaxOrderIndexFromContentType(CTF.getRemark3()) +
                                1;

                        //copyProperites(CTM, CTF);
                        CTM.setContentType(CTF.getContentType());
                        CTM.setName(CTF.getName());
                        CTM.setCreateDate(new Date());
                        CTM.setLastModDate(new Date());
                        CTM.setOrderIndex(ordexIndex);
                        CTM.setRemark3(CTF.getRemark3());

                        contentManageDAO.addContentType(CTM);

                        return true;
                }
                else
                {
                        return false;
                }
        }

        public static ContentTypeModel getContentTypeByContentTypeID(
                int contentType)
        {
                return contentManageDAO.getContentTypeByContentTypeID(contentType);
        }

        /**
         * 同过传入一个ContentTypeForm修改contentType
         *
         * @param CTF
         * @return
         */
        public static boolean updateContentType(ContentTypeForm CTF)
        {
                /*字符串contentType是和ContentTypeModel中的contentType对应的，要写入数据库中，而contentTypeTemp是暂时的，
                在修改页面中contentTypeTemp用来保存原始的contentType，也就是修改前的contentType,在页面提交后，无论页面中的contentType是否更改，都把页面中的contentType符给contentTypeTemp，
                在这里比较这两个字符串，从而根据contentType改变与否来进行操作*/
                String ContentType = CTF.getContentType();
                String contentTypeTemp = CTF.getContentTypeTemp();
                int contentTypeID = CTF.getContentTypeID();

                if (ContentType.equals(contentTypeTemp)) //contentType没有改变
                {
                        ContentTypeModel CTM = new ContentTypeModel();

                        CTM = contentManageDAO.getContentTypeByContentTypeID(contentTypeID);

                        CTM.setName(CTF.getName());
                        CTM.setLastModDate(new Date());

                        contentManageDAO.updateContentType(CTM);

                        return true;
                }
                else //改变contentType，要先判断一下改变后的contentType是否存在
                {
                        boolean isExist = contentManageDAO.isExistContentType(ContentType,
                                CTF.getRemark3());

                        if (isExist == false)
                        {
                                ContentTypeModel CTM = new ContentTypeModel();

                                CTM = contentManageDAO.getContentTypeByContentTypeID(contentTypeID);

                                CTM.setContentType(ContentType);
                                CTM.setName(CTF.getName());
                                CTM.setLastModDate(new Date());

                                contentManageDAO.updateContentType(CTM);

                                return true;
                        }
                        else //如果存在相同的contentType,则返回false
                        {
                                return false;
                        }
                }
        }

        /**
         * 通过传入一个List修改contentType的排序，List中的内容是字符串"7_9",
         * 9的意思是这个contentType的contentTypeID（contentTypeID是9）,7的意思是这个contentType排在第几位（第7位）
         *
         * @param lt
         */
        public static void updateContentTypeOrderIndex(List lt)
        {
                int strn = 0;

                for (int i = 0; i < lt.size(); i++)
                {
                        String str = String.valueOf(lt.get(i));
                        strn = str.indexOf("_");

                        String str_cssOrder = str.substring(0, strn);
                        String str_cssID = str.substring(strn + 1);
                        int cssID = Integer.parseInt(str_cssID);
                        int cssOrder = Integer.parseInt(str_cssOrder);
                        ContentTypeModel CTM = contentManageDAO.getContentTypeByContentTypeID(cssID);
                        CTM.setOrderIndex(cssOrder);
                        contentManageDAO.updateContentType(CTM);
                }
        }

        /**
         * 删除contentType,List中的内容是contentTypeID
         * <p/>
         * 返回包含正在使用中的contentType的id的List
         *
         * @param lt
         * @param aspID
         */
        public static List deleteContentType(List lt, String aspID)
        {
                List isUsing = new ArrayList();

                for (int i = 0; i < lt.size(); i++)
                {
                        int ID = Integer.parseInt(lt.get(i).toString());

                        if (isUsingContentType(ID))
                        {
                                isUsing.add(String.valueOf(ID));
                        }
                }

                //如果没有正在使用中的contentType，则可以删除
                if (isUsing.isEmpty())
                {
                        for (int i = 0; i < lt.size(); i++)
                        {
                                ContentTypeModel ctm = contentManageDAO.getContentTypeByContentTypeID(Integer.parseInt(
                                        lt.get(i).toString()));
                                int orderIndex = ctm.getOrderIndex();
                                contentManageDAO.deleteContentType(Integer.parseInt(
                                        lt.get(i).toString()));
                                contentManageDAO.updateContentTypeOrderIndex(orderIndex, aspID);

                                /*                        List tempList = contentManageDAO.getContentTypeByHQL("from ContentTypeModel where CONTENTTYPEID>" + lt.get(i).toString());
                                for (int j = 0; j < tempList.size(); j++)
                                {
                                        ContentTypeModel ctm = (ContentTypeModel) tempList.get(j);
                                        ctm.setOrderIndex(ctm.getOrderIndex() - 1);
                                        contentManageDAO.updateContentType(ctm);
                                }*/
                        }
                }

                return isUsing;
        }

        /**
         * get all content type.
         *
         * @param aspID
         * @return
         */
        public static List getAllContentType(String aspID)
        {
                return contentManageDAO.getAllContentType(aspID);
        }

        public static boolean isUsingContentType(int contentTypeID)
                throws ContentManageSysException
        {
                return contentManageDAO.isUsingContentType(contentTypeID);
        }

        /*********************************************     ContentConfig     ******************************************/

        /**
         * 通过传入ContentConfigForm修改ContentConfig
         *
         * @param CCF
         * @return
         */
        public static boolean updateContentConfig(ContentConfigForm CCF)
        {
                String type = CCF.getType();
                int relationID = CCF.getRelationID();

                //int configID = CCF.getContentConfigID();
                ContentConfigModel CCM = new ContentConfigModel();
                CCM = contentManageDAO.getContentConfig(type, String.valueOf(relationID));

                CCM.setLastModDate(new Date());
                CCM.setLimitUploadFileSize(CCF.getLimitUploadFileSize());
                CCM.setIsAllowedCommonUserUpload(CCF.getIsAllowedCommonUserUpload());
                CCM.setLimitSpaceSize(CCF.getLimitSpaceSize());

                contentManageDAO.updateContentConfig(CCM);

                return true;
        }

        public static ContentConfigModel getContentConfig(String relationID)
        {
                if (contentManageDAO.getContentConfig("0", relationID) == null)
                {
                        ContentConfigModel ccm = new ContentConfigModel();

                        ContentConfigModelPK ccmp = new ContentConfigModelPK();
                        ccmp.setType("0");
                        ccmp.setRelationID(Integer.parseInt(relationID));

                        ccm.setComp_id(ccmp);
                        ccm.setIsAllowedCommonUserUpload("0");
                        ccm.setLimitSpaceSize("10");
                        ccm.setLimitUploadFileSize("500");
                        ccm.setContentConfigID(contentManageDAO.getMaxContentConfigID() +
                                1);

                        contentManageDAO.addContentConfig(ccm);
                }

                return contentManageDAO.getContentConfig("0", relationID);
        }

        /****************************************     ContentCatalog     ***********************************************/
        /**
         * 添加一个目录
         *
         * @param ccf
         */
        public static boolean addContentCatalog(ContentCatalogForm ccf)
        {
                if (contentManageDAO.isHasSameCatalogByParentID(ccf.getTitle(),
                        ccf.getParentID(), ccf.getRelationID(),
                        Integer.parseInt(ccf.getType()), ccf.getRemark3()))
                {
                        return false;
                }
                else
                {
                        ContentCatalogModel ccm = new ContentCatalogModel();
                        copyProperites(ccm, ccf);
                        ccm.setCreateDate(new Date());
                        ccm.setLastModDate(new Date());

                        contentManageDAO.addContentCatalog(ccm);

                        return true;
                }
        }

        /**
         * 修改目录
         *
         * @param ccf
         */
        public static boolean updateContentCatalog(ContentCatalogForm ccf)
        {
                if (contentManageDAO.isHasSameCatalogByParentID_update(ccf.getTitle(),
                        ccf.getParentID(), ccf.getContentCatalogID(),
                        ccf.getRelationID(), Integer.parseInt(ccf.getType()),
                        ccf.getRemark3()))
                {
                        return false;
                }
                else
                {
                        ContentCatalogModel ccm = contentManageDAO.getContentCatalog(String.valueOf(
                                ccf.getContentCatalogID()));
                        ccm.setTitle(ccf.getTitle());
                        ccm.setLastModDate(new Date());

                        contentManageDAO.updateContentCatalog(ccm);

                        return true;
                }
        }

        /**
         * get a catalog.
         *
         * @param catalogID
         * @return
         */
        public static ContentCatalogModel getContentCatalog(int catalogID)
        {
                return contentManageDAO.getContentCatalog(String.valueOf(catalogID));
        }

        /**
         * 删除目录
         *
         * @param lt
         * @param aspID
         */
        public static void deleteContentCatalog(List lt, String aspID)
        {
                for (int i = 0; i < lt.size(); i++)
                {
                        String s = (String) lt.get(i);
                        int contentCatalogID = StringUtil.parseInt(s);

                        if (contentCatalogID == 0)
                        {
                                continue;
                        }

                        deleteContentCatalog(contentCatalogID, aspID);
                }
        }

        /**
         * 删除目录
         *
         * @param contentCatalogID
         * @param aspID
         */
        public static void deleteContentCatalog(int contentCatalogID, String aspID)
        {
                ContentTreeModel ctm = ContentTreeHelper.getTree(contentCatalogID, -1,
                        -1, 0, 1000000, aspID);
                List catalogs = ctm.getContentCatalogs();
                List contents = ctm.getContents();

                List contentIDList = new ArrayList();

                for (int i = 0; i < contents.size(); i++)
                {
                        ContentModel contentModel = (ContentModel) contents.get(i);
                        contentIDList.add(String.valueOf(contentModel.getContentID()));
                }

                deleteContent(contentIDList);

                for (int i = 0; i < catalogs.size(); i++)
                {
                        ContentCatalogModel contentCatalogModel = (ContentCatalogModel) catalogs.get(i);
                        deleteContentCatalog(contentCatalogModel.getContentCatalogID(),
                                aspID);
                }

                contentManageDAO.deleteContentCatalog(contentCatalogID);
        }

        /**
         * 移动.
         *
         * @param moveCatalogs 要被移动的目录
         * @param moveContent  要被移动的内容
         * @param catalogID    要移动到目标目录
         * @param aspID
         */
        public static boolean move(List moveCatalogs, List moveContent,
                                   int catalogID, int relationID, int type, String aspID)
        {
                List catalogList = new ArrayList();

                for (int i = 0; i < moveCatalogs.size(); i++)
                {
                        int catalogID1 = Integer.parseInt(moveCatalogs.get(i).toString());

                        if (isIncludeContentCatalog(catalogID1, catalogID, relationID,
                                type, aspID) == 1)
                        {
                                catalogList.add(moveCatalogs.get(i));
                        }
                        else
                        {
                                return false;
                        }
                }

                contentManageDAO.move(catalogList, moveContent, catalogID, aspID);

                return true;
        }

        /**
         * 判断catalogID2是否被catalogID1及其所有子目录包含。
         *
         * @param catalogID1 要复制的目录
         * @param catalogID2 要复制到的目录
         * @param aspID
         * @return ==1:正常.==2:为其子目录 ==3:为相同目录,
         */
        public static int isIncludeContentCatalog(int catalogID1, int catalogID2,
                                                  int relationID, int type, String aspID)
        {
                if (catalogID1 == catalogID2)
                {
                        return 3;
                }
                else
                {
                        return isIncludeContentCatalog_private(catalogID1, catalogID2,
                                relationID, type, aspID);
                }
        }

        /**
         * 判断catalogID2是否被catalogID1及其所有子目录包含。
         *
         * @param catalogID1 要复制的目录
         * @param catalogID2 要复制到的目录
         * @param aspID
         * @return ==1:正常.==2:为其子目录
         */
        private static int isIncludeContentCatalog_private(int catalogID1,
                                                           int catalogID2, int relationID, int type, String aspID)
        {
                if (catalogID1 == catalogID2)
                {
                        return 2;
                }
                else
                {
                        ContentCatalogModel ccm = ContentManageHelper.getContentCatalog(catalogID1);

                        List l = ContentTreeHelper.getSubCatalog(catalogID1, relationID,
                                type, aspID);

                        for (int i = 0; i < l.size(); i++)
                        {
                                ContentCatalogModel contentCatalogModel = (ContentCatalogModel) l.get(i);

                                if (ccm.getContentCatalogID() == catalogID2)
                                {
                                        return 2;
                                }

                                if (isIncludeContentCatalog(
                                        contentCatalogModel.getContentCatalogID(),
                                        catalogID2, relationID, type, aspID) == 1)
                                {
                                        continue;
                                }
                                else
                                {
                                        return 2;
                                }
                        }
                }

                return 1;
        }

        /*********************************************     Content     *************************************************/
        /**
         * 添加资源
         *
         * @param ccf
         */
        public static void addContent(ContentForm ccf)
        {
                ContentModel ccm = ccf.getContentModel();
                ccm.setCreateDate(new Date());
                ccm.setLastModDate(new Date());
                if (StringUtils.isBlank(ccf.getCreator()))
                {
                        UserForm um = UserHelper.getUser(String.valueOf(ccf.getUserID()));
                        ccm.setCreator(um.getName());
                        ccm.setUserID(ccf.getUserID());
                }
                if ((ccf.getBeginDate() != null) && !ccf.getBeginDate().equals(""))
                {
                        String[] tmp = StringUtil.splitString(ccf.getBeginDate(), "-");
                        ccm.setDisplayBeginDate(DateTimeUtil.toDate(tmp[1], tmp[2], tmp[0],
                                "0", "0", "0"));
                }

                if ((ccf.getBeginDate() != null) && !ccf.getEndDate().equals(""))
                {
                        String[] tmp = StringUtil.splitString(ccf.getEndDate(), "-");
                        ccm.setDisplayEndDate(DateTimeUtil.toDate(tmp[1], tmp[2], tmp[0],
                                "0", "0", "0"));
                }

                if (ccm.getRemark7() == null)
                {
                        ccm.setRemark7(" ");
                }
                else if (ccm.getRemark7().trim().equals(""))
                {
                        ccm.setRemark7(" ");
                }

                contentManageDAO.addContent(ccm);
        }

        /**
         * 修改资源
         *
         * @param cf
         */
        public static void updateContent(ContentForm cf)
        {
                ContentModel ccm = contentManageDAO.getContent(cf.getContentID());

                if (cf.getDuration() != null)
                {
                        ccm.setDuration(cf.getDuration());
                }
                if (cf.getTypicallEarningTime() != 0)
                {
                        ccm.setTypicallEarningTime(cf.getTypicallEarningTime());
                }
                if (cf.getIsCopyRight() != null)
                {
                        ccm.setIsCopyRight(cf.getIsCopyRight());
                }
                if (cf.getContributor() != null)
                {
                        ccm.setContributor(cf.getContributor());
                }
                if (cf.getVersion() != null)
                {
                        ccm.setVersion(cf.getVersion());
                }
                if (cf.getRights() != null)
                {
                        ccm.setRights(cf.getRights());
                }
                if (cf.getCoverage() != null)
                {
                        ccm.setCoverage(cf.getCoverage());
                }
                if (cf.getSubject() != null)
                {
                        ccm.setSubject(cf.getSubject());
                }
                if (cf.getRemark7() != null)
                {
                        ccm.setRemark7(cf.getRemark7());
                }
                if (cf.getTitle() != null)
                {
                        ccm.setTitle(cf.getTitle());
                }
                if (cf.getIdentifier() != null)
                {
                        ccm.setIdentifier(cf.getIdentifier());
                }
                //ccm.setType(cf.getType());
                if (cf.getIsView() != null)
                {
                        ccm.setIsView(cf.getIsView());
                }
                if (cf.getDescription() != null)
                {
                        ccm.setDescription(cf.getDescription());
                }
                if (cf.getContentClob() != null)
                {
                        ccm.setTempClobString(cf.getContentClob());
                }

                if (cf.getCourseContentTypeID() != -1 && NumberUtils.toInt(ccm.getType()) == ContentManageConstants.COURSE_CONTENT_TYPE)
                {
                        ccm.setCourseContentTypeID(cf.getCourseContentTypeID());
                }

                ccm.setKeyword(cf.getKeyword());
                ccm.setPublisher(cf.getPublisher());

                if (StringUtils.trimToEmpty(cf.getRadionbt()).equals("0") && StringUtils.trimToEmpty(cf.getIsChangeFile()).equals("0"))
                {
                }
                else
                {
                        ccm.setLocation(cf.getLocation());
                        ccm.setContentSize(cf.getContentSize());
                }

                ccm.setContentTypeID(cf.getContentTypeID());
                ccm.setLanguageID(cf.getLanguageID());

                if ((cf.getBeginDate() != null) && !cf.getBeginDate().equals(""))
                {
                        String begin = cf.getBeginDate().substring(0, 10);
                        String[] tmp = StringUtil.splitString(begin, "-");
                        ccm.setDisplayBeginDate(DateTimeUtil.toDate(tmp[1], tmp[2], tmp[0],
                                "0", "0", "0"));
                }

                if ((cf.getEndDate() != null) && !cf.getEndDate().equals(""))
                {
                        String end = cf.getEndDate().substring(0, 10);
                        String[] tmp = StringUtil.splitString(end, "-");
                        ccm.setDisplayEndDate(DateTimeUtil.toDate(tmp[1], tmp[2], tmp[0],
                                "0", "0", "0"));
                }

                ccm.setLastModDate(new Date());

                if (StringUtils.trimToEmpty(cf.getRadionbt()).equals("1") && (ccm.getContentSize() != -1))
                {
                        deleteContentFile(ccm.getContentID());
                }

                if (StringUtils.trimToEmpty(cf.getRadionbt()).equals("0") && StringUtils.trimToEmpty(cf.getIsChangeFile()).equals("1"))
                {
                        deleteContentFile(ccm.getContentID());
                }

                if (cf.getIndex() != null)
                {
                        String path = ccm.getLocation();
                        ccm.setLocation(path.substring(0,
                                path.lastIndexOf(File.separator) + 1) + cf.getIndex());
                }

                if (ccm.getRemark7() == null)
                {
                        ccm.setRemark7(" ");
                }
                else if (ccm.getRemark7().trim().equals(""))
                {
                        ccm.setRemark7(" ");
                }

                contentManageDAO.updateContent(ccm);
        }

        public static ContentModel getContent(int contentID)
        {
                return contentManageDAO.getContent(contentID);
        }

        /**
         * 删除资源
         *
         * @param lt
         */
        public static void deleteContent(List lt)
        {
                for (int i = 0; i < lt.size(); i++)
                {
                        int contentID = Integer.parseInt(lt.get(i).toString());
                        deleteContentFile(contentID);
                }

                contentManageDAO.deleteContent(lt);
        }

        /**
         * 删除目录或者资源.
         * <br>传入的list中的字符串为“12_1”,12表示要删除内容的id,1表示这个id是目录的id（若是2则表示这个id是资源的id）
         *
         * @param lt
         * @param aspID
         */
        public static void deleteContentOrCatalog(List lt, String aspID)
        {
                List deletedCatalogList = new ArrayList();
                List deletedContentList = new ArrayList();

                for (int i = 0; i < lt.size(); i++)
                {
                        String id = lt.get(i).toString();
                        String[] ids = id.split("_");

                        if (ids[1].equals(String.valueOf(
                                ContentManageConstants.displayContent)))
                        {
                                deletedContentList.add(ids[0]);
                        }
                        else if (ids[1].equals(String.valueOf(
                                ContentManageConstants.displayCatalog)))
                        {
                                //非新理念项目，需要判断 未认证资源
                                if(!Config.isXLNProject())
                                {
                                        if (StringUtil.parseInt(ids[0]) == ContentManageConstants.NO_AUTH_CONTENT_ID)
                                        {
                                                throw new ContentManageAppException("不能删除未认证资源目录!");
                                        }
                                }
                                deletedCatalogList.add(ids[0]);
                        }
                }

                deleteContentCatalog(deletedCatalogList, aspID);
                deleteContent(deletedContentList);
        }

        public static void deleteContentFile(int contentID)
        {
                ContentModel cm = contentManageDAO.getContent(contentID);
                String subPath = cm.getLocation();
                String newFilePath = Config.getUploadPhysicalPath() + subPath;
                File f = new File(newFilePath);
                IOUtil.delAllFile(f);
        }

        /****************************************     ContentLanguage     *********************************************/
        /**
         * 得到全部的language
         *
         * @param aspID
         * @return
         */
        public static List getAllContentLanguage(String aspID)
        {
                if (contentManageDAO.getAllContentLanguage(aspID).isEmpty())
                {
                        ContentLanguageForm clf1 = new ContentLanguageForm();
                        clf1.setLanguage("中文");
                        clf1.setLanguageName("中文");
                        clf1.setIsDefault("1");
                        clf1.setRemark3(aspID);
                        addContentLanguage(clf1);

                        ContentLanguageForm clf2 = new ContentLanguageForm();
                        clf2.setLanguage("英文");
                        clf2.setLanguageName("英文");
                        clf2.setRemark3(aspID);
                        addContentLanguage(clf2);

                        ContentLanguageForm clf3 = new ContentLanguageForm();
                        clf3.setLanguage("法文");
                        clf3.setLanguageName("法文");
                        clf3.setRemark3(aspID);
                        addContentLanguage(clf3);

                        ContentLanguageForm clf4 = new ContentLanguageForm();
                        clf4.setLanguage("日文");
                        clf4.setLanguageName("日文");
                        clf4.setRemark3(aspID);
                        addContentLanguage(clf4);
                }

                return contentManageDAO.getAllContentLanguage(aspID);
        }

        public static ContentLanguageModel getContentLanguage(int languageID)
        {
                return contentManageDAO.getContentLanguage(languageID);
        }

        /**
         * 添加一个语言
         *
         * @param clf
         * @return
         */
        public static boolean addContentLanguage(ContentLanguageForm clf)
        {
                if (contentManageDAO.isExistLanguage(clf.getLanguage(), clf.getRemark3()))
                {
                        return false;
                }
                else
                {
                        ContentLanguageModel clm = new ContentLanguageModel();

                        copyProperites(clm, clf);

                        clm.setCreateDate(new Date());
                        clm.setLastModDate(new Date());

                        contentManageDAO.addContentLanguage(clm);

                        return true;
                }
        }

        /**
         * 修改一个语言
         *
         * @param clf
         * @return
         */
        public static boolean updateContentLanguage(ContentLanguageForm clf)
        {
                if (contentManageDAO.isExistLanguage(clf.getLanguage(), clf.getRemark3()))
                {
                        return false;
                }
                else
                {
                        ContentLanguageModel clm = contentManageDAO.getContentLanguage(clf.getLanguageID());

                        clm.setLanguage(clf.getLanguage());
                        clm.setLanguageName(clf.getLanguageName());
                        clm.setLastModDate(new Date());

                        contentManageDAO.updateContentLanguage(clm);

                        return true;
                }
        }

        /**
         * 删除语言
         * 禁止删除正在使用的language，返回包含使用中语言的languageID的list
         *
         * @param lt
         * @return List
         */
        public static List deleteContentLanguage(List lt)
        {
                List isUsing = new ArrayList();

                for (int i = 0; i < lt.size(); i++)
                {
                        int languageID = Integer.parseInt(lt.get(i).toString());

                        if (isUsingContentLanguage(languageID))
                        {
                                isUsing.add(String.valueOf(languageID));
                        }
                }

                //如果没有正在使用在使用中的language，则删除
                if (isUsing.isEmpty())
                {
                        contentManageDAO.deleteContentLanguage(lt);
                }

                return isUsing;
        }

        public static boolean isUsingContentLanguage(int languageID)
                throws ContentManageSysException
        {
                return contentManageDAO.isUsingContentLanguage(languageID);
        }

        /****************************************     ContentServer     *********************************************/

        /**
         * 添加contentServer
         *
         * @param clf
         * @return
         */
        public static boolean addContentServer(ContentServerForm clf)
        {
                if (contentManageDAO.isExistContentServer(clf.getName(),
                        clf.getRemark3()))
                {
                        return false;
                }
                else
                {
                        if ((clf.getIsDefault() != null) &&
                                (Integer.parseInt(clf.getIsDefault()) == 1))
                        {
                                ContentServerModel defaultServer = contentManageDAO.getDefaultContentServer(clf.getRemark3());

                                if (defaultServer.getName() != null)
                                {
                                        ContentServerModel server = contentManageDAO.getContentServer(defaultServer.getContentServerID());
                                        server.setIsDefault("0");
                                        contentManageDAO.updateContentServer(server);
                                }
                        }

                        ContentServerModel csm = new ContentServerModel();

                        copyProperites(csm, clf);

                        if (clf.getIsDefault() == null)
                        {
                                csm.setIsDefault("0");
                        }

                        csm.setCreateDate(new Date());
                        csm.setLastModDate(new Date());

                        contentManageDAO.addContentServer(csm);

                        return true;
                }
        }

        /**
         * 修改contentServer
         *
         * @param clf
         * @return
         */
        public static boolean updateContentServer(ContentServerForm clf)
        {
                if (contentManageDAO.isExistContentServer(clf.getContentServerID(),
                        clf.getName(), clf.getRemark3()))
                {
                        return false;
                }
                else
                {
                        //如果把当前的server设置为默认服务器，则查找以前是否有默认服务器，如果有，将其设为非默认
                        if ((clf.getIsDefault() != null) &&
                                (Integer.parseInt(clf.getIsDefault()) == 1))
                        {
                                ContentServerModel defaultServer = contentManageDAO.getDefaultContentServer(clf.getRemark3());

                                if (defaultServer.getName() != null)
                                {
                                        ContentServerModel server = contentManageDAO.getContentServer(defaultServer.getContentServerID());
                                        server.setIsDefault("0");
                                        contentManageDAO.updateContentServer(server);
                                }
                        }

                        ContentServerModel clm = contentManageDAO.getContentServer(clf.getContentServerID());

                        if (clf.getIsDefault() == null)
                        {
                                clm.setIsDefault("0");
                        }
                        else
                        {
                                clm.setIsDefault(clf.getIsDefault());
                        }

                        clm.setName(clf.getName());
                        clm.setHost(clf.getHost());
                        clm.setPort(clf.getPort());
                        clm.setVirtualPath(clf.getVirtualPath());
                        clm.setPhysicalPath(clf.getPhysicalPath());
                        clm.setLastModDate(new Date());

                        contentManageDAO.updateContentServer(clm);

                        return true;
                }
        }

        /**
         * 设置某个server为默认的服务器
         *
         * @param id
         * @param aspID
         */
        public static void setDefaultContentServer(int id, String aspID)
        {
                ContentServerModel defaultServer = contentManageDAO.getDefaultContentServer(aspID);

                if (defaultServer.getName() != null)
                {
                        ContentServerModel server = contentManageDAO.getContentServer(defaultServer.getContentServerID());
                        server.setIsDefault("0");
                        contentManageDAO.updateContentServer(server);
                }

                ContentServerModel server = contentManageDAO.getContentServer(id);
                server.setIsDefault("1");
                contentManageDAO.updateContentServer(server);
        }

        /**
         * 删除contentServer
         *
         * @param lt
         */
        public static void deleteContentServer(List lt)
        {
                contentManageDAO.deleteContentServer(lt);
        }

        /**
         * 得到全部contentServer
         *
         * @param aspID
         * @return
         */
        public static List getAllContentServer(String aspID)
        {
                return contentManageDAO.getAllContentServer(aspID);
        }

        /**
         * 根据contentServerID得到contentServer
         *
         * @param contentServerID
         * @return
         */
        public static ContentServerModel getContentServer(int contentServerID)
        {
                return contentManageDAO.getContentServer(contentServerID);
        }

        //*****************************************      Input       ***************************************************

        /**
         * 导入zip文件
         *
         * @param cf
         * @throws ContentManageSysException
         */
        public static void addInputContent(ContentForm cf)
                throws IOException
        {
                String subPath = cf.getLocation();

                //取得文件名
                String fileName = subPath.substring(subPath.lastIndexOf(File.separator) +
                        1, subPath.lastIndexOf("."));

                //刚刚上传的zip文件
                String tempZipFile = Config.getUploadPhysicalPath() + subPath;

                //解压缩的目录
                String unZipFilePath = Config.getUploadPhysicalPath() + "repository" +
                        File.separator + cf.getType() + File.separator +
                        cf.getRelationID() + File.separator + cf.getParentID() +
                        File.separator + fileName + File.separator;

                //解压缩文件所在的子目录
                //String unFilePath = File.separator + "repository" + File.separator + cf.getType() + File.separator + cf.getRelationID() + File.separator + cf.getParentID() + File.separator + fileName + File.separator;

                //创建解压缩的目录
                IOUtil.mkDir(unZipFilePath);

                try
                {
                        ZipUtil.unZip(tempZipFile, unZipFilePath);
                }
                catch (IOException e)
                {
                        throw new IOException();
                }
                finally
                {
                        //删除上传的zip文件
                        IOUtil.delAllFile(new File(tempZipFile));
                }

                File file = new File(unZipFilePath);
                String[] listFile = file.list();

                //判断导入文件解压缩后的文件是否是目录
                for (int i = 0; i < listFile.length; i++)
                {
                        File tempFile = new File(unZipFilePath + listFile[i]);

                        //如果有目录的话，判断是否与在客户端要显示的文件夹中的目录有重名，如果有，则将其改名
                        if (tempFile.isDirectory())
                        {
                                if (contentManageDAO.isHasSameCatalogByParentID(
                                        tempFile.getName(), cf.getParentID(),
                                        cf.getRelationID(), Integer.parseInt(cf.getType()),
                                        cf.getRemark3()))
                                {
                                        LogUtil.info("system",
                                                "rename " + tempFile.getAbsolutePath() + "-" +
                                                        file.getName());
                                        tempFile.renameTo(new File(tempFile.getAbsolutePath() +
                                                "-" + file.getName()));

                                        //IOUtil.delAllFile(new File(unZipFilePath));
                                        //throw new ContentManageSysException();
                                }
                        }
                }

                int depth = 0;
                List lt = new ArrayList();
                putInputFileInfoToDB(cf, unZipFilePath, depth, lt);
        }

        /*        public static void putInputFileInfoToDB(int parentID, int relationID, int type, int userID, String path)
        {
                Session session = null;
                try
                {
                        session = HibernateUtil.getSession();
                        putInputFileInfoToDB_pri(parentID, relationID, type, userID, path);
                }
                catch (HibernateException he)
                {
                        throw new ContentManageSysException("HibernateException while find: \n" +
                                he);
                }
                finally
                {
                        try
                        {
                                System.out.println("close session");
                                HibernateUtil.releaseSession(session);
                        }
                        catch (HibernateException e)
                        {
                                throw new ContentManageSysException("HibernateException while Hibernate update:  " + e, e);
                        }
                }
        }*/

        /**
         * 把导入ZIP解压后的文件相应信息存入数据库
         *
         * @param cf
         * @param path
         */
        public static void putInputFileInfoToDB(ContentForm cf, String path,
                                                int depth, List lt)
        {
                /*
                  通过传入一个list，把每一层的catalog保存到list中，层数从第0层开始，第零层的catalogID保存到lt.add(0,catalogID),
                  第一层的catalogID保存到(1,catalogID),当每一层叠代结束退出时就删除这一层的parentID,及其以下的所有节点，例如：
                  当第五层结束时，就从lt.remove(4)开始删除，删除他以下的所有节点。
                */
                File file = new File(path);
                String[] fileList = file.list();

                for (int j = 0; j < fileList.length; j++)
                {
                        File f = new File(path + fileList[j] + File.separator);

                        if (f.isFile())
                        {
                                ContentModel ccm = new ContentModel();

                                try
                                {
                                        if (depth == 0)
                                        {
                                                ccm.setParentID(cf.getParentID());
                                        }
                                        else
                                        {
                                                ccm.setParentID(Integer.parseInt(
                                                        lt.get(depth - 1).toString()));
                                        }
                                }
                                catch (IndexOutOfBoundsException e)
                                {
                                        ccm.setParentID(cf.getParentID());
                                }

                                ccm.setRelationID(cf.getRelationID());
                                ccm.setType(cf.getType());
                                ccm.setCreateDate(new Date());
                                ccm.setLastModDate(new Date());
                                ccm.setTitle(fileList[j]);
                                ccm.setIdentifier("1");
                                ccm.setLanguageID(cf.getLanguageID());
                                ccm.setContentSize((long) f.length());

                                String location = path.substring(path.indexOf("repository") -
                                        1, path.length() - 1) + File.separator + fileList[j];
                                ccm.setLocation(location);

                                UserForm um = UserHelper.getUser(String.valueOf(cf.getUserID()));
                                ccm.setCreator(um.getName());
                                ccm.setUserID(cf.getUserID());
                                ccm.setIsView(cf.getIsView());
                                ccm.setContentTypeID(cf.getContentTypeID());
                                ccm.setRemark3(cf.getRemark3());

                                ContentForm ccf = new ContentForm();
                                addContent(ccf);
                        }
                        else if (f.isDirectory())
                        {
                                ContentCatalogModel ccm = new ContentCatalogModel();

                                if (lt.isEmpty())
                                {
                                        ccm.setParentID(cf.getParentID());
                                }
                                else
                                {
                                        ccm.setParentID(Integer.parseInt(lt.get(depth - 1).toString()));
                                }

                                ccm.setRelationID(cf.getRelationID());
                                ccm.setType(cf.getType());
                                ccm.setCreateDate(new Date());
                                ccm.setLastModDate(new Date());
                                ccm.setTitle(f.getName());
                                ccm.setRemark3(cf.getRemark3());

                                Serializable s = null;

                                try
                                {
                                        s = HibernateDAO.add(ccm);
                                }
                                catch (ContentManageSysException e)
                                {
                                        throw new ContentManageSysException(e);
                                }

                                //通过传入空的list来记录每层的catalogID，当要往第3层目录下存东西时，就读取lt.get(3)作为parentID
                                if (!lt.isEmpty())
                                {
                                        /*
                                          当把一个目录存入数据库后，判断当前当树的层数是否等于list的大小，
                                          如果相等，例如depth==4,lt.size()==4,说明list里一共有4层的节点，从第0层一直到第3层，共4个，
                                          但层数为4，表示这是新的一层（第四层），所以把他的catalogID加入到list中，
                                        */
                                        if (lt.size() == depth)
                                        {
                                                lt.add(depth, s.toString());
                                        }
                                }
                                else
                                {
                                        lt.add(s.toString());
                                }

                                cf.setParentID(Integer.parseInt(lt.get(depth).toString()));
                                putInputFileInfoToDB(cf, path + fileList[j] + File.separator,
                                        depth + 1, lt);
                        }
                }

                //当每一层叠贷完毕退出时，就删除从这层的上一层开始及其以下的所有节点。
                while (lt.size() != (depth - 1))
                {
                        if (depth == 0)
                        {
                                break;
                        }

                        lt.remove(lt.size() - 1);
                }
        }

        public static void addInputCourseware(ContentForm cf)
                throws IOException
        {
                String subPath = cf.getLocation();

                //取得文件名
                String fileName = subPath.substring(subPath.lastIndexOf(File.separator) +
                        1, subPath.lastIndexOf("."));

                //刚刚上传的zip文件
                String tempZipFile = Config.getUploadPhysicalPath() + subPath;

                //解压缩的目录
                String unZipFilePath = Config.getUploadPhysicalPath() + "repository" +
                        File.separator + cf.getType() + File.separator +
                        cf.getRelationID() + File.separator + cf.getParentID() +
                        File.separator + fileName + File.separator;

                //解压缩文件所在的子目录
                //String unFilePath = File.separator + "repository" + File.separator + cf.getType() + File.separator + cf.getRelationID() + File.separator + cf.getParentID() + File.separator + fileName + File.separator;

                //创建解压缩的目录
                IOUtil.mkDir(unZipFilePath);

                try
                {
                        ZipUtil.unZip(tempZipFile, unZipFilePath);
                }
                catch (IOException e)
                {
                        throw new IOException();
                }
                finally
                {
                        //删除上传的zip文件
                        IOUtil.delAllFile(new File(tempZipFile));
                }

                cf.setLocation(File.separator + "repository" + File.separator +
                        cf.getType() + File.separator + cf.getRelationID() +
                        File.separator + cf.getParentID() + File.separator + fileName +
                        File.separator + cf.getIndex());
                addContent(cf);
        }

        /******************************************     CourseContentType     *****************************************/
        /**
         * 同过传入一个ContentTypeForm添加一个contentType
         *
         * @param CTF
         * @return
         */
        public static boolean addCourseContentType(CourseContentTypeForm CTF)
        {
                String ContentType = CTF.getCourseContentType();
                boolean isExist = courseContentTypeDAO.isExistCourseContentType(ContentType,
                        CTF.getRemark3());

                if (isExist == false)
                {
                        CourseContentTypeModel CTM = new CourseContentTypeModel();
                        int ordexIndex = courseContentTypeDAO.getMaxOrderIndexFromCourseContentType(CTF.getRemark3()) +
                                1;

                        //copyProperites(CTM, CTF);
                        CTM.setCourseContentType(CTF.getCourseContentType());
                        CTM.setName(CTF.getName());
                        CTM.setCreateDate(new Date());
                        CTM.setLastModDate(new Date());
                        CTM.setOrderIndex(ordexIndex);
                        CTM.setRemark3(CTF.getRemark3());

                        courseContentTypeDAO.addCourseContentType(CTM);

                        return true;
                }
                else
                {
                        return false;
                }
        }

        public static CourseContentTypeModel getCourseContentTypeByCourseContentTypeID(
                int contentType)
        {
                return courseContentTypeDAO.getCourseContentTypeByCourseContentTypeID(contentType);
        }

        /**
         * 同过传入一个ContentTypeForm修改contentType
         *
         * @param CTF
         * @return
         */
        public static boolean updateCourseContentType(CourseContentTypeForm CTF)
        {
                /*字符串courseContentType是和courseContentTypeModel中的coursecontentType对应的，要写入数据库中，而coursecontentTypeTemp是暂时的，
                在修改页面中coursecontentTypeTemp用来保存原始的coursecontentType，也就是修改前的coursecontentType,在页面提交后，无论页面中的coursecontentType是否更改，都把页面中的coursecontentType符给coursecontentTypeTemp，
                在这里比较这两个字符串，从而根据coursecontentType改变与否来进行操作*/
                String ContentType = CTF.getCourseContentType();
                String contentTypeTemp = CTF.getCourseContentTypeTemp();
                int contentTypeID = CTF.getCourseContentTypeID();

                if (ContentType.equals(contentTypeTemp)) //contentType没有改变
                {
                        CourseContentTypeModel CTM = new CourseContentTypeModel();

                        CTM = courseContentTypeDAO.getCourseContentTypeByCourseContentTypeID(contentTypeID);

                        CTM.setName(CTF.getName());
                        CTM.setLastModDate(new Date());

                        courseContentTypeDAO.updateCourseContentType(CTM);

                        return true;
                }
                else //改变contentType，要先判断一下改变后的contentType是否存在
                {
                        boolean isExist = courseContentTypeDAO.isExistCourseContentType(ContentType,
                                CTF.getRemark3());

                        if (isExist == false)
                        {
                                CourseContentTypeModel CTM = new CourseContentTypeModel();

                                CTM = courseContentTypeDAO.getCourseContentTypeByCourseContentTypeID(contentTypeID);

                                CTM.setCourseContentType(ContentType);
                                CTM.setName(CTF.getName());
                                CTM.setLastModDate(new Date());

                                courseContentTypeDAO.updateCourseContentType(CTM);

                                return true;
                        }
                        else //如果存在相同的contentType,则返回false
                        {
                                return false;
                        }
                }
        }

        /**
         * 通过传入一个List修改contentType的排序，List中的内容是字符串"7_9",
         * 9的意思是这个contentType的contentTypeID（contentTypeID是9）,7的意思是这个contentType排在第几位（第7位）
         *
         * @param lt
         */
        public static void updateCourseContentTypeOrderIndex(List lt)
        {
                int strn = 0;

                for (int i = 0; i < lt.size(); i++)
                {
                        String str = String.valueOf(lt.get(i));
                        strn = str.indexOf("_");

                        String str_cssOrder = str.substring(0, strn);
                        String str_cssID = str.substring(strn + 1);
                        int cssID = Integer.parseInt(str_cssID);
                        int cssOrder = Integer.parseInt(str_cssOrder);
                        CourseContentTypeModel CTM = courseContentTypeDAO.getCourseContentTypeByCourseContentTypeID(cssID);
                        CTM.setOrderIndex(cssOrder);
                        courseContentTypeDAO.updateCourseContentType(CTM);
                }
        }

        /**
         * 删除contentType,List中的内容是contentTypeID
         * <p/>
         * 返回包含正在使用中的contentType的id的List
         *
         * @param lt
         * @param aspID
         */
        public static List deleteCourseContentType(List lt, String aspID)
        {
                List isUsing = new ArrayList();

                for (int i = 0; i < lt.size(); i++)
                {
                        int ID = Integer.parseInt(lt.get(i).toString());

                        if (isUsingCourseContentType(ID))
                        {
                                isUsing.add(String.valueOf(ID));
                        }
                }

                //如果没有正在使用中的contentType，则可以删除
                if (isUsing.isEmpty())
                {
                        for (int i = 0; i < lt.size(); i++)
                        {
                                CourseContentTypeModel cctm = courseContentTypeDAO.getCourseContentTypeByCourseContentTypeID(Integer.parseInt(
                                        lt.get(i).toString()));
                                int orderIndex = cctm.getOrderIndex();
                                courseContentTypeDAO.deleteCourseContentType(Integer.parseInt(
                                        lt.get(i).toString()));
                                courseContentTypeDAO.updateCourseContentTypeOrderIndex(orderIndex,
                                        aspID);

                                /*                        List tempList = courseContentTypeDAO.getContentTypeByHQL("from ContentTypeModel where CONTENTTYPEID>" + lt.get(i).toString());
                                for (int j = 0; j < tempList.size(); j++)
                                {
                                        ContentTypeModel ctm = (ContentTypeModel) tempList.get(j);
                                        ctm.setOrderIndex(ctm.getOrderIndex() - 1);
                                        courseContentTypeDAO.updateContentType(ctm);
                                }*/
                        }
                }

                return isUsing;
        }

        /**
         * get all content type.
         *
         * @param aspID
         * @return
         */
        public static List getAllCourseContentType(String aspID)
        {
                if (courseContentTypeDAO.getAllCourseContentType(aspID).isEmpty())
                {
                        CourseContentTypeForm ctf = new CourseContentTypeForm();
                        ctf.setCourseContentType("教师信息");
                        ctf.setName("教师信息");
                        ctf.setIsDefault("1");
                        ctf.setOrderIndex(1);
                        ctf.setRemark3(aspID);
                        addCourseContentType(ctf);

                        ctf.setCourseContentType("教学大纲");
                        ctf.setName("教学大纲");
                        ctf.setOrderIndex(2);
                        ctf.setRemark3(aspID);
                        addCourseContentType(ctf);

                        ctf.setCourseContentType("课件");
                        ctf.setName("课件");
                        ctf.setOrderIndex(3);
                        ctf.setRemark3(aspID);
                        addCourseContentType(ctf);

                        ctf.setCourseContentType("课程作业");
                        ctf.setName("课程作业");
                        ctf.setOrderIndex(4);
                        ctf.setRemark3(aspID);
                        addCourseContentType(ctf);

                        ctf.setCourseContentType("课程讲义");
                        ctf.setName("课程讲义");
                        ctf.setOrderIndex(5);
                        ctf.setRemark3(aspID);
                        addCourseContentType(ctf);

                        ctf.setCourseContentType("复习大纲");
                        ctf.setName("复习大纲");
                        ctf.setOrderIndex(6);
                        ctf.setRemark3(aspID);
                        addCourseContentType(ctf);

                        ctf.setCourseContentType("考前指导");
                        ctf.setName("考前指导");
                        ctf.setOrderIndex(7);
                        ctf.setRemark3(aspID);
                        addCourseContentType(ctf);

                        ctf.setCourseContentType("相关资源");
                        ctf.setName("相关资源");
                        ctf.setOrderIndex(8);
                        ctf.setRemark3(aspID);
                        addCourseContentType(ctf);
                }

                return courseContentTypeDAO.getAllCourseContentType(aspID);
        }

        public static boolean isUsingCourseContentType(int contentTypeID)
                throws ContentManageSysException
        {
                return courseContentTypeDAO.isUsingCourseContentType(contentTypeID);
        }

        /**
         * 通过课程资源类别名称查找课程资源类别ID,-1表示没有找到
         *
         * @param str
         * @param aspID
         * @return
         */
        public static int getCourseContentTypeID(String str, String aspID)
        {
                return courseContentTypeDAO.getCourseContentTypeID(str, aspID);
        }

        public static List getResourseList(int userID)
                throws ContentManageSysException
        {
                return contentManageDAO.getContentListByUser(userID);
        }

        /**
         * (示范校显示)返回示范校的某种类型的示范校资源。
         *
         * @param relationID          示范校ＩＤ
         * @param courseContentTypeID 示范校资源类型　目前使用courseContentType,==-1:为显示全部
         * @param auditBySubAdmin     是否被示范校管理员审核 ,==-1:为显示全部
         * @param pageNo
         * @param pageSize
         * @return
         * @throws ContentManageSysException
         */
        public static PagerList getShiFanXiaoContentsAuditBySubAdmin(int relationID, int courseContentTypeID,
                                                                     int auditBySubAdmin, int pageNo, int pageSize)
        {
                return contentManageDAO.getShiFanXiaoContentsAuditBySubAdmin(relationID,
                        courseContentTypeID,
                        auditBySubAdmin, pageNo, pageSize);
        }

        /**
         * (示范校显示)示范校首页显示本示范校和总校发布的的某种类型的示范校资源。
         *
         * @param relationID          示范校ＩＤ
         * @param courseContentTypeID 示范校资源类型　目前使用courseContentType,==-1:为显示全部
         * @param pageNo
         * @param pageSize
         * @return
         * @throws ContentManageSysException
         */
        public static PagerList getShiFanXiaoContentsDispalyInPortal(int relationID, int courseContentTypeID,
                                                                      int pageNo, int pageSize)
        {
                return contentManageDAO.getShiFanXiaoContentsDispalyInPortal(relationID,
                        courseContentTypeID,pageNo, pageSize);
        }

        /**
         * (总校显示)返回所有的某种类型的示范校资源。
         *
         * @param courseContentTypeID 示范校资源类型　目前使用courseContentType,==-1:为显示全部
         * @param auditByAdmin        是否被总校管理员审核,==-1:为显示全部
         * @param pageNo
         * @param pageSize
         * @return
         * @throws ContentManageSysException
         */
        public static PagerList getShiFanXiaoContentsAuditByAdmin(int courseContentTypeID, int auditByAdmin,
                                                                  int pageNo, int pageSize)
        {
                return contentManageDAO.getShiFanXiaoContentsAuditByAdmin(
                        courseContentTypeID,
                        auditByAdmin, pageNo, pageSize);
        }

        /**
         * 示范校主页 教学原创, 状态已被示范校管理员审核
         *
         * @param orgID
         * @param pageNo
         * @param pageSize
         * @return
         */
        public static PagerList getShiFanXiaoContentsByJiaoXueYuanChuang(int orgID, int pageNo, int pageSize)
        {
                return contentManageDAO.getShiFanXiaoContentsDispalyInPortal(orgID,
                        ContentManageConstants.COURSECONTENTTYPEID_JIAOXUEYUANCHUANG,
                         pageNo, pageSize);
        }

        /**
         * 示范校主页 课堂实录, 状态已被示范校管理员审核
         *
         * @param pageNo
         * @param pageSize
         * @return
         */
        public static PagerList getShiFanXiaoContentsByKeTangShiLu(int orgID, int pageNo, int pageSize)
        {
                return contentManageDAO.getShiFanXiaoContentsDispalyInPortal(orgID,
                        ContentManageConstants.COURSECONTENTTYPEID_KETANGSHILU, pageNo, pageSize);
        }

        /**
         * 示范校主页 视频课件, 状态已被示范校管理员审核
         *
         * @param pageNo
         * @param pageSize
         * @return
         */
        public static PagerList getShiFanXiaoContentsByShiPinKeJian(int orgID, int pageNo, int pageSize)
        {
                return contentManageDAO.getShiFanXiaoContentsDispalyInPortal(orgID,
                        ContentManageConstants.COURSECONTENTTYPEID_SHIPINKEJIAN,
                        pageNo, pageSize);
        }

        /**
         * 示范校主页  示范课程, 状态已被示范校管理员审核
         *
         * @param pageNo
         * @param pageSize
         * @return
         */
        public static PagerList getShiFanXiaoContentsByShiFanKeCheng(int orgID, int pageNo, int pageSize)
        {
                return contentManageDAO.getShiFanXiaoContentsDispalyInPortal(orgID,
                        ContentManageConstants.COURSECONTENTTYPEID_SHIFANKECHENG,
                         pageNo, pageSize);
        }

        /**
         * 我的资源 ; 个人U盘
         *
         * @param userId
         * @param pageNo
         * @param pageSize
         * @return
         */
        public static PagerList getMyContent(int userId, int pageNo, int pageSize)
        {
                PagerList pl = contentManageDAO.getContentsByType(
                        ContentManageConstants.PERSONAL_CONTENT_TYPE, userId, -1, pageNo, pageSize);
                return pl;
        }

        /**
         * 返回某类型资源
         *
         * @param relationID
         * @param type
         * @param pageNo
         * @param pageSize
         * @return
         */
        public static PagerList getContentsByType(int type, int relationID, int contentType, int pageNo, int pageSize)
        {
                /*PagerList pl = contentManageDAO.getContentsByType(
                        type, relationID, contentType, pageNo, pageSize);*/
                PagerList pl = contentManageDAO.getContents(
                        type, relationID,null, -1,contentType,-1, ContentManageConstants.AUDIT_APPROVED,-1,
                        ContentManageConstants.doView, pageNo, pageSize);
                return pl;
        }

        /**
         * 返回资源列表
         * @param type     -1返回所有
         * @param relationID     所说，-1返回所有
         * @param relationIDs   可以包含多个机构， -1返回所有
         * @param creatorID      -1返回所有
         * @param contentTypeID   -1返回所有
         * @param parentID       所述目录， -1返回所有
         * @param auditByAdmin     -1返回所有
         * @param auditBySubAdmin    -1返回所有
         * @param isview             -1返回所有
         * @param pageNo             -1返回所有
         * @param pageSize           -1返回所有
         * @return
         * @throws ContentManageSysException
         */
        public static PagerList getContents(int type, int relationID,int[] relationIDs,int creatorID,int contentTypeID,
                                     int parentID,int auditByAdmin,int auditBySubAdmin,int isview,
                                     int pageNo,int pageSize)
        {
                PagerList pl = contentManageDAO.getContents( type,  relationID, relationIDs, creatorID, contentTypeID,
                                      parentID, auditByAdmin, auditBySubAdmin, isview,
                                      pageNo, pageSize);
                return pl;
        }

        /**
         *  按目录返回公共资源
         *
         * @param pageNo
         * @param pageSize
         * @return
         */
        public static PagerList getGeneralContentsByCatalog(int catalogID,int pageNo, int pageSize)
        {
                return contentManageDAO.getContentsByCatalog(
                        catalogID, pageNo, pageSize);
        }

        /**
         * 管理经验
         *
         * @param pageNo
         * @param pageSize
         * @return
         */
        public static PagerList getContentByGuanLiJingYan(int pageNo, int pageSize)
        {
                return contentManageDAO.getContentsByCatalog(
                        ContentManageConstants.CATALOGID_GLJY, pageNo, pageSize);
        }

        /**
         * 品牌建设
         *
         * @param pageNo
         * @param pageSize
         * @return
         */
        public static PagerList getContentByPinPaiJianShe(int pageNo, int pageSize)
        {
                return contentManageDAO.getContentsByCatalog(
                        ContentManageConstants.CATALOGID_PPJS, pageNo, pageSize);
        }

        /**
         * 人力资源
         *
         * @param pageNo
         * @param pageSize
         * @return
         */
        public static PagerList getContentByRenLiZiYuan(int pageNo, int pageSize)
        {
                return contentManageDAO.getContentsByCatalog(
                        ContentManageConstants.CATALOGID_RLZY, pageNo, pageSize);
        }

        /**
         * 学校文化
         *
         * @param pageNo
         * @param pageSize
         * @return
         */
        public static PagerList getContentByXueXiaoWenHua(int pageNo, int pageSize)
        {
                return contentManageDAO.getContentsByCatalog(
                        ContentManageConstants.CATALOGID_XXWH, pageNo, pageSize);
        }

        /**
         * 校长资源
         *
         * @param pageNo
         * @param pageSize
         * @return
         */
        public static PagerList getContentByXiaoZhangZiYuan(int pageNo, int pageSize)
        {
                return contentManageDAO.getContentsByCatalog(
                        ContentManageConstants.CATALOGID_XZZY, pageNo, pageSize);
        }

        /**
         * 课堂实录
         *
         * @param pageNo
         * @param pageSize
         * @return
         */
        public static PagerList getContentByKeTangShiLu(int pageNo, int pageSize)
        {
                return contentManageDAO.getContentsByCatalog(
                        ContentManageConstants.CATALOGID_KTSL, pageNo, pageSize);
        }

        /**
         * 培训信息
         *
         * @param pageNo
         * @param pageSize
         * @return
         */
        public static PagerList getContentByPeiXunXinXi(int pageNo, int pageSize)
        {
                return contentManageDAO.getContentsByCatalog(
                        ContentManageConstants.CATALOGID_PXXX, pageNo, pageSize);
        }

        /**
         * 课标培训
         *
         * @param pageNo
         * @param pageSize
         * @return
         */
        public static PagerList getContentByKeBiaoPeiXun(int pageNo, int pageSize)
        {
                return contentManageDAO.getContentsByCatalog(
                        ContentManageConstants.CATALOGID_KBPX, pageNo, pageSize);
        }

        /**
         * 教材培训
         *
         * @param pageNo
         * @param pageSize
         * @return
         */
        public static PagerList getContentByJiaoCaiPeiXun(int pageNo, int pageSize)
        {
                return contentManageDAO.getContentsByCatalog(
                        ContentManageConstants.CATALOGID_JCPX, pageNo, pageSize);
        }

        /**
         * 教法培训
         *
         * @param pageNo
         * @param pageSize
         * @return
         */
        public static PagerList getContentByJiaoFaPeiXun(int pageNo, int pageSize)
        {
                return contentManageDAO.getContentsByCatalog(
                        ContentManageConstants.CATALOGID_JFPX, pageNo, pageSize);
        }

        /**
         * 基本功培训
         *
         * @param pageNo
         * @param pageSize
         * @return
         */
        public static PagerList getContentByJiBenGongXunLian(int pageNo, int pageSize)
        {
                return contentManageDAO.getContentsByCatalog(
                        ContentManageConstants.CATALOGID_JBGPX, pageNo, pageSize);
        }

        /**
         * 教学参考
         *
         * @param pageNo
         * @param pageSize
         * @return
         */
        public static PagerList getContentByJiaoXueCanKao(int pageNo, int pageSize)
        {
                return contentManageDAO.getContentsByCatalog(
                        ContentManageConstants.CATALOGID_JXCK, pageNo, pageSize);
        }

        /**
         * 教案精选
         *
         * @param pageNo
         * @param pageSize
         * @return
         */
        public static PagerList getContentByJiaoAnJingXuan(int pageNo, int pageSize)
        {
                return contentManageDAO.getContentsByCatalog(
                        ContentManageConstants.CATALOGID_JAJX, pageNo, pageSize);
        }

        /**
         * 配套练习
         *
         * @param pageNo
         * @param pageSize
         * @return
         */
        public static PagerList getContentByPeiTaoLianXi(int pageNo, int pageSize)
        {
                return contentManageDAO.getContentsByCatalog(
                        ContentManageConstants.CATALOGID_PTLX, pageNo, pageSize);
        }

        /**
         * 学生准入和过度教材
         *
         * @param pageNo
         * @param pageSize
         * @return
         */
        public static PagerList getContentByXSZRGDJC(int pageNo, int pageSize)
        {
                return contentManageDAO.getContentsByCatalog(
                        ContentManageConstants.CATALOGID_XSZRGDJC, pageNo, pageSize);
        }

        /**
         * 精选试题,同"学生准入和过度教材"
         *
         * @param pageNo
         * @param pageSize
         * @return
         */
        public static PagerList getContentByJingXuanShiTi(int pageNo, int pageSize)
        {
                return contentManageDAO.getContentsByCatalog(
                        ContentManageConstants.CATALOGID_XSZRGDJC, pageNo, pageSize);
        }

        /**
         * 视频参考
         *
         * @param pageNo
         * @param pageSize
         * @return
         */
        public static PagerList getContentBySPCK(int pageNo, int pageSize)
        {
                return contentManageDAO.getContentsByCatalog(
                        ContentManageConstants.CATALOGID_SPCK, pageNo, pageSize);
        }

        /**
         * 教材分析
         *
         * @param pageNo
         * @param pageSize
         * @return
         */
        public static PagerList getContentByJCFX(int pageNo, int pageSize)
        {
                return contentManageDAO.getContentsByCatalog(
                        ContentManageConstants.CATALOGID_JCFX, pageNo, pageSize);
        }

        /**
         * 教学挂图和单词卡片
         *
         * @param pageNo
         * @param pageSize
         * @return
         */
        public static PagerList getContentByJXGTDCKP(int pageNo, int pageSize)
        {
                return contentManageDAO.getContentsByCatalog(
                        ContentManageConstants.CATALOGID_JXGTDCKP, pageNo, pageSize);
        }

        /**
         * 教材配套简笔画
         *
         * @param pageNo
         * @param pageSize
         * @return
         */
        public static PagerList getContentByJCPTJBH(int pageNo, int pageSize)
        {
                return contentManageDAO.getContentsByCatalog(
                        ContentManageConstants.CATALOGID_JCPTJBH, pageNo, pageSize);
        }

        /**
         * 说课
         *
         * @param pageNo
         * @param pageSize
         * @return
         */
        public static PagerList getContentBySK(int pageNo, int pageSize)
        {
                return contentManageDAO.getContentsByCatalog(
                        ContentManageConstants.CATALOGID_SK, pageNo, pageSize);
        }

        /**
         * 教案交流
         *
         * @param pageNo
         * @param pageSize
         * @return
         */
        public static PagerList getContentByJAJL(int pageNo, int pageSize)
        {
                return contentManageDAO.getContentsByCatalog(
                        ContentManageConstants.CATALOGID_JAJL, pageNo, pageSize);
        }

        /**
         * 教学游戏
         *
         * @param pageNo
         * @param pageSize
         * @return
         */
        public static PagerList getContentByJXYX(int pageNo, int pageSize)
        {
                return contentManageDAO.getContentsByCatalog(
                        ContentManageConstants.CATALOGID_JXYX, pageNo, pageSize);
        }

        /**
         * 特色教学
         *
         * @param pageNo
         * @param pageSize
         * @return
         */
        public static PagerList getContentByTSJX(int pageNo, int pageSize)
        {
                return contentManageDAO.getContentsByCatalog(
                        ContentManageConstants.CATALOGID_TSJX, pageNo, pageSize);
        }

        /**
         * 美文赏析
         *
         * @param pageNo
         * @param pageSize
         * @return
         */
        public static PagerList getContentByMWSX(int pageNo, int pageSize)
        {
                return contentManageDAO.getContentsByCatalog(
                        ContentManageConstants.CATALOGID_MWSX, pageNo, pageSize);
        }

        /**
         * 精选资源
         *
         * @param pageNo
         * @param pageSize
         * @return
         */
        public static PagerList getContentByJXZY(int pageNo, int pageSize)
        {
                return contentManageDAO.getContentsByCatalog(
                        ContentManageConstants.CATALOGID_JXZY, pageNo, pageSize);
        }

        /**
         * 教学文摘
         *
         * @param pageNo
         * @param pageSize
         * @return
         */
        public static PagerList getContentByJXWZ(int pageNo, int pageSize)
        {
                return contentManageDAO.getContentsByCatalog(
                        ContentManageConstants.CATALOGID_JXWZ, pageNo, pageSize);
        }

        /**
         * 师资培训
         *
         * @param pageNo
         * @param pageSize
         * @return
         */
        public static PagerList getContentBySZPX(int pageNo, int pageSize)
        {
                return contentManageDAO.getContentsByCatalog(
                        ContentManageConstants.CATALOGID_SZPX, pageNo, pageSize);
        }

        /**
         * 录音磁带
         *
         * @param pageNo
         * @param pageSize
         * @return
         */
        public static PagerList getContentByLuYinCiDai(int pageNo, int pageSize)
        {
                return contentManageDAO.getContentsByCatalog(
                        ContentManageConstants.CATALOGID_LYCD, pageNo, pageSize);
        }

        /**
         * 英语歌曲
         *
         * @param pageNo
         * @param pageSize
         * @return
         */
        public static PagerList getContentByYingYuGeQu(int pageNo, int pageSize)
        {
                return contentManageDAO.getContentsByCatalog(
                        ContentManageConstants.CATALOGID_YYGQ, pageNo, pageSize);
        }

        /**
         * 英语笑话
         *
         * @param pageNo
         * @param pageSize
         * @return
         */
        public static PagerList getContentByYingYuXiaoHua(int pageNo, int pageSize)
        {
                return contentManageDAO.getContentsByCatalog(
                        ContentManageConstants.CATALOGID_YYXH, pageNo, pageSize);
        }

        /**
         * 网上练兵
         *
         * @param pageNo
         * @param pageSize
         * @return
         */
        public static PagerList getContentByWangShangLianBing(int pageNo, int pageSize)
        {
                return contentManageDAO.getContentsByCatalog(
                        ContentManageConstants.CATALOGID_WSLB, pageNo, pageSize);
        }

        /**
         * 双语故事
         *
         * @param pageNo
         * @param pageSize
         * @return
         */
        public static PagerList getContentByShangYuGuShi(int pageNo, int pageSize)
        {
                return contentManageDAO.getContentsByCatalog(
                        ContentManageConstants.CATALOGID_SYGS, pageNo, pageSize);
        }

        /**
         * 线上游戏
         *
         * @param pageNo
         * @param pageSize
         * @return
         */
        public static PagerList getContentByXianShangYouXi(int pageNo, int pageSize)
        {
                return contentManageDAO.getContentsByCatalog(
                        ContentManageConstants.CATALOGID_XSYX, pageNo, pageSize);
        }

        /**
         * 单词学习
         *
         * @param pageNo
         * @param pageSize
         * @return
         */
        public static PagerList getContentByDanCiXueXi(int pageNo, int pageSize)
        {
                return contentManageDAO.getContentsByCatalog(
                        ContentManageConstants.CATALOGID_DCXX, pageNo, pageSize);
        }

        /**
         * 短语对话
         *
         * @param pageNo
         * @param pageSize
         * @return
         */
        public static PagerList getContentByDuanYuDuiHua(int pageNo, int pageSize)
        {
                return contentManageDAO.getContentsByCatalog(
                        ContentManageConstants.CATALOGID_DYDH, pageNo, pageSize);
        }

        /**
         * 动画故事
         *
         * @param pageNo
         * @param pageSize
         * @return
         */
        public static PagerList getContentByDongHuaGuShi(int pageNo, int pageSize)
        {
                return contentManageDAO.getContentsByCatalog(
                        ContentManageConstants.CATALOGID_DHGS, pageNo, pageSize);
        }

        /**
         * 英语测试
         *
         * @param pageNo
         * @param pageSize
         * @return
         */
        public static PagerList getContentByYingYuCeShi(int pageNo, int pageSize)
        {
                return contentManageDAO.getContentsByCatalog(
                        ContentManageConstants.CATALOGID_YYCS, pageNo, pageSize);
        }

        /**
         * 显示在总校主页 教学原创, 状态已被总校管理员审核
         *
         * @param pageNo
         * @param pageSize
         * @return
         */
        public static PagerList getShiFanXiaoContentsByJiaoXueYuanChuang(int pageNo, int pageSize)
        {
                return contentManageDAO.getShiFanXiaoContentsAuditByAdmin(
                        ContentManageConstants.COURSECONTENTTYPEID_JIAOXUEYUANCHUANG,
                        ContentManageConstants.AUDIT_APPROVED, pageNo, pageSize);
        }

        /**
         * 显示在总校主页 课堂实录, 状态已被总校管理员审核
         *
         * @param pageNo
         * @param pageSize
         * @return
         */
        public static PagerList getShiFanXiaoContentsByKeTangShiLu(int pageNo, int pageSize)
        {
                return contentManageDAO.getShiFanXiaoContentsAuditByAdmin(
                        ContentManageConstants.COURSECONTENTTYPEID_KETANGSHILU,
                        ContentManageConstants.AUDIT_APPROVED, pageNo, pageSize);
        }

        /**
         * 显示在总校主页 视频课件, 状态已被在总校管理员审核
         *
         * @param pageNo
         * @param pageSize
         * @return
         */
        public static PagerList getShiFanXiaoContentsByShiPinKeJian(int pageNo, int pageSize)
        {
                return contentManageDAO.getShiFanXiaoContentsAuditByAdmin(
                        ContentManageConstants.COURSECONTENTTYPEID_SHIPINKEJIAN,
                        ContentManageConstants.AUDIT_APPROVED,
                        pageNo, pageSize);
        }

        /**
         * 显示在总校主页 示范课程, 状态已被总校管理员审核
         *
         * @param pageNo
         * @param pageSize
         * @return
         */
        public static PagerList getShiFanXiaoContentsByShiFanKeCheng(int pageNo, int pageSize)
        {
                return contentManageDAO.getShiFanXiaoContentsAuditByAdmin(
                        ContentManageConstants.COURSECONTENTTYPEID_SHIFANKECHENG,
                        ContentManageConstants.AUDIT_APPROVED,
                        pageNo, pageSize);
        }

        /**
         * 显示所属教师的示范校资源
         *
         * @param creatorID           所属教师
         * @param courseContentTypeID 是否被总校管理员审核,==-1:为显示全部
         * @param auditByAdmin        是否被总校管理员审核,==-1:为显示全部
         * @param pageNo
         * @param pageSize
         * @return
         */
        public static PagerList getShiFanXiaoContentsByCreator(int creatorID, int courseContentTypeID, int auditByAdmin, int pageNo,
                                                               int pageSize) throws ContentManageSysException
        {
                return contentManageDAO.getShiFanXiaoContentsByCreator(
                        creatorID, courseContentTypeID,
                        auditByAdmin,
                        pageNo, pageSize);
        }

        /**
         * 总校管理员审核资源
         *
         * @param ids         资源列表
         * @param auditStatus
         * @throws ContentManageSysException
         */
        public static void auditContentsByAdmin(List ids, int auditStatus) throws ContentManageSysException
        {
                contentManageDAO.auditContentsByAdmin(ids, auditStatus);
        }

        /**
         * 示范校管理员审核资源
         *
         * @param ids         资源列表
         * @param auditStatus
         * @throws ContentManageSysException
         */
        public static void auditContentsBySubAdmin(List ids, int auditStatus) throws ContentManageSysException
        {
                contentManageDAO.auditContentsBySubAdmin(ids, auditStatus);
        }

        /**
         * 返回资源列表
         *
         * @param type                -1返回所有
         * @param relationID          -1返回所有
         * @param relationIDs         -1返回所有
         * @param creatorID           -1返回所有
         * @param contentTypeID       -1返回所有
         * @param courseContentTypeID -1返回所有
         * @param parentID            -1返回所有
         * @param auditByAdmin        -1返回所有
         * @param auditBySubAdmin     -1返回所有
         * @param isview              -1返回所有
         * @param creator             null返回所有
         * @param publisher           null返回所有
         * @param keyword             null返回所有
         * @param orderBy             排序字段，默认按ID降序
         * @param isIncludeSubCatalog 是否包含子目录
         * @param pageNo
         * @param pageSize
         * @return
         * @throws ContentManageSysException
         */
        public static PagerList getContents(int type, int relationID, int[] relationIDs, int creatorID, int contentTypeID,
                                     int courseContentTypeID, int parentID, int auditByAdmin, int auditBySubAdmin, int isview,
                                     String creator, String publisher, String keyword, String orderBy,
                                     boolean isIncludeSubCatalog, int pageNo, int pageSize)
        {
                return contentManageDAO.getContents(type, relationID, relationIDs, creatorID, contentTypeID,
                                     courseContentTypeID, parentID, auditByAdmin, auditBySubAdmin, isview,
                                     creator, publisher, keyword, orderBy,
                                     isIncludeSubCatalog, pageNo, pageSize);
        }

        /**
         * 返回公共资源某目录下的所有资源，包括子目录下的资源
         * @param contentTypeID
         * @param parentID
         * @param pageNo
         * @param pageSize
         * @return
         */
        public static  PagerList getContents(int contentTypeID,int parentID, int pageNo, int pageSize)
        {
                return contentManageDAO.getContents(ContentManageConstants.PUBLIC_CONTENT_TYPE, -1, null, -1, contentTypeID,
                                     -1, parentID, -1, -1, ContentManageConstants.doView,
                                     null, null, null, null,
                                     true, pageNo, pageSize);
        }
}
