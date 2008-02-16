/**
 * VideoScopeDAOFactory.java.
 * User: liaoxingxing  Date: 2007-1-5 10:33:46
 *
 * Copyright (c) 2000-2004.Beijing Huixue Times Tech Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.video.dao;

import com.ulearning.ulms.video.exceptions.VideoScopeDAOSysException;

public class VideoScopeDAOFactory
{
        public static VideoScopeDAO getDAO() throws VideoScopeDAOSysException
        {
                VideoScopeDAO dao = null;
                try
                {
                        dao = new VideoScopeDAOImpl();
                }
                catch (Exception se)
                {
                        throw new VideoScopeDAOSysException("VideoScopeDAOFactory.getDAO:  Exception while getting DAO type : \n" + se.getMessage());
                }

                return dao;

        }
}
