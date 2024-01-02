package sg.vttp.nus.iss.vttp_mini_project_1.Model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class QuoteRequest {

    @NotBlank(message="Please enter your given name.")
    @Size(min=2, max = 20, message = "Given name must be between 2-20 characters")
    private String givenName;

    @NotBlank(message="Please enter your family name.")
    @Size(min=2, max = 20, message = "Family name must be between 2-20 characters")
    private String familyName;

    @Email
    @NotBlank(message = "Email is required.")
    @Size(max = 50, message = "Maximum of 50 characters allowed.")
    private String email;

    @Pattern(regexp = "(8|9)[0-9]{7}", message = "Invalid phone number entered. Please enter a valid Singapore Phone Number starting with 8/9.")
    private String mobileNo;

    @NotBlank(message = "Contact Address is required.")
    @Size(min=10, max = 50, message = "Contact address must be between 10-50 characters.")
    private String contactAddress;
    
    @Size(max = 50, message = "Maximum of 50 characters allowed.")
    private String siteAddress;

    @NotBlank(message = "Please select one of the sites most relevant to you.")
    private String siteType;

    @NotBlank(message = "Please select a roof type. If not installing on a roof, select others.")
    private String roofType;

    @Pattern(regexp = "^[1-9][0-9]*$|^[0]*$", message = "Monthly Bill must be either a whole number or 0, no leading zeros allowed.")
    private String monthlyBill;

    @NotBlank(message = "Electricity Retailer is required.")
    private String electricityRetailer;

    @NotBlank(message = "Please select one of the options")
    private String electricityUsage;

    @NotBlank(message = "Please select one of the options.")
    private String expectedInstallTime;

    private boolean contactPermission;

    
    public QuoteRequest(
        String givenName,
        String familyName,
        String email,
        String mobileNo,
        String contactAddress,
        String siteAddress,
        String siteType,
        String roofType,
        String monthlyBill,
        String electricityRetailer,
        String electricityUsage,
        String expectedInstallTime,
        boolean contactPermission) {
        this.givenName = givenName;
        this.familyName = familyName;
        this.email = email;
        this.mobileNo = mobileNo;
        this.contactAddress = contactAddress;
        this.siteAddress = siteAddress;
        this.siteType = siteType;
        this.roofType = roofType;
        this.monthlyBill = monthlyBill;
        this.electricityRetailer = electricityRetailer;
        this.electricityUsage = electricityUsage;
        this.expectedInstallTime = expectedInstallTime;
        this.contactPermission = contactPermission;
    }

    public QuoteRequest() {
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getSiteType() {
        return siteType;
    }

    public void setSiteType(String siteType) {
        this.siteType = siteType;
    }

    public String getRoofType() {
        return roofType;
    }

    public void setRoofType(String roofType) {
        this.roofType = roofType;
    }

    public String getMonthlyBill() {
        return monthlyBill;
    }

    public void setMonthlyBill(String monthlyBill) {
        this.monthlyBill = monthlyBill;
    }

    public String getElectricityRetailer() {
        return electricityRetailer;
    }

    public void setElectricityRetailer(String electricityRetailer) {
        this.electricityRetailer = electricityRetailer;
    }

    public String getElectricityUsage() {
        return electricityUsage;
    }

    public void setElectricityUsage(String electricityUsage) {
        this.electricityUsage = electricityUsage;
    }

    public String getExpectedInstallTime() {
        return expectedInstallTime;
    }

    public void setExpectedInstallTime(String expectedInstallTime) {
        this.expectedInstallTime = expectedInstallTime;
    }

    

    @Override
    public String toString() {
        return "givenName=" + givenName + ", familyName=" + familyName + ", email=" + email
                + ", mobileNo=" + mobileNo + ", contactAddress=" + contactAddress + ", siteAddress=" + siteAddress
                + ", siteType=" + siteType + ", roofType=" + roofType + ", monthlyBill=" + monthlyBill
                + ", electricityRetailer=" + electricityRetailer + ", electricityUsage=" + electricityUsage
                + ", expectedInstallTime=" + expectedInstallTime + ", contactPermission=" + contactPermission;
    }

    public boolean getContactPermission() {
        return contactPermission;
    }

    public void setContactPermission(boolean contactPermission) {
        this.contactPermission = contactPermission;
    }

    public String getContactAddress() {
        return contactAddress;
    }

    public void setContactAddress(String contactAddress) {
        this.contactAddress = contactAddress;
    }

    public String getSiteAddress() {
        return siteAddress;
    }

    public void setSiteAddress(String siteAddress) {
        this.siteAddress = siteAddress;
    }
    
}
