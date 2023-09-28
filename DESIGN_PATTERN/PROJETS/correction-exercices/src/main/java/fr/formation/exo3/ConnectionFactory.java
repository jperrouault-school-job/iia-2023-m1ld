package fr.formation.exo3;

public class ConnectionFactory {
    private ConnectionFactory() { }

    public static Connection createConnection(Type type, String host, String username, String password) {
        return switch (type) {
            case SQL -> new SqlConnection.Builder()
                .host(host)
                .port(3306)
                .username(username)
                .password(password)
                .build();
            
            case NOSQL -> new NosqlConnection.Builder()
                .host(host)
                .port(27017)
                .username(username)
                .password(password)
                .build();
        };
    }
}
