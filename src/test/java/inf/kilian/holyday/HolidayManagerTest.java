package inf.kilian.holyday;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.junit.Test;

import de.jollyday.config.ChristianHoliday;
import de.jollyday.config.ChristianHolidayType;
import de.jollyday.config.Configuration;
import de.jollyday.config.Fixed;
import de.jollyday.config.Holidays;
import de.jollyday.config.Month;
import de.jollyday.config.ObjectFactory;

public class HolidayManagerTest {

  @Test
  public void test() throws JAXBException {

    ObjectFactory objectFactory = new ObjectFactory();
    Configuration configuration = objectFactory.createConfiguration();
    JAXBElement<Configuration> root = objectFactory.createConfiguration(configuration);

    ChristianHoliday christianHoliday = objectFactory.createChristianHoliday();
    christianHoliday.setType(ChristianHolidayType.EASTER);


    Fixed fixed = objectFactory.createFixed();
    fixed.setDay(1);
    fixed.setMonth(Month.DECEMBER);

    Holidays holidays = objectFactory.createHolidays();

    holidays.getChristianHoliday().add(christianHoliday);
    holidays.getFixed().add(fixed);

    configuration.setHolidays(holidays);



    JAXBContext context = JAXBContext.newInstance(Configuration.class);
    Marshaller jaxbMarshaller = context.createMarshaller();

    jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

    jaxbMarshaller.marshal(root, System.out);

  }

}
