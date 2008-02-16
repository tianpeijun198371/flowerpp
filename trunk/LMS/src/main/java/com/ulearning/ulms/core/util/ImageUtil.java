/**
 * ImageUtil.java.
 * User: fengch Date: 2005-11-25 12:01:57
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.core.util;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class ImageUtil
{
        protected static Log logger = LogFactory.getLog(ImageUtil.class);
        /**
         * 生成缩略图图片，返回图片名称。
         * <p/>
         * 只能针对生成jpr,gif
         *
         * @param filePath
         * @return
         */
        public static String getImageThumbnails(String filePath)
        {
                String newFilePath = null;
                String fileName = "";
                String thumbnails = "";
                String fileExt = "";

                try
                {
                        java.io.File file = new java.io.File(filePath); //读入刚才上传的文件

                        String path = IOUtil.getFilePath(filePath);
                        fileName = IOUtil.getFileName(filePath);
                        fileExt = IOUtil.getFileExtName(filePath);

                        newFilePath = path + fileName + "_thumbnails" + fileExt; //新的缩略图保存地址
                        logger.info("newFilePath = " + newFilePath);

                        Image src = javax.imageio.ImageIO.read(file); //构造Image对象
                        float tagsize = 150;
                        int old_w = src.getWidth(null); //得到源图宽
                        int old_h = src.getHeight(null);
                        int new_w = 0;
                        int new_h = 0; //得到源图长
                        int tempsize;
                        float tempdouble;

                        //计算新图长宽
                        if (old_w > old_h)
                        {
                                if (old_w <= tagsize)
                                {
                                        new_w = old_w;
                                        new_h = old_h; //新图长宽不变
                                }
                                else
                                {
                                        tempdouble = old_w / tagsize;
                                        new_w = Math.round(old_w / tempdouble);
                                        new_h = Math.round(old_h / tempdouble);
                                }
                        }
                        else
                        {
                                if (old_h <= tagsize)
                                {
                                        new_w = old_w; //新图长宽不变
                                        new_h = old_h;
                                }
                                else
                                {
                                        tempdouble = old_h / tagsize;
                                        new_w = Math.round(old_w / tempdouble);
                                        new_h = Math.round(old_h / tempdouble);
                                }
                        }

                        BufferedImage tag = new BufferedImage(new_w, new_h,
                                BufferedImage.TYPE_INT_RGB);
                        tag.getGraphics().drawImage(src, 0, 0, new_w, new_h, null); //绘制缩小后的图

                        FileOutputStream newimage = new FileOutputStream(newFilePath); //输出到文件流
                        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(newimage);
                        encoder.encode(tag); //近JPEG编码
                        newimage.close();
                }
                catch (Exception ex)
                {
                        ex.printStackTrace();
                }

                return (fileName + "_thumbnails" + fileExt);
        }

        /**
         * 增加文字
         *
         * @param fontName  字体名称
         * @param fontStyle 字体风格，如正常Font.PLAIN，加粗Font.BOLD
         * @param fontSize  字体大小（像素）
         * @param color     颜色，如红色Color.red
         * @param content   文字
         * @param x         横坐标
         * @param y         纵坐标
         * @return
         */
        public static void drawString(String newFileName, String oldFileName, String fontName, int fontStyle, int fontSize,
                                      Color color, String content, int x, int y)
        {
                BufferedImage img = loadImageLocal(oldFileName);
                BufferedImage image = drawString(img, fontName, fontStyle, fontSize, color,
                        content, x, y);
                writeImageLocal(newFileName, image);
        }

        /**
         * 在多个地方增加多个文字
         *
         * @param fontNames  字体名称 数组
         * @param fontStyles 字体风格 数组，如正常Font.PLAIN，加粗Font.BOLD
         * @param fontSizes  字体大小 数组（像素）
         * @param colors     颜色  数组，如红色Color.red
         * @param contents   文字
         * @param x          横坐标
         * @param y          纵坐标
         * @return
         */
        public static void drawString(String newFileName, String oldFileName, String[] fontNames, int[] fontStyles, int[] fontSizes,
                                      Color[] colors, String[] contents, int[] x, int[] y)
        {
                BufferedImage img = loadImageLocal(oldFileName);
                BufferedImage image = drawString(img, fontNames, fontStyles, fontSizes, colors,
                        contents, x, y);
                writeImageLocal(newFileName, image);
        }

        /**
         * 给图片加水印，但不改变大小
         *
         * @param strOriginalFileName  String(原始文件)
         * @param strWaterMarkFileName String(水印后)
         */
        public void waterMark(String strOriginalFileName, String strWaterMarkFileName)
        {
                try
                {
                        //源文件
                        File fileOriginal = new File(strOriginalFileName);
                        Image imageOriginal = ImageIO.read(fileOriginal);
                        int widthOriginal = imageOriginal.getWidth(null);
                        int heightOriginal = imageOriginal.getHeight(null);
                        logger.info("widthOriginal:" + widthOriginal + "theightOriginal:" + heightOriginal);

                        BufferedImage bufImage = new BufferedImage(widthOriginal, heightOriginal, BufferedImage.TYPE_INT_RGB);
                        Graphics g = bufImage.createGraphics();
                        g.drawImage(imageOriginal, 0, 0, widthOriginal, heightOriginal, null);

                        //水印文件
                        File fileWaterMark = new File(strWaterMarkFileName);
                        Image imageWaterMark = ImageIO.read(fileWaterMark);
                        int widthWaterMark = imageWaterMark.getWidth(null);
                        int heightWaterMark = imageWaterMark.getHeight(null);
                        logger.info("widthWaterMark:" + widthWaterMark + "theightWaterMark:" + heightWaterMark);

                        //水印文件在源文件的右下角
                        g.drawImage(imageWaterMark, widthOriginal - widthWaterMark, heightOriginal - heightWaterMark,
                                widthWaterMark, heightWaterMark, null);
                        g.dispose();

                        FileOutputStream fos = new FileOutputStream(strOriginalFileName);
                        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(fos);
                        encoder.encode(bufImage);
                        fos.flush();
                        fos.close();
                        fos = null;
                }
                catch (Exception e)
                {
                        e.printStackTrace();
                }
        }

        /**
         * 缩放图片的大小
         *
         * @param newFile 新图文件
         * @param OldFile 原图文件
         * @param nw      转换后的宽度
         * @param nh      转换后的高度
         */
        public void zoom(String newFile, String OldFile, int nw, int nh)
        {

                try
                {

                        File fi = new File(newFile); //大图文件
                        File fo = new File(OldFile); //将要转换出的小图文件

                        AffineTransform transform = new AffineTransform();
                        BufferedImage bis = ImageIO.read(fi);

                        int w = bis.getWidth();                 //原图片的宽
                        int h = bis.getHeight();                 //原图片的高
                        double sx = (double) nw / w;
                        double sy = (double) nh / h;
                        transform.setToScale(sx, sy);

                        logger.info("Old Width and Height is: " + w + " " + h);
                        logger.info("New Width and Height is: " + nw + " " + nh);
                        logger.info("");

                        AffineTransformOp ato = new AffineTransformOp(transform, null);
                        BufferedImage bid = new BufferedImage(nw, nh, BufferedImage.TYPE_3BYTE_BGR);
                        ato.filter(bis, bid);
                        ImageIO.write(bid, "jpg", fo);
                }
                catch (Exception e)
                {

                        e.printStackTrace();
                }
        }

        /**
         * 缩放图片的大小
         *
         * @param newFile   新图文件
         * @param OldFile   原图文件
         * @param scaleType 根据宽或者根据高进行缩放 width or height or original shape
         * @param scaleNum  缩放的数值
         */
        public void zoomByScale(String newFile, String OldFile, String scaleType, int scaleNum)
        {

                try
                {
                        // 转换后的宽度,高度
                        int nw = 0, nh = 0;
                        File fi = new File(newFile); //大图文件
                        File fo = new File(OldFile); //将要转换出的小图文件

                        AffineTransform transform = new AffineTransform();
                        BufferedImage bis = ImageIO.read(fi);

                        int w = bis.getWidth();                 //原图片的宽
                        int h = bis.getHeight();                 //原图片的高

                        double scale = (double) w / h;         //计算出原图片的宽/高

                        if (scaleType.equals("width"))
                        {
                                logger.info("zoom by width:" + scaleNum);
                                nw = scaleNum;
                                nh = (nw * h) / w;        //根据比例计算出缩小后高度
                        }
                        else if (scaleType.equals("height"))
                        {
                                logger.info("zoom by height:" + scaleNum);
                                nh = scaleNum;
                                nw = (nh * w) / h;        //根据比例计算出缩小后高度
                        }

                        if (nw > w && nh > h)
                        {

                                logger.info("zoom original shape");
                                nw = w;
                                nh = h;
                        }


                        double sx = (double) nw / w;
                        double sy = (double) nh / h;
                        transform.setToScale(sx, sy);

                        logger.info("Old Width and Height is: " + w + " " + h);
                        logger.info("New Width and Height is: " + nw + " " + nh);
                        logger.info("");

                        AffineTransformOp ato = new AffineTransformOp(transform, null);
                        BufferedImage bid = new BufferedImage(nw, nh, BufferedImage.TYPE_3BYTE_BGR);
                        ato.filter(bis, bid);
                        ImageIO.write(bid, "jpg", fo);
                }
                catch (Exception e)
                {

                        e.printStackTrace();
                }
        }

        /**
         * 导入本地图片到缓冲区
         */
        private static BufferedImage loadImageLocal(String imgName)
        {
                try
                {
                        return ImageIO.read(new File(imgName));
                }
                catch (IOException e)
                {
                        e.printStackTrace();
                }
                return null;
        }

        /**
         * 导入网络图片到缓冲区
         */
        private static BufferedImage loadImageUrl(String imgName)
        {
                try
                {
                        URL url = new URL(imgName);
                        return ImageIO.read(url);
                }
                catch (IOException e)
                {
                        e.printStackTrace();
                        ;
                }
                return null;
        }

        /**
         * 生成新图片到本地
         */
        private static void writeImageLocal(String newImage, BufferedImage img)
        {
                if (newImage != null && img != null)
                {
                        try
                        {
                                File outputfile = new File(newImage);
                                ImageIO.write(img, "jpeg", outputfile);
                        }
                        catch (IOException e)
                        {
                                e.printStackTrace();
                        }

                }
        }

        /**
         * 增加文字
         *
         * @param img       图片缓冲区
         * @param fontName  字体名称
         * @param fontStyle 字体风格，如正常Font.PLAIN，加粗Font.BOLD
         * @param fontSize  字体大小（像素）
         * @param color     颜色，如红色Color.red
         * @param content   文字
         * @param x         横坐标
         * @param y         纵坐标
         * @return
         */
        private static BufferedImage drawString(BufferedImage img, String fontName, int fontStyle, int fontSize,
                                                Color color, String content, int x, int y)
        {
                int x_result = 0;
                int y_result = 0;
                Graphics2D g = null;
                Font font = new Font(fontName, fontStyle, fontSize);

                try
                {
                        int w = img.getWidth();
                        int h = img.getHeight();
                        g = img.createGraphics();
                        g.setBackground(Color.WHITE);
                        if (font != null)
                        {
                                g.setFont(font);
                        }
                        if (color != null)
                        {
                                g.setColor(color);
                        }

                        // 验证输出位置的纵坐标和横坐标
                        if (x >= h || y >= w)
                        {
                                x_result = h - fontSize + 2;
                                y_result = w;
                        }
                        else
                        {
                                x_result = x;
                                y_result = y;
                        }
                        if (content != null)
                        {
                                g.drawString(content, x_result, y_result);
                        }
                        g.dispose();
                }
                catch (Exception e)
                {
                        e.printStackTrace();
                }

                return img;
        }

        /**
         * 在多个地方增加多个文字
         *
         * @param img        图片缓冲区
         * @param fontNames  字体名称 数组
         * @param fontStyles 字体风格 数组，如正常Font.PLAIN，加粗Font.BOLD
         * @param fontSizes  字体大小 数组（像素）
         * @param colors     颜色  数组，如红色Color.red
         * @param contents   文字
         * @param x          横坐标
         * @param y          纵坐标
         * @return
         */
        private static BufferedImage drawString(BufferedImage img, String[] fontNames, int[] fontStyles, int[] fontSizes,
                                                Color[] colors, String[] contents, int[] x, int[] y)
        {
                int x_result = 0;
                int y_result = 0;
                Graphics2D g = null;

                try
                {
                        int w = img.getWidth();
                        int h = img.getHeight();
                        g = img.createGraphics();
                        g.setBackground(Color.WHITE);

                        for (int i = 0; i < contents.length; i++)
                        {
                                String str = contents[i];
                                Font font = new Font(fontNames[i], fontStyles[i], fontSizes[i]);
                                if (font != null)
                                {
                                        g.setFont(font);
                                }
                                if (colors[i] != null)
                                {
                                        g.setColor(colors[i]);
                                }
                                // 验证输出位置的纵坐标和横坐标
                                if (x[i] >= w || y[i] >= h)
                                {
                                        x_result = h - fontSizes[i] + 2;
                                        y_result = w;
                                }
                                else
                                {
                                        x_result = x[i];
                                        y_result = y[i];
                                }
                                if (str != null)
                                {
                                        g.drawString(str, x_result, y_result);
                                }
                        }
                        g.dispose();
                }
                catch (Exception e)
                {
                        e.printStackTrace();
                }

                return img;
        }
}
