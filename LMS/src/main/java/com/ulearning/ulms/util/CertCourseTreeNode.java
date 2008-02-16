/**
 * CertCourseTreeNode.java.
 * User: keyh  Date: 2004-9-7
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.util;

public class CertCourseTreeNode
{
        int ID;
        int layer;
        int parent;
        int type;
        int state;
        boolean isLeaf;

        public CertCourseTreeNode()
        {

        }

        public CertCourseTreeNode(int ID, int layer, int parent, int type, int state, boolean leaf)
        {
                this.ID = ID;
                this.layer = layer;
                this.parent = parent;
                this.type = type;
                this.state = state;
                isLeaf = leaf;
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

        public int getParent()
        {
                return parent;
        }

        public void setParent(int parent)
        {
                this.parent = parent;
        }

        public int getType()
        {
                return type;
        }

        public void setType(int type)
        {
                this.type = type;
        }

        public int getState()
        {
                return state;
        }

        public void setState(int state)
        {
                this.state = state;
        }

        public boolean isLeaf()
        {
                return isLeaf;
        }

        public void setLeaf(boolean leaf)
        {
                isLeaf = leaf;
        }

}

