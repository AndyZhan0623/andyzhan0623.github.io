# Game Box

This is a web application with a stateless server of a game box in which a user can play several games. I intend to make this a game platform in which multiple users can play and interact in the same game, but I have not learned how the server can actively communicate with the clients. I believe that I can achieve the goal in the future with WebSocket. 

# Games

Blackjack

Flip Stud 

Flip Stud 2

# Requirements

Java 17
Maven

# Installation

Clone the repository:

<code> git clone <https://github.com/AndyZhan0623/andyzhan0623.github.io> </code>

Open the project in your IDE or navigate to the project directory.

Install dependencies and build the project using the appropriate build tool.

# Usage

Operate the main method in GameboxApplication to start the whole thing.

# Architecture

Domain : The absolute core component of the project. All logic and rules of a game are implemented here. Other components invoke APIs in domain. 

Infrastructure : This component is responsible for the persistence of various game data. I plan to make feature "game history" for users by taking advantage of this. Additionally, since the server of this project would be stateless, it is necessary to record and update these data frequently so the server knows what is happening.

Input/Output : This component is responsible for connecting Domain to the webpage which users interact with. There are two layers in this component. The inside layer, which includes services and controllers, plugs the outside inputs into the Domain APIs and returns the snapshot of the game to the front-end, while the outside layer, which is the front-end, grabs this snapshot and generate appropriate UI to users for further interaction. 

Authentication : This component is responsible for the authentication of a user. 

# Project Structure

Briefly explain the purpose of the main directories and files.

andyzhan.gamebox/
├── src/
│   ├── main/
│   └── test/
├── README.md
└── ...

Testing

Explain how to run the tests.

# Contributors

AndyZhan0623

# License
