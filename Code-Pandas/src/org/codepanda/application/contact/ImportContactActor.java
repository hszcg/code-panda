package org.codepanda.application.contact;

import org.codepanda.application.CommandActor;
import org.codepanda.application.export.CsvImport;
import org.codepanda.application.export.XlsImport;

public class ImportContactActor implements CommandActor {
	private String allStr;
	private String type;
	private String path;
	public String temp[]=new String[2];
	public void parser(String c)
	{
		temp=c.split("--");
	}
	public Object executeCommand() 
	{
		this.setType(temp[0]);
		this.setPath(temp[1]);
		if(this.getType().equals("csv"))
		{
			CsvImport csvConvertor=new CsvImport();
			csvConvertor.convert(getPath());
		}
		else
		{
			XlsImport xlsConvert=new XlsImport();
			xlsConvert.convert(getPath());
		}
	return 0;
	}
	public void setType(String type)
	{
		parser(allStr);
		this.type=temp[0];
	}
	public String getType()
	{
		return this.type;
	}
	public void setPath(String Path)
	{
		parser(allStr);
		this.path=temp[1];
	}
	public String getPath()
	{
		return this.path;
	}
	public void setallStr(String str)
	{
		this.allStr=str;
	}
	public String getallStr()
	{
		return this.allStr;
	}
}
