package functions;

public class TabulatedFunction {
    private FunctionPoint[] ValuesArray;

    public TabulatedFunction(double leftX, double rightX, int pointsCount){
        double size = ((rightX-leftX)/(pointsCount-1));
        this.ValuesArray = new FunctionPoint[pointsCount];

        for (int i=0;i<pointsCount;i++){
            this.ValuesArray[i] = new FunctionPoint(leftX+i*size,0);
        }
    }

    public TabulatedFunction(double leftX, double rightX, double[] values){
        this.ValuesArray = new FunctionPoint[values.length];
        double size = ((rightX-leftX)/(values.length-1));

        for (int i=0; i< values.length; i++){
            this.ValuesArray[i] = new FunctionPoint(leftX + i * size, values[i]);
        }
    }

    double getLeftDomainBorder(){
        return this.ValuesArray[0].getX();
    }

    double getRightDomainBorder(){
        return this.ValuesArray[ValuesArray.length-1].getX();
    }

    private boolean checkBorders(FunctionPoint point){
        return (!((point.getX() > getRightDomainBorder()) || (point.getX() < getLeftDomainBorder())));
    }

    private boolean checkBorders(double x){
        return (!((x > getRightDomainBorder()) || (x < getLeftDomainBorder())));
    }

    public double getFunctionValue(double x){
        if((this.ValuesArray[0].getX() > x ) || (this.ValuesArray[ValuesArray.length-1].getX() < x)){
            return Double.NaN;
        }

        //y = k * x + b

        int i = 0;

        //Find index < x
        while (ValuesArray[i].getX() < x) i++;


        double x1,x2,y1,y2;

        //4 points for calculations
        x1 = ValuesArray[i - 1].getX();
        y1 = ValuesArray[i - 1].getY();
        x2 = ValuesArray[i].getX();
        y2 = ValuesArray[i].getY();
        //find k
        double k = (y2 - y1)/(x2 - x1);
        //find b
        double b = y1 - k * x1;

        return k * x + b;
    }

    int getPointsCount(){
        return ValuesArray.length;
    }

    FunctionPoint getPoint(int index){
        return new FunctionPoint(this.ValuesArray[index]);
    }



    public void setPoint(int index, FunctionPoint point){
        if(checkBorders(point)){
            this.ValuesArray[index] = point;
        }
    }

    public double getPointX(int index){
        return this.ValuesArray[index].getX();
    }

    void setPointX(int index, double x){
        if (checkBorders(x)){
            this.ValuesArray[index].setX(x);
        }
    }

    public double getPointY(int index){
        return this.ValuesArray[index].getY();
    }

    void setPointY(int index, double y){
        this.ValuesArray[index].setY(y);
    }

    public void deletePoint(int index){
        if(index < getPointsCount() && index >= 0){
            FunctionPoint[] tmp_before = new FunctionPoint[index-1];
            FunctionPoint[] tmp_after = new FunctionPoint[getPointsCount()-index];

            //Copy arrays to tmp
            System.arraycopy(ValuesArray, 0, tmp_before, 0, index -1);
            System.arraycopy(ValuesArray, index+1, tmp_after, index, getPointsCount()-index);

            this.ValuesArray = new FunctionPoint[tmp_before.length + tmp_after.length];

            //Add 2 tmp arrays to final array
            System.arraycopy(ValuesArray, 0, tmp_before,0, tmp_before.length);
            System.arraycopy(ValuesArray, 0, tmp_after,tmp_before.length, tmp_after.length);


        }


    }

    public void addPoint(FunctionPoint point) {

        if (checkBorders(point)) {

            //Find index < x
            int i = 0;
            while (ValuesArray[i].getX() < point.getX()) i++;

            //New resized array
            FunctionPoint[] tmp = new FunctionPoint[getPointsCount() + 1];

            //Add new point
            tmp[i] = point;

            //Copy old array to new
            System.arraycopy(ValuesArray, 0, tmp, 0, i);
            System.arraycopy(ValuesArray, 0, tmp, i+1,getPointsCount()-i);

            //resize an existing array to new size
            this.ValuesArray = new FunctionPoint[getPointsCount()+1];
            ValuesArray = tmp;
        }
    }
}

