package hslangParser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
public class Tools {
	public static boolean isNum(String text){
		String[] textArray=text.split("");
		for(int i=0;i<textArray.length;i++) {
			if(!textArray[i].equals("1"))
				if(!textArray[i].equals("2"))
					if(!textArray[i].equals("3"))
						if(!textArray[i].equals("4"))
							if(!textArray[i].equals("5"))
								if(!textArray[i].equals("6"))
									if(!textArray[i].equals("7"))
										if(!textArray[i].equals("8"))
											if(!textArray[i].equals("9"))
												if(!textArray[i].equals("0"))
													return false;
		}
		return true;
	}
	public static String readTxtFile(String filePath) {  
		String content="";
	    try {  
	        String encoding = "UTF-8";  
	        File file = new File(filePath);  
	        if (file.isFile() && file.exists()) {  
	            InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);  
	            BufferedReader bufferedReader = new BufferedReader(read);  
	            String lineTxt = null;  
	            while ((lineTxt = bufferedReader.readLine()) != null) {  
	            	content=content+lineTxt+"\n";
	            }  
	            read.close();  
	        } else {  
	            System.out.println("找不到指定的文件");  
	        }  
	    } catch (Exception e) {  
	        System.out.println("读取文件内容出错");  
	        e.printStackTrace();  
	    }  
	    //System.out.println(content);
	    return content;  
	}  
	public static boolean isImportantWord(String text) {
		for(int i=0;i<cfg.important_word.length;i++) {
			if(text.equals(cfg.important_word[i])) {
				return true;
			}
		}
		return false;
	}
	public static String setWord(String word) {
		if(word.substring(0, 1).equals(" ")) {
			//看来需要修饰了
			return word.substring(1,word.length());
		}
		return word;
	}
	public static void throwError(String errorText,int line) {
		System.err.println("在处理脚本时发生错误！因此解释器未能执行本行");
		System.err.println("在第"+line+"行出现");
		System.err.println("hslang对本次错误的解释："+errorText);
		System.err.println("本行会直接跳过，不会影响下面的代码");
	}
	public static int ifType(String blName) {
		if(blName.substring(0,5).equals("[int]")) {
			return cfg.TYPE_INT;
		}
		if(blName.substring(0,8).equals("[String]")) {
			return cfg.TYPE_STRING;
		}
		return cfg.TYPE_OTHER;
	}
	public static boolean isString(String text) {
		if(text.substring(0,1).equals("\"") && text.substring(text.length()-1,text.length()).equals("\"")) {
			return true;
		}
		return false;
	}
	public static String getTrueString(String text) {
		if(text.length()>=8 && ifType(text)==cfg.TYPE_STRING) {
			return text.substring(9, text.length()-1);
		}
		return text.substring(1, text.length()-1);
	}
	public static String removeType(String text) {
		//System.out.println(text);
		if(ifType(text)==cfg.TYPE_INT) {
			return text.substring(5, text.length());
		}
		if(ifType(text)==cfg.TYPE_STRING) {
			return text.substring(8, text.length());
		}
		return "";
	}
	
}
