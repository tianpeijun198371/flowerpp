/**
 * CreatPaperFile.java.
 * User: zhuyr Date: 2005-12-15 11:03:23
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.course.test.paper.bean;

import com.ulearning.ulms.core.util.*;
import com.ulearning.ulms.course.test.Exambatch.dao.ExambatchDAOImpl;
import com.ulearning.ulms.course.test.Exambatch.form.ExambatchForm;
import com.ulearning.ulms.course.test.paper.form.PaperForm;
import com.ulearning.ulms.course.test.paper.form.PaperQuestionForm;
import com.ulearning.ulms.course.test.question.base.bean.BaseHelper;
import com.ulearning.ulms.course.test.question.base.form.BaseForm;
import com.ulearning.ulms.course.test.question.choiceitem.bean.ChoiceItemHelper;
import com.ulearning.ulms.course.test.question.choiceitem.form.ChoiceItemForm;

import java.io.File;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class CreatPaperFile
{
        private int answerType;
        float[] totalScore;
        private int[] id;
        private int[] count;
        private int[] total;
        private String[][] content;
        private String[] score;
        private String[] pic;
        private String fileinfo = "";
        private String fileinfodoc = "";
        private float fullScore;
        private int fullnum;
        private String title = "";
        private String filejs = "";
        private int userID;
        private String checkjs = "";
        private PaperForm paperForm = null;

        public CreatPaperFile()
        {
        }

        public float[] getTotalScore()
        {
                return totalScore;
        }

        public void setTotalScore(float[] totalScore)
        {
                this.totalScore = totalScore;
        }

        public int[] getId()
        {
                return id;
        }

        public void setId(int[] id)
        {
                this.id = id;
        }

        public int getAnswerType()
        {
                return answerType;
        }

        public void setAnswerType(int answerType)
        {
                this.answerType = answerType;
        }

        public int[] getCount()
        {
                return count;
        }

        public void setCount(int[] count)
        {
                this.count = count;
        }

        public String[][] getContent()
        {
                return content;
        }

        public void setContent(String[][] content)
        {
                this.content = content;
        }

        public String[] getScore()
        {
                return score;
        }

        public void setScore(String[] score)
        {
                this.score = score;
        }

        public String[] getPic()
        {
                return pic;
        }

        public void setPic(String[] pic)
        {
                this.pic = pic;
        }

        public String getFileinfo()
        {
                return fileinfo;
        }

        public void setFileinfo(String fileinfo)
        {
                this.fileinfo = fileinfo;
        }

        public float getFullScore()
        {
                return fullScore;
        }

        public void setFullScore(float fullScore)
        {
                this.fullScore = fullScore;
        }

        public int getFullnum()
        {
                return fullnum;
        }

        public void setFullnum(int fullnum)
        {
                this.fullnum = fullnum;
        }

        public int[] getTotal()
        {
                return total;
        }

        public void setTotal(int[] total)
        {
                this.total = total;
        }

        public boolean makeFile(int paperID, String path, int courseID)
                throws Exception
        {
                CreatPaperFile maktrue = new CreatPaperFile();
                maktrue.makeFile(paperID, path, courseID, true);

                CreatPaperFile makfalse = new CreatPaperFile();
                makfalse.makeFile(paperID, path, courseID, false);

                CreatPaperFile maktrueSingle = new CreatPaperFile();
                maktrueSingle.makeFileSingle(paperID, path, courseID, true);

                CreatPaperFile makfalseSingle = new CreatPaperFile();
                makfalseSingle.makeFileSingle(paperID, path, courseID, false);

                return true;
        }

        //单题考试
        public boolean makeFileSingle(int paperID, String path, int courseID,
                                      boolean flag) throws Exception
        {
                this.creatList(paperID, flag);
                this.creatFilestr(true);
                this.creatFileclock(paperID, path, courseID);
                this.creatcheckjs();

                IOUtil v = new IOUtil();
                v.setPath("");

                String filename = "";

                if (flag)
                {
                        filename = "p" + paperID + "_A_single";
                        fileinfodoc = "<table width='100%' border='0' cellspacing='0' cellpadding='0'><tr>" +
                                "<td align='center'><font size='18px'>" + title +
                                "A卷</font></td></tr></table>" + fileinfodoc;
                }
                else
                {
                        filename = "p" + paperID + "_B_single";
                        fileinfodoc = "<table width='100%' border='0' cellspacing='0' cellpadding='0'><tr>" +
                                "<td align='center'><font size='18px'>" + title +
                                "B卷</font></td></tr></table>" + fileinfodoc;
                }

                v.isSaveFile(path + "/mystudy/course/test/paper/", filename + ".jsp",
                        fileinfo + filejs + checkjs);

                //v.isSaveFile(path + "/mystudy/course/test/paper/", filename + "doc.jsp", fileinfodoc);
                File filestr = new File(path + "/mystudy/course/test/paper/", filename);

                if (filestr.exists())
                {
                        return true;
                }
                else
                {
                        return false;
                }
        }

        public boolean makeFile(int paperID, String path, int courseID, boolean flag)
                throws Exception
        {
                this.creatList(paperID, flag);
                this.creatFilestr();
                this.creatFileclock(paperID, path, courseID);
                this.creatcheckjs();

                IOUtil v = new IOUtil();
                v.setPath("");

                String filename = "";

                if (flag)
                {
                        filename = "p" + paperID + "_A";
                        fileinfodoc = "<table width='100%' border='0' cellspacing='0' cellpadding='0'><tr>" +
                                "<td align='center'><font size='18px'>" + title +
                                "A卷</font></td></tr></table>" + fileinfodoc;
                }
                else
                {
                        filename = "p" + paperID + "_B";
                        fileinfodoc = "<table width='100%' border='0' cellspacing='0' cellpadding='0'><tr>" +
                                "<td align='center'><font size='18px'>" + title +
                                "B卷</font></td></tr></table>" + fileinfodoc;
                }

                v.isSaveFile(path + "/mystudy/course/test/paper/", filename + ".jsp",
                        fileinfo + filejs + checkjs);

                v.isSaveFile(path + "/mystudy/course/test/paper/",
                        filename + "doc.jsp", fileinfodoc);

                File filestr = new File(path + "/mystudy/course/test/paper/", filename);

                if (filestr.exists())
                {
                        return true;
                }
                else
                {
                        return false;
                }
        }

        private void creatcheckjs()
        {
                checkjs = "<SCRIPT LANGUAGE='JavaScript'>" + '\n';
                checkjs += ("<!--" + '\n');
                checkjs += "var number=1;";
                checkjs += ("function checkDone()" + '\n');
                checkjs += ("{" + '\n');

                for (int k = 0; k < id.length; k++)
                {
                        checkjs += ("if(paperAnswerForm.answer_" + id[k] + " != null)" +
                                '\n');
                        checkjs += ("{" + '\n');
                        checkjs += ("var len" + k + " = paperAnswerForm.answer_" + id[k] +
                                ".length;" + '\n');
                        checkjs += ("if(len" + k + " == null)" + '\n');
                        checkjs += ("{" + '\n');
                        checkjs += ("if(paperAnswerForm.answer_" + id[k] +
                                ".type == 'radio' || paperAnswerForm.answer_" + id[k] +
                                ".type == 'checkbox')" + '\n');
                        checkjs += ("{" + '\n');
                        checkjs += ("if(paperAnswerForm.answer_" + id[k] +
                                ".checked == false)" + '\n');
                        checkjs += ("{" + '\n');
                        checkjs += ("alert('您第" + (k + 1) + "题还没做！');" + '\n');
                        checkjs += ("paperAnswerForm.answer_" + id[k] + ".focus();" + '\n');
                        checkjs += ("return;" + '\n');
                        checkjs += ("}" + '\n');
                        checkjs += ("}" + '\n');
                        checkjs += ("else" + '\n');
                        checkjs += ("{" + '\n');
                        checkjs += ("if(paperAnswerForm.answer_" + id[k] + ".value == '')" +
                                '\n');
                        checkjs += ("{" + '\n');
                        checkjs += ("alert('您第" + (k + 1) + "题还没做！');" + '\n');
                        checkjs += ("paperAnswerForm.answer_" + id[k] + ".focus();" + '\n');
                        checkjs += ("return;" + '\n');
                        checkjs += ("}" + '\n');
                        checkjs += ("}" + '\n');
                        checkjs += ("}" + '\n');
                        checkjs += ("else" + '\n');
                        checkjs += ("{" + '\n');
                        checkjs += ("var flag" + k + " = 0;" + '\n');
                        checkjs += ("if(paperAnswerForm.answer_" + id[k] +
                                "[0].type == 'radio' || paperAnswerForm.answer_" + id[k] +
                                "[0].type == 'checkbox')" + '\n');
                        checkjs += ("{" + '\n');
                        checkjs += ("for(i=0; i<len" + k + "; i++)" + '\n');
                        checkjs += ("{" + '\n');
                        checkjs += ("if(paperAnswerForm.answer_" + id[k] +
                                "[i].checked == true)" + '\n');
                        checkjs += ("{" + '\n');
                        checkjs += ("flag" + k + " = 1;" + '\n');
                        checkjs += ("}" + '\n');
                        checkjs += ("}" + '\n');
                        checkjs += ("if(flag" + k + " == 0)" + '\n');
                        checkjs += ("{" + '\n');
                        checkjs += ("alert('您第" + (k + 1) + "题还没做！');" + '\n');
                        checkjs += ("paperAnswerForm.answer_" + id[k] + "[0].focus();" +
                                '\n');
                        checkjs += ("return;" + '\n');
                        checkjs += ("}" + '\n');
                        checkjs += ("}" + '\n');
                        checkjs += ("else" + '\n');
                        checkjs += ("{" + '\n');
                        checkjs += ("for(i=0; i<len" + k + "; i++)" + '\n');
                        checkjs += ("{" + '\n');
                        checkjs += ("if(paperAnswerForm.answer_" + id[k] +
                                "[i].value == '')" + '\n');
                        checkjs += ("{" + '\n');
                        checkjs += ("flag" + k + " = 1;" + '\n');
                        checkjs += ("}" + '\n');
                        checkjs += ("}" + '\n');
                        checkjs += ("if(flag" + k + " == 0)" + '\n');
                        checkjs += ("{" + '\n');
                        checkjs += ("alert('您第" + (k + 1) + "题还没做！');" + '\n');
                        checkjs += ("paperAnswerForm.answer_" + id[k] + "[0].focus();" +
                                '\n');
                        checkjs += ("return;" + '\n');
                        checkjs += ("}" + '\n');
                        checkjs += ("}" + '\n');
                        checkjs += ("}" + '\n');
                        checkjs += ("}" + '\n');
                }

                checkjs += ("alert('试题已全部完成！');" + '\n');
                checkjs += ("}" + '\n');
                checkjs += ("function submitpaper()" + '\n');
                checkjs += ("{" + '\n');
                checkjs += ("if(window.confirm('您确实要提交吗？'))" + '\n');
                checkjs += ("{" + '\n');
                //paperAnswerForm.action='/answerPaper.do';
                checkjs += ("paperAnswerForm.submit();" + '\n');
                checkjs += ("opener.location.reload();" + '\n');
                //window.close();
                checkjs += ("}" + '\n');
                checkjs += ("}" + '\n');
                checkjs += ("function next()" + '\n');
                checkjs += ("{" + '\n');

                for (int i = 1; i <= fullnum; i++)
                {
                        if (i == fullnum)
                        {
                                checkjs += ("if(number>=" + fullnum + "){" +
                                        " alert('您已经完成全部考试，请提交试卷！');" + "return;" + "}");
                        }
                        else
                        {
                                checkjs += ("if(number==" + i + "){" + "id_" + i +
                                        ".style.display='none';" + "id_" + (i + 1) +
                                        ".style.display='';" + "number++;" + "return;" + "}");
                        }
                }

                checkjs += ("}" + '\n');
                checkjs += ("function pre()" + '\n');
                checkjs += ("{" + '\n');

                for (int i = 1; i <= fullnum; i++)
                {
                        if (i == 1)
                        {
                                checkjs += ("if(number==1){" + " alert('这是第一题，请选择下一题！');" +
                                        "return;" + "}");
                        }
                        else
                        {
                                checkjs += ("if(number==" + i + "){" + "id_" + i +
                                        ".style.display='none';" + "id_" + (i - 1) +
                                        ".style.display='';" + "number--;" + "return;" + "}");
                        }
                }

                checkjs += ("}" + '\n');
                checkjs += ("--> " + '\n');
                checkjs += ("</SCRIPT>" + '\n');
        }

        private void creatFileclock(int paperID, String path, int courseID)
                throws Exception
        {
                filejs = "<script language='javascript'>" + '\n';
                filejs += ("var time_now_server,time_now_client,time_end,time_end1,time_server_client,time_server_client1,timerID;" +
                        '\n' + "time_now = new Date(); " + '\n' +
                        "time_now = time_now.getTime();" + '\n');
                filejs += ("<%" + '\n');
                filejs += ("Calendar cal = new Calendar();" + '\n');

                filejs += ("int hour = cal.getHour();" + '\n');
                filejs += ("int minute = cal.getMinute();" + '\n');
                filejs += ("int secend = cal.getSecond();" + '\n');

                PaperForm paperForm = null;
                PaperHelper paperHelper = new PaperHelper();
                paperForm = paperHelper.getPaper(paperID);
                title = paperForm.getTitle();

                filejs += ("if (paperForm.getDesc2().indexOf(\"A\") != -1)" + '\n');
                filejs += ("{" + '\n');
                filejs += ("cal.setDate(paperForm.getStartTime());" + '\n');
                filejs += ("}" + '\n');
                filejs += ("else if (paperForm.getDesc2().indexOf(\"B\") != -1)" +
                        '\n');
                filejs += ("{" + '\n');
                filejs += ("cal.setDate(new Date());" + '\n');
                filejs += ("}" + '\n');
                filejs += ("else if (paperForm.getDesc2().indexOf(\"C\") != -1)" +
                        '\n');
                filejs += ("{" + '\n');
                filejs += ("ExambatchDAOImpl exambatchDAOImpl = new ExambatchDAOImpl();" +
                        '\n');
                filejs += ("List paperList = exambatchDAOImpl.getPaperList(paperForm.getPaperID());" +
                        '\n');
                filejs += ("for (int i = 0; i < paperList.size(); i++)" + '\n');
                filejs += ("{" + '\n');
                filejs += ("ExambatchForm exambatchModel = (ExambatchForm) paperList.get(i);" +
                        '\n');
                filejs += ("if (DateTimeUtil.isTestTime(exambatchModel.getExambegintime(), new Date(), exambatchModel.getExamendtime()))" +
                        '\n');
                filejs += ("{" + '\n');
                filejs += ("cal.setDate(exambatchModel.getExambegintime());" + '\n');
                filejs += ("}" + '\n');
                filejs += ("}" + '\n');
                filejs += ("}" + '\n');

                filejs += ("int hour1 = cal.getHour();" + '\n');
                filejs += ("int minute1 = cal.getMinute();" + '\n');
                filejs += ("int secend1 = cal.getSecond();" + '\n');
                filejs += ("%>" + '\n');

                filejs += ("time_end = (<%=paperForm.getDesc3()%>* 60 - <%=hour - hour1%>*60 * 60 - <%=minute - minute1%> *60 - <%=secend-secend1%>)*1000;" +
                        '\n');
                filejs += ("time_end1 = (<%=paperForm.getDesc3()%>* 60 - <%=hour - hour1%>*60 * 60 - <%=minute - minute1%> *60 - <%=secend-secend1%>)*1000;" +
                        '\n');
                filejs += ("time_server_client = time_end;" + '\n');
                filejs += ("time_server_client1 = time_end1;" + '\n');
                filejs += ("<%if (paperForm.getDesc3() != null && !paperForm.getDesc3().equals(\"\") && !paperForm.getDesc3().equals(\"0\"))" +
                        '\n');
                filejs += ("{%>" + '\n');
                filejs += (" setTimeout('show_time()', 1000);" + '\n');
                filejs += (" setTimeout('show_time1()', 1000);" + '\n');
                filejs += ("<%}%>" + '\n');

                filejs += ("function show_time()" + '\n');
                filejs += ("{" + '\n');
                filejs += ("var courseID = " + courseID + ";" + '\n');
                filejs += ("timer.innerHTML = \"<B><font color=red>\" + time_server_client + \"</font></B>\";" +
                        '\n');
                filejs += ("var time_now, time_distance, str_time;" + '\n');
                filejs += ("var int_day, int_hour, int_minute, int_second;" + '\n');
                filejs += ("time_end = time_end - 1000; " + '\n');
                filejs += ("time_distance = time_end; " + '\n');
                /*
                  filejs += "if (time_distance == 300000) " + '\n';
                  filejs += "{" + '\n';
                  filejs += "alert('对不起，您还有5分钟答卷的时间！'); " + '\n';
                  filejs += "} " + '\n';
                */
                filejs += ("if (time_distance > 0)" + '\n');
                filejs += ("{" + '\n');
                filejs += ("int_day = Math.floor(time_distance / 86400000)" + '\n');
                filejs += ("time_distance -= int_day * 86400000;" + '\n');
                filejs += ("int_hour = Math.floor(time_distance / 3600000) " + '\n');
                filejs += ("time_distance -= int_hour * 3600000;" + '\n');
                filejs += ("int_minute = Math.floor(time_distance / 60000) " + '\n');
                filejs += ("time_distance -= int_minute * 60000; " + '\n');
                filejs += ("int_second = Math.floor(time_distance / 1000) " + '\n');

                filejs += ("if (int_hour < 10) " + '\n');
                filejs += ("{ " + '\n');
                filejs += ("int_hour = '0' + int_hour;  " + '\n');
                filejs += ("}   " + '\n');
                filejs += ("if (int_minute < 10)  " + '\n');
                filejs += ("{   " + '\n');
                filejs += ("int_minute = '0' + int_minute;  " + '\n');
                filejs += ("} " + '\n');
                filejs += ("if (int_second < 10)   " + '\n');
                filejs += ("{   " + '\n');
                filejs += ("int_second = '0' + int_second;  " + '\n');
                filejs += ("} " + '\n');
                filejs += ("str_time = + int_hour +\"<font color=#000000>时</font>\" + int_minute +\"<font color=#000000>分</font>\"+ int_second +\"<font color=#000000>秒</font>\";" +
                        '\n');
                filejs += ("timer.innerHTML = \"<B><font color=red>\" + str_time + \"</font></B>\"; " +
                        '\n');
                filejs += ("setTimeout('show_time()', 1000);" + '\n');
                filejs += ("}" + '\n');
                filejs += ("else" + '\n');
                filejs += ("{ " + '\n');
                filejs += ("paperAnswerForm.submit();" + '\n');
                filejs += ("alert('对不起，考试时间结束，试卷已自动提交！'); " + '\n');
                filejs += ("window.parent.href = '" +
                        Config.getContextRoot()+"/mystudy/course/test/paper/listexam.jsp?flag=1&courseID=' + courseID;" +
                        '\n');
                filejs += ("clearTimeout(timerID);" + '\n');
                filejs += ("} " + '\n');
                filejs += ("}" + '\n');
                filejs += ("function show_time1()" + '\n');
                filejs += ("{" + '\n');

                filejs += ("timer1.innerHTML = \"<B><font color=red>\" + time_server_client1 +\"</font></B>\";" +
                        '\n');
                filejs += ("var time_now, time_distance1, str_time1; " + '\n');
                filejs += ("var int_day1, int_hour1, int_minute1, int_second1;   " +
                        '\n');
                filejs += ("time_end1 = time_end1 - 1000; " + '\n');
                filejs += ("time_distance1 = time_end1; " + '\n');
                filejs += ("if (time_distance1 > 0)    " + '\n');
                filejs += ("{" + '\n');
                filejs += ("int_day1 = Math.floor(time_distance1 / 86400000)" + '\n');
                filejs += ("time_distance1 -= int_day1 * 86400000; " + '\n');
                filejs += ("int_hour1 = Math.floor(time_distance1 / 3600000) " + '\n');
                filejs += ("time_distance1 -= int_hour1 * 3600000;   " + '\n');
                filejs += ("int_minute1 = Math.floor(time_distance1 / 60000) " + '\n');
                filejs += ("time_distance1 -= int_minute1 * 60000; " + '\n');
                filejs += ("int_second1 = Math.floor(time_distance1 / 1000)" + '\n');

                filejs += ("if (int_hour1 < 10)" + '\n');
                filejs += ("{" + '\n');
                filejs += ("int_hour1 = '0' + int_hour1;" + '\n');
                filejs += ("}" + '\n');
                filejs += ("if (int_minute1 < 10) " + '\n');
                filejs += ("{ " + '\n');
                filejs += ("int_minute1 = '0' + int_minute1; " + '\n');
                filejs += ("}" + '\n');
                filejs += ("if (int_second1 < 10)" + '\n');
                filejs += ("{" + '\n');
                filejs += ("int_second1 = '0' + int_second1;" + '\n');
                filejs += ("}" + '\n');
                filejs += ("str_time1 = + int_hour1 + \"<font color=#000000>时</font>\" + int_minute1 + \"<font color=#000000>分</font>\" + int_second1 + \"<font color=#000000>秒</font>\"" +
                        '\n');
                filejs += ("timer1.innerHTML = \"<B><font color=red>\" + str_time1 + \"</font></B>\"" +
                        '\n');
                filejs += ("setTimeout('show_time1()', 1000);" + '\n');
                filejs += ("}" + '\n');
                filejs += ("}" + '\n');
                filejs += ("</script>" + '\n');
        }

        //        private String filejs = "";
        //        private int userID;
        //        private String checkjs = "";
        //        private PaperForm paperForm = null;
        //
        //        private void creatcheckjs()
        //        {
        //                checkjs = "<SCRIPT LANGUAGE='JavaScript'>" + '\n';
        //                checkjs += "<!--" + '\n';
        //                checkjs += "function checkDone()" + '\n';
        //                checkjs += "{" + '\n';
        //                for (int k = 0; k < id.length; k++)
        //                {
        //                        checkjs += "if(paperAnswerForm.answer_" + id[k] + " != null)" + '\n';
        //                        checkjs += "{" + '\n';
        //                        checkjs += "var len" + k + " = paperAnswerForm.answer_" + id[k] + ".length;" + '\n';
        //                        checkjs += "if(len" + k + " == null)" + '\n';
        //                        checkjs += "{" + '\n';
        //                        checkjs += "if(paperAnswerForm.answer_" + id[k] + ".type == 'radio' || paperAnswerForm.answer_" + id[k] + ".type == 'checkbox')" + '\n';
        //                        checkjs += "{" + '\n';
        //                        checkjs += "if(paperAnswerForm.answer_" + id[k] + ".checked == false)" + '\n';
        //                        checkjs += "{" + '\n';
        //                        checkjs += "alert('您第" + (k + 1) + "题还没做！');" + '\n';
        //                        checkjs += "paperAnswerForm.answer_" + id[k] + ".focus();" + '\n';
        //                        checkjs += "return;" + '\n';
        //                        checkjs += "}" + '\n';
        //                        checkjs += "}" + '\n';
        //                        checkjs += "else" + '\n';
        //                        checkjs += "{" + '\n';
        //                        checkjs += "if(paperAnswerForm.answer_" + id[k] + ".value == '')" + '\n';
        //                        checkjs += "{" + '\n';
        //                        checkjs += "alert('您第" + (k + 1) + "题还没做！');" + '\n';
        //                        checkjs += "paperAnswerForm.answer_" + id[k] + ".focus();" + '\n';
        //                        checkjs += "return;" + '\n';
        //                        checkjs += "}" + '\n';
        //                        checkjs += "}" + '\n';
        //                        checkjs += "}" + '\n';
        //                        checkjs += "else" + '\n';
        //                        checkjs += "{" + '\n';
        //                        checkjs += "var flag" + k + " = 0;" + '\n';
        //                        checkjs += "if(paperAnswerForm.answer_" + id[k] + "[0].type == 'radio' || paperAnswerForm.answer_" + id[k] + "[0].type == 'checkbox')" + '\n';
        //                        checkjs += "{" + '\n';
        //                        checkjs += "for(i=0; i<len" + k + "; i++)" + '\n';
        //                        checkjs += "{" + '\n';
        //                        checkjs += "if(paperAnswerForm.answer_" + id[k] + "[i].checked == true)" + '\n';
        //                        checkjs += "{" + '\n';
        //                        checkjs += "flag" + k + " = 1;" + '\n';
        //                        checkjs += "}" + '\n';
        //                        checkjs += "}" + '\n';
        //                        checkjs += "if(flag" + k + " == 0)" + '\n';
        //                        checkjs += "{" + '\n';
        //                        checkjs += "alert('您第" + (k + 1) + "题还没做！');" + '\n';
        //                        checkjs += "paperAnswerForm.answer_" + id[k] + "[0].focus();" + '\n';
        //                        checkjs += "return;" + '\n';
        //                        checkjs += "}" + '\n';
        //                        checkjs += "}" + '\n';
        //                        checkjs += "else" + '\n';
        //                        checkjs += "{" + '\n';
        //                        checkjs += "for(i=0; i<len" + k + "; i++)" + '\n';
        //                        checkjs += "{" + '\n';
        //                        checkjs += "if(paperAnswerForm.answer_" + id[k] + "[i].value == '')" + '\n';
        //                        checkjs += "{" + '\n';
        //                        checkjs += "flag" + k + " = 1;" + '\n';
        //                        checkjs += "}" + '\n';
        //                        checkjs += "}" + '\n';
        //                        checkjs += "if(flag" + k + " == 0)" + '\n';
        //                        checkjs += "{" + '\n';
        //                        checkjs += "alert('您第" + (k + 1) + "题还没做！');" + '\n';
        //                        checkjs += "paperAnswerForm.answer_" + id[k] + "[0].focus();" + '\n';
        //                        checkjs += "return;" + '\n';
        //                        checkjs += "}" + '\n';
        //                        checkjs += "}" + '\n';
        //                        checkjs += "}" + '\n';
        //                        checkjs += "}" + '\n';
        //                }
        //                checkjs += "alert('试题已全部完成！');" + '\n';
        //                checkjs += "}" + '\n';
        //                checkjs += "function submitpaper()" + '\n';
        //                checkjs += "{" + '\n';
        //                checkjs += "if(window.confirm('您确实要提交吗？'))" + '\n';
        //                checkjs += "{" + '\n';
        //                //paperAnswerForm.action='/answerPaper.do';
        //                checkjs += "paperAnswerForm.submit();" + '\n';
        //                checkjs += "opener.location.reload();" + '\n';
        //                //window.close();
        //                checkjs += "}" + '\n';
        //                checkjs += "}" + '\n';
        //                checkjs += "--> " + '\n';
        //                checkjs += "</SCRIPT>" + '\n';
        //        }
        //
        //
        //        private void creatFileclock(int paperID, String path, int courseID) throws Exception
        //        {
        //                filejs = "<script language='javascript'>" + '\n';
        //                filejs += "var time_now_server,time_now_client,time_end,time_end1,time_server_client,time_server_client1,timerID;" + '\n' +
        //                        "time_now = new Date(); " + '\n' +
        //                        "time_now = time_now.getTime();" + '\n';
        //                filejs += "<%" + '\n';
        //                filejs += "Calendar cal = new Calendar();" + '\n';
        //
        //                filejs += "int hour = cal.getHour();" + '\n';
        //                filejs += "int minute = cal.getMinute();" + '\n';
        //                filejs += "int secend = cal.getSecond();" + '\n';
        //                PaperForm paperForm = null;
        //                PaperHelper paperHelper = new PaperHelper();
        //                paperForm = paperHelper.getPaper(paperID);
        //                title=paperForm.getTitle();
        //
        //                filejs += "if (paperForm.getDesc2().indexOf(\"A\") != -1)" + '\n';
        //                filejs += "{" + '\n';
        //                filejs += "cal.setDate(paperForm.getStartTime());" + '\n';
        //                filejs += "}" + '\n';
        //                filejs += "else if (paperForm.getDesc2().indexOf(\"B\") != -1)" + '\n';
        //                filejs += "{" + '\n';
        //                        filejs += "cal.setDate(new Date());" + '\n';
        //                filejs += "}" + '\n';
        //                filejs += "else if (paperForm.getDesc2().indexOf(\"C\") != -1)" + '\n';
        //                filejs += "{" + '\n';
        //                filejs += "ExambatchDAOImpl exambatchDAOImpl = new ExambatchDAOImpl();" + '\n';
        //                        filejs += "List paperList = exambatchDAOImpl.getPaperList(paperForm.getPaperID());" + '\n';
        //                        filejs += "for (int i = 0; i < paperList.size(); i++)" + '\n';
        //                        filejs += "{" + '\n';
        //                                filejs += "ExambatchForm exambatchModel = (ExambatchForm) paperList.get(i);" + '\n';
        //                                filejs += "if (DateTimeUtil.isTestTime(exambatchModel.getExambegintime(), new Date(), exambatchModel.getExamendtime()))" + '\n';
        //                                filejs += "{" + '\n';
        //                                filejs += "cal.setDate(exambatchModel.getExambegintime());" + '\n';
        //                                filejs += "}" + '\n';
        //                        filejs += "}" + '\n';
        //                filejs += "}" + '\n';
        //
        //                filejs += "int hour1 = cal.getHour();" + '\n';
        //                filejs += "int minute1 = cal.getMinute();" + '\n';
        //                filejs += "int secend1 = cal.getSecond();" + '\n';
        //                filejs += "%>" + '\n';
        //
        //                filejs += "time_end = (<%=paperForm.getDesc3()%>* 60 - <%=hour - hour1%>*60 * 60 - <%=minute - minute1%> *60 - <%=secend-secend1%>)*1000;" + '\n';
        //                filejs += "time_end1 = (<%=paperForm.getDesc3()%>* 60 - <%=hour - hour1%>*60 * 60 - <%=minute - minute1%> *60 - <%=secend-secend1%>)*1000;" + '\n';
        //                filejs += "time_server_client = time_end;" + '\n';
        //                filejs += "time_server_client1 = time_end1;" + '\n';
        //                filejs += "<%if (paperForm.getDesc3() != null && !paperForm.getDesc3().equals(\"\") && !paperForm.getDesc3().equals(\"0\"))" + '\n';
        //                filejs += "{%>" + '\n';
        //                        filejs += " setTimeout('show_time()', 1000);" + '\n';
        //                        filejs += " setTimeout('show_time1()', 1000);" + '\n';
        //                filejs += "<%}%>" + '\n';
        //
        //                filejs += "function show_time()" + '\n';
        //                filejs += "{" + '\n';
        //                filejs += "var courseID = " + courseID + ";" + '\n';
        //                filejs += "timer.innerHTML = \"<B><font color=red>\" + time_server_client + \"</font></B>\";" + '\n';
        //                filejs += "var time_now, time_distance, str_time;" + '\n';
        //                filejs += "var int_day, int_hour, int_minute, int_second;" + '\n';
        //                filejs += "time_end = time_end - 1000; " + '\n';
        //                filejs += "time_distance = time_end; " + '\n';
        //                /*
        //                filejs += "if (time_distance == 300000) " + '\n';
        //                filejs += "{" + '\n';
        //                filejs += "alert('对不起，您还有5分钟答卷的时间！'); " + '\n';
        //                filejs += "} " + '\n';
        //                */
        //                filejs += "if (time_distance > 0)" + '\n';
        //                filejs += "{" + '\n';
        //                filejs += "int_day = Math.floor(time_distance / 86400000)" + '\n';
        //                filejs += "time_distance -= int_day * 86400000;" + '\n';
        //                filejs += "int_hour = Math.floor(time_distance / 3600000) " + '\n';
        //                filejs += "time_distance -= int_hour * 3600000;" + '\n';
        //                filejs += "int_minute = Math.floor(time_distance / 60000) " + '\n';
        //                filejs += "time_distance -= int_minute * 60000; " + '\n';
        //                filejs += "int_second = Math.floor(time_distance / 1000) " + '\n';
        //
        //                filejs += "if (int_hour < 10) " + '\n';
        //                filejs += "{ " + '\n';
        //                filejs += "int_hour = '0' + int_hour;  " + '\n';
        //                filejs += "}   " + '\n';
        //                filejs += "if (int_minute < 10)  " + '\n';
        //                filejs += "{   " + '\n';
        //                filejs += "int_minute = '0' + int_minute;  " + '\n';
        //                filejs += "} " + '\n';
        //                filejs += "if (int_second < 10)   " + '\n';
        //                filejs += "{   " + '\n';
        //                filejs += "int_second = '0' + int_second;  " + '\n';
        //                filejs += "} " + '\n';
        //                filejs += "str_time = + int_hour +\"<font color=#000000>时</font>\" + int_minute +\"<font color=#000000>分</font>\"+ int_second +\"<font color=#000000>秒</font>\";" + '\n';
        //                filejs += "timer.innerHTML = \"<B><font color=red>\" + str_time + \"</font></B>\"; " + '\n';
        //                filejs += "setTimeout('show_time()', 1000);" + '\n';
        //                filejs += "}" + '\n';
        //                filejs += "else" + '\n';
        //                filejs += "{ " + '\n';
        //                filejs += "paperAnswerForm.submit();" + '\n';
        //                filejs += "alert('对不起，考试时间结束，试卷已自动提交！'); " + '\n';
        //                filejs += "window.parent.href = '" + path + "/mystudy/course/test/paper/listexam.jsp?flag=1&courseID=' + courseID;" + '\n';
        //                filejs += "clearTimeout(timerID);" + '\n';
        //                filejs += "} " + '\n';
        //                filejs += "}" + '\n';
        //                filejs += "function show_time1()" + '\n';
        //                filejs += "{" + '\n';
        //
        //                filejs += "timer1.innerHTML = \"<B><font color=red>\" + time_server_client1 +\"</font></B>\";" + '\n';
        //                filejs += "var time_now, time_distance1, str_time1; " + '\n';
        //                filejs += "var int_day1, int_hour1, int_minute1, int_second1;   " + '\n';
        //                filejs += "time_end1 = time_end1 - 1000; " + '\n';
        //                filejs += "time_distance1 = time_end1; " + '\n';
        //                filejs += "if (time_distance1 > 0)    " + '\n';
        //                filejs += "{" + '\n';
        //                filejs += "int_day1 = Math.floor(time_distance1 / 86400000)" + '\n';
        //                filejs += "time_distance1 -= int_day1 * 86400000; " + '\n';
        //                filejs += "int_hour1 = Math.floor(time_distance1 / 3600000) " + '\n';
        //                filejs += "time_distance1 -= int_hour1 * 3600000;   " + '\n';
        //                filejs += "int_minute1 = Math.floor(time_distance1 / 60000) " + '\n';
        //                filejs += "time_distance1 -= int_minute1 * 60000; " + '\n';
        //                filejs += "int_second1 = Math.floor(time_distance1 / 1000)" + '\n';
        //
        //                filejs += "if (int_hour1 < 10)" + '\n';
        //                filejs += "{" + '\n';
        //                filejs += "int_hour1 = '0' + int_hour1;" + '\n';
        //                filejs += "}" + '\n';
        //                filejs += "if (int_minute1 < 10) " + '\n';
        //                filejs += "{ " + '\n';
        //                filejs += "int_minute1 = '0' + int_minute1; " + '\n';
        //                filejs += "}" + '\n';
        //                filejs += "if (int_second1 < 10)" + '\n';
        //                filejs += "{" + '\n';
        //                filejs += "int_second1 = '0' + int_second1;" + '\n';
        //                filejs += "}" + '\n';
        //                filejs += "str_time1 = + int_hour1 + \"<font color=#000000>时</font>\" + int_minute1 + \"<font color=#000000>分</font>\" + int_second1 + \"<font color=#000000>秒</font>\"" + '\n';
        //                filejs += "timer1.innerHTML = \"<B><font color=red>\" + str_time1 + \"</font></B>\"" + '\n';
        //                filejs += "setTimeout('show_time1()', 1000);" + '\n';
        //                filejs += "}" + '\n';
        //                filejs += "}" + '\n';
        //                filejs += "</script>" + '\n';
        //        }
        /*
          private void creatFileclock(int paperID, String path, int courseID) throws Exception
          {
                  filejs = "<script language='javascript'>" + '\n';
                  filejs += "var time_now_server,time_now_client,time_end,time_end1,time_server_client,time_server_client1,timerID;" + '\n' +
                          "time_now = new Date(); " + '\n' +
                          "time_now = time_now.getTime();" + '\n';
                  Calendar cal = new Calendar();
                  int hour = cal.getHour();
                  int minute = cal.getMinute();
                  int secend = cal.getSecond();
                  PaperForm paperForm = null;
                  PaperHelper paperHelper = new PaperHelper();
                  paperForm = paperHelper.getPaper(paperID);
                  title=paperForm.getTitle();
                  if (paperForm.getDesc2().indexOf("A") != -1)
                  {
                          cal.setDate(paperForm.getStartTime());
                  }
                  else if (paperForm.getDesc2().indexOf("B") != -1)
                  {
                          cal.setDate(new Date());
                  }
                  else if (paperForm.getDesc2().indexOf("C") != -1)
                  {
                          ExambatchDAOImpl exambatchDAOImpl = new ExambatchDAOImpl();
                          List paperList = exambatchDAOImpl.getPaperList(paperForm.getPaperID());
                          for (int i = 0; i < paperList.size(); i++)
                          {
                                  ExambatchForm exambatchModel = (ExambatchForm) paperList.get(i);
                                  if (DateTimeUtil.isTestTime(exambatchModel.getExambegintime(), new Date(), exambatchModel.getExamendtime()))
                                  {
                                          cal.setDate(exambatchModel.getExambegintime());
                                  }
                          }
                  }
                  int hour1 = cal.getHour();
                  int minute1 = cal.getMinute();
                  int secend1 = cal.getSecond();
                  filejs += "time_end = (" + paperForm.getDesc3() + " * 60 - " + (hour - hour1) + "*60 * 60 - " + (minute - minute1) + "*60 - " + (secend - secend1) + ")*1000;" + '\n';
                  filejs += "time_end1 = (" + paperForm.getDesc3() + " * 60 - " + (hour - hour1) + "*60 * 60 - " + (minute - minute1) + "*60 - " + (secend - secend1) + ")*1000;" + '\n';
                  filejs += "time_server_client = time_end;" + '\n';
                  filejs += "time_server_client1 = time_end1;" + '\n';
                  if (paperForm.getDesc3() != null && !paperForm.getDesc3().equals("") && !paperForm.getDesc3().equals("0"))
                  {
                          filejs += "setTimeout('show_time()', 1000);" + '\n';
                          filejs += "setTimeout('show_time1()', 1000);" + '\n';
                  }
                  filejs += "function show_time()" + '\n';
                  filejs += "{" + '\n';
                  filejs += "var courseID = " + courseID + ";" + '\n';
                  filejs += "timer.innerHTML = '<B><font color=\\'red\\'>' + time_server_client + '</font></B>';" + '\n';
                  filejs += "var time_now, time_distance, str_time;" + '\n';
                  filejs += "var int_day, int_hour, int_minute, int_second;" + '\n';
                  filejs += "time_end = time_end - 1000; " + '\n';
                  filejs += "time_distance = time_end; " + '\n';
                  filejs += "if (time_distance == 300000) " + '\n';
                  filejs += "{" + '\n';
                  filejs += "alert('对不起，您还有5分钟答卷的时间！'); " + '\n';
                  filejs += "} " + '\n';
                  filejs += "if (time_distance > 0)" + '\n';
                  filejs += "{" + '\n';
                  filejs += "int_day = Math.floor(time_distance / 86400000)" + '\n';
                  filejs += "time_distance -= int_day * 86400000;" + '\n';
                  filejs += "int_hour = Math.floor(time_distance / 3600000) " + '\n';
                  filejs += "time_distance -= int_hour * 3600000;" + '\n';
                  filejs += "int_minute = Math.floor(time_distance / 60000) " + '\n';
                  filejs += "time_distance -= int_minute * 60000; " + '\n';
                  filejs += "int_second = Math.floor(time_distance / 1000) " + '\n';
                  filejs += "if (int_hour < 10) " + '\n';
                  filejs += "{ " + '\n';
                  filejs += "int_hour = '0' + int_hour;  " + '\n';
                  filejs += "}   " + '\n';
                  filejs += "if (int_minute < 10)  " + '\n';
                  filejs += "{   " + '\n';
                  filejs += "int_minute = '0' + int_minute;  " + '\n';
                  filejs += "} " + '\n';
                  filejs += "if (int_second < 10)   " + '\n';
                  filejs += "{   " + '\n';
                  filejs += "int_second = '0' + int_second;  " + '\n';
                  filejs += "} " + '\n';
                  filejs += "str_time = '<font color=\\'#000000\\'></font>' + int_hour + '<font color=\\'#000000\\'>时</font>' + int_minute + '<font color=\\'#000000\\'>分</font>' + int_second + '<font color=\\'#000000\\'>秒</font>';" + '\n';
                  filejs += "timer.innerHTML = '<B><font color=\\'red\\'>' + str_time + '</font></B>'; " + '\n';
                  filejs += "setTimeout('show_time()', 1000);" + '\n';
                  filejs += "}" + '\n';
                  filejs += "else" + '\n';
                  filejs += "{ " + '\n';
                  filejs += "paperAnswerForm.submit();" + '\n';
                  filejs += "alert('对不起，考试时间结束，试卷已自动提交！'); " + '\n';
                  filejs += "window.parent.href = '" + path + "/mystudy/course/test/paper/listexam.jsp?flag=1&courseID=' + courseID;" + '\n';
                  filejs += "clearTimeout(timerID);" + '\n';
                  filejs += "} " + '\n';
                  filejs += "}" + '\n';
                  filejs += "function show_time1()" + '\n';
                  filejs += "{" + '\n';
                  filejs += "timer1.innerHTML = '<B><font color=\\'red\\'>' + time_server_client1 + '</font></B>';" + '\n';
                  filejs += "var time_now, time_distance1, str_time1; " + '\n';
                  filejs += "var int_day1, int_hour1, int_minute1, int_second1;   " + '\n';
                  filejs += "time_end1 = time_end1 - 1000; " + '\n';
                  filejs += "time_distance1 = time_end1; " + '\n';
                  filejs += "if (time_distance1 > 0)    " + '\n';
                  filejs += "{" + '\n';
                  filejs += "int_day1 = Math.floor(time_distance1 / 86400000)" + '\n';
                  filejs += "time_distance1 -= int_day1 * 86400000; " + '\n';
                  filejs += "int_hour1 = Math.floor(time_distance1 / 3600000) " + '\n';
                  filejs += "time_distance1 -= int_hour1 * 3600000;   " + '\n';
                  filejs += "int_minute1 = Math.floor(time_distance1 / 60000) " + '\n';
                  filejs += "time_distance1 -= int_minute1 * 60000; " + '\n';
                  filejs += "int_second1 = Math.floor(time_distance1 / 1000)" + '\n';
                  filejs += "if (int_hour1 < 10)" + '\n';
                  filejs += "{" + '\n';
                  filejs += "int_hour1 = '0' + int_hour1;" + '\n';
                  filejs += "}" + '\n';
                  filejs += "if (int_minute1 < 10) " + '\n';
                  filejs += "{ " + '\n';
                  filejs += "int_minute1 = '0' + int_minute1; " + '\n';
                  filejs += "}" + '\n';
                  filejs += "if (int_second1 < 10)" + '\n';
                  filejs += "{" + '\n';
                  filejs += "int_second1 = '0' + int_second1;" + '\n';
                  filejs += "}" + '\n';
                  filejs += "str_time1 = '<font color=\\'#000000\\'></font>' + int_hour1 + '<font color=\\'#000000\\'>时</font>' + int_minute1 + '<font color=\\'#000000\\'>分</font>' + int_second1 + '<font color=\\'#000000\\'>秒</font>'" + '\n';
                  filejs += "timer1.innerHTML = '<B><font color=\\'red\\'>' + str_time1 + '</font></B>'" + '\n';
                  filejs += "setTimeout('show_time1()', 1000);" + '\n';
                  filejs += "}" + '\n';
                  filejs += "}" + '\n';
                  filejs += "</script>" + '\n';
          }
        */

        //single表示是否为单题考卷

        private String creatFilestr(boolean single)
        {
                int num = 0;
                int i = 0;
                int j = 0;
                int number = 1;

                for (i = 0; i < answerType; i++)
                {
                        if (!single)
                        {
                                if (count[i] > 0)
                                {
                                        fileinfodoc = fileinfodoc + content[i][0];
                                        fileinfo = fileinfo + content[i][0];

                                        //System.out.println(content[i][0]);
                                        //fileinfodoc= fileinfo + content[i][0].substring(content[i][0].indexOf("questionType"));;
                                }
                        }

                        for (j = 1; j <= count[i]; j++)
                        {
                                ++num;

                                String dis = "none";

                                if (number == 1)
                                {
                                        dis = "";
                                }

                                if (!single)
                                {
                                        fileinfodoc = fileinfodoc +
                                                "<tr><td><table><tr><td><table>" + '\n';
                                        fileinfo = fileinfo + "<tr><td><table><tr><td><table>" +
                                                '\n';
                                }
                                else
                                {
                                        fileinfodoc = fileinfodoc + "<tr><td><table id='id_" +
                                                number + "' style='display:" + dis + "'>" +
                                                content[i][0] + "<tr><td><table>" + '\n';
                                        fileinfo = fileinfo + "<tr><td><table id='id_" + number +
                                                "' style='display:" + dis + "'>" + content[i][0] +
                                                "<tr><td><table>" + '\n';
                                }

                                fileinfodoc = fileinfodoc + "<tr><td class='th1'>第" + num +
                                        "题：（" + score[num - 1] + "分）</td></tr>" + '\n';
                                fileinfo = fileinfo + "<tr><td class='th1'>第" + num + "题：（" +
                                        score[num - 1] + "分）</td></tr>" + '\n';

                                fileinfodoc = fileinfodoc +
                                        (content[i][j].substring(0,
                                                content[i][j].indexOf("questionType") - 27));
                                //System.out.println(fileinfodoc);
                                fileinfo = fileinfo + content[i][j];
                                //System.out.println(content[i][j].substring(0,content[i][j].indexOf("questionType")-27));
                                //System.out.println(content[i][j].indexOf("questionType")+"=="+content[i][j].length()+"-------------------------------------------------------------------");
                                fileinfo = fileinfo + "</table></td><td>" + '\n';
                                fileinfodoc = fileinfodoc + "</table></td><td>" + '\n';

                                if ((pic[num - 1] != null) && (pic[num - 1].length() != 0))
                                {
                                        fileinfo = fileinfo + "<img src=" +
                                                Config.getUploadVirtualPath() + pic[num - 1] +
                                                " width='300' high='300'/>" + '\n';
                                        fileinfodoc = fileinfodoc + "<img src=" +
                                                Config.getUploadVirtualPath() + pic[num - 1] +
                                                " width='300' high='300'/>" + '\n';
                                }
                                else
                                {
                                        fileinfo = fileinfo + "&nbsp;" + '\n';
                                        fileinfodoc = fileinfodoc + "&nbsp;" + '\n';
                                }

                                fileinfo = fileinfo + "</td></tr></table></td></tr>" + '\n';
                                fileinfodoc = fileinfodoc + "</td></tr></table></td></tr>" +
                                        '\n';

                                number++;
                        }
                }

                //fileinfo = filstr + "<table>" + fileinfo + "</table>";
                fileinfodoc = "<%@ page contentType=\"application/msword;charset=GBK\"%>" +
                        '\n' +
                        "<link href=\"css/exam.css\" rel=\"stylesheet\" type=\"text/css\">" +
                        '\n' + "<table>" + fileinfodoc + "</table>";

                String filstr = "<body onkeydown=\"if(event.keyCode ==116 || event.ctrlKey && (event.keyCode == 82 || event.keyCode == 110)){event.keyCode=0;return false;} \" " +
                        "ondragstart=\"javascript:return false;javascript:window.event.returnvalue=false\" " +
                        "onselectstart=\"return false\" onpaste=\"return false\" " +
                        "oncontextmenu=\"self.event.returnValue=false\">" + '\n';
                filstr += ("<link href=\"css/exam.css\" rel=\"stylesheet\" type=\"text/css\">" +
                        '\n');
                filstr += "";
                filstr += ("<table width=\"770\" height=\"35\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\">\n" +
                        "  <tr><td width=\"29\"><img src=\"images/lefttop.gif\" width=\"29\" height=\"35\"></td>\n" +
                        "    <td background=\"images/top.gif\">&nbsp;</td>\n" +
                        "    <td width=\"29\"><img src=\"images/righttop.gif\" width=\"29\" height=\"35\"></td></tr></table>" +
                        '\n');

                filstr += ("<table width=\"770\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\">" +
                        "<tr><td width=\"16\" background=\"images/left.gif\"><img src=\"images/left.gif\" width=\"16\" height=\"1\"></td><td>");

                filstr += "<form method=\"post\" action=\"../../../../answerPaper\" name=\"paperAnswerForm\">";
                filstr += ("<input type=\"hidden\" name=\"userID\" value=<%=userID%>>" +
                        '\n');
                filstr += ("<input type=\"hidden\" name=\"paperID\" value=" +
                        paperForm.getPaperID() + ">" + '\n');
                filstr += ("<input type=\"hidden\" name=\"courseID\" value=" +
                        paperForm.getCourseID() + ">" + '\n');
                filstr += ("<input type=\"hidden\" name=\"papertype\" value=" +
                        paperForm.getType() + ">" + '\n');
                filstr += ("<table width=\"100%\" border='0' align='center' cellpadding='0' cellspacing='0'>\n" +
                        "  <tr><td class=\"scrollbg\">&nbsp;</td>\n" +
                        "<td id=\"timer\"' class=\"time\">&nbsp;</td>\n");

                if (single)
                {
                        filstr += ("<td class=\"btn\" ><a href=\"javascript:pre()\">上一题</a></td>\n" +
                                "<td class=\"btn\" ><a href=\"javascript:next()\">下一题</a></td>\n");
                }
                else
                {
                        filstr += "<td class=\"btn\" ><a href=\"javascript:checkDone()\">检查答卷</a></td>";
                }

                filstr += ("<td class=\"btn\"><a href=\"javascript:submitpaper()\">提交答卷</a>\n" +
                        "</td></tr></table>" + '\n');
                filstr += ("<table width='738' border='0' align='center'>" + '\n' +
                        "<tr><td width='718' height='37'><div align='center'>" + '\n' +
                        "<p class=\"title\">" + paperForm.getTitle() + "</p>" + '\n' +
                        "</div></td></tr><tr><td align='center'><p class=\"examinfo\">" + '\n' +
                        "（全卷共" + fullnum + "题，满分" + fullScore + "分，考试时间");

                //paperForm.getDesc3() != null && paperForm.getDesc3().equals("0") ? "不限时" : StringUtil.checkEmpty(paperForm.getDesc3() + "分钟") +
                if ((paperForm.getDesc3() != null) && paperForm.getDesc3().equals("0"))
                {
                        filstr += "不限时";
                }
                else
                {
                        filstr += (StringUtil.checkEmpty(paperForm.getDesc3()) + "分钟");
                }

                filstr += ("）</p></td>" + '\n' + "</tr><tr><td height='37'>" + '\n' +
                        "<table width='100%' border='0' align='center' cellpadding='0' cellspacing='0' class=\"stuinfo\">" +
                        '\n' + "<tr><th>姓名：</th>" + '\n' +
                        "<td><input name='textfield' size='10' type=text value=<%=name%> readonly></td>" +
                        '\n' + "<th>E_mail：</th>" + '\n' +
                        "<td><input name='textfield2 size='25' type=text readonly></td>" +
                        '\n' + "<th>成绩：</th>" + '\n' +
                        "<td><input name='textfield3' type=text size='25' readonly></td>" +
                        '\n' +
                        "</tr><tr><td >&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr></table></td></tr></table>" +
                        '\n');

                fileinfo = filstr + "<table>" + fileinfo + "</table>";

                //fileinfodoc= fileinfo;
                fileinfo += ("<table width=\"100%\" border='0' align='center' cellpadding='0' cellspacing='0'>\n" +
                        "  <tr><td class=\"scrollbg\">&nbsp;</td>\n" +
                        "<td id=\"timer1\"' class=\"time\">&nbsp;</td>");

                if (single)
                {
                        fileinfo += ("<td class=\"btn\" ><a href=\"javascript:pre()\">上一题</a></td>\n" +
                                "<td class=\"btn\" ><a href=\"javascript:next()\">下一题</a></td>\n");
                }
                else
                {
                        fileinfo += "<td class=\"btn\" ><a href=\"javascript:checkDone()\">检查答卷</a></td>";
                }

                fileinfo += ("<td class=\"btn\"><a href=\"javascript:submitpaper()\">提交答卷</a>\n" +
                        "</td></tr></table></form>" + '\n');

                fileinfo += ("</td><td width=\"16\" background=\"images/right.gif\"><img src=\"images/right.gif\" width=\"16\" height=\"1\"></td>" +
                        "</tr></table>");
                fileinfo += ("<table width=\"770\" height=\"44\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\">" +
                        "<tr><td width=\"29\"><img src=\"images/leftbm.gif\" width=\"29\" height=\"44\"></td>" +
                        "<td background=\"images/bm.gif\">&nbsp;</td>" +
                        "<td width=\"29\"><img src=\"images/rightbm.gif\" width=\"29\" height=\"44\"></td></tr></table>" +
                        "</body>");

                String Useridstr = "";
                Useridstr = "<%@ page language=\"java\" contentType=\"text/html;charset=GB2312\" %>\n" +
                        "<%@ taglib uri=\"/WEB-INF/tlds/struts-html.tld\" prefix=\"html\" %>\n" +
                        "<%@ taglib uri=\"/WEB-INF/tlds/struts-bean.tld\" prefix=\"bean\" %>\n" +
                        "<%@ taglib uri=\"/WEB-INF/tlds/struts-logic.tld\" prefix=\"logic\" %>\n" +
                        "<%@ page import=\"com.ulearning.ulms.core.util.*\" %>\n" +
                        "<%@ page import=\"java.util.Date\" %>\n" +
                        "<%@ page import=\"java.util.List\" %>\n" +
                        "<%@ page import=\"com.ulearning.ulms.util.*\" %>\n" +
                        "<%@ page import=\"com.ulearning.ulms.course.test.paper.form.PaperForm\" %>\n" +
                        "<%@ page import=\"com.ulearning.ulms.course.test.paper.bean.PaperHelper\" %>\n" +
                        "<%@ page import=\"com.ulearning.ulms.course.test.Exambatch.form.ExambatchForm\" %>\n" +
                        "<%@ page import=\"com.ulearning.ulms.course.test.Exambatch.dao.ExambatchDAOImpl\" %>\n" +
                        "<%@ page import=\"com.ulearning.ulms.util.LMSConstants\" %>\n";
                Useridstr += ("<%Object object = session.getAttribute(LMSConstants.USERID_KEY);" +
                        '\n' + "int userID = 0;" + '\n' + "String name = \"匿名登陆者\"; " + '\n' +
                        "int paperID = Integer.parseInt((String)request.getParameter(\"paperID\"));" +
                        '\n' + "PaperHelper paperHelper = new PaperHelper(); " + '\n' +
                        "PaperForm paperForm = paperHelper.getPaper(paperID);" + '\n' +
                        "if(object != null) " + '\n' + "{ " + '\n' +
                        "        userID = Integer.parseInt((String)session.getAttribute(LMSConstants.USERID_KEY)); " +
                        '\n' +
                        "        name = (String)session.getAttribute(LMSConstants.LOGINNAME_KEY_NAME);" +
                        '\n' + "}%>");

                fileinfo = Useridstr + fileinfo;

                return fileinfo;
        }

        private String creatFilestr()
        {
                int num = 0;
                int i = 0;
                int j = 0;

                for (i = 0; i < answerType; i++)
                {
                        if (count[i] > 0)
                        {
                                fileinfodoc = fileinfodoc + content[i][0];
                                fileinfo = fileinfo + content[i][0];

                                //System.out.println(content[i][0]);
                                //fileinfodoc= fileinfo + content[i][0].substring(content[i][0].indexOf("questionType"));;
                        }

                        for (j = 1; j <= count[i]; j++)
                        {
                                ++num;
                                fileinfodoc = fileinfodoc + "<tr><td><table><tr><td><table>" +
                                        '\n';
                                fileinfo = fileinfo + "<tr><td><table><tr><td><table>" + '\n';
                                fileinfodoc = fileinfodoc + "<tr><td class='th1'>第" + num +
                                        "题：（" + score[num - 1] + "分）</td></tr>" + '\n';
                                fileinfo = fileinfo + "<tr><td class='th1'>第" + num + "题：（" +
                                        score[num - 1] + "分）</td></tr>" + '\n';

                                fileinfodoc = fileinfodoc +
                                        (content[i][j].substring(0,
                                                content[i][j].indexOf("questionType") - 27));
                                //System.out.println(fileinfodoc);
                                fileinfo = fileinfo + content[i][j];
                                //System.out.println(content[i][j].substring(0,content[i][j].indexOf("questionType")-27));
                                //System.out.println(content[i][j].indexOf("questionType")+"=="+content[i][j].length()+"-------------------------------------------------------------------");
                                fileinfo = fileinfo + "</table></td><td>" + '\n';
                                fileinfodoc = fileinfodoc + "</table></td><td>" + '\n';

                                if ((pic[num - 1] != null) && (pic[num - 1].length() != 0))
                                {
                                        fileinfo = fileinfo + "<img src=" +
                                                Config.getUploadVirtualPath() + pic[num - 1] +
                                                " width='300' high='300'/>" + '\n';
                                        fileinfodoc = fileinfodoc + "<img src=" +
                                                Config.getUploadVirtualPath() + pic[num - 1] +
                                                " width='300' high='300'/>" + '\n';
                                }
                                else
                                {
                                        fileinfo = fileinfo + "&nbsp;" + '\n';
                                        fileinfodoc = fileinfodoc + "&nbsp;" + '\n';
                                }

                                fileinfo = fileinfo + "</td></tr></table></td></tr>" + '\n';
                                fileinfodoc = fileinfodoc + "</td></tr></table></td></tr>" +
                                        '\n';
                        }
                }

                //fileinfo = filstr + "<table>" + fileinfo + "</table>";
                fileinfodoc = "<%@ page contentType=\"application/msword;charset=GBK\"%>" +
                        '\n' +
                        "<link href=\"css/exam.css\" rel=\"stylesheet\" type=\"text/css\">" +
                        '\n' + "<table>" + fileinfodoc + "</table>";

                String filstr = "<body onkeydown=\"if(event.keyCode ==116 || event.ctrlKey && (event.keyCode == 82 || event.keyCode == 110)){event.keyCode=0;return false;} \" " +
                        "ondragstart=\"javascript:return false;javascript:window.event.returnvalue=false\" " +
                        "onselectstart=\"return false\" onpaste=\"return false\" " +
                        "oncontextmenu=\"self.event.returnValue=false\">" + '\n';
                filstr += ("<link href=\"css/exam.css\" rel=\"stylesheet\" type=\"text/css\">" +
                        '\n');
                filstr += "";
                filstr += ("<table width=\"770\" height=\"35\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\">\n" +
                        "  <tr><td width=\"29\"><img src=\"images/lefttop.gif\" width=\"29\" height=\"35\"></td>\n" +
                        "    <td background=\"images/top.gif\">&nbsp;</td>\n" +
                        "    <td width=\"29\"><img src=\"images/righttop.gif\" width=\"29\" height=\"35\"></td></tr></table>" +
                        '\n');

                filstr += ("<table width=\"770\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\">" +
                        "<tr><td width=\"16\" background=\"images/left.gif\"><img src=\"images/left.gif\" width=\"16\" height=\"1\"></td><td>");

                filstr += "<form method=\"post\" action=\"../../../../answerPaper\" name=\"paperAnswerForm\">";
                filstr += ("<input type=\"hidden\" name=\"userID\" value=<%=userID%>>" +
                        '\n');
                filstr += ("<input type=\"hidden\" name=\"paperID\" value=" +
                        paperForm.getPaperID() + ">" + '\n');
                filstr += ("<input type=\"hidden\" name=\"courseID\" value=" +
                        paperForm.getCourseID() + ">" + '\n');
                filstr += ("<input type=\"hidden\" name=\"papertype\" value=" +
                        paperForm.getType() + ">" + '\n');
                filstr += ("<table width=\"100%\" border='0' align='center' cellpadding='0' cellspacing='0'>\n" +
                        "  <tr><td class=\"scrollbg\">&nbsp;</td>\n" +
                        "<td id=\"timer\"' class=\"time\">&nbsp;</td><td class=\"btn\" >\n" +
                        "<a href=\"javascript:checkDone()\">检查答卷</a></td><td class=\"btn\"><a href=\"javascript:submitpaper()\">提交答卷</a>\n" +
                        "</td></tr></table>" + '\n');
                filstr += ("<table width='738' border='0' align='center'>" + '\n' +
                        "<tr><td width='718' height='37'><div align='center'>" + '\n' +
                        "<p class=\"title\">" + paperForm.getTitle() + "</p>" + '\n' +
                        "</div></td></tr><tr><td align='center'><p class=\"examinfo\">" + '\n' +
                        "（全卷共" + fullnum + "题，满分" + fullScore + "分，考试时间");

                //paperForm.getDesc3() != null && paperForm.getDesc3().equals("0") ? "不限时" : StringUtil.checkEmpty(paperForm.getDesc3() + "分钟") +
                if ((paperForm.getDesc3() != null) && paperForm.getDesc3().equals("0"))
                {
                        filstr += "不限时";
                }
                else
                {
                        filstr += (StringUtil.checkEmpty(paperForm.getDesc3()) + "分钟");
                }

                filstr += ("）</p></td>" + '\n' + "</tr><tr><td height='37'>" + '\n' +
                        "<table width='100%' border='0' align='center' cellpadding='0' cellspacing='0' class=\"stuinfo\">" +
                        '\n' + "<tr><th>姓名：</th>" + '\n' +
                        "<td><input name='textfield' size='10' type=text value=<%=name%> readonly></td>" +
                        '\n' + "<th>E_mail：</th>" + '\n' +
                        "<td><input name='textfield2 size='25' type=text readonly></td>" +
                        '\n' + "<th>成绩：</th>" + '\n' +
                        "<td><input name='textfield3' type=text size='25' readonly></td>" +
                        '\n' +
                        "</tr><tr><td >&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr></table></td></tr></table>" +
                        '\n');

                fileinfo = filstr + "<table>" + fileinfo + "</table>";

                //fileinfodoc= fileinfo;
                fileinfo += ("<table width=\"100%\" border='0' align='center' cellpadding='0' cellspacing='0'>\n" +
                        "  <tr><td class=\"scrollbg\">&nbsp;</td>\n" +
                        "<td id=\"timer1\"' class=\"time\">&nbsp;</td><td class=\"btn\" >\n" +
                        "<a href=\"javascript:checkDone()\">检查答卷</a></td><td class=\"btn\"><a href=\"javascript:submitpaper()\">提交答卷</a>\n" +
                        "</td></tr></table></form>" + '\n');

                fileinfo += ("</td><td width=\"16\" background=\"images/right.gif\"><img src=\"images/right.gif\" width=\"16\" height=\"1\"></td>" +
                        "</tr></table>");
                fileinfo += ("<table width=\"770\" height=\"44\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\">" +
                        "<tr><td width=\"29\"><img src=\"images/leftbm.gif\" width=\"29\" height=\"44\"></td>" +
                        "<td background=\"images/bm.gif\">&nbsp;</td>" +
                        "<td width=\"29\"><img src=\"images/rightbm.gif\" width=\"29\" height=\"44\"></td></tr></table>" +
                        "</body>");

                String Useridstr = "";
                Useridstr = "<%@ page language=\"java\" contentType=\"text/html;charset=GB2312\" %>\n" +
                        "<%@ taglib uri=\"/WEB-INF/tlds/struts-html.tld\" prefix=\"html\" %>\n" +
                        "<%@ taglib uri=\"/WEB-INF/tlds/struts-bean.tld\" prefix=\"bean\" %>\n" +
                        "<%@ taglib uri=\"/WEB-INF/tlds/struts-logic.tld\" prefix=\"logic\" %>\n" +
                        "<%@ page import=\"com.ulearning.ulms.core.util.*\" %>\n" +
                        "<%@ page import=\"java.util.Date\" %>\n" +
                        "<%@ page import=\"java.util.List\" %>\n" +
                        "<%@ page import=\"com.ulearning.ulms.core.util.*\" %>\n" +
                        "<%@ page import=\"com.ulearning.ulms.course.test.paper.form.PaperForm\" %>\n" +
                        "<%@ page import=\"com.ulearning.ulms.course.test.paper.bean.PaperHelper\" %>\n" +
                        "<%@ page import=\"com.ulearning.ulms.course.test.Exambatch.form.ExambatchForm\" %>\n" +
                        "<%@ page import=\"com.ulearning.ulms.course.test.Exambatch.dao.ExambatchDAOImpl\" %>\n" +
                        "<%@ page import=\"com.ulearning.ulms.util.LMSConstants\" %>\n";
                Useridstr += ("<%Object object = session.getAttribute(LMSConstants.USERID_KEY);" +
                        '\n' + "int userID = 0;" + '\n' + "String name = \"匿名登陆者\"; " + '\n' +
                        "int paperID = Integer.parseInt((String)request.getParameter(\"paperID\"));" +
                        '\n' + "PaperHelper paperHelper = new PaperHelper(); " + '\n' +
                        "PaperForm paperForm = paperHelper.getPaper(paperID);" + '\n' +
                        "if(object != null) " + '\n' + "{ " + '\n' +
                        "        userID = Integer.parseInt((String)session.getAttribute(LMSConstants.USERID_KEY)); " +
                        '\n' +
                        "        name = (String)session.getAttribute(LMSConstants.LOGINNAME_KEY_NAME);" +
                        '\n' + "}%>");

                fileinfo = Useridstr + fileinfo;

                return fileinfo;
        }

        private void creatList(int paperID, boolean flag)
        {
                PaperHelper paperHelper = new PaperHelper();
                PaperQuestionForm pqf = new PaperQuestionForm();
                paperForm = paperHelper.getPaper(paperID);

                ChoiceItemHelper chocieItemHelper = null;
                List choiceItemList = null;
                ChoiceItemForm cif = null;
                BaseHelper baseHelper = new BaseHelper();
                List questionList = PaperQuestionHelper.getPaperQuestionList(paperID);
                List baseList = null;

                if (flag)
                {
                        baseList = baseHelper.getBaseAList(questionList);
                }
                else
                {
                        baseList = baseHelper.getBaseList(questionList);
                }

                int size = 0;

                if (baseList != null)
                {
                        size = baseList.size();
                }

                int[] id = new int[size];

                int answerType = 5;
                String[][] content = new String[answerType][size + 1];

                String[] pic = new String[size];
                int[] count = new int[answerType];
                String[] score = new String[size];
                String[] title = new String[answerType];
                title[0] = "单选题";
                title[1] = "多选题 &nbsp;每题可能有两个或两个以上的答案,多选、少选和错选都不给分";
                title[2] = "填空题 &nbsp;多个答案时用‘;’隔开";
                title[3] = "判断题";
                title[4] = "问答题";

                String[] sequence = new String[answerType];
                sequence[0] = "";
                sequence[1] = "";
                sequence[2] = "";
                sequence[3] = "";
                sequence[4] = "";

                int[] order = {1, 2, 3, 4, 5};
                total = new int[answerType];
                totalScore = new float[answerType];

                for (int i = 0; (baseList != null) && (i < baseList.size()); i++)
                {
                        int Answer_Select = (int) 'A';
                        BaseForm bf = (BaseForm) baseList.get(i);
                        pqf = PaperQuestionHelper.getPaperQuestionForm(paperID,
                                bf.getQuestionID());
                        id[i] = bf.getQuestionID();

                        int onoff = 0;

                        switch (Integer.parseInt(bf.getType()))
                        {
                                case 1:
                                        onoff += 0;
                                        content[order[onoff] - 1][++count[order[onoff] - 1]] = "<tr><td class=\"th2\">" +
                                                bf.getTitle() + "&nbsp;&nbsp;</td></tr>";
                                        chocieItemHelper = new ChoiceItemHelper();
                                        choiceItemList = chocieItemHelper.getChoiceItemList(bf.getQuestionID());
                                        cif = new ChoiceItemForm();

                                        for (int j = 0; j < choiceItemList.size(); j++)
                                        {
                                                cif = (ChoiceItemForm) choiceItemList.get(j);
                                                content[order[onoff] - 1][count[order[onoff] - 1]] += ("<tr><td><input type=\"radio\" name=\"answer_" +
                                                        bf.getQuestionID() + "\" value=\"" + (char) Answer_Select +
                                                        "\"/>&nbsp;" + (char) Answer_Select + "&nbsp;" +
                                                        cif.getItemTitle() + "</td></tr>");
                                                Answer_Select++;
                                        }

                                        content[order[onoff] - 1][count[order[onoff] - 1]] += ("<tr><td>&nbsp;" +
                                                "<input type=\"hidden\" name=\"questionType\" value=\"" +
                                                bf.getType() + "\"/>" +
                                                "<input type=\"hidden\" name=\"questionID\" value=\"" +
                                                bf.getQuestionID() + "\"/></td></tr>");
                                        total[order[onoff] - 1]++;
                                        totalScore[order[onoff] - 1] = totalScore[order[onoff] - 1] +
                                                pqf.getScore();

                                        break;

                                case 2:
                                        onoff += 1;
                                        content[order[onoff] - 1][++count[order[onoff] - 1]] = "<tr><td class=\"th2\">" +
                                                bf.getTitle() + "&nbsp;&nbsp;</td></tr>";
                                        chocieItemHelper = new ChoiceItemHelper();
                                        choiceItemList = chocieItemHelper.getChoiceItemList(bf.getQuestionID());
                                        cif = new ChoiceItemForm();

                                        for (int j = 0; j < choiceItemList.size(); j++)
                                        {
                                                cif = (ChoiceItemForm) choiceItemList.get(j);
                                                content[order[onoff] - 1][count[order[onoff] - 1]] += ("<tr><td><input type=\"checkbox\" name=\"answer_" +
                                                        bf.getQuestionID() + "\" value=\"" + (char) Answer_Select +
                                                        "\"/>&nbsp;" + (char) Answer_Select + "&nbsp;" +
                                                        cif.getItemTitle() + "</td></tr>");
                                                Answer_Select++;
                                        }

                                        content[order[onoff] - 1][count[order[onoff] - 1]] += ("<tr><td>&nbsp;" +
                                                "<input type=\"hidden\" name=\"questionType\" value=\"" +
                                                bf.getType() + "\"/>" +
                                                "<input type=\"hidden\" name=\"questionID\" value=\"" +
                                                bf.getQuestionID() + "\"/></td></tr>");
                                        total[order[onoff] - 1]++;
                                        totalScore[order[onoff] - 1] = totalScore[order[onoff] - 1] +
                                                pqf.getScore();

                                        break;

                                case 3:
                                        onoff += 2;
                                        content[order[onoff] - 1][++count[order[onoff] - 1]] = "<tr><td class=\"th2\">" +
                                                bf.getTitle() + "&nbsp;&nbsp;</td></tr>";
                                        content[order[onoff] - 1][count[order[onoff] - 1]] += ("<tr><td><input type=\"text\" name=\"answer_" +
                                                bf.getQuestionID() +
                                                "\" size=\"30\" class=\"textfield01\" style=\"border-bottom-width:1px;" +
                                                "border-top-width:0px;border-left-width:0px;border-right-width:0px;\"/></td></tr>");
                                        content[order[onoff] - 1][count[order[onoff] - 1]] += ("<tr><td>&nbsp;" +
                                                "<input type=\"hidden\" name=\"questionType\" value=\"" +
                                                bf.getType() + "\"/>" +
                                                "<input type=\"hidden\" name=\"questionID\" value=\"" +
                                                bf.getQuestionID() + "\"/></td></tr>");
                                        total[order[onoff] - 1]++;
                                        totalScore[order[onoff] - 1] = totalScore[order[onoff] - 1] +
                                                pqf.getScore();

                                        break;

                                case 4:
                                        onoff += 3;
                                        content[order[onoff] - 1][++count[order[onoff] - 1]] = "<tr><td class=\"th2\">" +
                                                bf.getTitle() + "&nbsp;&nbsp;（ ）</td></tr>";
                                        content[order[onoff] - 1][count[order[onoff] - 1]] += ("<tr><td><input type=\"radio\" name=\"answer_" +
                                                bf.getQuestionID() + "\"" + "value=\"1\"/>正确&nbsp;&nbsp");
                                        Answer_Select++;
                                        content[order[onoff] - 1][count[order[onoff] - 1]] += ("<input type=\"radio\" name=\"answer_" +
                                                bf.getQuestionID() + "\" value=\"0\" />错误</td></tr>");
                                        content[order[onoff] - 1][count[order[onoff] - 1]] += ("<tr><td>&nbsp;" +
                                                "<input type=\"hidden\" name=\"questionType\" value=\"" +
                                                bf.getType() + "\"/>" +
                                                "<input type=\"hidden\" name=\"questionID\" value=\"" +
                                                bf.getQuestionID() + "\"/></td></tr>");
                                        total[order[onoff] - 1]++;
                                        totalScore[order[onoff] - 1] = totalScore[order[onoff] - 1] +
                                                pqf.getScore();

                                        break;

                                case 5:
                                        onoff += 4;
                                        content[order[onoff] - 1][++count[order[onoff] - 1]] = "<tr><td class=\"th2\">" +
                                                bf.getTitle() + "&nbsp;&nbsp;</td></tr>";
                                        content[order[onoff] - 1][count[order[onoff] - 1]] += ("<tr><td><div valign=\"top\"><b>答：</b></div><textarea cols='80' rows='5' name=\"answer_" +
                                                bf.getQuestionID() +
                                                "\" class='textfield01' style=\"border:0px;\"></textarea></td></tr>");
                                        content[order[onoff] - 1][count[order[onoff] - 1]] += ("<tr><td>&nbsp;" +
                                                "<input type=\"hidden\" name=\"questionType\" value=\"" +
                                                bf.getType() + "\"/>" +
                                                "<input type=\"hidden\" name=\"questionID\" value=\"" +
                                                bf.getQuestionID() + "\"/></td></tr>");
                                        total[order[onoff] - 1]++;
                                        totalScore[order[onoff] - 1] = totalScore[order[onoff] - 1] +
                                                pqf.getScore();

                                        break;
                        }

                        score[i] = pqf.getScore() + "";
                        pic[i] = bf.getLink();
                }

                int n = 1;

                for (int i = 0; i < answerType; i++)
                {
                        int m = 0;

                        for (m = 0; m < answerType; m++)
                        {
                                if (n == order[m])
                                {
                                        break;
                                }
                        }

                        content[i][0] = "<tr><td height=\"29\" class=\"tdcolor01\"><h1>" +
                                sequence[i] + title[m] + "&nbsp;共" + total[m] + "题&nbsp;" +
                                totalScore[m] + "分</h1></td></tr>";
                        n++;
                }

                float fullScore = 0;
                int fullnum = 0;

                for (int num = 0; num < totalScore.length; num++)
                {
                        fullScore += totalScore[num];
                        fullnum += total[num];
                }

                this.answerType = answerType;
                this.count = count;
                this.content = content;
                this.score = score;
                this.pic = pic;
                this.fullnum = fullnum;
                this.fullScore = fullScore;
                this.id = id;
        }

        public static void main(String[] args) throws Exception
        {
                CreatPaperFile aa = new CreatPaperFile();
                //aa.creatList(100);
                //aa.creatFilestr();
                //System.out.println(aa.creatFilestr());
                aa.makeFile(103, "D:\\paper\\", 42);

                //aa.creatFileclock(2, "", 5);
                //aa.creatcheckjs();
                //System.out.println(aa.checkjs);
        }
}
