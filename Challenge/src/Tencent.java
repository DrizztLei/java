import java.util.Scanner;

public class Tencent
{
	public static void main(String[] args)
	{
		Scanner scanner = new Scanner(System.in);
		String number = scanner.nextLine();
		String info = scanner.nextLine();
		int num = Integer.parseInt(number);
		
		int[] status = new int[num];
		Scanner infoScanner = new Scanner(info);
		for(int i = 0; i < num; i++)
		{
			int temp = infoScanner.nextInt();
			status[i] = temp;
			// System.out.println(status[i]);
		}
		
		process(info);
	}

	private static void process(String info)
	{
		// TODO Auto-generated method stub
		
	}
}