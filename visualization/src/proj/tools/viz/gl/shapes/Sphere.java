package proj.tools.viz.gl.shapes;

import static org.lwjgl.opengl.GL20.glVertexAttrib2f;
import static org.lwjgl.opengl.GL20.glVertexAttrib3f;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import proj.tools.viz.galaxy.CelestialBody;
import proj.tools.viz.gl.pipe.ProgramReference;
import proj.tools.viz.gl.scene.Window;

public class Sphere {

	private Sphere(){
		
	}
	
	//==== Static Creation of the sphere model ====//
	private static final int HORIZONTAL_DETAIL = 20;
	private static final int VERTICAL_DETAIL = 20;

	private static final Vector2f[][] uvs	= new Vector2f[HORIZONTAL_DETAIL + 1][VERTICAL_DETAIL + 1];
	private static final Vector3f[][] points = new Vector3f[HORIZONTAL_DETAIL + 1][VERTICAL_DETAIL + 1];
	
	static {
		for(int i = 0; i <= HORIZONTAL_DETAIL; i++){
			float theta = (float) (i * 2 * Math.PI / HORIZONTAL_DETAIL);

			for(int j = 0; j <= VERTICAL_DETAIL; j++){
				float phi = (float) (j * Math.PI/VERTICAL_DETAIL);

				float x = (float) (Math.sin(theta) * Math.cos(phi));
				float z = (float) (Math.sin(theta) * Math.sin(phi));
				float y = (float) (Math.cos(theta));

				float u = (float) (0.5f + Math.atan2(z, x) / (2* Math.PI));
				float v = (float) (0.5f - Math.asin(y) / (Math.PI));
				
				uvs[i][j]	 = new Vector2f(u,v);
				points[i][j] = new Vector3f(x,y,z);
			}
		}		
	}

	//==== Drawing spheres ====//
	public static void render(CelestialBody body, Window window, ProgramReference glProgram) {
		glProgram.bind();
		
		float radius = body.getRadius();
		float condition = body.getCondition();
		float brightness = body.getBrightness();
		
		Vector3f colourMask = new Vector3f(condition, condition, condition);
		Vector3f vbrightness = new Vector3f(brightness, brightness, brightness);
		
		body.getTexture().bind();
		glProgram.setUniformMatrix4f("model", body.getTransform());
		glProgram.setUniformVector3f("mask", colourMask);
		glProgram.setUniformVector3f("brightness", vbrightness);
		

		int positionLocation = glProgram.getAttributeLocation("position");
		int uvLocation = glProgram.getAttributeLocation("v_uv");
		
		GL11.glBegin(GL11.GL_QUADS);
		{
			for(int i = 0; i < points.length; i++){
				int h = i;
				int nextH = (i+1) % points.length;
				
				for(int j = 0; j < points[i].length; j++){				
					int v = j;
					int nextV = (j+1) % points[i].length;
					
					Vector2f uBase 		= uvs[h][v];
					Vector2f uNext 		= uvs[h][nextV];
					Vector2f uUp 		= uvs[nextH][v];
					Vector2f uUpNext 	= uvs[nextH][nextV];
					
					Vector3f vBase 		= points[h][v];
					Vector3f vNext 		= points[h][nextV];
					Vector3f vUp 		= points[nextH][v];
					Vector3f vUpNext 	= points[nextH][nextV];
					
					glVertexAttrib2f(uvLocation, uBase.x, uBase.y);
					glVertexAttrib3f(positionLocation, radius*vBase.x, 		radius*vBase.y, 	radius*vBase.z);

					glVertexAttrib2f(uvLocation, uUp.x, uUp.y);
					glVertexAttrib3f(positionLocation, radius*vUp.x, 		radius*vUp.y, 		radius*vUp.z);
					
					glVertexAttrib2f(uvLocation, uUpNext.x, uUpNext.y);
					glVertexAttrib3f(positionLocation, radius*vUpNext.x, 	radius*vUpNext.y, 	radius*vUpNext.z);

					glVertexAttrib2f(uvLocation, uNext.x, uNext.y);
					glVertexAttrib3f(positionLocation, radius*vNext.x, 		radius*vNext.y, 	radius*vNext.z);
				}
			}
		}	
		GL11.glEnd();
	}
}
