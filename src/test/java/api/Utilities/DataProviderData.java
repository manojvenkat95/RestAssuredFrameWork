package api.Utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviderData {

	@DataProvider(name = "Data")
	public static Object[][] getUser() throws IOException {
		String filepath = ".\\src\\test\\resources\\UserData.xlsx";
		XLUtility xl = new XLUtility(filepath);

		int totalRows = xl.getRowCount("Users");
		int totalCols = xl.getCellCount("Users", 1);
		Object[][] userData = new Object[totalRows][totalCols];

		for (int i = 1; i <= totalRows; i++) {
			for (int j = 0; j < totalCols; j++) {
				userData[i - 1][j] = xl.getCellData("Users", i, j);
			}
		}

		return userData;
	}

	@DataProvider(name = "UserName")
	public static Object[] getUserName() throws IOException {
		String filepath = ".\\src\\test\\resources\\UserData.xlsx";
		XLUtility xl = new XLUtility(filepath);

		int totalrows = xl.getRowCount("Users");
		String UserName[] = new String[totalrows];
		for (int i = 1; i <= totalrows; i++) {
			UserName[i - 1] = xl.getCellData("Users", i, 0);
		}

		return UserName;
	}
}
