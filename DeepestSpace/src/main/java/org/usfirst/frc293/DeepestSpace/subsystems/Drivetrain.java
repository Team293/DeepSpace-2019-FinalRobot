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
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import org.usfirst.frc293.DeepestSpace.Robot;

/**
 *
 */
public class Drivetrain extends Subsystem {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private WPI_TalonSRX leftTalon1;
    private WPI_TalonSRX leftTalon2;
    private WPI_TalonSRX leftTalon3;
    private WPI_TalonSRX rightTalon1;
    private WPI_TalonSRX rightTalon2;
    private WPI_TalonSRX rightTalon3;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private double kF = 1.6;
    private double kP = .2;
    private double kI = .1;
    private double kD = -0;
    
    private double speed = 1.0;
    //TODO Find Max RPM
    private double maxRPM = 1000;
    public Drivetrain() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        leftTalon1 = new WPI_TalonSRX(0);
        
        
        
        leftTalon2 = new WPI_TalonSRX(1);
        
        
        
        leftTalon3 = new WPI_TalonSRX(2);
        
        
        
        rightTalon1 = new WPI_TalonSRX(3);
        
        
        
        rightTalon2 = new WPI_TalonSRX(4);
        
        
        
        rightTalon3 = new WPI_TalonSRX(5);
        
        
        

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS

    leftTalon1.setSensorPhase(true);
    leftTalon1.clearStickyFaults();
    //TODO Check Values
    leftTalon1.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative,0,4000);
    //TODO Tune PIDF
    leftTalon1.config_kF(0,kF,10);
    leftTalon1.config_kP(0,kP,10);
    leftTalon1.config_kI(0,kI,10);
    leftTalon1.config_kD(0,kD,0);

    leftTalon2.follow(leftTalon1);
    leftTalon3.follow(leftTalon1);

    rightTalon1.setSensorPhase(true);
    rightTalon1.clearStickyFaults();
    //TODO Check Values
    rightTalon1.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative,0,4000);
    //TODO Tune PIDF
    rightTalon1.config_kF(0,kF,10);
    rightTalon1.config_kP(0,kP,10);
    rightTalon1.config_kI(0,kI,10);
    rightTalon1.config_kD(0,kD,0);

    rightTalon2.follow(rightTalon1);
    rightTalon3.follow(rightTalon1);
    }

    @Override
    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        setDefaultCommand(new DriveWJoystick());

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }

    @Override
    public void periodic() {
        // Put code here to be run every loop
        if(Robot.oi.getLeftJoy().getTrigger()||Robot.oi.getRightJoy().getTrigger()){
            speed = 0.25;
        }
        else{
            speed = 1.0;
        }
    }

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CMDPIDGETTERS


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CMDPIDGETTERS

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    public void full(){
        leftTalon1.set(100);
        rightTalon1.set(100);
    }
    public void stop(){
        leftTalon1.set(0);
        rightTalon1.set(0);
    }
    public void velocityDrive(Joystick left, Joystick right){
        double leftSetpoint = left.getY() * maxRPM * speed;
        double rightSetpoint = right.getY() * maxRPM * speed;
        leftTalon1.set(ControlMode.Velocity, leftSetpoint);
        rightTalon1.set(ControlMode.Velocity, rightSetpoint);
    }
    //WIP
    //TODO Fix it
    //TODO Motion Magic
    public void distance(double leftInch, double rightInch){
        leftTalon1.set(ControlMode.Position,leftInch);
        rightTalon1.set(ControlMode.Position,rightInch);
    }
    

    //For Redundancy
    public void percentDrive(){
        leftTalon1.set(Robot.oi.getLeftJoy().getY());
        rightTalon1.set(Robot.oi.getRightJoy().getY());
    }
}
