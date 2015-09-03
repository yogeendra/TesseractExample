package com.myocr;

import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FilterContent {
	public static void main(String[] argv) throws Exception {
		Pattern pattern = Pattern.compile("[A-Z0-9\\s:/-]");
		FileInputStream input = new FileInputStream("output/log.out.txt");
		FileChannel channel = input.getChannel();

		ByteBuffer bbuf = channel.map(FileChannel.MapMode.READ_ONLY, 0,
				(int) channel.size());
		CharBuffer cbuf = Charset.forName("8859_1").newDecoder().decode(bbuf);
		//System.out.println("CharBuffer: "+cbuf.toString());
		Matcher matcher = pattern.matcher(cbuf);
		while (matcher.find()) {

			String match = matcher.group();

			System.out.print(match);
		}
	}

	public boolean isThreeLetters(String s) {
		return s.matches("[A-Z0-9:/-]");
		// simpler from for
		// return s.matches("[a-Z][a-Z][a-Z]");
	}

}