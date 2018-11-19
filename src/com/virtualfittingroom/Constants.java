package com.virtualfittingroom;

public class Constants {

    public static final int START_CAPTURE_COMMAND = 101;
    public static final int START_RECONSTRUCTION_COMMAND = 102;
    public static final int START_RECONSTRUCTION_UPPER_COMMAND = 103;
    public static final int START_RECONSTRUCTION_LOWER_COMMAND = 104;
    public static final int REQUEST_STATUS_COMMAND = 105; 
    
    public static final int WAITING_FOR_ORDER = -1;   			
    public static final int UNDEFINED_STATUS = 0;				
    public static final int READY_TO_CAPTURE_STATUS = 1;		
    public static final int CAPTURING_STATUS = 2;				
    public static final int SENDING_DAR_FILES_STATUS = 5;
    public static final int READY_TO_LOAD_FILES = 6;
    public static final int RECEIVING_LOWER_FILE_STATUS = 7;
    public static final int RECONSTRUCTING_UPPER_STATUS = 10;	
    public static final int RECONSTRUCTING_LOWER_STATUS = 13;	
    public static final int MEASURING_STATUS = 15;				
    public static final int FINISHED_STATUS = 16;    			
    public static final int ERROR = 17;
    public static final int TIME_OUT = 18;

    public static final int REQUEST_STATUS_COMMAND_RESPONSE = 1;
}
