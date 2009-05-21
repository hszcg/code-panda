package org.codepanda.application.label;

import org.codepanda.application.CommandActor;
import org.codepanda.utility.data.DataPool;
import org.codepanda.utility.label.CommonLabel;

public class EditCommonLabelActor implements CommandActor {
private CommonLabel commonLabel;
public static final int NULL_LABEL = -1;
public static final int FAILED = -2;
public static final int SUCCEED = 0;
	@Override
	public int executeCommand() {
		// TODO Auto-generated method stub
		if(this.getLabel()==null)
		{
			System.out.println("Edit Label Null!!!");
			return EditCommonLabelActor.NULL_LABEL;
		}
		int result=DataPool.getInstance().editCommonLabel(getLabel());
		if(result==-2)
		{
			return EditCommonLabelActor.FAILED;
		}
		return EditCommonLabelActor.SUCCEED;
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
