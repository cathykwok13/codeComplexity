package proj.tools.viz.galaxy;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.lwjgl.util.vector.Matrix4f;
import org.newdawn.slick.opengl.Texture;

import proj.tools.viz.resources.TextureManager;

public class Star implements CelestialBody {

	
	private static final String TEXTURE_NAME = "star.jpg";
	
	private float radius;
	private Map<String, Planet> children = new HashMap<>();
	private Matrix4f transform = new Matrix4f();
	
	public Star(float radius){
		this.radius = radius;
	}
	
	public float getRadius(){
		return radius;
	}
	
	public void addPlanet(String name, Planet p){
		children.put(name, p);
	}
	
	public Planet getPlanet(String name){
		return children.get(name);
	}
	
	public Collection<Planet> getPlanets(){
		return children.values();
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
	
		return transform;
	}

	@Override
	public float getCondition() {
		return 1;
	}

	@Override
	public float getBrightness() {
		return 1;
	}
}
