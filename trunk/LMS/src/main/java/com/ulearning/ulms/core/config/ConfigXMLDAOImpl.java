/**
 * ConfigXMLDAOImpl.java.
 * User: fengch  Date: 2004-5-13
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.core.config;

import com.ulearning.ulms.core.util.Constants;
import com.ulearning.ulms.core.util.XMLUtil;
import com.ulearning.ulms.util.log.LogUtil;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;

import java.net.URL;

import java.util.HashMap;
import java.util.Map;


public class ConfigXMLDAOImpl implements ConfigDAO
{
        public HashMap loadulmsConfigMappings()
        {
                String location = Constants.ulmsCONFIG_FILE_Key;

                return loadMappings(location);
        }

        public HashMap loadSYSConfigMappings()
        {
                String location = Constants.SYSCONFIG_FILE_Key;

                return loadMappings(location);
        }

        /**
         * Loads properties from XML nodes
         */
        public HashMap loadMappings(String location)
        {
                String key = null;
                String value = null;
                Element root = XMLUtil.loadDocument(location);
                HashMap mappings = new HashMap();

                try
                {
                        NodeList list = root.getChildNodes();

                        for (int loop = 0; loop < list.getLength(); loop++)
                        {
                                Node node = list.item(loop);

                                if (node.getNodeType() == Node.ELEMENT_NODE)
                                {
                                        if (node != null)
                                        {
                                                key = node.getNodeName();
                                                value = XMLUtil.getTagValue(root, node.getNodeName());
                                                mappings.put(key, value);
                                        }
                                }
                        }
                }
                catch (Exception e)
                {
                        LogUtil.debug("core", "[ConfigXMLDAOImpl] Begin ******************");
                        e.printStackTrace();
                        LogUtil.debug("core", "[ConfigXMLDAOImpl] End ******************");
                }

                return mappings;
        }
}
