package org.model;

public class Emp {
    private Integer empId;
    private Integer dId;
    private String empName;
    private String photo;
    private String brief;
    private Dept dept;

    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public Integer getdId() {
        return dId;
    }

    public void setdId(Integer dId) {
        this.dId = dId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Emp emp = (Emp) o;

        if (empId != null ? !empId.equals(emp.empId) : emp.empId != null) return false;
        if (dId != null ? !dId.equals(emp.dId) : emp.dId != null) return false;
        if (empName != null ? !empName.equals(emp.empName) : emp.empName != null) return false;
        if (photo != null ? !photo.equals(emp.photo) : emp.photo != null) return false;
        if (brief != null ? !brief.equals(emp.brief) : emp.brief != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = empId != null ? empId.hashCode() : 0;
        result = 31 * result + (dId != null ? dId.hashCode() : 0);
        result = 31 * result + (empName != null ? empName.hashCode() : 0);
        result = 31 * result + (photo != null ? photo.hashCode() : 0);
        result = 31 * result + (brief != null ? brief.hashCode() : 0);
        return result;
    }

    public Dept getDept() {
        return dept;
    }

    public void setDept(Dept dept) {
        this.dept = dept;
    }
}
