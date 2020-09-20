package com.tw.tdd.lockerrobot;

import com.google.common.collect.ImmutableMap;
import com.tw.tdd.lockerrobot.domain.Bag;
import com.tw.tdd.lockerrobot.domain.Locker;
import com.tw.tdd.lockerrobot.domain.Ticket;
import com.tw.tdd.lockerrobot.enums.BagSizeEnum;
import com.tw.tdd.lockerrobot.enums.LockerTypeEnum;
import com.tw.tdd.lockerrobot.exceptions.NoAvailableSpaceException;
import org.junit.Rule;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.rules.ExpectedException;

import java.util.Map;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LockerTest {
    private static Map<LockerTypeEnum, BagSizeEnum> TYPE_SIZE = ImmutableMap.of(
            LockerTypeEnum.S, BagSizeEnum.S,
            LockerTypeEnum.M, BagSizeEnum.M,
            LockerTypeEnum.L, BagSizeEnum.L
    );

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

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
}
