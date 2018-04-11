import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;// big()mac os       little(intel/sun) win/linux

public class Trans 
{
	public static String endResult = "username,cardID,studentID\n";
	public static String inputFile = "/home/elvis/test.txt";
	public static String outFile = "/home/elvis/out.txt";

	public static void main(String[] args) throws IOException
	{
		readFileByBytes(inputFile);
		writeInFile(endResult, outFile);
	}

	public static void readFileByBytes(String fileName)
	{

		InputStream in = null;
		try
		{
			// 一次读多个字节
			byte[] name = new byte[31];
			byte[] cardID = new byte[4];
			byte[] stuID = new byte[4];
			in = new FileInputStream(fileName);

			int times = in.available() / 39;
			int count = 0;
			while (count++ < times)
			{
				in.read(name);
				in.read(cardID);
				in.read(stuID);

				String username = new String(name);// byte[] to String
				username = username.trim();
				// System.out.println(username.length());
				int cardIdResult = ByteBuffer.wrap(cardID).order(ByteOrder.BIG_ENDIAN).getInt();
				int stuIdResult = ByteBuffer.wrap(stuID).order(ByteOrder.BIG_ENDIAN).getInt();
				/*
				 * System.out.println("username : " + username);
				 * System.out.println("card id : " + cardIdResult);
				 * System.out.println("stu id : " + stuIdResult);
				 * 
				 */
				endResult += username + "," + cardIdResult + "," + stuIdResult + "\n";
			}
		}

		catch (Exception e1)
		{
			e1.printStackTrace();
		} finally
		{
			if (in != null)
			{
				try
				{
					in.close();
				} catch (IOException e1)
				{
				}
			}
		}
	}

	public static void writeInFile(String info, String outFile) throws IOException
	{
		File file = new File(outFile);
		if (!file.exists())
		{
			if (!file.exists())
			{
				try
				{
					file.createNewFile();
				} catch (IOException e)
				{
					e.printStackTrace();
				}
			}

			FileWriter fw = new FileWriter(file);
			fw.write(info);
			fw.close();

			try
			{
				file.createNewFile();
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}

		FileWriter fw = new FileWriter(file);
		fw.write(info);
		fw.close();

	}

}
