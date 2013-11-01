package proj.tools.viz.gl.scene;

import org.lwjgl.util.vector.Matrix4f;

public class Window {

	private float width, height, ratio;
	private float near, far;

	private Camera camera;

	private final Matrix4f 
	viewTransform = new Matrix4f(),
	projectionTransform = new Matrix4f(), 
	viewProjectionTransform = new Matrix4f();

	/**
	 *  
	 */
	public Window(float width, float height, float near, float far){
		this.far = far;
		this.near = near;
		this.width = width;
		this.height = height;

		this.ratio = width / height;
	}	

	/**
	 * 
	 * @param x
	 * @param y
	 */
	public void setOrthographicProjection(float x, float y){
		projectionTransform.setZero();

		float left = -x;
		float right = left + width;

		float bottom = -y;
		float top = bottom + height;

		//scaling factor
		projectionTransform.m00 = 2.f / (right - left);
		projectionTransform.m11 = 2.f / (top - bottom);
		projectionTransform.m22 = 2.f / (near - far);

		//translation factor
		projectionTransform.m30 = (right + left) / -(right - left);
		projectionTransform.m31 = (top + bottom) / -(top - bottom);
		projectionTransform.m32 = (far + near) / -(far - near);

		projectionTransform.m33 = 1.f;
	}

	/**
	 * 
	 * @param fovy
	 */
	public void setPerspectiveProjection(float fovy){
		projectionTransform.setZero();

		float f = (float) (1 / Math.tan(fovy / 2 * Math.PI / 180.f));

		projectionTransform.m00 = f / this.ratio;
		projectionTransform.m11 = f;

		projectionTransform.m22 = (far + near)/(near - far);
		projectionTransform.m32 = 2*far*near/(near - far);

		projectionTransform.m23 = -1;			
	}

	/**
	 * 
	 */
	public void update(){
		if(camera != null){
			camera.updateView();
			camera.getView(viewTransform);

			Matrix4f.mul(projectionTransform, viewTransform, viewProjectionTransform);
		}
	}

	/**
	 * 
	 * @return
	 */
	public float getNear(){
		return near;
	}

	/**
	 * 
	 * @return
	 */
	public float getFar(){
		return far;
	}

	/**
	 * 
	 * @return
	 */
	public float getAspectRatio(){
		return ratio;
	}

	/**
	 * 
	 * @param camera
	 */
	public void setCamera(Camera camera){
		this.camera = camera;
		this.camera.getView(viewTransform);
	}

	/**
	 * 
	 * @return
	 */
	public Camera getCamera(){
		return camera;
	}

	/**
	 * 
	 * @return
	 */
	public Matrix4f getViewProjectionMatrix(){
		return viewProjectionTransform;
	}

	/**
	 * 
	 * @return
	 */
	public Matrix4f getViewMatrix(){
		return viewTransform;
	}

	/**
	 * 
	 * @return
	 */
	public Matrix4f getProjectionMatrix(){
		return projectionTransform;
	}

}
