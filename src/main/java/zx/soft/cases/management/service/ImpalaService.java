package zx.soft.cases.management.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import zx.soft.cases.management.domain.Cases;
import zx.soft.cases.management.domain.Cases.CasesBuilder;
import zx.soft.cases.management.domain.CasesData;
import zx.soft.cases.management.domain.CasesData.CasesDataBuilder;
import zx.soft.cases.management.domain.CasesType;
import zx.soft.cases.management.domain.CasesType.CasesTypeBuilder;
import zx.soft.cases.management.domain.QueryParameters;
import zx.soft.cases.management.utils.JsonUtils;
import zx.soft.cases.management.utils.Tools;
import zx.soft.csases.management.impala.ImpalaConnection;

@Service
public class ImpalaService {

	private static Logger logger = LoggerFactory.getLogger(ImpalaService.class);

	//根据案件Id查询具体案件
	public Cases getSpecCases(String casesId) {

		String sqlStatement = "SELECT * FROM apt.cases WHERE casesId='" + casesId + "'";
		logger.info(sqlStatement);
		Cases cases = null;
		try (Connection conn = ImpalaConnection.getConnection();
				Statement statement = conn.createStatement();
				ResultSet resultSet = statement.executeQuery(sqlStatement);) {
			if (resultSet != null) {
				try {
					while (resultSet.next()) {
						cases = this.resultSetToCases(resultSet);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException();
		} catch (Exception e1) {
			throw new RuntimeException();
		}
		return cases;
	}

	public List<Cases> getCasesList(String tableName, List<QueryParameters> queryParams, String orderBy, String order,
			int pageSize, int page) {
		String sqlStatement = Tools.getBasicSqlStatement(tableName, queryParams, orderBy, order, pageSize, page);
		logger.info(sqlStatement);
		try (Connection conn = ImpalaConnection.getConnection();
				Statement statement = conn.createStatement();
				ResultSet resultSet = statement.executeQuery(sqlStatement);) {
			List<Cases> temp = new ArrayList<>();
			if (resultSet != null) {
				try {
					while (resultSet.next()) {
						temp.add(this.resultSetToCases(resultSet));
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return temp;
		} catch (SQLException e) {
			throw new RuntimeException();
		} catch (Exception e1) {
			throw new RuntimeException();
		}
	}

	//查询具体案件数据
	public CasesData getSpecCasesData(String rowkey) {

		String sqlStatement = "SELECT * FROM apt.casesData WHERE rowkey='" + rowkey + "'";
		logger.info(sqlStatement);
		CasesData casesData = null;
		try (Connection conn = ImpalaConnection.getConnection();
				Statement statement = conn.createStatement();
				ResultSet resultSet = statement.executeQuery(sqlStatement);) {
			if (resultSet != null) {
				try {
					while (resultSet.next()) {
						casesData = this.resultSetToCasesData(resultSet);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException();
		} catch (Exception e1) {
			throw new RuntimeException();
		}
		return casesData;
	}

	public List<CasesData> getCasesDataList(String tableName, List<QueryParameters> queryParams, String orderBy,
			String order, int pageSize, int page) {
		String sqlStatement = Tools.getBasicSqlStatement(tableName, queryParams, orderBy, order, pageSize, page);
		logger.info(sqlStatement);
		try (Connection conn = ImpalaConnection.getConnection();
				Statement statement = conn.createStatement();
				ResultSet resultSet = statement.executeQuery(sqlStatement);) {
			List<CasesData> temp = new ArrayList<>();
			if (resultSet != null) {
				try {
					while (resultSet.next()) {
						temp.add(this.resultSetToCasesData(resultSet));
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return temp;
		} catch (SQLException e) {
			throw new RuntimeException();
		} catch (Exception e1) {
			throw new RuntimeException();
		}
	}

	public List<CasesType> getCasesTypeList(String tableName, List<QueryParameters> queryParams, String orderBy,
			String order, int pageSize, int page) {
		String sqlStatement = Tools.getBasicSqlStatement(tableName, queryParams, orderBy, order, pageSize, page);
		logger.info(sqlStatement);
		try (Connection conn = ImpalaConnection.getConnection();
				Statement statement = conn.createStatement();
				ResultSet resultSet = statement.executeQuery(sqlStatement);) {
			List<CasesType> temp = new ArrayList<>();
			if (resultSet != null) {
				try {
					while (resultSet.next()) {
						temp.add(this.resultSetToCasesType(resultSet));
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return temp;
		} catch (SQLException e) {
			throw new RuntimeException();
		} catch (Exception e1) {
			throw new RuntimeException();
		}
	}

	//获取该查询条件下的数据总量
	public int getSum(String tableName, List<QueryParameters> queryParams) {
		String condition = Tools.getPartSqlStatement(queryParams);
		String sqlStatement = "SELECT COUNT(*) FROM " + tableName + " WHERE " + condition;
		logger.info(sqlStatement);
		int s = 0;
		try (Connection conn = ImpalaConnection.getConnection();
				Statement statement = conn.createStatement();
				ResultSet resultSet = statement.executeQuery(sqlStatement);) {
			if (resultSet != null) {
				while (resultSet.next()) {
					s = resultSet.getInt(1);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return s;
	}

	//根据案件类型id获取案件类型的基本信息
	public CasesType getSpecCasesType(String name) {
		String sqlStatement = "SELECT * FROM apt.casestype WHERE name='" + name + "'";
		logger.info(sqlStatement);
		CasesType casesType = null;
		try (Connection conn = ImpalaConnection.getConnection();
				Statement statement = conn.createStatement();
				ResultSet resultSet = statement.executeQuery(sqlStatement);) {
			if (resultSet != null) {
				try {
					while (resultSet.next()) {
						casesType = this.resultSetToCasesType(resultSet);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException();
		} catch (Exception e1) {
			throw new RuntimeException();
		}
		return casesType;
	}

	//根据案件类型id获取案件类型名称
	public String getNameByTypeId(String typeId) {
		String sqlStatement = "select name from apt.casestype where typeid ='" + typeId + "'";
		logger.info(sqlStatement);
		String name = null;
		try (Connection conn = ImpalaConnection.getConnection();
				Statement statement = conn.createStatement();
				ResultSet resultSet = statement.executeQuery(sqlStatement);) {
			if (resultSet != null) {
				try {
					while (resultSet.next()) {
						name = resultSet.getString(1);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException();
		} catch (Exception e1) {
			throw new RuntimeException();
		}
		return name;
	}

	//获取所有案件类型名称和ID
	public static Map<String, String> getAllNameTypeId() {
		String sqlStatement = "select typeId,name from apt.casestype ";
		logger.info(sqlStatement);
		Map<String, String> map = new HashMap<>();
		try (Connection conn = ImpalaConnection.getConnection();
				Statement statement = conn.createStatement();
				ResultSet resultSet = statement.executeQuery(sqlStatement);) {
			if (resultSet != null) {
				try {
					while (resultSet.next()) {
						map.put(resultSet.getString(1), resultSet.getString(2));
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException();
		} catch (Exception e1) {
			throw new RuntimeException();
		}
		return map;
	}

	//根据案件类型名称获取案件类型id
	public String getTypeIdByName(String name) {
		String sqlStatement = "select typeid from apt.casestype where name ='" + name + "'";
		logger.info(sqlStatement);
		String typeid = null;
		try (Connection conn = ImpalaConnection.getConnection();
				Statement statement = conn.createStatement();
				ResultSet resultSet = statement.executeQuery(sqlStatement);) {
			if (resultSet != null) {
				try {
					while (resultSet.next()) {
						typeid = resultSet.getString(1);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException();
		} catch (Exception e1) {
			throw new RuntimeException();
		}
		return typeid;
	}

	private Cases resultSetToCases(ResultSet resultSet) throws SQLException {
		String casesId = resultSet.getString(1);
		String typeId = resultSet.getString(6);
		String casesName = resultSet.getString(3);
		String casesDetail = resultSet.getString(2);
		long createTime = resultSet.getLong(4);
		String createUser = resultSet.getString(5);
		CasesBuilder builder = new CasesBuilder(casesId, typeId, casesName);
		if (casesDetail != null) {
			builder.addCasesDetail(casesDetail);
		}
		if (createUser != null) {
			builder.addCreateUser(createUser);
		}
		builder.addCreateTime(createTime);
		return builder.build();
	}

	private CasesData resultSetToCasesData(ResultSet resultSet) throws SQLException {
		String rowkey = resultSet.getString(1);
		String attackdetail = resultSet.getString(2);
		String agreementtype = resultSet.getString(3);
		int attacktype = resultSet.getInt(4);
		int attacktypedetail = resultSet.getInt(5);
		String casesid = resultSet.getString(6);
		long createtime = resultSet.getLong(7);
		String filedetail = resultSet.getString(8);
		String fileid = resultSet.getString(9);
		String filelocation = resultSet.getString(10);
		String filename = resultSet.getString(11);
		double filesize = resultSet.getDouble(12);
		String filetype = resultSet.getString(13);
		int isfile = resultSet.getInt(14);
		boolean malware = resultSet.getBoolean(15);
		int seversity = resultSet.getInt(16);
		String sourcebrowsertype = resultSet.getString(17);
		String sourcedetail = resultSet.getString(18);
		String sourceip = resultSet.getString(19);
		int serversitylevel = resultSet.getInt(20);
		String sourceos = resultSet.getString(21);
		int sourceport = resultSet.getInt(22);
		String targetbrowsertype = resultSet.getString(23);
		String targetdetail = resultSet.getString(24);
		String targetip = resultSet.getString(25);
		String targetos = resultSet.getString(26);
		int targetport = resultSet.getInt(27);
		long timestamp = resultSet.getLong(28);
		long trainmittime = resultSet.getLong(29);
		CasesDataBuilder builder = new CasesDataBuilder(rowkey, casesid);
		if (attackdetail != null) {
			builder.addAttackDetail(attackdetail);
		}
		if (agreementtype != null) {
			builder.addAgreementType(agreementtype);
		}
		if (filedetail != null) {
			builder.addFileDetail(filedetail);
		}
		if (fileid != null) {
			builder.addFileId(fileid);
		}
		if (filelocation != null) {
			builder.addFileLocation(filelocation);
		}
		if (filename != null) {
			builder.addFileName(filename);
		}
		if (filetype != null) {
			builder.addFileType(filetype);
		}
		if (sourcebrowsertype != null) {
			builder.addSourceBrowserType(sourcebrowsertype);
		}
		if (sourcedetail != null) {
			builder.addSourceDetail(sourcedetail);
		}
		if (sourceip != null) {
			builder.addSourceIp(sourceip);
		}
		if (sourceos != null) {
			builder.addSourceOS(sourceos);
		}
		if (targetbrowsertype != null) {
			builder.addTargetBrowserType(targetbrowsertype);
		}
		if (targetdetail != null) {
			builder.addTargetDetail(targetdetail);
		}
		if (targetip != null) {
			builder.addTargetIp(targetip);
		}
		if (targetos != null) {
			builder.addTargetOS(targetos);
		}
		builder.addAttackType(attacktype).addAttackTypeDetail(attacktypedetail).addAttackDetail(attackdetail)
				.addFileSize(filesize).addIsFile(isfile).addMalware(malware).addSeversitye(seversity)
				.addServersityLevel(serversitylevel).addSourcePort(sourceport).addTimestamp(timestamp)
				.addTrainmitTime(trainmittime).addTargetPort(targetport).addCreateTime(createtime);
		return builder.build();
	}

	private CasesType resultSetToCasesType(ResultSet resultSet) throws SQLException {
		String typeId = resultSet.getString(1);
		long createtime = resultSet.getLong(2);
		String createuser = resultSet.getString(3);
		String name = resultSet.getString(4);

		CasesTypeBuilder casesTypebuilder = new CasesTypeBuilder(typeId, name).addCreateTime(createtime);
		if (createuser != null) {
			casesTypebuilder.addCreateUser(createuser);
		}
		return casesTypebuilder.build();
	}

	//获取最大的类型id号
	public static int getMaxCasesTypeId() throws SQLException {
		String sqlStatement = "select max(typeId) from apt.casesType";
		logger.info(sqlStatement);
		String maxCasesTypeId = null;
		try (Connection conn = ImpalaConnection.getConnection();
				Statement statement = conn.createStatement();
				ResultSet resultSet = statement.executeQuery(sqlStatement);) {
			if (resultSet != null) {
				while (resultSet.next()) {
					maxCasesTypeId = resultSet.getString(1);
				}

			}
		}

		return Integer.valueOf(maxCasesTypeId);
	}

	//获取指定案件下的所有案件数据
	public List<String> getRowkeysByCasesId(String casesId) {
		String sqlStatement = "select rowkey from apt.casesData where rowkey like'" + casesId + "%'";
		List<String> rowkeys = new ArrayList<>();
		try (Connection conn = ImpalaConnection.getConnection();
				Statement statement = conn.createStatement();
				ResultSet resultSet = statement.executeQuery(sqlStatement);) {
			if (resultSet != null) {
				while (resultSet.next()) {
					rowkeys.add(resultSet.getString(1));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowkeys;
	}

	public static void main(String[] args) {
		ImpalaService service = new ImpalaService();
		System.out.println(JsonUtils.toJsonWithoutPretty(service.getSpecCases("14535296241331453530123977")));
		System.out.println(JsonUtils.toJsonWithoutPretty(service
				.getSpecCasesData("145352962413314535301239771453530579097")));
		System.out.println(JsonUtils.toJsonWithoutPretty(service.getSpecCasesType("紧急类型")));

	}
}
