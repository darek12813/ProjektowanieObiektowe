package main.java;

public class Matrix implements IMatrix {

    private int _wight;
    private int _hight;
    private double[][] _matrix;


    public Matrix(){

    }
    public Matrix(double[][] values){
        _wight = values.length;
        _hight = values[0].length;
        _matrix = new double[_wight][];
        for(int i = 0; i<_wight; i++){
            _matrix[i] = values[i].clone();
        }
    }

    public Matrix(int w, int h){
        _wight = w;
        _hight = h;
        _matrix = new double[w][h];
    }
    @Override
    public void createIdentityMatrix(int size) {
        _matrix = new double[size][size];
        for(int i = 0; i < size; i++)
            for(int j = 0; j < size; j++)
                _matrix[i][j] = (i == j) ? 1 : 0;

    }

    @Override
    public double determinant() throws InvalidDimensionException {

        if(_wight != _hight )
            throw new InvalidDimensionException("macierze nie maja identycznych rozmiarow");

        double result = determinant(_matrix);

        return (result);
    }
    public double determinant(double matrix[][]){
        double tmp[][];
        double result = 0;

        if(matrix.length == 1){
            result = matrix[0][0];
            return(result);
        }
        if(matrix.length == 2){
            result = ((matrix[0][0] * matrix[1][1]) - (matrix[0][1] * matrix[1][0]));
            return(result);
        }
        for (int i = 0; i < matrix.length; i++) {
            tmp = new double[matrix.length - 1][ matrix[0].length - 1];

            for (int j = 1; j < matrix.length; j++) {
                for (int k = 0; k < matrix[0].length; k++) {
                    if (k < i) {
                        tmp[j - 1][k] = matrix[j][k];
                    } else if (k > i) {
                        tmp[j - 1][k - 1] = matrix[j][k];
                    }
                }
            }

            result += matrix[0][i] * Math.pow (-1, (double) i) * determinant (tmp);
        }
        return (result);
    }

    @Override
    public double getMatrixValue(int row, int column) {
        return _matrix[row][column];
    }

    @Override
    public void setMatrixValue(int row, int column, double value) {
        _matrix[row][column] = value;
    }

    @Override
    public void setMatrixValues(double[][] values) throws InvalidDimensionException {
        _wight = values.length;
        _hight = values[0].length;
        _matrix = new double[_wight][];
        for(int i = 0; i<_wight; i++){
            _matrix[i] = values[i].clone();
        }
    }

    public double[][] getMatrix(){
        return _matrix;
    }

    @Override
    public int getWidth() {
        return _wight;
    }

    @Override
    public int getHeight() {
        return _hight;
    }

    @Override
    public String toString() {
        String matrixString = "";
        for(int i =0; i<_wight; i++){

            for(int j = 0; i<_hight; j++){
                matrixString += _matrix[i][j] + "\t";
            }

            matrixString += "\n";
        }

        return matrixString;
    }
}
