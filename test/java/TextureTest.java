import engine.Texture;
import engine.Window;

public class TextureTest {
    public static void main(String args[]) {
        Window window = new Window(800,800,"Texture Test");
        Texture texture = new Texture("brick1.png");
        try {
            texture.load();
        } catch (Exception e) {
            e.printStackTrace();
        }
        texture.cleanup();
        window.cleanup();
    }
}

