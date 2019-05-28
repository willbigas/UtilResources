package model;


/**
 *
 * @author William
 */
public class Customer {
    
    private Integer id;
    private String company;
    private String lastName;
    private String firstName;
    private String jobTitle;
    private String businessPhone;
    private String faxNumber;
    private String adress;
    private String city;
    private String stateProvince;
    private String zipPostalCode;
    private String countryRegion;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getBusinessPhone() {
        return businessPhone;
    }

    public void setBusinessPhone(String businessPhone) {
        this.businessPhone = businessPhone;
    }

    public String getFaxNumber() {
        return faxNumber;
    }

    public void setFaxNumber(String faxNumber) {
        this.faxNumber = faxNumber;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStateProvince() {
        return stateProvince;
    }

    public void setStateProvince(String stateProvince) {
        this.stateProvince = stateProvince;
    }

    public String getZipPostalCode() {
        return zipPostalCode;
    }

    public void setZipPostalCode(String zipPostalCode) {
        this.zipPostalCode = zipPostalCode;
    }

    public String getCountryRegion() {
        return countryRegion;
    }

    public void setCountryRegion(String countryRegion) {
        this.countryRegion = countryRegion;
    }

    @Override
    public String toString() {
        return "Customer{" + "id=" + id + ", company=" + company + ", lastName=" + lastName + ", firstName=" + firstName + ", jobTitle=" + jobTitle + ", businessPhone=" + businessPhone + ", faxNumber=" + faxNumber + ", adress=" + adress + ", city=" + city + ", stateProvince=" + stateProvince + ", zipPostalCode=" + zipPostalCode + ", countryRegion=" + countryRegion + '}';
    }
    
    
}
