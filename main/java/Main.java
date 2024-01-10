import engine.*;
import org.lwjgl.Version;

import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println(Version.getVersion());
        Window window = new Window(1000,800,"Java Game");
        //Shader defaultShader = new Shader("DefaultShader");
        //Mesh obj1 = Mesh.createRect(1,1);
        //obj1.load();
        //Mesh obj2 = Mesh.createRect(1.5f,1f);

        //Texture textureRED = new Texture(Color.red());
        //Texture textureBLUE = new Texture(Color.blue());
        //Texture textureBrick = new Texture("brick1.png");
        //textureBrick.load();

        //Transform obj1Transform = Transform.identity();
        //Transform obj2Transform = Transform.identity();

        //MeshRenderer meshRenderer1 = new MeshRenderer(obj1,obj1Transform,textureBrick,defaultShader);
        //MeshRenderer meshRenderer2 = new MeshRenderer(obj2,obj2Transform,textureRED,defaultShader);

        //MeshRenderer[] renderers = new MeshRenderer[] { meshRenderer1 };

        window.setWireFrameMode(false);


        while ( !window.shouldClose() ) {
            window.update();
            Thread.sleep(100);
        }
        window.cleanup();
        //obj1.cleanUp();
        //defaultShader.cleanup();

            //shader.bind();
            //shader.setUniform("matColor", new Color(0,1,0,1));
            //mesh.render();
            //shader.unbind();
    }
}
