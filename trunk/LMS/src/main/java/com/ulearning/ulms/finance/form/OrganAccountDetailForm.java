package com.ulearning.ulms.finance.form;

import com.ulearning.ulms.finance.model.ForganAccountDetailModel;
import com.ulearning.ulms.finance.model.ForganAccountModel;

import org.apache.struts.action.ActionForm;

import java.util.Date;


/**
 * Created by IntelliJ IDEA.
 * �����ʻ���ϸForm
 *
 * @author Liz
 * @ date  2005.12.08
 * @ struts.form name="OrganAccountDetailForm"  path="OrganAccountDetailForm"
 */
public class OrganAccountDetailForm extends ActionForm
{
        private int oaDetailID = 0; //ID
        private int orgID = 0; //����ID
        private String oaDetailOrganName; //��������
        private int oaDetailInOutType = 0; //��֧ģʽ:   1����;2��֧
        private int oaDetailTypeID = 0; //�������֧����
        private double oadetailAmount; // ���
        private Date oaDetailDate; //����ʱ��
        private int oaDetailOperatorID = 0; //����ԱID
        private String oaDetailOperatorName; //����Ա����
        private int oaDetailEntityValue = 0; //��֧����ʵ��ID��ֵ
        private int oaDetailRemitType = 0; //��ʽ
        private int oaDetailInvoice = 0; //��Ʊ��� 0��û��;1���ѿ�
        private int oaDetailAudit = 0; //�Ƿ���� 0��δ���  1�������
        private String oaDetailMemo; //��ע
        private String oaDetailRemark1; //����1
        private String oaDetailRemark2; //����2
        private int aspId; //aspid

        //������
        private String assIntOrOut; //��ϸ����1:����;2:֧��
        private String assTypeName; //�����֧������
        private String assEntityName; //��Ӧ���õ�ʵ������
        private String assDate; //���ձ����������ڣ��Ա����ת����FORM��������.
        private ForganAccountDetailModel detailModel;
        private ForganAccountModel mainModel = null; //���ʻ�Model

        public ForganAccountModel getForganAccountModel()
        {
                mainModel = new ForganAccountModel();
                mainModel.setOacotDescription("");
                mainModel.setOacotId(0);
                mainModel.setOacotLastUpdate(this.oaDetailDate);
                mainModel.setOacotLastUpUser(this.oaDetailOperatorName);
                mainModel.setOacotRemark1("");
                mainModel.setOacotRemark2("");
                mainModel.setOacotStatus(1);
                mainModel.setOacotTotal(this.oadetailAmount);
                mainModel.setOacotOrganName(this.oaDetailOrganName);
                mainModel.setOrgId(this.orgID);

                return mainModel;
        }

        public ForganAccountDetailModel getForganAccountDetailModel()
        {
                detailModel = new ForganAccountDetailModel();
                detailModel.setOadetailAmount(this.oadetailAmount);
                detailModel.setOadetailAudit(this.oaDetailAudit);
                detailModel.setOadetailDate(this.oaDetailDate);
                detailModel.setOadetailEntityValue(this.oaDetailEntityValue);
                detailModel.setOadetailId(this.oaDetailID);
                detailModel.setOadetailInOutType(this.oaDetailInOutType);
                detailModel.setOadetailInvoice(this.oaDetailInvoice);
                detailModel.setOadetailMemo(this.oaDetailMemo);
                detailModel.setOadetailOperatorId(this.oaDetailOperatorID);
                detailModel.setOadetailOperatorName(this.oaDetailOperatorName);
                detailModel.setOadetailRemark1(this.oaDetailRemark1);
                detailModel.setOadetailRemark2(this.oaDetailRemark2);
                detailModel.setOadetailRemitType(this.oaDetailRemitType);
                detailModel.setOadetailTypeId(this.oaDetailTypeID);
                detailModel.setOadetailOrganName(this.oaDetailOrganName);
                detailModel.setOrgId(this.orgID);
                detailModel.setAspId(this.aspId);

                return detailModel;
        }

        public OrganAccountDetailForm getOrganAccountDetailForm(
                ForganAccountDetailModel model)
        {
                OrganAccountDetailForm form = new OrganAccountDetailForm();
                form.setOadetailAmount(model.getOadetailAmount());
                form.setOaDetailAudit(model.getOadetailAudit());
                form.setOaDetailDate(model.getOadetailDate());
                form.setOaDetailEntityValue(model.getOadetailEntityValue());
                form.setOaDetailID(model.getOadetailId());
                form.setOaDetailInOutType(model.getOadetailInOutType());
                form.setOaDetailInvoice(model.getOadetailInvoice());
                form.setOaDetailMemo(model.getOadetailMemo());
                form.setOaDetailOperatorID(model.getOadetailOperatorId());
                form.setOaDetailOperatorName(model.getOadetailOperatorName());
                form.setOaDetailRemark1(model.getOadetailRemark1());
                form.setOaDetailRemark2(model.getOadetailRemark2());
                form.setOaDetailRemitType(model.getOadetailRemitType());
                form.setOaDetailTypeID(model.getOadetailTypeId());
                form.setOaDetailOrganName(model.getOadetailOrganName());
                form.setOrgID(model.getOrgId());
                form.setAspId(model.getAspId());

                return form;
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

        public int getOaDetailID()
        {
                return oaDetailID;
        }

        public void setOaDetailID(int oaDetailID)
        {
                this.oaDetailID = oaDetailID;
        }

        public int getOrgID()
        {
                return orgID;
        }

        public void setOrgID(int orgID)
        {
                this.orgID = orgID;
        }

        public String getOaDetailOrganName()
        {
                return oaDetailOrganName;
        }

        public void setOaDetailOrganName(String oaDetailOrganName)
        {
                this.oaDetailOrganName = oaDetailOrganName;
        }

        public int getOaDetailInOutType()
        {
                return oaDetailInOutType;
        }

        public void setOaDetailInOutType(int oaDetailInOutType)
        {
                this.oaDetailInOutType = oaDetailInOutType;
        }

        public int getOaDetailTypeID()
        {
                return oaDetailTypeID;
        }

        public void setOaDetailTypeID(int oaDetailTypeID)
        {
                this.oaDetailTypeID = oaDetailTypeID;
        }

        public double getOadetailAmount()
        {
                return oadetailAmount;
        }

        public void setOadetailAmount(double oadetailAmount)
        {
                this.oadetailAmount = oadetailAmount;
        }

        public Date getOaDetailDate()
        {
                return oaDetailDate;
        }

        public void setOaDetailDate(Date oaDetailDate)
        {
                this.oaDetailDate = oaDetailDate;
        }

        public int getOaDetailOperatorID()
        {
                return oaDetailOperatorID;
        }

        public void setOaDetailOperatorID(int oaDetailOperatorID)
        {
                this.oaDetailOperatorID = oaDetailOperatorID;
        }

        public String getOaDetailOperatorName()
        {
                return oaDetailOperatorName;
        }

        public void setOaDetailOperatorName(String oaDetailOperatorName)
        {
                this.oaDetailOperatorName = oaDetailOperatorName;
        }

        public int getOaDetailEntityValue()
        {
                return oaDetailEntityValue;
        }

        public void setOaDetailEntityValue(int oaDetailEntityValue)
        {
                this.oaDetailEntityValue = oaDetailEntityValue;
        }

        public int getOaDetailRemitType()
        {
                return oaDetailRemitType;
        }

        public void setOaDetailRemitType(int oaDetailRemitType)
        {
                this.oaDetailRemitType = oaDetailRemitType;
        }

        public int getOaDetailInvoice()
        {
                return oaDetailInvoice;
        }

        public void setOaDetailInvoice(int oaDetailInvoice)
        {
                this.oaDetailInvoice = oaDetailInvoice;
        }

        public int getOaDetailAudit()
        {
                return oaDetailAudit;
        }

        public void setOaDetailAudit(int oaDetailAudit)
        {
                this.oaDetailAudit = oaDetailAudit;
        }

        public String getOaDetailMemo()
        {
                return oaDetailMemo;
        }

        public void setOaDetailMemo(String oaDetailMemo)
        {
                this.oaDetailMemo = oaDetailMemo;
        }

        public String getOaDetailRemark1()
        {
                return oaDetailRemark1;
        }

        public void setOaDetailRemark1(String oaDetailRemark1)
        {
                this.oaDetailRemark1 = oaDetailRemark1;
        }

        public String getOaDetailRemark2()
        {
                return oaDetailRemark2;
        }

        public void setOaDetailRemark2(String oaDetailRemark2)
        {
                this.oaDetailRemark2 = oaDetailRemark2;
        }

        public int getAspId()
        {
                return aspId;
        }

        public void setAspId(int aspId)
        {
                this.aspId = aspId;
        }
}
