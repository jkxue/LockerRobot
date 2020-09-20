package com.tw.tdd.lockerrobot.domain;

import com.tw.tdd.lockerrobot.enums.BagSizeEnum;
import lombok.Getter;

@Getter
public class Bag {

    private BagSizeEnum bagSize;

    public Bag(BagSizeEnum bagSize) {
        this.bagSize = bagSize;
    }
}
