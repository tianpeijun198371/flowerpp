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
 * ����ҵ��ʵ���Ӧ���շ������ϸForm
 *
 * @author Liz
 * @ date 2005.12.26
 */
public class FinanceStatResultForm extends ActionForm
{
        private int entityID; //ҵ��ʵ��ID
        private String entityName; //ҵ��ʵ������
        private int userID; //�û�ID
        private String userName; //�û�����
        private Date outlayDate; //��������
        private double outlaySum; //���ý��

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
