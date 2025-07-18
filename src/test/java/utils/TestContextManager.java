package utils;

public class TestContextManager implements BaseObjectManager {

    private final WebObjectManager webObjectManager;
    private final ApiObjectManager apiObjectManager;

    public TestContextManager() {
        this.webObjectManager = new WebObjectManager();
        this.apiObjectManager = new ApiObjectManager();
    }

    public WebObjectManager web() {
        return webObjectManager;
    }

    public ApiObjectManager api() {
        return apiObjectManager;
    }
}

