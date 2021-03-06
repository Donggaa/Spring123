package com.SkyBlue.hr.circumstance.applicationService;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.SkyBlue.hr.circumstance.dao.HolidayDAO;
import com.SkyBlue.hr.circumstance.to.HolidayBean;

@Component
@Transactional
public class HolidayAppServiceImpl implements HolidayAppService {
	@Autowired
    private HolidayDAO holidayDAO;
	

	@Override
	/* 휴일목록을 가지고오는 메서드 */
	public List<HolidayBean> findHolidayList(Map<String, Object> dateList) {
		return holidayDAO.selectHolidayList(dateList);
	}

	@Override
	// 휴일관련 처리를 하는 메서드 
	public void batchHoliday(List<HolidayBean> holidayList) {
		for(HolidayBean holidayBean:holidayList){
			switch(holidayBean.getStatus()){
				case "insert" : holidayDAO.insertHoliday(holidayBean); break;
				
				case "delete" : holidayDAO.deleteHoliday(holidayBean); break;
				
				//case "update" : holidayDAO.updateHoliday(holidayBean); break;
			}
		}
	}

}
