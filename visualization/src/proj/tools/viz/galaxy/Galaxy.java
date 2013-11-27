package proj.tools.viz.galaxy;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import proj.tools.data.ClassMetrics;
import proj.tools.viz.gl.pipe.ProgramReference;
import proj.tools.viz.gl.scene.Window;
import proj.tools.viz.gl.shapes.Sphere;

public class Galaxy {

	private static final int FRAMES_PER_COMMIT = 300;

	private int currentFrame = 0;

	private Iterator<List<ClassMetrics>> metricsIterator;
	
	private final Map<String, Star> solarSystems = new HashMap<>();


	/**
	 * 
	 * @param metrics
	 */
	public Galaxy(List<List<ClassMetrics>> metrics){
		metricsIterator = metrics.iterator();
		nextCommit();
	}

	/**
	 * 
	 */
	private void nextCommit(){
		solarSystems.clear();
		
		if(!metricsIterator.hasNext()){
			//the simulation is done!
			System.exit(0);
		}
		
		List<ClassMetrics> nextCommit = metricsIterator.next();
		
		Star star = new Star(2f);
		int planetCount = 0;
		
		for(ClassMetrics cm : nextCommit){
			planetCount += 1;
			
			float planetRadius =  (float) Math.sqrt(cm.getLinesOfCode() / 300.f);
			
			Planet planet = new Planet(star, 4.f + 0.4f * planetCount, planetRadius,  1f);
			star.addPlanet(Integer.toString(planetCount), planet);
			
			planet.setRotation((float) (5f * Math.PI * planetCount / nextCommit.size())); 
			
			Collection<String> methodNames = cm.getComplexityPerMethod().keySet();
			int moonCount = 0;
			float planetCondition = 1f;
						
			for(String methodName : methodNames){
				moonCount++;
				
				float moonComplexity = cm.getComplexityPerMethod().get(methodName) + cm.getDependencyPerMethod().get(methodName);
				float moonCondition = Math.min(1.0f, 1.f / (0.25f * moonComplexity));
				
				Moon moon = new Moon(planet, planetRadius + 0.25f + 0.05f * moonCount, 0.1f, moonCondition);
				moon.setRotation((float) (2 * Math.PI * moonCount / methodNames.size()));
				
				planetCondition *= (float) Math.sqrt(moonCondition);
				planet.addChild(methodName, moon);
			}
			
			planet.setCondition(planetCondition);
		}
		
		solarSystems.put("star", star);
	}

	
	/**
	 * 
	 */
	public void update(){
		currentFrame++;

		if(currentFrame >= FRAMES_PER_COMMIT){
			currentFrame = 0;
			nextCommit();
		}

		for(Star s : solarSystems.values()){
			for(Planet p : s.getPlanets()){
				p.setRotation(p.getRotation() + 0.01f);

				for(Moon m : p.getChildren()){
					m.setRotation(m.getRotation() + 0.01f);
				}

			}
		}

	}

	/**
	 * 
	 * @param window
	 * @param glProgram
	 */
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
