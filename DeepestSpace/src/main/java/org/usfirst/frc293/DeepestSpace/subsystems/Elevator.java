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
// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import com.revrobotics.CANSparkMax;
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
    private DigitalInput upScrewLimit;
    private DigitalInput downScrewLimit;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    // Declaring Spark Stuff
    private CANSparkMax lowScrewSpark;
    private CANSparkMax highScrewSpark;

    // Limit Switches
    //TODO Verify amount of limit switches we are using
    private CANDigitalInput lowBottomSwitch;
    private CANDigitalInput highBottomSwitch;

    // Encoders
    private CANEncoder lowScrewEncoder;
    private CANEncoder highScrewEncoder;

    //PID Loop
    private CANPIDController lowScrewPID;
    private CANPIDController highScrewPID;

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

    // General Safety Stuff to Generalize
    private double maxOutput = 1;
    private double minOutput = -1;

    public Elevator() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        elevatorPiston = new DoubleSolenoid(1, 2, 3);
        addChild("ElevatorPiston",elevatorPiston);
        
        
        upScrewLimit = new DigitalInput(2);
        addChild("UpScrewLimit",upScrewLimit);
        
        
        downScrewLimit = new DigitalInput(3);
        addChild("DownScrewLimit",downScrewLimit);
        
        

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
    //TODO Verify Brushed motors
    lowScrewSpark = new CANSparkMax(5, MotorType.kBrushed);
    highScrewSpark = new CANSparkMax(6, MotorType.kBrushed);

    //TODO Verify that limit switches are normally open
    lowBottomSwitch = lowScrewSpark.getForwardLimitSwitch(LimitSwitchPolarity.kNormallyOpen);
    highBottomSwitch = highScrewSpark.getForwardLimitSwitch(LimitSwitchPolarity.kNormallyOpen);

    // Making Sparks and Encoders
    lowScrewPID = lowScrewSpark.getPIDController();
    highScrewPID = highScrewSpark.getPIDController();

    lowScrewEncoder = lowScrewSpark.getEncoder();
    highScrewEncoder = highScrewSpark.getEncoder();

    // PID Settings
    lowScrewPID.setFF(kF);
    lowScrewPID.setP(kP);
    lowScrewPID.setI(kI);
    lowScrewPID.setD(kD);
    lowScrewPID.setOutputRange(minOutput, maxOutput);

    highScrewPID.setFF(kF);
    highScrewPID.setP(kP);
    highScrewPID.setI(kI);
    highScrewPID.setD(kD);
    highScrewPID.setOutputRange(minOutput, maxOutput);


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
        lowScrewSpark.set(0.25);
    }
    public void lowScrewDown(){
        lowScrewSpark.set(-0.25);
    }
    public void lowScrewStop(){
        lowScrewSpark.set(0);
    }

    //Base Methods for Low Screw (See Above)
    public void highScrewUp(){
        highScrewSpark.set(0.25);
    }
    public void highScrewDown(){
        highScrewSpark.set(-0.25);
    }
    public void highScrewStop(){
        highScrewSpark.set(0);
    }

    //Limit Switches
    public boolean getHighLimit(){
        return upScrewLimit.get();
    }
    public boolean getLowLimit(){
        return downScrewLimit.get();
    }

    //Encoder Stuff
    public void resetHighEnc(){
        highScrewEncoder = highScrewSpark.getEncoder();
    }
    public void resetLowEnc(){
        lowScrewEncoder = lowScrewSpark.getEncoder();
    }
}
