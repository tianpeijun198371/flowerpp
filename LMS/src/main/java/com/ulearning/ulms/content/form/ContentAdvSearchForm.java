/**
 * ContentAdvSearchForm.java.
 * User: shid Date: 2005-8-9 10:18:16
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.content.form;

import org.apache.struts.action.ActionForm;

import java.util.Date;
import java.util.List;


public class ContentAdvSearchForm extends ActionForm
{
        private int remark3;
        private int parentID;
        private int relationID;
        private int type;
        private boolean isAdmin;
        private int scopeType;
        private int location1;
        private int location2;
        private int location3;
        private int location4;
        private int location5;
        private int match1;
        private int match2;
        private int match3;
        private int match4;
        private int match5;
        private String matchContent1;
        private String matchContent2;
        private String matchContent3;
        private String matchContent4;
        private String matchContent5;
        private int aor1;
        private int aor2;
        private int aor3;
        private int aor4;
        private int languageID;
        private int contentTypeID;
        private int datebutton;
        private int selectdate;
        private String beginDate;
        private String endDate;
        private int pageSize;
        private int pageNo;
        private int pageCount;
        private int totalCount;
        private List contentModels;
        private String sort;
        private String order;
        private Date startDate;
        private Date overDate;
        private String hql;
        private String remark1;
        private String coursecode;
        private String courseContentTypeID;

        public ContentAdvSearchForm()
        {
        }

        public int getParentID()
        {
                return parentID;
        }

        public void setParentID(int parentID)
        {
                this.parentID = parentID;
        }

        public boolean isAdmin()
        {
                return isAdmin;
        }

        public void setAdmin(boolean admin)
        {
                isAdmin = admin;
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

        public int getScopeType()
        {
                return scopeType;
        }

        public void setScopeType(int scopeType)
        {
                this.scopeType = scopeType;
        }

        public int getLocation1()
        {
                return location1;
        }

        public void setLocation1(int location1)
        {
                this.location1 = location1;
        }

        public int getLocation2()
        {
                return location2;
        }

        public void setLocation2(int location2)
        {
                this.location2 = location2;
        }

        public int getLocation3()
        {
                return location3;
        }

        public void setLocation3(int location3)
        {
                this.location3 = location3;
        }

        public int getLocation4()
        {
                return location4;
        }

        public void setLocation4(int location4)
        {
                this.location4 = location4;
        }

        public int getLocation5()
        {
                return location5;
        }

        public void setLocation5(int location5)
        {
                this.location5 = location5;
        }

        public int getMatch1()
        {
                return match1;
        }

        public void setMatch1(int match1)
        {
                this.match1 = match1;
        }

        public int getMatch2()
        {
                return match2;
        }

        public void setMatch2(int match2)
        {
                this.match2 = match2;
        }

        public int getMatch3()
        {
                return match3;
        }

        public void setMatch3(int match3)
        {
                this.match3 = match3;
        }

        public int getMatch4()
        {
                return match4;
        }

        public void setMatch4(int match4)
        {
                this.match4 = match4;
        }

        public int getMatch5()
        {
                return match5;
        }

        public void setMatch5(int match5)
        {
                this.match5 = match5;
        }

        public String getMatchContent1()
        {
                return matchContent1;
        }

        public void setMatchContent1(String matchContent1)
        {
                this.matchContent1 = matchContent1;
        }

        public String getMatchContent2()
        {
                return matchContent2;
        }

        public void setMatchContent2(String matchContent2)
        {
                this.matchContent2 = matchContent2;
        }

        public String getMatchContent3()
        {
                return matchContent3;
        }

        public void setMatchContent3(String matchContent3)
        {
                this.matchContent3 = matchContent3;
        }

        public String getMatchContent4()
        {
                return matchContent4;
        }

        public void setMatchContent4(String matchContent4)
        {
                this.matchContent4 = matchContent4;
        }

        public String getMatchContent5()
        {
                return matchContent5;
        }

        public void setMatchContent5(String matchContent5)
        {
                this.matchContent5 = matchContent5;
        }

        public int getAor1()
        {
                return aor1;
        }

        public void setAor1(int aor1)
        {
                this.aor1 = aor1;
        }

        public int getAor2()
        {
                return aor2;
        }

        public void setAor2(int aor2)
        {
                this.aor2 = aor2;
        }

        public int getAor3()
        {
                return aor3;
        }

        public void setAor3(int aor3)
        {
                this.aor3 = aor3;
        }

        public int getAor4()
        {
                return aor4;
        }

        public void setAor4(int aor4)
        {
                this.aor4 = aor4;
        }

        public int getLanguageID()
        {
                return languageID;
        }

        public void setLanguageID(int languageID)
        {
                this.languageID = languageID;
        }

        public int getContentTypeID()
        {
                return contentTypeID;
        }

        public void setContentTypeID(int contentTypeID)
        {
                this.contentTypeID = contentTypeID;
        }

        public int getDatebutton()
        {
                return datebutton;
        }

        public void setDatebutton(int datebutton)
        {
                this.datebutton = datebutton;
        }

        public int getSelectdate()
        {
                return selectdate;
        }

        public void setSelectdate(int selectdate)
        {
                this.selectdate = selectdate;
        }

        public String getBeginDate()
        {
                return beginDate;
        }

        public void setBeginDate(String beginDate)
        {
                this.beginDate = beginDate;
        }

        public String getEndDate()
        {
                return endDate;
        }

        public void setEndDate(String endDate)
        {
                this.endDate = endDate;
        }

        public int getPageSize()
        {
                return pageSize;
        }

        public void setPageSize(int pageSize)
        {
                this.pageSize = pageSize;
        }

        public int getPageNo()
        {
                return pageNo;
        }

        public void setPageNo(int pageNo)
        {
                this.pageNo = pageNo;
        }

        public int getPageCount()
        {
                return pageCount;
        }

        public void setPageCount(int pageCount)
        {
                this.pageCount = pageCount;
        }

        public List getContentModels()
        {
                return contentModels;
        }

        public void setContentModels(List contentModels)
        {
                this.contentModels = contentModels;
        }

        public int getTotalCount()
        {
                return totalCount;
        }

        public void setTotalCount(int totalCount)
        {
                this.totalCount = totalCount;
        }

        public String getSort()
        {
                return sort;
        }

        public void setSort(String sort)
        {
                this.sort = sort;
        }

        public String getOrder()
        {
                return order;
        }

        public void setOrder(String order)
        {
                this.order = order;
        }

        public Date getStartDate()
        {
                return startDate;
        }

        public void setStartDate(Date startDate)
        {
                this.startDate = startDate;
        }

        public Date getOverDate()
        {
                return overDate;
        }

        public void setOverDate(Date overDate)
        {
                this.overDate = overDate;
        }

        public String getHql()
        {
                return hql;
        }

        public void setHql(String hql)
        {
                this.hql = hql;
        }

        public String getRemark1()
        {
                return remark1;
        }

        public void setRemark1(String remark1)
        {
                this.remark1 = remark1;
        }

        public String getCoursecode()
        {
                return coursecode;
        }

        public void setCoursecode(String coursecode)
        {
                this.coursecode = coursecode;
        }

        public int getRemark3()
        {
                return remark3;
        }

        public void setRemark3(int remark3)
        {
                this.remark3 = remark3;
        }

        public String getCourseContentTypeID()
        {
                return courseContentTypeID;
        }

        public void setCourseContentTypeID(String courseContentTypeID)
        {
                this.courseContentTypeID = courseContentTypeID;
        }
}
