## Locker Tasking
 
 # Store
 
  * Given lockerA whose capacity is 2 and type is one of (S,M,L)
  * When store bagA whose size matched the type of lockerA
  * Then store successfully
  * And get valid ticketA
  
  --------------
   
  * Given lockerA whose capacity is 1 and type is one of (S,M,L) stored a bagA
  * When store bagB whose size matched the type of lockerA
  * Then store failed
  * And get 'No available space' error 

 # Pickup

  * Given lockerA whose type is one of (S,M,L) stored a bagA
  * And ticketA of bagA
  * When get bagA with ticketA
  * Then get bagA
  
  --------------
  
  * Given lockerA whose type is S stored a bagA
  * And lockerB whose type is M stored a bagB
  * When get bag with ticketA from lockerB
  * Then get 'Type not match' error 
  
  --------------
  
  * Given lockerA whose type is one of (S,M,L) stored a bagA
  * And a fake ticketB 
  * When get bag with ticketB
  * Then get 'Invalid ticket' error
