/** * UploadForm.java. 
 * User: xiejh  Date: 2004-6-22 *  
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd. 
 * All rights reserved. 
 */
package com.ulearning.ulms.tools.upload.model;


import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import org.apache.struts.upload.MultipartRequestHandler;

import javax.servlet.http.HttpServletRequest;


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

public class UploadForm extends ActionForm
{
        public static final String ERROR_PROPERTY_MAX_LENGTH_EXCEEDED = "com.ulearning.ulms.tools.upload.model.MaxLengthExceeded";

        /**
         * The file that the user has uploaded
         */
        protected FormFile theFile;

        /**
         * The file that the user has uploaded
         */
        protected FormFile theFile1;
        /**
         * The file that the user has uploaded
         */
        protected FormFile theFile2;

        /**
         * The file that the user has uploaded
         */
        protected FormFile theFile3;

        /**
         * The file that the user has uploaded
         */
        protected FormFile theFile4;

        /**
         * The file that the user has uploaded
         */
        protected FormFile theFile5;

        /**
         * The file that the user has uploaded
         */
        protected FormFile theFile6;

        /**
         * The file that the user has uploaded
         */
        protected FormFile theFile7;

        /**
         * The file that the user has uploaded
         */
        protected FormFile theFile8;

        /**
         * The file that the user has uploaded
         */
        protected FormFile theFile9;


        /**
         * The file path to write to
         */
        protected String filePath = null;
        protected String fileExtendName = null;
        protected String newFileName = null;


        public UploadForm getUploadForm()
        {
                UploadForm uploadForm = new UploadForm();
                uploadForm.setTheFile(getTheFile());
                uploadForm.setFileExtendName(getFileExtendName());
                uploadForm.setFilePath(getFilePath());
                uploadForm.setNewFileName(getNewFileName());
                return uploadForm;
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

        public FormFile getTheFile1()
        {
                return theFile1;
        }

        public void setTheFile1(FormFile theFile1)
        {
                this.theFile1 = theFile1;
        }

        public FormFile getTheFile2()
        {
                return theFile2;
        }

        public void setTheFile2(FormFile theFile2)
        {
                this.theFile2 = theFile2;
        }

        public FormFile getTheFile3()
        {
                return theFile3;
        }

        public void setTheFile3(FormFile theFile3)
        {
                this.theFile3 = theFile3;
        }

        public FormFile getTheFile4()
        {
                return theFile4;
        }

        public void setTheFile4(FormFile theFile4)
        {
                this.theFile4 = theFile4;
        }

        public FormFile getTheFile5()
        {
                return theFile5;
        }

        public void setTheFile5(FormFile theFile5)
        {
                this.theFile5 = theFile5;
        }

        public FormFile getTheFile6()
        {
                return theFile6;
        }

        public void setTheFile6(FormFile theFile6)
        {
                this.theFile6 = theFile6;
        }

        public FormFile getTheFile7()
        {
                return theFile7;
        }

        public void setTheFile7(FormFile theFile7)
        {
                this.theFile7 = theFile7;
        }

        public FormFile getTheFile8()
        {
                return theFile8;
        }

        public void setTheFile8(FormFile theFile8)
        {
                this.theFile8 = theFile8;
        }

        public FormFile getTheFile9()
        {
                return theFile9;
        }

        public void setTheFile9(FormFile theFile9)
        {
                this.theFile9 = theFile9;
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
