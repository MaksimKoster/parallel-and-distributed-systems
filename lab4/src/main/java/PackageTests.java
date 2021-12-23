import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class PackageTests {
    public static final String FUNCTION_NAME = "functionName";
    public static final String JS_SCRIPT = "jsScript";
    public static final String TESTS = "tests";
    public static final String EXPECTED_RESULT = "expectedResult";
    public static final String PARAMS = "params";
    public static final String TEST_NAME = "testName";
    public static final String PACKAGE_ID = "packageID";

    private int packageid;
    private String jsScript;
    private String functionName;
    private ArrayList<Test> tests;


    public static class Test{
        private Object[] params;
        private String testName;
        private String expectedResult;

        @JsonCreator
        Test(@JsonProperty(TEST_NAME) String testName, @JsonProperty(EXPECTED_RESULT) String expectedResult,
             @JsonProperty(PARAMS) Object[] params){
            this.testName = testName;
            this.expectedResult = expectedResult;
            this.params = params;
        }

        public String getExpectedResult(){
            return expectedResult;
        }

        public String getTestName(){
            return testName;
        }

        public Object[] getParams() {
            return params;
        }
    }
}
