package com.example.mdbspringboot.model;

import org.springframework.data.mongodb.core.mapping.Field;

public class NutritionalInfo {

    @Field("calories")
    private int calories;

    @Field("fat_grams")
    private double fatGrams;

    @Field("protein_grams")
    private double proteinGrams;

    public NutritionalInfo(int calories, double fatGrams, double proteinGrams) {
        this.calories = calories;
        this.fatGrams = fatGrams;
        this.proteinGrams = proteinGrams;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public double getFatGrams() {
        return fatGrams;
    }

    public void setFatGrams(double fatGrams) {
        this.fatGrams = fatGrams;
    }

    public double getProteinGrams() {
        return proteinGrams;
    }

    public void setProteinGrams(double proteinGrams) {
        this.proteinGrams = proteinGrams;
    }
}