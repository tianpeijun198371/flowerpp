/**
 * ReportDAO.java.
 * User: liz  Date: 2006-4-29
 * ���񱨱�ĳ���
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.report.general.dao;

import com.ulearning.ulms.core.exceptions.ULMSException;

import java.util.List;

public interface ReportDAO
{
        //��ѯ
        public List getList(String hql) throws ULMSException;

        public void addData(Object obj) throws ULMSException;

        public void updateData(Object ojb) throws ULMSException;

        public void removeData(String hql) throws ULMSException;
}
