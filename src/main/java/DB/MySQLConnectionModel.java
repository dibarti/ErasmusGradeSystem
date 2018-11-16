package DB;

public class MySQLConnectionModel {
    private String username;
    private String password;
    private String host;
    private Integer port;
    private String dbSchema;
    private String connString;

    public MySQLConnectionModel(String username, String password, String host, Integer port, String dbSchema) {
        this.username = username;
        this.password = password;
        this.host = host;
        this.port = port;
        this.dbSchema = dbSchema;
        this.connString = "jdbc:mysql://"+host+":"+port+"/"+dbSchema;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getDbSchema() {
        return dbSchema;
    }

    public void setDbSchema(String dbSchema) {
        this.dbSchema = dbSchema;
    }

    public String getConnString() {
        return connString;
    }

    public void setConnString(String connString) {
        this.connString = connString;
    }
}
