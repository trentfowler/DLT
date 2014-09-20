package DLT;
import java.io.Serializable;

import org.joda.time.Days;
import org.joda.time.LocalDate;

/**
 * DataField class
 * 
 * ...
 * 
 * @author Trent
 * @author Bryan
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
	private boolean nocIsChecked;
	private String company;
	private String name;
	private String email;
	private String phone;
	private String altPhone;
	private String address;
	private String address2;
	private String zip;
	private String altName;
	private String altEmail;
	private String altPrimaryPhone;
	private String altSecondaryPhone;
	private String altAddress;
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
	private String priority;
	
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
		this.nocIsChecked = false;
		this.company = "";
		this.name = "";
		this.email = "";
		this.phone = "";
		this.altPhone = "";
		this.address = "";
		this.address2 = "";
		this.zip = "";
		this.altName = "";
		this.altEmail = "";
		this.altPrimaryPhone = "";
		this.altSecondaryPhone = "";
		this.altAddress = "";
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
		this.priority = Main.PRIORITY_3;
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
		this.nocIsChecked = df.getNOCIsChecked();
		this.company = df.getCompany();
		this.name = df.getName();
		this.email = df.getEmail();
		this.phone = df.getPhone();
		this.altPhone = df.getAltPhone();
		this.address = df.getAddress();
		this.address2 = df.getAddress2();
		this.zip = df.getZip();
		this.altName = df.getAltName();
		this.altEmail = df.getAltEmail();
		this.altPrimaryPhone = df.getAltPrimaryPhone();
		this.altSecondaryPhone = df.getAltSecondaryPhone();
		this.altAddress = df.getAltAddress();
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
		this.priority = df.getPriority();
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
	
	boolean getNOCIsChecked() {
		return this.nocIsChecked;
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
	
	String getAddress2() {
		return this.address2;
	}
	
	String getZip() {
		return this.zip;
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
	
	String getPriority() {
		return this.priority;
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

	void setNOCIsChecked(boolean newNOCIsChecked) {
		this.cidarIsChecked = newNOCIsChecked;
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
	
	void setAddress2(String newAddress2) {
		this.address2 = newAddress2;
	}
	
	void setZip(String newZip) {
		this.zip = newZip;
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
	void setPriority(String newPriority) {
		this.priority = newPriority;
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

	public boolean getNocIsChecked() {
		// TODO Auto-generated method stub
		return false;
	}
	
}
