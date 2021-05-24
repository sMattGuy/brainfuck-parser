import java.io.*;
import java.util.Stack;
import java.util.Scanner;
import java.util.ArrayList;

public class BrainFreakInterp{
	public static void main(String args[]) throws FileNotFoundException, IOException{
		if(args.length != 1){
			System.out.println("Expected input file.\nUsage: java BrainFreakInterp input.txt");
			return;
		}
		//turns file into buffered reader
		File input = new File(args[0]);
		BufferedReader br = new BufferedReader(new FileReader(input));
		//turn text file into datastructure
		ArrayList<Integer> program = new ArrayList<Integer>();
		createProgram(br, program);
		//evaluates program
		parseProgram(br,program);	
		return;
	}
	//program to turn text file into usable internal structure
	static void createProgram(BufferedReader br, ArrayList<Integer> program) throws IOException{
		int token = 0;
		while(token != -1){
			token = br.read();
			//>
			if(token == 62){
				program.add(token);
			}
			//<
			else if(token == 60){
				program.add(token);
			}
			//+
			else if(token == 43){
				program.add(token);
			}
			//-
			else if(token == 45){
				program.add(token);
			}
			//[
			else if(token == 91){
				program.add(token);
			}
			//]
			else if(token == 93){
				program.add(token);
			}
			//,
			else if(token == 44){
				program.add(token);
			}
			//.
			else if(token == 46){
				program.add(token);
			}
			else{
				continue;
			}
		}
		return;
	}
	//parse function
	static void parseProgram(BufferedReader br, ArrayList<Integer> program){
		int index = 0;
		int tape[] = new int[3000];
		Stack<Integer> stack = new Stack<Integer>();
		Scanner scan = new Scanner(System.in);
		for(int stringIndex = 0;stringIndex < program.size(); stringIndex++){
			//>
			if(program.get(stringIndex) == 62){
				index++;
				if(index >= 3000){
					index = 0;
				}
			}
			//<
			else if(program.get(stringIndex) == 60){
				index--;
				if(index < 0){
					index = 2999;
				}
			}
			//+
			else if(program.get(stringIndex) == 43){
				tape[index]++;
				if(tape[index] > 255){
					tape[index] = 0;
				}
			}
			//-
			else if(program.get(stringIndex) == 45){
				tape[index]--;
				if(tape[index] < 0){
					tape[index] = 255;
				}
			}
			//[
			else if(program.get(stringIndex) == 91){
				stack.push(stringIndex);
			}
			//]
			else if(program.get(stringIndex) == 93){
				if(tape[index] != 0){
					stringIndex = stack.peek();
				}
				else{
					stack.pop();
				}
			}
			//,
			else if(program.get(stringIndex) == 44){
				System.out.print("\ninput:");
				tape[index] = scan.next().charAt(0);
			}
			//.
			else if(program.get(stringIndex) == 46){
				System.out.print((char)tape[index]);
			}
		}
		System.out.print("\nProgram Execution Finished\n");
		return;
	}
}