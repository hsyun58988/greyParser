package hslangParser;

public class cfg {
	//hslang重要的一个类
	
	//解析word时的模式
	public static final int MODE_COMMON=1;//普通模式
	public static final int MODE_TEXT=2;//文本模式
	public static final int MODE_ESCAPE=3;//转义模式（在文本模式）
	
	//保留关键字（这些关键字在遇到空格时会忽略空格且关键字做一个单词
	public static String[] important_word= {"int","String","float","include","var"};
	//单独符号(在解析时会独自占一个单词)
	public static String[] u={ "+", "-", "*", "/", "(", ")", ",", ";" ,"="};
	//执行代码时的模式
	//public static final int MODE_COMMON=1;//普通模式
	public static final int MODE_BL=2;//变量模式
	public static final int MODE_FZ=3;//赋值模式
	
	//变量代号
	public static final int TYPE_INT=1;
	public static final int TYPE_STRING=2;
	public static final int TYPE_NULL=3;
	public static final int TYPE_OTHER=4;
}
