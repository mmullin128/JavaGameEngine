#version 330 core

in vec3 vertexColor;
in vec2 texCoords;


out vec4 fragColor;

uniform sampler2D textureSampler;

void main()
{
    vec4 color = vec4(vertexColor,1);
    vec4 textureColor = texture(textureSampler, texCoords);
    fragColor = textureColor + color;
}