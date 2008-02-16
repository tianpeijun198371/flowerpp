/**
 * ContentTreeDAOImplTest.java.
 * User: shid Date: 2005-11-29 15:27:39
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.content.dao.test;

import com.ulearning.ulms.content.dao.ContentTreeDAOImpl;
import com.ulearning.ulms.content.model.ContentTreeModel;
import com.ulearning.ulms.content.util.ContentManageConstants;

import junit.framework.*;


/**
 * @see com.ulearning.ulms.content.dao.ContentTreeDAOImpl
 */
public class ContentTreeDAOImplTest extends TestCase
{
        ContentTreeDAOImpl contentTreeDAOImpl = new ContentTreeDAOImpl();

        /**
         * Sets up the fixture, for example, open a network connection.
         * This method is called before a test is executed.
         */
        protected void setUp() throws Exception
        {
                //super.setUp();
                //com.ulearning.ulms.log.LogUtilTest.initLog();
                //todo please create a object for ContentTreeDAOImpl here
        }

        /**
         * Tears down the fixture, for example, close a network connection.
         * This method is called after a test is executed.
         */
        protected void tearDown() throws Exception
        {
                super.tearDown();
        }

        /**
         * @throws Exception
         * @see ContentTreeDAOImpl#sumSubCatalogContent(int,int,int,String)
         */
        public void testSumSubCatalogContent() throws Exception
        {
                int catalogID = 0;
                int relationID = 880;
                int type = ContentManageConstants.PERSONAL_CONTENT_TYPE;
                String aspID = "2";
                long result = contentTreeDAOImpl.sumSubCatalogContent(catalogID,
                        relationID, type, aspID);
                System.out.println("result = " + result);
        }

        /**
         * @throws Exception
         * @see ContentTreeDAOImpl#getTree(int,int,int,int,int,String,boolean)
         */
        public void testGetTree() throws Exception
        {
                int catalogID = 1;
                int relationID = 1;
                int type = 1;
                int pageNo = 0;
                int pageSize = 9999;
                String aspID = "1";
                boolean isAdmin = true;
                ContentTreeModel result = contentTreeDAOImpl.getTree(catalogID,
                        relationID, type, pageNo, pageSize, aspID, isAdmin);
        }
}
