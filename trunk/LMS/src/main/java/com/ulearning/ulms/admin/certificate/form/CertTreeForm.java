/**
 * CertTreeForm.java.
 * User: huangsb  Date: 2004-5-20
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd. * All rights reserved.
 */
package com.ulearning.ulms.admin.certificate.form;

import com.ulearning.ulms.course.model.CatalogListModel;


public class CertTreeForm
{
        private CertListForm certList;
        private CatalogListModel catalogList;

        public CertTreeForm()
        {
        }

        public CertTreeForm(CertListForm certList, CatalogListModel catalogList)
        {
                this.certList = certList;
                this.catalogList = catalogList;
        }

        public CertListForm getMasterList()
        {
                return certList;
        }

        public void setMasterList(CertListForm masterList)
        {
                this.certList = masterList;
        }

        public CatalogListModel getCatalogList()
        {
                return catalogList;
        }

        public void setCatalogList(CatalogListModel catalogList)
        {
                this.catalogList = catalogList;
        }
}
