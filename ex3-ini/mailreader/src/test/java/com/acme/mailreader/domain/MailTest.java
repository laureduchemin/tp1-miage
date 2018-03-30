package com.acme.mailreader.domain;

import java.time.Instant;


import org.junit.Ignore;
import org.junit.Test;

import com.acme.mailreader.utils.DateIncorrecteException;

public class MailTest {
	
	@Test(expected=DateIncorrecteException.class)
	public final void erreurSiDateAvant1979() throws DateIncorrecteException {
		Mail mail1 = new Mail.Builder("test").date(Instant.parse("1966-06-05T15:38:12Z")).build();			
				
	}

}
