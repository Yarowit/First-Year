#ifndef HEADER_HPP
#define HEADER_HPP

class WierszTrojkataPascala {
    private:
        unsigned int* row;
    public:
        static const int maxRow = 33;//constexpr
        WierszTrojkataPascala (int n) noexcept(false);
        unsigned int wspolczynnik (int n);///tu też napisać noexcept i dodac const?
        virtual ~WierszTrojkataPascala() {delete[] row;}
};
#endif
