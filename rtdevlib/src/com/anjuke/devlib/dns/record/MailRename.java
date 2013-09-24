package com.anjuke.devlib.dns.record;

import java.io.IOException;

import com.anjuke.devlib.dns.DNSInputStream;
import com.anjuke.devlib.dns.DNSRR;

public class MailRename extends DNSRR {
	private String mailRename;

	protected void decode(DNSInputStream dnsIn) throws IOException {
		mailRename = dnsIn.readDomainName();
	}

	public String getMailRename() {
		return mailRename;
	}

	public String toString() {
		return getRRName() + "\tmail rename = " + mailRename;
	}
}
