package engine;

import engine.VertexArrayObject.ElementBuffer;
import engine.VertexArrayObject.PositionBuffer;
import engine.VertexArrayObject.VertexArrayObject;
import engine.VertexArrayObject.VertexBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.Collection;

import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

public class Mesh {

    private PositionBuffer positionBuffer;
    private ElementBuffer ebo;
    private Collection<VertexBuffer> vbos;
    private VertexArrayObject vao;

    public int vertexCount;
    public int indicesNum;

    public Mesh(PositionBuffer positionBuffer, ElementBuffer ebo, Collection<VertexBuffer> vbos) {
        this.positionBuffer = positionBuffer;
        this.ebo = ebo;
        this.vbos = vbos;
        this.vbos.add(positionBuffer);
        this.vao = new VertexArrayObject(this.vbos,this.ebo);

        this.vertexCount = positionBuffer.vertexCount;
        this.indicesNum = ebo.indicesCount;
        //vertex array object stores all the buffer data needed to render the mesh
        //vao = glGenVertexArrays();
        //glBindVertexArray(vao);

        //create vertex buffer
        //glGenBuffers creates a vertex buffer object which holds vertex data
        //v_id  = glGenBuffers();
        //glBindBuffer(GL_ARRAY_BUFFER, v_id);
        //GL_DYNAMIC_DRAW used for buffer data that changes frequently
        //glBufferData(GL_ARRAY_BUFFER, createBuffer(vertices), GL_DYNAMIC_DRAW);

        //create element buffer object for vertex buffer object
        //ebo = glGenBuffers();
        //glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ebo);
        //glBufferData(GL_ELEMENT_ARRAY_BUFFER, createBuffer(indices), GL_DYNAMIC_DRAW);


        //the vertex attribute pointer defines the format of the vertices array
        //index - defined in (layout = ?) in the shader source code
        //size - number of floats in each vertex
        //stride - number of bytes between vertices (3 * Float.Bytes)
        //glVertexAttribPointer(0,3, GL_FLOAT, false, 8 * Float.BYTES,0);
        //glVertexAttribPointer(1,3, GL_FLOAT, false, 8 * Float.BYTES,3 * Float.BYTES);
        //glVertexAttribPointer(2,2, GL_FLOAT, false, 8 * Float.BYTES,6 * Float.BYTES);
        //glEnableVertexAttribArray(0);
        //glEnableVertexAttribArray(1);
        //glEnableVertexAttribArray(2);


        //glBindBuffer(GL_ARRAY_BUFFER, 0);

/*
        //create uv buffer
        u_id = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, u_id);
        glBufferData(GL_ARRAY_BUFFER, createBuffer(uvs), GL_DYNAMIC_DRAW);
        //create uv attrib pointer
        glVertexAttribPointer(1,3, GL_FLOAT, false, 3 * Float.BYTES,0);
        glEnableVertexAttribArray(1);
*/

        //glBindBuffer(GL_ARRAY_BUFFER, 0);


        //glBindVertexArray(0);


    }
    public void load() {
        this.vao.load();
    }
    public FloatBuffer createBuffer(float[] data) {
        FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
        buffer.put(data);
        buffer.flip();
        return buffer;
    }
    public IntBuffer createBuffer(int[] data) {
        IntBuffer buffer = BufferUtils.createIntBuffer(data.length);
        buffer.put(data);
        buffer.flip();
        return buffer;
    }
    public void bind() {
        this.vao.bind();
    }
    public void unbind() {
        this.vao.unbind();
    }
    public static void cleanUp() {
        VertexArrayObject.cleanupAll();
        //GL30.glDeleteVertexArrays(vao);
        //GL15.glDeleteBuffers(v_id);
        //GL15.glDeleteBuffers(u_id);
    }

    public static Mesh createRect(float width, float height) {
        float[] vertices = {
                // positions
                width/2, height/2, 0f,
                width/2, -height/2, 0f,
                -width/2, -height/2, 0f,
                -width/2, height/2, 0f,
        };
        float[] colors = { // colors
                1.0f, 0.0f, 0.0f,
                0.0f, 1.0f, 0.0f,
                0.0f, 0.0f, 1.0f,
                1.0f, 1.0f, 0.0f
        };
        float[] texCoords = { // texture coords
                1.0f, 1.0f,   // top right
                1.0f, 0.0f,   // bottom right
                0.0f, 0.0f,   // bottom left
                0.0f, 1.0f    // top left
        };
        ArrayList<VertexBuffer> vbos = new ArrayList<VertexBuffer>();
        vbos.add(new VertexBuffer(2,1,texCoords));
        vbos.add(new VertexBuffer(3,2,colors));
        int[] indices = new int[] { 0, 1, 3, 2, 1, 3 };
        return new Mesh(new PositionBuffer(vertices),new ElementBuffer(indices),vbos);
    }
}
