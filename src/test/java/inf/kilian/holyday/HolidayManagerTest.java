package inf.kilian.holyday;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.junit.Test;

import de.jollyday.HolidayManager;
import de.jollyday.ManagerParameters;
import de.jollyday.config.ChristianHoliday;
import de.jollyday.config.ChristianHolidayType;
import de.jollyday.config.Configuration;
import de.jollyday.config.Fixed;
import de.jollyday.config.Holidays;
import de.jollyday.config.Month;
import de.jollyday.config.ObjectFactory;

public class HolidayManagerTest {

  @Test
  public void test() throws JAXBException, MalformedURLException {

    ObjectFactory objectFactory = new ObjectFactory();
    Configuration configuration = objectFactory.createConfiguration();
    JAXBElement<Configuration> root = objectFactory.createConfiguration(configuration);

    ChristianHoliday christianHoliday = objectFactory.createChristianHoliday();
    christianHoliday.setType(ChristianHolidayType.EASTER);


    Fixed fixed = objectFactory.createFixed();
    fixed.setDay(1);
    fixed.setMonth(Month.DECEMBER);
    fixed.setDescriptionPropertiesKey("MEU_FERIADO");

    Holidays holidays = objectFactory.createHolidays();

    holidays.getChristianHoliday().add(christianHoliday);
    holidays.getFixed().add(fixed);

    configuration.setHolidays(holidays);
    configuration.setHierarchy("br");
    configuration.setDescription("Brazil");


    JAXBContext context = JAXBContext.newInstance(Configuration.class);
    Marshaller jaxbMarshaller = context.createMarshaller();
    jaxbMarshaller.setProperty("com.sun.xml.bind.namespacePrefixMapper", new HolidayPrefixMapper());
    jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
    jaxbMarshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION,
        "http://www.example.org/Holiday /Holiday.xsd");


    jaxbMarshaller.marshal(root, System.out);
    jaxbMarshaller.marshal(root, new File("src/test/resources/generated.xml"));


    HolidayManager manager = HolidayManager
        .getInstance(ManagerParameters.create(new URL("file:src/test/resources/generated.xml")));



    manager.getHolidays(2017).stream().forEach(h -> {
      System.out.println(h.getDescription());
    });

  }

}
