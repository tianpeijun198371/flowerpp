package com.ulearning.ulms.util;

import java.util.List;

/**
 * <p>Title: 华夏大地自动化网络办公系统---- B2C系统 </p>
 * <p>Description: 爱迪思维基础类库</p>
 * <p>Function Description:    </p>
 * <p>Company : 华夏大地教育网</p>
 * @author ： 黄新娥  </p>
 * @version：1.0 </p>
 */
public class ArrayListModel  {
  private List arrayList;
  private int pageCount;
  private int recordCount;
  private int pageRecord;
  private int currentPage;

  /** @param arrayList @param arrayList
   * @param PageCount @param PageCount
   * @param RecordCount @param RecordCount
   * @param PageRecord @param PageRecord
   * @param CurrentPage @param CurrentPage */
  public ArrayListModel(List arrayList, int PageCount,
                        int RecordCount, int PageRecord, int CurrentPage) {
    this.arrayList = arrayList;
    this.pageCount = PageCount;
    this.recordCount = RecordCount;
    this.pageRecord = PageRecord;
    this.currentPage = CurrentPage;

  }

  public ArrayListModel() {

  }

  /** @param arrayList @param arrayList */
  public ArrayListModel(List arrayList) {
    this.arrayList = arrayList;
  }

  /** @return int */
  public int getPageCount() {
    return pageCount;
  }

  /** @return int */
  public int getCurrentPage() {
    return currentPage;
  }

  /** @return int */
  public int getRecordCount() {
    return recordCount;
  }

  /** @return int */
  public int getPageRecord() {
    return pageRecord;
  }





  private PageModel pageModel;





  /** @param arrayList  */
  public ArrayListModel(List arrayList, PageModel pageModel) {
    this.arrayList = arrayList;
    this.pageModel = pageModel;
  }

  public List getArrayList() {
    return arrayList;
  }

  public PageModel getPageModel() {
    return pageModel;
  }

}