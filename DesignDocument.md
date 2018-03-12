# Design Document for TM 

## Introduction

Our Time Management application "TM" is created mainly for developers and managers of small teams to help them start tracking time more efficiently to increase productivity. Clients in a commercial setting will find that actively managing time to reduce the amount of wasted resources will bring increased revenue for that company; however, this product is not just for commercial uses. Clients in a non-commercial setting will still find this application useful: it will help anyone and their team stay focused on whatever project they are working on.

## Product Reviews

We have looked into two applications for research purposes: Timely and GetHarvest. The Timely application is a simple application that helps track time, profit and efficiency. They also have implemented an idea of time as budget for each project, which gave us the idea of adding budget to TM.
GetHarvest is a more complex application, that can not only track time, but also estimate how much time a future project may take. Although this is an interesting and useful feature, it is out of the scope of our project.


## Project Overview

TM is a Time Management GUI application, that will help teams track time spent on projects with the purpose of increasing efficiency of a team or of a single developer who would like to maximize his/her time to increase profits. TM is a desktop application.


## Project Architecture


## Requirements
| Requirement name | Requirement description |
|------------------|-------------------------|
|   REQ1      x     |  The program shall create and keep a record of the time a task was started when commanded by the user.    |
|   REQ2       x    |  The program shall create and keep a record of the time a task was stopped when commanded by the user.     |
|   REQ3     E      |  The program shall find the time elapsed between the time a task was started and stopped.            |
|   REQ4        x   |  The program shall describe what a task is about with a description given by the user.                |
|   REQ5    E       |  Users shall be able to view a summary of the tasks created by them, and be able to create and download a clean spreadsheet of the summary data.    |
|   REQ6        J   |   Developers shall be able to easily pause a task that is currently running.           |
|   REQ7        M   |  Developers shall be able to set notes for the task they currently working on, separate from describing the task.     |
|   REQ8      M     |  Managers shall be able to set notes on tasks Developers are working on.            |
|   REQ9        M  | Both manages and developers shall be able to set a time budget for each task.  |                         
|   REQ10        A  | Managers shall be able to set due date on a task.   |
|   REQ11       b   | Managers shall be able to assign a task to a particular developer.         |
|   REQ12         A | The program shall notify the user of the upcoming due date or budget limit.  |
|   REQ13         b | Each task shall be categorized under a project, thus to organize the workflow   |
|   REQ14          |                                                                  |

## Use cases

| Use case UC-1             | Name: Start task              |
|---------------------------|-------------------------------|
| Related requirements      | REQ1                            |
| Initiating actor          | Developer                     |
| Actor's goal              | To start a task at the current time.                    |
| Participating actors      | System, File Writer                              |
| Preconditions             | The task is named.                              |
| Postconditions            | The time when the task was started is now stored.                              |

#### Flow of events for main success scenario:

| Number |    Description          |
|--------|-------------------------|
| 1      | **Developer** names the task to be started.                   |
| 2      | **Developer** then selects the option "Start Task".                       |
| 3      | **System** signals that there is a task name, and the "Start Task" option was chosen.              |
| 4      | **File Writer** then writes the time to a file.                        |


| Use case UC-2             | Name: Stop task              |
|---------------------------|-------------------------------|
| Related requirements      | REQ2                            |
| Initiating actor          | Developer                     |
| Actor's goal              | To stop a task at the current time.                    |
| Participating actors      | System, File Writer                              |
| Preconditions             | The task is named. The task has started.                             |
| Postconditions            | The time when the task was stopped is now stored.                              |

#### Flow of events for main success scenario:

| Number |    Description          |
|--------|-------------------------|
| 1      | **Developer** names the task to be stopped.                   |
| 2      | **Developer** then selects the option "Stop Task".                       |
| 3      | **System** signals that there is a task name, and the "Stop Task" option was chosen.              |
| 4      | **File Writer** then writes the time to a file.                        |


| Use case UC-3             | Name:   Summarize           |
|---------------------------|-------------------------------|
| Related requirements      | REQ3,                             |
| Initiating actor          | Developer                     |
| Actor's goal              | Get summary on projects/tasks                   |
| Participating actors      | System, FileWriter                             |
| Preconditions             | The task has name. The task has started and stopped.                             |
| Postconditions            | The elapsed time from when a task has started and stopped is now stored.                            |
#### Flow of events for main success scenario:
| Number |    Description          |
|--------|-------------------------|
| 1      |                 |
| 2      |                      |
| 3      |            |
| 4      |                      |
| 5      |                         |
| 6      |                         |
| 7      |                         |
| 8      |                         |

| Use case UC-4             | Name: Describe task              |
|---------------------------|-------------------------------|
| Related requirements      | REQ4                            |
| Initiating actor          | Developer                     |
| Actor's goal              | To add a description to a named task.                    |
| Participating actors      | System, File Writer                              |
| Preconditions             | The task is named. The task has started.                             |
| Postconditions            | The description provided is now stored.                              |
#### Flow of events for main success scenario:
| Number |    Description          |
|--------|-------------------------|
| 1      | **Developer** names the task to be described.                   |
| 2      | **Developer** then selects the option "Describe Task".|
| 3      |  **Developer** enters a description for named task             |
| 4      | **System** signals that there is a task name, and the "Describe Task" option was chosen.                          |
| 5      | **File Writer** then writes either a new description to file or appends to existing description.                      |
| 6      |                        |
| 7      |                         |
| 8      |                         |




