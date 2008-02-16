/**
 * Created by IntelliJ IDEA.
 * User: dengj
 * Date: Mar 25, 2004
 * Time: 6:54:10 PM
 * To change this template use Options | File Templates.
 */
package com.ulearning.ulms.util.log;


import org.apache.log4j.*;
import org.apache.log4j.xml.DOMConfigurator;

import java.io.InputStream;

public class LogUtil
{
        public static Logger getLogger(String category)
        {
                return LogManager.getLogger(category);

        }

        public static void initLogger()
        {
                LogUtil lu = new LogUtil();
                initLogger(lu.getDefaultLogConfig());
        }

        public static void initLogger(String configFile)
        {
                if (configFile == null || configFile.length() == 0)
                {
                        LogUtil lu = new LogUtil();
                        initLogger(lu.getLogFromConfig(configFile));
                        try
                        {
                        }
                        catch (Exception e)
                        {
                                e.printStackTrace();
                        }
                }
        }

        /**
         * Init the logger via inputStream
         *
         * @param configFile
         */
        public static void initLogger(InputStream configFile)
        {
                try
                {
                        isInitialize = true;
                        DOMConfigurator config = new DOMConfigurator();
                        LogManager.resetConfiguration();
                        config.doConfigure(configFile, LogManager.getLoggerRepository());
                }
                catch (Exception e)
                {
                        e.printStackTrace();
                }
        }

        /**
         * Debug the info for application by log4j, the method will
         * replace the system.out.println method
         *
         * @param cat     the logger catalog,e.g. "system","user"
         * @param message the info which needs to print out
         */
        public static void debug(String cat, String message)
        {
                LogUtil.getLogger("ulms." + cat).debug(message);
        }

        public static void info(String cat, String message)
        {
                LogUtil.getLogger("ulms." + cat).info(message);
        }

        public static void warn(String cat, String message)
        {
                LogUtil.getLogger("ulms." + cat).warn(message);
        }

        public static void error(String cat, String message)
        {
                LogUtil.getLogger("ulms." + cat).error(message);
        }

        public static void fatal(String cat, String message)
        {
                LogUtil.getLogger("ulms." + cat).fatal(message);
        }

        private InputStream getDefaultLogConfig()
        {
                InputStream ins = this.getClass().getClassLoader().getResourceAsStream(logFile);
                return ins;
        }

        private InputStream getLogFromConfig(String logConf)
        {
                InputStream ins = this.getClass().getClassLoader().getResourceAsStream(logConf);
                return ins;
        }

        /**
         * Constract the logger for the special pattern,filepath and level
         *
         * @param category
         * @param logLevel
         * @param logFilePath
         * @param logPattern
         * @return a prepared logger
         */
        public static Logger getLogger(String category, int logLevel, String logFilePath, String logPattern)
        {
                /**
                 # Valid level 0 - 4
                 # 0 - FATAL ERROR
                 # 1 - ERROR
                 # 2 - WARN
                 # 3 - INFO
                 # 4 - DEBUG
                 # 5 - DETAIL
                 */

                org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(category);
                switch (logLevel)
                {
                        case 0:
                                logger.setLevel(org.apache.log4j.Level.FATAL);
                                break;
                        case 1:
                                logger.setLevel(org.apache.log4j.Level.ERROR);
                                break;
                        case 2:
                                logger.setLevel(org.apache.log4j.Level.WARN);
                                break;
                        case 3:
                                logger.setLevel(org.apache.log4j.Level.INFO);
                                break;
                        case 4:
                                logger.setLevel(org.apache.log4j.Level.DEBUG);
                                break;
                        default:
                        {
                                if (logLevel > 4)
                                {
                                        logger.setLevel(org.apache.log4j.Level.DEBUG);
                                }
                                else
                                {
                                        logger.setLevel(org.apache.log4j.Level.ERROR);
                                }
                        }
                }

                Layout layout = new PatternLayout(logPattern);
                logger.setAdditivity(false);
                if (logFilePath != null)
                {
                        try
                        {
                                Appender appender = new FileAppender(layout, logFilePath);
                                logger.addAppender(appender);
                        }
                        catch (Exception e)
                        {
                                System.out.println(e);
                        }
                }
                else
                {
                        Appender appender = new ConsoleAppender(layout);
                        logger.addAppender(appender);
                }

                return logger;
        }

        private static final String DEFAULT_LOG_PATTERN = "%-9.9c{3}: %m%n";
        private static boolean isInitialize = false;
        private static String logFile = "log.xml";
}
