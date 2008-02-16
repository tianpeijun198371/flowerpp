/*
 * Created on 2004-10-5
 * Author: Huaxia, Copyright (C) 2004, Huaxia.
 */
package com.ulearning.ulms.bloger.web;


/**
 * To map the skin id to the path of the skin.
 *
 * @author Huaxia
 */
public final class SkinManager
{
        private static SkinManager instance = new SkinManager();
        private String[] skinNames;
        private String[] skinDirs;
        private int count;

        private SkinManager()
        {
                // search the directory "skin":
                count = 2;
                skinNames = new String[count];
                skinNames[0] = "simple";
                skinNames[1] = "bluesky";
                skinDirs = new String[count];
                skinDirs[0] = "skin/simple/";
                skinDirs[1] = "skin/bluesky/";
        }

        public static SkinManager getSkinManager()
        {
                return instance;
        }

        public String getSkin(int skinId)
        {
                if ((skinId < 0) || (skinId >= count))
                {
                        return skinDirs[0];
                }

                return skinDirs[skinId];
        }

        public String[] getAllSkins()
        {
                return skinNames;
        }
}
