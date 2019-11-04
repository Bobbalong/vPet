# vPet




## Class, constructor and method descriptions.

###### vPetGui

vPetGui()
Used to call clock(), vPetAnim() and initialize().
    
main()
initilizes vPetGui
(?)initializes saveStates (needs moved).
      
clock()
Initialise clock thread - used to regulate some checks to a regular time (ms) interval.
    
vPetAnim()
Initializes vPetAnim thread - used to cycle GUI images at a given 'framerate'.
    
initialize()
Build GUI.
        
###### vPetSrc

vPetSrc()
Initializes a pet dependant on given parameters.
      
###### saving

prep()
Adds to array current states to be saved.
    
writeState()
append given parameter to save.txt.
    
save()
Cycles through saveStates array and uses writeState() with the corresponding value.
    
readSave()
Takes 'C' or 'L' parameter.
'C' is used to read save.txt and add each line to an array.
'L' is used to distrobute array from 'C' to the corresponding variables.
    
###### vPetEvents

creation()
Takes 'E' or 'C' parameter.
'E' is used to add to an array user values related to pet generation.
'C' is used to create a pet by passing array values to vPetSrc().
    
hungerTick()
A calculator used for adjusting variables related to pet stomach dependant on current situation.
    
clean()
Decreases variables related to hygiene dependant on current situation.
    
dirtTick()
A calculator used for adjusting variables related to pet hygiene dependant on current situation.
  
intro() *Jank*
Check current setup and progresses as needed.
**Currently called in clock thread.**
    
milestone() *Jank* *trial/temp*
A calculator used for adjusting variables related to event dependant on current time.
    
eventCalc() *Jank*
Used to pass time to milestone.
    

###### Tools
  
###### Animation
  
hasBread()
idle()
blink()
stink()
These are called by the vPetAnim thread and change images based on situation and 'Frame'
