// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc293.DeepestSpace;

import org.usfirst.frc293.DeepestSpace.commands.*;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Joystick;
import org.usfirst.frc293.DeepestSpace.subsystems.*;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
   /*
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);

    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.

    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:

    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());

    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());

    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());
*/

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public Joystick leftJoy;
    public Joystick rightJoy;
    public JoystickButton moveElevStow;
    public JoystickButton moveElevGround;
    public JoystickButton moveElevLowHatch;
    public JoystickButton moveElevLowCargo;
    public JoystickButton moveElevMidHatch;
    public JoystickButton moveElevMidCargo;
    public JoystickButton moveElevHighHatch;
    public JoystickButton moveElevHighCargo;
    public Joystick opLeftJoy;
    public JoystickButton shootB;
    public JoystickButton openGripB;
    public JoystickButton gripCloseB;
    public JoystickButton armDownButton;
    public JoystickButton armUpButton;
    public JoystickButton pusherOutB;
    public JoystickButton homeDownB;
    public JoystickButton moveTopUpB;
    public JoystickButton moveTopDownB;
    public JoystickButton moveBottomDownB;
    public JoystickButton moveBottomUpB;
    public Joystick opRightJoy;
    public JoystickButton lowCargoB;
    public JoystickButton midCargoB;
    public JoystickButton highCargoB;
    public JoystickButton lowHatchB;
    public JoystickButton midHatchB;
    public JoystickButton highHatchB;
    public JoystickButton cargoB;
    public JoystickButton groundB;
    public JoystickButton stowB;
    public Joystick buttonBoard;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    public OI() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS

        buttonBoard = new Joystick(4);
        
        stowB = new JoystickButton(buttonBoard, 9);
        stowB.whenPressed(new MoveElevator("Stow"));
        groundB = new JoystickButton(buttonBoard, 8);
        groundB.whenPressed(new MoveElevator("Ground"));
        cargoB = new JoystickButton(buttonBoard, 7);
        cargoB.whenPressed(new MoveElevator("CargoShip"));
        highHatchB = new JoystickButton(buttonBoard, 3);
        highHatchB.whenPressed(new MoveElevator("HighHatch"));
        midHatchB = new JoystickButton(buttonBoard, 2);
        midHatchB.whenPressed(new MoveElevator("MidHatch"));
        lowHatchB = new JoystickButton(buttonBoard, 1);
        lowHatchB.whenPressed(new MoveElevator("LowHatch"));
        highCargoB = new JoystickButton(buttonBoard, 6);
        highCargoB.whenPressed(new MoveElevator("HighCargo"));
        midCargoB = new JoystickButton(buttonBoard, 5);
        midCargoB.whenPressed(new MoveElevator("MidCargo"));
        lowCargoB = new JoystickButton(buttonBoard, 4);
        lowCargoB.whenPressed(new MoveElevator("LowCargo"));
        opRightJoy = new Joystick(2);
        
        moveBottomUpB = new JoystickButton(opRightJoy, 5);
        moveBottomUpB.whileHeld(new MoveBottomUp());
        moveBottomDownB = new JoystickButton(opRightJoy, 9);
        moveBottomDownB.whileHeld(new MoveBottomDown());
        moveTopDownB = new JoystickButton(opRightJoy, 10);
        moveTopDownB.whileHeld(new MoveTopDown());
        moveTopUpB = new JoystickButton(opRightJoy, 6);
        moveTopUpB.whileHeld(new MoveTopUp());
        homeDownB = new JoystickButton(opRightJoy, 11);
        homeDownB.whenPressed(new HomeElevDown());
        pusherOutB = new JoystickButton(opRightJoy, 2);
        pusherOutB.whileHeld(new PusherOut());
        armUpButton = new JoystickButton(opRightJoy, 6);
        armUpButton.whileHeld(new ArmUp());
        armDownButton = new JoystickButton(opRightJoy, 4);
        armDownButton.whileHeld(new ArmDown());
        gripCloseB = new JoystickButton(opRightJoy, 3);
        gripCloseB.whenPressed(new GripClose());
        openGripB = new JoystickButton(opRightJoy, 5);
        openGripB.whenPressed(new GripOpen());
        shootB = new JoystickButton(opRightJoy, 1);
        shootB.whenPressed(new Shoot());
        opLeftJoy = new Joystick(3);
        
        moveElevHighCargo = new JoystickButton(opLeftJoy, 8);
        moveElevHighCargo.whileHeld(new MoveElevator("HighCargo"));
        moveElevHighHatch = new JoystickButton(opLeftJoy, 7);
        moveElevHighHatch.whileHeld(new MoveElevator("HighHatch"));
        moveElevMidCargo = new JoystickButton(opLeftJoy, 10);
        moveElevMidCargo.whileHeld(new MoveElevator("MidCargo"));
        moveElevMidHatch = new JoystickButton(opLeftJoy, 9);
        moveElevMidHatch.whileHeld(new MoveElevator("MidHatch"));
        moveElevLowCargo = new JoystickButton(opLeftJoy, 12);
        moveElevLowCargo.whenPressed(new MoveElevator("LowCargo"));
        moveElevLowHatch = new JoystickButton(opLeftJoy, 11);
        moveElevLowHatch.whenPressed(new MoveElevator("LowHatch"));
        moveElevGround = new JoystickButton(opLeftJoy, 2);
        moveElevGround.whenPressed(new MoveElevator("Ground"));
        moveElevStow = new JoystickButton(opLeftJoy, 1);
        moveElevStow.whenPressed(new MoveElevator("Stow"));
        rightJoy = new Joystick(0);
        
        leftJoy = new Joystick(1);
        


        // SmartDashboard Buttons
        SmartDashboard.putData("Autonomous Command", new AutonomousCommand());
        SmartDashboard.putData("DriveWJoystick", new DriveWJoystick());
        SmartDashboard.putData("GripOpen", new GripOpen());
        SmartDashboard.putData("GripClose", new GripClose());
        SmartDashboard.putData("PusherIn", new PusherIn());
        SmartDashboard.putData("PusherOut", new PusherOut());
        SmartDashboard.putData("BackStiltsUp", new BackStiltsUp());
        SmartDashboard.putData("BackStiltsDown", new BackStiltsDown());
        SmartDashboard.putData("FrontStiltsUp", new FrontStiltsUp());
        SmartDashboard.putData("FrontStiltsDown", new FrontStiltsDown());
        SmartDashboard.putData("ClimberForward", new ClimberForward());
        SmartDashboard.putData("MoveElevator", new MoveElevator("string"));
        SmartDashboard.putData("DriveForward", new DriveForward());
        SmartDashboard.putData("ArmToAngle", new ArmToAngle(0.0));
        SmartDashboard.putData("Shoot", new Shoot());
        SmartDashboard.putData("ArmDown", new ArmDown());
        SmartDashboard.putData("ArmUp", new ArmUp());
        SmartDashboard.putData("MoveBottomDown", new MoveBottomDown());
        SmartDashboard.putData("MoveBottomUp", new MoveBottomUp());
        SmartDashboard.putData("MoveTopDown", new MoveTopDown());
        SmartDashboard.putData("MoveTopUp", new MoveTopUp());
        SmartDashboard.putData("HomeElevDown", new HomeElevDown());

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        shootB.whenReleased(new PusherIn());
    }

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS
    public Joystick getLeftJoy() {
        return leftJoy;
    }

    public Joystick getRightJoy() {
        return rightJoy;
    }

    public Joystick getOpLeftJoy() {
        return opLeftJoy;
    }

    public Joystick getOpRightJoy() {
        return opRightJoy;
    }

    public Joystick getButtonBoard() {
        return buttonBoard;
    }


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS
    
}

