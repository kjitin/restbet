# Bets
1.To build the project please run

2.mvn clean package

3.The application can be run with the following command

4.java -jar target/rest-bets-1.0.0.jar

5.To list the bets traverse to the following location
  1. http://localhost:8080/available

6.To post a bet traverse to
  1.http://localhost:8080/bets

7.It accepts a JSON of the format
 {
 "bet_id":4,
 "odds":2.75,
 "stake":10
 }

