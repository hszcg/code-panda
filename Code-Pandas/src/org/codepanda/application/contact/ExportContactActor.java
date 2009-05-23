package org.codepanda.application.contact;

import org.codepanda.application.CommandActor;
import org.codepanda.application.export.CsvExport;
import org.codepanda.application.export.XlsConvert;


public class ExportContactActor implements CommandActor {

	
	private String type;
	private String path;
	public Object executeCommand() 
	{
		if(this.getType().equals("csv"))
		{
			CsvExport csvConvertor=new CsvExport();
			csvConvertor.convert(getPath());
		}
		else
		{
			XlsConvert xlsConvert=new XlsConvert();
			xlsConvert.convert(getPath());
		}
	return 0;
	}
	public void setType(String type)
	{
		this.type=type;
	}
	public String getType()
	{
		return this.type;
	}
	public void setPath(String Path)
	{
		this.path=Path;
	}
	public String getPath()
	{
		return this.path;
	}
}
