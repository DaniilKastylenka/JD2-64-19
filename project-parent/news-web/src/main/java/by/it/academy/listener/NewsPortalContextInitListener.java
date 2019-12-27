package by.it.academy.listener;

import by.it.academy.project.db.connection.pool.NewsDataSource;
import by.it.academy.project.db.migration.DbMigration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;
import java.util.ResourceBundle;

@WebListener()

public class NewsPortalContextInitListener implements ServletContextListener {

    private static final Logger logger = LoggerFactory.getLogger(NewsPortalContextInitListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        logger.info("Context initialized");

        try{
            ResourceBundle bundle = ResourceBundle.getBundle("mysql-hikari");
            NewsDataSource.configure(bundle);
            DataSource dataSource = NewsDataSource.getDataSource();
            DbMigration.migrate(dataSource);
        } catch (Exception e){
            logger.error("error", e);
            throw new RuntimeException("Datasource initialization error", e);
        }

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        logger.info("Context destroyed");
    }

}
