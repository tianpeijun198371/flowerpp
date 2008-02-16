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

        //查看请家教信息
        public static FamilyEducationInfoModel getFamilyEducationInfo(int id)
        {
                return familyEducationInfoDAO.get(id);
        }

        //发布请家教信息
        public static void addFamilyEducationInfo(FamilyEducationInfoModel model)
        {
                model.setCreateDate(new Date());
                model.setLastUpdateDate(new Date());
                familyEducationInfoDAO.add(model);
        }

        //删除请家教信息
        public static void deleteFamilyEducationInfo(int[] ids)
        {
                familyEducationInfoDAO.delete(ids);
        }

        //修改请家教信息
        public static void updateFamilyEducationInfo(FamilyEducationInfoModel model)
        {
                model.setLastUpdateDate(new Date());

                FamilyEducationInfoModel old = getFamilyEducationInfo(model.getId());
                model.setCreateDate(old.getCreateDate());
                familyEducationInfoDAO.update(model);
        }

        //查看做家教信息
        public static FamilyEducationTeacherProfileModel getFamilyEducationTeacherProfile(
                int id)
        {
                return familyEducationTeacherProfileDAO.get(id);
        }

        //发布做家教信息
        public static void addFamilyEducationTeacherProfile(
                FamilyEducationTeacherProfileModel model)
        {
                model.setCreateDate(new Date());
                model.setLastUpdateDate(new Date());
                familyEducationTeacherProfileDAO.add(model);
        }

        //删除做家教信息
        public static void deleteFamilyEducationTeacherProfile(int[] ids)
        {
                familyEducationTeacherProfileDAO.delete(ids);
        }

        //修改做家教信息
        public static void updateFamilyEducationTeacherProfile(
                FamilyEducationTeacherProfileModel model)
        {
                model.setLastUpdateDate(new Date());

                FamilyEducationTeacherProfileModel old = getFamilyEducationTeacherProfile(model.getTeacherID());
                model.setCreateDate(old.getCreateDate());
                familyEducationTeacherProfileDAO.update(model);
        }

        //搜索做家教信息
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

        //查看在线预约信息
        public static FeedBackModel getOnLineReservation(int id)
        {
                return feedBackDAO.get(id);
        }

        //查看在线预约信息的回复
        public static PagerList getOnLineReservationReply(int id, int pageNo,
                                                          int pageSize)
        {
                return feedBackDAO.getReplys(id, pageNo, pageSize);
        }

        //发布在线预约信息
        public static void addOnLineReservation(FeedBackModel model)
        {
                model.setCreateDate(new Date());
                feedBackDAO.insert(model);
        }

        //删除在线预约信息
        public static void deleteOnLineReservation(int[] ids)
        {
                feedBackDAO.delete(ids);
        }

        //修改在线预约信息
        public static void updateOnLineReservation(FeedBackModel model)
        {
                FeedBackModel old = getOnLineReservation(model.getFeedbackID());
                model.setCreateDate(old.getCreateDate());
                feedBackDAO.update(model);
        }

        //查看我发布的做家教信息
        public static FamilyEducationTeacherProfileModel myFamilyEducationTeacherProfile(
                int teacherID)
        {
                return familyEducationTeacherProfileDAO.get(teacherID);
        }

        //查看我发布的请家教信息
        public static PagerList myFamilyEducationInfo(int userID, int pageNo,
                                                      int pageSize)
        {
                return familyEducationInfoDAO.getFamilyEducationInfos(-1, userID, -1,
                        pageNo, pageSize);
        }

        //查看我发布的在线预约信息
        public static PagerList myOnLineReservation(int userID, int pageNo,
                                                    int pageSize)
        {
                return feedBackDAO.get(-1, userID, -1,
                        FeedBackConstants.FAMILYEDUCATION_ONLINERESERVE_TYPE, pageNo,
                        pageSize);
        }

        //返回做家教信息列表
        public static PagerList getFamilyEducationTeacherProfiles(int aspID,
                                                                  int bodyIdentity, int status, int pageNo, int pageSize)
        {
                return familyEducationTeacherProfileDAO.getFamilyEducationTeacherProfiles(aspID,
                        bodyIdentity, status, pageNo, pageSize);
        }

        //返回请家教信息列表
        public static PagerList getFamilyEducationInfos(int aspID, int createID,
                                                        int status, int pageNo, int pageSize)
        {
                return familyEducationInfoDAO.getFamilyEducationInfos(aspID, -1,
                        status, pageNo, pageSize);
        }

        //搜索请家教信息列表
        public static PagerList searchFamilyEducationInfo(int aspID,
                                                          String province, String area, String district, String gradeName,
                                                          String subjectName, String contactName,int id, int status, int pageNo,
                                                          int pageSize)
        {
                return familyEducationInfoDAO.search(aspID, province, area, district,
                        gradeName, subjectName, contactName, id,status, pageNo, pageSize);
        }

        //返回在线预约信息列表
        public static PagerList getOnLineReservations(int aspID, int userID,
                                                      int relationID, int pageNo, int pageSize)
        {
                return feedBackDAO.get(aspID, -1, relationID,
                        FeedBackConstants.FAMILYEDUCATION_ONLINERESERVE_TYPE, pageNo,
                        pageSize);
        }

        //搜索在线预约信息列表
        public static PagerList searchOnLineReservation(int aspID, int relationID, String userName, String relationName,
                                                        String keyword,int id,
                                                        int pageNo, int pageSize)
        {
                return feedBackDAO.search(aspID, -1, relationID,
                        FeedBackConstants.FAMILYEDUCATION_ONLINERESERVE_TYPE, userName,
                        relationName, keyword,id, pageNo, pageSize);
        }
}
