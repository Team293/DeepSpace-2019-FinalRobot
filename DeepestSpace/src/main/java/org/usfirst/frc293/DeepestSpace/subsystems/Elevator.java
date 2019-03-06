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
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import com.ctre.phoenix.motorcontrol.NeutralMode;
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
    private WPI_TalonSRX lowerScrewTalon;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    // Declaring Spark Stuff


    // Limit Switches
    //TODO Verify amount of limit switches we are using
 

    //PID Parameters
    //TODO Tune these things
    private double kF = 0;
    private double kP = .15;
    private double kI = 0;
    private double kD = 0.001;

    // Things for SmartMotion Stuff
    //TODO Get numbers
    private double maxRPM = 5000;
    //TODO Test these variables so they don't break the robot
    private int maxAcc = 4096;
    private int maxVel = 4096*2;
    

    // Settings For Elevator (Inches)
    // High Screw, Low Screw, Piston}u
    public double[]
        groundSet = {0,0,0},
        lowHatchSet = {0,8.5,0}, //used to be (0,0,0)
        lowCargoSet = {0,4.5,0},
        midHatchSet = {0,7,1},
        midCargoSet = {0,4,1},
        highHatchSet = {21,10.5,1}, //used to be (23,18,1)
        highCargoSet = {15.5,10.5,1},
        cargoShipSet = {0,5,1},
        stowSet = {16,0,0};
    
    private double gearRatio = 11.0/8.0;
        
    public Elevator() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        elevatorPiston = new DoubleSolenoid(0, 2, 3);
        addChild("ElevatorPiston",elevatorPiston);
        
        
        lowerScrewTalon = new WPI_TalonSRX(9);
        
        
        

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        /*upperScrewTalon.setSensorPhase(true);
        upperScrewTalon.clearStickyFaults();
        upperScrewTalon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative,0,4000);
        upperScrewTalon.config_kP(0, kP);
        upperScrewTalon.config_kI(0, kI);
        upperScrewTalon.config_kD(0, kD);
        upperScrewTalon.config_kF(0, kF);
        //These Are Very Long Lines
        //You Might Want to fix that
        upperScrewTalon.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen);
        upperScrewTalon.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen);
        upperScrewTalon.configMotionAcceleration(maxAcc);
        upperScrewTalon.configMotionCruiseVelocity(maxVel);
        upperScrewTalon.setNeutralMode(NeutralMode.Brake);*/
        // upperScrewTalon.configForwardSoftLimitEnable(true);
        // upperScrewTalon.configForwardSoftLimitThreshold(135168,4000); //135168


        lowerScrewTalon.setSensorPhase(true);
        lowerScrewTalon.clearStickyFaults();
        lowerScrewTalon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative,0,4000);
        lowerScrewTalon.config_kP(0, kP);
        lowerScrewTalon.config_kI(0, kI);
        lowerScrewTalon.config_kD(0, kD);
        lowerScrewTalon.config_kF(0, kF);
        lowerScrewTalon.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen);
        lowerScrewTalon.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen);
        lowerScrewTalon.configMotionAcceleration(maxAcc);
        lowerScrewTalon.configMotionCruiseVelocity(maxVel);
        lowerScrewTalon.setNeutralMode(NeutralMode.Brake);

        lowerScrewTalon.configForwardSoftLimitEnable(true);
        lowerScrewTalon.configForwardSoftLimitThreshold(73216,4000);

        // setting encoder to stow values
        // NOTE: This logic assumes that the robot starts in the STOW position!
        // If this is not the case, the encoder will be indexed to the WRONG value, until it
        // encounters a limit switch, at which time it will be reset.
        //setHighEncStow();
        setLowEncStow();
        
        

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
        //Stuff for smartDashboard will delete eventually
        //SmartDashboard.putNumber("Upper Screw Position",upperScrewTalon.getSensorCollection().getQuadraturePosition()/4096 * (.727272));
        SmartDashboard.putNumber("Lower Screw Position",lowerScrewTalon.getSensorCollection().getQuadraturePosition()/4096*(0.727272));
        ////SmartDashboard.putBoolean("Upper Screw Low Switch",!upperScrewTalon.getSensorCollection().isRevLimitSwitchClosed());
        SmartDashboard.putBoolean("Lower Screw Low Switch",!lowerScrewTalon.getSensorCollection().isRevLimitSwitchClosed());
        //SmartDashboard.putBoolean("Upper Screw High Switch",!upperScrewTalon.getSensorCollection().isFwdLimitSwitchClosed());
        //SmartDashboard.putNumber("Upper Raw Data", upperScrewTalon.getSensorCollection().getQuadraturePosition());
        SmartDashboard.putNumber("Lower Raw Data", lowerScrewTalon.getSensorCollection().getQuadraturePosition());
        //SmartDashboard.putNumber("Upper Test INches",getHighInch());
        SmartDashboard.putNumber("Lower Test Inches",getLowInch());
        SmartDashboard.putBoolean("At position to stow",atPosition(stowSet));
        
        /*if(upperScrewTalon.getSensorCollection().isRevLimitSwitchClosed()){
            upperScrewTalon.setSelectedSensorPosition(0);
        }*/
        if(lowerScrewTalon.getSensorCollection().isRevLimitSwitchClosed()){
            lowerScrewTalon.setSelectedSensorPosition(0);
        }
       /*if(upperScrewTalon.getSensorCollection().isFwdLimitSwitchClosed()){
            upperScrewTalon.setSelectedSensorPosition(135168); //TODO check value
        }*/
        if(lowerScrewTalon.getSensorCollection().isFwdLimitSwitchClosed()){
            lowerScrewTalon.setSelectedSensorPosition(73216); //TODO check value
        }

    }

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CMDPIDGETTERS


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CMDPIDGETTERS

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    // Base Methods for Elevator Talons
    public void elevatorUp(){
        elevatorPiston.set(Value.kReverse);
    }
    public void elevatorDown(){
        //For Testing
        elevatorPiston.set(Value.kForward);
    }
    public void elevatorOff(){
        elevatorPiston.set(Value.kOff);
    }

    //Base Methods for Low Screw (Will be Deprecated Soon)
    public void lowScrewUp(){
        lowerScrewTalon.set(1);
    }
    public void lowScrewDown(){
        lowerScrewTalon.set(-1);
    }
    public void lowScrewStop(){
        lowerScrewTalon.set(0);
    }

    //Base Methods for Low Screw (See Above)
    /*public void highScrewUp(){
        upperScrewTalon.set(1);
    }
    public void highScrewDown(){
        upperScrewTalon.set(-1);
    }
    public void highScrewStop(){
        upperScrewTalon.set(0);
    }

    //Returning Limit Switch States
    //Limit Switches are normally open
    public boolean highScrewLowLimit(){
        return upperScrewTalon.getSensorCollection().isRevLimitSwitchClosed();
    }*/
    public boolean lowScrewLowLimit(){
        return lowerScrewTalon.getSensorCollection().isRevLimitSwitchClosed();
    }/*
    public boolean highScrewHighLimit(){
        return upperScrewTalon.getSensorCollection().isFwdLimitSwitchClosed();
    }*/
    public boolean lowScrewHighLimit(){
        return lowerScrewTalon.getSensorCollection().isFwdLimitSwitchClosed();
    }



    //Encoder Stuff
    /*public void resetHighEnc(){
        upperScrewTalon.setSelectedSensorPosition(0);
    }*/
    public void resetLowEnc(){
        lowerScrewTalon.setSelectedSensorPosition(0);
    }
    /*public void setHighEncStow(){
        upperScrewTalon.setSelectedSensorPosition(90112); //16 inches * 11/8 * 4096
    }*/
    public void setLowEncStow(){
        lowerScrewTalon.setSelectedSensorPosition(0);
    }
    /*public double getHighInch(){
        double quadValue = upperScrewTalon.getSensorCollection().getQuadraturePosition();
        return (quadValue/4096.0) * (0.727272);
    }*/
    public double getLowInch(){
        double quadValue = lowerScrewTalon.getSensorCollection().getQuadraturePosition();
        return (quadValue/4096.0) * (0.727272);
    }
    public void elevatorLogic(double[] setpoints){
        //upperScrewTalon.set(ControlMode.Position, setpoints[0]*gearRatio * 4096);
        lowerScrewTalon.set(ControlMode.Position,setpoints[1]*gearRatio * 4096);

        //Reversed bc Solenoid is reversed
        if(setpoints[2] == 0){
            if(elevatorPiston.get() == Value.kReverse|| elevatorPiston.get() == Value.kOff){
                elevatorDown();
            }
        }
        else if(setpoints[2] == 1){
            if(elevatorPiston.get() == Value.kForward || elevatorPiston.get() == Value.kOff){
                elevatorUp();
            }
        }
        
    }
    /*public void elevatorMagic(double[] setpoints){
        upperScrewTalon.set( ControlMode.MotionMagic, setpoints[0]*gearRatio);
        lowerScrewTalon.set(ControlMode.MotionMagic,setpoints[1]*gearRatio);
        if(setpoints[2] == 0){
            if(elevatorPiston.get() == Value.kForward|| elevatorPiston.get() == Value.kOff){
                elevatorDown();
            }
        }
        else if(setpoints[2] == 1){
            if(elevatorPiston.get() == Value.kReverse || elevatorPiston.get() == Value.kOff){
                elevatorUp();
            }
        }
    }*/
    //Moving Screws to Position
    public void ground(){
        elevatorLogic(groundSet);
    }
    public void lowHatch(){
        elevatorLogic(lowHatchSet);
    }
    public void lowCargo(){
        elevatorLogic(lowCargoSet);
    }
    public void midHatch(){
        elevatorLogic(midHatchSet);
    }
    public void midCargo(){
        elevatorLogic(midCargoSet);
    }
    public void highHatch(){
        elevatorLogic(highHatchSet);
    }
    public void highCargo(){
        elevatorLogic(highCargoSet);
    }
    public void cargoShip(){
        elevatorLogic(cargoShipSet);
    }
    public void stow(){
        elevatorLogic(stowSet);
    }

    public boolean atPosition(double[] setpoints){
        double highAbsDif = 0.0;//getHighInch()+setpoints[0];
        SmartDashboard.putNumber("High Screw Dif",highAbsDif);
        double lowAbsDif = getLowInch()+setpoints[1];
        SmartDashboard.putNumber("Low Screw Dif",lowAbsDif);
        double totalError = Math.abs(highAbsDif + lowAbsDif);
        SmartDashboard.putNumber("Total Dif",totalError);
        if(totalError <= 1){
            return true;
        }
        else{
            return false;
        }
    }
}
