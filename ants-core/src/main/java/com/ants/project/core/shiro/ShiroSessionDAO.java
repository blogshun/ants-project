package com.ants.project.core.shiro;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author liushun
 * @version 1.0
 * @Date 2015-12-29
 */
public class ShiroSessionDAO extends AbstractSessionDAO {

    //统计在线人数
    private Map<Serializable,Session> map = new HashMap<Serializable,Session>();

    private final Logger logger = LoggerFactory.getLogger(ShiroSessionDAO.class);

    //修改session
    public void update(Session session) throws UnknownSessionException {
        logger.info("now update session");
        map.put(session.getId(),session);
    }

    //删除session
    public void delete(Session session) {
        logger.info("now delete session");
        map.remove(session.getId());
    }

    //得到活跃的session值
    public Collection<Session> getActiveSessions() {
        logger.info("now getActiveSessions session");
        return map.values();
    }

    //创建session
    protected Serializable doCreate(Session session) {
        logger.info("now doCreate session");
        Serializable sessionId = generateSessionId(session);
        assignSessionId(session, sessionId);
        map.put(sessionId, session);

        return sessionId;
    }

    //取session对象
    protected Session doReadSession(Serializable sessionId) {
        logger.info("now doReadSession session");
        return map.get(sessionId);
    }
}
