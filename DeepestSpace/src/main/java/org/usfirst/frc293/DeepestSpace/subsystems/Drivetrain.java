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
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Joystick;



// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import edu.wpi.first.wpilibj.DigitalOutput;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import org.usfirst.frc293.DeepestSpace.Robot;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.ControlType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Drivetrain extends Subsystem {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private DigitalOutput redOutput;
    private DigitalOutput greenOutput;
    private DigitalOutput blueOutput;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

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
    double maxRpm = 6000.0;
    double minOutput = -1;
    double maxOutput = 1; 

    //Smart Stuff that is Smart
    //Units are in RPM (Theoretically)
    //I don't trust the documentation though
    double maxVel = 3000;
    double minVel = 0;
    double maxAcc = 2000;
    int smartMotionSlot = 0;

    //ADIS16448_IMU imu;
    double visionCons = 0.1;

    double kYaw = 0.3;
    double avgYaw = 0.0;
    double oldYawData = 0.0;
    double newYawData = 0.0;
    
    double kRobotAng = 0.3;
    double avgRobotAngle = 0.0;
    double oldRobotAngle = 0.0;
    double newRobotAngle = 0.0;

    double kTargetAng = 0.3;
    double avgTargetAngle = 0.0;
    double oldTargetAngle = 0.0;
    double newTargetAngle = 0.0;

    double Ldeadband = .15;
    double Rdeadband = .15;
    
    //double kTurn;    // LED Logic


    private boolean badIdea = false;
    
    public Drivetrain() {

        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        redOutput = new DigitalOutput(0);
        addChild("RedOutput",redOutput);
        
        
        greenOutput = new DigitalOutput(1);
        addChild("GreenOutput",greenOutput);
        
        
        blueOutput = new DigitalOutput(2);
        addChild("BlueOutput",blueOutput);
        
        

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        leftMotor1 = new CANSparkMax(2, MotorType.kBrushless);
        leftMotor1.setInverted(true);
        leftMotor2 = new CANSparkMax(3, MotorType.kBrushless);
        leftMotor2.follow(leftMotor1);
        leftEncoder = leftMotor1.getEncoder();
        //TODO Confirm
        leftEncoder.setPositionConversionFactor(0.25);
        leftPID = leftMotor1.getPIDController();
        leftPID.setOutputRange(minOutput, maxOutput);
        leftPID.setSmartMotionMaxVelocity(maxVel, smartMotionSlot);
        leftPID.setSmartMotionMinOutputVelocity(minVel, smartMotionSlot);
        leftPID.setSmartMotionMaxAccel(maxAcc, smartMotionSlot);
        
        
        
        rightMotor1 = new CANSparkMax(4, MotorType.kBrushless);
        rightMotor2 = new CANSparkMax(5, MotorType.kBrushless);
        rightMotor2.follow(rightMotor1);
        rightEncoder = rightMotor1.getEncoder();
        rightEncoder.setPositionConversionFactor(0.25);
        rightPID = rightMotor1.getPIDController();
        rightPID.setOutputRange(minOutput, maxOutput); 
        rightPID.setSmartMotionMaxVelocity(maxVel, smartMotionSlot);
        rightPID.setSmartMotionMinOutputVelocity(minVel, smartMotionSlot);
        rightPID.setSmartMotionMaxAccel(maxAcc, smartMotionSlot);
        

        leftPID.setP(kP);
        leftPID.setI(kI);
        leftPID.setD(kD);
        leftPID.setFF(kF);
        

        rightPID.setP(kP);
        rightPID.setI(kI);
        rightPID.setD(kD);
        rightPID.setFF(kF);

        //imu = new ADIS16448_IMU();
        

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
            speed = 0.125;
        }
        else{
            speed = .5;
        }

        /*if(Robot.oi.getOpLeftJoy().getRawButton(7)){
            badIdea = true;
        }
        else{
            badIdea = false;
        }*/



        /*SmartDashboard.putNumber("Left Encoder RPM", leftEncoder.getVelocity());
        SmartDashboard.putNumber("Right Encoder RPM", rightEncoder.getVelocity());*/
        
        double[] tempArray = {0,0,0,0,0,0};
        double[] targetInfo = SmartDashboard.getNumberArray("vision/target_info",tempArray);

        /*SmartDashboard.putNumber("Inches to Target", targetInfo[3]);
        SmartDashboard.putNumber("angle displacement of robot to target",targetInfo[4]);
        SmartDashboard.putNumber("angle displacement of target to robot", targetInfo[5]);*/


        //newYawData = -imu.getRateY();
        //avgYaw = smoothingFilter(oldYawData, newYawData, kYaw);
        //oldYawData = newYawData;
        //double[] tempYArray = {avgYaw,newYawData};


        if(targetInfo[1] != 0.0){
            
            newRobotAngle = targetInfo[4];
            avgRobotAngle = smoothingFilter(oldRobotAngle, newRobotAngle, kRobotAng);
            double[] tempRArray = {avgRobotAngle,newRobotAngle};
            oldRobotAngle = newRobotAngle;
            SmartDashboard.putNumberArray("Angle of Robot Array", tempRArray);
    
            
            newTargetAngle = targetInfo[5];
            avgTargetAngle = smoothingFilter(oldTargetAngle, newTargetAngle, kTargetAng);
            double[] tempTArray = {avgTargetAngle,newTargetAngle};
            oldTargetAngle = newTargetAngle;
            SmartDashboard.putNumberArray("Angle of Target Array", tempTArray);
        }
        
 
        //kTurn = 0.01 * 0.5 * (leftEncoder.getVelocity() + rightEncoder.getVelocity());

    }

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CMDPIDGETTERS


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CMDPIDGETTERS

    // Put methods for controlling this subsystem
    // here. Call these from Commands.



    public void velocityDrive(Joystick left, Joystick right){
        double leftSetpoint = 0;
        double rightSetpoint = 0;
        double leftPos = left.getY();
        double rightPos = right.getY();
        rightSetpoint = 0.;
        leftSetpoint  = 0.;
        //rightSetpoint = rightPos * maxRpm * speed * 0.5;
        //leftSetpoint = leftPos * maxRpm * speed * 0.5;
        if(Math.abs(leftPos) >= Ldeadband){
            leftSetpoint = ((leftPos - Ldeadband) * maxRpm * speed);
            //leftSetpoint -= kTurn;
        }
        if(Math.abs(rightPos) >= Rdeadband){
            rightSetpoint = ((rightPos - Rdeadband) * maxRpm * speed);
            //rightSetpoint += kTurn;
        }
        
        /*if(badIdea){
            leftSetpoint = -maxRpm;
            rightSetpoint = -maxRpm;
        }*/


        leftPID.setReference(leftSetpoint, ControlType.kVelocity);
        rightPID.setReference(rightSetpoint, ControlType.kVelocity);
    }
    public void smartDrive(Joystick left, Joystick right){
        double leftSetpoint = 0;
        double rightSetpoint = 0;
        double leftPos = left.getY();
        double rightPos = right.getY();
        if(Math.abs(leftPos) >= Ldeadband){
            leftSetpoint = (leftPos - Ldeadband) * maxRpm * speed * 0.5;
            if(speed >= 1.0){
                leftPID.setReference(leftSetpoint, ControlType.kSmartVelocity);
            }
            else{
                leftPID.setReference(leftSetpoint, ControlType.kVelocity);
            }
        }
        if(Math.abs(rightPos) >= Rdeadband){
            rightSetpoint = (rightPos - Rdeadband) * maxRpm * speed  * 0.5;
            if(speed >= 1.0){
                rightPID.setReference(rightSetpoint, ControlType.kSmartVelocity);
            }
            else{
                rightPID.setReference(rightSetpoint, ControlType.kVelocity);
            }
        }
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
        leftMotor1.set(left.getY() * speed);
        rightMotor1.set(right.getY() * speed);
    }
    public void percentDrive(double speed){
        leftMotor1.set(0.25);
        rightMotor1.set(0.25);
    }

    public double visionForm(double angleOfRobot,double angleOfTarget){
        return (visionCons * (angleOfRobot+angleOfTarget));
    }

    


    public double smoothingFilter(double oldData, double newData, double constant){
        return (((1-constant)* newData)+(constant*oldData));
    }



}
