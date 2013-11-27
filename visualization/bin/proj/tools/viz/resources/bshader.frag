#version 110

uniform vec3 mask;

void main(){	 
	gl_FragColor = vec4(mask, 1.0);
}
