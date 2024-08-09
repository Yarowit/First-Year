// #include <iostream> 
#include "classes.hpp"
#include <cmath>

#define PI 3.14159265

Circle::Circle(double r){
    radius = r;
    name = "Circle";
}
double Circle::Area(){
    return PI * radius * radius;
}
double Circle::Perimeter(){
    return 2 * PI * radius;
}

Pentagon::Pentagon(double r){
    a = r;
    name = "Pentagon";
}
double Pentagon::Perimeter(){
    return 5 * a;
}
double Pentagon::Area(){
    return std::sqrt(25 + 10 * std::sqrt(5)) / 4 * a * a;
}

Hexagon::Hexagon(double r){
    a = r;
    name = "Hexagon";
}
double Hexagon::Perimeter(){
    return 6 * a;
}
double Hexagon::Area(){
    return 3 * std::sqrt(3) / 2 * a * a;
}

Square::Square(double side){
    a = side;
    name = "Square";
}
double Square::Perimeter(){
    return 4 * a;
}
double Square::Area(){
    return a * a;
}

Kite::Kite(double x, double alpha){
    a = x;
    angle = alpha * PI/180;
    name = "Kite";
}
double Kite::Perimeter(){
    return 4 * a;
}
double Kite::Area(){
    return a * a * sin(angle);
}

Rectangle::Rectangle(double x, double y){
    a = x;
    b = y;
    name = "Rectangle";
}
double Rectangle::Perimeter(){
    return 2 * a + 2 * b;
}
double Rectangle::Area(){
    return a * b;
}

Paralelogram::Paralelogram(double x, double y, double alpha){
    a = x;
    b = y;
    angle = alpha * PI/180;
    name = "Paralelogram";
}
double Paralelogram::Perimeter(){
    return 2 * a + 2 * b;
}
double Paralelogram::Area(){
    return a * b * sin(angle);
}