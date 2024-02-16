package edu.iu.habahram.weathermonitoring.model;

public class ForecastDisplay extends CurrentConditionDisplay{

    private Subject weatherData;
    private float avgTemp;
    private float maxTemp;
    private float minTemp;

    public ForecastDisplay(Subject weatherData) {
        super(weatherData);
    }

    @Override
    public String display() {
        String html = "";
        html += String.format("<div style=\"background-image: " +
                "url(/images/sky.webp); " +
                "height: 400px; " +
                "width: 647.2px;" +
                "display:flex;flex-wrap:wrap;justify-content:center;align-content:center;" +
                "\">");
        html += "<section>";
        html += String.format("<label>Avg Temp: %s</label><br />", avgTemp);
        html += String.format("<label>Max Temp: %s</label><br />", maxTemp);
        html += String.format("<label>Min Temp: %s</label>", minTemp);
        html += "</section>";
        html += "</div>";
        return html;
    }

    @Override
    public String name() {
        return "Weather Stats";
    }

    @Override
    public String id() {
        return "current-condition";
    }

    @Override
    public void update(float avg, float max, float min) {
        this.avgTemp = avg;
        this.maxTemp = max;
        this.minTemp = min;
    }

    public void subscribe() {
        weatherData.registerObserver(this);
    }

    public void unsubscribe() {
        weatherData.removeObserver(this);
    }
}
