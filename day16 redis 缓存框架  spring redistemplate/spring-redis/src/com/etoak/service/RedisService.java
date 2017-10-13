package com.etoak.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisService {

	@Autowired
	private RedisTemplate rt;
	
	/**
	 * rt.execute(RedisCallback)
	 * 	con.set(Byte[] key , Byte[] value)
	 */
	public void add(final String key , final String value){
		
		rt.execute(new RedisCallback(){

			@Override
			public Object doInRedis(RedisConnection con) throws DataAccessException {
				// TODO Auto-generated method stub
				con.set( key.getBytes(), value.getBytes());
				return null;
			}
			
		});
		
	}
	
	public String get(final String key){
		
		return (String)rt.execute(new RedisCallback(){

			@Override
			public Object doInRedis(RedisConnection con) throws DataAccessException {
				// TODO Auto-generated method stub
				
				if(con.exists(key.getBytes())){
					byte[] value = con.get(key.getBytes());
					return new String(value);
				}
				
				return null;
			}
			
		});
		
	}
	
	/**
	 * redis对list集合操作
	 */
	public void setList(String key , List list){
		
		ListOperations lo = rt.opsForList();
		
		/**
		 * push 进栈
		 * 	leftPush  rightPush
		 * pop 出栈
		 * 	leftPop  rightPop
		 */
		for(Object arg : list){
			lo.rightPush(key, arg);
		}
		
	}
	
	public List getList(String key){
		
		ListOperations lo = rt.opsForList();
		
		long size = lo.size(key);
		System.out.println("size-->"+size);
		
		Object result = lo.index(key, 3);
		System.out.println("result-->"+result);
		
		// 修改list集合中的第三个元素  'et'
		lo.set(key, 2, "et");
		
		return lo.range(key, 0, size-1);
		
		// return null;
		
	}
	
	public Object popList(String key){
		
		ListOperations lo = rt.opsForList();
		
		return lo.rightPop(key);
		
	}
	
	/**
	 * map操作
	 * 		opsForHash() -> HashOperations
	 */
	public void setMap(String key , String map_K , Object map_v){
		
		HashOperations ho = rt.opsForHash();
		
		ho.put(key, map_K, map_v);
	}
	
	
	public void getMap(String key){
		
		HashOperations ho = rt.opsForHash();
		
		Map map = ho.entries(key);
		System.out.println("map-->"+map);
		
		Object value = ho.get(key, "addr");
		System.out.println("addr->"+value);
		
		Set keys = ho.keys(key);
		System.out.println("keys-->"+keys);
		
		List values = ho.values(key);
		System.out.println("values-->"+values);
		
		long size = ho.size(key);
		System.out.println("size-->"+size);
	}
}


/**
 * 	tomcat启动时
 * 	将employee表中的所有数据存放在redis缓存中
 * 		ServletContextListener
 * 			service - dao - 数据库
 * 			service - redis - 存储
 * 	页面中使用easyui展示 -> 
 * 			controller - service - redis - 查询
 * 
 * 
 * 
 * 	EmpDaoIf{
 * 		public List<Employee> selectAllEmps();
 * 	}
 * 	使用spring-mybatis实现该接口
 * 	EmpServiceIf{
 * 		EmpDaoIf
 * 		RedisTemplate
 * 		// 调用DAO-selectAllEmps()
 * 		public List<Employee> selectAllEmps();
 *		// 向redis中存储
 *		public void saveListInRedis(String key , List list);
 *		// 从redis中读取
 *		public List<Employee> getListInRedis(String key);
 * 	}
 * 	// 客户端提交请求时
 * 	EmpController{
 * 		@RequestMapping("path")
 * 		@ResponseBody
 * 		public List<Employee> selectAllEmps(){
 * 			调用service getListInRedis
 * 		}
 * 	}
 * 	// 服务器启动
 * 	RedisListener implements ServletContextListener{
 * 		init(){
 * 			// 从数据库中拿到数据集合
 * 			List list = EmpServiceIf.selectAllEmps();
 * 			// 存储到redis中
 * 			EmpServiceIf.saveListInRedis();
 * 		}
 * 	}
 */









