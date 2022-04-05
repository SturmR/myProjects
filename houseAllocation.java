

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.Iterator;
import java.util.Locale;




public class project1main {
	
	
	public static void main(String[] args) throws FileNotFoundException {
		
		//this treemap will contain non-housed students
		TreeMap<Integer, student> studentTreeMap = new TreeMap<Integer, student>();
		
		//this treemap will contain houses which are filled up
		TreeMap<Integer, house> occupiedTreeMap = new TreeMap<Integer, house>();

		//this treemap will contain housed students
		TreeMap<Integer, student> allocatedTreeMap = new TreeMap<Integer, student>();
		
		//this treemap will contain free houses
		TreeMap<Integer, house> houseTreeMap = new TreeMap<Integer, house>();
		
		//this treemap will contain students who never got housed
		TreeMap<Integer, student> outputTreeMap = new TreeMap<Integer, student>();
		
		Scanner in = new Scanner(new File(args[0]));
		PrintStream out = new PrintStream(new File(args[1]));
		
		in.useLocale(Locale.US);
		
		while (in.hasNext()) {
			
			String N = in.next();
			
			//this if section creates a house object when it reads an "h"
			if (N.equals("h")) {
				int id = in.nextInt();
				int duration = in.nextInt();
				double rating = in.nextDouble();
				house ho = new house(id, duration, rating);
				int dur = ho.getFullDuration();
				if (dur == 0) {
					houseTreeMap.put(ho.getId(), ho);
				}
				else
					occupiedTreeMap.put(ho.getId(), ho);
			}
			
			//this if section creates a student object when it reads an "s"
			else if (N.equals("s")) {
				int id = in.nextInt();
				String name = in.next();
				int duration = in.nextInt();
				double rating = in.nextDouble();
				student st = new student(id, name, duration, rating);
				studentTreeMap.put(st.getId(), st);
			}
		
		}
		
		
		//this is the loop to be iterated through 8 semesters
		for (int i=0; i<8; i++) {
			
			//this iteration checks for houses which freed up when the student inside graduated
			Iterator<Integer> itr = occupiedTreeMap.keySet().iterator();
			while (itr.hasNext()) {
				Integer houseKey = itr.next();
				house house = occupiedTreeMap.get(houseKey);
				if (house.getFullDuration() == 0) {
					houseTreeMap.put(houseKey, house);
					itr.remove();
				}
			}
			
			//this iteration checks non-housed students whether they graduated or not
			Iterator<Integer> itr1 = studentTreeMap.keySet().iterator();
			while (itr1.hasNext()) {
				Integer studentKey = itr1.next();
				student student = studentTreeMap.get(studentKey);
				if(student.getRemainingDuration() == 0) {
					outputTreeMap.put(student.getId(), student);
					itr1.remove();
				}
			}
			
			//this iteration checks housed students whether they graduated or not
			Iterator<Integer> itr2 = allocatedTreeMap.keySet().iterator();
			while (itr2.hasNext()) {
				Integer studentKey = itr2.next();
				student student = allocatedTreeMap.get(studentKey);
				if(student.getRemainingDuration() == 0) {
					itr2.remove();
				}
			}
			
			//this iteration is the allocation process
			Iterator<Integer> itr3 = houseTreeMap.keySet().iterator();
			while (itr3.hasNext()) {
				Integer houseKey = itr3.next();
				house house = houseTreeMap.get(houseKey);
				double rating = house.getRating();
				Iterator<Integer> itr4 = studentTreeMap.keySet().iterator();
				while (itr4.hasNext()) {
					Integer studentKey = itr4.next();
					student student = studentTreeMap.get(studentKey);
					if (rating >= student.getRequestedRating()) {
						house.setFullDuration(student.getRemainingDuration());
						occupiedTreeMap.put(houseKey, house);
						allocatedTreeMap.put(studentKey, student);
						itr4.remove();
						itr3.remove();
						break;
					}
					
				}
			}
				
			//the next 4 iterations reduce the durations of full houses, housed students, free houses, non-housed students respectively, by 1
			Iterator<Integer> itr5 = occupiedTreeMap.keySet().iterator();
			while (itr5.hasNext()) {
				Integer houseKey = itr5.next();
				house house = occupiedTreeMap.get(houseKey);
				house.setFullDuration(house.getFullDuration() - 1);
			}
			
			Iterator<Integer> itr6 = allocatedTreeMap.keySet().iterator();
			while (itr6.hasNext()) {
				Integer studentKey = itr6.next();
				student student = allocatedTreeMap.get(studentKey);
				student.setDuration(student.getRemainingDuration() - 1);
			}
			
			Iterator<Integer> itr8 = houseTreeMap.keySet().iterator();
			while (itr8.hasNext()) {
				Integer houseKey = itr8.next();
				house house = houseTreeMap.get(houseKey);
				house.setFullDuration(house.getFullDuration() - 1);
			}
			
			Iterator<Integer> itr9 = studentTreeMap.keySet().iterator();
			while (itr9.hasNext()) {
				Integer studentKey = itr9.next();
				student student = studentTreeMap.get(studentKey);
				student.setDuration(student.getRemainingDuration() - 1);
			}
			
		}
		
		//this final iteration writes the output
		Iterator<Integer> itr7 = outputTreeMap.keySet().iterator();
		while (itr7.hasNext()) {
			Integer studentKey = itr7.next();
			student student = outputTreeMap.get(studentKey);
			out.println(student.getName());

		
		}
		
		in.close();
		out.close();
	}

}
