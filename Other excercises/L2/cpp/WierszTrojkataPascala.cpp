#include <iostream> 
#include "header.hpp"

class WrongRow : public std::runtime_error{
    public:
        WrongRow (const std::string & msg) noexcept(true) : std::runtime_error(msg.c_str()){
        }
};
WierszTrojkataPascala::WierszTrojkataPascala(int n) noexcept(false) {
    if (n < 0 || n > maxRow)
        throw WrongRow(std::to_string(n) + 
                       std::string(" - Niepoprawny numer wiersza"));

    unsigned int un = static_cast<unsigned int>(n);
    row = new unsigned int[un + 1];

    row[0] = 1;

    if (un == 0)
        return;

    row[un] = 1;

    if (un == 1)
        return;
        
    row[1] = un;
    row[un-1] = un;

    if (un < 4)
        return;   
//to można skrocic

    long val = static_cast<long>(un);

    for (long i = 2; i < (un + 3) / 2; i++) {
        val = (static_cast<long>(un) - i + 1) * val / i;
        
        row[i] = static_cast<unsigned int>(val);
        row[un-i] = static_cast<unsigned int>(val);
    }
}

unsigned int::WierszTrojkataPascala::wspolczynnik (int n) {
    return row[static_cast<unsigned int>(n)];
}
