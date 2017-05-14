# Bets
1.To build the project please run

2.mvn clean package

3.The application can be run with the following command

4.java -jar target/rest-bets-1.0.0.jar

5.As the project is integrated with Swagger to view the list of available bets  
  1.http://localhost:8080/swagger-ui.html and select bet-controller link and click on getAvailableBets and click "Try it Out" OR  
  2. http://localhost:8080/v1/available  

6.To post a bet traverse to  
  1.http://localhost:8080/swagger-ui.html and select bet-controller link and click on postBets and click on Model Example Value and change the values appropriately OR  
  1.http://localhost:8080/v1/bets

7.It accepts a JSON of the format
 {
 "bet_id":4,
 "odds":2.75,
 "stake":10
 }

