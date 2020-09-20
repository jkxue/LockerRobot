## Primary Locker Robot Tasking
 # Store

   * Given lockerA and lockerB, both type is M
   * When init primaryLockerRobotA with lockerA and lockerB
   * Then initialization succeeded
   --------------    
   * Given lockerA with type M and lockerB with type is L
   * When init primaryLockerRobotA with lockerA and lockerB
   * Then get 'Unsupported type locker' error
   -------------- 
   * Given primaryLockerRobotA managed lockerA and lockerB, both type is M, both have available spaces
   * When primaryLockerRobotA stores bagA
   * Then get valid ticketA
   * And bagA is stored in lockerA
   --------------  
   * Given primaryLockerRobotA managed lockerA and lockerB, both type is M, only lockerB has available spaces
   * When primaryLockerRobotA store bagA
   * Then get valid ticketB
   * And bagA is stored in lockerB
   --------------  
   * Given primaryLockerRobotA managed lockerA and lockerB, both lockers have no available space
   * When primaryLockerRobotA store bagA
   * Then get 'No available space' error 
   
 # Pickup

   * Given primaryLockerRobotA managed lockerA lockerB, lockerB stored a bagB with ticketB
   * When primaryLockerRobotA get bag with ticketB
   * Then get bagB successfully
   -------------- 
   * Given primaryLockerRobotA managed lockerA lockerB, lockerB stored a bagB 
   * And ticketA whose locker type is not M
   * When primaryLockerRobotA get bag with ticketA
   * Then get 'Type not match' error    
   --------------  
   * Given primaryLockerRobotA managed lockerA lockerB 
   * And a fake ticketA
   * When primaryLockerRobotA get bag with ticketA
   * Then get 'Invalid ticket' error
   
   
