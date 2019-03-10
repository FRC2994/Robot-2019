// package frc.subsystems;


// // import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
// import edu.wpi.first.wpilibj.command.Subsystem;
// import frc.utils.Constants;
// import edu.wpi.first.wpilibj.DigitalInput;
// import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

// import com.revrobotics.CANEncoder;
// import com.revrobotics.CANPIDController;
// import com.revrobotics.ControlType;

// import com.revrobotics.CANError;
// import com.revrobotics.CANSparkMax;
// import com.revrobotics.CANSparkMax.IdleMode;
// import com.revrobotics.CANSparkMaxLowLevel.MotorType;

// //public class Arm extends Subsystem {
// public class ArmSparkMax {
//     private static CANSparkMax motor;
//     public static enum ArmMoveDirection { HI, LO };

//     private CANPIDController m_pidController;
//     private CANEncoder m_encoder;
//     public double kP, kI, kD, kIz, kFF, kMaxOutput, kMinOutput;
//     private int startPosition = 0;
//     private int desiredPosition = 0;
//     private static final int arm_position_increment = 100;
//     public DigitalInput LimitArmTop = new DigitalInput(Constants.DIO_ARM_LIMIT_BOTTOM);
//     private boolean printedZeroing;
//     private final double kOutputRange = 1000;
  
//     public ArmSparkMax() {
  
//       // initialize motor
//       // motor = new CANSparkMax(Constants.CAN_ARM, MotorType.kBrushless);
//       /**
//        * In order to use PID functionality for a controller, a CANPIDController object
//        * is constructed by calling the getPIDController() method on an existing
//        * CANSparkMax object
//        */
//       m_pidController = motor.getPIDController();
     
//       // Encoder object created to display and set position values
//       m_encoder = motor.getEncoder();
//       // m_encoder.setPosition(); // TODO: Uncomment when release 1.1 is installed.

//       // setPIDCoefficients(0);
//       motor.restoreFactoryDefaults();
//     }
    
//     direction = 0 for down, 1 for up
//     private void setPIDCoefficients(int direction) {
//       // PID coefficients
//       kP = 0.1; 
//       kI = 1e-4;
//       kD = 1; 
//       kIz = 0; 
//       kFF = 0; 
//       kMaxOutput = -kOutputRange; 
//       kMinOutput = -kOutputRange;
    
//       // set PID coefficients
//       m_pidController.setP(kP);
//       m_pidController.setI(kI);
//       m_pidController.setD(kD);
//       m_pidController.setIZone(kIz);
//       m_pidController.setFF(kFF);
//       m_pidController.setOutputRange(kMinOutput, kMaxOutput);
    
//       //display PID coefficients on SmartDashboard
//       SmartDashboard.putNumber("P Gain", kP);
//       SmartDashboard.putNumber("I Gain", kI);
//       SmartDashboard.putNumber("D Gain", kD);
//       SmartDashboard.putNumber("I Zone", kIz);
//       SmartDashboard.putNumber("Feed Forward", kFF);
//       SmartDashboard.putNumber("Max Output", kMaxOutput);
//       SmartDashboard.putNumber("Min Output", kMinOutput);
//     }

//     public void setMotorOpenLoop(double percent) {
//       motor.set(percent);
//     }
  
//     public void setPosition(int positionInTicks) {
      
//       if (positionInTicks < 50 && positionInTicks < this.positionInTicks) {
//       			positionInTicks = 50;
//       		}
//       		System.out.println("Trying to move Arm in Closed Loop... currentPosition " + getDesiredPosition() 
//       			+ " encPos "  + getDesiredPosition() + " positionInTicks " + positionInTicks + ".");
//       		System.out.println("New desired: " + (positionInTicks + startPosition));
//       desiredPosition = positionInTicks + startPosition;
//       motor.set(desiredPosition);
//       m_pidController.setReference(desiredPosition, ControlType.kPosition);
//       m_pidController.setOutputRange(desiredPosition - kOutputRange, desiredPosition + kOutputRange);
//     }

//     public int getDesiredPosition() {
//       return desiredPosition;
//     }
    
//     public int getRealPosition() {
//       return (int)m_encoder.getPosition() - startPosition;
//     }

//     public void stopMotor() {
//       motor.set(0);
//     }
    
//     public void zero() {
//       if (!LimitArmTop.get()) {
//         if (!printedZeroing) {
//           System.out.println("Arm Zeroing!! Old startPosition " + startPosition + " New startPosition " +  getRealPosition());
//           printedZeroing = true;
//         }
//         startPosition = getRealPosition();
//         setPosition(0);
//       }
//       else {
//         printedZeroing = false;
//         setPosition(getRealPosition());
//       }
//     }
    
//     public void moveDown() {
//       if (LimitArmTop.get()) {
//         setPIDCoefficients(0);
//         setPosition(getRealPosition() - arm_position_increment);
//       } else {
//         // System.out.println("Arm Zeroing!!");
//         setPosition(0);
//         }
//       }
    
//     public void moveUp() {
//       if (LimitArmTop.get()) {
//         setPIDCoefficients(1);
//         setPosition(getRealPosition() + arm_position_increment);
//       } else {
//         // System.out.println("Arm Zeroing!!");
//         setPosition(0);
//         }
//       }
  
//       public void moveToPosition(int desiredPostion) {
//         if (LimitArmTop.get()) {
//           setPIDCoefficients(0);
//           setPosition(desiredPostion - startPosition);
//         } else {
//           // System.out.println("Arm Zeroing!!");
//           setPosition(desiredPostion);
//           }
//         }
      
//       public void adjustPID() {
//       //  read PID coefficients from SmartDashboard
//       double p = SmartDashboard.getNumber("P Gain", 0);
//       double i = SmartDashboard.getNumber("I Gain", 0);
//       double d = SmartDashboard.getNumber("D Gain", 0);
//       double iz = SmartDashboard.getNumber("I Zone", 0);
//       double ff = SmartDashboard.getNumber("Feed Forward", 0);
//       double max = SmartDashboard.getNumber("Max Output", 0);
//       double min = SmartDashboard.getNumber("Min Output", 0);

//       // if PID coefficients on SmartDashboard have changed, write new values to controller
//       if((p != kP)) { m_pidController.setP(p); kP = p; }
//       if((i != kI)) { m_pidController.setI(i); kI = i; }
//       if((d != kD)) { m_pidController.setD(d); kD = d; }
//       if((iz != kIz)) { m_pidController.setIZone(iz); kIz = iz; }
//       if((ff != kFF)) { m_pidController.setFF(ff); kFF = ff; }
//       if((max != kMaxOutput) || (min != kMinOutput)) { 
//         m_pidController.setOutputRange(min, max); 
//         kMinOutput = min; kMaxOutput = max; 
//       }
//     }

//   @Override
//   protected void initDefaultCommand() {

//   }
// }