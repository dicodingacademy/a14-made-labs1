package com.dicoding.picodiploma.myunittest;

// Cuboid == Balok
class CuboidModel {
    private double length;
    private double width;
    private double height;

    public CuboidModel() {
    }

    public void save(double length, double width, double height) {
        this.length = length;
        this.width = width;
        this.height = height;
    }

    double getVolume() {
        return length * width * height;
    }

    public double getSurfaceArea() {
        double lw = length * width;
        double wh = width * height;
        double lh = length * height;

        return 2 * (lw + wh + lh);
    }

    public double getCircumference() {
        return 4 * (length + width + height);
    }
}