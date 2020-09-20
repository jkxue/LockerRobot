package com.tw.tdd.lockerrobot;

import com.tw.tdd.lockerrobot.domain.*;
import com.tw.tdd.lockerrobot.enums.BagSizeEnum;
import com.tw.tdd.lockerrobot.enums.LockerTypeEnum;
import com.tw.tdd.lockerrobot.exceptions.UnsupportedTypeLockerException;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SuperLockerRobotTest {

    @Test
    public void should_init_SuperLockerRobot_success_when_init_with_lockerA_lockerB_given_lockerA_and_lockerB_both_type_is_L() {
        Locker lockerA = new Locker(LockerTypeEnum.L, 1);
        Locker lockerB = new Locker(LockerTypeEnum.L, 1);

        SuperLockerRobot superLockerRobot = new SuperLockerRobot(Arrays.asList(lockerA, lockerB));

        assertNotNull(superLockerRobot);
    }

    @Test
    public void should_throw_error_when_init_SuperLockerRobot_given_lockerA_with_type_L_and_lockerB_with_type_M()
            throws UnsupportedTypeLockerException {
        Locker lockerA = new Locker(LockerTypeEnum.L, 1);
        Locker lockerB = new Locker(LockerTypeEnum.M, 1);

        assertThrows(UnsupportedTypeLockerException.class, () -> {
            new SuperLockerRobot(Arrays.asList(lockerA, lockerB));
        });
    }

    @Test
    public void should_return_ticketC_and_bagC_stored_in_lockerA_when_store_bagC_given_superLockerRobotA_managed_lockerA_and_lockerB_both_capacity_is_2_and_stored_a_bag() {
        Locker lockerA = new Locker(LockerTypeEnum.L, 2);
        Locker lockerB = new Locker(LockerTypeEnum.L, 2);
        lockerA.store(new Bag(BagSizeEnum.L));
        lockerB.store(new Bag(BagSizeEnum.L));
        SuperLockerRobot superLockerRobotA = new SuperLockerRobot(Arrays.asList(lockerA, lockerB));
        Bag bagC = new Bag(BagSizeEnum.L);

        Ticket ticketC = superLockerRobotA.store(bagC);

        assertNotNull(ticketC);
        assertEquals(bagC, lockerA.getBag(ticketC));
    }
}
