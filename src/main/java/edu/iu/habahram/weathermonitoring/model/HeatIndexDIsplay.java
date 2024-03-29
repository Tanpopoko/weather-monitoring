package edu.iu.habahram.weathermonitoring.model;

public class HeatIndexDIsplay extends CurrentConditionDisplay {
    private Subject weatherData;
    private float temperature;
    private float humidity;
    private float heatIndex;

    public HeatIndexDIsplay(Subject weatherData) {
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
        html += String.format("<label>Temperature: %s</label><br />", temperature);
        html += String.format("<label>Humidity: %s</label><br />", humidity);
        html += String.format("<label>Heat Index: %s</label>", heatIndex);
        html += "</section>";
        html += "</div>";
        return html;
    }

    @Override
    public String name() {
        return "Heat Index Stats";
    }

    @Override
    public String id() {
        return "current-condition";
    }

    @Override
    public void update(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.heatIndex = computeHeatIndex(temperature, humidity);
    }

    private float computeHeatIndex(float t, float rh) {
        float index = (float)((16.923 + (0.185212 * t) + (5.37941 * rh) - (0.100254 * t * rh) +
                (0.00941695 * (t * t)) + (0.00728898 * (rh * rh)) +
                (0.000345372 * (t * t * rh)) - (0.000814971 * (t * rh * rh)) +
                (0.0000102102 * (t * t * rh * rh)) - (0.000038646 * (t * t * t)) + (0.0000291583 *
                (rh * rh * rh)) + (0.00000142721 * (t * t * t * rh)) +
                (0.000000197483 * (t * rh * rh * rh)) - (0.0000000218429 * (t * t * t * rh * rh)) +
                0.000000000843296 * (t * t * rh * rh * rh)) -
                (0.0000000000481975 * (t * t * t * rh * rh * rh)));
        return index;
    }

    public void subscribe() {
        weatherData.registerObserver(this);
    }

    public void unsubscribe() {
        weatherData.removeObserver(this);
    }
}
