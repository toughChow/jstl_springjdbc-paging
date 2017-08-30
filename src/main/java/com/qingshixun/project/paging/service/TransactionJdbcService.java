package com.qingshixun.project.paging.service;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.qingshixun.project.paging.dao.TransactionDao;
import com.qingshixun.project.paging.model.Page;
import com.qingshixun.project.paging.model.TransactionModel;

@Service
public class TransactionJdbcService extends AbstractJdbcService {

	@Autowired
	private TransactionDao transactionDao;

	// 查询数据记录总数
	private static String QUERY_TRANS_COUNT_SQL = "select count(*) from qsx_transaction";

	// 查询数据记录SQL
	private static String QUERY_TRANS_DATA_SQL = "select * from qsx_transaction";

	// 分页查询列表
	public Page<TransactionModel> findTransactionList(int pageNo, int pageSize) {
		Object[] args = null;
		return transactionDao.getPages(QUERY_TRANS_COUNT_SQL, QUERY_TRANS_DATA_SQL, args, pageNo, pageSize,
				new TransactionRowMap());
	}

	class TransactionRowMap implements RowMapper<TransactionModel> {

		@Override
		public TransactionModel mapRow(ResultSet rs, int rowNum) throws SQLException {
			TransactionModel transaction = new TransactionModel();
			transaction.setId(rs.getLong("id"));
			transaction.setAmmount(rs.getDouble("ammount"));
			transaction.setMonth(rs.getInt("month"));
			transaction.setRegion(rs.getString("region"));
			return transaction;
		}

	}
}
