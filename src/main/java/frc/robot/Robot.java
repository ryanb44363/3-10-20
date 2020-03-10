package frc.robot;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.actions.DriveAction;
import frc.robot.actions.DriveActionTurn;
import frc.robot.subsystems.DriveBase;


public class Robot extends TimedRobot {
    
    public static XboxController controller = new XboxController(0);
    public static XboxController controller1 = new XboxController(1);
    public static final GenericHID.Hand left = GenericHID.Hand.kLeft;
    public static final GenericHID.Hand right = GenericHID.Hand.kRight;
    public CANSparkMax leftfrontmotor;
    public CANSparkMax leftrearmotor;
    public CANSparkMax rightfrontmotor;
    public CANSparkMax rightrearmotor;

    public DriveAction autoBaby = new DriveAction(2);
    public DriveActionTurn autoTwo = new DriveActionTurn(3);

    public static DriveBase base = new DriveBase(1, 2, 3, 4);

    //public static DistanceSensor dist = new DistanceSensor(Rev2mDistanceSensor.Port.kOnboard);

    //public static ColorSensor color = new ColorSensor(I2C.Port.kMXP);

    @Override
    public void robotInit() {
        base.initialize();
        //dist.initialize();
    }

    @Override
    public void teleopInit() {
        base.initialize();
        base.reset();
    }

    @Override
    public void teleopPeriodic() {
        reseter();

        //setAngle(); does this change anything for our build? 
        
        drive();
        
        armStrong();
      
        climber();

        dashboard();

    //SmartDashboard.putNumber("Encoder Position", leftfrontmotor.getEncoder().getVelocity());

    }

    public void armStrong() {
        //System.out.print("armStrong Called");
        //if (controller.getAButtonPressed()){
            base.arm(controller1, left, right);
        //}
    }
    public void climber() {
        base.arm(controller1, left, right);
    }
    public void drive() {
        //if (controller.getTriggerAxis(left) > 0.5) {
            base.arcadeDrive(controller, left, right);
        //}
        
        //if (controller.getTriggerAxis(right) > 0.5) {
 //           base.angleLockedDrive(controller, left);
        /*
        } else if (controller.getTriggerAxis(left) > 0.5) {
            base.ballFollowDrive();
        */
        //} else if (controller.getBumper(left)) {
        //    base.distanceDrive();
        //} else {
        //    base.arcadeDrive(controller, left, right);
        //}

    }

    public void dashboard() {
        base.dashboard();
        //dist.dashboard();
        //color.dashboard();
    }


    public void reseter() {
        if (controller.getAButtonReleased()) {
            //base.reset(); //Could cause locking, we don't have gyro, need to look at reset
            //System.out.print("Base Reset.");
        }
    }
    @Override 
    public void autonomousInit() {
        
        base.initialize();
        base.reset();

      
    }
    @Override
    public void autonomousPeriodic() {
        Timer Timer = new Timer();
        Timer.start();


        System.out.println("Timer Started!");
        autoTwo.start();
        Timer.delay(2);
        autoTwo.isComplete();

        autoBaby.start();
        Timer.delay(2);
        autoBaby.isComplete();

        /*
        autoTwo.start();

        while (Timer.get() > 5 || Timer.get() > 10) {
            autoTwo.isComplete();
        }

        while (Timer.get() > 10 || Timer.get() < 15) {
            autoBaby.start();
        }
        
        while (Timer.get() > 15) {
            autoBaby.isComplete();
        }

        autoBaby.start();
        
        /* timer = new Timer();
        leftfrontmotor.set(.3);
        leftrearmotor.set(.3);
        rightfrontmotor.set(.3);
        rightrearmotor.set(.3); */
        
        
    }
}