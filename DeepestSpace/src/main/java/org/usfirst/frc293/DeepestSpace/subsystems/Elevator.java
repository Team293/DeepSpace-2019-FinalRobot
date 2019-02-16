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
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.DoubleSolenoid;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import com.revrobotics.CANDigitalInput;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANDigitalInput.LimitSwitchPolarity;

/**
 *
 */
public class Elevator extends Subsystem {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private DoubleSolenoid elevatorPiston;
    private WPI_TalonSRX upperScrewTalon;
    private WPI_TalonSRX lowerScrewTalon;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    // Declaring Spark Stuff


    // Limit Switches
    //TODO Verify amount of limit switches we are using
 

    //PID Parameters
    //TODO Tune these things
    private double kF = .2;
    private double kP = .15;
    private double kI = 0;
    private double kD = -0;

    // Things for SmartMotion Stuff
    //TODO Get numbers
    private double maxRPM = 5000;
    private double maxAcc = 1000;
    private double maxVel = 2000;

    // Settings For Elevator (Inches)
    private double[]
        groundSet = {0,0},
        lowHatchSet = {0,0},
        lowCargoSet = {0,0},
        midHatchSet = {0,0},
        midCargoSet = {0,0},
        highHatchSet = {0,0},
        highCargoSet = {0,0};

    public Elevator() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        elevatorPiston = new DoubleSolenoid(1, 2, 3);
        addChild("ElevatorPiston",elevatorPiston);
        
        
        upperScrewTalon = new WPI_TalonSRX(6);
        
        
        
        lowerScrewTalon = new WPI_TalonSRX(7);
        
        
        

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        upperScrewTalon.setSensorPhase(true);
        upperScrewTalon.clearStickyFaults();
        upperScrewTalon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative,0,4000);
        upperScrewTalon.config_kP(0, kP);
        upperScrewTalon.config_kI(0, kI);
        upperScrewTalon.config_kD(0, kD);
        upperScrewTalon.config_kF(0, kF);
        //These Are Very Long Lines
        //You Might Want to fix that
        upperScrewTalon.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen);

        lowerScrewTalon.setSensorPhase(true);
        lowerScrewTalon.clearStickyFaults();
        lowerScrewTalon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative,0,4000);
        lowerScrewTalon.config_kP(0, kP);
        lowerScrewTalon.config_kI(0, kI);
        lowerScrewTalon.config_kD(0, kD);
        lowerScrewTalon.config_kF(0, kF);
        lowerScrewTalon.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen);
        
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

    // Base Methods for Elevator Talons
    public void elevatorUp(){
        elevatorPiston.set(Value.kForward);
    }
    public void elevatorDown(){
        elevatorPiston.set(Value.kReverse);
    }
    public void elevatorOff(){
        elevatorPiston.set(Value.kOff);
    }

    //Base Methods for Low Screw (Will be Deprecated Soon)
    public void lowScrewUp(){
        lowerScrewTalon.set(0.25);
    }
    public void lowScrewDown(){
        lowerScrewTalon.set(-0.25);
    }
    public void lowScrewStop(){
        lowerScrewTalon.set(0);
    }

    //Base Methods for Low Screw (See Above)
    public void highScrewUp(){
        upperScrewTalon.set(0.25);
    }
    public void highScrewDown(){
        upperScrewTalon.set(-0.25);
    }
    public void highScrewStop(){
        upperScrewTalon.set(0);
    }

    //Returning Limit Switch States
    //TODO Verify limit switches are normally closed.
    public boolean highScrewLowLimit(){
        return upperScrewTalon.getSensorCollection().isRevLimitSwitchClosed();
    }
    public boolean lowScrewLowLimit(){
        return lowerScrewTalon.getSensorCollection().isRevLimitSwitchClosed();
    }


    //Encoder Stuff
    public void resetHighEnc(){
        upperScrewTalon.setSelectedSensorPosition(0);
    }
    public void resetLowEnc(){
        lowerScrewTalon.setSelectedSensorPosition(0);
    }

    //Moving Screws to Position
    public void ground(){
        if(elevatorPiston.get() == Value.kOff || elevatorPiston.get() == Value.kForward){
            elevatorDown();
        }
        lowerScrewTalon.set( ControlMode.Position, groundSet[0]);
        upperScrewTalon.set(ControlMode.Position,groundSet[1]);
    }
    public void lowHatch(){
        if(elevatorPiston.get() == Value.kOff || elevatorPiston.get() == Value.kForward){
            elevatorDown();
        }
        lowerScrewTalon.set( ControlMode.Position, lowHatchSet[0]);
        upperScrewTalon.set(ControlMode.Position,lowHatchSet[1]);
    }
    public void lowCargo(){
        if(elevatorPiston.get() == Value.kOff || elevatorPiston.get() == Value.kForward){
            elevatorDown();
        }
        lowerScrewTalon.set( ControlMode.Position, lowCargoSet[0]);
        upperScrewTalon.set(ControlMode.Position,lowCargoSet[1]);
    }
    public void midHatch(){
        if(elevatorPiston.get() == Value.kOff || elevatorPiston.get() == Value.kForward){
            elevatorDown();
        }
        lowerScrewTalon.set( ControlMode.Position, midHatchSet[0]);
        upperScrewTalon.set(ControlMode.Position,midHatchSet[1]);
    }
    public void midCargo(){
        if(elevatorPiston.get() == Value.kOff || elevatorPiston.get() == Value.kForward){
            elevatorDown();
        }
        lowerScrewTalon.set( ControlMode.Position, midCargoSet[0]);
        upperScrewTalon.set(ControlMode.Position,midCargoSet[1]);
    }
    public void highHatch(){
        if(elevatorPiston.get() == Value.kOff || elevatorPiston.get() == Value.kReverse){
            elevatorUp();
        }
        lowerScrewTalon.set( ControlMode.Position, highHatchSet[0]);
        upperScrewTalon.set(ControlMode.Position,highHatchSet[1]);
    }
    public void highCargo(){
        if(elevatorPiston.get() == Value.kOff || elevatorPiston.get() == Value.kReverse){
            elevatorUp();
        }
        lowerScrewTalon.set( ControlMode.Position, highCargoSet[0]);
        upperScrewTalon.set(ControlMode.Position,highCargoSet[1]);
    }
}
