/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jlmd.AdaptiveNeuroFuzzyScheduler.neuralnetwork.callback;
import com.jlmd.AdaptiveNeuroFuzzyScheduler.neuralnetwork.entity.Error;
import com.jlmd.AdaptiveNeuroFuzzyScheduler.neuralnetwork.entity.Result;

/**
 *
 * @author code
 */
public interface INeuralNetworkCallback {

    void success(Result result);

    void failure(Error error);
}
