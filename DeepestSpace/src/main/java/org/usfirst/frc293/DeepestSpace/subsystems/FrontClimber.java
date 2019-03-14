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

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS


/**
 *
 */
public class FrontClimber extends Subsystem {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private WPI_TalonSRX frontClimberTalon;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    // Front Climber PID Parameters
    // must be tuned
    private double kF = 0;
    private double kP = 0;
    private double kI = 0;
    private double kD = 0;

    // Front Climber Setpoint (inches)
    public double hab3set = 19;
    public double hab2set = 6;
    public double retract_set = 0;
        

    final int ENCODER_COUNT = 4096;

    // math for converting raw encoder values to inches
    public void climberSetpoint(double setpoints){
        frontClimberTalon.set(ControlMode.Position, setpoints * (ENCODER_COUNT/(2*3.14))); //desired inches * (encoder count/(2pi*r))
    }

    public void hab3(){
        climberSetpoint(hab3set);
    }
    public void hab2(){
        climberSetpoint(hab2set);
    }
    public void retractFrontClimber(){
        climberSetpoint(retract_set);
    }

    //front climber velocity setpoints (rpm)
    public double HAB_VELOCITY = 30;
    public double HOLD_VELOCITY = 0;

    public void habVelocity(){
        climberVelocitySetpoint(HAB_VELOCITY);
    }

    public void holdVelocity(){
        climberVelocitySetpoint(HOLD_VELOCITY);
    }

    //see talon srx reference manual section 12.4.2: converts rpm to rps then multiplies velocity sample period and encoder count
    public void climberVelocitySetpoint(double velocity_setpoint){
        frontClimberTalon.set(ControlMode.Velocity, (velocity_setpoint * (1/60) * (1/10) * 4096)); 
    }

    public FrontClimber() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        frontClimberTalon = new WPI_TalonSRX(15);
        
        
        

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
    
    // front climber talon config and PID config
    frontClimberTalon.setSensorPhase(true);
    frontClimberTalon.clearStickyFaults();
    frontClimberTalon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative,0,4000);
    frontClimberTalon.config_kP(0, kP);
    frontClimberTalon.config_kI(0, kI);
    frontClimberTalon.config_kD(0, kD);
    frontClimberTalon.config_kF(0, kF);
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

     // front climber methods
     public void frontClimberDeploy(){
        frontClimberTalon.set(.66);
    }
    public void frontClimberRetract(){
        frontClimberTalon.set(-.66);
    }
    public void frontClimberStop(){
        frontClimberTalon.set(0);
    }

    public double getFrontClimberEncValue(){
        double frontEncValue = frontClimberTalon.getSensorCollection().getQuadraturePosition();
        return (frontEncValue) * (ENCODER_COUNT/(2*3.14));
    }


}
