package com.tudou.crm.stock.stockTrade.entity;

import com.tudou.system.platform.base.annotation.CreateFlag;
import com.tudou.system.platform.base.annotation.ModifyFlag;
import com.tudou.system.platform.base.annotation.NotEmpty;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "crm_stock_trade_log")
@CreateFlag
@ModifyFlag
public class StockTrade {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(generator = "UUID")
    private String id;

    /**
     * 交易类型 10库存期初20入库30出库
     */
    @Column(name = "trade_type")
    @NotEmpty
    private String tradeType;

    /**
     * 库存交易时间
     */
    @Column(name = "trade_time")
    @NotEmpty
    private Date tradeTime;

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
     * 来源单号
     */
    @Column(name = "form_order")
    @NotEmpty
    private String formOrder;

    /**
     * 交易数量
     */
    @Column(name = "trade_amount")
    @NotEmpty
    private BigDecimal tradeAmount;

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
     * 总金额
     */
    @Column(name = "total_price")
    @NotEmpty
    private BigDecimal totalPrice;

    /**
     * 含税总金额
     */
    @Column(name = "total_price_rate")
    @NotEmpty
    private BigDecimal totalPriceRate;

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
     * 获取交易类型 10库存期初20入库30出库
     *
     * @return trade_type - 交易类型 10库存期初20入库30出库
     */
    public String getTradeType() {
        return tradeType;
    }

    /**
     * 设置交易类型 10库存期初20入库30出库
     *
     * @param tradeType 交易类型 10库存期初20入库30出库
     */
    public void setTradeType(String tradeType) {
        this.tradeType = tradeType == null ? null : tradeType.trim();
    }

    /**
     * 获取库存交易时间
     *
     * @return trade_time - 库存交易时间
     */
    public Date getTradeTime() {
        return tradeTime;
    }

    /**
     * 设置库存交易时间
     *
     * @param tradeTime 库存交易时间
     */
    public void setTradeTime(Date tradeTime) {
        this.tradeTime = tradeTime;
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
     * 获取来源单号
     *
     * @return form_order - 来源单号
     */
    public String getFormOrder() {
        return formOrder;
    }

    /**
     * 设置来源单号
     *
     * @param formOrder 来源单号
     */
    public void setFormOrder(String formOrder) {
        this.formOrder = formOrder == null ? null : formOrder.trim();
    }

    /**
     * 获取交易数量
     *
     * @return trade_amount - 交易数量
     */
    public BigDecimal getTradeAmount() {
        return tradeAmount;
    }

    /**
     * 设置交易数量
     *
     * @param tradeAmount 交易数量
     */
    public void setTradeAmount(BigDecimal tradeAmount) {
        this.tradeAmount = tradeAmount;
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
     * 获取总金额
     *
     * @return total_price - 总金额
     */
    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    /**
     * 设置总金额
     *
     * @param totalPrice 总金额
     */
    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    /**
     * 获取含税总金额
     *
     * @return total_price_rate - 含税总金额
     */
    public BigDecimal getTotalPriceRate() {
        return totalPriceRate;
    }

    /**
     * 设置含税总金额
     *
     * @param totalPriceRate 含税总金额
     */
    public void setTotalPriceRate(BigDecimal totalPriceRate) {
        this.totalPriceRate = totalPriceRate;
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
}