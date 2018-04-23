update Project
set projName = "Sonata"
where projNo=567;

update Project
set timeBudget = 150.00
where projNo=567;

update Project
set projName = "Jaguar", timeBudget = 175.00
where projNo=568;

update Project
set projName = "Moonlight", timeBudget = 120.00
where projNo=569;

update Project
set projName = "Eclair", timeBudget = 90.00
where projNo=570;

update Project
set projName = "Cambridge", timeBudget = 60.00
where projNo=571;

update Project
set projName = "Picasso", timeBudget = 80.00
where projNo=572;

update Project
set projName = "Mustang", timeBudget = 200.00
where projNo=573;

update Project
set projName = "Enigma", timeBudget = 100.00
where projNo=574;

update Project
set projName = "Harmony", timeBudget = 40.00
where projNo=575;

#add due dates

update Project
set dueDate = "2018-09-18"
where projNo=567;

update Project
set dueDate = "2019-02-27"
where projNo=568;

update Project
set dueDate = "2018-12-21"
where projNo=569;

update Project
set dueDate = "2018-10-16"
where projNo=570;

update Project
set dueDate = "2018-12-20"
where projNo=571;

update Project
set dueDate = "2018-04-10"
where projNo=572;

update Project
set dueDate = "2018-09-15"
where projNo=573;

update Project
set dueDate = "2018-05-16"
where projNo=574;

update Project
set dueDate = "2019-04-02"
where projNo=575;


#updates to the Task table

update Task
set start = "2018-01-09 09:15:00", end = "2018-01-09 12:45:00", duration = "3.5"
where taskID = 1;

update Task
set start = "2018-02-09 16:23:00", end = "2018-02-09 18:05:00", duration = "1.70"
where taskID = 2;

update Task
set start = "2018-02-11 13:00:00", end = "2018-02-11 15:30:00", duration = "2.50"
where taskID = 3;

update Task
set start = "2018-01-29 14:05:00", end = "2018-01-29 16:40:00", duration = "2.58"
where taskID = 4;

update Task
set start = "2018-02-15 10:10:00", end = "2018-02-15 12:00:00", duration = "1.83"
where taskID = 5;

update Task
set start = "2018-03-12 09:30:00", end = "2018-03-12 11:00:00", duration = "1.50"
where taskID = 6;

update Task
set start = "2018-02-18 09:45:00", end = "2018-02-18 13:00:00", duration = "3.25"
where taskID = 7;

update Task
set start = "2018-02-19 10:00:00", end = "2018-02-19 10:30:00", duration = "0.50"
where taskID = 8;

update Task
set start = "2018-01-18 15:00:00", end = "2018-01-18 16:00:00", duration = "1.00"
where taskID = 9;

update Task
set start = "2018-03-20 13:40:00", end = "2018-03-20 14:15:00", duration = "0.58"
where taskID = 10;

update Task
set start = "2018-03-18 12:00:00", end = "2018-03-18 16:00:00", duration = "4.00"
where taskID = 11;

update Task
set start = "2018-03-21 11:30:00", end = "2018-03-21 14:45:00", duration = "3.25"
where taskID = 12;

update Task
set start = "2018-03-24 09:10:00", end = "2018-03-24 10:00:00", duration = "0.83"
where taskID = 13;

update Task
set start = "2018-03-22 17:00:00", end = "2018-03-22 17:45:00", duration = "0.75"
where taskID = 14;

update Task
set start = "2018-03-10 11:03:00", end = "2018-03-10 13:45:00", duration = "2.70"
where taskID = 15;

update Task
set start = "2018-03-29 12:00:00", end = "2018-03-20 14:20:00", duration = "2.33"
where taskID = 16;

update Task
set start = "2018-04-02 13:00:00", end = "2018-04-02 17:30:00", duration = "4.50"
where taskID = 17;

update Task
set start = "2018-04-05 09:30:00", end = "2018-04-05 13:40:00", duration = "4.17"
where taskID = 18;

update Task
set start = "2018-04-06 12:30:00", end = "2018-04-06 12:45:00", duration = "0.25"
where taskID = 19;

update Task
set start = "2018-04-07 11:05:00", end = "2018-04-07 13:40:00", duration = "2.58"
where taskID = 20;

update Task
set start = "2018-04-08 14:50:00", end = "2018-04-08 16:30:00", duration = "1.67"
where taskID = 21;

update Task
set start = "2018-04-09 11:20:00", end = "2018-04-09 15:40:00", duration = "4.33"
where taskID = 22;

update Task
set start = "2018-04-09 09:45:00", end = "2018-04-09 14:15:00", duration = "4.50"
where taskID = 23;

update Task
set start = "2018-04-10 15:00:00", end = "2018-04-10 16:45:00", duration = "1.75"
where taskID = 24;

update Task
set start = "2018-04-11 13:30:00", end = "2018-04-11 14:45:00", duration = "1.25"
where taskID = 25;

update Task
set start = "2018-04-12 11:00:00", end = "2018-04-12 15:40:00", duration = "4.67"
where taskID = 26;

update Task
set start = "2018-04-13 12:45:00", end = "2018-04-13 16:30:00", duration = "3.75"
where taskID = 27;

update Task
set start = "2018-04-14 10:30:00", end = "2018-04-14 15:00:00", duration = "4.50"
where taskID = 28;

update Task
set start = "2018-04-15 12:45:00", end = "2018-04-15 13:55:00", duration = "1.17"
where taskID = 29;

update Task
set start = "2018-04-14 11:00:00", end = "2018-04-14 13:00:00", duration = "2.00"
where taskID = 30;

update Task
set start = "2018-04-15 09:00:00", end = "2018-04-15 10:45:00", duration = "1.75"
where taskID = 31;

update Task
set start = "2018-04-15 14:05:00", end = "2018-04-15 16:40:00", duration = "2.58"
where taskID = 32;

update Task
set start = "2018-04-16 12:30:00", end = "2018-04-16 13:00:00", duration = "0.50"
where taskID = 33;

update Task
set start = "2018-04-16 11:00:00", end = "2018-04-16 14:30:00", duration = "3.50"
where taskID = 34;

update Task
set start = "2018-04-17 15:00:00", end = "2018-04-17 17:30:00", duration = "2.50"
where taskID = 35;

update Task
set start = "2018-04-17 09:00:00", end = "2018-04-17 16:00:00", duration = "7.00"
where taskID = 36;

update Task
set start = "2018-04-18 16:15:00", end = "2018-04-18 17:45:00", duration = "1.50"
where taskID = 37;

update Task
set start = "2018-04-18 13:00:00", end = "2018-04-18 14:50:00", duration = "1.83"
where taskID = 38;

update Task
set start = "2018-04-19 12:05:00", end = "2018-04-19 16:00:00", duration = "3.92"
where taskID = 39;

update Task
set start = "2018-04-20 09:00:00", end = "2018-04-20 10:30:00", duration = "1.50"
where taskID = 40;
