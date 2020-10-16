package com.preloode.panel.model.user;

import com.preloode.panel.enumeration.user.UserLogType;
import com.preloode.panel.model.global.Base;
import com.preloode.panel.model.global.TimestampReference;
import org.springframework.data.mongodb.core.index.Indexed;


public class UserLog extends Base {


    @Indexed
    private String authentication;

    private UserAgentReference agent;

    private String description;

    private UserTargetReference target;

    @Indexed
    private UserLogType type;

    private UserReference user;


    public UserLog() {


    }


    public UserLog(String id, TimestampReference created, TimestampReference modified, String authentication, UserAgentReference agent, String description, UserTargetReference target, UserLogType type, UserReference user) {

        super(id, created, modified);
        this.authentication = authentication;
        this.agent = agent;
        this.description = description;
        this.target = target;
        this.type = type;
        this.user = user;

    }


    public String getAuthentication() {

        return authentication;

    }


    public void setAuthentication(String authentication) {

        this.authentication = authentication;

    }


    public UserAgentReference getAgent() {

        return agent;

    }


    public void setAgent(UserAgentReference agent) {

        this.agent = agent;

    }


    public String getDescription() {

        return description;

    }


    public void setDescription(String description) {

        this.description = description;

    }


    public UserTargetReference getTarget() {

        return target;

    }


    public void setTarget(UserTargetReference target) {

        this.target = target;

    }


    public UserLogType getType() {

        return type;

    }


    public void setType(UserLogType type) {

        this.type = type;

    }


    public UserReference getUser() {

        return user;

    }


    public void setUser(UserReference user) {

        this.user = user;

    }


}
