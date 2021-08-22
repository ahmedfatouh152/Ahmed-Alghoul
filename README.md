## **Technical Challenge for IOTblue**
*The Challenge consists of three parts:*
 1. The first part is a test design for testing the Amazon Shopping
    website
 2. The second part is testing the website and reporting the bugs found
    in the web application
 3. The third part is generating test cases for Google search Engine and implementing automation test cases.

#### -The First Part (Test Design for AWZ Shopping website):
The Design process is implemented to cover some of the test cases for the website and to make a good coverage for the main purpose of the website which completing the buying process smoothly.

#### -The Second Part (Testing the Web app and reporting the bugs found)
The testing process for the website is done on operation system of Windows 10 with screen of 15 inch and using google chrome (92.0.4515.131 [64-bit]), and found in the system 8 bugs and some other enhancements reported.

#### -The Third Part (Automating test cases for Google Search Engine)
The automation test scripts are developed using Selenium + Java and integrated with Cucumber and testng. The project is designed to be independant from each other where the tester can run a test for any feature without the need to run the whole project. 

##### 1. _Project Execution_
- To execute the project, the tester need to make sure that all JARs used in the project is added to the project locally.
- All the JARs needed can be found in the [JARs Folder](https://github.com/ahmedfatouh15-arch/googleSearch/tree/master/JARs) in the repository.
- After confirming that the JARs are already added to the project, the tester should navigate to `main_Runner.java` file and run it as **JUnit Test**.
- The project will begin to run and execute the features one by one and shows their status in the **JUnit Side Menu**
- The test should be executed over a chrome browser of Version 92.0.4515.159 (Official Build) (64-bit) or the tester will need to change the Browser driver in the BrowserDriver folder.

##### 2. _Project Structure_
- The project contain 4 libraries and 3 folders.
- The four libraries are the **JRE System Library, JUnit 5, TestNG and the refrenced libraries** that contains all the JARs added to the project.
- The three folers are the `src` folder, `features`, `BrowserDrivers`
    1. The `src` folder contains all **.java** files and contains three packages
        1. The **mainPackage** that contains all objects created to be used in the project and the `baseClass.java` that contains all functions shared between all step definition classes.
        2. The **StepDefinition** that contains all classes that defines the steps executed in the project
        3. The **testRunner** that contains all runners for the projects
    2. The `Features` folder contains all the features files to be exectued according to their calls
    3. The `BrowserDrivers` that contains all browsers drivers to be used in the project.

#### 3. _Automation Test Cases_
|#|Test Case|Expected Results|Actual Results|Status|
| ----------- | ----------- | ----------- | ----------- | ----------- |
|1|Access the link of the Google Search main screen|The screen should opens normally|As Expected|Pass|
2|Search using single word of iot as a keyword|The engine should open the results screen and shows results relevant to the keyword iot|As Expected|Pass|
3|Search using multiple words as keywords|The engine should open the results screen and shows results relevant to the keywords|As Expected|Pass|
4|input nothing in the search bar and submit the search|The engine should do nothing and wait for the user to insert some keywords|As Expected|Pass|
5|insert some keyword and then clear the search bar then submit the search|The engine should do nothing and wait for the user to insert some keywords|As Expected|Pass|
6|Search using single word as a keyword from the results screen|The engine should open the results screen and shows results relevant to the keyword|As Expected|Pass|
7|
Access the link of the Google Search main screen and check the country field in the footer|The country in the footer should be the local country of the machine|As Expected|Pass|
8|Sign in to a google account with invalid email|The system should block the user from signing in and fire a toast that the email is not found|As Expected|Pass|
9|Sign in to a google account with valid email and invalid password|The system should block the user from signing in and fire a toast that the password is not found|As Expected|Pass|
10|Sign in to a google account with valid credentials|The user should sign in to their account and the system should direct them to the main search screen|As Expected|Pass|
11|Images search using iot as a search keyword|The result screen should open filtered by images and the data should be relevant to iot|As Expected|Pass|
12|Compare between the image search and the image filteration|The result of the two process should be the same and relevant to the search keyworf iot|As Expected|Pass|
