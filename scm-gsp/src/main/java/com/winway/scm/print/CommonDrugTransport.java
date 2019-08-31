package com.winway.scm.print;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.winway.purchase.print.jasperreport.PrintBase;
import com.winway.purchase.print.jasperreport.PrintObject;
import com.winway.purchase.util.MoneyStringUtil;
import com.winway.purchase.util.ScmPuthWmsUtil;
import com.winway.purchase.vo.ScmWmsReturnData;
import com.winway.scm.vo.ScmGspDrugtransportVo;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("CommonDrugTransport")
public class CommonDrugTransport extends PrintBase {
	@Override
	public List<PrintObject> getPrintData(Map<String, Object> map) {

		String s = new JSONObject(map).toJSONString();
		ScmGspDrugtransportVo scmGspDrugtransportVo = JSON.parseObject(s, ScmGspDrugtransportVo.class);
		ScmWmsReturnData datPputh = ScmPuthWmsUtil.getDatPputh(JSONObject.toJSONString(scmGspDrugtransportVo), "/prolog_aomei_interface/interface/transport/records/scmTransportRecordsQuery.json");
		if(datPputh==null) {
			throw new RuntimeException("WMS返回数据异常");
		}
		List<Map> listMap=new ArrayList<>();
		for (String  text : datPputh.getData()){
			ScmGspDrugtransportVo scmGspDrugtransportVo1 = JSON.parseObject(text, ScmGspDrugtransportVo.class);
			BeanMap beanMap2 = BeanMap.create(scmGspDrugtransportVo1);
			HashMap<Object, Object> map2 = new HashMap<>();
			for (Object key : beanMap2.keySet()) {
				map2.put(key,beanMap2.get(key));
			}
			listMap.add(map2);
		}
		if(listMap.isEmpty()){
			listMap = null;
		}
		Map parameters1 = new HashMap(10);

		List<PrintObject> exports = new ArrayList<PrintObject>();
		PrintObject printObject1 = new PrintObject();
		parameters1.put("itemtype",scmGspDrugtransportVo.getItemtype());
		printObject1.setPrintMbname("CommonDrugTransport");
		printObject1.setParameters(parameters1);
		printObject1.setDatasource(listMap);
		exports.add(printObject1);
		return exports;
	}
	@Override
	public boolean beforePrint(Map<String, Object> map) {
		return false;
	}
	@Override
	public void afterPrint(Map<String, Object> map) {

	}

}
