import engine.*;
import org.joml.Vector2f;

public class MeshRendererTest {
    public static void main(String[] args) {
        Window window = new Window(800,800, "MeshRendererTest");
        Mesh obj1 = Mesh.createRect(1,1);
        Shader shader = new Shader("DefaultShader");
        Texture tex1 = new Texture("brick1.png");
        Transform obj1Transform = Transform.identity();
        Camera c1 = new Camera(new Vector2f(1,0),new Vector2f(1,1));
        MeshRenderer renderer = new MeshRenderer(obj1,obj1Transform,tex1,shader,c1);
        try {
            obj1.load();
            shader.load();
            tex1.load();
            shader.bind();
            window.addRenderer(renderer);
            while (!window.shouldClose())
            {
                //shader.bind();
                //obj1Transform.scale(new Vector2f(.9f,.9f));
                //obj1Transform.rotate(.1f);
                obj1Transform.translate(new Vector2f(.01f,0));
                window.update();
                Thread.sleep(10);
            }
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            shader.unbind();
            window.cleanup();
            Mesh.cleanUp();
            shader.cleanup();
            tex1.cleanup();
        }

    }
}
