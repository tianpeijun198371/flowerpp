/*
 * Created on 2004-8-28
 * Author: Huaxia, Copyright (C) 2004, Huaxia.
 */
package com.ulearning.ulms.bloger.domain;

import com.ulearning.ulms.bloger.exception.ValidateException;


/**
 * Image object knows how to find the image file and it's url to access.<br>
 * getOriginalUrl() will return the url of the original image such as
 * "http://server/images/1024/4096.gif".<br>
 * getPreviewUrl() will return the url of the preview image (120x120) such as
 * "http://server/preview/1024/4096.gif".<br>
 *
 * @author Huaxia
 */
public class Image implements Validator
{
        public static final String TYPE_GIF = ".gif";
        public static final String TYPE_JPG = ".jpg";
        public static final String TYPE_PNG = ".png";
        private static final String ALIAS_JPEG = ".jpeg";
        private static final String ALIAS_JPE = ".jpe";
        private static final String PREVIEW = "pre";
        private int categoryId; // foreign key
        private int imageId; // primary key
        private boolean visible = true;
        private String title = "";
        private String imageType = TYPE_GIF;

        /**
         * Get the file name of this image. Such as "6688.gif".
         *
         * @return The file name of the image. Composed as: $Image.imageId$Image.filetype
         */
        public String getFilename()
        {
                return imageId + imageType;
        }

        /**
         * Get the preview file name of this image. Such as "6688.gif".
         *
         * @return The file name of the image. Composed as: "pre"$Image.imageId$Image.filetype
         */
        public String getPreviewFilename()
        {
                return PREVIEW + imageId + imageType;
        }

        public int getCategoryId()
        {
                return categoryId;
        }

        public void setCategoryId(int categoryId)
        {
                this.categoryId = categoryId;
        }

        public int getImageId()
        {
                return imageId;
        }

        public void setImageId(int imageId)
        {
                this.imageId = imageId;
        }

        public String getTitle()
        {
                return title;
        }

        public void setTitle(String title)
        {
                this.title = title;
        }

        public boolean getVisible()
        {
                return visible;
        }

        public void setVisible(boolean visible)
        {
                this.visible = visible;
        }

        public String getImageType()
        {
                return imageType;
        }

        public void setImageType(String imageType)
        {
                imageType = imageType.toLowerCase();

                if (imageType.equals(TYPE_GIF) || imageType.equals(TYPE_JPG) ||
                        imageType.equals(TYPE_PNG))
                {
                        this.imageType = imageType;
                }
                else if (imageType.equals(ALIAS_JPEG) || imageType.equals(ALIAS_JPE))
                {
                        this.imageType = TYPE_JPG;
                }
                else
                {
                        throw new IllegalArgumentException(
                                "The image file is invalid. Only \".gif\", \".jpg\" and \".png\" formats are allowed.");
                }
        }

        public void validate() throws ValidateException
        {
        }
}
