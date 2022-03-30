package org.code.models;

public class Beneficiary {

	int sender;
	int recipient;
	String recipientContact;

	public int getSender() {
		return sender;
	}

	public void setSender(int sender) {
		this.sender = sender;
	}

	public int getRecipient() {
		return recipient;
	}

	public void setRecipient(int recipient) {
		this.recipient = recipient;
	}

	public String getRecipientContact() {
		return recipientContact;
	}

	public void setRecipientContact(String recipientContact) {
		this.recipientContact = recipientContact;
	}

	public Beneficiary(int sender, int recipient, String recipientContact) {
		super();
		this.sender = sender;
		this.recipient = recipient;
		this.recipientContact = recipientContact;
	}

	public Beneficiary() {
		super();
	}

}
