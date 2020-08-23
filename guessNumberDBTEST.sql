Drop database if exists GuessNumberDbTest;
Create Database GuessNumberDbTest;
use GuessNumberDbTest;

create table game (
	gameId int primary key auto_increment,
    solution char(4),
    isDone boolean default false
);

create table rounds (
	roundId int primary key auto_increment,
    gameId int not null,
    guessTime timestamp default current_timestamp,
    userGuess char(4),
    result char(7),
	foreign key fk_game_rounds(gameId) 
		references game(gameId)

);

