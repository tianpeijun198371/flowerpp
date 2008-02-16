/**
 * Created by IntelliJ IDEA.
 * Delete: dengj
 * Date: Apr 7, 2004
 * Time: 4:51:49 PM
 * To change this template use Options | File Templates.
 */
package com.ulearning.ulms.tools.delete.form;

import com.ulearning.ulms.tools.delete.model.DeleteModel;

import org.apache.struts.action.ActionForm;

import java.util.Date;


public class DeleteForm extends ActionForm
{
        private int deleteID = 0;
        private int relationID = 0;
        private String relationType = null;
        private String state = null;
        private String objectType = null;
        private int saveTimeNum = 0;
        private String timeType = null;
        private int saveRows = 0;
        private int userID = 0;
        private Date updateDate = null;

        public DeleteModel getDeleteModel()
        {
                DeleteModel deleteModel = new DeleteModel();
                deleteModel.setDeleteID(this.deleteID);
                deleteModel.setRelationID(this.relationID);
                deleteModel.setRelationType(this.relationType);
                deleteModel.setState(this.state);
                deleteModel.setObjectType(this.objectType);
                deleteModel.setSaveTimeNum(this.saveTimeNum);
                deleteModel.setTimeType(this.timeType);
                deleteModel.setSaveRows(this.saveRows);
                deleteModel.setUpdateDate(this.updateDate);

                return deleteModel;
        }

        public DeleteForm getDeleteForm(DeleteModel deleteModel)
        {
                DeleteForm deleteForm = new DeleteForm();
                deleteForm.setDeleteID(deleteModel.getDeleteID());
                deleteForm.setRelationID(deleteModel.getRelationID());
                deleteForm.setRelationType(deleteModel.getRelationType());
                deleteForm.setState(deleteModel.getState());
                deleteForm.setObjectType(deleteModel.getObjectType());
                deleteForm.setSaveTimeNum(deleteModel.getSaveTimeNum());
                deleteForm.setTimeType(deleteModel.getTimeType());
                deleteForm.setSaveRows(deleteModel.getSaveRows());
                deleteForm.setUpdateDate(deleteModel.getUpdateDate());

                return deleteForm;
        }

        public int getDeleteID()
        {
                return deleteID;
        }

        public void setDeleteID(int deleteID)
        {
                this.deleteID = deleteID;
        }

        public int getRelationID()
        {
                return relationID;
        }

        public void setRelationID(int relationID)
        {
                this.relationID = relationID;
        }

        public String getRelationType()
        {
                return relationType;
        }

        public void setRelationType(String relationType)
        {
                this.relationType = relationType;
        }

        public String getState()
        {
                return state;
        }

        public void setState(String state)
        {
                this.state = state;
        }

        public String getObjectType()
        {
                return objectType;
        }

        public void setObjectType(String objectType)
        {
                this.objectType = objectType;
        }

        public int getSaveTimeNum()
        {
                return saveTimeNum;
        }

        public void setSaveTimeNum(int saveTimeNum)
        {
                this.saveTimeNum = saveTimeNum;
        }

        public String getTimeType()
        {
                return timeType;
        }

        public void setTimeType(String timeType)
        {
                this.timeType = timeType;
        }

        public int getSaveRows()
        {
                return saveRows;
        }

        public void setSaveRows(int saveRows)
        {
                this.saveRows = saveRows;
        }

        public int getUserID()
        {
                return userID;
        }

        public void setUserID(int userID)
        {
                this.userID = userID;
        }

        public Date getUpdateDate()
        {
                return updateDate;
        }

        public void setUpdateDate(Date updateDate)
        {
                this.updateDate = updateDate;
        }
}
