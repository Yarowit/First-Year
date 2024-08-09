#include <iostream> 
#include "header.hpp"

WierszTrojkataPascala::WierszTrojkataPascala(int n) noexcept(true) {
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

    unsigned int val = un;

    for (unsigned int i = 2; i < (un + 3) / 2; i++) {
        val = (un - i + 1) * val / i;
        
        row[i] = static_cast<unsigned int>(val);
        row[un-i] = static_cast<unsigned int>(val);
    }
}

unsigned int::WierszTrojkataPascala::wspolczynnik (int n) {
    return row[static_cast<unsigned int>(n)];
}