#version 110

uniform mat4 model;
uniform mat4 view;
uniform mat4 proj;

attribute vec3 position;
attribute vec2 v_uv;

varying vec3 w_ps;
varying vec3 f_nm;
varying vec4 f_uv;

void main(){
	f_uv = vec4(v_uv, 0.0, 0.0);

	f_nm = normalize(position);
	f_nm = (model * vec4(f_nm, 0.0)).xyz;
	
	w_ps = (model * vec4(position, 1.0)).xyz;
	gl_Position = proj * view * vec4(w_ps, 1.0);
}
