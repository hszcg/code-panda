package org.codepanda.application.label;

import org.codepanda.application.CommandActor;
import org.codepanda.utility.data.DataPool;
import org.codepanda.utility.label.CommonLabel;

public class DeleteCommonLabelActor implements CommandActor {
	public static final int NULL_LABEL = -1;
	public static final int FAILED = -2;
	public static final int SUCCEED = 0;
	private CommonLabel commonLabel;
	@Override
	public int executeCommand() {
		// TODO Auto-generated method stub
		if(this.getLabel()==null)
		{
			return DeleteCommonLabelActor.NULL_LABEL;
		}
		int result=DataPool.getInstance().deleteCommonLabel(getLabel());
		if(result==-2)
		{
			return DeleteCommonLabelActor.FAILED;
		}
		return DeleteCommonLabelActor.SUCCEED;
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
