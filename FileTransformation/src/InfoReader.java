import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class InfoReader
{
	public static String inputFile = "./out.txt";

	public static void main(String[] args) throws FileNotFoundException
	{
		Scanner scanner = new Scanner(new File(inputFile));
		String[] name = new String[100];
		int[] cid = new int[100];
		int[] sid = new int[100];
		int count = 0;

		scanner.nextLine();
		while (scanner.hasNextLine())
		{

			String info = scanner.nextLine();
			// System.out.println(info);
			String[] all = info.split(",");

			name[count] = all[0];
			cid[count] = Integer.parseInt(all[1]);
			sid[count] = Integer.parseInt(all[2]);
			System.out.println(name[count]);
			count++;
		}
	}
}
