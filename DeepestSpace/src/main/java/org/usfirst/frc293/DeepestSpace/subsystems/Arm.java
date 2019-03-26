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


import edu.wpi.first.wpilibj.command.PIDSubsystem;

import edu.wpi.first.wpilibj.livewindow.LiveWindow;

import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import com.ctre.phoenix.motorcontrol.NeutralMode;
// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.AnalogPotentiometer;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Arm extends PIDSubsystem {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private WPI_TalonSRX armTalon;
    private AnalogPotentiometer throwawayPot;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    double manualSpeed = 5;

    // Initialize your subsystem here
    public Arm() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=PID
        super("Arm", 0.01, 0.0, 0.001);
        setAbsoluteTolerance(3.0);
        getPIDController().setContinuous(false);
        getPIDController().setName("Arm", "PIDSubsystem Controller");
        LiveWindow.add(getPIDController());

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=PID
        setInputRange(0, 1023);

        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        armTalon = new WPI_TalonSRX(11);
        
        
        
        throwawayPot = new AnalogPotentiometer(0, 1.0, 0.0);
        addChild("ThrowawayPot",throwawayPot);
        
        

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        armTalon.setNeutralMode(NeutralMode.Brake);
        armTalon.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen);
        armTalon.configFeedbackNotContinuous(true, 4000);
        // armTalon.configForwardSoftLimitEnable(true);
        // armTalon.configForwardSoftLimitThreshold(getAngleRawUnits(112));

        


        // Use these to get going:
        // setSetpoint() -  Sets where the PID controller should move the system
        //                  to
        enable();
    }

    @Override
    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }

    @Override
    protected double returnPIDInput() {
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;

        //Severed connection to robotbuilder ThrowawayPot
        return armTalon.getSensorCollection().getAnalogInRaw(); 
    }

    @Override
    protected void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);

        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=OUTPUT
        armTalon.pidWrite(output);

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=OUTPUT
        SmartDashboard.putNumber("AnalogInRaw",armTalon.getSensorCollection().getAnalogInRaw());
        armTalon.set(output);
    }

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CMDPIDGETTERS


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CMDPIDGETTERS

    // Use this function and not the setSetpoint function
    public void setAngle(double angle){
        double rawAngle = (angle * 4.94) + 323.0;
        setSetpoint(rawAngle);
    }
    public int getAngleRawUnits(int angle){
        int rawAngle = (angle * 5) + 340;
        return rawAngle;
    }

    // Makes the Arm move manually w/ out PID
    public void armUp(){
        setSetpoint(armTalon.getSensorCollection().getQuadraturePosition() + manualSpeed);
    }
    public void armDown(){
        setSetpoint(armTalon.getSensorCollection().getQuadraturePosition() - manualSpeed);
    }

    // Sets the setpoint to the point it's at
    public void armStop(){
        setSetpoint(armTalon.getSensorCollection().getQuadraturePosition());
    }
    public void enableArm(){
        enable();
    }
}
