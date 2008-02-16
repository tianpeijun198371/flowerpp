package com.ulearning.ulms.scorm.handler;

import com.ulearning.ulms.util.log.LogUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.*;

import java.util.Enumeration;
import java.util.Vector;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;


public class LMSPackageHandler
{
        protected static Log logger = LogFactory.getLog(LMSPackageHandler.class);
        public static ZipFile zf;

        /**
         * *************************************************************************
         * *
         * * Method:   Constructor
         * * Input:  none
         * * Output:   none
         * *
         * * Description: Constructor for the PackageHandler
         * *
         * ***************************************************************************
         */
        public LMSPackageHandler()
        {
        }

        /**
         * *************************************************************************
         * *
         * * Method:   version()
         * * Input:  none
         * * Output:   String --  Representing the Version of the class
         * *
         * * Description: This method is being used as a debugging tool.  It returns
         * *              a string indicating the version number of the class file.
         * *
         * ***************************************************************************
         */
        public static String version()
        {
                String versionId = "Version 1.03";

                return versionId;
        }

        /**
         * *************************************************************************
         * *
         * * Method:   display()
         * * Input:  String zipFileName  --  The name of the zip file to be used
         * * Output:   none
         * *
         * * Description: This method is being used as a debugging tool.  It writes
         * *              the contents of a zip file to the dos console.
         * *
         * ***************************************************************************
         */
        public static void display(String zipFileName)
        {
                logger.info("[LMSPackageHandler]--*************");
                logger.info("[LMSPackageHandler]--in display()");
                logger.info("[LMSPackageHandler]--*************\n");

                try
                {
                        logger.info("[LMSPackageHandler]--** " + zipFileName + " **\n");
                        logger.info(
                                "[LMSPackageHandler]--*****************************************");
                        logger.info(
                                "[LMSPackageHandler]--The Package Contains the following files:");
                        logger.info(
                                "[LMSPackageHandler]--*****************************************\n");

                        zf = new ZipFile(zipFileName);

                        for (Enumeration entries = zf.entries(); entries.hasMoreElements();)
                        {
                                logger.info(((ZipEntry) entries.nextElement()).getName());
                        }

                        zf.close();
                }
                catch (IOException e)
                {
                        logger.info("[LMSPackageHandler]--IO Exception Caught: " + e);
                }
        }

        /**
         * *************************************************************************
         * *
         * * Method:   extract()
         * * Input:  String zipFileName  --  The name of the zip file to be used
         * *         Sting  extractedFile  --  The name of the file to be extracted
         * *                                   from the zip
         * * Output:   none
         * *
         * * Description: This method takes in the name of a zip file and a file to
         * *              be extracted from the zip format.  The method locates the
         * *              file and extracts into the '.' directory.
         * *
         * ***************************************************************************
         */
        public static String extract(String zipFileName, String extractedFile,
                                     String pathOfExtract)
        {
                logger.info("[LMSPackageHandler]--in extract()           ");
                logger.info("[LMSPackageHandler]--zip file: " + zipFileName);
                logger.info("[LMSPackageHandler]--file to extract: " + extractedFile);
                logger.info("[LMSPackageHandler]--file to pathOfExtract: " +
                        pathOfExtract);

                String nameOfExtractedFile = new String("");

                try
                {
                        String pathAndName = new String("");

                        //  Input stream for the zip file (package)
                        ZipInputStream in = new ZipInputStream(new FileInputStream(
                                zipFileName));

                        //  Cut the path off of the name of the file. (for writing the file)
                        int indexOfFileBeginning = extractedFile.lastIndexOf(File.separator) +
                                1;
                        nameOfExtractedFile = extractedFile.substring(indexOfFileBeginning);
                        pathAndName = pathOfExtract + File.separator + nameOfExtractedFile;

                        //  Ouput stream for the extracted file
                        //*************************************
                        //*************************************
                        OutputStream out = new FileOutputStream(pathAndName);

                        //OutputStream out = new FileOutputStream(nameOfExtractedFile);
                        ZipEntry entry;
                        byte[] buf = new byte[1024];
                        int len;
                        int flag = 0;

                        while (flag != 1)
                        {
                                entry = in.getNextEntry();

                                if ((entry.getName()).equalsIgnoreCase(extractedFile))
                                {
                                        logger.info(
                                                "[LMSPackageHandler]--Found file to extract...  extracting to " +
                                                        pathOfExtract);

                                        flag = 1;
                                }
                        }

                        while ((len = in.read(buf)) > 0)
                        {
                                out.write(buf, 0, len);
                        }

                        out.close();
                        in.close();
                }
                catch (IOException e)
                {
                        logger.info("[LMSPackageHandler]--IO Exception Caught: " + e);
                }

                return nameOfExtractedFile;
        }

        /**
         * *************************************************************************
         * *
         * * Method:   findManifest()
         * * Input:  String zipFileName  --  The name of the zip file to be used
         * * Output:   Boolean  --  Signifies whether or not the manifest was found.
         * *
         * * Description: This method takes in the name of a zip file and tries to
         * *              locate the imsmanifest.xml file
         * *
         * ***************************************************************************
         */
        public static boolean findManifest(String zipFileName)
        {
                logger.info("[LMSPackageHandler]--***********************");
                logger.info("[LMSPackageHandler]--in findManifest()      ");
                logger.info("[LMSPackageHandler]--***********************\n");

                boolean rtn = false;

                try
                {
                        ZipInputStream in = new ZipInputStream(new FileInputStream(
                                zipFileName));

                        ZipEntry entry;
                        int flag = 0;

                        while ((flag != 1) && (in.available() != 0))
                        {
                                entry = in.getNextEntry();

                                if (in.available() != 0)
                                {
                                        if ((entry.getName()).equalsIgnoreCase("imsmanifest.xml"))
                                        {
                                                logger.info(
                                                        "[LMSPackageHandler]--Located manifest.... returning true");

                                                flag = 1;
                                                rtn = true;
                                        }
                                }
                        }

                        in.close();
                }
                catch (IOException e)
                {
                        logger.info("[LMSPackageHandler]--IO Exception Caught: " + e);
                }

                return rtn;
        }

        /**
         * *************************************************************************
         * *
         * * Method:   findMetadata()
         * * Input:  String zipFileName  --  The name of the zip file to be used
         * * Output: Boolean  --  Whether or not any xml files were found
         * *
         * * Description: This method takes in the name of a zip file and locates
         * *              all files with an .xml extension
         * *
         * ***************************************************************************
         */
        public static boolean findMetadata(String zipFileName)
        {
                logger.info("[LMSPackageHandler]--***********************");
                logger.info("[LMSPackageHandler]--in findMetadata()      ");
                logger.info("[LMSPackageHandler]--***********************\n");

                boolean rtn = false;
                String suffix = ".xml";

                try
                {
                        //  The zip file being searched.
                        ZipInputStream in = new ZipInputStream(new FileInputStream(
                                zipFileName));

                        //  An entry in the zip file
                        ZipEntry entry;

                        while ((in.available() != 0))
                        {
                                entry = in.getNextEntry();

                                if (in.available() != 0)
                                {
                                        if ((entry.getName()).endsWith(suffix))
                                        {
                                                rtn = true;

                                                logger.info(
                                                        "[LMSPackageHandler]--Other Meta-data located... returning true");
                                        }
                                }
                        }

                        in.close();
                }
                catch (IOException e)
                {
                        logger.info("[LMSPackageHandler]--IO Exception Caught: " + e);
                }

                return rtn;
        }

        /**
         * *************************************************************************
         * *
         * * Method:   locateMetadata()
         * * Input:  String zipFileName  --  The name of the zip file to be used
         * * Output: Vector  --  A vector of the names of xml files.
         * *
         * * Description: This method takes in the name of a zip file and locates
         * *              all files with an .xml extension an adds their names to a
         * *              vector.
         * *
         * ***************************************************************************
         */
        public static Vector locateMetadata(String zipFileName)
        {
                logger.info("[LMSPackageHandler]--***********************");
                logger.info("[LMSPackageHandler]--in locateMetadata()    ");
                logger.info("[LMSPackageHandler]--***********************\n");

                //  An array of names of xml files to be returned to ColdFusion
                Vector metaDataVector = new Vector();
                String suffix = ".xml";

                try
                {
                        //  The zip file being searched.
                        ZipInputStream in = new ZipInputStream(new FileInputStream(
                                zipFileName));

                        //  An entry in the zip file
                        ZipEntry entry;

                        logger.info("[LMSPackageHandler]--Other meta-data located:");

                        while ((in.available() != 0))
                        {
                                entry = in.getNextEntry();

                                if (in.available() != 0)
                                {
                                        if ((entry.getName()).endsWith(suffix))
                                        {
                                                logger.info(entry.getName());

                                                metaDataVector.addElement(entry.getName());
                                        }
                                }
                        }

                        in.close();
                }
                catch (IOException e)
                {
                        logger.info("[LMSPackageHandler]--IO Exception Caught: " + e);
                }

                return metaDataVector;
        }

        /**
         * *************************************************************************
         * *
         * * Method:   getListOfMetadata()
         * * Input:  String zipFileName  --  The name of the zip file to be used
         * * Output: String --  A comma delimited list of meta-data files.
         * *
         * * Description: This method takes in the name of a zip file and locates
         * *              all files with an .xml extension an adds their names to a
         * *              vector.  The vector is then changed to a comma delimited
         * *              string and returned to the caller.
         * *
         * ***************************************************************************
         */
        public static String getListOfMetadata(String zipFile)
        {
                logger.info("[LMSPackageHandler]--***********************");
                logger.info("[LMSPackageHandler]--in getListOfMetadata() ");
                logger.info("[LMSPackageHandler]--***********************\n");

                Vector mdVector = new Vector();
                mdVector = locateMetadata(zipFile);

                String mdString = new String();
                mdString = mdVector.toString();

                logger.info(
                        "[LMSPackageHandler]--**********************************************");
                logger.info("[LMSPackageHandler]--in getListOfMetadata(): String is " +
                        mdString);
                logger.info(
                        "[LMSPackageHandler]--**********************************************\n");

                return mdString;
        }
}
