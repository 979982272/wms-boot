package com.tudou.extra.system;

/**
 * 验证信息
 *
 * @author weihua
 * @create 2017-05-13 11:20
 */
public class AuthInfo {
    private String empId;
    private String empName;
    private String department;

    protected AuthInfo() {

    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
