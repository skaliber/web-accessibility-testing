readme.txt for the thirdparty folder.

This folder is where external files, program libraries, etc. can be stored to make the project easier to manage. 
Rather than distribute (and have to maintain) the third party files e.g. the Selenium / WebDriver libraries with our project we are able to keep this project small. You can then download and unpack the various files here. 

Place libraries, e.g. the jar files from the selenium project in the thirdparty folder. We recommend you create a separate, versioned folder for each source of code e.g. if you decide to use selenium 2.0b1 jars, create a folder in thirdparty/libs called selenium2.0b1 If you adopt this practice, then you can cleanly manage and track updates e.g. when selenium2.0b4 is available, you can download and copy it into a new folder in thirdparty called selenium2.0b4 then change your IDE project configuration to use the jar files from this folder. The old files are still available if you need them, and you can choose to delete them without risking mixing up versions of these dependencies.
