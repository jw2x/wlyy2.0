package com.yihu.jw.restmodel.base.user;

import com.yihu.jw.restmodel.IntegerIdentityVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * VO - 用户角色
 * Created by progr1mmer on 2018/8/27.
 */
@ApiModel(value = "UserRoleVO", description = "用户角色")
public class UserRoleVO extends IntegerIdentityVO {

	@ApiModelProperty(value = "用户ID", example = "402303ee65634dfs0234sf9a324a0023")
	private String userId;
	@ApiModelProperty(value = "角色ID", example = "232303ee65634dfs0234sf9ad2wa00cd")
	private String roleId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}


}