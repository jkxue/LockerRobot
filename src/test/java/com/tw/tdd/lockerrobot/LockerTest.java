package com.tw.tdd.lockerrobot;

import com.google.common.collect.ImmutableMap;
import com.tw.tdd.lockerrobot.domain.Bag;
import com.tw.tdd.lockerrobot.domain.Locker;
import com.tw.tdd.lockerrobot.domain.Ticket;
import com.tw.tdd.lockerrobot.enums.BagSizeEnum;
import com.tw.tdd.lockerrobot.enums.LockerTypeEnum;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.Map;

import static org.junit.Assert.assertNotNull;

public class LockerTest {
    private static Map<LockerTypeEnum, BagSizeEnum> TYPE_SIZE = ImmutableMap.of(
            LockerTypeEnum.S, BagSizeEnum.S,
            LockerTypeEnum.M, BagSizeEnum.M,
            LockerTypeEnum.L, BagSizeEnum.L
    );

    @ParameterizedTest
    @EnumSource(LockerTypeEnum.class)
    public void should_return_valid_ticketA_when_store_bagA_given_lockerA_capacity_is_2_and_type_is_S_or_M_or_L(
            LockerTypeEnum lockerType) {
        Locker lockerA = new Locker(lockerType, 2);
        Bag bagA = new Bag(TYPE_SIZE.get(lockerType));
        Ticket ticketA = lockerA.store(bagA);
        assertNotNull(ticketA);
    }
}
