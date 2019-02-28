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
public class MoveElevator extends Command {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
    private String m_Location;
 
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

    // Layout: Arm is Stowed(Vertical), Arm is Up(in position to shoot), Arm is Level , Arm is Down(get cargo from floor)
    private double[] armSetpoints = {0,47,90,100};


    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
    public MoveElevator(String Location) {

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
        m_Location = Location;

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.elevator);

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.arm);
        requires(Robot.buttonLights);
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        switch (m_Location){
            case "Ground":
                Robot.elevator.ground();
                Robot.arm.setAngle(armSetpoints[4]);
                Robot.buttonLights.turnAllOff();
                Robot.buttonLights.setButtonLight(9, Robot.elevator.atPosition(Robot.elevator.groundSet));
                break;
            case "LowHatch":
                Robot.elevator.lowHatch();
                Robot.arm.setAngle(armSetpoints[3]);
                Robot.buttonLights.turnAllOff();
                Robot.buttonLights.setButtonLight(5, Robot.elevator.atPosition(Robot.elevator.lowHatchSet));
                break;
            case "LowCargo":
                Robot.elevator.lowCargo();
                Robot.arm.setAngle(armSetpoints[2]);
                Robot.buttonLights.turnAllOff();
                Robot.buttonLights.setButtonLight(6, Robot.elevator.atPosition(Robot.elevator.lowCargoSet));
                break;
            case "MidHatch":
                Robot.elevator.midHatch();
                Robot.arm.setAngle(armSetpoints[3]);
                Robot.buttonLights.turnAllOff();
                Robot.buttonLights.setButtonLight(3, Robot.elevator.atPosition(Robot.elevator.midHatchSet));
                break;
            case "MidCargo":
                Robot.elevator.midCargo();
                Robot.arm.setAngle(armSetpoints[2]);
                Robot.buttonLights.turnAllOff();
                Robot.buttonLights.setButtonLight(4, Robot.elevator.atPosition(Robot.elevator.midCargoSet));
                break;
            case "HighHatch":
                Robot.elevator.highHatch();
                Robot.arm.setAngle(armSetpoints[3]);
                Robot.buttonLights.turnAllOff();
                Robot.buttonLights.setButtonLight(1, Robot.elevator.atPosition(Robot.elevator.highHatchSet));
                break;
            case "HighCargo":
                Robot.elevator.highCargo();
                Robot.arm.setAngle(armSetpoints[2]);
                Robot.buttonLights.turnAllOff();
                Robot.buttonLights.setButtonLight(2, Robot.elevator.atPosition(Robot.elevator.highCargoSet));
                break;
            case "Up":
                Robot.elevator.lowScrewUp();
                //Robot.elevator.highScrewUp();
                break;
            case "CargoShip":
                Robot.elevator.cargoShip();
                Robot.arm.setAngle(armSetpoints[3]);
                Robot.buttonLights.turnAllOff();
                Robot.buttonLights.setButtonLight(8, Robot.elevator.atPosition(Robot.elevator.cargoShipSet));
            case "Stow":
                Robot.elevator.ground();
                // Robot.arm.setAngle(armSetpoints[0]);

            default:
                throw new IllegalArgumentException("Look in MoveElevator " + m_Location);
                


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
