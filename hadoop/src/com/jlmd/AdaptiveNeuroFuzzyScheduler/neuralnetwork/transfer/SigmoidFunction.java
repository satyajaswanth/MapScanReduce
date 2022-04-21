/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jlmd.AdaptiveNeuroFuzzyScheduler.neuralnetwork.transfer;
/**
 *
 * @author code
 */
public class SigmoidFunction implements ITransferFunction {
    @Override
    public float transfer(float value) {
        return (float)(1/(1+Math.exp(-value)));
    }
}
