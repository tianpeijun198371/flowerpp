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
 * ����ȡҵ��ʵ������Frm,����ͨ��
 *
 * @author Liz
 * @ date2005-12-23
 */
public class FinanceStatType_Form extends ActionForm
{
        private int iCome; //���������Ǹ�JSP���룬����ת��ͬ��JSP

        public int getiCome()
        {
                return iCome;
        }

        public void setiCome(int iCome)
        {
                this.iCome = iCome;
        }
}
