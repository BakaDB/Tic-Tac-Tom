# Tic-Tac-Tom
The AI the player plays against is called Tom. He uses an algorithm for decision making called [Minimax algorithm](https://en.wikipedia.org/wiki/Minimax).

## Gameplay
Tom cannot be defeated. Games always finish in a draw or Tom winning.

## Performance & Improvements
There are 255,168 unique games that can be played. Of these, 131,184 are won by the first player, 77,904 are won by the second player, and 46,080 are drawn. Tom explores all the possibilities for its current move and selects the most favourable. 

The spacial cost of the problem could be reduced with [Alpa-beta pruning](https://en.wikipedia.org/wiki/Alpha%E2%80%93beta_pruning) by not evaluating further nodes which cannot yield a better result than the previously explored.

## Instructions
Execute the Main.java class.
