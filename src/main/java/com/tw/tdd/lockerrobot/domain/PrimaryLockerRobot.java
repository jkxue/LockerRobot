package com.tw.tdd.lockerrobot.domain;

import com.tw.tdd.lockerrobot.enums.LockerTypeEnum;
import com.tw.tdd.lockerrobot.exceptions.NoAvailableSpaceException;
import com.tw.tdd.lockerrobot.exceptions.UnsupportedTypeLockerException;

import java.util.List;
import java.util.Optional;

public class PrimaryLockerRobot {
    private List<Locker> lockers;

    public PrimaryLockerRobot(List<Locker> lockers) {
        if (lockers.stream().anyMatch(locker -> locker.getType() != LockerTypeEnum.M)) {
            throw new UnsupportedTypeLockerException();
        }
        this.lockers = lockers;
    }

    public Ticket store(Bag bag) {

        Optional<Locker> targetLocker = lockers.stream().filter(locker -> locker.getAvailableSpaceNumber() > 0).findFirst();
        if(targetLocker.isPresent()){
            return targetLocker.get().store(bag);
        }else{
            throw new NoAvailableSpaceException();
        }
    }
}
