package proj.tools.viz.galaxy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import proj.tools.data.ClassMetrics;
import proj.tools.viz.gl.pipe.ProgramReference;
import proj.tools.viz.gl.scene.Window;
import proj.tools.viz.gl.shapes.Sphere;

public class Galaxy {

	private Map<String, Star> solarSystems = new HashMap<>();
	
	public Galaxy(List<ClassMetrics> metrics){
		Star star = new Star(1f);
		solarSystems.put("test", star);
//		===
//		Planet p0 = new Planet(star, 3.5f, 0.8f, .4f);
//		star.addPlanet("p0", p0);
//
//		Moon m00 = new Moon(p0, 1.f, .3f, .6f);
//		p0.addChild("m00", m00);
//		
//		Moon m01 = new Moon(p0, 1.2f, 0.2f, .8f);
//		m01.setRotation((float) Math.PI);
//		p0.addChild("m01", m01);
//		
//		Moon m02 = new Moon(p0, 1.4f, 0.4f, .1f);
//		m02.setRotation((float) (1.5f*Math.PI));
//		p0.addChild("m02", m02);
	
		//
		Planet p00 = new Planet(star, 3f, 0.5f, 0.9f);
		star.addPlanet("p00", p00);

		Moon m000 = new Moon(p00, 1.f, .1f, .9f);
		p00.addChild("m00", m000);
		
		Moon m001 = new Moon(p00, 1.2f, .05f, .9f);
		m001.setRotation((float) Math.PI);
		p00.addChild("m01", m001);

//
		Planet p01 = new Planet(star, 3.5f, 0.5f, 0.95f);
		p01.setRotation((float) (Math.PI * 1.5));
		star.addPlanet("p01", p01);

		Moon m010 = new Moon(p01, 1.f, .05f, .9f);
		p01.addChild("m00", m010);
		
		Moon m011 = new Moon(p01, 1.2f, .05f, .9f);
		m011.setRotation((float) (Math.PI * 0.5));
		p01.addChild("m01", m011);

		//
		Planet p1 = new Planet(star, 2.f, 0.3f, 0.95f);
		p1.setRotation((float) Math.PI/2); 
		star.addPlanet("p1", p1);
		
		Moon m1 = new Moon(p1, .6f, 0.08f, 0.9f);
		p1.addChild("m1", m1);
	}
	
	public void next(){
		
	}
	
	public void update(){
		for(Star s : solarSystems.values()){
			for(Planet p : s.getPlanets()){
				p.setRotation(p.getRotation() + 0.01f);
				
				for(Moon m : p.getChildren()){
					m.setRotation(m.getRotation() + 0.05f);
				}
				
			}
		}
		
	}
	
	public void draw(Window window, ProgramReference glProgram){
		for(Star s : solarSystems.values()){
			Sphere.render(s, window, glProgram);
			
			for(Planet p : s.getPlanets()){
				Sphere.render(p, window, glProgram);
				
				for(Moon m : p.getChildren()){
					Sphere.render(m, window, glProgram);
				}
				
			}
		}
	}
	
}
