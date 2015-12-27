package lambda.typen;
import java.util.*;
public class Umgebung implements Cloneable {
	private Map<String,Typ>links;
	public Umgebung() {
		links=new TreeMap<String,Typ>();
	}
	@Override
	public Umgebung clone() {
		Umgebung ret=new Umgebung();
		ret.links.putAll(links);
		return ret;
	}
	public Umgebung fügeHinzu(String name,Typ typ) {
		Umgebung ret=clone();
		ret.links.put(name,typ);
		return ret;
	}
	public void erweitereUmKonstante(String name,Typ typ) {
		links.put(name,typ);
	}
	public void erweitereUmUmgebung(Umgebung e) {
		links.putAll(e.links);
	}
	public Typ schliesseAus(String name) {
		return links.get(name);
	}
}