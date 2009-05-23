package org.codepanda.application.label;

import org.codepanda.application.CommandActor;
import org.codepanda.utility.data.DataPool;
import org.codepanda.utility.label.CommonLabel;

public class NewCommonLabelActor implements CommandActor {
	public static final int NULL_LABEL = -1;
	public static final int FAILED = -2;
	public static final int SUCCEED = 0;
	private CommonLabel commonLabel;
	@Override
	public Object executeCommand() {
		// TODO TODO!!!
		
//		if(this.getLabel()==null)
//		{
//			System.out.println("New CommonLabel NULL!!!");
//			return NewCommonLabelActor.NULL_LABEL;
//		}
//		int result=DataPool.getInstance().newCommonLabel(getLabel());
//		if(result==-2)
//		{
//			return NewCommonLabelActor.FAILED;
//		}
		
		return NewCommonLabelActor.SUCCEED;
	}
	public void setLabel(CommonLabel label)
	{
		this.commonLabel=label;
	}
	public CommonLabel getLabel()
	{
		return this.commonLabel;
	}
}
