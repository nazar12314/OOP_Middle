## Use case diagram

<img src="https://user-images.githubusercontent.com/93192972/205349537-f4940aa8-2703-4118-94a8-6b5974b7f0de.png" width="700">

## Class Diagram

![image](https://user-images.githubusercontent.com/93192972/205349700-b593d503-0ab8-44ab-a640-b1af5ad2a996.png)

## Demo 

## Used patterns description

In our project we used several different patterns to organize the process, make it visually understandable and affect all the topics we learned before. Basically, in Request object class and ResponseDTO we used Builder to create objects with the ability to make changes and isolating code for construction. It gave us a finer control over process of construction.

Another usage of patterns is Singleton. Singleton pattern is convenient to use when a class in your program should have just a single instance available to all clients. It got useful in AZUREDataScrapper and PDLDataScrapper where we got more control over the instance of our classes.
