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
	private static final int HORIZONTAL_DETAIL = 21;
	private static final int VERITCAL_DETAIL = 20;
	
	private static final Vector2f[][] uvs	= new Vector2f[HORIZONTAL_DETAIL][VERITCAL_DETAIL];
	private static final Vector3f[][] points = new Vector3f[HORIZONTAL_DETAIL][VERITCAL_DETAIL];
	
	static {
		for(int i = 0; i < points.length; i++){
			for(int j = 0; j < points[i].length; j++){
				float theta = (float) (j * 2 * Math.PI / HORIZONTAL_DETAIL);
				float phi	= (float) (i*Math.PI/VERITCAL_DETAIL - Math.PI/2);

				float x = (float) (Math.sin(theta) * Math.cos(phi));
				float y = (float) (Math.sin(theta) * Math.sin(phi));
				float z = (float) (Math.cos(theta));

				uvs[i][j]	 = new Vector2f(theta / (4 * (float) Math.PI) + 0.5f, phi / (float) Math.PI + 0.5f);
				points[i][j] = new Vector3f(x,y,z);
			}
		}		
	}

	//==== Drawing spheres ====//
	public static void render(CelestialBody body, Window window, ProgramReference glProgram) {
		float radius = body.getRadius();
		float condition = body.getCondition();
		
		Vector3f colourMask = new Vector3f(1, condition, condition);
		
		body.getTexture().bind();
		glProgram.setUniformMatrix4f("model", body.getTransform());
		glProgram.setUniformVector3f("mask", colourMask);

		int positionLocation = glProgram.getAttributeLocation("position");
		int uvLocation = glProgram.getAttributeLocation("v_uv");
		
		GL11.glBegin(GL11.GL_QUADS);
		{
			for(int i = 0; i < points.length-1; i++){
				for(int j = 0; j < points[i].length; j++){			
					Vector2f uBase 		= uvs[i][j];
					Vector2f uNext 		= uvs[i][(j+1)%uvs[i].length];
					Vector2f uUp 		= uvs[i+1][j];
					Vector2f uUpNext 	= uvs[i+1][(j+1)%uvs[i].length];
					
					Vector3f vBase 		= points[i][j];
					Vector3f vNext 		= points[i][(j+1)%points[i].length];
					Vector3f vUp 		= points[i+1][j];
					Vector3f vUpNext 	= points[i+1][(j+1)%points[i].length];
					
					glVertexAttrib2f(uvLocation, uBase.x, uBase.y);
					glVertexAttrib3f(positionLocation, radius*vBase.x, radius*vBase.y, radius*vBase.z);

					glVertexAttrib2f(uvLocation, uNext.x, uNext.y);
					glVertexAttrib3f(positionLocation, radius*vNext.x, radius*vNext.y, radius*vNext.z);
					
					glVertexAttrib2f(uvLocation, uUpNext.x, uUpNext.y);
					glVertexAttrib3f(positionLocation, radius*vUpNext.x, radius*vUpNext.y, radius*vUpNext.z);
					
					glVertexAttrib2f(uvLocation, uUp.x, uUp.y);
					glVertexAttrib3f(positionLocation, radius*vUp.x, radius*vUp.y, radius*vUp.z);
				}
			}
		}	
		GL11.glEnd();
	}

}
