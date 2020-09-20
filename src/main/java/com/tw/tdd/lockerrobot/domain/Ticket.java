package com.tw.tdd.lockerrobot.domain;

import com.tw.tdd.lockerrobot.enums.LockerTypeEnum;
import lombok.Getter;

@Getter
public class Ticket {
    private LockerTypeEnum lockerType;

    public Ticket(LockerTypeEnum lockerType){
        this.lockerType = lockerType;
    }
}
