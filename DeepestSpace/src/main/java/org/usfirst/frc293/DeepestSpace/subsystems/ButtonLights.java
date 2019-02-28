// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc293.DeepestSpace.subsystems;


import org.usfirst.frc293.DeepestSpace.commands.*;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import org.usfirst.frc293.DeepestSpace.OI;
import org.usfirst.frc293.DeepestSpace.Robot;
import edu.wpi.first.wpilibj.Joystick;
/**
 *
 */
public class ButtonLights extends Subsystem {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private boolean[] wackedMoles = {true, true, true, true, true, true, true, true};

    public ButtonLights() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
    }

    @Override
    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }

    @Override
    public void periodic() {
        // Put code here to be run every loop
        wackAMole();
    }

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CMDPIDGETTERS


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CMDPIDGETTERS

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void turnAllOff(){
        Joystick tempJoy = Robot.oi.getOpLeftJoy();
        tempJoy.setOutputs(0);
    }
    public void setButtonLight(int lightNumber,boolean state){
        Robot.oi.getOpLeftJoy().setOutput(lightNumber, state);
    }
    public void wackAMole(){
        Joystick tempJoy = Robot.oi.getOpLeftJoy();
        tempJoy.setOutput(1,wackedMoles[0]);
        if(!tempJoy.getRawButton(0)){
            wackedMoles[0] = false;
        }
        tempJoy.setOutput(2,wackedMoles[1]);
        if(!tempJoy.getRawButton(2)){
            wackedMoles[1] = false;
        }
        tempJoy.setOutput(3,wackedMoles[2]);
        if(!tempJoy.getRawButton(3)){
            wackedMoles[2] = false;
        }
        tempJoy.setOutput(4,wackedMoles[3]);
        if(!tempJoy.getRawButton(4)){
            wackedMoles[3] = false;
        }
        tempJoy.setOutput(5,wackedMoles[4]);
        if(!tempJoy.getRawButton(5)){
            wackedMoles[4] = false;
        }
        tempJoy.setOutput(6,wackedMoles[5]);
        if(!tempJoy.getRawButton(6)){
            wackedMoles[5] = false;
        }
        tempJoy.setOutput(7,wackedMoles[6]);
        if(!tempJoy.getRawButton(7)){
            wackedMoles[6] = false;
        }
        tempJoy.setOutput(8,wackedMoles[7]);
        if(!tempJoy.getRawButton(8)){
            wackedMoles[7] = false;
        }
    }
}
