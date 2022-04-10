package wiki;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import junit.framework.Assert;

import static org.junit.Assert.*;

import wiki.getWiki;

public class testgetWiki {
	@Test
	void test_info() {
		// test 1
		String title = "aeroplane";
		String resp = getWiki.info(title);
		String expectedresp = "An airplane or aeroplane is a fixed-wing aircraft that is propelled forward by thrust from a jet engine, propeller, or rocket engine. Airplanes come in a variety of sizes, shapes, and wing configurations. The broad spectrum of uses for airplanes includes recreation, transportation of goods and people, military, and research. Worldwide, commercial aviation transports more than four billion passengers annually on airliners and transports more than 200 billion tonne-kilometers of cargo annually, which is less than 1% of the world's cargo movement. Most airplanes are flown by a pilot on board the aircraft, but some are designed to be remotely or computer-controlled such as drones.";
		boolean isEqual = resp.equals(expectedresp);
		assertTrue(isEqual); // we would expect this to be true, since both the strings are equal;

		// test 2
		title = null;
		resp = getWiki.info(title);
		expectedresp = null;
		isEqual = resp.equals(expectedresp);
		assertTrue(isEqual);

		// test 3
	}

	@Test
	void test_parseString() {
		// test 1
		String str = "how is your aeroplane";
		String expectString = "aeroplane";
		String resp = getWiki.parseString(str);
		assertTrue(expectString.equals(resp));

		// test 2
		str = null;
		expectString = null;
		resp = getWiki.parseString(str);
		assertTrue(expectString.equals(resp));

	}
}
