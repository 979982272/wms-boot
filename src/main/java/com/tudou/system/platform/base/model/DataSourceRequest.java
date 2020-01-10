package com.tudou.system.platform.base.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2017/2/6 0006.
 */
public class DataSourceRequest implements Serializable {
    private int page;
    private int pageSize;
    private int take;
    private int skip;
    private List<SortDescriptor> sort;
    private HashMap<String, Object> data = new HashMap();
    private DataSourceRequest.FilterDescriptor filter = new DataSourceRequest.FilterDescriptor();

    public List<SortDescriptor> getSort() {
        return sort;
    }

    public void setSort(List<SortDescriptor> sort) {
        this.sort = sort;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTake() {
        return take;
    }

    public void setTake(int take) {
        this.take = take;
    }

    public int getSkip() {
        return skip;
    }

    public void setSkip(int skip) {
        this.skip = skip;
    }

    public HashMap<String, Object> getData() {
        return data;
    }

    public void setData(HashMap<String, Object> data) {
        this.data = data;
    }

    public FilterDescriptor getFilter() {
        return filter;
    }

    public void setFilter(FilterDescriptor filter) {
        this.filter = filter;
    }

    public static class FilterDescriptor implements Serializable {
        private String logic;
        private List<FilterDescriptor> filters = new ArrayList();
        private String field;
        private Object value;
        private String operator;
        private boolean ignoreCase = true;

        public FilterDescriptor() {
        }

        public String getField() {
            return this.field;
        }

        public void setField(String field) {
            this.field = field;
        }

        public Object getValue() {
            return this.value;
        }

        public void setValue(Object value) {
            this.value = value;
        }

        public String getOperator() {
            return this.operator;
        }

        public void setOperator(String operator) {
            this.operator = operator;
        }

        public String getLogic() {
            return this.logic;
        }

        public void setLogic(String logic) {
            this.logic = logic;
        }

        public boolean isIgnoreCase() {
            return this.ignoreCase;
        }

        public void setIgnoreCase(boolean ignoreCase) {
            this.ignoreCase = ignoreCase;
        }

        public List<FilterDescriptor> getFilters() {
            return this.filters;
        }
    }
}


