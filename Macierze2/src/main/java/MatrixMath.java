package main.java;

public class MatrixMath implements IMatrixMath {
    @Override
    public IMatrix inverseMatrix(IMatrix m1) {
        int length = m1.getHeight();
        double[][] aMatrix = new double[length][length];
        double[][] invertedMatrix = new double[length][length];
        int[] index = new int[length];

        for(int i=0; i < length; i++){
            aMatrix[i][i] = 1;
        }

        transformToUpperTriangle(m1.getMatrix(), index);

        for (int i = 0; i < (length - 1); ++i) {
            for (int j = (i + 1); j < length; ++j) {
                for (int k = 0; k < length; ++k) {
                    aMatrix[index[j]][k] -= m1.getMatrixValue(index[j],i) * aMatrix[index[i]][k];
                }
            }
        }

        for (int i = 0; i < length; ++i) {
            invertedMatrix[length - 1][i] = aMatrix[index[length - 1]][i] / m1.getMatrixValue(index[length - 1],length - 1);

            for (int j = (length - 2); j >= 0; --j) {
                invertedMatrix[j][i] = aMatrix[index[j]][i];

                for (int k = (j + 1); k < length; ++k) {
                    invertedMatrix[j][i] -= (m1.getMatrixValue(index[j],k)* invertedMatrix[k][i]);
                }

                invertedMatrix[j][i] /= (m1.getMatrixValue(index[j],j));
            }
        }

        return new Matrix(invertedMatrix);
    }

    private void transformToUpperTriangle (double[][] matrix, int[] index) {
        int mLength = matrix.length;

        double[] c = new double[mLength];
        double c0, c1, pi0, pi1, pj;
        int itmp, k;

        for (int i = 0; i < mLength; ++i) {
            index[i] = i;
        }

        for (int i = 0; i < mLength; ++i) {
            c1 = 0;

            for (int j = 0; j < mLength; ++j) {
                c0 = Math.abs (matrix[i][j]);

                if (c0 > c1) {
                    c1 = c0;
                }
            }

            c[i] = c1;
        }

        k = 0;

        for (int j = 0; j < (mLength - 1); ++j) {
            pi1 = 0;

            for (int i = j; i < mLength; ++i) {
                pi0 = Math.abs (matrix[index[i]][j]);
                pi0 /= c[index[i]];

                if (pi0 > pi1) {
                    pi1 = pi0;
                    k = i;
                }
            }

            itmp = index[j];
            index[j] = index[k];
            index[k] = itmp;

            for (int i = (j + 1); i < mLength; ++i) {
                pj = matrix[index[i]][j] / matrix[index[j]][j];
                matrix[index[i]][j] = pj;

                for (int l = (j + 1); l < mLength; ++l) {
                    matrix[index[i]][l] -= pj * matrix[index[j]][l];
                }
            }
        }
    }

    @Override
    public IMatrix matrixAddition(IMatrix m1, IMatrix m2) throws InvalidDimensionException {
        if(m1.getWidth() != m2.getWidth() ||
                m1.getHeight() != m2.getHeight()){
            throw new InvalidDimensionException("Niewłaściwe wymiary macierzy");
        }
        int w = m1.getWidth();
        int h = m1.getHeight();
        Matrix result = new Matrix(w,h);
        for(int i = 0; i<w; i++)
            for(int j = 0; j<h; j++){
                double tmp =  m1.getMatrixValue(i,j) + m2.getMatrixValue(i,j);
                result.setMatrixValue(i,j,tmp);
            }

        return result;

    }

    @Override
    public IMatrix matrixMultiplication(IMatrix m1, IMatrix m2) throws InvalidDimensionException {
        if(m1.getWidth() != m2.getHeight())
            throw new InvalidDimensionException("zle wymiary");

        int size = m1.getWidth();
        double[][] result = new double[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                for (int k = 0; k < size; k++) {
                    result[i][j] += (m1.getMatrixValue(i, k) * m2.getMatrixValue(k, j));
                }
            }
        }


        return new Matrix(result);

    }

    @Override
    public IMatrix matrixSubtracting(IMatrix m1, IMatrix m2) throws InvalidDimensionException {
        if(m1.getWidth() != m2.getWidth() ||
                m1.getHeight() != m2.getHeight()){
            throw new InvalidDimensionException("Niewłaściwe wymiary macierzy");
        }
        int w = m1.getWidth();
        int h = m1.getHeight();
        Matrix result = new Matrix(w,h);
        for(int i = 0; i<w; i++)
            for(int j = 0; j<h; j++){
                double tmp =  m1.getMatrixValue(i,j) - m2.getMatrixValue(i,j);
                result.setMatrixValue(i,j,tmp);
            }

        return result;
    }

    @Override
    public IMatrix matrixTransposition(IMatrix m1) throws InvalidDimensionException {
        Matrix result = new Matrix(m1.getHeight(),m1.getWidth());
        for (int i = 0; i < m1.getHeight(); i++) {
            for (int j = 0; j < m1.getWidth(); j++) {
                result.setMatrixValue(i,j,m1.getMatrixValue(j,i));
            }
        }
        return result;
    }

    @Override
    public IMatrix scalarMultiplication(IMatrix m1, double scalar) {
        Matrix result = new Matrix(m1.getWidth(),m1.getHeight());
        for (int i = 0; i < m1.getWidth(); i++) {
            for (int j = 0; j < m1.getHeight(); j++) {
                result.setMatrixValue(i,j,m1.getMatrixValue(i, j) * scalar);
            }
        }
        return  result;
    }
}
