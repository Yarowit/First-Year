#include <iostream>
#include "header.hpp"

int main (int argc, char *argv[]){
    int n;

    Tree<int> *tree = new Tree<int>;
    tree -> insert(9);
    tree -> insert(7);
    tree -> insert(2);
    tree -> insert(3);
    tree -> insert(1);
    std::cout<<tree -> draw()<<std::endl;

    delete tree;
    std::cout<< "end" <<std::endl;
}
