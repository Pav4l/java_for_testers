package ru.stqa.geometry.figures;
import java.lang.Math;
import java.util.Objects;

public record Triangle (double a, double b, double c) {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Triangle triangle = (Triangle) o;
        return Double.compare(this.a, triangle.a) == 0 && Double.compare(this.b, triangle.b) == 0 && Double.compare(this.c, triangle.c) == 0
                || Double.compare(this.b, triangle.a) == 0 && Double.compare(this.a, triangle.b) == 0 && Double.compare(this.c, triangle.c) == 0
                || Double.compare(this.b, triangle.a) == 0 && Double.compare(this.c, triangle.b) == 0 && Double.compare(this.a, triangle.c) == 0
                || Double.compare(this.c, triangle.a) == 0 && Double.compare(this.a, triangle.b) == 0 && Double.compare(this.b, triangle.c) == 0
                || Double.compare(this.c, triangle.a) == 0 && Double.compare(this.b, triangle.b) == 0 && Double.compare(this.a, triangle.c) == 0
                || Double.compare(this.a, triangle.a) == 0 && Double.compare(this.c, triangle.b) == 0 && Double.compare(this.b, triangle.c) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b, c);
    }

    public Triangle {
        if (a < 0 || b < 0 || c < 0){
            throw new IllegalArgumentException("Triangle side should be non-negative");
        }
        if ((a + b) < c || (a + c) < b || (b + c) < a){
            throw new IllegalArgumentException("Triangle doesn't exist");
        }
    }
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
