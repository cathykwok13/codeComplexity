package proj.tools.viz.galaxy;

import org.lwjgl.util.vector.Matrix4f;
import org.newdawn.slick.opengl.Texture;


public interface CelestialBody {
	
	public float getRadius();
	public float getCondition();
	public float getBrightness();

	public Texture getTexture();
	public Matrix4f getTransform();
}
