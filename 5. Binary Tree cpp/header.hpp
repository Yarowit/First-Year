#ifndef HEADER_HPP
#define HEADER_HPP

#include <iostream>
template<typename T>
class Tree {
    private:
        template<typename t>
        class Node{
            public:
                t value;
                Node<T> *left = NULL, *right = NULL, *parent;
            
                std::string draw(std::string spacing){
                    std::string s = "";
                    if(this->right !=  NULL)
                        s += this -> right -> draw(spacing + "      ");

                    s += spacing + std::to_string(this->value) + " --{\n";

                    if(this->left !=  NULL)
                        s += this -> left -> draw( spacing + "      ");
                        
                    return s;
                };
                Node(t value, Node<T> *parent){
                    this->value = value;
                    this->parent = parent;
                }
                ~Node() {
                    if(left)
                        delete left;
                    if(right)
                        delete right;
                }
        };
        Node<T> *root;
    public:
        void insert(T val);
        bool search(T val);
        void remove(T val);
        std::string draw();
        
        ~Tree(){
            if(root)
                delete this -> root;
        }
};
#endif
