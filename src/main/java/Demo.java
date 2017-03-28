import generated.Testas;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.util.StatusPrinter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;


/**
 * Created by Bendras on 2017-03-28. Demo
 */
public class Demo {

    private static final String FILE_NAME = "AK.xml";
    private static final String FILE_NAME_FROM = "AK2.xml";

    public static void main(String[] args) {

        Logger logger = LoggerFactory.getLogger("Demo");
        logger.debug("Hello world.");
        // print internal state
        LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
        StatusPrinter.print(lc);

        Testas t = new Testas();
        Testas.Asmenys persons = new Testas.Asmenys();
        Testas.Asmenys.Asmuo person = new Testas.Asmenys.Asmuo();
        Testas.Asmenys.Asmuo person2 = new Testas.Asmenys.Asmuo();

        person.setVardas("Andrius");
        person.setPavarde("Kulikauskas");
        person2.setVardas("Inga");
        person2.setPavarde("Kulikauskiene");

        persons.setCnt((byte) 2);
        persons.setFoo((byte) 7);
        persons.getAsmuo().add(person);
        persons.getAsmuo().add(person2);

        t.setAsmenys(persons);

        jaxbObjectToXML(t);

        Testas t2 = jaxbXMLToObject();
        logger.debug("Pavarde :{}", t2.getAsmenys().getAsmuo().get(1).getPavarde());


    }

    private static void jaxbObjectToXML(Testas tst) {

        try {
            JAXBContext context = JAXBContext.newInstance(Testas.class);
            Marshaller m = context.createMarshaller();
            //for pretty-print XML in JAXB
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            // Write to System.out for debugging
            m.marshal(tst, System.out);

            // Write to File
            m.marshal(tst, new File(FILE_NAME));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    private static Testas jaxbXMLToObject() {
        try {
            JAXBContext context = JAXBContext.newInstance(Testas.class);
            Unmarshaller un = context.createUnmarshaller();
            Testas tst = (Testas) un.unmarshal(new File(FILE_NAME_FROM));
            return tst;
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

}
