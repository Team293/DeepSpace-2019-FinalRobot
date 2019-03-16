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

import java.awt.Robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS


/**
 *
 */
public class BackClimber extends Subsystem {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private WPI_TalonSRX backClimberTalon;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    // Back Climber PID Parameters
    // must be tuned
    private double kF = 0;
    private double kP = 0.1;
    private double kI = 0;
    private double kD = 0;
    
    // Back Climber Setpoint (inches)
    public double hab3set = 20;
    public double hab2set = 7;
    public double retract_set = 0;

    // encoder count
    final int ENCODER_COUNT = 4096; //comes from 1024 count encoder with 4x configuration

    public BackClimber() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        backClimberTalon = new WPI_TalonSRX(16);
        
        
        

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS

    // back climber talon config and PID config
        backClimberTalon.setSensorPhase(true);
        backClimberTalon.clearStickyFaults();
        backClimberTalon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative,0,4000);
        backClimberTalon.config_kP(0, kP);
        backClimberTalon.config_kI(0, kI);
        backClimberTalon.config_kD(0, kD);
        backClimberTalon.config_kF(0, kF);

        backClimberTalon.setSelectedSensorPosition(0);

    }



    // math for converting inches to raw encoder values
    // desired inches * (encoder count/(2pi*r))
    public void climberSetpoint(double setpoints){
        backClimberTalon.set(ControlMode.Position, setpoints * (ENCODER_COUNT/(2.*3.14)));
    }

    public void hab3(){
        climberSetpoint(hab3set);
    }
    public void hab2(){
        climberSetpoint(hab2set);
    }
    public void retractBackClimber(){
        climberSetpoint(retract_set);
    }


    //back climber velocity setpoints (rpm)
    public double HAB_VELOCITY = 30.;
    public double HOLD_VELOCITY = 0.;

    public void habVelocity(){
        climberVelocitySetpoint(HAB_VELOCITY);
    }

    public void holdVelocity(){
        climberVelocitySetpoint(HOLD_VELOCITY);
    }

    //see talon srx reference manual section 12.4.2: converts rpm to rps then multiplies velocity sample period and encoder count
    public void climberVelocitySetpoint(double velocity_setpoint){
        double scaledClimbVelocity = (velocity_setpoint * (1./60.) * (1./10.) * 4096.);
        backClimberTalon.set(ControlMode.Velocity, scaledClimbVelocity); 
    //    System.out.println("scaled climb velocity = " + scaledClimbVelocity);
    //    SmartDashboard.putNumber("scaled climb velocity", scaledClimbVelocity);
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
        SmartDashboard.putNumber("Back Climber Enc Value", getBackClimberEncValue());


    }

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CMDPIDGETTERS


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CMDPIDGETTERS

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

     // back climber methods
     public void backClimberDeploy(){
        backClimberTalon.set(.57);
    }
    public void backClimberRetract(){
        backClimberTalon.set(-.57);
    }
    public void backClimberStop(){
        backClimberTalon.set(0);
    }
    
    public int getBackClimberEncValue(){
        int backEncValue = backClimberTalon.getSensorCollection().getQuadraturePosition();
        return (backEncValue);
    }


}

