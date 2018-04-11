import java.util.Scanner;
import java.util.Stack;

public class LISP
{
	public static Stack<String> opStack = new Stack<String>();
	public static Stack<String> dataStack = new Stack<String>();

	public static void token(String info)
	{

	}

	public static void quote()
	{

	}

	public static void reverse()
	{

	}

	public static void search()
	{

	}

	public static void combine()
	{

	}

	public static String run(String input)
	{
		String result = input.trim();
		while (isTerminal(result))
		{
			result = result.trim();
			result = result.substring(1, result.length() - 1);
			result = result.trim();
			Scanner scanner = new Scanner(result);
			String op = scanner.next();
			
		}
		return null;
	}

	private static boolean isTerminal(String result)
	{

		return false;
	}

	public static void main(String[] args)
	{
		Scanner scanner = new Scanner(System.in);
		String info = scanner.nextLine();
		// String result = run(info);
		System.out.println(info.substring(1, info.length() - 1));
	}
}
