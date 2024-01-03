#version 330

in vec4 fragPos;
in vec4 tint;
in vec2 uv;

uniform sampler2D u_atlas;

void main(){
    // Sampling
    vec4 fragColor = tint * texture(u_atlas, uv);
    if(fragColor.a <= 0)
        discard;

    // Result
    gl_FragColor = fragColor;
}