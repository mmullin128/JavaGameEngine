package engine.Grid2d;

import engine.GameObject.GameObject;
import org.joml.Vector2f;

import java.util.ArrayList;
import java.util.HashMap;

public class Grid2d {
    static int x_hashCode = 1220147;
    static int y_hashCode = 6222163;
    static int z_hashCode = 1349737;
    static int w_hashCode = 8363533;

    static int maxWidth = 300;

    public HashMap<Integer, Cell> cells;
    public int gridSizePx;
    public int cellSizePx;
    public int divisionNum;
    public int leftBound;
    public int rightBound;
    public int topBound;
    public int bottomBound;

    public class Cell extends Grid2d {
        HashMap<Integer, GameObject> objects;
        public Cell(int sizePx,int divisionNum) {
            super(sizePx,divisionNum);
        }
        public void add(int id, GameObject obj) {
            if (objects == null) {
                objects = new HashMap<Integer, GameObject>();
            }
            objects.put(id,obj);
        }
        public ArrayList<GameObject> collect() {
            return new ArrayList<GameObject>(objects.values());
        }
    }

    public Grid2d(int gridSizePx,int divisionNum) {
        cells = new HashMap<Integer, Cell>();
        this.gridSizePx = gridSizePx;
        this.divisionNum = divisionNum * 4;
        this.cellSizePx = gridSizePx / (this.divisionNum / 2);
    }
    public static Integer hash(int x, int y, int size) {
        return x * x_hashCode + y * y_hashCode + size * z_hashCode;
    }
    public void put(Integer id, GameObject obj, Vector2f pos) {
        Cell gridLocation = findOrAdd(pos);
        gridLocation.add(id,obj);

    }
    public Cell findOrAdd(Vector2f pos) {
        int gridX = (int) pos.x / gridSizePx;
        int gridY = (int) pos.y / gridSizePx;
        if (!cells.containsKey(hash(gridX,gridY,gridSizePx))) {
            cells.put(hash(gridX,gridY,gridSizePx),new Cell(cellSizePx,divisionNum));
        }
        return cells.get(hash(gridX,gridY,gridSizePx));
    }
    public ArrayList<GameObject> collect() {
        ArrayList<GameObject> objs = new ArrayList<>();
        for (Cell gridCell : cells.values()) {
            objs.addAll(gridCell.collect());
        }
        return objs;
    }
}
