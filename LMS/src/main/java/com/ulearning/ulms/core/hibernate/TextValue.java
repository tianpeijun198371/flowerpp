/**
 * Copyright (c)2006 Beijing WenhuaOnline Sci-tech DevelopmentCo.,Ltd. 
 * All rights reserved. 
 *
 * User: fengch
 * Date: 2008-1-14 17:33:19 
 */
package com.ulearning.ulms.core.hibernate;

public class TextValue
{
  private long id;
  private String text;

  public long getId() { return id; }
  public void setId(long id) { this.id = id; }

  public String getText() { return text; }
  public void setText(String text) { this.text = text; }
}

