import main.java.*;

import static junit.framework.TestCase.assertEquals;

import org.junit.Test;


public class MatrixMathTests {
    private IMatrixMath _matrixMath = new MatrixMath();
    IMatrix _testMatrix1 = new Matrix(new double[][]{
            {1,2,3,1},
            {1,2,3,1},
            {1,2,3,1},
            {1,2,3,1}
    });
    IMatrix _testMatrix2 = new Matrix(new double[][]{
            {0,2,1,1},
            {0,2,1,1},
            {0,2,1,1},
            {0,2,1,1}
    });

    @Test
    public void	inverseMatrixTest(){
        IMatrix tmpM = new Matrix(new double[][]{
                {1,2,3},
                {0,1,4},
                {5,6,0}
        });
        double[][] result = new double[][]{
                {-24,18,5},
                {20,-15,-4},
                {-5,4,1}
        };
        IMatrix m = _matrixMath.inverseMatrix(tmpM);

        MatrixCompare(result,m,result.length);

    }

    @Test
    public void	matrixAdditionTest() throws InvalidDimensionException{
        double[][] result = new double[][]{
                {1,4,4,2},
                {1,4,4,2},
                {1,4,4,2},
                {1,4,4,2}
        };
        IMatrix m = _matrixMath.matrixAddition(_testMatrix1,_testMatrix2);

        MatrixCompare(result,m,result.length);
    }

    @Test
    public void	matrixMultiplicationTest() throws InvalidDimensionException{
        double[][] result = new double[][]{
                {0,14,7,7},
                {0,14,7,7},
                {0,14,7,7},
                {0,14,7,7}
        };

        IMatrix m = _matrixMath.matrixMultiplication(_testMatrix1,_testMatrix2);
        MatrixCompare(result,m,result.length);
    }

    @Test
    public void	matrixSubtractingTest() throws InvalidDimensionException{
        double[][] result = new double[][]{
                {1,0,2,0},
                {1,0,2,0},
                {1,0,2,0},
                {1,0,2,0}
        };

        IMatrix m = _matrixMath.matrixSubtracting(_testMatrix1,_testMatrix2);
        MatrixCompare(result,m,result.length);
    }

    @Test
    public void	matrixTranspositionTest() throws InvalidDimensionException{
        double[][] M1T = new double[][]{
                {1,1,1,1},
                {2,2,2,2},
                {3,3,3,3},
                {1,1,1,1}
        };
        double[][] M2T = new double[][]{
                {0,0,0,0},
                {2,2,2,2},
                {1,1,1,1},
                {1,1,1,1}
        };
        IMatrix m1 = _matrixMath.matrixTransposition(_testMatrix1);
        MatrixCompare(M1T,m1,M1T.length);
        IMatrix m2 = _matrixMath.matrixTransposition(_testMatrix2);
        MatrixCompare(M2T,m2,M2T.length);


    }

    @Test
    public void	scalarMultiplicationTest(){
        double[][] M1S = new double[][]{
                {2,4,6,2},
                {2,4,6,2},
                {2,4,6,2},
                {2,4,6,2}
        };
        double[][] M2S = new double[][]{
                {0,6,3,3},
                {0,6,3,3},
                {0,6,3,3},
                {0,6,3,3}
        };
        IMatrix m1 = _matrixMath.scalarMultiplication(_testMatrix1,2);
        MatrixCompare(M1S,m1,M1S.length);
        IMatrix m2 = _matrixMath.scalarMultiplication(_testMatrix2,3);
        MatrixCompare(M2S,m2,M2S.length);

    }

    private void MatrixCompare(double[][]m1, IMatrix m2, int size){
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                assertEquals(m1[i][j],(double)Math.round(m2.getMatrixValue(i,j)*100)/100);
            }
        }
    }

}
