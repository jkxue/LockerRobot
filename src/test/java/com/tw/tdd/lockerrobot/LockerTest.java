package com.tw.tdd.lockerrobot;

import com.google.common.collect.ImmutableMap;
import com.tw.tdd.lockerrobot.domain.Bag;
import com.tw.tdd.lockerrobot.domain.Locker;
import com.tw.tdd.lockerrobot.domain.Ticket;
import com.tw.tdd.lockerrobot.enums.BagSizeEnum;
import com.tw.tdd.lockerrobot.enums.LockerTypeEnum;
import com.tw.tdd.lockerrobot.exceptions.NoAvailableSpaceException;
import com.tw.tdd.lockerrobot.exceptions.TypeNotMatchException;
import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

    @ParameterizedTest
    @EnumSource(LockerTypeEnum.class)
    public void should_throw_error_when_store_bagB_given_lockerA_capacity_is_1_and_type_is_S_or_M_or_L_stored_bagA (
            LockerTypeEnum lockerType ) throws NoAvailableSpaceException {

        Locker lockerA = new Locker(lockerType, 1);
        Bag bagA = new Bag(TYPE_SIZE.get(lockerType));
        lockerA.store(bagA);
        Bag bagB = new Bag(TYPE_SIZE.get(lockerType));

        assertThrows(NoAvailableSpaceException.class, () -> {
            lockerA.store(bagB);
        });
    }

    @ParameterizedTest
    @EnumSource(LockerTypeEnum.class)
    public void should_return_bagA_when_get_bagA_with_ticketA_given_lockerA_capacity_is_1_and_type_is_S_or_M_or_L_stored_bagA (
            LockerTypeEnum lockerType ) {

        Locker lockerA = new Locker(lockerType, 1);
        Bag bagA = new Bag(TYPE_SIZE.get(lockerType));
        Ticket ticketA = lockerA.store(bagA);

        Bag bagFromLocker = lockerA.getBag(ticketA);

        assertEquals(bagA, bagFromLocker);
    }

    @Test
    public void should_throw_error_when_get_bagA_with_ticketA_from_lockerB_given_lockerA_type_is_S_stored_bagA_lockerB_type_is_M_stored_bagB ()
            throws TypeNotMatchException {

        Locker lockerA = new Locker(LockerTypeEnum.S, 1);
        Bag bagA = new Bag(BagSizeEnum.S);
        Ticket ticketA = lockerA.store(bagA);

        Locker lockerB = new Locker(LockerTypeEnum.M, 1);
        Bag bagB = new Bag(BagSizeEnum.M);
        lockerB.store(bagB);

        assertThrows(TypeNotMatchException.class, () -> {
            lockerB.getBag(ticketA);
        });
    }
}
