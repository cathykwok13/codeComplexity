package proj.tools.viz.gl.pipe;
import org.lwjgl.opengl.GL20;

public abstract class AbstractShaderReference {

	private int shaderId = -1;
	
	public AbstractShaderReference(String shaderSource){
		compileShader(shaderSource);
	}
	
	protected abstract int getShaderType();
	
	public int getShaderId(){
		return shaderId;
	}
		
	protected void compileShader(String source){
		shaderId = GL20.glCreateShader(getShaderType());
		GL20.glShaderSource(shaderId, source);
		GL20.glCompileShader(shaderId);
	
		String status = GL20.glGetShaderInfoLog(shaderId, 512);
		System.out.println("--Shader\n" + status + "---End Shader\n");
	}
	
	protected void finalize() throws Throwable {
		GL20.glDeleteShader(shaderId);
		super.finalize();
	}
}
