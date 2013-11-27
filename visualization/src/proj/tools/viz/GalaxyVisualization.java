package proj.tools.viz;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glDepthMask;

import java.io.IOException;
import java.util.List;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;
import org.lwjgl.util.vector.Vector3f;

import proj.tools.data.ClassMetrics;
import proj.tools.viz.galaxy.Background;
import proj.tools.viz.galaxy.Galaxy;
import proj.tools.viz.gl.display.GlDisplay;
import proj.tools.viz.gl.pipe.FragmentShaderReference;
import proj.tools.viz.gl.pipe.ProgramReference;
import proj.tools.viz.gl.pipe.VertexShaderReference;
import proj.tools.viz.gl.scene.Camera;
import proj.tools.viz.gl.scene.Window;
import proj.tools.viz.resources.ResourceLoader;

public class GalaxyVisualization {

	private Galaxy galaxy;
	private Background background;
	
	private Window backgroundWindow, galaxyWindow, overlayWindow;
	private Camera camera;

	private ProgramReference galaxyProgram;
	private ProgramReference backgroundProgram;

	/**
	 * 
	 * @param classMetrics
	 */
	public GalaxyVisualization(List<List<ClassMetrics>> classMetrics) {
		galaxy = new Galaxy(classMetrics);
		background = new Background();
	}

	/**
	 * 
	 */
	public void start() {
		try{
			GlDisplay.create(null);
		}catch(Exception ex){
			throw new RuntimeException(ex);
		}
		
		initializeGlState();

		//initialize the viewing structures
		backgroundWindow = new Window(Display.getWidth(), Display.getHeight(), 0.1f, 1f);
		backgroundWindow.setOrthographicProjection(0, 0);

		galaxyWindow = new Window(Display.getWidth(), Display.getHeight(), 0.1f, 10000f);
		galaxyWindow.setPerspectiveProjection(45f);

		overlayWindow = new Window(Display.getWidth(), Display.getHeight(), 0.1f, 1f);
		overlayWindow.setOrthographicProjection(0, 0);

		camera = new Camera(25f, new Vector3f());
		camera.setVertical(1f);
		camera.updateView();
		
		galaxyWindow.setCamera(camera);

		updateLoop();
	}

	/**
	 * 
	 */
	private void initializeGlState() {
		try {
			VertexShaderReference vertexShader = new VertexShaderReference(ResourceLoader.getResourceAsFile("gshader.vert"));
			FragmentShaderReference fragmentShader = new FragmentShaderReference(ResourceLoader.getResourceAsFile("gshader.frag"));
			galaxyProgram = new ProgramReference(vertexShader, fragmentShader);
			galaxyProgram.compile();
			galaxyProgram.bind();
			
			vertexShader = new VertexShaderReference(ResourceLoader.getResourceAsFile("bshader.vert"));
			fragmentShader = new FragmentShaderReference(ResourceLoader.getResourceAsFile("bshader.frag"));
			backgroundProgram = new ProgramReference(vertexShader, fragmentShader);
			backgroundProgram.compile();
			backgroundProgram.bind();
			
			
			
		} catch (IOException e) {
			throw new RuntimeException(e + "\nUnable to load GL state");
		}
	}

	/**
	 * @throws IOException
	 * 
	 */
	private void updateLoop() {
		int glError;
		
		while (!Display.isCloseRequested()) {
			Display.sync(60);
			drawBackground();

			updateGalaxy();
			drawGalaxy();

			drawOverlay();

			Display.update();

			// depth mask must be true in order to reset the depth buffer
			glDepthMask(true);
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

			// Print GL errors if any exist
			glError = GL11.glGetError();
			if (glError != 0) {
				System.err.println(GLU.gluErrorString(glError));
			}
		}

		shutdown();
	}

	/**
	 * 
	 */
	private void shutdown() {
		Display.destroy();
		System.out.println("GL Context Destroyed");
	}

	/**
	 * 
	 */
	public void drawBackground() {
		backgroundWindow.update();
		background.draw(galaxyWindow, backgroundProgram);
	}

	/**
	 * 
	 */
	public void updateGalaxy() {
	//	camera.incrementHorizontal();
		galaxyWindow.update();
		galaxy.update();
	}

	/**
	 * 
	 */	
	public void drawGalaxy() {
		galaxyProgram.bind();
		galaxyProgram.setUniformMatrix4f("proj", galaxyWindow.getProjectionMatrix());
		galaxyProgram.setUniformMatrix4f("view", galaxyWindow.getViewMatrix());
		
		galaxy.draw(galaxyWindow, galaxyProgram);
	}
	
	public void drawOverlay() {
		//TODO
	}
	
}
