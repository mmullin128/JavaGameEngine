package engine.VertexArrayObject;

import org.lwjgl.opengl.GL15;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.glBindBuffer;
import static org.lwjgl.opengl.GL30.*;

public class VertexArrayObject {
    public static List<Integer> list = new ArrayList<Integer>();

    public int id;
    private Collection<VertexBuffer> vbos;
    private ElementBuffer ebo;

    public VertexArrayObject(Collection<VertexBuffer> vbos, ElementBuffer ebo) {
        //vertex array object stores state for multiple VertexBuffers, and one ElementBuffer
        id = glGenVertexArrays();
        list.add(id);
        this.vbos = vbos;
        this.ebo = ebo;

    }

    public void load() {
        this.bind();
        ebo.load();
        for (VertexBuffer vbo : vbos) {
            vbo.load();
            //vbo automatically unbinds after loading
        }
        this.unbind();
        //element buffer must be unbound after unbinding the current vao
        ebo.unbind();

    }

    public static void cleanupAll() {
        //delete vertex buffers
        VertexBuffer.cleanupAll();
        //delete element buffers
        ElementBuffer.cleanupAll();
        //delete vaos
        for (int id : list) {
            glDeleteVertexArrays(id);
        }
        //disable all vertex atrrib pointers
        VertexAttributePointer.disableAll();
    }
    public void cleanup() {
        for (VertexBuffer vbo: vbos) {
            vbo.cleanup();
        }
        ebo.cleanup();
        glDeleteVertexArrays(id);
    }
    public void bind() { glBindVertexArray(id);;}
    public void unbind() { glBindVertexArray(0);;}
}
