package com.acme.mailreader.domain;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.time.Instant;

import org.junit.Before;
import org.junit.Test;

import com.acme.mailreader.utils.DateIncorrecteException;

public class MailComparatorTest {
	
	private MailComparator comparator;

	@Before
	public void setUp() throws Exception {
		this.comparator = new MailComparator();
	}

	@Test
	public final void egauxSiDeuxMailsNuls() {
		Mail mail1 = null;
		Mail mail2 = null;
		assertThat(comparator.compare(mail1, mail2), is(0));
	}
	
	@Test
	public final void egauxSiUnDesMailsNuls() {
		Mail mail1 = new Mail();
		Mail mail2 = null;
		assertThat(comparator.compare(mail1, mail2), is(0));
	}
	
	@Test
	public final void premierPlusPetitSiMoinsImportant(){
		Mail mail1 = new Mail.Builder("test").important(false).build();
		Mail mail2 = new Mail.Builder("test").important(true).build();
		assertThat(comparator.compare(mail1, mail2), is(1));
	}
	
	@Test
	public final void premierPlusGrandSiPlusImportant(){
		Mail mail1 = new Mail.Builder("test").important(true).build();
		Mail mail2 = new Mail.Builder("test").important(false).build();
		assertThat(comparator.compare(mail1, mail2), is(-1));
	}
	
	
	@Test
	public final void premierPlusGrandSiMeilleurStatut(){
		Mail mail1 = new Mail.Builder("test").statut(Mail.Statut.SENT).build();
		Mail mail2 = new Mail.Builder("test").statut(Mail.Statut.UNSENT).build();
		assertThat(comparator.compare(mail1, mail2), is(-1));
	}
	
	@Test
	public final void premierPlusPetitSiMoinsBonStatut(){
		Mail mail1 = new Mail.Builder("test").statut(Mail.Statut.UNSENT).build();
		Mail mail2 = new Mail.Builder("test").statut(Mail.Statut.SENT).build();
		assertThat(comparator.compare(mail1, mail2), is(1));
	}
	
	
	@Test
	public final void premierPlusPetitSiDatePosterieure() throws DateIncorrecteException{
		Mail mail1 = new Mail.Builder("test").date(Instant.parse("2018-03-05T15:42:12Z")).build();
		Mail mail2 = new Mail.Builder("test").date(Instant.parse("2018-03-04T15:42:12Z")).build();
		assertThat(comparator.compare(mail1, mail2), is(1));
	}
	
	@Test
	public final void egauxSiMemeDate() throws DateIncorrecteException {
		Mail mail1 = new Mail.Builder("test").date(Instant.parse("2018-03-04T15:42:12Z")).build();
		Mail mail2 = new Mail.Builder("test").date(Instant.parse("2018-03-04T15:42:12Z")).build();
		assertThat(comparator.compare(mail1, mail2), is(0));
	}
	
	
}
