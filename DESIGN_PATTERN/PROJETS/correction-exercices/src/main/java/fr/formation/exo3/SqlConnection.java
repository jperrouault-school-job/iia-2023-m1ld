package fr.formation.exo3;

import lombok.Getter;
import lombok.ToString;

@Getter @ToString
public class SqlConnection implements Connection {
    private String host;
    private int port;
    private String username;
    private String password;
    
    public static class Builder {
        private String host;
        private int port;
        private String username;
        private String password;

        public SqlConnection build() {
            SqlConnection connection = new SqlConnection();

            connection.host = this.host;
            connection.port = this.port;
            connection.username = this.username;
            connection.password = this.password;

            return connection;
        }
        
        public Builder host(String host) {
            this.host = host;
            return this;
        }
        
        public Builder port(int port) {
            this.port = port;
            return this;
        }
        
        public Builder username(String username) {
            this.username = username;
            return this;
        }
        
        public Builder password(String password) {
            this.password = password;
            return this;
        }
    }
}
