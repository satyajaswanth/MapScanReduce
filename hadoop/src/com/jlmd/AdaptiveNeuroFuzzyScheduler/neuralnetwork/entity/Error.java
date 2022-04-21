/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jlmd.AdaptiveNeuroFuzzyScheduler.neuralnetwork.entity;
/**
 *
 * @author code
 */
public enum Error {
    NOT_SAME_INPUT_OUTPUT(0, "Not same input and output size"),
    ZERO_INPUT_DIMENSION(1, "Input dimension is zero"),
    ZERO_INPUT_ELEMENTS(2, "Number of input elements is zero"),
    ZERO_NEURONS(3, "Number of neurons is zero");

    private final int code;
    private final String description;

    private Error(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public int getCode() {
        return code;
    }
}