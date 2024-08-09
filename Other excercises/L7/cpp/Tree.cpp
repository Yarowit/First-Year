#include <iostream> 
#include "header.hpp"

template<typename T>
void Tree<T> :: insert(T value){
    if(this -> root ==  NULL){
        this -> root = new Node<T>(value, NULL);
        return;
    }

    Node<T> *node = this -> root;

    while(true){
        if(value < node -> value){
            if(node -> left !=  NULL){
                node = node -> left;
            }else{
                node -> left = new Node<T>(value, node);
                node -> left -> parent = node;
                return;
            }
        }else{
            if(node -> right !=  NULL){
                node = node -> right;
            }else{
                node -> right = new Node<T>(value, node);
                return;
            }
        }
    }
    // this->left = new Node<T>(val);
}

template<typename T>
bool Tree<T> :: search(T value){
    if(this -> root ==  NULL)
        return false;

    Node<T> *node = this -> root;
    
    while(node !=  NULL){
        if(node -> value == value)
            return true;
            
        if(value < node -> value)
            node = node -> left;
        else
            node = node -> right;
    }
    return false;
}

    // public String inOrder(){
    //     if(this.root ==  NULL)
    //         return "--";
    //     return root.inOrderGenerator();
    // }

template<typename T>
std::string Tree<T> :: draw(){
    if(this -> root ==  NULL)
        return "--";
    return this -> root -> draw("");
}

template<typename T>
void Tree<T> :: remove(T value){
    Node<T> *node = this -> root;
    if(node ==  NULL)
        return;

    // Znajdowanie node'a
    while(node -> value != value){
        if(value < node -> value)
            node = node -> left;
        else
            node = node -> right;

        if(node ==  NULL)
            return;
    }

    if(node -> parent ==  NULL)
        if(this -> root -> left ==  NULL){
            if(this -> root -> right ==  NULL){
                this -> root =  NULL;
                return;
            }
            this -> root = this -> root -> right;
            this -> root -> parent =  NULL;
            return;
        }else
            this -> root = this -> root -> left;
    else if(value < node -> parent -> value){
        if(node -> left ==  NULL){
            node -> parent -> left = node -> right;
            if(node -> right !=  NULL)
                node -> right -> parent = node -> parent;
            return;
        }
        node -> parent -> left = node -> left;
    }else{
        if(node -> left ==  NULL){
            node -> parent -> right = node -> right;
            if(node -> right !=  NULL)
                node -> right -> parent = node -> parent;
            return;
        }
        node -> parent -> right = node -> left;
    }

    node -> left -> parent = node -> parent;
    
    Node<T> *rightRoot = node -> right;

    node = node -> left;

    while(node -> right !=  NULL)
        node = node -> right;
    
    node -> right = rightRoot;
    if(rightRoot !=  NULL)
        rightRoot -> parent = node;
}


template class Tree<int>;
// template class Tree<double>;
// template class Tree<std::string>;