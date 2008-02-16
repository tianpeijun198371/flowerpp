package com.ulearning.ulms.familyeducation.helper;

import com.ulearning.ulms.core.util.PagerList;
import com.ulearning.ulms.evaluate.dao.FeedBackDAO;
import com.ulearning.ulms.evaluate.dao.FeedBackDAOFactory;
import com.ulearning.ulms.evaluate.model.FeedBackModel;
import com.ulearning.ulms.evaluate.util.FeedBackConstants;
import com.ulearning.ulms.familyeducation.dao.FamilyEducationInfoDAO;
import com.ulearning.ulms.familyeducation.dao.FamilyEducationInfoDAOFactory;
import com.ulearning.ulms.familyeducation.dao.FamilyEducationTeacherProfileDAO;
import com.ulearning.ulms.familyeducation.dao.FamilyEducationTeacherProfileDAOFactory;
import com.ulearning.ulms.familyeducation.model.FamilyEducationInfoModel;
import com.ulearning.ulms.familyeducation.model.FamilyEducationTeacherProfileModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Date;


public class FamilyEducationHelper
{
        protected static Log logger = LogFactory.getLog(FamilyEducationHelper.class);
        private static FamilyEducationInfoDAO familyEducationInfoDAO;
        private static FamilyEducationTeacherProfileDAO familyEducationTeacherProfileDAO;
        private static FeedBackDAO feedBackDAO;

        static
        {
                try
                {
                        familyEducationInfoDAO = FamilyEducationInfoDAOFactory.getDAO();
                        familyEducationTeacherProfileDAO = FamilyEducationTeacherProfileDAOFactory.getDAO();
                        feedBackDAO = FeedBackDAOFactory.getDAO();
                }
                catch (Exception ex)
                {
                        ex.printStackTrace();
                }
        }

        //�鿴��ҽ���Ϣ
        public static FamilyEducationInfoModel getFamilyEducationInfo(int id)
        {
                return familyEducationInfoDAO.get(id);
        }

        //������ҽ���Ϣ
        public static void addFamilyEducationInfo(FamilyEducationInfoModel model)
        {
                model.setCreateDate(new Date());
                model.setLastUpdateDate(new Date());
                familyEducationInfoDAO.add(model);
        }

        //ɾ����ҽ���Ϣ
        public static void deleteFamilyEducationInfo(int[] ids)
        {
                familyEducationInfoDAO.delete(ids);
        }

        //�޸���ҽ���Ϣ
        public static void updateFamilyEducationInfo(FamilyEducationInfoModel model)
        {
                model.setLastUpdateDate(new Date());

                FamilyEducationInfoModel old = getFamilyEducationInfo(model.getId());
                model.setCreateDate(old.getCreateDate());
                familyEducationInfoDAO.update(model);
        }

        //�鿴���ҽ���Ϣ
        public static FamilyEducationTeacherProfileModel getFamilyEducationTeacherProfile(
                int id)
        {
                return familyEducationTeacherProfileDAO.get(id);
        }

        //�������ҽ���Ϣ
        public static void addFamilyEducationTeacherProfile(
                FamilyEducationTeacherProfileModel model)
        {
                model.setCreateDate(new Date());
                model.setLastUpdateDate(new Date());
                familyEducationTeacherProfileDAO.add(model);
        }

        //ɾ�����ҽ���Ϣ
        public static void deleteFamilyEducationTeacherProfile(int[] ids)
        {
                familyEducationTeacherProfileDAO.delete(ids);
        }

        //�޸����ҽ���Ϣ
        public static void updateFamilyEducationTeacherProfile(
                FamilyEducationTeacherProfileModel model)
        {
                model.setLastUpdateDate(new Date());

                FamilyEducationTeacherProfileModel old = getFamilyEducationTeacherProfile(model.getTeacherID());
                model.setCreateDate(old.getCreateDate());
                familyEducationTeacherProfileDAO.update(model);
        }

        //�������ҽ���Ϣ
        public static PagerList searchFamilyEducationTeacherProfile(int aspID,
                                                                    String province, String area, String district, String subject,
                                                                    int bodyIdentity, int tutorMode, String teachername,
                                                                    String teacherCode, int gender,String keyword,
                                                                    int pageNo, int pageSize)
        {
                return familyEducationTeacherProfileDAO.search(aspID, province, area,
                        district, subject, bodyIdentity, tutorMode, teachername,teacherCode, gender,keyword,
                        pageNo, pageSize);
        }

        //�鿴����ԤԼ��Ϣ
        public static FeedBackModel getOnLineReservation(int id)
        {
                return feedBackDAO.get(id);
        }

        //�鿴����ԤԼ��Ϣ�Ļظ�
        public static PagerList getOnLineReservationReply(int id, int pageNo,
                                                          int pageSize)
        {
                return feedBackDAO.getReplys(id, pageNo, pageSize);
        }

        //��������ԤԼ��Ϣ
        public static void addOnLineReservation(FeedBackModel model)
        {
                model.setCreateDate(new Date());
                feedBackDAO.insert(model);
        }

        //ɾ������ԤԼ��Ϣ
        public static void deleteOnLineReservation(int[] ids)
        {
                feedBackDAO.delete(ids);
        }

        //�޸�����ԤԼ��Ϣ
        public static void updateOnLineReservation(FeedBackModel model)
        {
                FeedBackModel old = getOnLineReservation(model.getFeedbackID());
                model.setCreateDate(old.getCreateDate());
                feedBackDAO.update(model);
        }

        //�鿴�ҷ��������ҽ���Ϣ
        public static FamilyEducationTeacherProfileModel myFamilyEducationTeacherProfile(
                int teacherID)
        {
                return familyEducationTeacherProfileDAO.get(teacherID);
        }

        //�鿴�ҷ�������ҽ���Ϣ
        public static PagerList myFamilyEducationInfo(int userID, int pageNo,
                                                      int pageSize)
        {
                return familyEducationInfoDAO.getFamilyEducationInfos(-1, userID, -1,
                        pageNo, pageSize);
        }

        //�鿴�ҷ���������ԤԼ��Ϣ
        public static PagerList myOnLineReservation(int userID, int pageNo,
                                                    int pageSize)
        {
                return feedBackDAO.get(-1, userID, -1,
                        FeedBackConstants.FAMILYEDUCATION_ONLINERESERVE_TYPE, pageNo,
                        pageSize);
        }

        //�������ҽ���Ϣ�б�
        public static PagerList getFamilyEducationTeacherProfiles(int aspID,
                                                                  int bodyIdentity, int status, int pageNo, int pageSize)
        {
                return familyEducationTeacherProfileDAO.getFamilyEducationTeacherProfiles(aspID,
                        bodyIdentity, status, pageNo, pageSize);
        }

        //������ҽ���Ϣ�б�
        public static PagerList getFamilyEducationInfos(int aspID, int createID,
                                                        int status, int pageNo, int pageSize)
        {
                return familyEducationInfoDAO.getFamilyEducationInfos(aspID, -1,
                        status, pageNo, pageSize);
        }

        //������ҽ���Ϣ�б�
        public static PagerList searchFamilyEducationInfo(int aspID,
                                                          String province, String area, String district, String gradeName,
                                                          String subjectName, String contactName,int id, int status, int pageNo,
                                                          int pageSize)
        {
                return familyEducationInfoDAO.search(aspID, province, area, district,
                        gradeName, subjectName, contactName, id,status, pageNo, pageSize);
        }

        //��������ԤԼ��Ϣ�б�
        public static PagerList getOnLineReservations(int aspID, int userID,
                                                      int relationID, int pageNo, int pageSize)
        {
                return feedBackDAO.get(aspID, -1, relationID,
                        FeedBackConstants.FAMILYEDUCATION_ONLINERESERVE_TYPE, pageNo,
                        pageSize);
        }

        //��������ԤԼ��Ϣ�б�
        public static PagerList searchOnLineReservation(int aspID, int relationID, String userName, String relationName,
                                                        String keyword,int id,
                                                        int pageNo, int pageSize)
        {
                return feedBackDAO.search(aspID, -1, relationID,
                        FeedBackConstants.FAMILYEDUCATION_ONLINERESERVE_TYPE, userName,
                        relationName, keyword,id, pageNo, pageSize);
        }
}
