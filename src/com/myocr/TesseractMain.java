package com.myocr;

import java.util.ArrayList;
import java.util.List;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract1;
import net.sourceforge.tess4j.TesseractException;

import org.opencv.core.Core;

public class TesseractMain {

	public void englishOCR(String inputImage, String outputFile) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		
		// Mat image = Imgcodecs.imread(inputImage);
		// System.out.println(inputImage+": "+image.height()+"x"+image.width());
		TesseractMain tesseract = new TesseractMain();

		String whitelistCharcters = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ:-/<>(),.";
		tesseract.RunOCR(inputImage, outputFile, "eng", whitelistCharcters);

	}

	public void RunOCR(String inputImage, String outputFile, String lang,
			String whitelistCharcters) {
		Tesseract1 instance = new Tesseract1(); // JNA Direct Mapping
		try {
			instance.setLanguage(lang);
			if (whitelistCharcters != null)
				instance.setTessVariable("tessedit_char_whitelist",
						whitelistCharcters);
			List<ITesseract.RenderedFormat> lst = new ArrayList<ITesseract.RenderedFormat>();
			lst.add(ITesseract.RenderedFormat.TEXT);
			instance.createDocuments(inputImage, outputFile, lst);

			// String result = instance.doOCR(imageFile);
			// System.out.println(result);
		} catch (TesseractException e) {
			System.err.println(e.getMessage());
		}

	}

}
