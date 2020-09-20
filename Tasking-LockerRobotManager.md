##  Locker Robot Manager Tasking
 # Store

   * Given lockerRobotManagerA managed lockerA and primaryLockerRobotA and superLockerRobotA
   * And all have available spaces
   * When lockerRobotManagerA stores bagA whose size is one of(S,M,L)
   * Then get valid ticketA
   --------------  
   * Given lockerRobotManagerA managed lockerA and primaryLockerRobotA and superLockerRobotA
   * And all have one capacity
   * And stored bagA whose size is one of(S,M,L)
   * When lockerRobotManagerA store bagB whose size is same with bagA
   * Then get 'No available space' error 
   
 # Pickup

   * Given lockerRobotManagerA managed lockerA and primaryLockerRobotA and superLockerRobotA
   * And stored bagA whose size is one of(S,M,L)
   * When lockerRobotManagerA get bag with ticketA
   * Then get bagA successfully
    
   --------------  
   * Given lockerRobotManagerA managed lockerA and primaryLockerRobotA and superLockerRobotA
   * And a fake ticketA
   * When lockerRobotManagerA get bag with ticketA
   * Then get 'Invalid ticket' error
   
   
