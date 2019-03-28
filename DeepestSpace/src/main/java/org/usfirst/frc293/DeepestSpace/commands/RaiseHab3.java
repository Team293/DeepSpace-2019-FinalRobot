// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc293.DeepestSpace.commands;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc293.DeepestSpace.Robot;

/**
 *
 */
public class RaiseHab3 extends Command {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
 
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
    public RaiseHab3() {

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.climber);

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    }

    double targetPos = 20.0;
    int targetPosRaw;
    double toler = 0.25;
    int tolerRaw;
    int backClimbEnc;
    int frontClimbEnc;

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        targetPosRaw = Robot.climber.inchesToRaw(targetPos);
        tolerRaw = Robot.climber.inchesToRaw(toler);
        Robot.climber.climbTarget = 20.0;

    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        backClimbEnc = Robot.climber.getBackClimbEnc();
        frontClimbEnc = Robot.climber.getFrontClimbEnc();
    
        /*
        if ((targetPosRaw + tolerRaw) < backClimbEnc){
            Robot.climber.pidBackDown();
        }
        else if ((targetPosRaw - tolerRaw) > backClimbEnc){
            Robot.climber.pidBackUp();
        }
        
        if((targetPosRaw + tolerRaw) < frontClimbEnc){
            Robot.climber.pidFrontDown();
        }
        else if ((targetPosRaw - tolerRaw) > frontClimbEnc){
            Robot.climber.pidFrontUp();
        }  
        */
      // All the above up and down is now just allowing for adding to the
      // encoder command until the back encoder is equal or greater than the
      // commanded position.
      if (targetPosRaw > backClimbEnc) {
        Robot.climber.pidBackUp();
      }
      if (targetPosRaw > frontClimbEnc){
        Robot.climber.pidFrontUp();
      }
        
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
    }
}
