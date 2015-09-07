package com.myocr;

import ClearImageJNI.CiException;
import ClearImageJNI.CiServer;
import ClearImageJNI.FBarcodeType;
import ClearImageJNI.ICiBarcode;
import ClearImageJNI.ICiBarcodePro;
import ClearImageJNI.ICiServer;

public class BarCodeReader {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			CiServer objCi = new CiServer();
			ICiServer Ci = objCi.getICiServer();
			ICiBarcodePro reader = Ci.CreateBarcodePro();
			reader.setType(new FBarcodeType(FBarcodeType.cibfCode39,
					FBarcodeType.cibfCode128));
			reader.getImage().Open("images/18331.jpg", 1);
			reader.Find(0);
			for (int i = 1; i <= reader.getBarcodes().getCount(); i++) {
				ICiBarcode Bc = reader.getBarcodes().getItem(i);
				System.out.println(Bc.getText());

			}
			System.out.println("DOne");
		} catch (CiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
