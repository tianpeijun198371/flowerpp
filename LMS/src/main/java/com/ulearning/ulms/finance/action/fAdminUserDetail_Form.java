/**
 * fAdminUserDetail_Form.java.
 * User: liz  Date: 2005-12-19
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.finance.action;

import org.apache.struts.action.ActionForm;


/**
 * 管理员查询个人用户，条件form
 *
 * @author Liz
 * @ date 2005-12-19
 */
public class fAdminUserDetail_Form extends ActionForm
{
        private String userName; //用户名称

        public String getUserName()
        {
                return userName;
        }

        public void setUserName(String userName)
        {
                this.userName = userName;
        }
}
