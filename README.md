# Vehicular-Route-Optimisation

For finding the optimal route between the given routes a genetic algorithm, hill climber algorithm and the simulated annealing algorithm is used.

## Simulated Annealing Implementation-

C=C(initial)--------------------- Intial Random path
For T maximum to T minimum (slowly cooling) 

T stands for Temperature

Epoch:

E1=E(C)------- Path Length for Current Path

N=next(C)------- Next Path

E2=E(N)-------- Path Length of Next Path

Change in Energy of Configuration(X) = E2-E1

if(X>0) C=N

else if( e^(X/T) > rand(0,1) ) C=N; --- bad path condition
 
## Hill Climbing Implementaion-

If we remove bad path condition,it will result in Hill Climbing. It is greedy appraoch so only looks for good path.

C=C(initial)--------------------- Intial Random path

For T maximum to T minimum (slowly cooling) ----- T stands for Temperature

Epoch:

E1=E(C)------- Path Length for Current Path

N=next(C)------- Next Path

E2=E(N)-------- Path Length of Next Path

Change in Energy of Configuration(X) = E2-E1

if(X>0) C=N

## Genetic Algorithm Implementaion-

Chromosome represents a path in genetic algorithm.To create path shuffling is used.One such technique is for every city create n-1 random nos are generated,each random no for other n-1 city. Then sort cities according to the random no.By doing this you will get a new sequence.Repeat this process to generate required no(k) of chromosomes.You can also use normal shuffling to generate chromosomes(paths).

### Crossover-

One such technique for crossover is choose cities in parent one you want to crossover and then remove cities you chose in parent one from other parent and place the removed cities in the end to get the first offspring. Simmilary repeat the process for second parent to get second offspring.

### Mutation-

Mutation can be done by choosing cities in parent one and simply reversing the city order in parent two to get a valid offspring.Another method is choosing city in parent one and simply shifting the same city order to different location.

Recap:  Lets say we have 50 chromosomes initially.From this generate 32 chromosomes through crossover,14 chromosomes through mutation,introduce 4 random chromosome to infuse new kind of thing. From all this total 100 chromosomes pick best chromosomes(least distance). Then update the iteration counter. If iteration counter<threshold repeat the process. If not, we have found present best solution.
