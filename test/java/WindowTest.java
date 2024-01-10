import engine.Window;
public class WindowTest
{
    public static void main(String[] args) {
        Window window = new Window(800,800,"Test Window");
        while (!window.shouldClose()) {
            window.update();
        }
        window.cleanup();
    }
}
