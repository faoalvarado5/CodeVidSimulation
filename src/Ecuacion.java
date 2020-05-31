public class Ecuacion {

    public static void main(String[] args){

        double y1 = 5.0;
        double x1 = -2.0;
        double y2 = 3.0;
        double x2 = -6.0;

        double pediente = (y2-y1)/(x2-x1);

        double b = -(pediente*x2-y2);

        System.out.println("y = " + pediente + " x+ " + b);

        for(int i = 0; i <= 500; i++){
            System.out.println("y = " + (double)((pediente * i) + b));
            System.out.println("x = " + (double)((i-b)/pediente));
        }

    }


}
