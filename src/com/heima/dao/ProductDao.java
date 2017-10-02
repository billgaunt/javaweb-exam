package com.heima.dao;

import java.util.List;

import com.heima.domain.Product;

/**      
 * @author: LiYanBin
 * @creationTime:2017年9月28日 晚上午12:25:55
 * @version: 1.1
 */
public interface ProductDao{

	void addproduct(Product product) throws Exception;

	String checkPName(String pname) throws Exception;

	Product findProcudtByPid(String pid) throws Exception;

	void updateProduct(Product product) throws Exception;

	void deleteProduct(String pid) throws Exception;

	Integer findCount() throws Exception;

	List<Product> findByPage(int begin, Integer pageSize) throws Exception;
	
}
