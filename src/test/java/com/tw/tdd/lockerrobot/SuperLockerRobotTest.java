package com.tw.tdd.lockerrobot;

import com.tw.tdd.lockerrobot.domain.Locker;
import com.tw.tdd.lockerrobot.domain.SuperLockerRobot;
import com.tw.tdd.lockerrobot.enums.LockerTypeEnum;
import com.tw.tdd.lockerrobot.exceptions.UnsupportedTypeLockerException;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SuperLockerRobotTest {

    @Test
    public void should_init_SuperLockerRobot_success_when_init_with_lockerA_lockerB_given_lockerA_and_lockerB_both_type_is_L() {
        Locker lockerA = new Locker(LockerTypeEnum.L, 1);
        Locker lockerB = new Locker(LockerTypeEnum.L, 1);

        SuperLockerRobot primaryLockerRobot = new SuperLockerRobot(Arrays.asList(lockerA, lockerB));

        assertNotNull(primaryLockerRobot);
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
}
