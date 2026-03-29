# My Personal Project

## Project Overview (Proposal)

In my personal project, I will develop an application called *What Did You Eat Today?*. This application is daily food tracker that allows users to record what they eat. Users can set a daily calories goal and log the food they consume, and based on those data, the application visually display each day's progress using emojis to successful and unsuccessful days, making it easy to review eating habits over time and motivating them.

The intended users of *What Did You Eat Today?* is **anyone in the world** who wants to eat healthy for various reasons, such as gaining muscle, losing weight, or maintaining a healthy lifestyle. I am interested in this project because I personally have very strict daily food goal for diet reason. Simply writing food in a calendar or taking pictures (I tried several times) does not allow me to easily review my process or evaluate my success. By providing a clear visual summary of success and failure through emojis, the app helps users stay motivated!

## User Stories
- As a user, I want to be able to set a daily calories goal.
- As a user, I want to be able to check my daily score to track whether my eating meets my goal.
- As a user, I want to be able to view a list of food I added.
- As a user, I want to be able to select a food from my list to log what I ate.
- As a user, I want to be able to save my food list to file (if I so choose)
- As a user, when I start the program, I want to be given the option to load my food list from file.


## Instructions for End User
- You can view the panel that displays the Foods that have already been added to the Food List by clicking the button "View My Food List".
- You can also view the panel that displays the Foods that have already been consumed by user by looking at the panel, "My Day: What Did I Eat Today?" in the main window.
- You can generate the first required action related to the user story "adding multiple Foods to a Food List" by clicking the button "Add Food to My Food List".
- You can generate the second required action related to the user story "Adding multiple Foods to a consumption list" by clicking the button "My day: Record Meal".
- You can locate my visual component by looking at the emoji and background colour in the main window.
- You can save the state of my application by clicking the button "Save My Food List".
- You can reload the state of my application by selecting "Yes" when you are asked to load previous data at the program launch.

## Phase 4: Task 2
A sample of the events

Daily calories goal has been set to 1500 calories.\
User choose to load data.\
Loaded your food list from ./data/foodList.json\
Burrito has been added to my food list.\
Chicken Legs has been added to my food list.\
Chicken Legs has been added to my day.\
Chicken Legs has been added to my day.\
Apple has been added to my day.\
Banana has been added to my day.\
Banana has been added to my day.\
Carrot has been added to my day.

## Phase 4: Task 3
Reflecting on my UML class diagram, one refactoring I would consider is improving the separation of responsibilities between classes if I had more time. In particular, WhatDidYouEatGUI class appears to handle all user interface logic and application logic in the same class. To improve this, I would make two or more separate classes like controller classes that manages different functionality of the program GUI. This change would better follow Single Responsibility Principle and make easy to maintain the codes.

In addition, the current structure of data persistence is tightly coupled to Food and FoodList class. With more time, I would refactor JsonReader and JsonWriter classes to be more general so that they can handle different types of data (e.g. saving state of user's daily score) other than the FoodList. While this change would make the code more reusable and easier to extend, it may also cause additional complexity, so there is a trade-off between these refactoring.