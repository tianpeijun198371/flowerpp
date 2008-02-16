/**
 * Copyright (c)2006 Beijing WenhuaOnline Sci-tech DevelopmentCo.,Ltd.
 * All rights reserved.
 *
 * User: fengch
 * Date: 2007-9-21 11:46:22
 */
package com.ulearning.ulms.scorm.model;

import java.util.Date;


public class NewScormScoesTrack
{
        Integer id;
        Integer userId;
        Integer scormId;
        Integer scoId;
        Integer attempt;
        String element;
        String value;
        Date createDate;
        Date lastUpdateDate;

        public NewScormScoesTrack()
        {
                this.attempt = new Integer(0);
        }

        public NewScormScoesTrack(Integer id, Integer userId, Integer scormId,
                                  Integer scoId, Integer attempt, String element, String value,
                                  Date createDate, Date lastUpdateDate)
        {
                this.id = id;
                this.userId = userId;
                this.scormId = scormId;
                this.scoId = scoId;
                this.attempt = attempt;
                this.element = element;
                this.value = value;
                this.createDate = createDate;
                this.lastUpdateDate = lastUpdateDate;
        }

        public Integer getId()
        {
                return id;
        }

        public void setId(Integer id)
        {
                this.id = id;
        }

        public Integer getUserId()
        {
                return userId;
        }

        public void setUserId(Integer userId)
        {
                this.userId = userId;
        }

        public Integer getScormId()
        {
                return scormId;
        }

        public void setScormId(Integer scormId)
        {
                this.scormId = scormId;
        }

        public Integer getScoId()
        {
                return scoId;
        }

        public void setScoId(Integer scoId)
        {
                this.scoId = scoId;
        }

        public Integer getAttempt()
        {
                return attempt;
        }

        public void setAttempt(Integer attempt)
        {
                this.attempt = attempt;
        }

        public String getElement()
        {
                return element;
        }

        public void setElement(String element)
        {
                this.element = element;
        }

        public String getValue()
        {
                return value;
        }

        public void setValue(String value)
        {
                this.value = value;
        }

        public Date getCreateDate()
        {
                return createDate;
        }

        public void setCreateDate(Date createDate)
        {
                this.createDate = createDate;
        }

        public Date getLastUpdateDate()
        {
                return lastUpdateDate;
        }

        public void setLastUpdateDate(Date lastUpdateDate)
        {
                this.lastUpdateDate = lastUpdateDate;
        }
}
