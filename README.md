<h2><mark> THIS REPOSITORY HAS BEEN ARCHIVED </mark></h2>

# 1719Code2024
Code for 1719's 2024 robot: RINGO! 

## CAN IDs

| Motor Label   | Motor Location | CAN ID |
| ------------- | -------------- | ------ |
| Motor #1      | Left Front Drive     | 1      |
| Motor #2      | Left Front Rotation Motor    | 2      |
| Motor #3      | Left Front CANCoder    | 3      |
| Motor #4      | Right Front Drive    | 4      |
| Motor #5      | Right Front Rotation Motor       | 5      |
| Motor #6      | Right Front CANCoder       | 6      |
| Motor #7      | Left Back Drive    | 7      |
| Motor #8      | Left Back Rotation Motor | 8      |
| Motor #9      | Left Back CANCoder | 9    |
| Motor #10     | Right Back Drive |10      |
| Motor #11     | Right Back Rotation Motor |11      |
| Motor #12     | Right Back CANCoder |12      |
| Gyro #13     | Gyro Port |13      |
| Motor #14     | Intake |14      |
| Motor #15     | Shooter |15     |
| Motor #16     | arm motor |16      |
| Motor #17     | Climber Drive Motor | 17      |








## Button Mappings:
### Driver Controller: 
      Left Axis: Controls left motors /n
      Right Axis: Controls right motors /n
      Button 5: Turns the robot towards the limelight /n
### Helper Controller:
      Moving X axis on Joystick + Button ???: Moves the climber motors left or right/n
      Moving Y axis on Joystick + Button 10: Enables manual arm control\n
      Button 1: Shoots /n
      Button 2: Suck in note /n
      Button 3: Intake sequence /n
      Button 6: Amp shoot /n
      Button 7: Resets the Gyro /n
      Button 11: Raises the climbers /n
      Button 12: Lowers the climbers /n
      TODO: add a propper button for climb motors :)
      
      
      
      


## Instructions

### Useful pages
   Setup: https://docs.wpilib.org/en/stable/docs/zero-to-robot/step-2/offline-installation-preparations.html
   
   WPILib Java Examples: https://docs.wpilib.org/en/stable/docs/software/examples-tutorials/wpilib-examples.html
   

### How to add a subsytem and commands
   1. Define a subsystem class
         1. Define components of the subsystem in the subsystem class - motors, motor groups, sensors
         1. Define functions - at a basic level what can this subsystem do - shoot, drive, etc.
         1. Publish telementry through NetworkTables
   1. Define command classes
         1. There can be more than one - collect balls, eject balls
         2. Pass subsystem to the constructor
   1. Wire up subsystems, commands and buttons in RobotContainer
         1. Instance of subsystem
         2. Wireup code

### Git commands    

Note: Just use the built in visual studio code git stuff, it's easiar. I'm leaving this here in case.

```git clone https://github.com/FRCTeam1719/2022Robot_3``` command to get brand new repository

```git pull``` pull down any updates from github

```git add . ``` add all files that were added or modified to the local repo

```git commit -m "<message goes here>" ``` stage files to commit

```git push``` push your commits to github

```git checkout (-b) <branch goes here>``` Creates / pulls a branch from github. Use -b when checking out a new branch!

## Programmers:

------------------------------------------------------
Sam Rabb-Jaros  @GethrexFe(Lead programmer 1)
-------------------------------------------------------
Jai Setty @IBHishere(Cool deaL Programmer)
_______________________________________________________
Tegan Hakim @TeganHakim
_______________________________________________________
Harrison @Hbg1010 
_______________________________________________________
Neel @realrealneel
___________________________________________________
Owen @Owr333
_______________________________________________________
Cece @cece1233




