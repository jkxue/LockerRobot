package com.tw.tdd.lockerrobot.domain;

import com.tw.tdd.lockerrobot.enums.BagSizeEnum;

import java.util.List;

public class LockerRobotManager{
    private List<Locker> lockers;
    private List<PrimaryLockerRobot> primaryLockerRobots;
    private List<SuperLockerRobot> superLockerRobots;

    public LockerRobotManager(List<Locker> lockers, List<PrimaryLockerRobot> primaryLockerRobots, List<SuperLockerRobot> superLockerRobots) {
        this.lockers = lockers;
        this.primaryLockerRobots = primaryLockerRobots;
        this.superLockerRobots = superLockerRobots;
    }

    public Ticket store(Bag bag) {
        if(bag.getBagSize() == BagSizeEnum.S){
            return lockers.get(0).store(bag);
        }else if(bag.getBagSize() == BagSizeEnum.M){
            return primaryLockerRobots.get(0).store(bag);
        }else{
            return superLockerRobots.get(0).store(bag);
        }
    }
}
