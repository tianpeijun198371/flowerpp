package com.ulearning.ulms.util;


public class PageModel {
  //��ҳ��
  private String PAGECOUNT;
  //�ܼ�¼��
  private String RECORDCOUNT;
  //ҳ��¼��
  private String PAGERECORD;
  //��ǰҳ
  private String CURRENTPAGE;
  //��һҳ
  private String NEXTPAGE;
  //��һҳ
  private String PREVIOUSPAGE;

  /**
   * ���캯��:��ʼ������
   * @param PageCount @param PageCount
   * @param RecordCount @param RecordCount
   * @param PageRecord @param PageRecord
   * @param CurrentPage @param CurrentPage */
  public PageModel(long PageCount, long RecordCount, int PageRecord,
                   int CurrentPage) {
    //ȡ����ҳ��
    if (CurrentPage > 1) {
      PREVIOUSPAGE = String.valueOf(CurrentPage - 1);
    }
    else {
      PREVIOUSPAGE = "";
    }
    //ȡ����ҳ��
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

  /** ȡ����ҳ��
   *  @return String */
  public String getPAGECOUNT() {
    return PAGECOUNT;
  }

  /** ȡ�õ�ǰҳ
   * @return String */
  public String getCURRENTPAGE() {
    return CURRENTPAGE;
  }

  /** ȡ���ܼ�¼��
   * @return String */
  public String getRECORDCOUNT() {
    return RECORDCOUNT;
  }

  /** ȡ��ҳ��¼��
   * @return String */
  public String getPAGERECORD() {
    return PAGERECORD;
  }

  /** ȡ����һҳ��
   * @return String */
  public String getNEXTPAGE() {
    return NEXTPAGE;
  }

  /** ȡ����һҳ��
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