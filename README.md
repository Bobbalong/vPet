# Virtual Pet

With this project I will try to create a virtual environment for a simulated pet.
	
The pet should have at least a set of stats and or skills relevant to bodily functions and requirements. The pet stats will fill state
conditions and this will generate pet actions the user can interact with. Pet stats will generate need requests the user will be encoraged to complete. Requirements
for the creation of virtual pet will be gathered from the user and modulated by the system to create a base pet. After generation the 
states of a pet will be moddified by the conditions set by the enviroment. Pet or user actions will futher affect the pet stats.

The environment will be a way for the user to affect the pet. Stats generated by the environment will affect the pet stats and cause adjustment.

## Currently implemented

#### Stomach
   The stomach is used to simulate hunger. It is a depleating stat that can be increased to a max with food. It has a modifier, affected by feeding, that decides if the pet is over or under weight. This modifier is used to calculate the returns from feeding and the rate of stomach depletion. There is a tolerance associated with this stat, it is used to decided the scale of increments.
	
#### Bowel
   Bowel is a resetting value used to generate poo. The increment it uses is affected by the stomach modifier. When the bowel value reaches a threshold a poo is spawed and the value resets. These will affect hygiene.
	
#### Hygiene

#### Energy

#### Pet Sim
   The pet sim is a class that holds the calculators for affecting the pet, and triggers the required calculations given the current conditions.
