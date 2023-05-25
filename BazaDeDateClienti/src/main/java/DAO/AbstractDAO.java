package DAO;
/**
 * This is an abstract class representing a Data Access Object (DAO).
 * It provides generic methods for performing common database operations.
 *
 * @param <T> The type of the entity handled by the DAO.
 * Creates a SELECT query string based on the provided field name.
 *
 * @param field The name of the field to filter the SELECT query by.
 * @return The generated SELECT query string.
 * Retrieves all entities of type T from the database.
 *
 * @return A list of all entities found in the database.
 * Retrieves an entity of type T from the database by its ID.
 *
 * @param id The ID of the entity to retrieve.
 * @return The entity with the specified ID, or null if not found.
 * Creates a list of objects of type T based on the provided result set.
 *
 * @param resultSet The result set containing the data.
 * @return A list of objects of type T created from the result set.
 */
import ConectareBD.Conectare;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AbstractDAO<T> {
    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());

    private final Class<T> type;

    @SuppressWarnings("unchecked")
    public AbstractDAO() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    }

    private String createSelectQuery(String field) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append(type.getSimpleName());
        sb.append(" WHERE " + field + " =?");
        return sb.toString();
    }

    public List<T> findAll() {
        List<T> resultList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = "SELECT * FROM " + type.getSimpleName();

        try {
            connection = Conectare.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();

            resultList = createObjects(resultSet);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findAll " + e.getMessage());
        } finally {
            Conectare.close(resultSet);
            Conectare.close(statement);
            Conectare.close(connection);
        }

        return resultList;
    }

    public T    findById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery("id");
        try {
            connection = Conectare.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            List<T> resultList = createObjects(resultSet);
            if (!resultList.isEmpty()) {
                return resultList.get(0);
            }

            //return createObjects(resultSet).get(0);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
        } finally {
            Conectare.close(resultSet);
            Conectare.close(statement);
            Conectare.close(connection);
        }
        return null;
    }
    private List<T> createObjects(ResultSet resultSet) {
        List<T> list = new ArrayList<T>();
        Constructor[] ctors = type.getDeclaredConstructors();
        Constructor ctor = null;
        for (int i = 0; i < ctors.length; i++) {
            ctor = ctors[i];
            if (ctor.getGenericParameterTypes().length == 0)
                break;
        }
        try {
            while (resultSet.next()) {
                ctor.setAccessible(true);
                T instance = (T)ctor.newInstance();
                for (Field field : type.getDeclaredFields()) {
                    String fieldName = field.getName();
                    Object value = resultSet.getObject(fieldName);
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance, value);
                }
                list.add(instance);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        return list;
    }
    private String generateInsertQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO ");
        sb.append(type.getSimpleName());
        sb.append(" (");

        Field[] fields = type.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            //if (!fields[i].getName().equals("id")) {
                sb.append(fields[i].getName());
                if (i != fields.length - 1) {
                    sb.append(", ");
                //}
            }
        }

        sb.append(") VALUES (");

        for (int i = 0; i < fields.length; i++) {
            //if (!fields[i].getName().equals("id")) {
                sb.append("?");
                if (i != fields.length - 1) {
                    sb.append(", ");
               // }
            }
        }

        sb.append(")");
        return sb.toString();
    }

    public T insert(T t) {
        System.out.println(generateInsertQuery());
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = Conectare.getConnection();
            String insertQuery = generateInsertQuery();
            statement = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);

            int parameterIndex = 1;
            Field[] fields = type.getDeclaredFields();

            for (Field field : fields) {
                field.setAccessible(true);
                Object value = field.get(t);
                statement.setObject(parameterIndex, value);
                parameterIndex++;
            }

            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating record failed, no rows affected.");
            }

            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                int generatedId = resultSet.getInt(1);
                Field idField = type.getDeclaredField("id");
                idField.setAccessible(true);
                idField.set(t, generatedId);
            }
            System.out.println(generateInsertQuery());
        } catch (SQLException | IllegalAccessException | NoSuchFieldException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:insert " + e.getMessage());
        } finally {
            Conectare.close(resultSet);
            Conectare.close(statement);
            Conectare.close(connection);
        }

        return t;
    }
    private String generateUpdateQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE ");
        sb.append(type.getSimpleName());
        sb.append(" SET ");

        Field[] fields = type.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            String fieldName = fields[i].getName();
            if (!fieldName.equals("id")) {
                sb.append(fieldName);
                sb.append(" = ?");
                if (i != fields.length - 1) {
                    sb.append(", ");
                }
            }
        }

        sb.append(" WHERE id = ?");
        return sb.toString();
    }

    public T update(T t) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = Conectare.getConnection();
            String updateQuery = generateUpdateQuery();
            statement = connection.prepareStatement(updateQuery);

            int parameterIndex = 1;
            Field[] fields = type.getDeclaredFields();

            for (Field field : fields) {
                if (!field.getName().equals("id")) {
                    field.setAccessible(true);
                    Object value = field.get(t);
                    statement.setObject(parameterIndex, value);
                    parameterIndex++;
                }
            }

            Field idField = type.getDeclaredField("id");
            idField.setAccessible(true);
            statement.setObject(parameterIndex, idField.get(t));

            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Updating record failed, no rows affected.");
            }
        } catch (SQLException | IllegalAccessException | NoSuchFieldException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:update " + e.getMessage());
        } finally {
            Conectare.close(statement);
            Conectare.close(connection);
        }

        return t;
    }
    private String generateDeleteQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE FROM ");
        sb.append(type.getSimpleName());
        sb.append(" WHERE id = ?");
        System.out.println(sb.toString());
        return sb.toString();
    }

    public void delete(T t) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = Conectare.getConnection();
            String deleteQuery = generateDeleteQuery();
            statement = connection.prepareStatement(deleteQuery);

            Field idField = type.getDeclaredField("id");
            idField.setAccessible(true);
            statement.setObject(1, idField.get(t));

            statement.executeUpdate();
        } catch (SQLException | IllegalAccessException | NoSuchFieldException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:delete " + e.getMessage());
        } finally {
            Conectare.close(statement);
            Conectare.close(connection);
        }
    }

    }
