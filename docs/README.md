# Duke ip project

##User Guide

This is a project user guide for a greenfield Java project. 
It's named after the Java mascot _Duke_. 
Duke is an application helping users store their tasks in a list.
The list of tasks would be save into a file which would be under 
the same file path as the executable file.

### Set up 

Prerequisites: **Java 11**or above version.

1. Download the *Duke.jar* from [here](https://github.com/yuqiaoluolong/ip/releases).
1. Locate the file to a folder in which you want to store your task list.

### Run Duke

1. Open the terminal and cd to the folder where *Dke.jar* is in.
1. type in the command: `java -jar Duke.jar`.

If you set up and run Duke correctly, you are supposed to see the below text:
```
    <------------------------------------------------------------>
    Here is yuqiaoluolong's Duke: 
        Hello from
         ____        _        
        |  _ \ _   _| | _____ 
        | | | | | | | |/ / _ \
        | |_| | |_| |   <  __/
        |____/ \__,_|_|\_\___|
    <------------------------------------------------------------>

    <------------------------------------------------------------>
    Here is yuqiaoluolong's Duke: 
     Hello! I'm Duke
     What can I do for you? (type"help" to get explanation)
    <------------------------------------------------------------>
```
###Functions
1. *help*: view detialed explanation and examples of the commands.
    * format: `help`
    * output:
    ````
        <------------------------------------------------------------>
        Here is yuqiaoluolong's Duke: 
          Hello, below is the explanation of the functions:
            1. you can add todo tasks follwed by the description
            example: todo task1 --> [T][✘] task1
            2. you can add deadlines followed by the description and the due time
            example: deadline d2 /by 2015-02-20T06:30:00 --> [D][✘] d2 (by: Year.2015, Month.FEBRUARY, Day.20, Time: h.6, m.30)
            3. you can add events followed by the description and the happening time
            example: event e2 /by 2015-02-20T06:30:00 --> [E][✘] e2 (by: Year.2015, Month.FEBRUARY, Day.20, Time: h.6, m.30)
            4. you can view all the tasks by typing " list" 
           5. you can search for the tasks containing some words
           example: find book --> Duke will show all the tasks related with book
           6. you can mark one task as done by byping "done" followed its number
           7. you can delete one task as done by byping "delete" followed its number
    <------------------------------------------------------------>

    ````
1. *todo*: add a todo task which has only the description and no time details.
    * format: `todo aTask`
    * example: `todo read a book`
    * output(example):
    ````
    <------------------------------------------------------------>
    Here is yuqiaoluolong's Duke: 
      Got it. I've added this task:
        [T][✘] read a book
      Now you have 1 tasks in the list.
    <------------------------------------------------------------>
    ````
1. *deadline*: add a deadline task which has description and due time.
    * format: `deadline aTask /by YYYY-MM-DDTHH:MM:00`
    * example: `deadline assignment1 /by 2020-09-20T06:30:00`
    * output(example):
    ````
    <------------------------------------------------------------>
    Here is yuqiaoluolong's Duke: 
      Got it. I've added this task:
        [D][✘] assignment1 (by: Year.2020, Month.SEPTEMBER, Day.20, Time: h.6, m.30)
      Now you have 2 tasks in the list.
    <------------------------------------------------------------>

    ````
1. *event*: add an event task which has description and happening time.
    * format: `event aTask /at YYYY-MM-DDTHH:MM:00`
        * example: `event a lecture /at 2020-02-20T12:00:00`
        * output(example):
     ````
    <------------------------------------------------------------>
    Here is yuqiaoluolong's Duke: 
      Got it. I've added this task:
        [E][✘] a lecture (at: Year.2020, Month.FEBRUARY, Day.20, Time: h.12, m.0)
      Now you have 3 tasks in the list.
    <------------------------------------------------------------>

    ````
1. *list*: print out all the tasks in the stored list.
    * format: `list`
    * output(example): 
    ````
       <------------------------------------------------------------>
       Here is yuqiaoluolong's Duke: 
         Here are the tasks in your list:
         1.[T][✘] read a book
         2.[D][✘] assignment1 (by: Year.2020, Month.SEPTEMBER, Day.20, Time: h.6, m.30)
         3.[E][✘] a lecture (at: Year.2020, Month.FEBRUARY, Day.20, Time: h.12, m.0)
       <------------------------------------------------------------>
    ````
1. *done*: mark a task with the entered index as done
    * format: `done index`
    * example: `done 1`
    * output(example):
    ````
    <------------------------------------------------------------>
    Here is yuqiaoluolong's Duke: 
      Nice! I've marked this task as done: 
        [T][✓] read a book
    <------------------------------------------------------------>
    ````
1. *delete*: delete a task with the entered index
    * format: `delete index`
    * example: `delete 1`
    * output(example):
    ````
       <------------------------------------------------------------>
       Here is yuqiaoluolong's Duke: 
         Noted. I've removed this task: 
           [T][✓] read a book
         Now you have 2 tasks in the list.
       <------------------------------------------------------------>
   ````
1. *find*: find tasks containing entered keywords
    * format: `fine keyword`
    * example: `find lecture`
    * output(example):
    ````
       <------------------------------------------------------------>
       Here is yuqiaoluolong's Duke: 
         Here are the matching tasks in your list:
         2.[E][✘] a lecture (at: Year.2020, Month.FEBRUARY, Day.20, Time: h.12, m.0)
       <------------------------------------------------------------>
   ````
###Command Summary
Command | Format
------------ | ------------- 
help | help
add todo task | todo aTask
add deadline task | deadline aTask /by YYYY-MM-DDTHH:MM:00
add event task | event aTask /at YYYY-MM-DDTHH:MM:00
done | done index
delete | delete index
find | find keyword