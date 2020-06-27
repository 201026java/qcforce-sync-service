package com.revature.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"id",
"batchId",
"name",
"startDate",
"endDate",
"skill",
"location",
"type",
"goodGrade",
"passingGrade",
"employeeAssignments"
})
public class Batch {

@JsonProperty("id")
private Integer id;
@JsonProperty("batchId")
private String batchId;
@JsonProperty("name")
private String name;
@JsonProperty("startDate")
private String startDate;
@JsonProperty("endDate")
private String endDate;
@JsonProperty("skill")
private String skill;
@JsonProperty("location")
private String location;
@JsonProperty("type")
private String type;
@JsonProperty("goodGrade")
private Integer goodGrade;
@JsonProperty("passingGrade")
private Integer passingGrade;
@JsonProperty("employeeAssignments")
private List<EmployeeAssignment> employeeAssignments = null;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("id")
public Integer getId() {
return id;
}

@JsonProperty("id")
public void setId(Integer id) {
this.id = id;
}

@JsonProperty("batchId")
public String getBatchId() {
return batchId;
}

@JsonProperty("batchId")
public void setBatchId(String batchId) {
this.batchId = batchId;
}

@JsonProperty("name")
public String getName() {
return name;
}

@JsonProperty("name")
public void setName(String name) {
this.name = name;
}

@JsonProperty("startDate")
public String getStartDate() {
return startDate;
}

@JsonProperty("startDate")
public void setStartDate(String startDate) {
this.startDate = startDate;
}

@JsonProperty("endDate")
public String getEndDate() {
return endDate;
}

@JsonProperty("endDate")
public void setEndDate(String endDate) {
this.endDate = endDate;
}

@JsonProperty("skill")
public String getSkill() {
return skill;
}

@JsonProperty("skill")
public void setSkill(String skill) {
this.skill = skill;
}

@JsonProperty("location")
public String getLocation() {
return location;
}

@JsonProperty("location")
public void setLocation(String location) {
this.location = location;
}

@JsonProperty("type")
public String getType() {
return type;
}

@JsonProperty("type")
public void setType(String type) {
this.type = type;
}

@JsonProperty("goodGrade")
public Integer getGoodGrade() {
return goodGrade;
}

@JsonProperty("goodGrade")
public void setGoodGrade(Integer goodGrade) {
this.goodGrade = goodGrade;
}

@JsonProperty("passingGrade")
public Integer getPassingGrade() {
return passingGrade;
}

@JsonProperty("passingGrade")
public void setPassingGrade(Integer passingGrade) {
this.passingGrade = passingGrade;
}

@JsonProperty("employeeAssignments")
public List<EmployeeAssignment> getEmployeeAssignments() {
return employeeAssignments;
}

@JsonProperty("employeeAssignments")
public void setEmployeeAssignments(List<EmployeeAssignment> employeeAssignments) {
this.employeeAssignments = employeeAssignments;
}

@JsonAnyGetter
public Map<String, Object> getAdditionalProperties() {
return this.additionalProperties;
}

@JsonAnySetter
public void setAdditionalProperty(String name, Object value) {
this.additionalProperties.put(name, value);
}

@Override
public String toString() {
	return "Batch [id=" + id + ", batchId=" + batchId + ", name=" + name + ", startDate=" + startDate + ", endDate="
			+ endDate + ", skill=" + skill + ", location=" + location + ", type=" + type + ", goodGrade=" + goodGrade
			+ ", passingGrade=" + passingGrade + ", employeeAssignments=" + employeeAssignments
			+ ", additionalProperties=" + additionalProperties + "]";
}



}