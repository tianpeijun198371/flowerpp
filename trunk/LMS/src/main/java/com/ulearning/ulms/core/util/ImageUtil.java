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
         * ��������ͼͼƬ������ͼƬ���ơ�
         * <p/>
         * ֻ���������jpr,gif
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
                        java.io.File file = new java.io.File(filePath); //����ղ��ϴ����ļ�

                        String path = IOUtil.getFilePath(filePath);
                        fileName = IOUtil.getFileName(filePath);
                        fileExt = IOUtil.getFileExtName(filePath);

                        newFilePath = path + fileName + "_thumbnails" + fileExt; //�µ�����ͼ�����ַ
                        logger.info("newFilePath = " + newFilePath);

                        Image src = javax.imageio.ImageIO.read(file); //����Image����
                        float tagsize = 150;
                        int old_w = src.getWidth(null); //�õ�Դͼ��
                        int old_h = src.getHeight(null);
                        int new_w = 0;
                        int new_h = 0; //�õ�Դͼ��
                        int tempsize;
                        float tempdouble;

                        //������ͼ����
                        if (old_w > old_h)
                        {
                                if (old_w <= tagsize)
                                {
                                        new_w = old_w;
                                        new_h = old_h; //��ͼ������
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
                                        new_w = old_w; //��ͼ������
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
                        tag.getGraphics().drawImage(src, 0, 0, new_w, new_h, null); //������С���ͼ

                        FileOutputStream newimage = new FileOutputStream(newFilePath); //������ļ���
                        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(newimage);
                        encoder.encode(tag); //��JPEG����
                        newimage.close();
                }
                catch (Exception ex)
                {
                        ex.printStackTrace();
                }

                return (fileName + "_thumbnails" + fileExt);
        }

        /**
         * ��������
         *
         * @param fontName  ��������
         * @param fontStyle ������������Font.PLAIN���Ӵ�Font.BOLD
         * @param fontSize  �����С�����أ�
         * @param color     ��ɫ�����ɫColor.red
         * @param content   ����
         * @param x         ������
         * @param y         ������
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
         * �ڶ���ط����Ӷ������
         *
         * @param fontNames  �������� ����
         * @param fontStyles ������ ���飬������Font.PLAIN���Ӵ�Font.BOLD
         * @param fontSizes  �����С ���飨���أ�
         * @param colors     ��ɫ  ���飬���ɫColor.red
         * @param contents   ����
         * @param x          ������
         * @param y          ������
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
         * ��ͼƬ��ˮӡ�������ı��С
         *
         * @param strOriginalFileName  String(ԭʼ�ļ�)
         * @param strWaterMarkFileName String(ˮӡ��)
         */
        public void waterMark(String strOriginalFileName, String strWaterMarkFileName)
        {
                try
                {
                        //Դ�ļ�
                        File fileOriginal = new File(strOriginalFileName);
                        Image imageOriginal = ImageIO.read(fileOriginal);
                        int widthOriginal = imageOriginal.getWidth(null);
                        int heightOriginal = imageOriginal.getHeight(null);
                        logger.info("widthOriginal:" + widthOriginal + "theightOriginal:" + heightOriginal);

                        BufferedImage bufImage = new BufferedImage(widthOriginal, heightOriginal, BufferedImage.TYPE_INT_RGB);
                        Graphics g = bufImage.createGraphics();
                        g.drawImage(imageOriginal, 0, 0, widthOriginal, heightOriginal, null);

                        //ˮӡ�ļ�
                        File fileWaterMark = new File(strWaterMarkFileName);
                        Image imageWaterMark = ImageIO.read(fileWaterMark);
                        int widthWaterMark = imageWaterMark.getWidth(null);
                        int heightWaterMark = imageWaterMark.getHeight(null);
                        logger.info("widthWaterMark:" + widthWaterMark + "theightWaterMark:" + heightWaterMark);

                        //ˮӡ�ļ���Դ�ļ������½�
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
         * ����ͼƬ�Ĵ�С
         *
         * @param newFile ��ͼ�ļ�
         * @param OldFile ԭͼ�ļ�
         * @param nw      ת����Ŀ��
         * @param nh      ת����ĸ߶�
         */
        public void zoom(String newFile, String OldFile, int nw, int nh)
        {

                try
                {

                        File fi = new File(newFile); //��ͼ�ļ�
                        File fo = new File(OldFile); //��Ҫת������Сͼ�ļ�

                        AffineTransform transform = new AffineTransform();
                        BufferedImage bis = ImageIO.read(fi);

                        int w = bis.getWidth();                 //ԭͼƬ�Ŀ�
                        int h = bis.getHeight();                 //ԭͼƬ�ĸ�
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
         * ����ͼƬ�Ĵ�С
         *
         * @param newFile   ��ͼ�ļ�
         * @param OldFile   ԭͼ�ļ�
         * @param scaleType ���ݿ���߸��ݸ߽������� width or height or original shape
         * @param scaleNum  ���ŵ���ֵ
         */
        public void zoomByScale(String newFile, String OldFile, String scaleType, int scaleNum)
        {

                try
                {
                        // ת����Ŀ��,�߶�
                        int nw = 0, nh = 0;
                        File fi = new File(newFile); //��ͼ�ļ�
                        File fo = new File(OldFile); //��Ҫת������Сͼ�ļ�

                        AffineTransform transform = new AffineTransform();
                        BufferedImage bis = ImageIO.read(fi);

                        int w = bis.getWidth();                 //ԭͼƬ�Ŀ�
                        int h = bis.getHeight();                 //ԭͼƬ�ĸ�

                        double scale = (double) w / h;         //�����ԭͼƬ�Ŀ�/��

                        if (scaleType.equals("width"))
                        {
                                logger.info("zoom by width:" + scaleNum);
                                nw = scaleNum;
                                nh = (nw * h) / w;        //���ݱ����������С��߶�
                        }
                        else if (scaleType.equals("height"))
                        {
                                logger.info("zoom by height:" + scaleNum);
                                nh = scaleNum;
                                nw = (nh * w) / h;        //���ݱ����������С��߶�
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
         * ���뱾��ͼƬ��������
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
         * ��������ͼƬ��������
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
         * ������ͼƬ������
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
         * ��������
         *
         * @param img       ͼƬ������
         * @param fontName  ��������
         * @param fontStyle ������������Font.PLAIN���Ӵ�Font.BOLD
         * @param fontSize  �����С�����أ�
         * @param color     ��ɫ�����ɫColor.red
         * @param content   ����
         * @param x         ������
         * @param y         ������
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

                        // ��֤���λ�õ�������ͺ�����
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
         * �ڶ���ط����Ӷ������
         *
         * @param img        ͼƬ������
         * @param fontNames  �������� ����
         * @param fontStyles ������ ���飬������Font.PLAIN���Ӵ�Font.BOLD
         * @param fontSizes  �����С ���飨���أ�
         * @param colors     ��ɫ  ���飬���ɫColor.red
         * @param contents   ����
         * @param x          ������
         * @param y          ������
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
                                // ��֤���λ�õ�������ͺ�����
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
