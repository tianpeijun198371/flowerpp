/**
 * AnnouncementWebImpl.java.
 * User: fengch  Date: 2004-4-28
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.common.webimpls;

import com.ulearning.ulms.common.dao.CodeMaintainDAO;
import com.ulearning.ulms.common.dao.CodeMaintainDAOFactory;
import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.tools.announcement.dao.AnnouncementDAO;
import com.ulearning.ulms.tools.announcement.dao.AnnouncementDAOFactory;
import com.ulearning.ulms.tools.announcement.exceptions.AnnouncementAppException;
import com.ulearning.ulms.tools.announcement.exceptions.AnnouncementSysException;
import com.ulearning.ulms.tools.announcement.model.Announcement;
import com.ulearning.ulms.tools.announcement.model.AnnouncementList;

import java.io.File;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class CodeMaintainWeblmpl
{
        private CodeMaintainDAO dao;

        public CodeMaintainWeblmpl() throws ULMSSysException
        {
                try
                {
                        dao = CodeMaintainDAOFactory.getDAO();
                }
                catch (Exception ex)
                {
                        ex.printStackTrace();
                        throw new ULMSSysException(ex);
                }
        }

        /**
         * @param codeID
         * @throws ULMSSysException
         */
        public void delete(ArrayList codeID) throws ULMSSysException
        {
                dao.delete(codeID);
        }

        public long getFoldSize(String foldPath) throws ULMSSysException
        {
                long s = 0;
                File fp = new File(foldPath);

                if (fp.exists() && fp.isDirectory())
                {
                        File[] f = fp.listFiles();

                        for (int i = 0; i < f.length; i++)
                        {
                                if (f[i].isDirectory())
                                {
                                        File[] twoLevel = f[i].listFiles();

                                        for (int j = 0; j < twoLevel.length; j++)
                                        {
                                                s += twoLevel[j].length();
                                        }
                                }

                                s = s + f[i].length();
                        }
                }
                else
                {
                        if (fp.isFile())
                        {
                                fp.delete();
                        }

                        fp.mkdirs();
                }

                return s;
        }

        public static boolean deleteFileAndParentDiretion(String filePath)
        {
                boolean returnValue = false;

                if ((filePath != null) && !filePath.equals(""))
                {
                        File tempFile = new File(filePath);
                        File tempDir = new File(tempFile.getParent());

                        if (tempFile.exists() && !tempFile.isDirectory())
                        {
                                returnValue = tempFile.delete();
                        }

                        returnValue = tempDir.delete();
                }

                return returnValue; //hehe
        }
}
