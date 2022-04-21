/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jlmd.AdaptiveNeuroFuzzyScheduler.neuralnetwork.parser.result;
/**
 *
 * @author code
 */
public interface IResultParser<T> {
    /**
     * This method should calculate the count the number successes and return it
     * @param success Actually count of successes
     * @param fOut Real output
     * @param t Excepted output
     * @return number of successes updated
     */
    int countSuccesses(int success, float fOut, float t);

    /**
     * Convert a value obtained from out function in a real result
     * @param result Obtained from out function
     * @return real result
     */
    T parseResult(float result);
}
