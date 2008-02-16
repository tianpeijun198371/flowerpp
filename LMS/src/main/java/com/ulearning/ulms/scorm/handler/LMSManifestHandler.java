package com.ulearning.ulms.scorm.handler;

import com.ulearning.ulms.core.exceptions.ULMSException;

// ADL imports
import com.ulearning.ulms.course.dao.CourseContentDAO;
import com.ulearning.ulms.course.dao.CourseContentDAOFactory;
import com.ulearning.ulms.course.helper.CourseContentHelper;
import com.ulearning.ulms.course.model.CourseContentForm;
import com.ulearning.ulms.scorm.adl.parsers.dom.ADLItem;
import com.ulearning.ulms.scorm.adl.parsers.dom.ADLOrganizations;
import com.ulearning.ulms.scorm.adl.parsers.dom.CPDOMParser;
import com.ulearning.ulms.scorm.dao.ScormDAO;
import com.ulearning.ulms.scorm.dao.ScormDAOFactory;
import com.ulearning.ulms.scorm.exceptions.ScormSysException;
import com.ulearning.ulms.scorm.util.ScormConstants;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.w3c.dom.Attr;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import org.xml.sax.InputSource;

import java.io.File;
import java.io.Serializable;

import java.util.Vector;


public class LMSManifestHandler extends CPDOMParser implements Serializable
{
        protected static Log logger = LogFactory.getLog(LMSManifestHandler.class);
        protected Vector items;
        private Vector tempItemList;
        protected InputSource sourceToParse;
        protected String courseTitle;
        protected String courseDir;
        protected int courseID;
        protected String control;
        protected CourseContentForm courseContentForm;
        protected ScormDAO scormDAO;
        protected CourseContentDAO courseContentDAO;

        /**
         * ***********************************************************************
         * *
         * * Method:  LMSManifestHandler()
         * * Input:  None
         * *
         * * Output: None
         * *
         * * Description:  Default Constructor.
         * *
         * *************************************************************************
         */
        public LMSManifestHandler()
        {
                super();
                logger.info("[LMSManifestHandler]-----LMSManifestHandler init...");

                tempItemList = new Vector();
                items = new Vector();
                courseTitle = "";
                courseDir = "";
                courseID = 0;
                control = "";
                getDAO();
                logger.info("[LMSManifestHandler]-----LMSManifestHandler inited");
        }

        public LMSManifestHandler(int i)
        {
                logger.info("[LMSManifestHandler]-----LMSManifestHandler " + i);
        }

        public static void test()
        {
                System.out.println("[LMSManifestHandler]test!");
        }

        /**
         * this method initiates the RTE import/parsing of a manifest
         * *              file from a PIF content package.
         *
         * @return if sucess return the nodeID,else return -1;
         */
        public int processManifest() throws ScormSysException
        {
                int nodeID = -1;

                logger.info(
                        "[LMSManifestHandler]processManifest--Entering LMSManifestHandler::processManifest()");

                try
                {
                        // Parse the file
                        parse(sourceToParse);

                        logger.info(
                                "[LMSManifestHandler]processManifestDocument parsing complete.");

                        document = getDocument();
                        valid = true;

                        //  Call process content
                        boolean result = processContent();
                }
                catch (ULMSException e)
                {
                        throw e;
                }
                catch (Exception se)
                {
                        logger.info("[LMSManifestHandler]processManifest--error:" +
                                se.getMessage());
                        se.printStackTrace();
                        throw new ScormSysException("您要导入的课件可能不是标准的Scorm课件，请重新上传！", se);
                }

                logger.info(
                        "[LMSManifestHandler]processManifestExiting LMSManifestHandler::processManifest()");
                //

                //  Write to the corresponding database
                nodeID = updateDB();

                //  Return boolean signifying whether or not the parsing was successful
                return nodeID;
        }

        /**
         * *************************************************************************
         * *
         * * Method:  processContent()
         * * Input:  none
         * * Output:  boolean - Whether the method was successful or not.
         * * Description:   This method starts at the root node and then calls
         * *                calls fillManifest which populates the parser structure.
         * *
         * * Side Effects:  none
         * *
         * *************************************************************************
         */
        public boolean processContent()
        {
                logger.info(
                        "[LMSManifestHandler]--*** LMSManifestHandler::processContent() ***");

                // boolean that signifies whether or not the method call was successful
                boolean result = true;

                if (document == null)
                {
                        logger.info("[LMSManifestHandler]--the document is null");
                }

                // Get the root node of the imsmanifest <manifest>
                Node contentNode = document.getDocumentElement();

                // Get the children of the root node
                NodeList contentChildren = contentNode.getChildNodes();

                logger.info("[LMSManifestHandler]--*** NODE: " +
                        contentNode.getNodeName());
                logger.info("[LMSManifestHandler]--*** Children - " +
                        contentChildren.getLength() + " ***");

                // Call fillManifest().  This will fill the parser structure
                this.manifest.fillManifest(contentNode);

                // Set the control attribute
                // *************************************************
                //  This is proprietary in version 1.2 of the RTE
                //  This will change!
                // *************************************************
                this.setSequence();

                //  Uses the newly populated structure to populate the
                //  items Vector.
                this.items = this.getItemList();

                //  Get resources and compare w/ the item Vector
                for (int i = 0; i < contentChildren.getLength(); i++)
                {
                        Node currentNode = contentChildren.item(i);

                        // Make sure this is an "element node"
                        if (currentNode.getNodeType() == Node.ELEMENT_NODE)
                        {
                                // Move to the resources element to process the resources
                                if (currentNode.getNodeName().equalsIgnoreCase("resources"))
                                {
                                        logger.info(
                                                "[LMSManifestHandler]--*** Found Current Node: " +
                                                        currentNode.getNodeName());

                                        // process the Resources section.
                                        result = processResources(currentNode);

                                        if (result == false)
                                        {
                                                break;
                                        }
                                }
                        }
                }

                logger.info(
                        "[LMSManifestHandler]--*** Exiting LMSManifestHandler::processContent() ***");
                logger.info("[LMSManifestHandler]--*** Returning: " + result);

                return result;
        }

        /**
         * *************************************************************************
         * *
         * * Method:  processResources
         * * Input:  Node resourcesNode - The <resources> node from the imsmanfest file
         * * Output:
         * * Description:  This method populates the resources section of the parser
         * *               structure.
         * *
         * * Side Effects:  none
         * *
         * *************************************************************************
         */
        public boolean processResources(Node resourcesNode)
        {
                boolean result = true;
                int level = 1;

                logger.info(
                        "[LMSManifestHandler]--******  LMSManifestHandler:processResources  *********");

                if (document == null)
                {
                        logger.info("[LMSManifestHandler]--the document is null");
                }

                // Get the children of the resources node
                NodeList resourcesChildren = resourcesNode.getChildNodes();

                logger.info("[LMSManifestHandler]--*** NODE: " +
                        resourcesNode.getNodeName());
                logger.info("[LMSManifestHandler]--*** Children - " +
                        resourcesChildren.getLength() + " ***");

                // Find the resource node(s)
                for (int i = 0; i < resourcesChildren.getLength(); i++)
                {
                        Node currentNode = resourcesChildren.item(i);

                        // Make sure this is an "element node"
                        if (currentNode.getNodeType() == Node.ELEMENT_NODE)
                        {
                                if (currentNode.getNodeName().equalsIgnoreCase("resource"))
                                {
                                        logger.info(
                                                "[LMSManifestHandler]--*** Found Current Node: " +
                                                        currentNode.getNodeName());

                                        result = processResource(currentNode);

                                        if (result == false)
                                        {
                                                break;
                                        }
                                }
                        }
                }

                logger.info(
                        "[LMSManifestHandler]--*** Exiting LMSManifestHandler::processResources() ***");
                logger.info("[LMSManifestHandler]--*** Returning: " + result);

                return result;
        }

        /**
         * *************************************************************************
         * *
         * * Method: processResource()
         * * Input:  Node resourceNode - A <resource> node from the imsmanifest file
         * * Output:
         * * Description:  This method populates the resource section of the parser
         * *               object.
         * *
         * * Side Effects:  none
         * *
         * *************************************************************************
         */
        public boolean processResource(Node resourceNode)
        {
                boolean result = true;

                logger.info(
                        "[LMSManifestHandler]--******  LMSManifestHandler:processResource  *********");

                String id = getAttribute(resourceNode, "identifier");
                String scormType = getAttribute(resourceNode, "adlcp:scormtype");
                String type = getAttribute(resourceNode, "type");
                String href = getAttribute(resourceNode, "href");

                updateItemList(id, scormType, type, href);

                logger.info(
                        "[LMSManifestHandler]--*** Exiting LMSManifestHandler::processResource() ***");
                logger.info("[LMSManifestHandler]--*** Returning: " + result);

                return result;
        }

        /**
         * *************************************************************************
         * *
         * * Method: updateItemList
         * * Input:  String id -  the identifier of the item
         * *         String scormType - the scormtype of the item (or reference)
         * *         String type -  the type attribute
         * *         String href -  the href location
         * * Output:
         * * Description:   This method adds the launch location to the item vector.
         * *                The launch is not part of the <item> element, so this
         * *                method compares the identifier and identifierref and when
         * *                a match is found, the item section of the parser object
         * *                is populated with the resource launch location.  This is
         * *                done only for convenience.
         * *
         * * Side Effects:  none
         * *
         * *************************************************************************
         */
        public void updateItemList(String id, String scormType, String type,
                                   String href)
        {
                logger.info(
                        "[LMSManifestHandler]--******  LMSManifestHandler:updateItemList  *********");

                ADLItem tempItem = new ADLItem();

                // Loop throught the item vector checking the identfierref with the
                // identifier of the resource.  If they match, update the item and exit
                for (int i = 0; i < items.size(); i++)
                {
                        tempItem = (com.ulearning.ulms.scorm.adl.parsers.dom.ADLItem) this.items.elementAt(i);

                        String idref = tempItem.getIdentifierref();

                        if (idref.equals(id))
                        {
                                logger.info(
                                        "[LMSManifestHandler]--****** !!  Found Matching Resource  !!  *****");
                                logger.info("[LMSManifestHandler]--i is:" + i +
                                        " -- Items size is" + items.size());

                                if ((type != null) && !type.equals(""))
                                {
                                        tempItem.setType(type);
                                }

                                if ((scormType != null) && !type.equals(""))
                                {
                                        tempItem.setScormType(scormType);
                                }

                                if ((href != null) && !href.equals(""))
                                {
                                        tempItem.setLaunchLocation(href);
                                }

                                items.removeElementAt(i);
                                items.insertElementAt(tempItem, i);
                        }
                }

                logger.info(
                        "[LMSManifestHandler]--****** Exiting - LMSManifestHandler:updateItemList  *********");
        }

        public int getCourseID()
        {
                return courseID;
        }

        public void setCourseID(int courseID)
        {
                this.courseID = courseID;
        }

        /**
         * *************************************************************************
         * *
         * * Method: getOrgsCopy()
         * * Input: none
         * * Output: ADLOrganizations
         * * Description: This method was developed anticipating sequencing for
         * *              version 1.3.  This method will return the entire course
         * *              <organization> information.  This will include all
         * *              sequencing information.
         * *
         * **************************************************************************
         */
        public ADLOrganizations getOrgsCopy()
        {
                return this.manifest.getOrganizations();
        }

        /**
         * *************************************************************************
         * *
         * * Method: setSequence()
         * * Input:  none
         * * Output: none
         * * Description: This method will be used to set the control type when
         * *              sequencing is introduced in version 1.3.
         * *
         * **************************************************************************
         */
        public void setSequence()
        {
                logger.info(
                        "[LMSManifestHandler]--In LMSManifestHandler:setSequence() ");

                // ****************************************************************
                // This is being commented out due to the lack of a sequencing spec
                // in version 1.2.  It will be used in the next version
                // ****************************************************************
                //   this.control =
                //   this.manifest.getOrganizations().getFirstOrg().getSequence().getControl();
                logger.info("[LMSManifestHandler]--Control is: " + this.control);
        }

        //  Class "Setter" methods
        public void setCourseName(String courseTitle)
        {
                this.courseTitle = courseTitle;
        }

        public void setControl(String controlType)
        {
                this.control = controlType;
        }

        public void setFileToParse(InputSource inputStream)
        {
                this.sourceToParse = inputStream;
        }

        /**
         * This method takes the relevant information from the
         * *                populated parser structure and writes it to a related
         * *                database.  This is done so that the JSP coding is
         * *                more straight forward.
         *
         * @return the courseContentNodeID
         */
        protected int updateDB() throws ScormSysException
        {
                logger.info(
                        "[LMSManifestHandler]--******  LMSManifestHandler:updateDB()  *********");

                int courseContentNodeID = -1;

                try
                {
                        CourseContentHelper.throwsReAddException(courseContentForm);
                        // Insert the course into the course Info table
                        courseContentNodeID = courseContentDAO.addCourseContent(courseContentForm);

                        // Create web root for new course on web server
                        courseDir = ScormConstants.ImportRoot + courseContentNodeID;

                        File theRTECourseDir = new File(courseDir);

                        // The course directory should not exist yet
                        if (!theRTECourseDir.isDirectory())
                        {
                                theRTECourseDir.mkdirs();
                        }

                        //insert items
                        scormDAO.insertItem(courseContentForm.getRelationID(),
                                courseContentForm.getType(), courseContentNodeID, items);
                }
                catch (ULMSException e)
                {
                        throw e;
                }
                catch (Exception e)
                {
                        e.printStackTrace();
                        throw new ScormSysException("您的课件可能不是标准的Scorm课件，请重新上传！", e);
                }

                return courseContentNodeID;
        }

        /**
         * *************************************************************************
         * *
         * * Method:  getAttribute()
         * * Input:  Node theNode -  The current node that was traversed to
         * *         String the Attribute -  The name of the attribute that the value
         * *                                 is desired for.
         * * Output:
         * * Description:   This method
         * *
         * * Side Effects:  none
         * *
         * *************************************************************************
         */
        protected String getAttribute(Node theNode, String theAttribute)
        {
                String returnValue = new String();

                // grab attributes of the node
                Attr[] attrs = sortAttributes(theNode.getAttributes());

                // now see if the asked for attribute exists and send
                // back the value
                Attr attribute;

                for (int i = 0; i < attrs.length; i++)
                {
                        attribute = attrs[i];

                        if (attribute.getName().equals(theAttribute))
                        {
                                returnValue = attribute.getValue();

                                break;
                        }
                }

                return returnValue;
        }

        /**
         * *************************************************************************
         * *
         * * Method: sortAttributes()
         * * Input: NamedNodeMap attrs - The list of attributes
         * * Output: Attr[] - Sorted array of attributes
         * * Description:   This method returns an array of sorted attributes.
         * *
         * * Side Effects:  none
         * *
         * *************************************************************************
         */
        protected Attr[] sortAttributes(NamedNodeMap attrs)
        {
                int len = (attrs != null) ? attrs.getLength() : 0;
                Attr[] array = new Attr[len];

                for (int i = 0; i < len; i++)
                {
                        array[i] = (Attr) attrs.item(i);
                }

                for (int i = 0; i < (len - 1); i++)
                {
                        String name = array[i].getNodeName();
                        int index = i;

                        for (int j = i + 1; j < len; j++)
                        {
                                String curName = array[j].getNodeName();

                                if (curName.compareTo(name) < 0)
                                {
                                        name = curName;
                                        index = j;
                                }
                        }

                        if (index != i)
                        {
                                Attr temp = array[i];
                                array[i] = array[index];
                                array[index] = temp;
                        }
                }

                return (array);
        }

        /**
         * *************************************************************************
         * *
         * * Method: getSubElement()
         * * Input: Node node - The current node that has been traversed to
         * *        String element - The name of the sub-element that's value is
         * *                         desired.
         * * Output:  String - The value of the sub-element
         * * Description: This method gets the value of a desired sub-element.
         * * Side Effects:
         * *
         * **************************************************************************
         */
        public String getSubElement(Node node, String element)
        {
                String value = new String();
                NodeList kids = node.getChildNodes();

                //cycle through all children of node to get the text
                if (kids != null)
                {
                        for (int i = 0; i < kids.getLength(); i++)
                        {
                                if (kids.item(i).getNodeType() == Node.ELEMENT_NODE)
                                {
                                        // look for the asked for element
                                        if (kids.item(i).getNodeName().equalsIgnoreCase(element))
                                        {
                                                value = getText(kids.item(i));

                                                return value;
                                        }
                                }
                        }
                }
                else
                {
                        logger.info("[LMSManifestHandler]--node has no kids");
                }

                return value;
        }

        /**
         * *************************************************************************
         * *
         * * Method: getText()
         * * Input: Node node - The current node
         * * Output: String - The text of the desired node.
         * * Description:   This method gets the child text node of the node that is
         * *                passed in for input.
         * * Side Effects:
         * *
         * **************************************************************************
         */
        public String getText(Node node)
        {
                String value = new String();
                NodeList kids = node.getChildNodes();

                //cycle through all children of node to get the text
                if (kids != null)
                {
                        for (int i = 0; i < kids.getLength(); i++)
                        {
                                // make sure we have a text element
                                if ((kids.item(i).getNodeType() == Node.TEXT_NODE) ||
                                        (kids.item(i).getNodeType() == Node.CDATA_SECTION_NODE))
                                {
                                        value = value + kids.item(i).getNodeValue().trim();
                                }
                        }
                }
                else
                {
                        logger.info("[LMSManifestHandler]--node has no kids");
                }

                return value;
        }

        public String getCourseTitle()
        {
                return courseTitle;
        }

        public void setCourseTitle(String courseTitle)
        {
                this.courseTitle = courseTitle;
        }

        public InputSource getSourceToParse()
        {
                return sourceToParse;
        }

        public void setSourceToParse(InputSource sourceToParse)
        {
                this.sourceToParse = sourceToParse;
        }

        public Vector getItems()
        {
                return items;
        }

        public void setItems(Vector items)
        {
                this.items = items;
        }

        public String getCourseDir()
        {
                return courseDir;
        }

        public void setCourseDir(String courseDir)
        {
                this.courseDir = courseDir;
        }

        public CourseContentForm getCourseContentForm()
        {
                return courseContentForm;
        }

        public void setCourseContentForm(CourseContentForm courseContentForm)
        {
                this.courseContentForm = courseContentForm;
        }

        protected void getDAO()
        {
                if ((scormDAO != null) && (courseContentDAO != null))
                {
                        return;
                }

                try
                {
                        scormDAO = ScormDAOFactory.getDAO();
                        courseContentDAO = CourseContentDAOFactory.getDAO();
                }
                catch (Exception ex)
                {
                        ex.printStackTrace();
                }
        }
}
