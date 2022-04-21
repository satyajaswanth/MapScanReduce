/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jlmd.AdaptiveNeuroFuzzyScheduler.neuralnetwork.utils;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 *
 * @author code
 */
public class DataUtils {

    public static float[][] readInputsFromFile(String fileURI) {
        float[][] fArray = new float[0][];

        List<String> lines = null;
        try {
            lines = Files.readAllLines(Paths.get(fileURI), StandardCharsets.UTF_8);
            fArray = new float[lines.size()][];

            for (int i = 0; i < lines.size(); i++) {
                fArray[i] = convertStringArrayToFloatArray(lines.get(i).split(","));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return fArray;
    }

    public static int[] readOutputsFromFile(String fileURI) {
        int[] iArray = new int[0];

        List<String> lines = null;
        try {
            lines = Files.readAllLines(Paths.get(fileURI), StandardCharsets.UTF_8);
            iArray = new int[lines.size()];

            for (int i = 0; i < lines.size(); i++) {
                iArray[i] = Integer.parseInt(lines.get(i));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return iArray;
    }

    private static float[] convertStringArrayToFloatArray(String[] num) {
        if (num != null) {
            float fArray[] = new float[num.length];
            for (int i = 0; i < num.length; i++) {
                fArray[i] = Float.parseFloat(num[i]);
            }
            return fArray;
        }
        return null;
    }

}
