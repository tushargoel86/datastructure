# What is backtracking ?

<p>
Backtracking is a general algorithm for finding all (or some) solutions to some computational problems, notably
constraint satisfaction problems, that incrementally builds candidates to the solutions, and abandons a candidate 
("backtracks") as soon as it determines that the candidate cannot possibly be completed to a valid solution.

Backtracking can be applied only for problems which admit the concept of a "partial candidate solution" and a
relatively quick test of whether it can possibly be completed to a valid solution. It is useless, for example, 
for locating a given value in an unordered table. When it is applicable, however, backtracking is often much faster 
than brute force enumeration of all complete candidates, since it can eliminate a large number of candidates with 
a single test.

Backtracking is an important tool for solving constraint satisfaction problems, such as crosswords, verbal arithmetic,
Sudoku, and many other puzzles. It is often the most convenient (if not the most efficient[citation needed]) technique 
for parsing, for the knapsack problem and other combinatorial optimization problems. It is also the basis of the so-called 
logic programming languages such as Icon, Planner and Prolog.

More details are at [wikipedia](https://en.wikipedia.org/wiki/Backtracking)
</p>

We will solve few problems related to it:

1) StringPermutation:
  Say if we have input AABC, we want all possible combination with this input.
    now as P&C :  we have possibe 4! combination and as 'A' is repeting twice so 
    total combinations are = 4! / 2! = 12 different combinations
    
 
    
    
