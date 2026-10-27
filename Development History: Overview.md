# Outline
The project can be roughly divided into three stages in chronological order. At the beginning of each new stage, I had a new set of goals and was dissatisfied with the existing project. Each time I moved on to the next stage, I created a new file and rewrote the entire project from scratch. As a result, I have at least three folders associated with the project, each corresponding to a different version of the project.

# Version 1: Console Program
After learning the basic syntax of Java, along with classes, loops, and conditions, I began trying to recreate Blackjack on my school computer. The initial version was very crude and rudimentary, with all of the game logic crammed into a single method and various nested loops and conditional statements. The only object used at runtime was ```PokerCard```.

# Version 2: Front-end UI

Dissatisfied with the rather rudimentary input and output capabilities of Java's console, I saw two possible ways to implement a graphical interface: using Java APIs such as Swing and JavaFX, or integrating a web interface with HTML, CSS, and JavaScript for front-end interaction. Since I already had a basic understanding of HTML, CSS, and JavaScript at the time, and a local application was not ambitious enough for me, I decided to switch to IntelliJ IDEA and learn Spring Boot. (The version of Eclipse on my school computer did not have the Maven plugin, making Spring Boot configuration difficult.)

After learning the basics of the request-response model and getting more familiar with HTML, CSS, and JavaScript, I built a second, much more substantial version of the game that could be played through a web browser, although it was still hosted locally. While the game logic was still somewhat rudimentary, I had introduced several new runtime objects: ```BlackjackPlayer```, ```BlackjackDealer```,  and ```BlackjackDeck```. At the very least, this allowed me to encapsulate some of the game's data fields.

# Version 3: Database & JWT Authentication

To allow players to retain their balances instead of having to start over every time, I began learning SQL and working with MySQL. As modern REST APIs tend to be stateless, I decided to design the server to be stateless as well. When implementing user authentication, I also took the opportunity to learn how to configure JWT and how its authentication mechanism works.

After building a simple addition simulator to practice these two new technologies, I integrated them into the project and began developing the third major version—this unfinished version available on GitHub.

Beyond introducing new technologies, I almost completely overrode my previous approach to code organization. As I wrote the new version, I carefully considered and analyzed whether responsibilities were properly separated, whether the architecture was extensible, and whether the design would accommodate future changes. Through this day-to-day process of reviewing, refining, and rethinking my decisions, I gradually developed a much deeper understanding of object-oriented programming (OOP), interfaces, generics, and other fundamental programming concepts.
