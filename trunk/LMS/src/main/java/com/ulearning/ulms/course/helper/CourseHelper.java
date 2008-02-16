/**
 * CourseHelper.java.
 * User: fengch  Date: 2004-5-19
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.course.helper;

import com.ulearning.ulms.admin.certificate.bean.CertHelper;
import com.ulearning.ulms.admin.certificate.dao.CertDAO;
import com.ulearning.ulms.admin.certificate.dao.CertDAOFactory;
import com.ulearning.ulms.admin.certificate.form.CertForm;
import com.ulearning.ulms.common.helper.HistoryHelper;
import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.core.security.bean.SecurityConstants;
import com.ulearning.ulms.core.util.Config;
import com.ulearning.ulms.core.util.PagerList;
import com.ulearning.ulms.course.courseconfig.bean.CreditCourseWiseHelper;
import com.ulearning.ulms.course.courseconfig.bean.GradeWeightingFactorHelper;
import com.ulearning.ulms.course.dao.*;
import com.ulearning.ulms.course.exceptions.CourseSysException;
import com.ulearning.ulms.course.model.*;
import com.ulearning.ulms.course.util.CourseKeys;
import com.ulearning.ulms.organ.dao.OrganDAO;
import com.ulearning.ulms.organ.dao.OrganDAOFactory;
import com.ulearning.ulms.organ.form.OrganForm;
import com.ulearning.ulms.tools.access.helper.AccessHelp;
import com.ulearning.ulms.tools.calendar.helper.CalendarHelper;
import com.ulearning.ulms.tools.meeting.helper.MeetingHelper;
import com.ulearning.ulms.user.form.UserForm;
import com.ulearning.ulms.user.model.UserModel;
import com.ulearning.ulms.util.HibernateUtil;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;
import org.apache.commons.lang.math.NumberUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class CourseHelper
{
        private static CourseDAO courseDAO;
        private static CourseUserDAO courseUserDAO;
        private static MasterDAO masterDAO;
        private static CertDAO certDAO;

        static
        {
                try
                {
                        courseDAO = CourseDAOFactory.getDAO();
                        courseUserDAO = CourseUserDAOFactory.getDAO();
                        masterDAO = MasterDAOFactory.getDAO();
                        certDAO = CertDAOFactory.getDAO();
                }
                catch (Exception ex)
                {
                        ex.printStackTrace();
                }
        }

        private List subCatalog = new ArrayList();

        /**
         * ���ؿγ���Ϣ
         *
         * @param course
         * @return
         * @throws CourseSysException
         */
        public static CourseModel getCourse(int course) throws CourseSysException
        {
                CourseModel cat;

                cat = courseDAO.getCourse(course);

                return cat;
        }

        public static void delJieFoCourse(String[] courseIDs)
                throws CourseSysException
        {
                courseDAO.deleteJieFoCourse(courseIDs);
        }

        /**
         * @param courseID
         * @param userID
         * @param type     1:���� 2: ��ҵ
         * @return
         * @throws CourseSysException
         */
        public static float getJieFoChenji(int courseID, int userID, int type)
                throws CourseSysException
        {
                float ff = 0;
                ff = courseDAO.getJieFoChenji(courseID, userID, type);

                return ff;
        }

         /**
          * ɾ���γ�
         * @param values
         * @throws CourseSysException
         */
        public static void deleteCourse(List values) throws CourseSysException
        {
                if(values!=null && !values.isEmpty())
                {
                        courseDAO.deleteCourse(values);
                        if(Config.isXLNProject())
                        {
                                for (int i = 0; i < values.size(); i++)
                                {
                                        int id = ((Integer) values.get(i)).intValue();
                                        MeetingHelper.deleteMeetingByCourseID(id);
                                }
                        }
                }
        }
        

        /**
         * ���ؿγ���Ϣ
         *
         * @param relationID
         * @return
         * @throws CourseSysException
         */
        public static String getASPName(int relationID, int type)
                throws CourseSysException
        {
                int aspID = 0;
                String aspName = null;

                if (type == SecurityConstants.USER_COURSE_RELATION)
                {
                        aspID = courseDAO.getCourse(relationID).getOrgID();
                }
                else if (type == SecurityConstants.USER_CERTIFICATE_RELATION)
                {
                        aspID = CertHelper.getCert(relationID).getOrgID();
                }
                else
                {
                        aspName = "";
                }

                //aspName = OrganHelper.getOrgan(aspID).getOrgName();
                aspName = Config.getSysConfig(String.valueOf(aspID)).getPlatName();

                return aspName;
        }

        /*
        * return master model
        */
        public static MasterModel getMaster(int masterID) throws CourseSysException
        {
                return masterDAO.getMaster(masterID);
        }

        public static int getMasterHasPubNum(int masterID, int type)
                throws CourseSysException
        {
                return masterDAO.getMasterHasPubNum(masterID, type);
        }

        /*
        * get a course accord to aspID and code
        */
        public static CourseModel getCourse(int aspID, String code)
                throws CourseSysException
        {
                return courseDAO.getCourse(aspID, 0, code);
        }

        public static CourseModel getCourse(int aspID, int orgID, String code)
                throws CourseSysException
        {
                return courseDAO.getCourse(aspID, orgID, code);
        }

        /*
        * �ж�������Ŀ¼�£���Ŀ¼���Ƿ��Ѵ���
        */
        public boolean isExistCatalog(String thisCatalogName, int thisCatalogID,
                                      int catalogID, int catType, int aspID) throws CourseSysException
        {
                return masterDAO.isExistCatalog(thisCatalogName, thisCatalogID,
                        catalogID, catType, aspID, 0);
        }

        /*
        * �ж�������Ŀ¼�£���Ŀ¼���Ƿ��Ѵ���
        */
        public static String getCourseName(int courseID) throws CourseSysException
        {
                return courseDAO.getCourseName(courseID);
        }

        /**
         * Get the course tree according to the aspID
         *
         * @param aspID
         * @return
         * @throws CourseSysException
         */
        public static List getCourseTree(int aspID) throws CourseSysException
        {
                return null;
        }

        /**
         * ��asp����ֹ���ڷ��ؿγ�����
         *
         * @param aspID
         * @param type
         * @return
         * @throws CourseSysException
         */
        public static int getAspTotalMaster(int aspID, int type)
                throws CourseSysException
        {
                return masterDAO.getAspTotalMaster(aspID, 0, type);
        }

        public static int getAspTotalMaster(int aspID, int orgID, int type)
                throws CourseSysException
        {
                return masterDAO.getAspTotalMaster(aspID, orgID, type);
        }

        public static int getAspTotalMaster(int aspID, int type, String startDate,
                                            String endDate) throws CourseSysException
        {
                return masterDAO.getAspTotalMaster(aspID, 0, type, startDate, endDate);
        }

        public static int getAspTotalMaster(int aspID, int orgID, int type,
                                            String startDate, String endDate) throws CourseSysException
        {
                return masterDAO.getAspTotalMaster(aspID, orgID, type, startDate,
                        endDate);
        }

        /**
         * ��asp����ֹ���ڷ��ط����γ�����
         *
         * @param aspID
         * @param startDate
         * @param endDate
         * @return
         * @throws CourseSysException
         */
        public static int getAspTotalCourse(int aspID, String startDate,
                                            String endDate) throws CourseSysException
        {
                return courseDAO.getAspTotalCourse(aspID, 0, startDate, endDate);
        }

        public static int getAspTotalCourse(int aspID, int orgID) throws CourseSysException
        {
                return courseDAO.getAspTotalCourse(aspID, orgID);
        }

        public static int getAspTotalCourse(int aspID, int orgID, String startDate,
                                            String endDate) throws CourseSysException
        {
                return courseDAO.getAspTotalCourse(aspID, orgID, startDate, endDate);
        }

        /**
         * ��asp����ֹ���ڷ��ط����γ��б�
         *
         * @param aspID
         * @param startDate
         * @param endDate
         * @return
         * @throws CourseSysException
         */
        public static List getAspCourseInfo(int aspID, String startDate,
                                            String endDate) throws CourseSysException
        {
                return courseDAO.getAspCourseInfo(aspID, 0, startDate, endDate);
        }

        public static List getAspCourseInfo(int aspID, int orgID, String startDate,
                                            String endDate) throws CourseSysException
        {
                return courseDAO.getAspCourseInfo(aspID, orgID, startDate, endDate);
        }

        /**
         * �жϿγ̴����Ƿ����
         *
         * @param courseCode
         * @param courseID
         * @param aspID
         * @return
         * @throws CourseSysException
         */
        public static boolean isExistCourseCode(String courseCode, int courseID,
                                                int aspID) throws CourseSysException
        {
                return courseDAO.isExistCourseCode(courseCode, courseID, aspID, 0);
        }

        /**
         * �жϿγ�(����)�����Ƿ����
         *
         * @param masterCode
         * @param masterID
         * @param type
         * @param aspID
         * @return
         * @throws CourseSysException
         */
        public static boolean isExistMasterCode(String masterCode, int masterID,
                                                int type, int aspID) throws CourseSysException
        {
                return masterDAO.isExistMasterCode(masterCode, masterID, type, aspID, 0);
        }

        public static boolean isExistMasterCode(String masterCode, int masterID,
                                                int type, int aspID, int orgID) throws CourseSysException
        {
                return masterDAO.isExistMasterCode(masterCode, masterID, type, aspID,
                        orgID);
        }

        /**
         * ���ؿγ̣�֤�飩״̬��
         *
         * @param relationID
         * @param type
         * @return
         * @throws CourseSysException
         */
        public static CourseCurrentStatus getCourseNowStatus(int relationID,
                                                             int type) throws CourseSysException
        {
                return courseDAO.getCourseNowStatus(relationID, type); //SecurityConstants.USER_COURSE_RELATION);
        }

        /**
         * ��type==SecurityConstants.USER_CERTIFICATE_RELATIONʱ,
         * ����֤��Ŀγ��б�
         * todo:������Ŀ�Ŀγ��б�
         *
         * @param relationID
         * @param type
         * @return
         * @throws CourseSysException
         */
        public static List getCourseList(int relationID, int type)
                throws CourseSysException
        {
                return courseUserDAO.getCourseList(relationID, type);
        }

        /**
         * �����γ�
         * ��type==-1: ����֤��Ϳγ�(ע��List�б�������֤��,���ǿγ�)
         * ��type==SecurityConstants.USER_COURSE_RELATION: �����γ�
         * ��type==SecurityConstants.SecurityConstants.USER_CERTIFICATE_RELATION: ����֤��
         *
         * @param key
         * @param aspID
         * @return
         * @throws CourseSysException
         */
        public static CourseCertList searchCourse(String key, int aspID, int type)
                throws CourseSysException
        {
                CourseCertList list = new CourseCertList();

                if (type == -1)
                {
                        list.setCourseList(courseDAO.searchCourse(key, aspID, 0));
                        list.setCertList(CertHelper.search(key, aspID));
                }
                else if (type == SecurityConstants.USER_COURSE_RELATION)
                {
                        list.setCourseList(courseDAO.searchCourse(key, aspID, 0));
                }
                else if (type == SecurityConstants.USER_CERTIFICATE_RELATION)
                {
                        list.setCertList(CertHelper.search(key, aspID, 0));
                }

                return list;
        }

        public static CourseCertList searchCourse(String key, int aspID, int orgID,
                                                  int type) throws CourseSysException
        {
                CourseCertList list = new CourseCertList();

                if (type == -1)
                {
                        list.setCourseList(courseDAO.searchCourse(key, aspID, orgID));
                        list.setCertList(CertHelper.search(key, aspID));
                }
                else if (type == SecurityConstants.USER_COURSE_RELATION)
                {
                        list.setCourseList(courseDAO.searchCourse(key, aspID, orgID));
                }
                else if (type == SecurityConstants.USER_CERTIFICATE_RELATION)
                {
                        list.setCertList(CertHelper.search(key, aspID, orgID));
                }

                return list;
        }

        /**
         * �ж�������
         * ��type==SecurityConstants.USER_COURSE_RELATION: ���ؿγ�������
         * ��type==SecurityConstants.SecurityConstants.USER_CERTIFICATE_RELATION: ����֤��������
         * ����ֵ:
         * ==false:�γ������ѹ���
         * ==true:�γ�����������
         *
         * @param relationID
         * @param type
         * @return int
         * @throws CourseSysException
         */
        public static boolean getCourseLifeStatus(int relationID, int type)
                throws CourseSysException
        {
                if (type == SecurityConstants.USER_COURSE_RELATION)
                {
                        return courseDAO.getCourseLifeStatus(relationID);
                }
                else if (type == SecurityConstants.USER_CERTIFICATE_RELATION)
                {
                        return true;
                }

                return false;
        }

        /**
         * �жϿ�����
         * �����䱾������:���ú������ڶ�Ϊ1.
         * ��type==SecurityConstants.USER_COURSE_RELATION: ���ؿγ̿�����
         * ��type==SecurityConstants.SecurityConstants.USER_CERTIFICATE_RELATION: ����֤�������
         * ����ֵ:
         * ==false:������
         * ==true:����
         *
         * @param type
         * @param relationID
         * @return int
         * @throws CourseSysException
         */
        public static boolean IsCourseAvailable(int relationID, int type)
                throws CourseSysException
        {
                if (type == SecurityConstants.USER_COURSE_RELATION)
                {
                        int status = 0;

                        if (courseDAO.getCourse(relationID) != null)
                        {
                                status = courseDAO.getCourse(relationID).getStatus();
                        }

                        if ((status == 1) && courseDAO.getCourseLifeStatus(relationID))
                        {
                                return true;
                        }
                }
                else if (type == SecurityConstants.USER_CERTIFICATE_RELATION)
                {
                        CertForm cf = CertHelper.getCert(relationID);

                        if ((cf != null) && cf.getStatus().equals("1"))
                        {
                                return true;
                        }
                }

                return false;
        }

        public static void deletedRelationData(int courseID)
                throws ULMSSysException
        {
                GradeWeightingFactorHelper.delete(SecurityConstants.USER_COURSE_RELATION,
                        courseID);
                CreditCourseWiseHelper.delete(SecurityConstants.USER_COURSE_RELATION,
                        courseID);
                HistoryHelper.delete(courseID);
                AccessHelp.deleteByCourseID(courseID);
                CalendarHelper.delete(2, courseID);
        }

        public List getAllSubCatalog(int catalog) throws ULMSSysException
        {
                List tempList = new ArrayList();
                tempList = getSubCatalog(catalog);

                return subCatalog;
        }

        private List getSubCatalog(int catalog) throws ULMSSysException
        {
                List tempList = new ArrayList();
                tempList = courseDAO.getSubCatalog(catalog);

                if ((tempList != null) && !tempList.isEmpty())
                {
                        for (int i = 0; i < tempList.size(); i++)
                        {
                                CatalogModel cm = (CatalogModel) tempList.get(i);
                                subCatalog.add(cm);

                                if (courseDAO.getIsSubCatalog(cm.getCatalogID()))
                                {
                                        getSubCatalog(cm.getCatalogID());
                                }
                        }
                }

                return tempList;
        }

        /**
         * ���˵����ڰ༶�Ŀγ�
         *
         * @param courseList
         * @return
         */
        public static List getPublicCourse(List courseList)
        {
                if (courseList == null)
                {
                        return null;
                }

                CourseModel cm = null;
                List publicCourseList = new ArrayList();

                for (int i = 0; i < courseList.size(); i++)
                {
                        cm = (CourseModel) courseList.get(i);

                        if (certDAO.isCourseAlone(cm.getCourseID()))
                        {
                                publicCourseList.add(cm);
                        }
                }

                return publicCourseList;
        }

        public static void main(String[] args)
        {
                //          List publicCourseList =new ArrayList();
                //         int [] a ={1,3,5,7,9};
                //         int [] b ={2,3,5,7,9};
                //         for(int i=0;i<a.length;i++)
                //         {
                //             if(b[i]!=a[i])
                //             {
                //                 publicCourseList.add(new Integer(i));
                //                 System.out.println("i ==== " + i);
                //             }
                //         }
                CourseHelper.getOrgAppUsers(null, "308", "17", "4", 0, 10);
        }

        /**
         * ������ѵ���е�ѧ��
         *
         * @param orgID      �����˵Ļ���id
         * @param relationID ��ѵ���id
         * @param type       4��ʾ����״̬����Ա
         * @return
         */
        public static CourseUserListModel getOrgAppUsers(UserForm uf, String orgID,
                                                         String relationID, String type, int first, int max)
        {
                StringBuffer orgwhere = new StringBuffer(); //�ӻ���id���ϣ������Լ����ڵĻ���id
                OrganDAO organDao = OrganDAOFactory.getDAO();
                List orgchild = organDao.getOrganChildbyOrgID(Integer.parseInt(
                        orgID.trim()));

                for (int i = 0; i < orgchild.size(); i++)
                {
                        OrganForm of = (OrganForm) orgchild.get(i);
                        orgwhere.append(of.getOrgID());

                        if (i < (orgchild.size() - 1))
                        {
                                orgwhere.append(",");
                        }
                }

                if (orgwhere.length() > 0)
                {
                        orgwhere.append("," + orgID);
                }
                else
                {
                        orgwhere.append(orgID);
                }

                StringBuffer sql = new StringBuffer();
                //		sql.append("select from UserModel UU,CourseUser CU WHERE UU.userid=CU.comp_id.userID  ");
                //		sql.append(" and CU.comp_id.relationID=");
                //		sql.append(relationID);
                //		sql.append(" and CU.comp_id.type=");
                //		sql.append(type);
                //		sql.append(" and u.catalogid in (");
                //		sql.append(orgwhere.toString());
                //		sql.append(")");
                sql.append("select  from CourseUser CU,UserModel UU ");
                sql.append(" WHERE CU.comp_id.userID = UU.userid and  CU.comp_id.type=");
                sql.append(type);
                sql.append(" and CU.comp_id.relationID=");
                sql.append(relationID);
                sql.append(" and UU.userid >=100");
                sql.append(" and UU.catalogid in (");
                sql.append(orgwhere.toString());
                sql.append(")");

                if (!((uf == null) || "".equals(uf)))
                {
                        if ((uf.getLoginName() != null) &&
                                (uf.getLoginName().length() > 0))
                        {
                                sql.append(" and UU.loginname like '%");
                                sql.append(uf.getLoginName().trim());
                                sql.append("%'");
                        }
                }

                ArrayList al_CourseUsers = new ArrayList();
                Session session = null;

                try
                {
                        System.out.println("sql.toString()===========" + sql.toString());
                        session = HibernateUtil.getSession();

                        Query query = session.createQuery(sql.toString());
                        query.setFirstResult(first);
                        query.setMaxResults(max);

                        List tmplist = query.list();

                        for (Iterator iter = tmplist.iterator(); iter.hasNext();)
                        {
                                Object[] record = (Object[]) iter.next();
                                CourseUser courseUser = (CourseUser) record[0];
                                UserModel userModel = (UserModel) record[1];

                                CourseUserDAO cudao = CourseUserDAOFactory.getDAO();
                                CourseRoleListModel crlm = cudao.getUserRoles(userModel.getUserid(),
                                        Integer.parseInt(relationID), Integer.parseInt(type),
                                        null);
                                System.out.println("userModel.getUserid()=====" +
                                        userModel.getUserid());

                                CourseUserModel cudm = new CourseUserModel(courseUser);
                                cudm.setName(userModel.getName());
                                cudm.setLoginName(userModel.getLoginname());
                                cudm.setCourseRoles(crlm);

                                al_CourseUsers.add(cudm);
                        }

                        System.out.println("al_CourseUsers.size()============" +
                                al_CourseUsers.size());

                        CourseUserListModel culm = new CourseUserListModel(al_CourseUsers,
                                first, max, 0);

                        return culm;
                }
                catch (HibernateException e)
                {
                        throw new CourseSysException("SQLException while select certeuser" +
                                e);
                }
                finally
                {
                        try
                        {
                                if (session != null)
                                {
                                        HibernateUtil.releaseSession(session);
                                }
                        }
                        catch (HibernateException he)
                        {
                                he.printStackTrace();
                        }
                }
        }

        public static String getClassType(int catalogID)
        {
                String classType = "��ͯ���";
                if (catalogID == CourseKeys.XLN_ENGLISH_ERTONG_ONE)
                {
                        classType = "��ͯ���";
                }
                else if (catalogID == CourseKeys.XLN_ENGLISH_ERTONG_TWO)
                {
                        classType = "��ͯ���";
                }
                else if (catalogID == CourseKeys.XLN_ENGLISH_ERTONG_THREE)
                {
                        classType = "��ͯ���";
                }
                else if (catalogID == CourseKeys.XLN_ENGLISH_ERTONG_FORE)
                {
                        classType = "��ͯ���";
                }


                else if (catalogID == CourseKeys.XLN_ENGLISH_SHAOER_ONE)
                {
                        classType = "�ٶ����";
                }
                else if (catalogID == CourseKeys.XLN_ENGLISH_SHAOER_TWO)
                {
                        classType = "�ٶ����";
                }
                else if (catalogID == CourseKeys.XLN_ENGLISH_SHAOER_THREE)
                {
                        classType = "�ٶ����";
                }
                else if (catalogID == CourseKeys.XLN_ENGLISH_SHAOER_FORE)
                {
                        classType = "�ٶ����";
                }
                else if (catalogID == CourseKeys.XLN_ENGLISH_SHAOER_FIVE)
                {
                        classType = "�ٶ����";
                }

                else if (catalogID == CourseKeys.XLN_ENGLISH_CHUZHONG_ONE)
                {
                        classType = "���а��";
                }
                else if (catalogID == CourseKeys.XLN_ENGLISH_CHUZHONG_TWO)
                {
                        classType = "���а��";
                }
                else if (catalogID == CourseKeys.XLN_ENGLISH_CHUZHONG_THREE)
                {
                        classType = "���а��";
                }
                else if (catalogID == CourseKeys.XLN_ENGLISH_CHUZHONG_FORE)
                {
                        classType = "���а��";
                }
                else if(catalogID == CourseKeys.XLN_ENGLISH_CHUZHONG_FIVE)
                {
                        classType = "���а��";
                }
                else if(catalogID == CourseKeys.XLN_ENGLISH_CHUZHONG_SIX)
                {
                        classType = "���а��";
                }
                 return classType;
        }

        /*
         ******************************************************* tuning ***************************************************************
         */

        /**
         * ����Catalog
         *
         * @param id
         * @return
         * @throws CourseSysException
         */
        public static Catalog readCatalog(int id)
                throws CourseSysException
        {
                return  courseDAO.readCatalog(id);
        }

        /**
         * �����γ�
         *
         * @param aspID   ѧУID��-1 ����
         * @param orgID   ����ID��-1 ����
         * @param orgIDs  �����������ѧУ
         * @param catalogID �������������Ŀ¼
         * @param type    ���ͣ�null����      
         * @param creator  �����ߣ�-1 ����
         * @param teachmode  ��ѧ��ʽ����ͳ�����磬�ҽ̣���-1 ����
         * @param keyword
         * @param pageNo
         * @param pageSize
         * @return
         * @throws CourseSysException
         */
        public static PagerList search(int aspID, int orgID, int[] orgIDs,int catalogID, String type, int creator, int teachmode,
                                String ischarge,String allowfreedomreg,String needapprove,String registermode,String guest,
                                String status,String isCommend,
                                String keyword, int pageNo, int pageSize)
                throws CourseSysException
        {
                return  courseDAO.search( aspID,  orgID,  orgIDs,catalogID,  type,  creator,  teachmode,
                        ischarge, allowfreedomreg, needapprove, registermode, guest,
                                 status, isCommend,keyword,  pageNo,  pageSize);
        }

        /**
         * ȡĳĿ¼�����е���Ŀ¼(Catalog)
         *
         * @param aspID
         * @param orgID
         * @param catalogID
         * @param isIncludeSubCatalog �Ƿ�ݹ������Ŀ¼�µ�����
         * @return
         */
        public static List getCatalogChildren(int aspID, int orgID, int catalogID, boolean isIncludeSubCatalog)
        {
                return  courseDAO.getCatalogChildren( aspID,  orgID,  catalogID,  isIncludeSubCatalog);
        }

        /**
         * ȡĳĿ¼�����е���Ŀ¼(CatalogID)
         *
         * @param aspID
         * @param orgID
         * @param catalogID
         * @param isIncludeSubCatalog �Ƿ�ݹ������Ŀ¼�µ�����
         * @return
         */
        public static List getCatalogChildrenID(int aspID, int orgID, int catalogID, boolean isIncludeSubCatalog)
        {
                return  courseDAO.getCatalogChildrenID( aspID,  orgID,  catalogID, isIncludeSubCatalog);
        }
}
