package com.myocr;

import java.util.Date;

import org.opencv.core.Core;

import com.myocr.util.ImageResize;

public class MainOCR {
	static {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	}
	
	public static void main(String[] args) {
		MainOCR object = new MainOCR();
		object.callOcr("Passport-Front.jpg");
	}
	
	public void callOcr(String args) {

		String inputDirectory = "images";
		String inputImageName = args; // "Passport-Front.jpg";
		String resizeDirectory = "resize";
		String outputDirectoty = "output";

		/*
		 * Call ImageResize class with resize method with input image and output
		 * image name
		 * 
		 * if image width is greater than 1400px or image height is 2000px
		 * resize image to half of width and height else use same width and
		 * height
		 */
		System.out.println("Start Time: " + new Date());
		String resizeImage = resizeDirectory + "//" + inputImageName;
		ImageResize ir = new ImageResize();
		ir.size(inputDirectory + "//" + inputImageName);
		ir.resize(inputDirectory + "//" + inputImageName, resizeImage);

		if (ir.getIMG_HEIGHT() > 700 || ir.getIMG_WIDTH() > 500) {
			new DeNoiseImage(inputDirectory + "//" + inputImageName,
					resizeImage);
			System.out.println("De Noising Time: " + new Date());

			new ClearImage(resizeImage);
			System.out.println("Clear Image Time: " + new Date());

			String outputFile = outputDirectoty + "//" + inputImageName;
			TesseractMain tm = new TesseractMain();
			tm.englishOCR(resizeImage, outputFile);

			System.out.println("Compleated Time: " + new Date());

		} else {
			System.out.println("Compleated Without OCR: " + new Date());
		}

	}

}
