/**
 * FinanceStatForm.java.
 * User: liz  Date: 2005-12-23
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.finance.form;

import org.apache.struts.action.ActionForm;


/**
 * 经费模块统计类后返回的Frm类
 *
 * @author liz
 * @ date 2005-12-23
 */
public class FinanceStatForm extends ActionForm
{
        private int entityid; //ID
        private String sname; //业务实体的名称
        private String description; //描述
        private int typeId; //类型

        public int getTypeId()
        {
                return typeId;
        }

        public void setTypeId(int typeId)
        {
                this.typeId = typeId;
        }

        public int getEntityid()
        {
                return entityid;
        }

        public void setEntityid(int entityid)
        {
                this.entityid = entityid;
        }

        public String getSname()
        {
                return sname;
        }

        public void setSname(String sname)
        {
                this.sname = sname;
        }

        public String getDescription()
        {
                return description;
        }

        public void setDescription(String description)
        {
                this.description = description;
        }
}
