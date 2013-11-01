package proj.tools.viz.galaxy;

import java.io.IOException;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;
import org.newdawn.slick.opengl.Texture;

import proj.tools.viz.resources.TextureManager;


public class Moon implements CelestialBody {
	
	private static final String TEXTURE_NAME = "moon.jpg";
	
	private Planet parent;
	private float radius, distanceFromParent, rotation, condition;
	private Matrix4f transform = new Matrix4f();
		
	public Moon(Planet parent, float distanceFromParent, float radius, float condition){
		this.parent = parent;
		this.radius = radius;
		this.condition = condition;
		this.distanceFromParent = distanceFromParent;
	}

	public void setRotation(float rotation){
		this.rotation = rotation;
	}
	
	public float getRotation(){
		return this.rotation;
	}
	
	public float getRadius(){
		return radius;
	}

	@Override
	public Texture getTexture() {
		try {
			return TextureManager.getTexture(TEXTURE_NAME);
		} catch (IOException e) {
			throw new RuntimeException("Could not find texture: " + TEXTURE_NAME);
		}
	}

	@Override
	public Matrix4f getTransform() {
		transform.setIdentity();

		//rotate
		transform.rotate(rotation, new Vector3f(0,1,0));
		
		//translate
		transform.translate(new Vector3f(distanceFromParent,0,0));
				
		//rotate
		transform.rotate(rotation, new Vector3f(0,1,0));


		return Matrix4f.mul(parent.getTransform(), transform, transform);
	}

	@Override
	public float getCondition() {
		return condition;
	}
	
}
