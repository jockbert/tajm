***Warning! This project is a work in progress...***


# TAJM #

Source syntax example

```
2014-05-06
	/projX/taskA 	expect 4
	/ 		expect 8 diff 32:30

	/projX/taskA/item1 	8:45-9:00 	# A problem fixed
	/projX/taskA/item3 	9-12
	/education/someCourse 13-15	# Session 2

2014-05-07

	miss 		# took the day of

2014-05-08
	/projX/taskA	expect 5 once

	/projX/taskA/item3	7:30-18:30  expect 6  # Long day
	lunch 		12-13
	break		15-15:30


2014-05-09
	# Another day of work 

	/projX/taskA unexpect

	/projX/taskB/caseA	7:30-12:30
	/projY/meeting	9:45-10:30 # In the middle
```
