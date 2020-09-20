package com.tw.tdd.lockerrobot;

import com.tw.tdd.lockerrobot.domain.Bag;
import com.tw.tdd.lockerrobot.domain.Locker;
import com.tw.tdd.lockerrobot.domain.PrimaryLockerRobot;
import com.tw.tdd.lockerrobot.domain.Ticket;
import com.tw.tdd.lockerrobot.enums.BagSizeEnum;
import com.tw.tdd.lockerrobot.enums.LockerTypeEnum;
import com.tw.tdd.lockerrobot.exceptions.NoAvailableSpaceException;
import com.tw.tdd.lockerrobot.exceptions.UnsupportedTypeLockerException;
import org.junit.Test;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PrimaryLockerRobotTest {

    @Test
    public void should_init_PrimaryLockerRobot_success_when_init_with_lockerA_lockerB_given_lockerA_and_lockerB_both_type_is_M() {
        Locker lockerA = new Locker(LockerTypeEnum.M,1);
        Locker lockerB = new Locker(LockerTypeEnum.M,1);

        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(Arrays.asList(lockerA,lockerB));

        assertNotNull(primaryLockerRobot);
    }

    @Test
    public void should_throw_error_when_init_PrimaryLockerRobot_given_lockerA_with_type_M_and_lockerB_with_type_L()
            throws UnsupportedTypeLockerException {
        Locker lockerA = new Locker(LockerTypeEnum.M,1);
        Locker lockerB = new Locker(LockerTypeEnum.L,1);

        assertThrows(UnsupportedTypeLockerException.class, () -> {
            new PrimaryLockerRobot(Arrays.asList(lockerA,lockerB));
        });
    }

    @Test
    public void should_return_ticketA_and_bagA_stored_correctly_when_store_bagA_given_primaryLockerRobotA_managed_lockerA_and_lockerB_both_lockers_have_available_spaces(){
        Locker lockerA = new Locker(LockerTypeEnum.M,12);
        Locker lockerB = new Locker(LockerTypeEnum.M,12);
        PrimaryLockerRobot primaryLockerRobotA = new PrimaryLockerRobot(Arrays.asList(lockerA, lockerB));
        Bag bagA = new Bag(BagSizeEnum.M);

        Ticket ticketA = primaryLockerRobotA.store(bagA);

        assertNotNull(ticketA);
        assertEquals(bagA, lockerA.getBag(ticketA));
    }

    @Test
    public void should_return_ticketA_and_bagA_stored_correctly_when_store_bagA_given_PrimaryLockerRobot_manage_LockerA_and_LockerB_only_LockerB_has_available_spaces() {
        Locker lockerA = new Locker(LockerTypeEnum.M,1);
        Locker lockerB = new Locker(LockerTypeEnum.M,12);
        lockerA.store(new Bag(BagSizeEnum.M));
        Bag bagB = new Bag(BagSizeEnum.M);
        PrimaryLockerRobot primaryLockerRobotA = new PrimaryLockerRobot(Arrays.asList(lockerA, lockerB));

        Ticket ticketB = primaryLockerRobotA.store(bagB);

        assertNotNull(ticketB);
        assertEquals(bagB, lockerB.getBag(ticketB));
    }

    @Test
    public void should_throw_error_when_store_bagA_given_PrimaryLockerRobot_managed_lockerA_and_lockerB_both_has_no_available_spaces()throws NoAvailableSpaceException {
        Locker lockerA = new Locker(LockerTypeEnum.M,1);
        Locker lockerB = new Locker(LockerTypeEnum.M,1);
        lockerA.store(new Bag(BagSizeEnum.M));
        lockerB.store(new Bag(BagSizeEnum.M));
        Bag bagA = new Bag(BagSizeEnum.M);
        PrimaryLockerRobot primaryLockerRobotA = new PrimaryLockerRobot(Arrays.asList(lockerA, lockerB));

        assertThrows(NoAvailableSpaceException.class, () -> {
            primaryLockerRobotA.store(bagA);
        });
    }
}
