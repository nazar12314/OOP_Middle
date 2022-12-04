## Use case diagram
![image](https://user-images.githubusercontent.com/93192972/205350877-a94b1b9c-b0c0-44a5-b7b4-105068e5c3f4.png)

## Class Diagram

![image](https://user-images.githubusercontent.com/93192972/205349700-b593d503-0ab8-44ab-a640-b1af5ad2a996.png)

## Demo 
https://evening-temple-88347.herokuapp.com

## Used patterns description

In our project we used several different patterns to organize the process, make it visually understandable and affect all the topics we learned before. Basically, in Request object class and ResponseDTO we used Builder to create objects with the ability to make changes and isolating code for construction. It gave us a finer control over process of construction.

Another usage of patterns is Singleton. Singleton pattern is convenient to use when a class in your program should have just a single instance available to all clients. It got useful in AZUREDataScrapper and PDLDataScrapper where we got more control over the instance of our classes.
