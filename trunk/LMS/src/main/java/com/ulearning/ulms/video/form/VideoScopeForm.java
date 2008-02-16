package com.ulearning.ulms.video.form;

import org.apache.struts.action.ActionForm;

import java.util.Date;

import com.ulearning.ulms.video.model.*;

public class VideoScopeForm extends ActionForm
{
        private int vclassid;
        private int conditonID;
        private String name;
        private String type;
        private String relationIDStr;
        private Date beginDate;
        private Date endDate;
        private String remark0;
        private String remark1;
        private String remark2;
        private String remark3;
        private int userID;
        private int orgID;

        public int getvclassid()
        {
                return vclassid;
        }

        public void setvclassid(int vclassid)
        {
                this.vclassid = vclassid;
        }

        public int getConditonID()
        {
                return conditonID;
        }

        public void setConditonID(int conditonID)
        {
                this.conditonID = conditonID;
        }

        public String getName()
        {
                return name;
        }

        public void setName(String name)
        {
                this.name = name;
        }

        public String getType()
        {
                return type;
        }

        public void setType(String type)
        {
                this.type = type;
        }

        public String getRelationIDStr()
        {
                return relationIDStr;
        }

        public void setRelationIDStr(String relationIDStr)
        {
                this.relationIDStr = relationIDStr;
        }

        public Date getBeginDate()
        {
                return beginDate;
        }

        public void setBeginDate(Date beginDate)
        {
                this.beginDate = beginDate;
        }

        public Date getEndDate()
        {
                return endDate;
        }

        public void setEndDate(Date endDate)
        {
                this.endDate = endDate;
        }

        public String getRemark0()
        {
                return remark0;
        }

        public void setRemark0(String remark0)
        {
                this.remark0 = remark0;
        }

        public String getRemark1()
        {
                return remark1;
        }

        public void setRemark1(String remark1)
        {
                this.remark1 = remark1;
        }

        public String getRemark2()
        {
                return remark2;
        }

        public void setRemark2(String remark2)
        {
                this.remark2 = remark2;
        }

        public String getRemark3()
        {
                return remark3;
        }

        public void setRemark3(String remark3)
        {
                this.remark3 = remark3;
        }

        public int getUserID()
        {
                return userID;
        }

        public void setUserID(int userID)
        {
                this.userID = userID;
        }

        public int getOrgID()
        {
                return orgID;
        }

        public void setOrgID(int orgID)
        {
                this.orgID = orgID;
        }

        public VideouserModel getVideouserModel()
        {
                VideouserModel eum = new VideouserModel();
                VideouserModelPK eumpk = new VideouserModelPK();
                eumpk.setvclassid(this.vclassid);
                eumpk.setType(this.type);
                eumpk.setUserid(this.userID);
                eum.setComp_id(eumpk);
                return eum;
        }

        public VideoOrganModel getVideoOrganModel()
        {
                VideoOrganModel eom = new VideoOrganModel();
                VideoOrganModelPK eompk = new VideoOrganModelPK();
                eompk.setvclassid(this.vclassid);
                eompk.setType(this.type);
                eompk.setOrgid(this.orgID);
                eom.setComp_id(eompk);
                return eom;
        }

        public VideoModel getVideoModel()
        {
                VideoModel cm = new VideoModel();
                cm.setVclassid(this.vclassid);
                cm.setName(this.name);
                cm.setType(this.type);
                cm.setRelationidstr(this.relationIDStr);
                cm.setBegindate(this.beginDate);
                cm.setEnddate(this.endDate);
                cm.setRemark0(this.remark0);
                cm.setRemark1(this.remark1);
                cm.setRemark2(this.remark2);
                cm.setRemark3(this.remark3);
                return cm;
        }

        public VideoScopeForm getVideoFrom(VideoModel videoModel)
        {
                VideoScopeForm ecm = new VideoScopeForm();
                ecm.setvclassid(videoModel.getVclassid());
                ecm.setBeginDate(videoModel.getBegindate());
                ecm.setEndDate(videoModel.getEnddate());
                ecm.setName(videoModel.getName());
                ecm.setRelationIDStr(videoModel.getRelationidstr());
                ecm.setRemark0(videoModel.getRemark0());
                ecm.setRemark1(videoModel.getRemark1());
                ecm.setRemark2(videoModel.getRemark2());
                ecm.setRemark3(videoModel.getRemark3());
                ecm.setType(videoModel.getType());
                return ecm;
        }
}
