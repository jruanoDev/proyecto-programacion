package com.proyectogestioncitas.model;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.net.ntp.NTPUDPClient;
import org.apache.commons.net.ntp.TimeInfo;

public class TimeController {

	private static LocalDate currentDate = null;
	
	public static LocalDate getCurrentTime() {
		String TIME_SERVER = "time-b.nist.gov";
		NTPUDPClient timeClient = new NTPUDPClient();
		InetAddress inetAddress;
		
		try {
			inetAddress = InetAddress.getByName(TIME_SERVER);
			TimeInfo timeInfo = timeClient.getTime(inetAddress);
			long returnTime = timeInfo.getReturnTime();
			Date time = new Date(returnTime);
			
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(time);
			int year = calendar.get(Calendar.YEAR);
			int month = calendar.get(Calendar.MONTH) + 1;
			int day = calendar.get(Calendar.DAY_OF_MONTH);
			
			currentDate = LocalDate.of(year, month, day);
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return currentDate;
	}
	
}
