package engine;

import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector4f;
import org.lwjgl.opengl.GL20;
import org.lwjgl.system.MemoryStack;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import static org.lwjgl.opengl.GL20.*;

public class Shader {
    private int program;
    private int vs;
    private int fs;

    private Map<String, Integer> uniforms;
    private String fileName;


    private static Path resourcesDirectory = Paths.get(System.getProperty("user.dir"),"src/main/resources");

    public Shader(String fileName) {
        this.uniforms = new HashMap<String, Integer>();
        this.fileName = fileName;
    }
    public void load() throws Exception {
        //create and compile vertex shader
        vs = glCreateShader(GL_VERTEX_SHADER);
        String vsSource = readProgram((fileName + ".vs"));
        if (vsSource == null) throw new Exception(("Failed to read: " + fileName + ".vs"));
        glShaderSource(vs,vsSource);
        glCompileShader(vs);
        //check for compile errors
        if (glGetShaderi(vs, GL_COMPILE_STATUS) != 1) {
            System.err.println(glGetShaderInfoLog(vs));
            throw new Exception("Failed to compile Vertex Shader from source: " + fileName + ".vs");
        }
        //create and compile fragment shader
        fs = glCreateShader(GL_FRAGMENT_SHADER);
        String fsSource = readProgram((fileName + ".fs"));
        if (fsSource == null) throw new Exception(("Failed to read: " + fileName + ".fs"));
        glShaderSource(fs, fsSource);
        glCompileShader(fs);
        //check for compile errors
        if (glGetShaderi(fs, GL_COMPILE_STATUS) != 1) {
            //System.out.println(glGetShaderInfoLog(fs));
            throw new Exception("Failed to compile Fragment Shader from source: " + fileName + ".fs" + "\n" + glGetShaderInfoLog(fs));
            //return;
        }
        //create shader program and link shaders
        program = glCreateProgram();

        glAttachShader(program, vs);
        glAttachShader(program, fs);

        //create the locations that correspond to the index of the
        //glBindAttribLocation(program, 0, "vertices");
        //glBindAttribLocation(program, 1, "uv");

        glLinkProgram(program);
        //check for errors
        if (glGetProgrami(program, GL_LINK_STATUS) != 1) {
            System.err.println(glGetProgramInfoLog(program));
            throw new Exception("Failed to create Shader Program");

        }

        glValidateProgram(program);
        if (glGetProgrami(program, GL_VALIDATE_STATUS) != 1) {
            System.err.println(glGetProgramInfoLog(program));
            throw new Exception("Failed to validate Shader Program");
        }
        this.bind();
        this.unbind();
        glDeleteShader(vs);
        glDeleteShader(fs);
    }
    private String readProgram(String filename) {
        try {
            StringBuilder sb = new StringBuilder();
            Path filePath = resourcesDirectory.resolve("Shaders/" + filename);
            BufferedReader br;
            br = new BufferedReader(new FileReader(new File(filePath.toString())));
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
                sb.append("\n");
            }
            br.close();
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }
    public void bind() {
        glUseProgram(program);
    }
    public void unbind() {
        glUseProgram(0);
    }
    public int getUniform(String uniformName) {
        int uniform = glGetUniformLocation(program, uniformName);
        return uniform;
    }
    public void setUniform(String uniformName, Vector4f v4) {
        glUniform4f(getUniform(uniformName),v4.x,v4.y,v4.z,v4.w);
    }
    public void setUniform(String uniformName, Vector3f v3) {
        glUniform3f(getUniform(uniformName),v3.x,v3.y,v3.z);
    }
    public void setUniform(String uniformName, Vector2f v2) {
        glUniform2f(getUniform(uniformName),v2.x,v2.y);
    }
    public void setUniform(String uniformName, float v) {
        glUniform1f(getUniform(uniformName), v);
    }
    public int getProgram() {return program;}
    public void cleanup() { glDeleteProgram(program);}
}
