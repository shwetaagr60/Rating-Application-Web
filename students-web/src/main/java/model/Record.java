package model;


public class Record {
	private String studentName = "";
	private String subjectName = "";
	private String assignmentCategory = "";
	private String dateOfSubmission = null;
	private float points = 0.0f;
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public String getAssignmentCategory() {
		return assignmentCategory;
	}
	public void setAssignmentCategory(String assignmentCategory) {
		this.assignmentCategory = assignmentCategory;
	}
	public String getDateOfSubmission() {
		return dateOfSubmission;
	}
	public void setDateOfSubmission(String dateOfSubmission) {
		this.dateOfSubmission = dateOfSubmission;
	}
	public float getPoints() {
		return points;
	}
	public void setPoints(float points) {
		this.points = points;
	}
	
}
