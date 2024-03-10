import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;

public class ContactRemovalTests extends TestBase{

    @Test
    public void canRemovalContactTests() {
        if (isContactPresent()) {
            createContact(new ContactData());
        }
        removeContact();
    }
}

