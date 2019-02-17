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
    private WPI_TalonSRX upperScrewTalon;
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
    private double kD = -0;

    // Things for SmartMotion Stuff
    //TODO Get numbers
    private double maxRPM = 5000;
    //TODO Test these variables so they don't break the robot
    private int maxAcc = 4096;
    private int maxVel = 4096*2;

    // Settings For Elevator (Inches)
    // {Up Screw, Low Screw, Piston}
    private double[]
        groundSet = {0,0,0},
        lowHatchSet = {4,4,0},
        lowCargoSet = {4,4,0},
        midHatchSet = {25,18,0},
        midCargoSet = {10,10,0},
        highHatchSet = {23,18,1},
        highCargoSet = {23,18,1};
    
    private double gearRatio = 11.0/8.0;
    public Elevator() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        elevatorPiston = new DoubleSolenoid(0, 2, 3);
        addChild("ElevatorPiston",elevatorPiston);
        
        
        upperScrewTalon = new WPI_TalonSRX(8);
        
        
        
        lowerScrewTalon = new WPI_TalonSRX(9);
        
        
        

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
        upperScrewTalon.configMotionAcceleration(maxAcc);
        upperScrewTalon.configMotionCruiseVelocity(maxVel);
        upperScrewTalon.setNeutralMode(NeutralMode.Brake);

        lowerScrewTalon.setSensorPhase(true);
        lowerScrewTalon.clearStickyFaults();
        lowerScrewTalon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative,0,4000);
        lowerScrewTalon.config_kP(0, kP);
        lowerScrewTalon.config_kI(0, kI);
        lowerScrewTalon.config_kD(0, kD);
        lowerScrewTalon.config_kF(0, kF);
        lowerScrewTalon.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen);
        lowerScrewTalon.configMotionAcceleration(maxAcc);
        lowerScrewTalon.configMotionCruiseVelocity(maxVel);
        upperScrewTalon.setNeutralMode(NeutralMode.Brake);
    }

    @Override
    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        setDefaultCommand(new DownUntilLimit());

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }

    @Override
    public void periodic() {
        // Put code here to be run every loop
        //Stuff for smartDashboard will delete eventually
        SmartDashboard.putNumber("Upper Screw Position",upperScrewTalon.getSensorCollection().getQuadraturePosition()/4096*(22/16));
        SmartDashboard.putNumber("Lower Screw Position",lowerScrewTalon.getSensorCollection().getQuadraturePosition()/4096*(22/16));
        SmartDashboard.putBoolean("Upper Screw Switch",!upperScrewTalon.getSensorCollection().isRevLimitSwitchClosed());
        SmartDashboard.putBoolean("Lower Screw Switch",!lowerScrewTalon.getSensorCollection().isRevLimitSwitchClosed());
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
    //Limit Switches are normally open
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
    public void elevatorLogic(double[] setpoints){
        upperScrewTalon.set( ControlMode.Position, setpoints[0]*gearRatio * 4096);
        lowerScrewTalon.set(ControlMode.Position,setpoints[1]*gearRatio * 4096);
        if(setpoints[2] == 0){
            if(elevatorPiston.get() == Value.kForward|| elevatorPiston.get() == Value.kOff){
                elevatorDown();
            }
            SmartDashboard.putNumber("Stuff",setpoints[0]);
            SmartDashboard.putNumber("Stuff2", setpoints[1]);
        }
        else if(setpoints[2] == 1){
            if(elevatorPiston.get() == Value.kReverse || elevatorPiston.get() == Value.kOff){
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
}
