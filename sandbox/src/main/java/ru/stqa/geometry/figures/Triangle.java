package ru.stqa.geometry.figures;
import java.lang.Math;

public record Triangle (double a, double b, double c) {
    public static void printTrianglePerimeter(Triangle t) {
        String text = String.format("Периметр треугольника со сторонами %f, %f и %f= %f", t.a, t.b, t.c, t.perimeter());
        System.out.println(text);
    }
    public static void printTriangleArea(Triangle t) {
        String text = String.format("Площадь треугольника со сторонами %f, %f и %f= %f", t.a, t.b, t.c, t.area());
        System.out.println(text);
    }

    public double perimeter() {
        return this.a + this.b + this.c;
    }
    public double area() {
        double p = perimeter() / 2;
        return Math.sqrt(p * (p - this.a) * (p - this.b ) * (p - this.c));
    }
}
