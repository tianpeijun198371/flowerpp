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
         * �����ض����͵���Դ�б�
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
         * ����ʦ��������-ѧϰ���ϲ���.
         * �ӷ�����������Ŀ¼�Ͻ���resourceĿ¼,
         * ����Ӧ�Ŀγ���Դ-ѧϰ���ϲ��ֵ������ǵ����ݿ�.
         * <p/>
         * ǰ������:resourceҪ�ϴ���������������Ŀ¼
         * ��resources���£����Կγ̱�ź���λ�������ϸ��˵�ǣ�0���γ��ļ������֣��γ̱�ţ��Ŀγ��ļ��У��磺001��003�ȣ���������Ǹ����γ��е�������Դ��
         * ��Դ�������£�
         * images
         * ������01
         * ������02
         * ������04
         * ������06
         * ���У���������ǰ�߼Ӹ���0������0������������ƽ̨�еĿγ̱��
         * ��01�������ʦ��Ϣ����02������γ̴�١���04������γ���ҵ����06�����������Դ��
         * ���е�images�ļ�Ϊ������01�е�ͼƬ��
         * <p/>
         * <p/>
         * �ļ���������
         * \resources\055������ǿγ̱��Ϊ0055�Ŀγ���Դ����·����
         * 05504������ǿγ̱��Ϊ0055�Ŀγ���ҵ��
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

                                                //�жϿγ̱����Ƿ�Ϸ�
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
                                                                                "��ʦ��Ϣ", aspID));
                                                                        content1.setTitle("��ʦ��Ϣ");
                                                                        contents.add(content1);
                                                                }
                                                                else if (temp.equals("02"))
                                                                {
                                                                        content1.setCourseContentTypeID(ContentManageHelper.getCourseContentTypeID(
                                                                                "��ѧ���", aspID));
                                                                        content1.setTitle("��ѧ���");
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
                                                                                "��ҵ";
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
                                                                                "�����Դ", aspID));
                                                                        content1.setTitle("�����Դ");
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
         * ����ʦ��������-�μ�����.
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

                //����Ա�ܲ鿴������Դ����ͨ�û�ֻ�ܲ鿴�ѷ�������Դ
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
                else if (sf.getScopeType() == ContentManageConstants.All_TYPE) // �������ȫ�����������ж�ʱ������Ӧ���Ͳ鿴��Ȩ��
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
                                        ContentManageConstants.PERSONAL_CONTENT_TYPE + "') "); //������������Դ
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

                                //����Ա�ܲ鿴������Դ����ͨ�û�ֻ�ܲ鿴�ѷ�������Դ(��ǰ����)
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
         * ������ҳ��λ����ѡ�������������ϣ�flagΪ-1��ʾ���һ����ѯ����
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
         * ͬ������һ��ContentTypeForm���һ��contentType
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
         * ͬ������һ��ContentTypeForm�޸�contentType
         *
         * @param CTF
         * @return
         */
        public static boolean updateContentType(ContentTypeForm CTF)
        {
                /*�ַ���contentType�Ǻ�ContentTypeModel�е�contentType��Ӧ�ģ�Ҫд�����ݿ��У���contentTypeTemp����ʱ�ģ�
                ���޸�ҳ����contentTypeTemp��������ԭʼ��contentType��Ҳ�����޸�ǰ��contentType,��ҳ���ύ������ҳ���е�contentType�Ƿ���ģ�����ҳ���е�contentType����contentTypeTemp��
                ������Ƚ��������ַ������Ӷ�����contentType�ı���������в���*/
                String ContentType = CTF.getContentType();
                String contentTypeTemp = CTF.getContentTypeTemp();
                int contentTypeID = CTF.getContentTypeID();

                if (ContentType.equals(contentTypeTemp)) //contentTypeû�иı�
                {
                        ContentTypeModel CTM = new ContentTypeModel();

                        CTM = contentManageDAO.getContentTypeByContentTypeID(contentTypeID);

                        CTM.setName(CTF.getName());
                        CTM.setLastModDate(new Date());

                        contentManageDAO.updateContentType(CTM);

                        return true;
                }
                else //�ı�contentType��Ҫ���ж�һ�¸ı���contentType�Ƿ����
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
                        else //���������ͬ��contentType,�򷵻�false
                        {
                                return false;
                        }
                }
        }

        /**
         * ͨ������һ��List�޸�contentType������List�е��������ַ���"7_9",
         * 9����˼�����contentType��contentTypeID��contentTypeID��9��,7����˼�����contentType���ڵڼ�λ����7λ��
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
         * ɾ��contentType,List�е�������contentTypeID
         * <p/>
         * ���ذ�������ʹ���е�contentType��id��List
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

                //���û������ʹ���е�contentType�������ɾ��
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
         * ͨ������ContentConfigForm�޸�ContentConfig
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
         * ���һ��Ŀ¼
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
         * �޸�Ŀ¼
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
         * ɾ��Ŀ¼
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
         * ɾ��Ŀ¼
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
         * �ƶ�.
         *
         * @param moveCatalogs Ҫ���ƶ���Ŀ¼
         * @param moveContent  Ҫ���ƶ�������
         * @param catalogID    Ҫ�ƶ���Ŀ��Ŀ¼
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
         * �ж�catalogID2�Ƿ�catalogID1����������Ŀ¼������
         *
         * @param catalogID1 Ҫ���Ƶ�Ŀ¼
         * @param catalogID2 Ҫ���Ƶ���Ŀ¼
         * @param aspID
         * @return ==1:����.==2:Ϊ����Ŀ¼ ==3:Ϊ��ͬĿ¼,
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
         * �ж�catalogID2�Ƿ�catalogID1����������Ŀ¼������
         *
         * @param catalogID1 Ҫ���Ƶ�Ŀ¼
         * @param catalogID2 Ҫ���Ƶ���Ŀ¼
         * @param aspID
         * @return ==1:����.==2:Ϊ����Ŀ¼
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
         * �����Դ
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
         * �޸���Դ
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
         * ɾ����Դ
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
         * ɾ��Ŀ¼������Դ.
         * <br>�����list�е��ַ���Ϊ��12_1��,12��ʾҪɾ�����ݵ�id,1��ʾ���id��Ŀ¼��id������2���ʾ���id����Դ��id��
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
                                //����������Ŀ����Ҫ�ж� δ��֤��Դ
                                if(!Config.isXLNProject())
                                {
                                        if (StringUtil.parseInt(ids[0]) == ContentManageConstants.NO_AUTH_CONTENT_ID)
                                        {
                                                throw new ContentManageAppException("����ɾ��δ��֤��ԴĿ¼!");
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
         * �õ�ȫ����language
         *
         * @param aspID
         * @return
         */
        public static List getAllContentLanguage(String aspID)
        {
                if (contentManageDAO.getAllContentLanguage(aspID).isEmpty())
                {
                        ContentLanguageForm clf1 = new ContentLanguageForm();
                        clf1.setLanguage("����");
                        clf1.setLanguageName("����");
                        clf1.setIsDefault("1");
                        clf1.setRemark3(aspID);
                        addContentLanguage(clf1);

                        ContentLanguageForm clf2 = new ContentLanguageForm();
                        clf2.setLanguage("Ӣ��");
                        clf2.setLanguageName("Ӣ��");
                        clf2.setRemark3(aspID);
                        addContentLanguage(clf2);

                        ContentLanguageForm clf3 = new ContentLanguageForm();
                        clf3.setLanguage("����");
                        clf3.setLanguageName("����");
                        clf3.setRemark3(aspID);
                        addContentLanguage(clf3);

                        ContentLanguageForm clf4 = new ContentLanguageForm();
                        clf4.setLanguage("����");
                        clf4.setLanguageName("����");
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
         * ���һ������
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
         * �޸�һ������
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
         * ɾ������
         * ��ֹɾ������ʹ�õ�language�����ذ���ʹ�������Ե�languageID��list
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

                //���û������ʹ����ʹ���е�language����ɾ��
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
         * ���contentServer
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
         * �޸�contentServer
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
                        //����ѵ�ǰ��server����ΪĬ�Ϸ��������������ǰ�Ƿ���Ĭ�Ϸ�����������У�������Ϊ��Ĭ��
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
         * ����ĳ��serverΪĬ�ϵķ�����
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
         * ɾ��contentServer
         *
         * @param lt
         */
        public static void deleteContentServer(List lt)
        {
                contentManageDAO.deleteContentServer(lt);
        }

        /**
         * �õ�ȫ��contentServer
         *
         * @param aspID
         * @return
         */
        public static List getAllContentServer(String aspID)
        {
                return contentManageDAO.getAllContentServer(aspID);
        }

        /**
         * ����contentServerID�õ�contentServer
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
         * ����zip�ļ�
         *
         * @param cf
         * @throws ContentManageSysException
         */
        public static void addInputContent(ContentForm cf)
                throws IOException
        {
                String subPath = cf.getLocation();

                //ȡ���ļ���
                String fileName = subPath.substring(subPath.lastIndexOf(File.separator) +
                        1, subPath.lastIndexOf("."));

                //�ո��ϴ���zip�ļ�
                String tempZipFile = Config.getUploadPhysicalPath() + subPath;

                //��ѹ����Ŀ¼
                String unZipFilePath = Config.getUploadPhysicalPath() + "repository" +
                        File.separator + cf.getType() + File.separator +
                        cf.getRelationID() + File.separator + cf.getParentID() +
                        File.separator + fileName + File.separator;

                //��ѹ���ļ����ڵ���Ŀ¼
                //String unFilePath = File.separator + "repository" + File.separator + cf.getType() + File.separator + cf.getRelationID() + File.separator + cf.getParentID() + File.separator + fileName + File.separator;

                //������ѹ����Ŀ¼
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
                        //ɾ���ϴ���zip�ļ�
                        IOUtil.delAllFile(new File(tempZipFile));
                }

                File file = new File(unZipFilePath);
                String[] listFile = file.list();

                //�жϵ����ļ���ѹ������ļ��Ƿ���Ŀ¼
                for (int i = 0; i < listFile.length; i++)
                {
                        File tempFile = new File(unZipFilePath + listFile[i]);

                        //�����Ŀ¼�Ļ����ж��Ƿ����ڿͻ���Ҫ��ʾ���ļ����е�Ŀ¼������������У��������
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
         * �ѵ���ZIP��ѹ����ļ���Ӧ��Ϣ�������ݿ�
         *
         * @param cf
         * @param path
         */
        public static void putInputFileInfoToDB(ContentForm cf, String path,
                                                int depth, List lt)
        {
                /*
                  ͨ������һ��list����ÿһ���catalog���浽list�У������ӵ�0�㿪ʼ��������catalogID���浽lt.add(0,catalogID),
                  ��һ���catalogID���浽(1,catalogID),��ÿһ����������˳�ʱ��ɾ����һ���parentID,�������µ����нڵ㣬���磺
                  ����������ʱ���ʹ�lt.remove(4)��ʼɾ����ɾ�������µ����нڵ㡣
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

                                //ͨ������յ�list����¼ÿ���catalogID����Ҫ����3��Ŀ¼�´涫��ʱ���Ͷ�ȡlt.get(3)��ΪparentID
                                if (!lt.isEmpty())
                                {
                                        /*
                                          ����һ��Ŀ¼�������ݿ���жϵ�ǰ�����Ĳ����Ƿ����list�Ĵ�С��
                                          �����ȣ�����depth==4,lt.size()==4,˵��list��һ����4��Ľڵ㣬�ӵ�0��һֱ����3�㣬��4����
                                          ������Ϊ4����ʾ�����µ�һ�㣨���Ĳ㣩�����԰�����catalogID���뵽list�У�
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

                //��ÿһ���������˳�ʱ����ɾ����������һ�㿪ʼ�������µ����нڵ㡣
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

                //ȡ���ļ���
                String fileName = subPath.substring(subPath.lastIndexOf(File.separator) +
                        1, subPath.lastIndexOf("."));

                //�ո��ϴ���zip�ļ�
                String tempZipFile = Config.getUploadPhysicalPath() + subPath;

                //��ѹ����Ŀ¼
                String unZipFilePath = Config.getUploadPhysicalPath() + "repository" +
                        File.separator + cf.getType() + File.separator +
                        cf.getRelationID() + File.separator + cf.getParentID() +
                        File.separator + fileName + File.separator;

                //��ѹ���ļ����ڵ���Ŀ¼
                //String unFilePath = File.separator + "repository" + File.separator + cf.getType() + File.separator + cf.getRelationID() + File.separator + cf.getParentID() + File.separator + fileName + File.separator;

                //������ѹ����Ŀ¼
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
                        //ɾ���ϴ���zip�ļ�
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
         * ͬ������һ��ContentTypeForm���һ��contentType
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
         * ͬ������һ��ContentTypeForm�޸�contentType
         *
         * @param CTF
         * @return
         */
        public static boolean updateCourseContentType(CourseContentTypeForm CTF)
        {
                /*�ַ���courseContentType�Ǻ�courseContentTypeModel�е�coursecontentType��Ӧ�ģ�Ҫд�����ݿ��У���coursecontentTypeTemp����ʱ�ģ�
                ���޸�ҳ����coursecontentTypeTemp��������ԭʼ��coursecontentType��Ҳ�����޸�ǰ��coursecontentType,��ҳ���ύ������ҳ���е�coursecontentType�Ƿ���ģ�����ҳ���е�coursecontentType����coursecontentTypeTemp��
                ������Ƚ��������ַ������Ӷ�����coursecontentType�ı���������в���*/
                String ContentType = CTF.getCourseContentType();
                String contentTypeTemp = CTF.getCourseContentTypeTemp();
                int contentTypeID = CTF.getCourseContentTypeID();

                if (ContentType.equals(contentTypeTemp)) //contentTypeû�иı�
                {
                        CourseContentTypeModel CTM = new CourseContentTypeModel();

                        CTM = courseContentTypeDAO.getCourseContentTypeByCourseContentTypeID(contentTypeID);

                        CTM.setName(CTF.getName());
                        CTM.setLastModDate(new Date());

                        courseContentTypeDAO.updateCourseContentType(CTM);

                        return true;
                }
                else //�ı�contentType��Ҫ���ж�һ�¸ı���contentType�Ƿ����
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
                        else //���������ͬ��contentType,�򷵻�false
                        {
                                return false;
                        }
                }
        }

        /**
         * ͨ������һ��List�޸�contentType������List�е��������ַ���"7_9",
         * 9����˼�����contentType��contentTypeID��contentTypeID��9��,7����˼�����contentType���ڵڼ�λ����7λ��
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
         * ɾ��contentType,List�е�������contentTypeID
         * <p/>
         * ���ذ�������ʹ���е�contentType��id��List
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

                //���û������ʹ���е�contentType�������ɾ��
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
                        ctf.setCourseContentType("��ʦ��Ϣ");
                        ctf.setName("��ʦ��Ϣ");
                        ctf.setIsDefault("1");
                        ctf.setOrderIndex(1);
                        ctf.setRemark3(aspID);
                        addCourseContentType(ctf);

                        ctf.setCourseContentType("��ѧ���");
                        ctf.setName("��ѧ���");
                        ctf.setOrderIndex(2);
                        ctf.setRemark3(aspID);
                        addCourseContentType(ctf);

                        ctf.setCourseContentType("�μ�");
                        ctf.setName("�μ�");
                        ctf.setOrderIndex(3);
                        ctf.setRemark3(aspID);
                        addCourseContentType(ctf);

                        ctf.setCourseContentType("�γ���ҵ");
                        ctf.setName("�γ���ҵ");
                        ctf.setOrderIndex(4);
                        ctf.setRemark3(aspID);
                        addCourseContentType(ctf);

                        ctf.setCourseContentType("�γ̽���");
                        ctf.setName("�γ̽���");
                        ctf.setOrderIndex(5);
                        ctf.setRemark3(aspID);
                        addCourseContentType(ctf);

                        ctf.setCourseContentType("��ϰ���");
                        ctf.setName("��ϰ���");
                        ctf.setOrderIndex(6);
                        ctf.setRemark3(aspID);
                        addCourseContentType(ctf);

                        ctf.setCourseContentType("��ǰָ��");
                        ctf.setName("��ǰָ��");
                        ctf.setOrderIndex(7);
                        ctf.setRemark3(aspID);
                        addCourseContentType(ctf);

                        ctf.setCourseContentType("�����Դ");
                        ctf.setName("�����Դ");
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
         * ͨ���γ���Դ������Ʋ��ҿγ���Դ���ID,-1��ʾû���ҵ�
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
         * (ʾ��У��ʾ)����ʾ��У��ĳ�����͵�ʾ��У��Դ��
         *
         * @param relationID          ʾ��У�ɣ�
         * @param courseContentTypeID ʾ��У��Դ���͡�Ŀǰʹ��courseContentType,==-1:Ϊ��ʾȫ��
         * @param auditBySubAdmin     �Ƿ�ʾ��У����Ա��� ,==-1:Ϊ��ʾȫ��
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
         * (ʾ��У��ʾ)ʾ��У��ҳ��ʾ��ʾ��У����У�����ĵ�ĳ�����͵�ʾ��У��Դ��
         *
         * @param relationID          ʾ��У�ɣ�
         * @param courseContentTypeID ʾ��У��Դ���͡�Ŀǰʹ��courseContentType,==-1:Ϊ��ʾȫ��
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
         * (��У��ʾ)�������е�ĳ�����͵�ʾ��У��Դ��
         *
         * @param courseContentTypeID ʾ��У��Դ���͡�Ŀǰʹ��courseContentType,==-1:Ϊ��ʾȫ��
         * @param auditByAdmin        �Ƿ���У����Ա���,==-1:Ϊ��ʾȫ��
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
         * ʾ��У��ҳ ��ѧԭ��, ״̬�ѱ�ʾ��У����Ա���
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
         * ʾ��У��ҳ ����ʵ¼, ״̬�ѱ�ʾ��У����Ա���
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
         * ʾ��У��ҳ ��Ƶ�μ�, ״̬�ѱ�ʾ��У����Ա���
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
         * ʾ��У��ҳ  ʾ���γ�, ״̬�ѱ�ʾ��У����Ա���
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
         * �ҵ���Դ ; ����U��
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
         * ����ĳ������Դ
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
         * ������Դ�б�
         * @param type     -1��������
         * @param relationID     ��˵��-1��������
         * @param relationIDs   ���԰������������ -1��������
         * @param creatorID      -1��������
         * @param contentTypeID   -1��������
         * @param parentID       ����Ŀ¼�� -1��������
         * @param auditByAdmin     -1��������
         * @param auditBySubAdmin    -1��������
         * @param isview             -1��������
         * @param pageNo             -1��������
         * @param pageSize           -1��������
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
         *  ��Ŀ¼���ع�����Դ
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
         * ������
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
         * Ʒ�ƽ���
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
         * ������Դ
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
         * ѧУ�Ļ�
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
         * У����Դ
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
         * ����ʵ¼
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
         * ��ѵ��Ϣ
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
         * �α���ѵ
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
         * �̲���ѵ
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
         * �̷���ѵ
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
         * ��������ѵ
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
         * ��ѧ�ο�
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
         * �̰���ѡ
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
         * ������ϰ
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
         * ѧ��׼��͹��Ƚ̲�
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
         * ��ѡ����,ͬ"ѧ��׼��͹��Ƚ̲�"
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
         * ��Ƶ�ο�
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
         * �̲ķ���
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
         * ��ѧ��ͼ�͵��ʿ�Ƭ
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
         * �̲����׼�ʻ�
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
         * ˵��
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
         * �̰�����
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
         * ��ѧ��Ϸ
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
         * ��ɫ��ѧ
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
         * ��������
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
         * ��ѡ��Դ
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
         * ��ѧ��ժ
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
         * ʦ����ѵ
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
         * ¼���Ŵ�
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
         * Ӣ�����
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
         * Ӣ��Ц��
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
         * ��������
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
         * ˫�����
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
         * ������Ϸ
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
         * ����ѧϰ
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
         * ����Ի�
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
         * ��������
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
         * Ӣ�����
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
         * ��ʾ����У��ҳ ��ѧԭ��, ״̬�ѱ���У����Ա���
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
         * ��ʾ����У��ҳ ����ʵ¼, ״̬�ѱ���У����Ա���
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
         * ��ʾ����У��ҳ ��Ƶ�μ�, ״̬�ѱ�����У����Ա���
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
         * ��ʾ����У��ҳ ʾ���γ�, ״̬�ѱ���У����Ա���
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
         * ��ʾ������ʦ��ʾ��У��Դ
         *
         * @param creatorID           ������ʦ
         * @param courseContentTypeID �Ƿ���У����Ա���,==-1:Ϊ��ʾȫ��
         * @param auditByAdmin        �Ƿ���У����Ա���,==-1:Ϊ��ʾȫ��
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
         * ��У����Ա�����Դ
         *
         * @param ids         ��Դ�б�
         * @param auditStatus
         * @throws ContentManageSysException
         */
        public static void auditContentsByAdmin(List ids, int auditStatus) throws ContentManageSysException
        {
                contentManageDAO.auditContentsByAdmin(ids, auditStatus);
        }

        /**
         * ʾ��У����Ա�����Դ
         *
         * @param ids         ��Դ�б�
         * @param auditStatus
         * @throws ContentManageSysException
         */
        public static void auditContentsBySubAdmin(List ids, int auditStatus) throws ContentManageSysException
        {
                contentManageDAO.auditContentsBySubAdmin(ids, auditStatus);
        }

        /**
         * ������Դ�б�
         *
         * @param type                -1��������
         * @param relationID          -1��������
         * @param relationIDs         -1��������
         * @param creatorID           -1��������
         * @param contentTypeID       -1��������
         * @param courseContentTypeID -1��������
         * @param parentID            -1��������
         * @param auditByAdmin        -1��������
         * @param auditBySubAdmin     -1��������
         * @param isview              -1��������
         * @param creator             null��������
         * @param publisher           null��������
         * @param keyword             null��������
         * @param orderBy             �����ֶΣ�Ĭ�ϰ�ID����
         * @param isIncludeSubCatalog �Ƿ������Ŀ¼
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
         * ���ع�����ԴĳĿ¼�µ�������Դ��������Ŀ¼�µ���Դ
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
