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
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS


/**
 *
 */
public class Gripper extends Subsystem {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private Compressor compressor;
    private DoubleSolenoid gripper;
    private DoubleSolenoid pusher;
    private WPI_TalonSRX armTalon;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    public Gripper() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        compressor = new Compressor(0);
        addChild("Compressor",compressor);
        
        
        gripper = new DoubleSolenoid(1, 0, 1);
        addChild("Gripper",gripper);
        
        
        pusher = new DoubleSolenoid(1, 2, 3);
        addChild("Pusher",pusher);
        
        
        armTalon = new WPI_TalonSRX(11);
        
        
        

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        compressor.setClosedLoopControl(true);
        armTalon.setSensorPhase(true);
        armTalon.configSelectedFeedbackSensor(FeedbackDevice.Analog,0,4000);
        armTalon.clearStickyFaults();
        
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

    }

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CMDPIDGETTERS


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CMDPIDGETTERS

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    // Base Gripper Methods
    public void gripperOpen(){
        gripper.set(Value.kForward);
    }
    public void gripperClose(){
        gripper.set(Value.kForward);
    }
    public void gripperOff(){
        gripper.set(Value.kOff);
    }

    // Base Pusher Methods
    public void pusherOut(){
        gripper.set(Value.kForward);
    }
    public void pusherIn(){
        gripper.set(Value.kReverse);
    }
    public void pusherOff(){
        gripper.set(Value.kOff);
    }

    //Base Arm Methods
    //TODO Fix this for Talon
    public void armUp(){
        //Talon.set(Value.kReverse);
    }
    public void armDown(){
        //arm.set(Value.kForward);
    }
    public void armOff(){
        //arm.set(Value.kOff);
    }
}

