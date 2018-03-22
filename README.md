

# Description

This app use https://jsonplaceholder.typicode.com/ API, it shows a list of post and the detail of the post once you click it.
The app is written on kotlin, because is awesome, smarter than java, and I have to write less code :D 

The app works on portrait and landscape. If the orientation is portrait it will show the post list, and then when you click on a post it will show the detail, if its landscape the posts and details will be shown at the same time. 

**Portrait Screenshot**

<img src="https://i.imgur.com/s2HskPJ.png" width="150">


**Lanscape Screenshot**

<img src="https://i.imgur.com/mIFRzG4.png" width="300">

# Architecture 

The project is divided in three modules **App**, **Core**, **Data**

1. **App**: This module controls how the app is shown on phones and tablets(this is like a front end module)
2. **Core**: This is the  domain layer, this module controls the interactions between applications modules, external services and providers, also in this module is where workload is done
3. **Data**: This is a data access module, this module allow us to get data from different sources

I choose  have  those modules because in this way, If I want the app to run on a wear device, a car or a cast device , I just have to add a new one module (front end) without affect the logic of the app.

Across these three modules I used clean architecture, first of all because this allow me to create unit tests across all the code, as my code is decouple. This architecture also allow me too write the bussiness logic independent from the front end, and the main reason is than I can change one layer easily without affect the others layer, or I can change libraries used across the project without a big impact in the project.

In the project I have included some unit tests and some instrumented tests

# Libraries 

On this Project I have used mainly  Dagger2, RXJava (RXAndroid), Retrofit, Android Arch,  and Mockito for testing purposes 

1. **Dagger**: Dependency Injection :) 
2. **RXJava**: Implementation of the observer pattern
3. **Retrofit**: Http request library, I have try apache http, UrlConnection but retrofit is just awesome, Before retrofit I used to spend at least 1 day setting up my Http request module, and even with a good code implementation handle the erros was a mess. 
4. **Android Arch**: Lifecycle observer library. Before this we have the context awareness problem, how to properly know the state of an activity or a fragment,this lib has been a helping me with this 
5. **Mockito**: Mock Library. In this project I did not mock any POJO but I mocked some Dagger components



