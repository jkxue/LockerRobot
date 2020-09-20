package com.tw.tdd.lockerrobot;

import com.tw.tdd.lockerrobot.domain.*;
import com.tw.tdd.lockerrobot.enums.BagSizeEnum;
import com.tw.tdd.lockerrobot.enums.LockerTypeEnum;
import com.tw.tdd.lockerrobot.exceptions.NoAvailableSpaceException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LockerRobotManagerTest {

    @ParameterizedTest
    @EnumSource(BagSizeEnum.class)
    public void should_return_valid_ticketA_when_store_bagA_given_lockerRobotManagerA_managed_lockerA_primaryLockerRobotA_superLockerRobotA_And_all_have_available_spaces(
            BagSizeEnum bagSize) {
        Bag bagA = new Bag(bagSize);
        Locker lockerA = new Locker(LockerTypeEnum.S, 2);
        PrimaryLockerRobot primaryLockerRobotA = new PrimaryLockerRobot(
                Arrays.asList(new Locker(LockerTypeEnum.M, 2)));
        SuperLockerRobot superLockerRobot = new SuperLockerRobot(
                Arrays.asList(new Locker(LockerTypeEnum.L, 2)));
        LockerRobotManager lockerRobotManager = new LockerRobotManager(
                Arrays.asList(lockerA),
                Arrays.asList(primaryLockerRobotA),
                Arrays.asList(superLockerRobot));


        Ticket ticketA = lockerRobotManager.store(bagA);

        assertNotNull(ticketA);
    }

    @ParameterizedTest
    @EnumSource(BagSizeEnum.class)
    public void should_throw_error_when_store_bagB_with_same_size_of_bagA_given_lockerRobotManagerA_managed_lockerA_primaryLockerRobotA_superLockerRobotA_and_all_have_1_capacity_and_stored_bagA(
            BagSizeEnum bagSize) throws NoAvailableSpaceException {
        Bag bagA = new Bag(bagSize);
        Bag bagB = new Bag(bagSize);
        Locker lockerA = new Locker(LockerTypeEnum.S, 1);
        PrimaryLockerRobot primaryLockerRobotA = new PrimaryLockerRobot(
                Arrays.asList(new Locker(LockerTypeEnum.M, 1)));
        SuperLockerRobot superLockerRobot = new SuperLockerRobot(
                Arrays.asList(new Locker(LockerTypeEnum.L, 1)));
        LockerRobotManager lockerRobotManager = new LockerRobotManager(
                Arrays.asList(lockerA),
                Arrays.asList(primaryLockerRobotA),
                Arrays.asList(superLockerRobot));
        lockerRobotManager.store(bagA);

        assertThrows(NoAvailableSpaceException.class, () -> {
            lockerRobotManager.store(bagB);
        });
    }

    @ParameterizedTest
    @EnumSource(BagSizeEnum.class)
    public void should_return_bagA_when_get_bagA_with_ticketA_given_lockerRobotManagerA_managed_lockerA_primaryLockerRobotA_superLockerRobotA_and_stored_bagA(
            BagSizeEnum bagSize) throws NoAvailableSpaceException {
        Bag bagA = new Bag(bagSize);
        Locker lockerA = new Locker(LockerTypeEnum.S, 1);
        PrimaryLockerRobot primaryLockerRobotA = new PrimaryLockerRobot(
                Arrays.asList(new Locker(LockerTypeEnum.M, 1)));
        SuperLockerRobot superLockerRobot = new SuperLockerRobot(
                Arrays.asList(new Locker(LockerTypeEnum.L, 1)));
        LockerRobotManager lockerRobotManager = new LockerRobotManager(
                Arrays.asList(lockerA),
                Arrays.asList(primaryLockerRobotA),
                Arrays.asList(superLockerRobot));
        Ticket ticketA = lockerRobotManager.store(bagA);

        Bag bagFromLocker = lockerRobotManager.getBag(ticketA);

        assertEquals(bagA, bagFromLocker);
    }

}
