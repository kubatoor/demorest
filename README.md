This is a demo REST application exposing couple of endpoints.

How to Build?
````
gradle clean build
````

How to run application locally ?
````
gradle bootRun
````

(1) /helloworld endpoint

This is a simple /helloword endpoint which returns Hello WorLd in the response. Can be executed using the following curl command
```
curl http://localhost:8080/helloworld
```

(2) /words endpoint

This endpoint takes in a paragraph as an input and provides an array of unique words in the paragraph along with its count in sorted order.

````
curl -H "Content-Type:application/json" -X PUT -d '{"text":"Mary Mary David Mary Mark John Mark"}' http://localhost:8080/words
````

Assumptions: This endpoint assumes that each word in the paragraph is separated by a whitespace. The endpoint converts the input paragraph into lower text and sorts the words based on the lexicographical sort order.


(3) /numbers endpoint

This endpoint takes in a Number N and generates N fibonacci series as the output
````
curl http://localhost:8080/numbers/fibonacci/10
````


(4) /posts endpoint
This endpoint pulls the information from an external third party website

````
curl http://localhost:8080/posts
````

(5) /threads/deadlock endpoint

This endpoint is to create a deadLock in the system. This endpoint waits for 10 secs to create a deadlock. This operation is idempotent. If a deadLock exists in the system
then it won't create a new DeadLock. Instead will return the existing DeadLock Information.

````
curl -XPUT http://localhost:8080/threads/deadlock 

````

This endpoint is to get existing DeadLock information in the system.

````
curl  http://localhost:8080/threads/deadlock
````


(6) /users endpoint
This endpoint provides the capability to CRUD a User object

(a) Create New User in the System

````

curl -H "Content-Type: application/json" -X POST -d '{"userId":"jdoe","name":"John Doe","email":"johndoe@gmail.com"}' http://localhost:8080/users
````

(b) Get all users in the System

````
curl http://localhost:8080/users

````

(c) Get a user based on userId
````
curl http://localhost:8080/users/jdoe
````

(d) Delete a user based on userId
````
curl -X DELETE http://localhost:8080/users/jdoe
````




