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
        /*��ʾ��У��Դ����  */
        //��ѧԭ��
        int COURSECONTENTTYPEID_JIAOXUEYUANCHUANG=9;
        //����ʵ¼
        int COURSECONTENTTYPEID_KETANGSHILU=10;
        //��Ƶ�μ�
        int COURSECONTENTTYPEID_SHIPINKEJIAN=11;
        //ʾ���γ�
        int COURSECONTENTTYPEID_SHIFANKECHENG=12;

        /* ϵͳ��ʼ����Ŀ¼ID������У��ר�� ѧ��ר�� ��ʦר���� */         
        //������
        int CATALOGID_GLJY=5;

        //Ʒ�ƽ���
        int CATALOGID_PPJS=7;

        //������Դ
        int CATALOGID_RLZY=9;

        //ѧУ�Ļ�
        int CATALOGID_XXWH=11;

        //У����Դ
        int CATALOGID_XZZY=13;

        //����ʵ¼
        int CATALOGID_KTSL   =15;

        //��ѵ��Ϣ
        int CATALOGID_PXXX =16;

        //�α���ѵ
        int CATALOGID_KBPX =17;

        //�̲���ѵ
        int  CATALOGID_JCPX=18;

        //�̷���ѵ
        int  CATALOGID_JFPX=19;

         //��������ѵ
        int CATALOGID_JBGPX=20;

        //��ѧ�ο�
        int CATALOGID_JXCK=22;

        //�̰���ѡ
        int CATALOGID_JAJX=23;

        //������ϰ
        int CATALOGID_PTLX   =24;

        //ѧ��׼��͹��ɽ̲�
        int CATALOGID_XSZRGDJC=25;

        //��Ƶ�ο�
        int CATALOGID_SPCK=26;

        //�̲ķ���
        int CATALOGID_JCFX=27;

        //��ѧ��ͼ�͵��ʿ�Ƭ
        int CATALOGID_JXGTDCKP=28;

        //�̲����׼�ʻ�
        int CATALOGID_JCPTJBH=29;

        //˵��
        int CATALOGID_SK=30;

        //�̰�����
        int CATALOGID_JAJL=32;

        //��ѧ��Ϸ
        int CATALOGID_JXYX=34;

        //��ɫ��ѧ
        int CATALOGID_TSJX=36;

        //��������
        int CATALOGID_MWSX=38;

        //��ѡ��Դ
        int CATALOGID_JXZY=40;

        //��ѧ��ժ
        int CATALOGID_JXWZ=41;

        //ʦ����ѵ
        int CATALOGID_SZPX=43;

        //¼���Ŵ�
        int CATALOGID_LYCD=45;

        //Ӣ�����
        int CATALOGID_YYGQ=47;

        //Ӣ��Ц��
        int CATALOGID_YYXH=49;

        //��������
        int CATALOGID_WSLB=51;

        //˫�����
        int CATALOGID_SYGS=53;

        //������Ϸ
        int CATALOGID_XSYX=54;

        //����ѧϰ
        int CATALOGID_DCXX=57;

        //����Ի�
        int CATALOGID_DYDH=58;

        //��������
        int CATALOGID_DHGS=59;

        //Ӣ�����
        int CATALOGID_YYCS=60;

        //��ѡ����
        int CATALOGID_JXST =0;


        /*������Դ���ͣ���������*/
        int All_TYPE = -1;

        /*������Դ����*/
        int PUBLIC_CONTENT_TYPE = 0;

        /*������Դ���� ����U��*/
        int PERSONAL_CONTENT_TYPE = 1;

        /*�γ���Դ����*/
        int COURSE_CONTENT_TYPE = 2;

        /*������Դ����*/
        int PAPER_CONTENT_TYPE = 3;

        /*��ҵ���Ϸ���������*/
        int OBTAIN_EMPLOYMENT_CONTENT_TYPE = 4;

        //�γ��ļ�������
        int COURSE_FOLDER_TYPE = 5;

        //ʾ��У��Դ
        int SHIFANXIAO_TYPE = 6;

        //����Ϊ�����Դ���
        //����Ϊ��Դ������
        //SCOREM����
        int CONTENT_TYPE_SCOREM_PACKAGE = 8;

        //����Ϊ��������Ŀ����
        //��ͨ
        int CONTENT_TYPE_GENERAL = 9;

        // ����
        int CONTENT_TYPE_ARTICLE = 10;

        //ͼ��
        int CONTENT_TYPE_GRAPHIC = 11;

        //��Ƶ
        int CONTENT_TYPE_VIDEO = 12;

        //��Ƶ
        int CONTENT_TYPE_AUDIO = 13;

        //��ͼ
        int CONTENT_TYPE_ZUTU = 14;

        //˵��
        int CONTENT_TYPE_SHUOKE = 15;

        //Flash
        int CONTENT_TYPE_Flash = 16;
        
        /*δ��֤��ԴID*/
        int NO_AUTH_CONTENT_ID = 1;

        //����ժҪ��
        String[] INIT_COURSE_CONTENT_CATALOG = {
                "����ָ��", "��ϰ���", "�̲ļ��ο�����", "�γ̽���", "�γ���ҵ", "��ѧ���", "������ʦ"
        };

        //��������
        int SEARCH_NAME = 1;

        //����˵��
        int SEARCH_EXPLAIN = 2;

        //�����ؼ���
        int SEARCH_KEYWORD = 3;

        //��������
        int SEARCH_AUTHOR = 4;

        //����������
        int SEARCH_RELEASE = 5;

        //����ʱ����
        int SEARCH_LIKE = 6;

        //����ʱ������
        int SEARCH_NOT_CONTAIN = 7;

        //����ʱ��ȷ
        int SEARCH_EQUAL = 8;

        //����ʱ��
        int SEARCH_OR = 9;

        //����ʱ��
        int SEARCH_AND = 10;

        //����ʱ��
        int SEARCH_NOT = 11;

        //��������
        int SEARCH_ALL_DAY = 12;

        //���7��
        int SEARCH_SEVEN_DAY = 13;

        //���30��
        int SEARCH_THIRTY_DAY = 14;

        //���60��
        int SEARCH_SIXTY_DAY = 15;

        //���һ��
        int SEARCH_ONE_YEAR = 16;

        //����������
        String ORDER_BY_TITLE = " order by title";

        //����������
        String ORDER_BY_PUBLISHER = " order by publisher";

        //������������
        String ORDER_BY_CREATOR = " order by creator";

        //��������������
        String ORDER_BY_CREATEDATE = " order by createdate";

        //����
        String DESC = " desc";

        //����
        String ASC = " asc";

        //������������
        int SEARCH_ALL_LANGUAGE = -1;

        //������������
        int SEARCH_ALL_CONTENT_TYPE = -1;

        //ѡ������
        int SEARCH_SELECT_DATE = 17;

        //��������
        int SEARCH_INTPUT_DATE = 18;

        //�ж��Ƿ����µ�����
        String IS_NEW = "isnew";

        /*������ͨ�û�������Դ*/
        int allowedCommonUserUpload = 1;

        /*������ͬ�û�������Դ*/
        int notAllowedCommonUserUpload = 0;

        /*�����ϴ��ռ�*/
        int limitSpace = 1;

        /*�ϴ��ռ�������*/
        int notLimitSpace = 0;

        /*�������ϴ��ļ�*/
        int notUploadFile = 1;

        /*�����ϴ��ļ�*/
        int uploadFile = 0;

        /*����Դά�������ҳ��ֱ��ĸ���Ŀ¼���ĸ������ݣ�1ΪĿ¼��2Ϊ����*/
        int displayCatalog = 1;
        int displayContent = 2;

        //��Դ�Ƿ񷢲���1Ϊ������0������
        int doView = 1;
        int doNotView = 0;

        //δ���
        int AUDIT_BLANK=0;
        //���ͨ��
        int AUDIT_APPROVED=1;
        //���δͨ��
        int AUDIT_UNAPPROVED=2;
}
