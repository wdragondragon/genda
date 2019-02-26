package Other;

import java.io.File;
import java.io.FilenameFilter;

public class FileAccept10_2 implements FilenameFilter {
	private String extendName;
	public void setExtendName(String s){
		extendName = s;
	}
	@Override
	public boolean accept(File dir, String name) {
		// TODO Auto-generated method stub
		return name.contains(extendName);
	}

}
