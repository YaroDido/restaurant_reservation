import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;

public class V2_Create_Users_Table extends BaseJavaMigration {
    @Override
    public void migrate(Context context) throws Exception {
        context.getConnection().prepareStatement(
                "CREATE TABLE users (" +
                        "id INT AUTO_INCREMENT PRIMARY KEY, " +
                        "username VARCHAR(255) NOT NULL, " +
                        "email VARCHAR(255) NOT NULL, " +
                        "password VARCHAR(255) NOT NULL" +
                        ")"
        ).execute();
    }
}