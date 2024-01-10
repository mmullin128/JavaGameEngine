import engine.Shader;
import engine.Window;

public class ShaderTest {
    public static void main(String args[]) {
        Window window = new Window(800,800,"Shader Test");
        Shader shader = new Shader("DefaultShader");
        try {
            shader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }
        shader.cleanup();
        window.cleanup();
    }
}
