package com.ulearning.ulms.util;


public class PageModel {
  //总页数
  private String PAGECOUNT;
  //总记录数
  private String RECORDCOUNT;
  //页记录数
  private String PAGERECORD;
  //当前页
  private String CURRENTPAGE;
  //下一页
  private String NEXTPAGE;
  //上一页
  private String PREVIOUSPAGE;

  /**
   * 构造函数:初始化数据
   * @param PageCount @param PageCount
   * @param RecordCount @param RecordCount
   * @param PageRecord @param PageRecord
   * @param CurrentPage @param CurrentPage */
  public PageModel(long PageCount, long RecordCount, int PageRecord,
                   int CurrentPage) {
    //取得上页数
    if (CurrentPage > 1) {
      PREVIOUSPAGE = String.valueOf(CurrentPage - 1);
    }
    else {
      PREVIOUSPAGE = "";
    }
    //取得下页数
    if (CurrentPage < PageCount) {
      NEXTPAGE = String.valueOf(CurrentPage + 1);
    }
    else {
      NEXTPAGE = "";

    }

    this.PAGECOUNT = String.valueOf(PageCount);
    this.RECORDCOUNT = String.valueOf(RecordCount);
    this.PAGERECORD = String.valueOf(PageRecord);
    this.CURRENTPAGE = String.valueOf(CurrentPage);

  }

  /** 取得总页数
   *  @return String */
  public String getPAGECOUNT() {
    return PAGECOUNT;
  }

  /** 取得当前页
   * @return String */
  public String getCURRENTPAGE() {
    return CURRENTPAGE;
  }

  /** 取得总记录数
   * @return String */
  public String getRECORDCOUNT() {
    return RECORDCOUNT;
  }

  /** 取得页记录数
   * @return String */
  public String getPAGERECORD() {
    return PAGERECORD;
  }

  /** 取得下一页数
   * @return String */
  public String getNEXTPAGE() {
    return NEXTPAGE;
  }

  /** 取得上一页数
   * @return String */
  public String getPREVIOUSPAGE() {
    return PREVIOUSPAGE;
  }
  
  public PageModel getPageCountByRecordCount(long recordCount)
  {
  	long PageCount=Long.parseLong(this.getPAGECOUNT());
  	long RecordCount=Long.parseLong(this.getRECORDCOUNT());
  	int PageRecord=Integer.parseInt(this.getPAGERECORD());
  	int CurrentPage=Integer.parseInt(this.getCURRENTPAGE());
  	
  	if(recordCount%PageRecord==0)
	{
  		PageCount=recordCount/PageRecord;
	}
	else
	{
		PageCount=recordCount/PageRecord+1;
	}
  	
  	PageModel newPageModel=new PageModel(PageCount,recordCount,PageRecord,CurrentPage);
  	return newPageModel;
  	
  }
}