package com.tw.tdd.lockerrobot.domain;

import com.tw.tdd.lockerrobot.enums.LockerTypeEnum;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class SuperLockerRobot extends LockerRobot{

    public SuperLockerRobot(List<Locker> lockers) {
        super(lockers);
    }

    @Override
    public boolean isTypeMatchedTicket(Ticket ticket) {
        return ticket.getLockerType() == LockerTypeEnum.L;
    }

    @Override
    public boolean isSupportedLocker(List<Locker> lockers) {
        return !lockers.stream().anyMatch(locker -> locker.getType() != LockerTypeEnum.L);
    }

    @Override
    public Optional<Locker> getTargetLocker() {
        return lockers.stream().max(Comparator.comparingDouble(Locker::getAvaliableCapacityRate));
    }

}
