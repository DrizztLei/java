import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketReceiver
{
	public static void main(String[] args) throws IOException
	{
		try
		{
			Socket socket = new Socket("127.0.0.1", 8889);
			socket.setOOBInline(true);
			System.out.println("Receiver started.");
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			while (true)
			{
				String receiverInfo = in.readLine();
				receiverInfo = receiverInfo.substring(1);
				System.out.println("Receiver get info : " + receiverInfo);
				if("end".hashCode() == receiverInfo.hashCode())
				{
					in.close();
					socket.close();
					break;
				}
			}
			// System.out.println(socket.isClosed());
		} catch (Exception e)
		{
			System.out.println("can not listen to : " + e);// 出错，打印出错信息
		}
	}

}