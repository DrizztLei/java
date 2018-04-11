import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Skilly
{

	public static Set A = new HashSet();
	public static Set B = new HashSet();
	public static Set C = new HashSet();

	static String[] fsd_team(String[] staff_info)
	{
		for (int i = 0; i < staff_info.length; i++)
		{
			String info = staff_info[i];
			// System.out.println(info);
			info = info.replaceAll(",", " ");
			String[] result = info.split(" ");
			int id = Integer.parseInt(result[0]);
			String skilly = result[1];

			System.out.println(id);
			System.out.println(skilly);

			if (skilly.hashCode() == "web".hashCode())
			{
				A.add(id);
			} else if (skilly.hashCode() == "java".hashCode())
			{
				B.add(id);
			} else
			{
				C.add(id);
			}
		}

		int aSize = A.size();
		int bSize = B.size();
		int cSize = C.size();

		int[] usedA = new int[aSize];
		int[] usedB = new int[bSize];
		int[] usedC = new int[cSize];

		return null;
	}

	public static void main(String[] args)
	{
		Scanner in = new Scanner(System.in);
		String[] res;

		int _staff_info_size = 0;
		_staff_info_size = Integer.parseInt(in.nextLine().trim());
		String[] _staff_info = new String[_staff_info_size];
		String _staff_info_item;
		for (int _staff_info_i = 0; _staff_info_i < _staff_info_size; _staff_info_i++)
		{
			try
			{
				_staff_info_item = in.nextLine();
			} catch (Exception e)
			{
				_staff_info_item = null;
			}
			_staff_info[_staff_info_i] = _staff_info_item;
		}

		res = fsd_team(_staff_info);
		if (res != null)
		{
			for (int res_i = 0; res_i < res.length; res_i++)
			{
				System.out.println(String.valueOf(res[res_i]));
			}
		}
	}
}