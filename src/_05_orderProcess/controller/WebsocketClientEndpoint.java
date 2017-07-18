package _05_orderProcess.controller;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

import javax.websocket.ClientEndpoint;
import javax.websocket.CloseReason;
import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

@ClientEndpoint
public class WebsocketClientEndpoint {
	
	Session userSession = null;
	private MessageHandler messageHandler;
	
	public WebsocketClientEndpoint(String uri) {	
		try {
			WebSocketContainer container = ContainerProvider.getWebSocketContainer();
				container.connectToServer(this, new URI(uri)); 
		} catch (DeploymentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}
	
	public void addMessageHandler(MessageHandler msgHandler) {
		this.messageHandler = msgHandler;
	}
	
	public void sendMessage(String message) {
		this.userSession.getAsyncRemote().sendText(message);	
	}
	
	@OnOpen
	public void onOpen(Session userSession) {
		this.userSession = userSession;
	}
	
	@OnMessage
	public void onMessage(String message) {
		if (this.messageHandler != null) {
			this.messageHandler.handleMessage(message);
		}
	}
	
	@OnClose
	public void onClose(Session userSession, CloseReason reason) {
		this.userSession = null;
	}
	
	public static interface MessageHandler {
		public void handleMessage(String message);
	}
}
