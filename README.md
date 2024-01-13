# accountLogger-Java
AutoLogin Social Media by Java lang, with the help from selenium web automation Framework.

this is a java aplication that using GUI to help the user manage their account and perform an auto login by choosing your account based on the email you registered into the java Program. and then the account registered will be shown in the combobox in the execute login tab panel you have to choose account first in order to enable the start button
and then you can click on it to run the auto login process

when you execute the program the program will create a .txt file named accMemo.txt if it is not exist, then it will start to read the content inside and the display it as options in the combobox, then we can choose an account email to be logged in, then we can click on start.

the program used the maven dependency from Mr. Boni Gracia https://github.com/bonigarcia/webdrivermanager.git which available too on maven, with the webdrivermanager we can simply use the code inside to download the webdriver needed and with the help from selenium we can create a chrome driver. for the first time it will takes a longer time, but for the second login it will be faster.

after we click the start the java program will automate it self to open a chrome web browser, navigate to the website, and do all the login things for you, and in a few second you are already inside the home page of you social media website logged to your account.

the program scope are auto login for facebook and twitter for now, we will keep improving to add a custom account type

please support me by giving me more advice so I can keep on going, for each of you advice means alot for me :)
# Cheers


Requirement : 
1. Java8 SDK
2. Apache maven
