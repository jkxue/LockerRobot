package com.tw.tdd.lockerrobot.domain;

import com.tw.tdd.lockerrobot.enums.LockerTypeEnum;
import com.tw.tdd.lockerrobot.exceptions.InvalidTicketException;
import com.tw.tdd.lockerrobot.exceptions.TypeNotMatchException;

import java.util.List;
import java.util.Optional;

public class PrimaryLockerRobot extends LockerRobot{

    public PrimaryLockerRobot(List<Locker> lockers) {
        super(lockers);
    }

    @Override
    public boolean isSupportedLocker(List<Locker> lockers) {
        return !lockers.stream().anyMatch(locker -> locker.getType() != LockerTypeEnum.M);
    }

    @Override
    public Optional<Locker> getTargetLocker() {
        return this.lockers.stream().filter(locker -> locker.getAvailableSpaceNumber() > 0).findFirst();
    }

    @Override
    public boolean isTypeMatchedTicket(Ticket ticket) {
        return ticket.getLockerType() == LockerTypeEnum.M;
    }
}
