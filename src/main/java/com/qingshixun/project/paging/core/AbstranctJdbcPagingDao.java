package com.qingshixun.project.paging.core;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import com.qingshixun.project.paging.model.Page;

public class AbstranctJdbcPagingDao<T> {

	protected JdbcTemplate jdbcTemplate;

	/**
	 * 绑定数据源
	 * 
	 * @param dataSource
	 */
	@Resource
	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public Page<T> getPages(final String countSql, final String dataSql, final Object args[], final int pageNo,
			final int pageSize, final RowMapper<T> rowMapper) {
		// 总记录数据
		final int rowCount = jdbcTemplate.queryForObject(countSql, args, Integer.class);

		// 分页对象
		final Page<T> page = new Page<T>();
		// 页数（第几页）
		page.setPageNo(pageNo);
		// 总记录数
		page.setPageSize(pageSize);
		// 每页显示的记录条数
		page.setPageSize(pageSize);

		// 根据pageNo取一页数据
		final int startRow = (pageNo - 1) * pageSize;

		// 根据分页信息，查询数据
		jdbcTemplate.query(dataSql, args, new ResultSetExtractor<Page<T>>() {
			public Page<T> extractData(ResultSet rs) throws SQLException, DataAccessException {
				final List<T> pageItems = page.getResult();
				int currentRow = 0;

				// 根据rowMapper填充数据对象
				while (rs.next() && currentRow < startRow + pageSize) {
					if (currentRow >= startRow) {
						pageItems.add(rowMapper.mapRow(rs, currentRow));
					}
					currentRow++;
				}
				return page;
			}
		});
		return page;
	}

}
