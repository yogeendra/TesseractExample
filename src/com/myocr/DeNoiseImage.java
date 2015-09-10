package com.myocr;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.photo.Photo;

public class DeNoiseImage {
	
	/**
	 * denoise image of imput: inputImage and output: outputImage
	 * 
	 */
	public DeNoiseImage(String inputImage, String outputImage) {

		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		denoise(inputImage, outputImage);
	}

	/**
	 * denoise image of same input image
	 * 
	 */
	public DeNoiseImage(String inputImage) {

		this(inputImage, inputImage);
	}

	private void denoise(String inputImage, String outputImage) {
		
		Mat image = Imgcodecs.imread(inputImage);
		Mat out = new Mat();

		if (image.empty()) {
			System.out.println("Image not found !!");
			return;
		}
		Photo.fastNlMeansDenoising(image, out, 10, 7, 21);
		Imgcodecs.imwrite(outputImage, out);
		if (out.empty()) {
			System.out.println("Image not found !!");
			return;
		}
	}
}
