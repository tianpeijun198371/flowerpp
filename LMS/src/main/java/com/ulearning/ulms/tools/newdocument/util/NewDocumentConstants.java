/**
 * NewDocumentConstants.java.
 * User: fengch  Date: 2005-3-14
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.newdocument.util;

/**
 * ����������곣������Ҫ�޸�NewDocumentUtil.getTitle(int type)��NewDocumentUtil.getUploadPath(int type, int aspID)��NewDocumentUtil.getParentType(int type)�����������е�����
 */
public interface NewDocumentConstants
{
        //��̬��Ѷ_ngx
        int CHINA_EDUCATION = 1;
        //��̬��Ѷ----Զ�̽���
        int CHINA_EDUCATION_SASAC = 11;
        //��̬��Ѷ---ũҵ����
        int CHINA_EDUCATION_REGION = 12;
        //��̬��Ѷ---��ϵ����
        int CHINA_EDUCATION_OTHER = 13;
        //��̬��Ѷ---
        int CHINA_EDUCATION_CENTER = 14;

        //------ ����ί��Ŀ start -----
        //���߷���_ngx
        int POLITICS_EDUCATION = 2;
        //���߷���---������
        int POLITICS_EDUCATION_CENTER = 21;
        //���߷���---ũҵ��
        int POLITICS_EDUCATION_SASAC = 22;

        //վ�����_ngx
        int SKYNET = 3;
        //վ�����_����֪ͨ
        int SKYNET_YX = 31;
        //վ�����_����֧��
        int SKYNET_JS = 32;
        //վ�����_�����ƶ�
        int SKYNET_ZL = 33;

        //Զ�̽�����ѵ_ngx
        int REMOTE = 6;
        //Զ�̽�����ѵ--ʦ����ѵ
        int REMOTE_SZ = 61;
        //Զ�̽�����ѵ--ר����ѵ
        int REMOTE_CERT = 62;
        //Զ�̽�����ѵ--��������
        int REMOTE_TCP = 63;
        //Զ�̽�����ѵ--֤����ѵ
        int REMOTE_ZC = 64;

        //����԰��
        int COMMUNNITE = 9;
        //����԰��_ѧϰ�߽���
        int COMMUNNITE_ZY = 91;
        //����԰��_��������
        int COMMUNNITE_ST = 92;
        //����԰��_��Դ����
        int COMMUNNITE_EX = 93;
        //����԰��_������齻��
        int COMMUNNITE_M = 94;

        //����֪ͨ
        int ANNOUNCE = 300;
        //ѧϰ����
        int STUDY_HELP = 400;
        //��ϵ����
        int CONTACTUS = 500;

        //��ѵ֪ͨ_gzw
        int TRAIN_ANNOUNCE = 4;
        //��ѵ֪ͨ----ѧ������_gzw
        int TRAIN_ANNOUNCE_SCHOOL = 41;
        //��ѵ֪ͨ----��У����
        int TRAIN_ANNOUNCE_POLITICS = 42;
        //��ѵ֪ͨ----����
        int TRAIN_ANNOUNCE_OTHER = 43;

        //Զ�̽���_gzw
        int REMOTE_EDUCATION = 5;
        //Զ�̽���---������ѵ
        int REMOTE_EDUCATION_SHORT = 51;
        //Զ�̽���---������ѵ
        int REMOTE_EDUCATION_LONG = 52;
        //Զ�̽���---�ʸ���֤
        int REMOTE_EDUCATION_QUALIFICATION = 53;

        //������ѵ_gzw
        int DOMESTIC = 7;
        //������ѵ--����ί
        int DOMESTIC_SASAC = 71;
        //������ѵ--������ί
        int DOMESTIC_OTHER = 72;
        //������ѵ--�ط�����ί
        int DOMESTIC_LOCAL = 73;

        //������ѵ_gzw
        int OVERSEAS = 8;
        //������ѵ--����ί
        int OVERSEAS_SASAC = 81;
        //������ѵ--�ط�����ί
        int OVERSEAS_LOCAL = 82;
        //������ѵ--��������
        int OVERSEAS_OTHER = 83;

        //ѧ������
        int EDUCATION = 100;
        //��Ҫ���ö�̬_gzw
        int ECONOMY = 200;
        //�������_gzw
        int INTRODUCE_DEPARTMENT = 110;
        //���߷���_gzw
        int LAW = 120;
        //�˲Ž���_gzw
        int PEOPLE = 130;
        //��ҵ��Ƹ
        int PEPOLE_CORP = 131;
        //�����Ƽ�
        int PEPOLE_SELF = 132;
        //ר�ҿ�_gzw
        int EXPERT = 140;
        //�������_gzw
        int DOWNLOAD = 150;
        //��վ��ͼ_gzw
        int MAP = 160;
        //��ѵ�ƻ�_gzw
        int TRAIN = 170;
        //ר��
        int COLUMN = 180;
        //ר��
        int SAMPLE = 181;
        //����԰��_gzw
        int COMMUNION = 190;
        //�������
        int DEMAND = 210;
        //���԰�
        int MESSAGE = 220;
        //���ҽ��
        int result = 230;
        //ͼƬ����_gzw
        int NEWS_PIC = 240;
        int NEWS_SCHOOL_PIC = 241;
        int NEWS_STUDENT_PIC = 242;
        int NEWS_TRAINSCHOOL_PIC = 243;
        //��������_gzw
        int EXPERIENCE_CENTER = 250;

        //��������
        int EDUCATION_NEWS = 252;
        
        //��������Ϣ
        int SCROLLNEWS = 256;
        //���Ż���ʾ
        int IS_VIEW = 1;
        //�����Ż���ʾ
        int NOT_IS_VIEW = 0;

        //�ͻ�����
        int SERVICE = 260;
        //�μ���ʾ
        int COURSEDEMO = 261;
        //��������
        int ABOUTUS = 262;
        //�ɹ�����
        int EXAMPLE = 263;
        //�����γ�
        int PUBICCOURSE = 264;
        //��ѵ��
        int CERT = 265;
        //------ ����ί��Ŀ end -----
        
        /*��������Ŀ  START*/
        /*����У��ҳ*/
        int MASTER_PORTAL = 1000;
        /*��Ѷ����*/
        int MASTER_PORTAL_ZIXUN = 1001;
        /*֪ͨ����*/
        int MASTER_PORTAL_ANNOUNCE = 10011;
        /*վ������*/
        int MASTER_PORTAL_NEWS = 10012;
        /*��ѵ��Ϣ*/
        int MASTER_PORTAL_TRAININFO = 10013;
        /*������Ƹ*/
        int MASTER_PORTAL_ZSZP = 10014;
        /*ý�屨��*/
        int MASTER_PORTAL_ADV = 10015;
        /*ר�ҽ���*/
        int MASTER_PORTAL_EXPERT = 1002;
        /*��ѵ��γ�*/
        int MASTER_PORTAL_TRAIN = 1003;
        /*�̲Ľ���*/
        int MASTER_PORTAL_BOOK = 1004;
        /*��ѵѧУ*/
        int MASTER_PORTAL_TRAINSCHOOL = 1005;

        /*ѧУ��Ѷ*/
        int SCHOOL_OVERVIEW = 600;
        /*ѧУ���*/
        int SCHOOL_OVERVIEW_BREIF = 601;
        /*ѧУ�*/
        int SCHOOL_OVERVIEW_ACTIVE = 602;
        /*У԰����*/
        int SCHOOL_OVERVIEW_ANNOUNCE = 603;
        /*ѧУ����*/
        int SCHOOL_OVERVIEW_NEWS = 604;
        /*ѧУ�ſ�*/
        int SCHOOL_OVERVIEW_VIEW = 605;
        /*ѧУ����*/
        int SCHOOL_OVERVIEW_HONOR = 606;
        /*ѧУ��ò*/
        int SCHOOL_OVERVIEW_OUTLOOK = 607;
        /*У���´�*/
        int SCHOOL_OVERVIEW_MASTER = 608;
        /*��������*/
        int SCHOOL_ZHAOSHENG = 609;
        /*��ʦ��Ƹ*/
        int SCHOOL_ZHAOPIN = 610;
        /*У԰����*/
        int SCHOOL_STAR = 611;
        /*ѧ������*/
        int SCHOOL_STUDNET = 612;
        /*��ѧ�ɹ�*/
        int SCHOOL_SUCCESS = 613;
        /*�ҳ�����*/
        int SCHOOL_PARENTS = 614;
        /*У�����*/
        int SCHOOL_MASTER = 616;
        /*��������Ŀ  END*/
}
