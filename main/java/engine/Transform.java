package engine;

import org.joml.Quaternionf;
import org.joml.Quaternionfc;
import org.joml.Vector2f;
import org.joml.Vector3f;

public class Transform {
    public Vector2f position, scale;
    float rotation;
    public Transform(Vector2f position, Vector2f scale, float rotation) {
        this.position = position;
        this.scale = scale;
        this.rotation = rotation;
    }
    public static Transform identity() {
        return new Transform(new Vector2f(0,0),new Vector2f(1f,1f), 0);
    }
    public void translate(Vector2f dP) {
        position = position.add(dP);
    }
    public void scale(Vector2f dS) {
        scale = scale.mul(dS.x,dS.y);
    }
    public void rotate(float dR) {
        rotation += dR;
    }
}
