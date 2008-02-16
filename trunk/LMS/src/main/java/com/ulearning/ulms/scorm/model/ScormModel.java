/**
 * ScormModel.java.
 * User: fengch  Date: 2004-7-28
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.scorm.model;

import org.apache.struts.webapp.upload.UploadForm;


public class ScormModel extends UploadForm
{
        private String name;
        private int nodeID;
        private String nodeType;
        private String filePath;
        private int style;
        private int relationID;
        private int type;
        private int parentID;
        private int isUserful = 1;
        private int isOpenGuest = 0;
        private int isContent = 0;

        public ScormModel()
        {
        }

        public int getNodeID()
        {
                return nodeID;
        }

        public void setNodeID(int nodeID)
        {
                this.nodeID = nodeID;
        }

        public String getName()
        {
                return name;
        }

        public void setName(String name)
        {
                this.name = name;
        }

        public String getNodeType()
        {
                return nodeType;
        }

        public void setNodeType(String nodeType)
        {
                this.nodeType = nodeType;
        }

        public String getFilePath()
        {
                return filePath;
        }

        public void setFilePath(String filePath)
        {
                this.filePath = filePath;
        }

        public int getStyle()
        {
                return style;
        }

        public void setStyle(int style)
        {
                this.style = style;
        }

        public int getRelationID()
        {
                return relationID;
        }

        public void setRelationID(int relationID)
        {
                this.relationID = relationID;
        }

        public int getType()
        {
                return type;
        }

        public void setType(int type)
        {
                this.type = type;
        }

        public int getParentID()
        {
                return parentID;
        }

        public void setParentID(int parentID)
        {
                this.parentID = parentID;
        }

        public int getUserful()
        {
                return isUserful;
        }

        public void setUserful(int userful)
        {
                isUserful = userful;
        }

        public int getOpenGuest()
        {
                return isOpenGuest;
        }

        public void setOpenGuest(int openGuest)
        {
                isOpenGuest = openGuest;
        }

        public int getContent()
        {
                return isContent;
        }

        public void setContent(int content)
        {
                isContent = content;
        }
}
