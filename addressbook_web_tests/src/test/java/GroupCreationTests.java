import org.junit.jupiter.api.Test;

public class GroupCreationTests extends TestBase{

    @Test
    public void canCreateGroup() {
        openGroupPage();
        createGroup("Group name", "Group header", "Group footer");
    }

    @Test
    public void canCreateGroupWithEmptyName() {
        openGroupPage();
        createGroup("", "", "");
    }
}
