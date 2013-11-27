package proj.tools.viz.galaxy;

import java.util.Random;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import proj.tools.viz.gl.pipe.ProgramReference;
import proj.tools.viz.gl.scene.Window;

public class Background {

	public void draw(Window window, ProgramReference program) {
		program.bind();
		
		program.setUniformMatrix4f("proj", new Matrix4f());
		program.setUniformMatrix4f("view", new Matrix4f());
		program.setUniformMatrix4f("model", new Matrix4f());

		program.setUniformVector3f("mask", new Vector3f(0.9f,0.9f,1f));
		
		int positionLoc = program.getAttributeLocation("position");
		GL11.glPointSize(1);
		
		GL11.glDepthMask(false);
		{
			Random rng = new Random(7);
			GL11.glBegin(GL11.GL_POINTS);
			{
				for (int i = 0; i < 256; i++) {
					GL20.glVertexAttrib3f(positionLoc, rng.nextFloat() * 2 - 1, rng.nextFloat() * 2 - 1, 0f);
				}
			}
			GL11.glEnd();
		}
		GL11.glDepthMask(true);		
	}

}
