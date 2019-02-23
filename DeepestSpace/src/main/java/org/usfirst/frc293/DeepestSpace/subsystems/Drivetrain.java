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
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDBase.Tolerance;
import edu.wpi.first.wpilibj.SPI.Port;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.FollowerType;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.RemoteSensorSource;
// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import org.usfirst.frc293.DeepestSpace.Robot;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.ControlType;
/**
 *
 */
public class Drivetrain extends Subsystem {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    CANSparkMax leftMotor1;
    CANSparkMax leftMotor2;
    CANPIDController leftPID;
    CANEncoder leftEncoder;

    CANSparkMax rightMotor1;
    CANSparkMax rightMotor2;
    CANPIDController rightPID;
    CANEncoder rightEncoder;

    double kP = 5e-5; 
    double kI = 1e-6;
    double kD = 0;
    double kF = 0;
    double speed = 1.0;
    double maxRpm = 5700.0;
    double minOutput = -1;
    double maxOutput = 1; 

    //Smart Stuff that is Smart
    //Units are in RPM (Theoretically)
    //I don't trust the documentation though
    double maxVel = 3000;
    double minVel = 1000;
    double maxAcc = 3000;
    double minAcc = 1000;

    AHRS gyro;
    double gyroAngle;

    public Drivetrain() {

        leftMotor1 = new CANSparkMax(4, MotorType.kBrushless);
        leftMotor1.setInverted(true);
        leftMotor2 = new CANSparkMax(5, MotorType.kBrushless);
        leftMotor2.follow(leftMotor1);
        leftEncoder = leftMotor1.getEncoder();
        //TODO Confirm
        leftEncoder.setPositionConversionFactor(0.25);
        leftPID = leftMotor1.getPIDController();
        leftPID.setOutputRange(minOutput, maxOutput);
        
        
        
        rightMotor1 = new CANSparkMax(2, MotorType.kBrushless);
        rightMotor2 = new CANSparkMax(3, MotorType.kBrushless);
        rightMotor2.follow(rightMotor1);
        rightEncoder = rightMotor1.getEncoder();
        rightEncoder.setPositionConversionFactor(0.25);
        rightPID = rightMotor1.getPIDController();
        rightPID.setOutputRange(minOutput, maxOutput); 
        

        leftPID.setP(kP);
        leftPID.setI(kI);
        leftPID.setD(kD);
        leftPID.setFF(kF);
        

        rightPID.setP(kP);
        rightPID.setI(kI);
        rightPID.setD(kD);
        rightPID.setFF(kF);

        gyro = new AHRS(Port.kMXP);

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

        //Updating Angle From Gyro
        //TODO Wire Gyro
        gyroAngle = gyro.getAngle();
        SmartDashboard.putNumber("Angle of Robot", gyroAngle);
    }

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CMDPIDGETTERS


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CMDPIDGETTERS

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void velocityDrive(Joystick left, Joystick right){
        double leftSetpoint = left.getY() * maxRpm * speed;
        double rightSetpoint = right.getY() * maxRpm * speed;
        leftPID.setReference(leftSetpoint, ControlType.kVelocity);
        rightPID.setReference(rightSetpoint, ControlType.kVelocity);
    }
    
    public void distance(double rotations){
        leftPID.setReference(rotations, ControlType.kPosition);
        rightPID.setReference(rotations, ControlType.kPosition);
    }
    /*public void magicDistance(double inches){
        leftPID.setReference(inches, ControlType.kSmartMotion);
    }*/
    
    public void stop(){
        leftMotor1.set(0);
        rightMotor1.set(0);
    }

    public void full(){
        leftMotor1.set(1);
        rightMotor1.set(1);

    }
    public void dumbJoy(Joystick left, Joystick right){
        leftMotor1.set(left.getY());
        rightMotor1.set(right.getY());
    }
    public void percentDrive(double speed){
        leftMotor1.set(0.25);
        rightMotor1.set(0.25);
    }
}
