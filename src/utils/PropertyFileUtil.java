package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyFileUtil 
{
	public static String getValueForKey(String Key) throws IOException
	{
		Properties p=new Properties();
		FileInputStream fi=new FileInputStream("E:\\Srikanth_82\\HybridFrameWork\\Propertiesfile\\Environment.properties");
		p.load(fi);
		return p.getProperty(Key);
		
		
		
	}

}
