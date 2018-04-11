import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Main
{

	/** 请完成下面这个函数，实现题目要求的功能 **/
	/** 当然，你也可以不按照这个模板来作答，完全按照自己的想法来 ^-^ **/
	static String bike_plan(double distance, double speed)
	{
		double time = distance / speed;

		double moneyHelloBike = computeHelloBick(distance);

		double moneyYAX = computeYAX(time);

		double moneyOFO = computeOFO(distance);

		String answer = "";
		answer += "骑行距离" + distance + "(千米)，匀速骑行速度" + speed + "(千米/分钟)最省钱方案:\n";

		boolean[] result = new boolean[3];

		for (int i = 0; i < result.length; i++)
		{
			result[i] = false;
		}

		int compareResult = Double.compare(moneyHelloBike, moneyYAX);
		if (compareResult == 0)
		{
			result[0] = true;
			result[1] = true;

			int finalResult = Double.compare(moneyHelloBike, moneyOFO);
			if (finalResult > 0)
			{
				result[0] = false;
				result[1] = false;
				result[2] = true;
			} else if (finalResult == 0)
			{
				result[2] = true;
			} else
			{

			}
		}

		if (compareResult > 0)
		{
			result[1] = true;
			int finalResult = Double.compare(moneyYAX, moneyOFO);
			if (finalResult == 0)
			{
				result[2] = true;
			} else if (finalResult > 0)
			{
				result[1] = false;
				result[2] = true;
			} else
			{

			}
		}

		if (compareResult < 0)
		{
			result[0] = true;
			int finalResult = Double.compare(moneyHelloBike, moneyOFO);
			if (finalResult == 0)
			{
				result[2] = true;
			} else if (finalResult > 0)
			{
				result[0] = false;
				result[2] = true;
			} else
			{

			}
		}

		boolean flag = false;

		if (result[0] == true)
		{
			answer += "hellobike" + moneyHelloBike + "（元）";
			flag = true;
		}
		if (result[1] == true)
		{
			String and = "";
			if (flag)
			{
				and = "和";
			}
			answer += and + "永安行" + moneyYAX + "（元）";
			flag = true;
		}
		if (result[2] == true)
		{
			String and = "";
			if (flag)
			{
				and = "和";
			}
			answer += and + "OFO小黄车" + moneyOFO + "（元）";
		}
		return answer;
	}

	private static double computeOFO(double distance)
	{
		double fixDistance = Math.ceil(distance);
		return fixDistance * 1.5;
	}

	private static double computeYAX(double time)
	{
		double fixTime = Math.ceil(time);
		return time * 0.2;
	}

	private static double computeHelloBick(double distance)
	{
		if (distance <= 2)
		{
			return 1;
		} else if (distance <= 4)
		{
			return 3;
		} else if (distance <= 8)
		{
			return 5;
		} else
		{
			return 8;
		}
	}

	public static void main(String[] args)
	{
		Scanner in = new Scanner(System.in);
		String res;

		double _distance;
		_distance = Double.parseDouble(in.nextLine().trim());

		double _speed;
		_speed = Double.parseDouble(in.nextLine().trim());

		res = bike_plan(_distance, _speed);
		System.out.println(res);
	}
}