import java.util.ArrayList;

public class pared {

    private int x1;
    private int x2;
    private int y1;
    private int y2;

    private ArrayList<Integer[]> funciones_lineales_de_las_paredes;

    public pared() {
    }

    public pared(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;

        this.funciones_lineales_de_las_paredes = new ArrayList<>();

        if(y1 + 2 > y2 && y1 - 2 < y2){
            if(x1 > x2){
                for (int i = x2; i <= x1; i++){
                    Integer[] puntos = new Integer[2];
                    puntos[0] = y1;
                    puntos[1] = i+25;
                    funciones_lineales_de_las_paredes.add(puntos);
                }
            }else{
                for (int i = x1; i <= x2; i++){
                    Integer[] puntos = new Integer[2];
                    puntos[0] = y1;
                    puntos[1] = i+25;
                    funciones_lineales_de_las_paredes.add(puntos);
                }
            }
        }else{

            if(y1 > y2){
                for (int i = y2; i <= y1; i++){
                    Integer[] puntos = new Integer[2];
                    puntos[0] = i+25;
                    puntos[1] = x1;
                    funciones_lineales_de_las_paredes.add(puntos);
                }
            }else{
                for (int i = y1; i <= y2; i++){
                    Integer[] puntos = new Integer[2];
                    puntos[0] = i+25;
                    puntos[1] = x1;
                    funciones_lineales_de_las_paredes.add(puntos);
                }
            }
        }

        for(int i = 0; i < funciones_lineales_de_las_paredes.size(); i++){
            System.out.println("(" + funciones_lineales_de_las_paredes.get(i)[1] + "," + funciones_lineales_de_las_paredes.get(i)[0]+ ")");
        }

    }

    public ArrayList<Integer[]> getFunciones_lineales_de_las_paredes() {
        return funciones_lineales_de_las_paredes;
    }

    public void setFunciones_lineales_de_las_paredes(ArrayList<Integer[]> funciones_lineales_de_las_paredes) {
        this.funciones_lineales_de_las_paredes = funciones_lineales_de_las_paredes;
    }

    public int getX1() {
        return x1;
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public int getX2() {
        return x2;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public int getY1() {
        return y1;
    }

    public void setY1(int y1) {
        this.y1 = y1;
    }

    public int getY2() {
        return y2;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }



    @Override
    public String toString() {
        return "pared{" +
                "x1=" + x1 +
                ", x2=" + x2 +
                ", y1=" + y1 +
                ", y2=" + y2 +
                '}';
    }
}
