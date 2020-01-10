package com.tudou.crm.storage.inStorage.entity;

import com.tudou.system.platform.base.annotation.CreateFlag;
import com.tudou.system.platform.base.annotation.ModifyFlag;
import com.tudou.system.platform.base.annotation.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;
import java.util.List;

@Table(name = "crm_in_storage_work")
@CreateFlag
@ModifyFlag
public class InStorageWork {
    @Id
    private String id;

    /**
     * 来源单号
     */
    @Column(name = "from_order")
    @NotEmpty
    private String fromOrder;

    /**
     * 订单类型
     */
    @Column(name = "order_type")
    @NotEmpty
    private String orderType;

    /**
     * 订单日期
     */
    @Column(name = "order_date")
    @NotEmpty
    private Date orderDate;

    /**
     * 供应商编码
     */
    @Column(name = "vendor_id")
    @NotEmpty
    private String vendorId;

    /**
     * 供应商名称
     */
    @Column(name = "vendor_name")
    @NotEmpty
    private String vendorName;

    /**
     * 仓库编码
     */
    @Column(name = "warehouse_id")
    @NotEmpty
    private String warehouseId;

    /**
     * 仓库名称
     */
    @Column(name = "warehouse_name")
    @NotEmpty
    private String warehouseName;

    /**
     * 订单状态 10制单20审核30部分入库40完全入库
     */
    private Integer status;

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
     * 审核时间
     */
    @Column(name = "audit_time")
    private Date auditTime;

    /**
     * 审核人
     */
    @Column(name = "audit_emp")
    private String auditEmp;

    /**
     * 审核人编号
     */
    @Column(name = "audit_emp_id")
    private String auditEmpId;

    @Transient
    private List<InStorageWorkPart> inStorageWorkParts;

    public List<InStorageWorkPart> getInStorageWorkParts() {
        return inStorageWorkParts;
    }

    public void setInStorageWorkParts(List<InStorageWorkPart> inStorageWorkParts) {
        this.inStorageWorkParts = inStorageWorkParts;
    }

    /**
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 获取来源单号
     *
     * @return from_order - 来源单号
     */
    public String getFromOrder() {
        return fromOrder;
    }

    /**
     * 设置来源单号
     *
     * @param fromOrder 来源单号
     */
    public void setFromOrder(String fromOrder) {
        this.fromOrder = fromOrder == null ? null : fromOrder.trim();
    }

    /**
     * 获取订单类型
     *
     * @return order_type - 订单类型
     */
    public String getOrderType() {
        return orderType;
    }

    /**
     * 设置订单类型
     *
     * @param orderType 订单类型
     */
    public void setOrderType(String orderType) {
        this.orderType = orderType == null ? null : orderType.trim();
    }

    /**
     * 获取订单日期
     *
     * @return order_date - 订单日期
     */
    public Date getOrderDate() {
        return orderDate;
    }

    /**
     * 设置订单日期
     *
     * @param orderDate 订单日期
     */
    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    /**
     * 获取供应商编码
     *
     * @return vendor_id - 供应商编码
     */
    public String getVendorId() {
        return vendorId;
    }

    /**
     * 设置供应商编码
     *
     * @param vendorId 供应商编码
     */
    public void setVendorId(String vendorId) {
        this.vendorId = vendorId == null ? null : vendorId.trim();
    }

    /**
     * 获取供应商名称
     *
     * @return vendor_name - 供应商名称
     */
    public String getVendorName() {
        return vendorName;
    }

    /**
     * 设置供应商名称
     *
     * @param vendorName 供应商名称
     */
    public void setVendorName(String vendorName) {
        this.vendorName = vendorName == null ? null : vendorName.trim();
    }

    /**
     * 获取仓库编码
     *
     * @return warehouse_id - 仓库编码
     */
    public String getWarehouseId() {
        return warehouseId;
    }

    /**
     * 设置仓库编码
     *
     * @param warehouseId 仓库编码
     */
    public void setWarehouseId(String warehouseId) {
        this.warehouseId = warehouseId == null ? null : warehouseId.trim();
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
     * 获取订单状态 10制单20审核30部分入库40完全入库
     *
     * @return status - 订单状态 10制单20审核30部分入库40完全入库
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置订单状态 10制单20审核30部分入库40完全入库
     *
     * @param status 订单状态 10制单20审核30部分入库40完全入库
     */
    public void setStatus(Integer status) {
        this.status = status;
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

    /**
     * 获取审核时间
     *
     * @return audit_time - 审核时间
     */
    public Date getAuditTime() {
        return auditTime;
    }

    /**
     * 设置审核时间
     *
     * @param auditTime 审核时间
     */
    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    /**
     * 获取审核人
     *
     * @return audit_emp - 审核人
     */
    public String getAuditEmp() {
        return auditEmp;
    }

    /**
     * 设置审核人
     *
     * @param auditEmp 审核人
     */
    public void setAuditEmp(String auditEmp) {
        this.auditEmp = auditEmp == null ? null : auditEmp.trim();
    }

    /**
     * 获取审核人编号
     *
     * @return audit_emp_id - 审核人编号
     */
    public String getAuditEmpId() {
        return auditEmpId;
    }

    /**
     * 设置审核人编号
     *
     * @param auditEmpId 审核人编号
     */
    public void setAuditEmpId(String auditEmpId) {
        this.auditEmpId = auditEmpId == null ? null : auditEmpId.trim();
    }
}