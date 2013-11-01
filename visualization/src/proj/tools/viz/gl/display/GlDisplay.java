package proj.tools.viz.gl.display;

import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glEnable;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class GlDisplay {

	/**
	 * 
	 */
	private GlDisplay(){
		
	}
	
	/**
	 * 
	 * @param mode
	 * @throws LWJGLException
	 */
	public static void create(DisplayMode mode) throws LWJGLException {
		if(mode == null){
			mode = selectDefaultDisplayMode();
		}
		
		Display.setTitle("Galaxy Visualization");
		Display.setDisplayMode(mode);
		Display.create();
		
		glEnable(GL_DEPTH_TEST);
		glEnable(GL_TEXTURE_2D);
		glEnable(GL_BLEND); 
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
	}
	
	/**
	 * 
	 * @return
	 * @throws LWJGLException
	 */
	private static DisplayMode selectDefaultDisplayMode() throws LWJGLException{
		DisplayMode[] displayModes = Display.getAvailableDisplayModes();
		DisplayMode displayMode = null;

		for(DisplayMode disp : displayModes){
			if(disp.getWidth() == 640 && disp.getHeight() == 480 && disp.getBitsPerPixel() == 32){
				displayMode = disp;
				break;
			}
		}

		if(displayMode == null)
			displayMode = displayModes[0];

		return displayMode;			
	}
	
}
