package com.yihu.jw.entity.base.module;

import com.yihu.jw.entity.UuidIdentityEntityWithOperator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

/**
 * Entity - 模块
 * Created by progr1mmer on 2018/8/14.
 */
@Entity
@Table(name = "base_module")
public class ModuleDO extends UuidIdentityEntityWithOperator {


	public enum Status {
		unAvailable("不可用", 0),
		available("可用", 1);
		private String name;
		private Integer value;

		Status(String name, Integer value) {
			this.name = name;
			this.value = value;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Integer getValue() {
			return value;
		}

		public void setValue(Integer value) {
			this.value = value;
		}
	}

	public enum Type {
		common("通用", 0),
		doctor("医生端", 1),
		patient("居民端", 2);
		private String name;
		private Integer value;

		Type(String name, Integer value) {
			this.name = name;
			this.value = value;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Integer getValue() {
			return value;
		}

		public void setValue(Integer value) {
			this.value = value;
		}
	}

	public enum Must {
		nonMust("非必选", 0),
		must("必选", 1);
		private String name;
		private Integer value;

		Must(String name, Integer value) {
			this.name = name;
			this.value = value;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Integer getValue() {
			return value;
		}

		public void setValue(Integer value) {
			this.value = value;
		}
	}

    public enum End {
        have("有子节点", 0),
        no("没有子节点", 1);
        private String name;
        private Integer value;

        End(String name, Integer value) {
            this.name = name;
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getValue() {
            return value;
        }

        public void setValue(Integer value) {
            this.value = value;
        }
    }

	//模块名称
	private String name;
	//模块连接
	private String url;
	//父id
	private String parentId;
	//状态
	private Integer status;
	//类型
	private Integer type;
	//备注
	private String remark;
	//0-表示有子节点，1-表示没有子节点
	private Integer isEnd ;
	//0-表示非必选，1-表示必选
	private Integer isMust ;
	//逻辑删除标志1正常，0删除
	private Integer del ;
	//子集
	private List<ModuleDO> children = new ArrayList<>();

	// Constructors

    @Column(name = "name")
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

	@Column(name = "parent_id")
	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

    @Column(name = "status")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

	@Column(name = "remark")
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "url")
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "type")
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Column(name = "is_end")
	public Integer getIsEnd() {
		return isEnd;
	}

	public void setIsEnd(Integer isEnd) {
		this.isEnd = isEnd;
	}

	@Column(name = "del")
	public Integer getDel() {
		return del;
	}

	public void setDel(Integer del) {
		this.del = del;
	}

	@Column(name = "is_must")
	public Integer getIsMust() {
		return isMust;
	}

	public void setIsMust(Integer isMust) {
		this.isMust = isMust;
	}

	@Transient
	public List<ModuleDO> getChildren() {
		return children;
	}

	public void setChildren(List<ModuleDO> children) {
		this.children = children;
	}
}