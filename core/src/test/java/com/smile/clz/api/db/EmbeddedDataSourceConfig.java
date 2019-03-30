package com.smile.clz.api.db;

import javax.sql.DataSource;
import org.springframework.context.annotation.Configuration;

/**
 * class to provide datasource
 *
 * @author hasithagamage
 * @date 10/28/17
 */
@Configuration
public class EmbeddedDataSourceConfig {

  public DataSource dataSource() {
    return new EmbeddedMysqlDatabaseBuilder().addSqlScript("SVD_CLASS_DB.sql").build();
  }

}
