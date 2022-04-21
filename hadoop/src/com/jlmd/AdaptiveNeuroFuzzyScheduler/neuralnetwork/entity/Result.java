/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jlmd.AdaptiveNeuroFuzzyScheduler.neuralnetwork.entity;
import com.jlmd.AdaptiveNeuroFuzzyScheduler.neuralnetwork.Analyzer;
import com.jlmd.AdaptiveNeuroFuzzyScheduler.neuralnetwork.parser.result.IResultParser;

/**
 *
 * @author code
 */
public class Result {

    private float successPercentage;
    private float quadraticError;
    private Analyzer analyzer;
    private IResultParser resultParser;

    public Result(Analyzer analyzer, IResultParser resultParser, float successPercentage, float quadraticError) {
        this.analyzer = analyzer;
        this.successPercentage = successPercentage;
        this.quadraticError = quadraticError;
        this.resultParser = resultParser;
    }

    public int predictValue(float[] element) {
        return (Integer) resultParser.parseResult(analyzer.getFOut(element));
    }

    public float getSuccessPercentage() {
        return successPercentage;
    }

    public float getQuadraticError() {
        return quadraticError;
    }
}
