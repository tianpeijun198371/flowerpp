package com.ulearning.ulms.course.model;

import java.io.Serializable;


/**
 * Created by IntelliJ IDEA.
 * User: ff
 * Date: 2004-4-27
 * Time: 23:34:56
 * To change this template use File | Settings | File Templates.
 */
public class MasterTreeModel implements Serializable
{
        private MasterListModel masterList;
        private CatalogListModel catalogList;

        public MasterTreeModel()
        {
        }

        public MasterTreeModel(MasterListModel masterList,
                               CatalogListModel catalogList)
        {
                this.masterList = masterList;
                this.catalogList = catalogList;
        }

        public MasterListModel getMasterList()
        {
                return masterList;
        }

        public void setMasterList(MasterListModel masterList)
        {
                this.masterList = masterList;
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
