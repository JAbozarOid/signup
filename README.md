# Authentication implemented with Clean Architecture plus MVVM
This App is developed by Clean Architecture (presentation, domain , data) with advantage of MVVM design pattern
in the presentation layer.

## Signup page 
In The signup page when user click on create account button all fields must be validate.
if both fields validation approved successfully the signup mock api will be call and if the api response was successful, 
the user navigate to main page of the application.

## SignIn page 

## Technologies
- Unit test cases for validation util
- Kotlin Coroutines
- Kotlin Flow
- Lifecycle viewmodel and livedata
- Retrofit
- MVVM design pattern with repository
- databinding
- Dependency Injection - Dagger-Hilt