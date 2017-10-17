package com.example.dicoding.myunittesting;

/**
 * Created by dicoding on 1/26/2017.
 */

public class MainPresenter {
    private MainView view;

    public MainPresenter(MainView view) {
        this.view = view;
    }

    public double volume(double panjang, double lebar, double tinggi) {
        return panjang * lebar * tinggi;
    }


    public void hitungVolume(double panjang, double lebar, double tinggi) {
        double volume = volume(panjang, lebar, tinggi);
        MainModel model = new MainModel(volume);
        view.tampilVolume(model);
    }
}

