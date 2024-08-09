#ifndef HEADER_HPP
#define HEADER_HPP

class WierszTrojkataPascala {
    private:
        unsigned int* row;
    public:
        static const int maxRow = 33;
        WierszTrojkataPascala (int n) noexcept(true);
        unsigned int wspolczynnik (int n);
        ~WierszTrojkataPascala() {delete[] row;}
};
#endif