package EnglishToFrench;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class EnglishToFrench {

	public static Scanner scanner1, scanner2, scanner3;
	public static FileWriter fileWriter1,fileWriter2,fileWriter3;

	public static void main(String[] args) {
		long start=System.currentTimeMillis();
		long beforeUsedMem=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
		File file1 = new File("C:\\Users\\kumar\\Downloads\\TranslateWords Challenge\\find_words.txt");
		File file2 = new File("C:\\Users\\kumar\\Downloads\\TranslateWords Challenge\\french_dictionary.csv");
		File file3 = new File("C:\\Users\\kumar\\Downloads\\TranslateWords Challenge\\t8.shakespeare.txt");
		try {
			scanner1 = new Scanner(file1);
			List<String> list = new ArrayList<>();
			while (scanner1.hasNextLine()) {
				list.add(scanner1.nextLine());
			}
			Map<String, String> map = new HashMap<>();
			Map<String,Integer> map1=new TreeMap<>();
			scanner2 = new Scanner(file2);
			while (scanner2.hasNextLine()) {
				String[] arr = scanner2.nextLine().split(",");
				map.put(arr[0], arr[1]);
			}
			scanner3 = new Scanner(file3);
			fileWriter1 = new FileWriter("C:\\Users\\kumar\\Downloads\\TranslateWords Challenge\\t8.shakespeare.translated.txt");
			fileWriter2=new FileWriter("C:\\Users\\kumar\\Downloads\\TranslateWords Challenge\\frequency.csv");
			fileWriter3=new FileWriter("C:\\Users\\kumar\\Downloads\\TranslateWords Challenge\\performance.txt");
			fileWriter2.write("English Word,French Word,Frequency");
			fileWriter2.write("\n");
			while (scanner3.hasNextLine()) {
				String str = scanner3.nextLine();
				String[] temp = str.split(" ");
				StringBuilder sb1 = new StringBuilder();
				
				for (String s1 : temp) {
					int flag = 0;
					StringBuilder sb2 = new StringBuilder();
					for (String s2 : map.keySet()) {
						if(s1.equals("advantage")) {
							
								if(s2.equals("advantage")) {
									System.out.println("Success");
								}
						}
						if (s1.toLowerCase().contains(s2)) {
							if(map1.containsKey(s2)==false) {
								map1.put(s2, 1);
							}
							else {
								map1.put(s2,map1.get(s2)+1);
							}
							sb2.append(map.get(s2));
							flag = 1;
							break;
						}
					}
					if (flag == 1) {
						int flag1 = 1;
						for (int i = 0; i < s1.length(); i++) {
							if (Character.isLowerCase(s1.charAt(i))) {
								flag1 = 0;
								break;
							}
						}
						StringBuilder sb3 = new StringBuilder();
						for (int i = 0; i < s1.length(); i++) {
							if ((s1.charAt(i) >= 'A' && s1.charAt(i) <= 'Z')
									|| s1.charAt(i) >= 'a' && s1.charAt(i) <= 'z') {
								sb3.append(s1.charAt(i));
							}
						}
						if (flag1 == 1) {
							s1 = s1.replace(sb3.toString(), sb2.toString().toUpperCase());
						} else {
							if (Character.isUpperCase(sb3.charAt(0))) {
								sb2.setCharAt(0, Character.toUpperCase(sb2.charAt(0)));
							}
							s1 = s1.replace(sb3.toString(), sb2.toString());
						}

					}
					sb1.append(s1);
					sb1.append(" ");
				}
				fileWriter1.write(sb1.toString());
				fileWriter1.write("\n");
			}
			for(Map.Entry<String,Integer> entry:map1.entrySet()) {
				fileWriter2.write(entry.getKey()+","+map.get(entry.getKey())+","+entry.getValue());
				fileWriter2.write("\n");
			}
			long end=System.currentTimeMillis();
			float msec=end-start;
			int sec=(int)((msec/1000)%60);
			int min=(int)((msec/1000)/60);
			long afterUsedMem=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
			long actualMemUsed=(afterUsedMem-beforeUsedMem)/(1024L*1024L);
			fileWriter3.write("Time to Process: "+min+" minutes"+sec+" seconds.");
			fileWriter3.write("\n");
			fileWriter3.write("Memory Used: "+actualMemUsed+" MB");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			scanner1.close();
			scanner2.close();
			scanner3.close();
			try {
				fileWriter1.close();
				fileWriter2.close();
				fileWriter3.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
