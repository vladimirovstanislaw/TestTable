package ru.spb.iac.WebSocket;

public class HelloMessage {
	private String id;

	public HelloMessage() {
	}

	public HelloMessage(String id) {
		this.id = id;
	}

	public String getName() {
		return id;
	}

	public void setName(String id) {
		this.id = id;
	}
}
