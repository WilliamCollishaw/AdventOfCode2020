package day1;

import com.google.common.base.Charsets;
import com.google.common.io.CharSink;
import com.google.common.io.FileWriteMode;
import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		//Read input file
		File input = new File(Main.class.getResource("input.txt").getFile());

		//Read output file
		File result = new File(Main.class.getResource("result.txt").getFile());

		//Convert input file to String
		String inputStr = Files.asCharSource(input, Charsets.UTF_8).read();

		//Split String into array of Strings
		String[] strArray = inputStr.split("\\r?\\n");

		//Create int array from String
		int[] intArray = new int[strArray.length];
		for(int i = 0; i < strArray.length; i++) {
			intArray[i] = Integer.parseInt(strArray[i]);
		}

		//Part 1

		//Once we found the pair, this will be set to true so we can break out of all loops
		boolean foundPair = false;
		for (int i : intArray) {
			for (int j : intArray) {
				if (i + j == 2020) {
					CharSink chs = Files.asCharSink(result, Charsets.UTF_8);
					chs.write("Pair found!\n"
							+ i + " + " + j + " = 2020\n"
							+ i + " * " + j + " = " + i * j + "\n\n");
					//No need to continue once pair is found
					foundPair = true;
					break;
				}
				if (foundPair) {
					break;
				}
			}
		}

		//Part 2
		//Reset foundPair value
		foundPair = false;
		for (int i : intArray) {
			for (int j : intArray) {
				//Calculate this here so it is not recalculated in every iteration of the next loop
				int placeholder = i+j;
				for (int k : intArray) {
					if (placeholder + k == 2020) {
						CharSink chs = Files.asCharSink(result, Charsets.UTF_8, FileWriteMode.APPEND);
						chs.write("Trio found!\n"
								+ i + " + " + j + " + " + k + " = 2020\n"
								+ i + " * " + j + " * " + k + " = " + i * j * k);
						//No need to continue once pair is found
						foundPair = true;
						break;
					}
				}
				if (foundPair) {
					break;
				}
			}
			if (foundPair) {
				break;
			}
		}
	}
}
