import functions.FunctionPoint;
import functions.TabulatedFunction;

public class Main {
    public static void main(String[] args) {

        //Generating values for points
        int size = 10;

        double[] values = new double[size*2];

        //y = 4x - 3
        for (int i = 0; i<size; i++){
            double t = i;
            t = t * 2 - 0.5;

            values[i] = t;

        }

        //Generating out of range numbers
        for(int i = 0; i < size; ++i){
            double t = i;
            t=t*2-0.5 - 1;

            values[10 + i] = t;
        }


        TabulatedFunction FunctionPoints = new TabulatedFunction(0,10 * 2 - 0.5, values);

        System.out.print("Generated array: ");


        for (int i = 0; i < 20; ++i){
            System.out.print("( ");
            System.out.print(FunctionPoints.getPointX(i));
            System.out.print(" ; ");
            System.out.print(FunctionPoints.getPointY(i));
            System.out.print(" ), ");
        }

        System.out.println();
        System.out.println();


        for(int i = 8; i < 15; i++ ){
            System.out.print("Value of the function in the point x = ");
            System.out.print(FunctionPoints.getPointX(i));
            System.out.print(" is y = ");
            System.out.println(FunctionPoints.getFunctionValue(FunctionPoints.getPointX(i)));

        }

        FunctionPoints.deletePoint(1);
        for (int i = 0; i < 19; ++i){
            System.out.print("( ");
            System.out.print(FunctionPoints.getPointX(i));
            System.out.print(" ; ");
            System.out.print(FunctionPoints.getPointY(i));
            System.out.print(" ), ");
        }



    }
}