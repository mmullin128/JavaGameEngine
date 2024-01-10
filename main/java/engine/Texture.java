package engine;

import org.lwjgl.stb.STBImage;
import org.lwjgl.system.MemoryStack;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL30.glGenerateMipmap;

public class Texture {
    private static List<Integer> textures = new ArrayList<Integer>();

    private Path resourceDirectory = Paths.get(System.getProperty("user.dir")).resolve("src/main/resources");

    int id;
    String fileName;

    public Texture(String fileName) {
        this.fileName = fileName;
        id = glGenTextures();
        textures.add(id);
        //glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
        //glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);
        //glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
        //glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
    }
    public void load() {
        try {
            loadTexture(this.fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void loadTexture(String fileName) throws Exception {
        Path filePath = resourceDirectory.resolve("Textures/" + fileName);
        int width, height;
        ByteBuffer buffer;
        //load image from file into memory
        try(MemoryStack stack = MemoryStack.stackPush()) {
            IntBuffer w = stack.mallocInt(1);
            IntBuffer h = stack.mallocInt(1);
            IntBuffer c = stack.mallocInt(1);

            buffer = STBImage.stbi_load(filePath.toString(),w,h,c,4);
            if (buffer == null)
                throw new Exception("Failed to load image file: " + filePath.toString() + " " + STBImage.stbi_failure_reason());

            width = w.get();
            height = h.get();
        }
        //bind texture to opengl
        this.bind();
        glPixelStorei(GL_UNPACK_ALIGNMENT, 1);
        glTexImage2D(GL_TEXTURE_2D,0,GL_RGBA,width,height,0,GL_RGBA,GL_UNSIGNED_BYTE,buffer);
        glGenerateMipmap(GL_TEXTURE_2D);
        //be sure to free the image buffer
        STBImage.stbi_image_free(buffer);
        this.unbind();

    }
    public void bind() {
        glBindTexture(GL_TEXTURE_2D, id);
    }
    public void unbind() {
        glBindTexture(GL_TEXTURE_2D, 0);
    }
    public void cleanup() { glDeleteTextures(id); }
    public static void cleanupAll() {
        for (Integer id : textures) {
            glDeleteTextures(id);
        }
    }
}
