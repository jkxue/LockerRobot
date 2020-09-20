package com.tw.tdd.lockerrobot;

import com.tw.tdd.lockerrobot.domain.Locker;
import com.tw.tdd.lockerrobot.domain.PrimaryLockerRobot;
import com.tw.tdd.lockerrobot.enums.LockerTypeEnum;
import com.tw.tdd.lockerrobot.exceptions.UnsupportedTypeLockerException;
import org.junit.Test;
import java.util.Arrays;

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
}
