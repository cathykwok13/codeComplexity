package proj.tools.viz.resources;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

public class TextureManager {

	private static final Map<String, Texture> textureCache = new HashMap<>(); 
	
	public static Texture getTexture(String textureName) throws IOException {
		Texture texture = textureCache.get(textureName);
		
		if(texture == null){
			texture = TextureLoader.getTexture("jpg", ResourceLoader.getResourceAsInputStream(textureName));
			textureCache.put(textureName, texture);
		}
		
		return texture;
	}
	
	private TextureManager() {
		
		
	}
	
	
}
