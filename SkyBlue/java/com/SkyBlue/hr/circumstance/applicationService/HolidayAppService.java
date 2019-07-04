package com.SkyBlue.hr.circumstance.applicationService;

import java.util.List;
import java.util.Map;

import com.SkyBlue.hr.circumstance.to.HolidayBean;

public interface HolidayAppService {
	public List<HolidayBean> findHolidayList(Map<String, Object> dateList);
	public void batchHoliday(List<HolidayBean> holidayList);
}
