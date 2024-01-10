package engine.VertexArrayObject;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL15;

import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL15.GL_DYNAMIC_DRAW;

public class VertexBuffer {
    //stores vertex data for rendering

    public static List<Integer> list = new ArrayList<Integer>();

    private int id;
    private int attributeIndex;
    private float[] data;

    public int channels; //how many individual values per vertex (i.e. 3 channels for position, 4 for rgba colors
    public int vertexCount;

    public VertexBuffer(int channels, int attributeIndex, float[] data) {
        id  = glGenBuffers();
        list.add(id);
        this.channels = channels;
        this.attributeIndex = attributeIndex;
        this.vertexCount = data.length / channels;
        this.data = data;
    }
    public void load() {
        // bind buffer - add data to buffer - create vertex atrribute pointer bound to that buffer;
        this.bind();
        //GL_DYNAMIC_DRAW used for buffer data that changes frequently
        glBufferData(GL_ARRAY_BUFFER, createBuffer(data), GL_STATIC_DRAW);
        //vertex atrrib pointer must be called while this buffer is bound
        new VertexAttributePointer(channels, attributeIndex).enable();
        //vertex buffer can be unbound after it's attribute pointer is set
        //this doesn't affect vao state
        this.unbind();
    }

    public FloatBuffer createBuffer(float[] data) {
        FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
        buffer.put(data);
        buffer.flip();
        return buffer;
    }

    public void cleanup() {
        GL15.glDeleteBuffers(id);
    }
    public static void cleanupAll() {
        for (int id : list) {
            GL15.glDeleteBuffers(id);
        }
    }

    public void bind() { glBindBuffer(GL_ARRAY_BUFFER, id);}
    public void unbind() { glBindBuffer(GL_ARRAY_BUFFER, 0);}

    public static void unbindAll() { glBindBuffer(GL_ARRAY_BUFFER, 0);}

}
