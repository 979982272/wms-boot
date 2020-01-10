package com.tudou.base.warehouse.entity;

import com.tudou.system.platform.base.annotation.CreateFlag;
import com.tudou.system.platform.base.annotation.DeleteFlag;
import com.tudou.system.platform.base.annotation.ModifyFlag;
import com.tudou.system.platform.base.annotation.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "eidp_warehouse")
@CreateFlag
@ModifyFlag
@DeleteFlag
public class Warehouse {
    /**
     * 仓库编码
     */
    @Id
    @NotEmpty
    private String id;

    /**
     * 仓库名称
     */
    @Column(name = "warehouse_name")
    @NotEmpty
    private String warehouseName;

    /**
     * 仓库类型编码
     */
    @Column(name = "warehouse_type")
    @NotEmpty
    private String warehouseType;

    /**
     * 类型名称10售后仓20物料仓30限量仓40零售仓
     */
    @Column(name = "warehouse_type_name")
    @NotEmpty
    private String warehouseTypeName;

    /**
     * 所属机构
     */
    private String department;

    /**
     * 所属区域
     */
    private String area;

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
     * 备注
     */
    private String remark;

    /**
     * 删除时间
     */
    @Column(name = "delete_time")
    private Date deleteTime;

    /**
     * 删除人
     */
    @Column(name = "delete_emp")
    private String deleteEmp;

    /**
     * 删除人编号
     */
    @Column(name = "delete_emp_id")
    private String deleteEmpId;

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
     * 获取仓库编码
     *
     * @return id - 仓库编码
     */
    public String getId() {
        return id;
    }

    /**
     * 设置仓库编码
     *
     * @param id 仓库编码
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 获取仓库名称
     *
     * @return warehouse_name - 仓库名称
     */
    public String getWarehouseName() {
        return warehouseName;
    }

    /**
     * 设置仓库名称
     *
     * @param warehouseName 仓库名称
     */
    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName == null ? null : warehouseName.trim();
    }

    /**
     * 获取仓库类型编码
     *
     * @return warehouse_type - 仓库类型编码
     */
    public String getWarehouseType() {
        return warehouseType;
    }

    /**
     * 设置仓库类型编码
     *
     * @param warehouseType 仓库类型编码
     */
    public void setWarehouseType(String warehouseType) {
        this.warehouseType = warehouseType == null ? null : warehouseType.trim();
    }

    /**
     * 获取类型名称10售后仓20物料仓30限量仓40零售仓
     *
     * @return warehouse_type_name - 类型名称10售后仓20物料仓30限量仓40零售仓
     */
    public String getWarehouseTypeName() {
        return warehouseTypeName;
    }

    /**
     * 设置类型名称10售后仓20物料仓30限量仓40零售仓
     *
     * @param warehouseTypeName 类型名称10售后仓20物料仓30限量仓40零售仓
     */
    public void setWarehouseTypeName(String warehouseTypeName) {
        this.warehouseTypeName = warehouseTypeName == null ? null : warehouseTypeName.trim();
    }

    /**
     * 获取所属机构
     *
     * @return department - 所属机构
     */
    public String getDepartment() {
        return department;
    }

    /**
     * 设置所属机构
     *
     * @param department 所属机构
     */
    public void setDepartment(String department) {
        this.department = department == null ? null : department.trim();
    }

    /**
     * 获取所属区域
     *
     * @return area - 所属区域
     */
    public String getArea() {
        return area;
    }

    /**
     * 设置所属区域
     *
     * @param area 所属区域
     */
    public void setArea(String area) {
        this.area = area == null ? null : area.trim();
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
     * 获取删除时间
     *
     * @return delete_time - 删除时间
     */
    public Date getDeleteTime() {
        return deleteTime;
    }

    /**
     * 设置删除时间
     *
     * @param deleteTime 删除时间
     */
    public void setDeleteTime(Date deleteTime) {
        this.deleteTime = deleteTime;
    }

    /**
     * 获取删除人
     *
     * @return delete_emp - 删除人
     */
    public String getDeleteEmp() {
        return deleteEmp;
    }

    /**
     * 设置删除人
     *
     * @param deleteEmp 删除人
     */
    public void setDeleteEmp(String deleteEmp) {
        this.deleteEmp = deleteEmp == null ? null : deleteEmp.trim();
    }

    /**
     * 获取删除人编号
     *
     * @return delete_emp_id - 删除人编号
     */
    public String getDeleteEmpId() {
        return deleteEmpId;
    }

    /**
     * 设置删除人编号
     *
     * @param deleteEmpId 删除人编号
     */
    public void setDeleteEmpId(String deleteEmpId) {
        this.deleteEmpId = deleteEmpId == null ? null : deleteEmpId.trim();
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