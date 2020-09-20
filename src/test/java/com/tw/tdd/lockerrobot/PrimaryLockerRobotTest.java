package com.tw.tdd.lockerrobot;

import com.tw.tdd.lockerrobot.domain.Locker;
import com.tw.tdd.lockerrobot.domain.PrimaryLockerRobot;
import com.tw.tdd.lockerrobot.enums.LockerTypeEnum;
import org.junit.Test;
import java.util.Arrays;

import static org.junit.Assert.assertNotNull;

public class PrimaryLockerRobotTest {

    @Test
    public void should_init_PrimaryLockerRobot_success_when_init_with_lockerA_lockerB_given_lockerA_and_lockerB_both_type_is_M() {
        Locker lockerA = new Locker(LockerTypeEnum.M,1);
        Locker lockerB = new Locker(LockerTypeEnum.M,1);

        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(Arrays.asList(lockerA,lockerB));

        assertNotNull(primaryLockerRobot);
    }
}
