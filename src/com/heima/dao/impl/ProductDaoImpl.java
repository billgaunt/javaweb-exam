package com.heima.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.heima.dao.ProductDao;
import com.heima.domain.Product;
import com.heima.utils.JDBCUtils;

/**      
 * @author: LiYanBin
 * @creationTime:2017年9月28日 晚上午12:15:55
 * @version: 1.1
 */
public class ProductDaoImpl implements ProductDao{
	private QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
	/**
	 * 添加商品数据到数据框
	 */
	@Override
	public void addproduct(Product product) throws Exception {
		String sql = "insert product values(?,?,?,?,?,?);";
		Object[] param = {product.getPid(),product.getPname(),product.getMarket_price(),
					      product.getShop_price(),product.getArea(),product.getPdesc()};
		queryRunner.update(sql, param);
	}
	/**
	 * 根据pname 查询商品， 查到数据返回0， 查不到返回1. (用于servlet的异步校验)
	 */
	@Override
	public String checkPName(String pname) throws Exception {
		String sql = "select * from product where pname = ?";
		ResultSetHandler<Object> rsh;
		List<Product> product = queryRunner.query(sql, new BeanListHandler<Product>(Product.class),pname);
		if (product == null || product.size() == 0) {
			return "1";
		}
		return "0";
	}
	/**
	 * 根据pid 查询商品，
	 */
	@Override
	public Product findProcudtByPid(String pid) throws Exception {
		String sql = "select * from product where pid = ?;";
		Product product = queryRunner.query(sql, new BeanHandler<Product>(Product.class),pid);
		return product;
	}
	/**
	 * 修改商品数据
	 */
	@Override
	public void updateProduct(Product product) throws Exception {
		String sql = "update product set pname =?, market_price = ?, shop_price = ?, area = ?, pdesc = ? where pid = ?;";
		Object[] param = {product.getPname(),product.getMarket_price(), product.getShop_price(),
						  product.getArea(),product.getPdesc(), product.getPid()};
		queryRunner.update(sql, param);
	}
	/**
	 * 删除商品数据
	 */
	@Override
	public void deleteProduct(String pid) throws Exception {
		String sql = "delete from product where pid = ?";
		queryRunner.update(sql,pid);
	}
	/**
	 * 获取商品总记录数  用于分页
	 */
	@Override
	public Integer findCount() throws Exception {
		String sql = "select count(*) from product;";
		Long count = (Long) queryRunner.query(sql, new ScalarHandler());
		return count.intValue();
	}
	/**
	 * 分页查询商品数据
	 */
	@Override
	public List<Product> findByPage(int begin, Integer pageSize) throws SQLException {
		String sql = "select * from product limit ?,?";
		List<Product> list = queryRunner.query(sql, new BeanListHandler<Product>(Product.class), begin, pageSize);
		return list;
	}
	
}
