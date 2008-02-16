/**
 * NewDocumentTreeModel.java.
 * User: Administrator  Date: 2005-3-14
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.newdocument.form;

public class NewDocumentTreeModel
{
        private NewDocumentCatalogForm newDocumentCatalog;
        private NewDocumentForm newDocumentForm;

        public NewDocumentTreeModel()
        {
        }

        public NewDocumentTreeModel(NewDocumentCatalogForm newDocumentCatalog, NewDocumentForm newDocumentForm)
        {
                this.newDocumentCatalog = newDocumentCatalog;
                this.newDocumentForm = newDocumentForm;
        }

        public NewDocumentCatalogForm getNewDocumentCatalog()
        {
                return newDocumentCatalog;
        }

        public void setNewDocumentCatalog(NewDocumentCatalogForm newDocumentCatalog)
        {
                this.newDocumentCatalog = newDocumentCatalog;
        }

        public NewDocumentForm getNewDocumentForm()
        {
                return newDocumentForm;
        }

        public void setNewDocumentForm(NewDocumentForm newDocumentForm)
        {
                this.newDocumentForm = newDocumentForm;
        }
}
