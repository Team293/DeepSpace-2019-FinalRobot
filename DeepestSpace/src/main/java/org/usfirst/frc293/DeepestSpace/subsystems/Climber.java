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
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
/**
 *
 */
public class Climber extends Subsystem {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private DoubleSolenoid frontPiston;
    private DoubleSolenoid backPiston;
    private DigitalInput frontProximity;
    private DigitalInput backProximity;
    private WPI_TalonSRX climberTalon;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    private double kF = 1.6;
    private double kP = .2;
    private double kI = .1;
    private double kD = -0;

    public Climber() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        frontPiston = new DoubleSolenoid(0, 0, 1);
        addChild("FrontPiston",frontPiston);
        
        
        backPiston = new DoubleSolenoid(0, 4, 5);
        addChild("BackPiston",backPiston);
        
        
        frontProximity = new DigitalInput(0);
        addChild("FrontProximity",frontProximity);
        
        
        backProximity = new DigitalInput(1);
        addChild("BackProximity",backProximity);
        
        
        climberTalon = new WPI_TalonSRX(10);
        
        
        

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        climberTalon.setSensorPhase(true);
        climberTalon.clearStickyFaults();
        climberTalon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative,0,4000);
        climberTalon.config_kF(0,kF,10);
        climberTalon.config_kP(0,kP,10);
        climberTalon.config_kI(0,kI,10);
        climberTalon.config_kD(0,kD,0);
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

    // Front Piston Base Methods
    public void frontUp(){
        frontPiston.set(Value.kForward);
    }
    public void frontDown(){
        frontPiston.set(Value.kReverse);
    }
    public void frontOff(){
        frontPiston.set(Value.kOff);
    }

    // Back Piston Base Methods
    public void backUp(){
        backPiston.set(Value.kForward);
    }
    public void backDown(){
        backPiston.set(Value.kReverse);
    }
    public void backOff(){
        backPiston.set(Value.kOff);
    }

    //Climber Motor Base Methods
    public void climberForward(){
        climberTalon.set(0.25);
    }
    public void climberStop(){
        climberTalon.set(0);
    }
}

