package com.calculator.coin.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "coin_calculation_table")
public class CoinEntity {

    private long id;
    private String inputType;
    private float inputAmount;
    private String outputType;
    private float outputAmount;
    private String date;

    public CoinEntity() {
    }

    public CoinEntity(String inputType, float inputAmount, String outputType, float outputAmount, String date) {
        this.inputType = inputType;
        this.inputAmount = inputAmount;
        this.outputType = outputType;
        this.outputAmount = outputAmount;
        this.date = date;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "input_type", nullable = false)
    public String getInputType() {
        return inputType;
    }

    public void setInputType(String inputType) {
        this.inputType = inputType;
    }

    @Column(name = "input_amount", nullable = false)
    public float getInputAmount() {
        return inputAmount;
    }

    public void setInputAmount(float inputAmount) {
        this.inputAmount = inputAmount;
    }


    @Column(name = "output_type", nullable = false)
    public String getOutputType() {
        return outputType;
    }

    public void setOutputType(String outputType) {
        this.outputType = outputType;
    }

    @Column(name = "output_amount", nullable = false)
    public float getOutputAmount() {
        return outputAmount;
    }

    public void setOutputAmount(float outputAmount) {
        this.outputAmount = outputAmount;
    }

    public String getDate() {
        return date;
    }

    @Column(name = "request_date", nullable = false)
    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "CoinEntity{" +
                "id=" + id +
                ", inputType='" + inputType + '\'' +
                ", inputAmount=" + inputAmount +
                ", outputType='" + outputType + '\'' +
                ", outputAmount=" + outputAmount +
                ", date='" + date + '\'' +
                '}';
    }
}