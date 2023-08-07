import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;
public class V1_Create_Users_Table extends BaseJavaMigration {
    @Override
    public void migrate(Context context) throws Exception {
        context.getConnection().prepareStatement(
                "CREATE TABLE reservations (" +
                        "id INT AUTO_INCREMENT PRIMARY KEY," +
                        "customer_name VARCHAR(255) NOT NULL, " +
                        "quantity_of_customers INT NOT NULL, " +
                        "table_number INT NOT NULL," +
                        "reservation_date_time TIMESTAMP NOT NULL" +
                        ")"
        ).execute();
    }
}