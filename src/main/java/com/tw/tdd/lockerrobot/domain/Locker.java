package com.tw.tdd.lockerrobot.domain;

import com.tw.tdd.lockerrobot.enums.LockerTypeEnum;
import lombok.Getter;

@Getter
public class Locker {

    private LockerTypeEnum type;
    private Integer capacity;

    public Locker(LockerTypeEnum type, int capacity) {
        this.type = type;
        this.capacity = capacity;
    }

    public Ticket store(Bag bag) {
        return new Ticket();
    }
}
