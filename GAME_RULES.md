# Vote Game Rules

This document outlines the rules of the game, as determined by the game's source code.

## Game Objective

The primary objective of the game is to be the last player standing in each round. The player who accumulates the most round wins by the end of the game is declared the overall winner.

## Gameplay Mechanics

### Rounds

- The game is played over a predetermined number of rounds.
- A round concludes when only one player remains alive.
- At the beginning of each new round, all players are respawned on new, randomly assigned planets.

### Spawning

- At the start of each round, every player is placed on a random planet.
- No two players will ever spawn on the same planet.

### Movement

- Players are able to move around the circumference of the planet they are currently on.
- To increase their speed, players must hold down their assigned "move" key.
- Each planet has a unique maximum speed limit. This limit is determined by the planet's size—smaller planets allow for higher maximum speeds, while larger planets have lower maximum speeds.

### Jumping

- Players can jump from one planet to another to navigate the level.
- The velocity of a player's jump is determined by their movement speed at the exact moment of jumping—the faster you're moving, the faster you'll jump.
- While in mid-air, players travel in a straight line.

### Landing

- If a player comes into contact with a planet while jumping, they will land on it.
- Upon landing, the player's movement will be relative to the new planet's gravitational pull and surface.

## Player Elimination

A player is eliminated from the round if any of the following occurs:

- **Collision:** If two players collide with each other, the player with the lower velocity is immediately eliminated.
- **Off-Screen:** If a player jumps and travels beyond the boundaries of the screen, they are eliminated.

## Winning

- The last player remaining alive at the end of a round wins that round.
- The player with the most rounds won at the end of the game is the overall winner.
