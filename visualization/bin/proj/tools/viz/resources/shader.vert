#version 110

uniform mat4 model;
uniform mat4 view;
uniform mat4 proj;

attribute vec3 position;
attribute vec2 v_uv;

varying vec4 f_uv;


void main(){
	f_uv = vec4(v_uv, 0.0, 0.0);
	gl_Position = proj * view * model * vec4(position, 1.0);
}
