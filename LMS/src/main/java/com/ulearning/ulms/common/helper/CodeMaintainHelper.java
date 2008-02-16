/**
 * CodeMaintainHelper.java.
 * User: yuhj  Date: 2004-9-21
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.common.helper;

import com.ulearning.ulms.common.dao.CodeMaintainDAO;
import com.ulearning.ulms.common.dao.CodeMaintainDAOFactory;
import com.ulearning.ulms.common.form.UpdatePeizForm;
import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.util.log.LogUtil;

import java.util.ArrayList;
import java.util.List;


public class CodeMaintainHelper
{
        private static CodeMaintainDAO dao;

        static
        {
                try
                {
                        dao = CodeMaintainDAOFactory.getDAO();
                }
                catch (Exception e)
                {
                        e.printStackTrace();
                }
        }

        public List getCodeInfo(int type, int aspID)
        {
                try
                {
                        return dao.getCodeInfo(type, aspID);
                }
                catch (ULMSSysException e)
                {
                        LogUtil.debug("CodeMaintainHelper",
                                "[CodeMaintainHelper] ==========[getCodeInfo] " +
                                        e.getMessage());
                }

                return new ArrayList();
        }

        /**
         * @param aspID
         * @return
         */
        public List getCodes(int aspID)
        {
                List list = new ArrayList();

                try
                {
                        list = dao.getCodes(aspID);
                }
                catch (ULMSSysException e)
                {
                        LogUtil.debug("CodeMaintainHelper",
                                "[CodeMaintainHelper]=======[getCodes]" + e.getMessage());
                }

                return list;
        }

        public static UpdatePeizForm getCodeItemByID(int id)
        {
                UpdatePeizForm uf = null;

                try
                {
                        uf = dao.getCodeItemByID(id);
                }
                catch (ULMSSysException e)
                {
                        LogUtil.debug("CodeMaintainHelper",
                                "[CodeMaintainHelper]=======[getCodes]" + e.getMessage());
                }

                return uf;
        }
}
