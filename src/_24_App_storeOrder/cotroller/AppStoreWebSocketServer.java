package _24_App_storeOrder.cotroller;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

@ServerEndpoint("/AppStoreWebSocketServer/{rest_id}")
public class AppStoreWebSocketServer {
	
	private Session userSession;
	private String message;
	private static Map<String, Session> map = new HashMap<String, Session>();
	
	private static final Set<Session> connectedSessions = 
			Collections.synchronizedSet(new HashSet<>());
	
	@OnOpen
	public void onOpen(@PathParam("rest_id") String rest_id, Session userSession) throws IOException {
		if (rest_id.equals("NA")) {
			//不連線
		} else {
			connectedSessions.add(userSession);
			map.put(rest_id, userSession);
			String text = String.format("Session ID = %s, connected; rest_id = %s", 
					userSession.getId(), rest_id);
			System.out.println(text);
		}
	}

	@OnMessage
	public void onMessage(Session userSession, String message) {
		System.out.println("onMessage start");
		Gson gson = new Gson();
		JsonObject jsonObject = gson.fromJson(message.toString(), JsonObject.class);
		String restId = jsonObject.get("restId").getAsString();
		Session session2 = map.get(restId);
		if (session2.isOpen()){
			session2.getAsyncRemote().sendText(message);
			System.out.println("Message received: " + message);
		}
	}

	@OnError
	public void onError(Session userSession, Throwable e) {
		System.out.println("Error: " + e.toString());
	}

	@OnClose
	public void onClose(Session userSession, CloseReason reason) {
		connectedSessions.remove(userSession);
		String text = String.format("session ID = %s, disconnected; close code = %d", userSession.getId(),
				reason.getCloseCode().getCode());
		System.out.println(text);
	}

}
