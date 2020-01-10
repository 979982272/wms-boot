package com.tudou.system.development.entity;

/**
 * 数据库列信息
 *
 * @author weihua
 * @create 2017-04-23 15:46
 */
public class Development {
    // 列名称
    private String columnName;
    // 列注释
    private String columnComment;
    // 列类型
    private String dataType;
    // 列键
    private String columnKey;
    // 排序
    private int sort;
    // 宽度
    private int width;
    // 模板
    private String template;
    // 是否显示提示是@Y，否@N
    private String toolTip;
    // 是否必填，是@Y，否@N
    private String required;

    // 数据源地址
    private String dataSource;
    // 改变事件
    private String changeEvent;
    // 只读 是@Y，否@N
    private String readOnly;

    public String getReadOnly() {
        return readOnly;
    }

    public void setReadOnly(String readOnly) {
        this.readOnly = readOnly;
    }

    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    public String getChangeEvent() {
        return changeEvent;
    }

    public void setChangeEvent(String changeEvent) {
        this.changeEvent = changeEvent;
    }

    public String getRequired() {
        return required;
    }

    public void setRequired(String required) {
        this.required = required;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnComment() {
        return columnComment;
    }

    public void setColumnComment(String columnComment) {
        this.columnComment = columnComment;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getColumnKey() {
        return columnKey;
    }

    public void setColumnKey(String columnKey) {
        this.columnKey = columnKey;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String getToolTip() {
        return toolTip;
    }

    public void setToolTip(String toolTip) {
        this.toolTip = toolTip;
    }
}
