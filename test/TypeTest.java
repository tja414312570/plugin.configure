import java.util.Iterator;
import java.util.Map.Entry;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import com.typesafe.config.ConfigValue;

public class TypeTest {
	public static void main(String[] args) {
		Config config = ConfigFactory.load("plugin2.conf");
		config.disableTypeVerify();
		System.out.println(config.isList("list"));
		System.out.println(config.isConfigList("confs"));
		for(Object obj :config.getSimpleObjectList("list")){
			System.out.println("=======");
				System.out.println(obj.getClass()+"  "+obj);
		}
		for(Config conf :config.getConfigList("confs")){
			Iterator<Entry<String, ConfigValue>> iterator= conf.entrySet().iterator();
			System.out.println("********");
			while(iterator.hasNext()){
				Entry<String, ConfigValue> entry = iterator.next();
				System.out.println(entry.getKey()+"  "+entry.getValue().unwrapped());
			}
		}
		
		System.out.println(config.getSimpleObject("str")+" "+config.getSimpleObject("str").getClass()+" "+config.getType("str"));
		System.out.println(config.getSimpleObject("int")+" "+config.getSimpleObject("int").getClass()+" "+config.getType("int"));
		System.out.println(config.getSimpleObject("float")+" "+config.getSimpleObject("float").getClass()+" "+config.getType("float"));
		System.out.println(config.getSimpleObject("boolean")+" "+config.getSimpleObject("boolean").getClass()+" "+config.getType("boolean"));
		
	}
}
