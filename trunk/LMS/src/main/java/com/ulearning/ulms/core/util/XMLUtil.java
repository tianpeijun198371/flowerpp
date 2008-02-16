/**
 * XMLUtil.java.
 * User: fengch  Date: 2004-5-13
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.core.util;

import org.w3c.dom.*;

import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;


/**
 * Utility class for XML document.
 */
public class XMLUtil
{
        public static final String KEY = "key";
        public static final String VALUE = "value";
        public static final String NAME = "name";
        public static final String PARAMETER = "parameter";

        public static Element loadDocument(String location)
        {
                Document doc = null;

                try
                {
                        InputStream ins = Class.forName(
                                "com.ulearning.ulms.core.util.XMLUtil").getClassLoader()
                                .getResourceAsStream(location);
                        DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
                        DocumentBuilder parser = docBuilderFactory.newDocumentBuilder();
                        doc = parser.parse(ins);

                        Element root = doc.getDocumentElement();
                        root.normalize();

                        return root;
                }
                catch (SAXParseException err)
                {
                        System.err.println("[XMLUtil] ** Parsing error" + ", line " +
                                err.getLineNumber() + ", uri " + err.getSystemId());
                        err.printStackTrace();
                        System.err.println("[XMLUtil] SAXParseException error: " +
                                err.getMessage());
                }
                catch (SAXException e)
                {
                        System.err.println("[XMLUtil] SAXException error: " + e);
                        e.printStackTrace();
                }
                catch (java.net.MalformedURLException mfx)
                {
                        System.err.println("[XMLUtil] MalformedURLException error: " + mfx);
                        mfx.printStackTrace();
                }
                catch (java.io.IOException e)
                {
                        System.err.println("[XMLUtil] IOException error: " + e);
                        e.printStackTrace();
                }
                catch (Exception pce)
                {
                        System.err.println("[XMLUtil] error: " + pce);
                        pce.printStackTrace();
                }

                return null;
        }

        public static String getSubTagValue(Node node, String subTagName)
        {
                String returnString = "";

                if (node != null)
                {
                        NodeList children = node.getChildNodes();

                        for (int innerLoop = 0; innerLoop < children.getLength();
                             innerLoop++)
                        {
                                Node child = children.item(innerLoop);

                                if ((child != null) && (child.getNodeName() != null) &&
                                        child.getNodeName().equals(subTagName))
                                {
                                        Node grandChild = child.getFirstChild();

                                        if (grandChild.getNodeValue() != null)
                                        {
                                                return grandChild.getNodeValue();
                                        }
                                }
                        } // end inner loop
                }

                return returnString;
        }

        public static String getSubTagValue(Element root, String tagName,
                                            String subTagName)
        {
                String returnString = "";
                NodeList list = root.getElementsByTagName(tagName);

                for (int loop = 0; loop < list.getLength(); loop++)
                {
                        Node node = list.item(loop);

                        if (node != null)
                        {
                                NodeList children = node.getChildNodes();

                                for (int innerLoop = 0; innerLoop < children.getLength();
                                     innerLoop++)
                                {
                                        Node child = children.item(innerLoop);

                                        if ((child != null) && (child.getNodeName() != null) &&
                                                child.getNodeName().equals(subTagName))
                                        {
                                                Node grandChild = child.getFirstChild();

                                                if (grandChild.getNodeValue() != null)
                                                {
                                                        return grandChild.getNodeValue();
                                                }
                                        }
                                } // end inner loop
                        }
                }

                return returnString;
        }

        public static String getTagValue(Element root, String tagName)
        {
                String returnString = "";
                NodeList list = root.getElementsByTagName(tagName);

                for (int loop = 0; loop < list.getLength(); loop++)
                {
                        Node node = list.item(loop);

                        if (node != null)
                        {
                                Node child = node.getFirstChild();

                                if ((child != null) && (child.getNodeValue() != null))
                                {
                                        return child.getNodeValue();
                                }
                        }
                }

                return returnString;
        }
}
