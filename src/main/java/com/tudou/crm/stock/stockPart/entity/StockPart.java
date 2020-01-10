package com.tudou.crm.stock.stockPart.entity;

import com.tudou.base.goods.entity.Goods;
import com.tudou.system.platform.base.annotation.CreateFlag;
import com.tudou.system.platform.base.annotation.ModifyFlag;
import com.tudou.system.platform.base.annotation.NotEmpty;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "crm_stock_part")
@CreateFlag
@ModifyFlag
public class StockPart {
    /**
     * 库存流水
     */
    @Id
    @GeneratedValue(generator = "UUID")
    private String id;

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
     * 总库存
     */
    @Column(name = "total_stock_amount")
    @NotEmpty
    private BigDecimal totalStockAmount;

    /**
     * 可用库存
     */
    @Column(name = "stock_amount")
    @NotEmpty
    private BigDecimal stockAmount;

    /**
     * 入库库存,废弃暂时不用
     */
    @Column(name = "in_stock_amount")
    @Deprecated
    private BigDecimal inStockAmount;

    /**
     * 出库库存，废弃暂时不用
     */
    @Column(name = "lock_stock_amount")
    private BigDecimal lockStockAmount;

    /**
     * 单位成本
     */
    @Column(name = "unit_cost_price")
    @NotEmpty
    private BigDecimal unitCostPrice;

    @Transient
    private Goods goods;

    /**
     * 含税单位成本
     */
    @Column(name = "unit_cost_price_rate")
    @NotEmpty
    private BigDecimal unitCostPriceRate;

    /**
     * 总成本
     */
    @Column(name = "total_cost_price")
    @NotEmpty
    private BigDecimal totalCostPrice;

    /**
     * 含税总成本
     */
    @Column(name = "total_cost_price_rate")
    @NotEmpty
    private BigDecimal totalCostPriceRate;

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

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(String warehouseId) {
        this.warehouseId = warehouseId;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsUnitId() {
        return goodsUnitId;
    }

    public void setGoodsUnitId(String goodsUnitId) {
        this.goodsUnitId = goodsUnitId;
    }

    public String getGoodsUnitName() {
        return goodsUnitName;
    }

    public void setGoodsUnitName(String goodsUnitName) {
        this.goodsUnitName = goodsUnitName;
    }

    public String getGoodsModel() {
        return goodsModel;
    }

    public void setGoodsModel(String goodsModel) {
        this.goodsModel = goodsModel;
    }

    public BigDecimal getTotalStockAmount() {
        return totalStockAmount;
    }

    public void setTotalStockAmount(BigDecimal totalStockAmount) {
        this.totalStockAmount = totalStockAmount;
    }

    public BigDecimal getStockAmount() {
        return stockAmount;
    }

    public void setStockAmount(BigDecimal stockAmount) {
        this.stockAmount = stockAmount;
    }

    public BigDecimal getInStockAmount() {
        return inStockAmount;
    }

    public void setInStockAmount(BigDecimal inStockAmount) {
        this.inStockAmount = inStockAmount;
    }

    public BigDecimal getLockStockAmount() {
        return lockStockAmount;
    }

    public void setLockStockAmount(BigDecimal lockStockAmount) {
        this.lockStockAmount = lockStockAmount;
    }

    public BigDecimal getUnitCostPrice() {
        return unitCostPrice;
    }

    public void setUnitCostPrice(BigDecimal unitCostPrice) {
        this.unitCostPrice = unitCostPrice;
    }

    public BigDecimal getUnitCostPriceRate() {
        return unitCostPriceRate;
    }

    public void setUnitCostPriceRate(BigDecimal unitCostPriceRate) {
        this.unitCostPriceRate = unitCostPriceRate;
    }

    public BigDecimal getTotalCostPrice() {
        return totalCostPrice;
    }

    public void setTotalCostPrice(BigDecimal totalCostPrice) {
        this.totalCostPrice = totalCostPrice;
    }

    public BigDecimal getTotalCostPriceRate() {
        return totalCostPriceRate;
    }

    public void setTotalCostPriceRate(BigDecimal totalCostPriceRate) {
        this.totalCostPriceRate = totalCostPriceRate;
    }

    public Integer getServerFlag() {
        return serverFlag;
    }

    public void setServerFlag(Integer serverFlag) {
        this.serverFlag = serverFlag;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateEmp() {
        return createEmp;
    }

    public void setCreateEmp(String createEmp) {
        this.createEmp = createEmp;
    }

    public String getCreateEmpId() {
        return createEmpId;
    }

    public void setCreateEmpId(String createEmpId) {
        this.createEmpId = createEmpId;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getModifyEmp() {
        return modifyEmp;
    }

    public void setModifyEmp(String modifyEmp) {
        this.modifyEmp = modifyEmp;
    }

    public String getModifyEmpId() {
        return modifyEmpId;
    }

    public void setModifyEmpId(String modifyEmpId) {
        this.modifyEmpId = modifyEmpId;
    }

    public Date getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(Date deleteTime) {
        this.deleteTime = deleteTime;
    }

    public String getDeleteEmp() {
        return deleteEmp;
    }

    public void setDeleteEmp(String deleteEmp) {
        this.deleteEmp = deleteEmp;
    }

    public String getDeleteEmpId() {
        return deleteEmpId;
    }

    public void setDeleteEmpId(String deleteEmpId) {
        this.deleteEmpId = deleteEmpId;
    }
}