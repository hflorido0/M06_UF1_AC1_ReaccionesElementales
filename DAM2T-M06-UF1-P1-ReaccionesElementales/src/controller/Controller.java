package controller;
import java.util.ArrayList;

import dao.Reader;
import dao.Writter;
import model.Personaje;

public class Controller {
	private ArrayList<Personaje> personajes;
	private Writter wr;
	private Reader elementos;
	private Reader pjs;
	
	public void init() {
		this.personajes = new ArrayList<>();
		this.wr = new Writter();
		this.elementos = new Reader("Files/elementos.txt");
		this.pjs = new Reader("Files/personajes.txt");
		readCharacters();
		readElements();
		this.wr.closeFile();
	}
	
	private void readCharacters() {
		String line = "";
		while((line = this.pjs.read()) != null) {
			this.personajes.add(new Personaje(line.split(" ")[0], line.split(" ")[1]));
		}
		int countEle = 0;
		String[] elements = {"A", "F", "R", "T", "H", "V", "N"};
		int[] count = new int[elements.length];
		int pos = 0;
		for (String e : elements) {
			int c = 0;
			for (Personaje p : this.personajes) {
				if (p.getTipo().equalsIgnoreCase(e))
					c++;
			}
			count[pos] = c;
			if (c != 0) countEle++;
			pos++;
		}
		
		this.wr.write("Total de personajes: " + this.personajes.size());
		this.wr.write("Total de elementos utilizados: " + countEle);
		this.wr.write("Total de personajes por elemento: ");
		for (int i = 0; i < elements.length; i++) {
			if (count[i] > 0) {
				this.wr.write("\t- " + elements[i] + " : " + count[i]);
			}
		}
	}
	
	private void readElements() {
		int element1, element2;
		element1 = this.elementos.readChar();
		element2 = this.elementos.readChar();
		String reaccion = "";
		while(element1 != -1 && element2 != -1) {
			if ((reaccion = checkElementalReaction((char) element1, (char) element2)).equals("")) {
				element1 = element2;
				element2 = this.elementos.readChar();
			} else {
				this.wr.write(reaccion);
				element1 = this.elementos.readChar();
				element2 = this.elementos.readChar();
			}
		}
	}
	
	private String checkElementalReaction(char a, char b) {
		//System.out.println("" + a + b);
		switch (""+a+b) {
			case "FA":
			case "AF":
				return "Evaporicacion";
			case "FH":
			case "HF":
				return "Derretido";
			case "FR":
			case "RF":
				return "Sobrecarga";
			case "RA":
			case "AR":
				return "Electro-carga";
			case "RH":
			case "HR":
				return "Superconductor";
			case "AH":
			case "HA":
				return "Congelar";
			case "VF":
			case "FV":
			case "VR":
			case "RV":
			case "VA":
			case "AV":
			case "VH":
			case "HV":
				return "Torbellino";
			case "TF":
			case "FT":
			case "TR":
			case "RT":
			case "TA":
			case "AT":
			case "TH":
			case "HT":
				return "Cristalizar";
			case "FN":
			case "NF":
				return "Quemadura";
			default:
				return "";
		}
	}
}
