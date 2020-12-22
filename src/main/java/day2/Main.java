package day2;

import com.google.common.base.Charsets;
import com.google.common.io.CharSink;
import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		//Read input file
		File input = new File(day2.Main.class.getResource("input.txt").getFile());

		//Read output file
		File result = new File(day2.Main.class.getResource("result.txt").getFile());

		//Convert input file to String
		String inputStr = Files.asCharSource(input, Charsets.UTF_8).read();

		//Split String into array of Strings
		String[] strArray = inputStr.split("\n");


		//Part 1
		int validPasswordCount1 = 0;
		int validPasswordCount2 = 0;

		for (String s : strArray) {
			int minChars = -1;
			int maxChars = -1;
			int dashChar = -1;
			char ruleChar = ' ';
			String password = "";

			//Starts loop at second character because we know the first char will always be a number
			for (int i = 1; i < s.length(); i++) {
				char c = s.charAt(i);
				if (minChars == -1) {
					if (c == '-') {
						minChars = Integer.parseInt(s.substring(0, i));
						dashChar = i;
					}
					//Continue to next iteration
					continue;
				}
				if (c == ' ') {
					maxChars = Integer.parseInt(s.substring(dashChar + 1, i));

					//At this point the remaining chars can be predicted so the remainder of the loop can be skipped
					ruleChar = s.charAt(i + 1);

					//Next two characters are always ': ' - so skip them and get the full password string
					password = s.substring(i + 4);

					//break out of this loop to
					break;
				}
			}

			//Part 1
			int charCount = 0;
			for (char c : password.toCharArray()) {
				if (c == ruleChar) {
					charCount++;
				}
			}
			if (charCount >= minChars && charCount <= maxChars) {
				validPasswordCount1++;
			}

			//Part 2
			if (password.charAt(minChars-1) == ruleChar ^ password.charAt(maxChars-1) == ruleChar) {
				validPasswordCount2++;
			}
		}
		CharSink chs = Files.asCharSink(result, Charsets.UTF_8);
		chs.write("Part one!\n"
				+ validPasswordCount1 + " valid passwords.\n"
		        + "Part two!\n"
				+ validPasswordCount2 + " valid passwords.");
	}
}
