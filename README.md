# MvRx-Architecture-Example

## The Assignment
Replicate the below UI and animation flow, and send us your final assignment using GitHub. :https://dribbble.com/shots/6571883-Food-Delivery-App

### Requirements:
You do not need to use exact images, so please use other suitable images.
Write a networking layer to call the API and fetch data which you have to mock the data to display on the UI.
Use the following dependencies/libraries to implement:
- Rxjava
- Kotlin
- MvRx architecture. (https://github.com/airbnb/MvRx)
## The Execution
 In order to accomplish the assignment, the following parts where taken care of:
### Language
The App is fully developed in Kotlin
### Architecture
Because of the assignment, MvRx architecture was used.

### Remote Connections
The library Square okhttp was used to create the network calls which are not being used because of the mocked server.

### View Pager 2
To accomplish the carousel and the tabs, view pager 2 was used

### Bottom Sheet Dialog Fragment

To be able to complete the flow, a Bottom Sheet Dialog Fragment was implemented

### Animations
Animations were implemented with the use of Airbnb’s Lottie library 


## The Result in a gif
![](dindinndemo.gif)
## Improvements
The  improvements to do to this code are to use DI such as Koin or Dagger and to write some unit tests to test the network calls and viewmodels once they are implemented. Also, in order to keep a clean architecture, some mapper functions should be created

