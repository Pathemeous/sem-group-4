# Bubble Trouble [![Build Status](https://travis-ci.org/Pathemeous/sem-group-4.svg?branch=master)](https://travis-ci.org/Pathemeous/sem-group-4)
A java clone of the game "Bubble Trouble". <br />
Made by Group 4 for the course Software Engineering Methods 2015-2016.

## Starting the game
To guarentee that the game will run properly, use the following Maven build goals:<br />
**THIS HAS BEEN CHANGED**

<code>clean package exec:java -Dexec.mainClass=nl.tudelft.controller.MainApp</code>

This will ensure that all natives are downloaded properly.<br />
The command also clears all natives and the site before starting the game.<br />
As a result the game takes a little longer to start, but all tests will run properly.

## Playing the game
You can start the game by either clicking "1 Player" or "2 Players" on the menu screen.<br />
Player 1 uses (by default) the left and right arrow keys to move and the space bar to shoot.<br />
Player 2 uses (by default) the a and d keys to move and the w key to shoot.<br />
Options (key bindings, sound, logger severity) can be changed in the options menu.<br />
Highscores can be viewed by hitting the highscores button on the main screen.<br />
The game can be paused by hitting the escape key.
