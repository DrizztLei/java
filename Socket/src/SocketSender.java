import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketSender
{
	public static void main(String[] args) throws IOException
	{
		try
		{
			Socket socket = new Socket("127.0.0.1", 8888);
			socket.setOOBInline(true);
			System.out.println("Sender started.");
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			PrintWriter write = new PrintWriter(socket.getOutputStream());

			while (true)
			{
				String sendInfo = br.readLine(); //readline.equals("end"));
				System.out.println("Sender send info : " + sendInfo);
				if(sendInfo.hashCode() == "quit".hashCode())
				{
					break;
				}
				write.println(sendInfo);
				write.flush();
				// readline = br.readLine();
			}
			write.close();
			socket.close();
		} catch (Exception e)
		{
			System.out.println("can not listen to:" + e);
		}
	}

}