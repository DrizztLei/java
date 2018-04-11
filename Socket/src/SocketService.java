import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Semaphore;

public class SocketService
{

	public static ServerSocket senderServer = null, receiverServer = null;
	public static String info;
	public static Semaphore semaphore = new Semaphore(0);

	public static void main(String[] args)
	{
		SocketService socketService = new SocketService();
		socketService.oneServer();
	}

	public void oneServer()
	{

		try
		{
			// ServerSocket server = null;
			try
			{
				senderServer = new ServerSocket(8888);
				receiverServer = new ServerSocket(8889);
				System.out.println("Server started");
			} catch (Exception e)
			{
				System.out.println("Error ：" + e);
			}

			sender send = new sender();
			send.start();

			receiver receive = new receiver();
			receive.start();

		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public static class sender extends Thread
	{
		public static Socket socket = null;

		@Override
		public void run()
		{
			System.out.println("sender run");

			try
			{
				socket = SocketService.senderServer.accept();
				System.out.println("sender accepted");
				BufferedReader senderReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

				while (true)
				{
					try
					{
						socket.sendUrgentData(0xFF);
					} catch (Exception e)
					{
						System.out.println("sender accepting ");
						senderReader.close();
						socket.close();
						socket = SocketService.senderServer.accept();
						senderReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
						continue;
					}
					String senderInfo;
					try
					{
						senderInfo = senderReader.readLine();
					} catch (Exception e)
					{
						System.out.println("sender accepting ");
						senderReader.close();
						socket.close();
						socket = SocketService.senderServer.accept();
						senderReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
						continue;
					}

					SocketService.info = senderInfo;
					// SocketService.semaphore.release();
					receiver.sendMessage();
					System.out.println("Server get info : " + senderInfo);

					if (senderInfo == null)
					{
						continue;
					}

					if ("quit".hashCode() == senderInfo.hashCode())
					{
						break;
					}

				}

				senderReader.close();
				socket.close();
			} catch (Exception e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	static class receiver extends Thread
	{
		private static Socket socket = null;
		public static PrintWriter write;
		public static BufferedReader br;

		public static void sendMessage() throws IOException
		{
			try
			{
				socket.sendUrgentData(0);
				write = new PrintWriter(socket.getOutputStream());
				String info = SocketService.info;
				write.println(info);
				write.flush();
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		}

		public void run()
		{
			System.out.println("receiver run");

			try
			{
				socket = SocketService.receiverServer.accept();
				br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			} catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			while (true)
			{
				try
				{
					// System.out.println("waiting");
					String receiverInfo = br.readLine();
					socket = SocketService.receiverServer.accept();
					br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				} catch (Exception e)
				{
					System.out.println(e);
				}
			}

			/*
			 * try { PrintWriter write = null; try { socket =
			 * SocketService.receiverServer.accept();
			 * System.out.println("receiver accepted"); write = new
			 * PrintWriter(socket.getOutputStream()); BufferedReader br = new
			 * BufferedReader(new InputStreamReader(socket.getInputStream()));
			 * while (true) { // System.out.println(SocketService.flag);
			 * SocketService.semaphore.acquire(); String sendInfo =
			 * SocketService.info; try { socket.sendUrgentData(0); } catch
			 * (Exception e) { write.close(); socket.close(); socket =
			 * SocketService.receiverServer.accept(); write = new
			 * PrintWriter(socket.getOutputStream()); socket.sendUrgentData(0);
			 * 
			 * }
			 * 
			 * write.println(sendInfo); write.flush(); } } catch (Exception e) {
			 * // TODO Auto-generated catch block e.printStackTrace();
			 * write.close(); socket.close(); }
			 * 
			 * } catch (IOException e) { // TODO Auto-generated catch block
			 * e.printStackTrace(); }
			 * 
			 * }
			 */
		}
	}
}
