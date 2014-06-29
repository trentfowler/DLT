package DLT;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import org.joda.time.Days;
import org.joda.time.LocalDate;

/**
 * DataField class
 * 
 * ...
 * 
 * @author Trent
 *
 */

public class DataField implements Serializable {
	
	private static final long serialVersionUID = -153071025273346052L;
	
	private int month;
	private int day;
	private int year;
	
	private int expirationMonth;
	private int expirationDay;
	private int expirationYear;
	
	private boolean vaIsChecked;
	private boolean toadeIsChecked;
	private boolean vdiIsChecked;
	private boolean emailCapIsChecked;
	private boolean tarpIsChecked;
	private boolean posIsChecked;
	private boolean palIsChecked;
	private boolean plasticsIsChecked;
	private boolean cidarIsChecked;
	
	private String company;
	
	private String name;
	private String email;
	private String phone;
	private String altPhone;
	private String address;
	private String cityStateZip;
	
	private String altName;
	private String altEmail;
	private String altPrimaryPhone;
	private String altSecondaryPhone;
	private String altAddress;
	private String altCityStateZip;
	
	private String serviceTag;
	private String serviceRequest;
	private String orderNumber;
	
	private String warrantyType;
	
	private String model;
	private String formFactor;
	private String OS;
	
	private String symptoms;
	private String troubleshooting;
	private String conclusion;
	private String description;
	private String notes;
	
	LocalDate dateAssignedFollowUp;
	private int followUpStatus;
	private int followUpIn;
	
	public DataField() {
		this.month = 5;
		this.day = 0;
		this.year = 0;
		try {
			this.month = Calendar.getInstance().get(Calendar.MONTH);
			this.day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH) - 1;
			this.year = Calendar.getInstance().get(Calendar.YEAR) - Main.START_YEAR;
		//if you catch an exception, just set to default
		} catch (Exception e) {
			this.month = 0;
			this.day = 0;
			this.year = 0;
		}
		
		this.expirationMonth = 0;
		this.expirationDay = 0;
		this.expirationYear = 0;
		
		this.vaIsChecked = false;
		this.toadeIsChecked = false;
		this.vdiIsChecked = false;
		this.emailCapIsChecked = false;
		this.tarpIsChecked = false;
		this.posIsChecked = false;
		this.palIsChecked = false;
		this.plasticsIsChecked = false;
		this.cidarIsChecked = false;
		
		this.company = "";
		
		this.name = "";
		this.email = "";
		this.phone = "";
		this.altPhone = "";
		this.address = "";
		this.cityStateZip = "";
		
		this.altName = "";
		this.altEmail = "";
		this.altPrimaryPhone = "";
		this.altSecondaryPhone = "";
		this.altAddress = "";
		this.altCityStateZip = "";
		
		this.serviceTag = "";
		this.serviceRequest = "";
		this.orderNumber = "";
		this.warrantyType = "";
		
		this.model = "";
		this.formFactor = "";
		this.OS = "";
		
		this.symptoms = "";
		this.troubleshooting = "";
		this.conclusion = "";
		this.description = "";
		this.notes = "";
		
		this.dateAssignedFollowUp = new LocalDate();
		this.followUpStatus = Main.STATUS_IS_UNKNOWN;
		this.followUpIn = 0;
	}
	
	public DataField(DataField df) {
		this.month = df.getMonth();
		this.day = df.getDay();
		this.year = df.getYear();
		
		this.expirationMonth = df.getExpirationMonth();
		this.expirationDay = df.getExpirationDay();
		this.expirationYear = df.getExpirationYear();
		
		this.vaIsChecked = df.getVAIsChecked();
		this.toadeIsChecked = df.getTOADEIsChecked();
		this.vdiIsChecked = df.getVDIIsChecked();
		this.emailCapIsChecked = df.getEmailCapIsChecked();
		this.tarpIsChecked = df.getTARPIsChecked();
		this.posIsChecked = df.getPOSIsChecked();
		this.palIsChecked = df.getPALIsChecked();
		this.plasticsIsChecked = df.getPlasticsIsChecked();
		this.cidarIsChecked = df.getCIDARIsChecked();
		
		this.company = df.getCompany();
		
		this.name = df.getName();
		this.email = df.getEmail();
		this.phone = df.getPhone();
		this.altPhone = df.getAltPhone();
		this.address = df.getAddress();
		this.cityStateZip = df.getCityStateZip();
		
		this.altName = df.getAltName();
		this.altEmail = df.getAltEmail();
		this.altPrimaryPhone = df.getAltPrimaryPhone();
		this.altSecondaryPhone = df.getAltSecondaryPhone();
		this.altAddress = df.getAltAddress();
		this.altCityStateZip = df.getAltCityStateZip();
		
		this.serviceTag = df.getServiceTag();
		this.serviceRequest = df.getServiceRequest();
		this.orderNumber = df.getOrderNumber();
		this.warrantyType = df.getWarrantyType();
		
		this.model = df.getModel();
		this.formFactor = df.getFormFactor();
		this.OS = df.getOS();
		
		this.symptoms = df.getSymptoms();
		this.troubleshooting = df.getTroubleshooting();
		this.conclusion = df.getConclusion();
		this.description = df.getDescription();
		this.notes = df.getNotes();
		
		this.dateAssignedFollowUp = df.getDateAssignedFollowUp();
		this.followUpStatus = df.getFollowUpStatus();
		this.followUpIn = df.getFollowUpIn();
	}
	
	//getters
	int getMonth() {
		return this.month;
	}
	
	int getDay() {
		return this.day;
	}
	
	int getYear() {
		return this.year;
	}
	
	int getExpirationMonth() {
		return this.expirationMonth;
	}
	
	int getExpirationDay() {
		return this.expirationDay;
	}
	
	int getExpirationYear() {
		return this.expirationYear;
	}
	
	boolean getVAIsChecked() {
		return this.vaIsChecked;
	}
	
	boolean getTOADEIsChecked() {
		return this.toadeIsChecked;
	}
	
	boolean getVDIIsChecked() {
		return this.vdiIsChecked;
	}
	
	boolean getEmailCapIsChecked() {
		return this.emailCapIsChecked;
	}
	
	boolean getTARPIsChecked() {
		return this.tarpIsChecked;
	}
	
	boolean getPOSIsChecked() {
		return this.posIsChecked;
	}
	
	boolean getPALIsChecked() {
		return this.palIsChecked;
	}
	
	boolean getPlasticsIsChecked() {
		return this.plasticsIsChecked;
	}
	
	boolean getCIDARIsChecked() {
		return this.cidarIsChecked;
	}
	
	String getCompany() {
		return this.company;
	}
	
	String getName() {
		return this.name;
	}
	
	String getEmail() {
		return this.email;
	}
	
	String getPhone() {
		return this.phone;
	}
	
	String getAltPhone() {
		return this.altPhone;
	}
	
	String getAddress() {
		return this.address;
	}
	
	String getCityStateZip() {
		return this.cityStateZip;
	}
	
	String getAltName() {
		return this.altName;
	}
	
	String getAltEmail() {
		return this.altEmail;
	}
	
	String getAltPrimaryPhone() {
		return this.altPrimaryPhone;
	}
	
	String getAltSecondaryPhone() {
		return this.altSecondaryPhone;
	}
	
	String getAltAddress() {
		return this.altAddress;
	}
	
	String getAltCityStateZip() {
		return this.altCityStateZip;
	}
	
	String getServiceTag() {
		return this.serviceTag;
	}
	
	String getServiceRequest() {
		return this.serviceRequest;
	}
	
	String getOrderNumber() {
		return this.orderNumber;
	}
	
	String getWarrantyType() {
		return this.warrantyType;
	}
	
	String getModel() {
		return this.model;
	}
	
	String getFormFactor() {
		return this.formFactor;
	}
	
	String getOS() {
		return this.OS;
	}
	
	String getSymptoms() {
		return this.symptoms;
	}
	
	String getTroubleshooting() {
		return this.troubleshooting;
	}
	
	String getConclusion() {
		return this.conclusion;
	}
	
	String getDescription() {
		return this.description;
	}
	
	String getNotes() {
		return this.notes;
	}
	
	LocalDate getDateAssignedFollowUp() {
		return this.dateAssignedFollowUp;
	}
	
	int getFollowUpStatus() {
		return this.followUpStatus;
	}
	
	int getFollowUpIn() {
		return this.followUpIn;
	}
	
	//setters
	void setMonth(int newMonth) {
		this.month = newMonth;
	}
	
	void setDay(int newDay) {
		this.day = newDay;
	}
	
	void setYear(int newYear) {
		this.year = newYear;
	}
	
	void setExpirationMonth(int newMonth) {
		this.expirationMonth = newMonth;
	}
	
	void setExpirationDay(int newDay) {
		this.expirationDay = newDay;
	}
	
	void setExpirationYear(int newYear) {
		this.expirationYear = newYear;
	}
	
	void setVAIsChecked(boolean newVAIsChecked) {
		this.vaIsChecked = newVAIsChecked;
	}
	
	void setTOADEIsChecked(boolean newTOADEIsChecked) {
		this.toadeIsChecked = newTOADEIsChecked;
	}
	
	void setVDIIsChecked(boolean newVDIIsChecked) {
		this.vdiIsChecked = newVDIIsChecked;
	}
	
	void setEmailCapIsChecked(boolean newEmailCapIsChecked) {
		this.emailCapIsChecked = newEmailCapIsChecked;
	}
	
	void setTARPIsChecked(boolean newTARPIsChecked) {
		this.tarpIsChecked = newTARPIsChecked;
	}
	
	void setPOSIsChecked(boolean newPOSIsChecked) {
		this.posIsChecked = newPOSIsChecked;
	}
	
	void setPALIsChecked(boolean newPALIsChecked) {
		this.palIsChecked = newPALIsChecked;
	}
	
	void setPlasticsIsChecked(boolean newPlasticsIsChecked) {
		this.plasticsIsChecked = newPlasticsIsChecked;
	}
	
	void setCIDARIsChecked(boolean newCIDARIsChecked) {
		this.cidarIsChecked = newCIDARIsChecked;
	}
	
	void setCompany(String newCompany) {
		this.company = newCompany;
	}
	
	void setName(String newName) {
		this.name = newName;
	}
	
	void setEmail(String newEmail) {
		this.email = newEmail;
	}
	
	void setPhone(String newPhone) {
		this.phone = newPhone;
	}
	
	void setAltPhone(String newAltPhone) {
		this.altPhone = newAltPhone;
	}
	
	void setAddress(String newAddress) {
		this.address = newAddress;
	}
	
	void setCityStateZip(String newCityStateZip) {
		this.cityStateZip = newCityStateZip;
	}
	
	void setAltName(String newAltName) {
		this.altName = newAltName;
	}
	
	void setAltEmail(String newAltEmail) {
		this.altEmail = newAltEmail;
	}
	
	void setAltPrimaryPhone(String newAltPrimaryPhone) {
		this.altPrimaryPhone = newAltPrimaryPhone;
	}
	
	void setAltSecondaryPhone(String newAltSecondaryPhone) {
		this.altSecondaryPhone = newAltSecondaryPhone;
	}
	
	void setAltAddress(String newAltAddress) {
		this.altAddress = newAltAddress;
	}
	
	void setAltCityStateZip(String newAltCityStateZip) {
		this.altCityStateZip = newAltCityStateZip;
	}
	
	void setServiceTag(String newServiceTag) {
		this.serviceTag = newServiceTag;
	}
	
	void setServiceRequest(String newServiceRequest) {
		this.serviceRequest = newServiceRequest;
	}
	
	void setOrderNumber(String newOrderNumber) {
		this.orderNumber = newOrderNumber;
	}
	
	void setWarrantyType(String newWarrantyType) {
		this.warrantyType = newWarrantyType;
	}
	
	void setModel(String newModel) {
		this.model = newModel;
	}
	
	void setFormFactor(String newFormFactor) {
		this.formFactor = newFormFactor;
	}
	
	void setOS(String newOS) {
		this.OS = newOS;
	}
	
	void setSymptoms(String newSymptoms) {
		this.symptoms = newSymptoms;
	}
	
	void setTroubleshooting(String newTroubleshooting) {
		this.troubleshooting = newTroubleshooting;
	}
	
	void setConclusion(String newConclusion) {
		this.conclusion = newConclusion;
	}
	
	void setDescription(String newDescription) {
		this.description = newDescription;
	}
	
	void setNotes(String newNotes) {
		this.notes = newNotes;
	}
	
	void setDateAssignedFollowUp(LocalDate newDateAssignedFollowUp) {
		this.dateAssignedFollowUp = newDateAssignedFollowUp;
	}
	
	void setFollowUpStatus(int newFollowUpStatus) {
		this.followUpStatus = newFollowUpStatus;
	}
	
	void setFollowUpIn(int newFollowUpIn) {
		this.followUpIn = newFollowUpIn;
	}
	
	/**
	 * TODO
	 */
	void initializeStatus() {
		if (this.getFollowUpStatus() != Main.STATUS_IS_CLOSED &&
				this.getFollowUpStatus() != Main.STATUS_IS_UNKNOWN) {
			LocalDate today = new LocalDate();
			if (this.dateAssignedFollowUp.isBefore(today))
				this.followUpIn -= workingDaysBetween(this.dateAssignedFollowUp, today);
			else
				this.followUpIn += workingDaysBetween(this.dateAssignedFollowUp, today);
			
			this.dateAssignedFollowUp = today;
			
			if (followUpIn == 0) {
				this.setFollowUpStatus(Main.STATUS_IS_DUE);
			}
			
			else if (followUpIn < 0) {
				this.setFollowUpStatus(Main.STATUS_IS_OVERDUE);
			}
					
			else if (followUpIn > 0) {
				this.setFollowUpStatus(Main.STATUS_IS_TOUCHED);
			}
			
		}
	}
	
	/**
	 * Tells you how many business days are between any two dates
	 * 
	 * @param 	date1	LocalDate variable
	 * @param 	date2	LocalDate variable
	 * @return	An integer for the number of business days
	 */
	int workingDaysBetween(LocalDate date1, LocalDate date2) {
		//get total days between
		int daysBetween = Days.daysBetween(date1, date2).getDays();
		
		//if negative, swap
		if (daysBetween < 0) {
			return workingDaysBetween(date2, date1);
		}
		
		//calculate business days between
		int workingDaysBetween = 0;
		for (int i = 0; i < daysBetween; i++) {
			if (date1.plusDays(i + 1).getDayOfWeek() <= 5) //is weekday?
				workingDaysBetween += 1;
		}
		
		return workingDaysBetween;
	}
	
	/**
	 * Overridden toString method, output formatted as follows:
	 * 
	 * TODO
	 * 
	 */
	@Override public String toString() {
		return "";
	}
	
}