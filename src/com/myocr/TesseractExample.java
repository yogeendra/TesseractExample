package com.myocr;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class TesseractExample {

	public static void main(String[] args) {
		//File imageFile = new File("images/55.jpg");
        Tesseract instance = Tesseract.getInstance();  // JNA Interface Mapping
         //Tesseract1 instance = new Tesseract1(); // JNA Direct Mapping

        try {
        	instance.setLanguage("eng");
        	//String result = instance.doOCR(imageFile);
        	List<ITesseract.RenderedFormat> l = new ArrayList<ITesseract.RenderedFormat>();
        	l.add(ITesseract.RenderedFormat.TEXT);
        	instance.createDocuments("images/55.jpg","output/log.out",l);
        	
            //String result = instance.doOCR(imageFile);
           // System.out.println(result);
        } catch (TesseractException e) {
            System.err.println(e.getMessage());	
        }

	}

}
