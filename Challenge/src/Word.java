import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Word
{
	public static char[] finalResult;
	public static void ReverseWords(char[] inputChar)
	{
		String info = new String(inputChar);
		String input = info.trim();
		Scanner scanner = new Scanner(input);
		String retureValue = "";

		ArrayList<String> temp = new ArrayList<>();
		while (scanner.hasNext())
		{
			String token = scanner.next();
			// System.out.println(token);
			temp.add(token);
		}

		int length = temp.size();
		
		Collections.reverse(temp);

		for (int i = 0; i < length; i++)
		{
			int index = i;
			String token = temp.get(index);
			retureValue = retureValue + " " + token;
		}

		input = new String(retureValue.trim());
		finalResult = input.toCharArray();
		
		for(int i = 0; i < finalResult.length; i++)
		{
			inputChar[i] = finalResult[i];
			if(i == finalResult.length - 1)
			{
				if(i + 1 < inputChar.length)
				{
					inputChar[i + 1] = '\0';
				}
			}
		}
		// System.out.println(inputChar);
	}

	public static void main(String[] args)
	{
		Scanner scanner = new Scanner(System.in);
		char[] input = scanner.nextLine().toCharArray();
		ReverseWords(input);
		// System.out.println(input);
		System.out.println(finalResult);
	}
}
