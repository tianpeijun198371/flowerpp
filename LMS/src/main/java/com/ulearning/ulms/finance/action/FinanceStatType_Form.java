/**
 * FinanceStatType_Form.java.
 * User: liz  Date: 2005-12-23
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.finance.action;

import org.apache.struts.action.ActionForm;


/**
 * 经费取业务实体类型Frm,可以通用
 *
 * @author Liz
 * @ date2005-12-23
 */
public class FinanceStatType_Form extends ActionForm
{
        private int iCome; //区分是由那个JSP进入，用来转向不同的JSP

        public int getiCome()
        {
                return iCome;
        }

        public void setiCome(int iCome)
        {
                this.iCome = iCome;
        }
}
