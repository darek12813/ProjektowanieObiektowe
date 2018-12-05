package main.java;

public interface IMatrix
{
    /** Generuje macierz jednostkową o podarnym rozmiarze */
    void createIdentityMatrix(int size);

    /** Oblicza wartość wyznacznika dla kwadratowej macierzy, w przypadku innej mamy wyjątek */
    double determinant() throws InvalidDimensionException;

    /** Zwraca wartość pola w macierzy */
    double getMatrixValue(int row, int column);

    /** Ustala wartość pojedyńczej komórki */
    void setMatrixValue(int row, int column, double value);

    /** Ustala zawartość macierzy na podstawie tablicy */
    void setMatrixValues(double[][] values) throws InvalidDimensionException;
    double[][] getMatrix();

    int getWidth();
    int getHeight();

    /** Reprezentacja w formie String macierzy, powinna utrzymywać konwencję wierszy i kolumn */
    String toString();
}