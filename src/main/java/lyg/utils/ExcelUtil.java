package lyg.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * 
* @Description:操作excel工具类  
* @author XiaoGang     
* @date 2018-8-1 上午8:51:31  
* @version V1.0
 */
public class ExcelUtil {
	public static final String VALUE_TYPE_STRING="String";
	public static final String VALUE_TYPE_DATE="Date";
	public static final String VALUE_TYPE_BOOLEAN="Boolean";
	public static final String VALUE_TYPE_NUMBER="Number";

	
	private Workbook workbook;
	private Sheet sheet;
	private FileInputStream inputStream;
	private FileOutputStream outputStream;
	private String filePath;//文件路径
	private String sheetName;//标签名
	private int sheetNo;//标签下标，默认为0
	private int maxRowNo;//最大行数
	private int maxColNo;//最大列数
	
	/**
	 * 根据文件路径打开文件，并得到对应标签名的sheet
	 * @param filePath	excel文件路径
	 * @param sheetName	表名
	 * @throws Exception
	 */
	public void openFile(String filePath,String sheetName) throws Exception{
		if(filePath.matches("^.+\\.(?i)((xls)|(xlsx))$")){//判断是否excel文档
			boolean is03Excel = filePath.matches("^.+\\.(?i)(xls)$");
			this.filePath=filePath;
			this.sheetName=sheetName;
			inputStream = new FileInputStream(filePath);
			workbook = is03Excel?new HSSFWorkbook(inputStream):new XSSFWorkbook(inputStream);
			sheet = workbook.getSheet(sheetName);
			if(sheet==null){
				sheet=workbook.createSheet(sheetName);
			}
			maxRowNo=sheet.getLastRowNum()+1;
			maxColNo=sheet.getRow(0).getPhysicalNumberOfCells();
		}else{
			System.out.println("文件不是excel文件");
		}
	}
	
	/**
	 * 根据文件路径打开文件，默认取第一个sheet
	 * @param filePath	excel文件路径
	 * @throws Exception
	 */
	public void openFile(String filePath) throws Exception{
		if(filePath.matches("^.+\\.(?i)((xls)|(xlsx))$")){//判断是否excel文档
			boolean is03Excel = filePath.matches("^.+\\.(?i)(xls)$");
			this.filePath=filePath;
			inputStream = new FileInputStream(filePath);
			workbook = is03Excel?new HSSFWorkbook(inputStream):new XSSFWorkbook(inputStream);
			sheet = workbook.getSheetAt(sheetNo);
			maxRowNo=sheet.getLastRowNum()+1;
			maxColNo=sheet.getRow(0).getPhysicalNumberOfCells();
		}else{
			System.out.println("文件不是excel文件");
		}
	}
	
	/**
	 * 获取excel单元格的值
	 * @param filePath 	excel文件路径
	 * @param sheetName	表名
	 * @param cellNO	单元格编号：A1
	 * @param valueType	单元格数据类型：常量
	 * @return			单元格值
	 * @throws Exception
	 */
	public Object readCellValue(String cellNO,String valueType) throws Exception{
		int[] rowAndColNum = getRowAndColNum(cellNO);
		int rowNum=rowAndColNum[1];
		int colNum=rowAndColNum[0];
		Object result=readCellValue(rowNum,colNum,valueType);
		return result;	
	}
	
	/**
	 * 获取excel单元格的值
	 * @param filePath 	excel文件路径
	 * @param sheetName	表名
	 * @param rowNum	行索引号
	 * @param colNum	列索引号
	 * @param valueType	单元格数据类型：常量
	 * @return			单元格值
	 * @throws Exception
	 */
	public Object readCellValue(int rowNum,int colNum,String valueType) throws Exception{
		Object result;
		Row row=sheet.getRow(rowNum);
		Cell cell = row.getCell(colNum);
		if(cell==null){
			return "";
		}
		switch (valueType){
			case VALUE_TYPE_STRING:
				result=cell.getStringCellValue();
				break;
			case VALUE_TYPE_NUMBER:
				result=cell.getNumericCellValue();
				break;
			case VALUE_TYPE_DATE:
				result=cell.getDateCellValue();
				break;
			case VALUE_TYPE_BOOLEAN:
				result=cell.getBooleanCellValue();
				break;
			default:
				result=null;
				System.out.println("填写的数据类型有误");
		}
		return result;	
	}	
	

	/**
	 * 写入单元格数据
	 * @param filePath	excel路径
	 * @param sheetName	表名
	 * @param cellNO	单元格编号：A1
	 * @param cellValue	单元格值
	 * @throws Exception 
	 */
	public void writeCellValue(String cellNO,Object cellValue) throws Exception{
		int[] rowAndColNum = getRowAndColNum(cellNO);
		int rowNum=rowAndColNum[1];
		int colNum=rowAndColNum[0];
		writeCellValue(rowNum,colNum,cellValue);
	}
	
/**
	 * 写入单元格数据
	 * @param filePath	excel路径
	 * @param sheetName	表名
	 * @param rowNum	行索引号
	 * @param colNum	列索引号
	 * @param cellValue	单元格值
	 * @throws Exception 
	 */
	public void writeCellValue(int rowNum,int colNum,Object cellValue) throws Exception{
		Row row=sheet.getRow(rowNum);
		if(row==null){
			row=sheet.createRow(rowNum);
		}
		Cell cell = row.getCell(colNum);
		if(cell==null){
			cell=row.createCell(colNum);
		}
		//cell.setCellValue(cellValue.toString());
		//获取数据类型，根据类型设置对应的值
		if(Integer.class.isInstance(cellValue)){
			cell.setCellValue((Integer)cellValue);		
		}
		else if(Double.class.isInstance(cellValue)){
			cell.setCellValue((Double) cellValue);
		}else if(Date.class.isInstance(cellValue)){
			cell.setCellValue((Date) cellValue);
		}else if(Boolean.class.isInstance(cellValue)){
			cell.setCellValue((Boolean) cellValue);
		}else if(String.class.isInstance(cellValue)){
			cell.setCellValue((String) cellValue);
		}else{
			System.out.println("暂不支持数据类型");
		}
		outputStream=new FileOutputStream(filePath);
		workbook.write(outputStream);
	}
	
	/**
	 * 关闭文件
	 * @throws Exception
	 */
	public void closeFile() throws Exception{
		workbook.close();
		if(inputStream!=null){
			inputStream.close();
		}
		if(outputStream!=null){
			outputStream.close();
		}
	}
	
	/**
	 * 根据单元格编号得到行索引值和列索引值
	 * @param CellNO	单元格编号
	 * @return 
	 */
	public int[] getRowAndColNum(String cellNO){
		cellNO=cellNO.toUpperCase();
		int colIndex=0;
		int i=0;
		for(;i<cellNO.length();i++){
			char c=cellNO.charAt(i);
			if(c<65||c>90){
				break;
			}
		}
		String colNO = cellNO.substring(0, i);//AA12解析后得到AA
		String rowNO = cellNO.substring(i,cellNO.length());
		char[] charArray = colNO.toCharArray();
		int count=0;
		for(int j=charArray.length-1;j>=0;j--){
			colIndex+=(charArray[j]-65+1)*Math.pow(26,count);//1*26^1+1*26^0    个位数多加了1
			count++;
		}
		colIndex=colIndex-1;
		int rowIndex=Integer.parseInt(rowNO)-1;////1*26^1+1*26^0    个位数多加了1需要去掉
		return new int[]{colIndex,rowIndex};
	}

	public int getMaxRowNo() {
		return maxRowNo;
	}

	public void setMaxRowNo(int maxRowNo) {
		this.maxRowNo = maxRowNo;
	}

	public int getMaxColNo() {
		return maxColNo;
	}

	public void setMaxColNo(int maxColNo) {
		this.maxColNo = maxColNo;
	}
}
