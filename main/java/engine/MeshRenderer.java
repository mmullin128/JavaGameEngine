package engine;

import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.glDrawArrays;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.glBindVertexArray;

public class MeshRenderer implements Renderable{
    public Mesh mesh;
    public Transform transform;
    public Texture texture;
    public Shader shader;
    public Camera camera;

    public MeshRenderer(Mesh mesh, Transform transform, Texture texture, Shader shader, Camera camera) {
        this.mesh = mesh;
        this.transform = transform;
        this.texture = texture;
        this.shader = shader;
        this.camera = camera;
    }

    public void render() {
        //set the shader transform uniforms to the current transform
        shader.bind();
        shader.setUniform("translation",transform.position);
        shader.setUniform("scale",transform.scale);
        shader.setUniform("rotation",transform.rotation);

        shader.setUniform("cameraOrigin",camera.origin);
        shader.setUniform("cameraScale",camera.scale);
        //int translationUniform = glGetUniformLocation(shader.getProgram(), "textureColor");

        //shader.setUniform("textureSampler",0);
        texture.bind();
        //glUniform4f(textureColorUniform,textureColor.r,textureColor.g,textureColor.b,textureColor.a);
        //shader.bind();
        mesh.bind();
        glActiveTexture(GL_TEXTURE0);
        glDrawElements(GL_TRIANGLES,mesh.indicesNum,GL_UNSIGNED_INT,0);
        mesh.unbind();
        texture.unbind();
        shader.unbind();
    }
}
