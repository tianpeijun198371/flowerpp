/**
 * NewDocumentDAOImplTest.java.
 * User: shid Date: 2005-12-8 9:51:38
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.newdocument.dao.test;

import junit.framework.*;
import com.ulearning.ulms.tools.newdocument.dao.NewDocumentDAOImpl;
import com.ulearning.ulms.tools.newdocument.model.NewDocumentModel;

import java.util.List;
import java.util.ArrayList;


/**
 * @see com.ulearning.ulms.tools.newdocument.dao.NewDocumentDAOImpl
 */
public class NewDocumentDAOImplTest extends TestCase
{
        NewDocumentDAOImpl newDocumentDAOImpl = new NewDocumentDAOImpl();

        /**
         * Sets up the fixture, for example, open a network connection.
         * This method is called before a test is executed.
         */
        protected void setUp() throws Exception
        {
                //super.setUp();
                //com.ulearning.ulms.log.LogUtilTest.initLog();
                //todo please create a object for NewDocumentDAOImpl here
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
         * @see NewDocumentDAOImpl#getDocument(int,int,java.util.List,int)
         */
        public void testGetDocument() throws Exception
        {
                int aspID = 0;
                int relationID = 0;
                List type = new ArrayList();
                type.add("1");
                type.add("100");
                type.add("11");
                type.add("12");
                type.add("13");
                type.add("14");
                int parentID = 0;
                List result = newDocumentDAOImpl.getDocument(aspID, relationID, type, parentID);
                for (int i = 0; i < result.size() && i < 5; i++)
                {
                        NewDocumentModel mdm = (NewDocumentModel) result.get(i);
                        System.out.println("mdm.getContentClobString() = " + mdm.getContentClobString());
                }
        }

        /**
         * @throws Exception
         * @see NewDocumentDAOImpl#search(int,int,List,String)
         */
        public void testSearch() throws Exception
        {
                int aspID = 0;
                int relationID = 0;
                String key = "a";

                List type = new ArrayList();
                type.add("1");
                type.add("100");
                type.add("11");
                type.add("12");
                type.add("13");
                type.add("14");
                int parentID = 0;
                List result = newDocumentDAOImpl.search(aspID, relationID, type, key);
                for (int i = 0; i < result.size() && i < 5; i++)
                {
                        NewDocumentModel mdm = (NewDocumentModel) result.get(i);
                        System.out.println("mdm.getContentClobString() = " + mdm.getContentClobString());
                }
        }

        /**
         * @throws Exception
         * @see NewDocumentDAOImpl#insertDocument(NewDocumentModel)
         */
        public void testInsertDocument() throws Exception
        {
                NewDocumentModel m = new NewDocumentModel();
                m.setParentID(0);
                m.setRelationID(0);
                m.setAspID(0);
                m.setType("0");
                //m.setContentClob("aaaaaaaaaaaaaaaa");
                newDocumentDAOImpl.insertDocument(m);
        }
}