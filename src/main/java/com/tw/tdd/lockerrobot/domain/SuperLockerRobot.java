package com.tw.tdd.lockerrobot.domain;

import com.tw.tdd.lockerrobot.enums.LockerTypeEnum;
import com.tw.tdd.lockerrobot.exceptions.UnsupportedTypeLockerException;

import java.util.List;

public class SuperLockerRobot {
    private List<Locker> lockers;

    public SuperLockerRobot(List<Locker> lockers) {
        if (lockers.stream().anyMatch(locker -> locker.getType() != LockerTypeEnum.L)) {
            throw new UnsupportedTypeLockerException();
        }
        this.lockers = lockers;
    }

}
