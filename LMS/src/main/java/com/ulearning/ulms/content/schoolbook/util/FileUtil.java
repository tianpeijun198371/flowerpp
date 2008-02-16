package com.ulearning.ulms.content.schoolbook.util;

import org.apache.log4j.Logger;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;


public class FileUtil
{
        private static final Logger log = Logger.getLogger(FileUtil.class);

        public static boolean checkDir(String path, boolean create)
        {
                log.debug("checking path=" + path);

                File file = new File(path);
                boolean isExist = file.exists();

                if (!isExist && create)
                {
                        isExist = file.mkdirs();
                        log.debug("create path success !!");
                }

                return isExist;
        }

        public static boolean copyTo(String src, String dest)
        {
                boolean res = false;

                try
                {
                        InputStream in = new FileInputStream(src);
                        OutputStream out = new FileOutputStream(dest);
                        copyTo(in, out);
                        res = true;
                }
                catch (Exception e)
                {
                        // e.printStackTrace();
                        log.error("copy file error !!" + e.getMessage());
                }

                log.debug("copy " + src + " to " + dest + " result is " +
                        Boolean.toString(res));

                return res;
        }

        public static boolean copyTo(InputStream file, String dest)
        {
                boolean res = false;

                try
                {
                        InputStream in = new BufferedInputStream(file);
                        OutputStream out = new FileOutputStream(dest);
                        copyTo(in, out);
                        res = true;
                }
                catch (Exception e)
                {
                        log.error("copy file error !!" + e.getMessage());
                }

                log.debug("copy stream to " + dest + " result is " +
                        Boolean.toString(res));

                return res;
        }

        public static boolean copyTo(InputStream in, OutputStream out, boolean close)
        {
                boolean res = false;
                byte[] buffer = new byte[4096];
                int len = 0;

                try
                {
                        while ((len = in.read(buffer)) > 0)
                        {
                                out.write(buffer, 0, len);
                        }
                }
                catch (Exception e)
                {
                        log.error("copy file error !!" + e.getMessage());
                }
                finally
                {
                        if (close)
                        {
                                try
                                {
                                        if (in != null)
                                        {
                                                in.close();
                                        }
                                }
                                catch (Exception e)
                                {
                                        e.printStackTrace();
                                }
                        }

                        try
                        {
                                if (out != null)
                                {
                                        out.close();
                                }
                        }
                        catch (Exception e)
                        {
                                e.printStackTrace();
                        }
                }

                res = true;
                log.debug("copy result is " + Boolean.toString(res));

                return res;
        }

        public static boolean copyTo(InputStream in, OutputStream out)
        {
                return copyTo(in, out, false);
        }

        public static boolean delDir(String path)
        {
                boolean result = false;
                File tempDir = new File(path);

                if (tempDir.exists() && tempDir.isDirectory())
                {
                        result = true;

                        File[] files = (new File(path + ".")).listFiles();

                        for (int i = 0; i < files.length; i++)
                        {
                                if (files[i].exists())
                                {
                                        if (files[i].isFile())
                                        {
                                                result = result && files[i].delete();
                                        }
                                        else
                                        {
                                                delDir(files[i].getAbsolutePath());
                                        }
                                }
                        }

                        result = result && tempDir.delete();
                }

                log.debug("del dir " + tempDir.getAbsolutePath() + " " + result +
                        " !!");

                return result;
        }

        public static boolean delFile(String path)
        {
                boolean result = false;
                File file = new File(path);

                if (file.isFile() && file.exists())
                {
                        result = file.delete();
                }

                log.debug("del file " + file.getAbsolutePath() + " " + result + " !!");

                return result;
        }

        public static long getFileSize(String uri)
        {
                File file = new File(uri);

                return file.length();
        }

        
}
