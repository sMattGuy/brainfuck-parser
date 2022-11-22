#include <iostream>
#include <fstream>
#include <stack>

int main(int argc, char** argv){
  if(argc != 3){
    std::cout<<"You must have 1 input file and memory size!\n";
    return -1;
  }
  std::ifstream input(argv[1]);
  if(!input.good()){
    std::cout<<"Your input file is invalid!\n";
    return -1;
  }
  
  std::stack<int> memoryStack;
  int memorySize = std::atoi(argv[2]);
  char memoryCells[memorySize] = {0};
  int memoryPointer = 0;
  
  char command;
  std::string commands = "";
  while(input.good()){
    input.get(command);
    switch(command){
      case '>':
        commands += command;
      break;
      case '<':
        commands += command;
      break;
      case '+':
        commands += command;
      break;
      case '-':
        commands += command;
      break;
      case '.':
        commands += command;
      break;
      case ',':
        commands += command;
      break;
      case '[':
        commands += command;
      break;
      case ']':
        commands += command;
      break;
      default:
      continue;
    }
  }
  input.close();
  
  for(int i=0;i<commands.length();i++){
    command = commands[i]; 
    switch(command){
      case '>':
        memoryPointer++;
        if(memoryPointer > memorySize){
          memoryPointer = 0;
        }
      break;
      
      case '<':
        memoryPointer--;
        if(memoryPointer < 0){
          memoryPointer = memorySize;
        }
      break;
      
      case '+':
        memoryCells[memoryPointer]++;
      break;
      
      case '-':
        memoryCells[memoryPointer]--;
      break;
      
      case '.':
        std::cout<<memoryCells[memoryPointer];
      break;
      
      case ',':
        char inputChar;
        std::cout<<"Input Character: ";
        std::cin>>inputChar;
        std::cout<<std::endl;
        memoryCells[memoryPointer] = inputChar;
      break;
      
      case '[':
        memoryStack.push(i);
      break;
      
      case ']':
        if(memoryCells[memoryPointer] != 0){
          i = memoryStack.top();
        }
        else{
          memoryStack.pop();
        }
      break;
      default:
      continue;
    }
  }
  return 0;
}
