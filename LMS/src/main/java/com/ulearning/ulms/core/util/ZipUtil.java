/**
 * ZipUtil.java.
 * User: fengch  Date: 2004-6-23 w
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.core.util;

import com.ulearning.ulms.util.log.LogUtil;

import java.io.*;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;


public class ZipUtil
{
        public static void zip(String zipFileName, String inputFilePath,
                               List inputFilePathNames) throws IOException
        {
                FileOutputStream dest = new FileOutputStream(zipFileName);
                ZipOutputStream zos = new ZipOutputStream(new BufferedOutputStream(dest));

                try
                {
                        int buffer = 2048;
                        int count = 0;

                        dest = new FileOutputStream(zipFileName);
                        zos = new ZipOutputStream(new BufferedOutputStream(dest));

                        byte[] data = new byte[buffer];

                        if (!inputFilePath.endsWith(File.separator))
                        {
                                inputFilePath = inputFilePath + File.separator;
                        }

                        // get a list of files from current directory
                        File file = null;
                        File file_temp = null;
                        String temp = null;
                        String[] files = null;

                        for (int i = 0; i < inputFilePathNames.size(); i++)
                        {
                                temp = (String) inputFilePathNames.get(i);
                                file = new File(inputFilePath + temp);

                                files = file.list();

                                for (int j = 0; j < files.length; j++)
                                {
                                        //System.out.println("Adding: " + files[j]);
                                        temp = inputFilePath + files[j];
                                        //System.out.println("Adding: " + temp);
                                        zipContent(zos, inputFilePath, files[j]);
                                }
                        }
                }
                catch (IOException ioe)
                {
                        ioe.printStackTrace();
                }
                finally
                {
                        if (zos != null)
                        {
                                try
                                {
                                        zos.close();
                                }
                                catch (IOException e)
                                {
                                        //ignore
                                }
                        }

                        if (dest != null)
                        {
                                try
                                {
                                        dest.close();
                                }
                                catch (IOException e)
                                {
                                        //ignore
                                }
                        }
                }
        }

        private static void zipContent(ZipOutputStream zos, String rootPath,
                                       String inputFilePath) throws IOException
        {
                try
                {
                        int buffer = 2048;
                        int count = 0;
                        byte[] data = new byte[buffer];

                        if (!rootPath.endsWith(File.separator))
                        {
                                rootPath = rootPath + File.separator;
                        }

                        String fileName = rootPath + inputFilePath;
                        File file = new File(fileName);

                        //System.out.println("rootPath:" + rootPath);
                        //System.out.println("inputFilePath:" + inputFilePath);
                        //System.out.println("fileName:" + fileName);
                        if (file.isDirectory())
                        {
                                inputFilePath = inputFilePath + "/";

                                ZipEntry zipEntry = new ZipEntry(inputFilePath);

                                zos.putNextEntry(zipEntry);

                                File[] files = file.listFiles();

                                for (int i = 0; i < files.length; i++)
                                {
                                        //System.out.println("$$:" + files[i].getName());
                                        zipContent(zos, rootPath + file.getName(),
                                                files[i].getName());
                                        zos.closeEntry();
                                }
                        }
                        else
                        {
                                ZipEntry zipEntry = new ZipEntry(inputFilePath);
                                zos.putNextEntry(zipEntry);

                                FileInputStream fis = null;
                                BufferedInputStream origin = null;

                                try
                                {
                                        fis = new FileInputStream(fileName);
                                        origin = new BufferedInputStream(fis, buffer);

                                        while ((count = origin.read(data, 0, buffer)) != -1)
                                        {
                                                zos.write(data, 0, count);
                                        }
                                }
                                catch (IOException ioe)
                                {
                                        ioe.printStackTrace();
                                }
                                finally
                                {
                                        if (fis != null)
                                        {
                                                try
                                                {
                                                        fis.close();
                                                }
                                                catch (IOException e)
                                                {
                                                        //ignore
                                                }
                                        }

                                        if (origin != null)
                                        {
                                                try
                                                {
                                                        origin.close();
                                                }
                                                catch (IOException e)
                                                {
                                                        //ignore
                                                }
                                        }
                                }
                        }
                }
                finally
                {
                        zos.closeEntry();
                }
        }

        public static String unZip(String zipFileName, String extractedFile,
                                   String pathOfExtract)
        {
                String nameOfExtractedFile = null;
                OutputStream out = null;
                ZipInputStream in = null;

                try
                {
                        IOUtil.mkDir(pathOfExtract);

                        String pathAndName = null;
                        //  Input stream for the zip file (package)
                        in = new ZipInputStream(new FileInputStream(zipFileName));
                        LogUtil.debug("core", "[ZipUtil]unZip--ZipInputStream " + in);

                        //  Cut the path off of the name of the file. (for writing the file)
                        int indexOfFileBeginning = extractedFile.lastIndexOf("/") + 1;
                        nameOfExtractedFile = extractedFile.substring(indexOfFileBeginning);
                        pathAndName = pathOfExtract + "\\" + nameOfExtractedFile;

                        //  Ouput stream for the extracted file
                        out = new FileOutputStream(pathAndName);

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
                                        LogUtil.debug("core",
                                                "[ZipUtil]unZip--Found file to extract...  extracting to " +
                                                        pathOfExtract);

                                        flag = 1;
                                }
                        }

                        while ((len = in.read(buf)) > 0)
                        {
                                out.write(buf, 0, len);
                        }
                }
                catch (IOException e)
                {
                        LogUtil.debug("core", "[ZipUtil]unZip--IO Exception Caught: " + e);
                }
                finally
                {
                        try
                        {
                                out.close();
                        }
                        catch (Exception ex)
                        {
                        }

                        try
                        {
                                in.close();
                        }
                        catch (Exception ex)
                        {
                        }
                }

                return nameOfExtractedFile;
        }

        public static void unZip(String zipFileName, File outputDir)
                throws IOException
        {
                org.apache.tools.zip.ZipFile archive = null;

                try
                {
                        int buffer = 16384;
                        archive = new org.apache.tools.zip.ZipFile(zipFileName, "GBK");

                        String entryName = null;

                        //IF the output directory is not found,creat it aotomatically.
                        if (!outputDir.exists())
                        {
                                outputDir.mkdirs();
                        }

                        String outputDirName = outputDir.getCanonicalPath();

                        if (!outputDirName.endsWith(File.separator))
                        {
                                outputDirName = outputDirName + File.separator;
                        }

                        // do our own buffering; reuse the same buffer.
                        byte[] buffers = new byte[buffer];

                        // Loop through each Zip file entry
                        for (Enumeration e = archive.getEntries(); e.hasMoreElements();)
                        {
                                // get the next entry in the archive
                                org.apache.tools.zip.ZipEntry entry = (org.apache.tools.zip.ZipEntry) e.nextElement();
                                entryName = entry.getName();
                                entryName = entryName.replace('/', File.separatorChar)
                                        .replace('\\', File.separatorChar);

                                if (!entry.isDirectory())
                                {
                                        String filename = outputDirName + entryName;
                                        java.io.File destFile = new File(filename);

                                        String parent = destFile.getParent();

                                        if (parent != null)
                                        {
                                                java.io.File parentFile = new java.io.File(parent);

                                                if (!parentFile.exists())
                                                {
                                                        // create the chain of subdirs to the file
                                                        parentFile.mkdirs();
                                                }
                                        }

                                        // get a stream of the archive entry's bytes
                                        InputStream in = null;

                                        // open a stream to the destination file
                                        OutputStream outStream = null;

                                        try
                                        {
                                                in = archive.getInputStream(entry);
                                                outStream = new FileOutputStream(filename);

                                                // repeat reading into buffer and writing buffer to file,
                                                // until done.  count will always be # bytes read, until
                                                // EOF when it is -1.
                                                int count;

                                                while ((count = in.read(buffers)) != -1)
                                                {
                                                        outStream.write(buffers, 0, count);
                                                }
                                        }
                                        finally
                                        {
                                                if (outStream != null)
                                                {
                                                        try
                                                        {
                                                                outStream.close();
                                                        }
                                                        catch (IOException ee)
                                                        {
                                                                // ignore
                                                        }
                                                }

                                                if (in != null)
                                                {
                                                        try
                                                        {
                                                                in.close();
                                                        }
                                                        catch (IOException ee)
                                                        {
                                                                // ignore
                                                        }
                                                }
                                        }
                                }
                                else
                                {
                                        File file = new File(outputDirName + entryName);

                                        if (!file.exists())
                                        {
                                                file.mkdirs();
                                        }
                                }
                        }
                }
                catch (IOException ioe)
                {
                        ioe.printStackTrace();
                }
                finally
                {
                        if (archive != null)
                        {
                                try
                                {
                                        archive.close();
                                }
                                catch (IOException e)
                                {
                                        //ignore
                                }
                        }
                }
        }

        public static void unZip(String zipFileName, String outputDirName)
                throws IOException
        {
                if (!outputDirName.endsWith(File.separator))
                {
                        outputDirName = outputDirName + File.separator;
                }

                File outputDir = new File(outputDirName);

                unZip(zipFileName, outputDir);
        }
}
