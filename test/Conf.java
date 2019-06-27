import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import com.typesafe.config.ConfigValue;

public class Conf {
	public static void main(String[] args) {
		Config config = ConfigFactory.load("plugin.conf");
		config.disableTypeVerify();
		List<? extends Object> list = config.getValueListUnwrapper("plugins");
		for(Object obj : list){
			System.out.println(obj+"    = "+obj.getClass());
		}
		System.out.println(list.toString().replace(",", "\r\n"));
		System.out.println("===========");
		List<? extends Object> lists = config.getSimpleObjectList("plugins");
		System.out.println(lists.toString().replace(",", "\r\n"));
//		System.out.println(list);
//		for(Config conf : list){
//			System.out.println("============");
//			conf.allowKeyNull(true);
//			System.out.println(conf.getString("class"));
//			System.out.println(conf.getBoolean("signlton"));
//			System.out.println(conf.hasPath("field"));
//			System.out.println(conf.entrySet());
//			Object s ;
//			System.out.println("**********");
//			System.out.println(conf.getType("args"));
//			System.out.println(conf.isList("args"));
//			System.out.println(conf.isConfigList("args"));
//			if(conf.isList("field")){
//				s= conf.getConfigList("field");
//			}
//		}
	}
}
