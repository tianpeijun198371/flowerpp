/**
 * FunctionConfigForm.java.
 * User: Fengch  Date: 2005-4-1 10:45:46
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.portal.fuctionconfig.form;

import com.ulearning.ulms.core.util.StringUtil;
import com.ulearning.ulms.portal.fuctionconfig.model.FunctionConfigModel;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;


public class FunctionConfigForm extends ActionForm
{
        private int fuctionID;
        private String name;
        private String description;
        private String isAvailable;
        private Date beginDate;
        private Date endDate;
        private String type;
        private String relationID;
        private Date modifyDate;

        public FunctionConfigModel getFunctionConfigModel()
        {
                FunctionConfigModel functionConfigModel = new FunctionConfigModel();
                functionConfigModel.setFuctionID(this.fuctionID);
                functionConfigModel.setName(this.name);
                functionConfigModel.setDescription(this.description);
                functionConfigModel.setIsAvailable(this.isAvailable);
                functionConfigModel.setBeginDate(this.beginDate);
                functionConfigModel.setEndDate(this.endDate);
                functionConfigModel.setRelationID(this.relationID);
                functionConfigModel.setType(this.type);
                functionConfigModel.setModifyDate(this.modifyDate);

                return functionConfigModel;
        }

        public FunctionConfigForm getFunctionConfigForm(
                FunctionConfigModel functionConfigModel)
        {
                FunctionConfigForm functionConfigForm = new FunctionConfigForm();
                functionConfigForm.setFuctionID(functionConfigForm.getFuctionID());
                functionConfigForm.setName(functionConfigForm.getName());
                functionConfigForm.setDescription(functionConfigForm.getDescription());
                functionConfigForm.setIsAvailable(functionConfigForm.getIsAvailable());
                functionConfigForm.setBeginDate(functionConfigForm.getBeginDate());
                functionConfigForm.setEndDate(functionConfigForm.getEndDate());
                functionConfigForm.setRelationID(functionConfigForm.getRelationID());
                functionConfigForm.setType(functionConfigForm.getType());
                functionConfigForm.setModifyDate(functionConfigForm.getModifyDate());

                return functionConfigForm;
        }

        public int getFuctionID()
        {
                return fuctionID;
        }

        public void setFuctionID(int fuctionID)
        {
                this.fuctionID = fuctionID;
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

        public String getIsAvailable()
        {
                return isAvailable;
        }

        public void setIsAvailable(String isAvailable)
        {
                this.isAvailable = isAvailable;
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

        public String getType()
        {
                return type;
        }

        public void setType(String type)
        {
                this.type = type;
        }

        public String getRelationID()
        {
                return relationID;
        }

        public void setRelationID(String relationID)
        {
                this.relationID = relationID;
        }

        public Date getModifyDate()
        {
                return modifyDate;
        }

        public void setModifyDate(Date modifyDate)
        {
                this.modifyDate = modifyDate;
        }

        public ActionErrors validate(ActionMapping mapping,
                                     HttpServletRequest request)
        {
                ActionErrors errors = new ActionErrors();

                if ((isAvailable == null) || (isAvailable.length() < 1))
                {
                        errors.add("isAvailable",
                                new ActionError("error.functionconfig.isAvailable.required"));
                }

                return errors;
        }
}
