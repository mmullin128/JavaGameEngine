package engine.VertexArrayObject;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL15;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;

public class ElementBuffer {

    private static List<Integer> buffers = new ArrayList<Integer>();

    private int id;
    private int[] indices;

    public int indicesCount;

    public ElementBuffer(int[] indices) {
        id  = glGenBuffers();
        buffers.add(id);
        this.indices  = indices;
        this.indicesCount = indices.length;
    }
    public void load() {
        this.bind();
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, createBuffer(indices), GL_STATIC_DRAW);
    }
    public IntBuffer createBuffer(int[] data) {
        IntBuffer buffer = BufferUtils.createIntBuffer(data.length);
        buffer.put(data);
        buffer.flip();
        return buffer;
    }

    public void cleanup() {
        GL15.glDeleteBuffers(id);
    }
    public static void cleanupAll() {
        for (int id : buffers) {
            GL15.glDeleteBuffers(id);
        }
    }
    public void bind() { glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, id);}
    public void unbind() { glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);}
    public static void unbindAll() { glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);}
}
