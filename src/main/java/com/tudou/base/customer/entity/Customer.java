package com.tudou.base.customer.entity;


import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "eidp_customer")
public class Customer {
    /**
     * 客户编码
     */
    @Id
    private String id;

    /**
     * 客户名称
     */
    @Column(name = "customer_name")
    private String customerName;

    /**
     * 客户类型 10售后20商场30零售
     */
    @Column(name = "customer_type")
    private String customerType;

    /**
     * 客户负责人编号
     */
    @Column(name = "customer_emp_id")
    private String customerEmpId;

    /**
     * 客户负责人 名称
     */
    @Column(name = "customer_emp_name")
    private String customerEmpName;

    /**
     * 销售方式10直销20委托代销30零售
     */
    @Column(name = "sales_type")
    private String salesType;

    /**
     * 客户级别10VIP客户20重要客户30一般客户
     */
    @Column(name = "customer_level")
    private String customerLevel;

    /**
     * 应收金额
     */
    @Column(name = "receivable_amount")
    private BigDecimal receivableAmount;

    /**
     * 国家
     */
    private String country;

    /**
     * 省份
     */
    private String province;

    /**
     * 城市
     */
    private String city;

    /**
     * 邮编
     */
    @Column(name = "post_code")
    private String postCode;

    /**
     * 地址
     */
    private String address;

    /**
     * 联系人
     */
    @Column(name = "contact_person")
    private String contactPerson;

    /**
     * 联系电话
     */
    @Column(name = "contact_phone")
    private String contactPhone;

    /**
     * 传真
     */
    @Column(name = "contact_fax")
    private String contactFax;

    /**
     * 邮件
     */
    @Column(name = "contact_email")
    private String contactEmail;

    /**
     * 法人
     */
    @Column(name = "legal_person")
    private String legalPerson;

    /**
     * 营业执照
     */
    @Column(name = "business_license")
    private String businessLicense;

    /**
     * 税务登记号
     */
    @Column(name = "taxation_code")
    private String taxationCode;

    /**
     * 开户行
     */
    @Column(name = "opening_bank")
    private String openingBank;

    /**
     * 开户账号
     */
    @Column(name = "opening_bank_account")
    private String openingBankAccount;

    /**
     * 备注
     */
    private String remark;

    /**
     * 是否删除0：正常,1删除
     */
    @Column(name = "server_flag")
    private Integer serverFlag;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 创建人
     */
    @Column(name = "create_emp")
    private String createEmp;

    /**
     * 创建人编号
     */
    @Column(name = "create_emp_id")
    private String createEmpId;

    /**
     * 修改时间
     */
    @Column(name = "modify_time")
    private Date modifyTime;

    /**
     * 修改人
     */
    @Column(name = "modify_emp")
    private String modifyEmp;

    /**
     * 修改人编号
     */
    @Column(name = "modify_emp_id")
    private String modifyEmpId;

    /**
     * 获取客户编码
     *
     * @return id - 客户编码
     */
    public String getId() {
        return id;
    }

    /**
     * 设置客户编码
     *
     * @param id 客户编码
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 获取客户名称
     *
     * @return customer_name - 客户名称
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * 设置客户名称
     *
     * @param customerName 客户名称
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName == null ? null : customerName.trim();
    }

    /**
     * 获取客户类型 10售后20商场30零售
     *
     * @return customer_type - 客户类型 10售后20商场30零售
     */
    public String getCustomerType() {
        return customerType;
    }

    /**
     * 设置客户类型 10售后20商场30零售
     *
     * @param customerType 客户类型 10售后20商场30零售
     */
    public void setCustomerType(String customerType) {
        this.customerType = customerType == null ? null : customerType.trim();
    }

    /**
     * 获取客户负责人编号
     *
     * @return customer_emp_id - 客户负责人编号
     */
    public String getCustomerEmpId() {
        return customerEmpId;
    }

    /**
     * 设置客户负责人编号
     *
     * @param customerEmpId 客户负责人编号
     */
    public void setCustomerEmpId(String customerEmpId) {
        this.customerEmpId = customerEmpId == null ? null : customerEmpId.trim();
    }

    /**
     * 获取客户负责人 名称
     *
     * @return customer_emp_name - 客户负责人 名称
     */
    public String getCustomerEmpName() {
        return customerEmpName;
    }

    /**
     * 设置客户负责人 名称
     *
     * @param customerEmpName 客户负责人 名称
     */
    public void setCustomerEmpName(String customerEmpName) {
        this.customerEmpName = customerEmpName == null ? null : customerEmpName.trim();
    }

    /**
     * 获取销售方式10直销20委托代销30零售
     *
     * @return sales_type - 销售方式10直销20委托代销30零售
     */
    public String getSalesType() {
        return salesType;
    }

    /**
     * 设置销售方式10直销20委托代销30零售
     *
     * @param salesType 销售方式10直销20委托代销30零售
     */
    public void setSalesType(String salesType) {
        this.salesType = salesType == null ? null : salesType.trim();
    }

    /**
     * 获取客户级别10VIP客户20重要客户30一般客户
     *
     * @return customer_level - 客户级别10VIP客户20重要客户30一般客户
     */
    public String getCustomerLevel() {
        return customerLevel;
    }

    /**
     * 设置客户级别10VIP客户20重要客户30一般客户
     *
     * @param customerLevel 客户级别10VIP客户20重要客户30一般客户
     */
    public void setCustomerLevel(String customerLevel) {
        this.customerLevel = customerLevel == null ? null : customerLevel.trim();
    }

    /**
     * 获取应收金额
     *
     * @return receivable_amount - 应收金额
     */
    public BigDecimal getReceivableAmount() {
        return receivableAmount;
    }

    /**
     * 设置应收金额
     *
     * @param receivableAmount 应收金额
     */
    public void setReceivableAmount(BigDecimal receivableAmount) {
        this.receivableAmount = receivableAmount;
    }

    /**
     * 获取国家
     *
     * @return country - 国家
     */
    public String getCountry() {
        return country;
    }

    /**
     * 设置国家
     *
     * @param country 国家
     */
    public void setCountry(String country) {
        this.country = country == null ? null : country.trim();
    }

    /**
     * 获取省份
     *
     * @return province - 省份
     */
    public String getProvince() {
        return province;
    }

    /**
     * 设置省份
     *
     * @param province 省份
     */
    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    /**
     * 获取城市
     *
     * @return city - 城市
     */
    public String getCity() {
        return city;
    }

    /**
     * 设置城市
     *
     * @param city 城市
     */
    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    /**
     * 获取邮编
     *
     * @return post_code - 邮编
     */
    public String getPostCode() {
        return postCode;
    }

    /**
     * 设置邮编
     *
     * @param postCode 邮编
     */
    public void setPostCode(String postCode) {
        this.postCode = postCode == null ? null : postCode.trim();
    }

    /**
     * 获取地址
     *
     * @return address - 地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置地址
     *
     * @param address 地址
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     * 获取联系人
     *
     * @return contact_person - 联系人
     */
    public String getContactPerson() {
        return contactPerson;
    }

    /**
     * 设置联系人
     *
     * @param contactPerson 联系人
     */
    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson == null ? null : contactPerson.trim();
    }

    /**
     * 获取联系电话
     *
     * @return contact_phone - 联系电话
     */
    public String getContactPhone() {
        return contactPhone;
    }

    /**
     * 设置联系电话
     *
     * @param contactPhone 联系电话
     */
    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone == null ? null : contactPhone.trim();
    }

    /**
     * 获取传真
     *
     * @return contact_fax - 传真
     */
    public String getContactFax() {
        return contactFax;
    }

    /**
     * 设置传真
     *
     * @param contactFax 传真
     */
    public void setContactFax(String contactFax) {
        this.contactFax = contactFax == null ? null : contactFax.trim();
    }

    /**
     * 获取邮件
     *
     * @return contact_email - 邮件
     */
    public String getContactEmail() {
        return contactEmail;
    }

    /**
     * 设置邮件
     *
     * @param contactEmail 邮件
     */
    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail == null ? null : contactEmail.trim();
    }

    /**
     * 获取法人
     *
     * @return legal_person - 法人
     */
    public String getLegalPerson() {
        return legalPerson;
    }

    /**
     * 设置法人
     *
     * @param legalPerson 法人
     */
    public void setLegalPerson(String legalPerson) {
        this.legalPerson = legalPerson == null ? null : legalPerson.trim();
    }

    /**
     * 获取营业执照
     *
     * @return business_license - 营业执照
     */
    public String getBusinessLicense() {
        return businessLicense;
    }

    /**
     * 设置营业执照
     *
     * @param businessLicense 营业执照
     */
    public void setBusinessLicense(String businessLicense) {
        this.businessLicense = businessLicense == null ? null : businessLicense.trim();
    }

    /**
     * 获取税务登记号
     *
     * @return taxation_code - 税务登记号
     */
    public String getTaxationCode() {
        return taxationCode;
    }

    /**
     * 设置税务登记号
     *
     * @param taxationCode 税务登记号
     */
    public void setTaxationCode(String taxationCode) {
        this.taxationCode = taxationCode == null ? null : taxationCode.trim();
    }

    /**
     * 获取开户行
     *
     * @return opening_bank - 开户行
     */
    public String getOpeningBank() {
        return openingBank;
    }

    /**
     * 设置开户行
     *
     * @param openingBank 开户行
     */
    public void setOpeningBank(String openingBank) {
        this.openingBank = openingBank == null ? null : openingBank.trim();
    }

    /**
     * 获取开户账号
     *
     * @return opening_bank_account - 开户账号
     */
    public String getOpeningBankAccount() {
        return openingBankAccount;
    }

    /**
     * 设置开户账号
     *
     * @param openingBankAccount 开户账号
     */
    public void setOpeningBankAccount(String openingBankAccount) {
        this.openingBankAccount = openingBankAccount == null ? null : openingBankAccount.trim();
    }

    /**
     * 获取备注
     *
     * @return remark - 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注
     *
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 获取是否删除0：正常,1删除
     *
     * @return server_flag - 是否删除0：正常,1删除
     */
    public Integer getServerFlag() {
        return serverFlag;
    }

    /**
     * 设置是否删除0：正常,1删除
     *
     * @param serverFlag 是否删除0：正常,1删除
     */
    public void setServerFlag(Integer serverFlag) {
        this.serverFlag = serverFlag;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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
     * 获取创建人编号
     *
     * @return create_emp_id - 创建人编号
     */
    public String getCreateEmpId() {
        return createEmpId;
    }

    /**
     * 设置创建人编号
     *
     * @param createEmpId 创建人编号
     */
    public void setCreateEmpId(String createEmpId) {
        this.createEmpId = createEmpId == null ? null : createEmpId.trim();
    }

    /**
     * 获取修改时间
     *
     * @return modify_time - 修改时间
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * 设置修改时间
     *
     * @param modifyTime 修改时间
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
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

    /**
     * 获取修改人编号
     *
     * @return modify_emp_id - 修改人编号
     */
    public String getModifyEmpId() {
        return modifyEmpId;
    }

    /**
     * 设置修改人编号
     *
     * @param modifyEmpId 修改人编号
     */
    public void setModifyEmpId(String modifyEmpId) {
        this.modifyEmpId = modifyEmpId == null ? null : modifyEmpId.trim();
    }
}