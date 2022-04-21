/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jlmd.AdaptiveNeuroFuzzyScheduler.example;
import com.jlmd.AdaptiveNeuroFuzzyScheduler.neuralnetwork.entity.Result;
import com.jlmd.AdaptiveNeuroFuzzyScheduler.neuralnetwork.utils.DataUtils;
import com.jlmd.AdaptiveNeuroFuzzyScheduler.neuralnetwork.AdaptiveNeuroFuzzyScheduler;
import com.jlmd.AdaptiveNeuroFuzzyScheduler.neuralnetwork.entity.Error;
import com.jlmd.AdaptiveNeuroFuzzyScheduler.neuralnetwork.callback.INeuralNetworkCallback;

/**
 *
 * @author code
 */
public class Main {
    public static void main(String [] args){
        System.out.println(" ADAPTIVE NEURO FUZZY SCHEDULER : ");

        float[][] x = DataUtils.readInputsFromFile("data/x.txt");
        int[] t = DataUtils.readOutputsFromFile("data/t.txt");

        AdaptiveNeuroFuzzyScheduler neuralNetwork = new AdaptiveNeuroFuzzyScheduler(x, t, new INeuralNetworkCallback() {
            @Override
            public void success(Result result) {
                float[] valueToPredict = new float[] {-0.205f, 0.780f};
                System.out.println("Success percentage: " + result.getSuccessPercentage());
                System.out.println("Predicted result: " + result.predictValue(valueToPredict));
            }

            @Override
            public void failure(Error error) {
                System.out.println("Error: " + error.getDescription());
            }
        });

        neuralNetwork.startLearning();
    }
}
