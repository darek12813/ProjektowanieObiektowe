import main.java.*;

import static junit.framework.TestCase.assertEquals;

import org.junit.Test;

public class MatrixTests {
    private int _w;
    private int _h;
    private int _size;
    private IMatrix _m;
    private double[][] _testMatrix = new double[][]{
            {1,3,0,-1},
            {0,2,1,3},
            {3,1,2,1},
            {-1,2,0,3}
    };

    @Test
    public void createIdentityMatrixTest(){
        _size = 5;
        Matrix M = new Matrix();
        M.createIdentityMatrix(_size);
        for(int i = 0; i < _size; i++)
            for(int j = 0; j < _size; j++){
                if (i == j) {
                    assertEquals(1.0,M.getMatrixValue(i, j));
                } else {
                    assertEquals(0.0,M.getMatrixValue(i, j));
                }
            }

    }
    @Test
    public void determinantTest() throws InvalidDimensionException{
        IMatrix tmpM;
        tmpM = new Matrix(new double[][]{
                {1,3,0,-1},
                {0,2,1,3},
                {3,1,2,1},
                {-1,2,0,3}
        });
        assertEquals(14.0 ,tmpM.determinant());

        tmpM = new Matrix(new double[][]{
                {5,4,-7},
                {1,3,-1},
                {2,0,-3}
        });
        assertEquals(1.0 ,tmpM.determinant());


        tmpM = new Matrix(new double[][]{
                {10}
        });
        assertEquals(10.0 ,tmpM.determinant());


        tmpM= new Matrix(new double[][]{
                {1,3},
                {1,3}
        });
        assertEquals(0.0 ,tmpM.determinant());
    }
    @Test
    public void setMatrixValueTest(){
        IMatrix matrix = new Matrix(_testMatrix);

        matrix.setMatrixValue(0,0,4);
        assertEquals(4.0, matrix.getMatrixValue(0,0));

        matrix.setMatrixValue(1,0,14);
        assertEquals(14.0, matrix.getMatrixValue(1,0));
    }

    @Test
    public void getWidthTest(){

        IMatrix matrix = new Matrix(_testMatrix);

        assertEquals(4, matrix.getWidth());

    }
    @Test
    public void getHeightTest(){


        IMatrix matrix = new Matrix(_testMatrix);

        assertEquals(4, matrix.getHeight());


    }

}
