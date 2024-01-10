package engine.VertexArrayObject;


import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL20.*;

public class VertexAttributePointer {
    private static List<Integer> indexList = new ArrayList<Integer>();

    private int channels;
    private int attributeIndex;
    public VertexAttributePointer(int channels, int attributeIndex) {
        this.channels = channels;
        this.attributeIndex = attributeIndex;
        glVertexAttribPointer(attributeIndex,channels, GL_FLOAT, false, channels * Float.BYTES,0);
        indexList.add(attributeIndex);
    }
    public void enable() {
        glEnableVertexAttribArray(attributeIndex);
    }
    public void disable() {
        glDisableVertexAttribArray(attributeIndex);
    }
    public static void disableAll() {
        for (Integer i : indexList) {
            glDisableVertexAttribArray(i);
        }
    }
}
