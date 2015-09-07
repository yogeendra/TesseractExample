package com.myocr;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.photo.Photo;

public class DeNoiseImage {
	public static void main(String[] args) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		DeNoiseImage i = new DeNoiseImage();
		i.denoise("images/18321.jpg", "output/outimg.jpg");
		
	}
	
	
	public void denoise(String inputImage,String outputImage) {
		//String path = "images/18321.jpg";
		Mat image = Imgcodecs.imread(inputImage);
		Mat out = new Mat();

		if (image.empty()) {
			System.out.println("Image not found !!");
			return;
		}
		Photo.fastNlMeansDenoising(image, out, 50, 7, 21);
		Imgcodecs.imwrite(outputImage, out);
		if (out.empty()) {
			System.out.println("Image not found !!");
			return;
		}
	}
}
