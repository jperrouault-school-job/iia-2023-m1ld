package fr.formation.exo3;

public class Application {
    public static void main(String[] args) {
        Connection connection = ConnectionFactory.createConnection(Type.NOSQL, "localhost", "user", "password");

        System.out.println(connection);
    }
}
