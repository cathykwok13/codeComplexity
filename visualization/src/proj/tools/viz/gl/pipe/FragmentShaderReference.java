package proj.tools.viz.gl.pipe;

import java.io.File;
import java.io.IOException;

import org.lwjgl.opengl.GL20;

import com.google.common.base.Charsets;
import com.google.common.io.Files;

public class FragmentShaderReference extends AbstractShaderReference {

	public FragmentShaderReference(File shaderSource) throws IOException{
		this(Files.toString(shaderSource, Charsets.UTF_8));
	}
	
	public FragmentShaderReference(String shaderSource) {
		super(shaderSource);
	}

	@Override
	protected int getShaderType() {
		return GL20.GL_FRAGMENT_SHADER;
	}

}
