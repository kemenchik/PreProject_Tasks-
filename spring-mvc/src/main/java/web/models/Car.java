package web.models;

public class Car {
    private String model;
    private int series;
    private int wheelCount;

    public Car(String model, int series, int wheelCount) {
        this.model = model;
        this.series = series;
        this.wheelCount = wheelCount;
    }

    public Car() {
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getSeries() {
        return series;
    }

    public void setSeries(int series) {
        this.series = series;
    }

    public int getWheelCount() {
        return wheelCount;
    }

    public void setWheelCount(int wheelCount) {
        this.wheelCount = wheelCount;
    }
}
