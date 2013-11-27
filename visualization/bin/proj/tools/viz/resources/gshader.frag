#version 110

uniform vec3 mask;
uniform vec3 brightness;

uniform sampler2D samp;

varying vec3 w_ps;
varying vec3 f_nm;
varying vec4 f_uv;


void main(){
	vec4 color = texture2D(samp, f_uv.xy);

	vec3 dir = normalize(vec3(0.0, 0.0, 0.0) - w_ps);
	
	float angle = dot(dir, f_nm);
	angle = max(brightness.x, angle);
	
	color.rgb *= angle;

	color.g *= max(0.25, mask.g);
	color.b *= max(0.25, mask.b);
	 
	gl_FragColor = color;
}
