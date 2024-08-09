#include <iostream>
#include <vector>
#include <cstring>
#include <algorithm>
#include "classes.hpp"

static Quad* quadParse(double arr[], int i);

int main (int argc, char *argv[]){
    if (argc == 1)
        return 0;
    if (argc == 2){
        std::cout<< "Brak parametrów" << std::endl;
        return 0;
    }
    
    int i = 0;
    double nums[argc-2];
    
    for (int j = 2; j < argc; j++){

        nums[j - 2] = std::atof(argv[j]);
        
    }
    
    std::vector< Figure* > Shapes;


    for (unsigned int j = 0; j < std::strlen(argv[1]); j++){
        char shape = argv[1][j];

        if (shape == 'o' || shape == 'p' || shape == 's'){
            if (i >= argc - 2){
                std::cout<< "kształt " << j << " - brak argumentów" << std::endl;
                i++;
                continue;
            }
            if(nums[i] <= 0){
                std::cout<<"kształt " << j << " : " <<argv[i+2] << " to niepoprawna długość" << std::endl;
                i++;
                continue;
            }
        }
        if (shape == 'c'){
            if(i + 4 >= argc - 2){
                std::cout<<"kształt " << j << " - brak argumentów" << std::endl;
                i += 5;
                continue;
            }

            bool wrongData = false;
            for(int k = 0; k < 4; k++){
                if(nums[i+k] <= 0){
                    std::cout<<"kształt " << j << " : " << argv[i+k+2] << " to niepoprawna długość" << std::endl;
                    wrongData = true;
                }
            }
            if (wrongData == true){
                i += 5;
                continue;
            }
            if(nums[i+4] <= 0 || nums[i+4] >= 180){
                std::cout<<"kształt " << j <<" : "<<argv[i+6]<< " to niepoprawny kąt"<<std::endl;
                i += 5;
                continue;
            }
        }

        switch(shape){
            case 'o':
                Shapes.push_back(new Circle(nums[i]));
                i++;
                break;
            case 'p':
                Shapes.push_back(new Pentagon(nums[i]));
                i++;
                break;
            case 's':
                Shapes.push_back(new Hexagon(nums[i]));
                i++;
                break;
            case 'c':{
                Quad* q = quadParse(nums, i);
                if (q != NULL)
                    Shapes.push_back(q);
                else
                    std::cout<<"Ksztalt "<< j << " : niepoprawne parametry czworokata"<<std::endl;
                i += 5;
                break;
            }               
            default:
                std::cout<<"Ksztalt "<< j << " nie jest poprawna figura"<<std::endl;                    
        }
    }

    for(Figure* f : Shapes){
        std::cout<< f->getName() << " " << f->Perimeter() << " " << f->Area() << std::endl;
        delete f;
    }
    return 0;
}
    
static Quad* quadParse(double arr[], int i){
    std::sort(arr+i, arr+i + 4);
    
    if(arr[i] == arr[i+1] && arr[i+2] == arr[i+3]){
        if(arr[i+1] == arr[i+2]){
            if(arr[i+4] == 90)
                return new Square(arr[i]);
            return new Kite(arr[i],arr[i+4]);
        }
        if(arr[i+4] == 90)
            return new Rectangle(arr[i],arr[i+2]);
        return new Paralelogram(arr[i], arr[i+2], arr[i+4]);
    }
    return NULL;
}