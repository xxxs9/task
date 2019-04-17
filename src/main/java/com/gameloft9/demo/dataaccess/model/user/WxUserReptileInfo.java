package com.gameloft9.demo.dataaccess.model.user;

import javax.persistence.*;

@Table(name = "wx_user_reptile_info")
public class WxUserReptileInfo {
    @Column(name = "ID")
    @Id
    private Long id;

    @Column(name = "UUID")
    private String uuid;

    @Column(name = "REPTILE_ID")
    private String reptileId;

    @Column(name = "SERVER_ID")
    private String serverId;

    @Column(name = "REPTILE_NAME")
    private String reptileName;

    @Column(name = "SERVER_NAME")
    private String serverName;

    /**
     * @return ID
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return UUID
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * @param uuid
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    /**
     * @return REPTILE_ID
     */
    public String getReptileId() {
        return reptileId;
    }

    /**
     * @param reptileId
     */
    public void setReptileId(String reptileId) {
        this.reptileId = reptileId;
    }

    /**
     * @return SERVER_ID
     */
    public String getServerId() {
        return serverId;
    }

    /**
     * @param serverId
     */
    public void setServerId(String serverId) {
        this.serverId = serverId;
    }

    /**
     * @return REPTILE_NAME
     */
    public String getReptileName() {
        return reptileName;
    }

    /**
     * @param reptileName
     */
    public void setReptileName(String reptileName) {
        this.reptileName = reptileName;
    }

    /**
     * @return SERVER_NAME
     */
    public String getServerName() {
        return serverName;
    }

    /**
     * @param serverName
     */
    public void setServerName(String serverName) {
        this.serverName = serverName;
    }
}