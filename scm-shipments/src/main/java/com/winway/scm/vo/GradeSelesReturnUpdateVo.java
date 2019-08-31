package com.winway.scm.vo;

import com.winway.scm.model.ScmFhGradeSelesReturn;
import com.winway.scm.model.ScmFhShipmentsDatailMaster;

import java.util.List;

/**
 * 当此退货修改页面数据
 */
public class GradeSelesReturnUpdateVo {
    private List<ScmFhShipmentsDatailMaster> shipmentsDatailMasterList;

    private List<ScmFhGradeSelesReturn> fhGradeSelesReturnList;


    public List<ScmFhShipmentsDatailMaster> getShipmentsDatailMasterList() {
        return shipmentsDatailMasterList;
    }

    public void setShipmentsDatailMasterList(List<ScmFhShipmentsDatailMaster> shipmentsDatailMasterList) {
        this.shipmentsDatailMasterList = shipmentsDatailMasterList;
    }

    public List<ScmFhGradeSelesReturn> getFhGradeSelesReturnList() {
        return fhGradeSelesReturnList;
    }

    public void setFhGradeSelesReturnList(List<ScmFhGradeSelesReturn> fhGradeSelesReturnList) {
        this.fhGradeSelesReturnList = fhGradeSelesReturnList;
    }
}
