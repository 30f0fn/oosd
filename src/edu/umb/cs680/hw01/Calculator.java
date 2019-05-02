package edu.umb.cs680.hw01;

public class Calculator {
    public static float multiply(float i, float j) {
        return i * j;
    } 
   public static float divide(float i, float j) throws IllegalArgumentException {
        if (j == 0) {
            throw new IllegalArgumentException("division by zero");
        }
        return i / j;
    }
    public static void main(String[] args) throws IllegalArgumentException {
        float x = 123456789;
        float y = 987654321;
        float z = 555555555;
        System.out.printf("Calculator: the result of dividing the product of %.0f and %.0f by the product of %.0f and %.0f is about %f.\n",
                          x, y, z, z,
                          Calculator.divide(Calculator.multiply(x, y),
                                             Calculator.multiply(z, z)));
    }
}

