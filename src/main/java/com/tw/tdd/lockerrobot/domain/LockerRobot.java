package com.tw.tdd.lockerrobot.domain;


import com.tw.tdd.lockerrobot.exceptions.InvalidTicketException;
import com.tw.tdd.lockerrobot.exceptions.NoAvailableSpaceException;
import com.tw.tdd.lockerrobot.exceptions.TypeNotMatchException;
import com.tw.tdd.lockerrobot.exceptions.UnsupportedTypeLockerException;

import java.util.List;
import java.util.Optional;

public abstract class LockerRobot {
    protected List<Locker> lockers;

    public LockerRobot(List<Locker> lockers) {

        if (isSupportedLocker(lockers)) {
            this.lockers = lockers;
        }else{
            throw new UnsupportedTypeLockerException();
        }
    }

    public Ticket store(Bag bag) {
        Optional<Locker> targetLocker = getTargetLocker();
        if (targetLocker.isPresent()) {
            return targetLocker.get().store(bag);
        } else {
            throw new NoAvailableSpaceException();
        }
    }

    public Bag getBag(Ticket ticket) {
        if(isTypeMatchedTicket(ticket)){
            Optional<Locker> targetLocker = lockers.stream().filter(locker -> locker.exist(ticket)).findFirst();
            if(targetLocker.isPresent()){
                return targetLocker.get().getBag(ticket);
            }else{
                throw new InvalidTicketException();
            }
        }else{
            throw new TypeNotMatchException();
        }
    }

    public abstract boolean isTypeMatchedTicket(Ticket ticket);
    public abstract boolean isSupportedLocker(List<Locker> lockers);
    public abstract Optional<Locker> getTargetLocker();

}
