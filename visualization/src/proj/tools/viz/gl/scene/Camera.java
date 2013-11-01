package proj.tools.viz.gl.scene;

import static java.lang.Math.PI;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;


public class Camera {

	private double radius, phi, theta, increment;
	private Vector3f target;
	private Vector3f eye;

	private Vector3f upVector, rightVector;
	private Vector3f viewingVector;

	private boolean isInverted;

	public Camera(float distance, Vector3f target){
		this.radius = distance;
		this.target = target;
		
		increment = 0.05f;
	
		eye = new Vector3f();

		upVector = new Vector3f(0, 1, 0);
		viewingVector = new Vector3f();

		theta 	= PI/3;
		phi 	= 3*PI/2;

		//update the initial view
		updateView();
	}


	public void updateView(){
		eye.x = (float) (radius*sin(theta)*cos(phi));
		eye.y = (float) (radius*cos(theta));
		eye.z = (float) (radius*sin(theta)*sin(phi));

		eye.x += target.x;
		eye.y += target.y;
		eye.z += target.z;

		Vector3f.sub(target, eye, viewingVector);
		viewingVector.normalise();
		
		rightVector = Vector3f.cross(viewingVector, new Vector3f(0,1,0), null);
		Vector3f.cross(viewingVector, rightVector, upVector);
		
		rightVector.normalise();
		upVector.normalise();
	}
	
	
	public void getView(Matrix4f dest){
		Vector3f rightVector = new Vector3f();
		Vector3f.cross(viewingVector, new Vector3f(0,1,0), null).normalise(rightVector);
		
		Vector3f lastVector = new Vector3f();
		Vector3f.cross(rightVector, viewingVector, null).normalise(lastVector);
		
		dest.setZero();
		
		dest.m00 = rightVector.x;
		dest.m10 = rightVector.y;
		dest.m20 = rightVector.z;
		dest.m30 = -Vector3f.dot(rightVector, eye);
		
		dest.m01 = lastVector.x;
		dest.m11 = lastVector.y;
		dest.m21 = lastVector.z;
		dest.m31 = -Vector3f.dot(lastVector, eye);
		
		dest.m02 = -viewingVector.x;
		dest.m12 = -viewingVector.y;
		dest.m22 = -viewingVector.z;
		dest.m32 = Vector3f.dot(viewingVector, eye);
		
		dest.m03 = 0;
		dest.m13 = 0;
		dest.m23 = 0;
		dest.m33 = 1;
	}
	
	public Vector3f getViewVector(){
		return viewingVector;
	}
	
	public void setFocus(Vector3f focus){
		this.target = focus;
	}

	public void invert(){
		isInverted = !isInverted;
	}


	public void setVertical(float val){
		theta = val;
		
		if(theta >= 7*PI/8){
			theta = 7*PI/8 - 0.001f;
		}
		else if(theta <= PI/8){
			theta = PI/8 + 0.001f;
		}

		double angle = cos(theta);
		
		if(angle == 1){
			theta += 0.0001f;
		}
		
		else if(angle == -1){
			theta -= 0.0001f;
		}

	}

	public void incrementHorizontal(){
		phi += increment;
		if(phi > 2*PI){
			phi -= 2*PI;
		}
		else if(phi < 0){
			phi += 2*PI;

		}
	}

	public Vector3f getUpVector() {
		return upVector;
	}
	
	public Vector3f getRightVector(){
		return rightVector;
	}

	public Vector3f getPosition() {
		return eye;
	}
}
