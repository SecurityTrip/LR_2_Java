import functions.FunctionPoint;
import functions.TabulatedFunction;

public class Main {
    public static void main(String[] args) {


        double left = 0;
        double right = 4;
        double[] values = { 1, 4 , 9 , 16};
        TabulatedFunction arr = new TabulatedFunction(left, right, values);


        arr.print();
        arr.deletePoint(1);
        FunctionPoint n = new FunctionPoint(5,25);
        arr.addPoint(n);
        System.out.println();
        arr.print();

        //System.out.println(arr.getFunctionValue(4.0));

        //FunctionPoint a = arr.getPoint(0);
        //System.out.println(a.getX());

    }
}