# sportradar-scoreboard
Sportradar coding exercise for the implementation of the Football World Cup Score Board.  
As one of the first requirements is that this should be a simple library I've tried to reduce dependencies as much as possible. That's why there aren't REST APIs and no Spring Framework is used.  
I've tried to focus on TDD, SOLID, and clean code so I'll talk about them and where you can find some examples.  
##TDD
TDD is probably the most difficult part to show. I've tried to make some evidence using the GIT commits to demonstrate the development process guided by the TDD classical Red, Green, Refactor.  
For the red step, I've tried to cover the requirement I was facing with failing tests.  
After that I've written the code for making the test go OK (green step).  
Finally is time for refactoring and improving the code.
##SOLID
I've tried to keep every object as simple as possible, reducing their responsibility to the minimum necessary.  
Probably the easiest place to look for open/close principle is the relationship between BaseScoreboard and FootballWorldCupScoreboard. BaseScoreboard defines the basic behavior for a scoreboard. FootballWorldCupScoreboard extends and adds new functionality, the summary of games by total score.  
The usage of the comparators for sorting the summary is a classic example of how a function that reference a base class (ChainedComparator) can use derived classes (CreationTimeComparator and TotalScoreComparator) without knowing it.  
Due to the "simplicity" of the exercises, there is not a demonstration of the interface segregation principle. I thought about doing it with the ScoreboardRepository but I discarded it. It looked forced.  
The dependency inversion principle is one of the easiest when you work with Spring, unfortunately, this is not our case. When I was thinking about the solution I always wanted to do a scoreboard with his repository easily replaced. That's why I've designed the ScoreboardRepository interface. Our scoreboard depends on this interface, but it doesn't depend on any implementation, and this is what dependency inversion talks about.  
##Clean code  
I've tried to follow the clean code principles reducing code repetition as much as possible (DRY). I've also tried to have method names with clear meaning, so everyone that looks at them can make an easy idea about what they do. I've also focused on testing and future maintenance. Tests are independent of each other, are self-validating, easy to understand, and as short as possible.  
##Design patterns
I've used a few design pattern, but I'd like to talk about one and why I've used it. I'm talking about CoR (chain of responsibility).  
I've used it within the comparators. The main goal when I thought about this scoreboard was to make it as extensible as possible. I thought that making a specific comparator for this solution wasn't a good idea. I wanted to make this functionality easily replaced.  
At first time I considered using a decorator pattern, but after doing a proof of concept I thought that CoR was going to be cleaner and easier to understand. That's why I discarded the decorator pattern and chose CoR.  
The implementation is really easy, you can and the next link to your chain of comparators for breaking the ties between games.  
##Validations
Despite I've made some functional validations, due to the time I've decided to ignore some of them for this exercise. For example, I'm not validating if the score that we are updating is a positive number. 