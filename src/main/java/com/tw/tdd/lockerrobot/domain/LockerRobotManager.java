package com.tw.tdd.lockerrobot.domain;

import com.tw.tdd.lockerrobot.enums.BagSizeEnum;
import com.tw.tdd.lockerrobot.enums.LockerTypeEnum;
import com.tw.tdd.lockerrobot.exceptions.InvalidTicketException;
import com.tw.tdd.lockerrobot.exceptions.NoAvailableSpaceException;

import java.util.List;
import java.util.Optional;

public class LockerRobotManager {
    private List<Locker> lockers;
    private List<PrimaryLockerRobot> primaryLockerRobots;
    private List<SuperLockerRobot> superLockerRobots;

    public LockerRobotManager(List<Locker> lockers, List<PrimaryLockerRobot> primaryLockerRobots, List<SuperLockerRobot> superLockerRobots) {
        this.lockers = lockers;
        this.primaryLockerRobots = primaryLockerRobots;
        this.superLockerRobots = superLockerRobots;
    }

    public Ticket store(Bag bag) {
        if (bag.getBagSize() == BagSizeEnum.S) {
            Optional<Locker> targetLocker = lockers.stream().filter(locker -> locker.getAvailableSpaceNumber() > 0).findFirst();
            if(targetLocker.isPresent()){
                return targetLocker.get().store(bag);
            }
        } else if (bag.getBagSize() == BagSizeEnum.M) {
            Optional<PrimaryLockerRobot> targetPLR = primaryLockerRobots.stream().filter(primaryLockerRobot -> primaryLockerRobot.getTargetLocker().isPresent()).findFirst();
            if(targetPLR.isPresent()){
                return targetPLR.get().store(bag);
            }
        } else {
            Optional<SuperLockerRobot> targetSLR = superLockerRobots.stream().filter(superLockerRobot -> superLockerRobot.getTargetLocker().isPresent()).findFirst();
            if(targetSLR.isPresent()){
                return targetSLR.get().store(bag);
            }
        }
        throw new NoAvailableSpaceException();
    }

    public Bag getBag(Ticket ticket) {
        if (ticket.getLockerType() == LockerTypeEnum.S) {
            Optional<Locker> targetLocker = lockers.stream().filter(locker -> locker.exist(ticket)).findFirst();
            if(targetLocker.isPresent()){
                return targetLocker.get().getBag(ticket);
            }
        } else if(ticket.getLockerType() == LockerTypeEnum.M) {
            Optional<PrimaryLockerRobot> targetPLR = primaryLockerRobots.stream().filter(pr ->pr.exist(ticket)).findFirst();
            if(targetPLR.isPresent()){
                return targetPLR.get().getBag(ticket);
            }
        } else {
            Optional<SuperLockerRobot> targetSLR = superLockerRobots.stream().filter(sr ->sr.exist(ticket)).findFirst();
            if(targetSLR.isPresent()){
                return targetSLR.get().getBag(ticket);
            }
        }
        throw new InvalidTicketException();
    }
}
