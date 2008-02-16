package com.ulearning.ulms.familyeducation.dao;

import com.ulearning.ulms.core.util.PagerList;
import com.ulearning.ulms.familyeducation.exception.FamilyEducationSysException;
import com.ulearning.ulms.familyeducation.model.FamilyEducationInfoModel;


public interface FamilyEducationInfoDAO
{
        //查看
        public FamilyEducationInfoModel get(int id)
                throws FamilyEducationSysException;

        //发布
        public void add(FamilyEducationInfoModel model)
                throws FamilyEducationSysException;

        //删除
        public void delete(int[] ids) throws FamilyEducationSysException;

        //修改
        public void update(FamilyEducationInfoModel model)
                throws FamilyEducationSysException;

        public PagerList getFamilyEducationInfos(int aspID, int createID,
                                                 int status, int pageNo, int pageSize)
                throws FamilyEducationSysException;

        public PagerList search(int aspID, String province, String area,
                                String district, String gradeName, String subjectName,
                                String contactName, int id,int status, int pageNo, int pageSize)
                throws FamilyEducationSysException;
}
