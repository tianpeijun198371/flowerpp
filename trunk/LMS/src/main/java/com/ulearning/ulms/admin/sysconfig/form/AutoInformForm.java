package com.ulearning.ulms.admin.sysconfig.form;

import com.ulearning.ulms.admin.sysconfig.model.AutoInformModel;
import com.ulearning.ulms.admin.sysconfig.model.AutoInformModelPK;
import com.ulearning.ulms.core.util.StringUtil;

import org.apache.struts.action.ActionForm;


public class AutoInformForm extends ActionForm
{
        private int orgID = 0;
        private String name = null;
        private int type = 0;
        private String isMail = "1";
        private String isMSG = "1";
        private String isTip = "0";
        private String description = null;

        public AutoInformForm()
        {
        }

        public AutoInformForm(AutoInformModel aim)
        {
                this.orgID = aim.getComp_id().getOrgid();
                this.type = aim.getComp_id().getType();
                this.name = StringUtil.nullToStr(aim.getName());
                this.isMail = aim.getIsmail();
                this.isMSG = aim.getIsmsg();
                this.isTip = aim.getIstip();
                this.description = StringUtil.nullToStr(aim.getDescription());
        }

        public AutoInformModel toModel()
        {
                AutoInformModel aim = new AutoInformModel();
                aim.setComp_id(new AutoInformModelPK(this.orgID, this.type));
                aim.setName(this.name);
                aim.setIsmail(this.isMail);
                aim.setIsmsg(this.isMSG);
                aim.setIstip(this.isTip);
                aim.setDescription(this.description);

                return aim;
        }

        public String getIsMail()
        {
                return isMail;
        }

        public void setIsMail(String isMail)
        {
                this.isMail = isMail;
        }

        public String getIsMSG()
        {
                return isMSG;
        }

        public void setIsMSG(String isMSG)
        {
                this.isMSG = isMSG;
        }

        public String getIsTip()
        {
                return isTip;
        }

        public void setIsTip(String isTip)
        {
                this.isTip = isTip;
        }

        public int getOrgID()
        {
                return orgID;
        }

        public void setOrgID(int orgID)
        {
                this.orgID = orgID;
        }

        public String getName()
        {
                return name;
        }

        public void setName(String name)
        {
                this.name = name;
        }

        public int getType()
        {
                return type;
        }

        public void setType(int type)
        {
                this.type = type;
        }

        public String getDescription()
        {
                return description;
        }

        public void setDescription(String description)
        {
                this.description = description;
        }
}
