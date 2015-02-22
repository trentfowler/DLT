package DLT;
import java.io.Serializable;

import org.joda.time.Days;
import org.joda.time.LocalDate;

/**
 * DataField class
 * 
 * This class represents one service request.
 * 
 * @author Trent
 * @author Bryan
 *
 */

public class DataField implements Serializable {
	
	private static final long serialVersionUID = -153071025273346052L;
	
	private boolean vaIsChecked;
	private boolean toadeIsChecked;
	private boolean vdiIsChecked;
	private boolean emailCapIsChecked;
	private boolean tarpIsChecked;
	private boolean posIsChecked;
	private boolean palIsChecked;
	private boolean plasticsIsChecked;
	private boolean cidarIsChecked;
	private boolean noacIsChecked;
	private String company;
	
	private String primaryFirstName;
	private String primaryLastName;
	private String primaryAreaCode;
	private String primaryPhoneNumber;
	private String primaryExt;
	private String primaryEmail;
	private String primaryAltAreaCode;
	private String primaryAltPhoneNumber;
	private String primaryAltExt;
	private String primaryAddress;
	private String primaryAddressL2;
	private String primaryCity;
	private String primaryZip;
	
	private String altFirstName;
	private String altLastName;
	private String altAreaCode;
	private String altPhoneNumber;
	private String altExt;
	private String altEmail;
	private String altAltAreaCode;
	private String altAltPhoneNumber;
	private String altAltExt;
	private String altAddress;
	private String altAddressL2;
	private String altCity;
	private String altZip;
	
	private String serviceTag;
	private String serviceRequest;
	private String symptoms;
	private String troubleshooting;
	private String conclusion;
	private String description;
	private String notes;
	private int status;
	private LocalDate committedDate;
	private LocalDate openedDate;
	
	/**
	 * DataField constructor
	 * 
	 * Creates a new DataField and initializes the values to default values.
	 */
	public DataField() {
		this.vaIsChecked = false;
		this.toadeIsChecked = false;
		this.vdiIsChecked = false;
		this.emailCapIsChecked = false;
		this.tarpIsChecked = false;
		this.posIsChecked = false;
		this.palIsChecked = false;
		this.plasticsIsChecked = false;
		this.cidarIsChecked = false;
		this.noacIsChecked = false;
		this.company = "";
		
		this.primaryFirstName = "";
		this.primaryLastName = "";
		this.primaryAreaCode = "";
		this.primaryPhoneNumber = "";
		this.primaryExt = "";
		this.primaryEmail = "";
		this.primaryAltAreaCode = "";
		this.primaryAltPhoneNumber = "";
		this.primaryAltExt = "";
		this.primaryAddress = "";
		this.primaryAddressL2 = "";
		this.primaryCity = "";
		this.primaryZip = "";
		
		this.altFirstName = "";
		this.altLastName = "";
		this.altAreaCode = "";
		this.altPhoneNumber = "";
		this.altExt = "";
		this.altEmail = "";
		this.altAltAreaCode = "";
		this.altAltPhoneNumber = "";
		this.altAltExt = "";
		this.altAddress = "";
		this.altAddressL2 = "";
		this.altCity = "";
		this.altZip = "";
		
		this.serviceTag = "";
		this.serviceRequest = "";
		this.symptoms = "";
		this.troubleshooting = "";
		this.conclusion = "";
		this.description = "";
		this.notes = "";
		this.status = Main.STATUS_IS_CLOSED;
		this.committedDate = new LocalDate();
		this.openedDate = new LocalDate();
	}
	
	/**
	 * DataField constructor
	 * 
	 * Creates a new DataField and initializes the values.
	 * 
	 * @param df	The DataField object to copy values from.
	 */
	public DataField(DataField df) {
		this.vaIsChecked = df.getVAIsChecked();
		this.toadeIsChecked = df.getTOADEIsChecked();
		this.vdiIsChecked = df.getVDIIsChecked();
		this.emailCapIsChecked = df.getEmailCapIsChecked();
		this.tarpIsChecked = df.getTARPIsChecked();
		this.posIsChecked = df.getPOSIsChecked();
		this.palIsChecked = df.getPALIsChecked();
		this.plasticsIsChecked = df.getPlasticsIsChecked();
		this.cidarIsChecked = df.getCIDARIsChecked();
		this.noacIsChecked = df.getNOACIsChecked();
		
		this.company = df.getCompany();
		
		this.primaryFirstName = df.getPrimaryFirstName();
		this.primaryLastName = df.getPrimaryLastName();
		this.primaryAreaCode = df.getPrimaryAreaCode();
		this.primaryPhoneNumber = df.getPrimaryPhoneNumber();
		this.primaryExt = df.getPrimaryExt();
		this.primaryEmail = df.getPrimaryEmail();
		this.primaryAltAreaCode = df.getPrimaryAltAreaCode();
		this.primaryAltPhoneNumber = df.getPrimaryAltPhoneNumber();
		this.primaryAltExt = df.getPrimaryExt();
		this.primaryAddress = df.getPrimaryAddress();
		this.primaryAddressL2 = df.getPrimaryAddressL2();
		this.primaryCity = df.getPrimaryCity();
		this.primaryZip = df.getPrimaryZip();
		
		this.altFirstName = df.getAltFirstName();
		this.altLastName = df.getAltLastName();
		this.altAreaCode = df.getAltAreaCode();
		this.altPhoneNumber = df.getAltPhoneNumber();
		this.altExt = df.getAltExt();
		this.altEmail = df.getAltEmail();
		this.altAltAreaCode = df.getAltAltAreaCode();
		this.altAltPhoneNumber = df.getAltAltPhoneNumber();
		this.altAltExt = df.getAltExt();
		this.altAddress = df.getAltAddress();
		this.altAddressL2 = df.getAltAddressL2();
		this.altCity = df.getAltCity();
		this.altZip = df.getAltZip();
		
		this.serviceTag = df.getServiceTag();
		this.serviceRequest = df.getServiceRequest();
		this.symptoms = df.getSymptoms();
		this.troubleshooting = df.getTroubleshooting();
		this.conclusion = df.getConclusion();
		this.description = df.getDescription();
		this.notes = df.getNotes();
		this.status = df.getStatus();
		this.committedDate = df.getCommittedDate();
		this.openedDate = df.getOpenedDate();
	}
	
	//getters
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
	
	boolean getNOACIsChecked() {
		return this.noacIsChecked;
	}
	
	String getCompany() {
		return this.company;
	}
	
	String getPrimaryFirstName() {
		return this.primaryFirstName;
	}
	
	String getPrimaryLastName() {
		return this.primaryLastName;
	}
	
	String getPrimaryAreaCode() {
		return this.primaryAreaCode;
	}
	
	String getPrimaryPhoneNumber() {
		return this.primaryPhoneNumber;
	}
	
	String getPrimaryExt() {
		return this.primaryExt;
	}
	
	String getPrimaryEmail() {
		return this.primaryEmail;
	}
	
	String getPrimaryAltAreaCode() {
		return this.primaryAltAreaCode;
	}
	
	String getPrimaryAltPhoneNumber() {
		return this.primaryAltPhoneNumber;
	}
	
	String getPrimaryAltExt() {
		return this.primaryAltExt;
	}
	
	String getPrimaryAddress() {
		return this.primaryAddress;
	}
	
	String getPrimaryAddressL2() {
		return this.primaryAddressL2;
	}
	
	String getPrimaryCity() {
		return this.primaryCity;
	}
	
	String getPrimaryZip() {
		return this.primaryZip;
	}
	
	String getAltFirstName() {
		return this.altFirstName;
	}
	
	String getAltLastName() {
		return this.altLastName;
	}
	
	String getAltAreaCode() {
		return this.altAreaCode;
	}
	
	String getAltPhoneNumber() {
		return this.altPhoneNumber;
	}
	
	String getAltExt() {
		return this.altExt;
	}
	
	String getAltEmail() {
		return this.altEmail;
	}
	
	String getAltAltAreaCode() {
		return this.altAltAreaCode;
	}
	
	String getAltAltPhoneNumber() {
		return this.altAltPhoneNumber;
	}
	
	String getAltAltExt() {
		return this.altAltExt;
	}
	
	String getAltAddress() {
		return this.altAddress;
	}
	
	String getAltAddressL2() {
		return this.altAddressL2;
	}
	
	String getAltCity() {
		return this.altCity;
	}
	
	String getAltZip() {
		return this.altZip;
	}
	
	String getServiceTag() {
		return this.serviceTag;
	}
	
	String getServiceRequest() {
		return this.serviceRequest;
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
	
	int getStatus() {
		return this.status;
	}
	
	LocalDate getCommittedDate() {
		return this.committedDate;
	}
	
	LocalDate getOpenedDate() {
		return this.openedDate;
	}
	
	//setters
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
	
	void setNOACIsChecked(boolean newNOACIsChecked) {
		this.noacIsChecked = newNOACIsChecked;
	}
	
	void setCompany(String newCompany) {
		this.company = newCompany;
	}
	
	void setPrimaryFirstName(String newPrimaryFirstName) {
		this.primaryFirstName = newPrimaryFirstName;
	}
	
	void setPrimaryLastName(String newPrimaryLastName) {
		this.primaryLastName = newPrimaryLastName;
	}
	
	void setPrimaryAreaCode(String newPrimaryAreaCode) {
		this.primaryAreaCode = newPrimaryAreaCode;
	}
	
	void setPrimaryPhoneNumber(String newPrimaryPhoneNumber) {
		this.primaryPhoneNumber = newPrimaryPhoneNumber;
	}
	
	void setPrimaryExt(String newPrimaryExt) {
		this.primaryExt = newPrimaryExt;
	}
	
	void setPrimaryEmail(String newPrimaryEmail) {
		this.primaryEmail = newPrimaryEmail;
	}
	
	void setPrimaryAltAreaCode(String newPrimaryAltAreaCode) {
		this.primaryAltAreaCode = newPrimaryAltAreaCode;
	}
	
	void setPrimaryAltPhoneNumber(String newPrimaryAltPhoneNumber) {
		this.primaryAltPhoneNumber = newPrimaryAltPhoneNumber;
	}
	
	void setPrimaryAltExt(String newPrimaryAltExt) {
		this.primaryAltExt = newPrimaryAltExt;
	}
	
	void setPrimaryAddress(String newPrimaryAddress) {
		this.primaryAddress = newPrimaryAddress;
	}
	
	void setPrimaryAddressL2(String newPrimaryAddressL2) {
		this.primaryAddressL2 = newPrimaryAddressL2;
	}
	
	void setPrimaryCity(String newPrimaryCity) {
		this.primaryCity = newPrimaryCity;
	}
	
	void setPrimaryZip(String newPrimaryZip) {
		this.primaryZip = newPrimaryZip;
	}
	
	void setAltFirstName(String newAltFirstName) {
		this.altFirstName = newAltFirstName;
	}
	
	void setAltLastName(String newAltLastName) {
		this.altLastName = newAltLastName;
	}
	
	void setAltAreaCode(String newAltAreaCode) {
		this.altAreaCode = newAltAreaCode;
	}
	
	void setAltPhoneNumber(String newAltPhoneNumber) {
		this.altPhoneNumber = newAltPhoneNumber;
	}
	
	void setAltExt(String newAltExt) {
		this.altExt = newAltExt;
	}
	
	void setAltEmail(String newAltEmail) {
		this.altEmail = newAltEmail;
	}
	
	void setAltAltAreaCode(String newAltAltAreaCode) {
		this.altAltAreaCode = newAltAltAreaCode;
	}
	
	void setAltAltPhoneNumber(String newAltAltPhoneNumber) {
		this.altAltPhoneNumber = newAltAltPhoneNumber;
	}
	
	void setAltAltExt(String newAltAltExt) {
		this.altAltExt = newAltAltExt;
	}
	
	void setAltAddress(String newAltAddress) {
		this.altAddress = newAltAddress;
	}
	
	void setAltAddressL2(String newAltAddressL2) {
		this.altAddressL2 = newAltAddressL2;
	}
	
	void setAltCity(String newAltCity) {
		this.altCity = newAltCity;
	}
	
	void setAltZip(String newAltZip) {
		this.altZip = newAltZip;
	}
	
	void setServiceTag(String newServiceTag) {
		this.serviceTag = newServiceTag;
	}
	
	void setServiceRequest(String newServiceRequest) {
		this.serviceRequest = newServiceRequest;
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
	
	void setStatus(int newStatus) {
		this.status = newStatus;
	}
	
	void setCommittedDate(LocalDate newCommittedDate) {
		this.committedDate = newCommittedDate;
	}
	
	void setOpenedDate(LocalDate newOpenedDate) {
		this.openedDate = newOpenedDate;
	}
	
	/**
	 * Returns true if the DataField object is empty.
	 * 
	 */
	boolean isEmpty() {
		String s = "";
		return this.vaIsChecked == false && 
			   this.toadeIsChecked == false && 
			   this.vdiIsChecked == false && 
			   this.emailCapIsChecked == false &&
			   this.tarpIsChecked == false &&
			   this.posIsChecked == false &&
			   this.palIsChecked == false &&
			   this.plasticsIsChecked == false &&
			   this.cidarIsChecked == false &&
			   this.noacIsChecked == false &&
			   s.equals(company) &&
			   s.equals(primaryFirstName) &&
			   s.equals(primaryLastName) &&
			   s.equals(primaryAreaCode) &&
			   s.equals(primaryPhoneNumber) &&
			   s.equals(primaryExt) &&
			   s.equals(primaryEmail) &&
			   s.equals(primaryAltAreaCode) &&
			   s.equals(primaryAltPhoneNumber) &&
			   s.equals(primaryAltExt) &&
			   s.equals(primaryAddress) &&
			   s.equals(primaryAddressL2) &&
			   s.equals(primaryCity) &&
			   s.equals(primaryZip) &&
			   s.equals(altFirstName) &&
			   s.equals(altLastName) &&
			   s.equals(altAreaCode) &&
			   s.equals(altPhoneNumber) &&
			   s.equals(altExt) &&
			   s.equals(altEmail) &&
			   s.equals(altAltAreaCode) &&
			   s.equals(altAltPhoneNumber) &&
			   s.equals(altAltExt) &&
			   s.equals(altAddress) &&
			   s.equals(altAddressL2) &&
			   s.equals(altCity) &&
			   s.equals(altZip) &&
			   s.equals(serviceTag) &&
			   s.equals(serviceRequest) &&
			   s.equals(symptoms) &&
			   s.equals(troubleshooting) &&
			   s.equals(conclusion) &&
			   s.equals(description) &&
			   s.equals(notes);
	}
		
	/**
	 * Sets the status of the case to DUE if committed date is today, OVERDUE if 
	 * committed date is before today, and TOUCHED if committed date is after 
	 * today. This method should be called when the program is launched.
	 */
	void initializeStatus() {
		if (this.getStatus() != Main.STATUS_IS_CLOSED &&
			this.getStatus() != Main.STATUS_IS_UNKNOWN) {
			LocalDate today = new LocalDate();
			if (this.committedDate.equals(today)) {
				this.setStatus(Main.STATUS_IS_DUE);
			}
			else if (this.committedDate.isAfter(today)) {
				this.setStatus(Main.STATUS_IS_TOUCHED);
			}
			else if (this.committedDate.isBefore(today)) {
				this.setStatus(Main.STATUS_IS_OVERDUE);
			}
		}
	}
	
	/**
	 * Tells you how many business days are between any two dates.
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
	 * TODO empty string
	 * 
	 */
	@Override public String toString() {
		return "";
	}
	
}