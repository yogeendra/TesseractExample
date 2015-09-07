package com.myocr;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract1;
import net.sourceforge.tess4j.TesseractException;

import org.im4java.core.ConvertCmd;
import org.im4java.core.IM4JavaException;
import org.im4java.core.IMOperation;
import org.im4java.core.Info;
import org.im4java.core.InfoException;

public class TesseractExample {

	public static void main(String[] args) {
		String inputImage = "images/300.jpg";
		String outputFile = "output/log.out";
		String reImage = "output/myNewFile.tif";
		TesseractExample te = new TesseractExample();
		/*try {
			te.imageResize(inputImage, reImage);
		} catch (IOException | InterruptedException | IM4JavaException e1) {
			e1.printStackTrace();
		}
*/
		// File imageFile = new File("images/55.jpg");
		//Tesseract instance = Tesseract.getInstance(); // JNA Interface Mapping
		Tesseract1 instance = new Tesseract1(); // JNA Direct Mapping

		try {
			instance.setLanguage("eng");
			instance.setTessVariable("tessedit_char_whitelist", "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ:-/<>");
			List<ITesseract.RenderedFormat> lst = new ArrayList<ITesseract.RenderedFormat>();
			lst.add(ITesseract.RenderedFormat.TEXT);
			instance.createDocuments(reImage, outputFile, lst);

			// String result = instance.doOCR(imageFile);
			// System.out.println(result);
		} catch (TesseractException e) {
			System.err.println(e.getMessage());
		}

	}
	
	public void cleanImage() {
		/** Typical scaling implementation using JMagick **/
		
	}
	

	private void imageResize(String highImage, String lowImage)
			throws IOException, InterruptedException, IM4JavaException {
		int newwidth = 0, newheight = 0;
		TesseractExample te = new TesseractExample();
		ImageProperty ip = null;

		ip = te.getProperty(highImage);
		if (ip.getWidth() > 1024 && ip.getWidth() > 764) {
			newheight = ip.getWidth() / 2;
			newwidth = ip.getWidth() / 2;
		}

		// create command
		ConvertCmd cmd = new ConvertCmd();

		// create the operation, add images and operators/options
		IMOperation op = new IMOperation();
		op.addImage(highImage);
		op.resize(newheight, newwidth);
		op.addImage(lowImage);

		// execute the operation
		cmd.run(op);
		System.out.println(te.getProperty(lowImage).toString());
	}

	private ImageProperty getProperty(String image) throws InfoException {
		Info imageInfo = new Info(image, true);

		ImageProperty ip = new ImageProperty();
		ip.setDepth(imageInfo.getImageDepth());
		ip.setFormat(imageInfo.getImageFormat());
		ip.setGeometry(imageInfo.getImageGeometry());
		ip.setHeight(imageInfo.getImageHeight());
		ip.setWidth(imageInfo.getImageWidth());
		ip.setImageClass(imageInfo.getImageClass());
		System.out.println(ip.toString());
		return ip;
	}

}
