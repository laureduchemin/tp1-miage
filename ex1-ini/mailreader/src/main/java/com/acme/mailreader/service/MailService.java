package com.acme.mailreader.service;

import com.acme.mailreader.domaine.Mail;
import com.acme.mailreader.infrastructure.InMemoryMailSender;
import com.acme.mailreader.infrastructure.MailSender;
import com.acme.mailreader.infrastructure.SmtpMailSender;
import com.acme.mailreader.presentation.ClientMail;

public class MailService {
	
	private MailSender sender;
	public void envoyerMail(Mail mail){
		if (ClientMail.production){
			sender = new SmtpMailSender();
		}else {
			sender = new InMemoryMailSender();
		}
		sender.envoyerMail(mail);
	}

}
