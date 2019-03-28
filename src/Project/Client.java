package Project;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Client { // 이 클라이언트 클래스는 클라이언트 프로그램이 챗서버에 들어간다가 아니라 한명의 클라이언트와 통신을 하기위해서 작성한 것임.

	Socket socket;

	public Client(Socket socket) {
		this.socket = socket;
		receive();
	}

	// 클라이언트로부터 메시지를 전달 받는 메소드
	public void receive() {
		Runnable thread = new Runnable() {
			@Override
			public void run() {
				try {
					InputStream in = socket.getInputStream();
					byte[] buffer = new byte[512];
					int length = in.read(buffer);
					while (length == -1)
						throw new IOException();
					System.out.println(
							"[메시지 수신 성공]" + socket.getRemoteSocketAddress() + ": " + Thread.currentThread().getName());
					String message = new String(buffer, 0, length, "UTF-8");
					for (Client client : ServerMain.clients) {
						client.send(message);
					}
				} catch (Exception e) {
					try {
						System.out.println("[메시지 수신 오류]" + socket.getRemoteSocketAddress() + ": "
								+ Thread.currentThread().getName());
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}
			}
		};
		ServerMain.threadPool.submit(thread); // 클라이언트가 접속을 했을 때 쓰레드가 생성이 될텐데
										// 그 쓰레드를 안정적으로 관리하기 위해서 쓰레드풀을 이용하는것이다.
	}

	// 클라이언트에게 메시지를 전송하는 메소드
	public void send(String message) {
		Runnable thread = new Runnable() {
			@Override
			public void run() {
				try {
					OutputStream out = socket.getOutputStream();
					byte[] buffer = message.getBytes("UTF-8");
					out.write(buffer);
					out.flush();
				} catch (Exception e) {
					try {
						System.out.println(
								"[메시지 송신 오류]" + socket.getRemoteSocketAddress() + Thread.currentThread().getName());
						ServerMain.clients.remove(Client.this);
						socket.close();
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}
			}
		};
		ServerMain.threadPool.submit(thread);
	}
}
