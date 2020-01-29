package by.it.academy.project.service;

import by.it.academy.project.dao.SectionDao;
import by.it.academy.project.dao.SectionDaoImpl;
import by.it.academy.project.model.Section;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.*;

public class SectionServiceImpl implements SectionService {

    private static final SectionService INSTANCE = new SectionServiceImpl();

    private static final Logger logger = LoggerFactory.getLogger(SectionServiceImpl.class);

    private static final SectionDao sectionDao = SectionDaoImpl.getINSTANCE();

    private SectionServiceImpl() {
    }

    public static SectionService getINSTANCE() {
        return INSTANCE;
    }


    @Override
    public Set<Section> getSections() {
        logger.debug("get sections");
        Set<Section> result = new HashSet<>();
        try {
            result.addAll(sectionDao.getAll());
            logger.debug("result " + result);
        } catch (SQLException e) {
            logger.error("error while getting sections", e);
        }
        return result;
    }

    @Override
    public Optional<Section> findSectionByID(Long id) {
        logger.debug("find section by id");
        Optional<Section> result = Optional.empty();
        try {
            result = sectionDao.read(id);
            logger.debug("result " + result);
        } catch (SQLException e) {
            logger.error("error while finding section by id", e);
        }
        return result;
    }


}
