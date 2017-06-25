package _02_App_login.controller;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.sql.Blob;

import javax.imageio.ImageIO;

public class ImageUtil {

	public static byte[] BlobToByteArrayAndAdjustSize(Blob blob, int newSize) {
		
		//------------Blob -> byte[]-----------------------------------------------
		byte[] blobAsByteArray = null;
		try {
			int blobSize = (int) blob.length();
			blobAsByteArray = blob.getBytes(1, blobSize);// 1為指定位元組註標
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		//------------Adjust Size---------------------------------------------------
		ByteArrayInputStream bais = new ByteArrayInputStream(blobAsByteArray);

		double sampleSize = 1; // 縮小的倍數
		int imageWidth = 0;
		int imageHeight = 0;

		// 如果欲縮小的尺寸過小，就直接定為128
		if (newSize <= 1) {
			newSize = 128;
		}

		try {
			BufferedImage bi = ImageIO.read(bais); // ImageIO讀入bais資訊為BufferedImage
			int type = bi.getType(); // Returns the image type(4種)
			String format = "";
			if (type == BufferedImage.TYPE_4BYTE_ABGR || type == BufferedImage.TYPE_4BYTE_ABGR_PRE
					|| type == BufferedImage.TYPE_INT_ARGB || type == BufferedImage.TYPE_INT_ARGB_PRE) {
				format = "png"; // 8位RGBA者format為png
			} else {
				format = "jpg"; // 不是者format為jpg
			}
			// 取得圖片的長寬
			imageWidth = bi.getWidth();
			imageHeight = bi.getHeight();
			// 如果圖片的長或寬為0 -> 圖片等同於無，直接回傳原Byte[]
			if (imageWidth == 0 || imageHeight == 0) {
				return blobAsByteArray;
			}
			// 依照圖片超過設定的比率調整圖片
			int longSide = Math.max(imageWidth, imageHeight); // 取得長寬中較大者
			if (longSide > newSize) { // 如果longSide大於設定的最大長寬
				sampleSize = longSide / newSize; // 計算longSize為設定最大長寬的幾倍
				imageWidth = (int) (imageWidth / sampleSize); // 將長寬等比率至框框
				imageHeight = (int) (imageHeight / sampleSize);
			}
			BufferedImage biscaled = new BufferedImage(imageWidth, imageHeight, type);
			Graphics graphics = biscaled.createGraphics(); // 取得繪圖工具(Graphics2D)
			// Draws as much of the specified image as has already
			// been scaled to fit inside the specified rectangle.
			// (原BufferedImage,x座標起點,y座標起點,rectangle寬,rectangle長,繪圖觀察員)
			graphics.drawImage(bi, 0, 0, imageWidth, imageHeight, null);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			// ImageIO寫出已調整的BufferedImage，format為適合的格式，輸出baos．
			ImageIO.write(biscaled, format, baos);
			
			return baos.toByteArray();
			
		} catch (Exception e2) {
			e2.printStackTrace();
			return blobAsByteArray;
		}

	}
}
