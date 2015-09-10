package com.myocr;

import ClearImageJNI.CiException;
import ClearImageJNI.CiServer;
import ClearImageJNI.ECleanNoiseFlags;
import ClearImageJNI.EFileFormat;
import ClearImageJNI.ELineDirection;
import ClearImageJNI.ESmoothType;
import ClearImageJNI.ICiRepair;
import ClearImageJNI.ICiServer;

public class ClearImage {
	public ClearImage(String inputImage, String outputImage) {
		
		clear(inputImage, outputImage);
	}

	public ClearImage(String inputImage) {
		
		this(inputImage, inputImage);
	}

	private void clear(String inputImage, String outputImage) {
		
		try {

			CiServer objCi = new CiServer();
			ICiServer Ci = objCi.getICiServer();
			ICiRepair repair = Ci.CreateRepair();
			repair.getImage().Open(inputImage, 1);
			// repair.RemovePunchHoles();
			// repair.AutoDeskew();
			// repair.AutoRotate();
			// repair.ClearBackground(20);
			repair.AutoDeskew();
			// repair.AutoRotate();
			// repair.AutoCrop(10, 10, 10, 10);
			// Crop to 10 pixels on each
			// side
			// to bitonal
			repair.AdvancedBinarize(20, 20, 20); // Convert to bitonal an image
			// repair.FaxStandardToFine(); // with complex background patterns
			// repair.BorderExtract(EBorderExtractFlags.ciBexBorderDeskewCrop,
			// EBorderExtractAlgorithm.ciBeaCleaner);
			// Deskew and crop
			// based on black
			// border
			// repair.RemovePunchHoles();
			repair.SmoothCharacters(ESmoothType.ciSmoothDarkenEdges);
			repair.CleanNoise(3); // Clean black noise of 3 pixels
			repair.ClearBackground(53);
			repair.CleanNoiseExt(new ECleanNoiseFlags(
					ECleanNoiseFlags.ciCnxBlackNoise,
					ECleanNoiseFlags.ciCnxWhiteNoise), 8, 10, 10, 0);// Clean
																		// black
																		// and
																		// white
																		// noise
			repair.ReconstructLines(ELineDirection.ciLineVertAndHorz);
			repair.getImage().SaveAs(outputImage, EFileFormat.ciTIFF_JPEG);
			repair.FaxRemoveBlankLines();

		} catch (CiException e) {
			e.printStackTrace();
		}
	}

}
