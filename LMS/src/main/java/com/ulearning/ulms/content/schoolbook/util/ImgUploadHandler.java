package com.ulearning.ulms.content.schoolbook.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.InputStream;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.Random;


public class ImgUploadHandler
{
        protected static Log log = LogFactory.getLog(ImgUploadHandler.class);
        private static DateFormat timeFormat = new SimpleDateFormat("HHmmss");
        private static DateFormat dateFormat = new SimpleDateFormat("yyyyMM");
        private static Random random = new Random();

        public static String getFileName(String filename)
        {
                String name = filename.substring(filename.lastIndexOf("."),
                        filename.length());
                String randomNum = String.valueOf(random.nextInt(100000));
                String time = timeFormat.format(new Date());

                return time + randomNum + name;
        }

        public static boolean handlerFiles(InputStream in, String filename)
        {
                log.debug("upload file");

                boolean result = true;
                String descfile = getContentPath() + filename;

                if (!FileUtil.checkDir(getContentPath(), true))
                {
                        result = false;
                }

                if (!FileUtil.copyTo(in, descfile))
                {
                        result = false;
                }

                return result;
        }

        public static String getContentPath()
        {
                return "schoolbook/" + dateFormat.format(new Date()) + "/";
        }

        public static void main(String[] args)
        {
                System.out.println(ImgUploadHandler.getContentPath());
                System.out.println(ImgUploadHandler.getFileName("image.pig"));
        }
}
