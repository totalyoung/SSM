package config;

import javax.sql.DataSource;

import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.PathResource;
import org.springframework.core.io.Resource;

import com.common.FileUtil;

/**
 *
 * @author yangst
 * @date 2018年1月18日 上午10:54:54
 * 
 */
@Configuration
@MapperScan("com.mvc.*.dao")
public class MybatisConfig {

	@Bean
	public DataSource dataSource() {
//		return new PooledDataSource("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/bus?characterEncoding=UTF-8",
//				"root", "1234");
		return new PooledDataSource("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/test?characterEncoding=UTF-8",
				"root", "1234");
	}

	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {

		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setConfigLocation(new InputStreamResource(Resources.getResourceAsStream("/mybatis-config.xml")));
		sessionFactory.setMapperLocations(FileUtil.getMapper());
		return sessionFactory.getObject();
	}

	// /**
	// * 全局参数
	// * @author yangst
	// * @date 2018年1月18日 下午1:51:38
	// * @return
	// */
	// @Bean
	// public org.apache.ibatis.session.Configuration configuration(){
	// org.apache.ibatis.session.Configuration configuration = new
	// org.apache.ibatis.session.Configuration();
	//
	// //使全局的映射器启用或禁用缓存
	// configuration.setCacheEnabled(true);
	//
	// //全局启用或禁用延迟加载。当禁用时，所有关联对象都会即时加载
	// configuration.setLazyLoadingEnabled(true);
	//
	// //当启用时，有延迟加载属性的对象在被调用时将会完全加载任意属性。否则，每种属性将会按需要加载
	// configuration.setAggressiveLazyLoading(true);
	//
	// //是否允许单条sql 返回多个数据集 (取决于驱动的兼容性) default:true
	// configuration.setMultipleResultSetsEnabled(true);
	//
	// //是否可以使用列的别名 (取决于驱动的兼容性) default:true
	// configuration.setUseColumnLabel(true);
	//
	// //允许JDBC 生成主键。需要驱动器支持。如果设为了true，这个设置将强制使用被生成的主键，有一些驱动器不兼容不过仍然可以执行。
	// default:false
	// configuration.setUseGeneratedKeys(false);
	//
	// //指定 MyBatis 如何自动映射 数据基表的列 NONE：不隐射 PARTIAL:部分 FULL:全部
	// configuration.setAutoMappingBehavior(AutoMappingBehavior.PARTIAL);
	//
	// //这是默认的执行类型 （SIMPLE: 简单； REUSE: 执行器可能重复使用prepared statements语句；BATCH:
	// 执行器可以重复执行语句和批量更新）
	// configuration.setDefaultExecutorType(ExecutorType.SIMPLE);
	//
	// //使用驼峰命名法转换字段
	// configuration.setMapUnderscoreToCamelCase(true);
	//
	// //设置本地缓存范围 session:就会有数据的共享 statement:语句范围 (这样就不会有数据的共享 ) defalut:session
	// configuration.setLocalCacheScope(LocalCacheScope.SESSION);
	//
	// //设置但JDBC类型为空时,某些驱动程序 要指定值,default:OTHER，插入空值时不需要指定类型
	// configuration.setJdbcTypeForNull(JdbcType.NULL);
	//
	// return configuration;
	// }
}
