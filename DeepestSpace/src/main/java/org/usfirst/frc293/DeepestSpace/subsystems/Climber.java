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



import edu.wpi.first.wpilibj.command.Subsystem;

// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.InvertType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;



/**
 *
 */
public class Climber extends Subsystem {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private WPI_TalonSRX frontClimber;
    private WPI_TalonSRX backClimber;
    private Spark climberDriver;
    private WPI_TalonSRX frontCimberFollow;
    private DigitalOutput redOutput;
    private DigitalOutput greenOutput;
    private DigitalOutput blueOutput;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    private double fkP = 4.0;
    private double fkI = -0;
    private double fkD = -0;

    private double bkP = 4.0;
    private double bkI = -0;
    private double bkD = -0;

    public double backSetpoint = 0;  // made public to see it in RaiseHabx
    public double frontSetpoint = 0;  // made public to see it in RaiseHabx
    private double manualSpeed = 40; //Raw Sensor 
    private double pidSpeed = 40;

    public double climbTarget = 0;

    // For Future   Reference
    private double hab2Height = 9; //In inches
    private double hab3Height  = 20;

    private double heightLimit = 100; // Inches ("to the moon, Alice!")

    public Climber() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        frontClimber = new WPI_TalonSRX(15);
        
        
        
        backClimber = new WPI_TalonSRX(16);
        
        
        
        climberDriver = new Spark(0);
        addChild("climberDriver",climberDriver);
        climberDriver.setInverted(false);
        
        frontCimberFollow = new WPI_TalonSRX(17);
        
        
        
        redOutput = new DigitalOutput(0);
        addChild("RedOutput",redOutput);
        
        
        greenOutput = new DigitalOutput(1);
        addChild("GreenOutput",greenOutput);
        
        
        blueOutput = new DigitalOutput(2);
        addChild("BlueOutput",blueOutput);
        
        

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS

        frontClimber.configFactoryDefault();
        backClimber.configFactoryDefault();
        frontCimberFollow.configFactoryDefault();

        frontClimber.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative,0,4000);
        frontClimber.config_kP(0, fkP);
        frontClimber.config_kI(0, fkI);
        frontClimber.config_kD(0, fkD);
        frontClimber.setSelectedSensorPosition(0);
        frontClimber.setNeutralMode(NeutralMode.Brake);
        frontClimber.setInverted(InvertType.InvertMotorOutput);
        frontClimber.setSensorPhase(true);
        // These were stored in memory and may be our limiting issue?
        //frontClimber.configForwardSoftLimitEnable(true);
        //frontClimber.configForwardSoftLimitThreshold(inchesToRaw(heightLimit));

        backClimber.setSensorPhase(false);
        backClimber.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative,0,4000);
        backClimber.config_kP(0, bkP);
        backClimber.config_kI(0, bkI);
        backClimber.config_kD(0, bkD);
        backClimber.setSelectedSensorPosition(0);
        backClimber.setNeutralMode(NeutralMode.Brake);
        // THis may be our issue with limiting positions!  Maybe ...
        //backClimber.configForwardSoftLimitEnable(true);
        //backClimber.configForwardSoftLimitThreshold(inchesToRaw(heightLimit));

        // We set the follower to ALL THE SAME parrameters as the front, THEN tell CTRE we are following!
        frontCimberFollow.set(0.); //chief delphi says that makes following work
        frontCimberFollow.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative,0,4000);
        frontCimberFollow.config_kP(0, fkP);
        frontCimberFollow.config_kI(0, fkI);
        frontCimberFollow.config_kD(0, fkD);
        frontCimberFollow.setSelectedSensorPosition(0);
        frontCimberFollow.setNeutralMode(NeutralMode.Brake);
        frontCimberFollow.setInverted(InvertType.InvertMotorOutput);
        frontCimberFollow.setSensorPhase(true);
        frontCimberFollow.follow(frontClimber);
        frontCimberFollow.setInverted(InvertType.FollowMaster);
    
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

        backClimber.set(ControlMode.Position,backSetpoint);
        frontClimber.set(ControlMode.Position,frontSetpoint);

        SmartDashboard.putNumber("Front Encoder",frontClimber.getSensorCollection().getQuadraturePosition());
        SmartDashboard.putNumber("Front Setpoint", frontClimber.getClosedLoopTarget());
        SmartDashboard.putNumber("FrontSetpoint Code",frontSetpoint);
        SmartDashboard.putNumber("Front Follower Setpoint internal", frontCimberFollow.getClosedLoopTarget());
        



        if (climbTarget != 0.0){
            if(climbAtPosition(climbTarget)){
                greenOutput.set(true);
            }
            else{
                greenOutput.set(false);
            }
        }
        else{
            greenOutput.set(false);
        }
        
    }
    
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CMDPIDGETTERS


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CMDPIDGETTERS

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void setBackPosInches(double inches){
        backSetpoint = ((inches * 4096) / (2 * 3.14));
    }
    
    public double inchesToRaw(double inches){
        return ((inches * 4096.) / (2 * 3.14));
    }

    public double rawToInches(int rawEncoderUnits){
        return ((((double)(rawEncoderUnits)) * (1/6.28))/4096);
    }

    public double getBackClimbEnc(){
        return (double) backClimber.getSensorCollection().getQuadraturePosition();
    }

    public double getFrontClimbEnc(){
        return (double) frontClimber.getSensorCollection().getQuadraturePosition();
    }

    public void backClimberUp(){
        backSetpoint += manualSpeed;
    }
    
    public void backClimberDown(){
        backSetpoint -= manualSpeed;
    }

    public void setFrontPosInches(double inches){
        frontSetpoint = ((inches * 4096) / (2 * 3.14));
    }

    public void frontClimberUp(){
        frontSetpoint += manualSpeed;
    }
    
    public void frontClimberDown(){
        frontSetpoint -= manualSpeed;
    }

    public void pidFrontUp(){
        frontSetpoint += pidSpeed;
    }

    public void pidFrontDown(){
        frontSetpoint -= pidSpeed;
    }

    public void pidBackUp(){
        backSetpoint += pidSpeed;
    }
    public void pidBackDown(){
        backSetpoint -= pidSpeed;
    }

    public void landingForward(){
        climberDriver.set(0.35);
    }

    public void landingBackward(){
        climberDriver.set(-0.35);
    }

    public void landingStop(){
        climberDriver.set(0);
    }

    public boolean climbAtPosition(double position){
        double frontDif = Math.abs(inchesToRaw(position) - (double)getFrontClimbEnc());
        double backDif = Math.abs(inchesToRaw(position) - (double)getBackClimbEnc());
        double toler = 0.25;

        if(( frontDif > inchesToRaw(position - toler) ) && ( frontDif < inchesToRaw(position + toler) )){

            if(( backDif > inchesToRaw(position - toler) ) && ( backDif < inchesToRaw(position + toler)) ){

                return true;

            }
            else{

                return false;

            }
        }
        else{

            return false;

        }
    }
}

