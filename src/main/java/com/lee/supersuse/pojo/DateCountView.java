package com.lee.supersuse.pojo;

public class DateCountView {

    private Integer allConut;

    private Integer MonthCont;

    private Integer weekConut;

    private Integer peopleConut;

    public Integer getAllConut() {
        return allConut;
    }

    @Override
    public String toString() {
        return "DateCountView{" +
                "allConut=" + allConut +
                ", MonthCont=" + MonthCont +
                ", weekConut=" + weekConut +
                ", peopleConut=" + peopleConut +
                '}';
    }

    public void setAllConut(Integer allConut) {
        this.allConut = allConut;
    }

    public Integer getMonthCont() {
        return MonthCont;
    }

    public void setMonthCont(Integer monthCont) {
        MonthCont = monthCont;
    }

    public Integer getWeekConut() {
        return weekConut;
    }

    public void setWeekConut(Integer weekConut) {
        this.weekConut = weekConut;
    }

    public Integer getPeopleConut() {
        return peopleConut;
    }

    public void setPeopleConut(Integer peopleConut) {
        this.peopleConut = peopleConut;
    }
}
