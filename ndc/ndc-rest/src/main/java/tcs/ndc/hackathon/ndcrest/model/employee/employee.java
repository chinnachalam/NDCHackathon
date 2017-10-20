
package tcs.ndc.hackathon.ndcrest.model.employee;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "gender",
    "dob",
    "grade",
    "role",
    "name",
    "contacts",
    "ffpStatus",
    "preferences",
    "paymentInformation"
})
public class Employee {

    @JsonProperty("id")
    private String id;
    @JsonProperty("gender")
    private String gender;
    @JsonProperty("dob")
    private String dob;
    @JsonProperty("grade")
    private String grade;
    @JsonProperty("role")
    private String role;
    @JsonProperty("name")
    private Name name;
    @JsonProperty("contacts")
    private Contacts contacts;
    @JsonProperty("ffpStatus")
    private FfpStatus ffpStatus;
    @JsonProperty("preferences")
    private Preferences preferences;
    @JsonProperty("paymentInformation")
    private PaymentInformation paymentInformation;

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("gender")
    public String getGender() {
        return gender;
    }

    @JsonProperty("gender")
    public void setGender(String gender) {
        this.gender = gender;
    }

    @JsonProperty("dob")
    public String getDob() {
        return dob;
    }

    @JsonProperty("dob")
    public void setDob(String dob) {
        this.dob = dob;
    }

    @JsonProperty("grade")
    public String getGrade() {
        return grade;
    }

    @JsonProperty("grade")
    public void setGrade(String grade) {
        this.grade = grade;
    }

    @JsonProperty("role")
    public String getRole() {
        return role;
    }

    @JsonProperty("role")
    public void setRole(String role) {
        this.role = role;
    }

    @JsonProperty("name")
    public Name getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(Name name) {
        this.name = name;
    }

    @JsonProperty("contacts")
    public Contacts getContacts() {
        return contacts;
    }

    @JsonProperty("contacts")
    public void setContacts(Contacts contacts) {
        this.contacts = contacts;
    }

    @JsonProperty("ffpStatus")
    public FfpStatus getFfpStatus() {
        return ffpStatus;
    }

    @JsonProperty("ffpStatus")
    public void setFfpStatus(FfpStatus ffpStatus) {
        this.ffpStatus = ffpStatus;
    }

    @JsonProperty("preferences")
    public Preferences getPreferences() {
        return preferences;
    }

    @JsonProperty("preferences")
    public void setPreferences(Preferences preferences) {
        this.preferences = preferences;
    }

    @JsonProperty("paymentInformation")
    public PaymentInformation getPaymentInformation() {
        return paymentInformation;
    }

    @JsonProperty("paymentInformation")
    public void setPaymentInformation(PaymentInformation paymentInformation) {
        this.paymentInformation = paymentInformation;
    }

}
