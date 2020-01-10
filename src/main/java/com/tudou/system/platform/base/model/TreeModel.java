package com.tudou.system.platform.base.model;

/**
 * 树的模型
 *
 * @author weihua
 * @create 2017-05-13 21:40
 */
public class TreeModel {
    private String parentId;
    private String FullNamel;
    private boolean HasEmployees;
    private String ReportsTo;

    public String getEmployeeId() {
        return parentId;
    }

    public void setEmployeeId(String employeeId) {
        parentId = employeeId;
    }

    public String getFullNamel() {
        return FullNamel;
    }

    public void setFullNamel(String fullNamel) {
        FullNamel = fullNamel;
    }

    public boolean isHasEmployees() {
        return HasEmployees;
    }

    public void setHasEmployees(boolean hasEmployees) {
        HasEmployees = hasEmployees;
    }

    public String getReportsTo() {
        return ReportsTo;
    }

    public void setReportsTo(String reportsTo) {
        ReportsTo = reportsTo;
    }
}
