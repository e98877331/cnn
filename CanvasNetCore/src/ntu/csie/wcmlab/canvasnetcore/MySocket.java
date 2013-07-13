package ntu.csie.wcmlab.canvasnetcore;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketTimeoutException;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.apache.http.conn.util.InetAddressUtils;

import android.app.Activity;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Message;
import android.text.format.Formatter;


public class MySocket {

	private final int SLEEP_TIME = 5;
	private boolean IsServer;
	
	private MySurfaceView mMySurfaceView;

	public java.io.ObjectInputStream ib;
	private java.io.ObjectOutputStream ob;

	public Vector<Connection> list;
	private ServerSocket serverSocket;
	private Socket clientSocket, socket;

	private int listenPort; // for listen port
    
	private Thread tmp;
	
	private String selfIP;
	public
	String idFromIP;

	public boolean IsServer(){		return IsServer;	} 
	public MySurfaceView getSurfaceView(){	return mMySurfaceView;	}
	
	public MySocket(MySurfaceView canvas, int listen, WifiManager wifiManager) {
		mMySurfaceView = canvas;
		listenPort = listen; 
		list = new Vector<Connection>();
		
		WifiInfo wifiInfo = wifiManager.getConnectionInfo();
		int ipAddress = wifiInfo.getIpAddress();
		selfIP = new String(Formatter.formatIpAddress(ipAddress));

		try {
			InetAddress.getByName(selfIP);
			serverSocket = new ServerSocket(listenPort);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		//ChengYan: get the client IP which is depend on IP
		String[] temp = selfIP.split("\\.");
		idFromIP = temp[temp.length-1];
		
	
		mMySurfaceView.drawStateMap.put(idFromIP, new ClientDrawState());
		
		
	}
	


	public void server() {

		//Log.d("proj" , "click server");
		IsServer = true;
		tmp = new Thread() {
			public void run() {
				while(true){

						try {						
							//Log.d("proj" , "listen!!!");							
							Socket socket = serverSocket.accept();
							Connection c = new Connection(socket);
							list.add(c);
							list.elementAt(list.size()-1).setIndex(list.size()-1);
							//Log.d("proj" , "new  a thread for new connection");
							//Log.d("proj" , "add it: " + c.sck.toString());
							
							
							//ChengYan: tell host all exist client

							
							list.elementAt(list.size()-1).RunThread = new Thread(){
								public void run(){
									Connection tempc = list.elementAt(list.size()-1);
									//Log.d("proj" , "in thread:" + tempc.toString());
									while (!Thread.interrupted()) {
										
										try {
											sleep(SLEEP_TIME);
										
										// get command from ib then send to UIthread
											Commands.BaseCmd tempC;
											//Log.d("proj",  "thread["+ tempc.getIndex() +"]B4 read obj!!");
													
											tempC = (Commands.BaseCmd) tempc.ib.readObject();											
											//Log.d("proj",  "thread["+ tempc.getIndex() +"]After read obj!!");
											Bundle tempB = new Bundle();
											tempB.putSerializable("cmd", tempC);
											Message m = new Message();
											m.what = MySurfaceView.GET_COMMAND;
											m.setData(tempB);
											mMySurfaceView.handler.sendMessage(m);
												
											Iterator<Connection> it1 = list.iterator();
											
											//Log.d("proj" , "["+tempc.getIndex()+"] thread: " );
											while(it1.hasNext()){
												Connection tmpcc = (Connection) it1.next();
												////Log.d("proj" , "\t [" + tmpcc.getIndex()+"] con " );
												if( !tempc.equals(tmpcc) ){	
													tmpcc.send(tempC);	
													//Log.d("proj" , "\t [" + tmpcc.getIndex()+"] connection send" );
												}
												else{
													//Log.d("proj" , "\t [" + tmpcc.getIndex()+"] con " );
												}
												
											}		
										
										} catch (InterruptedException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}  catch (ClassNotFoundException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										} catch (IOException e) {
											// TODO Auto-generated catch block
											//Log.d("proj" , "in read exception!!!");
											//here is the exception of readObject
											//maybe the client has close the socket!!
											//has to stop the thread and clean the connection
											if(list.removeElement(tempc)){
												//Log.d("proj" , "delete connection success");
											}
											else{
												//Log.d("proj" , "not found connection");
											}
										
											PrintList();
											e.printStackTrace();
											
											//break the loop to stop the thread
											break;
										}
									}
									
								}
							};
							list.elementAt(list.size()-1).RunThread.start();
							
							
							//ChengYan: send Broadcast command
							String[] tempStrings =mMySurfaceView.drawStateMap.keySet().toArray(new String[0]);
						
							send(new Commands.ServerBroadcastClientCmd(tempStrings));
							
							
							MySocket.this.sendMessageToUIThread("Connect Constructed! It has " + list.size() + " connections");
						
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							break;
						} 
				}
			}
		};
		tmp.start();
	}
	
	public int client(final String ip,final int port) {

		IsServer = false;
		InetAddress serverIp;
		SocketAddress tmpServerIP;
		try {
			serverIp = InetAddress.getByName(ip);
			 tmpServerIP = new InetSocketAddress(serverIp , port);
			
			clientSocket = new Socket();
			clientSocket.connect(tmpServerIP, 3000);
			
			if(clientSocket!=null){
				MySocket.this.sendMessageToUIThread("Connect to" + ip);
				
				ob = new java.io.ObjectOutputStream(clientSocket.getOutputStream());
				//if (ob != null)		Log.d("proj", "client ob not null");
				ib = new java.io.ObjectInputStream(clientSocket.getInputStream());
				//if (ib != null)		Log.d("proj", "client ib not null");
				tmp = new Thread() {
					public void run() {

					while (!Thread.interrupted()) {
						try {
							sleep(SLEEP_TIME);

							// get command from ib then send to UIthread

							Commands.BaseCmd tempC;
							tempC = (Commands.BaseCmd) ib.readObject();
							Bundle tempB = new Bundle();
							tempB.putSerializable("cmd", tempC);
							Message m = new Message();
							m.what = MySurfaceView.GET_COMMAND;
							m.setData(tempB);
							mMySurfaceView.handler.sendMessage(m);

						} catch (Exception e) {
							// TODO Auto-generated catch block
							//Log.d("proj" , "in client thread exception!!");
							e.printStackTrace();
							disconnect();
							MySocket.this.sendMessageToUIThread("Connect Lost");
							((MyCanvas) (mMySurfaceView.mContext)).finish();
							break;
						}
					}
				}
			};
				//Log.d("proj" , "thread ID: " +tmp.getId());
				//Log.d("proj" , "thread Name: " +tmp.getName());
				tmp.start();
				
				//ChengYan: tell all others the new Client
				send(new Commands.ClientConnectCmd());
				//Log.d("proj" , "after!!! ");
			}			

			
		} catch(SocketTimeoutException u){
			//Log.e("CYY" , "timeout!!!!!! ");
			//mMySurfaceView.errorToast("Can not connect to : " + ip);
			sendMessageToUIThread("Connect Timeout!!");
			this.disconnect();
			return -1;
		}catch (Exception e) {
			// TODO Auto-generated catch block
			//Log.e("CYY" , "timeout2222222222 ");
			sendMessageToUIThread("Connect Faild");
			//mMySurfaceView.errorToast("Connect Faild");
			//((MyCanvas) (mMySurfaceView.mContext)).finish();
			this.disconnect();

			e.printStackTrace();
			return -1;
		}

		return 0;
	}
    
    public void sendMessageToUIThread(String str)
    {
    	Bundle tempB = new Bundle();
    	tempB.putString("message", str);
		Message m = new Message();
		m.what = MySurfaceView.GET_SHOW_TOAST;
		m.setData(tempB);
		mMySurfaceView.handler.sendMessage(m);
    	
    }
	public void disconnect() {

		try {
			// �����s�u
			if (ib != null) {
				ib.close();
				ib = null;
			}
			if (ob != null) {
				ob.close();
				ib = null;
			}
			if (tmp != null) {
				tmp.interrupt();
				tmp = null;	
			}
			if (clientSocket != null) {
				clientSocket.close();
				clientSocket = null;
			}
			if (serverSocket != null) {
				serverSocket.close();
				serverSocket = null;
			}
			if (socket != null) {
				socket.close();
				socket = null;
			}
			Iterator<Connection> it = list.iterator();
			while(list.size()!=0){
				
				Connection tempc = list.lastElement();
				tempc.close();
			}
			list.clear();

			
			//Log.d("proj" , "@@@");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void send(Commands.BaseCmd obj) {

		if (ib == null && !IsServer())
			return;
		
		try {
			if(IsServer()){
				//ChengYan: annotate the command is send from host
				obj.setFrom(idFromIP); //ChengYan: 0 means from Host
				
				//Log.d("proj" , "Is Server: send to " + list.size());
				Iterator<Connection> it = list.iterator();
				while(it.hasNext()){
					Connection tmpc = it.next();
					tmpc.send(obj);		
					//Log.d("proj" , "tmpc:" + tmpc.sck.toString());
				}
			}
			else{
				//ChengYan: annotate the command is send from client
				obj.setFrom(idFromIP);
				
				ob.writeObject(obj);
				ob.flush();
			}	
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getIP() {	return this.selfIP;  }
	
    public static String getIPAddress(boolean useIPv4) {
        try {
            List<NetworkInterface> interfaces = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface intf : interfaces) {
                List<InetAddress> addrs = Collections.list(intf.getInetAddresses());
                for (InetAddress addr : addrs) {
                    if (!addr.isLoopbackAddress()) {
                        String sAddr = addr.getHostAddress().toUpperCase();
                        boolean isIPv4 = InetAddressUtils.isIPv4Address(sAddr); 
                        if (useIPv4) {
                            if (isIPv4) 
                                return sAddr;
                        } else {
                            if (!isIPv4) {
                                int delim = sAddr.indexOf('%'); // drop ip6 port suffix
                                return delim<0 ? sAddr : sAddr.substring(0, delim);
                            }
                        }
                    }
                }
            }
        } catch (Exception ex) { } // for now eat exceptions
        return "";
    }
	
	
	
	public void PrintList(){
		//Log.d("proj" , "In PrintList");
		Iterator<Connection> it = list.iterator();
		
		while(it.hasNext()){
			Connection c = it.next();
			//Log.d("proj" , "\t " + c.sck.toString());
		}
		
	}
}
