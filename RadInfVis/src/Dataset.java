import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Dataset {

	String source;
	Activity[] activities;
	Human[] humans;

	public Dataset(String s) {

		humans = new Human[4];
		activities = new Activity[300];
		int x = 0;

		try {
			File stocks = new File(s);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(stocks);
			doc.getDocumentElement().normalize();

			NodeList nList = doc.getElementsByTagName("person");

			for (int i = 0; i < nList.getLength(); i++) {
				Node node = nList.item(i);
				Element e = (Element) node;
				humans[i] = new Human(e.getAttribute("name"));

				for (int j = 0; j < e.getElementsByTagName("tag").getLength(); j++) {
					Element day = (Element) e.getElementsByTagName("tag").item(
							j);
					String sDay = day.getAttribute("name");

					for (int k = 0; k < day.getElementsByTagName("activity")
							.getLength(); k++) {
						Element a = (Element) e
								.getElementsByTagName("activity").item(k);
						String begin = a.getElementsByTagName("begin").item(0)
								.getTextContent();
						// String bTime =
						// a.getElementsByTagName("bTime").item(1).getTextContent();
						String end = a.getElementsByTagName("end").item(0)
								.getTextContent();
						// String eTime =
						// a.getElementsByTagName("eTime").item(0).getTextContent();
						String category = a.getElementsByTagName("category")
								.item(0).getTextContent();
						String transport = a.getElementsByTagName("transport")
								.item(0).getTextContent();
						String duration = a.getElementsByTagName("duration")
								.item(0).getTextContent();
						String distance = a.getElementsByTagName("distance")
								.item(0).getTextContent();

						this.activities[x] = new Activity(humans[i], sDay,
								category, begin, end, transport, duration,
								distance);
						x++;
						System.out.println(sDay);

					}
				}
			}

		}

		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Human getPerson(String name) {
		Human h = null;
		for (int i = 0; i < humans.length; i++) {
			if (humans[i].getName() == name)
				h = humans[i];
		}
		return h;
	}

	public Human[] getPersons() {
		return this.humans;
	}

	public Activity[] getPersonActivities(String name) {
		Activity[] act = new Activity[100];
		int i = 0;
		for (Activity a : this.activities) {
			if (a.getHuman().getName() == name) {
				act[i] = a;
				++i;
			}
		}
		return act;
	}

	public Activity[] getActivities() {
		return this.activities;
	}

}
