/**
 * Created by IntelliJ IDEA.
 * User: zengwj
 * Date: 2004-8-20
 * Time: 10:11:48
 * To change this template use File | Settings | File Templates.
 */
package com.ulearning.ulms.admin.colsignup.colsigndetail.form;

import com.ulearning.ulms.admin.colsignup.colsigndetail.model.ColSignDetailModel;

import org.apache.struts.action.ActionForm;


public class ColSignDetailForm extends ActionForm
{
        private int ColSignDetailID = 0;
        private int ColSignID = 0;
        private int RelationID = 0;
        private String RelationName;
        private int typeID = 0;
        private String Desc1;
        private String typName = "";

        public ColSignDetailForm()
        {
        }

        public ColSignDetailForm(int colSignDetailID, int colSignID,
                                 int relationID, int typeID, String desc1)
        {
                ColSignDetailID = colSignDetailID;
                ColSignID = colSignID;
                RelationID = relationID;
                this.typeID = typeID;
                Desc1 = desc1;
        }

        public ColSignDetailForm(ColSignDetailModel cdm)
        {
                this.ColSignDetailID = cdm.getColSignDetailID();
                this.ColSignID = cdm.getColSignID();
                this.RelationID = cdm.getRelationID();
                this.typeID = cdm.getTypeID();
                this.Desc1 = cdm.getDesc1();
        }

        public int getColSignDetailID()
        {
                return ColSignDetailID;
        }

        public void setColSignDetailID(int colSignDetailID)
        {
                ColSignDetailID = colSignDetailID;
        }

        public int getColSignID()
        {
                return ColSignID;
        }

        public void setColSignID(int colSignID)
        {
                ColSignID = colSignID;
        }

        public int getRelationID()
        {
                return RelationID;
        }

        public void setRelationID(int relationID)
        {
                RelationID = relationID;
        }

        public String getRelationName()
        {
                return RelationName;
        }

        public void setRelationName(String relationName)
        {
                RelationName = relationName;
        }

        public int getTypeID()
        {
                return typeID;
        }

        public void setTypeID(int typeID)
        {
                this.typeID = typeID;
        }

        public String getDesc1()
        {
                return Desc1;
        }

        public void setDesc1(String desc1)
        {
                Desc1 = desc1;
        }

        public String getTypName()
        {
                return typName;
        }

        public void setTypName(String typName)
        {
                this.typName = typName;
        }

        public ColSignDetailModel getColSignDetailModel()
        {
                ColSignDetailModel cdm = new ColSignDetailModel();
                cdm.setColSignDetailID(this.ColSignDetailID);
                cdm.setColSignID(this.ColSignID);
                cdm.setRelationID(this.RelationID);
                cdm.setTypeID(this.typeID);
                cdm.setDesc1(this.Desc1);

                return cdm;
        }
}
