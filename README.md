# README

## Project Description

We are developing a conversational agent named Serenity that talks to the user about their feelings and understands their emotional state, offers information and advice regarding their mental health. Depending on the user's detected emotional state, the bot will also alter how it talks to the user.

#### Link to github repo [here](https://github.com/arshappleid/Assignment3.git).

## How to run the code

1. Import this repository into Eclipse or IDE of your choice
2. Add the specified files as indicated in  ["required files.txt" ](https://github.com/COSC-310-Team-13/Assignment3/blob/main/required%20files.txt) to the classpath (files not included due to size constraint)
3. Go to the GUI.java file and run the Class , in an java SDK environment.

## Classes used

1. Bot : responsible for the agent's functionality, sets current input and sets current output based on input
2. GUI: creates the Graphical User Interface and uses the bot object to facilitate conversation
3. Optimizer: uses Lemmatization from CoreNLP Stanford toolkit to create an optimized search string by reducing to lemma and removes punctuation
4. LevenshteinDistance: Used as appears from Apache Lucene Search library, returns Levenshtein distance between two strings. Used to handle spelling errors . 
5. PoSTagger: uses Parts of Speech tagging from CoreNLP Stanford toolkit to retreive required part of speech. 
6. SentimentScorer: Uses Sentiment analysis from Stanford CoreNLP to extract mood score of the input.
7. ReadFromFile: Used for reading values from a text file into a HashMap using specified delimiter

## Packages:

1. Paypal - Contains all the classes for the paypal api integration.
   1. credentials - These are the hardcoded user credential info , that was taken from the paypal sandbox account.
   2. Create_order - This class takes the input amount as a string, and then 

### Link to video explanation of the video : [here](https://youtu.be/q9C-ruHS0mE)

### Api Integration 

1. Paypal - Since we developed a chatbot , that would mimic the role of a psychatrist. We can set a certain per reply rate, to charge our client at the end. 
   1. In the example demonstrated the rate is set at 5$ / reply , and will automatically charge the customers account when the customer decides to end the conversation.
   2. The trasaction summary for all the transactions made , can be found [here](https://developer.paypal.com/developer/dashboard/sandbox) .
   3. This part relies on user information to be hardcoded in the system. In reality , we would ask the user to verify his login info , then confirm login, and retrieve the user token from paypal api . Whereas in our example , we directly hardcoded the user token, so everytime all the transactions are made from only one user.
2. Wikipedia - Since we do not expect our chatbot , to predict all our responses. Anytime our chatbot does not understand the conversation, it will look up wikipedia for the most significant title. Then will return as a response, a brief summary of the wikipedia response.
   - Since this api relies on the exact title to be provided for the search, the title_parser method assumes that the longest word in the previous response was the search title. 
   - If the identified search title by the parser method is identified, the method will return null.



