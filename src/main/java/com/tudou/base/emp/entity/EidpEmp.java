package com.tudou.base.emp.entity;


import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "eidp_emp")
public class EidpEmp {
    /**
     * 主键
     */
    @Id
    private String id;

    /**
     * 姓名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    private String sex;

    /**
     * 机构
     */
    private String department;

    /**
     * 上司
     */
    private String superior;

    /**
     * 职务
     */
    private String post;

    /**
     * 职称
     */
    private String title;

    /**
     * 电话
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 出生日期
     */
    private Date birth;

    /**
     * 入职日期
     */
    private Date enrollment;

    /**
     * 离职日期
     */
    private Date resign;

    /**
     * 合同到期日期
     */
    private Date contract;

    /**
     * 创建日期
     */
    private Date createtime;

    /**
     * 创建人id
     */
    @Column(name = "create_emp_id")
    private String createEmpId;

    /**
     * 创建人
     */
    @Column(name = "create_emp")
    private String createEmp;

    /**
     * 修改日期
     */
    private Date modifytime;

    /**
     * 修改人id
     */
    @Column(name = "modify_emp_id")
    private String modifyEmpId;

    /**
     * 修改人
     */
    @Column(name = "modify_emp")
    private String modifyEmp;

    /**
     * 是否删除0：正常,1删除
     */
    @Column(name = "server_flag")
    private int serverFlag;


    public int getServerFlag() {
        return serverFlag;
    }

    public void setServerFlag(int serverFlag) {
        this.serverFlag = serverFlag;
    }

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public String getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取密码
     *
     * @return password - 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * @return sex
     */
    public String getSex() {
        return sex;
    }

    /**
     * @param sex
     */
    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    /**
     * 获取部门
     *
     * @return department - 部门
     */
    public String getDepartment() {
        return department;
    }

    /**
     * 设置部门
     *
     * @param department 部门
     */
    public void setDepartment(String department) {
        this.department = department == null ? null : department.trim();
    }

    /**
     * 获取上司
     *
     * @return superior - 上司
     */
    public String getSuperior() {
        return superior;
    }

    /**
     * 设置上司
     *
     * @param superior 上司
     */
    public void setSuperior(String superior) {
        this.superior = superior == null ? null : superior.trim();
    }

    /**
     * 获取职务
     *
     * @return post - 职务
     */
    public String getPost() {
        return post;
    }

    /**
     * 设置职务
     *
     * @param post 职务
     */
    public void setPost(String post) {
        this.post = post == null ? null : post.trim();
    }

    /**
     * 获取职称
     *
     * @return title - 职称
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置职称
     *
     * @param title 职称
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * 获取电话
     *
     * @return phone - 电话
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置电话
     *
     * @param phone 电话
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /**
     * 获取邮箱
     *
     * @return email - 邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置邮箱
     *
     * @param email 邮箱
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * 获取出生日期
     *
     * @return birth - 出生日期
     */
    public Date getBirth() {
        return birth;
    }

    /**
     * 设置出生日期
     *
     * @param birth 出生日期
     */
    public void setBirth(Date birth) {
        this.birth = birth;
    }

    /**
     * 获取入职日期
     *
     * @return enrollment - 入职日期
     */
    public Date getEnrollment() {
        return enrollment;
    }

    /**
     * 设置入职日期
     *
     * @param enrollment 入职日期
     */
    public void setEnrollment(Date enrollment) {
        this.enrollment = enrollment;
    }

    /**
     * 获取离职日期
     *
     * @return resign - 离职日期
     */
    public Date getResign() {
        return resign;
    }

    /**
     * 设置离职日期
     *
     * @param resign 离职日期
     */
    public void setResign(Date resign) {
        this.resign = resign;
    }

    /**
     * 获取创建日期
     *
     * @return createtime - 创建日期
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * 获取合同到期日期
     *
     * @return contract - 合同到期日期
     */
    public Date getContract() {
        return contract;
    }

    /**
     * 设置合同到期日期
     *
     * @param contract 合同到期日期
     */
    public void setContract(Date contract) {
        this.contract = contract;
    }

    /**
     * 设置创建日期
     *
     * @param createtime 创建日期
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    /**
     * 获取创建人id
     *
     * @return create_emp_id - 创建人id
     */
    public String getCreateEmpId() {
        return createEmpId;
    }

    /**
     * 设置创建人id
     *
     * @param createEmpId 创建人id
     */
    public void setCreateEmpId(String createEmpId) {
        this.createEmpId = createEmpId == null ? null : createEmpId.trim();
    }

    /**
     * 获取创建人
     *
     * @return create_emp - 创建人
     */
    public String getCreateEmp() {
        return createEmp;
    }

    /**
     * 设置创建人
     *
     * @param createEmp 创建人
     */
    public void setCreateEmp(String createEmp) {
        this.createEmp = createEmp == null ? null : createEmp.trim();
    }

    /**
     * 获取修改日期
     *
     * @return modifytime - 修改日期
     */
    public Date getModifytime() {
        return modifytime;
    }

    /**
     * 设置修改日期
     *
     * @param modifytime 修改日期
     */
    public void setModifytime(Date modifytime) {
        this.modifytime = modifytime;
    }

    /**
     * 获取修改人id
     *
     * @return modify_emp_id - 修改人id
     */
    public String getModifyEmpId() {
        return modifyEmpId;
    }

    /**
     * 设置修改人id
     *
     * @param modifyEmpId 修改人id
     */
    public void setModifyEmpId(String modifyEmpId) {
        this.modifyEmpId = modifyEmpId == null ? null : modifyEmpId.trim();
    }

    /**
     * 获取修改人
     *
     * @return modify_emp - 修改人
     */
    public String getModifyEmp() {
        return modifyEmp;
    }

    /**
     * 设置修改人
     *
     * @param modifyEmp 修改人
     */
    public void setModifyEmp(String modifyEmp) {
        this.modifyEmp = modifyEmp == null ? null : modifyEmp.trim();
    }
}