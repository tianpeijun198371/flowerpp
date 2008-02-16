// Source File Name:   MetaDataTester.java
package com.ulearning.ulms.scorm.adl.testsuite.metadata;

import com.ulearning.ulms.scorm.adl.testsuite.metadata.rules.LomRules;
import com.ulearning.ulms.scorm.adl.util.debug.DebugIndicator;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import java.io.PrintStream;

// Referenced classes of package com.ulearning.ulms.scorm.adl.testsuite.metadata:

//            MetaDataDOMParser
public class MetaDataTester extends MetaDataDOMParser
{
        private String metadataType;
        private boolean optionalNotUsed;
        private String parseType;
        private Node xmlNode;

        public MetaDataTester(String s, String s1, String s2)
        {
                super(s1, s2);
                optionalNotUsed = true;

                if (com.ulearning.ulms.scorm.adl.util.debug.DebugIndicator.ON)
                {
                        System.out.println("/////////////////////////////////");
                        System.out.println("MetaDataTester() called");
                        System.out.println("/////////////////////////////////");
                }

                metadataType = s;
                parseType = "file";
        }

        public MetaDataTester(String s, Node node, String s1)
        {
                super(node, s1);
                optionalNotUsed = true;

                if (com.ulearning.ulms.scorm.adl.util.debug.DebugIndicator.ON)
                {
                        System.out.println("/////////////////////////////////");
                        System.out.println("MetaDataTester() called");
                        System.out.println("/////////////////////////////////");
                }

                metadataType = s;
                parseType = "node";
                xmlNode = node;
        }

        private MetaDataTester()
        {
                super("", "");
                optionalNotUsed = true;
        }

        public boolean verifyMandatory()
        {
                boolean flag = false;
                Object obj = null;

                if (parseType == "file")
                {
                        obj = super.document.getDocumentElement();
                }
                else if (parseType == "node")
                {
                        obj = xmlNode;
                }

                LomRules lomrules = new LomRules(((Node) (obj)));

                if (metadataType.equalsIgnoreCase("asset"))
                {
                        flag = lomrules.verifyAssetMandatory();
                }
                else if (metadataType.equalsIgnoreCase("contentaggregation"))
                {
                        flag = lomrules.verifyCaMandatory();
                }
                else if (metadataType.equalsIgnoreCase("sco"))
                {
                        flag = lomrules.verifyScoMandatory();
                }

                appendMessages(lomrules.getMessages());

                return flag;
        }

        public boolean verifyOptional()
        {
                boolean flag = false;
                Object obj = null;

                if (parseType == "file")
                {
                        obj = super.document.getDocumentElement();
                }
                else if (parseType == "node")
                {
                        obj = xmlNode;
                }

                LomRules lomrules = new LomRules(((Node) (obj)));

                if (metadataType.equalsIgnoreCase("asset"))
                {
                        flag = lomrules.verifyAssetOptional();
                }
                else if (metadataType.equalsIgnoreCase("contentaggregation"))
                {
                        flag = lomrules.verifyCaOptional();
                }
                else
                {
                        flag = lomrules.verifyScoOptional();
                }

                appendMessages(lomrules.getMessages());
                optionalNotUsed = lomrules.isOptionalNotUsed() && optionalNotUsed;

                return flag;
        }

        public boolean isOptionalNotUsed()
        {
                return optionalNotUsed;
        }
}
