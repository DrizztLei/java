package scagnostics;

import java.util.ArrayList;

public class Test
{
	public ArrayList<String> array = new ArrayList<>();
	public int a = 'a';
	public int b = 'b';
	public String xx = "temp";
	public Node node = new Node();

	public static void change(Test x, Test y)
	{
		Test temp = new Test();
		temp.a = 213456;
		x.xx = "hahaha";
		x.a = 2222;
		x = temp;
		x.node.x = 2134;
	}

	public static void addThat(ArrayList<String> array)
	{
		array.set(0, "out");
	}

	public static void create(Test a)
	{
		a = new Test();
	}

	public static void main(String[] args)
	{
		Test x = new Test();
		x.a = 1;
		x.b = 1;
		Test y = new Test();

		change(x, y);
		System.out.println(x.a);
		System.out.println(x.xx);
		System.out.println(x.node.x);
		x.array.add("temp");
		addThat(x.array);
		System.out.println(x.array.get(0));
		x = null;
		create(x);
		System.out.println(x);
		double ax[] = new double[10];
		double ay[] = ax;
		ay[4] = 32456;
		System.out.println(ax[4]);
	}
}
