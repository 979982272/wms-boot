package com.tudou.base.goods.entity;


import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "eidp_goods_archive")
public class Goods {
    /**
     * 产品编码
     */
    @Id
    private String id;

    /**
     * 产品类型
     */
    @Column(name = "goods_type_id")
    private String goodsType;

    /**
     * 产品类型名称
     */
    @Column(name = "goods_type_name")
    private String goodsTypeName;

    /**
     * 产品单位
     */
    @Column(name = "goods_unit_id")
    private String goodsUnit;

    /**
     * 产品单位名称
     */
    @Column(name = "goods_unit_name")
    private String goodsUnitName;

    /**
     * 品牌
     */
    @Column(name = "goods_brand_id")
    private String goodsBrand;

    /**
     * 品牌名称
     */
    @Column(name = "goods_brand_name")
    private String goodsBrandName;

    /**
     * 产品名称
     */
    @Column(name = "goods_name")
    private String goodsName;

    /**
     * 规格型号
     */
    @Column(name = "goods_model")
    private String goodsModel;

    /**
     * 条形码
     */
    @Column(name = "goods_barcode")
    private String goodsBarcode;

    /**
     * sap物料编码
     */
    @Column(name = "sap_code")
    private String sapCode;

    /**
     * 标准价格
     */
    @Column(name = "normal_price")
    private BigDecimal normalPrice;

    /**
     * 税率
     */
    private BigDecimal rate;

    /**
     * 采购参考价
     */
    @Column(name = "purchase_price")
    private BigDecimal purchasePrice;

    /**
     * 是否删除0：正常,1删除
     */
    @Column(name = "server_flag")
    private int serverFlag;

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
     * 获取产品编码
     *
     * @return id - 产品编码
     */
    public String getId() {
        return id;
    }

    /**
     * 设置产品编码
     *
     * @param id 产品编码
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 获取产品类型
     *
     * @return goods_type - 产品类型
     */
    public String getGoodsType() {
        return goodsType;
    }

    /**
     * 设置产品类型
     *
     * @param goodsType 产品类型
     */
    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType == null ? null : goodsType.trim();
    }

    /**
     * 获取产品单位
     *
     * @return goods_unit - 产品单位
     */
    public String getGoodsUnit() {
        return goodsUnit;
    }

    /**
     * 设置产品单位
     *
     * @param goodsUnit 产品单位
     */
    public void setGoodsUnit(String goodsUnit) {
        this.goodsUnit = goodsUnit == null ? null : goodsUnit.trim();
    }

    /**
     * 获取品牌
     *
     * @return goods_brand - 品牌
     */
    public String getGoodsBrand() {
        return goodsBrand;
    }

    /**
     * 设置品牌
     *
     * @param goodsBrand 品牌
     */
    public void setGoodsBrand(String goodsBrand) {
        this.goodsBrand = goodsBrand == null ? null : goodsBrand.trim();
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
     * 获取条形码
     *
     * @return goods_barcode - 条形码
     */
    public String getGoodsBarcode() {
        return goodsBarcode;
    }

    /**
     * 设置条形码
     *
     * @param goodsBarcode 条形码
     */
    public void setGoodsBarcode(String goodsBarcode) {
        this.goodsBarcode = goodsBarcode == null ? null : goodsBarcode.trim();
    }

    /**
     * 获取sap物料编码
     *
     * @return sap_code - sap物料编码
     */
    public String getSapCode() {
        return sapCode;
    }

    /**
     * 设置sap物料编码
     *
     * @param sapCode sap物料编码
     */
    public void setSapCode(String sapCode) {
        this.sapCode = sapCode == null ? null : sapCode.trim();
    }

    /**
     * 获取标准价格
     *
     * @return normal_price - 标准价格
     */
    public BigDecimal getNormalPrice() {
        return normalPrice;
    }

    /**
     * 设置标准价格
     *
     * @param normalPrice 标准价格
     */
    public void setNormalPrice(BigDecimal normalPrice) {
        this.normalPrice = normalPrice;
    }

    /**
     * 获取税率
     *
     * @return rate - 税率
     */
    public BigDecimal getRate() {
        return rate;
    }

    /**
     * 设置税率
     *
     * @param rate 税率
     */
    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    /**
     * 获取采购参考价
     *
     * @return purchase_price - 采购参考价
     */
    public BigDecimal getPurchasePrice() {
        return purchasePrice;
    }

    /**
     * 设置采购参考价
     *
     * @param purchasePrice 采购参考价
     */
    public void setPurchasePrice(BigDecimal purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public int getServerFlag() {
        return serverFlag;
    }

    public void setServerFlag(int serverFlag) {
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

    public String getGoodsTypeName() {
        return goodsTypeName;
    }

    public void setGoodsTypeName(String goodsTypeName) {
        this.goodsTypeName = goodsTypeName;
    }

    public String getGoodsUnitName() {
        return goodsUnitName;
    }

    public void setGoodsUnitName(String goodsUnitName) {
        this.goodsUnitName = goodsUnitName;
    }

    public String getGoodsBrandName() {
        return goodsBrandName;
    }

    public void setGoodsBrandName(String goodsBrandName) {
        this.goodsBrandName = goodsBrandName;
    }
}