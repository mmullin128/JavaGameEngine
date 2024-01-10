#version 330

layout(location = 0) in vec3 aPos;
layout(location = 1) in vec2 aTexCoords;
layout(location = 2) in vec3 aColor;

out vec3 vertexColor;
out vec2 texCoords;

uniform vec2 cameraOrigin;
uniform vec2 cameraScale;


uniform vec2 translation;
uniform vec2 scale;
uniform float rotation;

vec2 translate(in vec2 v, in vec2 dV)
{
    return v + dV;
}
vec2 rotate(in vec2 v, in float r)
{
    return vec2(v.x * cos(r) - v.y * sin(r), v.x * sin(r) + v.y * cos(r));
}
vec2 scaleV(in vec2 v, in vec2 s)
{
    return vec2(v.x * s.x, v.y * s.y);
}
void main()
{
    vec2 pos2D = vec2(aPos.x, aPos.y);
    vec2 worldPos = translate(scaleV(rotate(pos2D,rotation),scale),translation);
    vec2 screenPos = scaleV(translate(worldPos,-cameraOrigin),cameraScale);
    gl_Position = vec4(screenPos, aPos.z, 1);
    vertexColor = aColor;
    texCoords = aTexCoords;
}