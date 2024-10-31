/*
 * public static void read(){
    //Declaro la query
    String query = "SELECT * FROM user";
    //Inicializo una conección con la ruta de variables a implementar
    ConectionDB db = new ConectionDB();
    //Manejo de la respuesta
    try (ResultSet resultSet = db.executeQuery(query)) {
        while (resultSet.next()) {
            String name = resultSet.getString("name");
            String lastName = resultSet.getString("last_name");
            String dni = resultSet.getString("dni");
            String email = resultSet.getString("email");
            System.out.println("Nombre: " + name + " Apellido: " + lastName + " DNI: " + dni  + " Email: " + email);
        }
    } catch (Exception e) {
        System.err.println(e);
    }
    db.closeConectionDB();
}
 */
