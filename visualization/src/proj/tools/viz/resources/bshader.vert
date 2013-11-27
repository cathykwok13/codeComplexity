#version 110

uniform mat4 model;
uniform mat4 view;
uniform mat4 proj;

attribute vec3 position;

void main(){
	vec4 transformedPos = proj * view * model * vec4(position, 1.0);
	gl_Position = transformedPos;
}
