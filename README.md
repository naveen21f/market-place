# market-place
Find Optimal Match between Buy and Sell Orders

# Problem 
Buyers and Sellers should be able to place their orders. Each order will contain
the Orderer name , the type of order (Buy or Sell) and the price they are willing
to buy or sell for.
The market place operator should be able to then generate matches by clicking
on a Match Order button. This should generate the best set of matches optimized
for maximum number of matches. It should also display any unmatched.
Example
Buy Orders
John – Rs 100
Jack – Rs 200
Raj – Rs 50
PP – Rs 80
Sell Orders
Rahul – Rs 50
PD – Rs 80
Jill – Rs 250
Jeff – Rs 200
Example of a set of matches
John – PD
Jack – Jeff
PP – Rahul
Raj and Jill are left unmatched. The above assumes preference to highest prices.

#Tech Stack:
1. Spring-Boot
2. Angular
3. MongoDB

#Steps:

1. Spring-Boot Service runs on port 8181
2. Angular dev runs on port 4200
3. MongoDb port 27017
4. MongoDb Database: market-place

1. Do npm install for Angular App
2. npm start for dev mode


