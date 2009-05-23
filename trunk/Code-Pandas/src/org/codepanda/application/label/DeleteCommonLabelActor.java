package org.codepanda.application.label;

import org.codepanda.application.CommandActor;
import org.codepanda.utility.data.DataPool;
import org.codepanda.utility.label.CommonLabel;

public class DeleteCommonLabelActor implements CommandActor {
	public static final int NULL_LABEL = -1;
	public static final int FAILED = -2;
	public static final int SUCCEED = 0;
	private CommonLabel commonLabel;
	private String StrInfo;
	@Override
	public Object executeCommand() {
		// TODO TODO!!!!
		DataPool.getInstance().deleteCommonLabel(getInfo());
		return 0;
		
	}
	public void setLabel(CommonLabel label)
	{
		this.commonLabel=label;
	}
	public CommonLabel getLabel()
	{
		return this.commonLabel;
	}
	public void setInfo(String str)
	{
		this.StrInfo=str;
	}
	public String getInfo()
	{
		return this.StrInfo;
	}

}
