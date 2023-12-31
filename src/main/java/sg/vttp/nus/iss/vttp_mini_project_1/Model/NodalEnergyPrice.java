package sg.vttp.nus.iss.vttp_mini_project_1.Model;

import com.opencsv.bean.CsvBindByPosition;

public class NodalEnergyPrice {

    @CsvBindByPosition(position = 2)
    private String date;

    @CsvBindByPosition(position = 3)
    private String period;

    @CsvBindByPosition(position = 4)
    private String price;

    public NodalEnergyPrice(String date, String period, String price) {
        this.date = date;
        this.period = period;
        this.price = price;
    }

    public NodalEnergyPrice() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "NodalEnergyPrice [date=" + date + ", period=" + period + ", price=" + price + "]";
    }

    
    
}
