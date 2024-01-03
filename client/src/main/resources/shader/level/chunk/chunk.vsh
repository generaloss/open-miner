#version 330

layout (location = 0) in vec3 a_position;
layout (location = 1) in vec4 a_tint;
layout (location = 2) in vec2 a_uv;

out vec4 fragPos;
out vec4 tint;
out vec2 uv;

uniform mat4 u_model;
uniform mat4 u_projection;
uniform mat4 u_view;

void main(){
    fragPos = u_model * vec4(a_position, 1.0);
    gl_Position = u_projection * u_view * fragPos;
    tint = a_tint;
    uv = a_uv;
}
