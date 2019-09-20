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
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.POVButton;

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
    public JoystickButton climberForwardB;
    public JoystickButton climberBackwardB;
    public Joystick leftJoy;
    public Joystick rightJoy;
    public JoystickButton moveElevStow;
    public JoystickButton moveElevGround;
    public JoystickButton moveElevLowHatch;
    public JoystickButton moveElevMidHatch;
    public JoystickButton moveElevMidCargo;
    public JoystickButton moveElevLowCargo;
    public JoystickButton frontClimberDeployB;
    public JoystickButton frontClimberRetractB;
    public JoystickButton backClimberDeployB;
    public JoystickButton backClimberRetractB;
    public JoystickButton homeDownB;
    public Joystick opLeftJoy;
    public JoystickButton shootB;
    public JoystickButton openGripB;
    public JoystickButton gripCloseB;
    public JoystickButton armDownButton;
    public JoystickButton armUpButton;
    public JoystickButton pusherOutB;
    public JoystickButton moveBottomDownB;
    public JoystickButton moveBottomUpB;
    public JoystickButton raiseHab2B;
    public JoystickButton raiseHab3B;
    public JoystickButton retractFrontClimberB;
    public JoystickButton retractBackClimberB;
    public Joystick opRightJoy;
    public JoystickButton lowCargoB;
    public JoystickButton midCargoB;
    public JoystickButton lowHatchB;
    public JoystickButton midHatchB;
    public JoystickButton cargoB;
    public JoystickButton groundB;
    public JoystickButton stowB;
    public Joystick buttonBoard;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public POVButton climberForwardPOV;
    public POVButton climberBackwardPOV;


    public OI() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS

        buttonBoard = new Joystick(4);
        
        stowB = new JoystickButton(buttonBoard, 9);
        stowB.whenPressed(new MoveElevator("Stow"));
        groundB = new JoystickButton(buttonBoard, 8);
        groundB.whenPressed(new MoveElevator("Ground"));
        cargoB = new JoystickButton(buttonBoard, 7);
        cargoB.whenPressed(new MoveElevator("Cargo"));
        midHatchB = new JoystickButton(buttonBoard, 5);
        midHatchB.whenPressed(new MoveElevator("MidHatch"));
        lowHatchB = new JoystickButton(buttonBoard, 4);
        lowHatchB.whenPressed(new MoveElevator("LowHatch"));
        midCargoB = new JoystickButton(buttonBoard, 2);
        midCargoB.whenPressed(new MoveElevator("MidCargo"));
        lowCargoB = new JoystickButton(buttonBoard, 1);
        lowCargoB.whenPressed(new MoveElevator("LowCargo"));
        opRightJoy = new Joystick(2);
        
        retractBackClimberB = new JoystickButton(opRightJoy, 8);
        retractBackClimberB.whenPressed(new RetractBackClimber());
        retractFrontClimberB = new JoystickButton(opRightJoy, 7);
        retractFrontClimberB.whenPressed(new RetractFrontClimber());
        raiseHab3B = new JoystickButton(opRightJoy, 10);
        raiseHab3B.whenPressed(new RaiseHab3());
        raiseHab2B = new JoystickButton(opRightJoy, 9);
        raiseHab2B.whenPressed(new RaiseHab2());
        moveBottomUpB = new JoystickButton(opRightJoy, 12);
        moveBottomUpB.whileHeld(new MoveBottomUp());
        moveBottomDownB = new JoystickButton(opRightJoy, 11);
        moveBottomDownB.whileHeld(new MoveBottomDown());
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
        
        homeDownB = new JoystickButton(opLeftJoy, 7);
        homeDownB.whenPressed(new HomeElevDown());
        backClimberRetractB = new JoystickButton(opLeftJoy, 4);
        backClimberRetractB.whileHeld(new BackClimberRetract());
        backClimberDeployB = new JoystickButton(opLeftJoy, 6);
        backClimberDeployB.whileHeld(new BackClimberDeploy());
        frontClimberRetractB = new JoystickButton(opLeftJoy, 3);
        frontClimberRetractB.whileHeld(new FrontClimberRetract());
        frontClimberDeployB = new JoystickButton(opLeftJoy, 5);
        frontClimberDeployB.whileHeld(new FrontClimberDeploy());
        moveElevLowCargo = new JoystickButton(opLeftJoy, 12);
        moveElevLowCargo.whenPressed(new MoveElevator("LowCargo"));
        moveElevMidCargo = new JoystickButton(opLeftJoy, 10);
        moveElevMidCargo.whileHeld(new MoveElev
        ator("MidCargo"));
        moveElevMidHatch = new JoystickButton(opLeftJoy, 9);
        moveElevMidHatch.whileHeld(new MoveElevator("MidHatch"));
        moveElevLowHatch = new JoystickButton(opLeftJoy, 11);
        moveElevLowHatch.whenPressed(new MoveElevator("LowHatch"));
        moveElevGround = new JoystickButton(opLeftJoy, 2);
        moveElevGround.whenPressed(new MoveElevator("Ground"));
        moveElevStow = new JoystickButton(opLeftJoy, 1);
        moveElevStow.whenPressed(new MoveElevator("Stow"));
        rightJoy = new Joystick(0);
        
        leftJoy = new Joystick(1);
        
        climberBackwardB = new JoystickButton(leftJoy, 3);
        climberBackwardB.whileHeld(new ClimberBackward());
        climberForwardB = new JoystickButton(leftJoy, 5);
        climberForwardB.whileHeld(new ClimberForward());


        // SmartDashboard Buttons
        SmartDashboard.putData("DriveWJoystick", new DriveWJoystick());
        SmartDashboard.putData("GripOpen", new GripOpen());
        SmartDashboard.putData("GripClose", new GripClose());
        SmartDashboard.putData("PusherIn", new PusherIn());
        SmartDashboard.putData("PusherOut", new PusherOut());
        SmartDashboard.putData("MoveElevator: lowHatch", new MoveElevator("lowHatch"));
        SmartDashboard.putData("MoveElevator: highHatch", new MoveElevator("midHatch"));
        SmartDashboard.putData("DriveForward", new DriveForward());
        SmartDashboard.putData("ArmToAngle: Vertical", new ArmToAngle(0));
        SmartDashboard.putData("Shoot", new Shoot());
        SmartDashboard.putData("ArmDown", new ArmDown());
        SmartDashboard.putData("ArmUp", new ArmUp());
        SmartDashboard.putData("MoveBottomDown", new MoveBottomDown());
        SmartDashboard.putData("MoveBottomUp", new MoveBottomUp());
        SmartDashboard.putData("HomeElevDown", new HomeElevDown());
        SmartDashboard.putData("PusherTimed", new PusherTimed());
        SmartDashboard.putData("GripperOpenTimed", new GripperOpenTimed());
        SmartDashboard.putData("FrontClimberDeploy", new FrontClimberDeploy());
        SmartDashboard.putData("FrontClimberRetract", new FrontClimberRetract());
        SmartDashboard.putData("BackClimberDeploy", new BackClimberDeploy());
        SmartDashboard.putData("BackClimberRetract", new BackClimberRetract());
        SmartDashboard.putData("ClimberForward", new ClimberForward());
        SmartDashboard.putData("ClimberBackward", new ClimberBackward());
        SmartDashboard.putData("RaiseHab2", new RaiseHab2());
        SmartDashboard.putData("RaiseHab3", new RaiseHab3());
        SmartDashboard.putData("RetractFrontClimber", new RetractFrontClimber());
        SmartDashboard.putData("RetractBackClimber", new RetractBackClimber());

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
    climberBackwardPOV = new POVButton(leftJoy, 180);
    climberBackwardPOV.whileHeld(new ClimberBackward());
    climberForwardPOV = new POVButton(leftJoy, 0);
    climberForwardPOV.whileHeld(new ClimberForward());
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

