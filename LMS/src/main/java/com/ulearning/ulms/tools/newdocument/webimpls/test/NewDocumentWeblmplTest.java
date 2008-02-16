/**
 * NewDocumentWeblmplTest.java.
 * User: shid Date: 2005-12-12 11:05:33
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.newdocument.webimpls.test;

import junit.framework.*;
import com.ulearning.ulms.tools.newdocument.webimpls.NewDocumentWeblmpl;
import com.ulearning.ulms.tools.newdocument.model.NewDocumentModel;

import java.util.List;
import java.util.ArrayList;


/**
 * @see com.ulearning.ulms.tools.newdocument.webimpls.NewDocumentWeblmpl
 */
public class NewDocumentWeblmplTest extends TestCase
{
        NewDocumentWeblmpl newDocumentWeblmpl = new NewDocumentWeblmpl();

        /**
         * Sets up the fixture, for example, open a network connection.
         * This method is called before a test is executed.
         */
        protected void setUp() throws Exception
        {
                //super.setUp();
                //com.ulearning.ulms.log.LogUtilTest.initLog();
                //todo please create a object for NewDocumentWeblmpl here
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
         * @see NewDocumentWeblmpl#search(int,int,List,String)
         */
        public void testSearch() throws Exception
        {
                int aspID = 0;
                int relationID = 0;
                List type = new ArrayList();
                type.add("11");
                type.add("12");
                String key = "¹ú";
                List result = newDocumentWeblmpl.search(aspID, relationID, type, key);
                for (int i = 0; i < result.size(); i++)
                {
                        NewDocumentModel o = (NewDocumentModel) result.get(i);
                        System.out.println("o.getDocName() = " + o.getDocName());

                }
        }

        /**
         * @throws Exception
         * @see NewDocumentWeblmpl#getDocument(int)
         */
        public void testGetDocument() throws Exception
        {
                int document = 162;
                NewDocumentModel result = newDocumentWeblmpl.getDocument(document);
        }
}