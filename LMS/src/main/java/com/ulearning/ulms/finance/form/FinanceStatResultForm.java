/**
 * FinanceStatResultForm.java.
 * User: liz  Date: 2005-12-26
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.finance.form;

import org.apache.struts.action.ActionForm;

import java.util.Date;


/**
 * 具体业务实体对应的收费情况明细Form
 *
 * @author Liz
 * @ date 2005.12.26
 */
public class FinanceStatResultForm extends ActionForm
{
        private int entityID; //业务实体ID
        private String entityName; //业务实体名称
        private int userID; //用户ID
        private String userName; //用户名称
        private Date outlayDate; //费用日期
        private double outlaySum; //费用金额

        public int getEntityID()
        {
                return entityID;
        }

        public void setEntityID(int entityID)
        {
                this.entityID = entityID;
        }

        public String getEntityName()
        {
                return entityName;
        }

        public void setEntityName(String entityName)
        {
                this.entityName = entityName;
        }

        public int getUserID()
        {
                return userID;
        }

        public void setUserID(int userID)
        {
                this.userID = userID;
        }

        public String getUserName()
        {
                return userName;
        }

        public void setUserName(String userName)
        {
                this.userName = userName;
        }

        public Date getOutlayDate()
        {
                return outlayDate;
        }

        public void setOutlayDate(Date outlayDate)
        {
                this.outlayDate = outlayDate;
        }

        public double getOutlaySum()
        {
                return outlaySum;
        }

        public void setOutlaySum(double outlaySum)
        {
                this.outlaySum = outlaySum;
        }
}
