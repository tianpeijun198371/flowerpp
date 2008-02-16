/**
 * CatalogListModel.java.
 * User: fengch  Date: 2004-4-27
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.course.model;

import java.io.Serializable;

import java.util.ArrayList;


public class CatalogListModel implements Serializable
{
        private ArrayList list;

        public CatalogListModel()
        {
        }

        public CatalogListModel(ArrayList list)
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
