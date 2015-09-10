package com.myocr.util;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class ImageResize {

	private int IMG_WIDTH = 0;
	private int IMG_HEIGHT = 0;
	

	public int getIMG_WIDTH() {
		return IMG_WIDTH;
	}

	public void setIMG_WIDTH(int iMG_WIDTH) {
		IMG_WIDTH = iMG_WIDTH;
	}

	public int getIMG_HEIGHT() {
		return IMG_HEIGHT;
	}

	public void setIMG_HEIGHT(int iMG_HEIGHT) {
		IMG_HEIGHT = iMG_HEIGHT;
	}
	
	public void size(String orginalImage) {
		BufferedImage originalImage = null;
		try {
			originalImage = ImageIO.read(new File(orginalImage));
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		IMG_WIDTH = originalImage.getWidth();
		IMG_HEIGHT = originalImage.getHeight();
	}
	

	public void resize(String orginalImage, String resizedImage) {

		try {

			BufferedImage originalImage = ImageIO.read(new File(orginalImage));

			/*
			 * System.out.println(originalImage.getHeight());
			 * System.out.println(originalImage.getWidth());
			 */
			int imageHeight = originalImage.getHeight();
			int imageWidth = originalImage.getWidth();
			System.out.println("Before: " + originalImage.getWidth() + "x"
					+ originalImage.getHeight());
			if (imageHeight > 2000 || imageWidth > 1400) {
				IMG_WIDTH = originalImage.getWidth() / 2;
				IMG_HEIGHT = originalImage.getHeight() / 2;
			} else {
				IMG_WIDTH = imageWidth;
				IMG_HEIGHT = imageHeight;
			}

			System.out.println("After: " + IMG_WIDTH + "x" + IMG_HEIGHT);
			int type = originalImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB
					: originalImage.getType();

			BufferedImage resizeImageJpg = resizeImage(originalImage, type);
			ImageIO.write(resizeImageJpg, "jpg", new File(resizedImage));

			/*
			 * BufferedImage resizeImagePng = resizeImage(originalImage, type);
			 * ImageIO.write(resizeImagePng, "png", new
			 * File("images/resizeImage.jpg"));
			 * 
			 * BufferedImage resizeImageHintJpg = resizeImageWithHint(
			 * originalImage, type); ImageIO.write(resizeImageHintJpg, "jpg",
			 * new File("images/resizeImage.jpg"));
			 * 
			 * BufferedImage resizeImageHintPng = resizeImageWithHint(
			 * originalImage, type); ImageIO.write(resizeImageHintPng, "png",
			 * new File("images/resizeImage.jpg"));
			 */
			// System.out.println(originalImage.getHeight());
			// System.out.println(originalImage.getWidth());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}

	public void resize(String orginalImage) {

		resize(orginalImage, orginalImage);
	}

	private BufferedImage resizeImage(BufferedImage originalImage,
			int type) {
		BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT,
				type);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
		g.dispose();
		return resizedImage;
	}
}