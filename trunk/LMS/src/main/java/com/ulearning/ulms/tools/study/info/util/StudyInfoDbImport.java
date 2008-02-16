/**
 * Copyright (c)2006 Beijing WenhuaOnline Sci-tech DevelopmentCo.,Ltd. 
 * All rights reserved. 
 *
 * User: zhangly
 * Date: 2007-11-5 13:28:10 
 */

package com.ulearning.ulms.tools.study.info.util;

import com.ulearning.ulms.organ.exceptions.OrganSysException;
import com.ulearning.ulms.tools.study.info.bean.StudyInfoHelper;
import com.ulearning.ulms.tools.study.info.exceptions.StudyInfoSysException;
import com.ulearning.ulms.tools.study.info.form.StudyInfoForm;
import com.ulearning.ulms.util.BatchImport;
import com.ulearning.ulms.util.DbInputUtil;
import com.ulearning.ulms.util.log.LogUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class StudyInfoDbImport extends BatchImport
{



        public List insertIntoDb(String file_path) throws StudyInfoSysException
        {
                ArrayList studyInfoFormList = new ArrayList();
                StudyInfoForm of = null;
                int cellNum = 0;

                int examType = 1;
                String examTypea = "";
                String year = "";
                String name = "";
                String school = "";
                String sex = "";
                String cellphone = "";
                int type = 1;
                int grade = 1;
                String subject = "sdf";
                float tingLi = 0;
                String tingLia = "";
                float tingLiScore = 0;
                String tingLiScorea = "";
                String tingLiPingYu = "";
                float yuYin = 0;
                String yuYina = "";
                float yuYinScore = 0;
                String yuYinScorea = "";
                String yuYinPingYu = "";
                float ciHui = 0;
                String ciHuia = "";
                float ciHuiScore = 0;
                String ciHuiScorea = "";
                String ciHuiPingYu = "";
                float huiHua = 0;
                String huiHuaa = "";
                float huiHuaScore = 0;
                String huiHuaScorea = "";
                String huiHuaPingYu = "";
                float yuFa = 0;
                String yuFaa = "";
                float yuFaScore = 0;
                String yuFaScorea = "";
                String yuFaPingYu = "";
                float yueDu = 0;
                String yueDua = "";
                float yueDuScore = 0;
                String yueDuScorea = "";
                String yueDuPingYu = "";
                float xieZuo = 0;
                String xieZuoa = "";
                float xieZuoScore = 0;
                String xieZuoScorea = "";
                String xieZuoPingYu = "";
                float zongHe = 0;
                String zongHea = "";
                float zongHeScore = 0;
                String zongHeScorea = "";
                String zongHePingYu = "";
                float zongChengJi = 0;
                String zongChengJia = "";
                float zongChengJiScore = 0;
                String zongChengJiScorea = "";
                String zongPingYu = "";
                int totalScore = 0;
                int reallyScore = 0 ;
                try
                {
                        FileInputStream in = new FileInputStream(file_path);
                        wb = new HSSFWorkbook(in);
                        sheet = wb.getSheetAt(0);
                        LogUtil.debug("User",
                                "[UserDbInput]====================Start insert user info!");

                        //get user info total number
                        rowCount = sheet.getLastRowNum();

                        String currentOrgNoString = "/";
                        //insert user info
                        for (int i = 1; i <= rowCount; i++)
                        {
                                row = sheet.getRow((short) i);
                                String tempString = "";
                                //deal with empty row
                                tempStr = "";

                                try
                                {
                                        cellNum = row.getLastCellNum();
                                }
                                catch (Exception e)
                                {
                                }

                                for (int j = 0; j < cellNum; j++)
                                {
                                        tempStr += String.valueOf(DbInputUtil.getThisCellValue(
                                                row, j));
                                }

                                if (tempStr.trim().equals(""))
                                {
                                        continue;
                                }
                                of = new StudyInfoForm();
                                examTypea = DbInputUtil.getThisCellValue(row, 0);
                                if (examTypea != null || examTypea.trim().length() > 0)
                                {

                                        if (examTypea.equals("三年级英语学情调查试卷分析"))
                                        {
                                                examType = 1;
                                        }
                                        if (examTypea.equals("四年级英语学情调查试卷分析"))
                                        {
                                                examType = 2;
                                        }
                                        if (examTypea.equals("五年级英语学情调查试卷分析"))
                                        {
                                                examType = 3;
                                        }
                                        if (examTypea.equals("六年级英语学情调查试卷分析"))
                                        {
                                                examType = 4;
                                        }
                                        if (examTypea.equals("七年级第一学期英语学情调查试卷分析"))
                                        {
                                                examType = 5;
                                        }
                                        if (examTypea.equals("七年级第二学期英语学情调查试卷分析"))
                                        {
                                                examType = 6;
                                        }
                                        if (examTypea.equals("八年级第一学期英语学情调查试卷分析"))
                                        {
                                                examType = 7;
                                        }
                                        if (examTypea.equals("八年级第二学期英语学情调查试卷分析"))
                                        {
                                                examType = 8;
                                        }
                                        if (examTypea.equals("九年级第一学期英语学情调查试卷分析"))
                                        {
                                                examType = 9;
                                        }
                                }
                                year = DbInputUtil.getThisCellValue(row, 1);
                                //year = Integer.parseInt(DbInputUtil.getThisCellValue(row, 0));
                                school = DbInputUtil.getThisCellValue(row, 2);
                                name = DbInputUtil.getThisCellValue(row, 3);
                                //String zongHeScorea = Integer.toString(zongHeScore);
                                sex = DbInputUtil.getThisCellValue(row, 4);
                                if (sex.equals("男"))
                                {
                                        sex = "1";
                                }
                                else if (sex.equals("女"))
                                {
                                        sex = "0";
                                }
                                cellphone = DbInputUtil.getThisCellValue(row, 5);
                                tingLia = DbInputUtil.getThisCellValue(row, 6);
                                if (tingLia != null && tingLia.trim().length() > 0)
                                {
                                        tingLi = Float.valueOf(tingLia).floatValue();
                                }
                                tingLiScorea = DbInputUtil.getThisCellValue(row, 7);
                                if (tingLiScorea != null && tingLiScorea.trim().length() > 0)
                                {
                                        tingLiScore = Float.valueOf(tingLiScorea).floatValue();
                                }
                                tingLiPingYu = DbInputUtil.getThisCellValue(row, 8);
                                if((tingLiPingYu == null)||((tingLiPingYu!=null && tingLiPingYu.trim().length() == 0))){
                                        if((!tingLia.equals("")) || (!tingLiScorea.equals(""))){
                                        tingLiPingYu = StudyInfoHelper.getCommments(tingLi,tingLiScore,0);
                                        }else{
                                                tingLiPingYu = "";
                                        }
                                   }
                                yuYina = DbInputUtil.getThisCellValue(row, 9);
                                if (yuYina != null && yuYina.trim().length() > 0)
                                {
                                        yuYin = Float.valueOf(yuYina).floatValue();
                                }
                                yuYinScorea = DbInputUtil.getThisCellValue(row, 10);
                                if (yuYinScorea != null && yuYinScorea.trim().length() > 0)
                                {
                                        yuYinScore = Float.valueOf(yuYinScorea).floatValue();
                                }
                                yuYinPingYu = DbInputUtil.getThisCellValue(row, 11);
                                if((yuYinPingYu == null)||((yuYinPingYu!=null && yuYinPingYu.trim().length() == 0))){
                                        if((!yuYina.equals(""))||(!yuYinScorea.equals(""))){
                                        yuYinPingYu = StudyInfoHelper.getCommments(yuYin,yuYinScore,7);
                                        }else{
                                                yuYinPingYu = "";
                                        }
                                }
                                ciHuia = DbInputUtil.getThisCellValue(row, 12);
                                if (ciHuia != null && ciHuia.trim().length() > 0)
                                {
                                        ciHui = Float.valueOf(ciHuia).floatValue();
                                }
                                ciHuiScorea = DbInputUtil.getThisCellValue(row, 13);
                                if (ciHuiScorea != null && ciHuiScorea.trim().length() > 0)
                                {
                                        ciHuiScore = Float.valueOf(ciHuiScorea).floatValue();
                                }
                                ciHuiPingYu = DbInputUtil.getThisCellValue(row,14);
                                if((ciHuiPingYu == null)||((ciHuiPingYu!=null && ciHuiPingYu.trim().length() == 0))){
                                        if((!ciHuia.equals(""))||(!ciHuiScorea.equals(""))){
                                        ciHuiPingYu = StudyInfoHelper.getCommments(ciHui,ciHuiScore,1);
                                        }else{
                                                ciHuiPingYu = "";
                                        }
                                }
                                huiHuaa = DbInputUtil.getThisCellValue(row, 15);
                                if (huiHuaa != null && huiHuaa.trim().length() > 0)
                                {
                                        huiHua = Float.valueOf(huiHuaa).floatValue();
                                }
                                huiHuaScorea = DbInputUtil.getThisCellValue(row, 16);
                                if (huiHuaScorea != null && huiHuaScorea.trim().length() > 0)
                                {
                                        huiHuaScore = Float.valueOf(huiHuaScorea).floatValue();
                                }
                                huiHuaPingYu = DbInputUtil.getThisCellValue(row, 17);
                                if((huiHuaPingYu == null)||((huiHuaPingYu!=null && huiHuaPingYu.trim().length() == 0))){
                                        if((!huiHuaa.equals(""))||(!huiHuaScorea.equals(""))){
                                        huiHuaPingYu = StudyInfoHelper.getCommments(huiHua,huiHuaScore,2);
                                        }else{
                                                huiHuaPingYu = "";
                                        }
                                }
                                yuFaa = DbInputUtil.getThisCellValue(row, 18);
                                if (yuFaa != null && yuFaa.trim().length() > 0)
                                {
                                        yuFa = Float.valueOf(yuFaa).floatValue();
                                }
                                yuFaScorea = DbInputUtil.getThisCellValue(row, 19);
                                if (yuFaScorea != null && yuFaScorea.trim().length() > 0)
                                {
                                        yuFaScore = Float.valueOf(yuFaScorea).floatValue();
                                }
                                yuFaPingYu = DbInputUtil.getThisCellValue(row, 20);
                                if((yuFaPingYu == null)||((yuFaPingYu!=null && yuFaPingYu.trim().length() == 0))){
                                        if((!yuFaa.equals(""))||(!yuFaScorea.equals(""))){
                                        yuFaPingYu = StudyInfoHelper.getCommments(yuFa,yuFaScore,3);
                                        }else{
                                                yuFaPingYu = "";
                                        }
                                }
                                yueDua = DbInputUtil.getThisCellValue(row, 21);
                                if (yueDua != null && yueDua.trim().length() > 0)
                                {
                                        yueDu = Float.valueOf(yueDua).floatValue();
                                }
                                yueDuScorea = DbInputUtil.getThisCellValue(row, 22);
                                if (yueDuScorea != null && yueDuScorea.trim().length() > 0)
                                {
                                        yueDuScore = Float.valueOf(yueDuScorea).floatValue();
                                }
                                yueDuPingYu = DbInputUtil.getThisCellValue(row, 23);
                                if((yueDuPingYu == null)||((yueDuPingYu!=null && yueDuPingYu.trim().length() == 0))){
                                        if((!yueDua.equals(""))||(!yueDuScorea.equals(""))){
                                        yueDuPingYu = StudyInfoHelper.getCommments(yueDu,yueDuScore,4);
                                        }else{
                                                yueDuPingYu = "";
                                        }
                                }
                                xieZuoa = DbInputUtil.getThisCellValue(row, 24);
                                if (xieZuoa != null && xieZuoa.trim().length() > 0)
                                {
                                        xieZuo = Float.valueOf(xieZuoa).floatValue();
                                }
                                xieZuoScorea = DbInputUtil.getThisCellValue(row, 25);
                                if (xieZuoScorea != null && xieZuoScorea.trim().length() > 0)
                                {
                                        xieZuoScore = Float.valueOf(xieZuoScorea).floatValue();
                                }
                                xieZuoPingYu = DbInputUtil.getThisCellValue(row, 26);
                                if((xieZuoPingYu == null)||((xieZuoPingYu!=null && xieZuoPingYu.trim().length() == 0))){
                                        if((!xieZuoa.equals(""))||(!xieZuoScorea.equals(""))){
                                        xieZuoPingYu = StudyInfoHelper.getCommments(xieZuo,xieZuoScore,5);
                                        }else{
                                                xieZuoPingYu = "";
                                        }
                                }
                                zongHea = DbInputUtil.getThisCellValue(row, 27);
                                if (zongHea != null && zongHea.trim().length() > 0)
                                {
                                        zongHe = Float.valueOf(zongHea).floatValue();
                                }
                                zongHeScorea = DbInputUtil.getThisCellValue(row, 28);
                                if (zongHeScorea != null && zongHeScorea.trim().length() > 0)
                                {
                                        zongHeScore = Float.valueOf(zongHeScorea).floatValue();
                                }
                                zongHePingYu = DbInputUtil.getThisCellValue(row, 29);
                                if((zongHePingYu == null)||((zongHePingYu!=null && zongHePingYu.trim().length() == 0))){
                                        if((!zongHea.equals(""))||(!zongHeScorea.equals(""))){
                                        zongHePingYu = StudyInfoHelper.getCommments(zongHe,zongHeScore,6);
                                        }else{
                                                zongHePingYu = "";
                                        }
                                }
                                zongChengJia = DbInputUtil.getThisCellValue(row, 30);
                                if (zongChengJia != null && zongChengJia.trim().length() > 0)
                                {
                                        zongChengJi = Float.valueOf(zongChengJia).floatValue();
                                }
                                zongChengJiScorea = DbInputUtil.getThisCellValue(row, 31);
                                if (zongChengJiScorea != null && zongChengJiScorea.trim().length() > 0)
                                {
                                        zongChengJiScore = Float.valueOf(zongChengJiScorea).floatValue();
                                }
                                zongPingYu = DbInputUtil.getThisCellValue(row, 32);
                                if((zongPingYu == null)||((zongPingYu!=null && zongPingYu.trim().length() == 0))){
                                        if((!zongChengJia.equals(""))||(!zongChengJiScorea.equals(""))){
                                        zongPingYu = StudyInfoHelper.getCommments(zongChengJi,zongChengJiScore,8);
                                        }else{
                                                zongPingYu = "";
                                        }
                                }

                                of.setExamType(examType);
                                of.setYear(year);
                                of.setSchool(school);
                                of.setName(name);
                                of.setType(type);
                                of.setGrade(grade);
                                of.setSubject(subject);
                                of.setSex(sex);
                                if (cellphone != null && cellphone.trim().length() > 0){
                                of.setCellphone(cellphone); }
                                if (tingLia != null && tingLia.trim().length() > 0){
                                of.setTingLi(tingLi);}
                                if (tingLiScorea != null && tingLiScorea.trim().length() > 0){
                                of.setTingLiScore(tingLiScore);}
                                of.setTingLiPingYu(tingLiPingYu);
                                if (yuYina != null && yuYina.trim().length() > 0){
                                of.setYuYin(yuYin);}
                                if (yuYinScorea != null && yuYinScorea.trim().length() > 0){
                                of.setYuYinScore(yuYinScore);}
                                of.setYuYinPingYu(yuYinPingYu);

                                if (ciHuia != null && ciHuia.trim().length() > 0){
                                of.setCiHui(ciHui);}
                                if (ciHuiScorea != null && ciHuiScorea.trim().length() > 0){
                                of.setCiHuiScore(ciHuiScore);}
                                of.setCiHuiPingYu(ciHuiPingYu);
                                if (huiHuaa != null && huiHuaa.trim().length() > 0){
                                of.setHuiHua(huiHua);}
                                if (huiHuaScorea != null && huiHuaScorea.trim().length() > 0){
                                of.setHuiHuaScore(huiHuaScore);}
                                of.setHuiHuaPingYu(huiHuaPingYu);

                                if (yuFaa != null && yuFaa.trim().length() > 0){
                                of.setYuFa(yuFa);}
                                if (yuFaScorea != null && yuFaScorea.trim().length() > 0){
                                of.setYuFaScore(yuFaScore); }
                                of.setYuFaPingYu(yuFaPingYu);
                                if (yueDua != null && yueDua.trim().length() > 0){
                                of.setYueDu(yueDu);}
                                if (yueDuScorea != null && yueDuScorea.trim().length() > 0){
                                of.setYueDuScore(yueDuScore); }
                                of.setYueDuPingYu(yueDuPingYu);

                                if (xieZuoa != null && xieZuoa.trim().length() > 0){
                                of.setXieZuo(xieZuo);}
                                if (xieZuoScorea != null && xieZuoScorea.trim().length() > 0){
                                of.setXieZuoScore(xieZuoScore); }
                                of.setXieZuoPingYu(xieZuoPingYu);

                                if (zongHea != null && zongHea.trim().length() > 0){
                                of.setZongHe(zongHe);}
                                if (zongHeScorea != null && zongHeScorea.trim().length() > 0){
                                of.setZongHeScore(zongHeScore);}
                                of.setZongHePingYu(zongHePingYu);

                                if (zongChengJia != null && zongChengJia.trim().length() > 0){
                                of.setZongChengJi(zongChengJi);}
                                if (zongChengJiScorea != null && zongChengJiScorea.trim().length() > 0){
                                of.setZongChengJiScore(zongChengJiScore);}
                                of.setZongPingYu(zongPingYu);

                                studyInfoFormList.add(of);
                        }
                }
                catch (Exception e)
                {
                        System.out.println("=================================here88888888888888888888888" + e.getMessage());
                        throw new StudyInfoSysException("Exception in insertExcel " + e);
                }

                return studyInfoFormList;
        }

        /**
         * check organ in file
         *
         * @param file_path
         * @return che  chinfo
         * @throws OrganSysException
         */
        public String checkExcel(String file_path) throws StudyInfoSysException
        {
                int cellNum = 0; // row number

                String examTypea = "";
                String year = "";
                String name = "";
                String school = "";
                String sex = "";
                String cellphone = "";
                int type = 1;
                int grade = 1;
                String subject = "sdf";
                float tingLi = 0;
                String tingLia = "";
                float tingLiScore = 0;
                String tingLiScorea = "";
                String tingLiPingYu = "";
                float yuYin = 0;
                String yuYina = "";
                float yuYinScore = 0;
                String yuYinScorea = "";
                String yuYinPingYu = "";
                float ciHui = 0;
                String ciHuia = "";
                float ciHuiScore = 0;
                String ciHuiScorea = "";
                String ciHuiPingYu = "";
                float huiHua = 0;
                String huiHuaa = "";
                float huiHuaScore = 0;
                String huiHuaScorea = "";
                String huiHuaPingYu = "";
                float yuFa = 0;
                String yuFaa = "";
                float yuFaScore = 0;
                String yuFaScorea = "";
                String yuFaPingYu = "";
                float yueDu = 0;
                String yueDua = "";
                float yueDuScore = 0;
                String yueDuScorea = "";
                String yueDuPingYu = "";
                float xieZuo = 0;
                String xieZuoa = "";
                float xieZuoScore = 0;
                String xieZuoScorea = "";
                String xieZuoPingYu = "";
                float zongHe = 0;
                String zongHea = "";
                float zongHeScore = 0;
                String zongHeScorea = "";
                String zongHePingYu = "";
                float zongChengJi = 0;
                String zongChengJia = "";
                float zongChengJiScore = 0;
                String zongChengJiScorea = "";
                String zongPingYu = "";

                try
                {
                        FileInputStream in = new FileInputStream(file_path);
                        wb = new HSSFWorkbook(in);
                        sheet = wb.getSheetAt(0);
                        LogUtil.debug("organ",
                                "[OrgDbInput]====================Start parse organ info!");

                        // get organ info total number
                        rowCount = sheet.getLastRowNum();

                        String currentOrganNoString = "/";

                        //check organ info ever row
                        List studyInfoList = new ArrayList();
                        for (int i = 1; i <= rowCount; i++)
                        {
                                row = sheet.getRow(i);
                                System.out.println(" i = " + i);
                                if (row == null)
                                {
                                        break;
                                }
                                //check examType
                                examTypea = DbInputUtil.getThisCellValue(row, 0);
                                if (examTypea.equals(""))
                                {
                                        res += ("第" + (i + 1) + "行 试卷名称不能为空<br>");
                                }
                                //check year

                                year = DbInputUtil.getThisCellValue(row, 1);
                                if (year.equals(""))
                                {
                                        res += ("第" + (i + 1) + "行 年度不能为空<br>");
                                }
                                // check school
                                school = DbInputUtil.getThisCellValue(row, 2);
                                if (school.equals(""))
                                {
                                        res += ("第" + (i + 1) + "行 学校名称不能为空<BR>");
                                }

                                //check name
                                name = DbInputUtil.getThisCellValue(row, 3);
                                //System.out.println("name=" + name);
                                if (name.equals(""))
                                {
                                        res += ("第" + (i + 1) + "行 姓名不能为空<BR>");
                                }
                                //System.out.println("=================================here777777");
                                //check sex
                                sex = DbInputUtil.getThisCellValue(row, 4);
                                cellphone = DbInputUtil.getThisCellValue(row, 5);
                                tingLia = DbInputUtil.getThisCellValue(row, 6);
                                tingLiScorea = DbInputUtil.getThisCellValue(row, 7);
                                tingLiPingYu = DbInputUtil.getThisCellValue(row, 8);

                                yuYina = DbInputUtil.getThisCellValue(row, 9);
                                yuYinScorea = DbInputUtil.getThisCellValue(row, 10);
                                yuYinPingYu = DbInputUtil.getThisCellValue(row, 11);

                                ciHuia = DbInputUtil.getThisCellValue(row, 12);
                                ciHuiScorea = DbInputUtil.getThisCellValue(row, 13);
                                ciHuiPingYu = DbInputUtil.getThisCellValue(row, 14);

                                huiHuaa = DbInputUtil.getThisCellValue(row, 15);
                                huiHuaScorea = DbInputUtil.getThisCellValue(row, 16);
                                huiHuaPingYu = DbInputUtil.getThisCellValue(row, 17);

                                yuFaa = DbInputUtil.getThisCellValue(row, 18);
                                yuFaScorea = DbInputUtil.getThisCellValue(row, 19);
                                yuFaPingYu = DbInputUtil.getThisCellValue(row, 20);

                                yueDua = DbInputUtil.getThisCellValue(row, 21);
                                yueDuScorea = DbInputUtil.getThisCellValue(row, 22);
                                yueDuPingYu = DbInputUtil.getThisCellValue(row, 23);

                                xieZuoa = DbInputUtil.getThisCellValue(row, 24);
                                xieZuoScorea = DbInputUtil.getThisCellValue(row, 25);
                                xieZuoPingYu = DbInputUtil.getThisCellValue(row, 26);

                                zongHea = DbInputUtil.getThisCellValue(row, 27);
                                zongHeScorea = DbInputUtil.getThisCellValue(row, 28);
                                zongHePingYu = DbInputUtil.getThisCellValue(row, 29);

                                zongChengJia = DbInputUtil.getThisCellValue(row, 30);
                                zongChengJiScorea = DbInputUtil.getThisCellValue(row, 31);
                                zongPingYu = DbInputUtil.getThisCellValue(row, 32);

                        }
                }
                catch (Exception e)
                {
                        throw new StudyInfoSysException("Exception in checkExcel " + e);
                }

                return res;
        }
}
