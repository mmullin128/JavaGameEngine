package engine;

import org.joml.Vector2f;

import static org.lwjgl.glfw.GLFW.*;

public class MouseListener {
    private static MouseListener mouseListener;
    private double scrollX, scrollY;
    private double xPos, yPos, lastY, lastX;
    private boolean mouseButtonPressed[] = new boolean[3];
    private boolean isDragging;

    public class MouseEvent {
        public int button;
        public Vector2f pos;
        public MouseEvent(int button, Vector2f pos) {
            this.button = button;
            this.pos = pos;
        }
    }
    public class MousePressedEvent extends MouseEvent {
        public int clicks;
        public MousePressedEvent(int button, Vector2f pos, int clicks) {
            super(button,pos);
            this.clicks = clicks;
        }
    }
    public class MouseReleasedEvent extends MouseEvent {
        public MouseReleasedEvent(int button, Vector2f pos, int clicks) {
            super(button,pos);
            //this.clicks = clicks;
        }
    }

    private MouseListener() {
        this.scrollX = 0;
        this.scrollY = 0;
        this.xPos = 0;
        this.yPos = 0;
        this.lastX = 0;
        this.lastY = 0;
    }

    public static MouseListener get() {
        if (mouseListener == null) {
            mouseListener = new MouseListener();
        }
        return mouseListener;
    }
    public static void mousePosCallback(long window, double xPos, double yPos) {
        get().lastX = get().xPos;
        get().lastY = get().yPos;
        get().xPos = xPos;
        get().yPos = yPos;
        get().isDragging = get().mouseButtonPressed[0] || get().mouseButtonPressed[1] || get().mouseButtonPressed[2];
    }
    public static void mouseButtonCallback(long window, int button, int action, int mods) {
        if (button >= get().mouseButtonPressed.length) return;
        if (action == GLFW_PRESS) {
            get().mouseButtonPressed[button] = true;
        } else if (action == GLFW_RELEASE) {
            get().isDragging = false;
            get().mouseButtonPressed[button] = false;
        }
    }
    public static void mouseScrollCallback(long window, double xOffset, double yOffset) {
        get().scrollX = xOffset;
        get().scrollY = yOffset;
    }
    public static void endFrame() {
        get().scrollX = 0;
        get().scrollY = 0;
        get().lastY = get().yPos;
        get().lastX = get().xPos;
    }
    public static float getX() {
        return (float)get().xPos;
    }
    public static float getY() {
        return (float)get().yPos;
    }
    public static float getDx() {
        return (float)(get().lastX - get().xPos);
    }
    public static float getDy() {
        return (float)(get().lastY - get().yPos);
    }
    public static float getScrollX() {
        return (float)get().scrollX;
    }
    public static float getScrollY() {
        return (float)get().scrollY;
    }
    public static boolean isDragging() {
        return get().isDragging;
    }
    public static boolean mouseButtonDown(int button) {
        if (button >= get().mouseButtonPressed.length) return false;
        return get().mouseButtonPressed[button];
    }
}
