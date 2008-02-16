//Source file: d:\\ulms\\source\\com\\eduedu\\ulms\\course\\model\\MasterModel.java
package com.ulearning.ulms.course.model;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import java.io.Serializable;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;


public class MasterModel extends ActionForm implements Serializable
{
        private int masterID;
        private String masterCode = "";
        private String name = "";
        private String description = "";
        private int orgID;
        private int aspID;
        private int type;
        private String key = "";
        private int creator;
        private Date establishDate;
        private Date modifyDate;
        private String plan = "";
        private int catalogID;
        private int objectID;
        private float period;
        private float credit;
        private int Status;
        private String operator = "";

        public MasterModel(int masterID, String name, String description)
        {
                this.masterID = masterID;
                this.name = name;
                this.description = description;
        }

        public MasterModel(int masterID, String name, String description,
                           String key, String masterCode)
        {
                this.masterID = masterID;
                this.name = name;
                this.description = description;
                this.key = key;
                this.masterCode = masterCode;
        }

        public MasterModel(int masterID, int type, String name, String description,
                           String key, String masterCode)
        {
                this.masterID = masterID;
                this.type = type;
                this.name = name;
                this.description = description;
                this.key = key;
                this.masterCode = masterCode;
        }

        public MasterModel(Master mm)
        {
                if (mm != null)
                {
                        this.masterID = mm.getMasterID();
                        this.masterCode = mm.getMasterCode();
                        this.name = mm.getName();
                        this.description = mm.getDescription();
                        this.orgID = mm.getOrgID();
                        this.aspID = mm.getAspID();
                        this.type = Integer.parseInt(mm.getType());
                        this.key = mm.getKey();
                        this.creator = mm.getCreator();
                        this.establishDate = mm.getEstablishDate();
                        this.modifyDate = mm.getModifyDate();
                        this.plan = mm.getPlan();
                        this.catalogID = mm.getCatalogID();
                        this.objectID = mm.getObjectID();
                        this.period = mm.getPeriod();
                        this.credit = mm.getCredit();
                        this.Status = Integer.parseInt(mm.getStatus());
                        this.operator = mm.getOperator();
                }
        }

        public MasterModel()
        {
        }

        public MasterModel(int masterID, String name, String masterCode,
                           String description)
        {
                this.masterID = masterID;
                this.name = name;
                this.masterCode = masterCode;
                this.description = description;
        }

        public int getMasterID()
        {
                return masterID;
        }

        public void setMasterID(int masterID)
        {
                this.masterID = masterID;
        }

        public String getMasterCode()
        {
                return masterCode;
        }

        public void setMasterCode(String masterCode)
        {
                this.masterCode = masterCode;
        }

        public String getName()
        {
                return name;
        }

        public void setName(String name)
        {
                this.name = name;
        }

        public String getDescription()
        {
                return description;
        }

        public void setDescription(String description)
        {
                this.description = description;
        }

        public int getOrgID()
        {
                return orgID;
        }

        public void setOrgID(int orgID)
        {
                this.orgID = orgID;
        }

        public int getAspID()
        {
                return aspID;
        }

        public void setAspID(int aspID)
        {
                this.aspID = aspID;
        }

        public int getType()
        {
                return type;
        }

        public void setType(int type)
        {
                this.type = type;
        }

        public String getKey()
        {
                return key;
        }

        public void setKey(String key)
        {
                this.key = key;
        }

        public int getCreator()
        {
                return creator;
        }

        public void setCreator(int creator)
        {
                this.creator = creator;
        }

        public Date getEstablishDate()
        {
                return establishDate;
        }

        public void setEstablishDate(Date establishDate)
        {
                this.establishDate = establishDate;
        }

        public Date getModifyDate()
        {
                return modifyDate;
        }

        public void setModifyDate(Date modifyDate)
        {
                this.modifyDate = modifyDate;
        }

        public String getPlan()
        {
                return plan;
        }

        public void setPlan(String plan)
        {
                this.plan = plan;
        }

        public int getCatalogID()
        {
                return catalogID;
        }

        public void setCatalogID(int catalogID)
        {
                this.catalogID = catalogID;
        }

        public int getObjectID()
        {
                return objectID;
        }

        public void setObjectID(int objectID)
        {
                this.objectID = objectID;
        }

        public float getPeriod()
        {
                return period;
        }

        public void setPeriod(float period)
        {
                this.period = period;
        }

        public float getCredit()
        {
                return credit;
        }

        public void setCredit(float credit)
        {
                this.credit = credit;
        }

        public int getStatus()
        {
                return Status;
        }

        public void setStatus(int status)
        {
                Status = status;
        }

        public String getOperator()
        {
                return operator;
        }

        public void setOperator(String operator)
        {
                this.operator = operator;
        }

        /**
         * Validate the properties that have been set from this HTTP request,
         * and return an <code>ActionErrors</code> object that encapsulates any
         * validation errors that have been found.  If no errors are found, return
         * <code>null</code> or an <code>ActionErrors</code> object with no
         * recorded error messages.
         *
         * @param mapping The mapping used to select this instance
         * @param request The servlet request we are processing
         */
        public ActionErrors validate(ActionMapping mapping,
                                     HttpServletRequest request)
        {
                ActionErrors errors = new ActionErrors();

                if ((name == null) || (name.length() < 1))
                {
                        errors.add("name", new ActionError("error.master.name.required"));
                }

                return (errors);
        }

        public Master getMaster()
        {
                Master mm = new Master();
                mm.setMasterID(this.masterID);
                mm.setMasterCode(this.masterCode);
                mm.setName(this.name);
                mm.setDescription(this.description);
                mm.setOrgID(this.orgID);
                mm.setAspID(this.aspID);
                mm.setType(String.valueOf(this.type));
                mm.setKey(this.key);
                mm.setCreator(this.creator);
                mm.setEstablishDate(this.establishDate);
                mm.setModifyDate(this.modifyDate);
                mm.setPlan(this.plan);
                mm.setCatalogID(this.catalogID);
                mm.setObjectID(this.objectID);
                mm.setPeriod(this.period);
                mm.setCredit(this.credit);
                mm.setStatus(String.valueOf(this.Status));
                mm.setOperator(this.operator);

                return mm;
        }
}
