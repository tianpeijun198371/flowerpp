package com.ulearning.ulms.familyeducation.dao;

import com.ulearning.ulms.core.util.PagerList;
import com.ulearning.ulms.familyeducation.exception.FamilyEducationSysException;
import com.ulearning.ulms.familyeducation.model.FamilyEducationInfoModel;


public interface FamilyEducationInfoDAO
{
        //�鿴
        public FamilyEducationInfoModel get(int id)
                throws FamilyEducationSysException;

        //����
        public void add(FamilyEducationInfoModel model)
                throws FamilyEducationSysException;

        //ɾ��
        public void delete(int[] ids) throws FamilyEducationSysException;

        //�޸�
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
