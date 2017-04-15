package com.project.salem.web.controller;
//package com.project.salem.web.controller;
//
//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//import java.io.OutputStream;
//import java.util.Enumeration;
//
//import org.springframework.stereotype.Controller;
//
//import gnu.io.CommPortIdentifier;
//import gnu.io.SerialPort;
//import gnu.io.SerialPortEvent;
//import gnu.io.SerialPortEventListener;
//
//@Controller
//public class SerialTest implements SerialPortEventListener {
//	
//	private static String sensorValue = "sensor";
//	SerialPort serialPort;
//        /** The port we're normally going to use. */
//	private static final String PORT_NAMES[] = { 
//			"/dev/tty.usbserial-A9007UX1", // Mac OS X
//            "/dev/ttyACM0", // Raspberry Pi
//			"/dev/ttyUSB0", // Linux
//			"COM5", // Windows
//	};
//
//	private BufferedReader input;
//	private OutputStream output;
//	private static final int TIME_OUT = 2000;
//	private static final int DATA_RATE = 9600;
//
//	public void initialize() {
//                // the next line is for Raspberry Pi and 
//                // gets us into the while loop and was suggested here was suggested http://www.raspberrypi.org/phpBB3/viewtopic.php?f=81&t=32186
////                System.setProperty("gnu.io.rxtx.SerialPorts", "/dev/ttyACM0");
//
//		CommPortIdentifier portId = null;
//		Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();
//
//		//First, Find an instance of serial port as set in PORT_NAMES.
//		while (portEnum.hasMoreElements()) {
//			CommPortIdentifier currPortId = (CommPortIdentifier) portEnum.nextElement();
//			for (String portName : PORT_NAMES) {
//				if (currPortId.getName().equals(portName)) {
//					portId = currPortId;
//					break;
//				}
//			}
//		}
//		if (portId == null) {
//			System.out.println("Could not find COM port.");
//			return;
//		}
//
//		try {
//			// open serial port, and use class name for the appName.
//			serialPort = (SerialPort) portId.open(this.getClass().getName(),
//					TIME_OUT);
//
//			// set port parameters
//			serialPort.setSerialPortParams(DATA_RATE,
//					SerialPort.DATABITS_8,
//					SerialPort.STOPBITS_1,
//					SerialPort.PARITY_NONE);
//
//			// open the streams
//			input = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));
//			output = serialPort.getOutputStream();
//
//			// add event listeners
//			serialPort.addEventListener(this);
//			serialPort.notifyOnDataAvailable(true);
//		} catch (Exception e) {
//			System.err.println(e.toString());
//		}
//	}
//
//	/**
//	 * This should be called when you stop using the port.
//	 * This will prevent port locking on platforms like Linux.
//	 */
//	public synchronized void close() {
//		if (serialPort != null) {
//			serialPort.removeEventListener();
//			serialPort.close();
//		}
//	}
//
//	/**
//	 * Handle an event on the serial port. Read the data and print it.
//	 */
//	public synchronized void serialEvent(SerialPortEvent oEvent) {
//		if (oEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
//			try {
//				sensorValue = input.readLine();				
//				System.out.println(sensorValue);
//			} catch (Exception e) {
//				System.err.println(e.toString());
//			}
//		}
//		// Ignore all the other eventTypes, but you should consider the other ones.
//	}
//
//	public void main() throws Exception {
//		SerialTest main = new SerialTest();
//		main.initialize();
//		Thread t=new Thread() {
//			public void run() {
//				//the following line will keep this app alive for 1000 seconds,
//				//waiting for events to occur and responding to them (printing incoming messages to console).
//				try {Thread.sleep(1000);} catch (InterruptedException ie) {}
//			}
//		};
//		t.start();
//	}
//
//	public String getSensorValue() {
//		return sensorValue;
//	}
//
//}