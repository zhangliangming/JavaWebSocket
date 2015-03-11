package com.test;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_17;
import org.java_websocket.framing.Framedata;
import org.java_websocket.handshake.ServerHandshake;

/**
 * This example demonstrates how to create a websocket connection to a server.
 * Only the most important callbacks are overloaded.
 */
public class ExampleClient extends WebSocketClient {

	public ExampleClient(URI serverUri, Draft draft) {
		super(serverUri, draft);
	}

	public ExampleClient(URI serverURI) {
		super(serverURI);
	}

	public void onClose(int code, String reason, boolean remote) {
		System.out.println("Connection closed by "
				+ (remote ? "remote peer" : "us"));
	}

	public void onError(Exception ex) {
		ex.printStackTrace();
	}

	public void onMessage(String message) {
		System.out.println("received: " + message);
	}

	public void onMessage(ByteBuffer blob) {
		System.out.println("received: " + blob);
	}

	public void onFragment(Framedata fragment) {
		System.out.println("received fragment: "
				+ new String(fragment.getPayloadData().array()));
	}

	public void onOpen(ServerHandshake handshakedata) {
		System.out.println("opened connection");
	}

	public static void main(String[] args) throws URISyntaxException,
			InterruptedException {
		String wsUrl = "ws://192.168.1.71:8080/webSocketServer/websocket";
		ExampleClient c = new ExampleClient(new URI(wsUrl), new Draft_17());
		c.connectBlocking();
		c.send("handshake");
	}
}