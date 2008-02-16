/**
 * CertCourseTreeNode.java.
 * User: keyh  Date: 2004-9-7
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.util.treeControl;

import java.util.List;
import java.io.Serializable;

public class TreeNode
{
        int ID;
        int layer;
        int parentID;
        int type; //1:course;0:cert
        boolean expanded;//1:expanded;0:closed
        boolean leaf;//true:cert&course;false:directory
        String description;

        public TreeNode()
        {
        }

        public TreeNode(int ID, int layer, int parentID, int type, boolean expanded, boolean leaf, String description)
        {
                this.ID = ID;
                this.layer = layer;
                this.parentID = parentID;
                this.type = type;
                this.expanded = expanded;
                this.leaf = leaf;
                this.description = description;
        }

        public int getID()
        {
                return ID;
        }

        public void setID(int ID)
        {
                this.ID = ID;
        }

        public int getLayer()
        {
                return layer;
        }

        public void setLayer(int layer)
        {
                this.layer = layer;
        }

        public int getParentID()
        {
                return parentID;
        }

        public void setParentID(int parentID)
        {
                this.parentID = parentID;
        }

        public int getType()
        {
                return type;
        }

        public void setType(int type)
        {
                this.type = type;
        }

        public boolean isExpanded()
        {
                return expanded;
        }

        public void setExpanded(boolean expanded)
        {
                this.expanded = expanded;
        }

        public boolean isLeaf()
        {
                return leaf;
        }

        public void setLeaf(boolean leaf)
        {
                this.leaf = leaf;
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

