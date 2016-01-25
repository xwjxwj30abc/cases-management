package zx.soft.cases.management.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CasesType {

	private String typeId;//案件类型id号
	private String name;//案件类型名称
	private String createUser;//案件类型创建者
	private long createTime;//案件类型创建时间

	public CasesType() {
	}

	/*@JsonCreator
	public CasesType(@JsonProperty("id") String id, @JsonProperty("name") String name, @JsonProperty("createUser") String createUser,
			@JsonProperty("createTime") long createTime) {
		this.id = id;
		this.name = name;
		this.createUser = createUser;
		this.createTime = createTime;
	}*/

	private CasesType(CasesTypeBuilder builder) {
		this.typeId = builder.typeId;
		this.name = builder.name;
		this.createUser = builder.createUser;
		this.createTime = builder.createTime;
	}

	@JsonIgnore
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	@JsonIgnore
	public void setName(String name) {
		this.name = name;
	}

	@JsonIgnore
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	@JsonIgnore
	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	@JsonProperty
	public String getTypeId() {
		return typeId;
	}

	@JsonProperty
	public String getName() {
		return name;
	}

	@JsonProperty
	public String getCreateUser() {
		return createUser;
	}

	@JsonProperty
	public long getCreateTime() {
		return createTime;
	}

	public static class CasesTypeBuilder {
		private String typeId;
		private String name;
		private String createUser = "";
		private long createTime;

		public CasesTypeBuilder(String typeId, String name) {
			this.typeId = typeId;
			this.name = name;
		}

		public CasesTypeBuilder addCreateUser(String createUser) {
			this.createUser = createUser;
			return this;
		}

		public CasesTypeBuilder addCreateTime(long createTime) {
			this.createTime = createTime;
			return this;
		}

		public CasesType build() {
			return new CasesType(this);
		}
	}
}
