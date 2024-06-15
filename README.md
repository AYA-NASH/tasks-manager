# Tasks Manager
A simple task management app, that allows user to manage [create, list, update or delete] his tasks and also sync them to his Google Calendar.

The app is written in Java using Spring Boot and PostgreSQL as the database. 

---
### **Progress Overview:** 

#### Done:
 - [x] Spring boot application spin off. 
 - [x] Design basic schema for tasks and events. 
 - [x] Implement `Task` CRUD and define application layers:
	 - [x] Controllers
	 - [x] Service
	 - [x] Repository 
 - [x] Setup Google API account. 
 - [x] Implement Google Calendar Integration and `Event`s creation.

#### TODO: 
- [ ] Improve authentication experience.
- [ ] Add synchronization background service to sync tasks with the calendar. 
- [ ] Implement cache layer for tasks and events.  using ex: **[caffeine](https://github.com/ben-manes/caffeine)**
- [ ]  Add code tests (units/integration)


#### Future work: 
- [ ] Add basic circleCI or Github Actions pipeline.
- [ ] Containrize the app. 
- [ ] Multiple users support. 
- [ ] Create a Postman collection for the API
- [ ] Create Swagger API documentation 
- [ ] Better errors/exception handling. 
- [ ] [**Bonus**] Implement basic UI for the application. 
---


### Usage:
- Run postegres on localhost and the same default port 5432
- Update the credientials if necessary in the application properties. 
- Run the app 🎉
