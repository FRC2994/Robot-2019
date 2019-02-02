
public void stopMotor() {
  //    	setPosition(getDesiredPosition() + Constants.ELEVATOR_POSITION_INCREMENT);
  //    	setMotorOpenLoop(0);
    }
  
    public void setPIDUp() {
      /* set closed loop gains in slot0, typically kF stays zero. */
      motor.config_kF(0, 0.0, 0);
      motor.config_kP(0, 0.2, 0);
      motor.config_kI(0, 0.0, 0);
      motor.config_kD(0, 0.0, 0);	
      motor.configPeakOutputForward(1.0, 0);
    }
    
  
    public void setPIDDown() {
      /* set closed loop gains in slot0, typically kF stays zero. */
      motor.config_kF(0, 0.0, 0);
      motor.config_kP(0, 0.1, 0);
      motor.config_kI(0, 0.0, 0);
      motor.config_kD(0, 0.0, 0);
      motor.configPeakOutputReverse(-0.5, 0);
    }
    
    public void moveUp() {
  //		if ( getDesiredPosition() + Constants.ELEVATOR_POSITION_INCREMENT <  Constants.ELEVATOR_POSITION_MAXIMUM)
  //		if ( ! limitElevatorTop.get()) {
  //			int limittedVal = getDesiredPosition() + Constants.ELEVATOR_POSITION_INCREMENT;
             System.out.println("Elevator Moving Up to " + getDesiredPosition() + Constants.ELEVATOR_POSITION_INCREMENT );
          System.out.println("ELEVATOR : getDesiredPosition() " + getDesiredPosition() + " getRealPosition() " + getRealPosition() + " Encoder Position " + motor.getSelectedSensorPosition(0));
        System.out.println("Voltage: " + motor.getMotorOutputVoltage() + " CL error: " + motor.getClosedLoopError(0) + " P: " + motor.configGetParameter(ParamEnum.eProfileParamSlot_P, 0, 10));
          setPIDUp();
        setPosition(getRealPosition() + Constants.ELEVATOR_POSITION_INCREMENT);
        System.out.println("Moving UP getDesiredPosition() " + getDesiredPosition() + " getRealPosition() " + getRealPosition() + " Encoder Position " + motor.getSelectedSensorPosition(0));
  //    		setMotorOpenLoop(-0.8);
  //		}
    }
    
    public void zero() {
      if (!limitElevatorBottom.get()) {
              if (!printedZeroing) {
            System.out.println("Elevator Zeroing!! Old startPosition " + startPosition + " New startPosition " +  getRealPosition());
            printedZeroing = true;
              }
          startPosition = getRealPosition();
        setPosition(0);
      }
      else {
        printedZeroing = false;
        setPosition(getRealPosition());
      }
    }
  
    public void moveDown() {
        if ( limitElevatorBottom.get()) {
        System.out.println("Elevator Moving Down to " + getDesiredPosition() + Constants.ELEVATOR_POSITION_DECREMENT );
        System.out.println("ELEVATOR : getDesiredPosition() " + getDesiredPosition() + " getRealPosition() " + getRealPosition() + " Encoder Position " + motor.getSelectedSensorPosition(0));
        System.out.println("Voltage: " + motor.getMotorOutputVoltage() + " CL error: " + motor.getClosedLoopError(0) + " P: " + motor.configGetParameter(ParamEnum.eProfileParamSlot_P, 0, 10));
        System.out.println("getErrorDerivative: " + motor.getErrorDerivative(0) );
        setPIDDown();
        setPosition(getRealPosition() + Constants.ELEVATOR_POSITION_DECREMENT);
        } else {
        System.out.println("Elevator Zeroing!!");
        startPosition = getRealPosition();
        setPosition(0);
        }
      }
  
  public ArmGamepiece() {
    // initialize motor
    m_motor = new CANSparkMax(deviceID, MotorType.kBrushless);

    /**
     * In order to use PID functionality for a controller, a CANPIDController object
     * is constructed by calling the getPIDController() method on an existing
     * CANSparkMax object
     */
    m_pidController = m_motor.getPIDController();

    // Encoder object created to display position values
    m_encoder = m_motor.getEncoder();

    // PID coefficients
    kP = 0.1; 
    kI = 1e-4;
    kD = 1; 
    kIz = 0; 
    kFF = 0; 
    kMaxOutput = 1; 
    kMinOutput = -1;

    // set PID coefficients
    m_pidController.setP(kP);
    m_pidController.setI(kI);
    m_pidController.setD(kD);
    m_pidController.setIZone(kIz);
    m_pidController.setFF(kFF);
    m_pidController.setOutputRange(kMinOutput, kMaxOutput);

    // display PID coefficients on SmartDashboard
    SmartDashboard.putNumber("P Gain", kP);
    SmartDashboard.putNumber("I Gain", kI);
    SmartDashboard.putNumber("D Gain", kD);
    SmartDashboard.putNumber("I Zone", kIz);
    SmartDashboard.putNumber("Feed Forward", kFF);
    SmartDashboard.putNumber("Max Output", kMaxOutput);
    SmartDashboard.putNumber("Min Output", kMinOutput);
    SmartDashboard.putNumber("Set Rotations", 0);
  }

  @Override
  public void teleopPeriodic() {
    // read PID coefficients from SmartDashboard
    double p = SmartDashboard.getNumber("P Gain", 0);
    double i = SmartDashboard.getNumber("I Gain", 0);
    double d = SmartDashboard.getNumber("D Gain", 0);
    double iz = SmartDashboard.getNumber("I Zone", 0);
    double ff = SmartDashboard.getNumber("Feed Forward", 0);
    double max = SmartDashboard.getNumber("Max Output", 0);
    double min = SmartDashboard.getNumber("Min Output", 0);
    double rotations = SmartDashboard.getNumber("Set Rotations", 0);

    // if PID coefficients on SmartDashboard have changed, write new values to controller
    if((p != kP)) { m_pidController.setP(p); kP = p; }
    if((i != kI)) { m_pidController.setI(i); kI = i; }
    if((d != kD)) { m_pidController.setD(d); kD = d; }
    if((iz != kIz)) { m_pidController.setIZone(iz); kIz = iz; }
    if((ff != kFF)) { m_pidController.setFF(ff); kFF = ff; }
    if((max != kMaxOutput) || (min != kMinOutput)) { 
      m_pidController.setOutputRange(min, max); 
      kMinOutput = min; kMaxOutput = max; 
    }
  }
}