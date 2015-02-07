package org.usfirst.frc4915.MecanumDrive.subsystems;

import org.usfirst.frc4915.MecanumDrive.RobotMap;
import org.usfirst.frc4915.MecanumDrive.commands.ElevatorFineTune;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.ControlMode;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Elevator extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	// These positions describe the number of totes you stacking on top of. 
	// If you need to stack on top of 3 totes, use position 3.
	// If you need to stack on the ground, use position 0.
	
	// TODO find exact height's of positions for number of totes in inches
	public static final double POSITION_ZERO = 0; // Lowest position
	public static final double POSITION_ONE = 1;
	public static final double POSITION_TWO = 2;
	public static final double POSITION_THREE = 3;
	public static final double POSITION_FOUR = 4;
	public static final double POSITION_FIVE = 5; 
	public static final double POSITION_SIX = 6; // Highest position
	
	public static final double MOTOR_SPEED = .5; // TODO find correct speed
	public static final double CONSTANT_SPEED = .1; // TODO find correct value for constant speed
	
	public CANTalon winch = RobotMap.elevatorWinchMotor14;
	
	// TODO Add Javadoc comments to each method
	
    public void initDefaultCommand() {
    	setDefaultCommand(new ElevatorFineTune());
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    // TODO Make sure that the winch does not begin winding the wrong way -- We may use a limit switch to tell if the cable is tight or not.
    // Discuss this with Elevator Subteam and Riyadth
    public void moveAtSpeed(Joystick joystick) {
    	/*
    	 * Moves elevator at a constant speed
    	 * speed is currently set, user cannot change speed to be moved at
    	 */
        double joystickY = joystick.getAxis(Joystick.AxisType.kY);
        System.out.println("Elevator joystick " + joystickY);
        if (Math.abs(joystickY) <= .2) {
            System.out.println("Stopping Motor");	
        	holdPosition();
        }
        else {
        	winch.changeControlMode(ControlMode.Speed);
        	System.out.println("Moving Elevator at constant speed");
        	if (joystickY > 0) {
        		winch.set(CONSTANT_SPEED);
        	}
        	else {
        		winch.set(CONSTANT_SPEED * -1);
        	}
        }
    }
    
    public void stopElevator() {
    	// stops any current commands telling the elevator to move.
    	
    	winch.disableControl();
    	System.out.println("Elevator has stopped.");
    }
    
    public void holdPosition() {
    	/*
    	 * Keeps the elevator in a constant position
    	 */
    	winch.changeControlMode(ControlMode.Position);
    	winch.set(winch.getPosition());
    }
 	
    // TODO potentiometer will be connected to the SRX
    public double getPosition() {
    	// Returns the position of the elevator
    	System.out.printf("The elevator is at this position %f", winch.getPosition()); // TODO use custom debugger
    	// TODO figure out scaling
    	return winch.getPosition();
    }
    
    public void setPosition(double position) {
    	// TODO figure out scaling
    	winch.set(position);
    }
}