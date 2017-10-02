package com.heima.service.impl;

import java.util.List;

import com.heima.dao.ProductDao;
import com.heima.dao.impl.ProductDaoImpl;
import com.heima.domain.Product;
import com.heima.service.ProductService;
import com.heima.utils.PageBean;

/**      
 * @author: LiYanBin
 * @creationTime:2017年9月28日 下午2:56:12
 * @version: 1.1
 */
public class ProductServiceImpl implements ProductService {
	private ProductDao productDao = new  ProductDaoImpl();
	@Override
	public void addProduct(Product product) throws Exception {
		productDao.addproduct(product);
	}
	@Override
	public String checkPName(String pname) throws Exception {
		return productDao.checkPName(pname);
	}
	@Override
	public Product findProcudtByPid(String pid) throws Exception {
		return productDao.findProcudtByPid(pid);
	}
	@Override
	public void updateProduct(Product product) throws Exception {
		productDao.updateProduct(product);
	}
	@Override
	public void deleteProduct(String pid) throws Exception {
		productDao.deleteProduct(pid);
	}
	@Override
	public void findByPage(PageBean pageBean) throws Exception {
		// 设置每页显示的记录数:
		Integer pageSize = 10;
		pageBean.setPageSize(pageSize);
		// 设置总记录数:
		Integer totalCount = productDao.findCount();
		pageBean.setTotalCount(totalCount);
		// 设置总页数:
		double tc = totalCount;
		Double num = Math.ceil(tc / pageSize);
		pageBean.setTotalPage(num.intValue());
		// 设置每页显示的数据的集合:
		int begin = (pageBean.getCurrPage() - 1) * pageSize;
		List<Product> list = productDao.findByPage(begin,pageSize);
		pageBean.setList(list);
	}
	
}
