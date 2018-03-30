package com.acme.mailreader.infrastructure;

import com.acme.mailreader.domaine.Mail;

public interface MailSender {

	public void envoyerMail(Mail mail);

}
