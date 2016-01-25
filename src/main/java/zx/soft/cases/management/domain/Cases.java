package zx.soft.cases.management.domain;

import zx.soft.cases.management.utils.JsonUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Cases {

	private String casesId;//案件唯一标识
	private String typeId;//案件类型id号
	private String casesName;//案件名称和案件标识对应
	private String casesDetail;//案件描述
	private long createTime;//创建时间
	private String createUser;//创建人

	public Cases() {
	}

	public Cases(CasesBuilder casesBuilder) {
		this.casesId = casesBuilder.casesId;
		this.typeId = casesBuilder.typeId;
		this.casesName = casesBuilder.casesName;
		this.casesDetail = casesBuilder.casesDetail;
		this.createTime = casesBuilder.createTime;
		this.createUser = casesBuilder.createUser;

	}

	@JsonProperty
	public String getCasesId() {
		return casesId;
	}

	@JsonProperty
	public String getTypeId() {
		return typeId;
	}

	@JsonProperty
	public String getCasesName() {
		return casesName;
	}

	@JsonProperty
	public String getCasesDetail() {
		return casesDetail;
	}

	@JsonProperty
	public long getCreateTime() {
		return createTime;
	}

	@JsonProperty
	public String getCreateUser() {
		return createUser;
	}

	@JsonIgnore
	public void setCasesId(String casesId) {
		this.casesId = casesId;
	}

	@JsonIgnore
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	@JsonIgnore
	public void setCasesName(String casesName) {
		this.casesName = casesName;
	}

	@JsonIgnore
	public void setCasesDetail(String casesDetail) {
		this.casesDetail = casesDetail;
	}

	@JsonIgnore
	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	@JsonIgnore
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public static class CasesBuilder {
		private String casesId;
		private String typeId;
		private String casesName;

		private String casesDetail = "";
		private long createTime;
		private String createUser = "";

		public CasesBuilder(String casesId, String typeId, String casesName) {
			this.casesId = casesId;
			this.typeId = typeId;
			this.casesName = casesName;
		}

		public CasesBuilder addCasesDetail(String casesDetail) {
			this.casesDetail = casesDetail;
			return this;
		}

		public CasesBuilder addCreateTime(long createTime) {
			this.createTime = createTime;
			return this;
		}

		public CasesBuilder addCreateUser(String createUser) {
			this.createUser = createUser;
			return this;
		}

		public Cases build() {
			return new Cases(this);
		}
	}

	public static void main(String[] args) {
		CasesBuilder cb = new CasesBuilder("00000002" + String.valueOf(System.currentTimeMillis()), "00000002", "案件名称")
		.addCasesDetail("案件细节信息").addCreateTime(System.currentTimeMillis()).addCreateUser("案件创建人");
		Cases ca = cb.build();
		System.out.println(JsonUtils.toJsonWithoutPretty(ca));
	}
}
