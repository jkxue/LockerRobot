package com.tw.tdd.lockerrobot;

import com.tw.tdd.lockerrobot.domain.*;
import com.tw.tdd.lockerrobot.enums.BagSizeEnum;
import com.tw.tdd.lockerrobot.enums.LockerTypeEnum;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.Arrays;

import static org.junit.Assert.assertNotNull;

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

}
