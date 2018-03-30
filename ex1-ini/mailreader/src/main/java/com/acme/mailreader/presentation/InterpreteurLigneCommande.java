package com.acme.mailreader.presentation;

import com.acme.mailreader.domaine.Mail;
import com.acme.mailreader.service.MailService;

public class InterpreteurLigneCommande {
	
	public void nouveauMail(String[] args){
		Mail mail = new Mail.Builder(args[1]).build();
		MailService mailService = new MailService();
		mailService.envoyerMail(mail);
	}

}
