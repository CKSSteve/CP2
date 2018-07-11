package com.uxb2b.ecp.service;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class DateConvertService {
	
	private Logger log = LoggerFactory.getLogger(DateConvertService.class);
	
	/**
	 * String  to Timestamp
	 * @param value
	 * @param format
	 * @return
	 */
	public Timestamp StringToTimestamp(String value, String format) {
		
		DateFormat dateFormat = new SimpleDateFormat(format);
		SimpleDateFormat timeStampFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		Timestamp timeStamp = null;
		try {
			date = dateFormat.parse(value);			
			timeStamp = Timestamp.valueOf(timeStampFormat.format(date));
			
		} catch (ParseException e) {
			log.error(e.toString());
		}
		return timeStamp;
	}
	
	/**
	 * String  Convert to Date
	 * @param value
	 * @param format
	 * @return
	 */
	public Date StringToDate(String value, String format) {
		
		DateFormat dateFormat = new SimpleDateFormat(format);
		Date date = null;
		try {			
			date = dateFormat.parse(value);
		} catch (ParseException e) {
			log.error(e.toString());
		}
		
		return date;
	}
	
	/**
	 * Date to Timestamp
	 * @param date
	 * @return
	 */
	public Timestamp DateToTimestamp(Date date) {
		
		SimpleDateFormat dateformat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		Timestamp timeStamp = Timestamp.valueOf(dateformat.format(date));
		
		return timeStamp;
	}
	
	/**
	 * Timestamp to String
	 * @param value
	 * @param format
	 * @return
	 */
	public String TimestampToString(Timestamp value, String format) {
		
		SimpleDateFormat df = new SimpleDateFormat(format);
		return df.format(value);
		
	}
	
	/**
     * 民國年
     * @param date
     * @return
     */
    public String getDateOfROCYear(Date date) {
        if (date == null) {
            return "";
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);     
        DecimalFormat df = (DecimalFormat) DecimalFormat.getInstance();
		df.applyPattern("000");
		return df.format(cal.get(Calendar.YEAR) - 1911);
    }
    
    /**
     * 民國年Format 104/01/01
     * @param date
     * @return
     */
    public String getDateOfROCYearWithSlashFormat(Date date) {
    	if (date == null) {
            return StringUtils.repeat(" ", 7);
        }
        
        SimpleDateFormat df = new SimpleDateFormat("MM/dd");        
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);     
        String year =  getDateOfROCYear(date);
        return year + "/" + df.format(cal.getTime());
    }
    
    /**
     * 西元年Format 1911-01-01
     * @param date
     * @return
     */
    public String getDateOfYuanYearWithHyphenFormat(Date date) {
    	if (date == null) {
            return StringUtils.repeat(" ", 7);
        }
        
        SimpleDateFormat df = new SimpleDateFormat("YYYY-MM-dd");        
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);     
        return df.format(cal.getTime());
    }
    
    /**
     * 時間Format HH:mm:ss
     * @param date
     * @return
     */
    public String getDateOfTimeFormat(Date date) {
    	if (date == null) {
            return StringUtils.repeat(" ", 7);
        }
        
        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");        
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);     
        return df.format(cal.getTime());
    }
    
    /**
     * 取得民國日期
     * @return
     */
    public String getDateOfROC(Date date) {
        
        if (date == null) {
            return StringUtils.repeat(" ", 7);
        }
        
        SimpleDateFormat df = new SimpleDateFormat("MMdd");        
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);     
        String year =  getDateOfROCYear(date);
        return year + df.format(cal.getTime());
    }
    
    
    public String format(Date date, String pattern) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat df = (SimpleDateFormat) SimpleDateFormat.getInstance();
        df.applyPattern(pattern);
        return df.format(date);
    }


    public static void main(String[] args) {
    	DateConvertService d = new DateConvertService();
    	System.out.println(d.getDateOfROC(new Date()));
    }
	

}
