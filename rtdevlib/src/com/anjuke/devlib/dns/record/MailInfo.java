package com.anjuke.devlib.dns.record;

import java.io.IOException;

import com.anjuke.devlib.dns.DNSInputStream;
import com.anjuke.devlib.dns.DNSRR;

public class MailInfo extends DNSRR {
	private String rBox, eBox;

	protected void decode(DNSInputStream dnsIn) throws IOException {
		rBox = dnsIn.readDomainName();
		eBox = dnsIn.readDomainName();
	}

	public String getResponsibleMailbox() {
		return rBox;
	}

	public String getErrorMailbox() {
		return eBox;
	}

	public String toString() {
		return getRRName() + "\tresponsible mailbox = " + rBox + ", error mailbox = " + eBox;
	}
}
