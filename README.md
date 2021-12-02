
ChessOOP
========
This simple GUI based chess game was amde using basic OOPS concepts in java. The class design and requirement analysis of this project was done using Object Oriented Analysis and Design course while the actual implementation was done was a part of Paradigms of Programming Part I course.

Running the Game
----------------

This game can be played by simply executing binary in Executable Jar folder. The Executable bit of the Chess.jar needs to be set in order to execute the file. New players have to be created for the first time you run the program.

Features
--------

1. GUI (front end) Developed using Java Swing
2. Backend was creating using Java
3. Backtracking to determine all possible moves of a piece
4. Greedy Approach to determine if the move results in a potential win/loss
5. A number of GUI Event Handlers were implemented

Developing the Project
----------------------

The project can be imported in Eclipse . The are two 2 main packages(Piece and chess package). The Piece package provides functionalities for each piece on the Chess Board. The chess package provides the major functionalies including the Main Class. The Time Class provides the timer related functions and the Player Class is the vitual avatar of a real player. The chessboard is made of 64 cells and each cell is modeled in Cell class. The GUI functions on each cell has also been provided.

The project has been well documented in 3 docs - Software Requirement Specification, Class Design, Project Documentation. Please go through it to get more insight about the working of the project.

System Requirements
-------------------
We'll be focusing on the following set of requirements while designing the chess game:
1)The system should support two online players to play the game of chess.
2)All the chess rules should be followed
3)Each player will be randomly assigned a side , which is either white or black
4)The white side starts first, and each player will move their pawn one after the other 
5)Players can't take back/roll back their moves
6)The system should maintain a log of all moves by both the players
7)Each side wil start with 8 pawns, 2 rooks , 2 knights, 2 bishops, a queen and a king
8)The game can be won by a checkmate(the king being trapped and no place to move) or by forfeit.
9)The game can be drawn by a stalemate




