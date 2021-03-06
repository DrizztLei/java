import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONObject;

public class BaiduUniversalOCR
{
	private static final Long OCR_MAX_IMAGE_SIZE = 4194304L; // 4 * 1024 * 1024

	private static final Integer OCR_MIN_IMAGE_SIDE_LENGTH = 15;
	private static final Integer OCR_MAX_IMAGE_SIDE_LENGTH = 4096;

	private static final String APPID = "10480494";
	private static final String APPKEY = "ND4baRV6m8gO08a2APlIRCHh";
	private static final String SECRETKEY = "rn1PecfUm7RUCih4gtCrmLcj7QVBKZdT";
	private static String img = "/home/elvis/Documents/medical_ocr/DATA/ALL_DATA/IMG_20171204_160833R.jpg";
	private static String path = "/home/elvis/Documents/medical_ocr/DATA/ALL_DATA/laboratory_sheet/test/";

	private static String[] pathSet = new String[]
	{ 
			"/home/elvis/Documents/medical_ocr/DATA/ALL_DATA/laboratory_sheet/test/segmentation/",
			// "/home/elvis/Documents/medical_ocr/DATA/ALL_DATA/medical_history/test/temp/segmentation/",
			// "/home/elvis/Documents/medical_ocr/DATA/ALL_DATA/pathological_report/test/segmentation/"
			// "/home/elvis/Documents/medical_ocr/DATA/ALL_DATA/surgery_record/test/segmentation/"
	};

	private static boolean init = false;
	private static AipOcr client;

	public static void main(String[] args)
	{
		init();
		JSONObject returnJSON;

		for (int i = 0; i < pathSet.length; i++)
		{
			String directory = pathSet[i];
			ArrayList<String> pictureSet = getFileArrayList(directory);

			for (int j = 0; j < pictureSet.size(); j++)
			{
				String pictureName = pictureSet.get(j);
				System.out.println("process the picture : " + pictureName);

				returnJSON = processOCR(pictureName);
				int pointPosition = pictureName.lastIndexOf('.');
				String JSONFileName = pictureName.substring(0, pointPosition) + ".json";
				System.out.println("process the json to : " + JSONFileName);
				System.out.println(returnJSON.toString(2));
				saveJSONFile(returnJSON, JSONFileName);
			}
		}
	}

	public static void saveJSONFile(JSONObject object, String outputFile)
	{
		File outFile = new File(outputFile);
		if (!outFile.exists())
		{
			try
			{
				outFile.createNewFile();
			} catch (IOException e)
			{
				e.printStackTrace();
				System.err.println("FILE : " + outputFile);
				System.exit(-1);
			}
		}

		try
		{
			FileOutputStream fileOutputStream = new FileOutputStream(outFile);
			fileOutputStream.write(object.toString().getBytes());
			fileOutputStream.close();
			System.out.println("SAVE SUCCEED");
		} catch (Exception e)
		{
			System.err.println("ERROR WHEN PROCESS : " + outputFile);
			e.printStackTrace();
		}
	}

	public static ArrayList<String> getFileArrayList(String path)
	{
		ArrayList<String> fileArrayList = new ArrayList<>();
		File file = new File(path);
		File[] fileArray = file.listFiles();
		for (int i = 0; i < fileArray.length; i++)
		{
			if (fileArray[i].isFile())
			{
				String fileName = fileArray[i].getAbsolutePath();
				if (isPicture(fileName))
				{
					fileArrayList.add(fileName);
				}

			}
		}
		return fileArrayList;
	}

	public static boolean isPicture(String name)
	{
		if (name.toLowerCase().endsWith("jpg") || name.toLowerCase().endsWith("png")
				|| name.toLowerCase().endsWith("bmp") || name.toLowerCase().endsWith("jepg"))
		{
			return true;
		}
		return false;
	}

	private static JSONObject processOCR(String pictureName)
	{
		// receipt
		// general
		// basicAccurateGeneral
		JSONObject returnValue = client.basicAccurateGeneral(pictureName, new HashMap<String, String>());
		return returnValue;
	}

	private static void init()
	{
		if (init == false)
		{
			client = new AipOcr(APPID, APPKEY, SECRETKEY);
			client.setConnectionTimeoutInMillis(2000);
			client.setSocketTimeoutInMillis(60000);
			init = true;
		}
	}
}