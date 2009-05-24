package org.codepanda.application.contact;

import org.codepanda.application.CommandActor;
import org.codepanda.utility.data.DataPool;

public class DeleteContactActor implements CommandActor {
	//private PersonalContact myContact;
	private int currentISN;
	public static final int NULL_CONTACT = -1;
	public static final int FAILED= -2;
	public static final int SUCCEED = 0;
	@Override
	public Object executeCommand() {
		// TODO Auto-generated method stub
		//删除一定成功
		int result=DataPool.getInstance().deleteContact(this.getISN());
		if(result==-2)
		{
			System.out.println("Delete Contact Failed!!!");
			return DeleteContactActor.FAILED;
		}
		return DeleteContactActor.SUCCEED;
	}
	public void setISN(int ISN)
	{
		this.currentISN=ISN;
	}
	public int getISN()
	{
		return this.currentISN;
	}
	
}
