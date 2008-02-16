/**
 * CourseContentForm.java.
 * User: dengj  Date: 2004-4-30
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.course.model;

import com.ulearning.ulms.tools.upload.model.UploadForm;

import org.apache.struts.action.ActionForm;

import java.util.Date;


public class CourseContentForm extends UploadForm
{
        private int nodeID = 0;
        private String nodeTitle = null;
        private int relationID = 0;
        private int type = 1;
        private int nodetype = 1;
        private int isUserful = 1;
        private int isOpenGuest = 0;
        private int isContent = 0;
        private String link = null;
        private int isHyperLink = 0;
        private int isView = 0;
        private int depth = 0;
        private int parentID = 0;
        private Date createDate = new java.sql.Date(System.currentTimeMillis());
        private Date lastModDate = new java.sql.Date(System.currentTimeMillis());
        private int orderIndex = 0;
        private String catalog = null;

        public CourseContentForm()
        {
        }

        public CourseContentForm(CourseContentModel ccm)
        {
                if (ccm != null)
                {
                        this.nodeID = ccm.getNodeid();
                        this.nodeTitle = ccm.getNodetitle();
                        this.relationID = ccm.getRelationid();
                        this.type = Integer.parseInt(ccm.getType());
                        this.nodetype = Integer.parseInt(ccm.getNodetype());
                        this.isUserful = Integer.parseInt(ccm.getIsuserful());
                        this.isOpenGuest = Integer.parseInt(ccm.getIsopenguest());
                        this.isContent = Integer.parseInt(ccm.getIscontent());
                        this.link = ccm.getLink();
                        this.isHyperLink = Integer.parseInt(ccm.getIshyperlink());
                        this.isView = Integer.parseInt(ccm.getIsview());
                        this.depth = ccm.getDepth();
                        this.parentID = ccm.getParentid();
                        this.createDate = ccm.getCreatedate();
                        this.lastModDate = ccm.getLastmoddate();
                        this.orderIndex = ccm.getOrderindex();
                        this.catalog = ccm.getCatalog();
                }
        }

        public int getNodeID()
        {
                return nodeID;
        }

        public void setNodeID(int nodeID)
        {
                this.nodeID = nodeID;
        }

        public int getNodetype()
        {
                return nodetype;
        }

        public void setNodetype(int nodetype)
        {
                this.nodetype = nodetype;
        }

        public String getNodeTitle()
        {
                return nodeTitle;
        }

        public void setNodeTitle(String nodeTitle)
        {
                this.nodeTitle = nodeTitle;
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

        public int getIsUserful()
        {
                return isUserful;
        }

        public void setIsUserful(int userful)
        {
                isUserful = userful;
        }

        public int getIsOpenGuest()
        {
                return isOpenGuest;
        }

        public void setIsOpenGuest(int openGuest)
        {
                isOpenGuest = openGuest;
        }

        public int getIsContent()
        {
                return isContent;
        }

        public void setIsContent(int content)
        {
                isContent = content;
        }

        public String getLink()
        {
                return link;
        }

        public void setLink(String link)
        {
                this.link = link;
        }

        public int getIsHyperLink()
        {
                return isHyperLink;
        }

        public void setIsHyperLink(int hyperLink)
        {
                isHyperLink = hyperLink;
        }

        public int getIsView()
        {
                return isView;
        }

        public void setIsView(int view)
        {
                isView = view;
        }

        public int getDepth()
        {
                return depth;
        }

        public void setDepth(int depth)
        {
                this.depth = depth;
        }

        public int getParentID()
        {
                return parentID;
        }

        public void setParentID(int parentID)
        {
                this.parentID = parentID;
        }

        public Date getCreateDate()
        {
                return createDate;
        }

        public void setCreateDate(Date createDate)
        {
                this.createDate = createDate;
        }

        public Date getLastModDate()
        {
                return lastModDate;
        }

        public void setLastModDate(Date lastModDate)
        {
                this.lastModDate = lastModDate;
        }

        public int getOrderIndex()
        {
                return orderIndex;
        }

        public void setOrderIndex(int orderIndex)
        {
                this.orderIndex = orderIndex;
        }

        public String getCatalog()
        {
                return catalog;
        }

        public void setCatalog(String catalog)
        {
                this.catalog = catalog;
        }

        public CourseContentModel getCourseContentModel()
        {
                CourseContentModel ccm = new CourseContentModel();
                ccm.setNodeid(this.nodeID);
                ccm.setNodetitle(this.nodeTitle);
                ccm.setRelationid(this.relationID);
                ccm.setType(new Integer(this.type).toString());
                ccm.setNodetype(new Integer(this.nodetype).toString());
                ccm.setIsuserful(new Integer(this.isUserful).toString());
                ccm.setIsopenguest(new Integer(this.isOpenGuest).toString());
                ccm.setIscontent(new Integer(this.isContent).toString());
                ccm.setLink(this.link);
                ccm.setIshyperlink(new Integer(this.isHyperLink).toString());
                ccm.setIsview(new Integer(this.isView).toString());
                ccm.setDepth(this.depth);
                ccm.setParentid(this.parentID);
                ccm.setCreatedate(this.createDate);
                ccm.setLastmoddate(this.lastModDate);
                ccm.setOrderindex(this.orderIndex);
                ccm.setCatalog(this.catalog);

                return ccm;
        }
}
