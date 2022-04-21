/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jlmd.AdaptiveNeuroFuzzyScheduler.neuralnetwork;
import com.jlmd.AdaptiveNeuroFuzzyScheduler.neuralnetwork.exception.ZeroInputDimensionException;
import com.jlmd.AdaptiveNeuroFuzzyScheduler.neuralnetwork.exception.ZeroNeuronsException;
import com.jlmd.AdaptiveNeuroFuzzyScheduler.neuralnetwork.utils.Utils;
/**
 *
 * @author code
 */
public class HiddenLayerNeuron {
    private float[] bias;
    private float[] vWeights;
    private float[][] wWeights;

    public HiddenLayerNeuron(int neurons, int dimension) throws ZeroNeuronsException, ZeroInputDimensionException {
        this.bias = new float[neurons];
        this.vWeights = new float[neurons];
        this.wWeights = new float[dimension][neurons];

        initWeights(neurons, dimension);
    }

    public float[] getBias(){
        return this.bias;
    }

    public float[] getVWeights(){
        return this.vWeights;
    }

    public float[][] getWWeights(){
        return this.wWeights;
    }

        private void initWeights(int neurons, int dimension) throws ZeroNeuronsException, ZeroInputDimensionException {
        if (neurons == 0)
            throw new ZeroNeuronsException();
        if (dimension == 0)
            throw new ZeroInputDimensionException();

        for (int i = 0;i<neurons;i++){
            this.bias[i] = Utils.randFloat(-0.5f, 0.5f);
            this.vWeights[i] = Utils.randFloat(-0.5f, 0.5f);
            for (int j = 0;j<dimension;j++) {
                this.wWeights[j][i] = Utils.randFloat(-0.5f, 0.5f);
            }
        }
    }

}
