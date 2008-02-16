/**
 * Created by IntelliJ IDEA.
 * User: zengwj
 * Date: 2004-8-20
 * Time: 10:11:48
 * To change this template use File | Settings | File Templates.
 */
package com.ulearning.ulms.admin.colsignup.colstudent.form;

import com.ulearning.ulms.admin.colsignup.colstudent.model.ColStudentModel;
import com.ulearning.ulms.admin.colsignup.colstudent.model.ColStudentModelPK;

import org.apache.struts.action.ActionForm;


public class ColStudentForm extends ActionForm
{
        private int ColSignDetailID = 0;
        private int RelationID = 0;
        private int Type = 0;
        private String Approved;
        private String feeState;
        private String Sex = "";
        private String Email = "";
        private String OrgName = "";
        private String LogInName = "";
        private String Name = "";
        private String Phone = "";

        public ColStudentForm()
        {
        }

        public ColStudentForm(ColStudentModel csm)
        {
                this.ColSignDetailID = ((ColStudentModelPK) csm.getComp_id()).getColSignDetailID();
                this.RelationID = ((ColStudentModelPK) csm.getComp_id()).getRelationID();
                this.Type = ((ColStudentModelPK) csm.getComp_id()).getType();
                this.Approved = csm.getApproved();
                this.feeState = csm.getFeeState();
        }

        public String getFeeState()
        {
                return feeState;
        }

        public void setFeeState(String feeState)
        {
                this.feeState = feeState;
        }

        public int getColSignDetailID()
        {
                return ColSignDetailID;
        }

        public void setColSignDetailID(int colSignDetailID)
        {
                ColSignDetailID = colSignDetailID;
        }

        public int getRelationID()
        {
                return RelationID;
        }

        public void setRelationID(int relationID)
        {
                RelationID = relationID;
        }

        public int getType()
        {
                return Type;
        }

        public void setType(int type)
        {
                Type = type;
        }

        public String getApproved()
        {
                return Approved;
        }

        public void setApproved(String approved)
        {
                Approved = approved;
        }

        public String getSex()
        {
                return Sex;
        }

        public void setSex(String sex)
        {
                this.Sex = sex;
        }

        public String getEmail()
        {
                return Email;
        }

        public void setEmail(String email)
        {
                this.Email = email;
        }

        public String getOrgName()
        {
                return OrgName;
        }

        public void setOrgName(String orgName)
        {
                this.OrgName = orgName;
        }

        public String getLogInName()
        {
                return LogInName;
        }

        public void setLogInName(String logInName)
        {
                this.LogInName = logInName;
        }

        public String getName()
        {
                return Name;
        }

        public void setName(String name)
        {
                Name = name;
        }

        public String getPhone()
        {
                return Phone;
        }

        public void setPhone(String phone)
        {
                Phone = phone;
        }

        public ColStudentModel getColStudentModel()
        {
                ColStudentModel csm = new ColStudentModel();
                ColStudentModelPK pk = new ColStudentModelPK(ColSignDetailID,
                        RelationID, Type);
                csm.setComp_id(pk);
                csm.setApproved(Approved);
                csm.setFeeState(feeState);

                return csm;
        }
}
