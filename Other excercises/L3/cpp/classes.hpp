#ifndef CLASSES_HPP
#define CLASSES_HPP
#include <iostream> 

class Figure{
    protected:
        std::string name;
    public:
        std::string getName(){
            return name;
        }
        virtual double Perimeter() = 0;
        virtual double Area() = 0;
        virtual ~Figure() = default;
};

class Quad : public Figure{
    public:
        virtual ~Quad() = default;
};

class Circle : public Figure{
    private:
        double radius;
    public:
        Circle(double r);
        double Perimeter();
        double Area();
        virtual ~Circle() = default;
};

class Pentagon : public Figure{
    private:
        double a;
    public:
        Pentagon(double a);
        double Perimeter();
        double Area();
        virtual ~Pentagon() = default;
};

class Hexagon : public Figure{
    private:
        double a;
    public:
        Hexagon (double a);
        double Perimeter();
        double Area();
        virtual ~Hexagon() = default;
};


class Square : public Quad{
    private:
        double a;
    public:
        Square(double a);
        double Perimeter();
        double Area();
        virtual ~Square() = default;
};

class Rectangle : public Quad{
    private:
        double a, b;
    public:
        Rectangle(double a, double b);
        double Perimeter();
        double Area();
        virtual ~Rectangle() = default;
};

class Paralelogram : public Quad{
    private:
        double a, b, angle;
    public:
        Paralelogram(double a,double b, double angle);
        double Perimeter();
        double Area();
        virtual ~Paralelogram() = default;
};

class Kite : public Quad{
    private:
        double a, angle;
    public:
        Kite(double a, double angle);
        double Perimeter();
        double Area();
        virtual ~Kite() = default;
};

#endif