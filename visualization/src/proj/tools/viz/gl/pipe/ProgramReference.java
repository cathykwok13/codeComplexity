package proj.tools.viz.gl.pipe;
import static org.lwjgl.opengl.GL20.glUniformMatrix4;

import java.nio.FloatBuffer;
import java.util.HashMap;
import java.util.Map;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL20;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

public class ProgramReference {

	private static int bound = -1;

	private int programId = -1;
	
	private Map<String, Integer> attribLocMap = new HashMap<>();
	private Map<String, Integer> uniformLocMap = new HashMap<>();
	
	private AbstractShaderReference vertexShader, fragmentShader;
	
	private final static FloatBuffer matrix4x4Buffer = BufferUtils.createFloatBuffer(16);
	
	public ProgramReference(VertexShaderReference vertexShader, FragmentShaderReference fragmentShader){
		this.vertexShader	= vertexShader;
		this.fragmentShader = fragmentShader;
	}
	
	public void compile(){
		programId = GL20.glCreateProgram();
		GL20.glAttachShader(programId, vertexShader.getShaderId());
		GL20.glAttachShader(programId, fragmentShader.getShaderId());
		
		GL20.glLinkProgram(programId);
		
		String status = GL20.glGetProgramInfoLog(programId, 1024);
		System.out.println("--Compile Program:\n" + status + "---End Compile Program\n");
		
	}
	
	public void bind(){
		if(bound != programId){
			GL20.glUseProgram(programId);
			bound = programId;
		}
	}
	
	public static void bindNull(){
		GL20.glUseProgram(0);
		bound = 0;
	}
	
	public int getAttributeLocation(String attributeName){
		return getLocation(attribLocMap, attributeName, true);
	}
	
	public int getUniformLocation(String uniformName){
		return getLocation(uniformLocMap, uniformName, false);
	}
	
	public void setUniformMatrix4f(String matrixName, Matrix4f matrix){
		int matLoc = getUniformLocation(matrixName);

		if(matLoc != -1){
			matrix.store(matrix4x4Buffer); matrix4x4Buffer.rewind();
			glUniformMatrix4(matLoc, false, matrix4x4Buffer);
		}
	}
	
	public void setUniformVector3f(String vectorName, Vector3f vector){
		int vecLoc = getUniformLocation(vectorName);
		
		if(vecLoc != -1){
			GL20.glUniform3f(vecLoc, vector.x, vector.y, vector.z);
		}
	}
	
	private int getLocation(Map<String, Integer> map, String varname, boolean isAttrib){
		Integer result = uniformLocMap.get(varname);
		
		if(result == null){
			result = isAttrib ? GL20.glGetAttribLocation(programId, varname) : GL20.glGetUniformLocation(programId, varname);
			map.put(varname, result);
		}
		
		return (int) result;
	}
	
	protected void finalize() throws Throwable{
		GL20.glDeleteProgram(programId);
		super.finalize();
	}
	
}
