package com.ulearning.ulms.finance.form;

import com.ulearning.ulms.finance.model.FuserAccountDetailModel;
import com.ulearning.ulms.finance.model.FuserAccountModel;

import org.apache.struts.action.ActionForm;

import java.util.Date;


/**
 * Created by IntelliJ IDEA.
 * 个人帐户收支明细Form
 *
 * @author Liz
 * @ date 2005.12.08
 * @ struts.form name="UserAccountDetailForm"  path="UserAccountDetailForm"
 */
public class UserAccountDetailForm extends ActionForm
{
        private int uaDetailID = 0; //ID
        private int userID = 0; //用户ID
        private String uaDetailUserName; //用户名称
        private int uaDetailInOutType = 0; //收支模式:   1：收;2：支
        private int uaDetailTypeID = 0; //具体的收支类型
        private double uaDetailAmount = 0.0; //金额
        private Date uaDetailDate; //发生时间
        private int uaDetailOperatorID = 0; //操作员ID
        private String uaDetailOperatorName; //操作员名称
        private int uaDetailEntityValue = 0; //收支类型实体ID的值
        private int uaDetailRemitType = 0; //汇款方式
        private int uaDetailInvoice = 0; //开票情况 0：没开;1：已开
        private int uaDetailAudit = 0; //是否审核 0：未审核  1：已审核
        private String uaDetailMemo; //备注
        private String uaDetailRemark1; //留用1
        private String uaDetailRemark2; //留用2
        private int aspId = 0; //aspID
        private int orgId = 0; //机构ID

        //辅助数据
        private String assEntityName; //明细支出对应的实体的 名称，如课程名称，班级名称...
        private String assTypeName; //收入或支出名称
        private String assIntOrOut; //收入或支出
        private String assDate; //接收表单传来的日期，以便进行转换到FORM的日期中.
        private FuserAccountDetailModel detailModel = null; //明细Model
        private FuserAccountModel mainModel = null; //总帐户Model

        public UserAccountDetailForm()
        {
        }

        /**
         * 在给用户帐户添加资金或扣除资金时必需使用的数据
         *
         * @param userID
         * @param uaDetailUserName
         * @param uaDetailInOutType
         * @param uaDetailTypeID       在扣除时必需，添加时可以给0
         * @param uaDetailAmount
         * @param uaDetailDate
         * @param uaDetailOperatorID
         * @param uaDetailOperatorName
         * @param uaDetailEntityValue  在扣除时必需，添加时可以给0
         */
        public UserAccountDetailForm(int userID, String uaDetailUserName,
                                     int uaDetailInOutType, int uaDetailTypeID, double uaDetailAmount,
                                     Date uaDetailDate, int uaDetailOperatorID, String uaDetailOperatorName,
                                     int uaDetailEntityValue, int aspId, int orgId)
        {
                this.userID = userID;
                this.uaDetailUserName = uaDetailUserName;
                this.uaDetailInOutType = uaDetailInOutType;
                this.uaDetailTypeID = uaDetailTypeID;
                this.uaDetailAmount = uaDetailAmount;
                this.uaDetailDate = uaDetailDate;
                this.uaDetailOperatorID = uaDetailOperatorID;
                this.uaDetailOperatorName = uaDetailOperatorName;
                this.uaDetailEntityValue = uaDetailEntityValue;
                this.aspId = aspId;
                this.orgId = orgId;
        }

        public FuserAccountModel getFuserAccountModel()
        {
                mainModel = new FuserAccountModel();
                mainModel.setUacotDescription("");
                mainModel.setUacotId(0);
                mainModel.setUacotlastUpDate(this.uaDetailDate);
                mainModel.setUacotLastUser(this.uaDetailOperatorName);
                mainModel.setUacotRemark1("");
                mainModel.setUacotRemark2("");
                mainModel.setUacotStatus(1);
                mainModel.setUacotTotal(this.uaDetailAmount);
                mainModel.setUacotUserName(this.uaDetailUserName);
                mainModel.setUserId(this.userID);

                return mainModel;
        }

        public UserAccountDetailForm getUserAccountDetailForm(
                FuserAccountDetailModel model)
        {
                UserAccountDetailForm form = new UserAccountDetailForm();
                form.setUaDetailAmount(model.getUadetailAmount());
                form.setUaDetailAudit(model.getUadetailAudit());
                form.setUaDetailDate(model.getUadetailDate());
                form.setUaDetailEntityValue(model.getUadetailEntityValue());
                form.setUaDetailID(model.getUadetailId());
                form.setUaDetailInOutType(model.getUadetailInOutType());
                form.setUaDetailInvoice(model.getUadetailInvoice());
                form.setUaDetailMemo(model.getUadetailMemo());
                form.setUaDetailOperatorID(model.getUadetailOperatorId());
                form.setUaDetailOperatorName(model.getUadetailOperatorName());
                form.setUaDetailRemark1(model.getUadetailRemark1());
                form.setUaDetailRemark2(model.getUadetailRemark2());
                form.setUaDetailRemitType(model.getUadetailRemitType());
                form.setUaDetailTypeID(model.getUadetailTypeId());
                form.setUaDetailUserName(model.getUadetailUserName());
                form.setUserID(model.getUserId());
                form.setAspId(model.getAspId());
                form.setOrgId(model.getOrgId());

                return form;
        }

        /**
         * Make Model
         *
         * @return Class for FuserAccountDetailModel
         */
        public FuserAccountDetailModel getDetailModel()
        {
                detailModel = new FuserAccountDetailModel();
                detailModel.setUadetailAmount(this.uaDetailAmount);
                detailModel.setUadetailAudit(this.uaDetailAudit);
                detailModel.setUadetailDate(this.uaDetailDate);
                detailModel.setUadetailEntityValue(this.uaDetailEntityValue);
                detailModel.setUadetailId(this.uaDetailID);
                detailModel.setUadetailInOutType(this.uaDetailInOutType);
                detailModel.setUadetailInvoice(this.uaDetailInvoice);
                detailModel.setUadetailMemo(this.uaDetailMemo);
                detailModel.setUadetailOperatorId(this.uaDetailOperatorID);
                detailModel.setUadetailOperatorName(this.uaDetailOperatorName);
                detailModel.setUadetailRemark1(this.uaDetailRemark1);
                detailModel.setUadetailRemark2(this.uaDetailRemark2);
                detailModel.setUadetailRemitType(this.uaDetailRemitType);
                detailModel.setUadetailTypeId(this.uaDetailTypeID);
                detailModel.setUadetailUserName(this.uaDetailUserName);
                detailModel.setUserId(this.userID);
                detailModel.setAspId(this.aspId);

                return detailModel;
        }

        public String getAssDate()
        {
                return assDate;
        }

        public void setAssDate(String assDate)
        {
                this.assDate = assDate;
        }

        public String getAssIntOrOut()
        {
                return assIntOrOut;
        }

        public void setAssIntOrOut(String assIntOrOut)
        {
                this.assIntOrOut = assIntOrOut;
        }

        public String getAssTypeName()
        {
                return assTypeName;
        }

        public void setAssTypeName(String assTypeName)
        {
                this.assTypeName = assTypeName;
        }

        public String getAssEntityName()
        {
                return assEntityName;
        }

        public void setAssEntityName(String assEntityName)
        {
                this.assEntityName = assEntityName;
        }

        public int getUaDetailID()
        {
                return uaDetailID;
        }

        public void setUaDetailID(int uaDetailID)
        {
                this.uaDetailID = uaDetailID;
        }

        public int getUserID()
        {
                return userID;
        }

        public void setUserID(int userID)
        {
                this.userID = userID;
        }

        public String getUaDetailUserName()
        {
                return uaDetailUserName;
        }

        public void setUaDetailUserName(String uaDetailUserName)
        {
                this.uaDetailUserName = uaDetailUserName;
        }

        public int getUaDetailInOutType()
        {
                return uaDetailInOutType;
        }

        public void setUaDetailInOutType(int uaDetailInOutType)
        {
                this.uaDetailInOutType = uaDetailInOutType;
        }

        public int getUaDetailTypeID()
        {
                return uaDetailTypeID;
        }

        public void setUaDetailTypeID(int uaDetailTypeID)
        {
                this.uaDetailTypeID = uaDetailTypeID;
        }

        public double getUaDetailAmount()
        {
                return uaDetailAmount;
        }

        public void setUaDetailAmount(double uaDetailAmount)
        {
                this.uaDetailAmount = uaDetailAmount;
        }

        public Date getUaDetailDate()
        {
                return uaDetailDate;
        }

        public void setUaDetailDate(Date uaDetailDate)
        {
                this.uaDetailDate = uaDetailDate;
        }

        public int getUaDetailOperatorID()
        {
                return uaDetailOperatorID;
        }

        public void setUaDetailOperatorID(int uaDetailOperatorID)
        {
                this.uaDetailOperatorID = uaDetailOperatorID;
        }

        public String getUaDetailOperatorName()
        {
                return uaDetailOperatorName;
        }

        public void setUaDetailOperatorName(String uaDetailOperatorName)
        {
                this.uaDetailOperatorName = uaDetailOperatorName;
        }

        public int getUaDetailEntityValue()
        {
                return uaDetailEntityValue;
        }

        public void setUaDetailEntityValue(int uaDetailEntityValue)
        {
                this.uaDetailEntityValue = uaDetailEntityValue;
        }

        public int getUaDetailRemitType()
        {
                return uaDetailRemitType;
        }

        public void setUaDetailRemitType(int uaDetailRemitType)
        {
                this.uaDetailRemitType = uaDetailRemitType;
        }

        public int getUaDetailInvoice()
        {
                return uaDetailInvoice;
        }

        public void setUaDetailInvoice(int uaDetailInvoice)
        {
                this.uaDetailInvoice = uaDetailInvoice;
        }

        public int getUaDetailAudit()
        {
                return uaDetailAudit;
        }

        public void setUaDetailAudit(int uaDetailAudit)
        {
                this.uaDetailAudit = uaDetailAudit;
        }

        public String getUaDetailMemo()
        {
                return uaDetailMemo;
        }

        public void setUaDetailMemo(String uaDetailMemo)
        {
                this.uaDetailMemo = uaDetailMemo;
        }

        public String getUaDetailRemark1()
        {
                return uaDetailRemark1;
        }

        public void setUaDetailRemark1(String uaDetailRemark1)
        {
                this.uaDetailRemark1 = uaDetailRemark1;
        }

        public String getUaDetailRemark2()
        {
                return uaDetailRemark2;
        }

        public void setUaDetailRemark2(String uaDetailRemark2)
        {
                this.uaDetailRemark2 = uaDetailRemark2;
        }

        public int getAspId()
        {
                return aspId;
        }

        public void setAspId(int aspId)
        {
                this.aspId = aspId;
        }

        public int getOrgId()
        {
                return orgId;
        }

        public void setOrgId(int orgId)
        {
                this.orgId = orgId;
        }
}
