package engine;

public class Color {
    public float r = 1;
    public float g = 1;
    public float b = 1;
    public float a = 1;

    public Color(float r, float g, float b, float a) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
    }
    public Color(float r, float g, float b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }
    public static Color black() {return new Color(0,0,0);}
    public static Color white() {return new Color(1,1,1);}
    public static Color grey() {return new Color(.5f,.5f,.5f);}
    public static Color red() {return new Color(1,0,0);}
    public static Color green() {return new Color(0,1,0);}
    public static Color blue() {return new Color(0,0,1);}
    public static Color yellow() {return new Color(1,1,0);}
    public static Color cyan() {return new Color(0,1,1);}
    public static Color magenta() {return new Color(1,0,1);}

    public String toString() {return "(" + String.valueOf(r) + ", " + String.valueOf(g) + ", " + String.valueOf(b) + ", " + String.valueOf(a) + ")";}

}
