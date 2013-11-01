package proj.tools.viz.resources;

import java.io.File;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;

public class ResourceLoader {
	
	private static final ResourceLoader instance = new ResourceLoader();
	
	private ResourceLoader(){
		
	}
	
	public static File getResourceAsFile(String resourceName){
		try {
			URL url = instance.getClass().getResource(resourceName);
			return new File(URLDecoder.decode(url.getPath(), "UTF8"));
		} catch (UnsupportedEncodingException e) {
			throw new Error("this exception is unreachable, UTF8 is required");
		}
	}
	
	public static InputStream getResourceAsInputStream(String resourceName){
		return instance.getClass().getResourceAsStream(resourceName);
	}
	
}
