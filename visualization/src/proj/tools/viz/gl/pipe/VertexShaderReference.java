package proj.tools.viz.gl.pipe;

import java.io.File;
import java.io.IOException;

import org.lwjgl.opengl.GL20;

import com.google.common.base.Charsets;
import com.google.common.io.Files;

public class VertexShaderReference extends AbstractShaderReference {

	public VertexShaderReference(File shaderSource) throws IOException{
		this(Files.toString(shaderSource, Charsets.UTF_8));
	}
	
	public VertexShaderReference(String shaderSource) {
		super(shaderSource);
	}

	@Override
	protected int getShaderType() {
		return GL20.GL_VERTEX_SHADER;
	}

}
