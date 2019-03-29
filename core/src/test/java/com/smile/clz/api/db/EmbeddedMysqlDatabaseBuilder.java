package com.smile.clz.api.db;

import com.mysql.management.MysqldResource;
import com.mysql.management.MysqldResourceI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * class to build in memory database
 *
 * @author hasithagamage
 * @date 10/28/17
 */
public class EmbeddedMysqlDatabaseBuilder {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmbeddedMysqlDatabaseBuilder.class);
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    private final String baseDatabaseDir = System.getProperty("java.io.tmpdir");
    private String databaseName = "SVD_CLASS_DB_" + System.nanoTime();
    private final int port = new Random().nextInt(10000) + 3306;
    private boolean foreignKeyCheck;

    private final ResourceLoader resourceLoader;
    private final ResourceDatabasePopulator databasePopulator;

    public EmbeddedMysqlDatabaseBuilder() {
        resourceLoader = new DefaultResourceLoader();
        databasePopulator = new ResourceDatabasePopulator();
        foreignKeyCheck = true;
    }

    private EmbeddedMysqlDatabase createDatabase(MysqldResource mysqldResource) {
        if (!mysqldResource.isRunning()) {
            LOGGER.error("MySQL instance not found... Terminating");
            throw new RuntimeException("Cannot get Datasource, MySQL instance not started.");
        }
        EmbeddedMysqlDatabase database = new EmbeddedMysqlDatabase(mysqldResource);
        database.setDriverClassName("com.mysql.jdbc.Driver");
        database.setUsername(USERNAME);
        database.setPassword(PASSWORD);
        String url = "jdbc:mysql://localhost:" + port + "/" + databaseName + "?" + "createDatabaseIfNotExist=true";

        if (!foreignKeyCheck) {
            url += "&sessionVariables=FOREIGN_KEY_CHECKS=0";
        }
        LOGGER.debug("database url: {}", url);
        database.setUrl(url);
        return database;
    }

    private MysqldResource createMysqldResource() {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("=============== Starting Embedded MySQL using these parameters ===============");
            LOGGER.debug("baseDatabaseDir : " + baseDatabaseDir);
            LOGGER.debug("databaseName : " + databaseName);
            LOGGER.debug("host : localhost (hardcoded)");
            LOGGER.debug("port : " + port);
            LOGGER.debug("USERNAME : root (hardcode)");
            LOGGER.debug("PASSWORD : (no PASSWORD)");
            LOGGER.debug("=============================================================================");
        }

        Map<String, String> databaseOptions = new HashMap<String, String>();
        databaseOptions.put(MysqldResourceI.PORT, Integer.toString(port));

        MysqldResource mysqldResource = new MysqldResource(new File(baseDatabaseDir, databaseName));
        mysqldResource.start("embedded-mysqld-thread-" + System.currentTimeMillis(), databaseOptions);

        if (!mysqldResource.isRunning()) {
            throw new RuntimeException("MySQL did not start.");
        }

        LOGGER.info("MySQL started successfully @ {}", System.currentTimeMillis());
        return mysqldResource;
    }

    private void populateScripts(EmbeddedMysqlDatabase database) {
        try {
            DatabasePopulatorUtils.execute(databasePopulator, database);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            database.shutdown();
        }
    }

    public EmbeddedMysqlDatabaseBuilder addSqlScript(String script) {
        databasePopulator.addScript(resourceLoader.getResource(script));
        return this;
    }

    /**
     * whether to enable mysql foreign key check
     *
     * @param foreignKeyCheck
     */
    public EmbeddedMysqlDatabaseBuilder setForeignKeyCheck(boolean foreignKeyCheck) {
        this.foreignKeyCheck = foreignKeyCheck;
        return this;
    }

    /**
     * @param databaseName
     *            the databaseName to set
     */
    public final void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    EmbeddedMysqlDatabase build() {
        MysqldResource mysqldResource = createMysqldResource();
        EmbeddedMysqlDatabase database = createDatabase(mysqldResource);
        populateScripts(database);
        return database;
    }
}
