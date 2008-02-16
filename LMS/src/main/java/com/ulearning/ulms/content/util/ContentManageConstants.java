/**
 * ContentManageConstants.java.
 * User: Fengch  Date: 2005-5-30 10:45:36
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.content.util;

public interface ContentManageConstants
{
        /*　示范校资源类型  */
        //教学原创
        int COURSECONTENTTYPEID_JIAOXUEYUANCHUANG=9;
        //课堂实录
        int COURSECONTENTTYPEID_KETANGSHILU=10;
        //视频课件
        int COURSECONTENTTYPEID_SHIPINKEJIAN=11;
        //示范课程
        int COURSECONTENTTYPEID_SHIFANKECHENG=12;

        /* 系统初始化的目录ID（包括校长专区 学生专区 教师专区） */         
        //管理经验
        int CATALOGID_GLJY=5;

        //品牌建设
        int CATALOGID_PPJS=7;

        //人力资源
        int CATALOGID_RLZY=9;

        //学校文化
        int CATALOGID_XXWH=11;

        //校长资源
        int CATALOGID_XZZY=13;

        //课堂实录
        int CATALOGID_KTSL   =15;

        //培训信息
        int CATALOGID_PXXX =16;

        //课标培训
        int CATALOGID_KBPX =17;

        //教材培训
        int  CATALOGID_JCPX=18;

        //教法培训
        int  CATALOGID_JFPX=19;

         //基本功培训
        int CATALOGID_JBGPX=20;

        //教学参考
        int CATALOGID_JXCK=22;

        //教案精选
        int CATALOGID_JAJX=23;

        //配套练习
        int CATALOGID_PTLX   =24;

        //学生准入和过渡教材
        int CATALOGID_XSZRGDJC=25;

        //视频参考
        int CATALOGID_SPCK=26;

        //教材分析
        int CATALOGID_JCFX=27;

        //教学挂图和单词卡片
        int CATALOGID_JXGTDCKP=28;

        //教材配套简笔画
        int CATALOGID_JCPTJBH=29;

        //说课
        int CATALOGID_SK=30;

        //教案交流
        int CATALOGID_JAJL=32;

        //教学游戏
        int CATALOGID_JXYX=34;

        //特色教学
        int CATALOGID_TSJX=36;

        //美文赏析
        int CATALOGID_MWSX=38;

        //精选资源
        int CATALOGID_JXZY=40;

        //教学文摘
        int CATALOGID_JXWZ=41;

        //师资培训
        int CATALOGID_SZPX=43;

        //录音磁带
        int CATALOGID_LYCD=45;

        //英语歌曲
        int CATALOGID_YYGQ=47;

        //英语笑话
        int CATALOGID_YYXH=49;

        //网上练兵
        int CATALOGID_WSLB=51;

        //双语故事
        int CATALOGID_SYGS=53;

        //线上游戏
        int CATALOGID_XSYX=54;

        //单词学习
        int CATALOGID_DCXX=57;

        //短语对话
        int CATALOGID_DYDH=58;

        //动画故事
        int CATALOGID_DHGS=59;

        //英语测试
        int CATALOGID_YYCS=60;

        //精选试题
        int CATALOGID_JXST =0;


        /*所有资源类型，用于搜索*/
        int All_TYPE = -1;

        /*公共资源类型*/
        int PUBLIC_CONTENT_TYPE = 0;

        /*个人资源类型 个人U盘*/
        int PERSONAL_CONTENT_TYPE = 1;

        /*课程资源类型*/
        int COURSE_CONTENT_TYPE = 2;

        /*论文资源类型*/
        int PAPER_CONTENT_TYPE = 3;

        /*就业资料发布区类型*/
        int OBTAIN_EMPLOYMENT_CONTENT_TYPE = 4;

        //课程文件夹类型
        int COURSE_FOLDER_TYPE = 5;

        //示范校资源
        int SHIFANXIAO_TYPE = 6;

        //上面为大的资源类别
        //下面为资源的类型
        //SCOREM类型
        int CONTENT_TYPE_SCOREM_PACKAGE = 8;

        //以下为新理念项目所需
        //普通
        int CONTENT_TYPE_GENERAL = 9;

        // 文章
        int CONTENT_TYPE_ARTICLE = 10;

        //图文
        int CONTENT_TYPE_GRAPHIC = 11;

        //视频
        int CONTENT_TYPE_VIDEO = 12;

        //音频
        int CONTENT_TYPE_AUDIO = 13;

        //组图
        int CONTENT_TYPE_ZUTU = 14;

        //说课
        int CONTENT_TYPE_SHUOKE = 15;

        //Flash
        int CONTENT_TYPE_Flash = 16;
        
        /*未认证资源ID*/
        int NO_AUTH_CONTENT_ID = 1;

        //总体摘要区
        String[] INIT_COURSE_CONTENT_CATALOG = {
                "考试指导", "复习大纲", "教材及参考资料", "课程讲义", "课程作业", "教学大纲", "主讲教师"
        };

        //搜索名字
        int SEARCH_NAME = 1;

        //搜索说明
        int SEARCH_EXPLAIN = 2;

        //搜索关键字
        int SEARCH_KEYWORD = 3;

        //搜索作者
        int SEARCH_AUTHOR = 4;

        //搜索发布者
        int SEARCH_RELEASE = 5;

        //搜索时包含
        int SEARCH_LIKE = 6;

        //搜索时不包括
        int SEARCH_NOT_CONTAIN = 7;

        //搜索时精确
        int SEARCH_EQUAL = 8;

        //搜索时或
        int SEARCH_OR = 9;

        //搜索时与
        int SEARCH_AND = 10;

        //搜索时非
        int SEARCH_NOT = 11;

        //所有日期
        int SEARCH_ALL_DAY = 12;

        //最近7天
        int SEARCH_SEVEN_DAY = 13;

        //最近30天
        int SEARCH_THIRTY_DAY = 14;

        //最近60天
        int SEARCH_SIXTY_DAY = 15;

        //最近一年
        int SEARCH_ONE_YEAR = 16;

        //按名称排序
        String ORDER_BY_TITLE = " order by title";

        //按作者排序
        String ORDER_BY_PUBLISHER = " order by publisher";

        //按发布者排序
        String ORDER_BY_CREATOR = " order by creator";

        //按创建日期排序
        String ORDER_BY_CREATEDATE = " order by createdate";

        //降序
        String DESC = " desc";

        //升序
        String ASC = " asc";

        //搜索所有语言
        int SEARCH_ALL_LANGUAGE = -1;

        //搜索所有类型
        int SEARCH_ALL_CONTENT_TYPE = -1;

        //选择日期
        int SEARCH_SELECT_DATE = 17;

        //输入日期
        int SEARCH_INTPUT_DATE = 18;

        //判断是否是新的搜索
        String IS_NEW = "isnew";

        /*允许普通用户上载资源*/
        int allowedCommonUserUpload = 1;

        /*不允许不同用户上载资源*/
        int notAllowedCommonUserUpload = 0;

        /*限制上传空间*/
        int limitSpace = 1;

        /*上传空间无限制*/
        int notLimitSpace = 0;

        /*不允许上传文件*/
        int notUploadFile = 1;

        /*允许上传文件*/
        int uploadFile = 0;

        /*在资源维护的浏览页面分辨哪个是目录，哪个是内容，1为目录，2为内容*/
        int displayCatalog = 1;
        int displayContent = 2;

        //资源是否发布，1为发布，0不发布
        int doView = 1;
        int doNotView = 0;

        //未审核
        int AUDIT_BLANK=0;
        //审核通过
        int AUDIT_APPROVED=1;
        //审核未通过
        int AUDIT_UNAPPROVED=2;
}
