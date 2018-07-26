package com.dicoding.picodiploma.myunittesting;

/**
 * Created by dicoding on 1/26/2017.
 */

public class MainPresenter {
    private MainView view;

    MainPresenter(MainView view) {
        this.view = view;
    }

    public double volume(double length, double width, double height) {
        return length * width * height;
    }

    public void hitungVolume(double length, double width, double height) {
        double volume = volume(length, width, height);
        MainModel model = new MainModel(volume);
        view.showVolume(model);
    }
}