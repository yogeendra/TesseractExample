package com.myocr;

import ClearImageJNI.CiException;
import ClearImageJNI.CiServer;
import ClearImageJNI.EBorderExtractAlgorithm;
import ClearImageJNI.EBorderExtractFlags;
import ClearImageJNI.ECleanNoiseFlags;
import ClearImageJNI.EFileFormat;
import ClearImageJNI.ELineDirection;
import ClearImageJNI.ESmoothType;
import ClearImageJNI.ICiRepair;
import ClearImageJNI.ICiServer;

public class ClearImagee {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {

			// String s =
			// CiServer.loadClearImage("C:\\Program Files (x86)\\Inlite\\ClearImage SDK.8_2_4362\\clearimage.dev.dll");
			// if (s != "") System.out.println("ERROR Loading: " + s);
			CiServer objCi = new CiServer();
			ICiServer Ci = objCi.getICiServer();
			ICiRepair repair = Ci.CreateRepair();
			repair.getImage().Open("output/outimg.jpg", 1);
			//repair.RemovePunchHoles();
		//	repair.AutoDeskew();
//			repair.AutoRotate();
			// repair.ClearBackground(20);
			repair.AutoDeskew();
	//		repair.AutoRotate();
			// repair.AutoCrop(10, 10, 10, 10); // Crop to 10 pixels on each
			// side
			// to bitonal
			repair.AdvancedBinarize(0,0,0); // Convert to bitonal an image
			//repair.FaxStandardToFine();								// with complex background
												// patterns
			//repair.BorderExtract(EBorderExtractFlags.ciBexBorderDeskewCrop,
				//	EBorderExtractAlgorithm.ciBeaCleaner); // Deskew and crop
															// based on black
															// border
			//repair.RemovePunchHoles();
			repair.SmoothCharacters(ESmoothType.ciSmoothDarkenEdges);
			repair.CleanNoise(3); // Clean black noise of 3 pixels
			repair.ClearBackground(60);
			repair.CleanNoiseExt // Clean black and white noise
			
			(new ECleanNoiseFlags(ECleanNoiseFlags.ciCnxBlackNoise,
					ECleanNoiseFlags.ciCnxWhiteNoise), 10, 10, 10, 0);
			repair.ReconstructLines(ELineDirection.ciLineVertAndHorz);
			repair.getImage().SaveAs("output/myNewFile.tif",
					EFileFormat.ciTIFF_JPEG);
			repair.FaxRemoveBlankLines();
			
			// /////////////////////////

		} catch (CiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
