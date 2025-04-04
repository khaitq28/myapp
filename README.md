
**About the application:**

1. Frontend: angular 17 + Meterial
2. Java 11 + Spring boot:
    - DDD  architecture
    - H2DB  in memory
    - configured to connect to IBM MQ (but no real IBM server -> et message error to connect but 
      the app works). So we can simulate by an API for that.

##  To run application by Docker

- Download the repository
- In the folder myapp which contains docker-compose.yml, run the command cmd (depends on the OS):

      docker compose up

or by

      docker compose up

- Open the web app:  
     `http://localhost:80`
- The backed API:
   
    `http://localhost:8080/home`


##  WebApp functionalities:
- 2 menus Messages and Partners, get data from the database H2DB inside the Java spring boot app
- In Messages menu: load data and navigate by pagination, click on the message to see detail by popup
- In Partners menu: load data and navigate by pagination, Add/delete a partner

Data: 
-  10.000 messages + 100 partners are created at start time

##  To test/simulate new messages:

- We estimate 10.000 messages are send to app by IBM Queue every 1 second. (50k message/5seconds)
- To simulate, run 
  - `http://localhost:8080/api/simulate`
  - It takes about 5 seconds to send 50k
  
- Check the webapp with new messages on the screen. 
- Each time run simulate, we have more 50k messages

- The code here: 
  - https://github.com/khaitq28/myapp/blob/main/myapp-back/src/main/java/com/tqk/myapp/interfaces/SimulateResources.java
  - use AtomicInteger for message Id

**Processing:** :

- Use  BlockingQueue<String> to simulate the queue
- ThreadPoolTaskScheduler with size 5 for Scheduled Thread to process messages

How to save messages to DB:
- process by Batch, run Scheduled task with interval 100ms, to save a batch of 1000 message to DB => capacity ~ 10k message/s

https://github.com/khaitq28/myapp/blob/main/myapp-back/src/main/java/com/tqk/myapp/application/MessageQueueProcessor.java

- MessageService call saveAll to save the batch (1000 messages) to DB

On the frontend side:
- use pagination (for performance) to load messages
- default sort by messageId
- sort by partner Alias


##  Some points:

- 10k messages are created at start time
- 10k message created every 1 second
- all messages are saved to DB almost immediately (some very small delays but it's acceptable for Users)
- 5 thead for Thread Pool task
- Interval of 1/10 second + Batch size of 1000 => can handle 10k message/s

- Other way to simulate:
  - run a service which send continoustly 1000 messages/100 ms (non stop) => 10k message/second:
  -     http://localhost:8080/api/simulate2/start
    While it's running, we can see the webapp receives contioustly new message
  - Stop simulate:
  -     http://localhost:8080/api/simulate2/stop

##  Follow-up: (to improve more if needed)
- add DTO, unit test, integration tests
- use Index for createdAt column, (if we want to sort by createdAt date)
- use cache for Partners data and/or Messages data (for old messages)  (@Cacheable of Spring boot, or Redis if big scale application)
- use cache for Messages data (old messages) with criteria of search/sort 
- Test with real IBM MQ, and use real DB like Postgres (faster and more stable than H2DB)
- Monitoring to check the bottleneck of the system:
  - Thread pool size
  - Database connection pool size
  - RAM, CPU, memory... of Java app, and Database machine...

If the message from queue is VERY BIG, some other solutions can consider:
- use RabbitMQ and DeadLetter Queue to keep message in queue, and process it later
- partition the database (by createdAt month/year, or by sender)
- use multiple instances of app (scale auto) by K8S  to process messages

## Screen:
![Screenshot 2025-04-04 124229](https://github.com/user-attachments/assets/4bc09a94-8f35-4f50-8058-3910bc61bf00)
- ![Screenshot 2025-04-04 124304](https://github.com/user-attachments/assets/270cb5e1-1d21-4a96-818f-b606543b4d2a)
![Screenshot 2025-04-04 124325](https://github.com/user-attachments/assets/c195963b-2837-4fb6-b2b0-3c609abde44c)




