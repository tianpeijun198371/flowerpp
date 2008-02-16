/**
 * Copyright (c)2006 Beijing WenhuaOnline Sci-tech DevelopmentCo.,Ltd.
 * All rights reserved.
 *
 * User: fengch
 * Date: 2007-9-25 16:16:32
 */
package com.ulearning.ulms.scorm.handler;

import com.ulearning.ulms.core.exceptions.ULMSException;
import com.ulearning.ulms.core.util.CSVParser;
import com.ulearning.ulms.course.helper.CourseContentHelper;
import com.ulearning.ulms.course.model.CourseContentForm;
import com.ulearning.ulms.scorm.exceptions.ScormAppException;
import com.ulearning.ulms.scorm.exceptions.ScormSysException;
import com.ulearning.ulms.scorm.helper.NewScormHelper;
import com.ulearning.ulms.scorm.model.NewScorm;
import com.ulearning.ulms.scorm.model.NewScormScoes;
import com.ulearning.ulms.scorm.util.ScormConstants;
import com.ulearning.ulms.scorm.util.ScormType2CourseContentTypeUtil;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.*;

import java.util.*;


public class NewScormManifestHandler
{
        protected static Log logger = LogFactory.getLog(NewScormManifestHandler.class);

        //解析后的scorm model
        private NewScorm scorm;

        //解析后的 scoes
        private List scoes = new ArrayList();

        //解析后的 scoesMap  (identifier,sco)
        private Map scoesMap = new HashMap();

        //scorm imsmanifest
        private File scormImsmanifestFile;

        //aicc 配置Cst文件
        private File aiccCstFile;

        //aicc 配置Crs文件
        private File aiccCrsFile;

        //aicc 配置au文件
        private File aiccAuFile;

        //aicc 配置des文件
        private File aiccDesFile;

        //标准课件解压位置
        private File scormFileDir;

        //文件编码
        private String charset = null;

        //版本信息，scorm_1.2 scorm_2004 aicc
        private String version = null;
        private String type = ScormConstants.SCORM_RELATIONTYPE_COURSECONTENT;
        private Integer scormID;
        private CourseContentForm courseContentForm;
        private int courseContentNodeID = 0;

        //定义全局变量 下面函数递归之用
        private int sequenceIndex = 0;

        public NewScormManifestHandler(File scormFileDir)
        {
                this.scormFileDir = scormFileDir;
        }

        public int getCourseContentNodeID()
        {
                return courseContentNodeID;
        }

        public void setCourseContentForm(CourseContentForm courseContentForm)
        {
                this.courseContentForm = courseContentForm;
        }

        public String getVersion()
        {
                if (version == null)
                {
                        validateScormType();
                }

                return this.version;
        }

        public File getScormImsmanifestFile()
        {
                if (scormImsmanifestFile == null)
                {
                        validateScormType();
                }

                return this.scormImsmanifestFile;
        }

        /**
         * 解析AICC课件,生成scorm,scoes
         */
        public void process() throws Exception
        {
                logger.info("processAiccFiles start!");
                validateScormType();

                if ((courseContentForm != null) && (version != null))
                {
                        courseContentForm.setNodetype(ScormType2CourseContentTypeUtil.convertToCourseContentType(
                                version));
                }

                logger.info("version=" + version);

                if (version.equals(ScormConstants.SCORMTYPE_AICC))
                {
                        processAiccCRS();
                        processAiccDES();
                        processAiccAU();
                        processAiccCST();
                        initTheLevelAndSeq("root");
                        persistAiccDate();
                }
        }

        /**
         * 判断标准课件的类型：aicc,scorm1.2,scorm2004。N/A
         * <p/>
         * 查找 是否存在CRS AU DES CST等文件(只找到二级目录)
         * <p/>
         * 并确认aiccFilepath ,aiccFilepath的值
         * 相关类型参见 ScormConstants定义
         * 若返回N/A，则表示非法类型
         *
         * @return 类型
         */
        public String validateScormType() throws ScormAppException
        {
                logger.info("validateScormType start!");

                if (version != null)
                {
                        return version;
                }

                String resultType = validateAiccScormDir(scormFileDir);

                if (resultType == null)
                {
                        resultType = ScormConstants.SCORMTYPE_NA;
                }

                version = resultType;
                logger.info("resultType=" + resultType);

                return resultType;
        }

        /**
         * 判断一个目录下是否含有CRS AU DES CST这4个文件，或包含imsmanifest.xml。
         *
         * @param dir 要判断的目录
         * @return 返回类型，若==null，代表不含scorm或aicc配置文件
         * @throws ScormAppException
         */
        private String validateAiccScormDir(File dir) throws ScormAppException
        {
                logger.info("validateAiccScormDir start!");

                String resultType = null;
                logger.info("dir=" + dir.getName());

                File[] files = null;
                String fileName = null;
                String extName = null;

                logger.info("#dir getPath= " + dir.getPath());
                logger.info("#dir getName= " + dir.getName());

                if (dir.isFile())
                {
                        //todo：判断Scorm2004还要解析xml
                        if (dir.getName().equals("imsmanifest.xml"))
                        {
                                scormImsmanifestFile = dir;

                                return ScormConstants.SCORMTYPE_SCORM_V12;
                        }
                }
                else if (dir.isDirectory())
                {
                        boolean isIncludeCrsFile = false;
                        boolean isIncludeAuFile = false;
                        boolean isIncludeDesFile = false;
                        boolean isIncludeCstFile = false;
                        boolean isSameFileName = false;
                        String crsFileBasename = "";
                        String auFileBasename = "";
                        String desFileBasename = "";
                        String cstFileBasename = "";

                        files = dir.listFiles();

                        for (int i = 0; i < files.length; i++)
                        {
                                fileName = files[i].getName();

                                logger.info("#path = " + files[i].getPath());
                                logger.info("#fileName = " + fileName);

                                if (files[i].isFile())
                                {
                                        //todo：判断Scorm2004还要解析xml,判断Scorm版本
                                        if (fileName.equals("imsmanifest.xml"))
                                        {
                                                scormImsmanifestFile = files[i];

                                                return ScormConstants.SCORMTYPE_SCORM_V12;
                                        }

                                        logger.info("Not scorm, 判断Aicc start!");
                                        extName = FilenameUtils.getExtension(fileName);

                                        if (extName.equalsIgnoreCase("crs"))
                                        {
                                                if (isIncludeCrsFile)
                                                {
                                                        throw new ScormAppException(
                                                                "非法的AICC课件,包含多个扩展名为“crs”的文件");
                                                }

                                                crsFileBasename = FilenameUtils.getBaseName(fileName);
                                                isIncludeCrsFile = true;
                                                aiccCrsFile = files[i];
                                        }
                                        else if (extName.equalsIgnoreCase("au"))
                                        {
                                                if (isIncludeAuFile)
                                                {
                                                        throw new ScormAppException(
                                                                "非法的AICC课件,包含多个扩展名为“au”的文件");
                                                }

                                                auFileBasename = FilenameUtils.getBaseName(fileName);
                                                isIncludeAuFile = true;
                                                aiccAuFile = files[i];
                                        }
                                        else if (extName.equalsIgnoreCase("cst"))
                                        {
                                                if (isIncludeCstFile)
                                                {
                                                        throw new ScormAppException(
                                                                "非法的AICC课件,包含多个扩展名为“cst”的文件");
                                                }

                                                cstFileBasename = FilenameUtils.getBaseName(fileName);
                                                isIncludeCstFile = true;
                                                aiccCstFile = files[i];
                                        }
                                        else if (extName.equalsIgnoreCase("des"))
                                        {
                                                if (isIncludeDesFile)
                                                {
                                                        throw new ScormAppException(
                                                                "非法的AICC课件,包含多个扩展名为“des”的文件");
                                                }

                                                desFileBasename = FilenameUtils.getBaseName(fileName);
                                                isIncludeDesFile = true;
                                                aiccDesFile = files[i];
                                        }

                                        isSameFileName = crsFileBasename.equalsIgnoreCase(auFileBasename) &&
                                                crsFileBasename.equalsIgnoreCase(auFileBasename) &&
                                                crsFileBasename.equalsIgnoreCase(desFileBasename) &&
                                                crsFileBasename.equalsIgnoreCase(cstFileBasename);

                                        logger.info("isSameFileName=" + isSameFileName);
                                        logger.info("isIncludeCrsFile=" + isIncludeCrsFile);
                                        logger.info("isIncludeAuFile=" + isIncludeAuFile);
                                        logger.info("isIncludeDesFile=" + isIncludeDesFile);
                                        logger.info("isIncludeCstFile=" + isIncludeCstFile);

                                        if (isIncludeCrsFile && isIncludeAuFile &&
                                                isIncludeDesFile && isIncludeCstFile &&
                                                isSameFileName)
                                        {
                                                logger.info("aiccDesFile=" + aiccDesFile.getName());
                                                logger.info("aiccAuFile=" + aiccAuFile.getName());
                                                logger.info("aiccCstFile=" + aiccCstFile.getName());
                                                logger.info("aiccCesFile=" + aiccCrsFile.getName());

                                                return ScormConstants.SCORMTYPE_AICC;
                                        }
                                }
                                else if (files[i].isDirectory())
                                {
                                        String tempResult = validateAiccScormDir(files[i]);

                                        if (tempResult != null)
                                        {
                                                return tempResult;
                                        }
                                }
                        }

                        if (isIncludeCrsFile && !isIncludeAuFile && isIncludeDesFile &&
                                isIncludeCstFile)
                        {
                                throw new ScormAppException("非法的AICC课件,缺少扩展名为\"AU\"的AICC的配置文件！");
                        }
                        else if (isIncludeCrsFile && isIncludeAuFile &&
                                !isIncludeDesFile && isIncludeCstFile)
                        {
                                throw new ScormAppException(
                                        "非法的AICC课件,缺少扩展名为\"DES\"的AICC的配置文件！");
                        }
                        else if (isIncludeCrsFile && isIncludeAuFile && isIncludeDesFile &&
                                !isIncludeCstFile)
                        {
                                throw new ScormAppException(
                                        "非法的AICC课件,缺少扩展名为\"CST\"的AICC的配置文件！");
                        }
                        else if (!isIncludeCrsFile && isIncludeAuFile &&
                                isIncludeDesFile && isIncludeCstFile)
                        {
                                throw new ScormAppException(
                                        "非法的AICC课件,缺少扩展名为\"CRS\"的AICC的配置文件！");
                        }
                        else if (isIncludeCrsFile && isIncludeAuFile && isIncludeDesFile &&
                                isIncludeCstFile && !isSameFileName)
                        {
                                throw new ScormAppException("非法的AICC课件,AICC的配置文件的基本文件名必须相同！");
                        }
                }

                return resultType;
        }

        // 解析AICC的CRS文件
        private void processAiccCRS() throws IOException, ScormAppException
        {
                logger.info("processAiccCRS start!");

                //[Course]
                //Mandatory
                String Course_Creator = null;

                //Mandatory
                String Course_ID = null;

                //Mandatory
                String Course_System = null;

                //Mandatory
                String Course_Title = null;

                //Mandatory
                String Level = null;

                //Mandatory
                String Max_Fields_CST = null;

                String Max_Fields_ORT = null;

                //Mandatory
                String Total_AUs = null;

                //Mandatory
                String Total_Blocks = null;

                String Total_Objectives = null;

                String Total_Complex_Objectives = null;

                //Mandatory
                String Version = null;

                //[Course_Behavior]
                //Mandatory
                String Max_Normal = null;

                //[Course_Description]
                //Mandatory
                String Description = null;

                BufferedReader input = null;

                //处理CRS
                if (aiccCrsFile != null)
                {
                        if (charset != null)
                        {
                                input = new BufferedReader(new InputStreamReader(
                                        new FileInputStream(aiccCrsFile), charset));
                        }
                        else
                        {
                                input = new BufferedReader(new InputStreamReader(
                                        new FileInputStream(aiccCrsFile)));
                        }

                        String line;
                        int lineNumber = 0;

                        while ((line = input.readLine()) != null)
                        {
                                logger.debug("## parse--- line = " + line);
                                line = StringUtils.trimToEmpty(line);

                                if (line.equals(""))
                                {
                                        continue; //越过此行
                                }

                                if (lineNumber == 0)
                                {
                                        if (!line.equals("[Course]"))
                                        {
                                                throw new ScormAppException("非法的AICC课件，CRS文件格式不对！");
                                        }
                                }

                                //Course_Creator
                                if (line.indexOf("[") != 0)
                                {
                                        String[] strs = StringUtils.split(line, "=");

                                        for (int i = 0; i < strs.length; i++)
                                        {
                                                strs[i] = StringUtils.trimToEmpty(strs[i]);
                                                logger.debug("parseLine--- parsed strs " + i + "  = " +
                                                        strs[i]);
                                        }

                                        if (strs.length > 1)
                                        {
                                                if ((Course_Creator == null) &&
                                                        strs[0].equalsIgnoreCase("Course_Creator"))
                                                {
                                                        Course_Creator = strs[1];
                                                }

                                                if ((Course_ID == null) &&
                                                        strs[0].equalsIgnoreCase("Course_ID"))
                                                {
                                                        Course_ID = strs[1];
                                                }

                                                if ((Course_System == null) &&
                                                        strs[0].equalsIgnoreCase("Course_System"))
                                                {
                                                        Course_System = strs[1];
                                                }

                                                if ((Course_Title == null) &&
                                                        strs[0].equalsIgnoreCase("Course_Title"))
                                                {
                                                        Course_Title = strs[1];
                                                }

                                                if ((Level == null) &&
                                                        strs[0].equalsIgnoreCase("Level"))
                                                {
                                                        Level = strs[1];
                                                }

                                                if ((Max_Fields_CST == null) &&
                                                        strs[0].equalsIgnoreCase("Max_Fields_CST"))
                                                {
                                                        Max_Fields_CST = strs[1];
                                                }

                                                if ((Max_Fields_ORT == null) &&
                                                        strs[0].equalsIgnoreCase("Max_Fields_ORT"))
                                                {
                                                        Max_Fields_ORT = strs[1];
                                                }

                                                if ((Total_AUs == null) &&
                                                        strs[0].equalsIgnoreCase("Total_AUs"))
                                                {
                                                        Total_AUs = strs[1];
                                                }

                                                if ((Total_Blocks == null) &&
                                                        strs[0].equalsIgnoreCase("Total_Blocks"))
                                                {
                                                        Total_Blocks = strs[1];
                                                }

                                                if ((Total_Objectives == null) &&
                                                        strs[0].equalsIgnoreCase("Total_Objectives"))
                                                {
                                                        Total_Objectives = strs[1];
                                                }

                                                if ((Total_Complex_Objectives == null) &&
                                                        strs[0].equalsIgnoreCase(
                                                                "Total_Complex_Objectives"))
                                                {
                                                        Total_Complex_Objectives = strs[1];
                                                }

                                                if ((Max_Normal == null) &&
                                                        strs[0].equalsIgnoreCase("Max_Normal"))
                                                {
                                                        Max_Normal = strs[1];
                                                }

                                                if ((Version == null) &&
                                                        strs[0].equalsIgnoreCase("Version"))
                                                {
                                                        Version = strs[1];
                                                }

                                                if ((Description == null) &&
                                                        strs[0].equalsIgnoreCase("Description"))
                                                {
                                                        Description = strs[1];
                                                }
                                        }
                                }

                                lineNumber++;
                        }

                        logger.debug("courseContentNodeID=" + courseContentNodeID);
                        logger.debug("type=" + type);
                        logger.debug("Course_Title=" + Course_Title);
                        logger.debug("Description=" + Description);
                        logger.debug("Version=" + Version);
                        scorm = new NewScorm();
                        scorm.setRelationID(new Integer(courseContentNodeID));
                        scorm.setType(type);
                        scorm.setName(Course_Title);
                        scorm.setReference("reference");
                        scorm.setSummary(Description);
                        scorm.setVersion(version);
                        scorm.setHideNav("0");
                        scorm.setCreateDate(new Date());
                        scorm.setLastUpdateDate(new Date());
                }
        }

        // 解析AICC的Des文件
        private void processAiccDES() throws Exception
        {
                logger.info("processAiccDes start!");

                //处理 Descriptor (.DES) File
                CSVParser csvparser = new CSVParser(aiccDesFile, ',', true, "GBK");
                List parserlt = csvparser.parse();
                Date date = new Date();
                int systemIDPosition = -1;
                int titlePosition = -1;
                int headerLength = 0;

                for (int i = 0; i < parserlt.size(); i++)
                {
                        String[] args = (String[]) parserlt.get(i);

                        if (i == 0)
                        {
                                headerLength = args.length;

                                for (int j = 0; j < args.length; j++)
                                {
                                        if ((systemIDPosition == -1) &&
                                                args[j].equalsIgnoreCase("System_ID"))
                                        {
                                                systemIDPosition = j;
                                        }

                                        if ((titlePosition == -1) &&
                                                args[j].equalsIgnoreCase("Title"))
                                        {
                                                titlePosition = j;
                                        }
                                }
                        }
                        else
                        {
                                logger.info("headerLength = " + headerLength);
                                logger.info("args.length = " + args.length);
                                logger.info("systemIDPosition = " + systemIDPosition);
                                logger.info("titlePosition = " + titlePosition);

                                if (headerLength != args.length)
                                {
                                        throw new ScormAppException(
                                                "非法的Aicc课件,Descriptor (.DES) File格式不对:标题行和内容不对应！");
                                }

                                if (systemIDPosition == -1)
                                {
                                        throw new ScormAppException(
                                                "非法的Aicc课件,Descriptor (.DES) File格式不对:不存在\"System_ID\"列");
                                }

                                if (titlePosition == -1)
                                {
                                        throw new ScormAppException(
                                                "非法的Aicc课件,Descriptor (.DES) File格式不对:不存在\"Title\"列");
                                }

                                logger.info("args[systemIDPosition] = " +
                                        args[systemIDPosition]);
                                logger.info("args[titlePosition] = " + args[titlePosition]);

                                NewScormScoes sco = new NewScormScoes();
                                sco.setIdentifier(args[systemIDPosition]);
                                sco.setTitle(args[titlePosition]);
                                //初始化,默认为SCOTYPE_ASSET
                                sco.setScoType(ScormConstants.SCOTYPE_BLOCK);
                                scoes.add(sco);
                                scoesMap.put(sco.getIdentifier(), sco);
                        }
                }
        }

        // 解析AICC的Au文件
        private void processAiccAU() throws Exception
        {
                logger.info("processAiccAU start!");

                //处理 Assignable Unit (.AU) File
                CSVParser csvparser = new CSVParser(aiccAuFile, ',', true, "GBK");
                List parserlt = csvparser.parse();
                Date date = new Date();
                int systemIDPosition = -1;
                int command_LinePosition = -1;
                int File_NamePosition = -1;
                int Max_ScorePosition = -1;
                int Mastery_ScorePosition = -1;
                int Max_Time_AllowedPosition = -1;
                int Time_Limit_ActionPosition = -1;
                int System_VendorPosition = -1;
                int Core_VendorPosition = -1;
                int Web_LaunchPosition = -1;
                int AU_PasswordPosition = -1;

                int headerLength = 0;

                for (int i = 0; i < parserlt.size(); i++)
                {
                        String[] args = (String[]) parserlt.get(i);
                        logger.info("### i = " + i);

                        for (int j = 0; j < args.length; j++)
                        {
                                logger.info("arg[" + j + "]=" + args[j]);
                        }

                        if (i == 0)
                        {
                                headerLength = args.length;

                                for (int j = 0; j < args.length; j++)
                                {
                                        if ((systemIDPosition == -1) &&
                                                args[j].equalsIgnoreCase("System_ID"))
                                        {
                                                systemIDPosition = j;
                                        }

                                        if ((command_LinePosition == -1) &&
                                                args[j].equalsIgnoreCase("Command_Line"))
                                        {
                                                command_LinePosition = j;
                                        }

                                        if ((File_NamePosition == -1) &&
                                                args[j].equalsIgnoreCase("File_Name"))
                                        {
                                                File_NamePosition = j;
                                        }

                                        if ((Max_ScorePosition == -1) &&
                                                args[j].equalsIgnoreCase("Max_Score"))
                                        {
                                                Max_ScorePosition = j;
                                        }

                                        if ((Mastery_ScorePosition == -1) &&
                                                args[j].equalsIgnoreCase("Mastery_Score"))
                                        {
                                                Mastery_ScorePosition = j;
                                        }

                                        if ((Max_Time_AllowedPosition == -1) &&
                                                args[j].equalsIgnoreCase("Max_Time_Allowed"))
                                        {
                                                Max_Time_AllowedPosition = j;
                                        }

                                        if ((Time_Limit_ActionPosition == -1) &&
                                                args[j].equalsIgnoreCase("Time_Limit_Action"))
                                        {
                                                Time_Limit_ActionPosition = j;
                                        }

                                        if ((System_VendorPosition == -1) &&
                                                args[j].equalsIgnoreCase("System_Vendor"))
                                        {
                                                System_VendorPosition = j;
                                        }

                                        if ((Core_VendorPosition == -1) &&
                                                args[j].equalsIgnoreCase("Core_Vendor"))
                                        {
                                                Core_VendorPosition = j;
                                        }

                                        if ((Web_LaunchPosition == -1) &&
                                                args[j].equalsIgnoreCase("Web_Launch"))
                                        {
                                                Web_LaunchPosition = j;
                                        }

                                        if ((AU_PasswordPosition == -1) &&
                                                args[j].equalsIgnoreCase("AU_Password"))
                                        {
                                                AU_PasswordPosition = j;
                                        }
                                }
                        }
                        else
                        {
                                logger.info("headerLength = " + headerLength);
                                logger.info("args.length = " + args.length);
                                logger.info("systemIDPosition = " + systemIDPosition);
                                logger.info("command_LinePosition = " + command_LinePosition);
                                logger.info("File_NamePosition = " + File_NamePosition);
                                logger.info("Max_ScorePosition = " + Max_ScorePosition);
                                logger.info("Mastery_ScorePosition = " + Mastery_ScorePosition);
                                logger.info("Max_Time_AllowedPosition = " +
                                        Max_Time_AllowedPosition);
                                logger.info("Time_Limit_ActionPosition = " +
                                        Time_Limit_ActionPosition);
                                logger.info("Max_Time_AllowedPosition = " +
                                        Max_Time_AllowedPosition);
                                logger.info("System_VendorPosition = " + System_VendorPosition);
                                logger.info("Core_VendorPosition = " + Core_VendorPosition);
                                logger.info("Web_LaunchPosition = " + Web_LaunchPosition);
                                logger.info("AU_PasswordPosition = " + AU_PasswordPosition);

                                if (headerLength != args.length)
                                {
                                        throw new ScormAppException(
                                                "非法的Aicc课件,Assignable Unit (.DES) File格式不对:标题行和内容不对应！");
                                }

                                if (systemIDPosition == -1)
                                {
                                        throw new ScormAppException(
                                                "非法的Aicc课件,Assignable Unit (.DES) File格式不对:不存在\"System_ID\"列");
                                }

                                if (command_LinePosition == -1)
                                {
                                        throw new ScormAppException(
                                                "非法的Aicc课件,Assignable Unit (.AU) File格式不对:不存在\"Command_Line\"列");
                                }

                                if (File_NamePosition == -1)
                                {
                                        throw new ScormAppException(
                                                "非法的Aicc课件,Assignable Unit (.AU) File格式不对:不存在\"File_Name\"列");
                                }

                                String identifer = args[systemIDPosition];
                                logger.info("identifer = " + identifer);

                                NewScormScoes sco = getSco(identifer);
                                logger.info("sco=" + sco);
                                sco.setCommandLine(args[command_LinePosition]);
                                sco.setLaunch(args[File_NamePosition]);

                                if (Mastery_ScorePosition != -1)
                                {
                                        logger.info("Mastery_ScorePosition=" +
                                                Mastery_ScorePosition);
                                        sco.setMasteryScore(new Float(NumberUtils.toFloat(
                                                args[Mastery_ScorePosition])));
                                }

                                if (Max_ScorePosition != -1)
                                {
                                        sco.setMaxScore(new Float(NumberUtils.toFloat(
                                                args[Max_ScorePosition])));
                                }

                                if (Max_Time_AllowedPosition != -1)
                                {
                                        sco.setMaxTimeAllowed(args[Max_Time_AllowedPosition]);
                                }

                                if (System_VendorPosition != -1)
                                {
                                        sco.setOrganization(args[System_VendorPosition]);
                                }

                                if (System_VendorPosition != -1)
                                {
                                        sco.setSystemVendor(args[System_VendorPosition]);
                                }

                                if (Max_Time_AllowedPosition != -1)
                                {
                                        sco.setTimeLimitAction(args[Time_Limit_ActionPosition]);
                                }

                                sco.setScormId(scormID);

                                //初始化,默认为SCOTYPE_ASSET
                                sco.setScoType(ScormConstants.SCOTYPE_AU);
                        }
                }
        }

        /**
         * 解析AICC的CST文件
         */
        private void processAiccCST() throws Exception
        {
                logger.info("processAiccCST start!");

                //处理 Course Structure (.CST) File
                CSVParser csvparser = new CSVParser(aiccCstFile, ',', true, "GBK");
                List parserlt = csvparser.parse();
                Date date = new Date();
                int BlockPosition = -1;
                int MemberPosition = -1;
                int sequence = 0;
                int headerLength = 0;

                List orderedList = new ArrayList();

                for (int i = 0; i < parserlt.size(); i++)
                {
                        String[] args = (String[]) parserlt.get(i);
                        logger.info("#line " + i + "args.length=" + args.length);

                        if (i == 0)
                        {
                                headerLength = args.length;

                                if ((BlockPosition == -1) &&
                                        !args[0].equalsIgnoreCase("Block"))
                                {
                                        throw new ScormAppException(
                                                "非法的Aicc课件,Course Structure (.CST) File格式不对:不存在\"Block\"列");
                                }

                                if ((MemberPosition == -1) &&
                                        !args[1].equalsIgnoreCase("Member"))
                                {
                                        throw new ScormAppException(
                                                "非法的Aicc课件,Course Structure (.CST) File格式不对:不存在\"Member\"列");
                                }
                        }
                        else
                        {
                                /*if (headerLength != args.length)
                                {
                                        throw new ScormAppException("非法的Aicc课件,Course Structure (.CST) File格式不对:标题行和内容不对应！");
                                }*/

                                //Course Structure (.CST) File的标题行和内容行可以不对应
                                String block = args[0];

                                if (block.equalsIgnoreCase("root"))
                                {
                                        block = block.toLowerCase();
                                }

                                /*else
                                {
                                        NewScormScoes sco = getSco(block);
                                        if(sco==null)
                                                continue;
                                        sco.setScoType(ScormConstants.SCOTYPE_BLOCK);
                                }*/
                                logger.info("block = " + block);

                                String identifer = null;

                                //查找子项
                                for (int j = 1; j < args.length; j++)
                                {
                                        identifer = args[j];
                                        logger.info("identifer = " + identifer);

                                        NewScormScoes sco = getSco(identifer);

                                        if (sco == null)
                                        {
                                                continue;
                                        }

                                        sco.setParent(block);
                                        sco.setSequence(new Integer(++sequence));

                                        if (!orderedList.contains(sco))
                                        {
                                                orderedList.add(sco);
                                        }
                                }
                        }
                }

                logger.info("scoes.size()=" + scoes.size());
                logger.info("orderedList.size()=" + orderedList.size());
                scoes = orderedList;
        }

        /**
         * 把数据(courseContentForm，newScorm，scoes)插入数据库
         */
        private void persistAiccDate()
        {
                logger.info("persistAiccDate start!");

                try
                {
                        //默认标准课件是隶属于课程内容的，以后可以在此做扩展
                        if (type.equals(ScormConstants.SCORM_RELATIONTYPE_COURSECONTENT) &&
                                (courseContentForm != null))
                        {
                                logger.info("addCourseContent start!");
                                CourseContentHelper.throwsReAddException(courseContentForm);
                                // Insert the course into the course Info table
                                courseContentNodeID = CourseContentHelper.addCourseContent(courseContentForm);
                        }

                        logger.info("insertNewScorm start!");
                        logger.debug("scorm.getRelationID=" + scorm.getRelationID());
                        logger.debug("scorm.getType=" + scorm.getType());
                        logger.debug("scorm.getName=" + scorm.getName());
                        logger.debug("scorm.getVersion=" + scorm.getVersion());
                        scorm.setRelationID(new Integer(courseContentNodeID));
                        NewScormHelper.insertNewScorm(scorm);
                        scormID = scorm.getId();
                        logger.info("scormID=" + scormID);

                        persistScoes();
                }
                catch (ULMSException e)
                {
                        throw e;
                }
                catch (Exception e)
                {
                        e.printStackTrace();
                        throw new ScormSysException("您的课件可能不是标准的AICC课件，请重新上传！", e);
                }
        }

        /**
         * 递归计算课件Tree每个子项的深度.
         *
         * @param identifer
         */
        private void initTheLevel(String identifer)
        {
                if (identifer == null)
                {
                        return;
                }

                NewScormScoes sco = (NewScormScoes) scoesMap.get(identifer);
                logger.info("scorm.getId() = " + scorm.getId());
                logger.info("identifer = " + identifer);
                logger.info("sco = " + sco);

                if (!identifer.equalsIgnoreCase("root") && (sco != null))
                {
                        String parent = StringUtils.trimToEmpty(sco.getParent());
                        logger.info("parent = " + parent);

                        if (parent.equalsIgnoreCase("root"))
                        {
                                sco.setTheLevel(new Integer(0));
                        }
                        else
                        {
                                NewScormScoes parentSco = (NewScormScoes) scoesMap.get(parent);

                                if (parentSco.getTheLevel() != null)
                                {
                                        int parentLevel = parentSco.getTheLevel().intValue();
                                        logger.info("parentLevel = " + parentLevel);
                                        sco.setTheLevel(new Integer(parentLevel + 1));
                                }
                        }

                        logger.info("sco.getTheLevel = " + sco.getTheLevel());
                }

                logger.info("scoes.size() = " + scoes.size());

                for (int i = 0; i < scoes.size(); i++)
                {
                        NewScormScoes newScormScoes = (NewScormScoes) scoes.get(i);

                        if (!newScormScoes.getIdentifier().equalsIgnoreCase(identifer))
                        {
                                String parent = StringUtils.trimToEmpty(newScormScoes.getParent());
                                logger.info("parent = " + parent);

                                if (parent.equalsIgnoreCase("root"))
                                {
                                        newScormScoes.setTheLevel(new Integer(0));

                                        //newScormScoes.setSequence(new Integer(sequenceIndex++));
                                }
                                else if (newScormScoes.getTheLevel() == null)
                                {
                                        initTheLevel(newScormScoes.getIdentifier());
                                }
                        }
                }
        }

        /**
         * 递归计算课件Tree每个子项的深度.
         *
         * @param identifer
         */
        private void initTheLevelAndSeq(String identifer)
        {
                if (identifer == null)
                {
                        return;
                }

                NewScormScoes sco = (NewScormScoes) scoesMap.get(identifer);
                logger.info("identifer = " + identifer);

                logger.info("scoes.size() = " + scoes.size());

                for (int i = 0; i < scoes.size(); i++)
                {
                        NewScormScoes newScormScoes = (NewScormScoes) scoes.get(i);
                        String parent1 = StringUtils.trimToEmpty(newScormScoes.getParent());

                        if (parent1.equalsIgnoreCase(identifer))
                        {
                                String parent = StringUtils.trimToEmpty(newScormScoes.getParent());
                                logger.info("#parent = " + parent);
                                logger.info("#sub Identifie = " +
                                        newScormScoes.getIdentifier());

                                if (parent.equalsIgnoreCase("root"))
                                {
                                        newScormScoes.setTheLevel(new Integer(0));
                                }
                                else
                                {
                                        NewScormScoes parentSco = (NewScormScoes) scoesMap.get(parent);

                                        if (parentSco.getTheLevel() != null)
                                        {
                                                int parentLevel = parentSco.getTheLevel().intValue();
                                                logger.info("parentLevel = " + parentLevel);
                                                newScormScoes.setTheLevel(new Integer(parentLevel + 1));
                                        }
                                }

                                sequenceIndex++;
                                logger.info("#sub Identifie'sequenceIndex = " + sequenceIndex);
                                newScormScoes.setSequence(new Integer(sequenceIndex));
                                initTheLevelAndSeq(newScormScoes.getIdentifier());
                        }
                }
        }

        //SCOES插入数据库
        private void persistScoes()
        {
                logger.info("persistScoes start!");

                NewScormScoes sco = null;

                if (scoes != null)
                {
                        for (int i = 0; i < scoes.size(); i++)
                        {
                                sco = (NewScormScoes) scoes.get(i);
                                sco.setScormId(scormID);

                                logger.debug("sco.getScormId=" + sco.getScormId());
                                logger.debug("sco.getOrganization=" + sco.getOrganization());
                                logger.debug("sco.getParent=" + sco.getParent());
                                logger.debug("sco.getIdentifier=" + sco.getIdentifier());
                                logger.debug("sco.getLaunch=" + sco.getLaunch());
                                logger.debug("sco.getScoType=" + sco.getScoType());

                                NewScormHelper.insertNewScormScoes(sco);
                        }
                }
        }

        private NewScormScoes getSco(String identifer)
        {
                logger.info("will find identifer=" + identifer);

                NewScormScoes sco = null;

                if (scoes != null)
                {
                        logger.info("scoes.size=" + scoes.size());

                        for (int i = 0; i < scoes.size(); i++)
                        {
                                NewScormScoes newScormScoes = (NewScormScoes) scoes.get(i);
                                logger.info("# getIdentifier=" + newScormScoes.getIdentifier());

                                if (newScormScoes.getIdentifier().equals(identifer))
                                {
                                        logger.info("在scoes里找到 identifer=" + identifer);
                                        sco = newScormScoes;

                                        break;
                                }
                        }
                }

                return sco;
        }
}
