package com.SkyBlue.base.to;

import com.SkyBlue.common.annotation.Dataset;
import com.SkyBlue.common.to.BaseBean;

import lombok.Getter;
import lombok.Setter;

@Dataset(name="dsOpenApi")
public class OpenApiBean extends BaseBean{
	@Setter
	@Getter
	private String  sidonm,co,no,so,tsp,pm,voc,pm2,nh3;
}
