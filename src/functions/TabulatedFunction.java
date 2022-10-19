package functions;

import javax.swing.*;

public class TabulatedFunction {
    public FunctionPoint[] ValuesArray;
    private int AvailableNumberOfPoints;

    public void print(){
        for(int i = 0; i < AvailableNumberOfPoints; i++){
            System.out.println("(" + ValuesArray[i].getX() + ";" + ValuesArray[i].getY() + ")");
        }

    }

    public TabulatedFunction(double leftX, double rightX, int pointsCount){
        double size = ((rightX-leftX)/(pointsCount));
        this.ValuesArray = new FunctionPoint[pointsCount];

        for (int i=0;i<pointsCount;i++){
            this.ValuesArray[i] = new FunctionPoint(leftX+i*size,0);
        }

        AvailableNumberOfPoints = pointsCount;
    }

    public TabulatedFunction(double leftX, double rightX, double[] values){
        this.ValuesArray = new FunctionPoint[values.length];
        double size = ((rightX - leftX )/(values.length));

        for (int i = 0; i < values.length; i++){
            this.ValuesArray[i] = new FunctionPoint(leftX + i * size, values[i]);
        }

        AvailableNumberOfPoints = values.length;
    }

    double getLeftDomainBorder(){
        return this.ValuesArray[0].getX();
    }

    double getRightDomainBorder(){
        return this.ValuesArray[AvailableNumberOfPoints-1].getX();
    }

    private boolean checkBorders(FunctionPoint point){
        return (!((point.getX() > getRightDomainBorder()) || (point.getX() < getLeftDomainBorder())));
    }

    private boolean checkBorders(double x){
        return (!((x > getRightDomainBorder()) || (x < getLeftDomainBorder())));
    }

    public double getFunctionValue(double x){
        if((this.ValuesArray[ValuesArray.length-1].getX() < x) || (this.ValuesArray[0].getX() > x ) ){
            return Double.NaN;
        }
        int i = 0;
        double x1,x2,y1,y2;

        //4 points for calculations
        x1 = ValuesArray[AvailableNumberOfPoints-2].getX();
        y1 = ValuesArray[AvailableNumberOfPoints-2].getY();
        x2 = ValuesArray[AvailableNumberOfPoints-1].getX();
        y2 = ValuesArray[AvailableNumberOfPoints-1].getY();
        //find k
        double k = (y2 - y1)/(x2 - x1);
        //find b
        double b = y1 - k * x1;

        return k * x + b;
    }

    public int getPointsCount(){
        return AvailableNumberOfPoints;
    }

    public FunctionPoint getPoint(int index){
        if(index < AvailableNumberOfPoints && index >= 0){
            return new FunctionPoint(this.ValuesArray[index]);
        }
        else return null;
    }


    public void setPoint(int index, FunctionPoint point){
        if(AvailableNumberOfPoints == 1 && index >=0){
            this.ValuesArray[index] = point;
        }
        else if(index >=0){
            if(index < AvailableNumberOfPoints){
                if(index == 0 && point.getX() < ValuesArray[1].getX()){
                    this.ValuesArray[index] = point;
                }
                else if (index == AvailableNumberOfPoints - 1 && point.getX() > ValuesArray[index-1].getX()){
                    this.ValuesArray[index] = point;
                }
                else if (index > 0 && index < AvailableNumberOfPoints-1  && point.getX() > ValuesArray[index-1].getX() && point.getX() < ValuesArray[index+1].getX()) {
                    this.ValuesArray[index] = point;
                }
            }
        }
    }

    public double getPointX(int index){
        if(index < AvailableNumberOfPoints && index >= 0){
            return this.ValuesArray[index].getX();
        }
        else return Double.NaN;
    }

    void setPointX(int index, double x){
        if(AvailableNumberOfPoints == 1 && index >=0){
            this.ValuesArray[index].setX(x);
        }
        else if(index >=0){
            if(index < AvailableNumberOfPoints){
                if(index == 0 && x < ValuesArray[1].getX()){
                    this.ValuesArray[index].setX(x);
                }
                else if (index == AvailableNumberOfPoints - 1 && x > ValuesArray[index-1].getX()){
                    this.ValuesArray[index].setX(x);
                }
                else if (index > 0 && index < AvailableNumberOfPoints-1  && x > ValuesArray[index-1].getX() && x < ValuesArray[index+1].getX()) {
                    this.ValuesArray[index].setX(x);
                }
            }
        }
    }

    public double getPointY(int index){
        if(index < AvailableNumberOfPoints && index >= 0){
            return this.ValuesArray[index].getY();
        }
        else return Double.NaN;
    }

    void setPointY(int index, double y){
        if(AvailableNumberOfPoints == 1 && index >=0){
            this.ValuesArray[index].setY(y);
        }
        else if(index >=0){
            if(index < AvailableNumberOfPoints){
                if(index == 0 && y < ValuesArray[1].getX()){
                    this.ValuesArray[index].setY(y);
                }
                else if (index == AvailableNumberOfPoints - 1 && y > ValuesArray[index-1].getX()){
                    this.ValuesArray[index].setY(y);
                }
                else if (index > 0 && index < AvailableNumberOfPoints-1  && y > ValuesArray[index-1].getX() && y < ValuesArray[index+1].getX()) {
                    this.ValuesArray[index].setY(y);
                }
            }
        }
    }

    public void deletePoint(int index){
        if(index < getPointsCount() && index >= 0){
            System.arraycopy(ValuesArray, index + 1, ValuesArray, index, ValuesArray.length - index - 1);
            AvailableNumberOfPoints--;
        }
    }
    public void addPoint(FunctionPoint point) {
        int index = 0;
        if(point.getX() > this.ValuesArray[AvailableNumberOfPoints-1].getX()){
            index = AvailableNumberOfPoints;
            if(this.ValuesArray.length>AvailableNumberOfPoints) {
                AvailableNumberOfPoints++;
            }
        }
        else {
            while (ValuesArray[index].getX() < point.getX()) index++;
        }

        if(index < AvailableNumberOfPoints){
            if(AvailableNumberOfPoints <= this.ValuesArray.length){
                FunctionPoint[] tmp = new FunctionPoint[getPointsCount()];
                System.arraycopy(ValuesArray,0,tmp,0,tmp.length);
                this.ValuesArray = new FunctionPoint[getPointsCount() + 1];
                AvailableNumberOfPoints++;
                System.arraycopy(tmp,0,ValuesArray,0,index);
                this.ValuesArray[index] = point;
                System.arraycopy(tmp,index,ValuesArray,index+1,tmp.length-index);
            }
            else{
                System.arraycopy(ValuesArray,index,ValuesArray,index+1,getPointsCount()-index);
                this.ValuesArray[index]=point;
            }

        }
        else{
            FunctionPoint[] tmp = new FunctionPoint[getPointsCount()];
            System.arraycopy(ValuesArray,0,tmp,0,tmp.length);
            this.ValuesArray = new FunctionPoint[getPointsCount() + 1];
            AvailableNumberOfPoints++;
            System.arraycopy(tmp,0,ValuesArray,0,tmp.length);
            this.ValuesArray[AvailableNumberOfPoints-1] = point;

        }
    }
}

