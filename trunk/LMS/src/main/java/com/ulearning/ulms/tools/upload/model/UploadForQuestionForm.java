/** * UploadForm.java.
 * User: xiejh  Date: 2004-6-22 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.upload.model;


import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import org.apache.struts.upload.MultipartRequestHandler;


/**
 * This class is a placeholder for form values.  In a multipart request, files are represented by
 * set and get methods that use the class org.apache.struts.upload.FormFile, an interface with
 * basic methods to retrieve file information.  The actual structure of the FormFile is dependant
 * on the underlying impelementation of multipart request handling.  The default implementation
 * that struts uses is org.apache.struts.upload.CommonsMultipartRequestHandler.
 *
 * @author Mike Schachter
 * @version $Revision: 1.8 $ $Date: 2003/02/28 02:18:23 $
 */

public class UploadForQuestionForm extends ActionForm
{
        public static final String ERROR_PROPERTY_MAX_LENGTH_EXCEEDED = "com.ulearning.ulms.tools.upload.model.MaxLengthExceeded";

        /**
         * The file that the user has uploaded
         */
        protected FormFile theFile;

        /**
         * The file path to write to
         */
        protected String filePath = null;
        protected String fileExtendName = null;
        protected String newFileName = null;
        /**
         * The file path to write to two upload
         */
        protected FormFile theFileTwo;
        protected String filePathTwo = null;
        protected String fileExtendNameTwo = null;
        protected String newFileNameTwo = null;

        public UploadForQuestionForm getUploadForm()
        {
                UploadForQuestionForm uploadForQuestionForm = new UploadForQuestionForm();

                uploadForQuestionForm.setTheFile(getTheFile());
                uploadForQuestionForm.setFileExtendName(getFileExtendName());
                uploadForQuestionForm.setFilePath(getFilePath());
                uploadForQuestionForm.setNewFileName(getNewFileName());
                //the Two Upload
                uploadForQuestionForm.setFileExtendNameTwo(getFileExtendNameTwo());
                uploadForQuestionForm.setFilePathTwo(getFilePathTwo());
                uploadForQuestionForm.setNewFileNameTwo(getNewFileNameTwo());
                uploadForQuestionForm.setTheFileTwo(getTheFileTwo());

                return uploadForQuestionForm;
        }


        public String getFileExtendName()
        {
                return fileExtendName;
        }

        public void setFileExtendName(String fileExtendName)
        {
                this.fileExtendName = fileExtendName;
        }

        /**
         * Retrieve a representation of the file the user has uploaded
         */
        public FormFile getTheFile()
        {
                return theFile;
        }

        /**
         * Set a representation of the file the user has uploaded
         */
        public void setTheFile(FormFile theFile)
        {
                this.theFile = theFile;
        }

        /**
         * Set the path to write a file to
         */
        public void setFilePath(String filePath)
        {
                this.filePath = filePath;
        }

        /**
         * Get the path to write a file to
         */
        public String getFilePath()
        {
                return filePath;
        }

        public String getNewFileName()
        {
                return newFileName;
        }

        public void setNewFileName(String newFileName)
        {
                this.newFileName = newFileName;
        }


        /**
         * protected FormFile theFileTwo;
         * protected String filePathTwo
         * protected String fileExtendNameTwo
         * protected String newFileNameTwo
         */
        public void setNewFileNameTwo(String newFileNameTwo)
        {
                this.newFileNameTwo = newFileNameTwo;
        }

        public String getNewFileNameTwo()
        {
                return newFileNameTwo;
        }

        public void setFileExtendNameTwo(String fileExtendNameTwo)
        {
                this.fileExtendNameTwo = fileExtendNameTwo;
        }

        public String getFileExtendNameTwo()
        {
                return fileExtendNameTwo;
        }

        public void setTheFileTwo(FormFile theFileTwo)
        {
                this.theFileTwo = theFileTwo;
        }

        public FormFile getTheFileTwo()
        {
                return theFileTwo;
        }

        public void setFilePathTwo(String filePathTwo)
        {
                this.filePathTwo = filePathTwo;
        }

        public String getFilePathTwo()
        {
                return filePathTwo;
        }

        /**
         * Check to make sure the client hasn't exceeded the maximum allowed upload size inside of this
         * validate method.
         */
        public ActionErrors validate(ActionMapping mapping, HttpServletRequest request)
        {
                ActionErrors errors = null;
                //has the maximum length been exceeded?
                Boolean maxLengthExceeded = (Boolean)
                        request.getAttribute(MultipartRequestHandler.ATTRIBUTE_MAX_LENGTH_EXCEEDED);
                if ((maxLengthExceeded != null) && (maxLengthExceeded.booleanValue()))
                {
                        errors = new ActionErrors();
                        errors.add(ERROR_PROPERTY_MAX_LENGTH_EXCEEDED, new ActionError("maxLengthExceeded"));
                }
                return errors;

        }
}
