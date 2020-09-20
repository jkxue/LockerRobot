## Super Locker Robot Tasking
 # Store

   * Given lockerA and lockerB, both type is L
   * When init superLockerRobotA with lockerA and lockerB
   * Then initialization succeeded
   --------------    
   * Given lockerA with type L and lockerB with type is M
   * When init superLockerRobotA with lockerA and lockerB
   * Then get 'Unsupported type locker' error
   -------------- 
   * Given superLockerRobotA managed lockerA whose capacity is 2 stored bagA
   * And lockerB whose capacity is 2 stored bagB
   * When superLockerRobotA stores bagC
   * Then get valid ticketC
   * And bagA is stored in lockerA
   --------------  
   * Given superLockerRobotA managed lockerA whose capacity is 2 stored bagA
   * And lockerB whose capacity is 3 stored bagB
   * When superLockerRobotA stores bagC
   * Then get valid ticketC
   * And bagA is stored in lockerB
   --------------  
   * Given superLockerRobotA managed lockerA and lockerB have no available space
   * When superLockerRobotA store bagA
   * Then get 'No available space' error 
   
 # Pickup

   * Given superLockerRobotA managed lockerA lockerB, lockerB stored a bagB with ticketB
   * When superLockerRobotA get bag with ticketB
   * Then get bagB successfully
   -------------- 
   * Given superLockerRobotA managed lockerA lockerB, lockerB stored a bagB 
   * And ticketA whose locker type is not L
   * When superLockerRobotA get bag with ticketA
   * Then get 'Type not match' error    
   --------------  
   * Given superLockerRobotA managed lockerA lockerB 
   * And a fake ticketA
   * When superLockerRobotA get bag with ticketA
   * Then get 'Invalid ticket' error
   
   
