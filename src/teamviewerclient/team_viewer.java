package teamviewerclient;
import java.io.*;
import java.net.*;
public class team_viewer 
{
	public static void main(String[] args) 
	{
		try
		{
			int portNumber;
			String ipAndPort = "";
			String cs = "http://logitekprojects.com/TeamViewer/getIpPort.php";
			URL url = new URL(cs);
			URLConnection uc = url.openConnection();
			uc.setDoOutput(true);
			uc.connect();
	        InputStream rxdData = uc.getInputStream();
	        int ch = 0;
	        while((ch  = rxdData.read()) != -1)
	        	ipAndPort = ipAndPort + ((char) ch);
	        System.out.println("Use this information to connect to remote server over Internet: " + ipAndPort.trim());
	        String ipAddress[] = ipAndPort.trim().split(",");
	        portNumber = Integer.parseInt(ipAddress[1]);
			Socket s= new Socket(ipAddress[0], portNumber);
			
			System.out.println("Connected to remote server at: " + s.getRemoteSocketAddress());
			
			DataInputStream dis = new DataInputStream(s.getInputStream());
			File f = new File("E:\\vid.mp4");
			FileOutputStream fos = new FileOutputStream(f);
			int rxdByte = 0;
			while((rxdByte = dis.read()) != -1)
				fos.write(rxdByte);
			dis.close();
			fos.close();
			s.close();
		}catch(Exception e ){}
	}
}
