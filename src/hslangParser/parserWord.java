package hslangParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
public class parserWord {
	public static ArrayList<String> analysis(String script) {//解析任意脚本
		String[] line=script.split("\n");//分割每个行
		ArrayList<String> word=new ArrayList<String>();//全部单词
		for(int i=0;i<line.length;i++) {
			String text="";//有效字符
			int mode=cfg.MODE_COMMON;//初始化
			String[] temp=line[i].split("");//逐字解析
			for(int a=0;a<temp.length;a++) {
				String text1=temp[a];//现行文字
				if(text1.equals("\"") && mode==cfg.MODE_COMMON){//在普通模式，如果遇到"则代表进入文本模式，如果已经进入则代表进入正常模式，如果为转义模式则把本符号计入有效字符
					mode=cfg.MODE_TEXT;//进入文本模式
					text=text+text1;
					continue;
				}
				if(text1.equals("\"") && mode==cfg.MODE_TEXT){//在文本模式
					mode=cfg.MODE_COMMON;//进入普通模式
					text=text+text1;
					word.add(text);//添加单词
					text="";//清空
					continue;
				}
				if(text1.equals("\"") && mode==cfg.MODE_ESCAPE){//在转义模式
					mode=cfg.MODE_TEXT;//进入文本模式
					text=text+text1;
					continue;
				}
				
				//如果遇到//则直接跳过本行，不在文本模式
				//判断到/时有三种情况，在文本模式则计入有效字符，文本数组下标大于当前下标并下一个字符也是/则跳出本行，其他的归为除号，将本符号当做单词
				if(text1.equals("/") && mode==cfg.MODE_TEXT){//在文本模式
					text=text+text1;
					continue;
				}
				if(text1.equals("/") && temp.length>=a && temp[a+1].equals("/")){//文本数组下标大于当前下标并下一个字符也是/
					break;  //直接结束for循环
				}
				if(text1.equals("/") && temp.length>=a && !temp[a+1].equals("/")){//文本数组下标大于当前下标并下一个字符不是/
					word.add(text);
					word.add(text1);
					text="";
					continue;
				}
				
				
				//如果遇到\且在文本模式，则进入转义模式，不把本字计入有效文本
				//如果已经进入转义模式则将本字符计入有效字符
				if(text1.equals("\\") && mode==cfg.MODE_TEXT){//在文本模式
					//进入转义模式
					mode=cfg.MODE_ESCAPE;
					continue;
				}
				if(text1.equals("\\") && mode==cfg.MODE_ESCAPE){//在转义模式
					text=text+text1;
					mode=cfg.MODE_TEXT;
					continue;
				}
				
				//如果为+-*（/）则将前面的做单词，本符号也做单词，不在文本模式
				if(text1.equals("+") && mode!=cfg.MODE_TEXT){
					word.add(text);
					word.add(text1);
					text="";
					continue;
				}
				if(text1.equals("-") && mode!=cfg.MODE_TEXT){
					word.add(text);
					word.add(text1);
					text="";
					continue;
				}
				if(text1.equals("*") && mode!=cfg.MODE_TEXT){
					word.add(text);
					word.add(text1);
					text="";
					continue;
				}
				if(text1.equals("(") && mode!=cfg.MODE_TEXT){
					word.add(text);
					word.add(text1);
					text="";
					continue;
				}
				if(text1.equals("/") && mode!=cfg.MODE_TEXT){
					word.add(text);
					word.add(text1);
					text="";
					continue;
				}
				if(text1.equals(")") && mode!=cfg.MODE_TEXT){
					word.add(text);
					word.add(text1);
					text="";
					continue;
				}
				if(text1.equals(";") && mode!=cfg.MODE_TEXT){
					word.add(text);
					word.add(text1);
					text="";
					continue;
				}
				if(text1.equals("=") && mode!=cfg.MODE_TEXT){
					word.add(text);
					word.add(text1);
					text="";
					continue;
				}
				if(text1.equals(",") && mode!=cfg.MODE_TEXT){
					word.add(text);
					word.add(text1);
					text="";
					continue;
				}
				
				//如果为.则判断有效字符是否不为数字开头，否则把前面的做单词，本符号也做单词，不在文本模式
				//如果为.且为数字开头且都是数字（不含.），本符号做有效字符
				if(text1.equals(".") && mode!=cfg.MODE_TEXT && Tools.isNum(text)!=true) {//引用了类
					word.add(text);
					word.add(text1);
					text="";
					continue;
				}
				if(text1.equals(".") && mode!=cfg.MODE_TEXT && Tools.isNum(text)==true) {//浮点整数
					text=text+text1;
					continue;
				}
				
				
				//如果为空格，且不在文本模式，且有效字符为关键字，则把有效字符做单词，忽略本字符
				if(text1.equals(" ") && mode!=cfg.MODE_TEXT && Tools.isImportantWord(text)) {
					word.add(text);
					text="";
					continue;
				}
				//如果为空格，且不在文本模式，且有效字符不为关键字，则忽略本字符
				if(text1.equals(" ") && mode!=cfg.MODE_TEXT && !Tools.isImportantWord(text)) {
					continue;
				}
				
				//如果为空格，且在文本模式，则把有效字符做单词，忽略本字符
				text=text+text1;//没什么就当做有效字符
			}
		}
		return word;
	}
}
