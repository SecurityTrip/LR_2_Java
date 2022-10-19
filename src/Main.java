import functions.FunctionPoint;
import functions.TabulatedFunction;

public class Main {
    public static void main(String[] args) {


        double left = 0;
        double right = 4;
        double[] val = { -1.0, 1.0 , 3.0 , 5.0};
        TabulatedFunction fir = new TabulatedFunction(left, right, val);


        fir.print();
        fir.deletePoint(0);
        FunctionPoint n = new FunctionPoint(4,7);
        //fir.addPoint(n);
        System.out.println();
        fir.print();

        //System.out.println(fir.getFunctionValue(4.0));

        FunctionPoint a = fir.getPoint(0);
        System.out.println(a.getX());

    }
}