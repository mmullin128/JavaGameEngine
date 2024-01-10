package engine.GameObject;

import engine.Mesh;
import engine.MeshRenderer;
import engine.Texture;
import engine.Transform;

import java.util.ArrayList;

public class GameObject {
    Mesh mesh;
    Transform transform;
    MeshRenderer meshRenderer;
    Texture texture;
    public GameObject(Transform transform, Mesh mesh, Texture texture, MeshRenderer meshRenderer) {
        this.mesh = mesh;
        this.transform = transform;
        this.texture = texture;
        this.meshRenderer = meshRenderer;
    }
    public void start() {}
    public void update() {}
    public void load() {
        this.mesh.load();
        this.texture.load();
    }
    public void render() {
        meshRenderer.render();
    }
}
