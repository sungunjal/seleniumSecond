package listner;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer{
	int count = 1;
	String max_count = "3";
	int maxCountFinel = Integer.parseInt(max_count);
	public boolean retry(ITestResult result) {
		if (count < maxCountFinel)
		{
			System.out.println("Retring failed Test Case "+ result.getName() + " with status "+ resultStatusName(result.getStatus())+ "Count is :" + count);
			count = count + 1;
			return true;
		}
		return false;
	}

	private String resultStatusName(int status) {
		String resultName = null;
		if (status == 1)
		{
			resultName = "SUCCESS";
		}
		if (status == 2)
		{
			resultName = "FAILURE";
		}
		if (status == 3)
		{
			resultName = "SKIP";
		}
		return resultName;
	}

}
