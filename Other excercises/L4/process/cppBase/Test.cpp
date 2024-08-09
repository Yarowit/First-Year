#include <iostream>
#include "header.hpp"

int main (int argc, char *argv[]){
    if (argc == 1)
        return 0;

    int n;
    try {
        n = std::stoi(argv[1]);
    }
    catch (std::invalid_argument) {
        std::cout<<argv[1]<<" nie jest liczba calkowita"<<std::endl;
        return 0;
    }
    
    if (n < 0 || n > WierszTrojkataPascala::maxRow){
        std::cout<<argv[1]<<" - niepoprawny numer wiersza"<<std::endl;
        return 0;
    }
    
    WierszTrojkataPascala row(n);
    
    for (int i = 2; i < argc; i++ ) {
        try{
            int k = std::stoi(argv[i]);

            std::cout<<k<<" - ";

            if (k >= 0 && k <= n)
                std::cout<< row.wspolczynnik(k) <<std::endl;
            else
                std::cout<<"Liczba spoza zakresu"<<std::endl;
        }
        catch (std::invalid_argument) {
            std::cout<<argv[i]<<" to nie liczba calkowita"<<std::endl;
        }
    }
    return 0;
}