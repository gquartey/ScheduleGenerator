# Changelog
All notable changes to this project will be documented in this file.
* 12/14/17
  * Jonah
    * Wrote in a dummy results page 
    * Now have "submit" button calling middle man constructor and attempting to get info 
    * Changed middle man so that it actually got the course catalog from somewhere
    * tried to call middle man constructor from web APP, found bug in userData (didnt check for null inputs)
    * middle man contstructor, when called from web app, not completiting// commented out for now
  * Kwate
    * worked on jparsec structure
    * edited old pre-req parser to return a hashmap of coursecode to degree requirements

* 12/08/17
  * Jonah
    * updated user history 
    * created middle man and middle man schedule generator tests
    * all front end working except for file input 
    * added an override .equals() method to course code
  * Kwate 
    * Excel parser now can return a collection of courses and sets a course time for any course that contains a course time
  * Yuheng
    * Added interests, second major, grad requirements logic to schedule generator
    * Worked on tests 
    * Second major CPSC is not working due to the fact that cpsc courses are not all offered over the course of a year
* 12/07/17
  * Kwate
    * added a coursetime and courseday field to courses and setters so that we are able to move forward without changing our previous declarations for courses and are able to have courses that do not have course time, while adding course time if neccesary.
  * Yuheng
    * Separated schedule generator catalog into two objects (one for fall and one for spring)
    * Created logic where depending on what's the next semester, the schedulue generator will use a different catalog in order to ensure that the class is there
* 12/06/17
  * Jonah
    * Changed andrequirement so the constructor no longer constructs the hashmap of possible courses
    * Changed all requirement files to take in a parameter for getSolution that gives 
    classes that can not be used to satisfy that requirement (ie those are already taken)
    * refactored code to have packages for better organization
    * hardcoded the biology major
  * Kwate 
    * started work on the excel parser so that we can use the excel file filled with courses rather than the pdf file with courses
  * Yuheng
    * Revised implemention of schedule generator model based on new requirement changes
* 12/05/17
  * Jonah
    * changed grad req to an interface
    * filled out all grad requirement files to reflect the interface refactor
* 12/03/17
  * Jonah
    * finished and requirement w/ tommy 
    * Wrote tests w/ tommy testing and requirement and repeat requirement
  * Kwate 
    * fixed tests for pre-req parser, transcript parser, and course catalog 
    * fixed course creation from the course catalog parser 
    * pre req parser now ignores optional requiremetns preceded by an "or" 
  * Tommy 
    * completed AND requirement and fixed Repeat Requirement with Jonah
    * Wrote tests for AND and Repeat Requirements and getting solutions from user with Jonah
    * Helped with test for Schedule Gen
    * Added tests for major and reqs and made changes to user
    * Helped Yuheng with 4 Year Simple Schedue Test
  * Yuheng
    * Added tests for scheduler, including testing numChecker, and generating the model
    * Added four year Test/function
    * Created schedule generator (that outputs 4 courses)
    * Finalized one sem simple scheduler
    * Implemented get full plan function
    
    
* 12/02/17
  * Jonah
    * Wrote the logic for repeat requirement
      * repeat requirement can take in different numbers for how many times to satisfy a given req
    * And requirement algorithm 
  * Kwate 
    * pre req parser now ignores duplicate pre-reqs
    * fixed and added tests for the pre-req parser
  * Tommy 
    * Coded algorithm for Repeat Requirement and made test with Jonah
  * Yuheng
    * finalized logic for calculating num of classes per sem
* 11/30/17
  * Jonah
    * Created the course code object
    * Altered course to reflect the new course code object
  * Tommy 
    * added 4Letter to each department
    * made changes to major and minor
    * made solutions for OneOf requirements

* 11/29/17
  * Jonah
    * Changed course constructor to allow for courseCode object 
    * Deleted bad code in departments that is no longer needed
    * deleted code in departments is work now done in the REQS objects
  * Kwate 
    * added a pre req setter to the course object 
    * course catalog parser now adds an empty list pre-reqs to produced courses
  * Tommy 
    * restructured departments to fit new DegreeRequirement design
    * changes to new Solution class
  * Yuheng
    * Worked on Degree Requirements
* 11/28/17
  * Kwate 
    * updated departments and courses to take in a course object from a singleton
  * Tommy
    * changing DegreeRequirement to fit our diagram
  * Yuheng 
    * worked on scheduler model, added tests
* 11/19/17
  * Yuheng
    * added department objects 
    * worked on scheduler model
    * added unit tests for scheduler
    * created helper functions for scheduler to help with picking classes/choosing number of major classes to take for a semester
  * Kwate
    * fixed tests and added a few more so now tests function with the new implementation.

  * Tommy
    * added and fixed all departments offered at Swat
    * added tests to check simple schedule generation
    * fixed tests for all the alterations we made with previos classes
    * made enum for requirements
    * added to singleton hashmap

  * Jonah
 
* 11/18/17
  * Yuheng
    * continued work on scheduler model
    * it creates a collection of courses that the user can take (based on userData)
  * Kwate
    

  * Tommy

  * Jonah
* 11/17/17
  * Yuheng
  
  * Kwate
    * Pre req parser is now able to identify all courses, even if optional, unable to represent optional courses at the moment
     so right now all courses listed as pre-reqs are mandatory. 

  * Tommy
    * helped Yuheng with initial schedule generator
    * added method getHistory from user for checking

  * Jonah
* 11/16/17
  * Yuheng
  
  * Kwate
    * Pre-req parser returns a hashmap of pre reqs that maps a class to all of the classes that you must take before it
    * Created the singleton so now we have a hashmap that matches departments as they are defined in the course catalog to a department object. 
    * have a place to store the distribution of each department 

  * Tommy
    * made changes existing Departments, fix more once singleton is made

  * Jonah
* 11/15/17
  * Yuheng
  
  * Kwate

  * Tommy
    * Converted Department to Interface and made necessary changes, better strategy

  * Jonah
    * structural changes for schedule generator including moving the algorithm heavy method to schedule generator model
* 11/14/17
  * Yuheng
  
  * Kwate

  * Tommy

  * Jonah
* 11/13/17
  * Yuheng
  
  * Kwate
    * Made a crude pre req parser that is able to identify courses 

  * Tommy
    * updated UML class diagram to look more in the model we are creating

  * Jonah
* 11/12/17
  * Yuheng
  
  * Kwate

  * Tommy

  * Jonah
* 11/11/17
  * Yuheng
  
  * Kwate

  * Tommy
    * Added Engineering, Computer Science, and Econ for major and minor options
    * fixed update history
    * fixed float and int problem for credits

  * Jonah
    * added multiple routes in js for user clicks
* 11/10/17
  * Yuheng
  
  * Kwate

  * Tommy

  * Jonah
    * fixed maven package for middle man wrapper 
    * added post requests in js
* 11/09/17
  * Yuheng
  
  * Kwate

  * Tommy
    * changes to User
    * rearranging for the middleman (wrapper module)

  * Jonah
    * minor changes to project configuration to reflect new structure of dependencies 
* 11/08/17
  * Yuheng
  
  * Kwate

  * Tommy
    * Department getter and fixes to User

  * Jonah
     * Added a file upload form to the questionnaire. 
     * Added javascript to immitate a post request to pass data from user to model
* 11/05/17
  * Kwate
    * added more units tests for the parser
  * Tommy
    * made fixes to User and Questionnaire
    * changes to GradRequirement and History for testing completion
    * added unit tests for model
  * Jonah
    * Added results page placeholder for webAPP.
    * Description on landing page changed.
  * Yuheng
* 11/04/17
  * Kwate
    * Changed the parser, now outputs a list of course objects instead of a list of strings
    * Made changes so it is compatible with the updated course objects
  * Tommy
    * applied changes to User and Course to work with improvements on the parser and webApp
    * added GradRequirement and all 5 subclasses
    * added DegreeRequirement and two subclasses
    * created History and deleted Transcript
    * added flags to Course to recognize if classes were PE, Writing, or Labs
    * changed User to accept Questionnaire and get info with that
    * added TODOs for items we were still debating on how we want to work
  * Jonah
    * finished basic styling of landing page
    * all data entry points are done but need to pass data to main so main can create model
    * created questionnaire class to hold userData from webAPP
    * created scheduleGenerator Model
    * changed user to take a questionnaire for constructor 
    * changed questionnaire to have gradYear as required field 
  * Yuheng
    -
* 11/03/17
  * Kwate
    * infomration from parser can now be cleaned to exclude information we don't need
    * added tests for the parser function calls
  * Tommy
    -
  * Jonah
    * Improved landing page --> added more drop downs to match UI sketches in documentation
    * ability to upload PDFs
  * Yuheng
    -
* 11/01/17
  * Kwate
    * Parsers can now identify course codes, class department, number and course name
  * Tommy
    * added Transcript, and User info
    * worked with Kwate to develop Course and Department to work with parser
    * made User and Department classes implement Serializable 
  * Jonah
    * Added scripts file for javascript client side operations
    * Created first dropdowns with js functions
  * Yuheng
    -
* 10/31/17
  * Kwate
    -
  * Tommy
    * added Major, Minor, and started classes for User and GradRequirement
  * Jonah
    * Created the first iteration of the landing page 
    * Added minimal HTML and styling
  * Yuheng
    -
* 10/30/17
  * Kwate
    * parser can now identify all the course codes in the course catalog
  * Tommy
    -
  * Jonah
    -
  * Yuheng
    -
* 10/29/17
  * Kwate
    -
  * Tommy
    * created classes for Time and Department as a start
    * added Result and SemesterSchedule class constructors
  * Jonah
    -
  * Yuheng
    -
* 10/27/17
  * Kwate
    -
  * Tommy
    -
  * Jonah
    -
  * Yuheng
    -
* 10/25/17
  * Kwate
    -
  * Tommy
    * Fixed issues with merges, added workspace to gitignore
  * Jonah
    * Created starfruit project, set maven dependencies 
    * Removed workspace file from git 
  * Yuheng
    -
