package com.tudou.crm.stock.stockBegin.entity;

import com.tudou.system.platform.base.annotation.AuditFlag;
import com.tudou.system.platform.base.annotation.CreateFlag;
import com.tudou.system.platform.base.annotation.ModifyFlag;
import com.tudou.system.platform.base.annotation.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "crm_stock_begin")
@CreateFlag
@ModifyFlag
@AuditFlag
public class StockBegin {
    /**
     * 主键
     */
    @Id
    @NotEmpty
    private String id;

    @NotEmpty
    private Integer status;

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
     * 产品编码
     */
    @Column(name = "goods_id")
    @NotEmpty
    private String goodsId;

    /**
     * 产品名称
     */
    @Column(name = "goods_name")
    @NotEmpty
    private String goodsName;

    /**
     * 单位编码
     */
    @Column(name = "goods_unit_id")
    @NotEmpty
    private String goodsUnitId;

    /**
     * 单位名称
     */
    @Column(name = "goods_unit_name")
    @NotEmpty
    private String goodsUnitName;

    /**
     * 规格型号
     */
    @Column(name = "goods_model")
    private String goodsModel;

    /**
     * 期初库存
     */
    @Column(name = "stock_amount")
    @NotEmpty
    private BigDecimal stockAmount;

    /**
     * 单位价格
     */
    @Column(name = "unit_price")
    @NotEmpty
    private BigDecimal unitPrice;

    /**
     * 含税单价
     */
    @Column(name = "unit_price_rate")
    @NotEmpty
    private BigDecimal unitPriceRate;

    /**
     * 总价
     */
    @Column(name = "total_price")
    @NotEmpty
    private BigDecimal totalPrice;

    /**
     * 含税总价
     */
    @Column(name = "total_price_rate")
    @NotEmpty
    private BigDecimal totalPriceRate;

    /**
     * 税金总额
     */
    @Column(name = "rate_price")
    @NotEmpty
    private BigDecimal ratePrice;

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
     * 获取产品编码
     *
     * @return goods_id - 产品编码
     */
    public String getGoodsId() {
        return goodsId;
    }

    /**
     * 设置产品编码
     *
     * @param goodsId 产品编码
     */
    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId == null ? null : goodsId.trim();
    }

    /**
     * 获取产品名称
     *
     * @return goods_name - 产品名称
     */
    public String getGoodsName() {
        return goodsName;
    }

    /**
     * 设置产品名称
     *
     * @param goodsName 产品名称
     */
    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName == null ? null : goodsName.trim();
    }

    /**
     * 获取单位编码
     *
     * @return goods_unit_id - 单位编码
     */
    public String getGoodsUnitId() {
        return goodsUnitId;
    }

    /**
     * 设置单位编码
     *
     * @param goodsUnitId 单位编码
     */
    public void setGoodsUnitId(String goodsUnitId) {
        this.goodsUnitId = goodsUnitId == null ? null : goodsUnitId.trim();
    }

    /**
     * 获取单位名称
     *
     * @return goods_unit_name - 单位名称
     */
    public String getGoodsUnitName() {
        return goodsUnitName;
    }

    /**
     * 设置单位名称
     *
     * @param goodsUnitName 单位名称
     */
    public void setGoodsUnitName(String goodsUnitName) {
        this.goodsUnitName = goodsUnitName == null ? null : goodsUnitName.trim();
    }

    /**
     * 获取规格型号
     *
     * @return goods_model - 规格型号
     */
    public String getGoodsModel() {
        return goodsModel;
    }

    /**
     * 设置规格型号
     *
     * @param goodsModel 规格型号
     */
    public void setGoodsModel(String goodsModel) {
        this.goodsModel = goodsModel == null ? null : goodsModel.trim();
    }

    /**
     * 获取期初库存
     *
     * @return stock_amount - 期初库存
     */
    public BigDecimal getStockAmount() {
        return stockAmount;
    }

    /**
     * 设置期初库存
     *
     * @param stockAmount 期初库存
     */
    public void setStockAmount(BigDecimal stockAmount) {
        this.stockAmount = stockAmount;
    }

    /**
     * 获取单位价格
     *
     * @return unit_price - 单位价格
     */
    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    /**
     * 设置单位价格
     *
     * @param unitPrice 单位价格
     */
    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    /**
     * 获取含税单价
     *
     * @return unit_price_rate - 含税单价
     */
    public BigDecimal getUnitPriceRate() {
        return unitPriceRate;
    }

    /**
     * 设置含税单价
     *
     * @param unitPriceRate 含税单价
     */
    public void setUnitPriceRate(BigDecimal unitPriceRate) {
        this.unitPriceRate = unitPriceRate;
    }

    /**
     * 获取总价
     *
     * @return total_price - 总价
     */
    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    /**
     * 设置总价
     *
     * @param totalPrice 总价
     */
    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    /**
     * 获取含税总价
     *
     * @return total_price_rate - 含税总价
     */
    public BigDecimal getTotalPriceRate() {
        return totalPriceRate;
    }

    /**
     * 设置含税总价
     *
     * @param totalPriceRate 含税总价
     */
    public void setTotalPriceRate(BigDecimal totalPriceRate) {
        this.totalPriceRate = totalPriceRate;
    }

    /**
     * 获取税金总额
     *
     * @return rate_price - 税金总额
     */
    public BigDecimal getRatePrice() {
        return ratePrice;
    }

    /**
     * 设置税金总额
     *
     * @param ratePrice 税金总额
     */
    public void setRatePrice(BigDecimal ratePrice) {
        this.ratePrice = ratePrice;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    public String getAuditEmp() {
        return auditEmp;
    }

    public void setAuditEmp(String auditEmp) {
        this.auditEmp = auditEmp;
    }

    public String getAuditEmpId() {
        return auditEmpId;
    }

    public void setAuditEmpId(String auditEmpId) {
        this.auditEmpId = auditEmpId;
    }

}