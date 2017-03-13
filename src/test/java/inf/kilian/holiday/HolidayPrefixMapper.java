package inf.kilian.holiday;

import java.util.HashMap;
import java.util.Map;

import com.sun.xml.bind.marshaller.NamespacePrefixMapper;

public class HolidayPrefixMapper extends NamespacePrefixMapper {

  private Map<String, String> namespaceMap = new HashMap<>();

  public HolidayPrefixMapper() {
    namespaceMap.put("http://www.w3.org/2001/XMLSchema-instance", "xsi");
    namespaceMap.put("http://www.example.org/Holiday", "tns");
  }

  @Override
  public String getPreferredPrefix(String namespaceUri, String suggestion, boolean requirePrefix) {
    return namespaceMap.getOrDefault(namespaceUri, suggestion);
  }
}
