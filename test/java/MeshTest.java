import engine.Mesh;
import engine.Window;

public class MeshTest {
    public static void main(String[] args) {
        Window window = new Window(800,800,"Mesh Test");
        try {
            Mesh obj1 = Mesh.createRect(1,1);
            obj1.load();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Mesh.cleanUp();
        window.cleanup();
    }
}
