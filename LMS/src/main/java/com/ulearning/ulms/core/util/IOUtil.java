/**
 * IOUtil.java.
 * User: fengch  Date: 2004-8-5
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.core.util;

import org.xml.sax.InputSource;

import java.io.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class IOUtil
{
        private String path;

        /**
         * create the desired dir.
         *
         * @param dir
         */
        public static void mkDir(String dir)
        {
                java.io.File theDir = new java.io.File(dir);

                //  The directory should not exist yet
                if (!theDir.isDirectory())
                {
                        theDir.mkdirs();
                }
        }

        /**
         * return the file ext name.
         *
         * @param url
         * @return
         */
        public static String getFileExtName(String url)
        {
                String str = null;

                //System.out.println("[IOUtil]getFileExtName----url = " + url);
                try
                {
                        int beginIndex = url.lastIndexOf(".");

                        if (beginIndex == -1)
                        {
                                str = "";
                        }
                        else
                        {
                                str = url.substring(beginIndex);
                        }
                }
                catch (Exception ex)
                {
                        ex.printStackTrace();
                }

                return str;
        }

        public String getPath()
        {
                return path;
        }

        public void setPath(String path)
        {
                this.path = path;
        }

        //得到文件内容
        public String getFileinfo(String tem_path, String filename)
                throws Exception
        {
                String path_new = "";
                path_new = tem_path;

                File fileName = new File(path_new, filename);

                if (fileName.exists())
                {
                        FileReader fr = new FileReader(path_new + "\\" + filename);
                        int c = fr.read();
                        String flieinfo = "";

                        while (c != -1)
                        {
                                flieinfo = flieinfo + (char) c + "";
                                c = fr.read();

                                if (c == 13)
                                {
                                        flieinfo = flieinfo + "\n";
                                        fr.skip(1L);
                                        c = fr.read();
                                }
                        }

                        fr.close();

                        return flieinfo;
                }
                else
                {
                        return null;
                }
        }

        //写入文件内容
        public boolean isSaveFile(String tem_path, String filename, String fileinto)
                throws Exception
        {
                String path_new = "";
                path_new = tem_path;

                File fileName = new File(path_new, filename);

                if (fileName.exists())
                {
                        fileName.delete();

                        FileWriter fw = new FileWriter(path_new + filename);
                        fw.write(fileinto);
                        fw.close();

                        return true;
                }
                else
                {
                        FileWriter fw = new FileWriter(path_new + filename);
                        fw.write(fileinto);
                        fw.close();

                        return true;
                }
        }

        /**
         * 创建文件夹
         *
         * @param tem_path 系统路径
         * @param dirname  文件名
         * @return
         */
        public boolean ismakeDir(String tem_path, String dirname)
        {
                String path_new = "";
                path_new = path + "\\" + tem_path + "\\" + dirname;

                File dirName = new File(path_new);

                if (dirName.exists())
                {
                        return false;
                }
                else
                {
                        //System.out.println(path_new);
                        return dirName.mkdir();
                }
        }

        //创建文件
        public boolean ismakeFile(String tem_path, String filename)
                throws Exception
        {
                String path_new = "";
                path_new = path + "\\" + tem_path;

                File fileName = new File(path_new, filename);

                if (fileName.exists())
                {
                        return false;
                }
                else
                {
                        System.out.println(path_new);
                        fileName.createNewFile();

                        return true;
                }
        }

        //得到文件列表
        public Collection getFilelist(String tem_path, String type)
                throws Exception
        {
                String path_new = "";
                path_new = tem_path;

                File d = new File(path_new);
                File[] list = d.listFiles();
                ArrayList al = new ArrayList();

                if (type.equals("D"))
                {
                        for (int i = 0; i < list.length; i++)
                        {
                                if (list[i].isDirectory())
                                {
                                        al.add(list[i].getName());
                                }
                        }
                }
                else if (type.equals("F"))
                {
                        for (int i = 0; i < list.length; i++)
                        {
                                if (!list[i].isDirectory())
                                {
                                        al.add(list[i].getName());
                                }
                        }
                }

                return al;
        }

        /**
         * return the file name.
         *
         * @param url
         * @return
         */
        public static String getFileName(String url)
        {
                if (url == null)
                {
                        return null;
                }

                String str = null;

                //System.out.println("[IOUtil]getFileName url = " + url);
                try
                {
                        int beginIndex1 = url.lastIndexOf("\\") + 1;
                        int beginIndex2 = url.lastIndexOf("/") + 1;

                        if (beginIndex1 > beginIndex2)
                        {
                                str = url.substring(beginIndex1);
                        }
                        else
                        {
                                str = url.substring(beginIndex2);
                        }
                }
                catch (Exception ex)
                {
                        ex.printStackTrace();
                }

                return str;
        }

        /**
         * return the file path.
         *
         * @param url
         * @return
         */
        public static String getFilePath(String url)
        {
                String str = null;

                //System.out.println("[IOUtil]getFileName url = " + url);
                try
                {
                        int beginIndex1 = url.lastIndexOf("\\") + 1;
                        int beginIndex2 = url.lastIndexOf("/") + 1;

                        if (beginIndex1 > beginIndex2)
                        {
                                str = url.substring(0, beginIndex1);
                        }
                        else
                        {
                                str = url.substring(0, beginIndex2);
                        }
                }
                catch (Exception ex)
                {
                        ex.printStackTrace();
                }

                return str;
        }

        /**
         * 判断文件是否存在。
         *
         * @param filePath
         * @return
         */
        public static boolean isExistFile(String filePath)
        {
                boolean isExistFile = false;

                try
                {
                        File f = new File(filePath);

                        isExistFile = f.exists();
                }
                catch (Exception ex)
                {
                        ex.printStackTrace();
                }

                return isExistFile;
        }

        public static String getNewFileName(String fileName)
        {
                StringBuffer newAbsoluteFilePath_buffer = new StringBuffer();

                for (int i = 0; i < newAbsoluteFilePath_buffer.length(); i++)
                {
                        int c = (int) newAbsoluteFilePath_buffer.charAt(i);

                        if (c > 125)
                        {
                                newAbsoluteFilePath_buffer.append("o");
                        }
                        else
                        {
                                newAbsoluteFilePath_buffer.append(c);
                        }
                }

                return newAbsoluteFilePath_buffer.toString();
        }

        /**
         * 返回目录下的可以存在的文件名。若是中文文件名先将其
         * 如参数为"d:\a.txt",若已经存在此文件，则返回"d:\a_1.txt"
         *
         * @return
         */
        public static String getNewAbsoluteFilePath(String absoluteFilePath)
        {
                String newAbsoluteFilePath = absoluteFilePath;

                String fileExt = "";
                String fileName = null;
                String filePath = null;

                if (isExistFile(newAbsoluteFilePath))
                {
                        int dotBeginIndex = absoluteFilePath.lastIndexOf('.');
                        int separatorBeginIndex = absoluteFilePath.lastIndexOf(File.separator);

                        if (dotBeginIndex != -1)
                        {
                                fileExt = absoluteFilePath.substring(dotBeginIndex);
                                fileName = absoluteFilePath.substring(separatorBeginIndex + 1,
                                        dotBeginIndex);
                        }
                        else
                        {
                                fileName = absoluteFilePath.substring(separatorBeginIndex + 1);
                        }

                        filePath = absoluteFilePath.substring(0, separatorBeginIndex + 1);

                        //LogUtil.debug("system","[IOUtil]getNewAbsoluteFilePath--------filePath = " + filePath);
                        //LogUtil.debug("system","[IOUtil]getNewAbsoluteFilePath--------fileName = " + fileName);
                        //LogUtil.debug("system","[IOUtil]getNewAbsoluteFilePath--------fileExt = " + fileExt);
                        int i = 0;

                        while (isExistFile(newAbsoluteFilePath))
                        {
                                i++;
                                newAbsoluteFilePath = filePath + fileName + "_" + i + fileExt;
                        }
                }

                return newAbsoluteFilePath;
        }

        /**
         * 返回链接地址指向的文件名称.
         *
         * @param url
         * @return
         */
        public static String getURLFileName(String url)
        {
                String str = null;

                try
                {
                        List l = StringUtil.split(url, "/");

                        if (l != null)
                        {
                                str = (String) l.get(l.size() - 1);
                        }
                }
                catch (Exception ex)
                {
                }

                return str;
        }

        /**
         * to judge if the file exisits.
         */
        public static boolean isFile(String f)
        {
                java.io.File theFile = new java.io.File(f);

                return theFile.isFile();
        }

        /**
         * to judge if the dir exisits.
         *
         * @param f
         * @return
         */
        public static boolean isDir(String f)
        {
                java.io.File theDir = new java.io.File(f);

                return theDir.isDirectory();
        }

        /**
         * Input:   fileName - String
         * Output:  is - InputSource
         * <p/>
         * Description:  This function returns the input source.
         */
        public static InputSource setUpInputSource(String fileName)
        {
                InputSource is = setupFileSource(fileName);

                return is;
        }

        /**
         * Input:   fileName - String
         * Output:  is - InputSource
         * <p/>
         * Description:  This function returns the input source.
         */
        private static InputSource setupFileSource(String filename)
        {
                try
                {
                        java.io.File xmlFile = new java.io.File(filename);

                        if (xmlFile.isFile())
                        {
                                FileReader fr = new FileReader(xmlFile);
                                InputSource is = new InputSource(fr);

                                return is;
                        }
                }
                catch (NullPointerException npe)
                {
                        System.out.println("Null pointer exception" + npe);
                }
                catch (SecurityException se)
                {
                        System.out.println("Security Exception" + se);
                }
                catch (FileNotFoundException fnfe)
                {
                        System.out.println("File Not Found Exception" + fnfe);
                }

                return new InputSource();
        }

        /**
         * Delete a whole Folder or a File
         *
         * @param deleteFile
         */
        public static void delAllFile(File deleteFile)
        {
                try
                {
                        //File can't exist
                        if (!deleteFile.exists())
                        {
                                throw new IOException("File can't exist:" +
                                        deleteFile.getName());
                        }

                        //Save the result
                        boolean rslt = true;

                        //First try to delete the file derectly
                        if (!(rslt = deleteFile.delete()))
                        {
                                //If the folder is not empty,give it a recursive delete action
                                File[] subs = deleteFile.listFiles();

                                for (int i = 0; i <= (subs.length - 1); i++)
                                {
                                        if (subs[i].isDirectory())
                                        {
                                                delAllFile(subs[i]); //recursive delete the content in sub folder
                                        }

                                        rslt = subs[i].delete(); //Delte the sub folder itself
                                }

                                rslt = deleteFile.delete(); //Delte the root folder itself
                        }

                        if (!rslt)
                        {
                                throw new IOException("Can't delete this File:" +
                                        deleteFile.getName());
                        }
                }
                catch (Exception ex)
                {
                        ex.printStackTrace();
                }
        }
}
