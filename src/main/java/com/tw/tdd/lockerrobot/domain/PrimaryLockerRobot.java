package com.tw.tdd.lockerrobot.domain;

import com.tw.tdd.lockerrobot.enums.LockerTypeEnum;
import com.tw.tdd.lockerrobot.exceptions.UnsupportedTypeLockerException;

import java.util.List;

public class PrimaryLockerRobot {
    private List<Locker> lockers;

    public PrimaryLockerRobot(List<Locker> lockers) {
        if(lockers.stream().anyMatch(locker -> locker.getType() != LockerTypeEnum.M)){
            throw new UnsupportedTypeLockerException();
        }
        this.lockers = lockers;
    }

    public Ticket store(Bag bagA) {
        return lockers.get(0).store(bagA);
    }
}
