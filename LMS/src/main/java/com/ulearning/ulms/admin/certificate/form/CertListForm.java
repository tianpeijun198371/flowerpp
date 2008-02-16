/**
 * CertListForm.java.
 * User: huangsb  Date: 2004-5-20
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd. * All rights reserved.
 */
package com.ulearning.ulms.admin.certificate.form;

import java.io.Serializable;

import java.util.ArrayList;


public class CertListForm implements Serializable
{
        private ArrayList list;

        public CertListForm()
        {
        }

        public CertListForm(ArrayList list)
        {
                this.list = list;
        }

        public ArrayList getList()
        {
                return list;
        }

        public void setList(ArrayList list)
        {
                this.list = list;
        }
}
