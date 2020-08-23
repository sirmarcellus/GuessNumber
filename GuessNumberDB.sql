Drop database if exists GuessNumberDb;
Create Database GuessNumberDb;
use GuessNumberDb;

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

Insert into game(gameId, solution, isDone) values
	(1,"1234",true),
    (2,"5412", false);
    
    

