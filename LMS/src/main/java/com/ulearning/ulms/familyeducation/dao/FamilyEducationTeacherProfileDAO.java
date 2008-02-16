package com.ulearning.ulms.familyeducation.dao;

import com.ulearning.ulms.core.util.PagerList;
import com.ulearning.ulms.familyeducation.exception.FamilyEducationSysException;
import com.ulearning.ulms.familyeducation.model.FamilyEducationInfoModel;
import com.ulearning.ulms.familyeducation.model.FamilyEducationTeacherProfileModel;


public interface FamilyEducationTeacherProfileDAO
{
        //查看
        public FamilyEducationTeacherProfileModel get(int id)
                throws FamilyEducationSysException;

        //发布
        public void add(FamilyEducationTeacherProfileModel model)
                throws FamilyEducationSysException;

        //删除
        public void delete(int[] ids) throws FamilyEducationSysException;

        //修改
        public void update(FamilyEducationTeacherProfileModel model)
                throws FamilyEducationSysException;

        public PagerList getFamilyEducationTeacherProfiles(int aspID,
                                                           int bodyIdentity, int status, int pageNo, int pageSize)
                throws FamilyEducationSysException;

        public PagerList search(int aspID, String province, String area,
                                String district, String subject, int bodyIdentity, int tutorMode,
                                String teachername,String teacherCode,int gender, String keyword, int pageNo, int pageSize)
                throws FamilyEducationSysException;
}
