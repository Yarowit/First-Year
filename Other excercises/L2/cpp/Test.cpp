#include <iostream>
#include "header.hpp"

int main (int argc, char *argv[]){
    int n;
    try {
        n = std::stoi(argv[1]);
    }
    catch (std::invalid_argument) {
        std::cout<<argv[1]<<" nie jest liczba calkowita"<<std::endl;
        return 0;
    }
    try{
    	WierszTrojkataPascala row(n);
    }
    catch(//tu zrobic catch dla tego wyjatku runtime error i pamietac o scope WTP
    for (int i = 2; i < argc; i++ ) {
        try{
            int k = std::stoi(argv[i]);

            std::cout<<k<<" - ";

            if (k >= 0 && k <= n)
                std::cout<< row.wspolczynnik(k) <<std::endl;
            else
                std::cout<<"Liczba spoza zakresu"<<std::endl;
        }
        catch (std::invalid_argument&) {
            std::cout<<argv[i]<<" nie jest liczba calkowita"<<std::endl;
        }
    }
    return 0;
}
