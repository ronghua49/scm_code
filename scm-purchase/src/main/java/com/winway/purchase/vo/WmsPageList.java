package com.winway.purchase.vo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Restful接口返回的分页列表数据
 * @author gsh456
 */
@ApiModel(description="任务查询参数对象")
public class WmsPageList<E>{
	@ApiModelProperty(name="rows",notes="结果列表")
	List<E> rows;
	@ApiModelProperty(name="total",notes="总记录数")
	long total;
	@ApiModelProperty(name="page",notes="当前页码")
	int page;
	@ApiModelProperty(name="pageSize",notes="每页条数")
	int pageSize;
	@ApiModelProperty(name="map",notes="统计查询")
	Map<String,Object> map = new HashMap<>();
	
	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

	public WmsPageList(){
	}
	
	public List<E> getRows() {
		return rows;
	}
	public void setRows(List<E> rows) {
		this.rows = rows;
	}
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
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
}
