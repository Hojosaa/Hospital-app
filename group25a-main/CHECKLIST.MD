TO-DO LIST

FRONT END ;-; 
VAR NAMES changed when needed

CENTER DISPLAYS - done
CODE REFACTORING
USER FRIEDNLYNESS
DOCUMENTATION <<<<< EXTRMEELY IMPORTANT
VAR NAMES REDONE

-FIX BACKEND


validation BACKEND TICKETS :
-EDGE CASES: -COMPLETE (SUBJECT TO TEST CASES)

refactor code where possible regarding register in frontend and make cleaner 
-TEST CASES 
-add logger
-remove warning messages
-CODE REFACTORING 
    -> add documentation on how to reset db and recreate tables
- move functionality to populate JComboBox of doctors from DoctorService to the frontend, and
replace backend method to just get all doctors from the database
- Fix issue when running program everytime it populates the tables (view doctors)
-Backend for view bookings need doing

Raharth
make toolbar look nice
fix back button for bookapointment

So I have changed a lot of stuff. In case i do not wake up for soft dev i shall list what i did here and what needs doing
basically we have now refactored all our files into proper packages with conventional names
Removed main and use Application as main as per conventions
Fixed a lot of errors to do with Jpanels and frames
in components package there is back button so we use a stack and pop it to see the last panel we saw
view history is the logic to do with back button
back button does not work for book appointments

Put dan (czech) and hamza on tests 

Jhesy can do the db schema that we need for sprint 3 (check  brief)

Overall a lot of refactoring was invovled idk why i did it now lmao 