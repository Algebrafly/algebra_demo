package com.algebra.authentication.util;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.io.File;

/**
 * @author al
 * @date 2020/11/16 9:22
 * @description
 */
public class OcrUtil {


    public static void main(String[] args) {
        ITesseract instance = new Tesseract();

//        instance.setDatapath("所存放的语言包的路径");
        try {
            String imgText = instance.doOCR(new File("./ocr-text.png"));

            System.out.println(imgText);

        } catch (TesseractException e) {
            e.getMessage();
        }

    }
}
