package WhatToDo;

public class Location {
	
	boolean waterBlock = false;
	boolean captain = false;
	boolean awake = false;
	
	boolean deepWoods = true;
	boolean moonlitGrove = false;
	boolean shore = false;
	boolean outsideMansion = false;
	boolean eastCliffs = false;
	boolean southCliffs = false;
	boolean hut = false;
	boolean scorchedBeach = false;
	boolean grotto = false;
	boolean mansion = false;
	
	boolean useCandle = false;
	boolean useLadder = false;
	boolean useTarp = false;
	boolean useMatch = false;
	boolean useAshes = false;
	boolean useBranch = false;
	boolean useMonkey = false;
	
	boolean ladderRemains = true;
	boolean tarpRemains = true;
	boolean matchRemains = true;
	boolean ashesRemain = true;
	boolean branchRemains = true;
	boolean monkeyRemains = true;
	boolean keyRemains = true;
	
	public void observe() {
		if(deepWoods == true) {
			System.out.println("The forest is dense in every direction.");
			if(tarpRemains) {
			System.out.println("Some sort of tarp hangs from a branch a ways above you.");
			}
		}
		if(moonlitGrove == true) {
			System.out.println("A large, hollowed out tree stump stands alone where the moonlight is brightest.");
			if(monkeyRemains) {
			System.out.println("Upon closer inspection, a small creature not unlike a monkey has made its home in the trunk.");
			}
		}
		if(shore == true) {
			System.out.println("The coastline curves back South to both the East and West. The sea all around you is quiet and eerie.");
			if(useBranch && useTarp && captain) {
				System.out.println("Your pseudo captain and makeshift boat stand at the ready.");
			}
			else {
				System.out.println("There isn't much around but the old boat.");
			}
		}
		if(outsideMansion == true) {
			System.out.println("The house looks absolutely new, somehow unblemished by the sea or time.");
		}
		if(eastCliffs == true) {
			System.out.println("Cliffs loom over you to the East and the South.");
			System.out.println("The broken tree remnants are various shapes and sizes.");
		}
		if(southCliffs == true) {
			System.out.println("A stone gate in the middle of the stream is open, allowing water to flow freely.");
			System.out.println("There is something like a handle on one of the gate's posts.");
		}
		if(hut == true) {
			if(!captain && !awake) {
					System.out.println("Upon closer inspection, the man is (disappointingly) most definitely asleep.");
					System.out.println("Various assumed belongings are littered around him and in the yurt.");
			}
			else if(!captain && awake) {
				System.out.println("The old man's gaze is intimidating, but you need to speak with him nonetheless.");
				}
			else if(captain) {
				System.out.println("You look upon the recently abandoned home and wonder many things.");
			}
		}
		if(scorchedBeach == true) {
			System.out.println("Nothing of note can be seen among the piles of ash and what once were trees.");
			System.out.println("The smell of campfire mixes pleasantly with the salt of the sea.");
		}
		if(grotto == true) {
			if(!waterBlock) {
				System.out.println("You can vaguely make out the glint of an object deep below the surface.");
			}
			else if(waterBlock) {
				System.out.println("An ornate set of metal scales sit upon a podium on the far side of the cavern.");
				System.out.println("One side is weighed down by a pouch full of powder, and the other side bears a similar (though empty) pouch.");
			}
		}
		if(mansion == true) {
			System.out.println("The mansion is completely abandoned, but as clean as can be.");
			if(ladderRemains) {
				System.out.println("It is nearly devoid of any furniture aside from a glass lamp on a lonely table in the main room,\nas well as a ladder with the storage room all to itself.");

			}
			else if(!ladderRemains) {
				System.out.println("It is nearly devoid of any furniture aside from a glass lamp on a lonely table in the main room.");
			}
		}
	}
	public void action() {
		
	}
	public void north() {
		if(deepWoods == true) {
			System.out.println("You walk North for a while until you arrive at a small clearing well lit by the moon.");
			deepWoods = false;
			moonlitGrove = true;
		}
		else if(moonlitGrove == true) {
				System.out.println("Heading North takes you out of the woods and onto a shore.");
			if(!useBranch) {
				System.out.println("A small boat (in severe disrepair) is beached in front of you.");
			}
			else if(useBranch) {
				System.out.println("A small boat with a makeshift mast is beached in front of you.");
			}
			else if(useTarp) {
				System.out.println("A small boat is beached in front of you.");
			}
			moonlitGrove = false;
			shore = true;
		}
		else if(shore == true) {
			System.out.println("There is nothing to the North but an endless sea.");
		}
		else if(outsideMansion == true) {
			System.out.println("You head North and follow the coastline West to a shore.");
			System.out.println("A small boat (in severe disrepair) is beached in front of you.");
			outsideMansion = false;
			shore = true;
		}
		else if(eastCliffs == true) {
			System.out.println("To the North you find an unbelievably untarnished mansion settled in the sand on a shore.");
			eastCliffs = false;
			outsideMansion = true;
		}
		else if(southCliffs == true) {
			System.out.println("You head North until you're deep in the woods, back where you awoke.");
			southCliffs = false;
			deepWoods = true;
		}
		else if(hut == true) {
			System.out.println("A mess of boulders blocks your way.");
		}
		else if(scorchedBeach == true) {
			System.out.println("You head North and follow the coastline East to a shore.");
			System.out.println("A small boat (in severe disrepair) is beached in front of you.");
			scorchedBeach = false;
			shore = true;
		}
		else if(grotto == true) {
			System.out.println("You cannot leave the grotto to the North; it surrounds you on all sides but East.");
		}
		else if(mansion == true) {
			System.out.println("You leave the abandoned mansion.");
			mansion = false;
			outsideMansion = true;
		}
	}
	
	public void east() {
		if(deepWoods == true) {
			System.out.println("You head East until you stand at the bottom of a tall cliff face, just at the edge of the woods.");
			System.out.println("Broken branches and logs are strewn about, as if the forest and cliffs collided.");
			deepWoods = false;
			eastCliffs = true;
		}
		else if(moonlitGrove == true) {
			System.out.println("To the East you find an unbelievably untarnished mansion settled in the sand on a shore.");
			moonlitGrove = false;
			outsideMansion = true;
		}
		else if(shore == true) {
			System.out.println("You follow the shore as it turns SouthEast until you find\nan unbelievably untarnished mansion settled in the sand on a shore.");
			shore = false;
			outsideMansion = true;
		}
		else if(outsideMansion == true) {
			System.out.println("There is nothing East of the mansion but the endless sea.");
		}
		else if(eastCliffs == true) {
			System.out.println("The cliff face is too tall to scale and stops you from going further East.");
		}
		else if(southCliffs == true) {
			System.out.println("The cliff face is too tall to scale and stops you from going further East.");
		}
		else if(hut == true) {
			System.out.println("Heading back to the East brings you to the cliffs on the Southern shore.");
			if(!waterBlock) {
				System.out.println("Water is bizzarely flowing from the sea up a small river that heads NorthWest.");
			}
			else if(waterBlock) {
				System.out.println("The stone gate stops any ocean water from flowing up the river.");
			}
			hut = false;
			southCliffs = true;
		}
		else if(scorchedBeach == true) {
			System.out.println("You walk East back into the forest until you arrive at a small clearing well lit by the moon.");
			scorchedBeach = false;
			moonlitGrove = true;
		}
		else if(grotto == true) {
			System.out.println("You leave the grotto and head East until you're deep in the woods, back where you awoke.");
			grotto = false;
			deepWoods = true;
		}
		else if(mansion == true) {
			System.out.println("You leave the abandoned mansion.");
			mansion = false;
			outsideMansion = true;
		}
	}
	
	public void south() {
		
	}
	
	public void west() {
		
	}

}
