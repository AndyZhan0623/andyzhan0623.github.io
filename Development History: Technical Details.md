# Exploration of "Context"

In V1, as I stared at the tangled mess of deeply nested code in that one method, recalling the painful debugging experiences I had been through, I had already started trying to organize the code. I broke the method down into a series of smaller static methods, arranged in chronological order, and put them all in a single class.

But I immediately ran into a problem. Originally, there was only one method, so variables such as the player's hand, the dealer's hand, and the deck were all declared within that method. Their scope was limited to that method, but they remained available throughout the game. Once I split the code into multiple methods, however, these variables could only be declared within one method. Their scope couldn't span multiple methods, meaning the other methods had no way of accessing the data stored in them.

To make these variables accessible to the other methods, I apparently have to pass them in as method parameters. But that quickly led to another problem: there were multiple variables, while a method could only return a single value.

Fortunately, I had a solution: just pack everything up and take it along. I created another class and stuffed all the variables into it. Thanks to Java's pass-by-value, I didn't even need these methods to return anything. I could simply pass this "wrapper" object from one method to another.

Looking back, I realize that, in a sense, I had already begun to encounter the concept of context.

# A Little Detour into “Pass-by-Value”

The idea that “an object that is a method parameter changes state when you modify it in the method” feels perfectly intuitive. I know this might sound like stating the obvious, but when I was practicing with int values, I was genuinely surprised: I pass an integer into a method, increment it by 100, and then print it after the method finishes, only to find that it hasn’t changed at all?! I thought I had encountered upon some kind of logical error again, but I later learned that this is exactly how Java’s pass-by-value mechanism works.

Nonetheless, I soon found myself puzzled again: “Then why can an object retain its changes after the method is done?”

I spent quite some time pondering the statement, “When an object is passed by value, a copy of the reference is passed,” before finally realizing what made it all click.

Imagine an object as a room and a reference as the key to that room. When an object is passed into a method as a parameter, a duplicate of that key is handed to the method. Since the duplicate can open the same room, modifying the object through the passed reference is essentially like entering that room with the duplicated key and changing something inside it.

This analogy also reveals a potential pitfall: if the reference is reassigned to refer to another object inside the method, the duplicate key would be disconnected from the original room. It now serves as the key to a different room, and using it can no longer change the state of the original object.

In other words, a method can modify the state of an object that exists outside it, but it cannot make that object itself become a different object.

This little detour not only helped me understand pass-by-value, but also gave me a deeper understanding of the relationship between references and objects.

# Attempt of Recursion

In V2, I hadn't turned this project into a web application yet. At the time, I had only recently started to use IntelliJ IDEA and had added two additional games (homemade card games), with the intention of expanding the project into a game box.
The game menu had two levels. The first level offered options such as selecting a game, resetting the chips, and exiting. The second level contained options for going back, playing Blackjack, playing Flip Stud, and playing Flip Stud 2 (the latter two were my own creations).

To allow users to navigate back and forth between these two levels, I came up with an whimsical approach: indirect recursion. I call it whimsical because, at the time, I didn't even know that methods could call themselves recursively. Here's how I implemented it, as shown in the image:

However, improper use of recursion always carries the risk of a stack overflow. Looking back, I think loops with repeatedly checking the current state would probably be a better solution.

# Flow Architecture of Blackjack

What I'm most proud of in the V3, which is the current version, is the Pipeline like flow architecture I developed for Blackjack. In theory, by adding and removing Mechanics, or by introducing new classes that implement them, I could build an infinitely many entirely new games based on Blackjack.

...In fact, this design really was one of those ideas that came to me almost by happy accident. I was inspired by a plot point in a novel.

In the novel, a railway project was divided into multiple sections and put up for bidding. The winning bidder for each section would then be responsible for constructing that particular stretch of railway.

In my design, each stage of Blackjack can be thought of as a section of the railway. The interface for each stage serves as the construction specification that defines how that section must be built, while the classes implementing that interface represent the companies bidding for the contract.

# Derivation of State Machine

But I soon ran into another problem: if the game logic was delegated to these individual "bidding companies," how would Blackjack know what to do next once one of them had finished its job?

Although the Blackjack engine coordinates the various Mechanics, it has no knowledge of what each Mechanic is actually doing internally. Given that, each Mechanic should send a signal to Blackjack upon completion, letting it know which Mechanic to invoke next.

In other words, the game context needs a field indicating which stage Blackjack is currently in.

Only later did I learn that this kind of "signal" is what we call a system's state, and that the design I had come up with essentially formed a state machine.

# ```Card``` & ```BetCarrier```

All games have a stage where players place their bet (at least, that's how things work in my game box), so I can create a dedicated ```BetService``` to handle the common logic. However, for BetService to handle betting uniformly, I first need to figure out one thing: who exactly is placing the bet? In other words, which object holds the ```bet``` field in certain game?

In most games, this would probably be the player. In Blackjack, however, it's the player's hand. That is, I need to find a way to handle all these classes with the field ```bet``` uniformly. The tricky part is that although they all have this field, they can be completely different kinds of objects, which makes nonsense if I state that all of them inherit the same class. 

In this situation, an interface is clearly the more natural choice. So I created the interface ```BetCarrier``` and had any class containing a bet field implement it. This way, ```BetService``` only needs to know that it's working with a BetCarrier, without knowing which particular game's ```BetCarrier``` it's dealing with.

Originally, ```Card``` was also an interface. After all, Java doesn't support multiple inheritance, so when designing things like this, I tend to favor interfaces over super classes. But things veer to the opposite way on ```Card```. I discovered a stable semantic property shared by all cards: visibility. This time, my intuition actually told me that ```Card``` should become a super class instead.

Comparing ```Card``` with ```BetCarrier```, I realized why my intuition had led me in different directions. Semantically, ```Card``` represents the somewhat abstract concept of a "card," from which a hierarchical, tree-like structure can naturally branch out. This makes inheritance a better fit. ```BetCarrier```, on the other hand, represents a shared characteristic across otherwise different games. It's a horizontal commonality rather than a vertical hierarchy, which makes an interface the more natural choice.


