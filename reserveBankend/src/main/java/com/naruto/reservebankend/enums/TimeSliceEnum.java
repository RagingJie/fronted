package com.naruto.reservebankend.enums;

public enum TimeSliceEnum {

    ONE("6zund8ga", "7.30-8.30"),
    TWO("ygw8b2td", "8.30-10.30"),
    THREE("tr5vgygw", "10.30-12.00"),
    FOUR("nyokqqf9", "12-13"),
    FIVE("1vjc5rkn", "13-15"),
    SIX("rrjy50as", "15-17"),
    SEVEN("b2pr09kj", "17-19"),
    EIGHT("ulg7wwgd", "19-21"),
    NINE("ri6mnmbp", "21-22"),
    TEN("4i06d63h", "22-23");

    String id;
    String name;

    TimeSliceEnum(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
