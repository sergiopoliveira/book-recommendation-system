# Book Recommendation System

[![CircleCI](https://circleci.com/gh/sergiopoliveira/book-recommendation-system.svg?style=svg)](https://circleci.com/gh/sergiopoliveira/book-recommendation-system)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/5b84bf2bcc5949338bb9806e35b4988e)](https://www.codacy.com/app/sergiopoliveira/book-recommendation-system?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=sergiopoliveira/book-recommendation-system&amp;utm_campaign=Badge_Grade)
[![codecov](https://codecov.io/gh/sergiopoliveira/book-recommendation-system/branch/master/graph/badge.svg)](https://codecov.io/gh/sergiopoliveira/book-recommendation-system)



REST API to retrieve a book recommendation list based on personal preference.

## Getting Started

Build and run the project with Gradle:

```
gradle clean build bootRun
```

## REST Endpoints

```
POST /api/users -> register user with name and email
POST /api/books/{name}/{asin}/{feedback} -> give feedback by that user for book
GET /api/books{name} -> returns 20 recommended books for that user
```
## Testing Examples

To test creation of user POST on the following url:
```
http://localhost:8080/api/users
```
this JSON: 
```
{
	"name" : "Sergio",
	"email" : "ema@ml.com"
}
```
POST it twice to check it doesn't allow repeated usernames.

To test recommendation of books:
```
GET http://localhost:8080/api/books/Sean
```
Sean is a user that belongs to bracket three (check business logic below) and has a high preference for History books, so at least 16 books will be with the History genre.

To test recommendation of books:
```
POST http://localhost:8080/api/books/Michael/143124048/-1
```
This will give a negative feedback for the book with that specific ASIN, making that genre less likely to be recommended for the user.

## Recommendation System (Business Logic)

Books recommendations will be based exclusively on Genre. Each user will have associated a HashMap where key = ”Genre” and value = “sum of feedbacks given on books of that genre.” The possible feedback will be:

 liked the book" = 1
, "not interested" = 0
, "disliked the book" = -1

Then, to calculate the recommended list (example):
A user has this associated HashMap<Genre,Integer>:
-> History: 10
-> Math: -3
-> Science: 15

Then add all and put in brackets, for example in this case 10+(-3)+15=22, so first bracket:

1. First bracket - if total values added <50, only 30% of recommended books reflect those genres preference, rest is random;
2. Second bracket - if total values added >=50 and <150 then system recommends 50% of books on t, rest random;
3. Third bracket - if total values added >=150 then system recommends 80% books on those genres, rest random.

The general idea is that the more feedback the user gives, the more books are recommended based on their Genre preferences

### Tools used

● Java 8
● Spring Boot
● Spring Data JPA -> repositories to hold data
● Project Lombok -> reduce Java boilerplate code
● MapStruct
● Gradle -> build and dependency management
● JUnit, Mockito -> for unit testing
● H2 in-memory database
