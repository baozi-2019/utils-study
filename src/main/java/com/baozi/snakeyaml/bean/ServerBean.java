package com.baozi.snakeyaml.bean;

/**
 * 作者：鲍庆洋
 * 时间：2023/6/9 10:51
 * 描述：
 */
public class ServerBean {
    private String ip;
    private Integer port;

    private String name;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ServerBean{" +
                "ip='" + ip + '\'' +
                ", port=" + port +
                ", name='" + name + '\'' +
                '}';
    }
}
