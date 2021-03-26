package ua.com.aircompany.synergyway.settings;


import org.springframework.context.annotation.ConfigurationClassPostProcessor;
import org.springframework.stereotype.Component;


@Component()
public class MyDate  {
    private int year;
    private int month;
    private int day;
    private int hour;
    private int min;

    public MyDate() {
    }

    public MyDate(int year, int month, int day, int hour, int min) {
        if (day >= 1 && day <= 31) {
            this.day = day;
        } else {
            System.out.println("Ви ввели не вірні дані( діапазон днів від 1 до 31 )");
        }

        if (month >= 1 && month <= 12) {
            this.month = month;
        } else {
            System.out.println("Ви ввели не вірні дані( діапазон місяців від 1 до 12 )");
        }

        if (year > 1500 && year < 2500) {
            this.year = year;
        } else {
            System.out.println("Ви ввели не вірні дані( діапазон років від 1500 до 2500)");
        }


        if (min >= 0 && min <= 59) {
            this.min = min;
        } else {
            System.out.println("Ви ввели не вірні дані( діапазон хвилин від 0 до 59 )");
        }

        if (hour >= 0 && hour <= 23) {
            this.hour = hour;
        } else {
            System.out.println("Ви ввели не вірні дані( діапазон годин від 0 до 23 )");
        }
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        if (year > 1500 && year < 2500) {
            this.year = year;
        } else {
            System.out.println("Ви ввели не вірні дані( діапазон років від 1500 до 2500)");
        }
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        if (month >= 1 && month <= 12) {
            this.month = month;
        } else {
            System.out.println("Ви ввели не вірні дані( діапазон місяців від 1 до 12 )");
        }
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        if (day >= 1 && day <= 31) {
            this.day = day;
        } else {
            System.out.println("Ви ввели не вірні дані( діапазон днів від 1 до 31 )");
        }
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        if (min >= 0 && min <= 59) {
            this.min = min;
        } else {
            System.out.println("Ви ввели не вірні дані( діапазон хвилин від 0 до 59 )");
        }

    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        if (hour >= 0 && hour <= 23) {
            this.hour = hour;
        } else {
            System.out.println("Ви ввели не вірні дані( діапазон годин від 0 до 23 )");
        }
    }


    @Override
    public String toString() {
        return "MyDate{" +
                "year=" + year +
                ", month=" + month +
                ", day=" + day +
                ", hour=" + hour +
                ", min=" + min +
                '}';
    }
}

