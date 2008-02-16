/**
 * Created by IntelliJ IDEA.
 * User: zengwj
 * Date: 2004-7-2
 * Time: 9:38:32
 * To change this template use File | Settings | File Templates.
 */
package com.ulearning.ulms.admin.webconfig.webcustom.form;

import com.ulearning.ulms.admin.webconfig.webcustom.model.WebCustomModel;

import org.apache.struts.action.ActionForm;

import java.util.Date;


public class WebCustomForm extends ActionForm
{
        private int CustomID = 0;
        private int RelationID = 0;
        private String RelationType = "0";
        private String ConfigTypeName = null;
        private Date UpdateDate = null;
        private String Description = null;
        private WebCustomModel webCustomModel = null;

        public WebCustomModel getWebCustomModel()
        {
                webCustomModel = new WebCustomModel();
                webCustomModel.setCustomID(this.CustomID);
                webCustomModel.setRelationID(this.RelationID);
                webCustomModel.setRelationType(this.RelationType);
                webCustomModel.setConfigTypeName(this.ConfigTypeName);
                webCustomModel.setUpdateDate(this.UpdateDate);
                webCustomModel.setDescription(this.Description);

                return webCustomModel;
        }

        public WebCustomForm getWebCustomForm(WebCustomModel webCustomModel)
        {
                WebCustomForm webCustomForm = new WebCustomForm();
                webCustomForm.setCustomID(webCustomModel.getCustomID());
                webCustomForm.setRelationID(webCustomModel.getRelationID());
                webCustomForm.setRelationType(webCustomModel.getRelationType());
                webCustomForm.setConfigTypeName(webCustomModel.getConfigTypeName());
                webCustomForm.setUpdateDate(webCustomModel.getUpdateDate());
                webCustomForm.setDescription(webCustomModel.getDescription());

                return webCustomForm;
        }

        public int getCustomID()
        {
                return CustomID;
        }

        public void setCustomID(int customID)
        {
                CustomID = customID;
        }

        public int getRelationID()
        {
                return RelationID;
        }

        public void setRelationID(int relationID)
        {
                RelationID = relationID;
        }

        public String getRelationType()
        {
                return RelationType;
        }

        public void setRelationType(String relationType)
        {
                RelationType = relationType;
        }

        public String getConfigTypeName()
        {
                return ConfigTypeName;
        }

        public void setConfigTypeName(String configTypeName)
        {
                ConfigTypeName = configTypeName;
        }

        public Date getUpdateDate()
        {
                return UpdateDate;
        }

        public void setUpdateDate(Date updateDate)
        {
                UpdateDate = updateDate;
        }

        public String getDescription()
        {
                return Description;
        }

        public void setDescription(String description)
        {
                Description = description;
        }
}
