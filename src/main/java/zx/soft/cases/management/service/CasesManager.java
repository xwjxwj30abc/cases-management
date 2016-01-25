package zx.soft.cases.management.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.RetriesExhaustedWithDetailsException;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.stereotype.Service;

import zx.soft.cases.management.domain.Cases;
import zx.soft.cases.management.domain.CasesData;
import zx.soft.cases.management.domain.CasesData.CasesDataBuilder;
import zx.soft.cases.management.domain.CasesType;
import zx.soft.cases.management.hbase.HbasePool;
import zx.soft.common.conn.pool.PoolConfig;

@Service
public class CasesManager {
	private static HbasePool pool;

	public CasesManager() {
		PoolConfig config = new PoolConfig();
		config.setMaxTotal(20);
		config.setMaxIdle(5);
		config.setMaxWaitMillis(1000);
		config.setTestOnBorrow(true);

		Configuration hbaseConfig = new Configuration();
		hbaseConfig.set("hbase.zookeeper.quorum", "kafka04,kafka05,kafka06,kafka07,kafka08");
		hbaseConfig.set("hbase.zookeeper.property.clientPort", "2181");

		pool = new HbasePool(config, hbaseConfig);
	}

	//创建表
	public void createTable(String tableName, String columnFamily) {
		Connection conn = pool.getConnection();
		try (Admin admin = conn.getAdmin();) {
			HTableDescriptor tableDescriptor = new HTableDescriptor(TableName.valueOf(tableName));
			tableDescriptor.addFamily(new HColumnDescriptor(columnFamily));
			admin.createTable(tableDescriptor);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			pool.returnConnection(conn);
		}

	}

	//插入案件信息
	public void insertCases(Cases cases) {
		Connection conn = pool.getConnection();
		TableName tableName = TableName.valueOf("cases".getBytes());
		try {

			Table table = conn.getTable(tableName);
			Put put = new Put(cases.getCasesId().getBytes());
			put.addColumn("info".getBytes(), "ti".getBytes(), cases.getTypeId().getBytes());
			put.addColumn("info".getBytes(), "cn".getBytes(), cases.getCasesName().getBytes());
			put.addColumn("info".getBytes(), "cd".getBytes(), cases.getCasesDetail().getBytes());
			put.addColumn("info".getBytes(), "ct".getBytes(), Bytes.toBytes(cases.getCreateTime()));
			put.addColumn("info".getBytes(), "cu".getBytes(), cases.getCreateUser().getBytes());
			table.put(put);
			table.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			pool.returnConnection(conn);
		}
	}

	//删除数据
	public void deleteData(String tableName, List<String> rowkeys) {
		Connection conn = pool.getConnection();
		List<Delete> deletes = new ArrayList<>();
		try {
			Table table = conn.getTable(TableName.valueOf(tableName.getBytes()));
			for (String rowkey : rowkeys) {
				Delete delete = new Delete(rowkey.getBytes());
				deletes.add(delete);
			}
			table.delete(deletes);
			table.close();
		} catch (RetriesExhaustedWithDetailsException e) {
			System.out.println(e.getExhaustiveDescription());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			pool.returnConnection(conn);
		}

	}

	//删除单条数据
	public void deleteSingleData(String tableName, String id) {
		Connection conn = pool.getConnection();
		try {
			Table table = conn.getTable(TableName.valueOf(tableName.getBytes()));
			Delete delete = new Delete(id.getBytes());
			table.delete(delete);
			table.close();
		} catch (RetriesExhaustedWithDetailsException e) {
			System.out.println(e.getExhaustiveDescription());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			pool.returnConnection(conn);
		}

	}

	//增加数据到案件
	public void insertCasesData(CasesData cases) {
		Connection conn = pool.getConnection();
		TableName tableName = TableName.valueOf("casesData".getBytes());
		try {

			Table table = conn.getTable(tableName);
			Put put = new Put(cases.getRowkey().getBytes());
			put.addColumn("info".getBytes(), "ci".getBytes(), cases.getCasesId().getBytes());
			put.addColumn("info".getBytes(), "ct".getBytes(), Bytes.toBytes(cases.getCreateTime()));
			put.addColumn("info".getBytes(), "fi".getBytes(), cases.getFileId().getBytes());
			put.addColumn("info".getBytes(), "if".getBytes(), Bytes.toBytes(cases.getIsFile()));
			put.addColumn("info".getBytes(), "fn".getBytes(), cases.getFileName().getBytes());
			put.addColumn("info".getBytes(), "ft".getBytes(), cases.getFileType().getBytes());
			put.addColumn("info".getBytes(), "fs".getBytes(), Bytes.toBytes(cases.getFileSize()));
			put.addColumn("info".getBytes(), "fl".getBytes(), cases.getFileLocation().getBytes());
			put.addColumn("info".getBytes(), "fd".getBytes(), cases.getFileDetail().getBytes());
			put.addColumn("info".getBytes(), "at".getBytes(), cases.getAgreementType().getBytes());
			put.addColumn("info".getBytes(), "tt".getBytes(), Bytes.toBytes(cases.getTrainmitTime()));
			put.addColumn("info".getBytes(), "si".getBytes(), cases.getSourceIp().getBytes());
			put.addColumn("info".getBytes(), "so".getBytes(), cases.getSourceOS().getBytes());
			put.addColumn("info".getBytes(), "sbt".getBytes(), cases.getSourceBrowserType().getBytes());
			put.addColumn("info".getBytes(), "sp".getBytes(), Bytes.toBytes(cases.getSourcePort()));
			put.addColumn("info".getBytes(), "sd".getBytes(), cases.getSourceDetail().getBytes());
			put.addColumn("info".getBytes(), "tip".getBytes(), cases.getTargetIp().getBytes());
			put.addColumn("info".getBytes(), "to".getBytes(), cases.getTargetOS().getBytes());
			put.addColumn("info".getBytes(), "tbt".getBytes(), cases.getTargetBrowserType().getBytes());
			put.addColumn("info".getBytes(), "tp".getBytes(), Bytes.toBytes(cases.getTargetPort()));
			put.addColumn("info".getBytes(), "td".getBytes(), cases.getTargetDetail().getBytes());
			put.addColumn("info".getBytes(), "ts".getBytes(), Bytes.toBytes(cases.getTimestamp()));
			put.addColumn("info".getBytes(), "mw".getBytes(), Bytes.toBytes(cases.isMalware()));
			put.addColumn("info".getBytes(), "s".getBytes(), Bytes.toBytes(cases.getSeversity()));
			put.addColumn("info".getBytes(), "sl".getBytes(), Bytes.toBytes(cases.getServersityLevel()));
			put.addColumn("info".getBytes(), "at".getBytes(), Bytes.toBytes(cases.getAttackType()));
			put.addColumn("info".getBytes(), "atd".getBytes(), Bytes.toBytes(cases.getAttackTypeDetail()));
			put.addColumn("info".getBytes(), "ad".getBytes(), cases.getAttackDetail().getBytes());
			table.put(put);
			table.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			pool.returnConnection(conn);
		}
	}

	//增加案件类型信息
	public void insertCasesType(CasesType casesType) {
		Connection conn = pool.getConnection();
		TableName tableName = TableName.valueOf("casesType".getBytes());
		try {

			Table table = conn.getTable(tableName);
			Put put = new Put(casesType.getTypeId().getBytes());
			put.addColumn("info".getBytes(), "name".getBytes(), Bytes.toBytes(casesType.getName()));
			put.addColumn("info".getBytes(), "createTime".getBytes(), Bytes.toBytes(casesType.getCreateTime()));
			put.addColumn("info".getBytes(), "createUser".getBytes(), Bytes.toBytes(casesType.getCreateUser()));
			table.put(put);
			table.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			pool.returnConnection(conn);
		}
	}

	//更新案件类型名
	public void updateCasesType(CasesType casesType) {
		Connection conn = pool.getConnection();
		TableName tableName = TableName.valueOf("casesType".getBytes());
		try {

			Table table = conn.getTable(tableName);
			Put put = new Put(casesType.getTypeId().getBytes());
			put.addColumn("info".getBytes(), "name".getBytes(), Bytes.toBytes(casesType.getName()));
			table.put(put);
			table.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			pool.returnConnection(conn);
		}
	}

	//删除案件类型
	public void deleteCasesType(String id) {
		Connection conn = pool.getConnection();
		TableName tableName = TableName.valueOf("casesType".getBytes());
		try {
			Table table = conn.getTable(tableName);
			Delete delete = new Delete(id.getBytes());
			table.delete(delete);
			table.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			pool.returnConnection(conn);
		}

	}

	//关闭连接池
	public void closePool() {
		pool.close();
	}

	public static void main(String[] args) {
		CasesManager manager = new CasesManager();
		CasesDataBuilder bu = new CasesDataBuilder("00000002" + String.valueOf(System.currentTimeMillis()), "00000002");
		manager.insertCasesData(bu.build());
		//manager.createTable("casesData", "info");

	}
}
