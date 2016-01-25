package zx.soft.cases.management.domain;

import zx.soft.cases.management.utils.JsonUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CasesData {

	private String rowkey;
	private String casesId;
	private long createTime;//创建时间

	private String fileId;
	private int isFile;
	private String fileName;
	private String fileType;
	private double fileSize;
	private String fileLocation;
	private String fileDetail;
	private String agreementType;
	private long trainmitTime;
	private String sourceIp;
	private String sourceOS;
	private String sourceBrowserType;
	private int sourcePort;
	private String sourceDetail;
	private String targetIp;
	private String targetOS;
	private String targetBrowserType;
	private int targetPort;
	private String targetDetail;
	private long timestamp;
	private boolean malware;//是否为有害信息
	private int seversity;
	private int serversityLevel;//危害等级
	private int attackType;//攻击类型,1:web攻击 2:APT攻击
	//攻击类型详解,
	//1:web攻击——101:SQL注入 102:XSS蠕虫 103:欺骗钓鱼 104:网站挂马 105:身份盗用 106:盗取网站用户信息 107:垃圾信息发送 108:劫持用户web行为
	//2:APT攻击——201:ODay漏洞 202:特种木马 203:组合攻击 204:敏感流量 205:内部攻击 206:僵尸网络
	private int attackTypeDetail;
	private String attackDetail;//攻击详细信息

	public CasesData() {
	}

	@JsonProperty
	public long getCreateTime() {
		return createTime;
	}

	@JsonIgnore
	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	@JsonProperty
	public String getRowkey() {
		return rowkey;
	}

	@JsonProperty
	public String getCasesId() {
		return casesId;
	}

	@JsonProperty
	public String getFileId() {
		return fileId;
	}

	@JsonProperty
	public int getIsFile() {
		return isFile;
	}

	@JsonProperty
	public String getFileName() {
		return fileName;
	}

	@JsonProperty
	public String getFileType() {
		return fileType;
	}

	@JsonProperty
	public double getFileSize() {
		return fileSize;
	}

	@JsonProperty
	public String getFileLocation() {
		return fileLocation;
	}

	@JsonProperty
	public String getFileDetail() {
		return fileDetail;
	}

	@JsonProperty
	public String getAgreementType() {
		return agreementType;
	}

	@JsonProperty
	public long getTrainmitTime() {
		return trainmitTime;
	}

	@JsonProperty
	public String getSourceIp() {
		return sourceIp;
	}

	@JsonProperty
	public String getSourceOS() {
		return sourceOS;
	}

	@JsonProperty
	public String getSourceBrowserType() {
		return sourceBrowserType;
	}

	@JsonProperty
	public int getSourcePort() {
		return sourcePort;
	}

	@JsonProperty
	public String getSourceDetail() {
		return sourceDetail;
	}

	@JsonProperty
	public String getTargetIp() {
		return targetIp;
	}

	@JsonProperty
	public String getTargetOS() {
		return targetOS;
	}

	@JsonProperty
	public String getTargetBrowserType() {
		return targetBrowserType;
	}

	@JsonProperty
	public int getTargetPort() {
		return targetPort;
	}

	@JsonProperty
	public String getTargetDetail() {
		return targetDetail;
	}

	@JsonProperty
	public long getTimestamp() {
		return timestamp;
	}

	@JsonProperty
	public boolean isMalware() {
		return malware;
	}

	@JsonProperty
	public int getSeversity() {
		return seversity;
	}

	@JsonProperty
	public int getServersityLevel() {
		return serversityLevel;
	}

	@JsonProperty
	public int getAttackType() {
		return attackType;
	}

	@JsonProperty
	public int getAttackTypeDetail() {
		return attackTypeDetail;
	}

	@JsonProperty
	public String getAttackDetail() {
		return attackDetail;
	}

	@JsonIgnore
	public void setRowkey(String rowkey) {
		this.rowkey = rowkey;
	}

	@JsonIgnore
	public void setTypeId(String casesId) {
		this.casesId = casesId;
	}

	@JsonIgnore
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	@JsonIgnore
	public void setIsFile(int isFile) {
		this.isFile = isFile;
	}

	@JsonIgnore
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@JsonIgnore
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	@JsonIgnore
	public void setFileSize(double fileSize) {
		this.fileSize = fileSize;
	}

	@JsonIgnore
	public void setFileLocation(String fileLocation) {
		this.fileLocation = fileLocation;
	}

	@JsonIgnore
	public void setFileDetail(String fileDetail) {
		this.fileDetail = fileDetail;
	}

	@JsonIgnore
	public void setAgreementType(String agreementType) {
		this.agreementType = agreementType;
	}

	@JsonIgnore
	public void setTrainmitTime(long trainmitTime) {
		this.trainmitTime = trainmitTime;
	}

	@JsonIgnore
	public void setSourceIp(String sourceIp) {
		this.sourceIp = sourceIp;
	}

	@JsonIgnore
	public void setSourceOS(String sourceOS) {
		this.sourceOS = sourceOS;
	}

	@JsonIgnore
	public void setSourceBrowserType(String sourceBrowserType) {
		this.sourceBrowserType = sourceBrowserType;
	}

	@JsonIgnore
	public void setSourcePort(int sourcePort) {
		this.sourcePort = sourcePort;
	}

	@JsonIgnore
	public void setSourceDetail(String sourceDetail) {
		this.sourceDetail = sourceDetail;
	}

	@JsonIgnore
	public void setTargetIp(String targetIp) {
		this.targetIp = targetIp;
	}

	@JsonIgnore
	public void setTargetOS(String targetOS) {
		this.targetOS = targetOS;
	}

	@JsonIgnore
	public void setTargetBrowserType(String targetBrowserType) {
		this.targetBrowserType = targetBrowserType;
	}

	@JsonIgnore
	public void setTargetPort(int targetPort) {
		this.targetPort = targetPort;
	}

	@JsonIgnore
	public void setTargetDetail(String targetDetail) {
		this.targetDetail = targetDetail;
	}

	@JsonIgnore
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	@JsonIgnore
	public void setMalware(boolean malware) {
		this.malware = malware;
	}

	@JsonIgnore
	public void setSeversity(int seversity) {
		this.seversity = seversity;
	}

	@JsonIgnore
	public void setServersityLevel(int serversityLevel) {
		this.serversityLevel = serversityLevel;
	}

	@JsonIgnore
	public void setAttackType(int attackType) {
		this.attackType = attackType;
	}

	@JsonIgnore
	public void setAttackTypeDetail(int attackTypeDetail) {
		this.attackTypeDetail = attackTypeDetail;
	}

	@JsonIgnore
	public void setAttackDetail(String attackDetail) {
		this.attackDetail = attackDetail;
	}

	public CasesData(CasesDataBuilder casesBuilder) {
		this.createTime = casesBuilder.createTime;
		this.rowkey = casesBuilder.rowkey;
		this.casesId = casesBuilder.casesId;
		this.fileId = casesBuilder.fileId;
		this.isFile = casesBuilder.isFile;
		this.fileName = casesBuilder.fileName;
		this.fileType = casesBuilder.fileType;
		this.fileSize = casesBuilder.fileSize;
		this.fileLocation = casesBuilder.fileLocation;
		this.fileDetail = casesBuilder.fileDetail;
		this.agreementType = casesBuilder.agreementType;
		this.trainmitTime = casesBuilder.trainmitTime;
		this.sourceIp = casesBuilder.sourceIp;
		this.sourceOS = casesBuilder.sourceOS;
		this.sourceBrowserType = casesBuilder.sourceBrowserType;
		this.sourcePort = casesBuilder.sourcePort;
		this.sourceDetail = casesBuilder.sourceDetail;
		this.targetIp = casesBuilder.targetIp;
		this.targetOS = casesBuilder.targetOS;
		this.targetBrowserType = casesBuilder.targetBrowserType;
		this.targetPort = casesBuilder.targetPort;
		this.targetDetail = casesBuilder.targetDetail;
		this.timestamp = casesBuilder.timestamp;
		this.malware = casesBuilder.malware;//是否为有害信息
		this.seversity = casesBuilder.seversity;
		this.serversityLevel = casesBuilder.serversityLevel;//危害等级
		this.attackType = casesBuilder.attackType;//攻击类型,1:web攻击 2:APT攻击
		this.attackTypeDetail = casesBuilder.attackTypeDetail;
		this.attackDetail = casesBuilder.attackDetail;//攻击详细信息
	}

	public static class CasesDataBuilder {
		private String rowkey;
		private String casesId;
		private long createTime;
		private String fileId = "";
		private int isFile;
		private String fileName = "";
		private String fileType = "";
		private double fileSize;
		private String fileLocation = "";
		private String fileDetail = "";
		private String agreementType = "";
		private long trainmitTime;
		private String sourceIp = "";
		private String sourceOS = "";
		private String sourceBrowserType = "";
		private int sourcePort;
		private String sourceDetail = "";
		private String targetIp = "";
		private String targetOS = "";
		private String targetBrowserType = "";
		private int targetPort;
		private String targetDetail = "";
		private long timestamp;
		private boolean malware;
		private int seversity;
		private int serversityLevel;
		private int attackType;
		private int attackTypeDetail;
		private String attackDetail = "";

		public CasesDataBuilder(String rowkey, String casesId) {
			this.rowkey = rowkey;
			this.casesId = casesId;
		}

		public CasesDataBuilder addFileId(String fileId) {
			this.fileId = fileId;
			return this;
		}

		public CasesDataBuilder addCreateTime(long createTime) {
			this.createTime = createTime;
			return this;
		}

		public CasesDataBuilder addIsFile(int isFile) {
			this.isFile = isFile;
			return this;
		}

		public CasesDataBuilder addFileName(String fileName) {
			this.fileName = fileName;
			return this;
		}

		public CasesDataBuilder addFileType(String fileType) {
			this.fileType = fileType;
			return this;
		}

		public CasesDataBuilder addFileSize(double fileSize) {
			this.fileSize = fileSize;
			return this;
		}

		public CasesDataBuilder addFileLocation(String fileLocation) {
			this.fileLocation = fileLocation;
			return this;
		}

		public CasesDataBuilder addFileDetail(String fileDetail) {
			this.fileDetail = fileDetail;
			return this;
		}

		public CasesDataBuilder addAgreementType(String agreementType) {
			this.agreementType = agreementType;
			return this;
		}

		public CasesDataBuilder addTrainmitTime(long trainmitTime) {
			this.trainmitTime = trainmitTime;
			return this;
		}

		public CasesDataBuilder addSourceIp(String sourceIp) {
			this.sourceIp = sourceIp;
			return this;
		}

		public CasesDataBuilder addSourceOS(String sourceOS) {
			this.sourceOS = sourceOS;
			return this;
		}

		public CasesDataBuilder addSourceBrowserType(String sourceBrowserType) {
			this.sourceBrowserType = sourceBrowserType;
			return this;
		}

		public CasesDataBuilder addSourcePort(int sourcePort) {
			this.sourcePort = sourcePort;
			return this;
		}

		public CasesDataBuilder addSourceDetail(String sourceDetail) {
			this.sourceDetail = sourceDetail;
			return this;
		}

		public CasesDataBuilder addTargetIp(String targetIp) {
			this.targetIp = targetIp;
			return this;
		}

		public CasesDataBuilder addTargetOS(String targetOS) {
			this.targetOS = targetOS;
			return this;
		}

		public CasesDataBuilder addTargetBrowserType(String targetBrowserType) {
			this.targetBrowserType = targetBrowserType;
			return this;
		}

		public CasesDataBuilder addTargetPort(int targetPort) {
			this.targetPort = targetPort;
			return this;
		}

		public CasesDataBuilder addTargetDetail(String targetDetail) {
			this.targetDetail = targetDetail;
			return this;
		}

		public CasesDataBuilder addTimestamp(long timestamp) {
			this.timestamp = timestamp;
			return this;
		}

		public CasesDataBuilder addMalware(boolean malware) {
			this.malware = malware;
			return this;
		}

		public CasesDataBuilder addSeversitye(int seversity) {
			this.seversity = seversity;
			return this;
		}

		public CasesDataBuilder addServersityLevel(int serversityLevel) {
			this.serversityLevel = serversityLevel;
			return this;
		}

		public CasesDataBuilder addAttackType(int attackType) {
			this.attackType = attackType;
			return this;
		}

		public CasesDataBuilder addAttackTypeDetail(int attackTypeDetail) {
			this.attackTypeDetail = attackTypeDetail;
			return this;
		}

		public CasesDataBuilder addAttackDetail(String attackDetail) {
			this.attackDetail = attackDetail;
			return this;
		}

		public CasesData build() {
			return new CasesData(this);
		}

		public static void main(String[] args) {
			CasesDataBuilder bu = new CasesDataBuilder("00000002" + String.valueOf(System.currentTimeMillis()),
					"00000002");
			System.out.println(JsonUtils.toJsonWithoutPretty(bu.build()));
		}
	}

}
