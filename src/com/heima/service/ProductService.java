package com.heima.service;

import java.util.List;

import com.heima.domain.Product;
import com.heima.utils.PageBean;

/**      
 * @author: LiYanBin
 * @creationTime:2017年9月28日 下午2:55:32
 * @version: 1.1
 */
public interface ProductService {

	void addProduct(Product producer) throws Exception;

	String checkPName(String pname) throws Exception;

	Product findProcudtByPid(String pid) throws Exception;

	void updateProduct(Product product) throws Exception;

	void deleteProduct(String pid) throws Exception;

	void findByPage(PageBean pageBean) throws Exception;

}
