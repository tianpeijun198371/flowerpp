// Source File Name:   DatetypeRules.java
package com.ulearning.ulms.scorm.adl.testsuite.metadata.rules;

import com.ulearning.ulms.scorm.adl.testsuite.metadata.MetadataUtil;
import com.ulearning.ulms.scorm.adl.util.MessageType;
import com.ulearning.ulms.scorm.adl.util.MessageType;
import com.ulearning.ulms.scorm.adl.util.debug.DebugIndicator;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.PrintStream;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.StringTokenizer;


public class DatetypeRules extends MetadataUtil
{
        private Node datetypeNode;

        public DatetypeRules(Node node)
        {
                if (com.ulearning.ulms.scorm.adl.util.debug.DebugIndicator.ON)
                {
                        System.out.println("/////////////////////////////////");
                        System.out.println("DatetypeRules() called");
                        System.out.println("/////////////////////////////////");
                }

                datetypeNode = node;
        }

        private DatetypeRules()
        {
        }

        public boolean verifyDatetime()
        {
                boolean flag = true;
                boolean flag1 = true;
                char c = '\310';
                boolean flag2 = true;
                String s = new String();
                String s2 = new String();
                int i = 0;
                Node node = datetypeNode;
                NodeList nodelist = datetypeNode.getChildNodes();

                if (nodelist != null)
                {
                        for (int j = 0; j < nodelist.getLength(); j++)
                        {
                                Node node1 = nodelist.item(j);

                                if ((node1.getNodeType() == 3) || (node1.getNodeType() == 4))
                                {
                                        i++;
                                }
                        }

                        if (i <= 0)
                        {
                                flag2 = false;
                                setMessage(MessageType.FAILED,
                                        "DatetypeRules::verifyDatetime()",
                                        "No data was found in element <datetime> and fails the minimum character length test");
                        }
                        else if (i == 1)
                        {
                                for (int k = 0; k < nodelist.getLength(); k++)
                                {
                                        Node node2 = nodelist.item(k);

                                        if ((node2.getNodeType() == 3) ||
                                                (node2.getNodeType() == 4))
                                        {
                                                setMessage(MessageType.INFO,
                                                        "DatetypeRules::verifyDatetime()",
                                                        "Testing element <datetime>..");

                                                String s1 = getTaggedData(datetypeNode);

                                                if (com.ulearning.ulms.scorm.adl.util.debug.DebugIndicator.ON)
                                                {
                                                        System.out.println("1 <= " + s1.length() + " <= " +
                                                                (int) c);
                                                }

                                                if (s1.length() > c)
                                                {
                                                        setMessage(MessageType.WARNING,
                                                                "DatetypeRules::verifyDatetime()",
                                                                "The text contained in element <datetime> is greater than " +
                                                                        (int) c);
                                                }
                                                else if (s1.length() < 1)
                                                {
                                                        flag2 = false;
                                                        setMessage(MessageType.FAILED,
                                                                "DatetypeRules::verifyDatetime()",
                                                                "No text was found in element <datetime> and fails the minimum character length test");
                                                }
                                                else
                                                {
                                                        setMessage(MessageType.PASSED,
                                                                "DatetypeRules::verifyDatetime()",
                                                                "Character length for element <datetime> has been verified");
                                                }

                                                boolean flag3 = false;
                                                StringTokenizer stringtokenizer = new StringTokenizer(s1,
                                                        "-");
                                                int l = stringtokenizer.countTokens();

                                                if (l == 3)
                                                {
                                                        String s3 = stringtokenizer.nextToken();
                                                        String s4 = stringtokenizer.nextToken();
                                                        String s5 = stringtokenizer.nextToken();

                                                        if ((s3.length() == 4) && (s4.length() == 2) &&
                                                                (s5.length() == 2))
                                                        {
                                                                flag3 = true;
                                                        }
                                                }

                                                SimpleDateFormat simpledateformat = new SimpleDateFormat(
                                                        "yyyy-mm-dd");
                                                SimpleDateFormat simpledateformat1 = new SimpleDateFormat(
                                                        "hh:mm:ss");

                                                if (flag3)
                                                {
                                                        try
                                                        {
                                                                simpledateformat.parse(s1);
                                                        }
                                                        catch (ParseException parseexception)
                                                        {
                                                                flag = false;
                                                        }
                                                }
                                                else
                                                {
                                                        flag = false;
                                                }

                                                if (!flag)
                                                {
                                                        try
                                                        {
                                                                simpledateformat1.parse(s1);
                                                        }
                                                        catch (ParseException parseexception1)
                                                        {
                                                                flag1 = false;
                                                        }
                                                }

                                                if (flag || flag1)
                                                {
                                                        setMessage(MessageType.PASSED,
                                                                "DatetypeRules::verifyDatetime()",
                                                                "DateType for element <datetime> has been verified");
                                                }
                                                else
                                                {
                                                        setMessage(MessageType.WARNING,
                                                                "DatetypeRules::verifyDatetime()",
                                                                "DateType for element <datetime> may not contain a valid date or time format");
                                                        setMessage(MessageType.WARNING,
                                                                "DatetypeRules::verifyDatetime()",
                                                                "Date/time used: " + s1);
                                                        setMessage(MessageType.WARNING,
                                                                "DatetypeRules::verifyDatetime()",
                                                                "Date format used for testing: International Standard Date notation (" +
                                                                        simpledateformat.toLocalizedPattern() + ")");
                                                        setMessage(MessageType.WARNING,
                                                                "DatetypeRules::verifyDatetime()",
                                                                "Time format used for testing: International Standard Notation for the Time of Day (" +
                                                                        simpledateformat1.toLocalizedPattern() + ")");
                                                }
                                        }
                                }
                        }
                        else
                        {
                                flag2 = false;

                                if (com.ulearning.ulms.scorm.adl.util.debug.DebugIndicator.ON)
                                {
                                        System.out.println("There were " + i +
                                                " TEXT_NODE elements found");
                                }

                                setMessage(MessageType.FAILED,
                                        "DatetypeRules::verifyDatetime()",
                                        "Too many text strings were found in <datetime> and fails the test");
                        }
                }
                else
                {
                        flag2 = false;
                        setMessage(MessageType.FAILED, "DatetypeRules::verifyDatetime()",
                                "No data was found in element <datetime> and fails the minimum character length test");
                }

                return flag2;
        }

        public boolean verifyDescription()
        {
                boolean flag = true;
                flag = verifyLangstring(datetypeNode, "<description>", "", 1000);

                return flag;
        }
}
