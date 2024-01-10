package engine;

import org.joml.Vector2f;

public class Camera {
    public Vector2f scale;
    public Vector2f origin;

    public Camera(Vector2f origin, Vector2f scale) {
        this.origin = origin;
        this.scale = scale;
    }
    public void setOrigin(Vector2f newOrigin) {
        origin = newOrigin;
    }
    public void moveOrigin(Vector2f dP) {
        origin = origin.add(dP);
    }
    public void setScale(Vector2f newScale) {
        scale = newScale;
    }
    public void zoom(Vector2f dS) {
        scale = scale.mul(dS.x,dS.y);
    }
}
