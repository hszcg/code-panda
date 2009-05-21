package org.codepanda.userinterface.utility;

import java.io.File;
import javax.swing.filechooser.FileFilter;

/**
 * �ļ�������
 * 
 * @author �ų���
 */
public class ExtensionFileFilter extends FileFilter {
	protected String desc;	//·��
	protected String[] extensions;	//��չ��

	/**
	 * @param desc
	 * @param extensions
	 */
	public ExtensionFileFilter(String desc, String[] extensions) {
		this.desc = desc;
		this.extensions = (String[]) extensions.clone();
	}

	@Override
	public boolean accept(File f) {
		if(f.isDirectory() == true)
			return true;
		
		String fileName = f.getName();
		int length = fileName.length();
		
		for(int i = 0; i <  this.extensions.length; i++ ){
			String ext = this.extensions[i];
			
			if( fileName.endsWith(ext) && fileName.charAt(length - ext.length()) == '.' )
				return true;
		}
			
		return false;
	}

	@Override
	public String getDescription() {
		return this.desc;
	}

}
