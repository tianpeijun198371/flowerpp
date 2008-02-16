/**
 * ContentManageDAOImplTest.java.
 * User: shid Date: 2005-12-20 17:14:28
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.content.dao.test;

import com.ulearning.ulms.content.dao.ContentManageDAOImpl;
import com.ulearning.ulms.content.model.ContentConfigModel;
import com.ulearning.ulms.content.model.ContentModel;

import junit.framework.*;

import java.util.List;


/**
 * @see com.ulearning.ulms.content.dao.ContentManageDAOImpl
 */
public class ContentManageDAOImplTest extends TestCase
{
        ContentManageDAOImpl contentManageDAOImpl = new ContentManageDAOImpl();

        /**
         * Sets up the fixture, for example, open a network connection.
         * This method is called before a test is executed.
         */
        protected void setUp() throws Exception
        {
                //super.setUp();
                //com.ulearning.ulms.log.LogUtilTest.initLog();
                //todo please create a object for ContentManageDAOImpl here
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
         * @see ContentManageDAOImpl#getContentConfig(String,String)
         */
        public void testGetContentConfig() throws Exception
        {
                String type = "0";
                String relationID = "1";
                ContentConfigModel result = contentManageDAOImpl.getContentConfig(type,
                        relationID);
        }

        /**
         * @throws Exception
         * @see ContentManageDAOImpl#getContentCatalog(int,int,int,String)
         */
        public void testGetContentCatalog() throws Exception
        {
                int parentID = 0;
                int relationID = 0;
                int type = 0;
                String aspID = "0";
                List result = contentManageDAOImpl.getContentCatalog(parentID,
                        relationID, type, aspID);
        }

        /**
         * @throws Exception
         * @see ContentManageDAOImpl#addContent(com.ulearning.ulms.content.model.ContentModel)
         */
        public void testAddContent() throws Exception
        {
                ContentModel cm = new ContentModel();
                cm.setTitle("s");
                cm.setParentID(0);
                cm.setRelationID(0);
                cm.setType("0");
                cm.setIdentifier("1");
                cm.setLanguageID(1);
                cm.setLocation("3");
                cm.setContentTypeID(1);
                contentManageDAOImpl.addContent(cm);
        }
}
