#version 110

uniform vec3 mask;
uniform sampler2D samp;

varying vec4 f_uv;


void main(){
	 vec4 color = texture2D(samp, f_uv.xy);
	 color.rgb *= mask.rgb;
	 
	 gl_FragColor = color;
}
